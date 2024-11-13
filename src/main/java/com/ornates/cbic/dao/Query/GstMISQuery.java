package com.ornates.cbic.dao.Query;

import com.ornates.cbic.service.DateCalculate;

public class GstMISQuery {

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Registration__1__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

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

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=ReturnFiling__2__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

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
    public String QueryFor_ReturnFiling_1_MonthBack(String month_date, String zone_code){
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

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Scrutiny__3__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

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
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);   // that is previous month for this query
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter6 ="WITH col9_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9_6a\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                col3_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_1 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                ranked_data_6a AS (\n" +
                "                    SELECT col9_data.ZONE_NAME, col9_data.ZONE_CODE, col9_data.col9_6a, col3_data.col3,\n" +
                "                           CASE\n" +
                "                               WHEN col3_data.col3 = 0 THEN 0\n" +
                "                               ELSE (col9_data.col9_6a / col3_data.col3) * 100\n" +
                "                           END AS total_score_6a\n" +
                "                    FROM col9_data\n" +
                "                    LEFT JOIN col3_data ON col9_data.ZONE_CODE = col3_data.ZONE_CODE AND col9_data.ZONE_NAME = col3_data.ZONE_NAME\n" +
                "                ),\n" +
                "                median_calc_6a AS (\n" +
                "                    SELECT col9_6a, ROW_NUMBER() OVER (ORDER BY col9_6a) AS row_num, COUNT(*) OVER () AS total_rows\n" +
                "                    FROM ranked_data_6a\n" +
                "                ),\n" +
                "                median_value_6a AS (\n" +
                "                    SELECT AVG(col9_6a) AS median\n" +
                "                    FROM median_calc_6a\n" +
                "                    WHERE row_num IN (FLOOR((total_rows + 1) / 2), FLOOR((total_rows + 2) / 2))\n" +
                "                ),\n" +
                "                ranked_data_6b AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                           SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) AS col18,\n" +
                "                           SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col13,\n" +
                "                           CASE\n" +
                "                               WHEN SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) = 0 THEN 0\n" +
                "                               ELSE (SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) /\n" +
                "                                    NULLIF(SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO), 0)) * 100\n" +
                "                           END AS total_score_6b\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                disposal_data_6c AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(COALESCE(14c.COMM_DISPOSAL_NO, 0) + COALESCE(14c.JC_DISPOSAL_NO, 0) + COALESCE(14c.AC_DISPOSAL_NO, 0) + COALESCE(14c.SUP_DISPOSAL_NO, 0)) AS numerator_6c,\n" +
                "                           ROW_NUMBER() OVER (ORDER BY SUM(COALESCE(14c.COMM_DISPOSAL_NO, 0) + COALESCE(14c.JC_DISPOSAL_NO, 0) + COALESCE(14c.AC_DISPOSAL_NO, 0) + COALESCE(14c.SUP_DISPOSAL_NO, 0))) AS row_num,\n" +
                "                           COUNT(*) OVER () AS total_count\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "                    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "                ),\n" +
                "                median_data_6c AS (\n" +
                "                    SELECT CASE\n" +
                "                               WHEN total_count % 2 = 1 THEN (SELECT numerator_6c FROM disposal_data_6c WHERE row_num = (total_count + 1) / 2)\n" +
                "                               ELSE (SELECT numerator_6c FROM disposal_data_6c WHERE row_num = total_count / 2)\n" +
                "                           END AS median_numerator_6c\n" +
                "                    FROM disposal_data_6c\n" +
                "                    LIMIT 1\n" +
                "                ),\n" +
                "                closing_data_6c AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(COALESCE(14c.COMM_CLOSING_NO, 0) + COALESCE(14c.JC_CLOSING_NO, 0) + COALESCE(14c.AC_CLOSING_NO, 0) + COALESCE(14c.SUP_CLOSING_NO, 0)) AS col3\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "                    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "                ),\n" +
                "                ranked_data_6c AS (\n" +
                "                    SELECT d.ZONE_NAME, d.ZONE_CODE, d.numerator_6c,\n" +
                "                           COALESCE(c.col3, 0) AS col3,\n" +
                "                           CASE WHEN COALESCE(c.col3, 0) = 0 THEN 0\n" +
                "                                ELSE (d.numerator_6c * 100 / COALESCE(c.col3, 0))\n" +
                "                           END AS score_of_parameter6c\n" +
                "                    FROM disposal_data_6c AS d\n" +
                "                    LEFT JOIN closing_data_6c AS c ON d.ZONE_NAME = c.ZONE_NAME AND d.ZONE_CODE = c.ZONE_CODE\n" +
                "                ),\n" +
                "                ranked_data_6d AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                           COALESCE(\n" +
                "                               (SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) /\n" +
                "                                NULLIF(SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO), 0)) * 100, 0\n" +
                "                           ) AS total_score_6d\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                )\n" +
                "                SELECT r6a.ZONE_NAME, r6a.ZONE_CODE, r6a.col9_6a, r6a.total_score_6a, m6a.median AS median_6a, r6b.total_score_6b, r6c.numerator_6c, r6c.score_of_parameter6c, m6c.median_numerator_6c, r6d.total_score_6d\n" +
                "                FROM ranked_data_6a AS r6a\n" +
                "                CROSS JOIN median_value_6a AS m6a\n" +
                "                LEFT JOIN ranked_data_6b AS r6b ON r6a.ZONE_NAME = r6b.ZONE_NAME AND r6a.ZONE_CODE = r6b.ZONE_CODE\n" +
                "                LEFT JOIN ranked_data_6c AS r6c ON r6a.ZONE_NAME = r6c.ZONE_NAME AND r6a.ZONE_CODE = r6c.ZONE_CODE\n" +
                "                CROSS JOIN median_data_6c AS m6c\n" +
                "                LEFT JOIN ranked_data_6d AS r6d ON r6a.ZONE_NAME = r6d.ZONE_NAME AND r6a.ZONE_CODE = r6d.ZONE_CODE\n" +
                "                WHERE r6a.ZONE_NAME NOT IN ('DG East', 'CEI DG') AND r6a.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter6;
    }
    public String QueryFor_Adjudication_Legacy_1_MonthBack(String month_date, String zone_code){
// '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter6 ="WITH col9_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9_6a\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_1 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                col3_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_2 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                ranked_data_6a AS (\n" +
                "                    SELECT col9_data.ZONE_NAME, col9_data.ZONE_CODE, col9_data.col9_6a, col3_data.col3,\n" +
                "                           CASE\n" +
                "                               WHEN col3_data.col3 = 0 THEN 0\n" +
                "                               ELSE (col9_data.col9_6a / col3_data.col3) * 100\n" +
                "                           END AS total_score_6a\n" +
                "                    FROM col9_data\n" +
                "                    LEFT JOIN col3_data ON col9_data.ZONE_CODE = col3_data.ZONE_CODE AND col9_data.ZONE_NAME = col3_data.ZONE_NAME\n" +
                "                ),\n" +
                "                median_calc_6a AS (\n" +
                "                    SELECT col9_6a, ROW_NUMBER() OVER (ORDER BY col9_6a) AS row_num, COUNT(*) OVER () AS total_rows\n" +
                "                    FROM ranked_data_6a\n" +
                "                ),\n" +
                "                median_value_6a AS (\n" +
                "                    SELECT AVG(col9_6a) AS median\n" +
                "                    FROM median_calc_6a\n" +
                "                    WHERE row_num IN (FLOOR((total_rows + 1) / 2), FLOOR((total_rows + 2) / 2))\n" +
                "                ),\n" +
                "                ranked_data_6b AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                           SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) AS col18,\n" +
                "                           SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col13,\n" +
                "                           CASE\n" +
                "                               WHEN SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) = 0 THEN 0\n" +
                "                               ELSE (SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) /\n" +
                "                                    NULLIF(SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO), 0)) * 100\n" +
                "                           END AS total_score_6b\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_1 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                disposal_data_6c AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(COALESCE(14c.COMM_DISPOSAL_NO, 0) + COALESCE(14c.JC_DISPOSAL_NO, 0) + COALESCE(14c.AC_DISPOSAL_NO, 0) + COALESCE(14c.SUP_DISPOSAL_NO, 0)) AS numerator_6c,\n" +
                "                           ROW_NUMBER() OVER (ORDER BY SUM(COALESCE(14c.COMM_DISPOSAL_NO, 0) + COALESCE(14c.JC_DISPOSAL_NO, 0) + COALESCE(14c.AC_DISPOSAL_NO, 0) + COALESCE(14c.SUP_DISPOSAL_NO, 0))) AS row_num,\n" +
                "                           COUNT(*) OVER () AS total_count\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_1 + "'\n" +
                "                    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "                ),\n" +
                "                median_data_6c AS (\n" +
                "                    SELECT CASE\n" +
                "                               WHEN total_count % 2 = 1 THEN (SELECT numerator_6c FROM disposal_data_6c WHERE row_num = (total_count + 1) / 2)\n" +
                "                               ELSE (SELECT numerator_6c FROM disposal_data_6c WHERE row_num = total_count / 2)\n" +
                "                           END AS median_numerator_6c\n" +
                "                    FROM disposal_data_6c\n" +
                "                    LIMIT 1\n" +
                "                ),\n" +
                "                closing_data_6c AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(COALESCE(14c.COMM_CLOSING_NO, 0) + COALESCE(14c.JC_CLOSING_NO, 0) + COALESCE(14c.AC_CLOSING_NO, 0) + COALESCE(14c.SUP_CLOSING_NO, 0)) AS col3\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_1 + "'\n" +
                "                    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "                ),\n" +
                "                ranked_data_6c AS (\n" +
                "                    SELECT d.ZONE_NAME, d.ZONE_CODE, d.numerator_6c,\n" +
                "                           COALESCE(c.col3, 0) AS col3,\n" +
                "                           CASE WHEN COALESCE(c.col3, 0) = 0 THEN 0\n" +
                "                                ELSE (d.numerator_6c * 100 / COALESCE(c.col3, 0))\n" +
                "                           END AS score_of_parameter6c\n" +
                "                    FROM disposal_data_6c AS d\n" +
                "                    LEFT JOIN closing_data_6c AS c ON d.ZONE_NAME = c.ZONE_NAME AND d.ZONE_CODE = c.ZONE_CODE\n" +
                "                ),\n" +
                "                ranked_data_6d AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                           COALESCE(\n" +
                "                               (SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) /\n" +
                "                                NULLIF(SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO), 0)) * 100, 0\n" +
                "                           ) AS total_score_6d\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_1 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                )\n" +
                "                SELECT r6a.ZONE_NAME, r6a.ZONE_CODE, r6a.col9_6a, r6a.total_score_6a, m6a.median AS median_6a, r6b.total_score_6b, r6c.numerator_6c, r6c.score_of_parameter6c, m6c.median_numerator_6c, r6d.total_score_6d\n" +
                "                FROM ranked_data_6a AS r6a\n" +
                "                CROSS JOIN median_value_6a AS m6a\n" +
                "                LEFT JOIN ranked_data_6b AS r6b ON r6a.ZONE_NAME = r6b.ZONE_NAME AND r6a.ZONE_CODE = r6b.ZONE_CODE\n" +
                "                LEFT JOIN ranked_data_6c AS r6c ON r6a.ZONE_NAME = r6c.ZONE_NAME AND r6a.ZONE_CODE = r6c.ZONE_CODE\n" +
                "                CROSS JOIN median_data_6c AS m6c\n" +
                "                LEFT JOIN ranked_data_6d AS r6d ON r6a.ZONE_NAME = r6d.ZONE_NAME AND r6a.ZONE_CODE = r6d.ZONE_CODE\n" +
                "                WHERE r6a.ZONE_NAME NOT IN ('DG East', 'CEI DG') AND r6a.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter6;
    }
    public String QueryFor_Adjudication_Legacy_2_MonthBack(String month_date, String zone_code){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter6 ="WITH col9_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9_6a\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_2 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                col3_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_3 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                ranked_data_6a AS (\n" +
                "                    SELECT col9_data.ZONE_NAME, col9_data.ZONE_CODE, col9_data.col9_6a, col3_data.col3,\n" +
                "                           CASE\n" +
                "                               WHEN col3_data.col3 = 0 THEN 0\n" +
                "                               ELSE (col9_data.col9_6a / col3_data.col3) * 100\n" +
                "                           END AS total_score_6a\n" +
                "                    FROM col9_data\n" +
                "                    LEFT JOIN col3_data ON col9_data.ZONE_CODE = col3_data.ZONE_CODE AND col9_data.ZONE_NAME = col3_data.ZONE_NAME\n" +
                "                ),\n" +
                "                median_calc_6a AS (\n" +
                "                    SELECT col9_6a, ROW_NUMBER() OVER (ORDER BY col9_6a) AS row_num, COUNT(*) OVER () AS total_rows\n" +
                "                    FROM ranked_data_6a\n" +
                "                ),\n" +
                "                median_value_6a AS (\n" +
                "                    SELECT AVG(col9_6a) AS median\n" +
                "                    FROM median_calc_6a\n" +
                "                    WHERE row_num IN (FLOOR((total_rows + 1) / 2), FLOOR((total_rows + 2) / 2))\n" +
                "                ),\n" +
                "                ranked_data_6b AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                           SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) AS col18,\n" +
                "                           SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col13,\n" +
                "                           CASE\n" +
                "                               WHEN SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) = 0 THEN 0\n" +
                "                               ELSE (SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) /\n" +
                "                                    NULLIF(SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO), 0)) * 100\n" +
                "                           END AS total_score_6b\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_2 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                disposal_data_6c AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(COALESCE(14c.COMM_DISPOSAL_NO, 0) + COALESCE(14c.JC_DISPOSAL_NO, 0) + COALESCE(14c.AC_DISPOSAL_NO, 0) + COALESCE(14c.SUP_DISPOSAL_NO, 0)) AS numerator_6c,\n" +
                "                           ROW_NUMBER() OVER (ORDER BY SUM(COALESCE(14c.COMM_DISPOSAL_NO, 0) + COALESCE(14c.JC_DISPOSAL_NO, 0) + COALESCE(14c.AC_DISPOSAL_NO, 0) + COALESCE(14c.SUP_DISPOSAL_NO, 0))) AS row_num,\n" +
                "                           COUNT(*) OVER () AS total_count\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_2 + "'\n" +
                "                    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "                ),\n" +
                "                median_data_6c AS (\n" +
                "                    SELECT CASE\n" +
                "                               WHEN total_count % 2 = 1 THEN (SELECT numerator_6c FROM disposal_data_6c WHERE row_num = (total_count + 1) / 2)\n" +
                "                               ELSE (SELECT numerator_6c FROM disposal_data_6c WHERE row_num = total_count / 2)\n" +
                "                           END AS median_numerator_6c\n" +
                "                    FROM disposal_data_6c\n" +
                "                    LIMIT 1\n" +
                "                ),\n" +
                "                closing_data_6c AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(COALESCE(14c.COMM_CLOSING_NO, 0) + COALESCE(14c.JC_CLOSING_NO, 0) + COALESCE(14c.AC_CLOSING_NO, 0) + COALESCE(14c.SUP_CLOSING_NO, 0)) AS col3\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_2 + "'\n" +
                "                    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "                ),\n" +
                "                ranked_data_6c AS (\n" +
                "                    SELECT d.ZONE_NAME, d.ZONE_CODE, d.numerator_6c,\n" +
                "                           COALESCE(c.col3, 0) AS col3,\n" +
                "                           CASE WHEN COALESCE(c.col3, 0) = 0 THEN 0\n" +
                "                                ELSE (d.numerator_6c * 100 / COALESCE(c.col3, 0))\n" +
                "                           END AS score_of_parameter6c\n" +
                "                    FROM disposal_data_6c AS d\n" +
                "                    LEFT JOIN closing_data_6c AS c ON d.ZONE_NAME = c.ZONE_NAME AND d.ZONE_CODE = c.ZONE_CODE\n" +
                "                ),\n" +
                "                ranked_data_6d AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                           COALESCE(\n" +
                "                               (SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) /\n" +
                "                                NULLIF(SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO), 0)) * 100, 0\n" +
                "                           ) AS total_score_6d\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_2 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                )\n" +
                "                SELECT r6a.ZONE_NAME, r6a.ZONE_CODE, r6a.col9_6a, r6a.total_score_6a, m6a.median AS median_6a, r6b.total_score_6b, r6c.numerator_6c, r6c.score_of_parameter6c, m6c.median_numerator_6c, r6d.total_score_6d\n" +
                "                FROM ranked_data_6a AS r6a\n" +
                "                CROSS JOIN median_value_6a AS m6a\n" +
                "                LEFT JOIN ranked_data_6b AS r6b ON r6a.ZONE_NAME = r6b.ZONE_NAME AND r6a.ZONE_CODE = r6b.ZONE_CODE\n" +
                "                LEFT JOIN ranked_data_6c AS r6c ON r6a.ZONE_NAME = r6c.ZONE_NAME AND r6a.ZONE_CODE = r6c.ZONE_CODE\n" +
                "                CROSS JOIN median_data_6c AS m6c\n" +
                "                LEFT JOIN ranked_data_6d AS r6d ON r6a.ZONE_NAME = r6d.ZONE_NAME AND r6a.ZONE_CODE = r6d.ZONE_CODE\n" +
                "                WHERE r6a.ZONE_NAME NOT IN ('DG East', 'CEI DG') AND r6a.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter6;
    }
    public String QueryFor_Adjudication_Legacy_3_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter6 ="WITH col9_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9_6a\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_3 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                col3_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_4 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                ranked_data_6a AS (\n" +
                "                    SELECT col9_data.ZONE_NAME, col9_data.ZONE_CODE, col9_data.col9_6a, col3_data.col3,\n" +
                "                           CASE\n" +
                "                               WHEN col3_data.col3 = 0 THEN 0\n" +
                "                               ELSE (col9_data.col9_6a / col3_data.col3) * 100\n" +
                "                           END AS total_score_6a\n" +
                "                    FROM col9_data\n" +
                "                    LEFT JOIN col3_data ON col9_data.ZONE_CODE = col3_data.ZONE_CODE AND col9_data.ZONE_NAME = col3_data.ZONE_NAME\n" +
                "                ),\n" +
                "                median_calc_6a AS (\n" +
                "                    SELECT col9_6a, ROW_NUMBER() OVER (ORDER BY col9_6a) AS row_num, COUNT(*) OVER () AS total_rows\n" +
                "                    FROM ranked_data_6a\n" +
                "                ),\n" +
                "                median_value_6a AS (\n" +
                "                    SELECT AVG(col9_6a) AS median\n" +
                "                    FROM median_calc_6a\n" +
                "                    WHERE row_num IN (FLOOR((total_rows + 1) / 2), FLOOR((total_rows + 2) / 2))\n" +
                "                ),\n" +
                "                ranked_data_6b AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                           SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) AS col18,\n" +
                "                           SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col13,\n" +
                "                           CASE\n" +
                "                               WHEN SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) = 0 THEN 0\n" +
                "                               ELSE (SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) /\n" +
                "                                    NULLIF(SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO), 0)) * 100\n" +
                "                           END AS total_score_6b\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_3 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                disposal_data_6c AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(COALESCE(14c.COMM_DISPOSAL_NO, 0) + COALESCE(14c.JC_DISPOSAL_NO, 0) + COALESCE(14c.AC_DISPOSAL_NO, 0) + COALESCE(14c.SUP_DISPOSAL_NO, 0)) AS numerator_6c,\n" +
                "                           ROW_NUMBER() OVER (ORDER BY SUM(COALESCE(14c.COMM_DISPOSAL_NO, 0) + COALESCE(14c.JC_DISPOSAL_NO, 0) + COALESCE(14c.AC_DISPOSAL_NO, 0) + COALESCE(14c.SUP_DISPOSAL_NO, 0))) AS row_num,\n" +
                "                           COUNT(*) OVER () AS total_count\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_3 + "'\n" +
                "                    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "                ),\n" +
                "                median_data_6c AS (\n" +
                "                    SELECT CASE\n" +
                "                               WHEN total_count % 2 = 1 THEN (SELECT numerator_6c FROM disposal_data_6c WHERE row_num = (total_count + 1) / 2)\n" +
                "                               ELSE (SELECT numerator_6c FROM disposal_data_6c WHERE row_num = total_count / 2)\n" +
                "                           END AS median_numerator_6c\n" +
                "                    FROM disposal_data_6c\n" +
                "                    LIMIT 1\n" +
                "                ),\n" +
                "                closing_data_6c AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(COALESCE(14c.COMM_CLOSING_NO, 0) + COALESCE(14c.JC_CLOSING_NO, 0) + COALESCE(14c.AC_CLOSING_NO, 0) + COALESCE(14c.SUP_CLOSING_NO, 0)) AS col3\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_3 + "'\n" +
                "                    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "                ),\n" +
                "                ranked_data_6c AS (\n" +
                "                    SELECT d.ZONE_NAME, d.ZONE_CODE, d.numerator_6c,\n" +
                "                           COALESCE(c.col3, 0) AS col3,\n" +
                "                           CASE WHEN COALESCE(c.col3, 0) = 0 THEN 0\n" +
                "                                ELSE (d.numerator_6c * 100 / COALESCE(c.col3, 0))\n" +
                "                           END AS score_of_parameter6c\n" +
                "                    FROM disposal_data_6c AS d\n" +
                "                    LEFT JOIN closing_data_6c AS c ON d.ZONE_NAME = c.ZONE_NAME AND d.ZONE_CODE = c.ZONE_CODE\n" +
                "                ),\n" +
                "                ranked_data_6d AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                           COALESCE(\n" +
                "                               (SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) /\n" +
                "                                NULLIF(SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO), 0)) * 100, 0\n" +
                "                           ) AS total_score_6d\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_3 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                )\n" +
                "                SELECT r6a.ZONE_NAME, r6a.ZONE_CODE, r6a.col9_6a, r6a.total_score_6a, m6a.median AS median_6a, r6b.total_score_6b, r6c.numerator_6c, r6c.score_of_parameter6c, m6c.median_numerator_6c, r6d.total_score_6d\n" +
                "                FROM ranked_data_6a AS r6a\n" +
                "                CROSS JOIN median_value_6a AS m6a\n" +
                "                LEFT JOIN ranked_data_6b AS r6b ON r6a.ZONE_NAME = r6b.ZONE_NAME AND r6a.ZONE_CODE = r6b.ZONE_CODE\n" +
                "                LEFT JOIN ranked_data_6c AS r6c ON r6a.ZONE_NAME = r6c.ZONE_NAME AND r6a.ZONE_CODE = r6c.ZONE_CODE\n" +
                "                CROSS JOIN median_data_6c AS m6c\n" +
                "                LEFT JOIN ranked_data_6d AS r6d ON r6a.ZONE_NAME = r6d.ZONE_NAME AND r6a.ZONE_CODE = r6d.ZONE_CODE\n" +
                "                WHERE r6a.ZONE_NAME NOT IN ('DG East', 'CEI DG') AND r6a.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter6;
    }
    public String QueryFor_Adjudication_Legacy_4_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter6 ="WITH col9_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9_6a\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_4 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                col3_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_5 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                ranked_data_6a AS (\n" +
                "                    SELECT col9_data.ZONE_NAME, col9_data.ZONE_CODE, col9_data.col9_6a, col3_data.col3,\n" +
                "                           CASE\n" +
                "                               WHEN col3_data.col3 = 0 THEN 0\n" +
                "                               ELSE (col9_data.col9_6a / col3_data.col3) * 100\n" +
                "                           END AS total_score_6a\n" +
                "                    FROM col9_data\n" +
                "                    LEFT JOIN col3_data ON col9_data.ZONE_CODE = col3_data.ZONE_CODE AND col9_data.ZONE_NAME = col3_data.ZONE_NAME\n" +
                "                ),\n" +
                "                median_calc_6a AS (\n" +
                "                    SELECT col9_6a, ROW_NUMBER() OVER (ORDER BY col9_6a) AS row_num, COUNT(*) OVER () AS total_rows\n" +
                "                    FROM ranked_data_6a\n" +
                "                ),\n" +
                "                median_value_6a AS (\n" +
                "                    SELECT AVG(col9_6a) AS median\n" +
                "                    FROM median_calc_6a\n" +
                "                    WHERE row_num IN (FLOOR((total_rows + 1) / 2), FLOOR((total_rows + 2) / 2))\n" +
                "                ),\n" +
                "                ranked_data_6b AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                           SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) AS col18,\n" +
                "                           SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col13,\n" +
                "                           CASE\n" +
                "                               WHEN SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) = 0 THEN 0\n" +
                "                               ELSE (SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) /\n" +
                "                                    NULLIF(SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO), 0)) * 100\n" +
                "                           END AS total_score_6b\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_4 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                disposal_data_6c AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(COALESCE(14c.COMM_DISPOSAL_NO, 0) + COALESCE(14c.JC_DISPOSAL_NO, 0) + COALESCE(14c.AC_DISPOSAL_NO, 0) + COALESCE(14c.SUP_DISPOSAL_NO, 0)) AS numerator_6c,\n" +
                "                           ROW_NUMBER() OVER (ORDER BY SUM(COALESCE(14c.COMM_DISPOSAL_NO, 0) + COALESCE(14c.JC_DISPOSAL_NO, 0) + COALESCE(14c.AC_DISPOSAL_NO, 0) + COALESCE(14c.SUP_DISPOSAL_NO, 0))) AS row_num,\n" +
                "                           COUNT(*) OVER () AS total_count\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_4 + "'\n" +
                "                    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "                ),\n" +
                "                median_data_6c AS (\n" +
                "                    SELECT CASE\n" +
                "                               WHEN total_count % 2 = 1 THEN (SELECT numerator_6c FROM disposal_data_6c WHERE row_num = (total_count + 1) / 2)\n" +
                "                               ELSE (SELECT numerator_6c FROM disposal_data_6c WHERE row_num = total_count / 2)\n" +
                "                           END AS median_numerator_6c\n" +
                "                    FROM disposal_data_6c\n" +
                "                    LIMIT 1\n" +
                "                ),\n" +
                "                closing_data_6c AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(COALESCE(14c.COMM_CLOSING_NO, 0) + COALESCE(14c.JC_CLOSING_NO, 0) + COALESCE(14c.AC_CLOSING_NO, 0) + COALESCE(14c.SUP_CLOSING_NO, 0)) AS col3\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_4 + "'\n" +
                "                    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "                ),\n" +
                "                ranked_data_6c AS (\n" +
                "                    SELECT d.ZONE_NAME, d.ZONE_CODE, d.numerator_6c,\n" +
                "                           COALESCE(c.col3, 0) AS col3,\n" +
                "                           CASE WHEN COALESCE(c.col3, 0) = 0 THEN 0\n" +
                "                                ELSE (d.numerator_6c * 100 / COALESCE(c.col3, 0))\n" +
                "                           END AS score_of_parameter6c\n" +
                "                    FROM disposal_data_6c AS d\n" +
                "                    LEFT JOIN closing_data_6c AS c ON d.ZONE_NAME = c.ZONE_NAME AND d.ZONE_CODE = c.ZONE_CODE\n" +
                "                ),\n" +
                "                ranked_data_6d AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                           COALESCE(\n" +
                "                               (SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) /\n" +
                "                                NULLIF(SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO), 0)) * 100, 0\n" +
                "                           ) AS total_score_6d\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_4 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                )\n" +
                "                SELECT r6a.ZONE_NAME, r6a.ZONE_CODE, r6a.col9_6a, r6a.total_score_6a, m6a.median AS median_6a, r6b.total_score_6b, r6c.numerator_6c, r6c.score_of_parameter6c, m6c.median_numerator_6c, r6d.total_score_6d\n" +
                "                FROM ranked_data_6a AS r6a\n" +
                "                CROSS JOIN median_value_6a AS m6a\n" +
                "                LEFT JOIN ranked_data_6b AS r6b ON r6a.ZONE_NAME = r6b.ZONE_NAME AND r6a.ZONE_CODE = r6b.ZONE_CODE\n" +
                "                LEFT JOIN ranked_data_6c AS r6c ON r6a.ZONE_NAME = r6c.ZONE_NAME AND r6a.ZONE_CODE = r6c.ZONE_CODE\n" +
                "                CROSS JOIN median_data_6c AS m6c\n" +
                "                LEFT JOIN ranked_data_6d AS r6d ON r6a.ZONE_NAME = r6d.ZONE_NAME AND r6a.ZONE_CODE = r6d.ZONE_CODE\n" +
                "                WHERE r6a.ZONE_NAME NOT IN ('DG East', 'CEI DG') AND r6a.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter6;
    }
    public String QueryFor_Adjudication_Legacy_5_MonthBack(String month_date, String zone_code){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter6 ="WITH col9_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9_6a\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_5 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                col3_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_6 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                ranked_data_6a AS (\n" +
                "                    SELECT col9_data.ZONE_NAME, col9_data.ZONE_CODE, col9_data.col9_6a, col3_data.col3,\n" +
                "                           CASE\n" +
                "                               WHEN col3_data.col3 = 0 THEN 0\n" +
                "                               ELSE (col9_data.col9_6a / col3_data.col3) * 100\n" +
                "                           END AS total_score_6a\n" +
                "                    FROM col9_data\n" +
                "                    LEFT JOIN col3_data ON col9_data.ZONE_CODE = col3_data.ZONE_CODE AND col9_data.ZONE_NAME = col3_data.ZONE_NAME\n" +
                "                ),\n" +
                "                median_calc_6a AS (\n" +
                "                    SELECT col9_6a, ROW_NUMBER() OVER (ORDER BY col9_6a) AS row_num, COUNT(*) OVER () AS total_rows\n" +
                "                    FROM ranked_data_6a\n" +
                "                ),\n" +
                "                median_value_6a AS (\n" +
                "                    SELECT AVG(col9_6a) AS median\n" +
                "                    FROM median_calc_6a\n" +
                "                    WHERE row_num IN (FLOOR((total_rows + 1) / 2), FLOOR((total_rows + 2) / 2))\n" +
                "                ),\n" +
                "                ranked_data_6b AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                           SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) AS col18,\n" +
                "                           SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col13,\n" +
                "                           CASE\n" +
                "                               WHEN SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) = 0 THEN 0\n" +
                "                               ELSE (SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) /\n" +
                "                                    NULLIF(SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO), 0)) * 100\n" +
                "                           END AS total_score_6b\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_5 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                disposal_data_6c AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(COALESCE(14c.COMM_DISPOSAL_NO, 0) + COALESCE(14c.JC_DISPOSAL_NO, 0) + COALESCE(14c.AC_DISPOSAL_NO, 0) + COALESCE(14c.SUP_DISPOSAL_NO, 0)) AS numerator_6c,\n" +
                "                           ROW_NUMBER() OVER (ORDER BY SUM(COALESCE(14c.COMM_DISPOSAL_NO, 0) + COALESCE(14c.JC_DISPOSAL_NO, 0) + COALESCE(14c.AC_DISPOSAL_NO, 0) + COALESCE(14c.SUP_DISPOSAL_NO, 0))) AS row_num,\n" +
                "                           COUNT(*) OVER () AS total_count\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_5 + "'\n" +
                "                    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "                ),\n" +
                "                median_data_6c AS (\n" +
                "                    SELECT CASE\n" +
                "                               WHEN total_count % 2 = 1 THEN (SELECT numerator_6c FROM disposal_data_6c WHERE row_num = (total_count + 1) / 2)\n" +
                "                               ELSE (SELECT numerator_6c FROM disposal_data_6c WHERE row_num = total_count / 2)\n" +
                "                           END AS median_numerator_6c\n" +
                "                    FROM disposal_data_6c\n" +
                "                    LIMIT 1\n" +
                "                ),\n" +
                "                closing_data_6c AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(COALESCE(14c.COMM_CLOSING_NO, 0) + COALESCE(14c.JC_CLOSING_NO, 0) + COALESCE(14c.AC_CLOSING_NO, 0) + COALESCE(14c.SUP_CLOSING_NO, 0)) AS col3\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_5 + "'\n" +
                "                    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "                ),\n" +
                "                ranked_data_6c AS (\n" +
                "                    SELECT d.ZONE_NAME, d.ZONE_CODE, d.numerator_6c,\n" +
                "                           COALESCE(c.col3, 0) AS col3,\n" +
                "                           CASE WHEN COALESCE(c.col3, 0) = 0 THEN 0\n" +
                "                                ELSE (d.numerator_6c * 100 / COALESCE(c.col3, 0))\n" +
                "                           END AS score_of_parameter6c\n" +
                "                    FROM disposal_data_6c AS d\n" +
                "                    LEFT JOIN closing_data_6c AS c ON d.ZONE_NAME = c.ZONE_NAME AND d.ZONE_CODE = c.ZONE_CODE\n" +
                "                ),\n" +
                "                ranked_data_6d AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                           COALESCE(\n" +
                "                               (SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) /\n" +
                "                                NULLIF(SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO), 0)) * 100, 0\n" +
                "                           ) AS total_score_6d\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + MonthBack_5 + "'\n" +
                "                    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                )\n" +
                "                SELECT r6a.ZONE_NAME, r6a.ZONE_CODE, r6a.col9_6a, r6a.total_score_6a, m6a.median AS median_6a, r6b.total_score_6b, r6c.numerator_6c, r6c.score_of_parameter6c, m6c.median_numerator_6c, r6d.total_score_6d\n" +
                "                FROM ranked_data_6a AS r6a\n" +
                "                CROSS JOIN median_value_6a AS m6a\n" +
                "                LEFT JOIN ranked_data_6b AS r6b ON r6a.ZONE_NAME = r6b.ZONE_NAME AND r6a.ZONE_CODE = r6b.ZONE_CODE\n" +
                "                LEFT JOIN ranked_data_6c AS r6c ON r6a.ZONE_NAME = r6c.ZONE_NAME AND r6a.ZONE_CODE = r6c.ZONE_CODE\n" +
                "                CROSS JOIN median_data_6c AS m6c\n" +
                "                LEFT JOIN ranked_data_6d AS r6d ON r6a.ZONE_NAME = r6d.ZONE_NAME AND r6a.ZONE_CODE = r6d.ZONE_CODE\n" +
                "                WHERE r6a.ZONE_NAME NOT IN ('DG East', 'CEI DG') AND r6a.ZONE_CODE = '" + zone_code + "';\n";
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

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=RecoveryOfArrears__8__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
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

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Arrests & prosecution__9__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    public String QueryFor_arrestsAndProsecution_CurrentMonth(String month_date, String zone_code){
     // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_arrestsAndProsecution_1_MonthBack(String month_date, String zone_code){
     // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_arrestsAndProsecution_2_MonthBack(String month_date, String zone_code){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_arrestsAndProsecution_3_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_arrestsAndProsecution_4_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_arrestsAndProsecution_5_MonthBack(String month_date, String zone_code){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Audit__10__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    public String QueryFor_audit_CurrentMonth(String month_date, String zone_code){
// '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_audit_1_MonthBack(String month_date, String zone_code){
// '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_audit_2_MonthBack(String month_date, String zone_code){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_audit_3_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_audit_4_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_audit_5_MonthBack(String month_date, String zone_code){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Appeals=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    public String QueryFor_appeals_CurrentMonth(String month_date, String zone_code){
// '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter11 ="WITH cte_11a AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS numerator_11a  \n" +
                "                    FROM mis_dla_gst_lgl_1 11a \n" +
                "                    LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6 \n" +
                "                    GROUP BY zc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ), \n" +
                "                cte_11a_1 AS (\n" +
                "\t\t\t    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4\n" +
                "                    FROM mis_dla_gst_lgl_1 11a \n" +
                "                    LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_1 + "' AND FORUM_CODE = 6 \n" +
                "                    GROUP BY zc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                median_cte_11a AS (SELECT AVG(numerator_11a) AS median_11a\n" +
                "                    FROM (SELECT numerator_11a, \n" +
                "                               ROW_NUMBER() OVER (ORDER BY numerator_11a) AS rn,\n" +
                "                               COUNT(*) OVER () AS cnt\n" +
                "                        FROM cte_11a\n" +
                "                    ) AS temp\n" +
                "                    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "                ),\n" +
                "                cte_11b AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n" +
                "                           SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A \n" +
                "                    FROM mis_dla_gst_lgl_1a AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6 GROUP BY zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte1_11b AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B,\n" +
                "                           SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B \n" +
                "                    FROM mis_dla_gst_lgl_1b AS 11b \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11b.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6 GROUP BY zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte_11c AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_DISPOSAL_NO + 11a.TAXPAYER_DISPOSAL_NO) AS numerator_11c \n" +
                "                    FROM mis_dla_gst_lgl_1 AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + month_date + "' AND 11a.FORUM_CODE = 7 GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte1_11c AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col4 \n" +
                "                    FROM mis_dla_gst_lgl_1 AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_1 + "' AND 11a.FORUM_CODE = 7 GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
                "                ),\n" +
                "                median_cte_11c AS (\n" +
                "                    SELECT \n" +
                "                        AVG(numerator_11c) AS median_11c \n" +
                "                    FROM (\n" +
                "                        SELECT numerator_11c,\n" +
                "                               ROW_NUMBER() OVER (ORDER BY numerator_11c) AS rn, \n" +
                "                               COUNT(*) OVER () AS cnt \n" +
                "                        FROM cte_11c\n" +
                "                    ) AS temp \n" +
                "                    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "                ),\n" +
                "                cte_11d AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n" +
                "                           SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A \n" +
                "                    FROM mis_dla_gst_lgl_1a AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 7 GROUP BY zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte1_11d AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, \n" +
                "                           SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B \n" +
                "                    FROM mis_dla_gst_lgl_1b AS 11b \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11b.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 7 GROUP BY zc.ZONE_CODE\n" +
                "                )\n" +
                "                SELECT cte_11a.ZONE_NAME, cte_11a.ZONE_CODE, cte_11a.numerator_11a, (cte_11a.numerator_11a / cte_11a_1.col4) AS total_score_11a, \n" +
                "                    median_cte_11a.median_11a,\n" +
                "                    CASE \n" +
                "                        WHEN (cte_11b.col10A + cte1_11b.col10B) = 0 THEN NULL \n" +
                "                        ELSE (((cte_11b.col10A - cte_11b.col12A) + (cte1_11b.col10B - cte1_11b.col12B)) / (cte_11b.col10A + cte1_11b.col10B)) \n" +
                "                    END AS total_score_11b,\n" +
                "                    cte_11c.numerator_11c,\n" +
                "                    (cte_11c.numerator_11c / NULLIF(cte1_11c.col4, 0)) AS total_score_11c,\n" +
                "                    median_cte_11c.median_11c,\n" +
                "                    ((cte_11d.col10A - cte_11d.col12A) + (cte1_11d.col10B - cte1_11d.col12B)) / (cte_11d.col10A + cte1_11d.col10B) AS total_score_11d\n" +
                "                FROM cte_11a\n" +
                "                INNER JOIN cte_11a_1 ON cte_11a.ZONE_CODE = cte_11a_1.ZONE_CODE\n" +
                "                INNER JOIN cte_11b ON cte_11a.ZONE_CODE = cte_11b.ZONE_CODE\n" +
                "                INNER JOIN cte1_11b ON cte_11b.ZONE_CODE = cte1_11b.ZONE_CODE\n" +
                "                INNER JOIN cte_11c ON cte_11a.ZONE_CODE = cte_11c.ZONE_CODE\n" +
                "                INNER JOIN cte1_11c ON cte_11c.ZONE_CODE = cte1_11c.ZONE_CODE\n" +
                "                INNER JOIN cte_11d ON cte_11a.ZONE_CODE = cte_11d.ZONE_CODE\n" +
                "                INNER JOIN cte1_11d ON cte_11d.ZONE_CODE = cte1_11d.ZONE_CODE\n" +
                "                CROSS JOIN median_cte_11a\n" +
                "                CROSS JOIN median_cte_11c\n" +
                "                WHERE cte_11a.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter11;
    }
    public String QueryFor_appeals_1_MonthBack(String month_date, String zone_code){
// '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter11 ="WITH cte_11a AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS numerator_11a  \n" +
                "                    FROM mis_dla_gst_lgl_1 11a \n" +
                "                    LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_1 + "' AND FORUM_CODE = 6 \n" +
                "                    GROUP BY zc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ), \n" +
                "                cte_11a_1 AS (\n" +
                "\t\t\t    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4\n" +
                "                    FROM mis_dla_gst_lgl_1 11a \n" +
                "                    LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_2 + "' AND FORUM_CODE = 6 \n" +
                "                    GROUP BY zc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                median_cte_11a AS (SELECT AVG(numerator_11a) AS median_11a\n" +
                "                    FROM (SELECT numerator_11a, \n" +
                "                               ROW_NUMBER() OVER (ORDER BY numerator_11a) AS rn,\n" +
                "                               COUNT(*) OVER () AS cnt\n" +
                "                        FROM cte_11a\n" +
                "                    ) AS temp\n" +
                "                    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "                ),\n" +
                "                cte_11b AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n" +
                "                           SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A \n" +
                "                    FROM mis_dla_gst_lgl_1a AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_1 + "' AND FORUM_CODE = 6 GROUP BY zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte1_11b AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B,\n" +
                "                           SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B \n" +
                "                    FROM mis_dla_gst_lgl_1b AS 11b \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11b.MM_YYYY = '" + MonthBack_1 + "' AND FORUM_CODE = 6 GROUP BY zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte_11c AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_DISPOSAL_NO + 11a.TAXPAYER_DISPOSAL_NO) AS numerator_11c \n" +
                "                    FROM mis_dla_gst_lgl_1 AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_1 + "' AND 11a.FORUM_CODE = 7 GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte1_11c AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col4 \n" +
                "                    FROM mis_dla_gst_lgl_1 AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_2 + "' AND 11a.FORUM_CODE = 7 GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
                "                ),\n" +
                "                median_cte_11c AS (\n" +
                "                    SELECT \n" +
                "                        AVG(numerator_11c) AS median_11c \n" +
                "                    FROM (\n" +
                "                        SELECT numerator_11c,\n" +
                "                               ROW_NUMBER() OVER (ORDER BY numerator_11c) AS rn, \n" +
                "                               COUNT(*) OVER () AS cnt \n" +
                "                        FROM cte_11c\n" +
                "                    ) AS temp \n" +
                "                    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "                ),\n" +
                "                cte_11d AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n" +
                "                           SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A \n" +
                "                    FROM mis_dla_gst_lgl_1a AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_1 + "' AND FORUM_CODE = 7 GROUP BY zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte1_11d AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, \n" +
                "                           SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B \n" +
                "                    FROM mis_dla_gst_lgl_1b AS 11b \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11b.MM_YYYY = '" + MonthBack_1 + "' AND FORUM_CODE = 7 GROUP BY zc.ZONE_CODE\n" +
                "                )\n" +
                "                SELECT cte_11a.ZONE_NAME, cte_11a.ZONE_CODE, cte_11a.numerator_11a, (cte_11a.numerator_11a / cte_11a_1.col4) AS total_score_11a, \n" +
                "                    median_cte_11a.median_11a,\n" +
                "                    CASE \n" +
                "                        WHEN (cte_11b.col10A + cte1_11b.col10B) = 0 THEN NULL \n" +
                "                        ELSE (((cte_11b.col10A - cte_11b.col12A) + (cte1_11b.col10B - cte1_11b.col12B)) / (cte_11b.col10A + cte1_11b.col10B)) \n" +
                "                    END AS total_score_11b,\n" +
                "                    cte_11c.numerator_11c,\n" +
                "                    (cte_11c.numerator_11c / NULLIF(cte1_11c.col4, 0)) AS total_score_11c,\n" +
                "                    median_cte_11c.median_11c,\n" +
                "                    ((cte_11d.col10A - cte_11d.col12A) + (cte1_11d.col10B - cte1_11d.col12B)) / (cte_11d.col10A + cte1_11d.col10B) AS total_score_11d\n" +
                "                FROM cte_11a\n" +
                "                INNER JOIN cte_11a_1 ON cte_11a.ZONE_CODE = cte_11a_1.ZONE_CODE\n" +
                "                INNER JOIN cte_11b ON cte_11a.ZONE_CODE = cte_11b.ZONE_CODE\n" +
                "                INNER JOIN cte1_11b ON cte_11b.ZONE_CODE = cte1_11b.ZONE_CODE\n" +
                "                INNER JOIN cte_11c ON cte_11a.ZONE_CODE = cte_11c.ZONE_CODE\n" +
                "                INNER JOIN cte1_11c ON cte_11c.ZONE_CODE = cte1_11c.ZONE_CODE\n" +
                "                INNER JOIN cte_11d ON cte_11a.ZONE_CODE = cte_11d.ZONE_CODE\n" +
                "                INNER JOIN cte1_11d ON cte_11d.ZONE_CODE = cte1_11d.ZONE_CODE\n" +
                "                CROSS JOIN median_cte_11a\n" +
                "                CROSS JOIN median_cte_11c\n" +
                "                WHERE cte_11a.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter11;
    }
    public String QueryFor_appeals_2_MonthBack(String month_date, String zone_code){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter11 ="WITH cte_11a AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS numerator_11a  \n" +
                "                    FROM mis_dla_gst_lgl_1 11a \n" +
                "                    LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_2 + "' AND FORUM_CODE = 6 \n" +
                "                    GROUP BY zc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ), \n" +
                "                cte_11a_1 AS (\n" +
                "\t\t\t    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4\n" +
                "                    FROM mis_dla_gst_lgl_1 11a \n" +
                "                    LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_3 + "' AND FORUM_CODE = 6 \n" +
                "                    GROUP BY zc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                median_cte_11a AS (SELECT AVG(numerator_11a) AS median_11a\n" +
                "                    FROM (SELECT numerator_11a, \n" +
                "                               ROW_NUMBER() OVER (ORDER BY numerator_11a) AS rn,\n" +
                "                               COUNT(*) OVER () AS cnt\n" +
                "                        FROM cte_11a\n" +
                "                    ) AS temp\n" +
                "                    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "                ),\n" +
                "                cte_11b AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n" +
                "                           SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A \n" +
                "                    FROM mis_dla_gst_lgl_1a AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_2 + "' AND FORUM_CODE = 6 GROUP BY zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte1_11b AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B,\n" +
                "                           SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B \n" +
                "                    FROM mis_dla_gst_lgl_1b AS 11b \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11b.MM_YYYY = '" + MonthBack_2 + "' AND FORUM_CODE = 6 GROUP BY zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte_11c AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_DISPOSAL_NO + 11a.TAXPAYER_DISPOSAL_NO) AS numerator_11c \n" +
                "                    FROM mis_dla_gst_lgl_1 AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_2 + "' AND 11a.FORUM_CODE = 7 GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte1_11c AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col4 \n" +
                "                    FROM mis_dla_gst_lgl_1 AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_3 + "' AND 11a.FORUM_CODE = 7 GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
                "                ),\n" +
                "                median_cte_11c AS (\n" +
                "                    SELECT \n" +
                "                        AVG(numerator_11c) AS median_11c \n" +
                "                    FROM (\n" +
                "                        SELECT numerator_11c,\n" +
                "                               ROW_NUMBER() OVER (ORDER BY numerator_11c) AS rn, \n" +
                "                               COUNT(*) OVER () AS cnt \n" +
                "                        FROM cte_11c\n" +
                "                    ) AS temp \n" +
                "                    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "                ),\n" +
                "                cte_11d AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n" +
                "                           SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A \n" +
                "                    FROM mis_dla_gst_lgl_1a AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_2 + "' AND FORUM_CODE = 7 GROUP BY zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte1_11d AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, \n" +
                "                           SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B \n" +
                "                    FROM mis_dla_gst_lgl_1b AS 11b \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11b.MM_YYYY = '" + MonthBack_2 + "' AND FORUM_CODE = 7 GROUP BY zc.ZONE_CODE\n" +
                "                )\n" +
                "                SELECT cte_11a.ZONE_NAME, cte_11a.ZONE_CODE, cte_11a.numerator_11a, (cte_11a.numerator_11a / cte_11a_1.col4) AS total_score_11a, \n" +
                "                    median_cte_11a.median_11a,\n" +
                "                    CASE \n" +
                "                        WHEN (cte_11b.col10A + cte1_11b.col10B) = 0 THEN NULL \n" +
                "                        ELSE (((cte_11b.col10A - cte_11b.col12A) + (cte1_11b.col10B - cte1_11b.col12B)) / (cte_11b.col10A + cte1_11b.col10B)) \n" +
                "                    END AS total_score_11b,\n" +
                "                    cte_11c.numerator_11c,\n" +
                "                    (cte_11c.numerator_11c / NULLIF(cte1_11c.col4, 0)) AS total_score_11c,\n" +
                "                    median_cte_11c.median_11c,\n" +
                "                    ((cte_11d.col10A - cte_11d.col12A) + (cte1_11d.col10B - cte1_11d.col12B)) / (cte_11d.col10A + cte1_11d.col10B) AS total_score_11d\n" +
                "                FROM cte_11a\n" +
                "                INNER JOIN cte_11a_1 ON cte_11a.ZONE_CODE = cte_11a_1.ZONE_CODE\n" +
                "                INNER JOIN cte_11b ON cte_11a.ZONE_CODE = cte_11b.ZONE_CODE\n" +
                "                INNER JOIN cte1_11b ON cte_11b.ZONE_CODE = cte1_11b.ZONE_CODE\n" +
                "                INNER JOIN cte_11c ON cte_11a.ZONE_CODE = cte_11c.ZONE_CODE\n" +
                "                INNER JOIN cte1_11c ON cte_11c.ZONE_CODE = cte1_11c.ZONE_CODE\n" +
                "                INNER JOIN cte_11d ON cte_11a.ZONE_CODE = cte_11d.ZONE_CODE\n" +
                "                INNER JOIN cte1_11d ON cte_11d.ZONE_CODE = cte1_11d.ZONE_CODE\n" +
                "                CROSS JOIN median_cte_11a\n" +
                "                CROSS JOIN median_cte_11c\n" +
                "                WHERE cte_11a.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter11;
    }
    public String QueryFor_appeals_3_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter11 ="WITH cte_11a AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS numerator_11a  \n" +
                "                    FROM mis_dla_gst_lgl_1 11a \n" +
                "                    LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_3 + "' AND FORUM_CODE = 6 \n" +
                "                    GROUP BY zc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ), \n" +
                "                cte_11a_1 AS (\n" +
                "\t\t\t    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4\n" +
                "                    FROM mis_dla_gst_lgl_1 11a \n" +
                "                    LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_4 + "' AND FORUM_CODE = 6 \n" +
                "                    GROUP BY zc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                median_cte_11a AS (SELECT AVG(numerator_11a) AS median_11a\n" +
                "                    FROM (SELECT numerator_11a, \n" +
                "                               ROW_NUMBER() OVER (ORDER BY numerator_11a) AS rn,\n" +
                "                               COUNT(*) OVER () AS cnt\n" +
                "                        FROM cte_11a\n" +
                "                    ) AS temp\n" +
                "                    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "                ),\n" +
                "                cte_11b AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n" +
                "                           SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A \n" +
                "                    FROM mis_dla_gst_lgl_1a AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_3 + "' AND FORUM_CODE = 6 GROUP BY zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte1_11b AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B,\n" +
                "                           SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B \n" +
                "                    FROM mis_dla_gst_lgl_1b AS 11b \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11b.MM_YYYY = '" + MonthBack_3 + "' AND FORUM_CODE = 6 GROUP BY zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte_11c AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_DISPOSAL_NO + 11a.TAXPAYER_DISPOSAL_NO) AS numerator_11c \n" +
                "                    FROM mis_dla_gst_lgl_1 AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_3 + "' AND 11a.FORUM_CODE = 7 GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte1_11c AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col4 \n" +
                "                    FROM mis_dla_gst_lgl_1 AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_4 + "' AND 11a.FORUM_CODE = 7 GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
                "                ),\n" +
                "                median_cte_11c AS (\n" +
                "                    SELECT \n" +
                "                        AVG(numerator_11c) AS median_11c \n" +
                "                    FROM (\n" +
                "                        SELECT numerator_11c,\n" +
                "                               ROW_NUMBER() OVER (ORDER BY numerator_11c) AS rn, \n" +
                "                               COUNT(*) OVER () AS cnt \n" +
                "                        FROM cte_11c\n" +
                "                    ) AS temp \n" +
                "                    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "                ),\n" +
                "                cte_11d AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n" +
                "                           SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A \n" +
                "                    FROM mis_dla_gst_lgl_1a AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_3 + "' AND FORUM_CODE = 7 GROUP BY zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte1_11d AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, \n" +
                "                           SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B \n" +
                "                    FROM mis_dla_gst_lgl_1b AS 11b \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11b.MM_YYYY = '" + MonthBack_3 + "' AND FORUM_CODE = 7 GROUP BY zc.ZONE_CODE\n" +
                "                )\n" +
                "                SELECT cte_11a.ZONE_NAME, cte_11a.ZONE_CODE, cte_11a.numerator_11a, (cte_11a.numerator_11a / cte_11a_1.col4) AS total_score_11a, \n" +
                "                    median_cte_11a.median_11a,\n" +
                "                    CASE \n" +
                "                        WHEN (cte_11b.col10A + cte1_11b.col10B) = 0 THEN NULL \n" +
                "                        ELSE (((cte_11b.col10A - cte_11b.col12A) + (cte1_11b.col10B - cte1_11b.col12B)) / (cte_11b.col10A + cte1_11b.col10B)) \n" +
                "                    END AS total_score_11b,\n" +
                "                    cte_11c.numerator_11c,\n" +
                "                    (cte_11c.numerator_11c / NULLIF(cte1_11c.col4, 0)) AS total_score_11c,\n" +
                "                    median_cte_11c.median_11c,\n" +
                "                    ((cte_11d.col10A - cte_11d.col12A) + (cte1_11d.col10B - cte1_11d.col12B)) / (cte_11d.col10A + cte1_11d.col10B) AS total_score_11d\n" +
                "                FROM cte_11a\n" +
                "                INNER JOIN cte_11a_1 ON cte_11a.ZONE_CODE = cte_11a_1.ZONE_CODE\n" +
                "                INNER JOIN cte_11b ON cte_11a.ZONE_CODE = cte_11b.ZONE_CODE\n" +
                "                INNER JOIN cte1_11b ON cte_11b.ZONE_CODE = cte1_11b.ZONE_CODE\n" +
                "                INNER JOIN cte_11c ON cte_11a.ZONE_CODE = cte_11c.ZONE_CODE\n" +
                "                INNER JOIN cte1_11c ON cte_11c.ZONE_CODE = cte1_11c.ZONE_CODE\n" +
                "                INNER JOIN cte_11d ON cte_11a.ZONE_CODE = cte_11d.ZONE_CODE\n" +
                "                INNER JOIN cte1_11d ON cte_11d.ZONE_CODE = cte1_11d.ZONE_CODE\n" +
                "                CROSS JOIN median_cte_11a\n" +
                "                CROSS JOIN median_cte_11c\n" +
                "                WHERE cte_11a.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter11;
    }
    public String QueryFor_appeals_4_MonthBack(String month_date, String zone_code){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter11 ="WITH cte_11a AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS numerator_11a  \n" +
                "                    FROM mis_dla_gst_lgl_1 11a \n" +
                "                    LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_4 + "' AND FORUM_CODE = 6 \n" +
                "                    GROUP BY zc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ), \n" +
                "                cte_11a_1 AS (\n" +
                "\t\t\t    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4\n" +
                "                    FROM mis_dla_gst_lgl_1 11a \n" +
                "                    LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_5 + "' AND FORUM_CODE = 6 \n" +
                "                    GROUP BY zc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                median_cte_11a AS (SELECT AVG(numerator_11a) AS median_11a\n" +
                "                    FROM (SELECT numerator_11a, \n" +
                "                               ROW_NUMBER() OVER (ORDER BY numerator_11a) AS rn,\n" +
                "                               COUNT(*) OVER () AS cnt\n" +
                "                        FROM cte_11a\n" +
                "                    ) AS temp\n" +
                "                    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "                ),\n" +
                "                cte_11b AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n" +
                "                           SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A \n" +
                "                    FROM mis_dla_gst_lgl_1a AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_4 + "' AND FORUM_CODE = 6 GROUP BY zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte1_11b AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B,\n" +
                "                           SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B \n" +
                "                    FROM mis_dla_gst_lgl_1b AS 11b \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11b.MM_YYYY = '" + MonthBack_4 + "' AND FORUM_CODE = 6 GROUP BY zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte_11c AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_DISPOSAL_NO + 11a.TAXPAYER_DISPOSAL_NO) AS numerator_11c \n" +
                "                    FROM mis_dla_gst_lgl_1 AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_4 + "' AND 11a.FORUM_CODE = 7 GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte1_11c AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col4 \n" +
                "                    FROM mis_dla_gst_lgl_1 AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_5 + "' AND 11a.FORUM_CODE = 7 GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
                "                ),\n" +
                "                median_cte_11c AS (\n" +
                "                    SELECT \n" +
                "                        AVG(numerator_11c) AS median_11c \n" +
                "                    FROM (\n" +
                "                        SELECT numerator_11c,\n" +
                "                               ROW_NUMBER() OVER (ORDER BY numerator_11c) AS rn, \n" +
                "                               COUNT(*) OVER () AS cnt \n" +
                "                        FROM cte_11c\n" +
                "                    ) AS temp \n" +
                "                    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "                ),\n" +
                "                cte_11d AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n" +
                "                           SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A \n" +
                "                    FROM mis_dla_gst_lgl_1a AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_4 + "' AND FORUM_CODE = 7 GROUP BY zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte1_11d AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, \n" +
                "                           SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B \n" +
                "                    FROM mis_dla_gst_lgl_1b AS 11b \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11b.MM_YYYY = '" + MonthBack_4 + "' AND FORUM_CODE = 7 GROUP BY zc.ZONE_CODE\n" +
                "                )\n" +
                "                SELECT cte_11a.ZONE_NAME, cte_11a.ZONE_CODE, cte_11a.numerator_11a, (cte_11a.numerator_11a / cte_11a_1.col4) AS total_score_11a, \n" +
                "                    median_cte_11a.median_11a,\n" +
                "                    CASE \n" +
                "                        WHEN (cte_11b.col10A + cte1_11b.col10B) = 0 THEN NULL \n" +
                "                        ELSE (((cte_11b.col10A - cte_11b.col12A) + (cte1_11b.col10B - cte1_11b.col12B)) / (cte_11b.col10A + cte1_11b.col10B)) \n" +
                "                    END AS total_score_11b,\n" +
                "                    cte_11c.numerator_11c,\n" +
                "                    (cte_11c.numerator_11c / NULLIF(cte1_11c.col4, 0)) AS total_score_11c,\n" +
                "                    median_cte_11c.median_11c,\n" +
                "                    ((cte_11d.col10A - cte_11d.col12A) + (cte1_11d.col10B - cte1_11d.col12B)) / (cte_11d.col10A + cte1_11d.col10B) AS total_score_11d\n" +
                "                FROM cte_11a\n" +
                "                INNER JOIN cte_11a_1 ON cte_11a.ZONE_CODE = cte_11a_1.ZONE_CODE\n" +
                "                INNER JOIN cte_11b ON cte_11a.ZONE_CODE = cte_11b.ZONE_CODE\n" +
                "                INNER JOIN cte1_11b ON cte_11b.ZONE_CODE = cte1_11b.ZONE_CODE\n" +
                "                INNER JOIN cte_11c ON cte_11a.ZONE_CODE = cte_11c.ZONE_CODE\n" +
                "                INNER JOIN cte1_11c ON cte_11c.ZONE_CODE = cte1_11c.ZONE_CODE\n" +
                "                INNER JOIN cte_11d ON cte_11a.ZONE_CODE = cte_11d.ZONE_CODE\n" +
                "                INNER JOIN cte1_11d ON cte_11d.ZONE_CODE = cte1_11d.ZONE_CODE\n" +
                "                CROSS JOIN median_cte_11a\n" +
                "                CROSS JOIN median_cte_11c\n" +
                "                WHERE cte_11a.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter11;
    }
    public String QueryFor_appeals_5_MonthBack(String month_date, String zone_code){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter11 ="WITH cte_11a AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS numerator_11a  \n" +
                "                    FROM mis_dla_gst_lgl_1 11a \n" +
                "                    LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                "                    LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_5 + "' AND FORUM_CODE = 6 \n" +
                "                    GROUP BY zc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ), \n" +
                "                cte_11a_1 AS (\n" +
                "\t\t\t    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4\n" +
                "                    FROM mis_dla_gst_lgl_1 11a \n" +
                "                    LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_6 + "' AND FORUM_CODE = 6 \n" +
                "                    GROUP BY zc.ZONE_CODE, zc.ZONE_NAME\n" +
                "                ),\n" +
                "                median_cte_11a AS (SELECT AVG(numerator_11a) AS median_11a\n" +
                "                    FROM (SELECT numerator_11a, \n" +
                "                               ROW_NUMBER() OVER (ORDER BY numerator_11a) AS rn,\n" +
                "                               COUNT(*) OVER () AS cnt\n" +
                "                        FROM cte_11a\n" +
                "                    ) AS temp\n" +
                "                    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "                ),\n" +
                "                cte_11b AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n" +
                "                           SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A \n" +
                "                    FROM mis_dla_gst_lgl_1a AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_5 + "' AND FORUM_CODE = 6 GROUP BY zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte1_11b AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B,\n" +
                "                           SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B \n" +
                "                    FROM mis_dla_gst_lgl_1b AS 11b \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11b.MM_YYYY = '" + MonthBack_5 + "' AND FORUM_CODE = 6 GROUP BY zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte_11c AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_DISPOSAL_NO + 11a.TAXPAYER_DISPOSAL_NO) AS numerator_11c \n" +
                "                    FROM mis_dla_gst_lgl_1 AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_5 + "' AND 11a.FORUM_CODE = 7 GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte1_11c AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col4 \n" +
                "                    FROM mis_dla_gst_lgl_1 AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_6 + "' AND 11a.FORUM_CODE = 7 GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
                "                ),\n" +
                "                median_cte_11c AS (\n" +
                "                    SELECT \n" +
                "                        AVG(numerator_11c) AS median_11c \n" +
                "                    FROM (\n" +
                "                        SELECT numerator_11c,\n" +
                "                               ROW_NUMBER() OVER (ORDER BY numerator_11c) AS rn, \n" +
                "                               COUNT(*) OVER () AS cnt \n" +
                "                        FROM cte_11c\n" +
                "                    ) AS temp \n" +
                "                    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "                ),\n" +
                "                cte_11d AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n" +
                "                           SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A \n" +
                "                    FROM mis_dla_gst_lgl_1a AS 11a \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11a.MM_YYYY = '" + MonthBack_5 + "' AND FORUM_CODE = 7 GROUP BY zc.ZONE_CODE\n" +
                "                ),\n" +
                "                cte1_11d AS (\n" +
                "                    SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, \n" +
                "                           SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B \n" +
                "                    FROM mis_dla_gst_lgl_1b AS 11b \n" +
                "                    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                    WHERE 11b.MM_YYYY = '" + MonthBack_5 + "' AND FORUM_CODE = 7 GROUP BY zc.ZONE_CODE\n" +
                "                )\n" +
                "                SELECT cte_11a.ZONE_NAME, cte_11a.ZONE_CODE, cte_11a.numerator_11a, (cte_11a.numerator_11a / cte_11a_1.col4) AS total_score_11a, \n" +
                "                    median_cte_11a.median_11a,\n" +
                "                    CASE \n" +
                "                        WHEN (cte_11b.col10A + cte1_11b.col10B) = 0 THEN NULL \n" +
                "                        ELSE (((cte_11b.col10A - cte_11b.col12A) + (cte1_11b.col10B - cte1_11b.col12B)) / (cte_11b.col10A + cte1_11b.col10B)) \n" +
                "                    END AS total_score_11b,\n" +
                "                    cte_11c.numerator_11c,\n" +
                "                    (cte_11c.numerator_11c / NULLIF(cte1_11c.col4, 0)) AS total_score_11c,\n" +
                "                    median_cte_11c.median_11c,\n" +
                "                    ((cte_11d.col10A - cte_11d.col12A) + (cte1_11d.col10B - cte1_11d.col12B)) / (cte_11d.col10A + cte1_11d.col10B) AS total_score_11d\n" +
                "                FROM cte_11a\n" +
                "                INNER JOIN cte_11a_1 ON cte_11a.ZONE_CODE = cte_11a_1.ZONE_CODE\n" +
                "                INNER JOIN cte_11b ON cte_11a.ZONE_CODE = cte_11b.ZONE_CODE\n" +
                "                INNER JOIN cte1_11b ON cte_11b.ZONE_CODE = cte1_11b.ZONE_CODE\n" +
                "                INNER JOIN cte_11c ON cte_11a.ZONE_CODE = cte_11c.ZONE_CODE\n" +
                "                INNER JOIN cte1_11c ON cte_11c.ZONE_CODE = cte1_11c.ZONE_CODE\n" +
                "                INNER JOIN cte_11d ON cte_11a.ZONE_CODE = cte_11d.ZONE_CODE\n" +
                "                INNER JOIN cte1_11d ON cte_11d.ZONE_CODE = cte1_11d.ZONE_CODE\n" +
                "                CROSS JOIN median_cte_11a\n" +
                "                CROSS JOIN median_cte_11c\n" +
                "                WHERE cte_11a.ZONE_CODE = '" + zone_code + "';\n";
        return queryGstParameter11;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=END=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

}