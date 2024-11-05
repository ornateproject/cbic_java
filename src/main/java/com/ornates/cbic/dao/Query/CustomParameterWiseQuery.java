package com.ornates.cbic.dao.Query;
import com.ornates.cbic.service.DateCalculate;
public class CustomParameterWiseQuery {
    // ***********************************CUS 1 parameter wise(Timely payment of Refund) *****************************************************************
    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_TimelyPaymentOfRefunds_1_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus1 = "SELECT \n"
                + "    zc.ZONE_NAME, \n"
                + "    cc.ZONE_CODE, \n"
                + "    SUM(c14.CLOSING_NO) AS col10, \n"
                + "    SUM(c14.MONTHS_3_NO) AS col12, \n"
                + "    (SUM(c14.CLOSING_NO) - SUM(c14.MONTHS_3_NO)) AS col_difference,\n"
                + "    ((SUM(c14.CLOSING_NO) - SUM(c14.MONTHS_3_NO)) / SUM(c14.CLOSING_NO)) * 100 AS total_score \n"
                + "FROM \n"
                + "    mis_gst_commcode AS cc \n"
                + "RIGHT JOIN \n"
                + "    mis_dgi_cus_4 AS c14 ON c14.COMM_CODE = cc.COMM_CODE \n"
                + "LEFT JOIN \n"
                + "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "WHERE  \n"
                + "    c14.MM_YYYY = '" + month_date + "'  \n"
                + "GROUP BY \n"
                + "    zc.ZONE_CODE, zc.ZONE_NAME, cc.ZONE_CODE \n"
                + "ORDER BY \n"
                + "    total_score ASC \n"
                + "LIMIT 0, 1000;\n"
                + "";
        return query_assessment_cus1;
    }
    // for 2no url , all india rank will show in this query
    public String QueryFor_TimelyPaymentOfRefunds_1_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus1 = "SELECT \n"
                + "    zc.ZONE_NAME, \n"
                + "    cc.ZONE_CODE, \n"
                + "    cc.COMM_NAME,  -- Added COMM_NAME here\n"
                + "    c14.CLOSING_NO AS col10, \n"
                + "    c14.MONTHS_3_NO AS col12, \n"
                + "    (c14.CLOSING_NO - c14.MONTHS_3_NO) AS col_difference,\n"
                + "    ((c14.CLOSING_NO - c14.MONTHS_3_NO) / c14.CLOSING_NO) * 100 AS total_score \n"
                + "FROM \n"
                + "    mis_gst_commcode AS cc \n"
                + "RIGHT JOIN \n"
                + "    mis_dgi_cus_4 AS c14 ON c14.COMM_CODE = cc.COMM_CODE \n"
                + "LEFT JOIN \n"
                + "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "WHERE  \n"
                + "    c14.MM_YYYY = '" + month_date + "'  \n"
                + "    AND cc.ZONE_CODE =  '"+zone_code+"'\n"
                + "ORDER BY \n"
                + "    total_score ASC \n"
                + "LIMIT 0, 1000;\n"
                + "";
        return query_assessment_cus1;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_TimelyPaymentOfRefunds_1_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus1 = "SELECT\n"
                + "    zc.ZONE_NAME,\n"
                + "    cc.ZONE_CODE,\n"
                + "    SUM(c14.CLOSING_NO) AS col10,\n"
                + "    SUM(c14.MONTHS_3_NO) AS col12,\n"
                + "    (SUM(c14.CLOSING_NO) - SUM(c14.MONTHS_3_NO)) AS col_difference,\n"
                + "    ((SUM(c14.CLOSING_NO) - SUM(c14.MONTHS_3_NO)) / SUM(c14.CLOSING_NO)) * 100 AS total_score,\n"
                + "    'Cus1' AS Cus1 -- Adding the static value 'Cus1'\n"
                + "FROM\n"
                + "    mis_gst_commcode AS cc\n"
                + "RIGHT JOIN\n"
                + "    mis_dgi_cus_4 AS c14 ON c14.COMM_CODE = cc.COMM_CODE\n"
                + "LEFT JOIN\n"
                + "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "WHERE\n"
                + "    c14.MM_YYYY = '" + month_date + "' -- Replace '" + month_date + "' with your desired date\n"
                + "    AND cc.ZONE_CODE ='"+zone_code+"' -- Replace 'your_zone_code' with actual zone code\n"
                + "GROUP BY\n"
                + "    zc.ZONE_CODE, zc.ZONE_NAME -- Removed COMM_NAME from GROUP BY\n"
                + "ORDER BY\n"
                + "    total_score ASC\n"
                + "LIMIT 0, 1000;\n"
                + "";
        return query_assessment_cus1;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_TimelyPaymentOfRefunds_1_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus1 = "SELECT \n"
                + "    zc.ZONE_NAME, \n"
                + "    cc.ZONE_CODE, \n"
                + "    cc.COMM_NAME,  -- Added COMM_NAME here\n"
                + "    c14.CLOSING_NO AS col10, \n"
                + "    c14.MONTHS_3_NO AS col12, \n"
                + "    (c14.CLOSING_NO - c14.MONTHS_3_NO) AS col_difference,\n"
                + "    ((c14.CLOSING_NO - c14.MONTHS_3_NO) / c14.CLOSING_NO) * 100 AS total_score \n"
                + "FROM \n"
                + "    mis_gst_commcode AS cc \n"
                + "RIGHT JOIN \n"
                + "    mis_dgi_cus_4 AS c14 ON c14.COMM_CODE = cc.COMM_CODE \n"
                + "LEFT JOIN \n"
                + "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "WHERE  \n"
                + "    c14.MM_YYYY = '" + month_date + "'  \n"
                + "   \n"
                + "ORDER BY \n"
                + "    total_score ASC \n"
                + "LIMIT 0, 1000;\n"
                + "";
        return query_assessment_cus1;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_TimelyPaymentOfRefunds_1_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus1 = "SELECT\n"
                + "    zc.ZONE_NAME,\n"
                + "    cc.ZONE_CODE,\n"
                + "    cc.COMM_NAME,  -- Adding COMM_NAME to SELECT\n"
                + "    c14.CLOSING_NO AS col10,\n"
                + "    c14.MONTHS_3_NO AS col12,\n"
                + "    (c14.CLOSING_NO - c14.MONTHS_3_NO) AS col_difference,\n"
                + "    ((c14.CLOSING_NO - c14.MONTHS_3_NO) / c14.CLOSING_NO) * 100 AS total_score,\n"
                + "    -- Removed 'ra' as it is undefined\n"
                + "    'Cus1' AS description\n"
                + "FROM\n"
                + "    mis_gst_commcode AS cc\n"
                + "RIGHT JOIN\n"
                + "    mis_dgi_cus_4 AS c14 ON c14.COMM_CODE = cc.COMM_CODE\n"
                + "LEFT JOIN\n"
                + "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "WHERE\n"
                + "    c14.MM_YYYY = '" + month_date + "'  -- Replace '" + month_date + "' with your desired date\n"
                + "    AND cc.ZONE_CODE = '"+zone_code+"'\n"
                + "    AND cc.COMM_NAME = '" + come_name + "'  -- Replace with your desired COMM_NAME filter\n"
                + "GROUP BY\n"
                + "    zc.ZONE_CODE, zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, c14.CLOSING_NO, c14.MONTHS_3_NO\n"
                + "ORDER BY\n"
                + "    total_score ASC\n"
                + "LIMIT 0, 1000;\n"
                + "";
        return query_assessment_cus1;
    }

    // ***********************************CUS 2 parameter wise(Management Of Export Obligation - EPGC) *****************************************************************
    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_ManagementOfExportObligation_EPGC_2_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus2 = "";
        return query_assessment_cus2;
    }
    // for 2no url , all india rank will show in this query
    public String QueryFor_ManagementOfExportObligation_EPGC_2_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus2 = "";
        return query_assessment_cus2;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_ManagementOfExportObligation_EPGC_2_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus2 = "";
        return query_assessment_cus2;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_ManagementOfExportObligation_EPGC_2_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus2 = "";
        return query_assessment_cus2;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_ManagementOfExportObligation_EPGC_2_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus2 = "";
        return query_assessment_cus2;
    }

    // ***********************************CUS 3 parameter wise(Management Of Export Obligation - AA) *****************************************************************
    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_ManagementOfExportObligation_AA_3_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus3 = "";
        return query_assessment_cus3;
    }
    // for 2no url , all india rank will show in this query
    public String QueryFor_ManagementOfExportObligation_AA_3_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus3 = "";
        return query_assessment_cus3;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_ManagementOfExportObligation_AA_3_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus3 = "";
        return query_assessment_cus3;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_ManagementOfExportObligation_AA_3_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus3 = "";
        return query_assessment_cus3;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_ManagementOfExportObligation_AA_3_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus3 = "";
        return query_assessment_cus3;
    }

    // ***********************************CUS 4 parameter wise(Disposal / pendency of Provisional Assessments) *****************************************************************
    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_DisposalPendencyOfProvisionalAssessments_4_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus4 = "";
        return query_assessment_cus4;
    }
    // for 2no url , all india rank will show in this query
    public String QueryFor_DisposalPendencyOfProvisionalAssessments_4_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus4 = "";
        return query_assessment_cus4;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_DisposalPendencyOfProvisionalAssessments_4_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus4 = "";
        return query_assessment_cus4;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_DisposalPendencyOfProvisionalAssessments_4_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus4 = "";
        return query_assessment_cus4;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_DisposalPendencyOfProvisionalAssessments_4_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus4 = "";
        return query_assessment_cus4;
    }

    // ***********************************CUS 5 parameter wise(Adjudication) *****************************************************************
    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_Adjudication_5_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus5 = "WITH \r\n"
                + "-- CTE for the first query\r\n"
                + "cte_5a AS (\r\n"
                + "    SELECT \r\n"
                + "        zc.ZONE_NAME, \r\n"
                + "        cc.ZONE_CODE, \r\n"
                + "        SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO) AS col5a\r\n"
                + "    FROM \r\n"
                + "        Mis_DGI_CUS_1A AS 14c\r\n"
                + "    RIGHT JOIN \r\n"
                + "        mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \r\n"
                + "    LEFT JOIN \r\n"
                + "        mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \r\n"
                + "    WHERE \r\n"
                + "        14c.MM_YYYY = '" + month_date + "'\r\n"
                + "    GROUP BY \r\n"
                + "        zc.ZONE_NAME, cc.ZONE_CODE\r\n"
                + "),\r\n"
                + "-- CTE for the second query\r\n"
                + "cte_3a AS (\r\n"
                + "    SELECT \r\n"
                + "        zc.ZONE_NAME, \r\n"
                + "        cc.ZONE_CODE, \r\n"
                + "        SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO) AS col3a\r\n"
                + "    FROM \r\n"
                + "        Mis_DGI_CUS_1A AS 14c\r\n"
                + "    RIGHT JOIN \r\n"
                + "        mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \r\n"
                + "    LEFT JOIN \r\n"
                + "        mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \r\n"
                + "    WHERE \r\n"
                + "        14c.MM_YYYY = DATE_SUB('" + month_date + "', INTERVAL 1 MONTH)\r\n"
                + "    GROUP BY \r\n"
                + "        zc.ZONE_NAME, cc.ZONE_CODE\r\n"
                + "),\r\n"
                + "-- CTE for the median calculation from the first query\r\n"
                + "median_cte AS (\r\n"
                + "    SELECT \r\n"
                + "        AVG(col5a) AS cus5a_median\r\n"
                + "    FROM (\r\n"
                + "        SELECT col5a, \r\n"
                + "               ROW_NUMBER() OVER (ORDER BY col5a) AS rn,\r\n"
                + "               COUNT(*) OVER () AS cnt\r\n"
                + "        FROM cte_5a\r\n"
                + "    ) AS temp\r\n"
                + "    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\r\n"
                + "),\r\n"
                + "-- CTE for the second query calculation (col7d and col6a)\r\n"
                + "cte_5b AS (\r\n"
                + "    SELECT \r\n"
                + "        zc.ZONE_NAME, \r\n"
                + "        cc.ZONE_CODE, \r\n"
                + "        SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) AS col7d,\r\n"
                + "        SUM(14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO) AS col6a,\r\n"
                + "        (SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) / \r\n"
                + "         SUM(14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO)) * 100 AS total_score5b\r\n"
                + "    FROM mis_dgi_cus_1A AS 14c\r\n"
                + "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE\r\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                + "    WHERE 14c.MM_YYYY = '" + month_date + "'\r\n"
                + "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\r\n"
                + "),\r\n"
                + "-- CTE for the third query (col7 and col9)\r\n"
                + "cte_5c AS (\r\n"
                + "    SELECT \r\n"
                + "        zc.ZONE_NAME, \r\n"
                + "        cc.ZONE_CODE, \r\n"
                + "        SUM(14c.CLOSING_NO) AS col7,\r\n"
                + "        SUM(14c.YEAR_1) AS col9,\r\n"
                + "        ((SUM(14c.CLOSING_NO) - SUM(14c.YEAR_1)) / SUM(14c.CLOSING_NO)) * 100 AS total_score5c\r\n"
                + "    FROM mis_gst_commcode AS cc\r\n"
                + "    RIGHT JOIN mis_dgi_cus_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\r\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                + "    WHERE 14c.MM_YYYY = '" + month_date + "'\r\n"
                + "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\r\n"
                + ")\r\n"
                + "-- Final SELECT combining all the CTEs\r\n"
                + "SELECT \r\n"
                + "    cte_5a.ZONE_NAME, \r\n"
                + "    cte_5a.ZONE_CODE, \r\n"
                + "    cte_5a.col5a, \r\n"
                + "    cte_3a.col3a,\r\n"
                + "    median_cte.cus5a_median,\r\n"
                + "    (cte_5a.col5a / cte_3a.col3a) * 100 AS total_score5a,\r\n"
                + "    cte_5b.col7d, \r\n"
                + "    cte_5b.col6a, \r\n"
                + "    cte_5b.total_score5b,\r\n"
                + "    cte_5c.col7, \r\n"
                + "    cte_5c.col9, \r\n"
                + "    cte_5c.total_score5c\r\n"
                + "FROM \r\n"
                + "    cte_5a\r\n"
                + "JOIN \r\n"
                + "    cte_3a ON cte_5a.ZONE_CODE = cte_3a.ZONE_CODE\r\n"
                + "JOIN \r\n"
                + "    cte_5b ON cte_5a.ZONE_CODE = cte_5b.ZONE_CODE\r\n"
                + "JOIN \r\n"
                + "    cte_5c ON cte_5a.ZONE_CODE = cte_5c.ZONE_CODE\r\n"
                + "CROSS JOIN \r\n"
                + "    median_cte\r\n"
                + "ORDER BY \r\n"
                + "    total_score5a DESC, \r\n"
                + "    total_score5b ASC, \r\n"
                + "    total_score5c ASC;\r\n"
                + "";
        return query_assessment_cus5;
    }
    // for 2no url , all india rank will show in this query
    public String QueryFor_Adjudication_5_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus5 = "WITH cte_5a AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, \n" +
                "           COALESCE((c.COMM_DISPOSAL_NO + c.JC_DISPOSAL_NO + c.AC_DISPOSAL_NO), 0) AS col5a, \n" +
                "           COALESCE((c_prev.COMM_CLOSING_NO + c_prev.JC_CLOSING_NO + c_prev.AC_CLOSING_NO), 0) AS col3a\n" +
                "    FROM Mis_DGI_CUS_1A AS c\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON c.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    LEFT JOIN Mis_DGI_CUS_1A AS c_prev ON c_prev.COMM_CODE = cc.COMM_CODE AND c_prev.MM_YYYY = '" + prev_month_new + "'  \n" +
                "    WHERE c.MM_YYYY ='" + month_date + "'\n" +
                "),\n" +
                "median_cte AS (\n" +
                "    SELECT AVG(col5a) AS median5a\n" +
                "    FROM (\n" +
                "        SELECT col5a, ROW_NUMBER() OVER (ORDER BY col5a) AS rn, COUNT(*) OVER () AS cnt\n" +
                "        FROM cte_5a\n" +
                "    ) AS temp\n" +
                "    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "),\n" +
                "cte_5b AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
                "           COALESCE(((c.COMM_MORE_YEAR_AMT + c.JC_MORE_YEAR_AMT + c.AC_MORE_YEAR_AMT) / \n" +
                "           (c.AC_CLOSING_NO + c.JC_CLOSING_NO + c.COMM_CLOSING_NO)), 0) AS total_score5b\n" +
                "    FROM mis_dgi_cus_1A AS c\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON c.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE c.MM_YYYY = '" + month_date + "'\n" +
                "),\n" +
                "cte_5c AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
                "           COALESCE((((c.CLOSING_NO) - (c.YEAR_1)) / (c.CLOSING_NO)), 0) AS total_score5c\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dgi_cus_2 AS c ON cc.COMM_CODE = c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE c.MM_YYYY = '" + month_date + "'\n" +
                ")\n" +
                "SELECT \n" +
                "    COALESCE(cte_5a.ZONE_NAME, cte_5b.ZONE_NAME, cte_5c.ZONE_NAME) AS ZONE_NAME,\n" +
                "    COALESCE(cte_5a.ZONE_CODE, cte_5b.ZONE_CODE, cte_5c.ZONE_CODE) AS ZONE_CODE,\n" +
                "    COALESCE(cte_5a.COMM_NAME, cte_5b.COMM_NAME, cte_5c.COMM_NAME) AS COMM_NAME,\n" +
                "    COALESCE(cte_5a.col5a, 0) AS numerator5a,\n" +
                "    COALESCE(median_cte.median5a, 0) AS median5a,\n" +
                "    COALESCE(cte_5a.col5a / NULLIF(cte_5a.col3a, 0), 0) AS total_score5a,\n" +
                "    COALESCE(cte_5b.total_score5b, 0) AS total_score5b,\n" +
                "    COALESCE(cte_5c.total_score5c, 0) AS total_score5c\n" +
                "FROM cte_5a\n" +
                "LEFT JOIN cte_5b ON cte_5a.COMM_NAME = cte_5b.COMM_NAME\n" +
                "LEFT JOIN cte_5c ON cte_5a.COMM_NAME = cte_5c.COMM_NAME\n" +
                "LEFT JOIN median_cte ON 1 = 1\n" +
                "WHERE COALESCE(cte_5a.ZONE_CODE, cte_5b.ZONE_CODE, cte_5c.ZONE_CODE) = '" + zone_code + "'\n" +
                "\n" +
                "UNION\n" +
                "\n" +
                "SELECT \n" +
                "    COALESCE(cte_5b.ZONE_NAME, cte_5a.ZONE_NAME, cte_5c.ZONE_NAME) AS ZONE_NAME,\n" +
                "    COALESCE(cte_5b.ZONE_CODE, cte_5a.ZONE_CODE, cte_5c.ZONE_CODE) AS ZONE_CODE,\n" +
                "    COALESCE(cte_5b.COMM_NAME, cte_5a.COMM_NAME, cte_5c.COMM_NAME) AS COMM_NAME,\n" +
                "    COALESCE(cte_5a.col5a, 0) AS numerator5a,\n" +
                "    COALESCE(median_cte.median5a, 0) AS median5a,\n" +
                "    COALESCE(cte_5a.col5a / NULLIF(cte_5a.col3a, 0), 0) AS total_score5a,\n" +
                "    COALESCE(cte_5b.total_score5b, 0) AS total_score5b,\n" +
                "    COALESCE(cte_5c.total_score5c, 0) AS total_score5c\n" +
                "FROM cte_5b\n" +
                "LEFT JOIN cte_5a ON cte_5b.COMM_NAME = cte_5a.COMM_NAME\n" +
                "LEFT JOIN cte_5c ON cte_5b.COMM_NAME = cte_5c.COMM_NAME\n" +
                "LEFT JOIN median_cte ON 1 = 1\n" +
                "WHERE COALESCE(cte_5b.ZONE_CODE, cte_5a.ZONE_CODE, cte_5c.ZONE_CODE) = '" + zone_code + "'\n" +
                "\n" +
                "UNION\n" +
                "\n" +
                "SELECT \n" +
                "    COALESCE(cte_5c.ZONE_NAME, cte_5a.ZONE_NAME, cte_5b.ZONE_NAME) AS ZONE_NAME,\n" +
                "    COALESCE(cte_5c.ZONE_CODE, cte_5a.ZONE_CODE, cte_5b.ZONE_CODE) AS ZONE_CODE,\n" +
                "    COALESCE(cte_5c.COMM_NAME, cte_5a.COMM_NAME, cte_5b.COMM_NAME) AS COMM_NAME,\n" +
                "    COALESCE(cte_5a.col5a, 0) AS numerator5a,\n" +
                "    COALESCE(median_cte.median5a, 0) AS median5a,\n" +
                "    COALESCE(cte_5a.col5a / NULLIF(cte_5a.col3a, 0), 0) AS total_score5a,\n" +
                "    COALESCE(cte_5b.total_score5b, 0) AS total_score5b,\n" +
                "    COALESCE(cte_5c.total_score5c, 0) AS total_score5c\n" +
                "FROM cte_5c\n" +
                "LEFT JOIN cte_5a ON cte_5c.COMM_NAME = cte_5a.COMM_NAME\n" +
                "LEFT JOIN cte_5b ON cte_5c.COMM_NAME = cte_5b.COMM_NAME\n" +
                "LEFT JOIN median_cte ON 1 = 1\n" +
                "WHERE COALESCE(cte_5c.ZONE_CODE, cte_5a.ZONE_CODE, cte_5b.ZONE_CODE) = '" + zone_code + "';\n";
        return query_assessment_cus5;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_Adjudication_5_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus5 = "WITH cte_5a AS (\r\n"
                + "    SELECT \r\n"
                + "        zc.ZONE_NAME, \r\n"
                + "        cc.ZONE_CODE, \r\n"
                + "        SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO) AS col5a\r\n"
                + "    FROM \r\n"
                + "        Mis_DGI_CUS_1A AS 14c\r\n"
                + "    RIGHT JOIN \r\n"
                + "        mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \r\n"
                + "    LEFT JOIN \r\n"
                + "        mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \r\n"
                + "    WHERE \r\n"
                + "        14c.MM_YYYY = '" + month_date + "'\r\n"
                + "    GROUP BY \r\n"
                + "        zc.ZONE_NAME, cc.ZONE_CODE\r\n"
                + "),\r\n"
                + "cte_3a AS (\r\n"
                + "    SELECT \r\n"
                + "        zc.ZONE_NAME, \r\n"
                + "        cc.ZONE_CODE, \r\n"
                + "        SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO) AS col3a\r\n"
                + "    FROM \r\n"
                + "        Mis_DGI_CUS_1A AS 14c\r\n"
                + "    RIGHT JOIN \r\n"
                + "        mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \r\n"
                + "    LEFT JOIN \r\n"
                + "        mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \r\n"
                + "    WHERE \r\n"
                + "        14c.MM_YYYY = DATE_SUB('" + month_date + "', INTERVAL 1 MONTH)\r\n"
                + "    GROUP BY \r\n"
                + "        zc.ZONE_NAME, cc.ZONE_CODE\r\n"
                + "),\r\n"
                + "median_cte AS (\r\n"
                + "    SELECT \r\n"
                + "        AVG(col5a) AS cus5a_median\r\n"
                + "    FROM (\r\n"
                + "        SELECT col5a, \r\n"
                + "               ROW_NUMBER() OVER (ORDER BY col5a) AS rn,\r\n"
                + "               COUNT(*) OVER () AS cnt\r\n"
                + "        FROM cte_5a\r\n"
                + "    ) AS temp\r\n"
                + "    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\r\n"
                + ")\r\n"
                + "\r\n"
                + "-- Final merged query starts here\r\n"
                + "SELECT \r\n"
                + "    cte_5a.ZONE_NAME, \r\n"
                + "    cte_5a.ZONE_CODE, \r\n"
                + "    cte_5a.col5a, \r\n"
                + "    median_cte.cus5a_median,\r\n"
                + "    (cte_5a.col5a / cte_3a.col3a) * 100 AS total_score,\r\n"
                + "    CONCAT(cte_5a.col5a, '/', cte_3a.col3a) AS absolutevalue,\r\n"
                + "    'CUS5A' AS custom\r\n"
                + "FROM \r\n"
                + "    cte_5a\r\n"
                + "JOIN \r\n"
                + "    cte_3a ON cte_5a.ZONE_CODE = cte_3a.ZONE_CODE\r\n"
                + "CROSS JOIN \r\n"
                + "    median_cte\r\n"
                + "WHERE \r\n"
                + "    cte_5a.ZONE_CODE = '"+zone_code+"'\r\n"
                + "\r\n"
                + "UNION ALL\r\n"
                + "\r\n"
                + "SELECT \r\n"
                + "    zc.ZONE_NAME, \r\n"
                + "    cc.ZONE_CODE,\r\n"
                + "    NULL AS col5a, \r\n"
                + "    NULL AS cus5a_median,\r\n"
                + "    (SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) / \r\n"
                + "     SUM(14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO)) * 100 AS total_score,\r\n"
                + "    CONCAT(SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT), '/', \r\n"
                + "           SUM(14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO)) AS absolutevalue,\r\n"
                + "    'CUS5B' AS custom\r\n"
                + "FROM \r\n"
                + "    mis_dgi_cus_1A AS 14c\r\n"
                + "RIGHT JOIN \r\n"
                + "    mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE\r\n"
                + "LEFT JOIN \r\n"
                + "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                + "WHERE \r\n"
                + "    14c.MM_YYYY = '" + month_date + "' \r\n"
                + "AND \r\n"
                + "    cc.ZONE_CODE = '"+zone_code+"'\r\n"
                + "GROUP BY \r\n"
                + "    cc.ZONE_CODE\r\n"
                + "\r\n"
                + "UNION ALL\r\n"
                + "\r\n"
                + "SELECT \r\n"
                + "    zc.ZONE_NAME, \r\n"
                + "    cc.ZONE_CODE,\r\n"
                + "    NULL AS col5a, \r\n"
                + "    NULL AS cus5a_median,\r\n"
                + "    ((SUM(14c.CLOSING_NO) - SUM(14c.YEAR_1)) / SUM(14c.CLOSING_NO)) * 100 AS total_score,\r\n"
                + "    CONCAT((SUM(14c.CLOSING_NO) - SUM(14c.YEAR_1)), '/', \r\n"
                + "           SUM(14c.CLOSING_NO)) AS absolutevalue,\r\n"
                + "    'CUS5C' AS custom\r\n"
                + "FROM \r\n"
                + "    mis_gst_commcode AS cc\r\n"
                + "RIGHT JOIN \r\n"
                + "    mis_dgi_cus_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\r\n"
                + "LEFT JOIN \r\n"
                + "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                + "WHERE \r\n"
                + "    14c.MM_YYYY = '" + month_date + "' \r\n"
                + "AND \r\n"
                + "    cc.ZONE_CODE = '"+zone_code+"'\r\n"
                + "GROUP BY \r\n"
                + "    zc.ZONE_NAME, cc.ZONE_CODE\r\n"
                + "\r\n"
                + "-- Updated ORDER BY to prioritize CUS5A first, then CUS5B, and finally CUS5C\r\n"
                + "ORDER BY \r\n"
                + "    CASE custom\r\n"
                + "        WHEN 'CUS5A' THEN 1\r\n"
                + "        WHEN 'CUS5B' THEN 2\r\n"
                + "        WHEN 'CUS5C' THEN 3\r\n"
                + "    END, \r\n"
                + "    total_score DESC;\r\n"
                + "";
        return query_assessment_cus5;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_Adjudication_5_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus5 = "WITH cte_5a AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, \n" +
                "           COALESCE((c.COMM_DISPOSAL_NO + c.JC_DISPOSAL_NO + c.AC_DISPOSAL_NO), 0) AS col5a, \n" +
                "           COALESCE((c_prev.COMM_CLOSING_NO + c_prev.JC_CLOSING_NO + c_prev.AC_CLOSING_NO), 0) AS col3a\n" +
                "    FROM Mis_DGI_CUS_1A AS c\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON c.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    LEFT JOIN Mis_DGI_CUS_1A AS c_prev ON c_prev.COMM_CODE = cc.COMM_CODE AND c_prev.MM_YYYY = '" + prev_month_new + "'  \n" +
                "    WHERE c.MM_YYYY ='" + month_date + "'\n" +
                "),\n" +
                "median_cte AS (\n" +
                "    SELECT AVG(col5a) AS median5a\n" +
                "    FROM (\n" +
                "        SELECT col5a, ROW_NUMBER() OVER (ORDER BY col5a) AS rn, COUNT(*) OVER () AS cnt\n" +
                "        FROM cte_5a\n" +
                "    ) AS temp\n" +
                "    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "),\n" +
                "cte_5b AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
                "           COALESCE(((c.COMM_MORE_YEAR_AMT + c.JC_MORE_YEAR_AMT + c.AC_MORE_YEAR_AMT) / \n" +
                "           (c.AC_CLOSING_NO + c.JC_CLOSING_NO + c.COMM_CLOSING_NO)), 0) AS total_score5b\n" +
                "    FROM mis_dgi_cus_1A AS c\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON c.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE c.MM_YYYY = '" + month_date + "'\n" +
                "),\n" +
                "cte_5c AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
                "           COALESCE((((c.CLOSING_NO) - (c.YEAR_1)) / (c.CLOSING_NO)), 0) AS total_score5c\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dgi_cus_2 AS c ON cc.COMM_CODE = c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE c.MM_YYYY = '" + month_date + "'\n" +
                ")\n" +
                "SELECT \n" +
                "    COALESCE(cte_5a.ZONE_NAME, cte_5b.ZONE_NAME, cte_5c.ZONE_NAME) AS ZONE_NAME,\n" +
                "    COALESCE(cte_5a.ZONE_CODE, cte_5b.ZONE_CODE, cte_5c.ZONE_CODE) AS ZONE_CODE,\n" +
                "    COALESCE(cte_5a.COMM_NAME, cte_5b.COMM_NAME, cte_5c.COMM_NAME) AS COMM_NAME,\n" +
                "    COALESCE(cte_5a.col5a, 0) AS numerator5a,\n" +
                "    COALESCE(median_cte.median5a, 0) AS median5a,\n" +
                "    COALESCE(cte_5a.col5a / NULLIF(cte_5a.col3a, 0), 0) AS total_score5a,\n" +
                "    COALESCE(cte_5b.total_score5b, 0) AS total_score5b,\n" +
                "    COALESCE(cte_5c.total_score5c, 0) AS total_score5c\n" +
                "FROM cte_5a\n" +
                "LEFT JOIN cte_5b ON cte_5a.COMM_NAME = cte_5b.COMM_NAME\n" +
                "LEFT JOIN cte_5c ON cte_5a.COMM_NAME = cte_5c.COMM_NAME\n" +
                "LEFT JOIN median_cte ON 1 = 1\n" +
                "\n" +
                "UNION\n" +
                "\n" +
                "SELECT \n" +
                "    COALESCE(cte_5b.ZONE_NAME, cte_5a.ZONE_NAME, cte_5c.ZONE_NAME) AS ZONE_NAME,\n" +
                "    COALESCE(cte_5b.ZONE_CODE, cte_5a.ZONE_CODE, cte_5c.ZONE_CODE) AS ZONE_CODE,\n" +
                "    COALESCE(cte_5b.COMM_NAME, cte_5a.COMM_NAME, cte_5c.COMM_NAME) AS COMM_NAME,\n" +
                "    COALESCE(cte_5a.col5a, 0) AS numerator5a,\n" +
                "    COALESCE(median_cte.median5a, 0) AS median5a,\n" +
                "    COALESCE(cte_5a.col5a / NULLIF(cte_5a.col3a, 0), 0) AS total_score5a,\n" +
                "    COALESCE(cte_5b.total_score5b, 0) AS total_score5b,\n" +
                "    COALESCE(cte_5c.total_score5c, 0) AS total_score5c\n" +
                "FROM cte_5b\n" +
                "LEFT JOIN cte_5a ON cte_5b.COMM_NAME = cte_5a.COMM_NAME\n" +
                "LEFT JOIN cte_5c ON cte_5b.COMM_NAME = cte_5c.COMM_NAME\n" +
                "LEFT JOIN median_cte ON 1 = 1\n" +
                "\n" +
                "UNION\n" +
                "\n" +
                "SELECT \n" +
                "    COALESCE(cte_5c.ZONE_NAME, cte_5a.ZONE_NAME, cte_5b.ZONE_NAME) AS ZONE_NAME,\n" +
                "    COALESCE(cte_5c.ZONE_CODE, cte_5a.ZONE_CODE, cte_5b.ZONE_CODE) AS ZONE_CODE,\n" +
                "    COALESCE(cte_5c.COMM_NAME, cte_5a.COMM_NAME, cte_5b.COMM_NAME) AS COMM_NAME,\n" +
                "    COALESCE(cte_5a.col5a, 0) AS numerator5a,\n" +
                "    COALESCE(median_cte.median5a, 0) AS median5a,\n" +
                "    COALESCE(cte_5a.col5a / NULLIF(cte_5a.col3a, 0), 0) AS total_score5a,\n" +
                "    COALESCE(cte_5b.total_score5b, 0) AS total_score5b,\n" +
                "    COALESCE(cte_5c.total_score5c, 0) AS total_score5c\n" +
                "FROM cte_5c\n" +
                "LEFT JOIN cte_5a ON cte_5c.COMM_NAME = cte_5a.COMM_NAME\n" +
                "LEFT JOIN cte_5b ON cte_5c.COMM_NAME = cte_5b.COMM_NAME\n" +
                "LEFT JOIN median_cte ON 1 = 1;";
        return query_assessment_cus5;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_Adjudication_5_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus5 = "WITH cte AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, \n" +
                "           (c.COMM_DISPOSAL_NO + c.JC_DISPOSAL_NO + c.AC_DISPOSAL_NO) AS col5a,\n" +
                "           (c_prev.COMM_CLOSING_NO + c_prev.JC_CLOSING_NO + c_prev.AC_CLOSING_NO) AS col3a\n" +
                "    FROM Mis_DGI_CUS_1A AS c\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON c.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    LEFT JOIN Mis_DGI_CUS_1A AS c_prev ON c_prev.COMM_CODE = cc.COMM_CODE AND c_prev.MM_YYYY = '" + prev_month_new + "'  \n" +
                "    WHERE c.MM_YYYY = '" + month_date + "'\n" +
                "),\n" +
                "median_cte AS (\n" +
                "    SELECT AVG(col5a) AS median\n" +
                "    FROM (\n" +
                "        SELECT col5a, ROW_NUMBER() OVER (ORDER BY col5a) AS rn, COUNT(*) OVER () AS cnt\n" +
                "        FROM cte\n" +
                "    ) AS temp\n" +
                "    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                ")\n" +
                "SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_NAME, \n" +
                "       cte.col5a AS numerator, \n" +
                "       median_cte.median, \n" +
                "       CONCAT(cte.col5a, '/', cte.col3a) AS absvl, \n" +
                "       COALESCE(cte.col5a / cte.col3a, 0) AS total_score, \n" +
                "       \"GST5A\" AS gst\n" +
                "FROM cte\n" +
                "CROSS JOIN median_cte\n" +
                "WHERE cte.ZONE_CODE = '" + zone_code + "'  AND cte.COMM_NAME = '" + come_name + "'\n" +
                "\n" +
                "UNION ALL\n" +
                "\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, \n" +
                "       0 AS numerator, \n" +
                "       0 AS median, \n" +
                "       CONCAT((14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT), '/', \n" +
                "              (14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO)) AS absvl, \n" +
                "       COALESCE(((14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) / \n" +
                "                 (14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO)), 0) AS total_score, \n" +
                "       \"GST5B\" AS gst\n" +
                "FROM mis_dgi_cus_1A AS 14c  \n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
                "  AND cc.ZONE_CODE = '" + zone_code + "'  AND cc.COMM_NAME = '" + come_name + "'\n" +
                "\n" +
                "UNION ALL\n" +
                "\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, \n" +
                "       0 AS numerator, \n" +
                "       0 AS median, \n" +
                "       CONCAT(((14c.CLOSING_NO) - (14c.YEAR_1)), '/', (14c.CLOSING_NO)) AS absval, \n" +
                "       COALESCE((((14c.CLOSING_NO) - (14c.YEAR_1)) / (14c.CLOSING_NO)), 0) AS total_score, \n" +
                "       \"GST5C\" AS gst\n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_dgi_cus_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
                "  AND cc.ZONE_CODE = '" + zone_code + "'  AND cc.COMM_NAME = '" + come_name + "';\n";
        return query_assessment_cus5;
    }

    // ***********************************CUS 6 parameter wise(Investigation) *****************************************************************
    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_Investigation_6_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus6 = "";
        return query_assessment_cus6;
    }
    // for 2no url , all india rank will show in this query
    public String QueryFor_Investigation_6_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus6 = "";
        return query_assessment_cus6;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_Investigation_6_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus6 = "";
        return query_assessment_cus6;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_Investigation_6_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus6 = "";
        return query_assessment_cus6;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_Investigation_6_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus6 = "";
        return query_assessment_cus6;
    }

    // ***********************************CUS 7 parameter wise(Arrests & Prosecution) *****************************************************************
    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_ArrestsAndProsecution_7_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus7 = "";
        return query_assessment_cus7;
    }
    // for 2no url , all india rank will show in this query
    public String QueryFor_ArrestsAndProsecution_7_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus7 = "";
        return query_assessment_cus7;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_ArrestsAndProsecution_7_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus7 = "";
        return query_assessment_cus7;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_ArrestsAndProsecution_7_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus7 = "";
        return query_assessment_cus7;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_ArrestsAndProsecution_7_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus7 = "";
        return query_assessment_cus7;
    }

    // ***********************************CUS 8 parameter wise(Monitoring of un-cleared / unclaimed cargo) *****************************************************************
    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_MonitoringOfUncleared_UnclaimedCargo_8_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus8 = "";
        return query_assessment_cus8;
    }
    // for 2no url , all india rank will show in this query
    public String QueryFor_MonitoringOfUncleared_UnclaimedCargo_8_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus8 = "";
        return query_assessment_cus8;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_MonitoringOfUncleared_UnclaimedCargo_8_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus8 = "";
        return query_assessment_cus8;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_MonitoringOfUncleared_UnclaimedCargo_8_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus8 = "";
        return query_assessment_cus8;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_MonitoringOfUncleared_UnclaimedCargo_8_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus8 = "";
        return query_assessment_cus8;
    }

    // ***********************************CUS 9 parameter wise(Disposal of Confiscated Gold and NDPS) *****************************************************************
    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_DisposalOfConfiscatedGoldAndNDPS_9_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus9 = "WITH main_query AS (\r\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s3col9, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END) AS s3col12, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + prev_month_new + "' THEN CB_QUAN ELSE 0 END) AS s3col3,\r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s6col9, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END) AS s6col12, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + prev_month_new + "' THEN CB_QUAN ELSE 0 END) AS s6col3,\r\n"
                + "\r\n"
                + "        (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END) + \r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY ='" + month_date + "' THEN PARTY_QUAN ELSE 0 END)) AS numerator_9a,\r\n"
                + "\r\n"
                + "        ( \r\n"
                + "          (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END)) \r\n"
                + "          / \r\n"
                + "          (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + prev_month_new + "' THEN CB_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + prev_month_new + "' THEN CB_QUAN ELSE 0 END))\r\n"
                + "        ) AS total_score\r\n"
                + "    FROM mis_gst_commcode AS cc \r\n"
                + "    RIGHT JOIN mis_dol_cus_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \r\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \r\n"
                + "    WHERE 14c.MM_YYYY IN ('" + month_date + "', '" + prev_month_new + "')\r\n"
                + "    AND 14c.COMMODITY_CODE IN (3, 6)\r\n"
                + "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\r\n"
                + "),\r\n"
                + "ranked_query AS (\r\n"
                + "    SELECT *,\r\n"
                + "           ROW_NUMBER() OVER (ORDER BY numerator_9a) AS row_num,\r\n"
                + "           COUNT(*) OVER () AS total_rows\r\n"
                + "    FROM main_query\r\n"
                + "),\r\n"
                + "median_query AS (\r\n"
                + "    SELECT AVG(numerator_9a) AS median_9a\r\n"
                + "    FROM ranked_query\r\n"
                + "    WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\r\n"
                + "),\r\n"
                + "RipeDisposalCTE AS (\r\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(mis.RIPE_DISPOSAL) AS s5col13\r\n"
                + "    FROM MIS_DOL_CUS_3 AS mis\r\n"
                + "    RIGHT JOIN mis_gst_commcode AS cc ON mis.COMM_CODE = cc.COMM_CODE\r\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                + "    WHERE mis.MM_YYYY = '" + month_date + "'\r\n"
                + "    GROUP BY cc.ZONE_CODE\r\n"
                + "),\r\n"
                + "RipeClosingCTE AS (\r\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(mis.RIPE_CLOSING) AS s5col11\r\n"
                + "    FROM MIS_DOL_CUS_3 AS mis\r\n"
                + "    RIGHT JOIN mis_gst_commcode AS cc ON mis.COMM_CODE = cc.COMM_CODE\r\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                + "    WHERE mis.MM_YYYY ='" + prev_month_new + "'\r\n"
                + "    GROUP BY cc.ZONE_CODE\r\n"
                + "),\r\n"
                + "RankedDisposalCTE AS (\r\n"
                + "    SELECT ZONE_NAME, ZONE_CODE, s5col13,\r\n"
                + "           ROW_NUMBER() OVER (ORDER BY s5col13) AS RowAsc,\r\n"
                + "           ROW_NUMBER() OVER (ORDER BY s5col13 DESC) AS RowDesc\r\n"
                + "    FROM RipeDisposalCTE\r\n"
                + "),\r\n"
                + "MedianCTE AS (\r\n"
                + "    SELECT AVG(s5col13) AS MedianValue\r\n"
                + "    FROM RankedDisposalCTE\r\n"
                + "    WHERE RowAsc = RowDesc OR RowAsc + 1 = RowDesc\r\n"
                + ")\r\n"
                + "SELECT rd.ZONE_NAME, rd.ZONE_CODE, \r\n"
                + "       rd.s5col13, \r\n"
                + "       rc.s5col11, \r\n"
                + "       COALESCE(m.MedianValue, 0) AS Median,\r\n"
                + "       COALESCE(rd.s5col13 / rc.s5col11, 0) * 100 AS total_score, -- multiplied by 100\r\n"
                + "       rq.numerator_9a, \r\n"
                + "       rq.total_score * 100 AS main_total_score, -- multiplied by 100\r\n"
                + "       mq.median_9a,\r\n"
                + "       rq.*\r\n"
                + "FROM RipeDisposalCTE rd\r\n"
                + "LEFT JOIN RipeClosingCTE rc ON rd.ZONE_CODE = rc.ZONE_CODE\r\n"
                + "CROSS JOIN MedianCTE m\r\n"
                + "LEFT JOIN ranked_query rq ON rd.ZONE_CODE = rq.ZONE_CODE\r\n"
                + "CROSS JOIN median_query mq\r\n"
                + "ORDER BY main_total_score DESC;\r\n"
                + "";
        return query_assessment_cus9;
    }
    // for 2no url , all india rank will show in this query
    public String QueryFor_DisposalOfConfiscatedGoldAndNDPS_9_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus9 = "WITH RankedData AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, \n" +
                "           SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY= '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) AS s3col9, \n" +
                "           SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY= '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END) AS s3col12, \n" +
                "           SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY= '" + prev_month_new + "' THEN c.CB_QUAN ELSE 0 END) AS s3col3,\n" +
                "           SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY= '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) AS s6col9, \n" +
                "           SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY= '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END) AS s6col12, \n" +
                "           SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY= '" + prev_month_new + "' THEN c.CB_QUAN ELSE 0 END) AS s6col3,\n" +
                "           (\n" +
                "             SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY= '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) + \n" +
                "             SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY= '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END) + \n" +
                "             SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY= '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) + \n" +
                "             SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY= '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END)\n" +
                "           ) AS numerator_9a,\n" +
                "           COALESCE((\n" +
                "             (SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY= '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) + \n" +
                "              SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY= '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END) + \n" +
                "              SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY= '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) + \n" +
                "              SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY= '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END)) \n" +
                "             / \n" +
                "             (SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY= '" + prev_month_new + "' THEN c.CB_QUAN ELSE 0 END) + \n" +
                "              SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY= '" + prev_month_new + "' THEN c.CB_QUAN ELSE 0 END))),0\n" +
                "           ) AS total_score9a\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dol_cus_1 AS c ON cc.COMM_CODE = c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE c.MM_YYYY IN ('" + month_date + "', '" + prev_month_new + "') \n" +
                "      AND c.COMMODITY_CODE IN (3, 6)\n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME\n" +
                "),\n" +
                "OrderedData AS (\n" +
                "    SELECT *,\n" +
                "           ROW_NUMBER() OVER (ORDER BY numerator_9a) AS row_num,\n" +
                "           COUNT(*) OVER() AS total_rows FROM RankedData\n" +
                "),\n" +
                "MedianValue AS (\n" +
                "    SELECT AVG(numerator_9a) AS median_9a\n" +
                "    FROM OrderedData WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                "),\n" +
                "CTE_Ripe_Disposal AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, SUM(14c.RIPE_DISPOSAL) AS numerator9b\n" +
                "    FROM MIS_DOL_CUS_3 AS 14c\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME\n" +
                "),\n" +
                "CTE_Ripe_Closing AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, SUM(14c.RIPE_CLOSING) AS s5col11\n" +
                "    FROM MIS_DOL_CUS_3 AS 14c\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME\n" +
                "),\n" +
                "CTE_Median AS (\n" +
                "    SELECT AVG(numerator9b) AS median9b\n" +
                "    FROM (\n" +
                "        SELECT numerator9b, @row_num := @row_num + 1 AS row_num, @total_rows := @total_rows + 1 AS total_rows\n" +
                "        FROM CTE_Ripe_Disposal, (SELECT @row_num := 0, @total_rows := 0) AS vars\n" +
                "        ORDER BY numerator9b\n" +
                "    ) AS sorted\n" +
                "    WHERE row_num IN ((total_rows + 1) / 2, (total_rows + 2) / 2)\n" +
                ")\n" +
                "SELECT \n" +
                "    r.ZONE_NAME, r.ZONE_CODE, r.COMM_NAME, r.numerator9b, m.median9b, COALESCE((r.numerator9b / c.s5col11), 0) AS total_score9b, \n" +
                "    rd.numerator_9a, mv.median_9a, rd.total_score9a\n" +
                "FROM CTE_Ripe_Disposal AS r\n" +
                "LEFT JOIN CTE_Ripe_Closing AS c ON r.ZONE_NAME = c.ZONE_NAME AND r.ZONE_CODE = c.ZONE_CODE AND r.COMM_NAME = c.COMM_NAME\n" +
                "CROSS JOIN CTE_Median AS m\n" +
                "LEFT JOIN OrderedData AS rd ON r.ZONE_NAME = rd.ZONE_NAME AND r.ZONE_CODE = rd.ZONE_CODE AND r.COMM_NAME = rd.COMM_NAME\n" +
                "CROSS JOIN MedianValue AS mv \n" +
                "WHERE r.ZONE_CODE = '" + zone_code + "'\n" +
                "ORDER BY total_score9b DESC;\n";
        return query_assessment_cus9;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_DisposalOfConfiscatedGoldAndNDPS_9_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus9 = "WITH RipeDisposalCTE AS (\r\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(mis.RIPE_DISPOSAL) AS numerator_9b\r\n"
                + "    FROM MIS_DOL_CUS_3 AS mis\r\n"
                + "    RIGHT JOIN mis_gst_commcode AS cc ON mis.COMM_CODE = cc.COMM_CODE\r\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                + "    WHERE mis.MM_YYYY = '" + month_date + "'\r\n"
                + "    GROUP BY cc.ZONE_CODE\r\n"
                + "),\r\n"
                + "RipeClosingCTE AS (\r\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(mis.RIPE_CLOSING) AS s5col11\r\n"
                + "    FROM MIS_DOL_CUS_3 AS mis\r\n"
                + "    RIGHT JOIN mis_gst_commcode AS cc ON mis.COMM_CODE = cc.COMM_CODE\r\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                + "    WHERE mis.MM_YYYY = '" + prev_month_new + "'\r\n"
                + "    GROUP BY cc.ZONE_CODE\r\n"
                + "),\r\n"
                + "RankedDisposalCTE AS (\r\n"
                + "    SELECT ZONE_NAME, ZONE_CODE, numerator_9b,\r\n"
                + "           ROW_NUMBER() OVER (ORDER BY numerator_9b) AS RowAsc,\r\n"
                + "           ROW_NUMBER() OVER (ORDER BY numerator_9b DESC) AS RowDesc\r\n"
                + "    FROM RipeDisposalCTE\r\n"
                + "),\r\n"
                + "MedianDisposalCTE AS (\r\n"
                + "    SELECT AVG(numerator_9b) AS MedianValue\r\n"
                + "    FROM RankedDisposalCTE\r\n"
                + "    WHERE RowAsc = RowDesc OR RowAsc + 1 = RowDesc\r\n"
                + "),\r\n"
                + "main_query AS (\r\n"
                + "    SELECT\r\n"
                + "        zc.ZONE_NAME,\r\n"
                + "        cc.ZONE_CODE,\r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s3col9,\r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END) AS s3col12,\r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + prev_month_new + "' THEN CB_QUAN ELSE 0 END) AS s3col3,\r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s6col9,\r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END) AS s6col12,\r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + prev_month_new + "' THEN CB_QUAN ELSE 0 END) AS s6col3,\r\n"
                + "        (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) +\r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END) +\r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) +\r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END)) AS numerator_9a,\r\n"
                + "        (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) +\r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END) +\r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) +\r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END)) /\r\n"
                + "        (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + prev_month_new + "' THEN CB_QUAN ELSE 0 END) +\r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + prev_month_new + "' THEN CB_QUAN ELSE 0 END)) AS total_score,\r\n"
                + "        CONCAT(\r\n"
                + "            (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) +\r\n"
                + "             SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END) +\r\n"
                + "             SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) +\r\n"
                + "             SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "'THEN PARTY_QUAN ELSE 0 END)),\r\n"
                + "            '/',\r\n"
                + "            (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + prev_month_new + "' THEN CB_QUAN ELSE 0 END) +\r\n"
                + "             SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + prev_month_new + "' THEN CB_QUAN ELSE 0 END))\r\n"
                + "        ) AS absolute_value\r\n"
                + "    FROM\r\n"
                + "        mis_gst_commcode AS cc\r\n"
                + "        RIGHT JOIN mis_dol_cus_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\r\n"
                + "        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                + "    WHERE\r\n"
                + "        14c.MM_YYYY IN ('" + month_date + "', '" + prev_month_new + "') AND\r\n"
                + "        14c.COMMODITY_CODE IN (3, 6)\r\n"
                + "    GROUP BY\r\n"
                + "        zc.ZONE_NAME, cc.ZONE_CODE\r\n"
                + "),\r\n"
                + "RankedQuery AS (\r\n"
                + "    SELECT *, ROW_NUMBER() OVER (ORDER BY numerator_9a) AS row_num, COUNT(*) OVER () AS total_rows\r\n"
                + "    FROM main_query\r\n"
                + "),\r\n"
                + "MedianQuery AS (\r\n"
                + "    SELECT AVG(numerator_9a) AS median_9a\r\n"
                + "    FROM RankedQuery\r\n"
                + "    WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\r\n"
                + ")\r\n"
                + "SELECT \r\n"
                + "    rq.ZONE_NAME,\r\n"
                + "    rq.ZONE_CODE,\r\n"
                + "    rq.numerator_9a AS numerator,\r\n"
                + "    COALESCE(mq.median_9a, 0) AS Median,\r\n"
                + "    rq.total_score,\r\n"
                + "    rq.absolute_value,\r\n"
                + "    'CUS9A' AS custom\r\n"
                + "FROM RankedQuery rq, MedianQuery mq\r\n"
                + "WHERE rq.ZONE_CODE = '"+zone_code+"'\r\n"
                + "UNION ALL\r\n"
                + "SELECT \r\n"
                + "    rd.ZONE_NAME, \r\n"
                + "    rd.ZONE_CODE, \r\n"
                + "    rd.numerator_9b AS numerator, \r\n"
                + "    COALESCE(m.MedianValue, 0) AS Median,\r\n"
                + "    COALESCE(rd.numerator_9b / rc.s5col11, 0)  AS total_score,\r\n"
                + "    CONCAT(rd.numerator_9b, '/', rc.s5col11) AS absolute_value,\r\n"
                + "    'CUS9B' AS custom\r\n"
                + "FROM RipeDisposalCTE rd\r\n"
                + "LEFT JOIN RipeClosingCTE rc ON rd.ZONE_CODE = rc.ZONE_CODE\r\n"
                + "CROSS JOIN MedianDisposalCTE m\r\n"
                + "WHERE rd.ZONE_CODE = '"+zone_code+"'\r\n"
                + "ORDER BY custom , total_score DESC;\r\n"
                + "";
        return query_assessment_cus9;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_DisposalOfConfiscatedGoldAndNDPS_9_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus9 = "WITH RankedData AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, \n" +
                "           SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY= '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) AS s3col9, \n" +
                "           SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY= '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END) AS s3col12, \n" +
                "           SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY= '" + prev_month_new + "' THEN c.CB_QUAN ELSE 0 END) AS s3col3,\n" +
                "           SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY= '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) AS s6col9, \n" +
                "           SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY= '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END) AS s6col12, \n" +
                "           SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY= '" + prev_month_new + "' THEN c.CB_QUAN ELSE 0 END) AS s6col3,\n" +
                "           -- numerator_9a as the sum of relevant columns\n" +
                "           (\n" +
                "             SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY= '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) + \n" +
                "             SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY= '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END) + \n" +
                "             SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY= '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) + \n" +
                "             SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY= '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END)\n" +
                "           ) AS numerator_9a,\n" +
                "           -- total_score calculation\n" +
                "           COALESCE((\n" +
                "             (SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY= '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) + \n" +
                "              SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY= '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END) + \n" +
                "              SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY= '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) + \n" +
                "              SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY= '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END)) \n" +
                "             / \n" +
                "             (SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY= '" + prev_month_new + "' THEN c.CB_QUAN ELSE 0 END) + \n" +
                "              SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY= '" + prev_month_new + "' THEN c.CB_QUAN ELSE 0 END))),0\n" +
                "           ) AS total_score9a\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dol_cus_1 AS c ON cc.COMM_CODE = c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE c.MM_YYYY IN ('" + month_date + "', '" + prev_month_new + "') \n" +
                "      AND c.COMMODITY_CODE IN (3, 6)\n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME\n" +
                "),\n" +
                "OrderedData AS (\n" +
                "    SELECT *,\n" +
                "           ROW_NUMBER() OVER (ORDER BY numerator_9a) AS row_num,\n" +
                "           COUNT(*) OVER() AS total_rows FROM RankedData\n" +
                "),\n" +
                "MedianValue AS (\n" +
                "    SELECT AVG(numerator_9a) AS median_9a\n" +
                "    FROM OrderedData WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                "),\n" +
                "CTE_Ripe_Disposal AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, SUM(14c.RIPE_DISPOSAL) AS numerator9b\n" +
                "    FROM MIS_DOL_CUS_3 AS 14c\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME\n" +
                "),\n" +
                "CTE_Ripe_Closing AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, SUM(14c.RIPE_CLOSING) AS s5col11\n" +
                "    FROM MIS_DOL_CUS_3 AS 14c\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME\n" +
                "),\n" +
                "CTE_Median AS (\n" +
                "    SELECT AVG(numerator9b) AS median9b\n" +
                "    FROM (\n" +
                "        SELECT numerator9b, @row_num := @row_num + 1 AS row_num, @total_rows := @total_rows + 1 AS total_rows\n" +
                "        FROM CTE_Ripe_Disposal, (SELECT @row_num := 0, @total_rows := 0) AS vars\n" +
                "        ORDER BY numerator9b\n" +
                "    ) AS sorted\n" +
                "    WHERE row_num IN ((total_rows + 1) / 2, (total_rows + 2) / 2)\n" +
                ")\n" +
                "SELECT \n" +
                "    r.ZONE_NAME, r.ZONE_CODE, r.COMM_NAME, r.numerator9b, m.median9b, COALESCE((r.numerator9b / c.s5col11), 0) AS total_score9b, \n" +
                "    rd.numerator_9a, mv.median_9a, rd.total_score9a\n" +
                "FROM CTE_Ripe_Disposal AS r\n" +
                "LEFT JOIN CTE_Ripe_Closing AS c ON r.ZONE_NAME = c.ZONE_NAME AND r.ZONE_CODE = c.ZONE_CODE AND r.COMM_NAME = c.COMM_NAME\n" +
                "CROSS JOIN CTE_Median AS m\n" +
                "LEFT JOIN OrderedData AS rd ON r.ZONE_NAME = rd.ZONE_NAME AND r.ZONE_CODE = rd.ZONE_CODE AND r.COMM_NAME = rd.COMM_NAME\n" +
                "CROSS JOIN MedianValue AS mv ORDER BY total_score9b DESC;\n";
        return query_assessment_cus9;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_DisposalOfConfiscatedGoldAndNDPS_9_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus9 = "WITH RankedData AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
                "        (\n" +
                "            SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY = '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) +\n" +
                "            SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY = '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END) +\n" +
                "            SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY = '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) +\n" +
                "            SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY = '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END)\n" +
                "        ) AS numerator,\n" +
                "        COALESCE((\n" +
                "            (SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY = '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) +\n" +
                "            SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY = '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END) +\n" +
                "            SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY = '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) +\n" +
                "            SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY = '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END))\n" +
                "            /\n" +
                "            (SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY = '" + prev_month_new + "' THEN c.CB_QUAN ELSE 0 END) +\n" +
                "            SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY = '" + prev_month_new + "' THEN c.CB_QUAN ELSE 0 END))), 0\n" +
                "        ) AS total_score,\n" +
                "        CONCAT(\n" +
                "            (\n" +
                "                SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY = '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) +\n" +
                "                SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY = '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END) +\n" +
                "                SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY = '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) +\n" +
                "                SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY = '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END)\n" +
                "            ), '/', \n" +
                "            (\n" +
                "                SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY = '" + prev_month_new + "' THEN c.CB_QUAN ELSE 0 END) +\n" +
                "                SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY = '" + prev_month_new + "' THEN c.CB_QUAN ELSE 0 END)\n" +
                "            )\n" +
                "        ) AS absvl\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dol_cus_1 AS c ON cc.COMM_CODE = c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE c.MM_YYYY IN ('" + month_date + "', '" + prev_month_new + "')\n" +
                "    AND c.COMMODITY_CODE IN (3, 6)\n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME\n" +
                "),\n" +
                "OrderedData AS (\n" +
                "    SELECT ZONE_NAME, ZONE_CODE, COMM_NAME, numerator, total_score, absvl,\n" +
                "           ROW_NUMBER() OVER (ORDER BY numerator) AS row_num,\n" +
                "           COUNT(*) OVER() AS total_rows\n" +
                "    FROM RankedData\n" +
                "),\n" +
                "MedianValue AS (\n" +
                "    SELECT AVG(numerator) AS median\n" +
                "    FROM OrderedData\n" +
                "    WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                "),\n" +
                "CTE_Ripe_Disposal AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, SUM(c14.RIPE_DISPOSAL) AS numerator\n" +
                "    FROM MIS_DOL_CUS_3 AS c14\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON c14.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE c14.MM_YYYY = '" + month_date + "'\n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME\n" +
                "),\n" +
                "CTE_Ripe_Closing AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, SUM(c14.RIPE_CLOSING) AS s5col11\n" +
                "    FROM MIS_DOL_CUS_3 AS c14\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON c14.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE c14.MM_YYYY = '" + prev_month_new + "'\n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME\n" +
                "),\n" +
                "CTE_Median AS (\n" +
                "    SELECT AVG(numerator) AS median\n" +
                "    FROM (\n" +
                "        SELECT numerator, @row_num := @row_num + 1 AS row_num, @total_rows := @total_rows + 1 AS total_rows\n" +
                "        FROM CTE_Ripe_Disposal, (SELECT @row_num := 0, @total_rows := 0) AS vars\n" +
                "        ORDER BY numerator\n" +
                "    ) AS sorted\n" +
                "    WHERE row_num IN ((total_rows + 1) / 2, (total_rows + 2) / 2)  \n" +
                ")\n" +
                "\n" +
                "SELECT rd.ZONE_NAME, rd.ZONE_CODE, rd.COMM_NAME, rd.numerator, mv.median, rd.total_score, rd.absvl, 'GST9A' AS gst\n" +
                "FROM OrderedData rd\n" +
                "CROSS JOIN MedianValue mv\n" +
                "WHERE rd.ZONE_CODE = '" + zone_code + "' AND rd.COMM_NAME = '" + come_name + "'\n" +
                "\n" +
                "UNION ALL\n" +
                "\n" +
                "SELECT r.ZONE_NAME, r.ZONE_CODE, r.COMM_NAME, r.numerator, m.median, COALESCE(r.numerator / c.s5col11, 0) AS total_score, \n" +
                "CONCAT(r.numerator, '/', c.s5col11) AS absvl, 'GST9B' AS gst\n" +
                "FROM CTE_Ripe_Disposal AS r\n" +
                "LEFT JOIN CTE_Ripe_Closing AS c ON r.ZONE_NAME = c.ZONE_NAME AND r.ZONE_CODE = c.ZONE_CODE AND r.COMM_NAME = c.COMM_NAME\n" +
                "CROSS JOIN CTE_Median AS m\n" +
                "WHERE r.ZONE_CODE = '" + zone_code + "' AND r.COMM_NAME = '" + come_name + "'\n" +
                "\n" +
                "UNION ALL\n" +
                "\n" +
                "SELECT r.ZONE_NAME, r.ZONE_CODE, r.COMM_NAME, r.numerator, m.median, COALESCE(r.numerator / c.s5col11, 0) AS total_score, \n" +
                "CONCAT(r.numerator, '/', c.s5col11) AS absvl, 'GST9B' AS gst\n" +
                "FROM CTE_Ripe_Disposal AS r\n" +
                "RIGHT JOIN CTE_Ripe_Closing AS c ON r.ZONE_NAME = c.ZONE_NAME AND r.ZONE_CODE = c.ZONE_CODE AND r.COMM_NAME = c.COMM_NAME\n" +
                "CROSS JOIN CTE_Median AS m\n" +
                "WHERE r.ZONE_NAME IS NULL AND r.ZONE_CODE = '" + zone_code + "' AND r.COMM_NAME = '" + come_name + "';\n";
        return query_assessment_cus9;
    }

    // ***********************************CUS 10 parameter wise(Recovery Of Arrears) *****************************************************************
    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_RecoveryOfArrears_10_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus10 = "";
        return query_assessment_cus10;
    }
    // for 2no url , all india rank will show in this query
    public String QueryFor_RecoveryOfArrears_10_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus10 = "";
        return query_assessment_cus10;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_RecoveryOfArrears_10_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus10 = "";
        return query_assessment_cus10;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_RecoveryOfArrears_10_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus10 = "";
        return query_assessment_cus10;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_RecoveryOfArrears_10_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus10 = "";
        return query_assessment_cus10;
    }

    // ***********************************CUS 11 parameter wise(Management of Warehousing bonds) *****************************************************************
    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_ManagementOfWarehousingBonds_11_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus11 = "";
        return query_assessment_cus11;
    }
    // for 2no url , all india rank will show in this query
    public String QueryFor_ManagementOfWarehousingBonds_11_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus11 = "";
        return query_assessment_cus11;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_ManagementOfWarehousingBonds_11_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus11 = "";
        return query_assessment_cus11;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_ManagementOfWarehousingBonds_11_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus11 = "";
        return query_assessment_cus11;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_ManagementOfWarehousingBonds_11_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus11 = "";
        return query_assessment_cus11;
    }

    // ***********************************CUS 12 parameter wise(Commissioner (Appeals)) *****************************************************************
    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_CommissionerAppeals_12_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus12 = "WITH CTE1 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.DISPOSAL_NO) AS s5col13_T1,SUM(14c.DISPOSAL_TRANSFER_NO) AS s5col17_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 5 GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "CTE2 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.CLOSING_NO) AS s5col3_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND FORUM_CODE = 5 GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "CTE3 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.DISPOSAL_NO) AS s5col9_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 5 GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "CTE4 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.CLOSING_NO) AS s5col3_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND FORUM_CODE = 5 GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "CTE5 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.CLOSING_NO) AS s5col29_T1, SUM(14c.AGEWISE_1) AS s5col31_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 5 GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "CTE6 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.CLOSING_NO) AS s5col23_T2,SUM(14c.AGEWISE_1) AS s5col25_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 5 GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "FinalData AS (\n" +
                "    SELECT \n" +
                "        COALESCE(A.ZONE_NAME, B.ZONE_NAME, C.ZONE_NAME, D.ZONE_NAME) AS ZONE_NAME,\n" +
                "        COALESCE(A.ZONE_CODE, B.ZONE_CODE, C.ZONE_CODE, D.ZONE_CODE) AS ZONE_CODE,\n" +
                "        (A.s5col13_T1 + A.s5col17_T1 + C.s5col9_T2) AS numerator12A,\n" +
                "        COALESCE(((A.s5col13_T1 + A.s5col17_T1 + C.s5col9_T2) / (B.s5col3_T1 + D.s5col3_T2)), 0) *100 AS Total_score12A,\n" +
                "        COALESCE(((E.s5col29_T1 - E.s5col31_T1) + (F.s5col23_T2 - F.s5col25_T2)) / (E.s5col29_T1 + F.s5col23_T2),0) *100 AS total_score12B\n" +
                "    FROM CTE1 AS A\n" +
                "    LEFT JOIN CTE2 AS B ON A.ZONE_CODE = B.ZONE_CODE\n" +
                "    LEFT JOIN CTE3 AS C ON A.ZONE_CODE = C.ZONE_CODE\n" +
                "    LEFT JOIN CTE4 AS D ON A.ZONE_CODE = D.ZONE_CODE\n" +
                "    LEFT JOIN CTE5 AS E ON A.ZONE_CODE = E.ZONE_CODE\n" +
                "    LEFT JOIN CTE6 AS F ON A.ZONE_CODE = F.ZONE_CODE\n" +
                "\n" +
                "    UNION ALL\n" +
                "\n" +
                "    SELECT \n" +
                "        COALESCE(A.ZONE_NAME, B.ZONE_NAME, C.ZONE_NAME, D.ZONE_NAME) AS ZONE_NAME,\n" +
                "        COALESCE(A.ZONE_CODE, B.ZONE_CODE, C.ZONE_CODE, D.ZONE_CODE) AS ZONE_CODE,\n" +
                "        (A.s5col13_T1 + A.s5col17_T1 + C.s5col9_T2) AS numerator12A,\n" +
                "        COALESCE(((A.s5col13_T1 + A.s5col17_T1 + C.s5col9_T2) / (B.s5col3_T1 + D.s5col3_T2)), 0) AS Total_score12A,\n" +
                "        COALESCE(((E.s5col29_T1 - E.s5col31_T1) + (F.s5col23_T2 - F.s5col25_T2)) / (E.s5col29_T1 + F.s5col23_T2),0) AS total_score12B\n" +
                "    FROM CTE1 AS A\n" +
                "    RIGHT JOIN CTE2 AS B ON A.ZONE_CODE = B.ZONE_CODE\n" +
                "    RIGHT JOIN CTE3 AS C ON A.ZONE_CODE = C.ZONE_CODE\n" +
                "    RIGHT JOIN CTE4 AS D ON A.ZONE_CODE = D.ZONE_CODE\n" +
                "    RIGHT JOIN CTE5 AS E ON A.ZONE_CODE = E.ZONE_CODE\n" +
                "    RIGHT JOIN CTE6 AS F ON A.ZONE_CODE = F.ZONE_CODE\n" +
                "    WHERE A.ZONE_CODE IS NULL\n" +
                ")\n" +
                "SELECT *,\n" +
                "       (SELECT AVG(numerator12A) AS median12A \n" +
                "        FROM (SELECT numerator12A, ROW_NUMBER() OVER (ORDER BY numerator12A) AS rn, COUNT(*) OVER () AS total_count\n" +
                "            FROM FinalData) AS subquery\n" +
                "        WHERE rn IN ((total_count + 1) / 2, (total_count + 2) / 2)) AS median12A\n" +
                "FROM FinalData;";
        return query_assessment_cus12;
    }
    // for 2no url , all india rank will show in this query
    public String QueryFor_CommissionerAppeals_12_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus12 = "WITH cte_1 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, COALESCE(14c.DISPOSAL_NO, 0) AS s5col13_T1, \n" +
                "           COALESCE(14c.DISPOSAL_TRANSFER_NO, 0) AS s5col17_T1, COALESCE(14c.CLOSING_NO, 0) AS s5col29_T1, COALESCE(14c.AGEWISE_1, 0) AS s5col31_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND 14c.FORUM_CODE = 5\n" +
                "),\n" +
                "cte_2 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, COALESCE(14c.CLOSING_NO, 0) AS s5col3_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND 14c.FORUM_CODE = 5\n" +
                "),\n" +
                "cte_3 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, COALESCE(14c.DISPOSAL_NO, 0) AS s5col9_T2, \n" +
                "    COALESCE(14c.CLOSING_NO, 0) AS s5col23_T2, COALESCE(14c.AGEWISE_1, 0) AS s5col25_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND 14c.FORUM_CODE = 5\n" +
                "),\n" +
                "cte_4 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, COALESCE(14c.CLOSING_NO, 0) AS s5col3_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND 14c.FORUM_CODE = 5\n" +
                "),\n" +
                "cte_main AS (\n" +
                "    SELECT cte_1.ZONE_NAME, cte_1.ZONE_CODE, cte_1.COMM_NAME, \n" +
                "           (cte_1.s5col13_T1 + cte_1.s5col17_T1 + cte_3.s5col9_T2) AS numerator12A,\n" +
                "           COALESCE(((cte_1.s5col13_T1 + cte_1.s5col17_T1 + cte_3.s5col9_T2) / (cte_2.s5col3_T1 + cte_4.s5col3_T2)), 0) AS total_score12A,\n" +
                "           COALESCE(((cte_1.s5col29_T1 - cte_1.s5col31_T1) + (cte_3.s5col23_T2 - cte_3.s5col25_T2)) / (cte_1.s5col29_T1 + cte_3.s5col23_T2), 0) AS total_score12B\n" +
                "    FROM cte_1\n" +
                "    LEFT JOIN cte_2 ON cte_1.ZONE_CODE = cte_2.ZONE_CODE AND cte_1.COMM_NAME = cte_2.COMM_NAME\n" +
                "    LEFT JOIN cte_3 ON cte_1.ZONE_CODE = cte_3.ZONE_CODE AND cte_1.COMM_NAME = cte_3.COMM_NAME\n" +
                "    LEFT JOIN cte_4 ON cte_1.ZONE_CODE = cte_4.ZONE_CODE AND cte_1.COMM_NAME = cte_4.COMM_NAME\n" +
                ")\n" +
                "SELECT cte_main.ZONE_NAME, cte_main.ZONE_CODE, cte_main.COMM_NAME, \n" +
                "       cte_main.numerator12A, cte_main.total_score12A, cte_main.total_score12B,\n" +
                "       (SELECT AVG(subquery.numerator12A) AS median12A\n" +
                "        FROM (\n" +
                "            SELECT numerator12A, \n" +
                "                   ROW_NUMBER() OVER (ORDER BY numerator12A) AS row_num, \n" +
                "                   COUNT(*) OVER () AS total_rows\n" +
                "            FROM cte_main) AS subquery\n" +
                "        WHERE subquery.row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                "       ) AS median12A \n" +
                "FROM cte_main\n" +
                "WHERE cte_main.ZONE_CODE = '" + zone_code + "';";
        return query_assessment_cus12;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_CommissionerAppeals_12_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus12 = "WITH CTE1 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.DISPOSAL_NO) AS s5col13_T1,SUM(14c.DISPOSAL_TRANSFER_NO) AS s5col17_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 5 \n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "CTE2 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.CLOSING_NO) AS s5col3_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND FORUM_CODE = 5 \n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "CTE3 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.DISPOSAL_NO) AS s5col9_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 5 \n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "CTE4 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.CLOSING_NO) AS s5col3_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND FORUM_CODE = 5 \n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "FinalData AS (\n" +
                "    SELECT \n" +
                "        COALESCE(A.ZONE_NAME, B.ZONE_NAME, C.ZONE_NAME, D.ZONE_NAME) AS ZONE_NAME,\n" +
                "        COALESCE(A.ZONE_CODE, B.ZONE_CODE, C.ZONE_CODE, D.ZONE_CODE) AS ZONE_CODE,\n" +
                "        (A.s5col13_T1 + A.s5col17_T1 + C.s5col9_T2) AS numerator,\n" +
                "        concat((A.s5col13_T1 + A.s5col17_T1 + C.s5col9_T2),'/',(B.s5col3_T1 + D.s5col3_T2)) as absvl,\n" +
                "        COALESCE(((A.s5col13_T1 + A.s5col17_T1 + C.s5col9_T2) / (B.s5col3_T1 + D.s5col3_T2)), 0) AS total_score, \"GST12A\" AS GST\n" +
                "    FROM CTE1 AS A\n" +
                "    LEFT JOIN CTE2 AS B ON A.ZONE_CODE = B.ZONE_CODE\n" +
                "    LEFT JOIN CTE3 AS C ON A.ZONE_CODE = C.ZONE_CODE\n" +
                "    LEFT JOIN CTE4 AS D ON A.ZONE_CODE = D.ZONE_CODE\n" +
                "\n" +
                "    UNION ALL\n" +
                "\n" +
                "    SELECT \n" +
                "        COALESCE(A.ZONE_NAME, B.ZONE_NAME, C.ZONE_NAME, D.ZONE_NAME) AS ZONE_NAME,\n" +
                "        COALESCE(A.ZONE_CODE, B.ZONE_CODE, C.ZONE_CODE, D.ZONE_CODE) AS ZONE_CODE,\n" +
                "        (A.s5col13_T1 + A.s5col17_T1 + C.s5col9_T2) AS numerator,\n" +
                "        concat((A.s5col13_T1 + A.s5col17_T1 + C.s5col9_T2),'/',(B.s5col3_T1 + D.s5col3_T2)) as absvl,\n" +
                "        COALESCE(((A.s5col13_T1 + A.s5col17_T1 + C.s5col9_T2) / (B.s5col3_T1 + D.s5col3_T2)), 0) AS total_score, \"GST12A\" AS GST\n" +
                "    FROM CTE1 AS A\n" +
                "    RIGHT JOIN CTE2 AS B ON A.ZONE_CODE = B.ZONE_CODE\n" +
                "    RIGHT JOIN CTE3 AS C ON A.ZONE_CODE = C.ZONE_CODE\n" +
                "    RIGHT JOIN CTE4 AS D ON A.ZONE_CODE = D.ZONE_CODE\n" +
                "    WHERE A.ZONE_CODE IS NULL\n" +
                "),\n" +
                "CTE5 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.CLOSING_NO) AS s5col29_T1, SUM(14c.AGEWISE_1) AS s5col31_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 5\n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "CTE6 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.CLOSING_NO) AS s5col23_T2,SUM(14c.AGEWISE_1) AS s5col25_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 5\n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                ")\n" +
                "\n" +
                "SELECT ZONE_NAME, ZONE_CODE, numerator, total_score, GST, median ,absvl\n" +
                "FROM (\n" +
                "    SELECT \n" +
                "        ZONE_NAME, ZONE_CODE, numerator,absvl, total_score, GST,\n" +
                "        (SELECT AVG(numerator) AS median \n" +
                "         FROM (SELECT numerator,ROW_NUMBER() OVER (ORDER BY numerator) AS rn,COUNT(*) OVER () AS total_count\n" +
                "               FROM FinalData\n" +
                "              ) AS subquery\n" +
                "         WHERE rn IN ((total_count + 1) / 2, (total_count + 2) / 2)\n" +
                "        ) AS median \n" +
                "    FROM FinalData\n" +
                "\n" +
                "    UNION ALL\n" +
                "\n" +
                "    SELECT \n" +
                "        c1.ZONE_NAME, c1.ZONE_CODE, 0 AS numerator,\n" +
                "        concat(((c1.s5col29_T1 - c1.s5col31_T1)+(c2.s5col23_T2 - c2.s5col25_T2)),'/',(c1.s5col29_T1 + c2.s5col23_T2)) as absvl,\n" +
                "        COALESCE((((c1.s5col29_T1 - c1.s5col31_T1) + (c2.s5col23_T2 - c2.s5col25_T2)) / (c1.s5col29_T1 + c2.s5col23_T2)),0) AS total_score, \n" +
                "        \"GST12B\" AS GST, 0 AS median\n" +
                "    FROM CTE5 AS c1\n" +
                "    JOIN CTE6 AS c2 ON c1.ZONE_CODE = c2.ZONE_CODE\n" +
                ") AS merged_result where ZONE_CODE = '" + zone_code + "';";
        return query_assessment_cus12;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_CommissionerAppeals_12_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus12 = "WITH cte_1 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, \n" +
                "           COALESCE(14c.DISPOSAL_NO, 0) AS s5col13_T1, \n" +
                "           COALESCE(14c.DISPOSAL_TRANSFER_NO, 0) AS s5col17_T1, \n" +
                "           COALESCE(14c.CLOSING_NO, 0) AS s5col29_T1, \n" +
                "           COALESCE(14c.AGEWISE_1, 0) AS s5col31_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND 14c.FORUM_CODE = 5\n" +
                "),\n" +
                "cte_2 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, \n" +
                "           COALESCE(14c.CLOSING_NO, 0) AS s5col3_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND 14c.FORUM_CODE = 5\n" +
                "),\n" +
                "cte_3 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, \n" +
                "           COALESCE(14c.DISPOSAL_NO, 0) AS s5col9_T2, \n" +
                "           COALESCE(14c.CLOSING_NO, 0) AS s5col23_T2, \n" +
                "           COALESCE(14c.AGEWISE_1, 0) AS s5col25_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND 14c.FORUM_CODE = 5\n" +
                "),\n" +
                "cte_4 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, \n" +
                "           COALESCE(14c.CLOSING_NO, 0) AS s5col3_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND 14c.FORUM_CODE = 5\n" +
                "),\n" +
                "cte_main AS (\n" +
                "    SELECT cte_1.ZONE_NAME, cte_1.ZONE_CODE, cte_1.COMM_NAME, \n" +
                "           (cte_1.s5col13_T1 + cte_1.s5col17_T1 + cte_3.s5col9_T2) AS numerator12A,\n" +
                "           COALESCE(((cte_1.s5col13_T1 + cte_1.s5col17_T1 + cte_3.s5col9_T2) / \n" +
                "                     (cte_2.s5col3_T1 + cte_4.s5col3_T2)), 0) AS total_score12A,\n" +
                "           COALESCE(((cte_1.s5col29_T1 - cte_1.s5col31_T1) + (cte_3.s5col23_T2 - cte_3.s5col25_T2)) / \n" +
                "                     (cte_1.s5col29_T1 + cte_3.s5col23_T2), 0) AS total_score12B\n" +
                "    FROM cte_1\n" +
                "    LEFT JOIN cte_2 ON cte_1.ZONE_CODE = cte_2.ZONE_CODE AND cte_1.COMM_NAME = cte_2.COMM_NAME\n" +
                "    LEFT JOIN cte_3 ON cte_1.ZONE_CODE = cte_3.ZONE_CODE AND cte_1.COMM_NAME = cte_3.COMM_NAME\n" +
                "    LEFT JOIN cte_4 ON cte_1.ZONE_CODE = cte_4.ZONE_CODE AND cte_1.COMM_NAME = cte_4.COMM_NAME\n" +
                ")\n" +
                "SELECT cte_main.ZONE_NAME, cte_main.ZONE_CODE, cte_main.COMM_NAME, \n" +
                "       cte_main.numerator12A, cte_main.total_score12A, cte_main.total_score12B,\n" +
                "       (SELECT AVG(subquery.numerator12A) AS median12A\n" +
                "        FROM (\n" +
                "            SELECT numerator12A, \n" +
                "                   ROW_NUMBER() OVER (ORDER BY numerator12A) AS row_num, \n" +
                "                   COUNT(*) OVER () AS total_rows\n" +
                "            FROM cte_main) AS subquery\n" +
                "        WHERE subquery.row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                "       ) AS median12A \n" +
                "FROM cte_main;\n";
        return query_assessment_cus12;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_CommissionerAppeals_12_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus12 = "WITH cte_1 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, COALESCE(14c.DISPOSAL_NO, 0) AS s5col13_T1, COALESCE(14c.DISPOSAL_TRANSFER_NO, 0) AS s5col17_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND 14c.FORUM_CODE = 5\n" +
                "),\n" +
                "cte_2 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, COALESCE(14c.CLOSING_NO, 0) AS s5col3_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND 14c.FORUM_CODE = 5\n" +
                "),\n" +
                "cte_3 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, COALESCE(14c.DISPOSAL_NO, 0) AS s5col9_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND 14c.FORUM_CODE = 5\n" +
                "),\n" +
                "cte_4 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, COALESCE(14c.CLOSING_NO, 0) AS s5col3_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND 14c.FORUM_CODE = 5\n" +
                "),\n" +
                "cte_main AS (\n" +
                "    SELECT cte_1.ZONE_NAME, cte_1.ZONE_CODE, cte_1.COMM_NAME, \n" +
                "           (cte_1.s5col13_T1 + cte_1.s5col17_T1 + cte_3.s5col9_T2) AS numerator,\n" +
                "           CONCAT((cte_1.s5col13_T1 + cte_1.s5col17_T1 + cte_3.s5col9_T2), '/', (cte_2.s5col3_T1 + cte_4.s5col3_T2)) AS absvl,\n" +
                "           \"GST12A\" AS gst,\n" +
                "           COALESCE(((cte_1.s5col13_T1 + cte_1.s5col17_T1 + cte_3.s5col9_T2) / (cte_2.s5col3_T1 + cte_4.s5col3_T2)), 0) AS total_score\n" +
                "    FROM cte_1\n" +
                "    LEFT JOIN cte_2 ON cte_1.ZONE_CODE = cte_2.ZONE_CODE AND cte_1.COMM_NAME = cte_2.COMM_NAME\n" +
                "    LEFT JOIN cte_3 ON cte_1.ZONE_CODE = cte_3.ZONE_CODE AND cte_1.COMM_NAME = cte_3.COMM_NAME\n" +
                "    LEFT JOIN cte_4 ON cte_1.ZONE_CODE = cte_4.ZONE_CODE AND cte_1.COMM_NAME = cte_4.COMM_NAME\n" +
                "),\n" +
                "Query1 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, (14c.CLOSING_NO) AS s5col29_T1, (14c.AGEWISE_1) AS s5col31_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 5\n" +
                "),\n" +
                "Query2 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, (14c.CLOSING_NO) AS s5col23_T2, (14c.AGEWISE_1) AS s5col25_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 5\n" +
                ")\n" +
                "\n" +
                "SELECT cte_main.ZONE_NAME, cte_main.ZONE_CODE, cte_main.COMM_NAME, \n" +
                "       cte_main.numerator, cte_main.absvl, cte_main.gst, cte_main.total_score,\n" +
                "       (SELECT AVG(subquery.numerator) AS median\n" +
                "        FROM (\n" +
                "            SELECT numerator, \n" +
                "                   ROW_NUMBER() OVER (ORDER BY numerator) AS row_num, \n" +
                "                   COUNT(*) OVER () AS total_rows\n" +
                "            FROM cte_main\n" +
                "        ) AS subquery\n" +
                "        WHERE subquery.row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                "       ) AS median \n" +
                "FROM cte_main\n" +
                "WHERE cte_main.ZONE_CODE = '" + zone_code + "' AND cte_main.COMM_NAME = '" + come_name + "'\n" +
                "\n" +
                "UNION ALL\n" +
                "\n" +
                "SELECT q1.ZONE_NAME, q1.ZONE_CODE, q1.COMM_NAME, \n" +
                "       0 AS numerator,\n" +
                "       CONCAT(((q1.s5col29_T1 - q1.s5col31_T1) + (q2.s5col23_T2 - q2.s5col25_T2)), '/', (q1.s5col29_T1 + q2.s5col23_T2)) AS absvl,\n" +
                "       \"GST12B\" AS gst,\n" +
                "       COALESCE((((q1.s5col29_T1 - q1.s5col31_T1) + (q2.s5col23_T2 - q2.s5col25_T2)) / (q1.s5col29_T1 + q2.s5col23_T2)), 0) AS total_score,\n" +
                "       0 AS median\n" +
                "FROM Query1 q1\n" +
                "JOIN Query2 q2 ON q1.ZONE_CODE = q2.ZONE_CODE AND q1.COMM_NAME = q2.COMM_NAME\n" +
                "WHERE q1.ZONE_CODE = '" + zone_code + "' AND q1.COMM_NAME = '" + come_name + "';\n";
        return query_assessment_cus12;
    }

    // ***********************************CUS 13 parameter wise(Audit) *****************************************************************
    //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
    // this query will show all zone || 1no url
    public String QueryFor_Audit_13_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus13 = "";
        return query_assessment_cus13;
    }
    // for 2no url , all india rank will show in this query
    public String QueryFor_Audit_13_ParticularZoneWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus13 = "";
        return query_assessment_cus13;
    }
    //  for perticular subparameter wise ||  3 no url
    public String QueryFor_Audit_13_ParticularSubparameterWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus13 = "";
        return query_assessment_cus13;
    }
    //  for all commissary wise || for 4no url
    public String QueryFor_Audit_13_AllCommissary(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus13 = "";
        return query_assessment_cus13;
    }
    //  for perticular commissonary in subparameter || for 5no url
    public String QueryFor_Audit_13_ParticularCommissonaryInSubparameter(String month_date, String zone_code,String come_name){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String query_assessment_cus13 = "";
        return query_assessment_cus13;
    }
}
