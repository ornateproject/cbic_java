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
        String queryCustom5a="SELECT "
                + "zc.ZONE_NAME, "
                + "cc.ZONE_CODE, "
                + "sum(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO) as col5a, "
                + "sum(14c.COMM_OPENING_NO + 14c.JC_OPENING_NO + 14c.AC_OPENING_NO) as col3a "
                + "FROM Mis_DGI_CUS_1A AS 14c "
                + "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE "
                + "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE "
                + "WHERE 14c.MM_YYYY = '" + month_date + "' "
                + "GROUP BY cc.ZONE_CODE;";
        return queryCustom5a;
    }
    public String QueryFor_cus5a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom5a="SELECT " +
                "zc.ZONE_NAME, " +
                "cc.ZONE_CODE, " +
                "cc.COMM_NAME, " +
                "(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO) as col5a, " +
                "(14c.COMM_OPENING_NO + 14c.JC_OPENING_NO + 14c.AC_OPENING_NO) as col3a " +
                "FROM mis_dgi_cus_1A AS 14c " +
                "RIGHT JOIN mis_gst_commcode AS cc " +
                "ON 14c.COMM_CODE = cc.COMM_CODE " +
                "LEFT JOIN mis_gst_zonecode AS zc " +
                "ON zc.ZONE_CODE = cc.ZONE_CODE " +
                "WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "';";
        return queryCustom5a;
    }
    public String QueryFor_cus5a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom5a="SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, \n" +
                "(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO) as col5a, \n" +
                "(14c.COMM_OPENING_NO + 14c.JC_OPENING_NO + 14c.AC_OPENING_NO) as col3a \n" +
                "FROM Mis_DGI_CUS_1A AS 14c \n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "';";
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
        String queryCustom9b="";
        return queryCustom9b;
    }
    public String QueryFor_cus9b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom9b="";
        return queryCustom9b;
    }
    public String QueryFor_cus9b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryCustom9b="";
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
}
