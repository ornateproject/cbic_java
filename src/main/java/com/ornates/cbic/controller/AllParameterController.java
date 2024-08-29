
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
//
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
     * Purpose: This methods for performing check the database
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
    @RequestMapping(value = "/total/parameter") // for front page graph
//  http://localhost:8080/cbicApi/cbic/allParameter/total/parameter?month_date=2023-05-01&type=parameter					//1no url for last month
//  http://localhost:8080/cbicApi/cbic/allParameter/total/parameter?month_date=2023-05-01&zone_code=70&type=zone_wise					//2no url
    public Object returnFiling(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<AllParameterTotalScore> allGstaList = new ArrayList<>();
        AllParameterTotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa= null;
        try {
            //      this query make with gst2 and gst7 only
            if (type.equalsIgnoreCase("parameter")) { // for parameter 1
                //     '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                String next_month_new = DateCalculate.getNextMonth(month_date);

                String query_assessment = "WITH score_calculation AS (\n" +
                        "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS 2_col21, \n" +
                        "        SUM(14c.GSTR_3BM_F) AS 2_col3,(SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / NULLIF(SUM(14c.GSTR_3BM_F), 0)) * 100 AS total_score_gst2\n" +
                        "    FROM mis_gst_commcode AS cc \n" +
                        "    RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                        "    WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
                        "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                        "),\n" +
                        "CTE AS (\n" +
                        "    SELECT SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS 7_col16, \n" +
                        "        SUM(14c.age_breakup_above60_no) AS 7_col22, cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        "    FROM mis_gst_commcode AS cc \n" +
                        "    RIGHT JOIN mis_dpm_gst_4 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                        "    WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
                        "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        ")\n" +
                        "SELECT sc.ZONE_NAME,sc.ZONE_CODE,\n" +
                        "    COALESCE(sc.2_col21, 0) AS 2_col21,COALESCE(sc.2_col3, 0) AS 2_col3,\n" +
                        "    COALESCE(sc.total_score_gst2, 0) AS total_score_gst2,COALESCE(c.7_col16, 0) AS 7_col16,\n" +
                        "    COALESCE(c.7_col22, 0) AS 7_col22,COALESCE((c.7_col22 * 100 / NULLIF(c.7_col16, 0)), 0) AS total_score_gst7\n" +
                        "FROM score_calculation sc\n" +
                        "LEFT JOIN CTE c ON sc.ZONE_CODE = c.ZONE_CODE AND sc.ZONE_NAME = c.ZONE_NAME\n" +
                        "UNION\n" +
                        "SELECT \n" +
                        "    c.ZONE_NAME,c.ZONE_CODE,COALESCE(sc.2_col21, 0) AS 2_col21,\n" +
                        "    COALESCE(sc.2_col3, 0) AS 2_col3,COALESCE(sc.total_score_gst2, 0) AS total_score_gst2,\n" +
                        "    COALESCE(c.7_col16, 0) AS 7_col16,COALESCE(c.7_col22, 0) AS 7_col22,\n" +
                        "    COALESCE((c.7_col22 * 100 / NULLIF(c.7_col16, 0)), 0) AS total_score_gst7\n" +
                        "FROM score_calculation sc\n" +
                        "RIGHT JOIN CTE c ON sc.ZONE_CODE = c.ZONE_CODE AND sc.ZONE_NAME = c.ZONE_NAME\n" +
                        "ORDER BY ZONE_NAME, ZONE_CODE;\n";

                rsGst14aa = GetExecutionSQL.getResult(query_assessment);
                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    double total_score_gst2 = rsGst14aa.getDouble("total_score_gst2");
                    double total_score_gst7 = rsGst14aa.getDouble("total_score_gst7");
                    double tScore = (total_score_gst2 + total_score_gst7) / 2;

                    String commName = "ALL";
                    String parameter_name = "null";
                    String formattedTotal = String.format("%.2f", tScore);
                    double total_score = Double.parseDouble(formattedTotal);
                    totalScore = new AllParameterTotalScore(zoneName, commName, zone_code, total_score,parameter_name);
                    allGstaList.add(totalScore);
                }

            } else if (type.equalsIgnoreCase("zone_wise")) { // for zone_wise 2
//			     '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                String next_month_new = DateCalculate.getNextMonth(month_date);

                String query_assessment = "WITH \n"
                        + "recovery_Arrears AS (\n"
                        + "    SELECT cc.ZONE_CODE, zc.ZONE_NAME, (SUM(14c.CLOSING_AMT) - SUM(14c.BELOW_YEAR_AMT)) / SUM(14c.CLOSING_AMT) AS total_score8\n"
                        + "    FROM mis_gst_commcode AS cc RIGHT JOIN mis_tar_gst_3_new AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                        + "    WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n"
                        + "),\n"
                        + "prosecution_data AS (\n"
                        + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.PROSECUTION_LAUNCHED) / SUM(14c.ARRESTS_MADE) AS total_score9\n"
                        + "    FROM mis_gst_commcode AS cc RIGHT JOIN mis_gi_gst_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                        + "    WHERE 14c.MM_YYYY <= '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n"
                        + "),\n"
                        + "refunds AS (\n"
                        + "    SELECT cc.ZONE_CODE, zc.ZONE_NAME,SUM(14c.age_breakup_above60_no) / SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS total_score7\n"
                        + "    FROM mis_gst_commcode AS cc RIGHT JOIN mis_dpm_gst_4 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                        + "    WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n"
                        + "),\n"
                        + "audit1 AS (\n"
                        + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,(SUM(TAXPAYER_AUDITED_NO_LARGE + TAXPAYER_AUDITED_NO_MEDIUM + TAXPAYER_AUDITED_NO_SMALL) / \n"
                        + "        (2 * SUM(TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL) / 12)) AS audit_score1\n"
                        + "    FROM mis_gst_commcode AS cc RIGHT JOIN mis_dga_gst_adt_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                        + "    WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n"
                        + "),\n"
                        + "audit2 AS (\n"
                        + "    SELECT cc.ZONE_CODE, zc.ZONE_NAME,(SUM(14c.AUDIT_PARAS_BREAKUP_6_12_NO_LARGE + 14c.AUDIT_PARAS_BREAKUP_6_12_NO_MEDIUM + 14c.AUDIT_PARAS_BREAKUP_6_12_NO_SMALL) + \n"
                        + "        SUM(14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_LARGE + 14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_MEDIUM + 14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_SMALL)) / SUM(14c.AUDIT_PARAS_CLOSING_NO_LARGE + 14c.AUDIT_PARAS_CLOSING_NO_MEDIUM + 14c.AUDIT_PARAS_CLOSING_NO_SMALL) AS audit_score2\n"
                        + "    FROM mis_gst_commcode AS cc RIGHT JOIN mis_dga_gst_adt_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                        + "    WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n"
                        + "),\n"
                        + "DisposalData AS (-- adjudication_legacy\n"
                        + "    SELECT cc.ZONE_CODE, SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9\n"
                        + "    FROM mis_gst_commcode AS cc RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
                        + "    WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "' GROUP BY cc.ZONE_CODE\n"
                        + "),\n"
                        + "ClosingData AS (\n"
                        + "    SELECT cc.ZONE_CODE, SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n"
                        + "    FROM mis_gst_commcode AS cc RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
                        + "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND cc.ZONE_CODE = '" + zone_code + "' GROUP BY cc.ZONE_CODE\n"
                        + "),\n"
                        + "Subpara1 AS (\n"
                        + "    SELECT zc.ZONE_NAME, d.ZONE_CODE, d.col9 / c.col3 AS total_score_of_subpara1\n"
                        + "    FROM DisposalData d  LEFT JOIN ClosingData c ON d.ZONE_CODE = c.ZONE_CODE \n"
                        + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = d.ZONE_CODE\n"
                        + "),\n"
                        + "Subpara2 AS (\n"
                        + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n"
                        + "        (SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) / SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO)) AS total_score_of_subpara2\n"
                        + "    FROM mis_gst_commcode AS cc RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                        + "    WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n"
                        + "),\n"
                        + "Subpara3 AS (\n"
                        + "    SELECT t1.ZONE_NAME, t1.ZONE_CODE, \n"
                        + "        CASE WHEN t2.col3 != 0 THEN t1.col9 / t2.col3 ELSE NULL END AS total_score_of_subpara3\n"
                        + "    FROM ( SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9\n"
                        + "        FROM mis_gst_commcode AS cc RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                        + "        WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "' GROUP BY cc.ZONE_CODE\n"
                        + "    ) AS t1\n"
                        + "    LEFT JOIN (\n"
                        + "        SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n"
                        + "        FROM mis_gst_commcode AS cc RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                        + "        WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND cc.ZONE_CODE = '" + zone_code + "' GROUP BY cc.ZONE_CODE\n"
                        + "    ) AS t2 ON t1.ZONE_CODE = t2.ZONE_CODE\n"
                        + "),\n"
                        + "Subpara4 AS (\n"
                        + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) AS col18, SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col13,\n"
                        + "        SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) / SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS total_score_of_subpara4\n"
                        + "    FROM mis_gst_commcode AS cc RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                        + "    WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "' GROUP BY cc.ZONE_CODE\n"
                        + ")\n"
                        + "SELECT ra.ZONE_CODE, ra.ZONE_NAME,ra.total_score8,COALESCE(pd.total_score9, 0.00) AS total_score9,COALESCE(rf.total_score7, 0.00) AS total_score7,\n"
                        + "    COALESCE(a1.audit_score1, 0.00) + COALESCE(a2.audit_score2, 0.00) AS total_score10,\n"
                        + "    (sp1.total_score_of_subpara1 + sp2.total_score_of_subpara2 + sp3.total_score_of_subpara3 + sp4.total_score_of_subpara4) AS total_score6\n"
                        + "FROM recovery_Arrears ra\n"
                        + "LEFT JOIN prosecution_data pd ON ra.ZONE_CODE = pd.ZONE_CODE\n"
                        + "LEFT JOIN refunds rf ON ra.ZONE_CODE = rf.ZONE_CODE\n"
                        + "LEFT JOIN audit1 a1 ON ra.ZONE_CODE = a1.ZONE_CODE\n"
                        + "LEFT JOIN audit2 a2 ON ra.ZONE_CODE = a2.ZONE_CODE\n"
                        + "LEFT JOIN Subpara1 sp1 ON ra.ZONE_CODE = sp1.ZONE_CODE\n"
                        + "LEFT JOIN Subpara2 sp2 ON sp1.ZONE_CODE = sp2.ZONE_CODE\n"
                        + "LEFT JOIN Subpara3 sp3 ON sp1.ZONE_CODE = sp3.ZONE_CODE\n"
                        + "LEFT JOIN Subpara4 sp4 ON sp1.ZONE_CODE = sp4.ZONE_CODE;";

                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    String commName = "ALL";
                    //String p1 = null, p2 = null, p3 = null, p4 = null, p5 = null, p11 = null;

                    // p6uf:- mean un-formatted, I will format in percentage
	      			  /*  String p6uf = rsGst14aa.getString("total_score6");
	      			    String p7uf = rsGst14aa.getString("total_score7");
	      			    String p8uf = rsGst14aa.getString("total_score8");
	      			    String p9uf = rsGst14aa.getString("total_score9");
	      			    String p10uf = rsGst14aa.getString("total_score10");

	      			    double p6d = Double.parseDouble(p6uf);
	      			    double p7d = Double.parseDouble(p7uf);
	      			    double p8d = Double.parseDouble(p8uf);
	      			    double p9d = Double.parseDouble(p9uf);
	      			    double p10d = Double.parseDouble(p10uf);

	      			    Double tScore = ((p6d * 10) + (p7d * 5) + (p8d * 8) + (p9d * 6) + (p10d * 12)) / (10 + 5 + 8 + 6 + 12);

	      			    // Sum of all parameters
	      			    double add_all_parameter = p6d + p7d + p8d + p9d + p10d;

	      			    // Calculate percentages
	      			    double p6p = (p6d / add_all_parameter) * 100;
	      			    double p7p = (p7d / add_all_parameter) * 100;
	      			    double p8p = (p8d / add_all_parameter) * 100;
	      			    double p9p = (p9d / add_all_parameter) * 100;
	      			    double p10p = (p10d / add_all_parameter) * 100;

	      			    // Formatting p6p and p7p as percentages
	      			    String p6 = String.format("%.2f%%", p6p);
	      			    String p7 = String.format("%.2f%%", p7p);
	      			    String p8 = String.format("%.2f%%", p8p);
	      			    String p9 = String.format("%.2f%%", p9p);
	      			    String p10 = String.format("%.2f%%", p10p);*/

                    // Formatting the total score
//	      			    String formattedTotal = String.format("%.2f", tScore);
//	      			    double total_score = Double.parseDouble(formattedTotal);
//	      			    totalScore = new AllParameterTotalScore(zoneName, commName, zone_code, total_score, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11);
                    // totalScore = new AllParameterTotalScore(zoneName, commName, zone_code, total_score);
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
