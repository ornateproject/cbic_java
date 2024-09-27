package com.ornates.cbic.controller;

import com.ornates.cbic.dao.pool.JDBCConnection;
import com.ornates.cbic.dao.result.GetExecutionSQL;
import com.ornates.cbic.model.response.MonthlyYearlyScore;
import com.ornates.cbic.model.response.TotalScore;
import com.ornates.cbic.service.RelevantAspect;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@CrossOrigin
@RequestMapping("/cbic/Last3Month")
@Controller
public class Last3MonthController {
    @ResponseBody
    @RequestMapping(value = "/")
    public String home() {
        return "its working api";
    }

    /*
     * Date: july 2, 2024
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
    @RequestMapping(value = "return_filling/month")
    //  http://localhost:8080/cbicApi/cbic/MonthYear/return_filling/month?type=last3month&month_date=2024-04-01&zone_code=70
    // http://localhost:8080/cbicApi/cbic/MonthYear/return_filling/month?type=monthly&month_date=2024-04-30
    // http://localhost:8080/cbicApi/cbic/MonthYear/return_filling/month?type=comm_name&month_date=2024-04-30&zone_code=70
    public Object returnFiling(@RequestParam String type, @RequestParam String month_date, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String comm_name) {
        List<MonthlyYearlyScore> allGstaList = new ArrayList<>();
        MonthlyYearlyScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa= null;
        ResultSet rsGst14aa_gst2= null;ResultSet rsGst14aa_gst7= null;
        ResultSet currentMonthValue= null;
        double total_score_of_year = 0;
        double total_score_previous_year = 0;
        double total_score_previous_year_2 = 0;
        try {
            // for parameter 1
                //     '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
                String query_assessment_for_returnFiling= "SELECT zc.ZONE_NAME,cc.ZONE_CODE,'Return Filling' AS parameter_name,\n"
                        + "    (SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / NULLIF(SUM(14c.GSTR_3BM_F), 0)) * 100 AS total_score_of_month,\n"
                        + "    (SELECT (SUM(sub1.GSTR_3BM_F - sub1.GSTR_3BM_D) / NULLIF(SUM(sub1.GSTR_3BM_F), 0)) * 100\n"
                        + "     FROM mis_gst_gst_2 AS sub1\n"
                        + "     JOIN mis_gst_commcode AS cc1 ON cc1.COMM_CODE = sub1.COMM_CODE\n"
                        + "     WHERE sub1.MM_YYYY = DATE_FORMAT(DATE_SUB('" + month_date + "', INTERVAL 1 MONTH), '%Y-%m-01')AND cc1.ZONE_CODE = cc.ZONE_CODE\n"
                        + "    ) AS total_score_previous_month,\n"
                        + "    (SELECT (SUM(sub2.GSTR_3BM_F - sub2.GSTR_3BM_D) / NULLIF(SUM(sub2.GSTR_3BM_F), 0)) * 100\n"
                        + "     FROM mis_gst_gst_2 AS sub2\n"
                        + "     JOIN mis_gst_commcode AS cc2 ON cc2.COMM_CODE = sub2.COMM_CODE\n"
                        + "     WHERE sub2.MM_YYYY = DATE_FORMAT(DATE_SUB('" + month_date + "', INTERVAL 2 MONTH), '%Y-%m-01')AND cc2.ZONE_CODE = cc.ZONE_CODE\n"
                        + "    ) AS total_score_previous_month_2\n"
                        + "FROM mis_gst_commcode AS cc \n"
                        + "RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
                        + "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                        + "WHERE 14c.MM_YYYY = '" + month_date + "'  AND cc.ZONE_CODE = '" + zone_code + "'\n"
                        + "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE;\n"
                        + "";


                rsGst14aa_gst2 = GetExecutionSQL.getResult(query_assessment_for_returnFiling);

                while (rsGst14aa_gst2.next()) {
                    String zoneName = rsGst14aa_gst2.getString("ZONE_NAME");
                    zone_code = rsGst14aa_gst2.getString("ZONE_CODE");
                    double current_t_score = rsGst14aa_gst2.getDouble("total_score_of_month");
                    double previous_t_score = rsGst14aa_gst2.getDouble("total_score_previous_month");
                    double previous_t_score_2 = rsGst14aa_gst2.getDouble("total_score_previous_month_2");
                    String commName = "ALL";
                    String parameter_name = rsGst14aa_gst2.getString("parameter_name");

                    // Formatting the total score
                    String formattedTotal = String.format("%.2f", current_t_score);
                    double total_score_of_month = Double.parseDouble(formattedTotal);

                    String formattedTotal2 = String.format("%.2f", previous_t_score);
                    double total_score_previous_month = Double.parseDouble(formattedTotal2);

                    String formattedTotal3 = String.format("%.2f", previous_t_score_2);
                    double total_score_previous_month_2 = Double.parseDouble(formattedTotal3);

                    totalScore = new MonthlyYearlyScore(zoneName, commName, zone_code, parameter_name, total_score_of_month, total_score_previous_month,
                            total_score_previous_month_2, total_score_of_year, total_score_previous_year, total_score_previous_year_2);
                    allGstaList.add(totalScore);
                }
                System.out.println("last 3 month");
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
        return allGstaList;
    }

}
