package com.ornates.cbic.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ornates.cbic.dao.pool.JDBCConnection;
import com.ornates.cbic.dao.result.GetExecutionSQL;
//import com.ornates.cbic.model.response.AllParameterTotalScore;
import com.ornates.cbic.model.response.MonthlyYearlyScore;
import com.ornates.cbic.service.DateCalculate;

//@CrossOrigin
@RequestMapping("/cbic/MonthYear")
@Controller
public class LastMonthYearController {
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
    @RequestMapping(value = "/month/year")
    //  http://localhost:8080/cbicApi/cbic/MonthYear/month/year?type=last3month&month_date=2023-05-01&zone_code=70
    public Object returnFiling(@RequestParam String type, @RequestParam String month_date, @RequestParam String zone_code) {
        List<MonthlyYearlyScore> allGstaList = new ArrayList<>();
        MonthlyYearlyScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa= null;
        ResultSet currentMonthValue= null;
        try {

            if (type.equalsIgnoreCase("last3month")) { // for parameter 1      zone wise
                //     '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'

//					String curentMonth = "SELECT DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%Y-%m-%d') AS month_date;";
//					currentMonthValue = GetExecutionSQL.getResult(curentMonth);
//					String month_date = currentMonthValue.getString("last_month");
//					String prev_month_new = DateCalculate.getPreviousMonth(month_date);
//		            String next_month_new = DateCalculate.getNextMonth(month_date);

                String query_assessment ="SELECT zc.ZONE_NAME,cc.ZONE_CODE,'Return Filling' AS parameter_name,\n"
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



                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    double current_t_score = rsGst14aa.getDouble("total_score_of_month");
                    double previous_t_score = rsGst14aa.getDouble("total_score_previous_month");
                    double previous_t_score_2 = rsGst14aa.getDouble("total_score_previous_month_2");
                    String commName = "ALL";
                    String parameter_name = rsGst14aa.getString("parameter_name");
                    double total_score_of_year = 0;
                    double total_score_previous_year = 0;
                    double total_score_previous_year_2 = 0;


                    // Formatting the total score
                    String formattedTotal = String.format("%.2f", current_t_score);
                    double total_score_of_month = Double.parseDouble(formattedTotal);

                    String formattedTotal2 = String.format("%.2f", previous_t_score);
                    double total_score_previous_month = Double.parseDouble(formattedTotal2);

                    String formattedTotal3 = String.format("%.2f", previous_t_score_2);
                    double total_score_previous_month_2 = Double.parseDouble(formattedTotal3);
                    totalScore = new MonthlyYearlyScore(zoneName, commName, zone_code,parameter_name, total_score_of_month,total_score_previous_month,
                            total_score_previous_month_2,total_score_of_year,total_score_previous_year,total_score_previous_year_2);
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
        return allGstaList;
    }
}








/*
else if (type.equalsIgnoreCase("yearly")) { // for zone_wise 2
//'" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
String prev_month_new = DateCalculate.getPreviousMonth(month_date);
String next_month_new = DateCalculate.getNextMonth(month_date);

String query_assessment = "";

 rsGst14aa = GetExecutionSQL.getResult(query_assessment);

   while (rsGst14aa.next()) {
   	String zoneName = rsGst14aa.getString("ZONE_NAME");
		    zone_code = rsGst14aa.getString("ZONE_CODE");
		  double tScore = rsGst14aa.getDouble("total_score");
		    String commName = "ALL";

		    // Formatting the total score
		    String formattedTotal = String.format("%.2f", tScore);
		    double total_score = Double.parseDouble(formattedTotal);
		    totalScore = new AllParameterTotalScore(zoneName, commName, zone_code, total_score);
		    allGstaList.add(totalScore);
   }
}
*/