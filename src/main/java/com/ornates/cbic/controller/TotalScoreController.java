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
					String ra ="null";
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";

					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra);
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
					String ra ="null";
					String gst = "null";
					String absval = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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
					String ra =rsGst14aa.getString("ra");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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
					String ra ="null";
					String gst ="null";
					String absval = "null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra =rsGst14aa.getString("ra");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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
					String ra ="null";

					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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
					String ra ="null";
					String commName = rsGst14aa.getString("COMM_NAME");


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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
						"    ROW_NUMBER() OVER (ORDER BY score_of_subParameter DESC) AS z_rank, ZONE_NAME, ZONE_CODE, col21, col3, score_of_subParameter, 'GST2' AS gst, '*Percentage of returns which were due but not filed vis-à-vis total returns due (GSTR 3B) ' as ra,\n" +
						"    CONCAT(CAST(col21 AS CHAR), '/', CAST(col3 AS CHAR)) AS absolute_value\n" +
						"FROM score_calculation ORDER BY score_of_subParameter DESC;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst = rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String ra =rsGst14aa.getString("ra");
					Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			} else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH score_calculation AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
						"        (14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, (14c.GSTR_3BM_F) AS col3, ((14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / (14c.GSTR_3BM_F)) AS score_of_subParameter,\n" +
						"        'GST2' AS gst, CONCAT((14c.GSTR_3BM_F - 14c.GSTR_3BM_D), '/', 14c.GSTR_3BM_F) AS absolute_value, '*Percentage of returns which were due but not filed vis-à-vis total returns due (GSTR 3B) ' as ra\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE = '" + zone_code + "' AND cc.COMM_NAME = '" + come_name + "'\n" +
						")\n" +
						"SELECT ROW_NUMBER() OVER (ORDER BY score_of_subParameter DESC) AS z_rank,\n" +
						"    ZONE_NAME, ZONE_CODE, COMM_NAME, col21, col3,score_of_subParameter,gst,absolute_value,ra\n" +
						"FROM score_calculation ORDER BY score_of_subParameter DESC;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);


				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					Zonal_rank = null;
					String gst = rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra =rsGst14aa.getString("ra");


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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

				String query_assessment = "WITH current_data AS (\n" +
                        "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4, SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9, SUM(14c.OUTCOME_SECTION_61) AS col10, SUM(14c.RETURN_SCRUTINY) AS col2\n" +
                        "    FROM mis_gst_commcode AS cc\n" +
                        "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "    WHERE 14c.MM_YYYY = '2023-05-01' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        "),\n" +
                        "previous_data AS (\n" +
                        "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col1\n" +
                        "    FROM mis_gst_commcode AS cc\n" +
                        "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "    WHERE 14c.MM_YYYY = '2023-04-01'\n" +
                        "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        "),\n" +
                        "parameter1 AS (\n" +
                        "    SELECT current_data.ZONE_NAME, current_data.ZONE_CODE, ((current_data.col4 + current_data.col9 + current_data.col10) / (current_data.col2 + previous_data.col1)) as score_of_parameter1\n" +
                        "    FROM current_data\n" +
                        "    JOIN previous_data ON current_data.ZONE_CODE = previous_data.ZONE_CODE\n" +
                        "),\n" +
                        "parameter2 AS (\n" +
                        "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) / SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter2\n" +
                        "    FROM mis_gst_commcode AS cc\n" +
                        "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "    WHERE 14c.MM_YYYY <= '2023-05-01' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        "),\n" +
                        "ranked_data AS (\n" +
                        "    SELECT p1.ZONE_NAME, p1.ZONE_CODE, (p1.score_of_parameter1 + p2.score_of_parameter2) as total_score,\n" +
                        "           ROW_NUMBER() OVER (ORDER BY (p1.score_of_parameter1 + p2.score_of_parameter2) DESC) as z_rank\n" +
                        "    FROM parameter1 p1\n" +
                        "    LEFT JOIN parameter2 p2 ON p1.ZONE_CODE = p2.ZONE_CODE\n" +
                        "    UNION\n" +
                        "    SELECT p2.ZONE_NAME, p2.ZONE_CODE, (p1.score_of_parameter1 + p2.score_of_parameter2) as total_score,\n" +
                        "           ROW_NUMBER() OVER (ORDER BY (p1.score_of_parameter1 + p2.score_of_parameter2) DESC) as z_rank\n" +
                        "    FROM parameter1 p1\n" +
                        "    RIGHT JOIN parameter2 p2 ON p1.ZONE_CODE = p2.ZONE_CODE\n" +
                        ")\n" +
                        "SELECT ZONE_NAME, ZONE_CODE, total_score, z_rank\n" +
                        "FROM ranked_data ORDER BY total_score DESC;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double total_score = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";
					String ra ="null";

					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra);
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
					String ra ="null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
                //                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "-- Combined query for GST 3A and GST 3B zone data\n" +
                        "SELECT\n" +
                        "    current_data.ZONE_NAME, current_data.ZONE_CODE,\n" +
                        "    ((current_data.col4 + current_data.col9 + current_data.col10) / (current_data.col2 + previous_data.col1)) AS score_of_parameter,\n" +
                        "    CONCAT( '(',\n" +
                        "        (current_data.col4 + current_data.col9 + current_data.col10),\n" +
                        "        ' / ',\n" +
                        "        (current_data.col2 + previous_data.col1),\n" +
                        "        ')'\n" +
                        "    ) AS absval,\n" +
                        "    'GST 3A' AS gst,'Number of Returns whose scrutiny completed for the month vis-à-vis total Returns pending for the month (Pro-rata basis)' as ra\n" +
                        "FROM (\n" +
                        "    SELECT\n" +
                        "        zc.ZONE_NAME,cc.ZONE_CODE,\n" +
                        "        SUM(scr.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4,SUM(scr.OUTCOME_ASMT_12_ISSUED) AS col9,SUM(scr.OUTCOME_SECTION_61) AS col10,SUM(scr.RETURN_SCRUTINY) AS col2\n" +
                        "    FROM mis_gst_commcode AS cc\n" +
                        "        RIGHT JOIN mis_dggst_gst_scr_1 AS scr ON cc.COMM_CODE = scr.COMM_CODE\n" +
                        "        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "    WHERE scr.MM_YYYY = '" + month_date + "' and cc.ZONE_CODE='" + zone_code + "'\n" +
                        "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        ") AS current_data\n" +
                        "JOIN (\n" +
                        "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(scr.CLOSING_NO) AS col1\n" +
                        "    FROM mis_gst_commcode AS cc\n" +
                        "        RIGHT JOIN mis_dggst_gst_scr_1 AS scr ON cc.COMM_CODE = scr.COMM_CODE\n" +
                        "        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "    WHERE scr.MM_YYYY = '" + prev_month_new + "' and cc.ZONE_CODE='" + zone_code + "'\n" +
                        "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        ") AS previous_data ON current_data.ZONE_CODE = previous_data.ZONE_CODE\n" +
                        "UNION ALL\n" +
                        "SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                        "    SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) /\n" +
                        "    SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter,\n" +
                        "    CONCAT( SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY),\n" +
                        "        ' / ', SUM(14c.TAX_LIABILITY_DETECTECT)\n" +
                        "    ) AS absval, 'GST 3B' AS gst,'Recoveries made upto the month vis-a-vis detections upto the month' as ra\n" +
                        "FROM mis_gst_commcode AS cc\n" +
                        "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "WHERE 14c.MM_YYYY <= '" + month_date + "' and cc.ZONE_CODE='" + zone_code + "'\n" +
                        "GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        "ORDER BY ZONE_CODE;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absval");
					double tScore = rsGst14aa.getDouble("score_of_parameter");
					String ra =rsGst14aa.getString("ra");
					Integer Zonal_rank = null;
					String commName = "null";
					//String ra = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
                //                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
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
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
                //                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
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
						"    SELECT ZONE_NAME, COMM_NAME, ZONE_CODE, 'GST3A' AS gst, 'Number of Returns whose scrutiny completed for the month vis-à-vis total Returns pending for the month (Pro-rata basis)' AS ra,\n" +
						"        CASE WHEN (col1 + col2) != 0 THEN (col4 + col9 + col10) / (col1 + col2) ELSE NULL END AS total_score,\n" +
						"        CASE WHEN (col1 + col2) != 0 THEN CONCAT((col4 + col9 + col10), '/', (col1 + col2)) ELSE NULL END AS absolute_value\n" +
						"    FROM CurrentMonthData\n" +
						"),\n" +
						"GST3B_Data AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 'GST3B' AS gst, 'Recoveries made upto the month vis-a-vis detections upto the month' AS ra,\n" +
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
					String ra =rsGst14aa.getString("ra");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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
						"        WHERE 7c.MM_YYYY = '" + month_date + "'\n" +
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
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";
					String ra ="null";

					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
                //                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
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
					String ra ="null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
                //                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
                //                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
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
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
                //                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);
				String next_month_new = DateCalculate.getNextMonth(month_date);

				String query_assessment = "WITH CTE1 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
						"        SUM(14c.ADC_COMMISSIONERATE_DISPOSAL_NO + 14c.ADC_AUDIT_DISPOSAL_NO + 14c.ADC_INVESTIGATION_DISPOSAL_NO + 14c.ADC_CALLBOOK_DISPOSAL_NO + \n" +
						"            14c.DC_COMMISSIONERATE_DISPOSAL_NO + 14c.DC_AUDIT_DISPOSAL_NO + 14c.DC_INVESTIGATION_DISPOSAL_NO + 14c.DC_CALLBOOK_DISPOSAL_NO + \n" +
						"            14c.SUPERINTENDENT_COMMISSIONERATE_DISPOSAL_NO + 14c.SUPERINTENDENT_AUDIT_DISPOSAL_NO + 14c.SUPERINTENDENT_INVESTIGATION_DISPOSAL_NO + \n" +
						"            14c.SUPERINTENDENT_CALLBOOK_DISPOSAL_NO) AS total_disposals,\n" +
						"        SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
						"            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
						"            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + \n" +
						"            14c.SUPERINTENDENT_CALLBOOK_OPENING_NO) AS total_openings\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
						"    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						"),\n" +
						"CTE2 AS (\n" +
						"\tSELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
						"        SUM(14c.ADC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.ADC_AUDIT_TIME_LESS_3_NO + 14c.ADC_INVESTIGATION_TIME_LESS_3_NO + 14c.ADC_CALLBOOK_TIME_LESS_3_NO + \n" +
						"            14c.DC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.DC_AUDIT_TIME_LESS_3_NO + 14c.DC_INVESTIGATION_TIME_LESS_3_NO + 14c.DC_CALLBOOK_TIME_LESS_3_NO + \n" +
						"            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_LESS_3_NO + 14c.SUPERINTENDENT_AUDIT_TIME_LESS_3_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_LESS_3_NO + \n" +
						"            14c.SUPERINTENDENT_CALLBOOK_TIME_LESS_3_NO) AS col22,\n" +
						"        SUM(14c.ADC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.ADC_AUDIT_TIME_3_TO_6_NO + 14c.ADC_INVESTIGATION_TIME_3_TO_6_NO + 14c.ADC_CALLBOOK_TIME_3_TO_6_NO + \n" +
						"            14c.DC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.DC_AUDIT_TIME_3_TO_6_NO + 14c.DC_INVESTIGATION_TIME_3_TO_6_NO + 14c.DC_CALLBOOK_TIME_3_TO_6_NO + \n" +
						"            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_AUDIT_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_3_TO_6_NO + \n" +
						"            14c.SUPERINTENDENT_CALLBOOK_TIME_3_TO_6_NO) AS col23\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						"),\n" +
						"CTE3 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
						"        SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
						"            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
						"            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + \n" +
						"            14c.SUPERINTENDENT_CALLBOOK_OPENING_NO) AS col16\n" +
						"    FROM mis_gst_commcode AS cc\n" +
						"    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + next_month_new + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
						"),\n" +
						"FINAL_CTE1 AS (\n" +
						"    SELECT CTE1.ZONE_NAME, CTE1.ZONE_CODE, total_disposals, total_openings,col22,col23,col16\n" +
						"    FROM CTE1\n" +
						"    LEFT JOIN CTE2 ON CTE1.ZONE_CODE = CTE2.ZONE_CODE\n" +
						"    LEFT JOIN CTE3 ON CTE1.ZONE_CODE = CTE3.ZONE_CODE\n" +
						"),\n" +
						"FINAL_CTE2 AS (\n" +
						"    SELECT CTE2.ZONE_NAME, CTE2.ZONE_CODE, total_disposals, total_openings,col22,col23,col16\n" +
						"    FROM CTE2\n" +
						"    LEFT JOIN CTE1 ON CTE2.ZONE_CODE = CTE1.ZONE_CODE\n" +
						"    LEFT JOIN CTE3 ON CTE2.ZONE_CODE = CTE3.ZONE_CODE\n" +
						"    WHERE CTE1.ZONE_CODE IS NULL\n" +
						"),\n" +
						"FINAL_CTE3 AS (\n" +
						"    SELECT CTE3.ZONE_NAME, CTE3.ZONE_CODE, total_disposals, total_openings,col22,col23,col16\n" +
						"    FROM CTE3\n" +
						"    LEFT JOIN CTE1 ON CTE3.ZONE_CODE = CTE1.ZONE_CODE\n" +
						"    LEFT JOIN CTE2 ON CTE3.ZONE_CODE = CTE2.ZONE_CODE\n" +
						"    WHERE CTE1.ZONE_CODE IS NULL AND CTE2.ZONE_CODE IS NULL\n" +
						"),\n" +
						"CombinedResults AS (\n" +
						"    SELECT ZONE_NAME,ZONE_CODE,(total_disposals / NULLIF(total_openings, 0)) AS total_score1,(col22 + col23) / NULLIF(col16, 0) AS total_score2,(total_disposals / NULLIF(total_openings, 0)) + (col22 + col23) / NULLIF(col16, 0) AS total_score\n" +
						"    FROM FINAL_CTE1\n" +
						"\n" +
						"    UNION ALL\n" +
						"\n" +
						"    SELECT ZONE_NAME,ZONE_CODE,(total_disposals / NULLIF(total_openings, 0)) AS total_score1,(col22 + col23) / NULLIF(col16, 0) AS total_score2,(total_disposals / NULLIF(total_openings, 0)) + (col22 + col23) / NULLIF(col16, 0) AS total_score\n" +
						"    FROM FINAL_CTE2\n" +
						"\n" +
						"    UNION ALL\n" +
						"\n" +
						"    SELECT ZONE_NAME,ZONE_CODE,(total_disposals / NULLIF(total_openings, 0)) AS total_score1,(col22 + col23) / NULLIF(col16, 0) AS total_score2,(total_disposals / NULLIF(total_openings, 0)) + (col22 + col23) / NULLIF(col16, 0) AS total_score\n" +
						"    FROM FINAL_CTE3\n" +
						")\n" +
						"SELECT ZONE_NAME,ZONE_CODE,total_score1,total_score2,total_score,ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank\n" +
						"FROM CombinedResults\n" +
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
					String ra ="null";

					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);
				String next_month_new = DateCalculate.getNextMonth(month_date);

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
					String ra ="null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);
				String next_month_new = DateCalculate.getNextMonth(month_date);

				String query_assessment = " SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
						"                CONCAT( ABS(SUM(\n" +
						"            14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + \n" +
						"            14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
						"            14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no\n" +
						"        )),\n" +
						"        '/',\n" +
						"        ABS(SUM(\n" +
						"            14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
						"            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
						"            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
						"        ))\n" +
						"    ) AS absolute_value,\n" +
						"    (SUM(\n" +
						"        14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + \n" +
						"        14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
						"        14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no\n" +
						"    ) / \n" +
						"    NULLIF(\n" +
						"        SUM(\n" +
						"            14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
						"            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
						"            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
						"        ),\n" +
						"        0\n" +
						"    )) AS score_of_subParameter, 'gst5a' AS gst, 'Number of cases disposed of during the month vis-à-vis total pending cases at the beginning of the month' as ra\n" +
						"FROM mis_gst_commcode AS cc\n" +
						"    JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"WHERE 14c.MM_YYYY = '" + month_date + "' and cc.ZONE_CODE ='" + zone_code + "' GROUP BY  cc.ZONE_CODE, zc.ZONE_NAME\n" +
						"\n" +
						"UNION ALL\n" +
						"\n" +
						"SELECT cm.ZONE_NAME, cm.ZONE_CODE,\n" +
						"    CONCAT(cm.col22 + cm.col23, '/', pm.col16) AS absolute_value2,\n" +
						"    (cm.col22 + cm.col23) / pm.col16 AS total_score2, 'gst5b' AS gst, 'Number of cases where time left for adjudication is less than 6 months vis-à-vis total adjudication cases pending at the end of the month' as ra\n" +
						"FROM\n" +
						"    (\n" +
						"        SELECT\n" +
						"            zc.ZONE_NAME,\n" +
						"            cc.ZONE_CODE,\n" +
						"            SUM( 14c.ADC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.ADC_AUDIT_TIME_LESS_3_NO +  14c.ADC_INVESTIGATION_TIME_LESS_3_NO + 14c.ADC_CALLBOOK_TIME_LESS_3_NO +\n" +
						"                14c.DC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.DC_AUDIT_TIME_LESS_3_NO + 14c.DC_INVESTIGATION_TIME_LESS_3_NO + 14c.DC_CALLBOOK_TIME_LESS_3_NO +\n" +
						"                14c.SUPERINTENDENT_COMMISSIONERATE_TIME_LESS_3_NO + 14c.SUPERINTENDENT_AUDIT_TIME_LESS_3_NO +  14c.SUPERINTENDENT_INVESTIGATION_TIME_LESS_3_NO +\n" +
						"                14c.SUPERINTENDENT_CALLBOOK_TIME_LESS_3_NO\n" +
						"            ) AS col22,\n" +
						"            SUM(  14c.ADC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.ADC_AUDIT_TIME_3_TO_6_NO +  14c.ADC_INVESTIGATION_TIME_3_TO_6_NO + 14c.ADC_CALLBOOK_TIME_3_TO_6_NO +\n" +
						"                14c.DC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.DC_AUDIT_TIME_3_TO_6_NO + 14c.DC_INVESTIGATION_TIME_3_TO_6_NO + 14c.DC_CALLBOOK_TIME_3_TO_6_NO +\n" +
						"                14c.SUPERINTENDENT_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_AUDIT_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_3_TO_6_NO +\n" +
						"                14c.SUPERINTENDENT_CALLBOOK_TIME_3_TO_6_NO\n" +
						"            ) AS col23\n" +
						"        FROM mis_gst_commcode AS cc\n" +
						"            RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"        WHERE 14c.MM_YYYY = '" + month_date + "' and cc.ZONE_CODE= '" + zone_code + "' GROUP BY cc.ZONE_CODE\n" +
						"    ) AS cm\n" +
						"    JOIN (\n" +
						"        SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
						"            SUM( 14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO +  14c.ADC_CALLBOOK_OPENING_NO +\n" +
						"                14c.DC_COMMISSIONERATE_OPENING_NO +  14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO +\n" +
						"                14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO +\n" +
						"                14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
						"            ) AS col16\n" +
						"        FROM mis_gst_commcode AS cc\n" +
						"            RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"        WHERE 14c.MM_YYYY = '" + next_month_new + "'  and cc.ZONE_CODE= '" + zone_code + "'\n" +
						"        GROUP BY cc.ZONE_CODE\n" +
						"    ) AS pm ON cm.ZONE_CODE = pm.ZONE_CODE;\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'		'" + next_month_new + "'
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);
				String next_month_new = DateCalculate.getNextMonth(month_date);

				String query_assessment = "WITH total_score1_query AS (\n" +
						"    SELECT \n" +
						"        zc.ZONE_NAME AS ZONE_NAME,cc.ZONE_CODE AS ZONE_CODE,cc.COMM_NAME AS COMM_NAME,\n" +
						"        (14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + 14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + 14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no) / \n" +
						"        NULLIF((14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + 14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + 14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO), 0) AS total_score1,\n" +
						"        NULL AS total_score2\n" +
						"    FROM \n" +
						"        mis_gst_commcode AS cc \n" +
						"        RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
						"),\n" +
						"total_score2_query AS (\n" +
						"    SELECT ZONE_NAME,ZONE_CODE,COMM_NAME,NULL AS total_score1,\n" +
						"        (MAX(col22) + MAX(col23)) / MAX(col16) AS total_score2\n" +
						"    FROM (\n" +
						"        SELECT zc.ZONE_NAME AS ZONE_NAME,cc.ZONE_CODE AS ZONE_CODE,cc.COMM_NAME AS COMM_NAME,\n" +
						"            (14c.ADC_COMMISSIONERATE_TIME_LESS_3_NO +14c.ADC_AUDIT_TIME_LESS_3_NO +14c.ADC_INVESTIGATION_TIME_LESS_3_NO +14c.ADC_CALLBOOK_TIME_LESS_3_NO +14c.DC_COMMISSIONERATE_TIME_LESS_3_NO +14c.DC_AUDIT_TIME_LESS_3_NO +14c.DC_INVESTIGATION_TIME_LESS_3_NO +14c.DC_CALLBOOK_TIME_LESS_3_NO +\n" +
						"             14c.SUPERINTENDENT_COMMISSIONERATE_TIME_LESS_3_NO + 14c.SUPERINTENDENT_AUDIT_TIME_LESS_3_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_LESS_3_NO + 14c.SUPERINTENDENT_CALLBOOK_TIME_LESS_3_NO) AS col22,\n" +
						"            (14c.ADC_COMMISSIONERATE_TIME_3_TO_6_NO +14c.ADC_AUDIT_TIME_3_TO_6_NO +14c.ADC_INVESTIGATION_TIME_3_TO_6_NO + 14c.ADC_CALLBOOK_TIME_3_TO_6_NO + 14c.DC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.DC_AUDIT_TIME_3_TO_6_NO +\n" +
						"             14c.DC_INVESTIGATION_TIME_3_TO_6_NO +14c.DC_CALLBOOK_TIME_3_TO_6_NO +14c.SUPERINTENDENT_COMMISSIONERATE_TIME_3_TO_6_NO +14c.SUPERINTENDENT_AUDIT_TIME_3_TO_6_NO +14c.SUPERINTENDENT_INVESTIGATION_TIME_3_TO_6_NO +14c.SUPERINTENDENT_CALLBOOK_TIME_3_TO_6_NO) AS col23,\n" +
						"            (14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO +14c.ADC_INVESTIGATION_OPENING_NO +14c.ADC_CALLBOOK_OPENING_NO + 14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO +\n" +
						"             14c.DC_INVESTIGATION_OPENING_NO +14c.DC_CALLBOOK_OPENING_NO +14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO +14c.SUPERINTENDENT_AUDIT_OPENING_NO +14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO +14c.SUPERINTENDENT_CALLBOOK_OPENING_NO) AS col16\n" +
						"        FROM mis_gst_commcode AS cc\n" +
						"            RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"        WHERE 14c.MM_YYYY IN ('" + month_date + "', '" + next_month_new + "')\n" +
						"    ) AS merged_data GROUP BY ZONE_NAME, ZONE_CODE, COMM_NAME\n" +
						")\n" +
						"SELECT \n" +
						"    COALESCE(t1.ZONE_NAME, t2.ZONE_NAME) AS ZONE_NAME,COALESCE(t1.ZONE_CODE, t2.ZONE_CODE) AS ZONE_CODE,COALESCE(t1.COMM_NAME, t2.COMM_NAME) AS COMM_NAME,t1.total_score1,t2.total_score2,\n" +
						"    COALESCE(t1.total_score1, 0) + COALESCE(t2.total_score2, 0) AS total_score,\n" +
						"    ROW_NUMBER() OVER (ORDER BY COALESCE(t1.total_score1, 0) + COALESCE(t2.total_score2, 0) DESC) AS z_rank\n" +
						"FROM total_score1_query t1\n" +
						"LEFT JOIN total_score2_query t2 ON t1.ZONE_NAME = t2.ZONE_NAME AND t1.ZONE_CODE = t2.ZONE_CODE AND t1.COMM_NAME = t2.COMM_NAME\n" +
						"\n" +
						"UNION ALL\n" +
						"\n" +
						"SELECT \n" +
						"\tCOALESCE(t1.ZONE_NAME, t2.ZONE_NAME) AS ZONE_NAME,COALESCE(t1.ZONE_CODE, t2.ZONE_CODE) AS ZONE_CODE,COALESCE(t1.COMM_NAME, t2.COMM_NAME) AS COMM_NAME,t1.total_score1,t2.total_score2,\n" +
						"    COALESCE(t1.total_score1, 0) + COALESCE(t2.total_score2, 0) AS total_score,\n" +
						"    ROW_NUMBER() OVER (ORDER BY COALESCE(t1.total_score1, 0) + COALESCE(t2.total_score2, 0) DESC) AS z_rank\n" +
						"FROM total_score2_query t2\n" +
						"LEFT JOIN total_score1_query t1 ON t1.ZONE_NAME = t2.ZONE_NAME AND t1.ZONE_CODE = t2.ZONE_CODE AND t1.COMM_NAME = t2.COMM_NAME\n" +
						"WHERE t1.ZONE_NAME IS NULL\n" +
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
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'		'" + next_month_new + "'
				//String prev_month_new = DateCalculate.getPreviousMonth(month_date);
				String next_month_new = DateCalculate.getNextMonth(month_date);

				String query_assessment = "WITH CTE1 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
						"           (14c.ADC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.ADC_AUDIT_TIME_LESS_3_NO + 14c.ADC_INVESTIGATION_TIME_LESS_3_NO + 14c.ADC_CALLBOOK_TIME_LESS_3_NO +\n" +
						"            14c.DC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.DC_AUDIT_TIME_LESS_3_NO + 14c.DC_INVESTIGATION_TIME_LESS_3_NO + 14c.DC_CALLBOOK_TIME_LESS_3_NO +\n" +
						"            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_LESS_3_NO + 14c.SUPERINTENDENT_AUDIT_TIME_LESS_3_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_LESS_3_NO + 14c.SUPERINTENDENT_CALLBOOK_TIME_LESS_3_NO) AS col22, \n" +
						"           (14c.ADC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.ADC_AUDIT_TIME_3_TO_6_NO + 14c.ADC_INVESTIGATION_TIME_3_TO_6_NO + 14c.ADC_CALLBOOK_TIME_3_TO_6_NO +\n" +
						"            14c.DC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.DC_AUDIT_TIME_3_TO_6_NO + 14c.DC_INVESTIGATION_TIME_3_TO_6_NO + 14c.DC_CALLBOOK_TIME_3_TO_6_NO +\n" +
						"            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_AUDIT_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_CALLBOOK_TIME_3_TO_6_NO) AS col23, \n" +
						"           NULL AS col16\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + month_date + "' and cc.ZONE_CODE='" + zone_code + "' and cc.COMM_NAME= '" + come_name + "'\n" +
						"),\n" +
						"CTE2 AS (\n" +
						"    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
						"           NULL AS col22, NULL AS col23,\n" +
						"           (14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO +\n" +
						"            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO +\n" +
						"            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO) AS col16\n" +
						"    FROM mis_gst_commcode AS cc \n" +
						"    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"    WHERE 14c.MM_YYYY = '" + next_month_new + "' and cc.ZONE_CODE='" + zone_code + "' and cc.COMM_NAME= '" + come_name + "'\n" +
						")\n" +
						"SELECT ZONE_NAME, ZONE_CODE, COMM_NAME, \n" +
						"       (SUM(col22) + SUM(col23)) / SUM(col16) AS score_of_subParameter,\n" +
						"       CONCAT((SUM(col22) + SUM(col23)), '/', SUM(col16)) AS absolute_value, \n" +
						"       'GST 5A' as gst, 'Number of cases disposed of during the month vis-à-vis total pending cases at the beginning of the month' as ra\n" +
						"FROM (\n" +
						"    SELECT * FROM CTE1\n" +
						"    UNION ALL\n" +
						"    SELECT * FROM CTE2\n" +
						") AS combined_data \n" +
						"GROUP BY ZONE_NAME, ZONE_CODE, COMM_NAME\n" +
						"\n" +
						"UNION ALL\n" +
						"\n" +
						"SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
						"    (\n" +
						"        14c.adc_commissionerate_disposal_no +  14c.adc_audit_disposal_no +   14c.adc_investigation_disposal_no +   14c.adc_callbook_disposal_no +   14c.dc_commissionerate_disposal_no + \n" +
						"        14c.dc_audit_disposal_no +   14c.dc_investigation_disposal_no +   14c.dc_callbook_disposal_no +  14c.superintendent_commissionerate_disposal_no +   14c.superintendent_audit_disposal_no + \n" +
						"        14c.superintendent_investigation_disposal_no +  14c.superintendent_callbook_disposal_no \n" +
						"    ) /\n" +
						"    (\n" +
						"        14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + 14c.DC_COMMISSIONERATE_OPENING_NO +  14c.DC_AUDIT_OPENING_NO +\n" +
						"        14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + 14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO +  14c.SUPERINTENDENT_AUDIT_OPENING_NO +\n" +
						"        14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
						"    ) as score_of_subParameter,\n" +
						"    CONCAT(\n" +
						"        14c.adc_commissionerate_disposal_no +   14c.adc_audit_disposal_no +  14c.adc_investigation_disposal_no +   14c.adc_callbook_disposal_no +  14c.dc_commissionerate_disposal_no + \n" +
						"        14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no +  14c.dc_callbook_disposal_no +   14c.superintendent_commissionerate_disposal_no +  14c.superintendent_audit_disposal_no + \n" +
						"        14c.superintendent_investigation_disposal_no +   14c.superintendent_callbook_disposal_no, \n" +
						"        ' / ',\n" +
						"        14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO +  14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + 14c.DC_COMMISSIONERATE_OPENING_NO +\n" +
						"        14c.DC_AUDIT_OPENING_NO +  14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + 14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO +\n" +
						"        14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
						"    ) as absolute_value  ,\n" +
						"    'GST 5A' as gst, 'Number of cases disposed of during the month vis-à-vis total pending cases at the beginning of the month' as ra\n" +
						"FROM mis_gst_commcode as cc \n" +
						"RIGHT JOIN mis_dpm_gst_adj_1 as 14c \n" +
						"ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"LEFT JOIN mis_gst_zonecode as zc \n" +
						"ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"WHERE 14c.MM_YYYY = '" + month_date + "' and cc.ZONE_CODE='" + zone_code + "' and cc.COMM_NAME= '" + come_name + "';\n";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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
					double total_score = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";
					String ra ="null";

					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra);
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
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";
					String ra ="null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subpara");
					String ra =rsGst14aa.getString("ra");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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
						"    WHERE 14c.MM_YYYY = '2023-05-01' \n" +
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
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst ="null";
					String absval = "null";
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
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
					String ra ="null";

					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			} else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
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
					String ra ="null";
					String commName = rsGst14aa.getString("COMM_NAME");


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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
						" ROW_NUMBER() OVER (ORDER BY (col22 / col16) DESC) AS z_rank,\n" +
						" 'GST7' AS gst,'Number of refunds applications pending beyond 60 days of receipt vis-à-vis total number of refunds applications pending at the end of the month' as ra \n" +
						" FROM CTE ORDER BY score_of_subParameter DESC;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst = rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String ra = rsGst14aa.getString("ra");
					Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			} else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
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
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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
						"    ROW_NUMBER() OVER (ORDER BY score_of_subParameter DESC) AS z_rank\n" +
						"FROM calculated_values ORDER BY score_of_subParameter DESC;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);


				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					Zonal_rank = null;
					String gst = rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra = rsGst14aa.getString("ra");


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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

				String query_assessment = "WITH cte AS ( \n" +
                        "SELECT zc.ZONE_NAME,cc.ZONE_CODE,SUM(a11.ARREAR_REALISED_AMT) AS col13\n" +
                        "  FROM mis_tar_gst_3_new AS a11\n" +
                        "  LEFT JOIN mis_gst_commcode AS cc ON a11.COMM_CODE = cc.COMM_CODE\n" +
                        "  LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                        "  WHERE a11.MM_YYYY = '" + month_date + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                        "  ),\n" +
                        "  cte1 AS (\n" +
                        "  SELECT zc.ZONE_NAME,cc.ZONE_CODE,COUNT(*) AS col3\n" +
                        "  FROM mis_tar_gst_3_new AS a11\n" +
                        "  LEFT JOIN mis_gst_commcode AS cc ON a11.COMM_CODE = cc.COMM_CODE\n" +
                        "  LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                        "  WHERE a11.MM_YYYY = '" + prev_month_new + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                        "  ),\n" +
                        "  final_cte AS (\n" +
                        "  SELECT cte.ZONE_NAME,cte.ZONE_CODE,SUM(cte.col13) AS col13,SUM(cte1.col3) AS col3,(cte.col13 / cte1.col3) AS col13_col3_ratio,\n" +
                        "  additional_data.col20,additional_data.col22,\n" +
                        "  ((additional_data.col20 - additional_data.col22) / additional_data.col20) AS col20_col22_ratio,(cte.col13 / cte1.col3) + ((additional_data.col20 - additional_data.col22) / additional_data.col20) AS total_score\n" +
                        "  FROM cte\n" +
                        "  INNER JOIN cte1 ON cte.ZONE_CODE = cte1.ZONE_CODE\n" +
                        "  LEFT JOIN (\n" +
                        "  SELECT\n" +
                        "  cc.ZONE_CODE,SUM(14c.CLOSING_AMT) AS col20,SUM(14c.BELOW_YEAR_AMT) AS col22\n" +
                        "  FROM mis_gst_commcode AS cc\n" +
                        "  RIGHT JOIN mis_tar_gst_3_new AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "  WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE\n" +
                        "  ) AS additional_data ON cte.ZONE_CODE = additional_data.ZONE_CODE\n" +
                        "  GROUP BY cte.ZONE_NAME, cte.ZONE_CODE, additional_data.col20, additional_data.col22\n" +
                        "  )\n" +
                        "  SELECT\n" +
                        "  final_cte.ZONE_NAME,final_cte.ZONE_CODE,final_cte.col13,final_cte.col3,final_cte.col13_col3_ratio,final_cte.col20,final_cte.col22,final_cte.col20_col22_ratio,final_cte.total_score,\n" +
                        "  ROW_NUMBER() OVER (ORDER BY final_cte.total_score DESC) AS z_rank FROM final_cte;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double total_score = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";
					String ra ="null";


					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra);
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
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;
					String commName = "null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH cte AS (   SELECT DISTINCT \n" +
						"   zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n" +
						"   a11.ARREAR_REALISED_AMT AS col13 \n" +
						"   FROM mis_tar_gst_3_new AS a11 \n" +
						"   LEFT JOIN mis_gst_commcode AS cc ON a11.COMM_CODE = cc.COMM_CODE \n" +
						"   LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
						"   WHERE a11.MM_YYYY = '" + prev_month_new + "' \n" +
						"   ), \n" +
						"   cte1 AS (\n" +
						"   SELECT DISTINCT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, 1 AS col3 \n" +
						"   FROM mis_tar_gst_3_new AS a11 \n" +
						"   LEFT JOIN mis_gst_commcode AS cc ON a11.COMM_CODE = cc.COMM_CODE \n" +
						"   LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
						"   WHERE a11.MM_YYYY = '" + prev_month_new + "'\n" +
						"   ),\n" +
						"   cte2 AS (\n" +
						"   SELECT cc.ZONE_CODE, cc.COMM_NAME,cc.COMM_CODE,MAX(14c.CLOSING_AMT) AS col20, MAX(14c.BELOW_YEAR_AMT) AS col22\n" +
						"   FROM mis_gst_commcode AS cc \n" +
						"   RIGHT JOIN mis_tar_gst_3_new AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
						"   LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						"   WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME,cc.COMM_NAME,cc.COMM_CODE\n" +
						"   )\n" +
						"   SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_CODE, cte.col13, cte1.col3,cte.COMM_NAME, \n" +
						"   CASE WHEN cte1.col3 = 0 THEN NULL \n" +
						"   END AS Score_of_subparameter1,cte2.col20, cte2.col22,(cte2.col20 - cte2.col22) / cte2.col20 AS Score_of_subparameter2,\n" +
						"   COALESCE((cte.col13 / cte1.col3), 0) + COALESCE((cte2.col20 - cte2.col22) / cte2.col20, 0) AS total_score,\n" +
						"   RANK() OVER (ORDER BY COALESCE((cte.col13 / cte1.col3), 0) + COALESCE((cte2.col20 - cte2.col22) / cte2.col20, 0) DESC, cte.COMM_CODE DESC) AS z_rank\n" +
						"   FROM cte \n" +
						"   INNER JOIN cte1 \n" +
						"   ON cte.COMM_CODE = cte1.COMM_CODE \n" +
						"   AND cte.ZONE_CODE = cte1.ZONE_CODE \n" +
						"   AND cte.COMM_NAME = cte1.COMM_NAME\n" +
						"   INNER JOIN cte2 \n" +
						"   ON cte.COMM_CODE = cte2.COMM_CODE \n" +
						"   AND cte.ZONE_CODE = cte2.ZONE_CODE \n" +
						"   AND cte.COMM_NAME = cte2.COMM_NAME\n" +
						"   ORDER BY total_score DESC, cte.COMM_CODE DESC;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String commName = rsGst14aa.getString("COMM_NAME");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					double tScore = rsGst14aa.getDouble("total_score");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String gst ="null";
					String absval = "null";
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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

				String query_assessment = "WITH cte AS ( \n" +
						"SELECT zc.ZONE_NAME,zc.ZONE_CODE,1 AS col8, _11a.PROSECUTION_SANCTIONED AS col5   \n" +
						" FROM mis_dla_gst_lgl_4 AS _11a    \n" +
						"\t  LEFT JOIN mis_gst_commcode AS cc ON _11a.COMM_CODE = cc.COMM_CODE \n" +
						"      LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE WHERE   _11a.MM_YYYY = '" + month_date + "'\n" +
						"      ),cte1 AS (SELECT zc.ZONE_NAME,zc.ZONE_CODE, _11a.PROSECUTION_SANCTIONED AS col5_1\n" +
						"      FROM mis_dla_gst_lgl_4 AS _11a \n" +
						"      LEFT JOIN mis_gst_commcode AS cc ON _11a.COMM_CODE = cc.COMM_CODE \n" +
						"      LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
						"      WHERE _11a.MM_YYYY = '" + prev_month_new + "'\n" +
						"      ),\n" +
						"      cte2 AS (SELECT zc.ZONE_NAME,  cc.ZONE_CODE,\n" +
						"      SUM(14c.PROSECUTION_LAUNCHED) AS col4, SUM(14c.ARRESTS_MADE) AS col4_1,\n" +
						"      SUM(14c.PROSECUTION_LAUNCHED) / SUM(14c.ARRESTS_MADE) AS total_scoregst9b\n" +
						"      FROM mis_gst_commcode AS cc\n" +
						"\t  RIGHT JOIN mis_gi_gst_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
						"      LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
						"      WHERE 14c.MM_YYYY <= '" + month_date + "'\n" +
						"      GROUP BY cc.ZONE_CODE, zc.ZONE_NAME ORDER BY cc.ZONE_CODE\n" +
						"      )\n" +
						"      SELECT cte.ZONE_NAME,cte.ZONE_CODE,cte.col8,cte.col5,cte1.col5_1,\n" +
						"      CASE \n" +
						"      WHEN (cte.col5 + cte1.col5_1) <> 0 THEN cte.col8 / (cte.col5 + cte1.col5_1)\n" +
						"      ELSE NULL END AS total_scoregst9a,\n" +
						"      cte2.col4, cte2.col4_1, cte2.total_scoregst9b,\n" +
						"      (CASE WHEN (cte.col5 + cte1.col5_1) <> 0 THEN cte.col8 / (cte.col5 + cte1.col5_1)\n" +
						"      ELSE NULL\n" +
						"      END + cte2.total_scoregst9b) AS total_score,\n" +
						"      ROW_NUMBER() OVER (ORDER BY (CASE \n" +
						"      WHEN (cte.col5 + cte1.col5_1) <> 0 THEN cte.col8 / (cte.col5 + cte1.col5_1)\n" +
						"      ELSE NULL END + cte2.total_scoregst9b) DESC) AS z_rank\n" +
						"      FROM \n" +
						"      cte \n" +
						"      INNER JOIN cte1 ON cte.ZONE_NAME = cte1.ZONE_NAME AND cte.ZONE_CODE = cte1.ZONE_CODE\n" +
						"      INNER JOIN cte2 ON cte.ZONE_NAME = cte2.ZONE_NAME AND cte.ZONE_CODE = cte2.ZONE_CODE\n" +
						"      GROUP BY cte.ZONE_NAME, cte.ZONE_CODE, cte.col8, cte.col5, cte1.col5_1, cte2.col4, cte2.col4_1, cte2.total_scoregst9b\n" +
						"      ORDER BY total_score DESC;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					double tScore = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra);
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
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "WITH cte AS (\n" +
                        " SELECT  zc.ZONE_NAME, zc.ZONE_CODE, \n" +
                        " COUNT(*) AS col8, COALESCE(SUM(lgl.PROSECUTION_SANCTIONED), 0) AS col5\n" +
                        " FROM mis_dla_gst_lgl_4 AS lgl \n" +
                        " LEFT JOIN mis_gst_commcode AS cc ON lgl.COMM_CODE = cc.COMM_CODE \n" +
                        " LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                        " WHERE lgl.MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE = '" + zone_code + "' GROUP BY zc.ZONE_CODE, zc.ZONE_NAME\n" +
                        " ),\n" +
                        " cte1 AS (\n" +
                        " SELECT  zc.ZONE_NAME,zc.ZONE_CODE,  COALESCE(SUM(lgl.PROSECUTION_SANCTIONED), 0) AS col5_1 \n" +
                        " FROM mis_dla_gst_lgl_4 AS lgl \n" +
                        " LEFT JOIN mis_gst_commcode AS cc ON lgl.COMM_CODE = cc.COMM_CODE \n" +
                        " LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                        " WHERE lgl.MM_YYYY = '" + prev_month_new + "' AND zc.ZONE_CODE = '" + zone_code + "'\n" +
                        " GROUP BY zc.ZONE_CODE, zc.ZONE_NAME\n" +
                        " ),\n" +
                        " query_gst9a AS (\n" +
                        " SELECT  cte.ZONE_NAME,  cte.ZONE_CODE, \n" +
                        " ROUND((cte.col8 / NULLIF(cte.col5 + cte1.col5_1, 0)), 2) AS score_of_subParameter,\n" +
                        " 'gst9A' AS gst, 'Number of cases where prosecution was not launched within 2 months of prosecution sanction date vis-à-vis total number of prosecution sanctioned cases ' as ra,\n" +
                        " CONCAT(\n" +
                        " ROUND((cte.col8 / NULLIF(cte.col5 + cte1.col5_1, 0)) * 100),\n" +
                        " '/',\n" +
                        " 100\n" +
                        " ) AS absolute_value\n" +
                        " FROM cte \n" +
                        " INNER JOIN cte1 ON cte.ZONE_CODE = cte1.ZONE_CODE\n" +
                        " ),\n" +
                        " query_gst9b AS (\n" +
                        " SELECT  zc.ZONE_NAME,  cc.ZONE_CODE, \n" +
                        " SUM(14c.PROSECUTION_LAUNCHED) / SUM(14c.ARRESTS_MADE) AS Totalscore9b,\n" +
                        " 'gst9B' AS Gst9b, 'Number of Prosecution launched upto the month vis-à-vis  number of arrests made upto the month' as ra,\n" +
                        " CONCAT(\n" +
                        " ABS(SUM(14c.PROSECUTION_LAUNCHED)),\n" +
                        " '/',\n" +
                        " ABS(SUM(14c.ARRESTS_MADE))\n" +
                        " ) AS absolute_valuegst9b\n" +
                        " FROM mis_gst_commcode AS cc\n" +
                        " RIGHT JOIN mis_gi_gst_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        " LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        " WHERE 14c.MM_YYYY <= '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        " )\n" +
                        " SELECT * FROM query_gst9a UNION ALL SELECT * FROM query_gst9b;";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;
					String commName = "null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
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
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";
					String ra ="null";


					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra);
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
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";
					String ra ="null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
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
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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
					double total_score = rsGst14aa.getDouble("total_score");
					zone_code = rsGst14aa.getString("ZONE_CODE");
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = "ALL";
					String gst = "ALL";
					String absval = "null";
					String ra ="null";


					totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra);
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
					Integer Zonal_rank = rsGst14aa.getInt("z_rank");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					String gst = "null";
					String absval = "null";
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					double tScore = rsGst14aa.getDouble("total_score");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;
					String commName = "null";

					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
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
					String ra ="null";


					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
					allGstaList.add(totalScore);
				}
			}else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
				//                  '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "'
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String query_assessment = "";

				rsGst14aa = GetExecutionSQL.getResult(query_assessment);

				while (rsGst14aa.next()) {
					zone_code = rsGst14aa.getString("ZONE_CODE");
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commName = rsGst14aa.getString("COMM_NAME");
					double tScore = rsGst14aa.getDouble("score_of_subParameter");
					String gst =rsGst14aa.getString("gst");
					String absval = rsGst14aa.getString("absolute_value");
					String ra = rsGst14aa.getString("ra");
					Integer Zonal_rank = null;



					String formattedTotal = String.format("%.2f", tScore);
					double total_score = Double.parseDouble(formattedTotal);
					totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra);
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