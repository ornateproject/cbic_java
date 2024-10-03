package com.ornates.cbic.dao.Query;

import com.ornates.cbic.service.DateCalculate;

public class MISQuery {
    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Registration=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    public String QueryFor_Registration_CurrentMonth(String month_date, String zone_code){
// '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter1="";
        return queryGstParameter1;
    }
    public String QueryFor_Registration_1_MonthBack(String month_date, String zone_code){
// '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter1="";
        return queryGstParameter1;
    }
    public String QueryFor_Registration_2_MonthBack(String month_date, String zone_code){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter1 ="";
        return queryGstParameter1;
    }
    public String QueryFor_Registration_3_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter1 ="";
        return queryGstParameter1;
    }
    public String QueryFor_Registration_4_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter1 ="";
        return queryGstParameter1;
    }
    public String QueryFor_Registration_5_MonthBack(String month_date, String zone_code){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter1 ="";
        return queryGstParameter1;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=ReturnFiling=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    public String QueryFor_ReturnFiling_CurrentMonth(String month_date, String zone_code){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter2 ="WITH score_calculation AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, SUM(14c.GSTR_3BM_F) AS col3,\n" +
                "(SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / NULLIF(SUM(14c.GSTR_3BM_F), 0)) * 100 AS total_score\n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "' and cc.ZONE_CODE = '" + zone_code + "' GROUP BY zc.ZONE_NAME,cc.ZONE_CODE\n" +
                ")\n" +
                "SELECT DENSE_RANK() OVER (ORDER BY total_score ASC) AS z_rank,\n" +
                "ZONE_NAME, ZONE_CODE, col21, col3,total_score\n" +
                "FROM score_calculation\n" +
                "ORDER BY total_score ASC;";
        return queryGstParameter2;
    }
    public String QueryFor_SReturnFiling_1_MonthBack(String month_date, String zone_code){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter2 ="WITH score_calculation AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, SUM(14c.GSTR_3BM_F) AS col3,\n" +
                "(SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / NULLIF(SUM(14c.GSTR_3BM_F), 0)) * 100 AS total_score\n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_1 + "' and cc.ZONE_CODE = '" + zone_code + "' GROUP BY zc.ZONE_NAME,cc.ZONE_CODE\n" +
                ")\n" +
                "SELECT DENSE_RANK() OVER (ORDER BY total_score ASC) AS z_rank,\n" +
                "ZONE_NAME, ZONE_CODE, col21, col3,total_score\n" +
                "FROM score_calculation\n" +
                "ORDER BY total_score ASC;";
        return queryGstParameter2;
    }
    public String QueryFor_ReturnFiling_2_MonthBack(String month_date, String zone_code){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter2 ="WITH score_calculation AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, SUM(14c.GSTR_3BM_F) AS col3,\n" +
                "(SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / NULLIF(SUM(14c.GSTR_3BM_F), 0)) * 100 AS total_score\n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_2 + "' and cc.ZONE_CODE = '" + zone_code + "' GROUP BY zc.ZONE_NAME,cc.ZONE_CODE\n" +
                ")\n" +
                "SELECT DENSE_RANK() OVER (ORDER BY total_score ASC) AS z_rank,\n" +
                "ZONE_NAME, ZONE_CODE, col21, col3,total_score\n" +
                "FROM score_calculation\n" +
                "ORDER BY total_score ASC;";
        return queryGstParameter2;
    }
    public String QueryFor_ReturnFiling_3_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter2 ="WITH score_calculation AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, SUM(14c.GSTR_3BM_F) AS col3,\n" +
                "(SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / NULLIF(SUM(14c.GSTR_3BM_F), 0)) * 100 AS total_score\n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_3 + "' and cc.ZONE_CODE = '" + zone_code + "' GROUP BY zc.ZONE_NAME,cc.ZONE_CODE\n" +
                ")\n" +
                "SELECT DENSE_RANK() OVER (ORDER BY total_score ASC) AS z_rank,\n" +
                "ZONE_NAME, ZONE_CODE, col21, col3,total_score\n" +
                "FROM score_calculation\n" +
                "ORDER BY total_score ASC;";
        return queryGstParameter2;
    }
    public String QueryFor_ReturnFiling_4_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter2 ="WITH score_calculation AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, SUM(14c.GSTR_3BM_F) AS col3,\n" +
                "(SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / NULLIF(SUM(14c.GSTR_3BM_F), 0)) * 100 AS total_score\n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_4 + "' and cc.ZONE_CODE = '" + zone_code + "' GROUP BY zc.ZONE_NAME,cc.ZONE_CODE\n" +
                ")\n" +
                "SELECT DENSE_RANK() OVER (ORDER BY total_score ASC) AS z_rank,\n" +
                "ZONE_NAME, ZONE_CODE, col21, col3,total_score\n" +
                "FROM score_calculation\n" +
                "ORDER BY total_score ASC;";
        return queryGstParameter2;
    }
    public String QueryFor_ReturnFiling_5_MonthBack(String month_date, String zone_code){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter2 ="WITH score_calculation AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, SUM(14c.GSTR_3BM_F) AS col3,\n" +
                "(SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / NULLIF(SUM(14c.GSTR_3BM_F), 0)) * 100 AS total_score\n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_5 + "' and cc.ZONE_CODE = '" + zone_code + "' GROUP BY zc.ZONE_NAME,cc.ZONE_CODE\n" +
                ")\n" +
                "SELECT DENSE_RANK() OVER (ORDER BY total_score ASC) AS z_rank,\n" +
                "ZONE_NAME, ZONE_CODE, col21, col3,total_score\n" +
                "FROM score_calculation\n" +
                "ORDER BY total_score ASC;";
        return queryGstParameter2;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Scrutiny=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    public String QueryFor_Scrutiny_CurrentMonth(String month_date, String zone_code){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter3="WITH ranked_data_3a AS (\n" +
                "    SELECT current_data.ZONE_NAME, current_data.ZONE_CODE, current_data.col4, current_data.col9, current_data.col10, current_data.col2, previous_data.col1,\n" +
                "           (current_data.col4 + current_data.col9 + current_data.col10) / (current_data.col2 + previous_data.col1) * 100 AS score_of_subpara3a\n" +
                "    FROM  \n" +
                "        (SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4, SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9, SUM(14c.OUTCOME_SECTION_61) AS col10, SUM(14c.RETURN_SCRUTINY) AS col2  \n" +
                "            FROM mis_gst_commcode AS cc  \n" +
                "            RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "            WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "        ) AS current_data\n" +
                "    JOIN \n" +
                "        (SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col1 \n" +
                "            FROM mis_gst_commcode AS cc \n" +
                "            RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "            WHERE 14c.MM_YYYY = '" + MonthBack_1 + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "        ) AS previous_data ON current_data.ZONE_CODE = previous_data.ZONE_CODE\n" +
                "),\n" +
                "numerator_data_3a AS (\n" +
                "    SELECT ZONE_NAME, ZONE_CODE, col4, col9, col10, col2, col1, score_of_subpara3a, (col4 + col9 + col10) AS numerator_3a\n" +
                "    FROM ranked_data_3a\n" +
                "),\n" +
                "ranked_numerator_3a AS (\n" +
                "    SELECT *, ROW_NUMBER() OVER (ORDER BY numerator_3a) AS rn,COUNT(*) OVER () AS cnt\n" +
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
                "    SELECT cc.ZONE_CODE,SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) AS numerator_3b,\n" +
                "        ROW_NUMBER() OVER (ORDER BY SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY)) AS row_num,\n" +
                "        COUNT(*) OVER () AS total_count\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE\n" +
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
                "           (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100) / SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter3b\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                ")\n" +
                "SELECT  \n" +
                "       m3a.ZONE_NAME, m3a.ZONE_CODE, m3a.score_of_subpara3a, m3a.numerator_3a, m3a.median_numerator_3a,f3b.score_of_parameter3b, f3b.numerator_3b, md3b.median_numerator_3b\n" +
                "FROM median_3a AS m3a\n" +
                "JOIN final_data_3b AS f3b ON m3a.ZONE_CODE = f3b.ZONE_CODE\n" +
                "CROSS JOIN median_data_3b AS md3b WHERE m3a.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter3;
    }
    public String QueryFor_Scrutiny_1_MonthBack(String month_date, String zone_code){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter3="WITH ranked_data_3a AS (\n" +
                "    SELECT current_data.ZONE_NAME, current_data.ZONE_CODE, current_data.col4, current_data.col9, current_data.col10, current_data.col2, previous_data.col1,\n" +
                "           (current_data.col4 + current_data.col9 + current_data.col10) / (current_data.col2 + previous_data.col1) * 100 AS score_of_subpara3a\n" +
                "    FROM  \n" +
                "        (SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4, SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9, SUM(14c.OUTCOME_SECTION_61) AS col10, SUM(14c.RETURN_SCRUTINY) AS col2  \n" +
                "            FROM mis_gst_commcode AS cc  \n" +
                "            RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "            WHERE 14c.MM_YYYY = '" + MonthBack_1 + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "        ) AS current_data\n" +
                "    JOIN \n" +
                "        (SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col1 \n" +
                "            FROM mis_gst_commcode AS cc \n" +
                "            RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "            WHERE 14c.MM_YYYY = '" + MonthBack_2 + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "        ) AS previous_data ON current_data.ZONE_CODE = previous_data.ZONE_CODE\n" +
                "),\n" +
                "numerator_data_3a AS (\n" +
                "    SELECT ZONE_NAME, ZONE_CODE, col4, col9, col10, col2, col1, score_of_subpara3a, (col4 + col9 + col10) AS numerator_3a\n" +
                "    FROM ranked_data_3a\n" +
                "),\n" +
                "ranked_numerator_3a AS (\n" +
                "    SELECT *, ROW_NUMBER() OVER (ORDER BY numerator_3a) AS rn,COUNT(*) OVER () AS cnt\n" +
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
                "    SELECT cc.ZONE_CODE,SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) AS numerator_3b,\n" +
                "        ROW_NUMBER() OVER (ORDER BY SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY)) AS row_num,\n" +
                "        COUNT(*) OVER () AS total_count\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + MonthBack_1 + "' GROUP BY cc.ZONE_CODE\n" +
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
                "           (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100) / SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter3b\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + MonthBack_1 + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                ")\n" +
                "SELECT  \n" +
                "       m3a.ZONE_NAME, m3a.ZONE_CODE, m3a.score_of_subpara3a, m3a.numerator_3a, m3a.median_numerator_3a,f3b.score_of_parameter3b, f3b.numerator_3b, md3b.median_numerator_3b\n" +
                "FROM median_3a AS m3a\n" +
                "JOIN final_data_3b AS f3b ON m3a.ZONE_CODE = f3b.ZONE_CODE\n" +
                "CROSS JOIN median_data_3b AS md3b WHERE m3a.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter3;
    }
    public String QueryFor_Scrutiny_2_MonthBack(String month_date, String zone_code){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month

        String queryGstParameter3="WITH ranked_data_3a AS (\n" +
                "    SELECT current_data.ZONE_NAME, current_data.ZONE_CODE, current_data.col4, current_data.col9, current_data.col10, current_data.col2, previous_data.col1,\n" +
                "           (current_data.col4 + current_data.col9 + current_data.col10) / (current_data.col2 + previous_data.col1) * 100 AS score_of_subpara3a\n" +
                "    FROM  \n" +
                "        (SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4, SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9, SUM(14c.OUTCOME_SECTION_61) AS col10, SUM(14c.RETURN_SCRUTINY) AS col2  \n" +
                "            FROM mis_gst_commcode AS cc  \n" +
                "            RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "            WHERE 14c.MM_YYYY = '" + MonthBack_2 + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "        ) AS current_data\n" +
                "    JOIN \n" +
                "        (SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col1 \n" +
                "            FROM mis_gst_commcode AS cc \n" +
                "            RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "            WHERE 14c.MM_YYYY = '" + MonthBack_3 + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "        ) AS previous_data ON current_data.ZONE_CODE = previous_data.ZONE_CODE\n" +
                "),\n" +
                "numerator_data_3a AS (\n" +
                "    SELECT ZONE_NAME, ZONE_CODE, col4, col9, col10, col2, col1, score_of_subpara3a, (col4 + col9 + col10) AS numerator_3a\n" +
                "    FROM ranked_data_3a\n" +
                "),\n" +
                "ranked_numerator_3a AS (\n" +
                "    SELECT *, ROW_NUMBER() OVER (ORDER BY numerator_3a) AS rn,COUNT(*) OVER () AS cnt\n" +
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
                "    SELECT cc.ZONE_CODE,SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) AS numerator_3b,\n" +
                "        ROW_NUMBER() OVER (ORDER BY SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY)) AS row_num,\n" +
                "        COUNT(*) OVER () AS total_count\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + MonthBack_2 + "' GROUP BY cc.ZONE_CODE\n" +
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
                "           (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100) / SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter3b\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + MonthBack_2 + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                ")\n" +
                "SELECT  \n" +
                "       m3a.ZONE_NAME, m3a.ZONE_CODE, m3a.score_of_subpara3a, m3a.numerator_3a, m3a.median_numerator_3a,f3b.score_of_parameter3b, f3b.numerator_3b, md3b.median_numerator_3b\n" +
                "FROM median_3a AS m3a\n" +
                "JOIN final_data_3b AS f3b ON m3a.ZONE_CODE = f3b.ZONE_CODE\n" +
                "CROSS JOIN median_data_3b AS md3b WHERE m3a.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter3;
    }
    public String QueryFor_Scrutiny_3_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month

        String queryGstParameter3="WITH ranked_data_3a AS (\n" +
                "    SELECT current_data.ZONE_NAME, current_data.ZONE_CODE, current_data.col4, current_data.col9, current_data.col10, current_data.col2, previous_data.col1,\n" +
                "           (current_data.col4 + current_data.col9 + current_data.col10) / (current_data.col2 + previous_data.col1) * 100 AS score_of_subpara3a\n" +
                "    FROM  \n" +
                "        (SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4, SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9, SUM(14c.OUTCOME_SECTION_61) AS col10, SUM(14c.RETURN_SCRUTINY) AS col2  \n" +
                "            FROM mis_gst_commcode AS cc  \n" +
                "            RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "            WHERE 14c.MM_YYYY = '" + MonthBack_3 + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "        ) AS current_data\n" +
                "    JOIN \n" +
                "        (SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col1 \n" +
                "            FROM mis_gst_commcode AS cc \n" +
                "            RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "            WHERE 14c.MM_YYYY = '" + MonthBack_4 + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "        ) AS previous_data ON current_data.ZONE_CODE = previous_data.ZONE_CODE\n" +
                "),\n" +
                "numerator_data_3a AS (\n" +
                "    SELECT ZONE_NAME, ZONE_CODE, col4, col9, col10, col2, col1, score_of_subpara3a, (col4 + col9 + col10) AS numerator_3a\n" +
                "    FROM ranked_data_3a\n" +
                "),\n" +
                "ranked_numerator_3a AS (\n" +
                "    SELECT *, ROW_NUMBER() OVER (ORDER BY numerator_3a) AS rn,COUNT(*) OVER () AS cnt\n" +
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
                "    SELECT cc.ZONE_CODE,SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) AS numerator_3b,\n" +
                "        ROW_NUMBER() OVER (ORDER BY SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY)) AS row_num,\n" +
                "        COUNT(*) OVER () AS total_count\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + MonthBack_3 + "' GROUP BY cc.ZONE_CODE\n" +
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
                "           (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100) / SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter3b\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + MonthBack_3 + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                ")\n" +
                "SELECT  \n" +
                "       m3a.ZONE_NAME, m3a.ZONE_CODE, m3a.score_of_subpara3a, m3a.numerator_3a, m3a.median_numerator_3a,f3b.score_of_parameter3b, f3b.numerator_3b, md3b.median_numerator_3b\n" +
                "FROM median_3a AS m3a\n" +
                "JOIN final_data_3b AS f3b ON m3a.ZONE_CODE = f3b.ZONE_CODE\n" +
                "CROSS JOIN median_data_3b AS md3b WHERE m3a.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter3;
    }
    public String QueryFor_Scrutiny_4_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month

        String queryGstParameter3="WITH ranked_data_3a AS (\n" +
                "    SELECT current_data.ZONE_NAME, current_data.ZONE_CODE, current_data.col4, current_data.col9, current_data.col10, current_data.col2, previous_data.col1,\n" +
                "           (current_data.col4 + current_data.col9 + current_data.col10) / (current_data.col2 + previous_data.col1) * 100 AS score_of_subpara3a\n" +
                "    FROM  \n" +
                "        (SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4, SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9, SUM(14c.OUTCOME_SECTION_61) AS col10, SUM(14c.RETURN_SCRUTINY) AS col2  \n" +
                "            FROM mis_gst_commcode AS cc  \n" +
                "            RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "            WHERE 14c.MM_YYYY = '" + MonthBack_4 + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "        ) AS current_data\n" +
                "    JOIN \n" +
                "        (SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col1 \n" +
                "            FROM mis_gst_commcode AS cc \n" +
                "            RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "            WHERE 14c.MM_YYYY = '" + MonthBack_5 + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "        ) AS previous_data ON current_data.ZONE_CODE = previous_data.ZONE_CODE\n" +
                "),\n" +
                "numerator_data_3a AS (\n" +
                "    SELECT ZONE_NAME, ZONE_CODE, col4, col9, col10, col2, col1, score_of_subpara3a, (col4 + col9 + col10) AS numerator_3a\n" +
                "    FROM ranked_data_3a\n" +
                "),\n" +
                "ranked_numerator_3a AS (\n" +
                "    SELECT *, ROW_NUMBER() OVER (ORDER BY numerator_3a) AS rn,COUNT(*) OVER () AS cnt\n" +
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
                "    SELECT cc.ZONE_CODE,SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) AS numerator_3b,\n" +
                "        ROW_NUMBER() OVER (ORDER BY SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY)) AS row_num,\n" +
                "        COUNT(*) OVER () AS total_count\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + MonthBack_4 + "' GROUP BY cc.ZONE_CODE\n" +
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
                "           (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100) / SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter3b\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + MonthBack_4 + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                ")\n" +
                "SELECT  \n" +
                "       m3a.ZONE_NAME, m3a.ZONE_CODE, m3a.score_of_subpara3a, m3a.numerator_3a, m3a.median_numerator_3a,f3b.score_of_parameter3b, f3b.numerator_3b, md3b.median_numerator_3b\n" +
                "FROM median_3a AS m3a\n" +
                "JOIN final_data_3b AS f3b ON m3a.ZONE_CODE = f3b.ZONE_CODE\n" +
                "CROSS JOIN median_data_3b AS md3b WHERE m3a.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter3;
    }
    public String QueryFor_Scrutiny_5_MonthBack(String month_date, String zone_code){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month

        String queryGstParameter3="WITH ranked_data_3a AS (\n" +
                "    SELECT current_data.ZONE_NAME, current_data.ZONE_CODE, current_data.col4, current_data.col9, current_data.col10, current_data.col2, previous_data.col1,\n" +
                "           (current_data.col4 + current_data.col9 + current_data.col10) / (current_data.col2 + previous_data.col1) * 100 AS score_of_subpara3a\n" +
                "    FROM  \n" +
                "        (SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4, SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9, SUM(14c.OUTCOME_SECTION_61) AS col10, SUM(14c.RETURN_SCRUTINY) AS col2  \n" +
                "            FROM mis_gst_commcode AS cc  \n" +
                "            RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "            WHERE 14c.MM_YYYY = '" + MonthBack_5 + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "        ) AS current_data\n" +
                "    JOIN \n" +
                "        (SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col1 \n" +
                "            FROM mis_gst_commcode AS cc \n" +
                "            RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "            WHERE 14c.MM_YYYY = '" + MonthBack_6 + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "        ) AS previous_data ON current_data.ZONE_CODE = previous_data.ZONE_CODE\n" +
                "),\n" +
                "numerator_data_3a AS (\n" +
                "    SELECT ZONE_NAME, ZONE_CODE, col4, col9, col10, col2, col1, score_of_subpara3a, (col4 + col9 + col10) AS numerator_3a\n" +
                "    FROM ranked_data_3a\n" +
                "),\n" +
                "ranked_numerator_3a AS (\n" +
                "    SELECT *, ROW_NUMBER() OVER (ORDER BY numerator_3a) AS rn,COUNT(*) OVER () AS cnt\n" +
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
                "    SELECT cc.ZONE_CODE,SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) AS numerator_3b,\n" +
                "        ROW_NUMBER() OVER (ORDER BY SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY)) AS row_num,\n" +
                "        COUNT(*) OVER () AS total_count\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + MonthBack_5 + "' GROUP BY cc.ZONE_CODE\n" +
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
                "           (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100) / SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter3b\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + MonthBack_5 + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                ")\n" +
                "SELECT  \n" +
                "       m3a.ZONE_NAME, m3a.ZONE_CODE, m3a.score_of_subpara3a, m3a.numerator_3a, m3a.median_numerator_3a,f3b.score_of_parameter3b, f3b.numerator_3b, md3b.median_numerator_3b\n" +
                "FROM median_3a AS m3a\n" +
                "JOIN final_data_3b AS f3b ON m3a.ZONE_CODE = f3b.ZONE_CODE\n" +
                "CROSS JOIN median_data_3b AS md3b WHERE m3a.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter3;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Investigation__4__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    public String QueryFor_Investigation_CurrentMonth(String month_date, String zone_code){
