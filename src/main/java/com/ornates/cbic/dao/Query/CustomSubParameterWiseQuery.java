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
                + "    AND cc.ZONE_CODE = '" + zone_code + "'\n"
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
        String queryCustom5c="SELECT zc.ZONE_NAME, \n" +
                "       cc.ZONE_CODE, \n" +
                "       SUM(14c.CLOSING_NO) AS col7,\n" +
                "       SUM(14c.YEAR_1) AS col9,\n" +
                "       ((SUM(14c.CLOSING_NO) - SUM(14c.YEAR_1)) / NULLIF(SUM(14c.CLOSING_NO), 0)) AS total_score,\n" +
                "       CONCAT(SUM(14c.CLOSING_NO) - SUM(14c.YEAR_1), '/', SUM(14c.CLOSING_NO)) AS absval\n" +
                "FROM mis_gst_commcode AS cc\n" +
                "RIGHT JOIN mis_dgi_cus_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "HAVING SUM(14c.CLOSING_NO) != 0; -- Exclude cases where denominator is 0\n";
        return queryCustom5c;
    }
    public String QueryFor_cus5c_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom5c="SELECT \n" +
                "    zc.ZONE_NAME, \n" +
                "    cc.ZONE_CODE, \n" +
                "    cc.COMM_NAME, \n" +
                "    cus.CLOSING_NO AS col7, \n" +
                "    cus.YEAR_1 AS col9,\n" +
                "    CASE \n" +
                "        WHEN cus.CLOSING_NO != 0 THEN ((cus.CLOSING_NO - cus.YEAR_1) / cus.CLOSING_NO) \n" +
                "        ELSE 0 \n" +
                "    END AS total_score,\n" +
                "    CONCAT(cus.CLOSING_NO - cus.YEAR_1, '/', cus.CLOSING_NO) AS absval\n" +
                "FROM \n" +
                "    mis_gst_commcode AS cc\n" +
                "RIGHT JOIN \n" +
                "    mis_dgi_cus_2 AS cus \n" +
                "    ON cc.COMM_CODE = cus.COMM_CODE\n" +
                "LEFT JOIN \n" +
                "    mis_gst_zonecode AS zc \n" +
                "    ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE \n" +
                "    cus.MM_YYYY = '" + month_date + "'\n" +
                "    AND cc.ZONE_CODE = '"+zone_code+"'\n" +
                "    AND cus.CLOSING_NO != 0;\n";
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
        String queryCustom5b="WITH RankedValues AS (\n"
                + "    SELECT \n"
                + "        cc.ZONE_CODE,\n"
                + "        zc.ZONE_NAME,\n"
                + "        SUM(COALESCE(14c.IMPORT_GOLD_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_NARCO_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(14c.IMPORT_FICN_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_WILD_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(14c.IMPORT_ODS_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_IPR_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(14c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_GOLD_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(14c.EXPORT_NARCO_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_FICN_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(14c.EXPORT_WILD_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_ODS_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(14c.EXPORT_IPR_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_OTHERS_DISPOSAL_NOC, 0)) \n"
                + "            + SUM(COALESCE(15c.IMPORT_VALUE_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_MIS_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(15c.IMPORT_DEEC_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_DEPB_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(15c.IMPORT_EPCG_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_EOU_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(15c.IMPORT_END_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(15c.EXPORT_DEEC_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_DEPB_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(15c.EXPORT_EPCG_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_EOU_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(15c.EXPORT_DBK_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_OTHERS_DISPOSAL_NOC, 0)) \n"
                + "            AS numerator_9\n"
                + "    FROM mis_dri_cus_3a AS 14c\n"
                + "    INNER JOIN mis_gst_commcode AS cc \n"
                + "        ON 14c.COMM_CODE = cc.COMM_CODE \n"
                + "        AND 14c.MM_YYYY = '" + month_date + "'\n"
                + "    INNER JOIN mis_gst_zonecode AS zc \n"
                + "        ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "    INNER JOIN mis_dri_cus_3b AS 15c \n"
                + "        ON 14c.COMM_CODE = 15c.COMM_CODE \n"
                + "        AND 15c.MM_YYYY = '" + month_date + "'\n"
                + "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n"
                + "),\n"
                + "MedianCTE AS (\n"
                + "    SELECT \n"
                + "        numerator_9,\n"
                + "        ROW_NUMBER() OVER (ORDER BY numerator_9) AS RowAsc,\n"
                + "        ROW_NUMBER() OVER (ORDER BY numerator_9 DESC) AS RowDesc,\n"
                + "        COUNT(*) OVER () AS TotalCount\n"
                + "    FROM RankedValues\n"
                + ")\n"
                + "\n"
                + "SELECT \n"
                + "    zc.ZONE_NAME, \n"
                + "    cc.ZONE_CODE, \n"
                + "    SUM(COALESCE(14c.IMPORT_GOLD_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_NARCO_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_FICN_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_WILD_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_ODS_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_IPR_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_GOLD_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_NARCO_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_FICN_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_WILD_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_ODS_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_IPR_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_OTHERS_DISPOSAL_NOC, 0)) AS col9_3a,\n"
                + "\n"
                + "    SUM(COALESCE(15c.IMPORT_VALUE_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_MIS_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_DEEC_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_DEPB_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_EPCG_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_EOU_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_END_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_DEEC_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_DEPB_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_EPCG_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_EOU_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_DBK_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_OTHERS_DISPOSAL_NOC, 0)) AS col9_3b,\n"
                + "\n"
                + "    SUM(COALESCE(14c_prev.IMPORT_NARCO_CLOSING_NOC, 0) + COALESCE(14c_prev.IMPORT_FICN_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.IMPORT_WILD_CLOSING_NOC, 0) + COALESCE(14c_prev.IMPORT_ODS_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.IMPORT_IPR_CLOSING_NOC, 0) + COALESCE(14c_prev.IMPORT_OTHERS_CLOSING_NOC, 0) + COALESCE(14c_prev.IMPORT_GOLD_CLOSING_NOC, 0)+\n"
                + "        COALESCE(14c_prev.EXPORT_GOLD_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_NARCO_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.EXPORT_FICN_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_WILD_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.EXPORT_ODS_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_IPR_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.EXPORT_OTHERS_CLOSING_NOC, 0)) AS col3_3a,\n"
                + "\n"
                + "    SUM(COALESCE(15c_prev.IMPORT_VALUE_CLOSING_NOC, 0) + COALESCE(15c_prev.IMPORT_MIS_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.IMPORT_DEEC_CLOSING_NOC, 0) + COALESCE(15c_prev.IMPORT_DEPB_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.IMPORT_EPCG_CLOSING_NOC, 0) + COALESCE(15c_prev.IMPORT_EOU_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.IMPORT_END_CLOSING_NOC, 0) + COALESCE(15c_prev.IMPORT_OTHERS_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.EXPORT_DEEC_CLOSING_NOC, 0) + COALESCE(15c_prev.EXPORT_DEPB_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.EXPORT_EPCG_CLOSING_NOC, 0) + COALESCE(15c_prev.EXPORT_EOU_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.EXPORT_DBK_CLOSING_NOC, 0) + COALESCE(15c_prev.EXPORT_OTHERS_CLOSING_NOC, 0)) AS col3_3b,\n"
                + "\n"
                + "    COALESCE((SELECT AVG(numerator_9) \n"
                + "               FROM MedianCTE \n"
                + "               WHERE TotalCount % 2 = 0 AND RowAsc IN (TotalCount / 2, TotalCount / 2 + 1)), \n"
                + "              (SELECT AVG(numerator_9) \n"
                + "               FROM MedianCTE \n"
                + "               WHERE TotalCount % 2 = 1 AND RowAsc = (TotalCount + 1) / 2)) AS median_6a,\n"
                + "\n"
                + "    SUM(COALESCE(14c.IMPORT_GOLD_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_NARCO_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_FICN_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_WILD_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_ODS_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_IPR_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_GOLD_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_NARCO_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_FICN_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_WILD_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_ODS_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_IPR_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_OTHERS_DISPOSAL_NOC, 0)) \n"
                + "        + SUM(COALESCE(15c.IMPORT_VALUE_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_MIS_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(15c.IMPORT_DEEC_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_DEPB_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(15c.IMPORT_EPCG_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_EOU_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(15c.IMPORT_END_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(15c.EXPORT_DEEC_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_DEPB_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(15c.EXPORT_EPCG_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_EOU_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(15c.EXPORT_DBK_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_OTHERS_DISPOSAL_NOC, 0)) AS numerator_9\n"
                + "\n"
                + "FROM mis_dri_cus_3a AS 14c\n"
                + "INNER JOIN mis_gst_commcode AS cc \n"
                + "    ON 14c.COMM_CODE = cc.COMM_CODE \n"
                + "    AND 14c.MM_YYYY = '" + month_date + "'\n"
                + "INNER JOIN mis_gst_zonecode AS zc \n"
                + "    ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "INNER JOIN mis_dri_cus_3b AS 15c \n"
                + "    ON 14c.COMM_CODE = 15c.COMM_CODE \n"
                + "    AND 15c.MM_YYYY = '" + month_date + "'\n"
                + "INNER JOIN mis_dri_cus_3a AS 14c_prev\n"
                + "    ON 14c_prev.COMM_CODE = cc.COMM_CODE \n"
                + "    AND 14c_prev.MM_YYYY = '" + prev_month_new + "'\n"
                + "INNER JOIN mis_dri_cus_3b AS 15c_prev\n"
                + "    ON 14c_prev.COMM_CODE = 15c_prev.COMM_CODE \n"
                + "    AND 15c_prev.MM_YYYY = '" + prev_month_new + "'\n"
                + "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE;\n"
                + "";
        return queryCustom5b;
    }
    public String QueryFor_cus6a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom5b="WITH data AS (\n"
                + "    SELECT \n"
                + "        zc.ZONE_NAME, \n"
                + "        cc.ZONE_CODE,\n"
                + "        cc.COMM_NAME,  \n"
                + "        COALESCE(14c.IMPORT_GOLD_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_NARCO_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_FICN_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_WILD_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_ODS_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_IPR_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_GOLD_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_NARCO_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_FICN_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_WILD_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_ODS_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_IPR_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_OTHERS_DISPOSAL_NOC, 0) AS col9_3a,\n"
                + "        \n"
                + "        COALESCE(15c.IMPORT_VALUE_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_MIS_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_DEEC_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_DEPB_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_EPCG_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_EOU_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_END_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_DEEC_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_DEPB_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_EPCG_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_EOU_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_DBK_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_OTHERS_DISPOSAL_NOC, 0) AS col9_3b,\n"
                + "\n"
                + "        -- Previous month data\n"
                + "        COALESCE(14c_prev.IMPORT_NARCO_CLOSING_NOC, 0) + COALESCE(14c_prev.IMPORT_FICN_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.IMPORT_WILD_CLOSING_NOC, 0) + COALESCE(14c_prev.IMPORT_ODS_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.IMPORT_IPR_CLOSING_NOC, 0) + COALESCE(14c_prev.IMPORT_OTHERS_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.IMPORT_GOLD_CLOSING_NOC, 0) +\n"
                + "        COALESCE(14c_prev.EXPORT_GOLD_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_NARCO_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.EXPORT_FICN_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_WILD_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.EXPORT_ODS_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_IPR_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.EXPORT_OTHERS_CLOSING_NOC, 0) AS col3_3a,\n"
                + "        \n"
                + "        COALESCE(15c_prev.IMPORT_VALUE_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.IMPORT_MIS_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.IMPORT_DEEC_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.IMPORT_DEPB_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.IMPORT_EPCG_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.IMPORT_EOU_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.IMPORT_END_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.IMPORT_OTHERS_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.EXPORT_DEEC_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.EXPORT_DEPB_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.EXPORT_EPCG_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.EXPORT_EOU_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.EXPORT_DBK_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.EXPORT_OTHERS_CLOSING_NOC, 0) AS col3_3b,\n"
                + "        \n"
                + "        -- Total for current month\n"
                + "        (COALESCE(14c.IMPORT_GOLD_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_NARCO_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_FICN_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_WILD_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_ODS_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_IPR_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_GOLD_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_NARCO_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_FICN_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_WILD_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_ODS_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_IPR_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_OTHERS_DISPOSAL_NOC, 0) +\n"
                + "        COALESCE(15c.IMPORT_VALUE_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_MIS_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_DEEC_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_DEPB_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_EPCG_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_EOU_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_END_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_DEEC_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_DEPB_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_EPCG_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_EOU_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_DBK_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_OTHERS_DISPOSAL_NOC, 0)) AS total_9\n"
                + "    FROM mis_dri_cus_3a AS 14c \n"
                + "    INNER JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE AND 14c.MM_YYYY = '" + month_date + "' \n"
                + "    INNER JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "    INNER JOIN mis_dri_cus_3b AS 15c ON 14c.COMM_CODE = 15c.COMM_CODE AND 15c.MM_YYYY = '" + month_date + "' \n"
                + "    INNER JOIN mis_dri_cus_3a AS 14c_prev ON 14c.COMM_CODE = 14c_prev.COMM_CODE AND 14c_prev.MM_YYYY = '" + prev_month_new + "' \n"
                + "    INNER JOIN mis_dri_cus_3b AS 15c_prev ON 14c.COMM_CODE = 15c_prev.COMM_CODE AND 15c_prev.MM_YYYY = '" + prev_month_new + "'\n"
                + "),\n"
                + "ranked_data AS (\n"
                + "    SELECT \n"
                + "        d.*,\n"
                + "        ROW_NUMBER() OVER (ORDER BY d.total_9 ASC) AS row_num,\n"
                + "        (SELECT COUNT(*) FROM data) AS total_rows\n"
                + "    FROM data d\n"
                + "),\n"
                + "median_cte AS (\n"
                + "    -- Get the median value (if odd rows, pick the middle; if even, average two middle values)\n"
                + "    SELECT AVG(total_9) AS median_total_9\n"
                + "    FROM ranked_data\n"
                + "    WHERE row_num IN (FLOOR(total_rows / 2), CEIL(total_rows / 2))\n"
                + ")\n"
                + "SELECT \n"
                + "    rd.*,\n"
                + "    mc.median_total_9\n"
                + "FROM ranked_data rd\n"
                + "CROSS JOIN median_cte mc\n"
                + "WHERE rd.ZONE_CODE =  '"+zone_code+"' -- Apply the filter here\n"
                + "ORDER BY rd.col9_3a ASC \n"
                + "LIMIT 1000;\n"
                + "";
        return queryCustom5b;
    }
    public String QueryFor_cus6a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom5b="WITH data AS (\n"
                + "    SELECT \n"
                + "        zc.ZONE_NAME, \n"
                + "        cc.ZONE_CODE,\n"
                + "        cc.COMM_NAME,  -- COMM_NAME column\n"
                + "        COALESCE(14c.IMPORT_GOLD_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_NARCO_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_FICN_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_WILD_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_ODS_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_IPR_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_GOLD_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_NARCO_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_FICN_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_WILD_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_ODS_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_IPR_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_OTHERS_DISPOSAL_NOC, 0) AS col9_3a,\n"
                + "        \n"
                + "        COALESCE(15c.IMPORT_VALUE_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_MIS_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_DEEC_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_DEPB_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_EPCG_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_EOU_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_END_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_DEEC_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_DEPB_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_EPCG_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_EOU_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_DBK_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_OTHERS_DISPOSAL_NOC, 0) AS col9_3b,\n"
                + "\n"
                + "        -- Previous month data\n"
                + "        COALESCE(14c_prev.IMPORT_NARCO_CLOSING_NOC, 0) + COALESCE(14c_prev.IMPORT_FICN_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.IMPORT_WILD_CLOSING_NOC, 0) + COALESCE(14c_prev.IMPORT_ODS_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.IMPORT_IPR_CLOSING_NOC, 0) + COALESCE(14c_prev.IMPORT_OTHERS_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.IMPORT_GOLD_CLOSING_NOC, 0)+\n"
                + "        COALESCE(14c_prev.EXPORT_GOLD_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_NARCO_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.EXPORT_FICN_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_WILD_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.EXPORT_ODS_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_IPR_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.EXPORT_OTHERS_CLOSING_NOC, 0) AS col3_3a,\n"
                + "        \n"
                + "        COALESCE(15c_prev.IMPORT_VALUE_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.IMPORT_MIS_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.IMPORT_DEEC_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.IMPORT_DEPB_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.IMPORT_EPCG_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.IMPORT_EOU_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.IMPORT_END_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.IMPORT_OTHERS_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.EXPORT_DEEC_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.EXPORT_DEPB_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.EXPORT_EPCG_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.EXPORT_EOU_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.EXPORT_DBK_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c_prev.EXPORT_OTHERS_CLOSING_NOC, 0) AS col3_3b,\n"
                + "        \n"
                + "        -- Total for current month\n"
                + "        (COALESCE(14c.IMPORT_GOLD_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_NARCO_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_FICN_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_WILD_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_ODS_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_IPR_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_GOLD_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_NARCO_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_FICN_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_WILD_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_ODS_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_IPR_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_OTHERS_DISPOSAL_NOC, 0) +\n"
                + "        COALESCE(15c.IMPORT_VALUE_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_MIS_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_DEEC_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_DEPB_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_EPCG_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_EOU_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_END_DISPOSAL_NOC, 0) + COALESCE(15c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_DEEC_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_DEPB_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_EPCG_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_EOU_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_DBK_DISPOSAL_NOC, 0) + COALESCE(15c.EXPORT_OTHERS_DISPOSAL_NOC, 0)) AS total_9\n"
                + "    FROM mis_dri_cus_3a AS 14c \n"
                + "    INNER JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE AND 14c.MM_YYYY = '" + month_date + "' \n"
                + "    INNER JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "    INNER JOIN mis_dri_cus_3b AS 15c ON 14c.COMM_CODE = 15c.COMM_CODE AND 15c.MM_YYYY = '" + month_date + "' \n"
                + "    INNER JOIN mis_dri_cus_3a AS 14c_prev ON 14c.COMM_CODE = 14c_prev.COMM_CODE AND 14c_prev.MM_YYYY = '" + prev_month_new + "'  \n"
                + "    INNER JOIN mis_dri_cus_3b AS 15c_prev ON 14c.COMM_CODE = 15c_prev.COMM_CODE AND 15c_prev.MM_YYYY = '" + prev_month_new + "'\n"
                + "),\n"
                + "ranked_data AS (\n"
                + "    SELECT \n"
                + "        d.*,\n"
                + "        ROW_NUMBER() OVER (ORDER BY d.total_9 ASC) AS row_num,\n"
                + "        (SELECT COUNT(*) FROM data) AS total_rows\n"
                + "    FROM data d\n"
                + "),\n"
                + "median_cte AS (\n"
                + "    -- Get the median value (if odd rows, pick the middle; if even, average two middle values)\n"
                + "    SELECT AVG(total_9) AS median_total_9\n"
                + "    FROM ranked_data\n"
                + "    WHERE row_num IN (FLOOR(total_rows / 2), CEIL(total_rows / 2))\n"
                + ")\n"
                + "SELECT \n"
                + "    rd.*,\n"
                + "    mc.median_total_9\n"
                + "FROM ranked_data rd\n"
                + "CROSS JOIN median_cte mc\n"
                + "ORDER BY rd.col9_3a ASC \n"
                + "LIMIT 0, 1000;\n"
                + "";
        return queryCustom5b;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus6b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6b="SELECT \n"
                + "    zc.ZONE_NAME, \n"
                + "    cc.ZONE_CODE,\n"
                + "\n"
                + "    -- Total import/export calculations for columns from the 14c table\n"
                + "    SUM(COALESCE(14c.IMPORT_GOLD_2_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_NARCO_2_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_FICN_2_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_WILD_2_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_ODS_2_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_IPR_2_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_OTHERS_2_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_GOLD_2_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_NARCO_2_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_FICN_2_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_WILD_2_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_ODS_2_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_IPR_2_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_OTHERS_2_NOC, 0)) AS col18_3a,\n"
                + "\n"
                + "    -- Total import/export calculations for columns from the 15c table\n"
                + "    SUM(COALESCE(15c.IMPORT_VALUE_2_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_MIS_2_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_DEEC_2_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_DEPB_2_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_EPCG_2_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_EOU_2_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_END_2_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_OTHERS_2_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_EPCG_2_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_EOU_2_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_DBK_2_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_OTHERS_2_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_DEEC_2_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_DEPB_2_NOC, 0)) AS col18_3b,\n"
                + "\n"
                + "    -- Closing totals for the 14c table\n"
                + "    SUM(COALESCE(14c.IMPORT_GOLD_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_NARCO_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_FICN_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_WILD_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_ODS_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_IPR_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_OTHERS_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_GOLD_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_NARCO_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_FICN_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_WILD_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_ODS_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_OTHERS_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_IPR_CLOSING_NOC, 0)) AS col12_3a,\n"
                + "\n"
                + "    -- Closing totals for the 15c table\n"
                + "    SUM(COALESCE(15c.IMPORT_VALUE_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_MIS_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_DEEC_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_DEPB_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_EPCG_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_EOU_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_END_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c.IMPORT_OTHERS_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_DEEC_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_DEPB_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_EPCG_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_EOU_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_DBK_CLOSING_NOC, 0) + \n"
                + "        COALESCE(15c.EXPORT_OTHERS_CLOSING_NOC, 0)) AS col12_3b\n"
                + "\n"
                + "FROM mis_dri_cus_3a AS 14c\n"
                + "INNER JOIN mis_gst_commcode AS cc \n"
                + "    ON 14c.COMM_CODE = cc.COMM_CODE \n"
                + "    AND 14c.MM_YYYY = '" + month_date + "'  -- Ensuring data is from April 2024\n"
                + "INNER JOIN mis_gst_zonecode AS zc \n"
                + "    ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "INNER JOIN mis_dri_cus_3b AS 15c \n"
                + "    ON 14c.COMM_CODE = 15c.COMM_CODE \n"
                + "    AND 15c.MM_YYYY = '" + month_date + "'  -- Ensuring data is from April 2024 for col18_3b\n"
                + "\n"
                + "GROUP BY cc.ZONE_CODE, zc.ZONE_NAME  -- Grouping by zone code and zone name\n"
                + "ORDER BY col18_3a ASC;  -- Ordering results by col18_3a in ascending order\n"
                + "";
        return queryCustom6b;
    }
    public String QueryFor_cus6b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6b="SELECT \n"
                + "    zc.ZONE_NAME, \n"
                + "    cc.ZONE_CODE,\n"
                + "    cc.COMM_NAME, -- Added COMM_NAME\n"
                + "\n"
                + "    COALESCE(14c.IMPORT_GOLD_2_NOC, 0) + COALESCE(14c.IMPORT_NARCO_2_NOC, 0) + \n"
                + "    COALESCE(14c.IMPORT_FICN_2_NOC, 0) + COALESCE(14c.IMPORT_WILD_2_NOC, 0) + \n"
                + "    COALESCE(14c.IMPORT_ODS_2_NOC, 0) + COALESCE(14c.IMPORT_IPR_2_NOC, 0) + \n"
                + "    COALESCE(14c.IMPORT_OTHERS_2_NOC, 0) + COALESCE(14c.EXPORT_GOLD_2_NOC, 0) + \n"
                + "    COALESCE(14c.EXPORT_NARCO_2_NOC, 0) + COALESCE(14c.EXPORT_FICN_2_NOC, 0) + \n"
                + "    COALESCE(14c.EXPORT_WILD_2_NOC, 0) + COALESCE(14c.EXPORT_ODS_2_NOC, 0) + \n"
                + "    COALESCE(14c.EXPORT_IPR_2_NOC, 0) + COALESCE(14c.EXPORT_OTHERS_2_NOC, 0) AS col18_3a,\n"
                + "\n"
                + "    COALESCE(15c_prev.IMPORT_VALUE_2_NOC, 0) + COALESCE(15c_prev.IMPORT_MIS_2_NOC, 0) + \n"
                + "    COALESCE(15c_prev.IMPORT_DEEC_2_NOC, 0) + COALESCE(15c_prev.IMPORT_DEPB_2_NOC, 0) + \n"
                + "    COALESCE(15c_prev.IMPORT_EPCG_2_NOC, 0) + COALESCE(15c_prev.IMPORT_EOU_2_NOC, 0) + \n"
                + "    COALESCE(15c_prev.IMPORT_END_2_NOC, 0) + COALESCE(15c_prev.IMPORT_OTHERS_2_NOC, 0) + \n"
                + "    COALESCE(15c_prev.EXPORT_DEEC_2_NOC, 0) + COALESCE(15c_prev.EXPORT_DEPB_2_NOC, 0) + \n"
                + "    COALESCE(15c_prev.EXPORT_EPCG_2_NOC, 0) + COALESCE(15c_prev.EXPORT_EOU_2_NOC, 0) + \n"
                + "    COALESCE(15c_prev.EXPORT_DBK_2_NOC, 0) + COALESCE(15c_prev.EXPORT_OTHERS_2_NOC, 0) AS col18_3b,\n"
                + "\n"
                + "    COALESCE(14c.IMPORT_GOLD_CLOSING_NOC, 0) + COALESCE(14c.IMPORT_NARCO_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c.IMPORT_FICN_CLOSING_NOC, 0) + COALESCE(14c.IMPORT_WILD_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c.IMPORT_ODS_CLOSING_NOC, 0) + COALESCE(14c.IMPORT_IPR_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c.IMPORT_OTHERS_CLOSING_NOC, 0) + COALESCE(14c.EXPORT_GOLD_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c.EXPORT_NARCO_CLOSING_NOC, 0) + COALESCE(14c.EXPORT_FICN_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c.EXPORT_WILD_CLOSING_NOC, 0) + COALESCE(14c.EXPORT_ODS_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c.EXPORT_OTHERS_CLOSING_NOC, 0) + COALESCE(14c.EXPORT_IPR_CLOSING_NOC, 0) AS col12_3a,\n"
                + "\n"
                + "    COALESCE(15c_prev.IMPORT_VALUE_CLOSING_NOC, 0) + COALESCE(15c_prev.IMPORT_MIS_CLOSING_NOC, 0) + \n"
                + "    COALESCE(15c_prev.IMPORT_DEEC_CLOSING_NOC, 0) + COALESCE(15c_prev.IMPORT_DEPB_CLOSING_NOC, 0) + \n"
                + "    COALESCE(15c_prev.IMPORT_EPCG_CLOSING_NOC, 0) + COALESCE(15c_prev.IMPORT_EOU_CLOSING_NOC, 0) + \n"
                + "    COALESCE(15c_prev.IMPORT_END_CLOSING_NOC, 0) + COALESCE(15c_prev.IMPORT_OTHERS_CLOSING_NOC, 0) + \n"
                + "    COALESCE(15c_prev.EXPORT_DEEC_CLOSING_NOC, 0) + COALESCE(15c_prev.EXPORT_DEPB_CLOSING_NOC, 0) + \n"
                + "    COALESCE(15c_prev.EXPORT_EPCG_CLOSING_NOC, 0) + COALESCE(15c_prev.EXPORT_EOU_CLOSING_NOC, 0) + \n"
                + "    COALESCE(15c_prev.EXPORT_DBK_CLOSING_NOC, 0) + COALESCE(15c_prev.EXPORT_OTHERS_CLOSING_NOC, 0) AS col12_3b\n"
                + "\n"
                + "FROM mis_dri_cus_3a AS 14c\n"
                + "INNER JOIN mis_gst_commcode AS cc \n"
                + "    ON 14c.COMM_CODE = cc.COMM_CODE \n"
                + "    AND 14c.MM_YYYY = '" + month_date + "'\n"
                + "INNER JOIN mis_gst_zonecode AS zc \n"
                + "    ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "INNER JOIN mis_dri_cus_3b AS 15c \n"
                + "    ON 14c.COMM_CODE = 15c.COMM_CODE \n"
                + "    AND 15c.MM_YYYY = '" + month_date + "'\n"
                + "INNER JOIN mis_dri_cus_3b AS 15c_prev \n"
                + "    ON 14c.COMM_CODE = 15c_prev.COMM_CODE \n"
                + "    AND 15c_prev.MM_YYYY = '" + month_date + "' -- Adjusted to previous month for closing values\n"
                + "\n"
                + "WHERE cc.ZONE_CODE = '"+zone_code+"' -- Added condition for ZONE_CODE\n"
                + "ORDER BY col18_3a ASC;\n"
                + "";
        return queryCustom6b;
    }
    public String QueryFor_cus6b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6b="SELECT \n"
                + "    zc.ZONE_NAME, \n"
                + "    cc.ZONE_CODE,\n"
                + "    cc.COMM_NAME, -- Added COMM_NAME\n"
                + "\n"
                + "    COALESCE(14c.IMPORT_GOLD_2_NOC, 0) + COALESCE(14c.IMPORT_NARCO_2_NOC, 0) + \n"
                + "    COALESCE(14c.IMPORT_FICN_2_NOC, 0) + COALESCE(14c.IMPORT_WILD_2_NOC, 0) + \n"
                + "    COALESCE(14c.IMPORT_ODS_2_NOC, 0) + COALESCE(14c.IMPORT_IPR_2_NOC, 0) + \n"
                + "    COALESCE(14c.IMPORT_OTHERS_2_NOC, 0) + COALESCE(14c.EXPORT_GOLD_2_NOC, 0) + \n"
                + "    COALESCE(14c.EXPORT_NARCO_2_NOC, 0) + COALESCE(14c.EXPORT_FICN_2_NOC, 0) + \n"
                + "    COALESCE(14c.EXPORT_WILD_2_NOC, 0) + COALESCE(14c.EXPORT_ODS_2_NOC, 0) + \n"
                + "    COALESCE(14c.EXPORT_IPR_2_NOC, 0) + COALESCE(14c.EXPORT_OTHERS_2_NOC, 0) AS col18_3a,\n"
                + "\n"
                + "    COALESCE(15c_prev.IMPORT_VALUE_2_NOC, 0) + COALESCE(15c_prev.IMPORT_MIS_2_NOC, 0) + \n"
                + "    COALESCE(15c_prev.IMPORT_DEEC_2_NOC, 0) + COALESCE(15c_prev.IMPORT_DEPB_2_NOC, 0) + \n"
                + "    COALESCE(15c_prev.IMPORT_EPCG_2_NOC, 0) + COALESCE(15c_prev.IMPORT_EOU_2_NOC, 0) + \n"
                + "    COALESCE(15c_prev.IMPORT_END_2_NOC, 0) + COALESCE(15c_prev.IMPORT_OTHERS_2_NOC, 0) + \n"
                + "    COALESCE(15c_prev.EXPORT_DEEC_2_NOC, 0) + COALESCE(15c_prev.EXPORT_DEPB_2_NOC, 0) + \n"
                + "    COALESCE(15c_prev.EXPORT_EPCG_2_NOC, 0) + COALESCE(15c_prev.EXPORT_EOU_2_NOC, 0) + \n"
                + "    COALESCE(15c_prev.EXPORT_DBK_2_NOC, 0) + COALESCE(15c_prev.EXPORT_OTHERS_2_NOC, 0) AS col18_3b,\n"
                + "\n"
                + "    COALESCE(14c.IMPORT_GOLD_CLOSING_NOC, 0) + COALESCE(14c.IMPORT_NARCO_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c.IMPORT_FICN_CLOSING_NOC, 0) + COALESCE(14c.IMPORT_WILD_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c.IMPORT_ODS_CLOSING_NOC, 0) + COALESCE(14c.IMPORT_IPR_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c.IMPORT_OTHERS_CLOSING_NOC, 0) + COALESCE(14c.EXPORT_GOLD_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c.EXPORT_NARCO_CLOSING_NOC, 0) + COALESCE(14c.EXPORT_FICN_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c.EXPORT_WILD_CLOSING_NOC, 0) + COALESCE(14c.EXPORT_ODS_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c.EXPORT_OTHERS_CLOSING_NOC, 0) + COALESCE(14c.EXPORT_IPR_CLOSING_NOC, 0) AS col12_3a,\n"
                + "\n"
                + "    COALESCE(15c_prev.IMPORT_VALUE_CLOSING_NOC, 0) + COALESCE(15c_prev.IMPORT_MIS_CLOSING_NOC, 0) + \n"
                + "    COALESCE(15c_prev.IMPORT_DEEC_CLOSING_NOC, 0) + COALESCE(15c_prev.IMPORT_DEPB_CLOSING_NOC, 0) + \n"
                + "    COALESCE(15c_prev.IMPORT_EPCG_CLOSING_NOC, 0) + COALESCE(15c_prev.IMPORT_EOU_CLOSING_NOC, 0) + \n"
                + "    COALESCE(15c_prev.IMPORT_END_CLOSING_NOC, 0) + COALESCE(15c_prev.IMPORT_OTHERS_CLOSING_NOC, 0) + \n"
                + "    COALESCE(15c_prev.EXPORT_DEEC_CLOSING_NOC, 0) + COALESCE(15c_prev.EXPORT_DEPB_CLOSING_NOC, 0) + \n"
                + "    COALESCE(15c_prev.EXPORT_EPCG_CLOSING_NOC, 0) + COALESCE(15c_prev.EXPORT_EOU_CLOSING_NOC, 0) + \n"
                + "    COALESCE(15c_prev.EXPORT_DBK_CLOSING_NOC, 0) + COALESCE(15c_prev.EXPORT_OTHERS_CLOSING_NOC, 0) AS col12_3b\n"
                + "\n"
                + "FROM mis_dri_cus_3a AS 14c\n"
                + "INNER JOIN mis_gst_commcode AS cc \n"
                + "    ON 14c.COMM_CODE = cc.COMM_CODE \n"
                + "    AND 14c.MM_YYYY = '" + month_date + "'\n"
                + "INNER JOIN mis_gst_zonecode AS zc \n"
                + "    ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "INNER JOIN mis_dri_cus_3b AS 15c \n"
                + "    ON 14c.COMM_CODE = 15c.COMM_CODE \n"
                + "    AND 15c.MM_YYYY = '" + month_date + "'\n"
                + "INNER JOIN mis_dri_cus_3b AS 15c_prev \n"
                + "    ON 14c.COMM_CODE = 15c_prev.COMM_CODE \n"
                + "    AND 15c_prev.MM_YYYY = '" + month_date + "' -- Adjusted to previous month for closing values\n"
                + "ORDER BY col18_3a ASC;\n"
                + "";
        return queryCustom6b;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus6c_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String start_date=DateCalculate.getFinancialYearStart(month_date);
        String queryCustom6c="WITH \n"
                + "cte AS (\n"
                + "    SELECT \n"
                + "        zc.ZONE_NAME,\n"
                + "        cc.ZONE_CODE,\n"
                + "        -- Total import/export calculations for columns from the 14c table\n"
                + "        SUM(COALESCE(14c.GOLD_DUTY, 0) + \n"
                + "            COALESCE(14c.FICN_DUTY, 0) + \n"
                + "            COALESCE(14c.WILD_DUTY, 0) + \n"
                + "            COALESCE(14c.ODS_DUTY, 0) + \n"
                + "            COALESCE(14c.IPR_DUTY, 0) + \n"
                + "            COALESCE(14c.OTHERS_I_DUTY, 0) + \n"
                + "            COALESCE(14c.GOLD_DUTY_E, 0) + \n"
                + "            COALESCE(14c.NARCOTICS_DUTY_E, 0) + \n"
                + "            COALESCE(14c.FICN_DUTY_E, 0) + \n"
                + "            COALESCE(14c.WILD_DUTY_E, 0) + \n"
                + "            COALESCE(14c.ODS_DUTY_E, 0) + \n"
                + "            COALESCE(14c.IPR_DUTY_E, 0) + \n"
                + "            COALESCE(14c.OTHERS_I_DUTY_E, 0) + \n"
                + "            COALESCE(14c.CFC_DUTY, 0)) AS col18_1\n"
                + "    FROM mis_dri_cus_1 AS 14c\n"
                + "    INNER JOIN mis_gst_commcode AS cc\n"
                + "        ON 14c.COMM_CODE = cc.COMM_CODE\n"
                + "        AND 14c.MM_YYYY BETWEEN '" + start_date + "' AND  '" + month_date + "' -- Financial year condition\n"
                + "    INNER JOIN mis_gst_zonecode AS zc\n"
                + "        ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n"
                + "),\n"
                + "row_numbered_cte AS (\n"
                + "    SELECT \n"
                + "        *,\n"
                + "        ROW_NUMBER() OVER (ORDER BY col18_1 ASC) AS row_num,\n"
                + "        COUNT(*) OVER () AS total_rows\n"
                + "    FROM cte\n"
                + "),\n"
                + "median_cte AS (\n"
                + "    SELECT \n"
                + "        col18_1\n"
                + "    FROM row_numbered_cte\n"
                + "    WHERE row_num = FLOOR((total_rows + 1) / 2)\n"
                + "),\n"
                + "col8_9_cte AS (\n"
                + "    SELECT \n"
                + "        zc.ZONE_CODE,\n"
                + "        zc.ZONE_NAME,\n"
                + "        -- Extract the month for grouping\n"
                + "        DATE_FORMAT(15c_prev.MM_YYYY, '%Y-%m-01') AS month, \n"
                + "        -- Total calculations for col8_ddm (Up to the end of the target month)\n"
                + "        SUM(COALESCE(15c_prev.CUSTOM_DUTY_NONGST_POL, 0) + \n"
                + "            COALESCE(15c_prev.CUSTOM_DUTY_NONGST_NON_POL, 0) + \n"
                + "            COALESCE(15c_prev.CESS_CESS, 0) + \n"
                + "            COALESCE(15c_prev.CESS_COMP_IMPORT, 0) + \n"
                + "            COALESCE(15c_prev.SEZ, 0) + \n"
                + "            COALESCE(15c_prev.OTHER_RECEIPTS_EXPORT_DUTY, 0) + \n"
                + "            COALESCE(15c_prev.OTHER_RECEIPTS_MISCLLANEOUS, 0)) AS col8_ddm,\n"
                + "        -- Total calculations for col9_ddm (Up to the end of the target month)\n"
                + "        SUM(COALESCE(15c_prev.OTHER_RECEIPTS_IGST_IMPORT, 0)) AS col9_ddm\n"
                + "    FROM mis_ddm_cus_1a AS 15c_prev\n"
                + "    INNER JOIN mis_gst_commcode AS cc\n"
                + "        ON cc.COMM_CODE = 15c_prev.COMM_CODE\n"
                + "    INNER JOIN mis_gst_zonecode AS zc\n"
                + "        ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "    -- Filter for the financial year starting from April up to the target month\n"
                + "    WHERE 15c_prev.MM_YYYY >= '" + start_date + "' AND 15c_prev.MM_YYYY <= '" + month_date + "'\n"
                + "    GROUP BY zc.ZONE_CODE, zc.ZONE_NAME, DATE_FORMAT(15c_prev.MM_YYYY, '%Y-%m-01')\n"
                + "),\n"
                + "aggregated_col8_9_cte AS (\n"
                + "    SELECT \n"
                + "        ZONE_NAME,\n"
                + "        ZONE_CODE,\n"
                + "        -- Aggregate cumulative data up to the target month\n"
                + "        SUM(col8_ddm) AS col8_ddm,\n"
                + "        SUM(col9_ddm) AS col9_ddm\n"
                + "    FROM col8_9_cte\n"
                + "    WHERE month <= '" + month_date + "' -- Ensure cumulative data up to the target month\n"
                + "    GROUP BY ZONE_NAME, ZONE_CODE\n"
                + ")\n"
                + "SELECT \n"
                + "    cte.ZONE_NAME,\n"
                + "    cte.ZONE_CODE,\n"
                + "    cte.col18_1,\n"
                + "    (SELECT col18_1 FROM median_cte) AS median_6c,\n"
                + "    -- Absolute value in p/q form (col18_1 / (col8_ddm + col9_ddm))\n"
                + "    CONCAT(ABS(cte.col18_1), '/', ABS(COALESCE(agg.col8_ddm + agg.col9_ddm, 1))) AS abs_value_pq,\n"
                + "    agg.col8_ddm,\n"
                + "    agg.col9_ddm\n"
                + "FROM cte\n"
                + "LEFT JOIN aggregated_col8_9_cte AS agg\n"
                + "    ON cte.ZONE_CODE = agg.ZONE_CODE\n"
                + "ORDER BY cte.col18_1 ASC;\n"
                + "";
        return queryCustom6c;
    }
    public String QueryFor_cus6c_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String start_date=DateCalculate.getFinancialYearStart(month_date);
        String queryCustom6c="WITH \n"
                + "cte AS (\n"
                + "    SELECT \n"
                + "        zc.ZONE_NAME,\n"
                + "        cc.ZONE_CODE,\n"
                + "        cc.COMM_NAME,\n"
                + "        -- Total import/export calculations for columns from the 14c table\n"
                + "        SUM(COALESCE(14c.GOLD_DUTY, 0) + \n"
                + "            COALESCE(14c.FICN_DUTY, 0) + \n"
                + "            COALESCE(14c.WILD_DUTY, 0) + \n"
                + "            COALESCE(14c.ODS_DUTY, 0) + \n"
                + "            COALESCE(14c.IPR_DUTY, 0) + \n"
                + "            COALESCE(14c.OTHERS_I_DUTY, 0) + \n"
                + "            COALESCE(14c.GOLD_DUTY_E, 0) + \n"
                + "            COALESCE(14c.NARCOTICS_DUTY_E, 0) + \n"
                + "            COALESCE(14c.FICN_DUTY_E, 0) + \n"
                + "            COALESCE(14c.WILD_DUTY_E, 0) + \n"
                + "            COALESCE(14c.ODS_DUTY_E, 0) + \n"
                + "            COALESCE(14c.IPR_DUTY_E, 0) + \n"
                + "            COALESCE(14c.OTHERS_I_DUTY_E, 0) + \n"
                + "            COALESCE(14c.CFC_DUTY, 0)) AS col18_1\n"
                + "    FROM mis_dri_cus_1 AS 14c\n"
                + "    INNER JOIN mis_gst_commcode AS cc\n"
                + "        ON 14c.COMM_CODE = cc.COMM_CODE\n"
                + "        AND 14c.MM_YYYY BETWEEN'" + start_date + "' AND '" + month_date + "' -- Financial year condition\n"
                + "    INNER JOIN mis_gst_zonecode AS zc\n"
                + "        ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME\n"
                + "),\n"
                + "row_numbered_cte AS (\n"
                + "    SELECT \n"
                + "        *,\n"
                + "        ROW_NUMBER() OVER (ORDER BY col18_1 ASC) AS row_num,\n"
                + "        COUNT(*) OVER () AS total_rows\n"
                + "    FROM cte\n"
                + "),\n"
                + "median_cte AS (\n"
                + "    SELECT \n"
                + "        col18_1\n"
                + "    FROM row_numbered_cte\n"
                + "    WHERE row_num = FLOOR((total_rows + 1) / 2)\n"
                + "),\n"
                + "col8_9_cte AS (\n"
                + "    SELECT \n"
                + "        zc.ZONE_CODE,\n"
                + "        zc.ZONE_NAME,\n"
                + "        cc.COMM_NAME,\n"
                + "        -- Extract the month for grouping\n"
                + "        DATE_FORMAT(15c_prev.MM_YYYY, '%Y-%m-01') AS month, \n"
                + "        -- Total calculations for col8_ddm (Up to the end of the target month)\n"
                + "        SUM(COALESCE(15c_prev.CUSTOM_DUTY_NONGST_POL, 0) + \n"
                + "            COALESCE(15c_prev.CUSTOM_DUTY_NONGST_NON_POL, 0) + \n"
                + "            COALESCE(15c_prev.CESS_CESS, 0) + \n"
                + "            COALESCE(15c_prev.CESS_COMP_IMPORT, 0) + \n"
                + "            COALESCE(15c_prev.SEZ, 0) + \n"
                + "            COALESCE(15c_prev.OTHER_RECEIPTS_EXPORT_DUTY, 0) + \n"
                + "            COALESCE(15c_prev.OTHER_RECEIPTS_MISCLLANEOUS, 0)) AS col8_ddm,\n"
                + "        -- Total calculations for col9_ddm (Up to the end of the target month)\n"
                + "        SUM(COALESCE(15c_prev.OTHER_RECEIPTS_IGST_IMPORT, 0)) AS col9_ddm\n"
                + "    FROM mis_ddm_cus_1a AS 15c_prev\n"
                + "    INNER JOIN mis_gst_commcode AS cc\n"
                + "        ON cc.COMM_CODE = 15c_prev.COMM_CODE\n"
                + "    INNER JOIN mis_gst_zonecode AS zc\n"
                + "        ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "    -- Filter for the financial year starting from April up to the target month\n"
                + "    WHERE 15c_prev.MM_YYYY >= '" + start_date + "' AND 15c_prev.MM_YYYY <= '" + month_date + "'\n"
                + "    GROUP BY zc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME, DATE_FORMAT(15c_prev.MM_YYYY, '%Y-%m-01')\n"
                + "),\n"
                + "aggregated_col8_9_cte AS (\n"
                + "    SELECT \n"
                + "        ZONE_NAME,\n"
                + "        ZONE_CODE,\n"
                + "        COMM_NAME,\n"
                + "        -- Aggregate cumulative data up to the target month\n"
                + "        SUM(col8_ddm) AS col8_ddm,\n"
                + "        SUM(col9_ddm) AS col9_ddm\n"
                + "    FROM col8_9_cte\n"
                + "    WHERE month <= '" + month_date + "' -- Ensure cumulative data up to the target month\n"
                + "    GROUP BY ZONE_NAME, ZONE_CODE, COMM_NAME\n"
                + ")\n"
                + "SELECT \n"
                + "    cte.ZONE_NAME,\n"
                + "    cte.ZONE_CODE,\n"
                + "    cte.COMM_NAME,\n"
                + "    cte.col18_1,\n"
                + "    (SELECT col18_1 FROM median_cte) AS median_6c,\n"
                + "    -- Absolute value in p/q form (col18_1 / (col8_ddm + col9_ddm))\n"
                + "    CONCAT(ABS(cte.col18_1), '/', ABS(COALESCE(agg.col8_ddm + agg.col9_ddm, 1))) AS abs_value_pq,\n"
                + "    agg.col8_ddm,\n"
                + "    agg.col9_ddm\n"
                + "FROM cte\n"
                + "LEFT JOIN aggregated_col8_9_cte AS agg\n"
                + "    ON cte.ZONE_CODE = agg.ZONE_CODE AND cte.COMM_NAME = agg.COMM_NAME\n"
                + "WHERE cte.ZONE_CODE = '"+zone_code+"'\n"
                + "ORDER BY cte.col18_1 ASC;\n"
                + "";
        return queryCustom6c;
    }
    public String QueryFor_cus6c_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String start_date=DateCalculate.getFinancialYearStart(month_date);
        String queryCustom6c="WITH \n"
                + "cte AS (\n"
                + "    SELECT \n"
                + "        zc.ZONE_NAME,\n"
                + "        cc.ZONE_CODE,\n"
                + "        cc.COMM_NAME,\n"
                + "        -- Total import/export calculations for columns from the 14c table\n"
                + "        SUM(COALESCE(14c.GOLD_DUTY, 0) + \n"
                + "            COALESCE(14c.FICN_DUTY, 0) + \n"
                + "            COALESCE(14c.WILD_DUTY, 0) + \n"
                + "            COALESCE(14c.ODS_DUTY, 0) + \n"
                + "            COALESCE(14c.IPR_DUTY, 0) + \n"
                + "            COALESCE(14c.OTHERS_I_DUTY, 0) + \n"
                + "            COALESCE(14c.GOLD_DUTY_E, 0) + \n"
                + "            COALESCE(14c.NARCOTICS_DUTY_E, 0) + \n"
                + "            COALESCE(14c.FICN_DUTY_E, 0) + \n"
                + "            COALESCE(14c.WILD_DUTY_E, 0) + \n"
                + "            COALESCE(14c.ODS_DUTY_E, 0) + \n"
                + "            COALESCE(14c.IPR_DUTY_E, 0) + \n"
                + "            COALESCE(14c.OTHERS_I_DUTY_E, 0) + \n"
                + "            COALESCE(14c.CFC_DUTY, 0)) AS col18_1\n"
                + "    FROM mis_dri_cus_1 AS 14c\n"
                + "    INNER JOIN mis_gst_commcode AS cc\n"
                + "        ON 14c.COMM_CODE = cc.COMM_CODE\n"
                + "        AND 14c.MM_YYYY BETWEEN '" + start_date + "' AND '" + month_date + "' -- Financial year condition\n"
                + "    INNER JOIN mis_gst_zonecode AS zc\n"
                + "        ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME\n"
                + "),\n"
                + "row_numbered_cte AS (\n"
                + "    SELECT \n"
                + "        *,\n"
                + "        ROW_NUMBER() OVER (ORDER BY col18_1 ASC) AS row_num,\n"
                + "        COUNT(*) OVER () AS total_rows\n"
                + "    FROM cte\n"
                + "),\n"
                + "median_cte AS (\n"
                + "    SELECT \n"
                + "        col18_1\n"
                + "    FROM row_numbered_cte\n"
                + "    WHERE row_num = FLOOR((total_rows + 1) / 2)\n"
                + "),\n"
                + "col8_9_cte AS (\n"
                + "    SELECT \n"
                + "        zc.ZONE_CODE,\n"
                + "        zc.ZONE_NAME,\n"
                + "        cc.COMM_NAME,\n"
                + "        -- Extract the month for grouping\n"
                + "        DATE_FORMAT(15c_prev.MM_YYYY, '%Y-%m-01') AS month, \n"
                + "        -- Total calculations for col8_ddm (Up to the end of the target month)\n"
                + "        SUM(COALESCE(15c_prev.CUSTOM_DUTY_NONGST_POL, 0) + \n"
                + "            COALESCE(15c_prev.CUSTOM_DUTY_NONGST_NON_POL, 0) + \n"
                + "            COALESCE(15c_prev.CESS_CESS, 0) + \n"
                + "            COALESCE(15c_prev.CESS_COMP_IMPORT, 0) + \n"
                + "            COALESCE(15c_prev.SEZ, 0) + \n"
                + "            COALESCE(15c_prev.OTHER_RECEIPTS_EXPORT_DUTY, 0) + \n"
                + "            COALESCE(15c_prev.OTHER_RECEIPTS_MISCLLANEOUS, 0)) AS col8_ddm,\n"
                + "        -- Total calculations for col9_ddm (Up to the end of the target month)\n"
                + "        SUM(COALESCE(15c_prev.OTHER_RECEIPTS_IGST_IMPORT, 0)) AS col9_ddm\n"
                + "    FROM mis_ddm_cus_1a AS 15c_prev\n"
                + "    INNER JOIN mis_gst_commcode AS cc\n"
                + "        ON cc.COMM_CODE = 15c_prev.COMM_CODE\n"
                + "    INNER JOIN mis_gst_zonecode AS zc\n"
                + "        ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "    -- Filter for the financial year starting from April up to the target month\n"
                + "    WHERE 15c_prev.MM_YYYY >= '" + start_date + "' AND 15c_prev.MM_YYYY <= '" + month_date + "'\n"
                + "    GROUP BY zc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME, DATE_FORMAT(15c_prev.MM_YYYY, '%Y-%m-01')\n"
                + "),\n"
                + "aggregated_col8_9_cte AS (\n"
                + "    SELECT \n"
                + "        ZONE_NAME,\n"
                + "        ZONE_CODE,\n"
                + "        COMM_NAME,\n"
                + "        -- Aggregate cumulative data up to the target month\n"
                + "        SUM(col8_ddm) AS col8_ddm,\n"
                + "        SUM(col9_ddm) AS col9_ddm\n"
                + "    FROM col8_9_cte\n"
                + "    WHERE month <= '" + month_date + "' -- Ensure cumulative data up to the target month\n"
                + "    GROUP BY ZONE_NAME, ZONE_CODE, COMM_NAME\n"
                + ")\n"
                + "SELECT \n"
                + "    cte.ZONE_NAME,\n"
                + "    cte.ZONE_CODE,\n"
                + "    cte.COMM_NAME,\n"
                + "    cte.col18_1,\n"
                + "    (SELECT col18_1 FROM median_cte) AS median_6c,\n"
                + "    -- Absolute value in p/q form (col18_1 / (col8_ddm + col9_ddm))\n"
                + "    CONCAT(ABS(cte.col18_1), '/', ABS(COALESCE(agg.col8_ddm + agg.col9_ddm, 1))) AS abs_value_pq,\n"
                + "    agg.col8_ddm,\n"
                + "    agg.col9_ddm\n"
                + "FROM cte\n"
                + "LEFT JOIN aggregated_col8_9_cte AS agg\n"
                + "    ON cte.ZONE_CODE = agg.ZONE_CODE AND cte.COMM_NAME = agg.COMM_NAME\n"
                + "ORDER BY cte.col18_1 ASC;\n"
                + "";
        return queryCustom6c;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus6d_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String getFinancialYear = DateCalculate.getFinancialYearStart(month_date);

        String queryCustom6d="WITH CumulativeData AS ( SELECT cc.ZONE_CODE,zc.ZONE_NAME,scr.MM_YYYY,\n" +
                "        SUM(DUTY_DUTY + FINE_DUTY + DURING_DUTY + PENALTY_DUTY + INTEREST_DUTY + DBK_D_DUTY + DBK_R_DUTY + RDG_R_DUTY) \n" +
                "            OVER (PARTITION BY cc.ZONE_CODE, zc.ZONE_NAME ORDER BY scr.MM_YYYY) AS cumulative_col4_7\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dri_cus_7 AS scr ON cc.COMM_CODE = scr.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE scr.MM_YYYY BETWEEN '" + getFinancialYear + "' AND '" + month_date + "'\n" +
                "),\n" +
                "cte AS ( SELECT zc.ZONE_NAME,cc.ZONE_CODE,\n" +
                "        -- Replace the original col4_7 logic with MAX from CumulativeData\n" +
                "        (SELECT MAX(cumulative_col4_7) FROM CumulativeData cd \n" +
                "         WHERE cd.ZONE_CODE = cc.ZONE_CODE AND cd.MM_YYYY = '" + month_date + "') AS col4_7,\n" +
                "        -- Total import/export calculations from mis_dri_cus_3a table for previous month\n" +
                "        SUM(IFNULL(3a_prev.IMPORT_GOLD_CLOSING_DUTY, 0) + IFNULL(3a_prev.IMPORT_NARCO_CLOSING_DUTY, 0) + IFNULL(3a_prev.IMPORT_FICN_CLOSING_DUTY, 0) + IFNULL(3a_prev.IMPORT_WILD_CLOSING_DUTY, 0) + \n" +
                "            IFNULL(3a_prev.IMPORT_ODS_CLOSING_DUTY, 0) + IFNULL(3a_prev.IMPORT_IPR_CLOSING_DUTY, 0) +IFNULL(3a_prev.IMPORT_OTHERS_CLOSING_DUTY, 0) + IFNULL(3a_prev.EXPORT_GOLD_CLOSING_DUTY, 0) + \n" +
                "            IFNULL(3a_prev.EXPORT_NARCO_CLOSING_DUTY, 0) + IFNULL(3a_prev.EXPORT_FICN_CLOSING_DUTY, 0) + IFNULL(3a_prev.EXPORT_WILD_CLOSING_DUTY, 0) + IFNULL(3a_prev.EXPORT_ODS_CLOSING_DUTY, 0) + \n" +
                "            IFNULL(3a_prev.EXPORT_IPR_CLOSING_DUTY, 0) + IFNULL(3a_prev.EXPORT_OTHERS_CLOSING_DUTY, 0)) AS col5_cus3a,\n" +
                "        -- Receipt calculations from mis_dri_cus_3a table\n" +
                "        SUM(IFNULL(3a.IMPORT_GOLD_RECEIPT_DUTY, 0) + IFNULL(3a.IMPORT_NARCO_RECEIPT_DUTY, 0) + IFNULL(3a.IMPORT_FICN_RECEIPT_DUTY, 0) + IFNULL(3a.IMPORT_WILD_RECEIPT_DUTY, 0) + \n" +
                "            IFNULL(3a.IMPORT_ODS_RECEIPT_DUTY, 0) + IFNULL(3a.IMPORT_IPR_RECEIPT_DUTY, 0) +IFNULL(3a.IMPORT_OTHERS_RECEIPT_DUTY, 0) + IFNULL(3a.EXPORT_GOLD_RECEIPT_DUTY, 0) + \n" +
                "            IFNULL(3a.EXPORT_NARCO_RECEIPT_DUTY, 0) + IFNULL(3a.EXPORT_FICN_RECEIPT_DUTY, 0) + IFNULL(3a.EXPORT_WILD_RECEIPT_DUTY, 0) + IFNULL(3a.EXPORT_ODS_RECEIPT_DUTY, 0) + \n" +
                "            IFNULL(3a.EXPORT_IPR_RECEIPT_DUTY, 0) + IFNULL(3a.EXPORT_OTHERS_RECEIPT_DUTY, 0)) AS col8_cus3a,\n" +
                "        -- Total import/export calculations from mis_dri_cus_3b table for previous month\n" +
                "        SUM(IFNULL(3b_prev.IMPORT_VALUE_CLOSING_DUTY, 0) + IFNULL(3b_prev.IMPORT_MIS_CLOSING_DUTY, 0) + IFNULL(3b_prev.IMPORT_DEEC_CLOSING_DUTY, 0) + IFNULL(3b_prev.IMPORT_DEPB_CLOSING_DUTY, 0) + \n" +
                "            IFNULL(3b_prev.IMPORT_EPCG_CLOSING_DUTY, 0) + IFNULL(3b_prev.IMPORT_EOU_CLOSING_DUTY, 0) +IFNULL(3b_prev.IMPORT_END_CLOSING_DUTY, 0) + IFNULL(3b_prev.IMPORT_OTHERS_CLOSING_DUTY, 0) + \n" +
                "            IFNULL(3b_prev.EXPORT_DEEC_CLOSING_DUTY, 0) + IFNULL(3b_prev.EXPORT_DEPB_CLOSING_DUTY, 0) + IFNULL(3b_prev.EXPORT_EPCG_CLOSING_DUTY, 0) + IFNULL(3b_prev.EXPORT_EOU_CLOSING_DUTY, 0) + \n" +
                "            IFNULL(3b_prev.EXPORT_DBK_CLOSING_DUTY, 0) + IFNULL(3b_prev.EXPORT_OTHERS_CLOSING_DUTY, 0)) AS col5_cus3b,\n" +
                "        -- Receipt calculations from mis_dri_cus_3b table\n" +
                "        SUM(IFNULL(3b.IMPORT_VALUE_RECEIPT_DUTY, 0) + IFNULL(3b.IMPORT_MIS_RECEIPT_DUTY, 0) + IFNULL(3b.IMPORT_DEEC_RECEIPT_DUTY, 0) + IFNULL(3b.IMPORT_DEPB_RECEIPT_DUTY, 0) + \n" +
                "            IFNULL(3b.IMPORT_EPCG_RECEIPT_DUTY, 0) + IFNULL(3b.IMPORT_EOU_RECEIPT_DUTY, 0) +IFNULL(3b.IMPORT_END_RECEIPT_DUTY, 0) + IFNULL(3b.IMPORT_OTHERS_RECEIPT_DUTY, 0) + \n" +
                "            IFNULL(3b.EXPORT_DEEC_RECEIPT_DUTY, 0) + IFNULL(3b.EXPORT_DEPB_RECEIPT_DUTY, 0) + IFNULL(3b.EXPORT_EPCG_RECEIPT_DUTY, 0) + IFNULL(3b.EXPORT_EOU_RECEIPT_DUTY, 0) + \n" +
                "            IFNULL(3b.EXPORT_DBK_RECEIPT_DUTY, 0) +IFNULL(3b.EXPORT_OTHERS_RECEIPT_DUTY, 0)) AS col8_cus3b\n" +
                "    FROM mis_dri_cus_7 AS 14c\n" +
                "    INNER JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE AND 14c.MM_YYYY = '" + month_date + "'\n" +
                "    INNER JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    INNER JOIN mis_dri_cus_3a AS 3a ON 14c.COMM_CODE = 3a.COMM_CODE AND 3a.MM_YYYY = '" + month_date + "'\n" +
                "    INNER JOIN mis_dri_cus_3a AS 3a_prev ON 14c.COMM_CODE = 3a_prev.COMM_CODE AND 3a_prev.MM_YYYY = '" + prev_month_new + "'\n" +
                "    INNER JOIN mis_dri_cus_3b AS 3b ON 14c.COMM_CODE = 3b.COMM_CODE AND 3b.MM_YYYY = '" + month_date + "'\n" +
                "    INNER JOIN mis_dri_cus_3b AS 3b_prev ON 14c.COMM_CODE = 3b_prev.COMM_CODE AND 3b_prev.MM_YYYY = '" + prev_month_new + "'\n" +
                "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "),\n" +
                "row_numbered_cte AS ( SELECT *, ROW_NUMBER() OVER (ORDER BY col4_7 ASC) AS row_num,COUNT(*) OVER () AS total_rows FROM cte),\n" +
                "median_cte AS ( SELECT col4_7\n" +
                "    FROM row_numbered_cte WHERE row_num = FLOOR((total_rows + 1) / 2))\n" +
                "SELECT ZONE_NAME,ZONE_CODE,col4_7,col5_cus3a,col8_cus3a,col5_cus3b,col8_cus3b,(SELECT col4_7 FROM median_cte) AS median_6c,\n" +
                "    CONCAT(col4_7, '/', (col5_cus3a + col8_cus3a + col5_cus3b + col8_cus3b)) AS absolute_value,(col4_7 / (col5_cus3a + col8_cus3a + col5_cus3b + col8_cus3b)) * 100 AS total_score\n" +
                "FROM cte ORDER BY col4_7 ASC;";
        return queryCustom6d;
    }
    public String QueryFor_cus6d_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String getFinancialYear = DateCalculate.getFinancialYearStart(month_date);

        String queryCustom6d="WITH CumulativeData AS ( SELECT cc.ZONE_CODE,zc.ZONE_NAME,cc.COMM_NAME,scr.MM_YYYY,\n" +
                "        SUM(DUTY_DUTY + FINE_DUTY + DURING_DUTY + PENALTY_DUTY + INTEREST_DUTY + DBK_D_DUTY + DBK_R_DUTY + RDG_R_DUTY) \n" +
                "            OVER (PARTITION BY cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME ORDER BY scr.MM_YYYY) AS cumulative_col4_7\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dri_cus_7 AS scr ON cc.COMM_CODE = scr.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE scr.MM_YYYY BETWEEN '" + getFinancialYear + "' AND '" + month_date + "'),\n" +
                "cte AS (SELECT zc.ZONE_NAME,cc.ZONE_CODE,cc.COMM_NAME,\n" +
                "        (SELECT MAX(cumulative_col4_7) FROM CumulativeData cd \n" +
                "         WHERE cd.ZONE_CODE = cc.ZONE_CODE AND cd.COMM_NAME = cc.COMM_NAME AND cd.MM_YYYY = '" + month_date + "') AS col4_7,\n" +
                "        SUM(IFNULL(3a_prev.IMPORT_GOLD_CLOSING_DUTY, 0) + IFNULL(3a_prev.IMPORT_NARCO_CLOSING_DUTY, 0) + IFNULL(3a_prev.IMPORT_FICN_CLOSING_DUTY, 0) + IFNULL(3a_prev.IMPORT_WILD_CLOSING_DUTY, 0) + \n" +
                "            IFNULL(3a_prev.IMPORT_ODS_CLOSING_DUTY, 0) + IFNULL(3a_prev.IMPORT_IPR_CLOSING_DUTY, 0) +IFNULL(3a_prev.IMPORT_OTHERS_CLOSING_DUTY, 0) + IFNULL(3a_prev.EXPORT_GOLD_CLOSING_DUTY, 0) + \n" +
                "            IFNULL(3a_prev.EXPORT_NARCO_CLOSING_DUTY, 0) + IFNULL(3a_prev.EXPORT_FICN_CLOSING_DUTY, 0) + IFNULL(3a_prev.EXPORT_WILD_CLOSING_DUTY, 0) + IFNULL(3a_prev.EXPORT_ODS_CLOSING_DUTY, 0) + \n" +
                "            IFNULL(3a_prev.EXPORT_IPR_CLOSING_DUTY, 0) + IFNULL(3a_prev.EXPORT_OTHERS_CLOSING_DUTY, 0)) AS col5_cus3a,\n" +
                "        SUM(IFNULL(3a.IMPORT_GOLD_RECEIPT_DUTY, 0) + IFNULL(3a.IMPORT_NARCO_RECEIPT_DUTY, 0) + IFNULL(3a.IMPORT_FICN_RECEIPT_DUTY, 0) + IFNULL(3a.IMPORT_WILD_RECEIPT_DUTY, 0) + \n" +
                "            IFNULL(3a.IMPORT_ODS_RECEIPT_DUTY, 0) + IFNULL(3a.IMPORT_IPR_RECEIPT_DUTY, 0) +IFNULL(3a.IMPORT_OTHERS_RECEIPT_DUTY, 0) + IFNULL(3a.EXPORT_GOLD_RECEIPT_DUTY, 0) + \n" +
                "            IFNULL(3a.EXPORT_NARCO_RECEIPT_DUTY, 0) + IFNULL(3a.EXPORT_FICN_RECEIPT_DUTY, 0) + IFNULL(3a.EXPORT_WILD_RECEIPT_DUTY, 0) + \n" +
                "            IFNULL(3a.EXPORT_ODS_RECEIPT_DUTY, 0) + IFNULL(3a.EXPORT_IPR_RECEIPT_DUTY, 0) + IFNULL(3a.EXPORT_OTHERS_RECEIPT_DUTY, 0)) AS col8_cus3a,\n" +
                "        SUM(IFNULL(3b_prev.IMPORT_VALUE_CLOSING_DUTY, 0) + IFNULL(3b_prev.IMPORT_MIS_CLOSING_DUTY, 0) + IFNULL(3b_prev.IMPORT_DEEC_CLOSING_DUTY, 0) + IFNULL(3b_prev.IMPORT_DEPB_CLOSING_DUTY, 0) + \n" +
                "            IFNULL(3b_prev.IMPORT_EPCG_CLOSING_DUTY, 0) + IFNULL(3b_prev.IMPORT_EOU_CLOSING_DUTY, 0) +IFNULL(3b_prev.IMPORT_END_CLOSING_DUTY, 0) + IFNULL(3b_prev.IMPORT_OTHERS_CLOSING_DUTY, 0) + \n" +
                "            IFNULL(3b_prev.EXPORT_DEEC_CLOSING_DUTY, 0) + IFNULL(3b_prev.EXPORT_DEPB_CLOSING_DUTY, 0) + IFNULL(3b_prev.EXPORT_EPCG_CLOSING_DUTY, 0) + IFNULL(3b_prev.EXPORT_EOU_CLOSING_DUTY, 0) + \n" +
                "            IFNULL(3b_prev.EXPORT_DBK_CLOSING_DUTY, 0) + IFNULL(3b_prev.EXPORT_OTHERS_CLOSING_DUTY, 0)) AS col5_cus3b,\n" +
                "        SUM(IFNULL(3b.IMPORT_VALUE_RECEIPT_DUTY, 0) + IFNULL(3b.IMPORT_MIS_RECEIPT_DUTY, 0) + IFNULL(3b.IMPORT_DEEC_RECEIPT_DUTY, 0) + IFNULL(3b.IMPORT_DEPB_RECEIPT_DUTY, 0) + \n" +
                "            IFNULL(3b.IMPORT_EPCG_RECEIPT_DUTY, 0) + IFNULL(3b.IMPORT_EOU_RECEIPT_DUTY, 0) +IFNULL(3b.IMPORT_END_RECEIPT_DUTY, 0) + IFNULL(3b.IMPORT_OTHERS_RECEIPT_DUTY, 0) + \n" +
                "            IFNULL(3b.EXPORT_DEEC_RECEIPT_DUTY, 0) + IFNULL(3b.EXPORT_DEPB_RECEIPT_DUTY, 0) + IFNULL(3b.EXPORT_EPCG_RECEIPT_DUTY, 0) + IFNULL(3b.EXPORT_EOU_RECEIPT_DUTY, 0) + \n" +
                "            IFNULL(3b.EXPORT_DBK_RECEIPT_DUTY, 0) +IFNULL(3b.EXPORT_OTHERS_RECEIPT_DUTY, 0)) AS col8_cus3b\n" +
                "    FROM mis_dri_cus_7 AS 14c\n" +
                "    INNER JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE AND 14c.MM_YYYY = '" + month_date + "'\n" +
                "    INNER JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    INNER JOIN mis_dri_cus_3a AS 3a ON 14c.COMM_CODE = 3a.COMM_CODE AND 3a.MM_YYYY = '" + month_date + "'\n" +
                "    INNER JOIN mis_dri_cus_3a AS 3a_prev ON 14c.COMM_CODE = 3a_prev.COMM_CODE AND 3a_prev.MM_YYYY = '" + prev_month_new + "'\n" +
                "    INNER JOIN mis_dri_cus_3b AS 3b ON 14c.COMM_CODE = 3b.COMM_CODE AND 3b.MM_YYYY = '" + month_date + "'\n" +
                "    INNER JOIN mis_dri_cus_3b AS 3b_prev ON 14c.COMM_CODE = 3b_prev.COMM_CODE AND 3b_prev.MM_YYYY = '" + prev_month_new + "'\n" +
                "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME\n" +
                "),\n" +
                "row_numbered_cte AS (\n" +
                "    SELECT *, ROW_NUMBER() OVER (ORDER BY col4_7 ASC) AS row_num, COUNT(*) OVER () AS total_rows FROM cte),\n" +
                "median_cte AS (SELECT  col4_7 FROM row_numbered_cte WHERE row_num = FLOOR((total_rows + 1) / 2)\n" +
                ")\n" +
                "SELECT ZONE_NAME,ZONE_CODE,COMM_NAME,col4_7,col5_cus3a,col8_cus3a,col5_cus3b,col8_cus3b,(SELECT col4_7 FROM median_cte) AS median_6c,\n" +
                "    CONCAT(col4_7, '/', (col5_cus3a + col8_cus3a + col5_cus3b + col8_cus3b)) AS absolute_value,(col4_7 / (col5_cus3a + col8_cus3a + col5_cus3b + col8_cus3b)) * 100 AS total_score\n" +
                "FROM cte WHERE ZONE_CODE = '" + zone_code + "'  ORDER BY col4_7 ASC;";
        return queryCustom6d;
    }
    public String QueryFor_cus6d_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String getFinancialYear = DateCalculate.getFinancialYearStart(month_date);

        String queryCustom6d="WITH CumulativeData AS (\n" +
                "    SELECT cc.ZONE_CODE,zc.ZONE_NAME,cc.COMM_NAME,scr.MM_YYYY,\n" +
                "        SUM(DUTY_DUTY + FINE_DUTY + DURING_DUTY + PENALTY_DUTY + INTEREST_DUTY + DBK_D_DUTY + DBK_R_DUTY + RDG_R_DUTY) OVER (PARTITION BY cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME ORDER BY scr.MM_YYYY) AS cumulative_col4_7\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dri_cus_7 AS scr ON cc.COMM_CODE = scr.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE scr.MM_YYYY BETWEEN '" + getFinancialYear + "' AND '" + month_date + "' ),\n" +
                "cte AS (SELECT zc.ZONE_NAME,cc.ZONE_CODE, cc.COMM_NAME,\n" +
                "        (SELECT MAX(cumulative_col4_7) FROM CumulativeData cd \n" +
                "         WHERE cd.ZONE_CODE = cc.ZONE_CODE AND cd.COMM_NAME = cc.COMM_NAME AND cd.MM_YYYY = '" + month_date + "') AS col4_7,\n" +
                "        SUM(IFNULL(3a_prev.IMPORT_GOLD_CLOSING_DUTY, 0) + IFNULL(3a_prev.IMPORT_NARCO_CLOSING_DUTY, 0) + IFNULL(3a_prev.IMPORT_FICN_CLOSING_DUTY, 0) + IFNULL(3a_prev.IMPORT_WILD_CLOSING_DUTY, 0) + \n" +
                "            IFNULL(3a_prev.IMPORT_ODS_CLOSING_DUTY, 0) + IFNULL(3a_prev.IMPORT_IPR_CLOSING_DUTY, 0) +IFNULL(3a_prev.IMPORT_OTHERS_CLOSING_DUTY, 0) + IFNULL(3a_prev.EXPORT_GOLD_CLOSING_DUTY, 0) + \n" +
                "            IFNULL(3a_prev.EXPORT_NARCO_CLOSING_DUTY, 0) + IFNULL(3a_prev.EXPORT_FICN_CLOSING_DUTY, 0) + IFNULL(3a_prev.EXPORT_WILD_CLOSING_DUTY, 0) + IFNULL(3a_prev.EXPORT_ODS_CLOSING_DUTY, 0) + \n" +
                "            IFNULL(3a_prev.EXPORT_IPR_CLOSING_DUTY, 0) + IFNULL(3a_prev.EXPORT_OTHERS_CLOSING_DUTY, 0)) AS col5_cus3a,\n" +
                "        SUM(IFNULL(3a.IMPORT_GOLD_RECEIPT_DUTY, 0) + IFNULL(3a.IMPORT_NARCO_RECEIPT_DUTY, 0) + IFNULL(3a.IMPORT_FICN_RECEIPT_DUTY, 0) + IFNULL(3a.IMPORT_WILD_RECEIPT_DUTY, 0) + \n" +
                "            IFNULL(3a.IMPORT_ODS_RECEIPT_DUTY, 0) + IFNULL(3a.IMPORT_IPR_RECEIPT_DUTY, 0) +IFNULL(3a.IMPORT_OTHERS_RECEIPT_DUTY, 0) + IFNULL(3a.EXPORT_GOLD_RECEIPT_DUTY, 0) + \n" +
                "            IFNULL(3a.EXPORT_NARCO_RECEIPT_DUTY, 0) + IFNULL(3a.EXPORT_FICN_RECEIPT_DUTY, 0) + IFNULL(3a.EXPORT_WILD_RECEIPT_DUTY, 0) + IFNULL(3a.EXPORT_ODS_RECEIPT_DUTY, 0) + \n" +
                "            IFNULL(3a.EXPORT_IPR_RECEIPT_DUTY, 0) + IFNULL(3a.EXPORT_OTHERS_RECEIPT_DUTY, 0)) AS col8_cus3a,\n" +
                "        SUM(IFNULL(3b_prev.IMPORT_VALUE_CLOSING_DUTY, 0) + IFNULL(3b_prev.IMPORT_MIS_CLOSING_DUTY, 0) + IFNULL(3b_prev.IMPORT_DEEC_CLOSING_DUTY, 0) + IFNULL(3b_prev.IMPORT_DEPB_CLOSING_DUTY, 0) + \n" +
                "            IFNULL(3b_prev.IMPORT_EPCG_CLOSING_DUTY, 0) + IFNULL(3b_prev.IMPORT_EOU_CLOSING_DUTY, 0) +IFNULL(3b_prev.IMPORT_END_CLOSING_DUTY, 0) + IFNULL(3b_prev.IMPORT_OTHERS_CLOSING_DUTY, 0) + \n" +
                "            IFNULL(3b_prev.EXPORT_DEEC_CLOSING_DUTY, 0) + IFNULL(3b_prev.EXPORT_DEPB_CLOSING_DUTY, 0) + IFNULL(3b_prev.EXPORT_EPCG_CLOSING_DUTY, 0) + IFNULL(3b_prev.EXPORT_EOU_CLOSING_DUTY, 0) + \n" +
                "            IFNULL(3b_prev.EXPORT_DBK_CLOSING_DUTY, 0) + IFNULL(3b_prev.EXPORT_OTHERS_CLOSING_DUTY, 0)) AS col5_cus3b,\n" +
                "        SUM(IFNULL(3b.IMPORT_VALUE_RECEIPT_DUTY, 0) + IFNULL(3b.IMPORT_MIS_RECEIPT_DUTY, 0) + IFNULL(3b.IMPORT_DEEC_RECEIPT_DUTY, 0) + IFNULL(3b.IMPORT_DEPB_RECEIPT_DUTY, 0) + \n" +
                "            IFNULL(3b.IMPORT_EPCG_RECEIPT_DUTY, 0) + IFNULL(3b.IMPORT_EOU_RECEIPT_DUTY, 0) +IFNULL(3b.IMPORT_END_RECEIPT_DUTY, 0) + IFNULL(3b.IMPORT_OTHERS_RECEIPT_DUTY, 0) + \n" +
                "            IFNULL(3b.EXPORT_DEEC_RECEIPT_DUTY, 0) + IFNULL(3b.EXPORT_DEPB_RECEIPT_DUTY, 0) + IFNULL(3b.EXPORT_EPCG_RECEIPT_DUTY, 0) + IFNULL(3b.EXPORT_EOU_RECEIPT_DUTY, 0) + \n" +
                "            IFNULL(3b.EXPORT_DBK_RECEIPT_DUTY, 0) +IFNULL(3b.EXPORT_OTHERS_RECEIPT_DUTY, 0)) AS col8_cus3b\n" +
                "    FROM mis_dri_cus_7 AS 14c\n" +
                "    INNER JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE AND 14c.MM_YYYY = '" + month_date + "'\n" +
                "    INNER JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    INNER JOIN mis_dri_cus_3a AS 3a ON 14c.COMM_CODE = 3a.COMM_CODE AND 3a.MM_YYYY = '" + month_date + "'\n" +
                "    INNER JOIN mis_dri_cus_3a AS 3a_prev ON 14c.COMM_CODE = 3a_prev.COMM_CODE AND 3a_prev.MM_YYYY = '" + prev_month_new + "'\n" +
                "    INNER JOIN mis_dri_cus_3b AS 3b ON 14c.COMM_CODE = 3b.COMM_CODE AND 3b.MM_YYYY = '" + month_date + "'\n" +
                "    INNER JOIN mis_dri_cus_3b AS 3b_prev ON 14c.COMM_CODE = 3b_prev.COMM_CODE AND 3b_prev.MM_YYYY = '" + prev_month_new + "'\n" +
                "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME\n" +
                "),\n" +
                "row_numbered_cte AS (SELECT *, ROW_NUMBER() OVER (ORDER BY col4_7 ASC) AS row_num, COUNT(*) OVER () AS total_rows FROM cte),\n" +
                "median_cte AS (\n" +
                "    SELECT  col4_7 FROM row_numbered_cte WHERE row_num = FLOOR((total_rows + 1) / 2))\n" +
                "SELECT ZONE_NAME,ZONE_CODE,COMM_NAME,col4_7,col5_cus3a,col8_cus3a,col5_cus3b,col8_cus3b,\n" +
                "    (SELECT col4_7 FROM median_cte) AS median_6c,CONCAT(col4_7, '/', (col5_cus3a + col8_cus3a + col5_cus3b + col8_cus3b)) AS absolute_value,\n" +
                "    (col4_7 / (col5_cus3a + col8_cus3a + col5_cus3b + col8_cus3b)) * 100 AS total_score\n" +
                "FROM cte ORDER BY col4_7 ASC;";
        return queryCustom6d;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus6e_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6e="WITH RankedValues AS (\n"
                + "    SELECT \n"
                + "        cc.ZONE_CODE,\n"
                + "        zc.ZONE_NAME,\n"
                + "        -- Calculate col9_3a from the current month\n"
                + "        SUM(COALESCE(14c.IMPORT_GOLD_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_NARCO_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(14c.IMPORT_FICN_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_WILD_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(14c.IMPORT_ODS_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_IPR_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(14c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_GOLD_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(14c.EXPORT_NARCO_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_FICN_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(14c.EXPORT_WILD_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_ODS_DISPOSAL_NOC, 0) + \n"
                + "            COALESCE(14c.EXPORT_IPR_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_OTHERS_DISPOSAL_NOC, 0)) AS col9_3a\n"
                + "    FROM mis_dri_cus_3a AS 14c\n"
                + "    INNER JOIN mis_gst_commcode AS cc \n"
                + "        ON 14c.COMM_CODE = cc.COMM_CODE \n"
                + "        AND 14c.MM_YYYY = '" + month_date + "'\n"
                + "    INNER JOIN mis_gst_zonecode AS zc \n"
                + "        ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "    INNER JOIN mis_dri_cus_3b AS 15c \n"
                + "        ON 14c.COMM_CODE = 15c.COMM_CODE \n"
                + "        AND 15c.MM_YYYY = '" + month_date + "'\n"
                + "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n"
                + "),\n"
                + "MedianCalculation AS (\n"
                + "    SELECT \n"
                + "        ZONE_CODE,\n"
                + "        ZONE_NAME,\n"
                + "        col9_3a,\n"
                + "        ROW_NUMBER() OVER (ORDER BY col9_3a) AS RowAsc,\n"
                + "        ROW_NUMBER() OVER (ORDER BY col9_3a DESC) AS RowDesc,\n"
                + "        COUNT(*) OVER () AS TotalCount\n"
                + "    FROM RankedValues\n"
                + ")\n"
                + "\n"
                + "SELECT \n"
                + "    zc.ZONE_NAME, \n"
                + "    cc.ZONE_CODE, \n"
                + "    \n"
                + "    -- Calculate col9_3a from the current month\n"
                + "    SUM(COALESCE(14c.IMPORT_GOLD_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_NARCO_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_FICN_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_WILD_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_ODS_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_IPR_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_GOLD_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_NARCO_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_FICN_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_WILD_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_ODS_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_IPR_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_OTHERS_DISPOSAL_NOC, 0)) AS col9_3a,\n"
                + "\n"
                + "    -- Calculate col3_3a from the previous month\n"
                + "    SUM(COALESCE(14c_prev.IMPORT_GOLD_CLOSING_NOC, 0) + COALESCE(14c_prev.IMPORT_NARCO_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.IMPORT_FICN_CLOSING_NOC, 0) + COALESCE(14c_prev.IMPORT_WILD_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.IMPORT_ODS_CLOSING_NOC, 0) + COALESCE(14c_prev.IMPORT_IPR_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.IMPORT_OTHERS_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_GOLD_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.EXPORT_NARCO_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_FICN_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.EXPORT_WILD_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_ODS_CLOSING_NOC, 0) + \n"
                + "        COALESCE(14c_prev.EXPORT_IPR_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_OTHERS_CLOSING_NOC, 0)) AS col3_3a,\n"
                + "\n"
                + "    -- Calculate the median for col9_3a\n"
                + "    COALESCE(\n"
                + "        (SELECT AVG(col9_3a) \n"
                + "         FROM MedianCalculation \n"
                + "         WHERE TotalCount % 2 = 0 AND RowAsc IN (TotalCount / 2, TotalCount / 2 + 1)),\n"
                + "        (SELECT col9_3a \n"
                + "         FROM MedianCalculation \n"
                + "         WHERE TotalCount % 2 = 1 AND RowAsc = (TotalCount + 1) / 2)\n"
                + "    ) AS median_6e\n"
                + "\n"
                + "FROM mis_dri_cus_3a AS 14c\n"
                + "INNER JOIN mis_dri_cus_3b AS 15c \n"
                + "    ON 14c.COMM_CODE = 15c.COMM_CODE \n"
                + "    AND 14c.MM_YYYY = '" + month_date + "' \n"
                + "    AND 15c.MM_YYYY = '" + month_date + "'\n"
                + "LEFT JOIN mis_dri_cus_3a AS 14c_prev \n"
                + "    ON 14c.COMM_CODE = 14c_prev.COMM_CODE \n"
                + "    AND 14c_prev.MM_YYYY = '" + prev_month_new + "'\n"
                + "INNER JOIN mis_gst_commcode AS cc \n"
                + "    ON cc.COMM_CODE = 14c.COMM_CODE\n"
                + "INNER JOIN mis_gst_zonecode AS zc \n"
                + "    ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE;\n"
                + "";
        return queryCustom6e;
    }
    public String QueryFor_cus6e_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6e="WITH RankedValues AS (\n"
                + "    SELECT \n"
                + "        cc.ZONE_CODE,\n"
                + "        zc.ZONE_NAME,\n"
                + "        -- Calculate col9_3a from the current month without using SUM\n"
                + "        COALESCE(14c.IMPORT_GOLD_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_NARCO_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_FICN_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_WILD_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_ODS_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_IPR_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_GOLD_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_NARCO_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_FICN_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_WILD_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_ODS_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_IPR_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_OTHERS_DISPOSAL_NOC, 0) AS col9_3a\n"
                + "    FROM mis_dri_cus_3a AS 14c\n"
                + "    INNER JOIN mis_gst_commcode AS cc \n"
                + "        ON 14c.COMM_CODE = cc.COMM_CODE \n"
                + "        AND 14c.MM_YYYY = '" + month_date + "'\n"
                + "    INNER JOIN mis_gst_zonecode AS zc \n"
                + "        ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "    INNER JOIN mis_dri_cus_3b AS 15c \n"
                + "        ON 14c.COMM_CODE = 15c.COMM_CODE \n"
                + "        AND 15c.MM_YYYY = '" + month_date + "'\n"
                + "),\n"
                + "MedianCalculation AS (\n"
                + "    SELECT \n"
                + "        ZONE_CODE,\n"
                + "        ZONE_NAME,\n"
                + "        col9_3a,\n"
                + "        ROW_NUMBER() OVER (ORDER BY col9_3a) AS RowAsc,\n"
                + "        ROW_NUMBER() OVER (ORDER BY col9_3a DESC) AS RowDesc,\n"
                + "        COUNT(*) OVER () AS TotalCount\n"
                + "    FROM RankedValues\n"
                + ")\n"
                + "\n"
                + "SELECT \n"
                + "    zc.ZONE_NAME, \n"
                + "    cc.ZONE_CODE, \n"
                + "    cc.COMM_NAME, -- Add COMM_NAME here\n"
                + "    \n"
                + "    -- Calculate col9_3a from the current month without using SUM\n"
                + "    COALESCE(14c.IMPORT_GOLD_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_NARCO_DISPOSAL_NOC, 0) + \n"
                + "    COALESCE(14c.IMPORT_FICN_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_WILD_DISPOSAL_NOC, 0) + \n"
                + "    COALESCE(14c.IMPORT_ODS_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_IPR_DISPOSAL_NOC, 0) + \n"
                + "    COALESCE(14c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_GOLD_DISPOSAL_NOC, 0) + \n"
                + "    COALESCE(14c.EXPORT_NARCO_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_FICN_DISPOSAL_NOC, 0) + \n"
                + "    COALESCE(14c.EXPORT_WILD_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_ODS_DISPOSAL_NOC, 0) + \n"
                + "    COALESCE(14c.EXPORT_IPR_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_OTHERS_DISPOSAL_NOC, 0) AS col9_3a,\n"
                + "\n"
                + "    -- Calculate col3_3a from the previous month without using SUM\n"
                + "    COALESCE(14c_prev.IMPORT_GOLD_CLOSING_NOC, 0) + COALESCE(14c_prev.IMPORT_NARCO_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c_prev.IMPORT_FICN_CLOSING_NOC, 0) + COALESCE(14c_prev.IMPORT_WILD_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c_prev.IMPORT_ODS_CLOSING_NOC, 0) + COALESCE(14c_prev.IMPORT_IPR_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c_prev.IMPORT_OTHERS_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_GOLD_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c_prev.EXPORT_NARCO_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_FICN_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c_prev.EXPORT_WILD_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_ODS_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c_prev.EXPORT_IPR_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_OTHERS_CLOSING_NOC, 0) AS col3_3a,\n"
                + "\n"
                + "    -- Calculate the median for col9_3a\n"
                + "    COALESCE(\n"
                + "        (SELECT AVG(col9_3a) \n"
                + "         FROM MedianCalculation \n"
                + "         WHERE TotalCount % 2 = 0 AND RowAsc IN (TotalCount / 2, TotalCount / 2 + 1)),\n"
                + "        (SELECT col9_3a \n"
                + "         FROM MedianCalculation \n"
                + "         WHERE TotalCount % 2 = 1 AND RowAsc = (TotalCount + 1) / 2)\n"
                + "    ) AS median_6e\n"
                + "\n"
                + "FROM mis_dri_cus_3a AS 14c\n"
                + "INNER JOIN mis_dri_cus_3b AS 15c \n"
                + "    ON 14c.COMM_CODE = 15c.COMM_CODE \n"
                + "    AND 14c.MM_YYYY = '" + month_date + "' \n"
                + "    AND 15c.MM_YYYY = '" + month_date + "'\n"
                + "LEFT JOIN mis_dri_cus_3a AS 14c_prev \n"
                + "    ON 14c.COMM_CODE = 14c_prev.COMM_CODE \n"
                + "    AND 14c_prev.MM_YYYY = '" + prev_month_new + "'\n"
                + "INNER JOIN mis_gst_commcode AS cc \n"
                + "    ON cc.COMM_CODE = 14c.COMM_CODE\n"
                + "INNER JOIN mis_gst_zonecode AS zc \n"
                + "    ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "WHERE cc.ZONE_CODE = '"+zone_code+"'  -- Apply the filter here\n"
                + "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,  -- Include COMM_NAME in GROUP BY\n"
                + "    14c.IMPORT_GOLD_DISPOSAL_NOC, 14c.IMPORT_NARCO_DISPOSAL_NOC, 14c.IMPORT_FICN_DISPOSAL_NOC, \n"
                + "    14c.IMPORT_WILD_DISPOSAL_NOC, 14c.IMPORT_ODS_DISPOSAL_NOC, 14c.IMPORT_IPR_DISPOSAL_NOC, \n"
                + "    14c.IMPORT_OTHERS_DISPOSAL_NOC, 14c.EXPORT_GOLD_DISPOSAL_NOC, 14c.EXPORT_NARCO_DISPOSAL_NOC, \n"
                + "    14c.EXPORT_FICN_DISPOSAL_NOC, 14c.EXPORT_WILD_DISPOSAL_NOC, 14c.EXPORT_ODS_DISPOSAL_NOC, \n"
                + "    14c.EXPORT_IPR_DISPOSAL_NOC, 14c.EXPORT_OTHERS_DISPOSAL_NOC,\n"
                + "    14c_prev.IMPORT_GOLD_CLOSING_NOC, 14c_prev.IMPORT_NARCO_CLOSING_NOC, 14c_prev.IMPORT_FICN_CLOSING_NOC,\n"
                + "    14c_prev.IMPORT_WILD_CLOSING_NOC, 14c_prev.IMPORT_ODS_CLOSING_NOC, 14c_prev.IMPORT_IPR_CLOSING_NOC, \n"
                + "    14c_prev.IMPORT_OTHERS_CLOSING_NOC, 14c_prev.EXPORT_GOLD_CLOSING_NOC, 14c_prev.EXPORT_NARCO_CLOSING_NOC, \n"
                + "    14c_prev.EXPORT_FICN_CLOSING_NOC, 14c_prev.EXPORT_WILD_CLOSING_NOC, 14c_prev.EXPORT_ODS_CLOSING_NOC, \n"
                + "    14c_prev.EXPORT_IPR_CLOSING_NOC, 14c_prev.EXPORT_OTHERS_CLOSING_NOC;\n"
                + "";
        return queryCustom6e;
    }
    public String QueryFor_cus6e_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6e="WITH RankedValues AS (\n"
                + "    SELECT \n"
                + "        cc.ZONE_CODE,\n"
                + "        zc.ZONE_NAME,\n"
                + "        -- Calculate col9_3a from the current month without using SUM\n"
                + "        COALESCE(14c.IMPORT_GOLD_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_NARCO_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_FICN_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_WILD_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_ODS_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_IPR_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_GOLD_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_NARCO_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_FICN_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_WILD_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_ODS_DISPOSAL_NOC, 0) + \n"
                + "        COALESCE(14c.EXPORT_IPR_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_OTHERS_DISPOSAL_NOC, 0) AS col9_3a\n"
                + "    FROM mis_dri_cus_3a AS 14c\n"
                + "    INNER JOIN mis_gst_commcode AS cc \n"
                + "        ON 14c.COMM_CODE = cc.COMM_CODE \n"
                + "        AND 14c.MM_YYYY = '" + month_date + "'\n"
                + "    INNER JOIN mis_gst_zonecode AS zc \n"
                + "        ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "    INNER JOIN mis_dri_cus_3b AS 15c \n"
                + "        ON 14c.COMM_CODE = 15c.COMM_CODE \n"
                + "        AND 15c.MM_YYYY = '" + month_date + "'\n"
                + "),\n"
                + "MedianCalculation AS (\n"
                + "    SELECT \n"
                + "        ZONE_CODE,\n"
                + "        ZONE_NAME,\n"
                + "        col9_3a,\n"
                + "        ROW_NUMBER() OVER (ORDER BY col9_3a) AS RowAsc,\n"
                + "        ROW_NUMBER() OVER (ORDER BY col9_3a DESC) AS RowDesc,\n"
                + "        COUNT(*) OVER () AS TotalCount\n"
                + "    FROM RankedValues\n"
                + ")\n"
                + "\n"
                + "SELECT \n"
                + "    zc.ZONE_NAME, \n"
                + "    cc.ZONE_CODE, \n"
                + "    cc.COMM_NAME, -- Add COMM_NAME here\n"
                + "    \n"
                + "    -- Calculate col9_3a from the current month without using SUM\n"
                + "    COALESCE(14c.IMPORT_GOLD_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_NARCO_DISPOSAL_NOC, 0) + \n"
                + "    COALESCE(14c.IMPORT_FICN_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_WILD_DISPOSAL_NOC, 0) + \n"
                + "    COALESCE(14c.IMPORT_ODS_DISPOSAL_NOC, 0) + COALESCE(14c.IMPORT_IPR_DISPOSAL_NOC, 0) + \n"
                + "    COALESCE(14c.IMPORT_OTHERS_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_GOLD_DISPOSAL_NOC, 0) + \n"
                + "    COALESCE(14c.EXPORT_NARCO_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_FICN_DISPOSAL_NOC, 0) + \n"
                + "    COALESCE(14c.EXPORT_WILD_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_ODS_DISPOSAL_NOC, 0) + \n"
                + "    COALESCE(14c.EXPORT_IPR_DISPOSAL_NOC, 0) + COALESCE(14c.EXPORT_OTHERS_DISPOSAL_NOC, 0) AS col9_3a,\n"
                + "\n"
                + "    -- Calculate col3_3a from the previous month without using SUM\n"
                + "    COALESCE(14c_prev.IMPORT_GOLD_CLOSING_NOC, 0) + COALESCE(14c_prev.IMPORT_NARCO_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c_prev.IMPORT_FICN_CLOSING_NOC, 0) + COALESCE(14c_prev.IMPORT_WILD_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c_prev.IMPORT_ODS_CLOSING_NOC, 0) + COALESCE(14c_prev.IMPORT_IPR_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c_prev.IMPORT_OTHERS_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_GOLD_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c_prev.EXPORT_NARCO_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_FICN_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c_prev.EXPORT_WILD_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_ODS_CLOSING_NOC, 0) + \n"
                + "    COALESCE(14c_prev.EXPORT_IPR_CLOSING_NOC, 0) + COALESCE(14c_prev.EXPORT_OTHERS_CLOSING_NOC, 0) AS col3_3a,\n"
                + "\n"
                + "    -- Calculate the median for col9_3a\n"
                + "    COALESCE(\n"
                + "        (SELECT AVG(col9_3a) \n"
                + "         FROM MedianCalculation \n"
                + "         WHERE TotalCount % 2 = 0 AND RowAsc IN (TotalCount / 2, TotalCount / 2 + 1)),\n"
                + "        (SELECT col9_3a \n"
                + "         FROM MedianCalculation \n"
                + "         WHERE TotalCount % 2 = 1 AND RowAsc = (TotalCount + 1) / 2)\n"
                + "    ) AS median_6e\n"
                + "\n"
                + "FROM mis_dri_cus_3a AS 14c\n"
                + "INNER JOIN mis_dri_cus_3b AS 15c \n"
                + "    ON 14c.COMM_CODE = 15c.COMM_CODE \n"
                + "    AND 14c.MM_YYYY = '" + month_date + "' \n"
                + "    AND 15c.MM_YYYY = '" + month_date + "'\n"
                + "LEFT JOIN mis_dri_cus_3a AS 14c_prev \n"
                + "    ON 14c.COMM_CODE = 14c_prev.COMM_CODE \n"
                + "    AND 14c_prev.MM_YYYY = '" + prev_month_new + "'\n"
                + "INNER JOIN mis_gst_commcode AS cc \n"
                + "    ON cc.COMM_CODE = 14c.COMM_CODE\n"
                + "INNER JOIN mis_gst_zonecode AS zc \n"
                + "    ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,  -- Include COMM_NAME in GROUP BY\n"
                + "    14c.IMPORT_GOLD_DISPOSAL_NOC, 14c.IMPORT_NARCO_DISPOSAL_NOC, 14c.IMPORT_FICN_DISPOSAL_NOC, \n"
                + "    14c.IMPORT_WILD_DISPOSAL_NOC, 14c.IMPORT_ODS_DISPOSAL_NOC, 14c.IMPORT_IPR_DISPOSAL_NOC, \n"
                + "    14c.IMPORT_OTHERS_DISPOSAL_NOC, 14c.EXPORT_GOLD_DISPOSAL_NOC, 14c.EXPORT_NARCO_DISPOSAL_NOC, \n"
                + "    14c.EXPORT_FICN_DISPOSAL_NOC, 14c.EXPORT_WILD_DISPOSAL_NOC, 14c.EXPORT_ODS_DISPOSAL_NOC, \n"
                + "    14c.EXPORT_IPR_DISPOSAL_NOC, 14c.EXPORT_OTHERS_DISPOSAL_NOC,\n"
                + "    14c_prev.IMPORT_GOLD_CLOSING_NOC, 14c_prev.IMPORT_NARCO_CLOSING_NOC, 14c_prev.IMPORT_FICN_CLOSING_NOC,\n"
                + "    14c_prev.IMPORT_WILD_CLOSING_NOC, 14c_prev.IMPORT_ODS_CLOSING_NOC, 14c_prev.IMPORT_IPR_CLOSING_NOC, \n"
                + "    14c_prev.IMPORT_OTHERS_CLOSING_NOC, 14c_prev.EXPORT_GOLD_CLOSING_NOC, 14c_prev.EXPORT_NARCO_CLOSING_NOC, \n"
                + "    14c_prev.EXPORT_FICN_CLOSING_NOC, 14c_prev.EXPORT_WILD_CLOSING_NOC, 14c_prev.EXPORT_ODS_CLOSING_NOC, \n"
                + "    14c_prev.EXPORT_IPR_CLOSING_NOC, 14c_prev.EXPORT_OTHERS_CLOSING_NOC;\n"
                + "";
        return queryCustom6e;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus6f_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6f="WITH cte_1 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "           sum(14c.IMPORT_VALUE_CLOSING_NOC + 14c.IMPORT_MIS_CLOSING_NOC + 14c.IMPORT_DEEC_CLOSING_NOC + 14c.IMPORT_DEPB_CLOSING_NOC + 14c.IMPORT_EPCG_CLOSING_NOC + \n" +
                "           14c.IMPORT_EOU_CLOSING_NOC + 14c.IMPORT_END_CLOSING_NOC + 14c.IMPORT_OTHERS_CLOSING_NOC + 14c.EXPORT_DEEC_CLOSING_NOC + 14c.EXPORT_DEPB_CLOSING_NOC + \n" +
                "           14c.EXPORT_EPCG_CLOSING_NOC + 14c.EXPORT_EOU_CLOSING_NOC + 14c.EXPORT_DBK_CLOSING_NOC + 14c.EXPORT_OTHERS_CLOSING_NOC) AS col3\n" +
                "    FROM MIS_DRI_CUS_3B AS 14c\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' GROUP BY cc.ZONE_CODE\n" +
                "),\n" +
                "cte_2 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "           sum(14c.IMPORT_VALUE_DISPOSAL_NOC + 14c.IMPORT_MIS_DISPOSAL_NOC + 14c.IMPORT_DEEC_DISPOSAL_NOC + 14c.IMPORT_DEPB_DISPOSAL_NOC + 14c.IMPORT_EPCG_DISPOSAL_NOC + \n" +
                "           14c.IMPORT_EOU_DISPOSAL_NOC + 14c.IMPORT_END_DISPOSAL_NOC + 14c.IMPORT_OTHERS_DISPOSAL_NOC + 14c.EXPORT_DEEC_DISPOSAL_NOC + 14c.EXPORT_DEPB_DISPOSAL_NOC + \n" +
                "           14c.EXPORT_EPCG_DISPOSAL_NOC + 14c.EXPORT_EOU_DISPOSAL_NOC + 14c.EXPORT_DBK_DISPOSAL_NOC + 14c.EXPORT_OTHERS_DISPOSAL_NOC) AS col9\n" +
                "    FROM MIS_DRI_CUS_3B AS 14c\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE\n" +
                "),\n" +
                "ordered_cte AS (\n" +
                "    SELECT col9, \n" +
                "           ROW_NUMBER() OVER (ORDER BY col9) AS rn,\n" +
                "           COUNT(*) OVER () AS total_count\n" +
                "    FROM cte_2\n" +
                "),\n" +
                "median_cte AS (\n" +
                "    SELECT AVG(col9) AS median6f\n" +
                "    FROM ordered_cte\n" +
                "    WHERE rn IN (FLOOR((total_count + 1) / 2), FLOOR((total_count + 2) / 2))\n" +
                ")\n" +
                "SELECT c1.ZONE_NAME, c1.ZONE_CODE, c1.col3, c2.col9, \n" +
                "       CONCAT(c2.col9, '/', c1.col3) AS absvl,\n" +
                "       COALESCE((c2.col9 / c1.col3), 0) AS total_score, m.median6f\n" +
                "FROM cte_1 AS c1\n" +
                "JOIN cte_2 AS c2 ON c1.ZONE_CODE = c2.ZONE_CODE\n" +
                "CROSS JOIN median_cte AS m;";
        return queryCustom6f;
    }
    public String QueryFor_cus6f_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6f="WITH closing_noc AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
                "           (14c.IMPORT_VALUE_CLOSING_NOC + 14c.IMPORT_MIS_CLOSING_NOC + 14c.IMPORT_DEEC_CLOSING_NOC + 14c.IMPORT_DEPB_CLOSING_NOC + \n" +
                "            14c.IMPORT_EPCG_CLOSING_NOC + 14c.IMPORT_EOU_CLOSING_NOC + 14c.IMPORT_END_CLOSING_NOC + 14c.IMPORT_OTHERS_CLOSING_NOC + \n" +
                "            14c.EXPORT_DEEC_CLOSING_NOC + 14c.EXPORT_DEPB_CLOSING_NOC + 14c.EXPORT_EPCG_CLOSING_NOC + 14c.EXPORT_EOU_CLOSING_NOC + \n" +
                "            14c.EXPORT_DBK_CLOSING_NOC + 14c.EXPORT_OTHERS_CLOSING_NOC) AS col3\n" +
                "    FROM MIS_DRI_CUS_3B AS 14c\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
                "),\n" +
                "disposal_noc AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
                "           (14c.IMPORT_VALUE_DISPOSAL_NOC + 14c.IMPORT_MIS_DISPOSAL_NOC + 14c.IMPORT_DEEC_DISPOSAL_NOC + 14c.IMPORT_DEPB_DISPOSAL_NOC + \n" +
                "            14c.IMPORT_EPCG_DISPOSAL_NOC + 14c.IMPORT_EOU_DISPOSAL_NOC + 14c.IMPORT_END_DISPOSAL_NOC + 14c.IMPORT_OTHERS_DISPOSAL_NOC + \n" +
                "            14c.EXPORT_DEEC_DISPOSAL_NOC + 14c.EXPORT_DEPB_DISPOSAL_NOC + 14c.EXPORT_EPCG_DISPOSAL_NOC + 14c.EXPORT_EOU_DISPOSAL_NOC + \n" +
                "            14c.EXPORT_DBK_DISPOSAL_NOC + 14c.EXPORT_OTHERS_DISPOSAL_NOC) AS col9\n" +
                "    FROM MIS_DRI_CUS_3B AS 14c\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "),\n" +
                "median_calculation AS (\n" +
                "    SELECT col9,\n" +
                "           ROW_NUMBER() OVER (ORDER BY col9) AS row_num,\n" +
                "           COUNT(*) OVER () AS total_rows\n" +
                "    FROM disposal_noc\n" +
                "),\n" +
                "median_value AS (\n" +
                "    SELECT AVG(col9) AS median6f\n" +
                "    FROM median_calculation\n" +
                "    WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                ")\n" +
                "SELECT cn.ZONE_NAME, cn.ZONE_CODE, cn.COMM_NAME, cn.col3, dn.col9, \n" +
                "       CONCAT(dn.col9, '/', cn.col3) AS absvl, COALESCE((dn.col9 / cn.col3), 0) AS total_score,\n" +
                "       (SELECT median6f FROM median_value) AS median6f\n" +
                "FROM closing_noc cn\n" +
                "JOIN disposal_noc dn ON cn.ZONE_CODE = dn.ZONE_CODE AND cn.COMM_NAME = dn.COMM_NAME\n" +
                "WHERE cn.ZONE_CODE = '" + zone_code + "';";
        return queryCustom6f;
    }
    public String QueryFor_cus6f_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom6f="WITH closing_noc AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
                "           (14c.IMPORT_VALUE_CLOSING_NOC + 14c.IMPORT_MIS_CLOSING_NOC + 14c.IMPORT_DEEC_CLOSING_NOC + 14c.IMPORT_DEPB_CLOSING_NOC + \n" +
                "            14c.IMPORT_EPCG_CLOSING_NOC + 14c.IMPORT_EOU_CLOSING_NOC + 14c.IMPORT_END_CLOSING_NOC + 14c.IMPORT_OTHERS_CLOSING_NOC + \n" +
                "            14c.EXPORT_DEEC_CLOSING_NOC + 14c.EXPORT_DEPB_CLOSING_NOC + 14c.EXPORT_EPCG_CLOSING_NOC + 14c.EXPORT_EOU_CLOSING_NOC + \n" +
                "            14c.EXPORT_DBK_CLOSING_NOC + 14c.EXPORT_OTHERS_CLOSING_NOC) AS col3\n" +
                "    FROM MIS_DRI_CUS_3B AS 14c\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
                "),\n" +
                "disposal_noc AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
                "           (14c.IMPORT_VALUE_DISPOSAL_NOC + 14c.IMPORT_MIS_DISPOSAL_NOC + 14c.IMPORT_DEEC_DISPOSAL_NOC + 14c.IMPORT_DEPB_DISPOSAL_NOC + \n" +
                "            14c.IMPORT_EPCG_DISPOSAL_NOC + 14c.IMPORT_EOU_DISPOSAL_NOC + 14c.IMPORT_END_DISPOSAL_NOC + 14c.IMPORT_OTHERS_DISPOSAL_NOC + \n" +
                "            14c.EXPORT_DEEC_DISPOSAL_NOC + 14c.EXPORT_DEPB_DISPOSAL_NOC + 14c.EXPORT_EPCG_DISPOSAL_NOC + 14c.EXPORT_EOU_DISPOSAL_NOC + \n" +
                "            14c.EXPORT_DBK_DISPOSAL_NOC + 14c.EXPORT_OTHERS_DISPOSAL_NOC) AS col9\n" +
                "    FROM MIS_DRI_CUS_3B AS 14c\n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "),\n" +
                "median_calculation AS (\n" +
                "    SELECT col9,\n" +
                "           ROW_NUMBER() OVER (ORDER BY col9) AS row_num,\n" +
                "           COUNT(*) OVER () AS total_rows\n" +
                "    FROM disposal_noc\n" +
                "),\n" +
                "median_value AS (\n" +
                "    SELECT AVG(col9) AS median6f\n" +
                "    FROM median_calculation\n" +
                "    WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                ")\n" +
                "SELECT cn.ZONE_NAME, cn.ZONE_CODE, cn.COMM_NAME, cn.col3, dn.col9, \n" +
                "       CONCAT(dn.col9, '/', cn.col3) AS absvl,COALESCE((dn.col9 / cn.col3), 0) AS total_score,\n" +
                "       (SELECT median6f FROM median_value) AS median6f\n" +
                "FROM closing_noc cn\n" +
                "JOIN disposal_noc dn ON cn.ZONE_CODE = dn.ZONE_CODE AND cn.COMM_NAME = dn.COMM_NAME;\n";
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
        String queryCustom12a ="WITH cte_1 AS (\n" +
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
                "           (cte_1.s5col13_T1 + cte_1.s5col17_T1 + cte_3.s5col9_T2) AS numerator12A,\n" +
                "           CONCAT((cte_1.s5col13_T1 + cte_1.s5col17_T1 + cte_3.s5col9_T2), '/', (cte_2.s5col3_T1 + cte_4.s5col3_T2)) AS absvl,\n" +
                "           COALESCE(((cte_1.s5col13_T1 + cte_1.s5col17_T1 + cte_3.s5col9_T2) / (cte_2.s5col3_T1 + cte_4.s5col3_T2)), 0) AS total_score\n" +
                "    FROM cte_1\n" +
                "    LEFT JOIN cte_2 ON cte_1.ZONE_CODE = cte_2.ZONE_CODE AND cte_1.COMM_NAME = cte_2.COMM_NAME\n" +
                "    LEFT JOIN cte_3 ON cte_1.ZONE_CODE = cte_3.ZONE_CODE AND cte_1.COMM_NAME = cte_3.COMM_NAME\n" +
                "    LEFT JOIN cte_4 ON cte_1.ZONE_CODE = cte_4.ZONE_CODE AND cte_1.COMM_NAME = cte_4.COMM_NAME\n" +
                ")\n" +
                "SELECT cte_main.ZONE_NAME, cte_main.ZONE_CODE, cte_main.COMM_NAME, \n" +
                "       cte_main.numerator12A, cte_main.absvl, cte_main.total_score,\n" +
                "       (SELECT AVG(subquery.numerator12A) AS median12A\n" +
                "        FROM (\n" +
                "            SELECT numerator12A, \n" +
                "                   ROW_NUMBER() OVER (ORDER BY numerator12A) AS row_num, COUNT(*) OVER () AS total_rows\n" +
                "            FROM cte_main\n" +
                "        ) AS subquery\n" +
                "        WHERE subquery.row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                "       ) AS median12A\n" +
                "FROM cte_main\n" +
                "WHERE cte_main.ZONE_CODE = '" + zone_code + "';\n";
        return queryCustom12a;
    }
    public String QueryFor_cus12a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom12a ="WITH cte_1 AS (SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, COALESCE(14c.DISPOSAL_NO,0) AS s5col13_T1, COALESCE(14c.DISPOSAL_TRANSFER_NO,0) AS s5col17_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND 14c.FORUM_CODE = 5),\n" +
                "cte_2 AS (SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, COALESCE(14c.CLOSING_NO,0) AS s5col3_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND 14c.FORUM_CODE = 5),\n" +
                "cte_3 AS (SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, COALESCE(14c.DISPOSAL_NO,0) AS s5col9_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND 14c.FORUM_CODE = 5),\n" +
                "cte_4 AS (SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, COALESCE(14c.CLOSING_NO,0) AS s5col3_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND 14c.FORUM_CODE = 5),\n" +
                "cte_main AS (\n" +
                "    SELECT cte_1.ZONE_NAME, cte_1.ZONE_CODE, cte_1.COMM_NAME, (cte_1.s5col13_T1 + cte_1.s5col17_T1 + cte_3.s5col9_T2) AS numerator12A,\n" +
                "           CONCAT((cte_1.s5col13_T1 + cte_1.s5col17_T1 + cte_3.s5col9_T2), '/', (cte_2.s5col3_T1 + cte_4.s5col3_T2)) AS absvl,\n" +
                "           COALESCE(((cte_1.s5col13_T1 + cte_1.s5col17_T1 + cte_3.s5col9_T2) / (cte_2.s5col3_T1 + cte_4.s5col3_T2)), 0) AS total_score\n" +
                "    FROM cte_1\n" +
                "    LEFT JOIN cte_2 ON cte_1.ZONE_CODE = cte_2.ZONE_CODE AND cte_1.COMM_NAME = cte_2.COMM_NAME\n" +
                "    LEFT JOIN cte_3 ON cte_1.ZONE_CODE = cte_3.ZONE_CODE AND cte_1.COMM_NAME = cte_3.COMM_NAME\n" +
                "    LEFT JOIN cte_4 ON cte_1.ZONE_CODE = cte_4.ZONE_CODE AND cte_1.COMM_NAME = cte_4.COMM_NAME)\n" +
                "SELECT cte_main.ZONE_NAME, cte_main.ZONE_CODE, cte_main.COMM_NAME, \n" +
                "       cte_main.numerator12A, cte_main.absvl, cte_main.total_score,\n" +
                "       (SELECT AVG(subquery.numerator12A) AS median12A\n" +
                "        FROM (\n" +
                "            SELECT numerator12A, \n" +
                "                   ROW_NUMBER() OVER (ORDER BY numerator12A) AS row_num, COUNT(*) OVER () AS total_rows\n" +
                "            FROM cte_main) AS subquery\n" +
                "        WHERE subquery.row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))) AS median12A FROM cte_main;";
        return queryCustom12a;
    }
    // ********************************************************************************************************************************
    public String QueryFor_cus12b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom12b ="WITH cte_1 AS (\n" +
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
                "COALESCE((((c1.s5col29_T1 - c1.s5col31_T1) + (c2.s5col23_T2 - c2.s5col25_T2))  / (c1.s5col29_T1 + c2.s5col23_T2)),0) AS total_score\n" +
                "FROM cte_1 AS c1\n" +
                "JOIN cte_2 AS c2 ON c1.ZONE_CODE = c2.ZONE_CODE;\n";
        return queryCustom12b;
    }
    public String QueryFor_cus12b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom12b="WITH Query1 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,(14c.CLOSING_NO) AS s5col29_T1, (14c.AGEWISE_1) AS s5col31_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 5 AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                "),\n" +
                "Query2 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,(14c.CLOSING_NO) AS s5col23_T2, (14c.AGEWISE_1) AS s5col25_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 5 AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                ")\n" +
                "SELECT q1.ZONE_NAME, q1.ZONE_CODE, q1.COMM_NAME, q1.s5col29_T1, q1.s5col31_T1,q2.s5col23_T2, q2.s5col25_T2,\n" +
                "concat(((q1.s5col29_T1 - q1.s5col31_T1) + (q2.s5col23_T2 - q2.s5col25_T2)),'/',(q1.s5col29_T1 + q2.s5col23_T2)) AS absvl,\n" +
                "COALESCE((((q1.s5col29_T1 - q1.s5col31_T1) + (q2.s5col23_T2 - q2.s5col25_T2)) / (q1.s5col29_T1 + q2.s5col23_T2)),0) AS total_score\n" +
                "FROM Query1 q1\n" +
                "JOIN Query2 q2 ON q1.ZONE_CODE = q2.ZONE_CODE AND q1.COMM_NAME = q2.COMM_NAME;";
        return queryCustom12b;
    }
    public String QueryFor_cus12b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom12b ="WITH Query1 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,(14c.CLOSING_NO) AS s5col29_T1, (14c.AGEWISE_1) AS s5col31_T1\n" +
                "    FROM MIS_DLA_CUS_1 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 5\n" +
                "),\n" +
                "Query2 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,(14c.CLOSING_NO) AS s5col23_T2, (14c.AGEWISE_1) AS s5col25_T2\n" +
                "    FROM MIS_DLA_CUS_2 AS 14c  \n" +
                "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 5\n" +
                ")\n" +
                "SELECT q1.ZONE_NAME, q1.ZONE_CODE, q1.COMM_NAME, q1.s5col29_T1, q1.s5col31_T1,q2.s5col23_T2, q2.s5col25_T2,\n" +
                "concat(((q1.s5col29_T1 - q1.s5col31_T1) + (q2.s5col23_T2 - q2.s5col25_T2)),'/',(q1.s5col29_T1 + q2.s5col23_T2)) AS absvl,\n" +
                "COALESCE((((q1.s5col29_T1 - q1.s5col31_T1) + (q2.s5col23_T2 - q2.s5col25_T2)) / (q1.s5col29_T1 + q2.s5col23_T2)),0) AS total_score\n" +
                "FROM Query1 q1\n" +
                "JOIN Query2 q2 ON q1.ZONE_CODE = q2.ZONE_CODE AND q1.COMM_NAME = q2.COMM_NAME;";
        return queryCustom12b;
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
