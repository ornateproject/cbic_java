package com.ornates.cbic.dao.Query;

import com.ornates.cbic.service.DateCalculate;
import org.springframework.web.bind.annotation.RequestParam;

public class CGSTParameterWiseQuery {
    // ***********************************GST 1 parameter wise *****************************************************************

    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_Registration_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }

    // for 2no url , all india rank will show in this query
    public String QueryFor_Registration_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_Registration_1_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_Registration_1_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_Registration_1_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);

        String query_assessment = "";
        return query_assessment;
    }
    // ***********************************GST 2 parameter wise *****************************************************************

    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_ReturnFilling_2_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }

    // for 2no url , all india rank will show in this query
    public String QueryFor_ReturnFilling_2_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_ReturnFilling_2_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_ReturnFilling_2_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_ReturnFilling_2_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);

        String query_assessment = "";
        return query_assessment;
    }

    // ***********************************GST 3 parameter wise *****************************************************************

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
                "    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
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
                "    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
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
        String query_assessment ="WITH PreviousMonthData AS (\n" +
                "    SELECT zc.ZONE_NAME AS prev_ZONE_NAME, cc.COMM_NAME AS prev_COMM_NAME, cc.ZONE_CODE AS prev_ZONE_CODE, 14c.CLOSING_NO AS prev_col1 \n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' -- Use appropriate value for prev_month_new\n" +
                "),\n" +
                "Parameter3a AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, \n" +
                "        ((14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61) * 100) / \n" +
                "        (pm.prev_col1 + 14c.RETURN_SCRUTINY) AS score_of_parameter3a,\n" +
                "        CONCAT((14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61),'/', (pm.prev_col1 + 14c.RETURN_SCRUTINY)) AS absvl3a\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    LEFT JOIN PreviousMonthData AS pm ON pm.prev_ZONE_CODE = cc.ZONE_CODE AND pm.prev_COMM_NAME = cc.COMM_NAME\n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' -- Use appropriate value for month_date\n" +
                "    GROUP BY zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 14c.SCRUTINIZED_DISCRIPANCY_FOUND, 14c.OUTCOME_ASMT_12_ISSUED, 14c.OUTCOME_SECTION_61, 14c.RETURN_SCRUTINY, pm.prev_col1\n" +
                "),\n" +
                "Parameter3b AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.COMM_NAME, zc.ZONE_CODE,\n" +
                "       (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100 / \n" +
                "        SUM(14c.TAX_LIABILITY_DETECTECT)) AS score_of_parameter3b,\n" +
                "        CONCAT(SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY),'/',SUM(14c.TAX_LIABILITY_DETECTECT)) AS absvl3b\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY <= '" + month_date + "' -- Use appropriate value for month_date\n" +
                "    GROUP BY zc.ZONE_NAME, cc.COMM_NAME, zc.ZONE_CODE\n" +
                "),\n" +
                "ComputedScores AS (\n" +
                "    SELECT p3a.ZONE_NAME, p3a.COMM_NAME, p3a.ZONE_CODE, \n" +
                "           COALESCE(p3a.score_of_parameter3a, 0) AS score_of_parameter3a,\n" +
                "           p3a.absvl3a,\n" +
                "           COALESCE(p3b.score_of_parameter3b, 0) AS score_of_parameter3b,\n" +
                "           p3b.absvl3b,\n" +
                "           ((COALESCE(p3a.score_of_parameter3a, 0) * 0.5 * 10) + (COALESCE(p3b.score_of_parameter3b, 0) * 0.5 * 10)) / 10 AS total_score,\n" +
                "           -- Extract the numerator from absvl3a\n" +
                "           CAST(SUBSTRING_INDEX(p3a.absvl3a, '/', 1) AS DECIMAL(10, 2)) AS numerator_3a,\n" +
                "           -- Extract the numerator from absvl3b\n" +
                "           CAST(SUBSTRING_INDEX(p3b.absvl3b, '/', 1) AS DECIMAL(10, 2)) AS numerator_3b\n" +
                "    FROM Parameter3a AS p3a\n" +
                "    JOIN Parameter3b AS p3b ON p3a.ZONE_CODE = p3b.ZONE_CODE AND p3a.COMM_NAME = p3b.COMM_NAME\n" +
                "),\n" +
                "RankedScores AS (\n" +
                "    SELECT *,\n" +
                "           ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank\n" +
                "    FROM ComputedScores\n" +
                "),\n" +
                "MedianCalculation AS (\n" +
                "    SELECT AVG(mid_value) AS median_numerator_3a\n" +
                "    FROM (\n" +
                "        SELECT numerator_3a AS mid_value,\n" +
                "               ROW_NUMBER() OVER (ORDER BY numerator_3a) AS rn_asc,\n" +
                "               ROW_NUMBER() OVER (ORDER BY numerator_3a DESC) AS rn_desc\n" +
                "        FROM ComputedScores\n" +
                "    ) AS ranked\n" +
                "    WHERE rn_asc = rn_desc\n" +
                "       OR rn_asc + 1 = rn_desc\n" +
                "       OR rn_asc = rn_desc + 1\n" +
                "),\n" +
                "MedianCalculation_3b AS (\n" +
                "    SELECT AVG(mid_value) AS median_numerator_3b\n" +
                "    FROM (\n" +
                "        SELECT numerator_3b AS mid_value,\n" +
                "               ROW_NUMBER() OVER (ORDER BY numerator_3b) AS rn_asc,\n" +
                "               ROW_NUMBER() OVER (ORDER BY numerator_3b DESC) AS rn_desc\n" +
                "        FROM ComputedScores\n" +
                "    ) AS ranked\n" +
                "    WHERE rn_asc = rn_desc\n" +
                "       OR rn_asc + 1 = rn_desc\n" +
                "       OR rn_asc = rn_desc + 1\n" +
                ")\n" +
                "SELECT rs.z_rank, \n" +
                "       rs.ZONE_NAME, rs.COMM_NAME, rs.ZONE_CODE, \n" +
                "       rs.score_of_parameter3a, rs.absvl3a, rs.score_of_parameter3b, rs.absvl3b, rs.total_score,\n" +
                "       rs.numerator_3a, rs.numerator_3b,\n" +
                "       mc.median_numerator_3a,\n" +
                "       mc3b.median_numerator_3b\n" +
                "FROM RankedScores AS rs\n" +
                "CROSS JOIN MedianCalculation AS mc\n" +
                "CROSS JOIN MedianCalculation_3b AS mc3b\n" +
                "WHERE rs.ZONE_CODE = '" + zone_code + "' -- Filtering condition\n" +
                "ORDER BY rs.total_score DESC;\n";



