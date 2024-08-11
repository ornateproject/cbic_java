package com.ornates.cbic.dao.Query;

import com.ornates.cbic.service.DateCalculate;
import org.springframework.web.bind.annotation.RequestParam;

public class CGSTParameterWiseQuery {
    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryForScrutinyAssessmentZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "WITH ranked_data_3a AS (\n" +
                "    SELECT current_data.ZONE_NAME, current_data.ZONE_CODE, current_data.col4, \n" +
                "           current_data.col9, current_data.col10, current_data.col2, previous_data.col1,\n" +
                "           (current_data.col4 + current_data.col9 + current_data.col10) / \n" +
                "           (current_data.col2 + previous_data.col1) * 100 AS score_of_subpara3a\n" +
                "    FROM  \n" +
                "        (SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
                "                   SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4, SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9, \n" +
                "                   SUM(14c.OUTCOME_SECTION_61) AS col10, SUM(14c.RETURN_SCRUTINY) AS col2  \n" +
                "            FROM mis_gst_commcode AS cc  \n" +
                "            RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "            WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
                "            GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "        ) AS current_data\n" +
                "    JOIN \n" +
                "        (\n" +
                "            SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col1 \n" +
                "            FROM mis_gst_commcode AS cc \n" +
                "            RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "            WHERE 14c.MM_YYYY = '" + prev_month_new + "' \n" +
                "            GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "        ) AS previous_data\n" +
                "    ON current_data.ZONE_CODE = previous_data.ZONE_CODE\n" +
                "),\n" +
                "numerator_data_3a AS (\n" +
                "    SELECT ZONE_NAME, ZONE_CODE, col4, col9, col10, col2, col1, score_of_subpara3a, \n" +
                "           (col4 + col9 + col10) AS numerator_3a\n" +
                "    FROM ranked_data_3a\n" +
                "),\n" +
                "ranked_numerator_3a AS (\n" +
                "    SELECT *, ROW_NUMBER() OVER (ORDER BY numerator_3a) AS rn,\n" +
                "              COUNT(*) OVER () AS cnt\n" +
                "    FROM numerator_data_3a\n" +
                "),\n" +
                "median_3a AS (\n" +
                "    SELECT ZONE_NAME, ZONE_CODE, score_of_subpara3a, numerator_3a,\n" +
                "           CASE\n" +
                "               WHEN cnt % 2 = 1 THEN (SELECT numerator_3a FROM ranked_numerator_3a WHERE rn = (cnt + 1) / 2)\n" +
                "               ELSE (SELECT AVG(numerator_3a) FROM ranked_numerator_3a WHERE rn IN (cnt / 2, cnt / 2 + 1))\n" +
                "           END AS median_numerator_3a\n" +
                "    FROM ranked_numerator_3a\n" +
                "),\n" +
                "ranked_data_3b AS (\n" +
                "    SELECT cc.ZONE_CODE,\n" +
                "        SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) AS numerator_3b,\n" +
                "        ROW_NUMBER() OVER (ORDER BY SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY)) AS row_num,\n" +
                "        COUNT(*) OVER () AS total_count\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    WHERE 14c.MM_YYYY <= '" + month_date + "'\n" +
                "    GROUP BY cc.ZONE_CODE\n" +
                "),\n" +
                "median_data_3b AS (\n" +
                "    SELECT\n" +
                "        CASE\n" +
                "            WHEN total_count % 2 = 1 THEN\n" +
                "                (SELECT numerator_3b FROM ranked_data_3b WHERE row_num = (total_count + 1) / 2)\n" +
                "            ELSE\n" +
                "                (SELECT AVG(numerator_3b) FROM ranked_data_3b WHERE row_num IN ((total_count / 2), (total_count / 2) + 1))\n" +
                "        END AS median_numerator_3b\n" +
                "    FROM ranked_data_3b\n" +
                "    LIMIT 1\n" +
                "),\n" +
                "final_data_3b AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) AS numerator_3b,\n" +
                "           (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100) / \n" +
                "           SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter3b\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY <= '" + month_date + "'\n" +
                "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                ")\n" +
                "SELECT  \n" +
                "       m3a.ZONE_NAME, m3a.ZONE_CODE, \n" +
                "       m3a.score_of_subpara3a, m3a.numerator_3a, m3a.median_numerator_3a,\n" +
                "       f3b.score_of_parameter3b, f3b.numerator_3b, md3b.median_numerator_3b,\n" +
                "       ROW_NUMBER() OVER (ORDER BY m3a.ZONE_CODE) AS z_rank\n" +
                "FROM median_3a AS m3a\n" +
                "JOIN final_data_3b AS f3b ON m3a.ZONE_CODE = f3b.ZONE_CODE\n" +
                "CROSS JOIN median_data_3b AS md3b;\n";
        return query_assessment;
    }

    // for 2no url , all india rank will show in this query
    public String QueryForScrutinyAssessmentParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "WITH PreviousMonthData AS (\n" +
                "    SELECT zc.ZONE_NAME AS prev_ZONE_NAME, cc.COMM_NAME AS prev_COMM_NAME, cc.ZONE_CODE AS prev_ZONE_CODE, 14c.CLOSING_NO AS prev_col1 \n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
                "),\n" +
                "Parameter3a AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, \n" +
                "        ((14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61) * 100) / (pm.prev_col1 + 14c.RETURN_SCRUTINY) AS score_of_parameter3a,\n" +
                "        concat((14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61),'/', (pm.prev_col1 + 14c.RETURN_SCRUTINY )) as absvl3a\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    LEFT JOIN PreviousMonthData AS pm ON pm.prev_ZONE_CODE = cc.ZONE_CODE AND pm.prev_COMM_NAME = cc.COMM_NAME\n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "    GROUP BY zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 14c.SCRUTINIZED_DISCRIPANCY_FOUND, 14c.OUTCOME_ASMT_12_ISSUED, 14c.OUTCOME_SECTION_61, 14c.RETURN_SCRUTINY, pm.prev_col1\n" +
                "),\n" +
                "Parameter3b AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.COMM_NAME, zc.ZONE_CODE,\n" +
                "       (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100 / \n" +
                "        SUM(14c.TAX_LIABILITY_DETECTECT)) AS score_of_parameter3b ,\n" +
                "        concat(SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY),'/',SUM(14c.TAX_LIABILITY_DETECTECT)) as absvl3b\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY <= '" + month_date + "'\n" +
                "    GROUP BY zc.ZONE_NAME, cc.COMM_NAME, zc.ZONE_CODE\n" +
                "),\n" +
                "ComputedScores AS (\n" +
                "    SELECT p3a.ZONE_NAME, p3a.COMM_NAME, p3a.ZONE_CODE, \n" +
                "           COALESCE(p3a.score_of_parameter3a, 0) AS score_of_parameter3a,\n" +
                "           p3a.absvl3a,\n" +
                "           COALESCE(p3b.score_of_parameter3b, 0) AS score_of_parameter3b,\n" +
                "           p3b.absvl3b,\n" +
                "           ((COALESCE(p3a.score_of_parameter3a, 0) * 0.5 * 10) + (COALESCE(p3b.score_of_parameter3b, 0) * 0.5 * 10)) / 10 AS total_score\n" +
                "    FROM Parameter3a AS p3a\n" +
                "    JOIN Parameter3b AS p3b ON p3a.ZONE_CODE = p3b.ZONE_CODE AND p3a.COMM_NAME = p3b.COMM_NAME\n" +
                "),\n" +
                "RankedScores AS (\n" +
                "    SELECT ZONE_NAME, COMM_NAME, ZONE_CODE, \n" +
                "           score_of_parameter3a, absvl3a, score_of_parameter3b, absvl3b, total_score,\n" +
                "           ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank\n" +
                "    FROM ComputedScores\n" +
                ")\n" +
                "SELECT z_rank, ZONE_NAME, COMM_NAME, ZONE_CODE, \n" +
                "       score_of_parameter3a, absvl3a, score_of_parameter3b, absvl3b, total_score\n" +
                "FROM RankedScores\n" +
                "WHERE ZONE_CODE = '" + zone_code + "'\n" +
                "ORDER BY total_score DESC;\n";
        return query_assessment;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryForScrutinyAssessment_3_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "SELECT\n" +
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
        return query_assessment;
    }
    //  for all commissary wise || for 4no url
    public String QueryForScrutinyAssessment_3_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "WITH PreviousMonthData AS (\n" +
                "    SELECT zc.ZONE_NAME AS prev_ZONE_NAME, cc.COMM_NAME AS prev_COMM_NAME, cc.ZONE_CODE AS prev_ZONE_CODE, 14c.CLOSING_NO AS prev_col1 \n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
                "),\n" +
                "Parameter3a AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, \n" +
                "        ((14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61) * 100) / (pm.prev_col1 + 14c.RETURN_SCRUTINY) AS score_of_parameter3a,\n" +
                "        concat((14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61),'/', (pm.prev_col1 + 14c.RETURN_SCRUTINY )) as absvl3a\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    LEFT JOIN PreviousMonthData AS pm ON pm.prev_ZONE_CODE = cc.ZONE_CODE AND pm.prev_COMM_NAME = cc.COMM_NAME\n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "    GROUP BY zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 14c.SCRUTINIZED_DISCRIPANCY_FOUND, 14c.OUTCOME_ASMT_12_ISSUED, 14c.OUTCOME_SECTION_61, 14c.RETURN_SCRUTINY, pm.prev_col1\n" +
                "),\n" +
                "Parameter3b AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.COMM_NAME, zc.ZONE_CODE,\n" +
                "       (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100 / \n" +
                "        SUM(14c.TAX_LIABILITY_DETECTECT)) AS score_of_parameter3b ,\n" +
                "        concat(SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY),'/',SUM(14c.TAX_LIABILITY_DETECTECT)) as absvl3b\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY <= '" + month_date + "'\n" +
                "    GROUP BY zc.ZONE_NAME, cc.COMM_NAME, zc.ZONE_CODE\n" +
                "),\n" +
                "ComputedScores AS (\n" +
                "    SELECT p3a.ZONE_NAME, p3a.COMM_NAME, p3a.ZONE_CODE, \n" +
                "           COALESCE(p3a.score_of_parameter3a, 0) AS score_of_parameter3a,\n" +
                "           p3a.absvl3a,\n" +
                "           COALESCE(p3b.score_of_parameter3b, 0) AS score_of_parameter3b,\n" +
                "           p3b.absvl3b,\n" +
                "           ((COALESCE(p3a.score_of_parameter3a, 0) * 0.5 * 10) + (COALESCE(p3b.score_of_parameter3b, 0) * 0.5 * 10)) / 10 AS total_score\n" +
                "    FROM Parameter3a AS p3a\n" +
                "    JOIN Parameter3b AS p3b ON p3a.ZONE_CODE = p3b.ZONE_CODE AND p3a.COMM_NAME = p3b.COMM_NAME\n" +
                ")\n" +
                "SELECT ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank, \n" +
                "       ZONE_NAME, COMM_NAME, ZONE_CODE, \n" +
                "       score_of_parameter3a, absvl3a, score_of_parameter3b, absvl3b, total_score," +
                "\"Number of Returns whose scrutiny completed for the month vis-à-vis total Returns pending for the month (Pro-rata basis) || Recoveries made upto the month vis-a-vis detections upto the month\" as ra\n" +
                "FROM ComputedScores\n" +
                "ORDER BY total_score DESC;\n";
        return query_assessment;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryForScrutinyAssessment_3_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);

        String query_assessment = "WITH PreviousMonthData AS (\n" +
                "    SELECT zc.ZONE_NAME AS prev_ZONE_NAME, cc.COMM_NAME AS prev_COMM_NAME, cc.ZONE_CODE AS prev_ZONE_CODE,  \n" +
                "        14c.CLOSING_NO AS prev_col1 \n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' and zc.ZONE_CODE = '" + zone_code + "' and cc.COMM_NAME = '" + come_name + "'\n" +
                ")\n" +
                "SELECT zc.ZONE_NAME,cc.COMM_NAME,cc.ZONE_CODE, \n" +
                "    COALESCE((14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61) / (pm.prev_col1 + 14c.RETURN_SCRUTINY), \n" +
                "        0\n" +
                "    ) AS score_of_parameter,\n" +
                "    concat(\n" +
                "        (14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61),\n" +
                "        '/',\n" +
                "        (pm.prev_col1 + 14c.RETURN_SCRUTINY)\n" +
                "    ) AS absvl, \n" +
                "    \"GST3A\" AS gst, \n" +
                "    \"Number of Returns whose scrutiny completed for the month vis-à-vis total Returns pending for the month (Pro-rata basis)\" AS ra\n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "LEFT JOIN PreviousMonthData AS pm ON pm.prev_ZONE_CODE = cc.ZONE_CODE AND pm.prev_COMM_NAME = cc.COMM_NAME\n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE = '" + zone_code + "' AND cc.COMM_NAME = '" + come_name + "'\n" +
                "GROUP BY zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, \n" +
                "14c.SCRUTINIZED_DISCRIPANCY_FOUND, 14c.OUTCOME_ASMT_12_ISSUED, 14c.OUTCOME_SECTION_61, 14c.RETURN_SCRUTINY, pm.prev_col1\n" +
                "\n" +
                "UNION ALL\n" +
                "\n" +
                "SELECT zc.ZONE_NAME,cc.COMM_NAME,zc.ZONE_CODE,\n" +
                "    COALESCE(\n" +
                "        SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) / SUM(14c.TAX_LIABILITY_DETECTECT), \n" +
                "        0\n" +
                "    ) AS score_of_parameter, \n" +
                "    concat(\n" +
                "        SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY),\n" +
                "        '/',\n" +
                "        SUM(14c.TAX_LIABILITY_DETECTECT)\n" +
                "    ) AS absvl,\n" +
                "    \"GST3B\" AS gst, \n" +
                "    \"Recoveries made upto the month vis-a-vis detections upto the month\" AS ra\n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY <= '" + month_date + "' AND zc.ZONE_CODE = '" + zone_code + "' AND cc.COMM_NAME = '" + come_name + "'\n" +
                "GROUP BY zc.ZONE_NAME, cc.COMM_NAME, zc.ZONE_CODE;\n";
        return query_assessment;
    }
}
