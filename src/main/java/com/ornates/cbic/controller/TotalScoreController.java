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
@RequestMapping("/cbic/t_score")
@Controller
public class TotalScoreController {
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
//

	@ResponseBody
	@RequestMapping(value = "/registration")// cgst 1
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
//              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "SELECT \n"
						+ "    t1.ZONE_NAME,t1.ZONE_CODE,t1.total_score1,t2.total_score2,t3.total_score3,t4.total_score4,\n"
						+ "    t1.total_score1 + t2.total_score2 + t3.total_score3 + t4.total_score4 AS total_score,\n"
						+ "    ROW_NUMBER() OVER (ORDER BY (t1.total_score1 + t2.total_score2 + t3.total_score3 + t4.total_score4) DESC) AS z_rank\n"
						+ "FROM\n"
						+ "    (SELECT \n"
						+ "            zc.ZONE_NAME, cc.ZONE_CODE,\n"
						+ "            CASE \n"
						+ "                WHEN SUM(14c.NO_OF_ARN_RECEIVED_GSTN) + SUM(14c.NO_OF_ARN_RECEIVED_CPC) = 0 THEN 0\n"
						+ "                ELSE SUM(14c.DISPOSAL_OF_ARN_DEEMED_REG) / (SUM(14c.NO_OF_ARN_RECEIVED_GSTN) + SUM(14c.NO_OF_ARN_RECEIVED_CPC))\n"
						+ "            END AS total_score1\n"
						+ "        FROM mis_gst_commcode AS cc \n"
						+ "        RIGHT JOIN mis_dpm_gst_14a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
						+ "        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
						+ "        WHERE MM_YYYY = '" + month_date + "' \n"
						+ "        GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n"
						+ "    ) AS t1\n"
						+ "JOIN\n"
						+ "    (SELECT \n"
						+ "            zc.ZONE_NAME, cc.ZONE_CODE,\n"
						+ "            CASE \n"
						+ "                WHEN SUM(14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC) = 0 THEN 0\n"
						+ "                ELSE SUM((14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC) \n"
						+ "                         - (14c.DISPOSAL_OF_ARN_TRANSFERED_CPC + 14c.DISPOSAL_OF_ARN_DEEMED_REG + 14c.DISPOSAL_OF_ARN_WITHIN_7 \n"
						+ "                            + 14c.DISPOSAL_OF_ARN_PV_APPROVED + 14c.DISPOSAL_OF_ARN_PV_REJECTED))\n"
						+ "                     / SUM(14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC)\n"
						+ "            END AS total_score2\n"
						+ "        FROM  mis_gst_commcode AS cc \n"
						+ "        RIGHT JOIN mis_dpm_gst_14a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
						+ "        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
						+ "        WHERE MM_YYYY = '" + month_date + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n"
						+ "    ) AS t2\n"
						+ "    ON t1.ZONE_NAME = t2.ZONE_NAME AND t1.ZONE_CODE = t2.ZONE_CODE\n"
						+ "JOIN\n"
						+ "    (SELECT \n"
						+ "            zc.ZONE_NAME, cc.ZONE_CODE,\n"
						+ "            SUM((14c.SUO_MOTO_OPENING_BALANCE + 14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION - 14c.SUO_MOTO_GSTIN_CANCELLED) \n"
						+ "                + (14c.CANCELLATION_OPENING_BALANCE + 14c.CANCELLATION_NO_APPLICATION_RECEIVED - 14c.CANCELLATION_GSTIN_CANCELLED)) \n"
						+ "                / (SUM(14c.SUO_MOTO_OPENING_BALANCE) + SUM(14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION) \n"
						+ "                   + SUM(14c.CANCELLATION_OPENING_BALANCE) + SUM(14c.CANCELLATION_NO_APPLICATION_RECEIVED)) AS total_score3\n"
						+ "        FROM mis_gst_commcode AS cc \n"
						+ "        RIGHT JOIN mis_dpm_gst_15a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
						+ "        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
						+ "        WHERE 14c.MM_YYYY = '" + month_date + "' \n"
						+ "        GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n"
						+ "    ) AS t3\n"
						+ "    ON t1.ZONE_NAME = t3.ZONE_NAME AND t1.ZONE_CODE = t3.ZONE_CODE\n"
						+ "JOIN\n"
						+ "    (\n"
						+ "        SELECT \n"
						+ "            zc.ZONE_NAME, cc.ZONE_CODE,\n"
						+ "            SUM(15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED - 15a.REVOCATION_GSTIN_REVOKED - 15a.REVOCATION_APPLICATION_REJECTED) \n"
						+ "                / (SUM(15a.REVOCATION_OPENING_BALANCE) + SUM(15a.REVOCATION_ARN_RECEIVED)) AS total_score4\n"
						+ "        FROM  mis_gst_commcode AS cc \n"
						+ "        RIGHT JOIN mis_dpm_gst_15a AS 15a ON cc.COMM_CODE = 15a.COMM_CODE \n"
						+ "        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
						+ "        WHERE 15a.MM_YYYY = '" + month_date + "' \n"
						+ "        GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n"
						+ "    ) AS t4\n"
						+ "    ON t1.ZONE_NAME = t4.ZONE_NAME AND t1.ZONE_CODE = t4.ZONE_CODE\n"
						+ "ORDER BY total_score DESC;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double total_score = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String ra ="Registration";
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;

					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				//	'" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH total_score1 AS (\n"
						+ "    SELECT zc.ZONE_NAME,cc.ZONE_CODE,cc.COMM_NAME,\n"
						+ "        (14c.DISPOSAL_OF_ARN_DEEMED_REG / 14c.NO_OF_ARN_RECEIVED_GSTN) + 14c.NO_OF_ARN_RECEIVED_CPC AS total_score1\n"
						+ "    FROM mis_gst_commcode cc\n"
						+ "    RIGHT JOIN mis_dpm_gst_14a 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE MM_YYYY = '" + month_date + "'\n"
						+ "),\n"
						+ "total_score2 AS (\n"
						+ "    SELECT zc.ZONE_NAME,cc.ZONE_CODE,cc.COMM_NAME,\n"
						+ "        ((14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC) -\n"
						+ "         (14c.DISPOSAL_OF_ARN_TRANSFERED_CPC + 14c.DISPOSAL_OF_ARN_DEEMED_REG + 14c.DISPOSAL_OF_ARN_WITHIN_7 +\n"
						+ "          14c.DISPOSAL_OF_ARN_PV_APPROVED + 14c.DISPOSAL_OF_ARN_PV_REJECTED)) / (14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC) AS total_score2\n"
						+ "    FROM mis_gst_commcode cc\n"
						+ "    RIGHT JOIN mis_dpm_gst_14a 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE MM_YYYY = '" + month_date + "'\n"
						+ "),\n"
						+ "total_score3 AS (\n"
						+ "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n"
						+ "        ((14c.SUO_MOTO_OPENING_BALANCE + 14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION - 14c.SUO_MOTO_GSTIN_CANCELLED) +\n"
						+ "            (14c.CANCELLATION_OPENING_BALANCE + 14c.CANCELLATION_NO_APPLICATION_RECEIVED - 14c.CANCELLATION_GSTIN_CANCELLED)) / \n"
						+ "            (14c.SUO_MOTO_OPENING_BALANCE + 14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION + 14c.CANCELLATION_OPENING_BALANCE + 14c.CANCELLATION_NO_APPLICATION_RECEIVED) AS total_score3\n"
						+ "    FROM mis_gst_commcode AS cc\n"
						+ "    RIGHT JOIN mis_dpm_gst_15a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE MM_YYYY = '" + month_date + "'\n"
						+ "),\n"
						+ "total_score4 AS (\n"
						+ "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n"
						+ "        (\n"
						+ "            15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED - 15a.REVOCATION_GSTIN_REVOKED - 15a.REVOCATION_APPLICATION_REJECTED\n"
						+ "        ) / (15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED) AS total_score4\n"
						+ "    FROM mis_gst_commcode AS cc\n"
						+ "    RIGHT JOIN mis_dpm_gst_15a AS 15a ON cc.COMM_CODE = 15a.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE MM_YYYY = '" + month_date + "'\n"
						+ "),\n"
						+ "score_calculation AS (\n"
						+ "    SELECT\n"
						+ "        ts1.ZONE_NAME, ts1.ZONE_CODE, ts1.COMM_NAME, ts1.total_score1, ts2.total_score2, ts3.total_score3, ts4.total_score4,\n"
						+ "        COALESCE(ts1.total_score1, 0) + COALESCE(ts2.total_score2, 0) + COALESCE(ts3.total_score3, 0) + COALESCE(ts4.total_score4, 0) AS total_score\n"
						+ "    FROM total_score1 ts1\n"
						+ "    JOIN total_score2 ts2 ON ts1.ZONE_CODE = ts2.ZONE_CODE AND ts1.COMM_NAME = ts2.COMM_NAME\n"
						+ "    JOIN total_score3 ts3 ON ts1.ZONE_CODE = ts3.ZONE_CODE AND ts1.COMM_NAME = ts3.COMM_NAME\n"
						+ "    JOIN total_score4 ts4 ON ts1.ZONE_CODE = ts4.ZONE_CODE AND ts1.COMM_NAME = ts4.COMM_NAME\n"
						+ "),\n"
						+ "ranked_scores AS (\n"
						+ "    SELECT\n"
						+ "        ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank,\n"
						+ "        ZONE_NAME, ZONE_CODE, COMM_NAME, total_score\n"
						+ "    FROM score_calculation\n"
						+ ")\n"
						+ "SELECT * FROM ranked_scores\n"
						+ "WHERE\n"
						+ "    COMM_NAME IN (\n"
						+ "        SELECT DISTINCT COMM_NAME FROM ranked_scores WHERE ZONE_CODE = '" + zone_code + "'	\n"
						+ "    ) ORDER BY z_rank;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String ra ="null";
					String gst = "null";
					String absval = "null";
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
//				'" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n"
						+ "    CASE \n"
						+ "        WHEN SUM(14c.NO_OF_ARN_RECEIVED_GSTN) + SUM(14c.NO_OF_ARN_RECEIVED_CPC) = 0 THEN 0\n"
						+ "        ELSE SUM(14c.DISPOSAL_OF_ARN_DEEMED_REG) / (SUM(14c.NO_OF_ARN_RECEIVED_GSTN) + SUM(14c.NO_OF_ARN_RECEIVED_CPC))\n"
						+ "    END AS score_of_subParameter,\n"
						+ "    'GST1c' AS gst, 'Number of Deemed registrations vis-à-vis total number of applications received for registration in the month ' as ra,\n"
						+ "    CONCAT(SUM(14c.DISPOSAL_OF_ARN_DEEMED_REG), '/', SUM(14c.NO_OF_ARN_RECEIVED_GSTN) + SUM(14c.NO_OF_ARN_RECEIVED_CPC)) AS absolute_value\n"
						+ "FROM  mis_gst_commcode AS cc \n"
						+ "RIGHT JOIN mis_dpm_gst_14a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
						+ "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
						+ "WHERE MM_YYYY = '" + month_date + "'  AND cc.ZONE_CODE = '" + zone_code + "'GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n"
						+ "\n"
						+ "UNION ALL\n"
						+ "\n"
						+ "SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n"
						+ "    CASE \n"
						+ "        WHEN SUM(14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC) = 0 THEN 0\n"
						+ "        ELSE SUM((14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC) \n"
						+ "                 - (14c.DISPOSAL_OF_ARN_TRANSFERED_CPC + 14c.DISPOSAL_OF_ARN_DEEMED_REG + 14c.DISPOSAL_OF_ARN_WITHIN_7 \n"
						+ "                    + 14c.DISPOSAL_OF_ARN_PV_APPROVED + 14c.DISPOSAL_OF_ARN_PV_REJECTED))\n"
						+ "             / SUM(14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC)\n"
						+ "    END AS score_of_subParameter,\n"
						+ "    'GST1d' AS gst, '*Percentage of registration applications pending at the end of the month vis-à-vis total applications for registration received. \n"
						+ "(OB+R-D)/(OB+R)\n"
						+ "' as ra,\n"
						+ "    CASE \n"
						+ "        WHEN SUM(14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC) = 0 THEN '0/1'\n"
						+ "        ELSE CONCAT(SUM((14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC) \n"
						+ "                        - (14c.DISPOSAL_OF_ARN_TRANSFERED_CPC + 14c.DISPOSAL_OF_ARN_DEEMED_REG + 14c.DISPOSAL_OF_ARN_WITHIN_7 \n"
						+ "                           + 14c.DISPOSAL_OF_ARN_PV_APPROVED + 14c.DISPOSAL_OF_ARN_PV_REJECTED)), '/',\n"
						+ "                   SUM(14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC))\n"
						+ "    END AS absolute_value\n"
						+ "FROM  mis_gst_commcode AS cc \n"
						+ "RIGHT JOIN mis_dpm_gst_14a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
						+ "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
						+ "WHERE MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n"
						+ "\n"
						+ "UNION ALL\n"
						+ "\n"
						+ "SELECT \n"
						+ "    zc.ZONE_NAME, cc.ZONE_CODE,\n"
						+ "    SUM((14c.SUO_MOTO_OPENING_BALANCE + 14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION - 14c.SUO_MOTO_GSTIN_CANCELLED) \n"
						+ "        + (14c.CANCELLATION_OPENING_BALANCE + 14c.CANCELLATION_NO_APPLICATION_RECEIVED - 14c.CANCELLATION_GSTIN_CANCELLED)) \n"
						+ "        / (SUM(14c.SUO_MOTO_OPENING_BALANCE) + SUM(14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION) \n"
						+ "           + SUM(14c.CANCELLATION_OPENING_BALANCE) + SUM(14c.CANCELLATION_NO_APPLICATION_RECEIVED)) AS score_of_subParameter,\n"
						+ "    'GST1e' AS gst, '*Percentage of registrations pending for cancellation at the end of the month vis-à-vis total applications initiated for cancellation \n"
						+ "(OB+R-D)/(OB+R)\n"
						+ " ' as ra,\n"
						+ "    CONCAT( SUM((14c.SUO_MOTO_OPENING_BALANCE + 14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION - 14c.SUO_MOTO_GSTIN_CANCELLED) \n"
						+ "        + (14c.CANCELLATION_OPENING_BALANCE + 14c.CANCELLATION_NO_APPLICATION_RECEIVED - 14c.CANCELLATION_GSTIN_CANCELLED)), '/', SUM(14c.SUO_MOTO_OPENING_BALANCE) + SUM(14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION)+SUM(14c.CANCELLATION_OPENING_BALANCE)+SUM(14c.CANCELLATION_NO_APPLICATION_RECEIVED)) AS absolute_value\n"
						+ "FROM  mis_gst_commcode AS cc \n"
						+ "RIGHT JOIN mis_dpm_gst_15a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
						+ "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
						+ "WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n"
						+ "\n"
						+ "UNION ALL\n"
						+ "\n"
						+ "SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n"
						+ "    SUM(15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED - 15a.REVOCATION_GSTIN_REVOKED - 15a.REVOCATION_APPLICATION_REJECTED) \n"
						+ "        / (SUM(15a.REVOCATION_OPENING_BALANCE) + SUM(15a.REVOCATION_ARN_RECEIVED)) AS score_of_subParameter,\n"
						+ "    'GST1f' AS gst,\n"
						+ "    '*Percentage of registration pending for revocation at the end of the month vis-à-vis total applications received for revocation of cancellation \n"
						+ "(OB+R-D)/(OB+R)\n"
						+ "' as ra, CONCAT(SUM(15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED - 15a.REVOCATION_GSTIN_REVOKED - 15a.REVOCATION_APPLICATION_REJECTED), '/', SUM(15a.REVOCATION_OPENING_BALANCE) + SUM(15a.REVOCATION_ARN_RECEIVED)) AS absolute_value\n"
						+ "FROM  mis_gst_commcode AS cc \n"
						+ "RIGHT JOIN mis_dpm_gst_15a AS 15a ON cc.COMM_CODE = 15a.COMM_CODE \n"
						+ "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
						+ "WHERE 15a.MM_YYYY = '" + month_date + "'  AND cc.ZONE_CODE = '" + zone_code + "'\n"
						+ "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String ra =rsGst14aa.getString("ra");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH total_score1 AS (\n"
						+ "    SELECT zc.ZONE_NAME,cc.ZONE_CODE,cc.COMM_NAME, (14c.DISPOSAL_OF_ARN_DEEMED_REG / 14c.NO_OF_ARN_RECEIVED_GSTN) + 14c.NO_OF_ARN_RECEIVED_CPC AS total_score1\n"
						+ "    FROM mis_gst_commcode cc\n"
						+ "    RIGHT JOIN mis_dpm_gst_14a 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE MM_YYYY = '" + month_date + "'\n"
						+ "),\n"
						+ "total_score2 AS (\n"
						+ "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n"
						+ "        ((14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC) -\n"
						+ "         (14c.DISPOSAL_OF_ARN_TRANSFERED_CPC + 14c.DISPOSAL_OF_ARN_DEEMED_REG + 14c.DISPOSAL_OF_ARN_WITHIN_7 +\n"
						+ "          14c.DISPOSAL_OF_ARN_PV_APPROVED + 14c.DISPOSAL_OF_ARN_PV_REJECTED)) / (14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC) AS total_score2\n"
						+ "    FROM mis_gst_commcode cc\n"
						+ "    RIGHT JOIN mis_dpm_gst_14a 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE MM_YYYY = '" + month_date + "'\n"
						+ "),\n"
						+ "total_score3 AS (\n"
						+ "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n"
						+ "        ((14c.SUO_MOTO_OPENING_BALANCE +14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION - 14c.SUO_MOTO_GSTIN_CANCELLED ) +\n"
						+ "            ( 14c.CANCELLATION_OPENING_BALANCE + 14c.CANCELLATION_NO_APPLICATION_RECEIVED -  14c.CANCELLATION_GSTIN_CANCELLED)\n"
						+ "        ) / (14c.SUO_MOTO_OPENING_BALANCE + 14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION + 14c.CANCELLATION_OPENING_BALANCE + 14c.CANCELLATION_NO_APPLICATION_RECEIVED) AS total_score3\n"
						+ "    FROM mis_gst_commcode AS cc\n"
						+ "    RIGHT JOIN mis_dpm_gst_15a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE MM_YYYY = '" + month_date + "'\n"
						+ "),\n"
						+ "total_score4 AS (\n"
						+ "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n"
						+ "        (15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED -  15a.REVOCATION_GSTIN_REVOKED - 15a.REVOCATION_APPLICATION_REJECTED\n"
						+ "        ) / (15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED) AS total_score4\n"
						+ "    FROM mis_gst_commcode AS cc\n"
						+ "    RIGHT JOIN mis_dpm_gst_15a AS 15a ON cc.COMM_CODE = 15a.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE MM_YYYY = '" + month_date + "'\n"
						+ ")\n"
						+ "SELECT\n"
						+ "    ts1.ZONE_NAME, ts1.ZONE_CODE, ts1.COMM_NAME,\n"
						+ "    COALESCE(ts1.total_score1, 0) AS total_score1, COALESCE(ts2.total_score2, 0) AS total_score2, COALESCE(ts3.total_score3, 0) AS total_score3,COALESCE(ts4.total_score4, 0) AS total_score4,\n"
						+ "    (COALESCE(ts1.total_score1, 0) + COALESCE(ts2.total_score2, 0) + COALESCE(ts3.total_score3, 0) + COALESCE(ts4.total_score4, 0)) AS total_score,\n"
						+ "    ROW_NUMBER() OVER (ORDER BY (COALESCE(ts1.total_score1, 0) + COALESCE(ts2.total_score2, 0) + COALESCE(ts3.total_score3, 0) + COALESCE(ts4.total_score4, 0)) DESC) AS z_rank\n"
						+ "FROM total_score1 ts1\n"
						+ "JOIN total_score2 ts2 ON ts1.ZONE_CODE = ts2.ZONE_CODE AND ts1.COMM_NAME = ts2.COMM_NAME\n"
						+ "JOIN total_score3 ts3 ON ts1.ZONE_CODE = ts3.ZONE_CODE AND ts1.COMM_NAME = ts3.COMM_NAME\n"
						+ "JOIN total_score4 ts4 ON ts1.ZONE_CODE = ts4.ZONE_CODE AND ts1.COMM_NAME = ts4.COMM_NAME;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String ra ="null";
					String gst ="null";
					String absval = "null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				//		'" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "SELECT\n"
						+ "    zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n"
						+ "    (14c.DISPOSAL_OF_ARN_DEEMED_REG / (14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC)) AS score_of_subParameter,\n"
						+ "    'GST1c' AS gst, 'Number of Deemed registrations vis-à-vis total number of applications received for registration in the month' AS ra,\n"
						+ "    CONCAT(14c.DISPOSAL_OF_ARN_DEEMED_REG, '/', (14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC)) AS absolute_value\n"
						+ "FROM mis_gst_commcode cc\n"
						+ "RIGHT JOIN mis_dpm_gst_14a 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
						+ "LEFT JOIN mis_gst_zonecode zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "WHERE MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE = '" + zone_code + "' AND cc.COMM_NAME = '" + come_name + "'\n"
						+ "\n"
						+ "UNION ALL\n"
						+ "\n"
						+ "SELECT\n"
						+ "    zc.ZONE_NAME, cc.ZONE_CODE,cc.COMM_NAME,\n"
						+ "    ((14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC) -\n"
						+ "     (14c.DISPOSAL_OF_ARN_TRANSFERED_CPC + 14c.DISPOSAL_OF_ARN_DEEMED_REG + 14c.DISPOSAL_OF_ARN_WITHIN_7 +\n"
						+ "      14c.DISPOSAL_OF_ARN_PV_APPROVED + 14c.DISPOSAL_OF_ARN_PV_REJECTED)) / (14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC) AS score_of_subParameter,\n"
						+ "    'GST1d' AS gst,\n"
						+ "    '*Percentage of registration applications pending at the end of the month vis-à-vis total applications for registration received. (OB+R-D)/(OB+R)' AS ra,\n"
						+ "    CONCAT(((14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC) -\n"
						+ "     (14c.DISPOSAL_OF_ARN_TRANSFERED_CPC + 14c.DISPOSAL_OF_ARN_DEEMED_REG + 14c.DISPOSAL_OF_ARN_WITHIN_7 +\n"
						+ "      14c.DISPOSAL_OF_ARN_PV_APPROVED + 14c.DISPOSAL_OF_ARN_PV_REJECTED)), '/', (14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC)) AS absolute_value\n"
						+ "FROM mis_gst_commcode cc\n"
						+ "RIGHT JOIN mis_dpm_gst_14a 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
						+ "LEFT JOIN mis_gst_zonecode zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "WHERE\n"
						+ "    MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE = '" + zone_code + "' AND cc.COMM_NAME = '" + come_name + "'\n"
						+ "\n"
						+ "UNION ALL\n"
						+ "\n"
						+ "SELECT\n"
						+ "    zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n"
						+ "    ((14c.SUO_MOTO_OPENING_BALANCE + 14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION - 14c.SUO_MOTO_GSTIN_CANCELLED ) +\n"
						+ "        ( 14c.CANCELLATION_OPENING_BALANCE +14c.CANCELLATION_NO_APPLICATION_RECEIVED - 14c.CANCELLATION_GSTIN_CANCELLED)\n"
						+ "    ) / (14c.SUO_MOTO_OPENING_BALANCE + 14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION + 14c.CANCELLATION_OPENING_BALANCE + 14c.CANCELLATION_NO_APPLICATION_RECEIVED) AS score_of_subParameter,\n"
						+ "    'GST1e' AS gst,'*Percentage of registrations pending for cancellation at the end of the month vis-à-vis total applications initiated for cancellation (OB+R-D)/(OB+R)' AS ra,\n"
						+ "    CONCAT(((14c.SUO_MOTO_OPENING_BALANCE + 14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION - 14c.SUO_MOTO_GSTIN_CANCELLED) +\n"
						+ "            ( 14c.CANCELLATION_OPENING_BALANCE +14c.CANCELLATION_NO_APPLICATION_RECEIVED - 14c.CANCELLATION_GSTIN_CANCELLED)),'/',\n"
						+ "        (14c.SUO_MOTO_OPENING_BALANCE + 14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION + 14c.CANCELLATION_OPENING_BALANCE + 14c.CANCELLATION_NO_APPLICATION_RECEIVED)\n"
						+ "    ) AS absolute_value\n"
						+ "FROM mis_gst_commcode AS cc\n"
						+ "RIGHT JOIN mis_dpm_gst_15a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
						+ "LEFT JOIN  mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "WHERE MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE = '" + zone_code + "' AND cc.COMM_NAME = '" + come_name + "'\n"
						+ "\n"
						+ "UNION ALL\n"
						+ "\n"
						+ "SELECT\n"
						+ "    zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n"
						+ "    (\n"
						+ "        15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED - 15a.REVOCATION_GSTIN_REVOKED - 15a.REVOCATION_APPLICATION_REJECTED\n"
						+ "    ) / (15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED) AS score_of_subParameter,\n"
						+ "    'GST1f' AS gst,'*Percentage of registration pending for revocation at the end of the month vis-à-vis total applications received for revocation of cancellation (OB+R-D)/(OB+R)' AS ra,\n"
						+ "    CONCAT((15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED - 15a.REVOCATION_GSTIN_REVOKED - 15a.REVOCATION_APPLICATION_REJECTED),'/',\n"
						+ "        (15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED)) AS absolute_value\n"
						+ "FROM mis_gst_commcode AS cc\n"
						+ "RIGHT JOIN mis_dpm_gst_15a AS 15a ON cc.COMM_CODE = 15a.COMM_CODE\n"
						+ "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "WHERE 15a.MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE = '" + zone_code + "' AND cc.COMM_NAME = '" + come_name + "';\n"
						+ "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra =rsGst14aa.getString("ra");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
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