// '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter4 ="";
        return queryGstParameter4;
    }
    public String QueryFor_Investigation_1_MonthBack(String month_date, String zone_code){
// '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter4 ="";
        return queryGstParameter4;
    }
    public String QueryFor_Investigation_2_MonthBack(String month_date, String zone_code){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter4 ="";
        return queryGstParameter4;
    }
    public String QueryFor_Investigation_3_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter4 ="";
        return queryGstParameter4;
    }
    public String QueryFor_Investigation_4_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter4 ="";
        return queryGstParameter4;
    }
    public String QueryFor_Investigation_5_MonthBack(String month_date, String zone_code){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter4 ="";
        return queryGstParameter4;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Adjudication__5__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_Adjudication_CurrentMonth(String month_date, String zone_code){
// '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        String next_month_new = DateCalculate.getNextMonth(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter5 ="WITH ranked_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + \n" +
                "                            14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
                "                            14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no\n" +
                "                        ) AS col10,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO +\n" +
                "                            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                        ) AS col4,\n" +
                "                        CASE \n" +
                "                            WHEN SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                                14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO +\n" +
                "                                14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                            ) = 0 THEN NULL\n" +
                "                            ELSE SUM(14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + \n" +
                "                                14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
                "                                14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no\n" +
                "                            ) / \n" +
                "                            SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                                14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                                14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                            ) * 100\n" +
                "                        END AS score_of_subparameter5a\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                ranked_col10 AS (\n" +
                "                    SELECT col10, @row_num := @row_num + 1 AS row_num, @total_rows AS total_rows\n" +
                "                    FROM ranked_data\n" +
                "                    CROSS JOIN (SELECT @row_num := 0, @total_rows := (SELECT COUNT(*) FROM ranked_data WHERE col10 IS NOT NULL)) AS vars\n" +
                "                    WHERE col10 IS NOT NULL\n" +
                "                    ORDER BY col10\n" +
                "                ),\n" +
                "                median_data AS (\n" +
                "                    SELECT AVG(col10) AS median_col10\n" +
                "                    FROM ranked_col10\n" +
                "                    WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                "                ),\n" +
                "                Query1 AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.ADC_AUDIT_TIME_LESS_3_NO + 14c.ADC_INVESTIGATION_TIME_LESS_3_NO + 14c.ADC_CALLBOOK_TIME_LESS_3_NO +\n" +
                "                            14c.DC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.DC_AUDIT_TIME_LESS_3_NO + 14c.DC_INVESTIGATION_TIME_LESS_3_NO + 14c.DC_CALLBOOK_TIME_LESS_3_NO +\n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_LESS_3_NO + 14c.SUPERINTENDENT_AUDIT_TIME_LESS_3_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_LESS_3_NO + 14c.SUPERINTENDENT_CALLBOOK_TIME_LESS_3_NO\n" +
                "                        ) AS col22,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.ADC_AUDIT_TIME_3_TO_6_NO + 14c.ADC_INVESTIGATION_TIME_3_TO_6_NO + 14c.ADC_CALLBOOK_TIME_3_TO_6_NO +\n" +
                "                            14c.DC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.DC_AUDIT_TIME_3_TO_6_NO + 14c.DC_INVESTIGATION_TIME_3_TO_6_NO + 14c.DC_CALLBOOK_TIME_3_TO_6_NO +\n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_AUDIT_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_CALLBOOK_TIME_3_TO_6_NO\n" +
                "                        ) AS col23\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                Query2 AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                        ) AS col16\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + next_month_new + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                RankedData AS (\n" +
                "                    SELECT q1.ZONE_NAME, q1.ZONE_CODE,\n" +
                "                        COALESCE(q1.col22, 0) AS col22, COALESCE(q1.col23, 0) AS col23, COALESCE(q2.col16, 0) AS col16,\n" +
                "                        CASE\n" +
                "                            WHEN COALESCE(q2.col16, 0) = 0 THEN 0\n" +
                "                            ELSE (((COALESCE(q1.col22, 0) + COALESCE(q1.col23, 0)) * 100) / COALESCE(q2.col16, 0))\n" +
                "                        END AS score_of_subparameter5b\n" +
                "                    FROM Query1 AS q1\n" +
                "                    LEFT JOIN Query2 AS q2 ON q1.ZONE_CODE = q2.ZONE_CODE\n" +
                "                )\n" +
                "                SELECT rd.ZONE_NAME, rd.ZONE_CODE, rd.col10, rd.col4, rd.score_of_subparameter5a,\n" +
                "                       md.median_col10 AS median5a, r.col22, r.col23, r.col16, r.score_of_subparameter5b,\n" +
                "                       DENSE_RANK() OVER (PARTITION BY rd.ZONE_CODE ORDER BY r.score_of_subparameter5b ASC) AS z_rank\n" +
                "                FROM ranked_data rd\n" +
                "                CROSS JOIN median_data md\n" +
                "                LEFT JOIN RankedData r ON rd.ZONE_CODE = r.ZONE_CODE\n" +
                "                WHERE rd.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter5;
    }
    public String QueryFor_Adjudication_1_MonthBack(String month_date, String zone_code){
// '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter5 ="WITH ranked_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + \n" +
                "                            14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
                "                            14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no\n" +
                "                        ) AS col10,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO +\n" +
                "                            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                        ) AS col4,\n" +
                "                        CASE \n" +
                "                            WHEN SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                                14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO +\n" +
                "                                14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                            ) = 0 THEN NULL\n" +
                "                            ELSE SUM(14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + \n" +
                "                                14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
                "                                14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no\n" +
                "                            ) / \n" +
                "                            SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                                14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                                14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                            ) * 100\n" +
                "                        END AS score_of_subparameter5a\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_1 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                ranked_col10 AS (\n" +
                "                    SELECT col10, @row_num := @row_num + 1 AS row_num, @total_rows AS total_rows\n" +
                "                    FROM ranked_data\n" +
                "                    CROSS JOIN (SELECT @row_num := 0, @total_rows := (SELECT COUNT(*) FROM ranked_data WHERE col10 IS NOT NULL)) AS vars\n" +
                "                    WHERE col10 IS NOT NULL\n" +
                "                    ORDER BY col10\n" +
                "                ),\n" +
                "                median_data AS (\n" +
                "                    SELECT AVG(col10) AS median_col10\n" +
                "                    FROM ranked_col10\n" +
                "                    WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                "                ),\n" +
                "                Query1 AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.ADC_AUDIT_TIME_LESS_3_NO + 14c.ADC_INVESTIGATION_TIME_LESS_3_NO + 14c.ADC_CALLBOOK_TIME_LESS_3_NO +\n" +
                "                            14c.DC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.DC_AUDIT_TIME_LESS_3_NO + 14c.DC_INVESTIGATION_TIME_LESS_3_NO + 14c.DC_CALLBOOK_TIME_LESS_3_NO +\n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_LESS_3_NO + 14c.SUPERINTENDENT_AUDIT_TIME_LESS_3_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_LESS_3_NO + 14c.SUPERINTENDENT_CALLBOOK_TIME_LESS_3_NO\n" +
                "                        ) AS col22,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.ADC_AUDIT_TIME_3_TO_6_NO + 14c.ADC_INVESTIGATION_TIME_3_TO_6_NO + 14c.ADC_CALLBOOK_TIME_3_TO_6_NO +\n" +
                "                            14c.DC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.DC_AUDIT_TIME_3_TO_6_NO + 14c.DC_INVESTIGATION_TIME_3_TO_6_NO + 14c.DC_CALLBOOK_TIME_3_TO_6_NO +\n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_AUDIT_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_CALLBOOK_TIME_3_TO_6_NO\n" +
                "                        ) AS col23\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_1 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                Query2 AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                        ) AS col16\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                RankedData AS (\n" +
                "                    SELECT q1.ZONE_NAME, q1.ZONE_CODE,\n" +
                "                        COALESCE(q1.col22, 0) AS col22, COALESCE(q1.col23, 0) AS col23, COALESCE(q2.col16, 0) AS col16,\n" +
                "                        CASE\n" +
                "                            WHEN COALESCE(q2.col16, 0) = 0 THEN 0\n" +
                "                            ELSE (((COALESCE(q1.col22, 0) + COALESCE(q1.col23, 0)) * 100) / COALESCE(q2.col16, 0))\n" +
                "                        END AS score_of_subparameter5b\n" +
                "                    FROM Query1 AS q1\n" +
                "                    LEFT JOIN Query2 AS q2 ON q1.ZONE_CODE = q2.ZONE_CODE\n" +
                "                )\n" +
                "                SELECT rd.ZONE_NAME, rd.ZONE_CODE, rd.col10, rd.col4, rd.score_of_subparameter5a,\n" +
                "                       md.median_col10 AS median5a, r.col22, r.col23, r.col16, r.score_of_subparameter5b,\n" +
                "                       DENSE_RANK() OVER (PARTITION BY rd.ZONE_CODE ORDER BY r.score_of_subparameter5b ASC) AS z_rank\n" +
                "                FROM ranked_data rd\n" +
                "                CROSS JOIN median_data md\n" +
                "                LEFT JOIN RankedData r ON rd.ZONE_CODE = r.ZONE_CODE\n" +
                "                WHERE rd.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter5;
    }
    public String QueryFor_Adjudication_2_MonthBack(String month_date, String zone_code){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is next month for this query
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter5 ="WITH ranked_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + \n" +
                "                            14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
                "                            14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no\n" +
                "                        ) AS col10,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO +\n" +
                "                            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                        ) AS col4,\n" +
                "                        CASE \n" +
                "                            WHEN SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                                14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO +\n" +
                "                                14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                            ) = 0 THEN NULL\n" +
                "                            ELSE SUM(14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + \n" +
                "                                14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
                "                                14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no\n" +
                "                            ) / \n" +
                "                            SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                                14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                                14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                            ) * 100\n" +
                "                        END AS score_of_subparameter5a\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_2 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                ranked_col10 AS (\n" +
                "                    SELECT col10, @row_num := @row_num + 1 AS row_num, @total_rows AS total_rows\n" +
                "                    FROM ranked_data\n" +
                "                    CROSS JOIN (SELECT @row_num := 0, @total_rows := (SELECT COUNT(*) FROM ranked_data WHERE col10 IS NOT NULL)) AS vars\n" +
                "                    WHERE col10 IS NOT NULL\n" +
                "                    ORDER BY col10\n" +
                "                ),\n" +
                "                median_data AS (\n" +
                "                    SELECT AVG(col10) AS median_col10\n" +
                "                    FROM ranked_col10\n" +
                "                    WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                "                ),\n" +
                "                Query1 AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.ADC_AUDIT_TIME_LESS_3_NO + 14c.ADC_INVESTIGATION_TIME_LESS_3_NO + 14c.ADC_CALLBOOK_TIME_LESS_3_NO +\n" +
                "                            14c.DC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.DC_AUDIT_TIME_LESS_3_NO + 14c.DC_INVESTIGATION_TIME_LESS_3_NO + 14c.DC_CALLBOOK_TIME_LESS_3_NO +\n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_LESS_3_NO + 14c.SUPERINTENDENT_AUDIT_TIME_LESS_3_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_LESS_3_NO + 14c.SUPERINTENDENT_CALLBOOK_TIME_LESS_3_NO\n" +
                "                        ) AS col22,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.ADC_AUDIT_TIME_3_TO_6_NO + 14c.ADC_INVESTIGATION_TIME_3_TO_6_NO + 14c.ADC_CALLBOOK_TIME_3_TO_6_NO +\n" +
                "                            14c.DC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.DC_AUDIT_TIME_3_TO_6_NO + 14c.DC_INVESTIGATION_TIME_3_TO_6_NO + 14c.DC_CALLBOOK_TIME_3_TO_6_NO +\n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_AUDIT_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_CALLBOOK_TIME_3_TO_6_NO\n" +
                "                        ) AS col23\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_2 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                Query2 AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                        ) AS col16\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_1 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                RankedData AS (\n" +
                "                    SELECT q1.ZONE_NAME, q1.ZONE_CODE,\n" +
                "                        COALESCE(q1.col22, 0) AS col22, COALESCE(q1.col23, 0) AS col23, COALESCE(q2.col16, 0) AS col16,\n" +
                "                        CASE\n" +
                "                            WHEN COALESCE(q2.col16, 0) = 0 THEN 0\n" +
                "                            ELSE (((COALESCE(q1.col22, 0) + COALESCE(q1.col23, 0)) * 100) / COALESCE(q2.col16, 0))\n" +
                "                        END AS score_of_subparameter5b\n" +
                "                    FROM Query1 AS q1\n" +
                "                    LEFT JOIN Query2 AS q2 ON q1.ZONE_CODE = q2.ZONE_CODE\n" +
                "                )\n" +
                "                SELECT rd.ZONE_NAME, rd.ZONE_CODE, rd.col10, rd.col4, rd.score_of_subparameter5a,\n" +
                "                       md.median_col10 AS median5a, r.col22, r.col23, r.col16, r.score_of_subparameter5b,\n" +
                "                       DENSE_RANK() OVER (PARTITION BY rd.ZONE_CODE ORDER BY r.score_of_subparameter5b ASC) AS z_rank\n" +
                "                FROM ranked_data rd\n" +
                "                CROSS JOIN median_data md\n" +
                "                LEFT JOIN RankedData r ON rd.ZONE_CODE = r.ZONE_CODE\n" +
                "                WHERE rd.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter5;
    }
    public String QueryFor_Adjudication_3_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is next month for this query
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter5 ="WITH ranked_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + \n" +
                "                            14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
                "                            14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no\n" +
                "                        ) AS col10,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO +\n" +
                "                            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                        ) AS col4,\n" +
                "                        CASE \n" +
                "                            WHEN SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                                14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO +\n" +
                "                                14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                            ) = 0 THEN NULL\n" +
                "                            ELSE SUM(14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + \n" +
                "                                14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
                "                                14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no\n" +
                "                            ) / \n" +
                "                            SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                                14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                                14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                            ) * 100\n" +
                "                        END AS score_of_subparameter5a\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_3 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                ranked_col10 AS (\n" +
                "                    SELECT col10, @row_num := @row_num + 1 AS row_num, @total_rows AS total_rows\n" +
                "                    FROM ranked_data\n" +
                "                    CROSS JOIN (SELECT @row_num := 0, @total_rows := (SELECT COUNT(*) FROM ranked_data WHERE col10 IS NOT NULL)) AS vars\n" +
                "                    WHERE col10 IS NOT NULL\n" +
                "                    ORDER BY col10\n" +
                "                ),\n" +
                "                median_data AS (\n" +
                "                    SELECT AVG(col10) AS median_col10\n" +
                "                    FROM ranked_col10\n" +
                "                    WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                "                ),\n" +
                "                Query1 AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.ADC_AUDIT_TIME_LESS_3_NO + 14c.ADC_INVESTIGATION_TIME_LESS_3_NO + 14c.ADC_CALLBOOK_TIME_LESS_3_NO +\n" +
                "                            14c.DC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.DC_AUDIT_TIME_LESS_3_NO + 14c.DC_INVESTIGATION_TIME_LESS_3_NO + 14c.DC_CALLBOOK_TIME_LESS_3_NO +\n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_LESS_3_NO + 14c.SUPERINTENDENT_AUDIT_TIME_LESS_3_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_LESS_3_NO + 14c.SUPERINTENDENT_CALLBOOK_TIME_LESS_3_NO\n" +
                "                        ) AS col22,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.ADC_AUDIT_TIME_3_TO_6_NO + 14c.ADC_INVESTIGATION_TIME_3_TO_6_NO + 14c.ADC_CALLBOOK_TIME_3_TO_6_NO +\n" +
                "                            14c.DC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.DC_AUDIT_TIME_3_TO_6_NO + 14c.DC_INVESTIGATION_TIME_3_TO_6_NO + 14c.DC_CALLBOOK_TIME_3_TO_6_NO +\n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_AUDIT_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_CALLBOOK_TIME_3_TO_6_NO\n" +
                "                        ) AS col23\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_3 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                Query2 AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                        ) AS col16\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_2 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                RankedData AS (\n" +
                "                    SELECT q1.ZONE_NAME, q1.ZONE_CODE,\n" +
                "                        COALESCE(q1.col22, 0) AS col22, COALESCE(q1.col23, 0) AS col23, COALESCE(q2.col16, 0) AS col16,\n" +
                "                        CASE\n" +
                "                            WHEN COALESCE(q2.col16, 0) = 0 THEN 0\n" +
                "                            ELSE (((COALESCE(q1.col22, 0) + COALESCE(q1.col23, 0)) * 100) / COALESCE(q2.col16, 0))\n" +
                "                        END AS score_of_subparameter5b\n" +
                "                    FROM Query1 AS q1\n" +
                "                    LEFT JOIN Query2 AS q2 ON q1.ZONE_CODE = q2.ZONE_CODE\n" +
                "                )\n" +
                "                SELECT rd.ZONE_NAME, rd.ZONE_CODE, rd.col10, rd.col4, rd.score_of_subparameter5a,\n" +
                "                       md.median_col10 AS median5a, r.col22, r.col23, r.col16, r.score_of_subparameter5b,\n" +
                "                       DENSE_RANK() OVER (PARTITION BY rd.ZONE_CODE ORDER BY r.score_of_subparameter5b ASC) AS z_rank\n" +
                "                FROM ranked_data rd\n" +
                "                CROSS JOIN median_data md\n" +
                "                LEFT JOIN RankedData r ON rd.ZONE_CODE = r.ZONE_CODE\n" +
                "                WHERE rd.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter5;
    }
    public String QueryFor_Adjudication_4_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is mext month for this query
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter5 ="WITH ranked_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + \n" +
                "                            14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
                "                            14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no\n" +
                "                        ) AS col10,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO +\n" +
                "                            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                        ) AS col4,\n" +
                "                        CASE \n" +
                "                            WHEN SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                                14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO +\n" +
                "                                14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                            ) = 0 THEN NULL\n" +
                "                            ELSE SUM(14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + \n" +
                "                                14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
                "                                14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no\n" +
                "                            ) / \n" +
                "                            SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                                14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                                14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                            ) * 100\n" +
                "                        END AS score_of_subparameter5a\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_4 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                ranked_col10 AS (\n" +
                "                    SELECT col10, @row_num := @row_num + 1 AS row_num, @total_rows AS total_rows\n" +
                "                    FROM ranked_data\n" +
                "                    CROSS JOIN (SELECT @row_num := 0, @total_rows := (SELECT COUNT(*) FROM ranked_data WHERE col10 IS NOT NULL)) AS vars\n" +
                "                    WHERE col10 IS NOT NULL\n" +
                "                    ORDER BY col10\n" +
                "                ),\n" +
                "                median_data AS (\n" +
                "                    SELECT AVG(col10) AS median_col10\n" +
                "                    FROM ranked_col10\n" +
                "                    WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                "                ),\n" +
                "                Query1 AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.ADC_AUDIT_TIME_LESS_3_NO + 14c.ADC_INVESTIGATION_TIME_LESS_3_NO + 14c.ADC_CALLBOOK_TIME_LESS_3_NO +\n" +
                "                            14c.DC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.DC_AUDIT_TIME_LESS_3_NO + 14c.DC_INVESTIGATION_TIME_LESS_3_NO + 14c.DC_CALLBOOK_TIME_LESS_3_NO +\n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_LESS_3_NO + 14c.SUPERINTENDENT_AUDIT_TIME_LESS_3_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_LESS_3_NO + 14c.SUPERINTENDENT_CALLBOOK_TIME_LESS_3_NO\n" +
                "                        ) AS col22,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.ADC_AUDIT_TIME_3_TO_6_NO + 14c.ADC_INVESTIGATION_TIME_3_TO_6_NO + 14c.ADC_CALLBOOK_TIME_3_TO_6_NO +\n" +
                "                            14c.DC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.DC_AUDIT_TIME_3_TO_6_NO + 14c.DC_INVESTIGATION_TIME_3_TO_6_NO + 14c.DC_CALLBOOK_TIME_3_TO_6_NO +\n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_AUDIT_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_CALLBOOK_TIME_3_TO_6_NO\n" +
                "                        ) AS col23\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_4 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                Query2 AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                        ) AS col16\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_3 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                RankedData AS (\n" +
                "                    SELECT q1.ZONE_NAME, q1.ZONE_CODE,\n" +
                "                        COALESCE(q1.col22, 0) AS col22, COALESCE(q1.col23, 0) AS col23, COALESCE(q2.col16, 0) AS col16,\n" +
                "                        CASE\n" +
                "                            WHEN COALESCE(q2.col16, 0) = 0 THEN 0\n" +
                "                            ELSE (((COALESCE(q1.col22, 0) + COALESCE(q1.col23, 0)) * 100) / COALESCE(q2.col16, 0))\n" +
                "                        END AS score_of_subparameter5b\n" +
                "                    FROM Query1 AS q1\n" +
                "                    LEFT JOIN Query2 AS q2 ON q1.ZONE_CODE = q2.ZONE_CODE\n" +
                "                )\n" +
                "                SELECT rd.ZONE_NAME, rd.ZONE_CODE, rd.col10, rd.col4, rd.score_of_subparameter5a,\n" +
                "                       md.median_col10 AS median5a, r.col22, r.col23, r.col16, r.score_of_subparameter5b,\n" +
                "                       DENSE_RANK() OVER (PARTITION BY rd.ZONE_CODE ORDER BY r.score_of_subparameter5b ASC) AS z_rank\n" +
                "                FROM ranked_data rd\n" +
                "                CROSS JOIN median_data md\n" +
                "                LEFT JOIN RankedData r ON rd.ZONE_CODE = r.ZONE_CODE\n" +
                "                WHERE rd.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter5;
    }
    public String QueryFor_Adjudication_5_MonthBack(String month_date, String zone_code){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is mext month for this query
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter5 ="WITH ranked_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + \n" +
                "                            14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
                "                            14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no\n" +
                "                        ) AS col10,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO +\n" +
                "                            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                        ) AS col4,\n" +
                "                        CASE \n" +
                "                            WHEN SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                                14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO +\n" +
                "                                14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                            ) = 0 THEN NULL\n" +
                "                            ELSE SUM(14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + \n" +
                "                                14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
                "                                14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no\n" +
                "                            ) / \n" +
                "                            SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                                14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                                14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                            ) * 100\n" +
                "                        END AS score_of_subparameter5a\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_5 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                ranked_col10 AS (\n" +
                "                    SELECT col10, @row_num := @row_num + 1 AS row_num, @total_rows AS total_rows\n" +
                "                    FROM ranked_data\n" +
                "                    CROSS JOIN (SELECT @row_num := 0, @total_rows := (SELECT COUNT(*) FROM ranked_data WHERE col10 IS NOT NULL)) AS vars\n" +
                "                    WHERE col10 IS NOT NULL\n" +
                "                    ORDER BY col10\n" +
                "                ),\n" +
                "                median_data AS (\n" +
                "                    SELECT AVG(col10) AS median_col10\n" +
                "                    FROM ranked_col10\n" +
                "                    WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                "                ),\n" +
                "                Query1 AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.ADC_AUDIT_TIME_LESS_3_NO + 14c.ADC_INVESTIGATION_TIME_LESS_3_NO + 14c.ADC_CALLBOOK_TIME_LESS_3_NO +\n" +
                "                            14c.DC_COMMISSIONERATE_TIME_LESS_3_NO + 14c.DC_AUDIT_TIME_LESS_3_NO + 14c.DC_INVESTIGATION_TIME_LESS_3_NO + 14c.DC_CALLBOOK_TIME_LESS_3_NO +\n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_LESS_3_NO + 14c.SUPERINTENDENT_AUDIT_TIME_LESS_3_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_LESS_3_NO + 14c.SUPERINTENDENT_CALLBOOK_TIME_LESS_3_NO\n" +
                "                        ) AS col22,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.ADC_AUDIT_TIME_3_TO_6_NO + 14c.ADC_INVESTIGATION_TIME_3_TO_6_NO + 14c.ADC_CALLBOOK_TIME_3_TO_6_NO +\n" +
                "                            14c.DC_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.DC_AUDIT_TIME_3_TO_6_NO + 14c.DC_INVESTIGATION_TIME_3_TO_6_NO + 14c.DC_CALLBOOK_TIME_3_TO_6_NO +\n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_AUDIT_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_INVESTIGATION_TIME_3_TO_6_NO + 14c.SUPERINTENDENT_CALLBOOK_TIME_3_TO_6_NO\n" +
                "                        ) AS col23\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_5 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                Query2 AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + 14c.ADC_CALLBOOK_OPENING_NO + \n" +
                "                            14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + 14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "                            14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + 14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n" +
                "                        ) AS col16\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_4 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                RankedData AS (\n" +
                "                    SELECT q1.ZONE_NAME, q1.ZONE_CODE,\n" +
                "                        COALESCE(q1.col22, 0) AS col22, COALESCE(q1.col23, 0) AS col23, COALESCE(q2.col16, 0) AS col16,\n" +
                "                        CASE\n" +
                "                            WHEN COALESCE(q2.col16, 0) = 0 THEN 0\n" +
                "                            ELSE (((COALESCE(q1.col22, 0) + COALESCE(q1.col23, 0)) * 100) / COALESCE(q2.col16, 0))\n" +
                "                        END AS score_of_subparameter5b\n" +
                "                    FROM Query1 AS q1\n" +
                "                    LEFT JOIN Query2 AS q2 ON q1.ZONE_CODE = q2.ZONE_CODE\n" +
                "                )\n" +
                "                SELECT rd.ZONE_NAME, rd.ZONE_CODE, rd.col10, rd.col4, rd.score_of_subparameter5a,\n" +
                "                       md.median_col10 AS median5a, r.col22, r.col23, r.col16, r.score_of_subparameter5b,\n" +
                "                       DENSE_RANK() OVER (PARTITION BY rd.ZONE_CODE ORDER BY r.score_of_subparameter5b ASC) AS z_rank\n" +
                "                FROM ranked_data rd\n" +
                "                CROSS JOIN median_data md\n" +
                "                LEFT JOIN RankedData r ON rd.ZONE_CODE = r.ZONE_CODE\n" +
                "                WHERE rd.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter5;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Adjudication_Legacy__6__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_Adjudication_Legacy_CurrentMonth(String month_date, String zone_code){
