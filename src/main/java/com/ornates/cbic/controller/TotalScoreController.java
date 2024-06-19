package com.ornates.cbic.controller;

import com.ornates.cbic.dao.pool.JDBCConnection;
import com.ornates.cbic.dao.result.GetExecutionSQL;
import com.ornates.cbic.model.response.GST4A;
import com.ornates.cbic.model.response.TotalScore;
import com.ornates.cbic.service.DateCalculate;
import com.ornates.cbic.service.RelevantAspect;
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

@CrossOrigin
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
	//  http://localhost:8080/cbicApi/cbic/t_score/registration?month_date=2023-04-01&type=zone
	//  http://localhost:8080/cbicApi/cbic/t_score/registration?month_date=2023-04-01&type=commissary&zone_code=59

	public Object registration(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
		List<TotalScore> allGstaList = new ArrayList<>();
		Connection con = null;
		ResultSet rsGst14aa = null;
		TotalScore totalScore = null;
		try {

			if (type.equalsIgnoreCase("zone")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH unified_results AS ("
						+ "SELECT zc.ZONE_NAME, cc.ZONE_CODE,"
						+ "(SUM(14c.DISPOSAL_OF_ARN_WITHIN_7) / (SUM(14c.NO_OF_ARN_RECEIVED_GSTN) + SUM(14c.NO_OF_ARN_RECEIVED_CPC))) AS ratio "
						+ "FROM mis_gst_commcode AS cc "
						+ "RIGHT JOIN mis_dpm_gst_14a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE "
						+ "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE "
						+ "WHERE 14c.MM_YYYY = '" + month_date + "' "
						+ "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE "
						+ "UNION "
						+ "SELECT zc.ZONE_NAME, cc.ZONE_CODE,"
						+ "(SUM(14c.DISPOSAL_OF_ARN_PV_NOT_RECEIVED) / SUM(14c.DISPOSAL_OF_ARN_PV_RECOMMENDED)) AS ratio "
						+ "FROM mis_gst_commcode AS cc "
						+ "RIGHT JOIN mis_dpm_gst_14a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE "
						+ "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE "
						+ "WHERE 14c.MM_YYYY = '" + month_date + "' "
						+ "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE "
						+ "UNION "
						+ "SELECT zc.ZONE_NAME, cc.ZONE_CODE,"
						+ "(SUM(14c.DISPOSAL_OF_ARN_DEEMED_REG) / (SUM(14c.NO_OF_ARN_RECEIVED_GSTN) + SUM(14c.NO_OF_ARN_RECEIVED_CPC))) AS ratio "
						+ "FROM mis_gst_commcode AS cc "
						+ "RIGHT JOIN mis_dpm_gst_14a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE "
						+ "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE "
						+ "WHERE 14c.MM_YYYY = '" + month_date + "' "
						+ "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE "
						+ "UNION "
						+ "SELECT zc.ZONE_NAME, cc.ZONE_CODE,"
						+ "(SUM(14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC - 14c.DISPOSAL_OF_ARN_DEEMED_REG) / "
						+ "(SUM(14c.OPENING_BALANCE) + SUM(14c.NO_OF_ARN_RECEIVED_GSTN) + SUM(14c.NO_OF_ARN_RECEIVED_CPC))) AS ratio "
						+ "FROM mis_gst_commcode AS cc "
						+ "RIGHT JOIN mis_dpm_gst_14a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE "
						+ "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE "
						+ "WHERE 14c.MM_YYYY = '" + month_date + "' "
						+ "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE "
						+ "UNION "
						+ "SELECT zc.ZONE_NAME, cc.ZONE_CODE,"
						+ "(SUM((14c.SUO_MOTO_OPENING_BALANCE + 14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION - 14c.SUO_MOTO_GSTIN_CANCELLED) + "
						+ "(14c.CANCELLATION_OPENING_BALANCE + 14c.CANCELLATION_NO_APPLICATION_RECEIVED - 14c.CANCELLATION_GSTIN_CANCELLED)) / "
						+ "(SUM(14c.SUO_MOTO_OPENING_BALANCE) + SUM(14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION) + "
						+ "SUM(14c.CANCELLATION_OPENING_BALANCE) + SUM(14c.CANCELLATION_NO_APPLICATION_RECEIVED))) AS ratio "
						+ "FROM mis_gst_commcode AS cc "
						+ "RIGHT JOIN mis_dpm_gst_15a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE "
						+ "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE "
						+ "WHERE 14c.MM_YYYY = '" + month_date + "' "
						+ "GROUP BY cc.ZONE_CODE, zc.ZONE_NAME "
						+ "UNION "
						+ "SELECT zc.ZONE_NAME, cc.ZONE_CODE,"
						+ "(SUM(15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED - 15a.REVOCATION_GSTIN_REVOKED - 15a.REVOCATION_APPLICATION_REJECTED) / "
						+ "(SUM(15a.REVOCATION_OPENING_BALANCE) + SUM(15a.REVOCATION_ARN_RECEIVED))) AS ratio "
						+ "FROM mis_gst_commcode AS cc "
						+ "RIGHT JOIN mis_dpm_gst_15a AS 15a ON cc.COMM_CODE = 15a.COMM_CODE "
						+ "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE "
						+ "WHERE 15a.MM_YYYY ='" + month_date + "' "
						+ "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE "
						+ ") "
						+ "SELECT "
						+ "ROW_NUMBER() OVER (ORDER BY AVG(ratio) DESC) AS z_rank, "
						+ "ZONE_NAME, "
						+ "ZONE_CODE, "
						+ "AVG(ratio) AS total_score "
						+ "FROM unified_results "
						+ "GROUP BY ZONE_NAME, ZONE_CODE "
						+ "ORDER BY total_score DESC;";

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
			}else if (type.equalsIgnoreCase("commissary")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
						"       ((14c.SUO_MOTO_OPENING_BALANCE + 14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION - 14c.SUO_MOTO_GSTIN_CANCELLED) + \n" +
						"       (14c.CANCELLATION_OPENING_BALANCE + 14c.CANCELLATION_NO_APPLICATION_RECEIVED - 14c.CANCELLATION_GSTIN_CANCELLED)) AS col9,\n" +
						"       (14c.SUO_MOTO_OPENING_BALANCE) AS col1, \n" +
						"       (14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION) AS col2,\n" +
						"       NULL AS col3,\n" +
						"       NULL AS col4,\n" +
						"       (14c.CANCELLATION_OPENING_BALANCE) AS col5,\n" +
						"       (14c.CANCELLATION_NO_APPLICATION_RECEIVED) AS col6,\n" +
						"       NULL AS col7,\n" +
						"       NULL AS col8,\n" +
						"       'GST 1E' AS gst,\n" +
						"       ((14c.SUO_MOTO_OPENING_BALANCE + 14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION - 14c.SUO_MOTO_GSTIN_CANCELLED) + \n" +
						"       (14c.CANCELLATION_OPENING_BALANCE + 14c.CANCELLATION_NO_APPLICATION_RECEIVED - 14c.CANCELLATION_GSTIN_CANCELLED)) /\n" +
						"       (14c.SUO_MOTO_OPENING_BALANCE + 14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION + 14c.CANCELLATION_OPENING_BALANCE + 14c.CANCELLATION_NO_APPLICATION_RECEIVED) AS total_score,\n" +
						"       CONCAT(\n" +
						"           ABS(((14c.SUO_MOTO_OPENING_BALANCE + 14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION - 14c.SUO_MOTO_GSTIN_CANCELLED) + \n" +
						"           (14c.CANCELLATION_OPENING_BALANCE + 14c.CANCELLATION_NO_APPLICATION_RECEIVED - 14c.CANCELLATION_GSTIN_CANCELLED))),\n" +
						"           '/',\n" +
						"           ABS((14c.SUO_MOTO_OPENING_BALANCE + 14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION + 14c.CANCELLATION_OPENING_BALANCE + 14c.CANCELLATION_NO_APPLICATION_RECEIVED))\n" +
						"       ) AS absolute_value\n" +
						"FROM mis_gst_commcode AS cc \n" +
						"RIGHT JOIN mis_dpm_gst_15a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"WHERE MM_YYYY = '2023-04-01' AND cc.ZONE_CODE = '54'\n" +
						"\n" +
						"UNION ALL\n" +
						"\n" +
						"SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
						"    NULL AS col9,\n" +
						"    NULL AS col1, \n" +
						"    NULL AS col2,\n" +
						"    (15a.REVOCATION_OPENING_BALANCE) as col3, \n" +
						"    (15a.REVOCATION_ARN_RECEIVED) as col4,\n" +
						"    NULL AS col5,\n" +
						"    NULL AS col6,\n" +
						"    NULL AS col7,\n" +
						"    NULL AS col8,\n" +
						"    'GST 1F' as gst,\n" +
						"    ((15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED - 15a.REVOCATION_GSTIN_REVOKED - 15a.REVOCATION_APPLICATION_REJECTED) / (15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED)) as total_score,\n" +
						"    CONCAT(FORMAT((15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED - 15a.REVOCATION_GSTIN_REVOKED - 15a.REVOCATION_APPLICATION_REJECTED) / (15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED), 2), '/', FORMAT(1, 2)) as absolute_value\n" +
						"FROM mis_gst_commcode as cc  \n" +
						"RIGHT JOIN mis_dpm_gst_15a as 15a ON cc.COMM_CODE = 15a.COMM_CODE  \n" +
						"LEFT JOIN mis_gst_zonecode as zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"WHERE MM_YYYY = '2023-04-01' AND cc.ZONE_CODE = '54';\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = null;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = rsGst14aa.getString("GST");
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
	@RequestMapping(value = "/returnFiling") //2
	//  http://localhost:8080/cbicApi/cbic/t_score/returnFiling?month_date=2023-04-01&type=zone
	//  http://localhost:8080/cbicApi/cbic/t_score/returnFiling?month_date=2023-04-01&type=commissary&zone_code=59			//for commissary name ALL button
	//  http://localhost:8080/cbicApi/cbic/t_score/returnFiling?month_date=2023-04-01&type=zone_wise_comm&zone_code=59   //for show button
	//  http://localhost:8080/cbicApi/cbic/t_score/returnFiling?month_date=2023-04-01&type=all_commissary
	public Object returnFiling(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		double total = 0.00;
		try {

			if (type.equalsIgnoreCase("zone")) {
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "SELECT \n" +
						"    zc.ZONE_NAME, \n" +
						"    cc.ZONE_CODE, \n" +
						"    SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21,\n" +
						"    SUM(14c.GSTR_3BM_F) AS col3,\n" +
						"    SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / SUM(14c.GSTR_3BM_F) AS total_score,\n" +
						"    RANK() OVER (ORDER BY SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / SUM(14c.GSTR_3BM_F) DESC) AS z_rank\n" +
						"FROM mis_gst_commcode AS cc \n" +
						"RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
						"GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
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
			}else if (type.equalsIgnoreCase("commissary")) {

				String query_assessment = "SELECT \n" +
						"    zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, \n" +
						"    (14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21,\n" +
						"    (14c.GSTR_3BM_F) AS col3,'GST 2' AS gst,\n" +
						"    (14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / (14c.GSTR_3BM_F) AS total_score,\n" +
						"    CONCAT((14c.GSTR_3BM_F - 14c.GSTR_3BM_D), '/', (14c.GSTR_3BM_F)) AS absolute_value\n" +
						"FROM mis_gst_commcode AS cc \n" +
						"RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "';\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = null;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = rsGst14aa.getString("GST");
					String absval = rsGst14aa.getString("absolute_value");

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("zone_wise_comm")) {
				String query_assessment = "SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,\n" +
						"    (14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21,\n" +
						"    14c.GSTR_3BM_F AS col3,\n" +
						"    (14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / 14c.GSTR_3BM_F AS total_score,\n" +
						"    CONCAT((14c.GSTR_3BM_F - 14c.GSTR_3BM_D), \n" +
						"        '/', \n" +
						"        14c.GSTR_3BM_F\n" +
						"    ) AS absolute_value,\n" +
						"    ROW_NUMBER() OVER (ORDER BY (14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / 14c.GSTR_3BM_F DESC) AS z_rank\n" +
						"FROM mis_gst_commcode AS cc \n" +
						"RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"WHERE  14c.MM_YYYY =  '" + month_date + "' AND zc.ZONE_CODE = '" + zone_code + "'\n" +
						"ORDER BY total_score DESC;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double total_score = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "GST2";
					String absval = rsGst14aa.getString("absolute_value");

					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) {
				String query_assessment="SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,\n" +
						"    (14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, 14c.GSTR_3BM_F AS col3,\n" +
						"    (14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / 14c.GSTR_3BM_F AS total_score,\n" +
						"    CONCAT(\n" +
						"        CAST(14c.GSTR_3BM_F - 14c.GSTR_3BM_D AS CHAR), \n" +
						"        '/', \n" +
						"        CAST(14c.GSTR_3BM_F AS CHAR)\n" +
						"    ) AS absolute_value\n" +
						"FROM mis_gst_commcode AS cc\n" +
						"RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"WHERE  14c.MM_YYYY = '" + month_date + "';";

				rsGst14aa =GetExecutionSQL.getResult(query_assessment);
				while (rsGst14aa.next()) {
					double total_score = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = null;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "GST2";
					String absval = "absolute_value";

					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst);
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
//                              merge 3a and 3b zone queri

	@ResponseBody
	@RequestMapping(value = "/scrutiny/assessment") //3
	//  http://localhost:8080/cbicApi/cbic/t_score/scrutiny/assessment?month_date=2023-04-01&type=parameter 						//for scrutiny/assessment button
	//  http://localhost:8080/cbicApi/cbic/t_score/scrutiny/assessment?month_date=2023-04-01&type=zone&zone_code=59 				// for all button
	//  http://localhost:8080/cbicApi/cbic/t_score/scrutiny/assessment?month_date=2023-04-01&type=commissary&zone_code=59			// for show button, zone wise
	public Object scrutinyAssessment(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("parameter")) {
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
			}else if (type.equalsIgnoreCase("zone")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH PreviousMonthData AS (\n" +
						"    SELECT zc.ZONE_NAME AS prev_ZONE_NAME, cc.COMM_NAME AS prev_COMM_NAME, cc.ZONE_CODE AS prev_ZONE_CODE, 14c.CLOSING_NO AS col1\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"        RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"    WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + prev_month_new + "'\n" +
						"),\n" +
						"CurrentMonthData AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,\n" +
						"        (14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61) /\n" +
						"            (pm.col1 + 14c.RETURN_SCRUTINY) AS n_d_score_current,\n" +
						"        (14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) / 14c.TAX_LIABILITY_DETECTECT AS n_d_score_recovered\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"        RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"        LEFT JOIN PreviousMonthData AS pm ON pm.prev_ZONE_CODE = cc.ZONE_CODE AND pm.prev_COMM_NAME = cc.COMM_NAME\n" +
						"    WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY =  '" + month_date + "'\n" +
						"    GROUP BY zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, pm.col1, 14c.RETURN_SCRUTINY,\n" +
						"        14c.SCRUTINIZED_DISCRIPANCY_FOUND, 14c.OUTCOME_ASMT_12_ISSUED, 14c.OUTCOME_SECTION_61,\n" +
						"        14c.AMOUNT_RECOVERED_TAX, 14c.AMOUNT_RECOVERED_INTEREST, 14c.AMOUNT_RECOVERED_PENALTY, 14c.TAX_LIABILITY_DETECTECT\n" +
						"),\n" +
						"MergedData AS (\n" +
						"    SELECT cm.ZONE_NAME, cm.COMM_NAME, cm.ZONE_CODE, cm.n_d_score_current, cm.n_d_score_recovered,\n" +
						"        (cm.n_d_score_current + cm.n_d_score_recovered) AS total_score,\n" +
						"        ROW_NUMBER() OVER (ORDER BY (cm.n_d_score_current + cm.n_d_score_recovered) DESC) AS z_rank\n" +
						"    FROM CurrentMonthData cm\n" +
						")\n" +
						"SELECT md.ZONE_NAME, md.COMM_NAME, md.ZONE_CODE, md.n_d_score_current, md.n_d_score_recovered, md.total_score, md.z_rank\n" +
						"FROM MergedData md\n" +
						"ORDER BY md.total_score DESC;\n";

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
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button
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
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					zone_code = "null";
					Integer Zonal_rank = null;
					String commName = "null";

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
	//  http://localhost:8080/cbicApi/cbic/t_score/investigation?month_date=2023-04-01&type=zone
	//  http://localhost:8080/cbicApi/cbic/t_score/investigation?month_date=2023-04-01&type=commissary&zone_code=59
	public Object investigation(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("zone")) {
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
			}else if (type.equalsIgnoreCase("commissary")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = null;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = rsGst14aa.getString("GST");
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
	 * Purpose: This methods have core function in adjudication.
	 */
	@ResponseBody
	@RequestMapping(value = "/adjudication") //5
	//  http://localhost:8080/cbicApi/cbic/t_score/adjudication?month_date=2023-04-01&type=zone
	//  http://localhost:8080/cbicApi/cbic/t_score/adjudication?month_date=2023-04-01&type=commissary&zone_code=59
	public Object adjudication(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("zone")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH RankedData AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
						"           SUM(14c.adc_commissionerate_disposal_no +  14c.adc_audit_disposal_no +  14c.adc_investigation_disposal_no + \n" +
						"               14c.adc_investigation_disposal_no +  14c.adc_callbook_disposal_no +  14c.dc_commissionerate_disposal_no + \n" +
						"               14c.dc_audit_disposal_no +   14c.dc_investigation_disposal_no +  14c.dc_callbook_disposal_no + \n" +
						"               14c.superintendent_commissionerate_disposal_no +   14c.superintendent_audit_disposal_no +   14c.superintendent_investigation_disposal_no + \n" +
						"               14c.superintendent_callbook_disposal_no) AS col9,\n" +
						"           SUM(14c.ADC_COMMISSIONERATE_OPENING_NO +  14c.ADC_AUDIT_OPENING_NO +  14c.ADC_INVESTIGATION_OPENING_NO + \n" +
						"               14c.ADC_CALLBOOK_OPENING_NO +  14c.DC_COMMISSIONERATE_OPENING_NO +  14c.DC_AUDIT_OPENING_NO + \n" +
						"               14c.DC_INVESTIGATION_OPENING_NO +  14c.DC_CALLBOOK_OPENING_NO +  14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + \n" +
						"               14c.SUPERINTENDENT_AUDIT_OPENING_NO +  14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO +  14c.SUPERINTENDENT_CALLBOOK_OPENING_NO) AS col3,\n" +
						"           COALESCE(\n" +
						"             (SUM(14c.adc_commissionerate_disposal_no +   14c.adc_audit_disposal_no +  14c.adc_investigation_disposal_no + \n" +
						"                  14c.adc_investigation_disposal_no +  14c.adc_callbook_disposal_no +  14c.dc_commissionerate_disposal_no + \n" +
						"                  14c.dc_audit_disposal_no +  14c.dc_investigation_disposal_no +  14c.dc_callbook_disposal_no + \n" +
						"                  14c.superintendent_commissionerate_disposal_no +  14c.superintendent_audit_disposal_no +  14c.superintendent_investigation_disposal_no + \n" +
						"                  14c.superintendent_callbook_disposal_no) / \n" +
						"                  SUM(14c.ADC_COMMISSIONERATE_OPENING_NO +   14c.ADC_AUDIT_OPENING_NO +  14c.ADC_INVESTIGATION_OPENING_NO + \n" +
						"                  14c.ADC_CALLBOOK_OPENING_NO +   14c.DC_COMMISSIONERATE_OPENING_NO +  14c.DC_AUDIT_OPENING_NO + \n" +
						"                  14c.DC_INVESTIGATION_OPENING_NO +   14c.DC_CALLBOOK_OPENING_NO +  14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + \n" +
						"                  14c.SUPERINTENDENT_AUDIT_OPENING_NO +    14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO +  14c.SUPERINTENDENT_CALLBOOK_OPENING_NO)), 0.00) AS total_score\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
						"    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						")\n" +
						"SELECT ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank,\n" +
						"       ZONE_NAME, ZONE_CODE, col9,  col3,  total_score\n" +
						"FROM RankedData\n" +
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
			}else if (type.equalsIgnoreCase("commissary")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "SELECT \n" +
						"    zc.ZONE_NAME, \n" +
						"    cc.ZONE_CODE, \n" +
						"    cc.COMM_NAME,\n" +
						"    (14c.adc_commissionerate_disposal_no + \n" +
						"     14c.adc_audit_disposal_no + \n" +
						"     14c.adc_investigation_disposal_no + \n" +
						"     14c.adc_investigation_disposal_no + \n" +
						"     14c.adc_callbook_disposal_no + \n" +
						"     14c.dc_commissionerate_disposal_no + \n" +
						"     14c.dc_audit_disposal_no + \n" +
						"     14c.dc_investigation_disposal_no + \n" +
						"     14c.dc_callbook_disposal_no + \n" +
						"     14c.superintendent_commissionerate_disposal_no + \n" +
						"     14c.superintendent_audit_disposal_no + \n" +
						"     14c.superintendent_investigation_disposal_no + \n" +
						"     14c.superintendent_callbook_disposal_no) as col9,\n" +
						"    (14c.ADC_COMMISSIONERATE_OPENING_NO + \n" +
						"     14c.ADC_AUDIT_OPENING_NO + \n" +
						"     14c.ADC_INVESTIGATION_OPENING_NO + \n" +
						"     14c.ADC_CALLBOOK_OPENING_NO + \n" +
						"     14c.DC_COMMISSIONERATE_OPENING_NO + \n" +
						"     14c.DC_AUDIT_OPENING_NO + \n" +
						"     14c.DC_INVESTIGATION_OPENING_NO + \n" +
						"     14c.DC_CALLBOOK_OPENING_NO + \n" +
						"     14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + \n" +
						"     14c.SUPERINTENDENT_AUDIT_OPENING_NO + \n" +
						"     14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + \n" +
						"     14c.SUPERINTENDENT_CALLBOOK_OPENING_NO) as col3,\n" +
						"    'GST 5A' as GST,\n" +
						"    COALESCE(\n" +
						"        (14c.adc_commissionerate_disposal_no + \n" +
						"         14c.adc_audit_disposal_no + \n" +
						"         14c.adc_investigation_disposal_no + \n" +
						"         14c.adc_investigation_disposal_no + \n" +
						"         14c.adc_callbook_disposal_no + \n" +
						"         14c.dc_commissionerate_disposal_no + \n" +
						"         14c.dc_audit_disposal_no + \n" +
						"         14c.dc_investigation_disposal_no + \n" +
						"         14c.dc_callbook_disposal_no + \n" +
						"         14c.superintendent_commissionerate_disposal_no + \n" +
						"         14c.superintendent_audit_disposal_no + \n" +
						"         14c.superintendent_investigation_disposal_no + \n" +
						"         14c.superintendent_callbook_disposal_no) / \n" +
						"        (14c.ADC_COMMISSIONERATE_OPENING_NO + \n" +
						"         14c.ADC_AUDIT_OPENING_NO + \n" +
						"         14c.ADC_INVESTIGATION_OPENING_NO + \n" +
						"         14c.ADC_CALLBOOK_OPENING_NO + \n" +
						"         14c.DC_COMMISSIONERATE_OPENING_NO + \n" +
						"         14c.DC_AUDIT_OPENING_NO + \n" +
						"         14c.DC_INVESTIGATION_OPENING_NO + \n" +
						"         14c.DC_CALLBOOK_OPENING_NO + \n" +
						"         14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + \n" +
						"         14c.SUPERINTENDENT_AUDIT_OPENING_NO + \n" +
						"         14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + \n" +
						"         14c.SUPERINTENDENT_CALLBOOK_OPENING_NO), \n" +
						"        0.00\n" +
						"    ) as total_score,\n" +
						"    CONCAT(\n" +
						"        (14c.adc_commissionerate_disposal_no + \n" +
						"         14c.adc_audit_disposal_no + \n" +
						"         14c.adc_investigation_disposal_no + \n" +
						"         14c.adc_investigation_disposal_no + \n" +
						"         14c.adc_callbook_disposal_no + \n" +
						"         14c.dc_commissionerate_disposal_no + \n" +
						"         14c.dc_audit_disposal_no + \n" +
						"         14c.dc_investigation_disposal_no + \n" +
						"         14c.dc_callbook_disposal_no + \n" +
						"         14c.superintendent_commissionerate_disposal_no + \n" +
						"         14c.superintendent_audit_disposal_no + \n" +
						"         14c.superintendent_investigation_disposal_no + \n" +
						"         14c.superintendent_callbook_disposal_no), \n" +
						"        '/', \n" +
						"        (14c.ADC_COMMISSIONERATE_OPENING_NO + \n" +
						"         14c.ADC_AUDIT_OPENING_NO + \n" +
						"         14c.ADC_INVESTIGATION_OPENING_NO + \n" +
						"         14c.ADC_CALLBOOK_OPENING_NO + \n" +
						"         14c.DC_COMMISSIONERATE_OPENING_NO + \n" +
						"         14c.DC_AUDIT_OPENING_NO + \n" +
						"         14c.DC_INVESTIGATION_OPENING_NO + \n" +
						"         14c.DC_CALLBOOK_OPENING_NO + \n" +
						"         14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + \n" +
						"         14c.SUPERINTENDENT_AUDIT_OPENING_NO + \n" +
						"         14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + \n" +
						"         14c.SUPERINTENDENT_CALLBOOK_OPENING_NO)\n" +
						"    ) as absolute_value\n" +
						"FROM \n" +
						"    mis_gst_commcode as cc \n" +
						"RIGHT JOIN \n" +
						"    mis_dpm_gst_adj_1 as 14c \n" +
						"    ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"LEFT JOIN \n" +
						"    mis_gst_zonecode as zc \n" +
						"    ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"WHERE \n" +
						"    14c.MM_YYYY = '" + month_date + "' \n" +
						"    AND cc.ZONE_CODE = '" + zone_code + "';\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = null;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = rsGst14aa.getString("GST");
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
	 * Purpose: This methods have core function in adjudication/legacy.
	 */
	@ResponseBody
	@RequestMapping(value = "/adjudication/legacy") //6
	//  http://localhost:8080/cbicApi/cbic/t_score/adjudication/legacy?month_date=2023-04-01&type=zone
	//  http://localhost:8080/cbicApi/cbic/t_score/adjudication/legacy?month_date=2023-04-01&type=commissary&zone_code=59
	public Object adjudicationLegacy(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("zone")) {
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
			}else if (type.equalsIgnoreCase("commissary")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = null;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = rsGst14aa.getString("GST");
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
	 * Purpose: This methods have core function in adjudication.
	 */
	@ResponseBody
	@RequestMapping(value = "/refunds") //7
	//  http://localhost:8080/cbicApi/cbic/t_score/refunds?month_date=2023-04-01&type=zone
	//  http://localhost:8080/cbicApi/cbic/t_score/refunds?month_date=2023-04-01&type=commissary&zone_code=59
	public Object refunds(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("zone")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH ScoreCalculation AS (\n" +
						"    SELECT \n" +
						"        SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS col16, \n" +
						"        SUM(14c.age_breakup_above60_no) AS col22,\n" +
						"        cc.ZONE_CODE, zc.ZONE_NAME \n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dpm_gst_4 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
						"    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						")\n" +
						"SELECT ZONE_CODE,ZONE_NAME, col16, col22,\n" +
						"    CASE WHEN col16 != 0 THEN col22 / col16 ELSE 0 END AS total_score,\n" +
						"    RANK() OVER (ORDER BY CASE WHEN col16 != 0 THEN col22 / col16 ELSE 0 END DESC) AS z_rank\n" +
						"FROM ScoreCalculation\n" +
						"ORDER BY total_score DESC;";

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
			}else if (type.equalsIgnoreCase("commissary")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment =
//						"SELECT \n" +
//						"  cc.ZONE_CODE AS zone_code,\n" +
//						"  zc.ZONE_NAME AS zone_name,\n" +
//						"  cc.COMM_NAME AS comm_name,\n" +
//						"  'GST 7' AS gst,\n" +
//						"  SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS col16,\n" +
//						"  SUM(14c.age_breakup_above60_no) AS col22,\n" +
//						"  SUM(14c.age_breakup_above60_no) / NULLIF(SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO), 0) AS total_score,\n" +
//						"  CONCAT(\n" +
//						"    SUM(14c.age_breakup_above60_no), '/', \n" +
//						"    NULLIF(SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO), 0)\n" +
//						"  ) AS absolute_value\n" +
//						"FROM mis_dpm_gst_4 AS 14c \n" +
//						"LEFT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE\n" +
//						"LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
//						"WHERE \n" +
//						"  14c.MM_YYYY = '" + month_date + "' \n" +
//						"  AND cc.ZONE_CODE = '" + zone_code + "'\n" +
//						"GROUP BY cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME;\n";


						"SELECT \n" +
								"    cc.ZONE_CODE AS zone_code,  \n" +
								"    zc.ZONE_NAME AS zone_name, \n" +
								"    cc.COMM_NAME AS comm_name,\n" +
								"    SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS col16,\n" +
								"    SUM(14c.age_breakup_above60_no) AS col22,\n" +
								"    ROW_NUMBER() OVER (ORDER BY SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) DESC) AS z_rank\n" +
								"FROM mis_dpm_gst_4 AS 14c \n" +
								"LEFT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE\n" +
								"LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
								"WHERE \n" +
								"    14c.MM_YYYY = '" + month_date + "' \n" +
								"    AND cc.ZONE_CODE = '" + zone_code + "' \n" +
								"GROUP BY \n" +
								"    cc.ZONE_CODE,  \n" +
								"    zc.ZONE_NAME, \n" +
								"    cc.COMM_NAME\n" +
								"ORDER BY \n" +
								"    col16 DESC;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = rsGst14aa.getString("GST");
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
	//  http://localhost:8080/cbicApi/cbic/t_score/recovery?month_date=2023-04-01&type=zone
	//  http://localhost:8080/cbicApi/cbic/t_score/recovery?month_date=2023-04-01&type=commissary&zone_code=59
	public Object recovery(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("zone")) {
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
			}else if (type.equalsIgnoreCase("commissary")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = null;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = rsGst14aa.getString("GST");
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
	 * Purpose: This methods have core function in arrest/prosecution.
	 */
	@ResponseBody
	@RequestMapping(value = "/arrest/prosecution") //9
	//  http://localhost:8080/cbicApi/cbic/t_score/arrest/prosecution?month_date=2023-04-01&type=zone
	//  http://localhost:8080/cbicApi/cbic/t_score/arrest/prosecution?month_date=2023-04-01&type=commissary&zone_code=59
	public Object arrestProsecution(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("zone")) {
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
			}else if (type.equalsIgnoreCase("commissary")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = null;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = rsGst14aa.getString("GST");
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
	 * Purpose: This methods have core function in audit.
	 */
	@ResponseBody
	@RequestMapping(value = "/audit") //10
	//  http://localhost:8080/cbicApi/cbic/t_score/audit?month_date=2023-04-01&type=zone
	//  http://localhost:8080/cbicApi/cbic/t_score/audit?month_date=2023-04-01&type=commissary&zone_code=59
	public Object audit(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("zone")) {
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
			}else if (type.equalsIgnoreCase("commissary")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = null;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = rsGst14aa.getString("GST");
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
	 * Purpose: This methods have core function in appeals.
	 */
	@ResponseBody
	@RequestMapping(value = "/appeals") //11
	//  http://localhost:8080/cbicApi/cbic/t_score/appeals?month_date=2023-04-01&type=zone
	//  http://localhost:8080/cbicApi/cbic/t_score/appeals?month_date=2023-04-01&type=commissary&zone_code=59
	public Object appeals(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("zone")) {
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
			}else if (type.equalsIgnoreCase("commissary")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = null;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = rsGst14aa.getString("GST");
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
}