				String query_assessment = "WITH score_calculation AS (\n"
						+ "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, SUM(14c.GSTR_3BM_F) AS col3,\n"
						+ "        (SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / NULLIF(SUM(14c.GSTR_3BM_F), 0)) * 100 AS total_score\n"
						+ "    FROM mis_gst_commcode AS cc \n"
						+ "    RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
						+ "    WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY zc.ZONE_NAME,cc.ZONE_CODE\n"
						+ ")\n"
						+ "SELECT \n"
						+ "    DENSE_RANK() OVER (ORDER BY total_score ASC) AS z_rank,\n"
						+ "    ZONE_NAME, ZONE_CODE, col21, col3,total_score\n"
						+ "FROM score_calculation\n"
						+ "ORDER BY total_score ASC;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					int col21=rsGst14aa.getInt("col21");
					int col3=rsGst14aa.getInt("col3");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer insentavization = 0;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					//Zonal_rank = rsGst14aa.getInt("z_rank");
					double  total = rsGst14aa.getDouble("total_score");
					String commName = "ALL";
					String gst = "ALL";
					String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

					// Object total = ((double) col21*100 / col3);
					//String ra ="null";
					String ra= RelevantAspect.Gst2_RA;
					String formattedTotal = String.format("%.2f", total);
					double total_score = Double.parseDouble(formattedTotal);
					Integer way_to_grade =score.marks2(total_score);
					double sub_parameter_weighted_average = way_to_grade * 0.5;
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
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
						"    SELECT ROW_NUMBER() OVER (ORDER BY total_score) AS z_rank, ZONE_NAME, ZONE_CODE, COMM_NAME, col21, col3, total_score FROM score_calculation\n" +
						")\n" +
						"SELECT * FROM ranked_scores\n" +
						"WHERE COMM_NAME IN ( SELECT DISTINCT COMM_NAME FROM ranked_scores WHERE ZONE_CODE = '" + zone_code + "'\n" +
						")ORDER BY z_rank;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score") * 100;
					int col21=rsGst14aa.getInt("col21");
					int col3=rsGst14aa.getInt("col3");

					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer insentavization = 0;
					Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst = "null";
					//String absval = "null";
					String absval = String.valueOf(col21)+"/"+String.valueOf(col3);
					//String ra ="null";
					String ra=RelevantAspect.Gst2_RA;
					String commName = rsGst14aa.getString("COMM_NAME");

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					Integer way_to_grade =score.marks2(total_score);
					double sub_parameter_weighted_average = way_to_grade * 0.5;
					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			} else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH score_calculation AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
						"        SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, SUM(14c.GSTR_3BM_F) AS col3, (SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / SUM(14c.GSTR_3BM_F)) AS score_of_subParameter\n" +
						"    FROM mis_gst_commcode AS cc RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" + month_date + "' and  cc.ZONE_CODE = '" + zone_code + "'\n" +
						"    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
						")\n" +
						"SELECT \n" +
						"    ROW_NUMBER() OVER (ORDER BY score_of_subParameter DESC) AS z_rank, ZONE_NAME, ZONE_CODE, col21, col3, score_of_subParameter, 'GST2' AS gst, '*Percentage of returns which were due but not filed vis-à-vis total returns due ' as ra,\n" +
						"    CONCAT(CAST(col21 AS CHAR), '/', CAST(col3 AS CHAR)) AS absolute_value\n" +
						"FROM score_calculation ORDER BY score_of_subParameter ;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer insentavization = 0;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst = rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter") * 100;
					String ra =rsGst14aa.getString("ra");
					//String ra=RelevantAspect.Gst2_RA;
					Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					Integer way_to_grade =score.marks2(total_score);
					double sub_parameter_weighted_average = way_to_grade * 0.5;
					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
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
						"SELECT ROW_NUMBER() OVER (ORDER BY total_score) AS z_rank,\n" +
						"    ZONE_NAME, ZONE_CODE, COMM_NAME, col21, col3,total_score\n" +
						"FROM score_calculation\n" +
						"ORDER BY total_score;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer insentavization = 0;
					int col21=rsGst14aa.getInt("col21");
					int col3=rsGst14aa.getInt("col3");
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score") * 100;
					Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst = "null";
					String absval = String.valueOf(col21)+"/"+String.valueOf(col3);
					//String absval = "null";
					//String ra ="null";
					String ra=RelevantAspect.Gst2_RA;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					Integer way_to_grade =score.marks2(total_score);
					double sub_parameter_weighted_average = way_to_grade * 0.5;
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			} else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH score_calculation AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
						"        (14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, (14c.GSTR_3BM_F) AS col3, ((14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / (14c.GSTR_3BM_F)) AS score_of_subParameter,\n" +
						"        'GST2' AS gst, CONCAT((14c.GSTR_3BM_F - 14c.GSTR_3BM_D), '/', 14c.GSTR_3BM_F) AS absolute_value, 'Percentage of returns which were due but not filed vis-à-vis total returns due ' as ra\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE = '" + zone_code + "' AND cc.COMM_NAME = '" + come_name + "'\n" +
						")\n" +
						"SELECT ROW_NUMBER() OVER (ORDER BY score_of_subParameter DESC) AS z_rank,\n" +
						"    ZONE_NAME, ZONE_CODE, COMM_NAME, col21, col3,score_of_subParameter,gst,absolute_value,ra\n" +
						"FROM score_calculation ORDER BY score_of_subParameter;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);


				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer insentavization = 0;
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("score_of_subParameter") * 100;
					Zonal_rank = null;
					String gst = rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra =rsGst14aa.getString("ra");
					//String ra=RelevantAspect.Gst2_RA;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					Integer way_to_grade =score.marks2(total_score);
					double sub_parameter_weighted_average = way_to_grade * 0.5;
					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
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

		// Sort by sub_parameter_weighted_average in descending order
		allGstaList = allGstaList.stream()
				.sorted(Comparator.comparing(TotalScore::getSub_parameter_weighted_average).reversed())
				.collect(Collectors.toList());

		// Set Zonal_rank based on the sorted sub_parameter_weighted_average
		int currentRank = 1;
		double previousValue = Double.NaN;
		for (int i = 0; i < allGstaList.size(); i++) {
			TotalScore currentScore = allGstaList.get(i);
			if (Double.compare(currentScore.getSub_parameter_weighted_average(), previousValue) != 0) {
				currentRank = i + 1; // Update rank when the value changes
			}
			currentScore.setZonal_rank(currentRank);
			previousValue = currentScore.getSub_parameter_weighted_average();
		}

		return allGstaList;
	}


	@ResponseBody
	@RequestMapping(value = "/scrutiny/assessment") //3  
	//  http://localhost:8080/cbicApi/cbic/t_score/scrutiny/assessment?month_date=2024-04-01&type=parameter							// for scrutiny/assessment button
	//  http://localhost:8080/cbicApi/cbic/t_score/scrutiny/assessment?month_date=2024-04-01&type=zone&zone_code=59 				// for all button
	//  http://localhost:8080/cbicApi/cbic/t_score/scrutiny/assessment?month_date=2024-04-01&type=commissary&zone_code=59			// for show button, zone wise
	//  http://localhost:8080/cbicApi/cbic/t_score/scrutiny/assessment?month_date=2024-04-01&type=all_commissary					// for all commissary
	//  http://localhost:8080/cbicApi/cbic/t_score/scrutiny/assessment?month_date=2024-04-01&type=come_name&zone_code=64&come_name=Rajkot			// for only commissary wise, show button
	public Object scrutinyAssessment(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		//double median =0.00;

		try {

			if (type.equalsIgnoreCase("parameter")) { // scrutiny/assessment all zone name 1
//              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = new CGSTParameterWiseQuery().QueryForScrutinyAssessmentZoneWise(month_date);

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

					// Logic to adjust insentavization3a and insentavization3b based on the median and numerator values
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
					double sub_parameter_weighted_average = 0.00;
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";
					String ra = "SCRUTINY & ASSESSMENT";

					totalScore = new TotalScore(zoneName, commName, zone_code, total_weighted_average, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
					allGstaList.add(totalScore);



					System.out.println(zoneName);
					System.out.println("total3a: " + total3a);
					System.out.println("numerator_3a : " + numerator_3a);
					System.out.println("median3a : " + median3a);
					System.out.println("way_to_grade3a: " + way_to_grade3a);
					System.out.println("insentavization3a : " + insentavization3a);
					System.out.println("sub_parameter_weighted_average3a : " + sub_parameter_weighted_average3a);

					System.out.println("total3b: " + total3b);
					System.out.println("numerator_3b : " + numerator_3b);
					System.out.println("median3b : " + median3b);
					System.out.println("way_to_grade3b: " + way_to_grade3b);
					System.out.println("insentavization3b : " + insentavization3b);
					System.out.println("sub_parameter_weighted_average3b : " + sub_parameter_weighted_average3b);
					System.out.println("total_score : " + total_weighted_average);
					System.out.println("********************************************************************************************");
				}

			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				// my sql query
				String query_assessment = new CGSTParameterWiseQuery().QueryForScrutinyAssessmentParticularZoneWise(month_date,zone_code);
				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next() ) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					//Integer way_to_grade = 0;
					//Integer insentavization = 0;
					//double sub_parameter_weighted_average = 0.00;
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					double tScore_3a = rsGst14aa.getDouble("total_score");
					double tScore_3b = rsGst14aa.getDouble("total_score");
					Double median_3a = rsGst14aa.getDouble("median_numerator_3a");
					Double numerator_3a = rsGst14aa.getDouble("numerator_3a");
					Double median_3b = rsGst14aa.getDouble("median_numerator_3b");
					Double numerator_3b = rsGst14aa.getDouble("numerator_3b");
					String gst ="null";
					String absval = "null";
					String ra ="SCRUTINY & ASSESSMENT";

					int way_to_grade3a = score.marks3a(tScore_3a);
					int way_to_grade3b = score.marks3b(tScore_3b);

					int insentavization3a = way_to_grade3a;
					int insentavization3b = way_to_grade3b;

					// Logic to adjust insentavization3a and insentavization3b based on the median and numerator values
					if (numerator_3a > median_3a && way_to_grade3a < 10) {
						insentavization3a += 1;
					}
					if (numerator_3b > median_3b && way_to_grade3b < 10) {
						insentavization3b += 1;
					}

					Integer way_to_grade = way_to_grade3a + way_to_grade3b;
					Integer insentavization = insentavization3a + insentavization3b;

					double sub_parameter_weighted_average3a = insentavization3a * 0.5;
					double sub_parameter_weighted_average3b = insentavization3b * 0.5;

					double total_weighted_average = sub_parameter_weighted_average3a + sub_parameter_weighted_average3b;
					double sub_parameter_weighted_average = 0.00;

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,total_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				// my sql query
				String query_assessment = new CGSTParameterWiseQuery().QueryForScrutinyAssessment_3_ParticularSubparameterWise(month_date,zone_code);
				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String gst = rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absval");
					Double t_score = rsGst14aa.getDouble("score_of_parameter");
					Double median = rsGst14aa.getDouble("median_numerator");
					Double numerator = rsGst14aa.getDouble("numerator");

					String formattedTotal = String.format("%.2f", t_score);
					double total_score = Double.parseDouble(formattedTotal);

					int way_to_grade;
					int insentavization;

					// Logic based on parameter type
					if ("GST3A".equalsIgnoreCase(gst)) {
						way_to_grade = score.marks3a(total_score);
						insentavization = score.marks3a(total_score);

						if (numerator > median && way_to_grade < 10) {
							insentavization += 1;
						}
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
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				// my sql query
				String query_assessment = new CGSTParameterWiseQuery().QueryForScrutinyAssessment_3_AllCommissary(month_date);
				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					//Integer way_to_grade = 0;
					//Integer insentavization = 0;
					//double sub_parameter_weighted_average = 0.00;
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					double tScore_3a = rsGst14aa.getDouble("total_score");
					double tScore_3b = rsGst14aa.getDouble("total_score");
					Double median_3a = rsGst14aa.getDouble("median_numerator_3a");
					Double numerator_3a = rsGst14aa.getDouble("numerator_3a");
					Double median_3b = rsGst14aa.getDouble("median_numerator_3b");
					Double numerator_3b = rsGst14aa.getDouble("numerator_3b");
					String gst ="null";
					String absval = "null";
					String ra ="SCRUTINY & ASSESSMENT";

					int way_to_grade3a = score.marks3a(tScore_3a);
					int way_to_grade3b = score.marks3b(tScore_3b);

					int insentavization3a = way_to_grade3a;
					int insentavization3b = way_to_grade3b;

					// Logic to adjust insentavization3a and insentavization3b based on the median and numerator values
					if (numerator_3a > median_3a && way_to_grade3a < 10) {
						insentavization3a += 1;
					}
					if (numerator_3b > median_3b && way_to_grade3b < 10) {
						insentavization3b += 1;
					}

					Integer way_to_grade = way_to_grade3a + way_to_grade3b;
					Integer insentavization = insentavization3a + insentavization3b;

					double sub_parameter_weighted_average3a = insentavization3a * 0.5;
					double sub_parameter_weighted_average3b = insentavization3b * 0.5;

					double total_weighted_average = sub_parameter_weighted_average3a + sub_parameter_weighted_average3b;
					double sub_parameter_weighted_average = 0.00;

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,total_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				// my sql query
				String query_assessment = new CGSTParameterWiseQuery().QueryForScrutinyAssessment_3_ParticularCommissonaryInSubparameter(month_date,zone_code,come_name);
				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String commName = rsGst14aa.getString("COMM_NAME");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					//double sub_parameter_weighted_average = 0.00;
					double tScore = rsGst14aa.getDouble("score_of_parameter");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absvl");
					Double median = rsGst14aa.getDouble("median");
					Double numerator_3a = rsGst14aa.getDouble("numerator_3a");
					Double numerator_3b = rsGst14aa.getDouble("numerator_3b");
					String ra ="SCRUTINY & ASSESSMENT";
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);

					// Logic based on parameter type
					if ("GST3A".equalsIgnoreCase(gst)) {
						way_to_grade = score.marks3a(total_score);
						insentavization = score.marks3a(total_score);

						if (numerator_3a > median && way_to_grade < 10) {
							insentavization += 1;
						}
					} else if ("GST3B".equalsIgnoreCase(gst)) {
						way_to_grade = score.marks3b(total_score);
						insentavization = score.marks3b(total_score);

						if (numerator_3b > median && way_to_grade < 10) {
							insentavization += 1;
						}
					} else {
						// Default handling if parameter type is neither 3a nor 3b
						way_to_grade = 0;
						insentavization = 0;
					}
					Double sub_parameter_weighted_average = insentavization * 0.5;
					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
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
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH \n" +
						"    FirstCTE AS (\n" +
						"        SELECT \n" +
						"            zc.ZONE_NAME, cc.ZONE_CODE,\n" +
						"            SUM(14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO) / SUM(14c.OPENING_BALANCE_NO) as score_of_parameter1\n" +
						"        FROM mis_gst_commcode AS cc\n" +
						"        RIGHT JOIN mis_gi_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"        WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
						"        GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						"    ),\n" +
						"    SecondCTE AS (\n" +
						"        SELECT \n" +
						"            zc.ZONE_NAME, cc.ZONE_CODE,\n" +
						"            SUM(14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col1_6\n" +
						"        FROM mis_gst_commcode AS cc\n" +
						"        RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"        WHERE 14c.MM_YYYY <= '" + month_date + "'\n" +
						"        GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						"    ),\n" +
						"    ThirdCTE AS (\n" +
						"        SELECT \n" +
						"            zc.ZONE_NAME, cc.ZONE_CODE,\n" +
						"            SUM(7c.GROSS_TAX_CGST_FOR_C + 7c.GROSS_TAX_SGST_FOR_C + 7c.GROSS_TAX_IGST_FOR_C + 7c.GROSS_TAX_CESS_FOR_C) AS col1_7\n" +
						"        FROM mis_gst_commcode AS cc\n" +
						"        RIGHT JOIN mis_ddm_gst_1 AS 7c ON cc.COMM_CODE = 7c.COMM_CODE\n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"        WHERE 7c.MM_YYYY <= '" + month_date + "'\n" +
						"        GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						"    ),\n" +
						"    FourthCTE AS (\n" +
						"        SELECT \n" +
						"            COALESCE(fq.ZONE_NAME, sq.ZONE_NAME) AS ZONE_NAME, COALESCE(fq.ZONE_CODE, sq.ZONE_CODE) AS ZONE_CODE, \n" +
						"            fq.col1_6 / sq.col1_7 as score_of_parameter3\n" +
						"        FROM SecondCTE fq\n" +
						"        LEFT JOIN ThirdCTE sq ON fq.ZONE_CODE = sq.ZONE_CODE\n" +
						"        UNION\n" +
						"        SELECT \n" +
						"            COALESCE(fq.ZONE_NAME, sq.ZONE_NAME) AS ZONE_NAME, COALESCE(fq.ZONE_CODE, sq.ZONE_CODE) AS ZONE_CODE, \n" +
						"            fq.col1_6 / sq.col1_7 as score_of_parameter3\n" +
						"        FROM ThirdCTE sq\n" +
						"        LEFT JOIN SecondCTE fq ON fq.ZONE_CODE = sq.ZONE_CODE\n" +
						"    ),\n" +
						"    MayData AS (\n" +
						"        SELECT \n" +
						"            cc.ZONE_CODE, zc.ZONE_NAME, \n" +
						"            SUM(14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT) AS col6_1,\n" +
						"            SUM(14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_3 \n" +
						"        FROM mis_gst_commcode AS cc \n" +
						"        RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"        WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
						"        GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						"    ),\n" +
						"    AprilData AS (\n" +
						"        SELECT \n" +
						"            cc.ZONE_CODE, zc.ZONE_NAME, \n" +
						"            SUM(14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT) AS col6_2,\n" +
						"            SUM(14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_4\n" +
						"        FROM mis_gst_commcode AS cc\n" +
						"        RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"        WHERE 14c.MM_YYYY = '" + prev_month_new + "' \n" +
						"        GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						"    ),\n" +
						"    FifthCTE AS (\n" +
						"        SELECT \n" +
						"            COALESCE(may_data.ZONE_CODE, april_data.ZONE_CODE) AS ZONE_CODE,\n" +
						"            COALESCE(may_data.ZONE_NAME, april_data.ZONE_NAME) AS ZONE_NAME,\n" +
						"            ((may_data.col6_1 + april_data.col6_2) / (may_data.col6_3 + april_data.col6_4)) as score_of_parameter4\n" +
						"        FROM MayData may_data\n" +
						"        LEFT JOIN AprilData april_data ON may_data.ZONE_CODE = april_data.ZONE_CODE\n" +
						"        UNION\n" +
						"        SELECT \n" +
						"            COALESCE(may_data.ZONE_CODE, april_data.ZONE_CODE) AS ZONE_CODE, \n" +
						"            COALESCE(may_data.ZONE_NAME, april_data.ZONE_NAME) AS ZONE_NAME,\n" +
						"            ((may_data.col6_1 + april_data.col6_2) / (may_data.col6_3 + april_data.col6_4)) as score_of_parameter4\n" +
						"        FROM MayData may_data\n" +
						"        RIGHT JOIN AprilData april_data ON may_data.ZONE_CODE = april_data.ZONE_CODE\n" +
						"    )\n" +
						"SELECT \n" +
						"    ROW_NUMBER() OVER (ORDER BY (f.score_of_parameter1 + sq.score_of_parameter3 + tq.score_of_parameter4) DESC) AS z_rank,\n" +
						"    f.ZONE_NAME, \n" +
						"    f.ZONE_CODE, \n" +
						"    (f.score_of_parameter1 + sq.score_of_parameter3 + tq.score_of_parameter4) as total_score\n" +
						"FROM FirstCTE f\n" +
						"LEFT JOIN FourthCTE sq ON f.ZONE_CODE = sq.ZONE_CODE\n" +
						"LEFT JOIN FifthCTE tq ON f.ZONE_CODE = tq.ZONE_CODE\n" +
						"ORDER BY total_score DESC;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double total_score = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";
					String ra ="null";

					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH score_of_parameter1 AS (\n"
						+ "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, (14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO) / (14c.OPENING_BALANCE_NO) AS score_of_parameter1\n"
						+ "    FROM mis_gst_commcode AS cc\n"
						+ "    RIGHT JOIN mis_gi_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
						+ "    WHERE 14c.MM_YYYY = '" + month_date + "' and cc.ZONE_CODE = '" + zone_code + "'\n"
						+ "),\n"
						+ "detection_sum AS (\n"
						+ "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, SUM(14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col1_6 \n"
						+ "    FROM mis_gst_commcode AS cc\n"
						+ "    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE 14c.MM_YYYY <= '" + month_date + "' and cc.ZONE_CODE = '" + zone_code + "' GROUP BY cc.COMM_NAME, cc.ZONE_CODE, zc.ZONE_NAME\n"
						+ "),\n"
						+ "gross_tax_sum AS (\n"
						+ "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, SUM(7c.GROSS_TAX_CGST_FOR_C + 7c.GROSS_TAX_SGST_FOR_C + 7c.GROSS_TAX_IGST_FOR_C + 7c.GROSS_TAX_CESS_FOR_C) AS col1_7 \n"
						+ "    FROM mis_gst_commcode AS cc \n"
						+ "    RIGHT JOIN mis_ddm_gst_1 AS 7c ON cc.COMM_CODE = 7c.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
						+ "    WHERE 7c.MM_YYYY <= '" + month_date + "' and cc.ZONE_CODE = '" + zone_code + "' GROUP BY cc.COMM_NAME, cc.ZONE_CODE, zc.ZONE_NAME\n"
						+ "),\n"
						+ "score_of_parameter3 AS (\n"
						+ "    SELECT d.ZONE_NAME, d.ZONE_CODE, d.COMM_NAME, (d.col1_6 / g.col1_7) AS score_of_parameter3\n"
						+ "    FROM detection_sum d\n"
						+ "    LEFT JOIN gross_tax_sum g ON d.ZONE_NAME = g.ZONE_NAME AND d.ZONE_CODE = g.ZONE_CODE AND d.COMM_NAME = g.COMM_NAME\n"
						+ "),\n"
						+ "MayData AS (\n"
						+ "    SELECT cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME, (14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT) AS col6_1,\n"
						+ "        (14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_3\n"
						+ "    FROM mis_gst_commcode AS cc \n"
						+ "    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE 14c.MM_YYYY = '" + month_date + "' and cc.ZONE_CODE = '" + zone_code + "'\n"
						+ "),\n"
						+ "AprilData AS (\n"
						+ "    SELECT cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME,\n"
						+ "        (14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT) AS col6_2,\n"
						+ "        (14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_4\n"
						+ "    FROM mis_gst_commcode AS cc\n"
						+ "    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' and cc.ZONE_CODE = '" + zone_code + "'\n"
						+ "),\n"
						+ "score_of_parameter4 AS (\n"
						+ "    SELECT MayData.ZONE_CODE, MayData.ZONE_NAME, MayData.COMM_NAME, ((MayData.col6_1 + AprilData.col6_2) / (MayData.col6_3 + AprilData.col6_4)) AS score_of_parameter4\n"
						+ "    FROM MayData\n"
						+ "    JOIN AprilData ON MayData.ZONE_CODE = AprilData.ZONE_CODE AND MayData.COMM_NAME = AprilData.COMM_NAME\n"
						+ "),\n"
						+ "first_query_ranks AS (\n"
						+ "    SELECT\n"
						+ "        sp1.ZONE_NAME, sp1.ZONE_CODE, sp1.COMM_NAME,\n"
						+ "        COALESCE(sp1.score_of_parameter1, 0) AS score_of_parameter1, COALESCE(sp3.score_of_parameter3, 0) AS score_of_parameter3, COALESCE(sp4.score_of_parameter4, 0) AS score_of_parameter4,\n"
						+ "        (COALESCE(sp1.score_of_parameter1, 0) + COALESCE(sp3.score_of_parameter3, 0) + COALESCE(sp4.score_of_parameter4, 0)) AS total_score,\n"
						+ "        ROW_NUMBER() OVER (ORDER BY (COALESCE(sp1.score_of_parameter1, 0) + COALESCE(sp3.score_of_parameter3, 0) + COALESCE(sp4.score_of_parameter4, 0)) DESC) AS z_rank\n"
						+ "    FROM score_of_parameter1 sp1\n"
						+ "    LEFT JOIN score_of_parameter3 sp3 ON sp1.ZONE_NAME = sp3.ZONE_NAME AND sp1.ZONE_CODE = sp3.ZONE_CODE AND sp1.COMM_NAME = sp3.COMM_NAME\n"
						+ "    LEFT JOIN score_of_parameter4 sp4 ON sp1.ZONE_NAME = sp4.ZONE_NAME AND sp1.ZONE_CODE = sp4.ZONE_CODE AND sp1.COMM_NAME = sp4.COMM_NAME\n"
						+ "),\n"
						+ "score_of_parameter1_no_zone_code_condition AS (\n"
						+ "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, (14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO) / (14c.OPENING_BALANCE_NO) AS score_of_parameter1\n"
						+ "    FROM mis_gst_commcode AS cc\n"
						+ "    RIGHT JOIN mis_gi_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
						+ "    WHERE 14c.MM_YYYY = '" + month_date + "'\n"
						+ "),\n"
						+ "detection_sum_no_zone_code_condition AS (\n"
						+ "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, SUM(14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col1_6 \n"
						+ "    FROM mis_gst_commcode AS cc\n"
						+ "    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE 14c.MM_YYYY <= '" + month_date + "' GROUP BY cc.COMM_NAME, cc.ZONE_CODE, zc.ZONE_NAME\n"
						+ "),\n"
						+ "gross_tax_sum_no_zone_code_condition AS (\n"
						+ "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, SUM(7c.GROSS_TAX_CGST_FOR_C + 7c.GROSS_TAX_SGST_FOR_C + 7c.GROSS_TAX_IGST_FOR_C + 7c.GROSS_TAX_CESS_FOR_C) AS col1_7 \n"
						+ "    FROM mis_gst_commcode AS cc \n"
						+ "    RIGHT JOIN mis_ddm_gst_1 AS 7c ON cc.COMM_CODE = 7c.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
						+ "    WHERE 7c.MM_YYYY <= '" + month_date + "' GROUP BY cc.COMM_NAME, cc.ZONE_CODE, zc.ZONE_NAME\n"
						+ "),\n"
						+ "score_of_parameter3_no_zone_code_condition AS (\n"
						+ "    SELECT d.ZONE_NAME, d.ZONE_CODE, d.COMM_NAME, (d.col1_6 / g.col1_7) AS score_of_parameter3\n"
						+ "    FROM detection_sum_no_zone_code_condition d\n"
						+ "    LEFT JOIN gross_tax_sum_no_zone_code_condition g ON d.ZONE_NAME = g.ZONE_NAME AND d.ZONE_CODE = g.ZONE_CODE AND d.COMM_NAME = g.COMM_NAME\n"
						+ "),\n"
						+ "MayData_no_zone_code_condition AS (\n"
						+ "    SELECT cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME,\n"
						+ "        (14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT) AS col6_1,\n"
						+ "        (14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_3\n"
						+ "    FROM mis_gst_commcode AS cc \n"
						+ "    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE 14c.MM_YYYY = '" + month_date + "'\n"
						+ "),\n"
						+ "AprilData_no_zone_code_condition AS (\n"
						+ "    SELECT cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME,\n"
						+ "        (14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT) AS col6_2,\n"
						+ "        (14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_4\n"
						+ "    FROM mis_gst_commcode AS cc\n"
						+ "    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n"
						+ "),\n"
						+ "score_of_parameter4_no_zone_code_condition AS (\n"
						+ "    SELECT MayData_no_zone_code_condition.ZONE_CODE, MayData_no_zone_code_condition.ZONE_NAME, MayData_no_zone_code_condition.COMM_NAME,\n"
						+ "        ((MayData_no_zone_code_condition.col6_1 + AprilData_no_zone_code_condition.col6_2) / (MayData_no_zone_code_condition.col6_3 + AprilData_no_zone_code_condition.col6_4)) AS score_of_parameter4\n"
						+ "    FROM MayData_no_zone_code_condition\n"
						+ "    JOIN AprilData_no_zone_code_condition ON MayData_no_zone_code_condition.ZONE_CODE = AprilData_no_zone_code_condition.ZONE_CODE AND MayData_no_zone_code_condition.COMM_NAME = AprilData_no_zone_code_condition.COMM_NAME\n"
						+ "),\n"
						+ "second_query_ranks AS (\n"
						+ "    SELECT sp1.ZONE_NAME, sp1.ZONE_CODE, sp1.COMM_NAME,\n"
						+ "        COALESCE(sp1.score_of_parameter1, 0) AS score_of_parameter1, COALESCE(sp3.score_of_parameter3, 0) AS score_of_parameter3,\n"
						+ "        COALESCE(sp4.score_of_parameter4, 0) AS score_of_parameter4, (COALESCE(sp1.score_of_parameter1, 0) + COALESCE(sp3.score_of_parameter3, 0) + COALESCE(sp4.score_of_parameter4, 0)) AS total_score,\n"
						+ "        ROW_NUMBER() OVER (ORDER BY (COALESCE(sp1.score_of_parameter1, 0) + COALESCE(sp3.score_of_parameter3, 0) + COALESCE(sp4.score_of_parameter4, 0)) DESC) AS z_rank\n"
						+ "    FROM score_of_parameter1_no_zone_code_condition sp1\n"
						+ "    LEFT JOIN score_of_parameter3_no_zone_code_condition sp3 ON sp1.ZONE_NAME = sp3.ZONE_NAME AND sp1.ZONE_CODE = sp3.ZONE_CODE AND sp1.COMM_NAME = sp3.COMM_NAME\n"
						+ "    LEFT JOIN score_of_parameter4_no_zone_code_condition sp4 ON sp1.ZONE_NAME = sp4.ZONE_NAME AND sp1.ZONE_CODE = sp4.ZONE_CODE AND sp1.COMM_NAME = sp4.COMM_NAME\n"
						+ ")\n"
						+ "SELECT fqr.ZONE_NAME, fqr.ZONE_CODE, fqr.COMM_NAME, fqr.score_of_parameter1, fqr.score_of_parameter3, fqr.score_of_parameter4, fqr.total_score, COALESCE(sqr.z_rank, fqr.z_rank) AS z_rank\n"
						+ "FROM first_query_ranks fqr\n"
						+ "LEFT JOIN second_query_ranks sqr\n"
						+ "ON fqr.ZONE_NAME = sqr.ZONE_NAME AND fqr.ZONE_CODE = sqr.ZONE_CODE AND fqr.COMM_NAME = sqr.COMM_NAME\n"
						+ "ORDER BY fqr.total_score DESC;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";
					String ra ="null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "SELECT\n"
						+ "    zc.ZONE_NAME,cc.ZONE_CODE, SUM(gi.SCN_NO + gi.VOLUNTARY_NO + gi.MERIT_NO + gi.TRANSFER_NO) / NULLIF(SUM(gi.OPENING_BALANCE_NO), 0) AS score_of_subParameter,\n"
						+ "    CONCAT(SUM(gi.SCN_NO + gi.VOLUNTARY_NO + gi.MERIT_NO + gi.TRANSFER_NO),'/', SUM(gi.OPENING_BALANCE_NO)\n"
						+ "    ) AS absolute_value,\n"
						+ "    'GST4A' as gst, 'No. of cases disposed (investigation completed)during the month vis a vis total investigations pending at beginning of the month' as ra\n"
						+ "FROM mis_gst_commcode AS cc\n"
						+ "RIGHT JOIN mis_gi_gst_2 AS gi ON cc.COMM_CODE = gi.COMM_CODE\n"
						+ "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "WHERE gi.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "'\n"
						+ "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n"
						+ "\n"
						+ "UNION ALL\n"
						+ "\n"
						+ "SELECT \n"
						+ "    COALESCE(fq.ZONE_NAME, sq.ZONE_NAME) AS ZONE_NAME, COALESCE(fq.ZONE_CODE, sq.ZONE_CODE) AS ZONE_CODE, (fq.col1_6 / sq.col1_7) AS score_of_subParameter,\n"
						+ "    CONCAT(fq.col1_6, '/', sq.col1_7) AS absolute_value,\n"
						+ "    'GST4C' AS gst, 'Detections made upto the month vis-à-vis total revenue collected upto the month for the formation (Zone/Commissionerate)' AS ra\n"
						+ "FROM (\n"
						+ "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col1_6 \n"
						+ "    FROM mis_gst_commcode AS cc\n"
						+ "    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE 14c.MM_YYYY <= '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n"
						+ ") AS fq\n"
						+ "LEFT JOIN (\n"
						+ "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(7c.GROSS_TAX_CGST_FOR_C + 7c.GROSS_TAX_SGST_FOR_C + 7c.GROSS_TAX_IGST_FOR_C + 7c.GROSS_TAX_CESS_FOR_C) AS col1_7 \n"
						+ "    FROM mis_gst_commcode AS cc\n"
						+ "    RIGHT JOIN mis_ddm_gst_1 AS 7c ON cc.COMM_CODE = 7c.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE 7c.MM_YYYY <= '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n"
						+ ") AS sq ON fq.ZONE_CODE = sq.ZONE_CODE\n"
						+ "\n"
						+ "UNION ALL\n"
						+ "\n"
						+ "SELECT \n"
						+ "    COALESCE(may_data.ZONE_NAME, april_data.ZONE_NAME) AS ZONE_NAME,\n"
						+ "    COALESCE(may_data.ZONE_CODE, april_data.ZONE_CODE) AS ZONE_CODE,\n"
						+ "    CASE \n"
						+ "        WHEN (COALESCE(may_data.col6_3, 0) + COALESCE(april_data.col6_4, 0)) = 0 THEN NULL \n"
						+ "        ELSE (COALESCE(may_data.col6_1, 0) + COALESCE(april_data.col6_2, 0)) / (COALESCE(may_data.col6_3, 0) + COALESCE(april_data.col6_4, 0)) \n"
						+ "    END AS score_of_subParameter,\n"
						+ "    CONCAT(COALESCE(may_data.col6_1, 0) + COALESCE(april_data.col6_2, 0), '/', COALESCE(may_data.col6_3, 0) + COALESCE(april_data.col6_4, 0)\n"
						+ "    ) AS absolute_value,\n"
						+ "    'GST4D' AS gst, 'Recoveries made upto the month vis-a-vis detections upto the month' AS ra\n"
						+ "FROM (\n"
						+ "    SELECT cc.ZONE_CODE, zc.ZONE_NAME, \n"
						+ "        SUM(14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT) AS col6_1,\n"
						+ "        SUM(14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_3 \n"
						+ "    FROM mis_gst_commcode AS cc \n"
						+ "    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
						+ "    WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n"
						+ ") AS may_data\n"
						+ "LEFT JOIN (\n"
						+ "    SELECT cc.ZONE_CODE, zc.ZONE_NAME, \n"
						+ "        SUM(14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT) AS col6_2,\n"
						+ "        SUM(14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_4\n"
						+ "    FROM mis_gst_commcode AS cc\n"
						+ "    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
						+ "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND cc.ZONE_CODE = '" + zone_code + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n"
						+ ") AS april_data ON may_data.ZONE_CODE = april_data.ZONE_CODE;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH score_of_parameter1 AS (\n" +
						"    SELECT\n" +
						"        zc.ZONE_NAME,cc.ZONE_CODE,cc.COMM_NAME,\n" +
						"        (14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO) / (14c.OPENING_BALANCE_NO) AS score_of_parameter1\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"    RIGHT JOIN mis_gi_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
						"),\n" +
						"detection_sum AS (\n" +
						"    SELECT \n" +
						"        zc.ZONE_NAME,cc.ZONE_CODE,cc.COMM_NAME,\n" +
						"        SUM(14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col1_6 \n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"    WHERE 14c.MM_YYYY <= '" + month_date + "' GROUP BY cc.COMM_NAME, cc.ZONE_CODE, zc.ZONE_NAME\n" +
						"),\n" +
						"gross_tax_sum AS (\n" +
						"    SELECT \n" +
						"        zc.ZONE_NAME,cc.ZONE_CODE,cc.COMM_NAME,\n" +
						"        SUM(7c.GROSS_TAX_CGST_FOR_C + 7c.GROSS_TAX_SGST_FOR_C + 7c.GROSS_TAX_IGST_FOR_C + 7c.GROSS_TAX_CESS_FOR_C) AS col1_7 \n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_ddm_gst_1 AS 7c ON cc.COMM_CODE = 7c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 7c.MM_YYYY <= '" + month_date + "'\n" +
						"    GROUP BY cc.COMM_NAME, cc.ZONE_CODE, zc.ZONE_NAME\n" +
						"),\n" +
						"score_of_parameter3 AS (\n" +
						"    SELECT\n" +
						"        d.ZONE_NAME,d.ZONE_CODE,d.COMM_NAME,\n" +
						"        (d.col1_6 / g.col1_7) AS score_of_parameter3\n" +
						"    FROM detection_sum d\n" +
						"    LEFT JOIN gross_tax_sum g ON d.ZONE_NAME = g.ZONE_NAME AND d.ZONE_CODE = g.ZONE_CODE AND d.COMM_NAME = g.COMM_NAME\n" +
						"),\n" +
						"MayData AS (\n" +
						"    SELECT \n" +
						"        cc.ZONE_CODE,zc.ZONE_NAME,cc.COMM_NAME,\n" +
						"        (14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT) AS col6_1,\n" +
						"        (14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_3\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
						"),\n" +
						"AprilData AS (\n" +
						"    SELECT \n" +
						"        cc.ZONE_CODE,\n" +
						"        zc.ZONE_NAME,\n" +
						"        cc.COMM_NAME,\n" +
						"        (14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT) AS col6_2,\n" +
						"        (14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_4\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"    WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
						"),\n" +
						"score_of_parameter4 AS (\n" +
						"    SELECT \n" +
						"        MayData.ZONE_CODE,MayData.ZONE_NAME,MayData.COMM_NAME,\n" +
						"        ((MayData.col6_1 + AprilData.col6_2) / (MayData.col6_3 + AprilData.col6_4)) AS score_of_parameter4\n" +
						"    FROM MayData\n" +
						"    JOIN AprilData ON MayData.ZONE_CODE = AprilData.ZONE_CODE AND MayData.COMM_NAME = AprilData.COMM_NAME\n" +
						")\n" +
						"SELECT\n" +
						"    sp1.ZONE_NAME,sp1.ZONE_CODE,sp1.COMM_NAME,\n" +
						"    COALESCE(sp1.score_of_parameter1, 0) AS score_of_parameter1,\n" +
						"    COALESCE(sp3.score_of_parameter3, 0) AS score_of_parameter3,\n" +
						"    COALESCE(sp4.score_of_parameter4, 0) AS score_of_parameter4,\n" +
						"    (COALESCE(sp1.score_of_parameter1, 0) + COALESCE(sp3.score_of_parameter3, 0) + COALESCE(sp4.score_of_parameter4, 0)) AS total_score,\n" +
						"    ROW_NUMBER() OVER (ORDER BY (COALESCE(sp1.score_of_parameter1, 0) + COALESCE(sp3.score_of_parameter3, 0) + COALESCE(sp4.score_of_parameter4, 0)) DESC) AS z_rank\n" +
						"FROM score_of_parameter1 sp1\n" +
						"LEFT JOIN score_of_parameter3 sp3 ON sp1.ZONE_NAME = sp3.ZONE_NAME AND sp1.ZONE_CODE = sp3.ZONE_CODE AND sp1.COMM_NAME = sp3.COMM_NAME\n" +
						"LEFT JOIN score_of_parameter4 sp4 ON sp1.ZONE_NAME = sp4.ZONE_NAME AND sp1.ZONE_CODE = sp4.ZONE_CODE AND sp1.COMM_NAME = sp4.COMM_NAME\n" +
						"ORDER BY total_score DESC;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst ="null";
					String absval = "null";
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "SELECT\n" +
						"    zc.ZONE_NAME,cc.ZONE_CODE,cc.COMM_NAME,\n" +
						"    (14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO) / (14c.OPENING_BALANCE_NO) AS score_of_subParameter,\n" +
						"    CONCAT((14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO), '/', (14c.OPENING_BALANCE_NO)) AS absolute_value,\n" +
						"    'GST4A' AS gst,'No. of cases disposed (investigation completed) during the month vis a vis total investigations pending at beginning of the month' AS ra\n" +
						"FROM mis_gst_commcode AS cc\n" +
						"RIGHT JOIN mis_gi_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.COMM_NAME = '" + come_name + "'\n" +
						"\n" +
						"UNION ALL\n" +
						"\n" +
						"SELECT\n" +
						"    d.ZONE_NAME,d.ZONE_CODE,d.COMM_NAME,(d.col1_6 / g.col1_7) AS score_of_subParameter,\n" +
						"    CONCAT(d.col1_6, '/', g.col1_7) AS absolute_value,\n" +
						"    'GST4c' AS gst,'Detections made upto the month vis-à-vis total revenue collected upto the month for the formation (Zone/Commissionerate)' AS ra\n" +
						"FROM (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, SUM(14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col1_6 \n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"    WHERE 14c.MM_YYYY <= '" + month_date + "' AND cc.COMM_NAME = '" + come_name + "'\n" +
						"    GROUP BY cc.COMM_NAME, cc.ZONE_CODE, zc.ZONE_NAME\n" +
						") d\n" +
						"LEFT JOIN (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, SUM(7c.GROSS_TAX_CGST_FOR_C + 7c.GROSS_TAX_SGST_FOR_C + 7c.GROSS_TAX_IGST_FOR_C + 7c.GROSS_TAX_CESS_FOR_C) AS col1_7 \n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_ddm_gst_1 AS 7c ON cc.COMM_CODE = 7c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 7c.MM_YYYY <= '" + month_date + "'  AND cc.COMM_NAME = '" + come_name + "'\n" +
						"    GROUP BY cc.COMM_NAME, cc.ZONE_CODE, zc.ZONE_NAME\n" +
						") g ON d.ZONE_NAME = g.ZONE_NAME AND d.ZONE_CODE = g.ZONE_CODE AND d.COMM_NAME = g.COMM_NAME\n" +
						"\n" +
						"UNION ALL\n" +
						"\n" +
						"SELECT\n" +
						"    MayData.ZONE_NAME,MayData.ZONE_CODE,MayData.COMM_NAME,\n" +
						"    ((MayData.col6_1 + AprilData.col6_2) / (MayData.col6_3 + AprilData.col6_4)) AS score_of_subParameter,\n" +
						"    CONCAT((MayData.col6_1 + AprilData.col6_2), ' /', (MayData.col6_3 + AprilData.col6_4)) AS absolute_value,\n" +
						"    'GST4D' AS gst,'Recoveries made upto the month vis-a-vis detections upto the month' AS ra\n" +
						"FROM (\n" +
						"    SELECT \n" +
						"        cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME,\n" +
						"        (14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT) AS col6_1,\n" +
						"        (14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_3\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.COMM_NAME = '" + come_name + "'\n" +
						") MayData\n" +
						"JOIN (\n" +
						"    SELECT \n" +
						"        cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME,\n" +
						"        (14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT) AS col6_2,\n" +
						"        (14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_4\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND cc.COMM_NAME = '" + come_name + "'\n" +
						") AprilData ON MayData.ZONE_CODE = AprilData.ZONE_CODE AND MayData.COMM_NAME = AprilData.COMM_NAME;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//logger.error(e.getClass().getName());
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
	 * created: nishant(url no:- 1), RKS (url no:-)
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
				// MSG :- 5a is following disposal and 5b following in pendency, so we convert 5a(disposal to pendency(100-17.87)),  then it is totally following ascending order
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);
				String next_month_new = DateCalculate.getNextMonth(month_date);

				String query_assessment = new CGSTParameterWiseQuery().QueryFor_Adjudication_5_ZoneWise(month_date);
				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					zone_code = rsGst14aa.getString("ZONE_CODE");

					double total5a = rsGst14aa.getDouble("score_of_subparameter5a");
					double total5b = rsGst14aa.getDouble("score_of_subparameter5b");

					double median5a = rsGst14aa.getDouble("median5a");
					//double median5b = rsGst14aa.getDouble("median_numerator_3b");

					Double numerator_5a = rsGst14aa.getDouble("col10");
					//Double numerator_5b = rsGst14aa.getDouble("numerator_3b");

					int way_to_grade5a = score.marks5a(total5a);
					int way_to_grade5b = score.marks5b(total5b);

					int insentavization5a = way_to_grade5a;
					int insentavization5b = way_to_grade5b;

					if (numerator_5a > median5a && way_to_grade5a < 10) {
						insentavization5a += 1;
					}
					Integer way_to_grade = way_to_grade5a + way_to_grade5b;
					Integer insentavization = insentavization5a + insentavization5b;

					double sub_parameter_weighted_average5a = insentavization5a * 0.5;
					double sub_parameter_weighted_average5b = insentavization5b * 0.5;

					double total_weighted_average = sub_parameter_weighted_average5a + sub_parameter_weighted_average5b;


					double sub_parameter_weighted_average = 0.00;
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";
					String ra ="Adjudication";

