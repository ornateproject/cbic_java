package com.ornates.cbic.controller;

import com.ornates.cbic.dao.Query.CGSTParameterWiseQuery;
import com.ornates.cbic.dao.Query.MISQuery;
import com.ornates.cbic.dao.pool.JDBCConnection;
import com.ornates.cbic.dao.result.GetExecutionSQL;
import com.ornates.cbic.model.response.MonthlyYearlyScore;
import com.ornates.cbic.model.response.TotalScore;
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

//@CrossOrigin
@RequestMapping("/cbic/MIS")
@Controller
public class MISController {
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
    @RequestMapping(value = "/scrutiny/assessment") //3
    //  http://localhost:8080/cbicApi/cbic/MIS/scrutiny/assessment?month_date=2024-04-01&zone_code=59&type=CurrentMonth
    //  http://localhost:8080/cbicApi/cbic/MIS/scrutiny/assessment?month_date=2024-04-01&zone_code=59&type=1_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/scrutiny/assessment?month_date=2024-04-01&zone_code=59&type=2_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/scrutiny/assessment?month_date=2024-04-01&zone_code=59&type=3_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/scrutiny/assessment?month_date=2024-04-01&zone_code=59&type=4_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/scrutiny/assessment?month_date=2024-04-01&zone_code=59&type=5_MonthBack
    public Object scrutinyAssessment(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<TotalScore> allGstaList = new ArrayList<>();
        TotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        //double median =0.00;

        try {

            if (type.equalsIgnoreCase("CurrentMonth")) {
                String query_assessment = new MISQuery().QueryFor_Scrutiny_CurrentMonth(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double total3a = rsGst14aa.getDouble("score_of_subpara3a");
                    double total3b = rsGst14aa.getDouble("score_of_parameter3b");

                    double median3a = rsGst14aa.getDouble("median_numerator_3a");
                    double median3b = rsGst14aa.getDouble("median_numerator_3b");

                    Double numerator_3a = rsGst14aa.getDouble("numerator_3a");
                    Double numerator_3b = rsGst14aa.getDouble("numerator_3b");

                    int way_to_grade3a = score.marks3a(total3a);
                    int way_to_grade3b = score.marks3b(total3b);

                    int insentavization3a = way_to_grade3a;
                    int insentavization3b = way_to_grade3b;

                    if (numerator_3a > median3a && way_to_grade3a < 10) {
                        insentavization3a += 1;
                    }
                    if (numerator_3b > median3b && way_to_grade3b < 10) {
                        insentavization3b += 1;
                    }

                    Integer way_to_grade = way_to_grade3a + way_to_grade3b;
                    Integer insentavization = insentavization3a + insentavization3b;

                    double sub_parameter_weighted_average3a = insentavization3a * 0.5;
                    double sub_parameter_weighted_average3b = insentavization3b * 0.5;

                    double total_weighted_average = sub_parameter_weighted_average3a + sub_parameter_weighted_average3b;
                    double total_score = 0.00;
                    Integer Zonal_rank = 0;
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    String ra = "SCRUTINY & ASSESSMENT";
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
                    allGstaList.add(totalScore);

                }

            }else if (type.equalsIgnoreCase("1_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Scrutiny_1_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double total3a = rsGst14aa.getDouble("score_of_subpara3a");
                    double total3b = rsGst14aa.getDouble("score_of_parameter3b");

                    double median3a = rsGst14aa.getDouble("median_numerator_3a");
                    double median3b = rsGst14aa.getDouble("median_numerator_3b");

                    Double numerator_3a = rsGst14aa.getDouble("numerator_3a");
                    Double numerator_3b = rsGst14aa.getDouble("numerator_3b");

                    int way_to_grade3a = score.marks3a(total3a);
                    int way_to_grade3b = score.marks3b(total3b);

                    int insentavization3a = way_to_grade3a;
                    int insentavization3b = way_to_grade3b;

                    if (numerator_3a > median3a && way_to_grade3a < 10) {
                        insentavization3a += 1;
                    }
                    if (numerator_3b > median3b && way_to_grade3b < 10) {
                        insentavization3b += 1;
                    }

                    Integer way_to_grade = way_to_grade3a + way_to_grade3b;
                    Integer insentavization = insentavization3a + insentavization3b;

                    double sub_parameter_weighted_average3a = insentavization3a * 0.5;
                    double sub_parameter_weighted_average3b = insentavization3b * 0.5;

                    double total_weighted_average = sub_parameter_weighted_average3a + sub_parameter_weighted_average3b;
                    double total_score = 0.00;
                    Integer Zonal_rank = 0;
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    String ra = "SCRUTINY & ASSESSMENT";
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("2_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Scrutiny_2_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double total3a = rsGst14aa.getDouble("score_of_subpara3a");
                    double total3b = rsGst14aa.getDouble("score_of_parameter3b");

                    double median3a = rsGst14aa.getDouble("median_numerator_3a");
                    double median3b = rsGst14aa.getDouble("median_numerator_3b");

                    Double numerator_3a = rsGst14aa.getDouble("numerator_3a");
                    Double numerator_3b = rsGst14aa.getDouble("numerator_3b");

                    int way_to_grade3a = score.marks3a(total3a);
                    int way_to_grade3b = score.marks3b(total3b);

                    int insentavization3a = way_to_grade3a;
                    int insentavization3b = way_to_grade3b;

                    if (numerator_3a > median3a && way_to_grade3a < 10) {
                        insentavization3a += 1;
                    }
                    if (numerator_3b > median3b && way_to_grade3b < 10) {
                        insentavization3b += 1;
                    }

                    Integer way_to_grade = way_to_grade3a + way_to_grade3b;
                    Integer insentavization = insentavization3a + insentavization3b;

                    double sub_parameter_weighted_average3a = insentavization3a * 0.5;
                    double sub_parameter_weighted_average3b = insentavization3b * 0.5;

                    double total_weighted_average = sub_parameter_weighted_average3a + sub_parameter_weighted_average3b;
                    double total_score = 0.00;
                    Integer Zonal_rank = 0;
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    String ra = "SCRUTINY & ASSESSMENT";
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("3_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Scrutiny_3_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double total3a = rsGst14aa.getDouble("score_of_subpara3a");
                    double total3b = rsGst14aa.getDouble("score_of_parameter3b");

                    double median3a = rsGst14aa.getDouble("median_numerator_3a");
                    double median3b = rsGst14aa.getDouble("median_numerator_3b");

                    Double numerator_3a = rsGst14aa.getDouble("numerator_3a");
                    Double numerator_3b = rsGst14aa.getDouble("numerator_3b");

                    int way_to_grade3a = score.marks3a(total3a);
                    int way_to_grade3b = score.marks3b(total3b);

                    int insentavization3a = way_to_grade3a;
                    int insentavization3b = way_to_grade3b;

                    if (numerator_3a > median3a && way_to_grade3a < 10) {
                        insentavization3a += 1;
                    }
                    if (numerator_3b > median3b && way_to_grade3b < 10) {
                        insentavization3b += 1;
                    }

                    Integer way_to_grade = way_to_grade3a + way_to_grade3b;
                    Integer insentavization = insentavization3a + insentavization3b;

                    double sub_parameter_weighted_average3a = insentavization3a * 0.5;
                    double sub_parameter_weighted_average3b = insentavization3b * 0.5;

                    double total_weighted_average = sub_parameter_weighted_average3a + sub_parameter_weighted_average3b;
                    double total_score = 0.00;
                    Integer Zonal_rank = 0;
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    String ra = "SCRUTINY & ASSESSMENT";
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("4_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Scrutiny_4_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double total3a = rsGst14aa.getDouble("score_of_subpara3a");
                    double total3b = rsGst14aa.getDouble("score_of_parameter3b");

                    double median3a = rsGst14aa.getDouble("median_numerator_3a");
                    double median3b = rsGst14aa.getDouble("median_numerator_3b");

                    Double numerator_3a = rsGst14aa.getDouble("numerator_3a");
                    Double numerator_3b = rsGst14aa.getDouble("numerator_3b");

                    int way_to_grade3a = score.marks3a(total3a);
                    int way_to_grade3b = score.marks3b(total3b);

                    int insentavization3a = way_to_grade3a;
                    int insentavization3b = way_to_grade3b;

                    if (numerator_3a > median3a && way_to_grade3a < 10) {
                        insentavization3a += 1;
                    }
                    if (numerator_3b > median3b && way_to_grade3b < 10) {
                        insentavization3b += 1;
                    }

                    Integer way_to_grade = way_to_grade3a + way_to_grade3b;
                    Integer insentavization = insentavization3a + insentavization3b;

                    double sub_parameter_weighted_average3a = insentavization3a * 0.5;
                    double sub_parameter_weighted_average3b = insentavization3b * 0.5;

                    double total_weighted_average = sub_parameter_weighted_average3a + sub_parameter_weighted_average3b;
                    double total_score = 0.00;
                    Integer Zonal_rank = 0;
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    String ra = "SCRUTINY & ASSESSMENT";
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("5_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Scrutiny_1_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double total3a = rsGst14aa.getDouble("score_of_subpara3a");
                    double total3b = rsGst14aa.getDouble("score_of_parameter3b");

                    double median3a = rsGst14aa.getDouble("median_numerator_3a");
                    double median3b = rsGst14aa.getDouble("median_numerator_3b");

                    Double numerator_3a = rsGst14aa.getDouble("numerator_3a");
                    Double numerator_3b = rsGst14aa.getDouble("numerator_3b");

                    int way_to_grade3a = score.marks3a(total3a);
                    int way_to_grade3b = score.marks3b(total3b);

                    int insentavization3a = way_to_grade3a;
                    int insentavization3b = way_to_grade3b;

                    if (numerator_3a > median3a && way_to_grade3a < 10) {
                        insentavization3a += 1;
                    }
                    if (numerator_3b > median3b && way_to_grade3b < 10) {
                        insentavization3b += 1;
                    }

                    Integer way_to_grade = way_to_grade3a + way_to_grade3b;
                    Integer insentavization = insentavization3a + insentavization3b;

                    double sub_parameter_weighted_average3a = insentavization3a * 0.5;
                    double sub_parameter_weighted_average3b = insentavization3b * 0.5;

                    double total_weighted_average = sub_parameter_weighted_average3a + sub_parameter_weighted_average3b;
                    double total_score = 0.00;
                    Integer Zonal_rank = 0;
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    String ra = "SCRUTINY & ASSESSMENT";
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
                    allGstaList.add(totalScore);
                }            }
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
                .sorted(Comparator.comparing(TotalScore::getSub_parameter_weighted_average).reversed())
                .collect(Collectors.toList());
    }

}
