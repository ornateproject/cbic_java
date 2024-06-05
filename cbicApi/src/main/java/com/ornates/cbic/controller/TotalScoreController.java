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
	@RequestMapping(value = "/scrutiny/assessment")
	//  http://localhost:8080/CbicAPI/cbic/t_score/scrutiny/assessment?month_date=2023-04-01
	public Object scrutinyAssessment(@RequestParam String month_date) {
		List<TotalScore> allGstaList = new ArrayList<>();
		Connection con = null;
		ResultSet rsGst14aa = null;
		try {
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

			rsGst14aa= GetExecutionSQL.getResult(query_assessment);

			while (rsGst14aa.next()) {
				double total_score = rsGst14aa.getDouble("total_score");
				Integer Zonal_rank = rsGst14aa.getInt("z_rank");
				String zoneName = rsGst14aa.getString("ZONE_NAME");
				String commName = "ALL";

				TotalScore totalScore = new TotalScore(zoneName, commName, total_score, Zonal_rank);
				allGstaList.add(totalScore);
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
