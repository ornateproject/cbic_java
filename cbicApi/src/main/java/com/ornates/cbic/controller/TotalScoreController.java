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

// total score 1(commissary only with 1E and 1F), 2, 3, 5(only 5a)

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
	//  http://localhost:8080/CbicAPI/cbic/t_score/registration?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/t_score/registration?month_date=2023-04-01&type=commissary&zone_code=59

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
	//  http://localhost:8080/CbicAPI/cbic/t_score/returnFiling?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/t_score/returnFiling?month_date=2023-04-01&type=commissary&zone_code=59
	public Object returnFiling(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("zone")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

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
	//  http://localhost:8080/CbicAPI/cbic/t_score/scrutiny/assessment?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/t_score/scrutiny/assessment?month_date=2023-04-01&type=commissary&zone_code=59
	public Object scrutinyAssessment(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("zone")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH cte AS (\n" +
						"                        SELECT \n" +
						"                            zc.ZONE_NAME, \n" +
						"                            cc.ZONE_CODE, \n" +
						"                            (SUM(CASE WHEN 14c.MM_YYYY = '" + month_date + "' THEN 14c.SCRUTINIZED_ASMT_10 ELSE 0 END) + \n" +
						"                             SUM(CASE WHEN 14c.MM_YYYY = '" + month_date + "' THEN 14c.SCRUTINIZED_DISCRIPANCY_FOUND ELSE 0 END)) /\n" +
						"                            (SUM(CASE WHEN 14c.MM_YYYY = '" + month_date + "' THEN 14c.RETURN_SCRUTINY ELSE 0 END) + \n" +
						"                             SUM(CASE WHEN 14c.MM_YYYY = '" + prev_month_new + "' THEN 14c.CLOSING_NO ELSE 0 END)) AS hgf\n" +
						"                        FROM mis_gst_commcode AS cc \n" +
						"                        RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"                        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"                        WHERE 14c.MM_YYYY IN ('" + month_date + "', '" + prev_month_new + "') \n" +
						"                        GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
						"\n" +
						"                        UNION ALL\n" +
						"\n" +
						"                        SELECT \n" +
						"                            zc.ZONE_NAME, \n" +
						"                            cc.ZONE_CODE, \n" +
						"                            SUM(14c.amount_recovered_penalty) / SUM(14c.tax_liability_detectect) AS hgf\n" +
						"                        FROM mis_gst_commcode AS cc\n" +
						"                        RIGHT JOIN mis_dggst_gst_scr_2a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"                        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"                        WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
						"                        GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
						"                    )\n" +
						"                    SELECT ROW_NUMBER() OVER (ORDER BY AVG(hgf) DESC) AS z_rank,\n" +
						"                           ZONE_NAME, \n" +
						"                           ZONE_CODE, \n" +
						"                           AVG(hgf) AS total_score\n" +
						"                    FROM cte \n" +
						"                    GROUP BY ZONE_NAME, ZONE_CODE \n" +
						"                    ORDER BY total_score DESC;";

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

				String query_assessment = "WITH SecondQuery AS (\n" +
						"    SELECT cc.COMM_CODE, 14c.CLOSING_NO AS col2\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    WHERE cc.ZONE_CODE =  '" + zone_code + "' AND 14c.MM_YYYY = '" + prev_month_new + "'\n" +
						")\n" +
						"SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, \n" +
						"    14c.SCRUTINIZED_ASMT_10 AS col6, \n" +
						"    14c.SCRUTINIZED_DISCRIPANCY_FOUND AS col5, \n" +
						"    14c.RETURN_SCRUTINY AS col3,sq.col2,'GST 3A' AS GST,\n" +
						"    CASE \n" +
						"        WHEN (COALESCE(sq.col2, 0) + COALESCE(14c.RETURN_SCRUTINY, 0)) = 0 THEN NULL\n" +
						"        ELSE (COALESCE(14c.SCRUTINIZED_DISCRIPANCY_FOUND, 0) + COALESCE(14c.SCRUTINIZED_ASMT_10, 0)) / \n" +
						"             (COALESCE(sq.col2, 0) + COALESCE(14c.RETURN_SCRUTINY, 0))\n" +
						"    END AS total_score,\n" +
						"    CASE \n" +
						"        WHEN (COALESCE(sq.col2, 0) + COALESCE(14c.RETURN_SCRUTINY, 0)) = 0 THEN '0/0'\n" +
						"        ELSE CONCAT(\n" +
						"            COALESCE(14c.SCRUTINIZED_DISCRIPANCY_FOUND, 0) + COALESCE(14c.SCRUTINIZED_ASMT_10, 0), '/', \n" +
						"            COALESCE(sq.col2, 0) + COALESCE(14c.RETURN_SCRUTINY, 0)\n" +
						"        )\n" +
						"    END AS absolute_value,NULL AS col22,NULL AS col14\n" +
						"FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    LEFT JOIN SecondQuery AS sq ON sq.COMM_CODE = cc.COMM_CODE\n" +
						"WHERE cc.ZONE_CODE =  '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "'\n" +
						"\n" +
						"UNION ALL\n" +
						"\n" +
						"SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, NULL AS col6, NULL AS col5, NULL AS col3,NULL AS col2,'GST 3B' AS GST,\n" +
						"    (14c.amount_recovered_penalty / 14c.tax_liability_detectect) AS total_score,\n" +
						"    CONCAT(14c.amount_recovered_penalty, '/', 14c.tax_liability_detectect) AS absolute_value,\n" +
						"    14c.amount_recovered_penalty AS col22, \n" +
						"    14c.tax_liability_detectect AS col14\n" +
						"FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dggst_gst_scr_2a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"WHERE 14c.MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE =  '" + zone_code + "';\n";

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
	 * Date: jund 09, 2024
	 * created: RKS
	 * updated:
	 * Purpose: This methods have core function in investigation.
	 */
	@ResponseBody
	@RequestMapping(value = "/investigation")// 4
	//  http://localhost:8080/CbicAPI/cbic/t_score/investigation?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/t_score/investigation?month_date=2023-04-01&type=commissary&zone_code=59
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
	//  http://localhost:8080/CbicAPI/cbic/t_score/adjudication?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/t_score/adjudication?month_date=2023-04-01&type=commissary&zone_code=59
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
	 * Purpose: This methods have core function in adjudication/legacy.
	 */
	@ResponseBody
	@RequestMapping(value = "/adjudication/legacy") //6
	//  http://localhost:8080/CbicAPI/cbic/t_score/adjudication/legacy?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/t_score/adjudication/legacy?month_date=2023-04-01&type=commissary&zone_code=59
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
	//  http://localhost:8080/CbicAPI/cbic/t_score/refunds?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/t_score/refunds?month_date=2023-04-01&type=commissary&zone_code=59
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
						"    WHERE 14c.MM_YYYY = '2023-04-01' \n" +
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
	 * Purpose: This methods have core function in recovery.
	 */
	@ResponseBody
	@RequestMapping(value = "/recovery") //8
	//  http://localhost:8080/CbicAPI/cbic/t_score/recovery?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/t_score/recovery?month_date=2023-04-01&type=commissary&zone_code=59
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
	//  http://localhost:8080/CbicAPI/cbic/t_score/arrest/prosecution?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/t_score/arrest/prosecution?month_date=2023-04-01&type=commissary&zone_code=59
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
	//  http://localhost:8080/CbicAPI/cbic/t_score/audit?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/t_score/audit?month_date=2023-04-01&type=commissary&zone_code=59
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
	//  http://localhost:8080/CbicAPI/cbic/t_score/appeals?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/t_score/appeals?month_date=2023-04-01&type=commissary&zone_code=59
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