// '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter6 ="";
        return queryGstParameter6;
    }
    public String QueryFor_Adjudication_Legacy_1_MonthBack(String month_date, String zone_code){
// '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter6 ="";
        return queryGstParameter6;
    }
    public String QueryFor_Adjudication_Legacy_2_MonthBack(String month_date, String zone_code){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter6 ="";
        return queryGstParameter6;
    }
    public String QueryFor_Adjudication_Legacy_3_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter6 ="";
        return queryGstParameter6;
    }
    public String QueryFor_Adjudication_Legacy_4_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter6 ="";
        return queryGstParameter6;
    }
    public String QueryFor_Adjudication_Legacy_5_MonthBack(String month_date, String zone_code){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter6 ="";
        return queryGstParameter6;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Refunds__7__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_Refunds_CurrentMonth(String month_date, String zone_code){
// '" + month_date + "' 	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter7 ="WITH CTE AS ( SELECT \n" +
                "SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS col16, \n" +
                "SUM(14c.age_breakup_above60_no) AS col22, cc.ZONE_CODE,zc.ZONE_NAME\n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_dpm_gst_4 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                "GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                ")\n" +
                "SELECT ZONE_CODE, ZONE_NAME, col16, col22, (col22 * 100 / col16) AS total_score,\n" +
                "CONCAT(col22, '/', col16) AS absolute_value\n" +
                "FROM CTE ORDER BY total_score ASC;";
        return queryGstParameter7;
    }
    public String QueryFor_Refunds_1_MonthBack(String month_date, String zone_code){
// '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter7 ="WITH CTE AS ( SELECT \n" +
                "SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS col16, \n" +
                "SUM(14c.age_breakup_above60_no) AS col22, cc.ZONE_CODE,zc.ZONE_NAME\n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_dpm_gst_4 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_1 + "' AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                "GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                ")\n" +
                "SELECT ZONE_CODE, ZONE_NAME, col16, col22, (col22 * 100 / col16) AS total_score,\n" +
                "CONCAT(col22, '/', col16) AS absolute_value\n" +
                "FROM CTE ORDER BY total_score ASC;";
        return queryGstParameter7;
    }
    public String QueryFor_Refunds_2_MonthBack(String month_date, String zone_code){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter7 ="WITH CTE AS ( SELECT \n" +
                "SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS col16, \n" +
                "SUM(14c.age_breakup_above60_no) AS col22, cc.ZONE_CODE,zc.ZONE_NAME\n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_dpm_gst_4 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_2 + "' AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                "GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                ")\n" +
                "SELECT ZONE_CODE, ZONE_NAME, col16, col22, (col22 * 100 / col16) AS total_score,\n" +
                "CONCAT(col22, '/', col16) AS absolute_value\n" +
                "FROM CTE ORDER BY total_score ASC;";
        return queryGstParameter7;
    }
    public String QueryFor_Refunds_3_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter7 ="WITH CTE AS ( SELECT \n" +
                "SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS col16, \n" +
                "SUM(14c.age_breakup_above60_no) AS col22, cc.ZONE_CODE,zc.ZONE_NAME\n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_dpm_gst_4 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_3 + "' AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                "GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                ")\n" +
                "SELECT ZONE_CODE, ZONE_NAME, col16, col22, (col22 * 100 / col16) AS total_score,\n" +
                "CONCAT(col22, '/', col16) AS absolute_value\n" +
                "FROM CTE ORDER BY total_score ASC;";
        return queryGstParameter7;
    }
    public String QueryFor_Refunds_4_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter7 ="WITH CTE AS ( SELECT \n" +
                "SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS col16, \n" +
                "SUM(14c.age_breakup_above60_no) AS col22, cc.ZONE_CODE,zc.ZONE_NAME\n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_dpm_gst_4 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_4 + "' AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                "GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                ")\n" +
                "SELECT ZONE_CODE, ZONE_NAME, col16, col22, (col22 * 100 / col16) AS total_score,\n" +
                "CONCAT(col22, '/', col16) AS absolute_value\n" +
                "FROM CTE ORDER BY total_score ASC;";
        return queryGstParameter7;
    }
    public String QueryFor_Refunds_5_MonthBack(String month_date, String zone_code){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter7 ="WITH CTE AS ( SELECT \n" +
                "SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS col16, \n" +
                "SUM(14c.age_breakup_above60_no) AS col22, cc.ZONE_CODE,zc.ZONE_NAME\n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_dpm_gst_4 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_5 + "' AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                "GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                ")\n" +
                "SELECT ZONE_CODE, ZONE_NAME, col16, col22, (col22 * 100 / col16) AS total_score,\n" +
                "CONCAT(col22, '/', col16) AS absolute_value\n" +
                "FROM CTE ORDER BY total_score ASC;";
        return queryGstParameter7;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=RecoveryOfArrears=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_RecoveryOfArrears_CurrentMonth(String month_date, String zone_code){
// '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_RecoveryOfArrears_1_MonthBack(String month_date, String zone_code){
// '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_RecoveryOfArrears_2_MonthBack(String month_date, String zone_code){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_RecoveryOfArrears_3_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_RecoveryOfArrears_4_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_RecoveryOfArrears_5_MonthBack(String month_date, String zone_code){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

}