//					String formattedTotal = String.format("%.2f", tScore);
//					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_weighted_average, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
					allGstaList.add(totalScore);


					System.out.println(zoneName);
					System.out.println("total5a: " + total5a);
					System.out.println("numerator_5a : " + numerator_5a);
					System.out.println("median5a : " + median5a);
					System.out.println("way_to_grade5a: " + way_to_grade5a);
					System.out.println("insentavization5a : " + insentavization5a);
					System.out.println("sub_parameter_weighted_average5a : " + sub_parameter_weighted_average5a);

					System.out.println("total5b: " + total5b);
					//System.out.println("numerator_5b : " + numerator_5b);
					//System.out.println("median5b : " + median5b);
					System.out.println("way_to_grade5b: " + way_to_grade5b);
					System.out.println("insentavization5b : " + insentavization5b);
					System.out.println("sub_parameter_weighted_average5b : " + sub_parameter_weighted_average5b);
					System.out.println("total_score : " + total_weighted_average);
					System.out.println("********************************************************************************************");

				}
			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				String query_assessment = new CGSTParameterWiseQuery().QueryFor_Adjudication_5_ParticularZoneWise(month_date,zone_code);
				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("parameter");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";
					String ra ="Adjudication";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				String query_assessment = new CGSTParameterWiseQuery().QueryFor_Adjudication_5_ParticularSubparameterWise(month_date,zone_code);
				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String gst = rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absvl");
					Double t_score = rsGst14aa.getDouble("total_score");
					Double median = rsGst14aa.getDouble("median");
					Double numerator = rsGst14aa.getDouble("numerator");

					String formattedTotal = String.format("%.2f", t_score);
					double total_score = Double.parseDouble(formattedTotal);

					int way_to_grade;
					int insentavization;

					// Logic based on parameter type
					if ("GST5A".equalsIgnoreCase(gst)) {
						way_to_grade = score.marks5a(total_score);
						insentavization = score.marks5a(total_score);

						if (numerator > median && way_to_grade < 10) {
							insentavization += 1;
						}
					} else if ("GST5B".equalsIgnoreCase(gst)) {
						way_to_grade = score.marks5b(total_score);
						insentavization = way_to_grade;
					} else {
						// Default handling if parameter type is neither 5a nor 5b
						way_to_grade = 0;
						insentavization = 0;
					}

					Integer Zonal_rank = null;
					String commName = "null";
					String ra = "Adjudication";

					Double sub_parameter_weighted_average = insentavization * 0.5;
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'		'" + next_month_new + "'
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);
				String query_assessment = new CGSTParameterWiseQuery().QueryFor_Adjudication_5_AllCommissary(month_date);
				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score") * 100;
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst ="null";
					String absval = "null";
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'		'" + next_month_new + "'
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);
				String next_month_new = DateCalculate.getNextMonth(month_date);
				String query_assessment = new CGSTParameterWiseQuery().QueryFor_Adjudication_5_ParticularCommissonaryInSubparameter(month_date,zone_code,come_name);

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("score_of_subParameter") * 100;
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
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
		return allGstaList;

	}
	/*
	 * Date: july 03, 2024
	 * created: RKS(all)
	 * updated:
	 * Purpose: This methods have core function in adjudication/legacy.
	 */
	@ResponseBody
	@RequestMapping(value = "/adjudication(legacy cases)") //6
	//  http://localhost:8080/cbicApi/cbic/t_score/adjudication(legacy cases)?month_date=2023-05-01&type=parameter							// for scrutiny/assessment button
	//  http://localhost:8080/cbicApi/cbic/t_score/adjudication(legacy cases)?month_date=2023-05-01&type=zone&zone_code=59 				// for all button
	//  http://localhost:8080/cbicApi/cbic/t_score/adjudication(legacy cases)?month_date=2023-05-01&type=commissary&zone_code=59			// for show button, zone wise
	//  http://localhost:8080/cbicApi/cbic/t_score/adjudication(legacy cases)?month_date=2023-05-01&type=all_commissary					// for all commissary
	//  http://localhost:8080/cbicApi/cbic/t_score/adjudication(legacy cases)?month_date=2023-05-01&type=come_name&zone_code=64&come_name=Rajkot			// for only commissary wise, show button
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
					double tScore = rsGst14aa.getDouble("total_score") * 100;
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";
					String ra ="ADJUDICATION(LEGACY CASES)";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH cte1 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, (14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
						"),\n" +
						"cte2 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" + prev_month_new + "' \n" +
						"),\n" +
						"cte3 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, (14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) / (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS total_score2\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
						"),\n" +
						"cte4 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, (14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
						"),\n" +
						"cte5 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" + prev_month_new + "' \n" +
						"),\n" +
						"cte6 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, (14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) / (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS total_score4\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
						"),\n" +
						"ranked_scores AS (\n" +
						"    SELECT ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank, ZONE_NAME, COMM_NAME, ZONE_CODE, total_score\n" +
						"    FROM (\n" +
						"        SELECT cte1.ZONE_NAME, cte1.COMM_NAME, cte1.ZONE_CODE, \n" +
						"               cte1.col9 / cte2.col3 AS total_score1, cte3.total_score2, \n" +
						"               cte4.col9 / cte5.col3 AS total_score3, cte6.total_score4,\n" +
						"               (COALESCE(cte1.col9 / cte2.col3, 0) + COALESCE(cte3.total_score2, 0) + COALESCE(cte4.col9 / cte5.col3, 0) + COALESCE(cte6.total_score4, 0)) AS total_score \n" +
						"        FROM cte1 \n" +
						"        LEFT JOIN cte2 ON cte1.ZONE_NAME = cte2.ZONE_NAME AND cte1.COMM_NAME = cte2.COMM_NAME AND cte1.ZONE_CODE = cte2.ZONE_CODE\n" +
						"        LEFT JOIN cte3 ON cte1.ZONE_NAME = cte3.ZONE_NAME AND cte1.COMM_NAME = cte3.COMM_NAME AND cte1.ZONE_CODE = cte3.ZONE_CODE\n" +
						"        LEFT JOIN cte4 ON cte1.ZONE_NAME = cte4.ZONE_NAME AND cte1.COMM_NAME = cte4.COMM_NAME AND cte1.ZONE_CODE = cte4.ZONE_CODE\n" +
						"        LEFT JOIN cte5 ON cte1.ZONE_NAME = cte5.ZONE_NAME AND cte1.COMM_NAME = cte5.COMM_NAME AND cte1.ZONE_CODE = cte5.ZONE_CODE\n" +
						"        LEFT JOIN cte6 ON cte1.ZONE_NAME = cte6.ZONE_NAME AND cte1.COMM_NAME = cte6.COMM_NAME AND cte1.ZONE_CODE = cte6.ZONE_CODE\n" +
						"    ) sub\n" +
						")\n" +
						"SELECT z_rank,ZONE_NAME, COMM_NAME, ZONE_CODE, total_score\n" +
						"FROM ranked_scores\n" +
						"WHERE ZONE_CODE = '" + zone_code + "';\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score") * 100;
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";
					String ra ="ADJUDICATION(LEGACY CASES)";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
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
						"\t\tELSE 0 END AS score_of_subpara, CONCAT(d.col9, '/', c.col3) AS absolute_value, 'GST6A' AS gst, 'No. of cases disposed of during the month in Service Tax vis-à-vis  total cases in the beginning of the month' As ra\n" +
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
						"        ) AS absolute_value,'GST6B' AS gst, 'Number of adjudication cases pending for more than one year in Service Tax vis-à-vis total adjudication pending at the end of the month' As ra\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' and cc.ZONE_CODE = '" + zone_code + "'\n" +
						"    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						"),\n" +
						"GST6C AS (SELECT t1.ZONE_NAME, t1.ZONE_CODE, (t1.col9 / t2.col3) AS score_of_subpara,\n" +
						"           CONCAT(t1.col9, '/', t2.col3) AS absolute_value, 'GST6C' AS gst, 'No. of cases disposed of during the month in Central Excise vis-à-vis total cases in the beginning of the month' As ra\n" +
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
						"            )END AS absolute_value, 'GST6D' AS gst, 'Number of adjudication cases pending for more than one year in Central Excise vis-à-vis total adjudication pending at the end of the month' As ra\n" +
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
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subpara") * 100;
					String ra =rsGst14aa.getString("ra");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH cte1 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, (14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"        RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE  14c.MM_YYYY = '" + month_date + "' \n" +
						"),\n" +
						"cte2 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"        RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE  14c.MM_YYYY = '" + prev_month_new + "' \n" +
						"),\n" +
						"cte3 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,\n" +
						"        (14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) / (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS total_score2\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"        RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
						"),\n" +
						"cte4 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, (14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9 \n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"        RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE  14c.MM_YYYY = '" + month_date + "' \n" +
						"),\n" +
						"cte5 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3 \n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"        RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE  14c.MM_YYYY = '" + prev_month_new + "' \n" +
						"),\n" +
						"cte6 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,\n" +
						"        (14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) / \n" +
						"        (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS total_score4\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"        RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY =  '" + month_date + "' \n" +
						")\n" +
						"SELECT cte1.ZONE_NAME,cte1.COMM_NAME,cte1.ZONE_CODE,COALESCE(cte1.col9 / cte2.col3, 0) +COALESCE(cte3.total_score2, 0) +COALESCE(FORMAT(cte4.col9 / cte5.col3, 2), 0) +COALESCE(cte6.total_score4, 0) AS total_score,\n" +
						"    ROW_NUMBER() OVER (ORDER BY \n" +
						"        COALESCE(cte1.col9 / cte2.col3, 0) +COALESCE(cte3.total_score2, 0) +COALESCE(cte4.col9 / cte5.col3, 0) +COALESCE(cte6.total_score4, 0) DESC,\n" +
						"        cte1.ZONE_NAME, cte1.COMM_NAME\n" +
						"    ) AS z_rank\n" +
						"FROM cte1\n" +
						"LEFT JOIN cte2 ON cte1.ZONE_NAME = cte2.ZONE_NAME \n" +
						"         AND cte1.COMM_NAME = cte2.COMM_NAME \n" +
						"         AND cte1.ZONE_CODE = cte2.ZONE_CODE\n" +
						"LEFT JOIN cte3 ON cte1.ZONE_NAME = cte3.ZONE_NAME \n" +
						"         AND cte1.COMM_NAME = cte3.COMM_NAME \n" +
						"         AND cte1.ZONE_CODE = cte3.ZONE_CODE\n" +
						"LEFT JOIN cte4 ON cte1.ZONE_NAME = cte4.ZONE_NAME \n" +
						"         AND cte1.COMM_NAME = cte4.COMM_NAME \n" +
						"         AND cte1.ZONE_CODE = cte4.ZONE_CODE\n" +
						"LEFT JOIN cte5 ON cte1.ZONE_NAME = cte5.ZONE_NAME \n" +
						"         AND cte1.COMM_NAME = cte5.COMM_NAME \n" +
						"         AND cte1.ZONE_CODE = cte5.ZONE_CODE\n" +
						"LEFT JOIN cte6 ON cte1.ZONE_NAME = cte6.ZONE_NAME \n" +
						"         AND cte1.COMM_NAME = cte6.COMM_NAME \n" +
						"         AND cte1.ZONE_CODE = cte6.ZONE_CODE\n" +
						"ORDER BY total_score DESC,cte1.ZONE_NAME,cte1.COMM_NAME;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score") * 100;
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst ="null";
					String absval = "null";
					String ra ="ADJUDICATION(LEGACY CASES)";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH cte1 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, \n" +
						"           (14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9 \n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "' AND cc.COMM_NAME = 'Gurugram'\n" +
						"),\n" +
						"cte2 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, \n" +
						"           (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3 \n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + prev_month_new + "' AND cc.COMM_NAME = '" + come_name + "'\n" +
						"),\n" +
						"cte3 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, \n" +
						"           (14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9 \n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "' AND cc.COMM_NAME ='" + come_name + "'\n" +
						"),\n" +
						"cte4 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, \n" +
						"           (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3 \n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + prev_month_new + "' AND cc.COMM_NAME = '" + come_name + "'\n" +
						")\n" +
						"SELECT cte1.ZONE_NAME,cte1.COMM_NAME,cte1.ZONE_CODE, \n" +
						"    CASE WHEN cte2.col3 <> 0 THEN cte1.col9 / cte2.col3 ELSE NULL END AS total_score,\n" +
						"    CASE WHEN cte2.col3 <> 0 THEN CONCAT(cte1.col9, '/', cte2.col3) ELSE NULL END AS absolute_value,\n" +
						"    'GST6A' AS gst,'No. of cases disposed of during the month in Service Tax vis-à-vis  total cases in the beginning of the month' AS ra\n" +
						"FROM cte1 \n" +
						"LEFT JOIN cte2 ON cte1.ZONE_NAME = cte2.ZONE_NAME \n" +
						"              AND cte1.COMM_NAME = cte2.COMM_NAME \n" +
						"              AND cte1.ZONE_CODE = cte2.ZONE_CODE\n" +
						"UNION ALL\n" +
						"SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,\n" +
						"    (14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) / \n" +
						"    (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS total_score,\n" +
						"    CONCAT((14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT), '/',(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO)\n" +
						"    ) AS absolute_value, 'GST6B' AS gst, 'Number of adjudication cases pending for more than one year in Service Tax vis-à-vis total adjudication pending at the end of the month' AS ra\n" +
						"FROM mis_gst_commcode AS cc \n" +
						"RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"WHERE 14c.MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE = '" + zone_code + "' AND cc.COMM_NAME = '" + come_name + "'\n" +
						"UNION ALL\n" +
						"SELECT cte3.ZONE_NAME,cte3.COMM_NAME,cte3.ZONE_CODE,\n" +
						"    FORMAT(cte3.col9 / cte4.col3, 2) AS total_score, -- Formatting to two decimal places\n" +
						"    CONCAT(FORMAT(cte3.col9, 0), '/', FORMAT(cte4.col3, 0)) AS absolute_value, 'GST6C' AS gst, 'No. of cases disposed of during the month in Central Excise vis-à-vis total cases in the beginning of the month' AS ra\n" +
						"FROM cte3\n" +
						"INNER JOIN cte4 ON cte3.ZONE_NAME = cte4.ZONE_NAME\n" +
						"               AND cte3.COMM_NAME = cte4.COMM_NAME\n" +
						"               AND cte3.ZONE_CODE = cte4.ZONE_CODE\n" +
						"UNION ALL\n" +
						"SELECT zc.ZONE_NAME,cc.COMM_NAME,cc.ZONE_CODE,\n" +
						"    (14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) / \n" +
						"    (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS total_score,\n" +
						"    CONCAT(\n" +
						"        (14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT), '/',\n" +
						"        (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO)\n" +
						"    ) AS absolute_value, 'GST6D' AS gst, 'Number of adjudication cases pending for more than one year in Central Excise vis-à-vis total adjudication pending at the end of the month' AS ra\n" +
						"FROM mis_gst_commcode AS cc \n" +
						"RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "' AND cc.COMM_NAME = '" + come_name + "';\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("total_score") * 100;
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
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
		return allGstaList;

	}

	/*
	 * Date: june 22, 2024
	 * created: RKS
	 * updated:
	 * Purpose: This methods have core function in Return Filing .
	 */

	@ResponseBody
	@RequestMapping(value = "/refunds") //7//
	//  http://localhost:8080/cbicApi/cbic/t_score/refunds?month_date=2024-04-01&type=parameter							// for return filing button
	//  http://localhost:8080/cbicApi/cbic/t_score/refunds?month_date=2024-04-01&type=zone&zone_code=59 				// for all button
	//  http://localhost:8080/cbicApi/cbic/t_score/refunds?month_date=2024-04-01&type=commissary&zone_code=59			// for show button, zone wise
	//  http://localhost:8080/cbicApi/cbic/t_score/refunds?month_date=2024-04-01&type=all_commissary					// for all commissary
	//  http://localhost:8080/cbicApi/cbic/t_score/refunds?month_date=2024-04-01&type=come_name&zone_code=64&come_name=Rajkot			// for particular commissary wise, show button
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
			} else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH calculated_values_1 AS (\n" +
						"SELECT  cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME, \n" +
						"SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO) AS col16,\n" +
						"SUM(dpm.age_breakup_above60_no) AS col22,\n" +
						"SUM(dpm.age_breakup_above60_no) * 1.0 / NULLIF(SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO), 0) AS total_score\n" +
						"FROM mis_gst_commcode AS cc\n" +
						"RIGHT JOIN mis_dpm_gst_4 AS dpm ON cc.COMM_CODE = dpm.COMM_CODE\n" +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"WHERE dpm.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "'\n" +
						"GROUP BY cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME\n" +
						"),\n" +
						"calculated_values_2 AS (\n" +
						"SELECT  cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME, \n" +
						"SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO) AS col16,\n" +
						"SUM(dpm.age_breakup_above60_no) AS col22,\n" +
						"SUM(dpm.age_breakup_above60_no) * 1.0 / NULLIF(SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO), 0) AS total_score\n" +
						"FROM mis_gst_commcode AS cc\n" +
						"RIGHT JOIN mis_dpm_gst_4 AS dpm ON cc.COMM_CODE = dpm.COMM_CODE\n" +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"WHERE dpm.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME\n" +
						"),\n" +
						"ranked_values AS (\n" +
						"SELECT cv2.ZONE_CODE, cv2.ZONE_NAME, cv2.COMM_NAME, cv2.col16, cv2.col22, cv2.total_score,\n" +
						"ROW_NUMBER() OVER (ORDER BY cv2.total_score) AS z_rank\n" +
						"FROM calculated_values_2 cv2\n" +
						")\n" +
						"SELECT rv.ZONE_CODE, rv.ZONE_NAME, rv.COMM_NAME, rv.col16, rv.col22, \n" +
						"concat(rv.col22,'/',rv.col16) as absval, rv.total_score, rv.z_rank\n" +
						"FROM ranked_values rv\n" +
						"JOIN (SELECT DISTINCT COMM_NAME FROM calculated_values_1) cv1 ON rv.COMM_NAME = cv1.COMM_NAME\n" +
						"ORDER BY rv.z_rank;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score") * 100;
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer insentavization = 0;
					Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst = "null";
					String absval = rsGst14aa.getString("absval");
					//String ra ="null";
					String ra = RelevantAspect.Gst7_RA;
					String commName = rsGst14aa.getString("COMM_NAME");


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					int way_to_grade = score.marks7(total_score);
					double sub_parameter_weighted_average = way_to_grade * 0.5;
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			} else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH CTE AS (\n" +
						" SELECT SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS col16,\n" +
						" SUM(14c.age_breakup_above60_no) AS col22, cc.ZONE_CODE, zc.ZONE_NAME\n" +
						" FROM mis_gst_commcode AS cc \n" +
						" RIGHT JOIN mis_dpm_gst_4 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						" LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						" WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "'\n" +
						" GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						" )\n" +
						" SELECT ZONE_CODE, ZONE_NAME, col16, col22, \n" +
						" CONCAT(col22, '/', col16) AS absolute_value,\n" +
						" (col22 / col16) AS score_of_subParameter,\n" +
						" ROW_NUMBER() OVER (ORDER BY (col22 / col16)) AS z_rank,\n" +
						" 'GST7' AS gst,'Number of refunds applications pending beyond 60 days of receipt vis-à-vis total number of refunds applications pending at the end of the month' as ra \n" +
						" FROM CTE ORDER BY score_of_subParameter;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer insentavization = 0;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst = rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter") * 100;
					String ra = rsGst14aa.getString("ra");
					Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					int way_to_grade = score.marks7(total_score);
					double sub_parameter_weighted_average = way_to_grade * 0.5;
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			} else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH calculated_values AS (\n" +
						"SELECT  cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME, \n" +
						"SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO) AS col16,\n" +
						"SUM(dpm.age_breakup_above60_no) AS col22,\n" +
						"SUM(dpm.age_breakup_above60_no) * 1.0 / NULLIF(SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO), 0) AS total_score\n" +
						"FROM mis_gst_commcode AS cc\n" +
						"RIGHT JOIN mis_dpm_gst_4 AS dpm ON cc.COMM_CODE = dpm.COMM_CODE\n" +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"WHERE dpm.MM_YYYY = '" + month_date + "'\n" +
						"GROUP BY cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME\n" +
						")\n" +
						"SELECT ZONE_CODE, ZONE_NAME, COMM_NAME, col16,col22,total_score,\n" +
						"concat(col22 ,'/', col16 ) as absval,\n" +
						"ROW_NUMBER() OVER (ORDER BY total_score ) AS z_rank\n" +
						"FROM calculated_values\n" +
						"ORDER BY total_score ;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer insentavization = 0;
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score") * 100;
					Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst = "null";
					String absval = rsGst14aa.getString("absval");
					//String ra ="null";
					String ra = RelevantAspect.Gst7_RA;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					int way_to_grade = score.marks7(total_score);
					double sub_parameter_weighted_average = way_to_grade * 0.5;
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			} else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "\n" +
						"WITH calculated_values AS (\n" +
						"    SELECT  cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME, \n" +
						"        SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO) AS col16,\n" +
						"        SUM(dpm.age_breakup_above60_no) AS col22,\n" +
						"        SUM(dpm.age_breakup_above60_no) * 1.0 / NULLIF(SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO), 0) AS score_of_subParameter\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"        RIGHT JOIN mis_dpm_gst_4 AS dpm ON cc.COMM_CODE = dpm.COMM_CODE\n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"    WHERE dpm.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "' AND cc.COMM_NAME = '" + come_name + "'\n" +
						"    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME\n" +
						")\n" +
						"SELECT \n" +
						"    ZONE_CODE, ZONE_NAME, COMM_NAME, col16,col22, score_of_subParameter, 'GST 7' as gst, 'Number of refunds applications pending beyond 60 days of receipt vis-à-vis total number of refunds applications pending at the end of the month' as ra,\n" +
						"    CONCAT(col22, '/', col16) AS absolute_value,\n" +
						"    ROW_NUMBER() OVER (ORDER BY score_of_subParameter) AS z_rank\n" +
						"FROM calculated_values ORDER BY score_of_subParameter ;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);


				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer insentavization = 0;
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("score_of_subParameter") * 100;
					Zonal_rank = null;
					String gst = rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra = rsGst14aa.getString("ra");


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					int way_to_grade = score.marks7(total_score);
					double sub_parameter_weighted_average = way_to_grade * 0.5;
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

	/*
	 * Date: june 09, 2024
	 * created: RKS
	 * updated:
	 * Purpose: This methods have core function in recovery.
	 */
	@ResponseBody
	@RequestMapping(value = "/recovery of arrears") //8
	//  http://localhost:8080/cbicApi/cbic/t_score/recovery of arrears?month_date=2023-05-01&type=parameter							// for scrutiny/assessment button
	//  http://localhost:8080/cbicApi/cbic/t_score/recovery of arrears?month_date=2023-05-01&type=zone&zone_code=59 				// for all button
	//  http://localhost:8080/cbicApi/cbic/t_score/recovery of arrears?month_date=2023-05-01&type=commissary&zone_code=59			// for show button, zone wise
	//  http://localhost:8080/cbicApi/cbic/t_score/recovery of arrears?month_date=2023-05-01&type=all_commissary					// for all commissary
	//  http://localhost:8080/cbicApi/cbic/t_score/recovery of arrears?month_date=2023-05-01&type=come_name&zone_code=64&come_name=Rajkot			// for only commissary wise, show button
	public Object recovery(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("parameter")) { // recovery all zone name 1
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH ranked_data AS (\n"
						+ "    SELECT cc.ZONE_CODE, zc.ZONE_NAME, SUM(14c.CLOSING_AMT) AS col20, SUM(14c.BELOW_YEAR_AMT) AS col22,\n"
						+ "        (SUM(14c.CLOSING_AMT) - SUM(14c.BELOW_YEAR_AMT)) / SUM(14c.CLOSING_AMT) AS total_score,\n"
						+ "        ROW_NUMBER() OVER (ORDER BY (SUM(14c.CLOSING_AMT) - SUM(14c.BELOW_YEAR_AMT)) / SUM(14c.CLOSING_AMT) DESC) AS z_rank\n"
						+ "    FROM mis_gst_commcode AS cc \n"
						+ "    RIGHT JOIN mis_tar_gst_3_new AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
						+ "    WHERE 14c.MM_YYYY = '" + month_date + "' \n"
						+ "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n"
						+ ")\n"
						+ "SELECT ZONE_CODE, ZONE_NAME, col20, col22, total_score, z_rank\n"
						+ "FROM ranked_data;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double total_score = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";
					String ra ="RECOVERY OF ARREARS";


					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH ranked_data AS (\n"
						+ "    SELECT cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME,\n"
						+ "        MAX(14c.CLOSING_AMT) AS col20,\n"
						+ "        MAX(14c.BELOW_YEAR_AMT) AS col22,\n"
						+ "        (MAX(14c.CLOSING_AMT) - MAX(14c.BELOW_YEAR_AMT)) / MAX(14c.CLOSING_AMT) AS total_score\n"
						+ "    FROM mis_gst_commcode AS cc\n"
						+ "    RIGHT JOIN mis_tar_gst_3_new AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "'\n"
						+ "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME\n"
						+ "),\n"
						+ "ranked_data_with_rank AS (\n"
						+ "    SELECT cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME,\n"
						+ "        MAX(14c.CLOSING_AMT) AS col20,\n"
						+ "        MAX(14c.BELOW_YEAR_AMT) AS col22,\n"
						+ "        (MAX(14c.CLOSING_AMT) - MAX(14c.BELOW_YEAR_AMT)) / MAX(14c.CLOSING_AMT) AS total_score,\n"
						+ "        ROW_NUMBER() OVER (ORDER BY (MAX(14c.CLOSING_AMT) - MAX(14c.BELOW_YEAR_AMT)) / MAX(14c.CLOSING_AMT) DESC) AS z_rank\n"
						+ "    FROM mis_gst_commcode AS cc\n"
						+ "    RIGHT JOIN mis_tar_gst_3_new AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE 14c.MM_YYYY = '" + month_date + "'\n"
						+ "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME\n"
						+ ")\n"
						+ "SELECT rd.ZONE_CODE, rd.ZONE_NAME, rd.COMM_NAME, rd.col20, rd.col22, rd.total_score,\n"
						+ "       rdwr.z_rank AS actual_z_rank\n"
						+ "FROM ranked_data AS rd\n"
						+ "JOIN ranked_data_with_rank AS rdwr\n"
						+ "ON rd.ZONE_CODE = rdwr.ZONE_CODE\n"
						+ "AND rd.ZONE_NAME = rdwr.ZONE_NAME\n"
						+ "AND rd.COMM_NAME = rdwr.COMM_NAME\n"
						+ "order by actual_z_rank;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					Integer Zonal_rank = rsGst14aa.getInt("actual_z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";
					String ra ="RECOVERY OF ARREARS";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH ranked_data AS (\n"
						+ "    SELECT cc.ZONE_CODE, zc.ZONE_NAME, SUM(14c.CLOSING_AMT) AS col20, SUM(14c.BELOW_YEAR_AMT) AS col22,(SUM(14c.CLOSING_AMT) - SUM(14c.BELOW_YEAR_AMT)) / SUM(14c.CLOSING_AMT) AS score_of_subParameter,\n"
						+ "        ROW_NUMBER() OVER (ORDER BY (SUM(14c.CLOSING_AMT) - SUM(14c.BELOW_YEAR_AMT)) / SUM(14c.CLOSING_AMT) DESC) AS z_rank,\n"
						+ "        CONCAT(SUM(14c.CLOSING_AMT) - SUM(14c.BELOW_YEAR_AMT), '/', SUM(14c.CLOSING_AMT)) AS absolute_value\n"
						+ "    FROM mis_gst_commcode AS cc \n"
						+ "    RIGHT JOIN mis_tar_gst_3_new AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
						+ "    WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "'\n"
						+ "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n"
						+ ")\n"
						+ "SELECT ZONE_CODE, ZONE_NAME, col20, col22, score_of_subParameter,\n"
						+ "    'GST8B' AS gst,'Recoverable arrears pending for more than one year vis-à-vis total recoverable arrears pending ' as ra,\n"
						+ "    z_rank,absolute_value FROM ranked_data;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;
					String commName = "null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH ranked_data AS (\n"
						+ "    SELECT cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME,\n"
						+ "        MAX(14c.CLOSING_AMT) AS col20, MAX(14c.BELOW_YEAR_AMT) AS col22,\n"
						+ "        (MAX(14c.CLOSING_AMT) - MAX(14c.BELOW_YEAR_AMT)) / MAX(14c.CLOSING_AMT) AS total_score,\n"
						+ "        ROW_NUMBER() OVER (ORDER BY (MAX(14c.CLOSING_AMT) - MAX(14c.BELOW_YEAR_AMT)) / MAX(14c.CLOSING_AMT) DESC) AS z_rank\n"
						+ "    FROM mis_gst_commcode AS cc\n"
						+ "    RIGHT JOIN mis_tar_gst_3_new AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE 14c.MM_YYYY = '" + month_date + "'\n"
						+ "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME\n"
						+ ")\n"
						+ "SELECT ZONE_CODE, ZONE_NAME, COMM_NAME, col20, col22, total_score, z_rank\n"
						+ "FROM ranked_data;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst ="null";
					String absval = "null";
					String ra ="RECOVERY OF ARREARS";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH ranked_data AS (\n"
						+ "    SELECT cc.ZONE_CODE,zc.ZONE_NAME,cc.COMM_NAME,\n"
						+ "        MAX(14c.CLOSING_AMT) AS col20,MAX(14c.BELOW_YEAR_AMT) AS col22,\n"
						+ "        (MAX(14c.CLOSING_AMT) - MAX(14c.BELOW_YEAR_AMT)) / MAX(14c.CLOSING_AMT) AS score_of_subParameter,\n"
						+ "        ROW_NUMBER() OVER (ORDER BY (MAX(14c.CLOSING_AMT) - MAX(14c.BELOW_YEAR_AMT)) / MAX(14c.CLOSING_AMT) DESC) AS z_rank\n"
						+ "    FROM mis_gst_commcode AS cc\n"
						+ "    RIGHT JOIN mis_tar_gst_3_new AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE 14c.MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE = '" + zone_code + "' AND cc.COMM_NAME = '" + come_name + "'\n"
						+ "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME\n"
						+ ")\n"
						+ "SELECT ZONE_CODE, ZONE_NAME, COMM_NAME, col20, col22, score_of_subParameter,\n"
						+ "    'GST8B' AS gst, 'Recoverable arrears pending for more than one year vis-à-vis total recoverable arrears pending ' as ra, z_rank,\n"
						+ "    CONCAT(ABS(col20 - col22),'/',col20) AS absolute_value FROM ranked_data;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
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
		return allGstaList;

	}
	/*
	 * Date: june 09, 2024
	 * created: RKS
	 * updated:
	 * Purpose: This methods have core function in arrest/prosecution.
	 */
	@ResponseBody
	@RequestMapping(value = "/arrest and prosecution") //9
	//  http://localhost:8080/cbicApi/cbic/t_score/arrest and prosecution?month_date=2023-05-01&type=parameter							// for scrutiny/assessment button
	//  http://localhost:8080/cbicApi/cbic/t_score/arrest and prosecution?month_date=2023-05-01&type=zone&zone_code=59 				// for all button
	//  http://localhost:8080/cbicApi/cbic/t_score/arrest and prosecution?month_date=2023-05-01&type=commissary&zone_code=59			// for show button, zone wise
	//  http://localhost:8080/cbicApi/cbic/t_score/arrest and prosecution?month_date=2023-05-01&type=all_commissary					// for all commissary
	//  http://localhost:8080/cbicApi/cbic/t_score/arrest and prosecution?month_date=2023-05-01&type=come_name&zone_code=64&come_name=Rajkot			// for only commissary wise, show button
	public Object arrestProsecution(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("parameter")) { // arrest/prosecution all zone name 1
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "SELECT zc.ZONE_NAME,cc.ZONE_CODE,\n"
						+ "    SUM(14c.PROSECUTION_LAUNCHED) AS col4,SUM(14c.ARRESTS_MADE) AS col4_1,SUM(14c.PROSECUTION_LAUNCHED) / SUM(14c.ARRESTS_MADE) AS total_score,\n"
						+ "    ROW_NUMBER() OVER (ORDER BY SUM(14c.PROSECUTION_LAUNCHED) / SUM(14c.ARRESTS_MADE) DESC) AS z_rank\n"
						+ "FROM mis_gst_commcode AS cc\n"
						+ "RIGHT JOIN mis_gi_gst_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
						+ "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "WHERE 14c.MM_YYYY <= '" + month_date + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n"
						+ "ORDER BY total_score DESC, z_rank;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";
					String ra ="ARREST AND PROSECUTION";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";
					String ra ="ARREST AND PROSECUTION";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "SELECT zc.ZONE_NAME,\n"
						+ "       cc.ZONE_CODE,\n"
						+ "       SUM(c.PROSECUTION_LAUNCHED) AS col4,\n"
						+ "       SUM(c.ARRESTS_MADE) AS col4_1,\n"
						+ "       CONCAT(SUM(c.PROSECUTION_LAUNCHED), '/', SUM(c.ARRESTS_MADE)) AS score_of_subParameter,\n"
						+ "       'GST9B' AS gst,\n"
						+ "       'Number of Prosecution launched upto the month vis-à-vis number of arrests made upto the month' AS ra,\n"
						+ "       CONCAT(ROW_NUMBER() OVER (ORDER BY SUM(c.PROSECUTION_LAUNCHED) / NULLIF(SUM(c.ARRESTS_MADE), 0) DESC), '') AS z_rank,\n"
						+ "       CONCAT(SUM(c.PROSECUTION_LAUNCHED), '/', SUM(c.ARRESTS_MADE)) AS absolute_value\n"
						+ "FROM mis_gst_commcode AS cc\n"
						+ "RIGHT JOIN mis_gi_gst_1a AS c ON cc.COMM_CODE = c.COMM_CODE\n"
						+ "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "WHERE c.MM_YYYY <=  '" + month_date + "' AND cc.ZONE_CODE = '69'\n"
						+ "GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n"
						+ "ORDER BY SUM(c.PROSECUTION_LAUNCHED) / NULLIF(SUM(c.ARRESTS_MADE), 0) DESC;\n"
						+ "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;
					String commName = "null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH ranked_data AS (\n"
						+ "    SELECT zc.ZONE_NAME,cc.ZONE_CODE,cc.COMM_NAME,gst.PROSECUTION_LAUNCHED AS col4, gst.ARRESTS_MADE AS col4_1,\n"
						+ "        CASE WHEN gst.ARRESTS_MADE <> 0 THEN gst.PROSECUTION_LAUNCHED / gst.ARRESTS_MADE ELSE NULL\n"
						+ "	END AS total_score\n"
						+ "    FROM mis_gst_commcode AS cc\n"
						+ "    RIGHT JOIN mis_gi_gst_1a AS gst ON cc.COMM_CODE = gst.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE gst.MM_YYYY < '" + month_date + "'\n"
						+ ")\n"
						+ "SELECT ranked_data.*,\n"
						+ "    ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank\n"
						+ "FROM ranked_data;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst ="null";
					String absval = "null";
					String ra ="ARREST AND PROSECUTION";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH ranked_data AS (\n"
						+ "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, gst.PROSECUTION_LAUNCHED AS col4, gst.ARRESTS_MADE AS col4_1,\n"
						+ "        CASE\n"
						+ "            WHEN gst.ARRESTS_MADE <> 0 THEN gst.PROSECUTION_LAUNCHED / gst.ARRESTS_MADE\n"
						+ "            ELSE NULL\n"
						+ "        END AS score_of_subParameter,\n"
						+ "        'GST9B' AS gst, 'Number of Prosecution launched upto the month vis-à-vis  number of arrests made upto the month ' as ra\n"
						+ "    FROM mis_gst_commcode AS cc\n"
						+ "    RIGHT JOIN mis_gi_gst_1a AS gst ON cc.COMM_CODE = gst.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
						+ "    WHERE gst.MM_YYYY < '" + month_date + "' AND zc.ZONE_CODE = '" + zone_code + "' AND cc.COMM_NAME = '" + come_name + "'\n"
						+ ")\n"
						+ "SELECT ranked_data.*,\n"
						+ "    ROW_NUMBER() OVER (ORDER BY score_of_subParameter DESC) AS z_rank,\n"
						+ "    CASE\n"
						+ "        WHEN col4_1 <> 0 THEN CONCAT(ABS(col4), '/', ABS(col4_1))\n"
						+ "        ELSE NULL END AS absolute_value FROM ranked_data;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
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
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double total_score = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";
					String ra ="null";


					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";
					String ra ="null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst ="null";
					String absval = "null";
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
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
	//  http://localhost:8080/cbicApi/cbic/t_score/appeals?month_date=2023-05-01&type=all_commissary					// for all commissary		// data is not pr
	//  http://localhost:8080/cbicApi/cbic/t_score/appeals?month_date=2023-05-01&type=come_name&zone_code=64&come_name=Rajkot			// for only commissary wise, show button
	public Object appeals(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
		List<TotalScore> allGstaList = new ArrayList<>();
		TotalScore totalScore = null;
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {

			if (type.equalsIgnoreCase("parameter")) { // appeals all zone name 1
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH cteA AS (\n" +
						"    SELECT zc.ZONE_NAME, zc.ZONE_CODE,\n" +
						"        SUM(11a.DEPARTMENT_DISPOSAL_NO + 11a.TAXPAYER_DISPOSAL_NO) AS col10\n" +
						"    FROM mis_dla_gst_lgl_1 11a\n" +
						"    LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
						"    WHERE 11a.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6\n" +
						"    GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
						"),\n" +
						"\n" +
						"cte1A AS (\n" +
						"    SELECT zc.ZONE_CODE, SUM(DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4\n" +
						"    FROM mis_dla_gst_lgl_1 11a\n" +
						"    LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
						"    WHERE 11a.MM_YYYY = '" + prev_month_new + "' AND FORUM_CODE = 6\n" +
						"    GROUP BY zc.ZONE_CODE\n" +
						"),\n" +
						"\n" +
						"cte2A AS (\n" +
						"    SELECT zc.ZONE_CODE, \n" +
						"           SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n" +
						"           SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A\n" +
						"    FROM mis_dla_gst_lgl_1a AS 11a\n" +
						"    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
						"    WHERE 11a.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6\n" +
						"    GROUP BY zc.ZONE_CODE\n" +
						"),\n" +
						"\n" +
						"cte3A AS (\n" +
						"    SELECT zc.ZONE_CODE,\n" +
						"           SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, \n" +
						"           SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B\n" +
						"    FROM mis_dla_gst_lgl_1b AS 11b\n" +
						"    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
						"    WHERE 11b.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6\n" +
						"    GROUP BY zc.ZONE_CODE\n" +
						"),\n" +
						"\n" +
						"cteB AS (\n" +
						"    SELECT zc.ZONE_NAME, zc.ZONE_CODE,\n" +
						"        SUM(11a.DEPARTMENT_DISPOSAL_NO + 11a.TAXPAYER_DISPOSAL_NO) AS col10\n" +
						"    FROM mis_dla_gst_lgl_1 11a\n" +
						"    LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
						"    WHERE 11a.MM_YYYY ='" + month_date + "' AND 11a.FORUM_CODE = 7\n" +
						"    GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
						"),\n" +
						"\n" +
						"cte1B AS (\n" +
						"    SELECT zc.ZONE_CODE, SUM(DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4\n" +
						"    FROM mis_dla_gst_lgl_1 11a\n" +
						"    LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
						"    WHERE 11a.MM_YYYY = '" + prev_month_new + "' AND 11a.FORUM_CODE = 7\n" +
						"    GROUP BY zc.ZONE_CODE\n" +
						"),\n" +
						"\n" +
						"cte2B AS (\n" +
						"    SELECT zc.ZONE_CODE,\n" +
						"        SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A,\n" +
						"        SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A\n" +
						"    FROM mis_dla_gst_lgl_1a AS 11a\n" +
						"    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
						"    WHERE 11a.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 7\n" +
						"    GROUP BY zc.ZONE_CODE\n" +
						"),\n" +
						"\n" +
						"cte3B AS (\n" +
						"    SELECT zc.ZONE_CODE,\n" +
						"        SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B,\n" +
						"        SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B\n" +
						"    FROM mis_dla_gst_lgl_1b AS 11b\n" +
						"    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
						"    WHERE 11b.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 7\n" +
						"    GROUP BY zc.ZONE_CODE\n" +
						"),\n" +
						"\n" +
						"scoreA AS (\n" +
						"    SELECT cteA.ZONE_NAME, cteA.ZONE_CODE,\n" +
						"        COALESCE((cteA.col10 / cte1A.col4), 0) + \n" +
						"        COALESCE(CASE WHEN (cte2A.col10A + cte3A.col10B) = 0 THEN NULL \n" +
						"                      ELSE (((cte2A.col10A - cte2A.col12A) + (cte3A.col10B - cte3A.col12B)) / (cte2A.col10A + cte3A.col10B)) \n" +
						"                 END, 0) AS total_scoreA\n" +
						"    FROM cteA\n" +
						"    INNER JOIN cte1A ON cteA.ZONE_CODE = cte1A.ZONE_CODE\n" +
						"    INNER JOIN cte2A ON cteA.ZONE_CODE = cte2A.ZONE_CODE\n" +
						"    INNER JOIN cte3A ON cteA.ZONE_CODE = cte3A.ZONE_CODE\n" +
						"),\n" +
						"\n" +
						"scoreB AS (\n" +
						"    SELECT cteB.ZONE_NAME, cteB.ZONE_CODE,\n" +
						"        COALESCE(CASE WHEN cte1B.col4 = 0 THEN NULL ELSE (cteB.col10 / cte1B.col4) END, 0) + \n" +
						"        COALESCE((((cte2B.col10A - cte2B.col12A) + (cte3B.col10B - cte3B.col12B)) / (cte2B.col10A + cte3B.col10B)), 0) AS total_scoreB\n" +
						"    FROM cteB\n" +
						"    INNER JOIN cte1B ON cteB.ZONE_CODE = cte1B.ZONE_CODE\n" +
						"    INNER JOIN cte2B ON cteB.ZONE_CODE = cte2B.ZONE_CODE\n" +
						"    INNER JOIN cte3B ON cteB.ZONE_CODE = cte3B.ZONE_CODE\n" +
						")\n" +
						"\n" +
						"SELECT scoreA.ZONE_NAME, scoreA.ZONE_CODE,\n" +
						"       scoreA.total_scoreA, scoreB.total_scoreB,\n" +
						"       (scoreA.total_scoreA + scoreB.total_scoreB) AS total_score,\n" +
						"       DENSE_RANK() OVER (ORDER BY (scoreA.total_scoreA + scoreB.total_scoreB) DESC) AS z_rank\n" +
						"FROM scoreA\n" +
						"INNER JOIN scoreB ON scoreA.ZONE_CODE = scoreB.ZONE_CODE;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score") * 100;
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH cte_disposal_1 AS (\n"
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n"
						+ "           (11a.DEPARTMENT_DISPOSAL_NO + 11a.TAXPAYER_DISPOSAL_NO) AS col10\n"
						+ "    FROM mis_dla_gst_lgl_1 AS 11a\n"
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11a.MM_YYYY = '" + month_date + "' AND 11a.FORUM_CODE = 6\n"
						+ "),\n"
						+ "cte_closing_1 AS (\n"
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n"
						+ "           (11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col4\n"
						+ "    FROM mis_dla_gst_lgl_1 AS 11a\n"
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11a.MM_YYYY = '" + month_date + "' AND 11a.FORUM_CODE = 6\n"
						+ "),\n"
						+ "cte_breakup_1a AS (\n"
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n"
						+ "           (11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n"
						+ "           (11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A\n"
						+ "    FROM mis_dla_gst_lgl_1a AS 11a\n"
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11a.MM_YYYY = '" + month_date + "' AND 11a.FORUM_CODE = 6\n"
						+ "),\n"
						+ "cte_breakup_1b AS (\n"
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n"
						+ "           (11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, \n"
						+ "           (11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B\n"
						+ "    FROM mis_dla_gst_lgl_1b AS 11b\n"
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11b.MM_YYYY = '" + month_date + "' AND 11b.FORUM_CODE = 6\n"
						+ "),\n"
						+ "cte_disposal_2 AS (\n"
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n"
						+ "           (11a.DEPARTMENT_DISPOSAL_NO + 11a.TAXPAYER_DISPOSAL_NO) AS col10\n"
						+ "    FROM mis_dla_gst_lgl_1 AS 11a\n"
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11a.MM_YYYY = '" + month_date + "' AND 11a.FORUM_CODE = 7\n"
						+ "),\n"
						+ "cte_closing_2 AS (\n"
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n"
						+ "           (11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col4\n"
						+ "    FROM mis_dla_gst_lgl_1 AS 11a\n"
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11a.MM_YYYY = '" + prev_month_new + "' AND 11a.FORUM_CODE = 7\n"
						+ "),\n"
						+ "cte_breakup_2a AS (\n"
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n"
						+ "           (11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n"
						+ "           (11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A\n"
						+ "    FROM mis_dla_gst_lgl_1a AS 11a\n"
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11a.MM_YYYY = '" + month_date + "' AND 11a.FORUM_CODE = 7\n"
						+ "),\n"
						+ "cte_breakup_2b AS (\n"
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n"
						+ "           (11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, \n"
						+ "           (11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B\n"
						+ "    FROM mis_dla_gst_lgl_1b AS 11b\n"
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11b.MM_YYYY = '" + month_date + "' AND 11b.FORUM_CODE = 7\n"
						+ "),\n"
						+ "cte_scores AS (\n"
						+ "    SELECT\n"
						+ "        cte_disposal_1.ZONE_NAME, \n"
						+ "        cte_disposal_1.ZONE_CODE, \n"
						+ "        cte_disposal_1.COMM_NAME, \n"
						+ "        COALESCE((cte_disposal_1.col10 / cte_closing_1.col4) + \n"
						+ "                 (((cte_breakup_1a.col10A - cte_breakup_1a.col12A) + \n"
						+ "                   (cte_breakup_1b.col10B - cte_breakup_1b.col12B)) / \n"
						+ "                   (cte_breakup_1a.col10A + cte_breakup_1b.col10B)), 0) AS total_score_1,\n"
						+ "        COALESCE((cte_disposal_2.col10 / cte_closing_2.col4) + \n"
						+ "                 (((cte_breakup_2a.col10A - cte_breakup_2a.col12A) + \n"
						+ "                   (cte_breakup_2b.col10B - cte_breakup_2b.col12B)) / \n"
						+ "                   (cte_breakup_2a.col10A + cte_breakup_2b.col10B)), 0) AS total_score_2,\n"
						+ "        COALESCE((cte_disposal_1.col10 / cte_closing_1.col4) + \n"
						+ "                 (((cte_breakup_1a.col10A - cte_breakup_1a.col12A) + \n"
						+ "                   (cte_breakup_1b.col10B - cte_breakup_1b.col12B)) / \n"
						+ "                   (cte_breakup_1a.col10A + cte_breakup_1b.col10B)), 0) + \n"
						+ "        COALESCE((cte_disposal_2.col10 / cte_closing_2.col4) + \n"
						+ "                 (((cte_breakup_2a.col10A - cte_breakup_2a.col12A) + \n"
						+ "                   (cte_breakup_2b.col10B - cte_breakup_2b.col12B)) / \n"
						+ "                   (cte_breakup_2a.col10A + cte_breakup_2b.col10B)), 0) AS parameter\n"
						+ "    FROM cte_disposal_1\n"
						+ "    INNER JOIN cte_closing_1 ON cte_disposal_1.COMM_CODE = cte_closing_1.COMM_CODE\n"
						+ "    INNER JOIN cte_breakup_1a ON cte_disposal_1.COMM_CODE = cte_breakup_1a.COMM_CODE\n"
						+ "    INNER JOIN cte_breakup_1b ON cte_disposal_1.COMM_CODE = cte_breakup_1b.COMM_CODE\n"
						+ "    INNER JOIN cte_disposal_2 ON cte_disposal_1.COMM_CODE = cte_disposal_2.COMM_CODE\n"
						+ "    INNER JOIN cte_closing_2 ON cte_disposal_2.COMM_CODE = cte_closing_2.COMM_CODE\n"
						+ "    INNER JOIN cte_breakup_2a ON cte_disposal_2.COMM_CODE = cte_breakup_2a.COMM_CODE\n"
						+ "    INNER JOIN cte_breakup_2b ON cte_disposal_2.COMM_CODE = cte_breakup_2b.COMM_CODE\n"
						+ "),\n"
						+ "cte_ranked AS (\n"
						+ "    SELECT\n"
						+ "        ZONE_NAME,\n"
						+ "        ZONE_CODE,\n"
						+ "        COMM_NAME,\n"
						+ "        total_score_1,\n"
						+ "        total_score_2,\n"
						+ "        parameter,\n"
						+ "        ROW_NUMBER() OVER (ORDER BY parameter DESC) AS z_rank\n"
						+ "    FROM cte_scores\n"
						+ ")\n"
						+ "SELECT *\n"
						+ "FROM cte_ranked\n"
						+ "WHERE ZONE_CODE = '" + zone_code + "';\n"
						+ "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("parameter") * 100;
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH cte AS (\n" +
						"    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS col10\n" +
						"    FROM\n" +
						"        mis_dla_gst_lgl_1 11a\n" +
						"        LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
						"        LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
						"    WHERE 11a.MM_YYYY = '" + month_date + "' and zc.ZONE_CODE='" + zone_code + "' AND FORUM_CODE = 6\n" +
						"    GROUP BY zc.ZONE_CODE\n" +
						"),\n" +
						"\n" +
						"cte1 AS (\n" +
						"    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4\n" +
						"    FROM mis_dla_gst_lgl_1 11a\n" +
						"        LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
						"        LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
						"    WHERE 11a.MM_YYYY = '" + prev_month_new + "' and zc.ZONE_CODE='" + zone_code + "' AND FORUM_CODE = 6 GROUP BY zc.ZONE_CODE\n" +
						"),\n" +
						"\n" +
						"cte2 AS (\n" +
						"    SELECT zc.ZONE_NAME,  zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A\n" +
						"    FROM mis_dla_gst_lgl_1a AS 11a\n" +
						"    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
						"    WHERE 11a.MM_YYYY = '" + month_date + "' and zc.ZONE_CODE='" + zone_code + "' AND FORUM_CODE = 6 GROUP BY zc.ZONE_CODE\n" +
						"),\n" +
						"\n" +
						"cte3 AS (\n" +
						"    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B\n" +
						"    FROM mis_dla_gst_lgl_1b AS 11b\n" +
						"    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
						"    WHERE 11b.MM_YYYY = '" + month_date + "' and zc.ZONE_CODE='" + zone_code + "'  AND FORUM_CODE = 6 GROUP BY zc.ZONE_CODE\n" +
						"),\n" +
						"\n" +
						"cte_gst11C AS (\n" +
						"    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_DISPOSAL_NO + 11a.TAXPAYER_DISPOSAL_NO) AS col10\n" +
						"    FROM  mis_dla_gst_lgl_1 11a\n" +
						"        LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
						"        LEFT JOIN  mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
						"    WHERE 11a.MM_YYYY ='" + month_date + "' and zc.ZONE_CODE='" + zone_code + "'  AND 11a.FORUM_CODE = 7 GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
						"),\n" +
						"\n" +
						"cte_gst11C1 AS (\n" +
						"    SELECT zc.ZONE_NAME, zc.ZONE_CODE,  SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col4\n" +
						"    FROM mis_dla_gst_lgl_1 11a\n" +
						"        LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
						"        LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
						"    WHERE 11a.MM_YYYY = '" + prev_month_new + "' and zc.ZONE_CODE='" + zone_code + "' AND 11a.FORUM_CODE = 7 GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
						"),\n" +
						"\n" +
						"cte_gst11D AS (\n" +
						"    SELECT zc.ZONE_NAME,  zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A\n" +
						"    FROM  mis_dla_gst_lgl_1a AS 11a\n" +
						"    LEFT JOIN  mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
						"    LEFT JOIN  mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
						"    WHERE   11a.MM_YYYY = '" + month_date + "' and zc.ZONE_CODE='" + zone_code + "'  AND FORUM_CODE = 7  GROUP BY zc.ZONE_CODE\n" +
						"),\n" +
						"\n" +
						"cte_gst11D1 AS (\n" +
						"    SELECT  zc.ZONE_NAME,  zc.ZONE_CODE,  SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B,  SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B\n" +
						"    FROM  mis_dla_gst_lgl_1b AS 11b\n" +
						"    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE\n" +
						"    LEFT JOIN  mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
						"    WHERE 11b.MM_YYYY = '" + month_date + "' and zc.ZONE_CODE='" + zone_code + "'  AND FORUM_CODE = 7\n" +
						"    GROUP BY  zc.ZONE_CODE\n" +
						")\n" +
						"\n" +
						"SELECT\n" +
						"    cte.ZONE_NAME, cte.ZONE_CODE, (cte.col10 / cte1.col4) AS total_score, 'gst11A' AS Gst, 'Number of appeal cases disposed of during the month vis-à-vis  pending appeal cases  at the beginning for the month' as ra,\n" +
						"    CONCAT(cte.col10, '/', cte1.col4) AS absolute_value\n" +
						"FROM cte\n" +
						"    INNER JOIN cte1 ON cte.ZONE_CODE = cte1.ZONE_CODE\n" +
						"\n" +
						"UNION ALL\n" +
						"\n" +
						"SELECT\n" +
						"    cte2.ZONE_NAME, cte2.ZONE_CODE, CASE WHEN (cte2.col10A + cte3.col10B) = 0 THEN NULL ELSE (((cte2.col10A - cte2.col12A) + (cte3.col10B - cte3.col12B)) / (cte2.col10A + cte3.col10B)) END AS total_score, 'gst11B' AS Gst, 'Number of appeal cases  pending for more than one year vis-à-vis  total cases of appeal pending ' as ra,\n" +
						"    CONCAT((cte2.col10A - cte2.col12A) + (cte3.col10B - cte3.col12B), '/', (cte2.col10A + cte3.col10B)) AS absolute_value\n" +
						"FROM\n" +
						"    cte2\n" +
						"    INNER JOIN cte3 ON cte2.ZONE_CODE = cte3.ZONE_CODE\n" +
						"\n" +
						"UNION ALL\n" +
						"\n" +
						"SELECT\n" +
						"    cte_gst11C.ZONE_NAME,\n" +
						"    cte_gst11C.ZONE_CODE,\n" +
						"    CASE WHEN cte_gst11C1.col4 = 0 THEN NULL ELSE (cte_gst11C.col10 / cte_gst11C1.col4) END AS total_score,\n" +
						"    'gst11C' AS Gst, 'Number of Cases disposed during the month vis-à-vis  pending cases at the beginning for the month' as ra,\n" +
						"    CASE WHEN cte_gst11C1.col4 = 0 THEN NULL ELSE CONCAT(cte_gst11C.col10, '/', cte_gst11C1.col4) END AS absolute_value\n" +
						"FROM\n" +
						"    cte_gst11C\n" +
						"    INNER JOIN cte_gst11C1 ON cte_gst11C.ZONE_CODE = cte_gst11C1.ZONE_CODE\n" +
						"\n" +
						"UNION ALL\n" +
						"\n" +
						"SELECT\n" +
						"    cte_gst11D.ZONE_NAME,\n" +
						"    cte_gst11D.ZONE_CODE,\n" +
						"    CASE WHEN (cte_gst11D.col10A + cte_gst11D1.col10B) = 0 THEN NULL ELSE (((cte_gst11D.col10A - cte_gst11D.col12A) + (cte_gst11D1.col10B - cte_gst11D1.col12B)) / (cte_gst11D.col10A + cte_gst11D1.col10B)) END AS total_score,\n" +
						"    'gst11D' AS Gst, 'Number of appeal cases pending for more than One year vis-à-vis  total cases of appeal pending ' as ra,\n" +
						"    CONCAT(\n" +
						"        ABS(((cte_gst11D.col10A - cte_gst11D.col12A) + (cte_gst11D1.col10B - cte_gst11D1.col12B))),\n" +
						"        '/',\n" +
						"        ABS((cte_gst11D.col10A + cte_gst11D1.col10B))\n" +
						"    ) AS absolute_value\n" +
						"FROM\n" +
						"    cte_gst11D\n" +
						"    INNER JOIN cte_gst11D1 ON cte_gst11D.ZONE_CODE = cte_gst11D1.ZONE_CODE;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("total_score") * 100;
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH cte_disposal_1 AS (\n"
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n"
						+ "           (11a.DEPARTMENT_DISPOSAL_NO + 11a.TAXPAYER_DISPOSAL_NO) AS col10\n"
						+ "    FROM mis_dla_gst_lgl_1 AS 11a\n"
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11a.MM_YYYY = '" + month_date + "' AND 11a.FORUM_CODE = 6\n"
						+ "),\n"
						+ "cte_closing_1 AS (\n"
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n"
						+ "           (11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col4\n"
						+ "    FROM mis_dla_gst_lgl_1 AS 11a\n"
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11a.MM_YYYY = '" + month_date + "' AND 11a.FORUM_CODE = 6\n"
						+ "),\n"
						+ "cte_breakup_1a AS (\n"
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n"
						+ "           (11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n"
						+ "           (11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A\n"
						+ "    FROM mis_dla_gst_lgl_1a AS 11a\n"
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11a.MM_YYYY = '" + month_date + "' AND 11a.FORUM_CODE = 6\n"
						+ "),\n"
						+ "cte_breakup_1b AS (\n"
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n"
						+ "           (11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, \n"
						+ "           (11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B\n"
						+ "    FROM mis_dla_gst_lgl_1b AS 11b\n"
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11b.MM_YYYY = '" + month_date + "' AND 11b.FORUM_CODE = 6\n"
						+ "),\n"
						+ "cte_disposal_2 AS (\n"
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n"
						+ "           (11a.DEPARTMENT_DISPOSAL_NO + 11a.TAXPAYER_DISPOSAL_NO) AS col10\n"
						+ "    FROM mis_dla_gst_lgl_1 AS 11a\n"
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11a.MM_YYYY = '" + month_date + "' AND 11a.FORUM_CODE = 7\n"
						+ "),\n"
						+ "cte_closing_2 AS (\n"
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n"
						+ "           (11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col4\n"
						+ "    FROM mis_dla_gst_lgl_1 AS 11a\n"
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11a.MM_YYYY = '" + prev_month_new + "' AND 11a.FORUM_CODE = 7\n"
						+ "),\n"
						+ "cte_breakup_2a AS (\n"
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n"
						+ "           (11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n"
						+ "           (11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A\n"
						+ "    FROM mis_dla_gst_lgl_1a AS 11a\n"
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11a.MM_YYYY = '" + month_date + "' AND 11a.FORUM_CODE = 7\n"
						+ "),\n"
						+ "cte_breakup_2b AS (\n"
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n"
						+ "           (11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, \n"
						+ "           (11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B\n"
						+ "    FROM mis_dla_gst_lgl_1b AS 11b\n"
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11b.MM_YYYY = '" + month_date + "' AND 11b.FORUM_CODE = 7\n"
						+ ")\n"
						+ "SELECT\n"
						+ "    cte_disposal_1.ZONE_NAME, \n"
						+ "    cte_disposal_1.ZONE_CODE, \n"
						+ "    cte_disposal_1.COMM_NAME, \n"
						+ "    COALESCE((cte_disposal_1.col10 / cte_closing_1.col4) + \n"
						+ "             (((cte_breakup_1a.col10A - cte_breakup_1a.col12A) + \n"
						+ "               (cte_breakup_1b.col10B - cte_breakup_1b.col12B)) / \n"
						+ "               (cte_breakup_1a.col10A + cte_breakup_1b.col10B)), 0) AS total_score_1,\n"
						+ "    COALESCE((cte_disposal_2.col10 / cte_closing_2.col4) + \n"
						+ "             (((cte_breakup_2a.col10A - cte_breakup_2a.col12A) + \n"
						+ "               (cte_breakup_2b.col10B - cte_breakup_2b.col12B)) / \n"
						+ "               (cte_breakup_2a.col10A + cte_breakup_2b.col10B)), 0) AS total_score_2,\n"
						+ "    COALESCE((cte_disposal_1.col10 / cte_closing_1.col4) + \n"
						+ "             (((cte_breakup_1a.col10A - cte_breakup_1a.col12A) + \n"
						+ "               (cte_breakup_1b.col10B - cte_breakup_1b.col12B)) / \n"
						+ "               (cte_breakup_1a.col10A + cte_breakup_1b.col10B)), 0) + \n"
						+ "    COALESCE((cte_disposal_2.col10 / cte_closing_2.col4) + \n"
						+ "             (((cte_breakup_2a.col10A - cte_breakup_2a.col12A) + \n"
						+ "               (cte_breakup_2b.col10B - cte_breakup_2b.col12B)) / \n"
						+ "               (cte_breakup_2a.col10A + cte_breakup_2b.col10B)), 0) AS parameter,\n"
						+ "    ROW_NUMBER() OVER (ORDER BY \n"
						+ "        COALESCE((cte_disposal_1.col10 / cte_closing_1.col4) + \n"
						+ "             (((cte_breakup_1a.col10A - cte_breakup_1a.col12A) + \n"
						+ "               (cte_breakup_1b.col10B - cte_breakup_1b.col12B)) / \n"
						+ "               (cte_breakup_1a.col10A + cte_breakup_1b.col10B)), 0) + \n"
						+ "        COALESCE((cte_disposal_2.col10 / cte_closing_2.col4) + \n"
						+ "             (((cte_breakup_2a.col10A - cte_breakup_2a.col12A) + \n"
						+ "               (cte_breakup_2b.col10B - cte_breakup_2b.col12B)) / \n"
						+ "               (cte_breakup_2a.col10A + cte_breakup_2b.col10B)), 0) DESC\n"
						+ "    ) AS z_rank\n"
						+ "FROM cte_disposal_1\n"
						+ "INNER JOIN cte_closing_1 ON cte_disposal_1.COMM_CODE = cte_closing_1.COMM_CODE\n"
						+ "INNER JOIN cte_breakup_1a ON cte_disposal_1.COMM_CODE = cte_breakup_1a.COMM_CODE\n"
						+ "INNER JOIN cte_breakup_1b ON cte_disposal_1.COMM_CODE = cte_breakup_1b.COMM_CODE\n"
						+ "INNER JOIN cte_disposal_2 ON cte_disposal_1.COMM_CODE = cte_disposal_2.COMM_CODE\n"
						+ "INNER JOIN cte_closing_2 ON cte_disposal_2.COMM_CODE = cte_closing_2.COMM_CODE\n"
						+ "INNER JOIN cte_breakup_2a ON cte_disposal_2.COMM_CODE = cte_breakup_2a.COMM_CODE\n"
						+ "INNER JOIN cte_breakup_2b ON cte_disposal_2.COMM_CODE = cte_breakup_2b.COMM_CODE;\n"
						+ "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("parameter") * 100;
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst ="null";
					String absval = "null";
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH cte AS (\n"
						+ "    SELECT\n"
						+ "        zc.ZONE_NAME,zc.ZONE_CODE,cc.COMM_NAME,(DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS col10\n"
						+ "        FROM mis_dla_gst_lgl_1 11a\n"
						+ "        LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE\n"
						+ "        LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11a.MM_YYYY = '" + month_date + "' and zc.ZONE_CODE='" + zone_code + "' and cc.COMM_NAME='GUWAHATI' AND FORUM_CODE = 6\n"
						+ "),\n"
						+ "cte1 AS (\n"
						+ "    SELECT\n"
						+ "        zc.ZONE_NAME,zc.ZONE_CODE,cc.COMM_NAME,(DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4\n"
						+ "        FROM mis_dla_gst_lgl_1 11a\n"
						+ "        LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE\n"
						+ "        LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11a.MM_YYYY = '" + prev_month_new + "' and zc.ZONE_CODE='" + zone_code + "' and cc.COMM_NAME='GUWAHATI' AND FORUM_CODE = 6\n"
						+ "),\n"
						+ "cte2 AS (\n"
						+ "    SELECT\n"
						+ "        zc.ZONE_NAME,zc.ZONE_CODE,cc.COMM_NAME,(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A,\n"
						+ "        (11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A\n"
						+ "        FROM mis_dla_gst_lgl_1a AS 11a\n"
						+ "        LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n"
						+ "        LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11a.MM_YYYY = '" + month_date + "' and zc.ZONE_CODE='" + zone_code + "' and cc.COMM_NAME='GUWAHATI' AND FORUM_CODE = 6\n"
						+ "),\n"
						+ "cte3 AS (\n"
						+ "    SELECT\n"
						+ "        zc.ZONE_NAME,zc.ZONE_CODE,cc.COMM_NAME,(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B,(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B\n"
						+ "		FROM mis_dla_gst_lgl_1b AS 11b\n"
						+ "        LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE\n"
						+ "        LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11b.MM_YYYY = '" + month_date + "' and zc.ZONE_CODE='" + zone_code + "'  AND FORUM_CODE = 6 and cc.COMM_NAME='GUWAHATI'\n"
						+ "),\n"
						+ "cte_gst11C AS (\n"
						+ "    SELECT\n"
						+ "        zc.ZONE_NAME,zc.ZONE_CODE,cc.COMM_NAME,(11a.DEPARTMENT_DISPOSAL_NO + 11a.TAXPAYER_DISPOSAL_NO) AS col10\n"
						+ "    FROM mis_dla_gst_lgl_1 11a\n"
						+ "        LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE\n"
						+ "        LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11a.MM_YYYY ='" + month_date + "' and zc.ZONE_CODE='" + zone_code + "' and cc.COMM_NAME='GUWAHATI' AND 11a.FORUM_CODE = 7\n"
						+ "    \n"
						+ "),\n"
						+ "\n"
						+ "cte_gst11C1 AS (\n"
						+ "    SELECT\n"
						+ "        zc.ZONE_NAME,zc.ZONE_CODE,cc.COMM_NAME,(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col4\n"
						+ "    FROM mis_dla_gst_lgl_1 11a\n"
						+ "        LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE\n"
						+ "        LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11a.MM_YYYY = '" + prev_month_new + "' and zc.ZONE_CODE='" + zone_code + "' and cc.COMM_NAME='GUWAHATI' AND 11a.FORUM_CODE = 7\n"
						+ "),\n"
						+ "cte_gst11D AS (\n"
						+ "    SELECT\n"
						+ "        zc.ZONE_NAME,zc.ZONE_CODE,cc.COMM_NAME,(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A,(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A\n"
						+ "    FROM mis_dla_gst_lgl_1a AS 11a\n"
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11a.MM_YYYY = '" + month_date + "' and zc.ZONE_CODE='" + zone_code + "' and cc.COMM_NAME='GUWAHATI' AND FORUM_CODE = 7\n"
						+ "),\n"
						+ "cte_gst11D1 AS (\n"
						+ "    SELECT\n"
						+ "        zc.ZONE_NAME,zc.ZONE_CODE,cc.COMM_NAME,(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B,(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B\n"
						+ "    FROM mis_dla_gst_lgl_1b AS 11b\n"
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE\n"
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n"
						+ "    WHERE 11b.MM_YYYY = '" + month_date + "' and zc.ZONE_CODE='" + zone_code + "' and cc.COMM_NAME='GUWAHATI' AND FORUM_CODE = 7\n"
						+ ")\n"
						+ "SELECT\n"
						+ "    cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_NAME, (cte.col10 / cte1.col4) AS score_of_subParameter,\n"
						+ "    'gst11A' AS gst, 'Number of appeal cases disposed of during the month vis-à-vis  pending appeal cases  at the beginning for the month' as ra,\n"
						+ "    CONCAT(cte.col10, '/', cte1.col4) AS absolute_value\n"
						+ "FROM cte INNER JOIN cte1 ON cte.ZONE_CODE = cte1.ZONE_CODE\n"
						+ "\n"
						+ "UNION ALL\n"
						+ "\n"
						+ "SELECT\n"
						+ "    cte2.ZONE_NAME, cte2.ZONE_CODE, cte2.COMM_NAME, CASE WHEN (cte2.col10A + cte3.col10B) = 0 THEN NULL ELSE (((cte2.col10A - cte2.col12A) + (cte3.col10B - cte3.col12B)) / (cte2.col10A + cte3.col10B)) END AS total_score,\n"
						+ "    'gst11B' AS gst, 'Number of appeal cases  pending for more than one year vis-à-vis  total cases of appeal pending ' as ra,\n"
						+ "    CONCAT((cte2.col10A - cte2.col12A) + (cte3.col10B - cte3.col12B), '/', (cte2.col10A + cte3.col10B)) AS absolute_value\n"
						+ "FROM cte2 INNER JOIN cte3 ON cte2.ZONE_CODE = cte3.ZONE_CODE\n"
						+ "\n"
						+ "UNION ALL\n"
						+ "\n"
						+ "SELECT\n"
						+ "    cte_gst11C.ZONE_NAME, cte_gst11C.ZONE_CODE, cte_gst11C.COMM_NAME, CASE WHEN cte_gst11C1.col4 = 0 THEN NULL ELSE (cte_gst11C.col10 / cte_gst11C1.col4) END AS total_score,\n"
						+ "    'gst11C' AS gst, 'Number of Cases disposed during the month vis-à-vis  pending cases at the beginning for the month ' as ra,\n"
						+ "    CASE WHEN cte_gst11C1.col4 = 0 THEN NULL ELSE CONCAT(cte_gst11C.col10, '/', cte_gst11C1.col4) END AS absolute_value\n"
						+ "FROM cte_gst11C INNER JOIN cte_gst11C1 ON cte_gst11C.ZONE_CODE = cte_gst11C1.ZONE_CODE\n"
						+ "\n"
						+ "UNION ALL\n"
						+ "\n"
						+ "SELECT\n"
						+ "    cte_gst11D.ZONE_NAME, cte_gst11D.ZONE_CODE, cte_gst11D.COMM_NAME,\n"
						+ "    CASE WHEN (cte_gst11D.col10A + cte_gst11D1.col10B) = 0 THEN NULL ELSE (((cte_gst11D.col10A - cte_gst11D.col12A) + (cte_gst11D1.col10B - cte_gst11D1.col12B)) / (cte_gst11D.col10A + cte_gst11D1.col10B)) END AS total_score,\n"
						+ "    'gst11D' AS gst, 'Number of appeal cases pending for more than One year vis-à-vis  total cases of appeal pending ' as ra,\n"
						+ "    CONCAT(\n"
						+ "        ABS(((cte_gst11D.col10A - cte_gst11D.col12A) + (cte_gst11D1.col10B - cte_gst11D1.col12B))),\n"
						+ "        '/',\n"
						+ "        ABS((cte_gst11D.col10A + cte_gst11D1.col10B))\n"
						+ "    ) AS absolute_value\n"
						+ "FROM cte_gst11D INNER JOIN cte_gst11D1 ON cte_gst11D.ZONE_CODE = cte_gst11D1.ZONE_CODE;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer way_to_grade = 0;
					Integer insentavization = 0;
					double sub_parameter_weighted_average = 0.00;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("score_of_subParameter") * 100;
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;



					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
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
		return allGstaList;
	}
}