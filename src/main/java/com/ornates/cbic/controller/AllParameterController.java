package com.ornates.cbic.controller;

import com.ornates.cbic.dao.pool.JDBCConnection;
import com.ornates.cbic.dao.result.GetExecutionSQL;
import com.ornates.cbic.model.response.AllParameterTotalScore;
import com.ornates.cbic.model.response.TotalScore;
import com.ornates.cbic.service.DateCalculate;
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
@RequestMapping("/cbic/allParameter")
@Controller
public class AllParameterController {
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
    @RequestMapping(value = "/total/parameter")
    //  http://localhost:8080/cbicApi/cbic/allParameter/total/parameter?month_date=2023-05-01&type=parameter						//1
    public Object TotalParameter(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<AllParameterTotalScore> allGstaList = new ArrayList<>();
        AllParameterTotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        try {

            if (type.equalsIgnoreCase("parameter")) { //  1
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String query_assessment = "WITH score_calculation AS (\n" +
                        "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, SUM(14c.GSTR_3BM_F) AS col3,(SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / NULLIF(SUM(14c.GSTR_3BM_F), 0)) AS total_score2\n" +
                        "    FROM mis_gst_commcode AS cc RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                        "    WHERE 14c.MM_YYYY = '2023-05-01' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE), \n" +
                        "current_data AS (\n" +
                        "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4,   SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9,  SUM(14c.OUTCOME_SECTION_61) AS col10,   SUM(14c.RETURN_SCRUTINY) AS col2  \n" +
                        "    FROM mis_gst_commcode AS cc RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE  \n" +
                        "    WHERE 14c.MM_YYYY = '2023-05-01' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME), \n" +
                        "previous_data AS (\n" +
                        "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col1 FROM mis_gst_commcode AS cc RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                        "    WHERE 14c.MM_YYYY = '2023-04-01' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME), \n" +
                        "combined_data AS (\n" +
                        "    SELECT current_data.ZONE_NAME, current_data.ZONE_CODE, (current_data.col4 + current_data.col9 + current_data.col10) / NULLIF((previous_data.col1 + current_data.col2), 0) AS n_d_score1 \n" +
                        "    FROM current_data JOIN previous_data ON current_data.ZONE_CODE = previous_data.ZONE_CODE), \n" +
                        "tax_recovery_data AS (\n" +
                        "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, CASE WHEN SUM(14c.TAX_LIABILITY_DETECTECT) = 0 THEN 0 ELSE SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) / SUM(14c.TAX_LIABILITY_DETECTECT)  END AS n_d_score2 FROM mis_gst_commcode AS cc  RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                        "    WHERE 14c.MM_YYYY <= '2023-05-01' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME), \n" +
                        "final_data AS (\n" +
                        "    SELECT cd.ZONE_NAME, cd.ZONE_CODE, cd.n_d_score1, trd.n_d_score2, cd.n_d_score1 + trd.n_d_score2 AS total_score3 FROM combined_data AS cd \n" +
                        "    LEFT JOIN tax_recovery_data AS trd ON cd.ZONE_CODE = trd.ZONE_CODE\n" +
                        "    UNION\n" +
                        "    SELECT trd.ZONE_NAME, trd.ZONE_CODE, cd.n_d_score1, trd.n_d_score2, cd.n_d_score1 + trd.n_d_score2 AS total_score3 FROM combined_data AS cd  RIGHT JOIN tax_recovery_data AS trd ON cd.ZONE_CODE = trd.ZONE_CODE)\n" +
                        "    SELECT COALESCE(sc.ZONE_NAME, fd.ZONE_NAME) AS ZONE_NAME, COALESCE(sc.ZONE_CODE, fd.ZONE_CODE) AS ZONE_CODE, sc.total_score2, fd.total_score3 FROM score_calculation sc LEFT JOIN final_data fd ON sc.ZONE_CODE = fd.ZONE_CODE;\n";

                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Double p1=null;Double p4=null;Double p5=null;Double p6=null;Double p7=null;Double p8=null;Double p9=null;Double p10=null;Double p11=null;
                    Double p2=rsGst14aa.getDouble("total_score2");
                    Double p3=rsGst14aa.getDouble("total_score3");
                    String commName = "ALL";


                    Double tScore = ((p2 * 5) + (p3 * 10)) / (10+5);

                    //double tScore = rsGst14aa.getDouble("total_score");
                    String formattedTotal = String.format("%.2f", tScore);
                    double total_score = Double.parseDouble(formattedTotal);
                    totalScore = new AllParameterTotalScore(zoneName, commName,zone_code, total_score, p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("commissary")) { //  2
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String query_assessment = "";

                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    double tScore = rsGst14aa.getDouble("total_score");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String commName = rsGst14aa.getString("COMM_NAME");
                    Double p1=null;Double p2=null;Double p3=null;Double p4=null;Double p5=null;Double p6=null;Double p7=null;Double p8=null;Double p9=null;Double p10=null;Double p11=null;

                    String formattedTotal = String.format("%.2f", tScore);
                    double total_score = Double.parseDouble(formattedTotal);
                    totalScore = new AllParameterTotalScore(zoneName, commName,zone_code, total_score, p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("month_wise")) { //  3
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String query_assessment = "";

                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    String commName = rsGst14aa.getString("COMM_NAME");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double tScore = rsGst14aa.getDouble("total_score");
                    Double p1=null;Double p2=null;Double p3=null;Double p4=null;Double p5=null;Double p6=null;Double p7=null;Double p8=null;Double p9=null;Double p10=null;Double p11=null;


                    String formattedTotal = String.format("%.2f", tScore);
                    double total_score = Double.parseDouble(formattedTotal);
                    totalScore = new AllParameterTotalScore(zoneName, commName,zone_code, total_score, p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("year_wise")) { //  4
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String query_assessment = "";

                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String commName = rsGst14aa.getString("COMM_NAME");
                    double tScore = rsGst14aa.getDouble("total_score");
                    Double p1=null;Double p2=null;Double p3=null;Double p4=null;Double p5=null;Double p6=null;Double p7=null;Double p8=null;Double p9=null;Double p10=null;Double p11=null;


                    String formattedTotal = String.format("%.2f", tScore);
                    double total_score = Double.parseDouble(formattedTotal);
                    totalScore = new AllParameterTotalScore(zoneName, commName,zone_code, total_score, p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11);
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