//        String query_assessment = "WITH PreviousMonthData AS (\n" +
//                "    SELECT zc.ZONE_NAME AS prev_ZONE_NAME, cc.COMM_NAME AS prev_COMM_NAME, cc.ZONE_CODE AS prev_ZONE_CODE, 14c.CLOSING_NO AS prev_col1 \n" +
//                "    FROM mis_gst_commcode AS cc \n" +
//                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
//                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
//                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
//                "),\n" +
//                "Parameter3a AS (\n" +
//                "    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, \n" +
//                "        ((14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61) * 100) / (pm.prev_col1 + 14c.RETURN_SCRUTINY) AS score_of_parameter3a,\n" +
//                "        concat((14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61),'/', (pm.prev_col1 + 14c.RETURN_SCRUTINY )) as absvl3a\n" +
//                "    FROM mis_gst_commcode AS cc \n" +
//                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
//                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
//                "    LEFT JOIN PreviousMonthData AS pm ON pm.prev_ZONE_CODE = cc.ZONE_CODE AND pm.prev_COMM_NAME = cc.COMM_NAME\n" +
//                "    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
//                "    GROUP BY zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 14c.SCRUTINIZED_DISCRIPANCY_FOUND, 14c.OUTCOME_ASMT_12_ISSUED, 14c.OUTCOME_SECTION_61, 14c.RETURN_SCRUTINY, pm.prev_col1\n" +
//                "),\n" +
//                "Parameter3b AS (\n" +
//                "    SELECT zc.ZONE_NAME, cc.COMM_NAME, zc.ZONE_CODE,\n" +
//                "       (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100 / \n" +
//                "        SUM(14c.TAX_LIABILITY_DETECTECT)) AS score_of_parameter3b ,\n" +
//                "        concat(SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY),'/',SUM(14c.TAX_LIABILITY_DETECTECT)) as absvl3b\n" +
//                "    FROM mis_gst_commcode AS cc \n" +
//                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
//                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
//                "    WHERE 14c.MM_YYYY <= '" + month_date + "'\n" +
//                "    GROUP BY zc.ZONE_NAME, cc.COMM_NAME, zc.ZONE_CODE\n" +
//                "),\n" +
//                "ComputedScores AS (\n" +
//                "    SELECT p3a.ZONE_NAME, p3a.COMM_NAME, p3a.ZONE_CODE, \n" +
//                "           COALESCE(p3a.score_of_parameter3a, 0) AS score_of_parameter3a,\n" +
//                "           p3a.absvl3a,\n" +
//                "           COALESCE(p3b.score_of_parameter3b, 0) AS score_of_parameter3b,\n" +
//                "           p3b.absvl3b,\n" +
//                "           ((COALESCE(p3a.score_of_parameter3a, 0) * 0.5 * 10) + (COALESCE(p3b.score_of_parameter3b, 0) * 0.5 * 10)) / 10 AS total_score\n" +
//                "    FROM Parameter3a AS p3a\n" +
//                "    JOIN Parameter3b AS p3b ON p3a.ZONE_CODE = p3b.ZONE_CODE AND p3a.COMM_NAME = p3b.COMM_NAME\n" +
//                "),\n" +
//                "RankedScores AS (\n" +
//                "    SELECT ZONE_NAME, COMM_NAME, ZONE_CODE, \n" +
//                "           score_of_parameter3a, absvl3a, score_of_parameter3b, absvl3b, total_score,\n" +
//                "           ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank\n" +
//                "    FROM ComputedScores\n" +
//                ")\n" +
//                "SELECT z_rank, ZONE_NAME, COMM_NAME, ZONE_CODE, \n" +
//                "       score_of_parameter3a, absvl3a, score_of_parameter3b, absvl3b, total_score\n" +
//                "FROM RankedScores\n" +
//                "WHERE ZONE_CODE = '" + zone_code + "'\n" +
//                "ORDER BY total_score DESC;\n";
        return query_assessment;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryForScrutinyAssessment_3_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "WITH ranked_data_gst3a AS (\n" +
                "    SELECT current_data.ZONE_NAME, current_data.ZONE_CODE, current_data.col4, \n" +
                "           current_data.col9, current_data.col10, current_data.col2, previous_data.col1,\n" +
                "           ((current_data.col4 + current_data.col9 + current_data.col10) * 100) / (current_data.col2 + previous_data.col1) AS score_of_parameter,\n" +
                "           (current_data.col4 + current_data.col9 + current_data.col10) AS numerator_gst3a\n" +
                "    FROM  \n" +
                "        (\n" +
                "            SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4, \n" +
                "                   SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9, SUM(14c.OUTCOME_SECTION_61) AS col10, \n" +
                "                   SUM(14c.RETURN_SCRUTINY) AS col2  \n" +
                "            FROM mis_gst_commcode AS cc  \n" +
                "            RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "            WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
                "            GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "        ) AS current_data\n" +
                "    JOIN \n" +
                "        (\n" +
                "            SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
                "                   SUM(14c.CLOSING_NO) AS col1 \n" +
                "            FROM mis_gst_commcode AS cc \n" +
                "            RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "            WHERE 14c.MM_YYYY = '" + prev_month_new + "' \n" +
                "            GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "        ) AS previous_data\n" +
                "    ON current_data.ZONE_CODE = previous_data.ZONE_CODE\n" +
                "),\n" +
                "numerator_data_gst3a AS (\n" +
                "    SELECT ZONE_NAME, ZONE_CODE, col4, col9, col10, col2, col1, score_of_parameter, numerator_gst3a,\n" +
                "           (col4 + col9 + col10) AS numerator\n" +
                "    FROM ranked_data_gst3a\n" +
                "),\n" +
                "ranked_numerator_gst3a AS (\n" +
                "    SELECT *, ROW_NUMBER() OVER (ORDER BY numerator) AS rn,\n" +
                "              COUNT(*) OVER () AS cnt\n" +
                "    FROM numerator_data_gst3a\n" +
                "),\n" +
                "median_data_gst3b AS (\n" +
                "    SELECT \n" +
                "        SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) AS numerator,\n" +
                "        ROW_NUMBER() OVER (ORDER BY SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY)) AS row_num,\n" +
                "        COUNT(*) OVER () AS total_count\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
                "    GROUP BY cc.ZONE_CODE\n" +
                "),\n" +
                "median_numerator_gst3b AS (\n" +
                "    SELECT \n" +
                "        CASE \n" +
                "            WHEN total_count % 2 = 1 THEN \n" +
                "                (SELECT numerator FROM median_data_gst3b WHERE row_num = (total_count + 1) / 2)\n" +
                "            ELSE \n" +
                "                (SELECT AVG(numerator) FROM median_data_gst3b WHERE row_num IN ((total_count / 2), (total_count / 2) + 1))\n" +
                "        END AS median_numerator\n" +
                "    FROM median_data_gst3b\n" +
                "    LIMIT 1\n" +
                "),\n" +
                "gst3b_data AS (\n" +
                "    SELECT \n" +
                "        zc.ZONE_NAME, cc.ZONE_CODE, \n" +
                "        SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) AS numerator,\n" +
                "        (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100) / SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter,\n" +
                "        CONCAT(SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY), ' / ', SUM(14c.TAX_LIABILITY_DETECTECT)) AS absval\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
                "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                ")\n" +
                "\n" +
                "SELECT \n" +
                "    ZONE_NAME, ZONE_CODE, score_of_parameter, numerator,\n" +
                "    CONCAT(col4 + col9 + col10, '/', col2 + col1) AS absval,\n" +
                "    CASE\n" +
                "       WHEN cnt % 2 = 1 THEN (SELECT numerator FROM ranked_numerator_gst3a WHERE rn = (cnt + 1) / 2)\n" +
                "       ELSE (SELECT AVG(numerator) FROM ranked_numerator_gst3a WHERE rn IN (cnt / 2, cnt / 2 + 1))\n" +
                "    END AS median_numerator,\n" +
                "    'GST3A' AS gst\n" +
                "FROM ranked_numerator_gst3a\n" +
                "WHERE ZONE_CODE = '" + zone_code + "'\n" +
                "\n" +
                "UNION ALL\n" +
                "\n" +
                "SELECT \n" +
                "    t.ZONE_NAME, t.ZONE_CODE, t.score_of_parameter, t.numerator AS numerator_gst3b,\n" +
                "    t.absval, m.median_numerator, 'GST3B' AS gst\n" +
                "FROM gst3b_data t\n" +
                "CROSS JOIN median_numerator_gst3b m\n" +
                "WHERE t.ZONE_CODE = '" + zone_code + "';\n";
        return query_assessment;
    }
    //  for all commissary wise || for 4no url
    public String QueryForScrutinyAssessment_3_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment ="WITH PreviousMonthData AS (\n" +
                "    SELECT zc.ZONE_NAME AS prev_ZONE_NAME, cc.COMM_NAME AS prev_COMM_NAME, cc.ZONE_CODE AS prev_ZONE_CODE, 14c.CLOSING_NO AS prev_col1 \n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' -- Use appropriate value for prev_month_new\n" +
                "),\n" +
                "Parameter3a AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, \n" +
                "        ((14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61) * 100) / \n" +
                "        (pm.prev_col1 + 14c.RETURN_SCRUTINY) AS score_of_parameter3a,\n" +
                "        CONCAT((14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61),'/', (pm.prev_col1 + 14c.RETURN_SCRUTINY)) AS absvl3a\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    LEFT JOIN PreviousMonthData AS pm ON pm.prev_ZONE_CODE = cc.ZONE_CODE AND pm.prev_COMM_NAME = cc.COMM_NAME\n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' -- Use appropriate value for month_date\n" +
                "    GROUP BY zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 14c.SCRUTINIZED_DISCRIPANCY_FOUND, 14c.OUTCOME_ASMT_12_ISSUED, 14c.OUTCOME_SECTION_61, 14c.RETURN_SCRUTINY, pm.prev_col1\n" +
                "),\n" +
                "Parameter3b AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.COMM_NAME, zc.ZONE_CODE,\n" +
                "       (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100 / \n" +
                "        SUM(14c.TAX_LIABILITY_DETECTECT)) AS score_of_parameter3b,\n" +
                "        CONCAT(SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY),'/',SUM(14c.TAX_LIABILITY_DETECTECT)) AS absvl3b\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY <= '" + month_date + "' -- Use appropriate value for month_date\n" +
                "    GROUP BY zc.ZONE_NAME, cc.COMM_NAME, zc.ZONE_CODE\n" +
                "),\n" +
                "ComputedScores AS (\n" +
                "    SELECT p3a.ZONE_NAME, p3a.COMM_NAME, p3a.ZONE_CODE, \n" +
                "           COALESCE(p3a.score_of_parameter3a, 0) AS score_of_parameter3a,\n" +
                "           p3a.absvl3a,\n" +
                "           COALESCE(p3b.score_of_parameter3b, 0) AS score_of_parameter3b,\n" +
                "           p3b.absvl3b,\n" +
                "           ((COALESCE(p3a.score_of_parameter3a, 0) * 0.5 * 10) + (COALESCE(p3b.score_of_parameter3b, 0) * 0.5 * 10)) / 10 AS total_score,\n" +
                "           -- Extract the numerator from absvl3a\n" +
                "           CAST(SUBSTRING_INDEX(p3a.absvl3a, '/', 1) AS DECIMAL(10, 2)) AS numerator_3a,\n" +
                "           -- Extract the numerator from absvl3b\n" +
                "           CAST(SUBSTRING_INDEX(p3b.absvl3b, '/', 1) AS DECIMAL(10, 2)) AS numerator_3b\n" +
                "    FROM Parameter3a AS p3a\n" +
                "    JOIN Parameter3b AS p3b ON p3a.ZONE_CODE = p3b.ZONE_CODE AND p3a.COMM_NAME = p3b.COMM_NAME\n" +
                "),\n" +
                "RankedScores AS (\n" +
                "    SELECT *,\n" +
                "           ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank\n" +
                "    FROM ComputedScores\n" +
                "),\n" +
                "MedianCalculation AS (\n" +
                "    SELECT AVG(mid_value) AS median_numerator_3a\n" +
                "    FROM (\n" +
                "        SELECT numerator_3a AS mid_value,\n" +
                "               ROW_NUMBER() OVER (ORDER BY numerator_3a) AS rn_asc,\n" +
                "               ROW_NUMBER() OVER (ORDER BY numerator_3a DESC) AS rn_desc\n" +
                "        FROM ComputedScores\n" +
                "    ) AS ranked\n" +
                "    WHERE rn_asc = rn_desc\n" +
                "       OR rn_asc + 1 = rn_desc\n" +
                "       OR rn_asc = rn_desc + 1\n" +
                "),\n" +
                "MedianCalculation_3b AS (\n" +
                "    SELECT AVG(mid_value) AS median_numerator_3b\n" +
                "    FROM (\n" +
                "        SELECT numerator_3b AS mid_value,\n" +
                "               ROW_NUMBER() OVER (ORDER BY numerator_3b) AS rn_asc,\n" +
                "               ROW_NUMBER() OVER (ORDER BY numerator_3b DESC) AS rn_desc\n" +
                "        FROM ComputedScores\n" +
                "    ) AS ranked\n" +
                "    WHERE rn_asc = rn_desc\n" +
                "       OR rn_asc + 1 = rn_desc\n" +
                "       OR rn_asc = rn_desc + 1\n" +
                ")\n" +
                "SELECT rs.z_rank, \n" +
                "       rs.ZONE_NAME, rs.COMM_NAME, rs.ZONE_CODE, \n" +
                "       rs.score_of_parameter3a, rs.absvl3a, rs.score_of_parameter3b, rs.absvl3b, rs.total_score,\n" +
                "       rs.numerator_3a, rs.numerator_3b,\n" +
                "       mc.median_numerator_3a,\n" +
                "       mc3b.median_numerator_3b,\n" +
                "       'Number of Returns whose scrutiny completed for the month vis-à-vis total Returns pending for the month (Pro-rata basis) || Recoveries made upto the month vis-a-vis detections upto the month' as ra\n" +
                "FROM RankedScores AS rs\n" +
                "CROSS JOIN MedianCalculation AS mc\n" +
                "CROSS JOIN MedianCalculation_3b AS mc3b\n" +
                "ORDER BY rs.total_score DESC;\n";




