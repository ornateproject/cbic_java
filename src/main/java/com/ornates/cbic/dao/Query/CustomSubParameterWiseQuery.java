package com.ornates.cbic.dao.Query;
import com.ornates.cbic.service.DateCalculate;
public class CustomSubParameterWiseQuery {

    public String QueryFor_cus1a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom1="SELECT \n"
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
        return queryCustom1;
    }
    public String QueryFor_cus1a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom1="SELECT \n"
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
                + "    AND cc.ZONE_CODE = 71\n"
                + "ORDER BY \n"
                + "    total_score ASC \n"
                + "LIMIT 0, 1000;\n"
                + "";
        return queryCustom1;
    }
    public String QueryFor_cus1a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom1="SELECT \n"
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
        return queryCustom1;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus2a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom2a="";
        return queryCustom2a;
    }
    public String QueryFor_cus2a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom2a="";
        return queryCustom2a;
    }
    public String QueryFor_cus2a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom2a="";
        return queryCustom2a;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus4a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom4a="";
        return queryCustom4a;
    }
    public String QueryFor_cus4a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom4a="";
        return queryCustom4a;
    }
    public String QueryFor_cus4a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom4a="";
        return queryCustom4a;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus4b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom4b="";
        return queryCustom4b;
    }
    public String QueryFor_cus4b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom4b="";
        return queryCustom4b;
    }
    public String QueryFor_cus4b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom4b="";
        return queryCustom4b;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus4c_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom4c="";
        return queryCustom4c;
    }
    public String QueryFor_cus4c_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom4c="";
        return queryCustom4c;
    }
    public String QueryFor_cus4c_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom4c="";
        return queryCustom4c;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus4d_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom4d="SELECT \n"
                + "    zc.ZONE_NAME, \n"
                + "    cc.ZONE_CODE, \n"
                + "    SUM(14c.PENDING_YEAR_1TO2_NO) AS col6, \n"
                + "    SUM(14c.PENDING_MORE_2YEAR_NO) AS col8, \n"
                + "    SUM(14c.PENDING_MONTHS_0TO6_NO) AS col2, \n"
                + "    SUM(14c.PENDING_MONTHS_6TO12_NO) AS col4 \n"
                + "FROM \n"
                + "    MIS_DGI_CUS_5B AS 14c \n"
                + "RIGHT JOIN \n"
                + "    mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n"
                + "LEFT JOIN \n"
                + "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "WHERE \n"
                + "    14c.MM_YYYY = '" + month_date + "' \n"
                + "GROUP BY \n"
                + "    zc.ZONE_NAME, \n"
                + "    cc.ZONE_CODE;\n"
                + "";
        return queryCustom4d;
    }
    public String QueryFor_cus4d_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom4d="SELECT \n"
                + "    zc.ZONE_NAME, \n"
                + "    cc.ZONE_CODE, \n"
                + "    cc.COMM_NAME,   -- Added COMM_NAME here\n"
                + "    14c.PENDING_YEAR_1TO2_NO AS col6, \n"
                + "    14c.PENDING_MORE_2YEAR_NO AS col8, \n"
                + "    14c.PENDING_MONTHS_0TO6_NO AS col2, \n"
                + "    14c.PENDING_MONTHS_6TO12_NO AS col4 \n"
                + "FROM \n"
                + "    MIS_DGI_CUS_5B AS 14c \n"
                + "RIGHT JOIN \n"
                + "    mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n"
                + "LEFT JOIN \n"
                + "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "WHERE \n"
                + "    14c.MM_YYYY = '" + month_date + "' \n"
                + "    AND cc.ZONE_CODE ='"+zone_code+"' -- Added condition for ZONE_CODE = 70\n"
                + "GROUP BY \n"
                + "    zc.ZONE_NAME, \n"
                + "    cc.ZONE_CODE, \n"
                + "    cc.COMM_NAME,  -- Added COMM_NAME to GROUP BY\n"
                + "    14c.PENDING_YEAR_1TO2_NO, \n"
                + "    14c.PENDING_MORE_2YEAR_NO, \n"
                + "    14c.PENDING_MONTHS_0TO6_NO, \n"
                + "    14c.PENDING_MONTHS_6TO12_NO;\n"
                + "";
        return queryCustom4d;
    }
    public String QueryFor_cus4d_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom4d="SELECT \n"
                + "    zc.ZONE_NAME, \n"
                + "    cc.ZONE_CODE, \n"
                + "    cc.COMM_NAME,   -- Added COMM_NAME here\n"
                + "    14c.PENDING_YEAR_1TO2_NO AS col6, \n"
                + "    14c.PENDING_MORE_2YEAR_NO AS col8, \n"
                + "    14c.PENDING_MONTHS_0TO6_NO AS col2, \n"
                + "    14c.PENDING_MONTHS_6TO12_NO AS col4 \n"
                + "FROM \n"
                + "    MIS_DGI_CUS_5B AS 14c \n"
                + "RIGHT JOIN \n"
                + "    mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n"
                + "LEFT JOIN \n"
                + "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "WHERE \n"
                + "    14c.MM_YYYY = '" + month_date + "' \n"
                + "   \n"
                + "GROUP BY \n"
                + "    zc.ZONE_NAME, \n"
                + "    cc.ZONE_CODE, \n"
                + "    cc.COMM_NAME,  -- Added COMM_NAME to GROUP BY\n"
                + "    14c.PENDING_YEAR_1TO2_NO, \n"
                + "    14c.PENDING_MORE_2YEAR_NO, \n"
                + "    14c.PENDING_MONTHS_0TO6_NO, \n"
                + "    14c.PENDING_MONTHS_6TO12_NO;\n"
                + "";
        return queryCustom4d;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus5a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom5a="WITH cte_5a AS (\n"
                + "    SELECT \n"
                + "        zc.ZONE_NAME, \n"
                + "        cc.ZONE_CODE, \n"
                + "        SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO) AS col5a\n"
                + "    FROM \n"
                + "        Mis_DGI_CUS_1A AS 14c\n"
                + "    RIGHT JOIN \n"
                + "        mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n"
                + "    LEFT JOIN \n"
                + "        mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "    WHERE \n"
                + "        14c.MM_YYYY = '" + month_date + "'\n"
                + "    GROUP BY \n"
                + "        zc.ZONE_NAME, cc.ZONE_CODE\n"
                + "),\n"
                + "cte_3a AS (\n"
                + "    SELECT \n"
                + "        zc.ZONE_NAME, \n"
                + "        cc.ZONE_CODE, \n"
                + "        SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO) AS col3a\n"
                + "    FROM \n"
                + "        Mis_DGI_CUS_1A AS 14c\n"
                + "    RIGHT JOIN \n"
                + "        mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n"
                + "    LEFT JOIN \n"
                + "        mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "    WHERE \n"
                + "        14c.MM_YYYY = DATE_SUB('" + month_date + "', INTERVAL 1 MONTH) \n"
                + "    GROUP BY \n"
                + "        zc.ZONE_NAME, cc.ZONE_CODE\n"
                + "),\n"
                + "median_cte AS (\n"
                + "    SELECT \n"
                + "        AVG(col5a) AS cus5a_median\n"
                + "    FROM (\n"
                + "        SELECT col5a, \n"
                + "               ROW_NUMBER() OVER (ORDER BY col5a) AS rn,\n"
                + "               COUNT(*) OVER () AS cnt\n"
                + "        FROM cte_5a\n"
                + "    ) AS temp\n"
                + "    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n"
                + ")\n"
                + "SELECT \n"
                + "    cte_5a.ZONE_NAME, \n"
                + "    cte_5a.ZONE_CODE, \n"
                + "    cte_5a.col5a, \n"
                + "    cte_3a.col3a,\n"
                + "    median_cte.cus5a_median\n"
                + "FROM \n"
                + "    cte_5a\n"
                + "JOIN \n"
                + "    cte_3a ON cte_5a.ZONE_CODE = cte_3a.ZONE_CODE\n"
                + "CROSS JOIN \n"
                + "    median_cte;\n"
                + "";
        return queryCustom5a;
    }
    public String QueryFor_cus5a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom5a="WITH cte AS (\n"
                + "    SELECT \n"
                + "        zc.ZONE_NAME, \n"
                + "        cc.ZONE_CODE, \n"
                + "        cc.COMM_NAME, \n"
                + "        (c.COMM_DISPOSAL_NO + c.JC_DISPOSAL_NO + c.AC_DISPOSAL_NO) AS col5a, \n"
                + "        (c_prev.COMM_CLOSING_NO + c_prev.JC_CLOSING_NO + c_prev.AC_CLOSING_NO) AS col3a\n"
                + "    FROM \n"
                + "        Mis_DGI_CUS_1A AS c\n"
                + "    RIGHT JOIN \n"
                + "        mis_gst_commcode AS cc ON c.COMM_CODE = cc.COMM_CODE\n"
                + "    LEFT JOIN \n"
                + "        mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "    LEFT JOIN \n"
                + "        Mis_DGI_CUS_1A AS c_prev ON c_prev.COMM_CODE = cc.COMM_CODE AND c_prev.MM_YYYY = '" + prev_month_new + "' -- Previous month\n"
                + "    WHERE \n"
                + "        c.MM_YYYY = '" + month_date + "'  -- Current month\n"
                + "),\n"
                + "median_cte AS (\n"
                + "    SELECT \n"
                + "        AVG(col5a) AS cus5a_median\n"
                + "    FROM (\n"
                + "        SELECT \n"
                + "            col5a, \n"
                + "            ROW_NUMBER() OVER (ORDER BY col5a) AS rn,\n"
                + "            COUNT(*) OVER () AS cnt\n"
                + "        FROM cte\n"
                + "    ) AS temp\n"
                + "    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n"
                + ")\n"
                + "SELECT \n"
                + "    cte.ZONE_NAME, \n"
                + "    cte.ZONE_CODE, \n"
                + "    cte.COMM_NAME, \n"
                + "    cte.col5a, \n"
                + "    cte.col3a,\n"
                + "    median_cte.cus5a_median\n"
                + "FROM \n"
                + "    cte\n"
                + "CROSS JOIN \n"
                + "    median_cte\n"
                + "WHERE \n"
                + "    cte.ZONE_CODE = '"+zone_code+"';  -- Filter by ZONE_CODE\n"
                + "";
        return queryCustom5a;
    }
    public String QueryFor_cus5a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom5a="WITH cte AS (\n"
                + "    SELECT \n"
                + "        zc.ZONE_NAME, \n"
                + "        cc.ZONE_CODE, \n"
                + "        cc.COMM_NAME, \n"
                + "        (c.COMM_DISPOSAL_NO + c.JC_DISPOSAL_NO + c.AC_DISPOSAL_NO) AS col5a, \n"
                + "        (c_prev.COMM_CLOSING_NO + c_prev.JC_CLOSING_NO + c_prev.AC_CLOSING_NO) AS col3a\n"
                + "    FROM \n"
                + "        Mis_DGI_CUS_1A AS c\n"
                + "    RIGHT JOIN \n"
                + "        mis_gst_commcode AS cc ON c.COMM_CODE = cc.COMM_CODE\n"
                + "    LEFT JOIN \n"
                + "        mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "    LEFT JOIN \n"
                + "        Mis_DGI_CUS_1A AS c_prev ON c_prev.COMM_CODE = cc.COMM_CODE AND c_prev.MM_YYYY = '" + prev_month_new + "' -- Previous month\n"
                + "    WHERE \n"
                + "        c.MM_YYYY ='" + month_date + "'  -- Current month\n"
                + "),\n"
                + "median_cte AS (\n"
                + "    SELECT \n"
                + "        AVG(col5a) AS cus5a_median\n"
                + "    FROM (\n"
                + "        SELECT \n"
                + "            col5a, \n"
                + "            ROW_NUMBER() OVER (ORDER BY col5a) AS rn,\n"
                + "            COUNT(*) OVER () AS cnt\n"
                + "        FROM cte\n"
                + "    ) AS temp\n"
                + "    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n"
                + ")\n"
                + "SELECT \n"
                + "    cte.ZONE_NAME, \n"
                + "    cte.ZONE_CODE, \n"
                + "    cte.COMM_NAME, \n"
                + "    cte.col5a, \n"
                + "    cte.col3a,\n"
                + "    median_cte.cus5a_median\n"
                + "FROM \n"
                + "    cte\n"
                + "CROSS JOIN \n"
                + "    median_cte;\n"
                + "";
        return queryCustom5a;
    }

    // ********************************************************************************************************************************
    public String QueryFor_cus5b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom5b="SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                " SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) AS col7d, \n" +
                " SUM(14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO) AS col6a\n" +
                " FROM  mis_dgi_cus_1A  AS 14c  RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                " LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                " WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE ;";
        return queryCustom5b;
    }
    public String QueryFor_cus5b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom5b="SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
                " (14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) AS col7d, \n" +
                " (14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO) AS col6a\n" +
                " FROM  mis_dgi_cus_1A  AS 14c  RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                " LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                " WHERE 14c.MM_YYYY ='" + month_date + "' and cc.ZONE_CODE = '"+zone_code+"';";
        return queryCustom5b;
    }
    public String QueryFor_cus5b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom5b="SELECT zc.ZONE_NAME, cc.ZONE_CODE,cc.COMM_NAME,\n" +
                "(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) AS col7d, \n" +
                " (14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO) AS col6a\n" +
                " FROM  mis_dgi_cus_1A  AS 14c  RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                " LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                " WHERE 14c.MM_YYYY = '" + month_date + "';";
        return queryCustom5b;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus5c_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom5c="SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col7, \n"
                + "        SUM(14c.YEAR_1) AS col9,((SUM(14c.CLOSING_NO) - SUM(14c.YEAR_1)) / SUM(14c.CLOSING_NO)) as total_score,\n"
                + "        CONCAT((SUM(14c.CLOSING_NO) - SUM(14c.YEAR_1)), '/', SUM(14c.CLOSING_NO)) as absval\n"
                + "    FROM mis_gst_commcode AS cc \n"
                + "    RIGHT JOIN mis_dgi_cus_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "    WHERE 14c.MM_YYYY = '" + month_date + "'\n"
                + "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE;";
        return queryCustom5c;
    }
    public String QueryFor_cus5c_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom5c="SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, (14c.CLOSING_NO) AS col7, \n" +
                "        (14c.YEAR_1) AS col9,(((14c.CLOSING_NO) - (14c.YEAR_1)) / (14c.CLOSING_NO)) as total_score,\n" +
                "        CONCAT(((14c.CLOSING_NO) - (14c.YEAR_1)), '/', (14c.CLOSING_NO)) as absval\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dgi_cus_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' and cc.ZONE_CODE = '"+zone_code+"';";
        return queryCustom5c;
    }
    public String QueryFor_cus5c_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom5c="SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, (14c.CLOSING_NO) AS col7, \n" +
                "        (14c.YEAR_1) AS col9,(((14c.CLOSING_NO) - (14c.YEAR_1)) / (14c.CLOSING_NO)) as total_score,\n" +
                "        CONCAT(((14c.CLOSING_NO) - (14c.YEAR_1)), '/', (14c.CLOSING_NO)) as absval\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dgi_cus_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "';";
        return queryCustom5c;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus6a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom5b="";
        return queryCustom5b;
    }
    public String QueryFor_cus6a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom5b="";
        return queryCustom5b;
    }
    public String QueryFor_cus6a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom5b="";
        return queryCustom5b;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus6b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6b="";
        return queryCustom6b;
    }
    public String QueryFor_cus6b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6b="";
        return queryCustom6b;
    }
    public String QueryFor_cus6b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6b="";
        return queryCustom6b;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus6c_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6c="";
        return queryCustom6c;
    }
    public String QueryFor_cus6c_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6c="";
        return queryCustom6c;
    }
    public String QueryFor_cus6c_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6c="";
        return queryCustom6c;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus6d_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6d="";
        return queryCustom6d;
    }
    public String QueryFor_cus6d_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6d="";
        return queryCustom6d;
    }
    public String QueryFor_cus6d_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6d="";
        return queryCustom6d;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus6e_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6e="";
        return queryCustom6e;
    }
    public String QueryFor_cus6e_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6e="";
        return queryCustom6e;
    }
    public String QueryFor_cus6e_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6e="";
        return queryCustom6e;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus6f_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6f="";
        return queryCustom6f;
    }
    public String QueryFor_cus6f_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6f="";
        return queryCustom6f;
    }
    public String QueryFor_cus6f_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6f="";
        return queryCustom6f;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus7a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom7a="";
        return queryCustom7a;
    }
    public String QueryFor_cus7a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom7a="";
        return queryCustom7a;
    }
    public String QueryFor_cus7a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom7a="";
        return queryCustom7a;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus7b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom7b="";
        return queryCustom7b;
    }
    public String QueryFor_cus7b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom7b="";
        return queryCustom7b;
    }
    public String QueryFor_cus7b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom7b="";
        return queryCustom7b;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus8a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom8a="";
        return queryCustom8a;
    }
    public String QueryFor_cus8a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom8a="";
        return queryCustom8a;
    }
    public String QueryFor_cus8a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom8a="";
        return queryCustom8a;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus8b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom8b="";
        return queryCustom8b;
    }
    public String QueryFor_cus8b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom8b="";
        return queryCustom8b;
    }
    public String QueryFor_cus8b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom8b="";
        return queryCustom8b;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus9a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom9a="WITH main_query AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
                "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 and 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s3col9, \n" +
                "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 and 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END) AS s3col12, \n" +
                "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 and 14c.MM_YYYY = '" + prev_month_new + "' THEN CB_QUAN ELSE 0 END) AS s3col3,\n" +
                "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 and 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s6col9, \n" +
                "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 and 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END) AS s6col12, \n" +
                "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 and 14c.MM_YYYY = '" + prev_month_new + "' THEN CB_QUAN ELSE 0 END) AS s6col3,\n" +
                "        \n" +
                "        (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 and 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) + \n" +
                "         SUM(CASE WHEN 14c.COMMODITY_CODE = 3 and 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END) + \n" +
                "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 and 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) + \n" +
                "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 and 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END)) AS numerator_9a,\n" +
                "\n" +
                "        ( \n" +
                "          (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 and 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) + \n" +
                "           SUM(CASE WHEN 14c.COMMODITY_CODE = 3 and 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END) + \n" +
                "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 and 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) + \n" +
                "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 and 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END)) \n" +
                "          / \n" +
                "          (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 and 14c.MM_YYYY = '" + prev_month_new + "' THEN CB_QUAN ELSE 0 END) + \n" +
                "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 and 14c.MM_YYYY = '" + prev_month_new + "' THEN CB_QUAN ELSE 0 END))\n" +
                "        ) AS total_score\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dol_cus_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY IN ('" + month_date + "', '" + prev_month_new + "')\n" +
                "    AND 14c.COMMODITY_CODE IN (3, 6)\n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "ranked_query AS (\n" +
                "    SELECT *,\n" +
                "           ROW_NUMBER() OVER (ORDER BY numerator_9a) AS row_num,\n" +
                "           COUNT(*) OVER () AS total_rows\n" +
                "    FROM main_query\n" +
                "),\n" +
                "median_query AS (\n" +
                "    SELECT AVG(numerator_9a) AS median_9a\n" +
                "    FROM ranked_query\n" +
                "    WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                ")\n" +
                "SELECT rq.*, mq.median_9a\n" +
                "FROM ranked_query rq, median_query mq\n" +
                "ORDER BY rq.total_score DESC;";
        return queryCustom9a;
    }
    public String QueryFor_cus9a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom9a="WITH main_query AS (\n"
                + "    SELECT \n"
                + "        zc.ZONE_NAME, \n"
                + "        cc.ZONE_CODE, \n"
                + "        cc.COMM_NAME, -- Added COMM_NAME\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s3col9, \n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END) AS s3col12, \n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN CB_QUAN ELSE 0 END) AS s3col3,\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s6col9, \n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END) AS s6col12, \n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + prev_month_new + "' THEN CB_QUAN ELSE 0 END) AS s6col3,\n"
                + "\n"
                + "        (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) + \n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END) + \n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) + \n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END)) AS numerator_9a,\n"
                + "\n"
                + "        ( \n"
                + "          (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "'THEN 14c.SALE_QUAN ELSE 0 END) + \n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END) + \n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) + \n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END)) \n"
                + "          / \n"
                + "          (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + prev_month_new + "' THEN CB_QUAN ELSE 0 END) + \n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + prev_month_new + "' THEN CB_QUAN ELSE 0 END))\n"
                + "        ) AS total_score\n"
                + "    FROM mis_gst_commcode AS cc \n"
                + "    RIGHT JOIN mis_dol_cus_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "    WHERE 14c.MM_YYYY IN ('" + month_date + "', '" + prev_month_new + "')\n"
                + "    AND 14c.COMMODITY_CODE IN (3, 6)\n"
                + "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME -- Updated GROUP BY clause\n"
                + "),\n"
                + "ranked_query AS (\n"
                + "    SELECT *,\n"
                + "           ROW_NUMBER() OVER (ORDER BY numerator_9a) AS row_num,\n"
                + "           COUNT(*) OVER () AS total_rows\n"
                + "    FROM main_query\n"
                + "),\n"
                + "median_query AS (\n"
                + "    SELECT AVG(numerator_9a) AS median_9a\n"
                + "    FROM ranked_query\n"
                + "    WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n"
                + ")\n"
                + "SELECT rq.*, mq.median_9a\n"
                + "FROM ranked_query rq, median_query mq\n"
                + "WHERE rq.ZONE_CODE = '"+zone_code+"' -- Filter for ZONE_CODE = 58\n"
                + "ORDER BY rq.total_score DESC;\n"
                + "";
        return queryCustom9a;
    }
    public String QueryFor_cus9a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom9a="WITH RankedData AS (\n" +
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
                "           (\n" +
                "             (SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY= '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) + \n" +
                "              SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY= '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END) + \n" +
                "              SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY= '" + month_date + "' THEN c.SALE_QUAN ELSE 0 END) + \n" +
                "              SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY= '" + month_date + "' THEN c.PARTY_QUAN ELSE 0 END)) \n" +
                "             / \n" +
                "             (SUM(CASE WHEN c.COMMODITY_CODE = 3 AND c.MM_YYYY= '" + prev_month_new + "' THEN c.CB_QUAN ELSE 0 END) + \n" +
                "              SUM(CASE WHEN c.COMMODITY_CODE = 6 AND c.MM_YYYY= '" + prev_month_new + "' THEN c.CB_QUAN ELSE 0 END))\n" +
                "           ) AS total_score\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dol_cus_1 AS c ON cc.COMM_CODE = c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE c.MM_YYYY IN ('" + month_date + "', '" + prev_month_new + "') \n" +
                "      AND c.COMMODITY_CODE IN (3, 6)\n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME\n" +
                "),\n" +
                "-- Calculate the median of numerator_9a\n" +
                "OrderedData AS (\n" +
                "    SELECT *,\n" +
                "           ROW_NUMBER() OVER (ORDER BY numerator_9a) AS row_num,\n" +
                "           COUNT(*) OVER() AS total_rows FROM RankedData\n" +
                "),\n" +
                "MedianValue AS (\n" +
                "    SELECT AVG(numerator_9a) AS median_9a\n" +
                "    FROM OrderedData WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                ")\n" +
                "-- Final output query\n" +
                "SELECT rd.*, mv.median_9a\n" +
                "FROM OrderedData rd, MedianValue mv ORDER BY rd.total_score DESC;";
        return queryCustom9a;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus9b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom9b="WITH RipeDisposalCTE AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.RIPE_DISPOSAL) AS s5col13\n" +
                "    FROM MIS_DOL_CUS_3 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE\n" +
                "),\n" +
                "RipeClosingCTE AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.RIPE_CLOSING) AS s5col11\n" +
                "    FROM MIS_DOL_CUS_3 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' GROUP BY cc.ZONE_CODE\n" +
                "),\n" +
                "RankedDisposalCTE AS (\n" +
                "    SELECT ZONE_NAME, ZONE_CODE, s5col13,\n" +
                "           ROW_NUMBER() OVER (ORDER BY s5col13) AS RowAsc,ROW_NUMBER() OVER (ORDER BY s5col13 DESC) AS RowDesc\n" +
                "    FROM RipeDisposalCTE\n" +
                "),\n" +
                "MedianCTE AS (\n" +
                "    SELECT AVG(s5col13) AS MedianValue\n" +
                "    FROM RankedDisposalCTE\n" +
                "    WHERE RowAsc = RowDesc OR RowAsc + 1 = RowDesc\n" +
                ")\n" +
                "SELECT rd.ZONE_NAME, rd.ZONE_CODE, rd.s5col13, rc.s5col11, COALESCE(m.MedianValue, 0) AS Median,COALESCE(rd.s5col13 / rc.s5col11, 0) AS total_score\n" +
                "FROM RipeDisposalCTE rd\n" +
                "LEFT JOIN RipeClosingCTE rc ON rd.ZONE_CODE = rc.ZONE_CODE\n" +
                "CROSS JOIN MedianCTE m;\n";
        return queryCustom9b;
    }
    public String QueryFor_cus9b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom9b="WITH CTE_Ripe_Disposal AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, SUM(14c.RIPE_DISPOSAL) AS s5col13\n" +
                "    FROM MIS_DOL_CUS_3 AS 14c\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME\n" +
                "),\n" +
                "CTE_Ripe_Closing AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, SUM(14c.RIPE_CLOSING) AS s5col11\n" +
                "    FROM MIS_DOL_CUS_3 AS 14c\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' \n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME\n" +
                "),\n" +
                "CTE_Median AS (\n" +
                "    SELECT AVG(s5col13) AS median_value\n" +
                "    FROM (\n" +
                "        SELECT \n" +
                "            s5col13, @row_num := @row_num + 1 AS row_num, @total_rows := @total_rows + 1 AS total_rows\n" +
                "        FROM CTE_Ripe_Disposal, (SELECT @row_num := 0, @total_rows := 0) AS vars\n" +
                "        ORDER BY s5col13\n" +
                "    ) AS sorted\n" +
                "    WHERE row_num IN ((total_rows + 1) / 2, (total_rows + 2) / 2)  -- For odd/even number of rows\n" +
                ")\n" +
                "SELECT r.ZONE_NAME, r.ZONE_CODE, r.COMM_NAME, r.s5col13, c.s5col11, m.median_value,\n" +
                "       COALESCE(r.s5col13 / c.s5col11, 0) AS total_score\n" +
                "FROM CTE_Ripe_Disposal AS r\n" +
                "LEFT JOIN CTE_Ripe_Closing AS c ON r.ZONE_NAME = c.ZONE_NAME \n" +
                "                                  AND r.ZONE_CODE = c.ZONE_CODE \n" +
                "                                  AND r.COMM_NAME = c.COMM_NAME\n" +
                "CROSS JOIN CTE_Median AS m  \n" +
                "WHERE r.ZONE_CODE = '" + zone_code + "'  -- Adding the condition here\n" +
                "\n" +
                "UNION ALL\n" +
                "\n" +
                "SELECT r.ZONE_NAME, r.ZONE_CODE, r.COMM_NAME, r.s5col13, c.s5col11, m.median_value,\n" +
                "       COALESCE(r.s5col13 / c.s5col11, 0) AS total_score\n" +
                "FROM CTE_Ripe_Disposal AS r\n" +
                "RIGHT JOIN CTE_Ripe_Closing AS c ON r.ZONE_NAME = c.ZONE_NAME \n" +
                "                                   AND r.ZONE_CODE = c.ZONE_CODE \n" +
                "                                   AND r.COMM_NAME = c.COMM_NAME\n" +
                "CROSS JOIN CTE_Median AS m  \n" +
                "WHERE r.ZONE_NAME IS NULL\n" +
                "  AND c.ZONE_CODE = '" + zone_code + "';  -- Adding the condition here\n";
        return queryCustom9b;
    }
    public String QueryFor_cus9b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom9b="-- cus 9b all c0mmi with median \n" +
                "WITH CTE_Ripe_Disposal AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, SUM(14c.RIPE_DISPOSAL) AS s5col13\n" +
                "    FROM MIS_DOL_CUS_3 AS 14c\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME\n" +
                "),\n" +
                "CTE_Ripe_Closing AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,SUM(14c.RIPE_CLOSING) AS s5col11\n" +
                "    FROM MIS_DOL_CUS_3 AS 14c\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' \n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME\n" +
                "),\n" +
                "CTE_Median AS (\n" +
                "    SELECT AVG(s5col13) AS median_value\n" +
                "    FROM (\n" +
                "        SELECT \n" +
                "            s5col13,@row_num := @row_num + 1 AS row_num,@total_rows := @total_rows + 1 AS total_rows\n" +
                "        FROM CTE_Ripe_Disposal, (SELECT @row_num := 0, @total_rows := 0) AS vars\n" +
                "        ORDER BY s5col13\n" +
                "    ) AS sorted\n" +
                "    WHERE row_num IN ((total_rows + 1) / 2, (total_rows + 2) / 2)  -- For odd/even number of rows\n" +
                ")\n" +
                "SELECT r.ZONE_NAME, r.ZONE_CODE, r.COMM_NAME, r.s5col13, c.s5col11,m.median_value,COALESCE(r.s5col13 / c.s5col11, 0) AS total_score\n" +
                "FROM CTE_Ripe_Disposal AS r\n" +
                "LEFT JOIN CTE_Ripe_Closing AS c ON r.ZONE_NAME = c.ZONE_NAME AND r.ZONE_CODE = c.ZONE_CODE AND r.COMM_NAME = c.COMM_NAME\n" +
                "CROSS JOIN CTE_Median AS m  \n" +
                "\n" +
                "UNION ALL\n" +
                "\n" +
                "SELECT r.ZONE_NAME, r.ZONE_CODE, r.COMM_NAME, r.s5col13, c.s5col11,m.median_value,COALESCE(r.s5col13 / c.s5col11, 0) AS total_score\n" +
                "FROM CTE_Ripe_Disposal AS r\n" +
                "RIGHT JOIN CTE_Ripe_Closing AS c ON r.ZONE_NAME = c.ZONE_NAME AND r.ZONE_CODE = c.ZONE_CODE AND r.COMM_NAME = c.COMM_NAME\n" +
                "CROSS JOIN CTE_Median AS m  \n" +
                "WHERE r.ZONE_NAME IS NULL;\n";
        return queryCustom9b;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus10a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    public String QueryFor_cus10a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    public String QueryFor_cus10a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus10b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    public String QueryFor_cus10b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    public String QueryFor_cus10b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus11a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    public String QueryFor_cus11a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    public String QueryFor_cus11a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus11b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    public String QueryFor_cus11b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    public String QueryFor_cus11b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus12a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom12a="WITH CTE1 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.DISPOSAL_NO) AS s5col13_T1,SUM(14c.DISPOSAL_TRANSFER_NO) AS s5col17_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '2024-04-01' AND FORUM_CODE = 5 \n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "CTE2 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.CLOSING_NO) AS s5col3_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '2024-03-01' AND FORUM_CODE = 5 \n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "CTE3 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.DISPOSAL_NO) AS s5col9_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '2024-04-01' AND FORUM_CODE = 5 \n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "CTE4 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.CLOSING_NO) AS s5col3_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '2024-03-01' AND FORUM_CODE = 5 \n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "FinalData AS (\n" +
                "    SELECT \n" +
                "        COALESCE(A.ZONE_NAME, B.ZONE_NAME, C.ZONE_NAME, D.ZONE_NAME) AS ZONE_NAME,\n" +
                "        COALESCE(A.ZONE_CODE, B.ZONE_CODE, C.ZONE_CODE, D.ZONE_CODE) AS ZONE_CODE,\n" +
                "        A.s5col13_T1,A.s5col17_T1,B.s5col3_T1,C.s5col9_T2,D.s5col3_T2,\n" +
                "        CONCAT((A.s5col13_T1 + A.s5col17_T1 + C.s5col9_T2), '/', (B.s5col3_T1 + D.s5col3_T2)) AS absvl,\n" +
                "        (A.s5col13_T1 + A.s5col17_T1 + C.s5col9_T2) AS numerator12A,\n" +
                "        COALESCE(((A.s5col13_T1 + A.s5col17_T1 + C.s5col9_T2) / (B.s5col3_T1 + D.s5col3_T2)), 0) AS Total_score\n" +
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
                "        A.s5col13_T1,A.s5col17_T1,B.s5col3_T1,C.s5col9_T2,D.s5col3_T2,\n" +
                "        CONCAT((A.s5col13_T1 + A.s5col17_T1 + C.s5col9_T2), '/', (B.s5col3_T1 + D.s5col3_T2)) AS absvl,\n" +
                "        (A.s5col13_T1 + A.s5col17_T1 + C.s5col9_T2) AS numerator12A,\n" +
                "        COALESCE(((A.s5col13_T1 + A.s5col17_T1 + C.s5col9_T2) / (B.s5col3_T1 + D.s5col3_T2)), 0) AS Total_score\n" +
                "    FROM CTE1 AS A\n" +
                "    RIGHT JOIN CTE2 AS B ON A.ZONE_CODE = B.ZONE_CODE\n" +
                "    RIGHT JOIN CTE3 AS C ON A.ZONE_CODE = C.ZONE_CODE\n" +
                "    RIGHT JOIN CTE4 AS D ON A.ZONE_CODE = D.ZONE_CODE\n" +
                "    WHERE A.ZONE_CODE IS NULL\n" +
                ")\n" +
                "SELECT *,\n" +
                "       (SELECT AVG(numerator12A) AS median12A \n" +
                "        FROM (SELECT numerator12A,ROW_NUMBER() OVER (ORDER BY numerator12A) AS rn,COUNT(*) OVER () AS total_count\n" +
                "            FROM FinalData\n" +
                "        ) AS subquery\n" +
                "        WHERE rn IN ((total_count + 1) / 2, (total_count + 2) / 2)\n" +
                "       ) AS median12A FROM FinalData;\n";
        return queryCustom12a;
    }
    public String QueryFor_cus12a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom12a ="";
        return queryCustom12a;
    }
    public String QueryFor_cus12a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom12a ="";
        return queryCustom12a;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus12b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="WITH cte_1 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.CLOSING_NO) AS s5col29_T1, SUM(14c.AGEWISE_1) AS s5col31_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 5\n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "cte_2 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,SUM(14c.CLOSING_NO) AS s5col23_T2,SUM(14c.AGEWISE_1) AS s5col25_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 5\n" +
                "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                ")\n" +
                "\n" +
                "SELECT c1.ZONE_NAME, c1.ZONE_CODE, c1.s5col29_T1, c1.s5col31_T1, c2.s5col23_T2, c2.s5col25_T2,\n" +
                "concat(((c1.s5col29_T1 - c1.s5col31_T1) + (c2.s5col23_T2 - c2.s5col25_T2)),'/',(c1.s5col29_T1 + c2.s5col23_T2)) AS absvl,\n" +
                "concat(((c1.s5col29_T1 - c1.s5col31_T1) + (c2.s5col23_T2 - c2.s5col25_T2)) / (c1.s5col29_T1 + c2.s5col23_T2)) AS total_score\n" +
                "FROM cte_1 AS c1\n" +
                "JOIN cte_2 AS c2 ON c1.ZONE_CODE = c2.ZONE_CODE;\n";
        return queryCustom10a;
    }
    public String QueryFor_cus12b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    public String QueryFor_cus12b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus13a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    public String QueryFor_cus13a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    public String QueryFor_cus13a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus13b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    public String QueryFor_cus13b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    public String QueryFor_cus13b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    // ********************************************************************************************************************************

    public String QueryFor_cus13c_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    public String QueryFor_cus13c_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    public String QueryFor_cus13c_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    // ********************************************************************************************************************************

    public String QueryFor_cus13d_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    public String QueryFor_cus13d_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    public String QueryFor_cus13d_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    // ********************************************************************************************************************************

    public String QueryFor_cus13e_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    public String QueryFor_cus13e_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    public String QueryFor_cus13e_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom10a="";
        return queryCustom10a;
    }
    // ********************************************************************************************************************************

}
