package com.ornates.cbic.controller;
import com.ornates.cbic.dao.Query.CGSTParameterWiseQuery;
import com.ornates.cbic.dao.pool.JDBCConnection;
import com.ornates.cbic.dao.result.GetExecutionSQL;
import com.ornates.cbic.model.response.TotalScore;
import com.ornates.cbic.service.DateCalculate;
import com.ornates.cbic.service.GradeScore;

import com.ornates.cbic.service.RelevantAspect;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@RequestMapping("/cbic/testing2")
@Controller
public class TestingController2 {
    GradeScore score=new GradeScore();
    @ResponseBody
    @RequestMapping(value = "/")
    public String home() {
        return "its working api";
    }

    /*
     * Date: june 4, 2024
     * Purpose: This methods for performing check the database  .
     */
    @ResponseBody
    @RequestMapping(value = "/db_check")
    public String bdTest() {

        //Connection done
        Connection con = JDBCConnection.getTNConnection();
        System.out.println("Connection :"+con);
        return "its working api";
    }

    @ResponseBody
    @RequestMapping(value = "/scrutiny/assessment")
//  http://localhost:8080/cbicApi/cbic/testing2/scrutiny/assessment?month_date=2023-05-01&type=commissary&zone_code=59 // for show button, zone wise
    public Object scrutinyAssessment(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<TotalScore> allGstaList = new ArrayList<>();
        TotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        double median = 0.00;
        try {
            if (type.equalsIgnoreCase("commissary")) {
                // My SQL query
                String query_assessment = new CGSTParameterWiseQuery().QueryForScrutinyAssessment_3_ParticularSubparameterWise(month_date, zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    String gst = rsGst14aa.getString("gst");
                    String absval = rsGst14aa.getString("absval");
                    Double t_score = rsGst14aa.getDouble("score_of_parameter");
                    median = rsGst14aa.getDouble("median_numerator");
                    Double numerator = rsGst14aa.getDouble("numerator");

                    String formattedTotal = String.format("%.2f", t_score);
                    double total_score = Double.parseDouble(formattedTotal);

                    int way_to_grade;
                    int insentavization;

                    // Logic based on parameter type
                    if ("GST3A".equalsIgnoreCase(gst)) {
                        way_to_grade = score.marks3a(total_score);
                        insentavization = score.marks3a(total_score);
                    } else if ("GST3B".equalsIgnoreCase(gst)) {
                        way_to_grade = score.marks3b(total_score);
                        insentavization = score.marks3b(total_score);

                        if (numerator > median && way_to_grade < 10) {
                            insentavization += 1;
                        }
                    } else {
                        // Default handling if parameter type is neither 3a nor 3b
                        way_to_grade = 0;
                        insentavization = 0;
                    }

                    Integer Zonal_rank = null;
                    String commName = "null";
                    String ra = "SCRUTINY & ASSESSMENT";

                    Double sub_parameter_weighted_average = insentavization * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rsGst14aa != null) rsGst14aa.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(TotalScore::getTotalScore).reversed()).collect(Collectors.toList());































    }

}
