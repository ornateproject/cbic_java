package com.ornates.cbic.controller;

import com.ornates.cbic.dao.pool.JDBCConnection;
import com.ornates.cbic.dao.result.GetExecutionSQL;
import com.ornates.cbic.model.response.TotalScore;
import com.ornates.cbic.service.DateCalculate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// total score 1(commissary only with 1E and 1F), 2, 3, 5(only 5a-- zone and commissary both),7

//@CrossOrigin
@RequestMapping("/cbic/t_score")
@Controller
public class TotalScoreController {
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
	@RequestMapping(value = "/registration")// 1
	//  http://localhost:8080/cbicApi/cbic/t_score/registration?month_date=2023-05-01&type=parameter							// for scrutiny/assessment button
	//  http://localhost:8080/cbicApi/cbic/t_score/registration?month_date=2023-05-01&type=zone&zone_code=59 				// for all button
	//  http://localhost:8080/cbicApi/cbic/t_score/registration?month_date=2023-05-01&type=commissary&zone_code=59			// for show button, zone wise
	//  http://localhost:8080/cbicApi/cbic/t_score/registration?month_date=2023-05-01&type=all_commissary					// for all commissary
	//  http://localhost:8080/cbicApi/cbic/t_score/registration?month_date=2023-05-01&type=come_name&zone_code=64&come_name=Rajkot			// for only commissary wise, show button
	public Object registration(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("parameter")) { // registration all zone name 1
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double total_score = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";

					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst ="null";
					String absval = "null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
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

	/*
	 * Date: june 22, 2024
	 * created: RKS
	 * updated:
	 * Purpose: This methods have core function in Return Filing .
	 */

	@ResponseBody
	@RequestMapping(value = "/returnFiling") //2
	//  http://localhost:8080/cbicApi/cbic/t_score/returnFiling?month_date=2023-05-01&type=parameter							// for return filing button
	//  http://localhost:8080/cbicApi/cbic/t_score/returnFiling?month_date=2023-05-01&type=zone&zone_code=59 				// for all button
	//  http://localhost:8080/cbicApi/cbic/t_score/returnFiling?month_date=2023-05-01&type=commissary&zone_code=59			// for show button, zone wise
	//  http://localhost:8080/cbicApi/cbic/t_score/returnFiling?month_date=2023-05-01&type=all_commissary					// for all commissary
	//  http://localhost:8080/cbicApi/cbic/t_score/returnFiling?month_date=2023-05-01&type=come_name&zone_code=64&come_name=Rajkot			// for particular commissary wise, show button
	public Object returnFiling(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		Integer Zonal_rank = 0;
		try {

			if (type.equalsIgnoreCase("parameter")) { // returnFiling all zone name 1
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH score_calculation AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
						"        SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, SUM(14c.GSTR_3BM_F) AS col3,\n" +
						"        (SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / SUM(14c.GSTR_3BM_F)) AS total_score\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"        RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
						")\n" +
						"SELECT ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank,\n" +
						"    ZONE_NAME, ZONE_CODE, col21, col3,total_score\n" +
						"FROM score_calculation\n" +
						"ORDER BY total_score DESC;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					Zonal_rank = rsGst14aa.getInt("z_rank");
					double total_score = rsGst14aa.getDouble("total_score");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";

					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			} else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH score_calculation AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, (14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21,\n" +
						"        (14c.GSTR_3BM_F) AS col3, ((14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / (14c.GSTR_3BM_F)) AS total_score\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
						"),\n" +
						"ranked_scores AS (\n" +
						"    SELECT ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank, ZONE_NAME, ZONE_CODE, COMM_NAME, col21, col3, total_score FROM score_calculation\n" +
						")\n" +
						"SELECT * FROM ranked_scores\n" +
						"WHERE COMM_NAME IN ( SELECT DISTINCT COMM_NAME FROM ranked_scores WHERE ZONE_CODE = '" + zone_code + "'\n" +
						")ORDER BY z_rank;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst = "null";
					String absval = "null";
					String commName = rsGst14aa.getString("COMM_NAME");


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			} else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH score_calculation AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
						"        SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, SUM(14c.GSTR_3BM_F) AS col3, (SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / SUM(14c.GSTR_3BM_F)) AS total_score\n" +
						"    FROM mis_gst_commcode AS cc RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" + month_date + "' and  cc.ZONE_CODE = '" + zone_code + "'\n" +
						"    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
						")\n" +
						"SELECT \n" +
						"    ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank, ZONE_NAME, ZONE_CODE, col21, col3, total_score, 'GST2' AS gst,\n" +
						"    CONCAT(CAST(col21 AS CHAR), '/', CAST(col3 AS CHAR)) AS absolute_value\n" +
						"FROM score_calculation ORDER BY total_score DESC;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst = rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("total_score");
					Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			} else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH score_calculation AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
						"        (14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, (14c.GSTR_3BM_F) AS col3,\n" +
						"        ((14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / (14c.GSTR_3BM_F)) AS total_score\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"        RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
						")\n" +
						"SELECT ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank,\n" +
						"    ZONE_NAME, ZONE_CODE, COMM_NAME, col21, col3,total_score\n" +
						"FROM score_calculation\n" +
						"ORDER BY total_score DESC;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst = "null";
					String absval = "null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			} else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH score_calculation AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
						"        (14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, (14c.GSTR_3BM_F) AS col3, ((14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / (14c.GSTR_3BM_F)) AS total_score,\n" +
						"        'GST2' AS gst, CONCAT((14c.GSTR_3BM_F - 14c.GSTR_3BM_D), '/', 14c.GSTR_3BM_F) AS absolute_value\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE = '" + zone_code + "' AND cc.COMM_NAME = '" + come_name + "'\n" +
						")\n" +
						"SELECT ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank,\n" +
						"    ZONE_NAME, ZONE_CODE, COMM_NAME, col21, col3,total_score,gst,absolute_value\n" +
						"FROM score_calculation ORDER BY total_score DESC;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Zonal_rank = null;
					String gst = rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
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

	@ResponseBody
	@RequestMapping(value = "/scrutiny/assessment") //3
	//  http://localhost:8080/cbicApi/cbic/t_score/scrutiny/assessment?month_date=2023-05-01&type=parameter							// for scrutiny/assessment button
	//  http://localhost:8080/cbicApi/cbic/t_score/scrutiny/assessment?month_date=2023-05-01&type=zone&zone_code=59 				// for all button
	//  http://localhost:8080/cbicApi/cbic/t_score/scrutiny/assessment?month_date=2023-05-01&type=commissary&zone_code=59			// for show button, zone wise
	//  http://localhost:8080/cbicApi/cbic/t_score/scrutiny/assessment?month_date=2023-05-01&type=all_commissary					// for all commissary
	//  http://localhost:8080/cbicApi/cbic/t_score/scrutiny/assessment?month_date=2023-05-01&type=come_name&zone_code=64&come_name=Rajkot			// for only commissary wise, show button
	public Object scrutinyAssessment(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;

		try {

			if (type.equalsIgnoreCase("parameter")) { // scrutiny/assessment all zone name 1
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH current_data AS (SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4,   SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9,  SUM(14c.OUTCOME_SECTION_61) AS col10,   SUM(14c.RETURN_SCRUTINY) AS col2  \n" +
						"    FROM mis_gst_commcode AS cc  RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE  WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						"), \n" +
						"previous_data AS ( SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col1 \n" +
						"    FROM mis_gst_commcode AS cc RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" + prev_month_new + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						"), \n" +
						"combined_data AS (SELECT current_data.ZONE_NAME, current_data.ZONE_CODE, \n" +
						"           (current_data.col4 + current_data.col9 + current_data.col10) / (previous_data.col1 + current_data.col2) AS n_d_score1 FROM current_data JOIN previous_data ON current_data.ZONE_CODE = previous_data.ZONE_CODE\n" +
						"), \n" +
						"tax_recovery_data AS (SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
						"           CASE WHEN SUM(14c.TAX_LIABILITY_DETECTECT) = 0 THEN 0 ELSE SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) / SUM(14c.TAX_LIABILITY_DETECTECT) END AS n_d_score2\n" +
						"    FROM mis_gst_commcode AS cc RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY <= '" + month_date + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						"), \n" +
						"final_data AS (SELECT cd.ZONE_NAME, cd.ZONE_CODE, cd.n_d_score1, trd.n_d_score2,\n" +
						"           (cd.n_d_score1 + trd.n_d_score2) / 2 AS average_score, cd.n_d_score1 + trd.n_d_score2 AS total_score\n" +
						"    FROM combined_data AS cd JOIN tax_recovery_data AS trd ON cd.ZONE_CODE = trd.ZONE_CODE\n" +
						")SELECT ZONE_NAME, ZONE_CODE, n_d_score1, n_d_score2, average_score, total_score,\n" +
						"       RANK() OVER (ORDER BY total_score DESC) AS z_rank FROM final_data;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double total_score = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";

					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				// for z_rank, ZONE_NAME, COMM_NAME, ZONE_CODE
				String query_assessment = "WITH FirstQuery AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, ROW_NUMBER() OVER (ORDER BY zc.ZONE_CODE) AS z_rank\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE = '" + zone_code + "'\n" +
						"),\n" +
						"SecondQuery AS (\n" +
						"    WITH PreviousMonthData AS (\n" +
						"        SELECT zc.ZONE_NAME AS prev_ZONE_NAME, cc.COMM_NAME AS prev_COMM_NAME, cc.ZONE_CODE AS prev_ZONE_CODE, 14c.CLOSING_NO AS prev_col1 \n" +
						"        FROM mis_gst_commcode AS cc \n" +
						"        RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"        WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
						"    ),\n" +
						"    FirstQueryResult AS (\n" +
						"        SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, (14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61) /(pm.prev_col1 + 14c.RETURN_SCRUTINY) AS score_of_subParameter1\n" +
						"        FROM mis_gst_commcode AS cc  \n" +
						"        RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"        LEFT JOIN PreviousMonthData AS pm ON pm.prev_ZONE_CODE = cc.ZONE_CODE AND pm.prev_COMM_NAME = cc.COMM_NAME\n" +
						"        WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
						"        GROUP BY zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, pm.prev_col1, 14c.RETURN_SCRUTINY, 14c.SCRUTINIZED_DISCRIPANCY_FOUND, 14c.OUTCOME_ASMT_12_ISSUED, 14c.OUTCOME_SECTION_61\n" +
						"    ),\n" +
						"    SecondQueryResult AS (\n" +
						"        SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, (14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) / 14c.TAX_LIABILITY_DETECTECT AS score_of_subParameter2\n" +
						"        FROM mis_gst_commcode AS cc \n" +
						"        RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"        WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
						"    ),\n" +
						"    FinalResult AS (\n" +
						"        SELECT fqr.ZONE_NAME, fqr.COMM_NAME, fqr.ZONE_CODE, fqr.score_of_subParameter1, sqr.score_of_subParameter2, (fqr.score_of_subParameter1 + sqr.score_of_subParameter2) AS total_score\n" +
						"        FROM FirstQueryResult AS fqr\n" +
						"        JOIN SecondQueryResult AS sqr ON fqr.ZONE_NAME = sqr.ZONE_NAME AND fqr.COMM_NAME = sqr.COMM_NAME AND fqr.ZONE_CODE = sqr.ZONE_CODE\n" +
						"    )\n" +
						"    SELECT ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank, ZONE_NAME, COMM_NAME, ZONE_CODE, total_score FROM FinalResult\n" +
						")\n" +
						"SELECT fq.ZONE_NAME, fq.COMM_NAME, fq.ZONE_CODE, sq.z_rank \n" +
						"FROM FirstQuery fq\n" +
						"JOIN SecondQuery sq ON fq.COMM_NAME = sq.COMM_NAME\n" +
						"ORDER BY sq.z_rank ASC;\n";

				// for only total_score
				String query_assessment2 = "WITH PreviousMonthData AS (\n" +
						" SELECT zc.ZONE_NAME AS prev_ZONE_NAME, cc.COMM_NAME AS prev_COMM_NAME, cc.ZONE_CODE AS prev_ZONE_CODE, 14c.CLOSING_NO AS prev_col1  FROM mis_gst_commcode AS cc \n" +
						" RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE  LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						" WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND zc.ZONE_CODE = '" + zone_code + "'\n" +
						" ),\n" +
						" FirstQueryResult AS (\n" +
						" SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,  (14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61) / (pm.prev_col1 + 14c.RETURN_SCRUTINY) AS score_of_subParameter1\n" +
						" FROM mis_gst_commcode AS cc   RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE  LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						" LEFT JOIN PreviousMonthData AS pm ON pm.prev_ZONE_CODE = cc.ZONE_CODE AND pm.prev_COMM_NAME = cc.COMM_NAME\n" +
						" WHERE 14c.MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE = '" + zone_code + "'\n" +
						" GROUP BY zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, pm.prev_col1,  14c.RETURN_SCRUTINY, 14c.SCRUTINIZED_DISCRIPANCY_FOUND, 14c.OUTCOME_ASMT_12_ISSUED, 14c.OUTCOME_SECTION_61\n" +
						" ),\n" +
						" SecondQueryResult AS (\n" +
						" SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,  (14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) /  14c.TAX_LIABILITY_DETECTECT AS score_of_subParameter2\n" +
						" FROM mis_gst_commcode AS cc  RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE  LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						" WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND zc.ZONE_CODE = '" + zone_code + "'\n" +
						" ),\n" +
						" FinalResult AS (\n" +
						" SELECT fqr.ZONE_NAME, fqr.COMM_NAME, fqr.ZONE_CODE,  fqr.score_of_subParameter1, sqr.score_of_subParameter2,  (fqr.score_of_subParameter1 + sqr.score_of_subParameter2) AS total_score\n" +
						" FROM FirstQueryResult AS fqr JOIN SecondQueryResult AS sqr ON fqr.ZONE_NAME = sqr.ZONE_NAME  AND fqr.COMM_NAME = sqr.COMM_NAME AND fqr.ZONE_CODE = sqr.ZONE_CODE\n" +
						" )\n" +
						" SELECT ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank, ZONE_NAME, COMM_NAME, ZONE_CODE,total_score FROM FinalResult ORDER BY total_score DESC;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);
				ResultSet rsGst14aa2 = GetExecutionSQL.getResult(query_assessment2);

				while (rsGst14aa.next() && rsGst14aa2.next()) {
					double tScore = rsGst14aa2.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "SELECT \n" +
						"    current_data.ZONE_NAME, current_data.ZONE_CODE,\n" +
						"    (current_data.col4 + current_data.col9 + current_data.col10) / (previous_data.col1 + current_data.col2) AS score_of_subParameter,\n" +
						"    CONCAT((current_data.col4 + current_data.col9 + current_data.col10), '/', (previous_data.col1 + current_data.col2)) AS absolute_value,\n" +
						"    'GST3A' AS gst\n" +
						"FROM (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
						"        SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4, SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9, SUM(14c.OUTCOME_SECTION_61) AS col10, SUM(14c.RETURN_SCRUTINY) AS col2\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"        RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE = '" + zone_code + "'\n" +
						"    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						") AS current_data\n" +
						"JOIN (\n" +
						"    SELECT  zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col1\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"        RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"    WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND zc.ZONE_CODE = '" + zone_code + "'\n" +
						"    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						") AS previous_data ON current_data.ZONE_CODE = previous_data.ZONE_CODE\n" +
						"UNION ALL\n" +
						"SELECT  subquery.ZONE_NAME, subquery.ZONE_CODE, subquery.score_of_subParameter, subquery.absolute_value, subquery.gst\n" +
						"FROM ( SELECT  zc.ZONE_NAME, cc.ZONE_CODE,\n" +
						"        CASE WHEN SUM(14c.TAX_LIABILITY_DETECTECT) <> 0 THEN SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) / SUM(14c.TAX_LIABILITY_DETECTECT)\n" +
						"\tELSE NULL END AS score_of_subParameter,\n" +
						"        CASE WHEN SUM(14c.TAX_LIABILITY_DETECTECT) <> 0 THEN CONCAT(SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY), '/', SUM(14c.TAX_LIABILITY_DETECTECT))\n" +
						"\t\tELSE NULL END AS absolute_value, 'GST3B' AS gst\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"        RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"    WHERE 14c.MM_YYYY <= '" + month_date + "' AND zc.ZONE_CODE = '" + zone_code + "'\n" +
						"    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						") AS subquery;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH PreviousMonthData AS (\n" +
						"    SELECT zc.ZONE_NAME AS prev_ZONE_NAME, cc.COMM_NAME AS prev_COMM_NAME, cc.ZONE_CODE AS prev_ZONE_CODE, 14c.CLOSING_NO AS prev_col1 \n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"    WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
						"),\n" +
						"FirstQueryResult AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,  \n" +
						"        (14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61) / \n" +
						"            (pm.prev_col1 + 14c.RETURN_SCRUTINY) AS score_of_subParameter1\n" +
						"    FROM mis_gst_commcode AS cc  \n" +
						"    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    LEFT JOIN PreviousMonthData AS pm ON pm.prev_ZONE_CODE = cc.ZONE_CODE AND pm.prev_COMM_NAME = cc.COMM_NAME\n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
						"    GROUP BY zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, pm.prev_col1, \n" +
						"        14c.RETURN_SCRUTINY, 14c.SCRUTINIZED_DISCRIPANCY_FOUND, 14c.OUTCOME_ASMT_12_ISSUED, 14c.OUTCOME_SECTION_61\n" +
						"),\n" +
						"SecondQueryResult AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, \n" +
						"        (14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) / \n" +
						"            14c.TAX_LIABILITY_DETECTECT AS score_of_subParameter2\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
						"),\n" +
						"FinalResult AS (\n" +
						"    SELECT fqr.ZONE_NAME, fqr.COMM_NAME, fqr.ZONE_CODE, \n" +
						"        fqr.score_of_subParameter1, sqr.score_of_subParameter2, \n" +
						"        (fqr.score_of_subParameter1 + sqr.score_of_subParameter2) AS total_score\n" +
						"    FROM FirstQueryResult AS fqr\n" +
						"    JOIN SecondQueryResult AS sqr ON fqr.ZONE_NAME = sqr.ZONE_NAME \n" +
						"        AND fqr.COMM_NAME = sqr.COMM_NAME AND fqr.ZONE_CODE = sqr.ZONE_CODE\n" +
						")\n" +
						"SELECT ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank,\n" +
						"    ZONE_NAME, COMM_NAME, ZONE_CODE, \n" +
						"    score_of_subParameter1, score_of_subParameter2, total_score\n" +
						"FROM FinalResult\n" +
						"ORDER BY total_score DESC;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst ="null";
					String absval = "null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH PreviousMonthData AS (\n" +
						"    SELECT zc.ZONE_NAME AS prev_ZONE_NAME, cc.COMM_NAME AS prev_COMM_NAME, cc.ZONE_CODE AS prev_ZONE_CODE, 14c.CLOSING_NO AS col1\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"        RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"    WHERE 14c.MM_YYYY = '" + prev_month_new + "' and zc.ZONE_CODE = '" + zone_code + "' and cc.COMM_NAME = '" + come_name + "'\n" +
						"),\n" +
						"CurrentMonthData AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,\n" +
						"        14c.SCRUTINIZED_DISCRIPANCY_FOUND AS col4, 14c.OUTCOME_ASMT_12_ISSUED AS col9, 14c.OUTCOME_SECTION_61 AS col10, 14c.RETURN_SCRUTINY AS col2, pm.col1\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"        RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"        LEFT JOIN PreviousMonthData AS pm ON pm.prev_ZONE_CODE = cc.ZONE_CODE AND pm.prev_COMM_NAME = cc.COMM_NAME\n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' and zc.ZONE_CODE = '" + zone_code + "' and cc.COMM_NAME = '" + come_name + "'\n" +
						"    GROUP BY zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,\n" +
						"        14c.SCRUTINIZED_DISCRIPANCY_FOUND, 14c.OUTCOME_ASMT_12_ISSUED, 14c.OUTCOME_SECTION_61, 14c.RETURN_SCRUTINY, pm.col1\n" +
						"),\n" +
						"GST3A_Data AS (\n" +
						"    SELECT ZONE_NAME, COMM_NAME, ZONE_CODE, 'GST3A' AS gst,\n" +
						"        CASE WHEN (col1 + col2) != 0 THEN (col4 + col9 + col10) / (col1 + col2) ELSE NULL END AS total_score,\n" +
						"        CASE WHEN (col1 + col2) != 0 THEN CONCAT((col4 + col9 + col10), '/', (col1 + col2)) ELSE NULL END AS absolute_value\n" +
						"    FROM CurrentMonthData\n" +
						"),\n" +
						"GST3B_Data AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 'GST3B' AS gst,\n" +
						"        (14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) / 14c.TAX_LIABILITY_DETECTECT AS total_score,\n" +
						"        CONCAT((14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY), '/', 14c.TAX_LIABILITY_DETECTECT) AS absolute_value\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"        RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + prev_month_new + "' and zc.ZONE_CODE = '" + zone_code + "' and cc.COMM_NAME = '" + come_name + "'\n" +
						")\n" +
						"SELECT * FROM GST3A_Data UNION ALL SELECT * FROM GST3B_Data;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
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

	/*
	 * Date: jund 09, 2024
	 * created: RKS
	 * updated:
	 * Purpose: This methods have core function in investigation.
	 */
	@ResponseBody
	@RequestMapping(value = "/investigation")// 4
	//  http://localhost:8080/cbicApi/cbic/t_score/investigation?month_date=2023-05-01&type=parameter							// for scrutiny/assessment button
	//  http://localhost:8080/cbicApi/cbic/t_score/investigation?month_date=2023-05-01&type=zone&zone_code=59 				// for all button
	//  http://localhost:8080/cbicApi/cbic/t_score/investigation?month_date=2023-05-01&type=commissary&zone_code=59			// for show button, zone wise
	//  http://localhost:8080/cbicApi/cbic/t_score/investigation?month_date=2023-05-01&type=all_commissary					// for all commissary
	//  http://localhost:8080/cbicApi/cbic/t_score/investigation?month_date=2023-05-01&type=come_name&zone_code=64&come_name=Rajkot			// for only commissary wise, show button
	public Object investigation(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("parameter")) { // investigation all zone name 1
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double total_score = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";

					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst ="null";
					String absval = "null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
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

	/*
	 * Date: june 09, 2024
	 * created: RKS
	 * updated:
	 * Purpose: This methods have core function in adjudication.
	 */
	@ResponseBody
	@RequestMapping(value = "/adjudication") //5
	//  http://localhost:8080/cbicApi/cbic/t_score/adjudication?month_date=2023-05-01&type=parameter							// for scrutiny/assessment button
	//  http://localhost:8080/cbicApi/cbic/t_score/adjudication?month_date=2023-05-01&type=zone&zone_code=59 				// for all button
	//  http://localhost:8080/cbicApi/cbic/t_score/adjudication?month_date=2023-05-01&type=commissary&zone_code=59			// for show button, zone wise
	//  http://localhost:8080/cbicApi/cbic/t_score/adjudication?month_date=2023-05-01&type=all_commissary					// for all commissary
	//  http://localhost:8080/cbicApi/cbic/t_score/adjudication?month_date=2023-05-01&type=come_name&zone_code=64&come_name=Rajkot			// for only commissary wise, show button
	public Object adjudication(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("parameter")) { // adjudication all zone name 1
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double total_score = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";

					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst ="null";
					String absval = "null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
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
	/*
	 * Date: june 09, 2024
	 * created: RKS
	 * updated:
	 * Purpose: This methods have core function in adjudication/legacy.
	 */
	@ResponseBody
	@RequestMapping(value = "/adjudication(legacy)") //6
	//  http://localhost:8080/cbicApi/cbic/t_score/adjudication(legacy)?month_date=2023-05-01&type=parameter							// for scrutiny/assessment button
	//  http://localhost:8080/cbicApi/cbic/t_score/adjudication(legacy)?month_date=2023-05-01&type=zone&zone_code=59 				// for all button
	//  http://localhost:8080/cbicApi/cbic/t_score/adjudication(legacy)?month_date=2023-05-01&type=commissary&zone_code=59			// for show button, zone wise
	//  http://localhost:8080/cbicApi/cbic/t_score/adjudication(legacy)?month_date=2023-05-01&type=all_commissary					// for all commissary
	//  http://localhost:8080/cbicApi/cbic/t_score/adjudication(legacy)?month_date=2023-05-01&type=come_name&zone_code=64&come_name=Rajkot			// for only commissary wise, show button
	public Object adjudicationLegacy(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
		try {

			if (type.equalsIgnoreCase("parameter")) { // returnFiling all zone name 1
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH DisposalData AS (\n" +
						"    SELECT cc.ZONE_CODE, SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE\n" +
						"),\n" +
						"ClosingData AS (\n" +
						"    SELECT cc.ZONE_CODE, SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + prev_month_new + "' GROUP BY cc.ZONE_CODE\n" +
						"),\n" +
						"Subpara1 AS (\n" +
						"    SELECT zc.ZONE_NAME, d.ZONE_CODE, d.col9 / c.col3 AS total_score_of_subpara1\n" +
						"    FROM DisposalData d \n" +
						"    LEFT JOIN ClosingData c ON d.ZONE_CODE = c.ZONE_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = d.ZONE_CODE\n" +
						"),\n" +
						"Subpara2 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, (SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) / SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO)) AS total_score_of_subpara2\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
						"),\n" +
						"Subpara3 AS (\n" +
						"    SELECT t1.ZONE_NAME, t1.ZONE_CODE, CASE WHEN t2.col3 != 0 THEN t1.col9 / t2.col3 ELSE NULL END AS total_score_of_subpara3\n" +
						"    FROM (\n" +
						"        SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9\n" +
						"        FROM mis_gst_commcode AS cc\n" +
						"        RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"        WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE\n" +
						"    ) AS t1\n" +
						"    LEFT JOIN (\n" +
						"        SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
						"        FROM mis_gst_commcode AS cc\n" +
						"        RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"        WHERE 14c.MM_YYYY = '" + prev_month_new + "' GROUP BY cc.ZONE_CODE\n" +
						"    ) AS t2 ON t1.ZONE_CODE = t2.ZONE_CODE ORDER BY t1.ZONE_NAME, t1.ZONE_CODE\n" +
						"),\n" +
						"Subpara4 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
						"           SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) AS col18, \n" +
						"           SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col13,\n" +
						"           SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) / SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS total_score_of_subpara4\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE\n" +
						")\n" +
						"SELECT ROW_NUMBER() OVER (ORDER BY (sp1.total_score_of_subpara1 + sp2.total_score_of_subpara2 + sp3.total_score_of_subpara3 + sp4.total_score_of_subpara4) DESC) AS z_rank,\n" +
						"       sp1.ZONE_NAME,sp1.ZONE_CODE,sp1.total_score_of_subpara1,sp2.total_score_of_subpara2,sp3.total_score_of_subpara3,sp4.total_score_of_subpara4,\n" +
						"       (sp1.total_score_of_subpara1 + sp2.total_score_of_subpara2 + sp3.total_score_of_subpara3 + sp4.total_score_of_subpara4) AS total_score\n" +
						"FROM Subpara1 sp1\n" +
						"LEFT JOIN Subpara2 sp2 ON sp1.ZONE_CODE = sp2.ZONE_CODE\n" +
						"LEFT JOIN Subpara3 sp3 ON sp1.ZONE_CODE = sp3.ZONE_CODE\n" +
						"LEFT JOIN Subpara4 sp4 ON sp1.ZONE_CODE = sp4.ZONE_CODE\n" +
						"ORDER BY total_score DESC;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double total_score = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";

					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH DisposalData AS (\n" +
						"    SELECT cc.ZONE_CODE, SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' and cc.ZONE_CODE = '" + zone_code + "'\n" +
						"    GROUP BY cc.ZONE_CODE\n" +
						"),\n" +
						"ClosingData AS (\n" +
						"    SELECT cc.ZONE_CODE, SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"    WHERE 14c.MM_YYYY = '" + prev_month_new + "' and cc.ZONE_CODE = '" + zone_code + "'\n" +
						"    GROUP BY cc.ZONE_CODE\n" +
						"),\n" +
						"GST6A AS (\n" +
						"    SELECT zc.ZONE_NAME,d.ZONE_CODE, CASE WHEN c.col3 != 0 THEN d.col9 / c.col3\n" +
						"\t\tELSE 0 END AS score_of_subpara, CONCAT(d.col9, '/', c.col3) AS absolute_value, 'GST6A' AS gst\n" +
						"    FROM DisposalData d\n" +
						"    LEFT JOIN ClosingData c ON d.ZONE_CODE = c.ZONE_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = d.ZONE_CODE\n" +
						"),\n" +
						"GST6B AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
						"        SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) / SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS score_of_subpara,\n" +
						"        CONCAT(\n" +
						"            SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT), \n" +
						"            '/', \n" +
						"            SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO)\n" +
						"        ) AS absolute_value,'GST6B' AS gst\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' and cc.ZONE_CODE = '" + zone_code + "'\n" +
						"    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						"),\n" +
						"GST6C AS (SELECT t1.ZONE_NAME, t1.ZONE_CODE, (t1.col9 / t2.col3) AS score_of_subpara,\n" +
						"           CONCAT(t1.col9, '/', t2.col3) AS absolute_value, 'GST6C' AS gst\n" +
						"    FROM (\n" +
						"        SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9\n" +
						"        FROM mis_gst_commcode AS cc\n" +
						"        RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"        WHERE 14c.MM_YYYY = '" + month_date + "' and cc.ZONE_CODE = '" + zone_code + "'\n" +
						"        GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						"    ) AS t1\n" +
						"    LEFT JOIN ( SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
						"               SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
						"        FROM mis_gst_commcode AS cc\n" +
						"        RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"        WHERE 14c.MM_YYYY = '" + prev_month_new + "' and cc.ZONE_CODE = '" + zone_code + "'\n" +
						"        GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						"    ) AS t2 ON t1.ZONE_CODE = t2.ZONE_CODE\n" +
						"),\n" +
						"GST6D AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
						"        CASE WHEN SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) = 0 THEN 0\n" +
						"            ELSE SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) / \n" +
						"                 SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO)\n" +
						"        END AS score_of_subpara,\n" +
						"        CASE WHEN SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) = 0 THEN '0/0'\n" +
						"            ELSE CONCAT(\n" +
						"                SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT), \n" +
						"                '/', \n" +
						"                SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO)\n" +
						"            )END AS absolute_value, 'GST6D' AS gst\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' and cc.ZONE_CODE = '" + zone_code + "'\n" +
						"    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
						")\n" +
						"SELECT * FROM GST6A UNION ALL SELECT * FROM GST6B UNION ALL SELECT * FROM GST6C UNION ALL SELECT * FROM GST6D;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subpara");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH CTE1 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, (14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9 \n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
						"),\n" +
						"CTE2 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3 \n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
						"),\n" +
						"CTE3 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, (14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) AS col4,(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col5\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
						"),\n" +
						"CTE4 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col6\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
						"),\n" +
						"RankedResults AS (\n" +
						"    SELECT COALESCE(cte1.ZONE_NAME, cte3.ZONE_NAME) AS ZONE_NAME,COALESCE(cte1.ZONE_CODE, cte3.ZONE_CODE) AS ZONE_CODE,COALESCE(cte1.COMM_NAME, cte3.COMM_NAME) AS COMM_NAME,\n" +
						"        CASE WHEN cte2.col3 <> 0 THEN cte1.col9 / cte2.col3 ELSE NULL END AS total_score_of_subpara1, CASE WHEN cte3.col5 <> 0 THEN cte3.col4 / cte4.col6 ELSE NULL END AS total_score_of_subpara2,\n" +
						"        CASE WHEN cte3.col5 <> 0 THEN cte3.col4 / cte3.col5 ELSE NULL END AS total_score_of_subpara3, CASE WHEN cte4.col6 <> 0 THEN cte3.col4 / cte4.col6 ELSE NULL END AS total_score_of_subpara4,\n" +
						"        (CASE WHEN cte2.col3 <> 0 THEN cte1.col9 / cte2.col3 ELSE NULL END + CASE WHEN cte3.col5 <> 0 THEN cte3.col4 / cte4.col6 ELSE NULL END + CASE WHEN cte3.col5 <> 0 THEN cte3.col4 / cte3.col5 ELSE NULL END + CASE WHEN cte4.col6 <> 0 THEN cte3.col4 / cte4.col6 ELSE NULL END) AS total_score,\n" +
						"        ROW_NUMBER() OVER (ORDER BY \n" +
						"         (CASE WHEN cte2.col3 <> 0 THEN cte1.col9 / cte2.col3 ELSE NULL END + CASE WHEN cte3.col5 <> 0 THEN cte3.col4 / cte4.col6 ELSE NULL END + CASE WHEN cte3.col5 <> 0 THEN cte3.col4 / cte3.col5 ELSE NULL END + CASE WHEN cte4.col6 <> 0 THEN cte3.col4 / cte4.col6 ELSE NULL END) DESC) AS z_rank\n" +
						"    FROM \n" +
						"        (SELECT DISTINCT ZONE_NAME, ZONE_CODE, COMM_NAME FROM CTE1\n" +
						"         UNION SELECT DISTINCT ZONE_NAME, ZONE_CODE, COMM_NAME FROM CTE3) AS common\n" +
						"    LEFT JOIN CTE1 ON common.ZONE_NAME = CTE1.ZONE_NAME AND common.ZONE_CODE = CTE1.ZONE_CODE AND common.COMM_NAME = CTE1.COMM_NAME LEFT JOIN CTE2 ON common.ZONE_NAME = CTE2.ZONE_NAME AND common.ZONE_CODE = CTE2.ZONE_CODE AND common.COMM_NAME = CTE2.COMM_NAME\n" +
						"    LEFT JOIN CTE3 ON common.ZONE_NAME = CTE3.ZONE_NAME AND common.ZONE_CODE = CTE3.ZONE_CODE AND common.COMM_NAME = CTE3.COMM_NAME LEFT JOIN CTE4 ON common.ZONE_NAME = CTE4.ZONE_NAME AND common.ZONE_CODE = CTE4.ZONE_CODE AND common.COMM_NAME = CTE4.COMM_NAME\n" +
						")\n" +
						"SELECT * FROM RankedResults ORDER BY z_rank;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst ="null";
					String absval = "null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
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

	/*
	 * Date: june 22, 2024
	 * created: RKS
	 * updated:
	 * Purpose: This methods have core function in Return Filing .
	 */

	@ResponseBody
	@RequestMapping(value = "/refunds") //7
	//  http://localhost:8080/cbicApi/cbic/t_score/refunds?month_date=2023-05-01&type=parameter							// for return filing button
	//  http://localhost:8080/cbicApi/cbic/t_score/refunds?month_date=2023-05-01&type=zone&zone_code=59 				// for all button
	//  http://localhost:8080/cbicApi/cbic/t_score/refunds?month_date=2023-05-01&type=commissary&zone_code=59			// for show button, zone wise
	//  http://localhost:8080/cbicApi/cbic/t_score/refunds?month_date=2023-05-01&type=all_commissary					// for all commissary
	//  http://localhost:8080/cbicApi/cbic/t_score/refunds?month_date=2023-05-01&type=come_name&zone_code=64&come_name=Rajkot			// for particular commissary wise, show button
	public Object refunds(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		Integer Zonal_rank = 0;
		try {

			if (type.equalsIgnoreCase("parameter")) { // returnFiling all zone name 1
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH CTE AS ( SELECT \n" +
						"        SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS col16, \n" +
						"        SUM(14c.age_breakup_above60_no) AS col22, cc.ZONE_CODE,zc.ZONE_NAME\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dpm_gst_4 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
						"    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						")\n" +
						"SELECT ZONE_CODE, ZONE_NAME, col16, col22, (col22 / col16) AS total_score,\n" +
						"    ROW_NUMBER() OVER (ORDER BY (col22 / col16) DESC) AS z_rank\n" +
						"FROM CTE ORDER BY total_score DESC;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					Zonal_rank = rsGst14aa.getInt("z_rank");
					double total_score = rsGst14aa.getDouble("total_score");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";

					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			} else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "-- First query to get the COMM_NAME\n" +
						"WITH calculated_values_1 AS (\n" +
						"    SELECT  cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME, \n" +
						"        SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO) AS col16,\n" +
						"        SUM(dpm.age_breakup_above60_no) AS col22,\n" +
						"        SUM(dpm.age_breakup_above60_no) * 1.0 / NULLIF(SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO), 0) AS total_score\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"    RIGHT JOIN mis_dpm_gst_4 AS dpm ON cc.COMM_CODE = dpm.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"    WHERE dpm.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "'\n" +
						"    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME\n" +
						"),\n" +
						"calculated_values_2 AS (\n" +
						"    SELECT  cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME, \n" +
						"        SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO) AS col16,\n" +
						"        SUM(dpm.age_breakup_above60_no) AS col22,\n" +
						"        SUM(dpm.age_breakup_above60_no) * 1.0 / NULLIF(SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO), 0) AS total_score\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"    RIGHT JOIN mis_dpm_gst_4 AS dpm ON cc.COMM_CODE = dpm.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"    WHERE dpm.MM_YYYY = '" + month_date + "'\n" +
						"    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME\n" +
						"),\n" +
						"ranked_values AS (\n" +
						"    SELECT cv2.ZONE_CODE, cv2.ZONE_NAME, cv2.COMM_NAME, cv2.col16, cv2.col22, cv2.total_score,\n" +
						"           ROW_NUMBER() OVER (ORDER BY cv2.total_score DESC) AS z_rank\n" +
						"    FROM calculated_values_2 cv2\n" +
						")\n" +
						"SELECT rv.ZONE_CODE, rv.ZONE_NAME, rv.COMM_NAME, rv.col16, rv.col22, rv.total_score, rv.z_rank\n" +
						"FROM ranked_values rv\n" +
						"JOIN (SELECT DISTINCT COMM_NAME FROM calculated_values_1) cv1\n" +
						"ON rv.COMM_NAME = cv1.COMM_NAME\n" +
						"ORDER BY rv.z_rank;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst = "null";
					String absval = "null";
					String commName = rsGst14aa.getString("COMM_NAME");


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			} else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH CTE AS (\n" +
						"    SELECT SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS col16, \n" +
						"        SUM(14c.age_breakup_above60_no) AS col22, cc.ZONE_CODE, zc.ZONE_NAME\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"        RIGHT JOIN mis_dpm_gst_4 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "'\n" +
						"    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						")\n" +
						"SELECT ZONE_CODE, ZONE_NAME, col16, col22, \n" +
						"    CONCAT(col22, '/', col16) AS absolute_value, -- Concatenating col22 and col16 with '/'\n" +
						"    (col22 / col16) AS total_score,\n" +
						"    ROW_NUMBER() OVER (ORDER BY (col22 / col16) DESC) AS z_rank,\n" +
						"    'GST7' AS gst  -- Adding the 'gst' column with value 'GST7'\n" +
						"FROM CTE ORDER BY total_score DESC;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst = rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("total_score");
					Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			} else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH calculated_values AS (\n" +
						"    SELECT  cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME, \n" +
						"        SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO) AS col16,\n" +
						"        SUM(dpm.age_breakup_above60_no) AS col22,\n" +
						"        SUM(dpm.age_breakup_above60_no) * 1.0 / NULLIF(SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO), 0) AS total_score\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"    RIGHT JOIN mis_dpm_gst_4 AS dpm ON cc.COMM_CODE = dpm.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"    WHERE dpm.MM_YYYY = '" + month_date + "'\n" +
						"    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME\n" +
						")\n" +
						"SELECT ZONE_CODE, ZONE_NAME, COMM_NAME, col16,col22,total_score,\n" +
						"    ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank\n" +
						"FROM calculated_values\n" +
						"ORDER BY total_score DESC;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst = "null";
					String absval = "null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			} else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH calculated_values AS (\n" +
						"    SELECT cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME, \n" +
						"        SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO) AS col16,\n" +
						"        SUM(dpm.age_breakup_above60_no) AS col22,\n" +
						"        SUM(dpm.age_breakup_above60_no) * 1.0 / NULLIF(SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO), 0) AS total_score\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"        RIGHT JOIN mis_dpm_gst_4 AS dpm ON cc.COMM_CODE = dpm.COMM_CODE\n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"    WHERE dpm.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "' AND cc.COMM_NAME = '" + come_name + "'\n" +
						"    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME\n" +
						")\n" +
						"SELECT ZONE_CODE, ZONE_NAME, COMM_NAME, col16,col22, total_score,\n" +
						"    CONCAT(col22, '/', col16) AS absolute_value,\n" +
						"    'GST7' AS gst,  -- Adding the constant 'GST7' as the gst column\n" +
						"    ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank\n" +
						"FROM calculated_values ORDER BY total_score DESC;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Zonal_rank = null;
					String gst = rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
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

	/*
	 * Date: june 09, 2024
	 * created: RKS
	 * updated:
	 * Purpose: This methods have core function in recovery.
	 */
	@ResponseBody
	@RequestMapping(value = "/recovery") //8
	//  http://localhost:8080/cbicApi/cbic/t_score/recovery?month_date=2023-05-01&type=parameter							// for scrutiny/assessment button
	//  http://localhost:8080/cbicApi/cbic/t_score/recovery?month_date=2023-05-01&type=zone&zone_code=59 				// for all button
	//  http://localhost:8080/cbicApi/cbic/t_score/recovery?month_date=2023-05-01&type=commissary&zone_code=59			// for show button, zone wise
	//  http://localhost:8080/cbicApi/cbic/t_score/recovery?month_date=2023-05-01&type=all_commissary					// for all commissary
	//  http://localhost:8080/cbicApi/cbic/t_score/recovery?month_date=2023-05-01&type=come_name&zone_code=64&come_name=Rajkot			// for only commissary wise, show button
	public Object recovery(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("parameter")) { // recovery all zone name 1
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double total_score = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";

					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst ="null";
					String absval = "null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
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
	/*
	 * Date: june 09, 2024
	 * created: RKS
	 * updated:
	 * Purpose: This methods have core function in arrest/prosecution.
	 */
	@ResponseBody
	@RequestMapping(value = "/arrest/prosecution") //9
	//  http://localhost:8080/cbicApi/cbic/t_score/arrest/prosecution?month_date=2023-05-01&type=parameter							// for scrutiny/assessment button
	//  http://localhost:8080/cbicApi/cbic/t_score/arrest/prosecution?month_date=2023-05-01&type=zone&zone_code=59 				// for all button
	//  http://localhost:8080/cbicApi/cbic/t_score/arrest/prosecution?month_date=2023-05-01&type=commissary&zone_code=59			// for show button, zone wise
	//  http://localhost:8080/cbicApi/cbic/t_score/arrest/prosecution?month_date=2023-05-01&type=all_commissary					// for all commissary
	//  http://localhost:8080/cbicApi/cbic/t_score/arrest/prosecution?month_date=2023-05-01&type=come_name&zone_code=64&come_name=Rajkot			// for only commissary wise, show button
	public Object arrestProsecution(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("parameter")) { // arrest/prosecution all zone name 1
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double total_score = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";

					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst ="null";
					String absval = "null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
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

	/*
	 * Date: june 09, 2024
	 * created: RKS
	 * updated:
	 * Purpose: This methods have core function in audit.
	 */
	@ResponseBody
	@RequestMapping(value = "/audit") //10
	//  http://localhost:8080/cbicApi/cbic/t_score/audit?month_date=2023-05-01&type=parameter							// for scrutiny/assessment button
	//  http://localhost:8080/cbicApi/cbic/t_score/audit?month_date=2023-05-01&type=zone&zone_code=59 				// for all button
	//  http://localhost:8080/cbicApi/cbic/t_score/audit?month_date=2023-05-01&type=commissary&zone_code=59			// for show button, zone wise
	//  http://localhost:8080/cbicApi/cbic/t_score/audit?month_date=2023-05-01&type=all_commissary					// for all commissary
	//  http://localhost:8080/cbicApi/cbic/t_score/audit?month_date=2023-05-01&type=come_name&zone_code=64&come_name=Rajkot			// for only commissary wise, show button
	public Object audit(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("parameter")) { // audit all zone name 1
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double total_score = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";

					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst ="null";
					String absval = "null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
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

	/*
	 * Date: june 09, 2024
	 * created: RKS
	 * updated:
	 * Purpose: This methods have core function in appeals.
	 */
	@ResponseBody
	@RequestMapping(value = "/appeals") //11
	//  http://localhost:8080/cbicApi/cbic/t_score/appeals?month_date=2023-05-01&type=parameter							// for scrutiny/assessment button
	//  http://localhost:8080/cbicApi/cbic/t_score/appeals?month_date=2023-05-01&type=zone&zone_code=59 				// for all button
	//  http://localhost:8080/cbicApi/cbic/t_score/appeals?month_date=2023-05-01&type=commissary&zone_code=59			// for show button, zone wise
	//  http://localhost:8080/cbicApi/cbic/t_score/appeals?month_date=2023-05-01&type=all_commissary					// for all commissary
	//  http://localhost:8080/cbicApi/cbic/t_score/appeals?month_date=2023-05-01&type=come_name&zone_code=64&come_name=Rajkot			// for only commissary wise, show button
	public Object appeals(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("parameter")) { // appeals all zone name 1
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double total_score = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";

					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst ="null";
					String absval = "null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
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