//        String query_assessment = "WITH PreviousMonthData AS (\n" +
//                "    SELECT zc.ZONE_NAME AS prev_ZONE_NAME, cc.COMM_NAME AS prev_COMM_NAME, cc.ZONE_CODE AS prev_ZONE_CODE, 14c.CLOSING_NO AS prev_col1 \n" +
//                "    FROM mis_gst_commcode AS cc \n" +
//                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
//                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
//                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
//                "),\n" +
//                "Parameter3a AS (\n" +
//                "    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, \n" +
//                "        ((14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61) * 100) / (pm.prev_col1 + 14c.RETURN_SCRUTINY) AS score_of_parameter3a,\n" +
//                "        concat((14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61),'/', (pm.prev_col1 + 14c.RETURN_SCRUTINY )) as absvl3a\n" +
//                "    FROM mis_gst_commcode AS cc \n" +
//                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
//                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
//                "    LEFT JOIN PreviousMonthData AS pm ON pm.prev_ZONE_CODE = cc.ZONE_CODE AND pm.prev_COMM_NAME = cc.COMM_NAME\n" +
//                "    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
//                "    GROUP BY zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 14c.SCRUTINIZED_DISCRIPANCY_FOUND, 14c.OUTCOME_ASMT_12_ISSUED, 14c.OUTCOME_SECTION_61, 14c.RETURN_SCRUTINY, pm.prev_col1\n" +
//                "),\n" +
//                "Parameter3b AS (\n" +
//                "    SELECT zc.ZONE_NAME, cc.COMM_NAME, zc.ZONE_CODE,\n" +
//                "       (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100 / \n" +
//                "        SUM(14c.TAX_LIABILITY_DETECTECT)) AS score_of_parameter3b ,\n" +
//                "        concat(SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY),'/',SUM(14c.TAX_LIABILITY_DETECTECT)) as absvl3b\n" +
//                "    FROM mis_gst_commcode AS cc \n" +
//                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
//                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
//                "    WHERE 14c.MM_YYYY <= '" + month_date + "'\n" +
//                "    GROUP BY zc.ZONE_NAME, cc.COMM_NAME, zc.ZONE_CODE\n" +
//                "),\n" +
//                "ComputedScores AS (\n" +
//                "    SELECT p3a.ZONE_NAME, p3a.COMM_NAME, p3a.ZONE_CODE, \n" +
//                "           COALESCE(p3a.score_of_parameter3a, 0) AS score_of_parameter3a,\n" +
//                "           p3a.absvl3a,\n" +
//                "           COALESCE(p3b.score_of_parameter3b, 0) AS score_of_parameter3b,\n" +
//                "           p3b.absvl3b,\n" +
//                "           ((COALESCE(p3a.score_of_parameter3a, 0) * 0.5 * 10) + (COALESCE(p3b.score_of_parameter3b, 0) * 0.5 * 10)) / 10 AS total_score\n" +
//                "    FROM Parameter3a AS p3a\n" +
//                "    JOIN Parameter3b AS p3b ON p3a.ZONE_CODE = p3b.ZONE_CODE AND p3a.COMM_NAME = p3b.COMM_NAME\n" +
//                ")\n" +
//                "SELECT ROW_NUMBER() OVER (ORDER BY total_score DESC) AS z_rank, \n" +
//                "       ZONE_NAME, COMM_NAME, ZONE_CODE, \n" +
//                "       score_of_parameter3a, absvl3a, score_of_parameter3b, absvl3b, total_score," +
//                "\"Number of Returns whose scrutiny completed for the month vis-à-vis total Returns pending for the month (Pro-rata basis) || Recoveries made upto the month vis-a-vis detections upto the month\" as ra\n" +
//                "FROM ComputedScores\n" +
//                "ORDER BY total_score DESC;\n";
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

    // ***********************************GST 4 parameter wise *****************************************************************
    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_Investigation_4_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }

    // for 2no url , all india rank will show in this query
    public String QueryFor_Investigation_4_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_Investigation_4_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_Investigation_4_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_Investigation_4_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);

        String query_assessment = "";
        return query_assessment;
    }

    // ***********************************GST 5 parameter wise *****************************************************************

    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_Adjudication_5_ZoneWise(String month_date){
        //String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String next_month_new = DateCalculate.getNextMonth(month_date);
        String query_assessment = "WITH ranked_data AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "        SUM(14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + \n" +
                "            14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
                "            14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no\n" +
                "        ) AS col10,\n" +
                "        SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "        ) AS col4,\n" +
                "        CASE \n" +
                "            WHEN SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "            ) = 0 THEN NULL\n" +
                "            ELSE SUM(14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + \n" +
                "                14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
                "                14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no\n" +
                "            ) / \n" +
                "            SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "            ) * 100\n" +
                "        END AS score_of_subparameter5a\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "),\n" +
                "ranked_col10 AS (\n" +
                "    SELECT col10, @row_num := @row_num + 1 AS row_num, @total_rows AS total_rows\n" +
                "    FROM ranked_data\n" +
                "    CROSS JOIN (SELECT @row_num := 0, @total_rows := (SELECT COUNT(*) FROM ranked_data WHERE col10 IS NOT NULL)) AS vars\n" +
                "    WHERE col10 IS NOT NULL\n" +
                "    ORDER BY col10\n" +
                "),\n" +
                "median_data AS (\n" +
                "    SELECT AVG(col10) AS median_col10\n" +
                "    FROM ranked_col10\n" +
                "    WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                "),\n" +
                "Query1 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "        SUM(14c.ADC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.ADC_AUDIT_TIME_LESS_3_NO + 14c.ADC_INVESTIGATION_TIME_LESS_3_NO + 14c.ADC_CALLBOOK_TIME_LESS_3_NO +\n" +
                "            14c.DC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.DC_AUDIT_TIME_LESS_3_NO + 14c.DC_INVESTIGATION_TIME_LESS_3_NO + 14c.DC_CALLBOOK_TIME_LESS_3_NO +\n" +
                "            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_LESS_3_NO + 14c.SUPERINTENDENT_AUDIT_TIME_LESS_3_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_LESS_3_NO + 14c.SUPERINTENDENT_CALLBOOK_TIME_LESS_3_NO\n" +
                "        ) AS col22,\n" +
                "        SUM(14c.ADC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.ADC_AUDIT_TIME_3_TO_6_NO + 14c.ADC_INVESTIGATION_TIME_3_TO_6_NO + 14c.ADC_CALLBOOK_TIME_3_TO_6_NO +\n" +
                "            14c.DC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.DC_AUDIT_TIME_3_TO_6_NO + 14c.DC_INVESTIGATION_TIME_3_TO_6_NO + 14c.DC_CALLBOOK_TIME_3_TO_6_NO +\n" +
                "            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_AUDIT_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_CALLBOOK_TIME_3_TO_6_NO\n" +
                "        ) AS col23\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "),\n" +
                "Query2 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "        SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "        ) AS col16\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + next_month_new + "'\n" +
                "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "),\n" +
                "RankedData AS (\n" +
                "    SELECT q1.ZONE_NAME, q1.ZONE_CODE,\n" +
                "        COALESCE(q1.col22, 0) AS col22, COALESCE(q1.col23, 0) AS col23, COALESCE(q2.col16, 0) AS col16,\n" +
                "        CASE\n" +
                "            WHEN COALESCE(q2.col16, 0) = 0 THEN 0\n" +
                "            ELSE (((COALESCE(q1.col22, 0) + COALESCE(q1.col23, 0)) * 100) / COALESCE(q2.col16, 0))\n" +
                "        END AS score_of_subparameter5b\n" +
                "    FROM Query1 AS q1\n" +
                "    LEFT JOIN Query2 AS q2 ON q1.ZONE_CODE = q2.ZONE_CODE\n" +
                ")\n" +
                "SELECT rd.ZONE_NAME, rd.ZONE_CODE, rd.col10, rd.col4, rd.score_of_subparameter5a,\n" +
                "       md.median_col10 AS median5a, r.col22, r.col23, r.col16, r.score_of_subparameter5b,\n" +
                "       DENSE_RANK() OVER (PARTITION BY rd.ZONE_CODE ORDER BY r.score_of_subparameter5b ASC) AS z_rank\n" +
                "FROM ranked_data rd\n" +
                "CROSS JOIN median_data md\n" +
                "LEFT JOIN RankedData r ON rd.ZONE_CODE = r.ZONE_CODE;";
        return query_assessment;
    }
    // for 2no url , all india rank will show in this query
    public String QueryFor_Adjudication_5_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        //String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String next_month_new = DateCalculate.getNextMonth(month_date);
        String query_assessment = "WITH total_score1_query AS (\n"
                + "    SELECT zc.ZONE_NAME AS ZONE_NAME,cc.ZONE_CODE AS ZONE_CODE,cc.COMM_NAME AS COMM_NAME,\n"
                + "        (14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + 14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + 14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no) / \n"
                + "        NULLIF((14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + 14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + 14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO), 0) AS total_score1,\n"
                + "        NULL AS total_score2\n"
                + "    FROM mis_gst_commcode AS cc \n"
                + "	RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE  LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "    WHERE 14c.MM_YYYY = '" + month_date + "'\n"
                + "),\n"
                + "total_score2_query AS (\n"
                + "    SELECT ZONE_NAME,ZONE_CODE,COMM_NAME,NULL AS total_score1,(MAX(col22) + MAX(col23)) / MAX(col16) AS total_score2\n"
                + "    FROM (\n"
                + "        SELECT zc.ZONE_NAME AS ZONE_NAME,cc.ZONE_CODE AS ZONE_CODE,cc.COMM_NAME AS COMM_NAME,\n"
                + "            (14c.ADC_COMMISSIONERATE_TIME_LESS_3_NO +14c.ADC_AUDIT_TIME_LESS_3_NO +14c.ADC_INVESTIGATION_TIME_LESS_3_NO +14c.ADC_CALLBOOK_TIME_LESS_3_NO +14c.DC_COMMISSIONERATE_TIME_LESS_3_NO +14c.DC_AUDIT_TIME_LESS_3_NO +14c.DC_INVESTIGATION_TIME_LESS_3_NO +14c.DC_CALLBOOK_TIME_LESS_3_NO +\n"
                + "             14c.SUPERINTENDENT_COMMISSIONERATE_TIME_LESS_3_NO + 14c.SUPERINTENDENT_AUDIT_TIME_LESS_3_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_LESS_3_NO + 14c.SUPERINTENDENT_CALLBOOK_TIME_LESS_3_NO) AS col22,\n"
                + "            (14c.ADC_COMMISSIONERATE_TIME_3_TO_6_NO +14c.ADC_AUDIT_TIME_3_TO_6_NO +14c.ADC_INVESTIGATION_TIME_3_TO_6_NO + 14c.ADC_CALLBOOK_TIME_3_TO_6_NO + 14c.DC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.DC_AUDIT_TIME_3_TO_6_NO +\n"
                + "             14c.DC_INVESTIGATION_TIME_3_TO_6_NO +14c.DC_CALLBOOK_TIME_3_TO_6_NO +14c.SUPERINTENDENT_COMMISSIONERATE_TIME_3_TO_6_NO +14c.SUPERINTENDENT_AUDIT_TIME_3_TO_6_NO +14c.SUPERINTENDENT_INVESTIGATION_TIME_3_TO_6_NO +14c.SUPERINTENDENT_CALLBOOK_TIME_3_TO_6_NO) AS col23,\n"
                + "            (14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO +14c.ADC_INVESTIGATION_OPENING_NO +14c.ADC_CALLBOOK_OPENING_NO + 14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO +\n"
                + "             14c.DC_INVESTIGATION_OPENING_NO +14c.DC_CALLBOOK_OPENING_NO +14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO +14c.SUPERINTENDENT_AUDIT_OPENING_NO +14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO +14c.SUPERINTENDENT_CALLBOOK_OPENING_NO) AS col16\n"
                + "        FROM mis_gst_commcode AS cc\n"
                + "		RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE  LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "        WHERE 14c.MM_YYYY IN ('" + month_date + "', '" + next_month_new + "')\n"
                + "    ) AS merged_data GROUP BY ZONE_NAME, ZONE_CODE, COMM_NAME\n"
                + "),\n"
                + "combined_scores AS (\n"
                + "    SELECT COALESCE(t1.ZONE_NAME, t2.ZONE_NAME) AS ZONE_NAME, COALESCE(t1.ZONE_CODE, t2.ZONE_CODE) AS ZONE_CODE, COALESCE(t1.COMM_NAME, t2.COMM_NAME) AS COMM_NAME,\n"
                + "        t1.total_score1, t2.total_score2,\n"
                + "        COALESCE(t1.total_score1, 0) + COALESCE(t2.total_score2, 0) AS parameter\n"
                + "    FROM total_score1_query t1\n"
                + "    LEFT JOIN total_score2_query t2 ON t1.ZONE_NAME = t2.ZONE_NAME AND t1.ZONE_CODE = t2.ZONE_CODE AND t1.COMM_NAME = t2.COMM_NAME\n"
                + "\n"
                + "    UNION ALL\n"
                + "\n"
                + "    SELECT \n"
                + "        COALESCE(t1.ZONE_NAME, t2.ZONE_NAME) AS ZONE_NAME, COALESCE(t1.ZONE_CODE, t2.ZONE_CODE) AS ZONE_CODE, COALESCE(t1.COMM_NAME, t2.COMM_NAME) AS COMM_NAME,\n"
                + "        t1.total_score1, t2.total_score2,\n"
                + "        COALESCE(t1.total_score1, 0) + COALESCE(t2.total_score2, 0) AS parameter\n"
                + "    FROM total_score2_query t2\n"
                + "    LEFT JOIN total_score1_query t1 ON t1.ZONE_NAME = t2.ZONE_NAME AND t1.ZONE_CODE = t2.ZONE_CODE AND t1.COMM_NAME = t2.COMM_NAME\n"
                + "    WHERE t1.ZONE_NAME IS NULL\n"
                + "),\n"
                + "ranked_scores AS (\n"
                + "    SELECT ZONE_NAME, ZONE_CODE, COMM_NAME, total_score1, total_score2, parameter,\n"
                + "	ROW_NUMBER() OVER (ORDER BY parameter DESC) AS z_rank\n"
                + "    FROM combined_scores\n"
                + ")\n"
                + "SELECT ZONE_NAME, ZONE_CODE, COMM_NAME, total_score1, total_score2, parameter, z_rank\n"
                + "FROM ranked_scores WHERE ZONE_CODE = '" + zone_code + "';\n"
                + "";
        return query_assessment;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_Adjudication_5_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	'" + next_month_new + "'      '" + prev_month_new + "'	    '" + zone_code + "'		'" + come_name + "'
        //String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String next_month_new = DateCalculate.getNextMonth(month_date);
        String query_assessment = "WITH ranked_data AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "        SUM(14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + \n" +
                "            14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
                "            14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no\n" +
                "        ) AS col10,\n" +
                "        SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "        ) AS col4,\n" +
                "        CASE \n" +
                "            WHEN SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "            ) = 0 THEN NULL\n" +
                "            ELSE SUM(14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + \n" +
                "                14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
                "                14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no\n" +
                "            ) / \n" +
                "            SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "            ) * 100\n" +
                "        END AS total_score\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "),\n" +
                "ranked_col10 AS (\n" +
                "    SELECT col10, @row_num := @row_num + 1 AS row_num, @total_rows AS total_rows\n" +
                "    FROM ranked_data\n" +
                "    CROSS JOIN (SELECT @row_num := 0, @total_rows := (SELECT COUNT(*) FROM ranked_data WHERE col10 IS NOT NULL)) AS vars\n" +
                "    WHERE col10 IS NOT NULL\n" +
                "    ORDER BY col10\n" +
                "),\n" +
                "median_data AS (\n" +
                "    SELECT AVG(col10) AS median_col10\n" +
                "    FROM ranked_col10\n" +
                "    WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                "),\n" +
                "query1 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "        SUM(14c.ADC_COMMISSIONERATE_TIME_LESS_3_NO +14c.ADC_AUDIT_TIME_LESS_3_NO +14c.ADC_INVESTIGATION_TIME_LESS_3_NO +14c.ADC_CALLBOOK_TIME_LESS_3_NO +\n" +
                "            14c.DC_COMMISSIONERATE_TIME_LESS_3_NO +14c.DC_AUDIT_TIME_LESS_3_NO +14c.DC_INVESTIGATION_TIME_LESS_3_NO +14c.DC_CALLBOOK_TIME_LESS_3_NO +\n" +
                "            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_LESS_3_NO +14c.SUPERINTENDENT_AUDIT_TIME_LESS_3_NO +14c.SUPERINTENDENT_INVESTIGATION_TIME_LESS_3_NO +\n" +
                "            14c.SUPERINTENDENT_CALLBOOK_TIME_LESS_3_NO) AS col22,\n" +
                "        SUM(14c.ADC_COMMISSIONERATE_TIME_3_TO_6_NO +14c.ADC_AUDIT_TIME_3_TO_6_NO +14c.ADC_INVESTIGATION_TIME_3_TO_6_NO +14c.ADC_CALLBOOK_TIME_3_TO_6_NO +\n" +
                "            14c.DC_COMMISSIONERATE_TIME_3_TO_6_NO +14c.DC_AUDIT_TIME_3_TO_6_NO +14c.DC_INVESTIGATION_TIME_3_TO_6_NO +14c.DC_CALLBOOK_TIME_3_TO_6_NO +\n" +
                "            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_3_TO_6_NO +14c.SUPERINTENDENT_AUDIT_TIME_3_TO_6_NO +14c.SUPERINTENDENT_INVESTIGATION_TIME_3_TO_6_NO +\n" +
                "            14c.SUPERINTENDENT_CALLBOOK_TIME_3_TO_6_NO) AS col23\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "),\n" +
                "query2 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "        SUM(14c.ADC_COMMISSIONERATE_OPENING_NO +14c.ADC_AUDIT_OPENING_NO +14c.ADC_INVESTIGATION_OPENING_NO +14c.ADC_CALLBOOK_OPENING_NO +14c.DC_COMMISSIONERATE_OPENING_NO +\n" +
                "            14c.DC_AUDIT_OPENING_NO +14c.DC_INVESTIGATION_OPENING_NO +14c.DC_CALLBOOK_OPENING_NO +14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO +\n" +
                "            14c.SUPERINTENDENT_AUDIT_OPENING_NO +14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO +14c.SUPERINTENDENT_CALLBOOK_OPENING_NO) AS col16\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + next_month_new + "'\n" +
                "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "),\n" +
                "ranked_data_2 AS (\n" +
                "    SELECT q1.ZONE_NAME, q1.ZONE_CODE,\n" +
                "        COALESCE(q1.col22, 0) AS col22,COALESCE(q1.col23, 0) AS col23,COALESCE(q2.col16, 0) AS col16,\n" +
                "        CASE\n" +
                "            WHEN COALESCE(q2.col16, 0) = 0 THEN 0\n" +
                "            ELSE ((COALESCE(q1.col22, 0) + COALESCE(q1.col23, 0)) / COALESCE(q2.col16, 0)) * 100\n" +
                "        END AS total_score,\n" +
                "        0 AS numerator,0 AS median,\n" +
                "        CASE\n" +
                "            WHEN COALESCE(q2.col16, 0) = 0 THEN '0'\n" +
                "            ELSE CONCAT(COALESCE(q1.col22, 0) + COALESCE(q1.col23, 0), '/', COALESCE(q2.col16, 0))\n" +
                "        END AS absvl\n" +
                "    FROM query1 AS q1\n" +
                "    LEFT JOIN query2 AS q2 ON q1.ZONE_CODE = q2.ZONE_CODE\n" +
                ")\n" +
                "SELECT rd.ZONE_NAME, rd.ZONE_CODE, rd.total_score,rd.col10 AS numerator,md.median_col10 AS median,CONCAT(rd.col10, '/', rd.col4) AS absvl, 'GST5A' AS gst\n" +
                "FROM ranked_data rd\n" +
                "CROSS JOIN median_data md \n" +
                "WHERE rd.ZONE_CODE = '" + zone_code + "'\n" +
                "UNION ALL\n" +
                "SELECT ZONE_NAME,ZONE_CODE, total_score,numerator,median,absvl,'GST5B' AS gst\n" +
                "FROM ranked_data_2\n" +
                "WHERE ZONE_CODE = '" + zone_code + "';\n";
        return query_assessment;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_Adjudication_5_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
       // String prev_month_new = DateCalculate.getPreviousMonth(month_date);
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
        return query_assessment;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_Adjudication_5_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
       // String prev_month_new = DateCalculate.getPreviousMonth(month_date);
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
                "    'GST 5B' as gst, 'Number of cases disposed of during the month vis-à-vis total pending cases at the beginning of the month' as ra\n" +
                "FROM mis_gst_commcode as cc \n" +
                "RIGHT JOIN mis_dpm_gst_adj_1 as 14c \n" +
                "ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode as zc \n" +
                "ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "' and cc.ZONE_CODE='" + zone_code + "' and cc.COMM_NAME= '" + come_name + "';\n";
        return query_assessment;
    }

    // ***********************************GST 6 parameter wise *****************************************************************

    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_Adjudication_Legacy_6_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }

    // for 2no url , all india rank will show in this query
    public String QueryFor_Adjudication_Legacy_6_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_Adjudication_Legacy_6_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_Adjudication_Legacy_6_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_Adjudication_Legacy_6_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);

        String query_assessment = "";
        return query_assessment;
    }

    // ***********************************GST 7 parameter wise *****************************************************************

    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_Refunds_7_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }

    // for 2no url , all india rank will show in this query
    public String QueryFor_Refunds_7_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_Refunds_7_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_Refunds_7_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_Refunds_7_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);

        String query_assessment = "";
        return query_assessment;
    }

    // ***********************************GST 8 parameter wise *****************************************************************

    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_Recovery_Of_Arrears_8_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }

    // for 2no url , all india rank will show in this query
    public String QueryFor_Recovery_Of_Arrears_8_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_Recovery_Of_Arrears_8_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_Recovery_Of_Arrears_8_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_Recovery_Of_Arrears_8_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);

        String query_assessment = "";
        return query_assessment;
    }

    // ***********************************GST 9 parameter wise *****************************************************************

    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_Arrests_And_Prosecution_9_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }

    // for 2no url , all india rank will show in this query
    public String QueryFor_Arrests_And_Prosecution_9_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_Arrests_And_Prosecution_9_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_Arrests_And_Prosecution_9_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_Arrests_And_Prosecution_9_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);

        String query_assessment = "";
        return query_assessment;
    }

    // ***********************************GST 10 parameter wise *****************************************************************

    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_Audit_10_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }

    // for 2no url , all india rank will show in this query
    public String QueryFor_Audit_10_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_Audit_10_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_Audit_10_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_Audit_10_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);

        String query_assessment = "";
        return query_assessment;
    }

    // ***********************************GST 11 parameter wise *****************************************************************

    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_Appeals_11_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }

    // for 2no url , all india rank will show in this query
    public String QueryFor_Appeals_11_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_Appeals_11_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_Appeals_11_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment = "";
        return query_assessment;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_Appeals_11_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);

        String query_assessment = "";
        return query_assessment;
    }

}
