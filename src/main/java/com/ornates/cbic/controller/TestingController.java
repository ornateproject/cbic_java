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
//@CrossOrigin
@RequestMapping("/cbic/t_score/testing")
@Controller
public class TestingController {
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
    @RequestMapping(value = "/refundsTesting") //7
    //  http://localhost:8080/cbicApi/cbic/t_score/testing/refundsTesting?month_date=2023-05-01&type=parameter			// for return filing button
    public Object refunds(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<TotalScore> allGstaList = new ArrayList<>();
        TotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        Integer Zonal_rank = 0;
        try {

            if (type.equalsIgnoreCase("parameter")) { // returnFiling all zone name 1
                //                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
                //String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String query_assessment = "WITH CTE AS ( SELECT \n"
                        + "        SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS col16, \n"
                        + "        SUM(14c.age_breakup_above60_no) AS col22, cc.ZONE_CODE,zc.ZONE_NAME\n"
                        + "    FROM mis_gst_commcode AS cc \n"
                        + "    RIGHT JOIN mis_dpm_gst_4 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
                        + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                        + "    WHERE 14c.MM_YYYY = '" + month_date + "' \n"
                        + "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n"
                        + ")\n"
                        + "SELECT ZONE_CODE, ZONE_NAME, col16, col22, (col22 * 100 / col16) AS total_score,\n"
                        + "CONCAT(col22, '/', col16) AS absolute_value,\n"
                        + "    ROW_NUMBER() OVER (ORDER BY (col22 / col16) ) AS z_rank\n"
                        + "FROM CTE ORDER BY total_score ASC;";

                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    Zonal_rank = rsGst14aa.getInt("z_rank");
                    double tScore = rsGst14aa.getDouble("total_score"); // 100 is multiply in query
                    String absval = rsGst14aa.getString("absolute_value");
                    String commName = "ALL";
                    String gst = "ALL";
                    //String ra ="null";
                    String ra = RelevantAspect.Gst7_RA;

                    String formattedTotal = String.format("%.2f", tScore);
                    double total_score = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks7(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    //System.out.println(way_to_grade);
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
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
