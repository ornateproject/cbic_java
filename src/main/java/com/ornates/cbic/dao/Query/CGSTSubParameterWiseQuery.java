package com.ornates.cbic.dao.Query;

import com.ornates.cbic.service.DateCalculate;

public class CGSTSubParameterWiseQuery {
    public String QueryFor_gst1a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT zc.ZONE_NAME, cc.ZONE_CODE,sum(14c.DISPOSAL_OF_ARN_WITHIN_7) as col6, "
                + "sum(14c.NO_OF_ARN_RECEIVED_GSTN) as col2, "
                + "sum(14c.NO_OF_ARN_RECEIVED_CPC) as col4 "
                + "FROM mis_gst_commcode as cc right join mis_dpm_gst_14a as 14c on cc.COMM_CODE=14c.COMM_CODE "
                + "left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE "
                + "where  14c.MM_YYYY='" + month_date + "'  group by ZONE_CODE;";
        return queryGst14aa;
    }
    public String QueryFor_gst1a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT zc.ZONE_NAME, cc.ZONE_CODE,cc.COMM_NAME," +
                "(14c.DISPOSAL_OF_ARN_WITHIN_7) as col6, " +
                "(14c.NO_OF_ARN_RECEIVED_GSTN) as col2, " +
                "(14c.NO_OF_ARN_RECEIVED_CPC) as col4 FROM mis_gst_commcode as cc " +
                "right join mis_dpm_gst_14a as 14c on cc.COMM_CODE=14c.COMM_CODE " +
                "left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE " +
                "where  14c.MM_YYYY='" + month_date + "' and cc.ZONE_CODE = '" + zone_code + "';";
        return queryGst14aa;
    }
    public String QueryFor_gst1a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst1b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT zc.ZONE_NAME, cc.ZONE_CODE,sum(14c.DISPOSAL_OF_ARN_PV_NOT_RECEIVED) as col10,"
                + " sum(14c.DISPOSAL_OF_ARN_PV_RECOMMENDED) as col7 "
                + "FROM mis_gst_commcode as cc right join mis_dpm_gst_14a as 14c on cc.COMM_CODE=14c.COMM_CODE"
                + " left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE"
                + " where  14c.MM_YYYY='" + month_date + "'  group by ZONE_CODE;";
        return queryGst14aa;
    }
    public String QueryFor_gst1b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT zc.ZONE_NAME, cc.ZONE_CODE,cc.COMM_NAME, " +
                "(14c.DISPOSAL_OF_ARN_PV_NOT_RECEIVED) as col10, " +
                "(14c.DISPOSAL_OF_ARN_PV_RECOMMENDED) as col7 FROM mis_gst_commcode as cc " +
                "right join mis_dpm_gst_14a as 14c on cc.COMM_CODE=14c.COMM_CODE " +
                "left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE " +
                "where  14c.MM_YYYY='" + month_date + "'  and cc.ZONE_CODE = '" + zone_code + "';";
        return queryGst14aa;
    }
    public String QueryFor_gst1b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst1c_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT \n" +
                "    zc.ZONE_NAME, \n" +
                "    cc.ZONE_CODE,\n" +
                "    SUM(14c.DISPOSAL_OF_ARN_DEEMED_REG) AS col10,\n" +
                "    SUM(14c.NO_OF_ARN_RECEIVED_GSTN) AS col2,\n" +
                "    SUM(14c.NO_OF_ARN_RECEIVED_CPC) AS col3,\n" +
                "    (SUM(14c.DISPOSAL_OF_ARN_DEEMED_REG) / (SUM(14c.NO_OF_ARN_RECEIVED_GSTN) + SUM(14c.NO_OF_ARN_RECEIVED_CPC)))*100 AS total_score\n" +
                "FROM \n" +
                "    mis_gst_commcode AS cc\n" +
                "RIGHT JOIN \n" +
                "    mis_dpm_gst_14a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN \n" +
                "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE \n" +
                "    MM_YYYY = '" + month_date + "'\n" +
                "GROUP BY \n" +
                "    cc.ZONE_CODE\n" +
                "ORDER BY \n" +
                "    total_score ;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst1c_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT \n" +
                "    zc.ZONE_NAME, \n" +
                "    cc.ZONE_CODE,\n" +
                "    cc.COMM_NAME, \n" +
                "    14c.DISPOSAL_OF_ARN_DEEMED_REG AS col10,\n" +
                "    14c.NO_OF_ARN_RECEIVED_GSTN AS col2,\n" +
                "    14c.NO_OF_ARN_RECEIVED_CPC AS col3,\n" +
                "    (14c.DISPOSAL_OF_ARN_DEEMED_REG / (14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC)) * 100 AS total_score\n" +
                "FROM  \n" +
                "    mis_gst_commcode AS cc\n" +
                "RIGHT JOIN \n" +
                "    mis_dpm_gst_14a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN \n" +
                "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE \n" +
                "    MM_YYYY = '" + month_date + "' \n" +
                "    AND zc.ZONE_CODE = '" + zone_code + "'\n" +
                "ORDER BY \n" +
                "    total_score;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst1c_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT \n" +
                "    zc.ZONE_NAME, \n" +
                "    cc.ZONE_CODE,\n" +
                "    cc.COMM_NAME, \n" +
                "    14c.DISPOSAL_OF_ARN_DEEMED_REG AS col10,\n" +
                "    14c.NO_OF_ARN_RECEIVED_GSTN AS col2,\n" +
                "    14c.NO_OF_ARN_RECEIVED_CPC AS col3,\n" +
                "    (14c.DISPOSAL_OF_ARN_DEEMED_REG / (14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC)) * 100 AS total_score\n" +
                "FROM  \n" +
                "    mis_gst_commcode AS cc\n" +
                "RIGHT JOIN \n" +
                "    mis_dpm_gst_14a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN \n" +
                "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE \n" +
                "    MM_YYYY = '" + month_date + "'\n" +
                "ORDER BY \n" +
                "    total_score;\n";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst1d_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT zc.ZONE_NAME, \n" +
                "       cc.ZONE_CODE, \n" +
                "       SUM((14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC) - \n" +
                "           (14c.DISPOSAL_OF_ARN_TRANSFERED_CPC + 14c.DISPOSAL_OF_ARN_DEEMED_REG + \n" +
                "            14c.DISPOSAL_OF_ARN_WITHIN_7 + 14c.DISPOSAL_OF_ARN_PV_APPROVED + 14c.DISPOSAL_OF_ARN_PV_REJECTED)) AS col14, \n" +
                "       SUM(14c.OPENING_BALANCE) AS col1, \n" +
                "       SUM(14c.NO_OF_ARN_RECEIVED_GSTN) AS col2, \n" +
                "       SUM(14c.NO_OF_ARN_RECEIVED_CPC) AS col3,\n" +
                "       (SUM((14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC) - \n" +
                "           (14c.DISPOSAL_OF_ARN_TRANSFERED_CPC + 14c.DISPOSAL_OF_ARN_DEEMED_REG + \n" +
                "            14c.DISPOSAL_OF_ARN_WITHIN_7 + 14c.DISPOSAL_OF_ARN_PV_APPROVED + 14c.DISPOSAL_OF_ARN_PV_REJECTED)) /\n" +
                "       (SUM(14c.OPENING_BALANCE) + SUM(14c.NO_OF_ARN_RECEIVED_GSTN) + SUM(14c.NO_OF_ARN_RECEIVED_CPC))) * 100 AS total_score\n" +
                "FROM mis_gst_commcode cc \n" +
                "RIGHT JOIN mis_dpm_gst_14a 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE MM_YYYY = '" + month_date + "'\n" +
                "GROUP BY cc.ZONE_CODE\n" +
                "ORDER BY total_score ASC;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst1d_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "SELECT zc.ZONE_NAME, \n" +
                "       cc.ZONE_CODE, \n" +
                "       cc.COMM_NAME,\n" +
                "       (14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC - \n" +
                "       (14c.DISPOSAL_OF_ARN_TRANSFERED_CPC + 14c.DISPOSAL_OF_ARN_DEEMED_REG + \n" +
                "        14c.DISPOSAL_OF_ARN_WITHIN_7 + 14c.DISPOSAL_OF_ARN_PV_APPROVED + \n" +
                "        14c.DISPOSAL_OF_ARN_PV_REJECTED)) AS col14, \n" +
                "       14c.OPENING_BALANCE AS col1,\n" +
                "       14c.NO_OF_ARN_RECEIVED_GSTN AS col2,\n" +
                "       14c.NO_OF_ARN_RECEIVED_CPC AS col3,\n" +
                "       ((14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC - \n" +
                "        (14c.DISPOSAL_OF_ARN_TRANSFERED_CPC + 14c.DISPOSAL_OF_ARN_DEEMED_REG + \n" +
                "         14c.DISPOSAL_OF_ARN_WITHIN_7 + 14c.DISPOSAL_OF_ARN_PV_APPROVED + \n" +
                "         14c.DISPOSAL_OF_ARN_PV_REJECTED)) / \n" +
                "       (14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC)) * 100 AS total_score\n" +
                "FROM mis_gst_commcode cc\n" +
                "RIGHT JOIN mis_dpm_gst_14a 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE MM_YYYY = '" + month_date + "'\n" +
                "  AND zc.ZONE_CODE = '" + zone_code + "'\n" +
                "ORDER BY total_score ASC\n" +
                "LIMIT 0, 1000;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst1d_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "SELECT \n" +
                "    zc.ZONE_NAME, \n" +
                "    cc.ZONE_CODE,\n" +
                "    cc.COMM_NAME,\n" +
                "    ((14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC) - \n" +
                "    (14c.DISPOSAL_OF_ARN_TRANSFERED_CPC + 14c.DISPOSAL_OF_ARN_DEEMED_REG + \n" +
                "    14c.DISPOSAL_OF_ARN_WITHIN_7 + 14c.DISPOSAL_OF_ARN_PV_APPROVED + \n" +
                "    14c.DISPOSAL_OF_ARN_PV_REJECTED)) AS col14, \n" +
                "    14c.OPENING_BALANCE AS col1,\n" +
                "    14c.NO_OF_ARN_RECEIVED_GSTN AS col2,\n" +
                "    14c.NO_OF_ARN_RECEIVED_CPC AS col3,\n" +
                "    ((14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC - \n" +
                "      (14c.DISPOSAL_OF_ARN_TRANSFERED_CPC + 14c.DISPOSAL_OF_ARN_DEEMED_REG + \n" +
                "       14c.DISPOSAL_OF_ARN_WITHIN_7 + 14c.DISPOSAL_OF_ARN_PV_APPROVED + \n" +
                "       14c.DISPOSAL_OF_ARN_PV_REJECTED)) / \n" +
                "     (14c.OPENING_BALANCE + 14c.NO_OF_ARN_RECEIVED_GSTN + 14c.NO_OF_ARN_RECEIVED_CPC)) * 100 AS total_score\n" +
                "FROM \n" +
                "    mis_gst_commcode cc \n" +
                "RIGHT JOIN \n" +
                "    mis_dpm_gst_14a 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN \n" +
                "    mis_gst_zonecode zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE \n" +
                "    MM_YYYY = '" + month_date + "'\n" +
                "ORDER BY \n" +
                "    total_score ASC;\n";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst1e_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "SELECT \n" +
                "    zc.ZONE_NAME, \n" +
                "    cc.ZONE_CODE,\n" +
                "    SUM(\n" +
                "        (14c.SUO_MOTO_OPENING_BALANCE + 14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION - 14c.SUO_MOTO_GSTIN_CANCELLED) +\n" +
                "        (14c.CANCELLATION_OPENING_BALANCE + 14c.CANCELLATION_NO_APPLICATION_RECEIVED - 14c.CANCELLATION_GSTIN_CANCELLED)\n" +
                "    ) AS col9,\n" +
                "    SUM(14c.SUO_MOTO_OPENING_BALANCE) AS col1,\n" +
                "    SUM(14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION) AS col2,\n" +
                "    SUM(14c.CANCELLATION_OPENING_BALANCE) AS col5,\n" +
                "    SUM(14c.CANCELLATION_NO_APPLICATION_RECEIVED) AS col6,\n" +
                "    (SUM(\n" +
                "        (14c.SUO_MOTO_OPENING_BALANCE + 14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION - 14c.SUO_MOTO_GSTIN_CANCELLED) +\n" +
                "        (14c.CANCELLATION_OPENING_BALANCE + 14c.CANCELLATION_NO_APPLICATION_RECEIVED - 14c.CANCELLATION_GSTIN_CANCELLED)\n" +
                "    ) / \n" +
                "    (SUM(14c.SUO_MOTO_OPENING_BALANCE) + \n" +
                "    SUM(14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION) + \n" +
                "    SUM(14c.CANCELLATION_OPENING_BALANCE) + \n" +
                "    SUM(14c.CANCELLATION_NO_APPLICATION_RECEIVED))) * 100 AS total_score\n" +
                "FROM \n" +
                "    mis_gst_commcode AS cc\n" +
                "RIGHT JOIN \n" +
                "    mis_dpm_gst_15a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN \n" +
                "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE \n" +
                "    14c.MM_YYYY = '" + month_date + "'\n" +
                "GROUP BY \n" +
                "    cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "ORDER BY \n" +
                "    total_score ASC;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst1e_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT \n" +
                "    zc.ZONE_NAME, \n" +
                "    cc.ZONE_CODE,\n" +
                "    cc.COMM_NAME,\n" +
                "    ((tbl14c.SUO_MOTO_OPENING_BALANCE + \n" +
                "      tbl14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION - \n" +
                "      tbl14c.SUO_MOTO_GSTIN_CANCELLED) +\n" +
                "     (tbl14c.CANCELLATION_OPENING_BALANCE + \n" +
                "      tbl14c.CANCELLATION_NO_APPLICATION_RECEIVED - \n" +
                "      tbl14c.CANCELLATION_GSTIN_CANCELLED)) AS col9,\n" +
                "    tbl14c.SUO_MOTO_OPENING_BALANCE AS col1,\n" +
                "    tbl14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION AS col2,\n" +
                "    tbl14c.CANCELLATION_OPENING_BALANCE AS col5,\n" +
                "    tbl14c.CANCELLATION_NO_APPLICATION_RECEIVED AS col6,\n" +
                "    ((tbl14c.SUO_MOTO_OPENING_BALANCE + \n" +
                "      tbl14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION - \n" +
                "      tbl14c.SUO_MOTO_GSTIN_CANCELLED) +\n" +
                "     (tbl14c.CANCELLATION_OPENING_BALANCE + \n" +
                "      tbl14c.CANCELLATION_NO_APPLICATION_RECEIVED - \n" +
                "      tbl14c.CANCELLATION_GSTIN_CANCELLED)) / \n" +
                "    (tbl14c.SUO_MOTO_OPENING_BALANCE +\n" +
                "     tbl14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION +\n" +
                "     tbl14c.CANCELLATION_OPENING_BALANCE +\n" +
                "     tbl14c.CANCELLATION_NO_APPLICATION_RECEIVED) * 100 AS total_score\n" +
                "FROM \n" +
                "    mis_gst_commcode AS cc\n" +
                "RIGHT JOIN \n" +
                "    mis_dpm_gst_15a AS tbl14c ON cc.COMM_CODE = tbl14c.COMM_CODE\n" +
                "LEFT JOIN \n" +
                "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE \n" +
                "    MM_YYYY = '" + month_date + "' \n" +
                "    AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                "ORDER BY \n" +
                "    total_score ASC\n" +
                "LIMIT 0, 1000;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst1e_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "SELECT \n" +
                "    zc.ZONE_NAME, \n" +
                "    cc.ZONE_CODE,\n" +
                "    cc.COMM_NAME,\n" +
                "    ((tbl14c.SUO_MOTO_OPENING_BALANCE +\n" +
                "      tbl14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION -\n" +
                "      tbl14c.SUO_MOTO_GSTIN_CANCELLED) +\n" +
                "     (tbl14c.CANCELLATION_OPENING_BALANCE +\n" +
                "      tbl14c.CANCELLATION_NO_APPLICATION_RECEIVED -\n" +
                "      tbl14c.CANCELLATION_GSTIN_CANCELLED)) AS col9,\n" +
                "    tbl14c.SUO_MOTO_OPENING_BALANCE AS col1,\n" +
                "    tbl14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION AS col2,\n" +
                "    tbl14c.CANCELLATION_OPENING_BALANCE AS col5,\n" +
                "    tbl14c.CANCELLATION_NO_APPLICATION_RECEIVED AS col6,\n" +
                "    (((\n" +
                "        tbl14c.SUO_MOTO_OPENING_BALANCE +\n" +
                "        tbl14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION -\n" +
                "        tbl14c.SUO_MOTO_GSTIN_CANCELLED) +\n" +
                "       (tbl14c.CANCELLATION_OPENING_BALANCE +\n" +
                "        tbl14c.CANCELLATION_NO_APPLICATION_RECEIVED -\n" +
                "        tbl14c.CANCELLATION_GSTIN_CANCELLED))\n" +
                "        / (tbl14c.SUO_MOTO_OPENING_BALANCE +\n" +
                "           tbl14c.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION +\n" +
                "           tbl14c.CANCELLATION_OPENING_BALANCE +\n" +
                "           tbl14c.CANCELLATION_NO_APPLICATION_RECEIVED)) * 100 AS total_score\n" +
                "FROM \n" +
                "    mis_gst_commcode AS cc\n" +
                "RIGHT JOIN \n" +
                "    mis_dpm_gst_15a AS tbl14c ON cc.COMM_CODE = tbl14c.COMM_CODE\n" +
                "LEFT JOIN \n" +
                "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE \n" +
                "    tbl14c.MM_YYYY ='" + month_date + "'\n" +
                "ORDER BY \n" +
                "    total_score ASC;\n";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst1f_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT \n" +
                "    zc.ZONE_NAME, \n" +
                "    cc.ZONE_CODE,\n" +
                "    SUM(15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED - 15a.REVOCATION_GSTIN_REVOKED - 15a.REVOCATION_APPLICATION_REJECTED) AS col14,\n" +
                "    SUM(15a.REVOCATION_OPENING_BALANCE) AS col10,\n" +
                "    SUM(15a.REVOCATION_ARN_RECEIVED) AS col11,\n" +
                "    (SUM(15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED - 15a.REVOCATION_GSTIN_REVOKED - 15a.REVOCATION_APPLICATION_REJECTED) / \n" +
                "    (SUM(15a.REVOCATION_OPENING_BALANCE) + SUM(15a.REVOCATION_ARN_RECEIVED)) * 100) AS total_score\n" +
                "FROM \n" +
                "    mis_gst_commcode AS cc\n" +
                "RIGHT JOIN \n" +
                "    mis_dpm_gst_15a AS 15a ON cc.COMM_CODE = 15a.COMM_CODE\n" +
                "LEFT JOIN \n" +
                "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE \n" +
                "    MM_YYYY = '" + month_date + "'\n" +
                "GROUP BY \n" +
                "    zc.ZONE_NAME, \n" +
                "    cc.ZONE_CODE\n" +
                "ORDER BY \n" +
                "    total_score ASC;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst1f_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT \n" +
                "    zc.ZONE_NAME, \n" +
                "    cc.ZONE_CODE,\n" +
                "    cc.COMM_NAME,\n" +
                "    (15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED - 15a.REVOCATION_GSTIN_REVOKED - 15a.REVOCATION_APPLICATION_REJECTED) AS col14,\n" +
                "    15a.REVOCATION_OPENING_BALANCE AS col10,\n" +
                "    15a.REVOCATION_ARN_RECEIVED AS col11,\n" +
                "    ((15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED - 15a.REVOCATION_GSTIN_REVOKED - 15a.REVOCATION_APPLICATION_REJECTED) / \n" +
                "     (15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED)) * 100 AS total_score\n" +
                "FROM \n" +
                "    mis_gst_commcode AS cc\n" +
                "RIGHT JOIN \n" +
                "    mis_dpm_gst_15a AS 15a ON cc.COMM_CODE = 15a.COMM_CODE\n" +
                "LEFT JOIN \n" +
                "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE \n" +
                "    MM_YYYY = '" + month_date + "'\n" +
                "    AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                "ORDER BY \n" +
                "    total_score ASC;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst1f_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "SELECT \n" +
                "    zc.ZONE_NAME, \n" +
                "    cc.ZONE_CODE,\n" +
                "    cc.COMM_NAME,\n" +
                "    (15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED - 15a.REVOCATION_GSTIN_REVOKED - 15a.REVOCATION_APPLICATION_REJECTED) AS col14,\n" +
                "    15a.REVOCATION_OPENING_BALANCE AS col10,\n" +
                "    15a.REVOCATION_ARN_RECEIVED AS col11,\n" +
                "    (15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED - 15a.REVOCATION_GSTIN_REVOKED - 15a.REVOCATION_APPLICATION_REJECTED) / \n" +
                "    (15a.REVOCATION_OPENING_BALANCE + 15a.REVOCATION_ARN_RECEIVED) * 100 AS total_score\n" +
                "FROM \n" +
                "    mis_gst_commcode AS cc\n" +
                "RIGHT JOIN \n" +
                "    mis_dpm_gst_15a AS 15a ON cc.COMM_CODE = 15a.COMM_CODE\n" +
                "LEFT JOIN \n" +
                "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE \n" +
                "    MM_YYYY = '" + month_date + "'\n" +
                "ORDER BY \n" +
                "    total_score ASC;\n";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst2_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, SUM(14c.GSTR_3BM_F) AS col3,\n"
                + "    CASE WHEN SUM(14c.GSTR_3BM_F) <> 0 \n"
                + "         THEN (SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / SUM(14c.GSTR_3BM_F)) * 100\n"
                + "         ELSE 0 END AS total_score,\n"
                + "    DENSE_RANK() OVER (ORDER BY \n"
                + "        CASE WHEN SUM(14c.GSTR_3BM_F) <> 0 \n"
                + "             THEN (SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / SUM(14c.GSTR_3BM_F)) * 100\n"
                + "             ELSE 0 END ) AS z_rank\n"
                + "FROM mis_gst_commcode AS cc \n"
                + "RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
                + "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "WHERE 14c.MM_YYYY = '"+ month_date+"' \n"
                + "GROUP BY zc.ZONE_NAME,cc.ZONE_CODE\n"
                + "ORDER BY total_score ;";
        return queryGst14aa;
    }
    public String QueryFor_gst2_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa=  "SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,(14c.GSTR_3BM_F-14c.GSTR_3BM_D) AS col21,14c.GSTR_3BM_F as col3 "
                + "FROM  mis_gst_commcode as cc right join mis_gst_gst_2 as 14c on cc.COMM_CODE=14c.COMM_CODE "
                + "left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE "
                + "where  14c.MM_YYYY='"+ month_date+"' and zc.ZONE_CODE='"+zone_code+"';";
        return queryGst14aa;
    }
    public String QueryFor_gst2_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,(14c.GSTR_3BM_F-14c.GSTR_3BM_D) AS col21,14c.GSTR_3BM_F as col3 \n" +
                "FROM  mis_gst_commcode as cc right join mis_gst_gst_2 as 14c on cc.COMM_CODE=14c.COMM_CODE \n" +
                "left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE \n" +
                "where  14c.MM_YYYY='"+ month_date+"';";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst3a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "WITH ranked_data AS (\n" +
                "    SELECT current_data.ZONE_NAME, current_data.ZONE_CODE, current_data.col4, \n" +
                "           current_data.col9, current_data.col10, current_data.col2, previous_data.col1,\n" +
                "           (current_data.col4 + current_data.col9 + current_data.col10) / \n" +
                "           (current_data.col2 + previous_data.col1) * 100 AS total_score\n" +
                "    FROM  \n" +
                "        (\n" +
                "            SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
                "                   SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4, \n" +
                "                   SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9, \n" +
                "                   SUM(14c.OUTCOME_SECTION_61) AS col10, \n" +
                "                   SUM(14c.RETURN_SCRUTINY) AS col2  \n" +
                "            FROM mis_gst_commcode AS cc  \n" +
                "            RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "            LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "            WHERE 14c.MM_YYYY = '" +month_date+"' \n" +
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
                "numerator_data AS (\n" +
                "    SELECT ZONE_NAME, ZONE_CODE, col4, col9, col10, col2, col1, total_score, \n" +
                "           (col4 + col9 + col10) AS numerator_3a\n" +
                "    FROM ranked_data\n" +
                "),\n" +
                "ranked_numerator AS (\n" +
                "    SELECT *, ROW_NUMBER() OVER (ORDER BY numerator_3a) AS rn,\n" +
                "              COUNT(*) OVER () AS cnt\n" +
                "    FROM numerator_data\n" +
                ")\n" +
                "SELECT ZONE_NAME, ZONE_CODE, col4, col9, col10, col2, col1, total_score, numerator_3a,\n" +
                "concat((col4 + col9 + col10) , '/', (col2 + col1)) as absval,\n" +
                "       RANK() OVER (ORDER BY total_score DESC) AS z_rank,\n" +
                "       CASE\n" +
                "           WHEN cnt % 2 = 1 THEN (SELECT numerator_3a FROM ranked_numerator WHERE rn = (cnt + 1) / 2)\n" +
                "           ELSE (SELECT AVG(numerator_3a) FROM ranked_numerator WHERE rn IN (cnt / 2, cnt / 2 + 1))\n" +
                "       END AS median_numerator_3a\n" +
                "FROM ranked_numerator;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst3a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "WITH PreviousMonthData AS (\n" +
                "    SELECT zc.ZONE_NAME AS prev_ZONE_NAME, cc.COMM_NAME AS prev_COMM_NAME, cc.ZONE_CODE AS prev_ZONE_CODE, 14c.CLOSING_NO AS prev_col1\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
                "),\n" +
                "NumeratorData AS (\n" +
                "    SELECT (14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61) AS numerator_3a\n" +
                "    FROM mis_dggst_gst_scr_1 AS 14c WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "),\n" +
                "RankedData AS (\n" +
                "    SELECT numerator_3a,\n" +
                "        ROW_NUMBER() OVER (ORDER BY numerator_3a) AS row_num,COUNT(*) OVER() AS total_rows\n" +
                "    FROM NumeratorData\n" +
                "),\n" +
                "MedianValue AS (\n" +
                "    SELECT AVG(numerator_3a) AS median_value\n" +
                "    FROM RankedData WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                ")\n" +
                "SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 14c.SCRUTINIZED_DISCRIPANCY_FOUND AS col4,14c.OUTCOME_ASMT_12_ISSUED AS col9, 14c.OUTCOME_SECTION_61 AS col10,\n" +
                "    (14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61) AS numerator_3a,\n" +
                "    14c.RETURN_SCRUTINY AS col2, pm.prev_col1,\n" +
                "    ((14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61) / (14c.RETURN_SCRUTINY + pm.prev_col1)) * 100 AS total_score,\n" +
                "    mv.median_value AS median\n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "LEFT JOIN PreviousMonthData AS pm ON pm.prev_ZONE_CODE = cc.ZONE_CODE \n" +
                "    AND pm.prev_COMM_NAME = cc.COMM_NAME\n" +
                "CROSS JOIN MedianValue AS mv\n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "AND zc.ZONE_CODE = '" + zone_code + "'  -- Condition added here\n" +
                "GROUP BY zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 14c.SCRUTINIZED_DISCRIPANCY_FOUND, 14c.OUTCOME_ASMT_12_ISSUED, 14c.OUTCOME_SECTION_61, 14c.RETURN_SCRUTINY, pm.prev_col1, mv.median_value\n" +
                "ORDER BY total_score DESC, zc.ZONE_NAME, cc.COMM_NAME;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst3a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "WITH PreviousMonthData AS (\n" +
                "    SELECT zc.ZONE_NAME AS prev_ZONE_NAME, cc.COMM_NAME AS prev_COMM_NAME, cc.ZONE_CODE AS prev_ZONE_CODE, 14c.CLOSING_NO AS prev_col1\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
                "),\n" +
                "NumeratorData AS (SELECT (14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61) AS numerator_3a\n" +
                "    FROM mis_dggst_gst_scr_1 AS 14c WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "),\n" +
                "RankedData AS (\n" +
                "    SELECT numerator_3a,\n" +
                "        ROW_NUMBER() OVER (ORDER BY numerator_3a) AS row_num,COUNT(*) OVER() AS total_rows\n" +
                "    FROM NumeratorData\n" +
                "),\n" +
                "MedianValue AS (\n" +
                "    SELECT AVG(numerator_3a) AS median_value\n" +
                "    FROM RankedData WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                ")\n" +
                "SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 14c.SCRUTINIZED_DISCRIPANCY_FOUND AS col4,14c.OUTCOME_ASMT_12_ISSUED AS col9, 14c.OUTCOME_SECTION_61 AS col10,\n" +
                "    (14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61) AS numerator_3a,\n" +
                "    14c.RETURN_SCRUTINY AS col2,pm.prev_col1,\n" +
                "    ((14c.SCRUTINIZED_DISCRIPANCY_FOUND + 14c.OUTCOME_ASMT_12_ISSUED + 14c.OUTCOME_SECTION_61) / (14c.RETURN_SCRUTINY + pm.prev_col1)) * 100 AS total_score,\n" +
                "    mv.median_value AS median\n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "LEFT JOIN PreviousMonthData AS pm ON pm.prev_ZONE_CODE = cc.ZONE_CODE \n" +
                "    AND pm.prev_COMM_NAME = cc.COMM_NAME\n" +
                "CROSS JOIN MedianValue AS mv\n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "GROUP BY zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 14c.SCRUTINIZED_DISCRIPANCY_FOUND, 14c.OUTCOME_ASMT_12_ISSUED, 14c.OUTCOME_SECTION_61, 14c.RETURN_SCRUTINY, pm.prev_col1, mv.median_value\n" +
                "ORDER BY total_score DESC, zc.ZONE_NAME, cc.COMM_NAME;\n";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst3b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH ranked_data AS (\n" +
                "    SELECT \n" +
                "        SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) AS numerator_3b,\n" +
                "        ROW_NUMBER() OVER (ORDER BY SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY)) AS row_num,\n" +
                "        COUNT(*) OVER () AS total_count\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" +month_date+"'\n" +
                "    GROUP BY cc.ZONE_CODE\n" +
                "),\n" +
                "median_data AS (\n" +
                "    SELECT \n" +
                "        CASE \n" +
                "            WHEN total_count % 2 = 1 THEN \n" +
                "                (SELECT numerator_3b FROM ranked_data WHERE row_num = (total_count + 1) / 2)\n" +
                "            ELSE \n" +
                "                (SELECT AVG(numerator_3b) FROM ranked_data WHERE row_num IN ((total_count / 2), (total_count / 2) + 1))\n" +
                "        END AS median_numerator_3b\n" +
                "    FROM ranked_data\n" +
                "    LIMIT 1\n" +
                ")\n" +
                "SELECT t.ZONE_NAME, t.ZONE_CODE, t.score_of_parameter,t.absval,t.numerator_3b, \n" +
                "    DENSE_RANK() OVER (ORDER BY t.score_of_parameter DESC) AS z_rank,\n" +
                "    m.median_numerator_3b\n" +
                "FROM (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
                "        SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) AS numerator_3b,\n" +
                "        (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100) / SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter,\n" +
                "        CONCAT(SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY), ' / ', SUM(14c.TAX_LIABILITY_DETECTECT)) AS absval\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" +month_date+"' \n" +
                "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                ") AS t\n" +
                "CROSS JOIN median_data m\n" +
                "ORDER BY t.score_of_parameter DESC;";
        return queryGst14aa;
    }
    public String QueryFor_gst3b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH ranked_data AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.COMM_NAME, zc.ZONE_CODE,\n" +
                "        SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) / SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter,\n" +
                "        CONCAT(SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY), '/', SUM(14c.TAX_LIABILITY_DETECTECT)) AS absval,\n" +
                "        SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) AS numerator_3b,\n" +
                "        ROW_NUMBER() OVER (ORDER BY SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY)) AS row_num,\n" +
                "        COUNT(*) OVER () AS total_rows\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
                "    GROUP BY zc.ZONE_NAME, cc.COMM_NAME, zc.ZONE_CODE\n" +
                "),\n" +
                "median_calc AS (\n" +
                "    SELECT \n" +
                "        CASE \n" +
                "            WHEN total_rows % 2 = 1 THEN\n" +
                "                (SELECT numerator_3b FROM ranked_data WHERE row_num = (total_rows + 1) / 2)\n" +
                "            ELSE\n" +
                "                (SELECT AVG(numerator_3b) FROM ranked_data WHERE row_num IN (total_rows / 2, (total_rows / 2) + 1))\n" +
                "        END AS median_3b\n" +
                "    FROM ranked_data\n" +
                "    LIMIT 1\n" +
                ")\n" +
                "SELECT r.ZONE_NAME, r.COMM_NAME, r.ZONE_CODE, r.score_of_parameter, r.absval, r.numerator_3b, m.median_3b\n" +
                "FROM ranked_data r\n" +
                "CROSS JOIN median_calc m\n" +
                "WHERE r.ZONE_CODE = '" + zone_code + "'\n" +
                "ORDER BY r.score_of_parameter DESC;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst3b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa=  "WITH ranked_data AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.COMM_NAME, zc.ZONE_CODE,\n" +
                "        SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) / SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter,\n" +
                "        CONCAT(SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY), '/', SUM(14c.TAX_LIABILITY_DETECTECT)) AS absval,\n" +
                "        SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) AS numerator_3b,\n" +
                "        ROW_NUMBER() OVER (ORDER BY SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY)) AS row_num,\n" +
                "        COUNT(*) OVER () AS total_rows\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
                "    GROUP BY zc.ZONE_NAME, cc.COMM_NAME, zc.ZONE_CODE\n" +
                "),\n" +
                "median_calc AS (\n" +
                "    SELECT \n" +
                "        CASE \n" +
                "            WHEN total_rows % 2 = 1 THEN\n" +
                "                (SELECT numerator_3b FROM ranked_data WHERE row_num = (total_rows + 1) / 2)\n" +
                "            ELSE\n" +
                "                (SELECT AVG(numerator_3b) FROM ranked_data WHERE row_num IN (total_rows / 2, (total_rows / 2) + 1))\n" +
                "        END AS median_3b\n" +
                "    FROM ranked_data\n" +
                "    LIMIT 1\n" +
                ")\n" +
                "SELECT r.ZONE_NAME, r.COMM_NAME, r.ZONE_CODE,r.score_of_parameter,r.absval,r.numerator_3b,m.median_3b\n" +
                "FROM ranked_data r\n" +
                "CROSS JOIN median_calc m\n" +
                "ORDER BY r.score_of_parameter DESC;";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst4a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="-- ----gst4a zone wise \n" +
                "WITH CTE AS (\n" +
                "SELECT zc.ZONE_NAME,cc.ZONE_CODE,\n" +
                "SUM(14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO) AS col13, SUM(14c.OPENING_BALANCE_NO) AS col1,\n" +
                "((SUM(14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO) * 100) / SUM(14c.OPENING_BALANCE_NO)) AS total_score\n" +
                "FROM mis_gst_commcode cc RIGHT JOIN mis_gi_gst_2 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE\n" +
                "),\n" +
                "CTE_ranked AS (\n" +
                "SELECT CTE.*, \n" +
                "ROW_NUMBER() OVER (ORDER BY col13 ASC) AS row_num,\n" +
                "COUNT(*) OVER () AS total_rows\n" +
                "FROM CTE\n" +
                ")\n" +
                "SELECT CTE_ranked.*,\n" +
                "(SELECT AVG(subquery.col13)\n" +
                "FROM ( SELECT col13\n" +
                "FROM CTE_ranked WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                ") AS subquery) AS median_4a\n" +
                "FROM CTE_ranked ORDER BY total_score DESC;";
//        String queryGst14aa= "SELECT zc.ZONE_NAME,  cc.ZONE_CODE, SUM(14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO) AS col13,  SUM(14c.OPENING_BALANCE_NO) AS col1 ,\n" +
//                "((SUM(14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO) * 100)/ SUM(14c.OPENING_BALANCE_NO)) as total_score\n" +
//                "FROM mis_gst_commcode AS cc\n" +
//                "RIGHT JOIN mis_gi_gst_2 AS 14c  ON cc.COMM_CODE = 14c.COMM_CODE \n" +
//                "LEFT JOIN    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
//                "WHERE 14c.MM_YYYY = '"+ month_date+"' GROUP BY  cc.ZONE_CODE\n" +
//                "order by total_score desc;";
        return queryGst14aa;
    }
    public String QueryFor_gst4a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "WITH RankedData AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE,cc.COMM_NAME, (14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO) AS col13,\n" +
                "(14c.OPENING_BALANCE_NO) AS col1,\n" +
                "((14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO) * 100 / 14c.OPENING_BALANCE_NO) AS score_of_parameter4a,\n" +
                "ROW_NUMBER() OVER (ORDER BY (14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO)) AS row_num,\n" +
                "COUNT(*) OVER () AS total_count\n" +
                "FROM mis_gst_commcode AS cc\n" +
                "RIGHT JOIN mis_gi_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "'),\n" +
                "MedianData AS (\n" +
                "SELECT ZONE_NAME, ZONE_CODE, COMM_NAME, col13, col1, score_of_parameter4a,\n" +
                "(SELECT AVG(col13) FROM (SELECT col13\n" +
                "FROM RankedData WHERE\n" +
                "row_num IN (FLOOR((total_count + 1) / 2.0), CEIL((total_count + 1) / 2.0))\n" +
                ") AS MedianSubquery\n" +
                ") AS median_4a\n" +
                "FROM RankedData\n" +
                ")\n" +
                "SELECT ZONE_NAME, ZONE_CODE, COMM_NAME, col13, col1, score_of_parameter4a, median_4a\n" +
                "FROM MedianData WHERE ZONE_CODE = '" + zone_code + "'\n" +
                "ORDER BY score_of_parameter4a DESC;";
//        String queryGst14aa="SELECT zc.ZONE_NAME,cc.ZONE_CODE,cc.COMM_NAME,\n" +
//                "                            (14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO) AS col13,\n" +
//                "                            (14c.OPENING_BALANCE_NO) AS col1,\n" +
//                "                            ((14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO) * 100 / (14c.OPENING_BALANCE_NO)) as score_of_parameter4a\n" +
//                "                        FROM mis_gst_commcode AS cc\n" +
//                "                        right  join  mis_gi_gst_2 as 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
//                "                        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
//                "                        WHERE 14c.MM_YYYY = '" + month_date+ "' and cc.ZONE_CODE = '" + zone_code + "'\n" +
//                "                        order by score_of_parameter4a desc;";
        return queryGst14aa;
    }
    public String QueryFor_gst4a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH RankedData AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, (14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO) AS col13, (14c.OPENING_BALANCE_NO) AS col1,\n" +
                "((14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO) * 100 / 14c.OPENING_BALANCE_NO) AS score_of_parameter4a,\n" +
                "ROW_NUMBER() OVER (ORDER BY (14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO)) AS row_num, COUNT(*) OVER () AS total_count\n" +
                "FROM mis_gst_commcode AS cc\n" +
                "RIGHT JOIN mis_gi_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "'),\n" +
                "MedianData AS (\n" +
                "SELECT ZONE_NAME, ZONE_CODE, COMM_NAME, col13,col1,score_of_parameter4a,\n" +
                "(SELECT AVG(col13)\n" +
                "FROM\n" +
                "(SELECT col13 FROM RankedData\n" +
                "WHERE row_num IN (FLOOR((total_count + 1) / 2.0), CEIL((total_count + 1) / 2.0))\n" +
                ") AS MedianSubquery\n" +
                ") AS median_4a\n" +
                "FROM RankedData )\n" +
                "SELECT ZONE_NAME,ZONE_CODE,COMM_NAME,col13,col1,score_of_parameter4a,median_4a\n" +
                "FROM MedianData ORDER BY score_of_parameter4a DESC;";
//        String queryGst14aa="SELECT zc.ZONE_NAME,cc.ZONE_CODE,cc.COMM_NAME,\n" +
//                "                            (14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO) AS col13,\n" +
//                "                            (14c.OPENING_BALANCE_NO) AS col1,\n" +
//                "                            ((14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO) * 100 / (14c.OPENING_BALANCE_NO)) as score_of_parameter4a\n" +
//                "                        FROM mis_gst_commcode AS cc\n" +
//                "                        right  join  mis_gi_gst_2 as 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
//                "                        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
//                "                        WHERE 14c.MM_YYYY = '" + month_date+ "'\n" +
//                "                        order by score_of_parameter4a desc;";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst4b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa=" SELECT zc.ZONE_NAME, cc.ZONE_CODE,sum(1) as col29,sum(2c.MORE_THAN_2_NO) as col31,sum(2c.MORE_THAN_2_NO) as col25 FROM  mis_gst_commcode as cc \n" +
                " right join mis_gi_gst_2 as 2c on cc.COMM_CODE=2c.COMM_CODE \n" +
                " left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE \n" +
                " where MM_YYYY='" + month_date+ "' group by ZONE_CODE;";
        return queryGst14aa;
    }
    public String QueryFor_gst4b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "SELECT zc.ZONE_NAME,cc.COMM_NAME, cc.ZONE_CODE,(1) as col29,(2c.MORE_THAN_2_NO) as col31,(2c.MORE_THAN_2_NO) as col25 " +
                "FROM  mis_gst_commcode as cc right join mis_gi_gst_2 as 2c on cc.COMM_CODE=2c.COMM_CODE " +
                "left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE where 2c.MM_YYYY='"+month_date+"'  AND zc.ZONE_CODE = '"+zone_code+"';";
        return queryGst14aa;
    }
    public String QueryFor_gst4b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa=   "SELECT zc.ZONE_NAME,cc.COMM_NAME, cc.ZONE_CODE,(1) as col29,(2c.MORE_THAN_2_NO) as col31,(2c.MORE_THAN_2_NO) as col25 \n"
                + "FROM  mis_gst_commcode as cc \n"
                + "right join mis_gi_gst_2 as 2c on cc.COMM_CODE=2c.COMM_CODE \n"
                + "left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE \n"
                + "where 2c.MM_YYYY='"+month_date+"' ;";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst4c_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "WITH FirstQuery AS (\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n"
                + "           SUM(14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col1_7\n"
                + "    FROM mis_gst_commcode AS cc\n"
                + "    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "    WHERE 14c.MM_YYYY = '" + month_date + "'\n"
                + "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n"
                + "),\n"
                + "SecondQuery AS (\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n"
                + "           SUM(7c.GROSS_TAX_CGST_FOR_C + 7c.GROSS_TAX_SGST_FOR_C + 7c.GROSS_TAX_IGST_FOR_C + 7c.GROSS_TAX_CESS_FOR_C) * 100 AS col1_8 -- convert crore into lakhs\n"
                + "    FROM mis_gst_commcode AS cc\n"
                + "    RIGHT JOIN mis_ddm_gst_1 AS 7c ON cc.COMM_CODE = 7c.COMM_CODE\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "    WHERE 7c.MM_YYYY = '" + month_date + "'\n"
                + "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n"
                + "),\n"
                + "CombinedQuery AS (\n"
                + "    SELECT \n"
                + "        COALESCE(fq.ZONE_NAME, sq.ZONE_NAME) AS ZONE_NAME,\n"
                + "        COALESCE(fq.ZONE_CODE, sq.ZONE_CODE) AS ZONE_CODE,\n"
                + "        fq.col1_7, sq.col1_8,\n"
                + "        CONCAT(fq.col1_7, '/', sq.col1_8) AS avsvl,\n"
                + "        (fq.col1_7 * 100 / sq.col1_8) AS score_of_parameter4c\n"
                + "    FROM FirstQuery fq\n"
                + "    LEFT JOIN SecondQuery sq ON fq.ZONE_CODE = sq.ZONE_CODE\n"
                + "    WHERE COALESCE(fq.ZONE_CODE, sq.ZONE_CODE) REGEXP '^[0-9]+$'\n"
                + "    \n"
                + "    UNION ALL\n"
                + "    \n"
                + "    SELECT \n"
                + "        COALESCE(fq.ZONE_NAME, sq.ZONE_NAME) AS ZONE_NAME,\n"
                + "        COALESCE(fq.ZONE_CODE, sq.ZONE_CODE) AS ZONE_CODE,\n"
                + "        fq.col1_7, sq.col1_8,\n"
                + "        CONCAT(fq.col1_7, '/', sq.col1_8) AS avsvl,\n"
                + "        (fq.col1_7 * 100 / sq.col1_8) AS score_of_parameter4c\n"
                + "    FROM SecondQuery sq\n"
                + "    LEFT JOIN FirstQuery fq ON fq.ZONE_CODE = sq.ZONE_CODE\n"
                + "    WHERE fq.ZONE_CODE IS NULL\n"
                + "    AND COALESCE(fq.ZONE_CODE, sq.ZONE_CODE) REGEXP '^[0-9]+$'\n"
                + "),\n"
                + "RankedQuery AS (\n"
                + "    SELECT *,\n"
                + "           ROW_NUMBER() OVER (ORDER BY col1_7) AS rn,\n"
                + "           COUNT(*) OVER () AS total_count\n"
                + "    FROM CombinedQuery\n"
                + "),\n"
                + "MedianCalculation AS (\n"
                + "    SELECT *,\n"
                + "           CASE\n"
                + "               WHEN total_count % 2 = 1 THEN \n"
                + "                   (SELECT col1_7 FROM RankedQuery WHERE rn = (total_count + 1) / 2)\n"
                + "               ELSE \n"
                + "                   (SELECT AVG(col1_7) FROM RankedQuery WHERE rn IN (total_count / 2, total_count / 2 + 1))\n"
                + "           END AS median_4c\n"
                + "    FROM RankedQuery\n"
                + ")\n"
                + "SELECT ZONE_NAME, ZONE_CODE, col1_7, col1_8, avsvl, score_of_parameter4c, median_4c\n"
                + "FROM MedianCalculation\n"
                + "ORDER BY score_of_parameter4c DESC;\n"
                + "";
        return queryGst14aa;
    }
    public String QueryFor_gst4c_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "WITH FirstQuery AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
                "           14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT AS col1_7\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
                "), \n" +
                "SecondQuery AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
                "           (7c.GROSS_TAX_CGST_FOR_C + 7c.GROSS_TAX_SGST_FOR_C + 7c.GROSS_TAX_IGST_FOR_C + 7c.GROSS_TAX_CESS_FOR_C) * 100 AS col1_8\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_ddm_gst_1 AS 7c ON cc.COMM_CODE = 7c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 7c.MM_YYYY = '" + month_date + "' \n" +
                "),\n" +
                "CombinedQuery AS (\n" +
                "    SELECT \n" +
                "        COALESCE(fq.ZONE_NAME, sq.ZONE_NAME) AS ZONE_NAME,\n" +
                "        COALESCE(fq.ZONE_CODE, sq.ZONE_CODE) AS ZONE_CODE,\n" +
                "        COALESCE(fq.COMM_NAME, sq.COMM_NAME) AS COMM_NAME,\n" +
                "        fq.col1_7, sq.col1_8,\n" +
                "        CONCAT((fq.col1_7), '/', (sq.col1_8)) AS avsvl,\n" +
                "        (fq.col1_7 * 100 / sq.col1_8) AS score_of_subparameter4c\n" +
                "    FROM FirstQuery fq \n" +
                "    LEFT JOIN SecondQuery sq ON fq.ZONE_CODE = sq.ZONE_CODE AND fq.COMM_NAME = sq.COMM_NAME\n" +
                "    WHERE COALESCE(fq.ZONE_CODE, sq.ZONE_CODE) REGEXP '^[0-9]+$'\n" +
                "\n" +
                "    UNION ALL\n" +
                "\n" +
                "    SELECT \n" +
                "        COALESCE(fq.ZONE_NAME, sq.ZONE_NAME) AS ZONE_NAME,\n" +
                "        COALESCE(fq.ZONE_CODE, sq.ZONE_CODE) AS ZONE_CODE,\n" +
                "        COALESCE(fq.COMM_NAME, sq.COMM_NAME) AS COMM_NAME,\n" +
                "        fq.col1_7, sq.col1_8,\n" +
                "        CONCAT((fq.col1_7), '/', (sq.col1_8)) AS avsvl,\n" +
                "        (fq.col1_7 * 100 / sq.col1_8) AS score_of_subparameter4c\n" +
                "    FROM SecondQuery sq \n" +
                "    LEFT JOIN FirstQuery fq ON fq.ZONE_CODE = sq.ZONE_CODE AND fq.COMM_NAME = sq.COMM_NAME\n" +
                "    WHERE fq.ZONE_CODE IS NULL \n" +
                "    AND COALESCE(fq.ZONE_CODE, sq.ZONE_CODE) REGEXP '^[0-9]+$'\n" +
                "),\n" +
                "RankedQuery AS (\n" +
                "    SELECT *,\n" +
                "           ROW_NUMBER() OVER (ORDER BY col1_7 ASC) AS row_num,\n" +
                "           COUNT(*) OVER () AS total_rows\n" +
                "    FROM CombinedQuery\n" +
                "),\n" +
                "MedianValue AS (\n" +
                "    SELECT \n" +
                "        CASE \n" +
                "            WHEN total_rows % 2 = 1 THEN \n" +
                "                (SELECT col1_7 FROM RankedQuery WHERE row_num = (total_rows + 1) / 2)\n" +
                "            ELSE \n" +
                "                (SELECT AVG(col1_7) FROM RankedQuery WHERE row_num IN (total_rows / 2, total_rows / 2 + 1))\n" +
                "        END AS median_col1_7\n" +
                "    FROM RankedQuery\n" +
                "    LIMIT 1 -- To ensure that the median is returned as a single value\n" +
                ")\n" +
                "SELECT rq.ZONE_NAME, rq.ZONE_CODE, rq.COMM_NAME, rq.col1_7, rq.col1_8, \n" +
                "       rq.avsvl, rq.score_of_subparameter4c, mv.median_col1_7 AS median\n" +
                "FROM RankedQuery rq\n" +
                "CROSS JOIN MedianValue mv\n" +
                "WHERE rq.ZONE_CODE = 56 -- Filter for ZONE_CODE = 56\n" +
                "ORDER BY rq.score_of_subparameter4c DESC;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst4c_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "WITH FirstQuery AS (SELECT zc.ZONE_NAME,cc.ZONE_CODE,cc.COMM_NAME,14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT AS col1_7\n" +
                "FROM mis_gst_commcode AS cc\n" +
                "RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
                "), \n" +
                "SecondQuery AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE,cc.COMM_NAME,(7c.GROSS_TAX_CGST_FOR_C + 7c.GROSS_TAX_SGST_FOR_C + 7c.GROSS_TAX_IGST_FOR_C + 7c.GROSS_TAX_CESS_FOR_C) * 100 AS col1_8\n" +
                "FROM mis_gst_commcode AS cc\n" +
                "RIGHT JOIN mis_ddm_gst_1 AS 7c ON cc.COMM_CODE = 7c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 7c.MM_YYYY = '" + month_date + "' \n" +
                "),\n" +
                "CombinedQuery AS (\n" +
                "SELECT \n" +
                "COALESCE(fq.ZONE_NAME, sq.ZONE_NAME) AS ZONE_NAME,\n" +
                "COALESCE(fq.ZONE_CODE, sq.ZONE_CODE) AS ZONE_CODE,\n" +
                "COALESCE(fq.COMM_NAME, sq.COMM_NAME) AS COMM_NAME,\n" +
                "fq.col1_7, sq.col1_8,CONCAT((fq.col1_7), '/', (sq.col1_8)) AS avsvl,(fq.col1_7 * 100 / sq.col1_8) AS score_of_subparameter4c\n" +
                "FROM FirstQuery fq \n" +
                "LEFT JOIN \n" +
                "SecondQuery sq ON fq.ZONE_CODE = sq.ZONE_CODE AND fq.COMM_NAME = sq.COMM_NAME\n" +
                "WHERE COALESCE(fq.ZONE_CODE, sq.ZONE_CODE) REGEXP '^[0-9]+$'\n" +
                "\n" +
                "UNION ALL\n" +
                "\n" +
                "SELECT \n" +
                "COALESCE(fq.ZONE_NAME, sq.ZONE_NAME) AS ZONE_NAME,\n" +
                "COALESCE(fq.ZONE_CODE, sq.ZONE_CODE) AS ZONE_CODE,\n" +
                "COALESCE(fq.COMM_NAME, sq.COMM_NAME) AS COMM_NAME,\n" +
                "fq.col1_7, sq.col1_8,CONCAT((fq.col1_7), '/', (sq.col1_8)) AS avsvl,(fq.col1_7 * 100 / sq.col1_8) AS score_of_subparameter4c\n" +
                "FROM SecondQuery sq \n" +
                "LEFT JOIN \n" +
                "FirstQuery fq ON fq.ZONE_CODE = sq.ZONE_CODE AND fq.COMM_NAME = sq.COMM_NAME\n" +
                "WHERE \n" +
                "fq.ZONE_CODE IS NULL \n" +
                "AND COALESCE(fq.ZONE_CODE, sq.ZONE_CODE) REGEXP '^[0-9]+$'\n" +
                "),\n" +
                "RankedQuery AS (\n" +
                "SELECT *,ROW_NUMBER() OVER (ORDER BY col1_7 ASC) AS row_num,\n" +
                "COUNT(*) OVER () AS total_rows\n" +
                "FROM CombinedQuery\n" +
                "),\n" +
                "MedianValue AS (\n" +
                "SELECT \n" +
                "        CASE \n" +
                "            WHEN total_rows % 2 = 1 THEN \n" +
                "                (SELECT col1_7 FROM RankedQuery WHERE row_num = (total_rows + 1) / 2)\n" +
                "            ELSE \n" +
                "                (SELECT AVG(col1_7) FROM RankedQuery WHERE row_num IN (total_rows / 2, total_rows / 2 + 1))\n" +
                "        END AS median_col1_7\n" +
                "    FROM \n" +
                "        RankedQuery\n" +
                "    LIMIT 1\n" +
                ")\n" +
                "SELECT rq.ZONE_NAME,rq.ZONE_CODE,rq.COMM_NAME,rq.col1_7, rq.col1_8,rq.avsvl,rq.score_of_subparameter4c,mv.median_col1_7 AS median\n" +
                "FROM RankedQuery rq\n" +
                "CROSS JOIN MedianValue mv\n" +
                "ORDER BY rq.score_of_subparameter4c DESC;\n";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst4d_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH may_data AS (\n" +
                "    SELECT \n" +
                "        cc.ZONE_CODE, \n" +
                "        zc.ZONE_NAME, \n" +
                "        SUM(14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT) AS col6_1,\n" +
                "        SUM(14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_3 \n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' \n" +
                "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "),\n" +
                "april_data AS (\n" +
                "    SELECT \n" +
                "        cc.ZONE_CODE, \n" +
                "        zc.ZONE_NAME, \n" +
                "        SUM(14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT) AS col6_2,\n" +
                "        SUM(14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_4\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' \n" +
                "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                ")\n" +
                "SELECT \n" +
                "    COALESCE(may_data.ZONE_CODE, april_data.ZONE_CODE) AS ZONE_CODE,\n" +
                "    COALESCE(may_data.ZONE_NAME, april_data.ZONE_NAME) AS ZONE_NAME,\n" +
                "    COALESCE(may_data.col6_1, 0) AS col6_1, \n" +
                "    COALESCE(may_data.col6_3, 0) AS col6_3,\n" +
                "    COALESCE(april_data.col6_2, 0) AS col6_2,\n" +
                "    COALESCE(april_data.col6_4, 0) AS col6_4,\n" +
                "    CONCAT(COALESCE(may_data.col6_1, 0), '/', COALESCE(may_data.col6_3, 0)) AS absval,\n" +
                "    CASE\n" +
                "        WHEN COALESCE(may_data.col6_3, 0) = 0 THEN 0\n" +
                "        ELSE (COALESCE(may_data.col6_1, 0) / COALESCE(may_data.col6_3, 0)) * 100\n" +
                "    END AS total_score\n" +
                "FROM \n" +
                "    may_data\n" +
                "LEFT JOIN april_data ON may_data.ZONE_CODE = april_data.ZONE_CODE\n" +
                "UNION\n" +
                "SELECT \n" +
                "    COALESCE(may_data.ZONE_CODE, april_data.ZONE_CODE) AS ZONE_CODE,\n" +
                "    COALESCE(may_data.ZONE_NAME, april_data.ZONE_NAME) AS ZONE_NAME,\n" +
                "    COALESCE(may_data.col6_1, 0) AS col6_1, \n" +
                "    COALESCE(may_data.col6_3, 0) AS col6_3,\n" +
                "    COALESCE(april_data.col6_2, 0) AS col6_2,\n" +
                "    COALESCE(april_data.col6_4, 0) AS col6_4,\n" +
                "    CONCAT(COALESCE(may_data.col6_1, 0), '/', COALESCE(may_data.col6_3, 0)) AS absval,\n" +
                "    CASE\n" +
                "        WHEN COALESCE(may_data.col6_3, 0) = 0 THEN 0\n" +
                "        ELSE (COALESCE(may_data.col6_1, 0) / COALESCE(may_data.col6_3, 0)) * 100\n" +
                "    END AS total_score\n" +
                "FROM \n" +
                "    may_data\n" +
                "RIGHT JOIN april_data ON may_data.ZONE_CODE = april_data.ZONE_CODE\n" +
                "ORDER BY total_score DESC;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst4d_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "WITH may_data AS (\n" +
                "    SELECT\n" +
                "        cc.ZONE_CODE,\n" +
                "        zc.ZONE_NAME,\n" +
                "        cc.COMM_NAME,\n" +
                "        14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT AS col6_1,\n" +
                "        14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT AS col6_3\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                "),\n" +
                "april_data AS (\n" +
                "    SELECT\n" +
                "        cc.ZONE_CODE,\n" +
                "        zc.ZONE_NAME,\n" +
                "        cc.COMM_NAME,\n" +
                "        14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT AS col6_2,\n" +
                "        14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT AS col6_4\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                ")\n" +
                "SELECT\n" +
                "    COALESCE(may_data.ZONE_CODE, april_data.ZONE_CODE) AS ZONE_CODE,\n" +
                "    COALESCE(may_data.ZONE_NAME, april_data.ZONE_NAME) AS ZONE_NAME,\n" +
                "    COALESCE(may_data.COMM_NAME, april_data.COMM_NAME) AS COMM_NAME,\n" +
                "    COALESCE(may_data.col6_1, 0) AS col6_1,\n" +
                "    COALESCE(may_data.col6_3, 0) AS col6_3,\n" +
                "    COALESCE(april_data.col6_2, 0) AS col6_2,\n" +
                "    COALESCE(april_data.col6_4, 0) AS col6_4,\n" +
                "    CONCAT(COALESCE(may_data.col6_1, 0), '/', COALESCE(may_data.col6_3, 0)) AS absval,\n" +
                "    CASE\n" +
                "        WHEN COALESCE(may_data.col6_3, 0) = 0 THEN 0\n" +
                "        ELSE (COALESCE(may_data.col6_1, 0) / COALESCE(may_data.col6_3, 0)) * 100\n" +
                "    END AS total_score\n" +
                "FROM\n" +
                "    may_data\n" +
                "LEFT JOIN april_data ON may_data.ZONE_CODE = april_data.ZONE_CODE AND may_data.COMM_NAME = april_data.COMM_NAME\n" +
                "UNION\n" +
                "SELECT\n" +
                "    COALESCE(may_data.ZONE_CODE, april_data.ZONE_CODE) AS ZONE_CODE,\n" +
                "    COALESCE(may_data.ZONE_NAME, april_data.ZONE_NAME) AS ZONE_NAME,\n" +
                "    COALESCE(may_data.COMM_NAME, april_data.COMM_NAME) AS COMM_NAME,\n" +
                "    COALESCE(may_data.col6_1, 0) AS col6_1,\n" +
                "    COALESCE(may_data.col6_3, 0) AS col6_3,\n" +
                "    COALESCE(april_data.col6_2, 0) AS col6_2,\n" +
                "    COALESCE(april_data.col6_4, 0) AS col6_4,\n" +
                "    CONCAT(COALESCE(may_data.col6_1, 0), '/', COALESCE(may_data.col6_3, 0)) AS absval,\n" +
                "    CASE\n" +
                "        WHEN COALESCE(may_data.col6_3, 0) = 0 THEN 0\n" +
                "        ELSE (COALESCE(may_data.col6_1, 0) / COALESCE(may_data.col6_3, 0)) * 100\n" +
                "    END AS total_score\n" +
                "FROM\n" +
                "    may_data\n" +
                "RIGHT JOIN april_data ON may_data.ZONE_CODE = april_data.ZONE_CODE AND may_data.COMM_NAME = april_data.COMM_NAME\n" +
                "ORDER BY total_score DESC;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst4d_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH may_data AS (\n" +
                "    SELECT\n" +
                "        cc.ZONE_CODE,\n" +
                "        zc.ZONE_NAME,\n" +
                "        cc.COMM_NAME,\n" +
                "        14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT AS col6_1,\n" +
                "        14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT AS col6_3\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "'  \n" +
                "),\n" +
                "april_data AS (\n" +
                "    SELECT\n" +
                "        cc.ZONE_CODE,\n" +
                "        zc.ZONE_NAME,\n" +
                "        cc.COMM_NAME,\n" +
                "        14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT AS col6_2,\n" +
                "        14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT AS col6_4\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
                ")\n" +
                "SELECT\n" +
                "    COALESCE(may_data.ZONE_CODE, april_data.ZONE_CODE) AS ZONE_CODE,\n" +
                "    COALESCE(may_data.ZONE_NAME, april_data.ZONE_NAME) AS ZONE_NAME,\n" +
                "    COALESCE(may_data.COMM_NAME, april_data.COMM_NAME) AS COMM_NAME,\n" +
                "    COALESCE(may_data.col6_1, 0) AS col6_1,\n" +
                "    COALESCE(may_data.col6_3, 0) AS col6_3,\n" +
                "    COALESCE(april_data.col6_2, 0) AS col6_2,\n" +
                "    COALESCE(april_data.col6_4, 0) AS col6_4,\n" +
                "    CONCAT(COALESCE(may_data.col6_1, 0), '/', COALESCE(may_data.col6_3, 0)) AS absval,\n" +
                "    CASE\n" +
                "        WHEN COALESCE(may_data.col6_3, 0) = 0 THEN 0\n" +
                "        ELSE (COALESCE(may_data.col6_1, 0) / COALESCE(may_data.col6_3, 0)) * 100\n" +
                "    END AS total_score\n" +
                "FROM\n" +
                "    may_data\n" +
                "LEFT JOIN april_data ON may_data.ZONE_CODE = april_data.ZONE_CODE AND may_data.COMM_NAME = april_data.COMM_NAME\n" +
                "UNION\n" +
                "SELECT\n" +
                "    COALESCE(may_data.ZONE_CODE, april_data.ZONE_CODE) AS ZONE_CODE,\n" +
                "    COALESCE(may_data.ZONE_NAME, april_data.ZONE_NAME) AS ZONE_NAME,\n" +
                "    COALESCE(may_data.COMM_NAME, april_data.COMM_NAME) AS COMM_NAME,\n" +
                "    COALESCE(may_data.col6_1, 0) AS col6_1,\n" +
                "    COALESCE(may_data.col6_3, 0) AS col6_3,\n" +
                "    COALESCE(april_data.col6_2, 0) AS col6_2,\n" +
                "    COALESCE(april_data.col6_4, 0) AS col6_4,\n" +
                "    CONCAT(COALESCE(may_data.col6_1, 0), '/', COALESCE(may_data.col6_3, 0)) AS absval,\n" +
                "    CASE\n" +
                "        WHEN COALESCE(may_data.col6_3, 0) = 0 THEN 0\n" +
                "        ELSE (COALESCE(may_data.col6_1, 0) / COALESCE(may_data.col6_3, 0)) * 100\n" +
                "    END AS total_score\n" +
                "FROM\n" +
                "    may_data\n" +
                "RIGHT JOIN april_data ON may_data.ZONE_CODE = april_data.ZONE_CODE AND may_data.COMM_NAME = april_data.COMM_NAME\n" +
                "ORDER BY total_score DESC;\n";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst5a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH ranked_data AS (\n" +
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
                "    SELECT col10,@row_num := @row_num + 1 AS row_num,@total_rows AS total_rows\n" +
                "    FROM ranked_data\n" +
                "    CROSS JOIN (SELECT @row_num := 0, @total_rows := (SELECT COUNT(*) FROM ranked_data WHERE col10 IS NOT NULL)) AS vars\n" +
                "    WHERE col10 IS NOT NULL\n" +
                "    ORDER BY col10\n" +
                "),\n" +
                "median_data AS (\n" +
                "    SELECT AVG(col10) AS median_col10\n" +
                "    FROM ranked_col10\n" +
                "    WHERE row_num IN (FLOOR((total_rows + 1) / 2), CEIL((total_rows + 1) / 2))\n" +
                ")\n" +
                "SELECT rd.ZONE_NAME,rd.ZONE_CODE,rd.col10,rd.col4,rd.score_of_subparameter5a,\n" +
                "    DENSE_RANK() OVER (ORDER BY rd.score_of_subparameter5a DESC) AS z_rank,\n" +
                "    md.median_col10 AS median5a\n" +
                "FROM ranked_data rd\n" +
                "CROSS JOIN median_data md;";
        return queryGst14aa;
    }
    public String QueryFor_gst5a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "WITH cte AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,\n" +
                "    (14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + \n" +
                "    14c.adc_callbook_disposal_no + 14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + \n" +
                "    14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
                "    14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no +\n" +
                "    14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no) as numerator_5a, -- col10\n" +
                "    (14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + \n" +
                "    14c.ADC_CALLBOOK_OPENING_NO + 14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + \n" +
                "    14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "    14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + \n" +
                "    14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO) as col4\n" +
                "    FROM mis_gst_commcode as cc\n" +
                "    RIGHT JOIN mis_dpm_gst_adj_1 as 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode as zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "),\n" +
                "median_cte AS (\n" +
                "    SELECT numerator_5a, ROW_NUMBER() OVER (ORDER BY numerator_5a) as rn,\n" +
                "        COUNT(*) OVER () as cnt\n" +
                "    FROM cte\n" +
                "),\n" +
                "median_value AS (\n" +
                "    SELECT AVG(numerator_5a) as median_5a\n" +
                "    FROM median_cte\n" +
                "    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                ")\n" +
                "SELECT ZONE_NAME, COMM_NAME, ZONE_CODE, numerator_5a,col4 ,\n" +
                "    CASE WHEN col4 = 0 THEN 0 ELSE numerator_5a * 100 / col4 END AS score_of_subparameter_5a,\n" +
                "    (SELECT median_5a FROM median_value) as median_5a,\n" +
                "    CONCAT(numerator_5a, '/', col4) as absvl_5a\n" +
                "FROM cte\n" +
                "WHERE ZONE_CODE = '" + zone_code + "';";
        return queryGst14aa;
    }
    public String QueryFor_gst5a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "WITH cte AS (\n" +
                "    SELECT zc.ZONE_NAME,cc.COMM_NAME,cc.ZONE_CODE,\n" +
                "        (14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + \n" +
                "         14c.adc_callbook_disposal_no + 14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + \n" +
                "         14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + \n" +
                "         14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + \n" +
                "         14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no) as col10,\n" +
                "        (14c.ADC_COMMISSIONERATE_OPENING_NO + 14c.ADC_AUDIT_OPENING_NO + 14c.ADC_INVESTIGATION_OPENING_NO + \n" +
                "         14c.ADC_CALLBOOK_OPENING_NO + 14c.DC_COMMISSIONERATE_OPENING_NO + 14c.DC_AUDIT_OPENING_NO + \n" +
                "         14c.DC_INVESTIGATION_OPENING_NO + 14c.DC_CALLBOOK_OPENING_NO + \n" +
                "         14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO + 14c.SUPERINTENDENT_AUDIT_OPENING_NO + \n" +
                "         14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO + 14c.SUPERINTENDENT_CALLBOOK_OPENING_NO) as col4\n" +
                "    FROM mis_gst_commcode as cc\n" +
                "    RIGHT JOIN mis_dpm_gst_adj_1 as 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode as zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "),\n" +
                "median_cte AS (\n" +
                "    SELECT col10,ROW_NUMBER() OVER (ORDER BY col10) as rn,\n" +
                "        COUNT(*) OVER () as cnt\n" +
                "    FROM cte\n" +
                "),\n" +
                "median_value AS (\n" +
                "    SELECT AVG(col10) as median\n" +
                "    FROM median_cte\n" +
                "    WHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                ")\n" +
                "SELECT ZONE_NAME,COMM_NAME,ZONE_CODE,col10,col4,\n" +
                "    CASE WHEN col4 = 0 THEN 0 ELSE col10 * 100 / col4 \n" +
                "    END AS score_of_subparameter,\n" +
                "    (SELECT median FROM median_value) as median\n" +
                "FROM cte\n" +
                "ORDER BY score_of_subparameter DESC;\n";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst5b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String next_month_new = DateCalculate.getNextMonth(month_date);

        String queryGst14aa= "WITH Query1 AS (\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n"
                + "        SUM(14c.ADC_COMMISSIONERATE_TIME_LESS_3_NO +14c.ADC_AUDIT_TIME_LESS_3_NO +14c.ADC_INVESTIGATION_TIME_LESS_3_NO +14c.ADC_CALLBOOK_TIME_LESS_3_NO +\n"
                + "            14c.DC_COMMISSIONERATE_TIME_LESS_3_NO +14c.DC_AUDIT_TIME_LESS_3_NO +14c.DC_INVESTIGATION_TIME_LESS_3_NO +14c.DC_CALLBOOK_TIME_LESS_3_NO +\n"
                + "            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_LESS_3_NO +14c.SUPERINTENDENT_AUDIT_TIME_LESS_3_NO +14c.SUPERINTENDENT_INVESTIGATION_TIME_LESS_3_NO +14c.SUPERINTENDENT_CALLBOOK_TIME_LESS_3_NO\n"
                + "        ) AS col22,\n"
                + "        SUM(14c.ADC_COMMISSIONERATE_TIME_3_TO_6_NO +14c.ADC_AUDIT_TIME_3_TO_6_NO +14c.ADC_INVESTIGATION_TIME_3_TO_6_NO +14c.ADC_CALLBOOK_TIME_3_TO_6_NO +\n"
                + "            14c.DC_COMMISSIONERATE_TIME_3_TO_6_NO +14c.DC_AUDIT_TIME_3_TO_6_NO +14c.DC_INVESTIGATION_TIME_3_TO_6_NO +14c.DC_CALLBOOK_TIME_3_TO_6_NO +\n"
                + "            14c.SUPERINTENDENT_COMMISSIONERATE_TIME_3_TO_6_NO +14c.SUPERINTENDENT_AUDIT_TIME_3_TO_6_NO +14c.SUPERINTENDENT_INVESTIGATION_TIME_3_TO_6_NO +14c.SUPERINTENDENT_CALLBOOK_TIME_3_TO_6_NO\n"
                + "        ) AS col23\n"
                + "    FROM mis_gst_commcode AS cc\n"
                + "    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "    WHERE 14c.MM_YYYY = '" + month_date + "'  GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n"
                + "),\n"
                + "Query2 AS (\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n"
                + "        SUM(14c.ADC_COMMISSIONERATE_OPENING_NO +14c.ADC_AUDIT_OPENING_NO +14c.ADC_INVESTIGATION_OPENING_NO +14c.ADC_CALLBOOK_OPENING_NO +14c.DC_COMMISSIONERATE_OPENING_NO +\n"
                + "            14c.DC_AUDIT_OPENING_NO +14c.DC_INVESTIGATION_OPENING_NO +14c.DC_CALLBOOK_OPENING_NO +14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO +\n"
                + "            14c.SUPERINTENDENT_AUDIT_OPENING_NO +14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO +14c.SUPERINTENDENT_CALLBOOK_OPENING_NO\n"
                + "        ) AS col16\n"
                + "    FROM mis_gst_commcode AS cc\n"
                + "    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "    WHERE 14c.MM_YYYY = '" + next_month_new + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n"
                + "),\n"
                + "RankedData AS (\n"
                + "    SELECT q1.ZONE_NAME, q1.ZONE_CODE,\n"
                + "        COALESCE(q1.col22, 0) AS col22,COALESCE(q1.col23, 0) AS col23,COALESCE(q2.col16, 0) AS col16,\n"
                + "        CASE\n"
                + "            WHEN COALESCE(q2.col16, 0) = 0 THEN 0\n"
                + "            ELSE ((COALESCE(q1.col22, 0) + COALESCE(q1.col23, 0)) / COALESCE(q2.col16, 0)) * 100\n"
                + "        END AS total_score\n"
                + "    FROM Query1 AS q1\n"
                + "    LEFT JOIN \n"
                + "        Query2 AS q2 ON q1.ZONE_CODE = q2.ZONE_CODE\n"
                + ")\n"
                + "SELECT ZONE_NAME,ZONE_CODE, col22, col23, col16,total_score,\n"
                + "    DENSE_RANK() OVER (ORDER BY total_score ASC) AS z_rank FROM RankedData;";
        return queryGst14aa;
    }
    public String QueryFor_gst5b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String next_month_new = DateCalculate.getNextMonth(month_date);
        String queryGst14aa="WITH CTE1 AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
                "\t(14c.ADC_COMMISSIONERATE_TIME_LESS_3_NO +14c.ADC_AUDIT_TIME_LESS_3_NO +14c.ADC_INVESTIGATION_TIME_LESS_3_NO +14c.ADC_CALLBOOK_TIME_LESS_3_NO +14c.DC_COMMISSIONERATE_TIME_LESS_3_NO +14c.DC_AUDIT_TIME_LESS_3_NO +\n" +
                "\t14c.DC_INVESTIGATION_TIME_LESS_3_NO +14c.DC_CALLBOOK_TIME_LESS_3_NO +14c.SUPERINTENDENT_COMMISSIONERATE_TIME_LESS_3_NO +14c.SUPERINTENDENT_AUDIT_TIME_LESS_3_NO +14c.SUPERINTENDENT_INVESTIGATION_TIME_LESS_3_NO +\n" +
                "\t14c.SUPERINTENDENT_CALLBOOK_TIME_LESS_3_NO) AS col22, \n" +
                "    (14c.ADC_COMMISSIONERATE_TIME_3_TO_6_NO +14c.ADC_AUDIT_TIME_3_TO_6_NO +14c.ADC_INVESTIGATION_TIME_3_TO_6_NO +14c.ADC_CALLBOOK_TIME_3_TO_6_NO +14c.DC_COMMISSIONERATE_TIME_3_TO_6_NO +14c.DC_AUDIT_TIME_3_TO_6_NO +\n" +
                "    14c.DC_INVESTIGATION_TIME_3_TO_6_NO +14c.DC_CALLBOOK_TIME_3_TO_6_NO +14c.SUPERINTENDENT_COMMISSIONERATE_TIME_3_TO_6_NO +14c.SUPERINTENDENT_AUDIT_TIME_3_TO_6_NO +14c.SUPERINTENDENT_INVESTIGATION_TIME_3_TO_6_NO +\n" +
                "    14c.SUPERINTENDENT_CALLBOOK_TIME_3_TO_6_NO) AS col23, \n" +
                "        NULL AS col16\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" +  month_date  + "' and cc.ZONE_CODE = '" + zone_code + "'),\n" +
                "CTE2 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,NULL AS col22, NULL AS col23,\n" +
                "    (14c.ADC_COMMISSIONERATE_OPENING_NO +14c.ADC_AUDIT_OPENING_NO +14c.ADC_INVESTIGATION_OPENING_NO +14c.ADC_CALLBOOK_OPENING_NO +14c.DC_COMMISSIONERATE_OPENING_NO +14c.DC_AUDIT_OPENING_NO +\n" +
                "\t14c.DC_INVESTIGATION_OPENING_NO +14c.DC_CALLBOOK_OPENING_NO +14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO +14c.SUPERINTENDENT_AUDIT_OPENING_NO +14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO +14c.SUPERINTENDENT_CALLBOOK_OPENING_NO) AS col16\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" + next_month_new + "' and cc.ZONE_CODE = '" + zone_code + "')\n" +
                "    SELECT ZONE_NAME, ZONE_CODE, COMM_NAME, SUM(col22) AS col22, SUM(col23) AS col23, SUM(col16) AS col16,\n" +
                "    (( SUM(col22) + SUM(col23) ) * 100 / SUM(col16)) as score_of_parameter\n" +
                "    FROM (SELECT * FROM CTE1\n" +
                "\tUNION ALL\n" +
                "    SELECT * FROM CTE2) AS combined_data GROUP BY ZONE_NAME, ZONE_CODE, COMM_NAME " +
                "order by score_of_parameter;";
        return queryGst14aa;
    }
    public String QueryFor_gst5b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String next_month_new = DateCalculate.getNextMonth(month_date);
        String queryGst14aa="WITH CTE1 AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
                "\t(14c.ADC_COMMISSIONERATE_TIME_LESS_3_NO +14c.ADC_AUDIT_TIME_LESS_3_NO +14c.ADC_INVESTIGATION_TIME_LESS_3_NO +14c.ADC_CALLBOOK_TIME_LESS_3_NO +14c.DC_COMMISSIONERATE_TIME_LESS_3_NO +14c.DC_AUDIT_TIME_LESS_3_NO +\n" +
                "\t14c.DC_INVESTIGATION_TIME_LESS_3_NO +14c.DC_CALLBOOK_TIME_LESS_3_NO +14c.SUPERINTENDENT_COMMISSIONERATE_TIME_LESS_3_NO +14c.SUPERINTENDENT_AUDIT_TIME_LESS_3_NO +14c.SUPERINTENDENT_INVESTIGATION_TIME_LESS_3_NO +\n" +
                "\t14c.SUPERINTENDENT_CALLBOOK_TIME_LESS_3_NO) AS col22, \n" +
                "    (14c.ADC_COMMISSIONERATE_TIME_3_TO_6_NO +14c.ADC_AUDIT_TIME_3_TO_6_NO +14c.ADC_INVESTIGATION_TIME_3_TO_6_NO +14c.ADC_CALLBOOK_TIME_3_TO_6_NO +14c.DC_COMMISSIONERATE_TIME_3_TO_6_NO +14c.DC_AUDIT_TIME_3_TO_6_NO +\n" +
                "    14c.DC_INVESTIGATION_TIME_3_TO_6_NO +14c.DC_CALLBOOK_TIME_3_TO_6_NO +14c.SUPERINTENDENT_COMMISSIONERATE_TIME_3_TO_6_NO +14c.SUPERINTENDENT_AUDIT_TIME_3_TO_6_NO +14c.SUPERINTENDENT_INVESTIGATION_TIME_3_TO_6_NO +\n" +
                "    14c.SUPERINTENDENT_CALLBOOK_TIME_3_TO_6_NO) AS col23, \n" +
                "        NULL AS col16\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" +  month_date  + "'),\n" +
                "CTE2 AS (\n" +
                "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,NULL AS col22, NULL AS col23,\n" +
                "    (14c.ADC_COMMISSIONERATE_OPENING_NO +14c.ADC_AUDIT_OPENING_NO +14c.ADC_INVESTIGATION_OPENING_NO +14c.ADC_CALLBOOK_OPENING_NO +14c.DC_COMMISSIONERATE_OPENING_NO +14c.DC_AUDIT_OPENING_NO +\n" +
                "\t14c.DC_INVESTIGATION_OPENING_NO +14c.DC_CALLBOOK_OPENING_NO +14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO +14c.SUPERINTENDENT_AUDIT_OPENING_NO +14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO +14c.SUPERINTENDENT_CALLBOOK_OPENING_NO) AS col16\n" +
                "    FROM mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" + next_month_new + "')\n" +
                "    SELECT ZONE_NAME, ZONE_CODE, COMM_NAME, SUM(col22) AS col22, SUM(col23) AS col23, SUM(col16) AS col16,\n" +
                "    (( SUM(col22) + SUM(col23) ) * 100 / SUM(col16)) as score_of_parameter\n" +
                "    FROM (SELECT * FROM CTE1\n" +
                "\tUNION ALL\n" +
                "    SELECT * FROM CTE2) AS combined_data GROUP BY ZONE_NAME, ZONE_CODE, COMM_NAME " +
                "order by score_of_parameter;";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst6a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "WITH col9_data AS (\n" +
                "    SELECT zc.ZONE_NAME,cc.ZONE_CODE,SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "),\n" +
                "col3_data AS (\n" +
                "    SELECT zc.ZONE_NAME,cc.ZONE_CODE,SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
                "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "),\n" +
                "ranked_data AS (\n" +
                "    SELECT col9_data.ZONE_NAME,col9_data.ZONE_CODE,col9_data.col9,col3_data.col3,\n" +
                "        CASE\n" +
                "\t\tWHEN col3_data.col3 = 0 THEN 0\n" +
                "            ELSE (col9_data.col9 / col3_data.col3) * 100\n" +
                "        END AS total_score\n" +
                "    FROM col9_data\n" +
                "    LEFT JOIN col3_data ON col9_data.ZONE_CODE = col3_data.ZONE_CODE AND col9_data.ZONE_NAME = col3_data.ZONE_NAME\n" +
                "),\n" +
                "median_calc AS (\n" +
                "    SELECT col9,ROW_NUMBER() OVER (ORDER BY col9) AS row_num,COUNT(*) OVER () AS total_rows\n" +
                "    FROM ranked_data\n" +
                "),\n" +
                "median_value AS (\n" +
                "    SELECT AVG(col9) AS median\n" +
                "    FROM median_calc\n" +
                "    WHERE row_num IN (FLOOR((total_rows + 1) / 2), FLOOR((total_rows + 2) / 2))\n" +
                ")\n" +
                "SELECT r.ZONE_NAME,r.ZONE_CODE,r.col9,r.col3,r.total_score,m.median,\n" +
                "    RANK() OVER (ORDER BY r.total_score DESC) AS z_rank\n" +
                "FROM ranked_data r\n" +
                "CROSS JOIN median_value m\n" +
                "WHERE r.ZONE_NAME NOT IN ('DG East', 'CEI DG');\n";
        return queryGst14aa;
    }
    public String QueryFor_gst6a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);

        String queryGst14aa="WITH cte1 AS (\n" +
                "SELECT zc.ZONE_NAME,cc.ZONE_CODE,cc.COMM_NAME, (14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9\n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "' AND zc.ZONE_NAME NOT IN ('DG East', 'CEI DG')\n" +
                "), \n" +
                "cte2 AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_dgi_st_1a AS 14c \n" +
                "ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc \n" +
                "ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND zc.ZONE_NAME NOT IN ('DG East', 'CEI DG')\n" +
                "), \n" +
                "final_data AS (\n" +
                "SELECT cte1.ZONE_NAME, cte1.ZONE_CODE, cte1.COMM_NAME, cte1.col9, cte2.col3, cte1.col9 AS numerator_6a, \n" +
                "CASE WHEN cte2.col3 = 0 THEN 0 \n" +
                "ELSE (cte1.col9 / cte2.col3) * 100 \n" +
                "END AS total_score \n" +
                "FROM cte1 \n" +
                "JOIN cte2 \n" +
                "ON cte1.ZONE_NAME = cte2.ZONE_NAME AND cte1.ZONE_CODE = cte2.ZONE_CODE AND cte1.COMM_NAME = cte2.COMM_NAME\n" +
                "), \n" +
                "ranked_data AS (\n" +
                "SELECT final_data.*,\n" +
                "ROW_NUMBER() OVER (ORDER BY final_data.numerator_6a) AS row_num, COUNT(*) OVER () AS total_rows FROM final_data\n" +
                ")\n" +
                "SELECT ranked_data.ZONE_NAME, ranked_data.ZONE_CODE, ranked_data.COMM_NAME, ranked_data.col9, ranked_data.col3, ranked_data.total_score, ranked_data.numerator_6a,\n" +
                "CASE \n" +
                "WHEN total_rows % 2 = 1 THEN (SELECT numerator_6a FROM ranked_data WHERE row_num = (total_rows + 1) / 2) \n" +
                "ELSE (SELECT AVG(numerator_6a) FROM ranked_data WHERE row_num IN (total_rows / 2, (total_rows / 2) + 1)) \n" +
                "END AS median_numerator_6a, \n" +
                "DENSE_RANK() OVER (ORDER BY total_score DESC) AS z_rank \n" +
                "FROM ranked_data\n" +
                "WHERE ranked_data.ZONE_CODE = '" + zone_code + "';";
//        String queryGst14aa="WITH cte1 AS (\n" +
//                "    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, \n" +
//                "           (14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9\n" +
//                "    FROM mis_gst_commcode AS cc\n" +
//                "    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
//                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
//                "    WHERE cc.ZONE_CODE = '" + zone_code + "' \n" +
//                "      AND 14c.MM_YYYY = '" + month_date + "'\n" +
//                "),\n" +
//                "cte2 AS (\n" +
//                "    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, \n" +
//                "           (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
//                "    FROM mis_gst_commcode AS cc\n" +
//                "    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
//                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
//                "    WHERE cc.ZONE_CODE = '" + zone_code + "' \n" +
//                "      AND 14c.MM_YYYY = '" + prev_month_new + "'\n" +
//                "),\n" +
//                "ranked_data AS (\n" +
//                "    SELECT cte1.ZONE_NAME, cte1.COMM_NAME, cte1.ZONE_CODE, cte1.col9, cte2.col3,\n" +
//                "           (CASE WHEN cte2.col3 != 0 THEN (cte1.col9 / cte2.col3) * 100 ELSE NULL END) AS total_score\n" +
//                "    FROM cte1\n" +
//                "    LEFT JOIN cte2 \n" +
//                "    ON cte1.ZONE_NAME = cte2.ZONE_NAME \n" +
//                "       AND cte1.COMM_NAME = cte2.COMM_NAME \n" +
//                "       AND cte1.ZONE_CODE = cte2.ZONE_CODE\n" +
//                ")\n" +
//                "SELECT ZONE_NAME, COMM_NAME, ZONE_CODE, col9, col3, total_score,\n" +
//                "       DENSE_RANK() OVER (ORDER BY total_score DESC) AS z_rank\n" +
//                "FROM ranked_data;\n";

        return queryGst14aa;
    }
    public String QueryFor_gst6a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);

        String queryGst14aa= "WITH cte1 AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, \n" +
                "(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9 \n" +
                "FROM mis_gst_commcode AS cc RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "' AND zc.ZONE_NAME NOT IN ('DG East', 'CEI DG')\n" +
                "), \n" +
                "cte2 AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3 \n" +
                "FROM mis_gst_commcode AS cc RIGHT JOIN mis_dgi_st_1a AS 14c \n" +
                "ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc \n" +
                "ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + prev_month_new + "' AND zc.ZONE_NAME NOT IN ('DG East', 'CEI DG')\n" +
                "), \n" +
                "final_data AS (\n" +
                "SELECT cte1.ZONE_NAME, cte1.ZONE_CODE, cte1.COMM_NAME, cte1.col9, cte2.col3, cte1.col9 AS numerator_6a, \n" +
                "CASE \n" +
                "WHEN cte2.col3 = 0 THEN 0 \n" +
                "ELSE (cte1.col9 / cte2.col3) * 100 \n" +
                "END AS total_score \n" +
                "FROM \n" +
                "cte1 \n" +
                "JOIN \n" +
                "cte2 ON \n" +
                "cte1.ZONE_NAME = cte2.ZONE_NAME \n" +
                "AND cte1.ZONE_CODE = cte2.ZONE_CODE \n" +
                "AND cte1.COMM_NAME = cte2.COMM_NAME\n" +
                "), \n" +
                "ranked_data AS (\n" +
                "SELECT final_data.*, \n" +
                "ROW_NUMBER() OVER (ORDER BY final_data.numerator_6a) AS row_num, \n" +
                "COUNT(*) OVER () AS total_rows\n" +
                "FROM final_data\n" +
                ")\n" +
                "SELECT \n" +
                "ranked_data.ZONE_NAME, ranked_data.ZONE_CODE, ranked_data.COMM_NAME, ranked_data.col9, ranked_data.col3, ranked_data.total_score, ranked_data.numerator_6a, \n" +
                "CASE \n" +
                "WHEN total_rows % 2 = 1 THEN \n" +
                "(SELECT numerator_6a FROM ranked_data WHERE row_num = (total_rows + 1) / 2) \n" +
                "ELSE\n" +
                "(SELECT AVG(numerator_6a) FROM ranked_data WHERE row_num IN (total_rows / 2, (total_rows / 2) + 1)) \n" +
                "END AS median_numerator_6a, \n" +
                "DENSE_RANK() OVER (ORDER BY total_score DESC) AS z_rank \n" +
                "FROM ranked_data;\n";
//        String queryGst14aa= "WITH cte1 AS (\n" +
//                "    SELECT \n" +
//                "        zc.ZONE_NAME, \n" +
//                "        cc.ZONE_CODE, \n" +
//                "        cc.COMM_NAME, \n" +
//                "        (14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9 \n" +
//                "    FROM \n" +
//                "        mis_gst_commcode AS cc \n" +
//                "    RIGHT JOIN \n" +
//                "        mis_dgi_st_1a AS 14c \n" +
//                "    ON \n" +
//                "        cc.COMM_CODE = 14c.COMM_CODE \n" +
//                "    LEFT JOIN \n" +
//                "        mis_gst_zonecode AS zc \n" +
//                "    ON \n" +
//                "        zc.ZONE_CODE = cc.ZONE_CODE \n" +
//                "    WHERE \n" +
//                "        14c.MM_YYYY = '" + month_date + "'\n" +
//                "        AND zc.ZONE_NAME NOT IN ('DG East', 'CEI DG')\n" +
//                "),\n" +
//                "cte2 AS (\n" +
//                "    SELECT \n" +
//                "        zc.ZONE_NAME, \n" +
//                "        cc.ZONE_CODE, \n" +
//                "        cc.COMM_NAME, \n" +
//                "        (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3 \n" +
//                "    FROM \n" +
//                "        mis_gst_commcode AS cc \n" +
//                "    RIGHT JOIN \n" +
//                "        mis_dgi_st_1a AS 14c \n" +
//                "    ON \n" +
//                "        cc.COMM_CODE = 14c.COMM_CODE \n" +
//                "    LEFT JOIN \n" +
//                "        mis_gst_zonecode AS zc \n" +
//                "    ON \n" +
//                "        zc.ZONE_CODE = cc.ZONE_CODE \n" +
//                "    WHERE \n" +
//                "        14c.MM_YYYY = '" + prev_month_new + "'\n" +
//                "        AND zc.ZONE_NAME NOT IN ('DG East', 'CEI DG')\n" +
//                "),\n" +
//                "final_data AS (\n" +
//                "    SELECT \n" +
//                "        cte1.ZONE_NAME, \n" +
//                "        cte1.ZONE_CODE, \n" +
//                "        cte1.COMM_NAME, \n" +
//                "        cte1.col9, \n" +
//                "        cte2.col3,\n" +
//                "        CASE \n" +
//                "            WHEN cte2.col3 = 0 THEN 0 -- Use 0 instead of NULL\n" +
//                "            ELSE (cte1.col9 / cte2.col3) * 100 \n" +
//                "        END AS total_score\n" +
//                "    FROM \n" +
//                "        cte1\n" +
//                "    JOIN \n" +
//                "        cte2 \n" +
//                "    ON \n" +
//                "        cte1.ZONE_NAME = cte2.ZONE_NAME \n" +
//                "        AND cte1.ZONE_CODE = cte2.ZONE_CODE \n" +
//                "        AND cte1.COMM_NAME = cte2.COMM_NAME\n" +
//                ")\n" +
//                "SELECT \n" +
//                "    ZONE_NAME, \n" +
//                "    ZONE_CODE, \n" +
//                "    COMM_NAME, \n" +
//                "    col9, \n" +
//                "    col3, \n" +
//                "    total_score,\n" +
//                "    DENSE_RANK() OVER (ORDER BY total_score DESC) AS z_rank\n" +
//                "FROM \n" +
//                "    final_data;\n";

        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst6b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH RankedData AS (\n"
                + "    SELECT zc.ZONE_NAME,cc.ZONE_CODE,\n"
                + "        SUM(14c.COMM_MORE_YEAR_AMT +14c.JC_MORE_YEAR_AMT +14c.AC_MORE_YEAR_AMT +14c.SUP_MORE_YEAR_AMT) AS col18,SUM(14c.COMM_CLOSING_NO +14c.JC_CLOSING_NO +14c.AC_CLOSING_NO +14c.SUP_CLOSING_NO) AS col13,\n"
                + "        CASE \n"
                + "            WHEN SUM(\n"
                + "                14c.COMM_CLOSING_NO +14c.JC_CLOSING_NO +14c.AC_CLOSING_NO +14c.SUP_CLOSING_NO\n"
                + "            ) = 0 THEN 0\n"
                + "            ELSE (SUM(14c.COMM_MORE_YEAR_AMT +14c.JC_MORE_YEAR_AMT +14c.AC_MORE_YEAR_AMT +14c.SUP_MORE_YEAR_AMT\n"
                + "            ) / NULLIF(SUM(14c.COMM_CLOSING_NO +14c.JC_CLOSING_NO +14c.AC_CLOSING_NO +14c.SUP_CLOSING_NO), 0)) * 100\n"
                + "        END AS total_score\n"
                + "    FROM mis_gst_commcode AS cc\n"
                + "    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "    WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE,zc.ZONE_NAME\n"
                + "),\n"
                + "RankedDataWithRank AS (\n"
                + "    SELECT *,\n"
                + "        RANK() OVER (ORDER BY total_score ASC) AS z_rank\n"
                + "    FROM RankedData\n"
                + ")\n"
                + "SELECT ZONE_NAME,ZONE_CODE,col18,col13,total_score,z_rank\n"
                + "FROM RankedDataWithRank;";
        return queryGst14aa;
    }
    public String QueryFor_gst6b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT \n" +
                "    zc.ZONE_NAME, \n" +
                "    cc.COMM_NAME, \n" +
                "    cc.ZONE_CODE,\n" +
                "    (14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) AS col18,\n" +
                "    (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col13,\n" +
                "    ((14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) / \n" +
                "     (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO)) * 100 AS total_score \n" +
                "FROM \n" +
                "    mis_gst_commcode AS cc\n" +
                "RIGHT JOIN \n" +
                "    mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN \n" +
                "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE \n" +
                "    14c.MM_YYYY = '" + month_date + "' AND \n" +
                "    zc.ZONE_CODE = '"+zone_code+"'\n" +
                "ORDER BY \n" +
                "    total_score ASC;\n";

        return queryGst14aa;
    }
    public String QueryFor_gst6b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT \n" +
                "    zc.ZONE_NAME, \n" +
                "    cc.COMM_NAME, \n" +
                "    cc.ZONE_CODE,\n" +
                "    (14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) AS col18,\n" +
                "    (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col13,\n" +
                "    CASE \n" +
                "        WHEN (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) = 0 THEN 0\n" +
                "        ELSE ((14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) / \n" +
                "              (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO)) * 100\n" +
                "    END AS total_score\n" +
                "FROM \n" +
                "    mis_gst_commcode AS cc \n" +
                "    RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE \n" +
                "    14c.MM_YYYY =  '" + month_date + "'\n" +
                "ORDER BY \n" +
                "    total_score ASC;\n";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst6c_ZoneWise(String month_date){
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH disposal_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(COALESCE(14c.COMM_DISPOSAL_NO, 0) +COALESCE(14c.JC_DISPOSAL_NO, 0) +COALESCE(14c.AC_DISPOSAL_NO, 0) +COALESCE(14c.SUP_DISPOSAL_NO, 0)\n" +
                "                        ) AS numerator_6c,\n" +
                "                        ROW_NUMBER() OVER (ORDER BY SUM(COALESCE(14c.COMM_DISPOSAL_NO, 0) +COALESCE(14c.JC_DISPOSAL_NO, 0) +COALESCE(14c.AC_DISPOSAL_NO, 0) +COALESCE(14c.SUP_DISPOSAL_NO, 0)\n" +
                "                        )) AS row_num,\n" +
                "                        COUNT(*) OVER () AS total_count\n" +
                "                    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + month_date + "'  -- Current month\\n\" +\n" +
                "                    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "                    ORDER BY numerator_6c\n" +
                "                ),\n" +
                "                median_data AS (\n" +
                "                    SELECT\n" +
                "                        CASE\n" +
                "                            WHEN total_count % 2 = 1 THEN\n" +
                "                                (SELECT numerator_6c FROM disposal_data WHERE row_num = (total_count + 1) / 2)\n" +
                "                            ELSE\n" +
                "                                (SELECT numerator_6c FROM disposal_data \n" +
                "                                 WHERE row_num = total_count / 2) -- Select the lower middle value in the case of even count\\n\" +\n" +
                "                        END AS median_numerator_6c\n" +
                "                    FROM disposal_data\n" +
                "                    LIMIT 1\n" +
                "                ),\n" +
                "                closing_data AS (\n" +
                "                    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                "                        SUM(COALESCE(14c.COMM_CLOSING_NO, 0) +COALESCE(14c.JC_CLOSING_NO, 0) +COALESCE(14c.AC_CLOSING_NO, 0) +COALESCE(14c.SUP_CLOSING_NO, 0)\n" +
                "\t        ) AS col3\n" +
                "\t    FROM mis_gst_commcode AS cc\n" +
                "                    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "                    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "                    WHERE 14c.MM_YYYY = '" + prev_month_new + "'  -- Previous month\n" +
                "                    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "                ),\n" +
                "                ranked_data AS (\n" +
                "                    SELECT d.ZONE_NAME, d.ZONE_CODE,d.numerator_6c,\n" +
                "                        COALESCE(c.col3, 0) AS col3,  -- Handle possible NULL values\n" +
                "                        CASE WHEN COALESCE(c.col3, 0) = 0 THEN 0\n" +
                "                            ELSE (d.numerator_6c * 100 / COALESCE(c.col3, 0)) \n" +
                "                        END AS score_of_parameter6c,\n" +
                "                        CONCAT(d.numerator_6c, '/', COALESCE(c.col3, 0)) AS absval  -- Format absolute values\n" +
                "                    FROM disposal_data AS d\n" +
                "                    LEFT JOIN closing_data AS c ON d.ZONE_NAME = c.ZONE_NAME AND d.ZONE_CODE = c.ZONE_CODE\n" +
                "                )\n" +
                "                SELECT t.ZONE_NAME, t.ZONE_CODE,t.numerator_6c,t.col3,t.score_of_parameter6c,t.absval,m.median_numerator_6c\n" +
                "                FROM ranked_data AS t\n" +
                "                CROSS JOIN median_data AS m\n" +
                "                WHERE t.ZONE_NAME NOT IN ('DG East', 'CEI DG');";
        return queryGst14aa;
    }
    public String QueryFor_gst6c_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH CTE1 AS (\n" +
                "SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,\n" +
                "(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9\n" +
                "FROM mis_gst_commcode AS cc\n" +
                "RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "),\n" +
                "CTE2 AS (\n" +
                "SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,\n" +
                "(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
                "FROM mis_gst_commcode AS cc\n" +
                "RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
                "),\n" +
                "CTE_Median AS (\n" +
                "SELECT ZONE_NAME, COMM_NAME, ZONE_CODE, col9,\n" +
                "(SELECT col9\n" +
                "FROM (SELECT col9, ROW_NUMBER() OVER (ORDER BY col9) AS row_num\n" +
                "FROM CTE1) AS ranked\n" +
                "WHERE row_num = FLOOR((SELECT COUNT(*) FROM CTE1) / 2) + 1\n" +
                ") AS median_6c\n" +
                "FROM CTE1\n" +
                ")\n" +
                "SELECT CTE1.ZONE_NAME, CTE1.COMM_NAME, CTE1.ZONE_CODE, CTE1.col9, CTE2.col3,\n" +
                "(CASE WHEN CTE2.col3 = 0 THEN 0 ELSE (CTE1.col9 / CTE2.col3) * 100 END) AS total_score,\n" +
                "CTE_Median.median_6c\n" +
                "FROM CTE1\n" +
                "JOIN CTE2 \n" +
                "ON CTE1.ZONE_NAME = CTE2.ZONE_NAME \n" +
                "AND CTE1.COMM_NAME = CTE2.COMM_NAME \n" +
                "AND CTE1.ZONE_CODE = CTE2.ZONE_CODE\n" +
                "JOIN CTE_Median \n" +
                "ON CTE1.ZONE_NAME = CTE_Median.ZONE_NAME \n" +
                "AND CTE1.COMM_NAME = CTE_Median.COMM_NAME \n" +
                "AND CTE1.ZONE_CODE = CTE_Median.ZONE_CODE\n" +
                "WHERE CTE1.ZONE_CODE ='" + zone_code + "'\n" +
                "ORDER BY total_score DESC;";
        return queryGst14aa;
    }
    public String QueryFor_gst6c_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "WITH CTE1 AS (\n" +
                "SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,\n" +
                "(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9\n" +
                "FROM mis_gst_commcode AS cc\n" +
                "RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "),\n" +
                "CTE2 AS (\n" +
                "SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,\n" +
                "(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
                "FROM mis_gst_commcode AS cc\n" +
                "RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
                "),\n" +
                "CTE_Median AS (\n" +
                "SELECT ZONE_NAME, COMM_NAME, ZONE_CODE, col9,\n" +
                "(SELECT col9\n" +
                "FROM (SELECT col9, ROW_NUMBER() OVER (ORDER BY col9) AS row_num\n" +
                "FROM CTE1) AS ranked\n" +
                "WHERE row_num = FLOOR((SELECT COUNT(*) FROM CTE1) / 2) + 1) AS median_6c\n" +
                "FROM CTE1)\n" +
                "SELECT CTE1.ZONE_NAME, CTE1.COMM_NAME, CTE1.ZONE_CODE, CTE1.col9, CTE2.col3,\n" +
                "(CASE WHEN CTE2.col3 = 0 THEN 0 ELSE (CTE1.col9 / CTE2.col3) * 100 END) AS total_score,\n" +
                "CTE_Median.median_6c\n" +
                "FROM CTE1\n" +
                "JOIN CTE2 \n" +
                "ON CTE1.ZONE_NAME = CTE2.ZONE_NAME \n" +
                "AND CTE1.COMM_NAME = CTE2.COMM_NAME \n" +
                "AND CTE1.ZONE_CODE = CTE2.ZONE_CODE\n" +
                "JOIN CTE_Median \n" +
                "ON CTE1.ZONE_NAME = CTE_Median.ZONE_NAME \n" +
                "AND CTE1.COMM_NAME = CTE_Median.COMM_NAME \n" +
                "AND CTE1.ZONE_CODE = CTE_Median.ZONE_CODE\n" +
                "ORDER BY total_score DESC;";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst6d_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT \n" +
                "    zc.ZONE_NAME,\n" +
                "    cc.ZONE_CODE,\n" +
                "    SUM(\n" +
                "        14c.COMM_MORE_YEAR_AMT +\n" +
                "        14c.JC_MORE_YEAR_AMT +\n" +
                "        14c.AC_MORE_YEAR_AMT +\n" +
                "        14c.SUP_MORE_YEAR_AMT\n" +
                "    ) AS col18,\n" +
                "    SUM(\n" +
                "        14c.COMM_CLOSING_NO +\n" +
                "        14c.JC_CLOSING_NO +\n" +
                "        14c.AC_CLOSING_NO +\n" +
                "        14c.SUP_CLOSING_NO\n" +
                "    ) AS col13,\n" +
                "    COALESCE(\n" +
                "        (SUM(\n" +
                "            14c.COMM_MORE_YEAR_AMT +\n" +
                "            14c.JC_MORE_YEAR_AMT +\n" +
                "            14c.AC_MORE_YEAR_AMT +\n" +
                "            14c.SUP_MORE_YEAR_AMT\n" +
                "        ) / NULLIF(SUM(\n" +
                "            14c.COMM_CLOSING_NO +\n" +
                "            14c.JC_CLOSING_NO +\n" +
                "            14c.AC_CLOSING_NO +\n" +
                "            14c.SUP_CLOSING_NO\n" +
                "        ), 0)) * 100, 0\n" +
                "    ) AS total_score\n" +
                "FROM mis_gst_commcode AS cc\n" +
                "RIGHT JOIN mis_dgi_ce_1a AS 14c\n" +
                "    ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc\n" +
                "    ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "ORDER BY total_score ASC;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst6d_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT zc.ZONE_NAME,\n" +
                "       cc.COMM_NAME,\n" +
                "       cc.ZONE_CODE,\n" +
                "       (14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) AS col18,\n" +
                "       (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col13,\n" +
                "       ((14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) /\n" +
                "       (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) * 100) AS total_score\n" +
                "FROM mis_gst_commcode AS cc\n" +
                "RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE = '"+zone_code+"'\n" +
                "ORDER BY total_score ASC;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst6d_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT \n" +
                "    zc.ZONE_NAME, \n" +
                "    cc.ZONE_CODE, \n" +
                "    cc.COMM_NAME,\n" +
                "    IFNULL((14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT), 0) AS col18, \n" +
                "    IFNULL((14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO), 0) AS col13,\n" +
                "    IFNULL((14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) / \n" +
                "    (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO), 0) AS total_score_of_subpara4,\n" +
                "    IFNULL(((14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) / \n" +
                "    (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO)) * 100, 0) AS total_score\n" +
                "FROM \n" +
                "    mis_gst_commcode AS cc\n" +
                "RIGHT JOIN \n" +
                "    mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN \n" +
                "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE \n" +
                "    14c.MM_YYYY = '" + month_date + "'\n" +
                "ORDER BY \n" +
                "    total_score ASC;\n";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst7_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "SELECT " +
                "SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS col16, " +
                "SUM(14c.age_breakup_above60_no) AS col22, " +
                "cc.ZONE_CODE,zc.ZONE_NAME " +
                "FROM mis_gst_commcode AS cc " +
                "RIGHT JOIN mis_dpm_gst_4 AS 14c " +
                "ON cc.COMM_CODE = 14c.COMM_CODE " +
                "LEFT JOIN mis_gst_zonecode AS zc " +
                "ON zc.ZONE_CODE = cc.ZONE_CODE " +
                "WHERE 14c.MM_YYYY = '" + month_date + "' " +
                "GROUP BY cc.ZONE_CODE;";
        return queryGst14aa;
    }
    public String QueryFor_gst7_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "SELECT \r\n"
                + "    cc.ZONE_CODE,\r\n"
                + "    zc.ZONE_NAME,\r\n"
                + "    cc.COMM_NAME,\r\n"
                + "    SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO) AS col16,\r\n"
                + "    SUM(dpm.age_breakup_above60_no) AS col22\r\n"
                + "FROM mis_gst_commcode AS cc\r\n"
                + "RIGHT JOIN mis_dpm_gst_4 AS dpm\r\n"
                + "    ON cc.COMM_CODE = dpm.COMM_CODE\r\n"
                + "LEFT JOIN mis_gst_zonecode AS zc\r\n"
                + "    ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                + "WHERE dpm.MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE = '"+zone_code+"'\r\n"
                + "GROUP BY \r\n"
                + "    cc.ZONE_CODE, \r\n"
                + "    zc.ZONE_NAME,\r\n"
                + "    cc.COMM_NAME;\r\n"
                + "";
        return queryGst14aa;
    }
    public String QueryFor_gst7_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT \r\n"
                + "    cc.ZONE_CODE,\r\n"
                + "    zc.ZONE_NAME,\r\n"
                + "    cc.COMM_NAME,\r\n"
                + "    SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO) AS col16,\r\n"
                + "    SUM(dpm.age_breakup_above60_no) AS col22\r\n"
                + "FROM mis_gst_commcode AS cc\r\n"
                + "RIGHT JOIN mis_dpm_gst_4 AS dpm\r\n"
                + "    ON cc.COMM_CODE = dpm.COMM_CODE\r\n"
                + "LEFT JOIN mis_gst_zonecode AS zc\r\n"
                + "    ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                + "WHERE dpm.MM_YYYY = '" + month_date + "' \r\n"
                + "GROUP BY \r\n"
                + "    cc.ZONE_CODE, \r\n"
                + "    zc.ZONE_NAME,\r\n"
                + "    cc.COMM_NAME;\r\n"
                + "";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst8a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH cte AS (\n" +
                "    SELECT DISTINCT \n" +
                "        zc.ZONE_NAME,zc.ZONE_CODE, cc.COMM_CODE,\n" +
                "        SUM(a11.ARREAR_REALISED_AMT) AS col13\n" +
                "    FROM mis_tar_gst_3_new AS a11\n" +
                "    LEFT JOIN mis_gst_commcode AS cc ON a11.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                "    WHERE a11.MM_YYYY = '"+month_date+"'\n" +
                "    GROUP BY zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_CODE\n" +
                "), \n" +
                "cte1 AS (\n" +
                "    SELECT DISTINCT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_CODE,\n" +
                "        SUM(1) AS col3\n" +
                "    FROM mis_tar_gst_3_new AS a11\n" +
                "    LEFT JOIN mis_gst_commcode AS cc ON a11.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                "    WHERE a11.MM_YYYY = '"+ prev_month_new +"'\n" +
                "    GROUP BY zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_CODE\n" +
                ")\n" +
                "SELECT\n" +
                "    cte.ZONE_NAME, cte.ZONE_CODE,cte.COMM_CODE,\n" +
                "    cte.col13, cte1.col3,\n" +
                "    (cte.col13 / cte1.col3) AS total_score\n" +
                "FROM cte\n" +
                "INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst8a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "WITH cte AS ( "
                + "SELECT DISTINCT "
                + "zc.ZONE_NAME, "
                + "zc.ZONE_CODE, "
                + "cc.COMM_NAME, "
                + "cc.COMM_CODE, "
                + "(a11.ARREAR_REALISED_AMT) AS col13 "
                + "FROM mis_tar_gst_3_new AS a11 "
                + "LEFT JOIN mis_gst_commcode AS cc ON a11.COMM_CODE = cc.COMM_CODE "
                + "LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE "
                + "WHERE a11.MM_YYYY = '"+month_date+"' AND cc.ZONE_CODE = '+zone_code+' "
                + "), "
                + "cte1 AS ( "
                + "SELECT DISTINCT "
                + "zc.ZONE_NAME, "
                + "zc.ZONE_CODE, "
                + "cc.COMM_NAME, "
                + "cc.COMM_CODE, "
                + "1 AS col3 "
                + "FROM mis_tar_gst_3_new AS a11 "
                + "LEFT JOIN mis_gst_commcode AS cc ON a11.COMM_CODE = cc.COMM_CODE "
                + "LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE "
                + "WHERE a11.MM_YYYY = '" + prev_month_new + "'"
                + "AND cc.ZONE_CODE = '"+zone_code+"' "
                + ") "
                + "SELECT "
                + "cte.ZONE_NAME, "
                + "cte.ZONE_CODE, "
                + "cte.COMM_NAME, "
                + "cte.COMM_CODE, "
                + "cte.col13, "
                + "cte1.col3, "
                + "((cte.col13 / cte1.col3)) AS total_score "
                + "FROM cte "
                + "INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE;";
        return queryGst14aa;
    }
    public String QueryFor_gst8a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "WITH cte AS (\n" +
                "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, a.PROSECUTION_LAUNCHED AS col4_1, a.ARRESTS_MADE AS col4_2\n" +
                "    FROM mis_gi_gst_1a AS a \n" +
                "    LEFT JOIN mis_gst_commcode AS cc ON a.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "    WHERE a.MM_YYYY = '"+month_date+"' \n" +
                "),\n" +
                "cte1 AS (\n" +
                "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, a.PROSECUTION_LAUNCHED AS col4_3, a.ARRESTS_MADE AS col4_4\n" +
                "    FROM mis_gi_gst_1a AS a \n" +
                "    LEFT JOIN mis_gst_commcode AS cc ON a.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "    WHERE a.MM_YYYY = '" + prev_month_new + "' \n" +
                ")\n" +
                "SELECT DISTINCT cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_NAME, cte.col4_1,cte.col4_2,cte1.col4_3,cte1.col4_4\n" +
                "FROM cte INNER JOIN cte1 \n" +
                "ON cte.ZONE_CODE = cte1.ZONE_CODE \n" +
                "AND cte.COMM_NAME = cte1.COMM_NAME;";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst8b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "SELECT\n" +
                "    cc.ZONE_CODE,\n" +
                "    zc.ZONE_NAME,\n" +
                "    SUM(tc.CLOSING_AMT) AS col20,\n" +
                "    SUM(tc.BELOW_YEAR_AMT) AS col22,\n" +
                "    -- Calculate total_score using the formula\n" +
                "    (SUM(tc.CLOSING_AMT) - SUM(tc.BELOW_YEAR_AMT)) / NULLIF(SUM(tc.CLOSING_AMT), 0) * 100 AS total_score\n" +
                "FROM\n" +
                "    mis_gst_commcode AS cc\n" +
                "RIGHT JOIN\n" +
                "    mis_tar_gst_3_new AS tc ON cc.COMM_CODE = tc.COMM_CODE\n" +
                "LEFT JOIN\n" +
                "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE\n" +
                "    tc.MM_YYYY = '" + month_date + "'\n" +
                "GROUP BY\n" +
                "    cc.ZONE_CODE, zc.ZONE_NAME\n" +
                "ORDER BY\n" +
                "    total_score ASC;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst8b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT \n" +
                "    cc.ZONE_CODE, \n" +
                "    zc.ZONE_NAME,\n" +
                "    cc.COMM_NAME,  \n" +
                "    MAX(14c.CLOSING_AMT) AS col20, \n" +
                "    MAX(14c.BELOW_YEAR_AMT) AS col22,\n" +
                "    ((MAX(14c.CLOSING_AMT) - MAX(14c.BELOW_YEAR_AMT)) / MAX(14c.CLOSING_AMT)) * 100 AS total_score\n" +
                "FROM \n" +
                "    mis_gst_commcode AS cc \n" +
                "RIGHT JOIN \n" +
                "    mis_tar_gst_3_new AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN \n" +
                "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE \n" +
                "    14c.MM_YYYY = '"+month_date+"' \n" +
                "    AND zc.ZONE_CODE = '"+zone_code+"'\n" +
                "GROUP BY \n" +
                "    cc.ZONE_CODE, \n" +
                "    zc.ZONE_NAME,\n" +
                "    cc.COMM_NAME\n" +
                "ORDER BY \n" +
                "    total_score ASC;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst8b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "SELECT \n" +
                "    cc.ZONE_CODE, \n" +
                "    zc.ZONE_NAME,\n" +
                "    cc.COMM_NAME,  \n" +
                "    MAX(14c.CLOSING_AMT) AS col20, \n" +
                "    MAX(14c.BELOW_YEAR_AMT) AS col22,\n" +
                "    ((MAX(14c.CLOSING_AMT) - MAX(14c.BELOW_YEAR_AMT)) / MAX(14c.CLOSING_AMT)) * 100 AS total_score\n" +
                "FROM \n" +
                "    mis_gst_commcode AS cc \n" +
                "RIGHT JOIN \n" +
                "    mis_tar_gst_3_new AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN \n" +
                "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE \n" +
                "    14c.MM_YYYY = '"+month_date+"' \n" +
                "GROUP BY \n" +
                "    cc.ZONE_CODE, \n" +
                "    zc.ZONE_NAME,\n" +
                "    cc.COMM_NAME\n" +
                "ORDER BY \n" +
                "    total_score ASC;\n";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst9a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH cte AS (\n" +
                "    SELECT \n" +
                "        zc.ZONE_NAME, \n" +
                "        zc.ZONE_CODE, \n" +
                "        COUNT(*) AS col8,\n" +
                "        SUM(lgl.PROSECUTION_SANCTIONED) AS col5\n" +
                "    FROM mis_dla_gst_lgl_4 AS lgl \n" +
                "    LEFT JOIN mis_gst_commcode AS cc ON lgl.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "    WHERE lgl.MM_YYYY = '"+month_date+"'\n" +
                "    GROUP BY zc.ZONE_CODE\n" +
                "),\n" +
                "cte1 AS (\n" +
                "    SELECT \n" +
                "        zc.ZONE_NAME, \n" +
                "        zc.ZONE_CODE, \n" +
                "        SUM(lgl.PROSECUTION_SANCTIONED) AS col5_1 \n" +
                "    FROM mis_dla_gst_lgl_4 AS lgl \n" +
                "    LEFT JOIN mis_gst_commcode AS cc ON lgl.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "    WHERE lgl.MM_YYYY = '"+prev_month_new+"'\n" +
                "    GROUP BY zc.ZONE_CODE\n" +
                ")\n" +
                "SELECT \n" +
                "    cte.ZONE_NAME, \n" +
                "    cte.ZONE_CODE, \n" +
                "    cte.col8,\n" +
                "    cte.col5,\n" +
                "    cte1.col5_1,\n" +
                "    (cte.col8 / (cte.col5 + cte1.col5_1)) AS total_score\n" +
                "FROM cte \n" +
                "INNER JOIN cte1 ON cte.ZONE_CODE = cte1.ZONE_CODE;";
        return queryGst14aa;
    }
    public String QueryFor_gst9a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH cte AS (\n" +
                "    SELECT \n" +
                "        zc.ZONE_NAME, \n" +
                "        zc.ZONE_CODE, \n" +
                "        cc.COMM_NAME, \n" +
                "        cc.COMM_CODE, \n" +
                "        1 AS col8,\n" +
                "        _11a.PROSECUTION_SANCTIONED AS col5 \n" +
                "    FROM \n" +
                "        mis_dla_gst_lgl_4 AS _11a \n" +
                "    LEFT JOIN \n" +
                "        mis_gst_commcode AS cc ON _11a.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN \n" +
                "        mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "    WHERE \n" +
                "        _11a.MM_YYYY = '"+month_date+"' \n" +
                "        AND cc.ZONE_CODE = '"+zone_code+"'\n" +
                "),\n" +
                "cte1 AS (\n" +
                "    SELECT \n" +
                "        zc.ZONE_NAME, \n" +
                "        zc.ZONE_CODE, \n" +
                "        cc.COMM_NAME, \n" +
                "        cc.COMM_CODE, \n" +
                "        _11a.PROSECUTION_SANCTIONED AS col5_1\n" +
                "    FROM \n" +
                "        mis_dla_gst_lgl_4 AS _11a \n" +
                "    LEFT JOIN \n" +
                "        mis_gst_commcode AS cc ON _11a.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN \n" +
                "        mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "    WHERE \n" +
                "        _11a.MM_YYYY = '"+prev_month_new+"'\n" +
                "        AND cc.ZONE_CODE = '"+zone_code+"'\n" +
                ")\n" +
                "SELECT \n" +
                "    cte.ZONE_NAME,\n" +
                "    cte.ZONE_CODE,\n" +
                "    cte.COMM_NAME,\n" +
                "    cte.COMM_CODE,\n" +
                "    cte.col8,\n" +
                "    cte.col5,\n" +
                "    cte1.col5_1,\n" +
                "    CASE \n" +
                "        WHEN (cte.col5 + cte1.col5_1) <> 0 THEN cte.col8 / (cte.col5 + cte1.col5_1)\n" +
                "        ELSE NULL -- or any default value or handling you prefer\n" +
                "    END AS total_score\n" +
                "FROM \n" +
                "    cte \n" +
                "INNER JOIN \n" +
                "    cte1 ON cte.COMM_CODE = cte1.COMM_CODE;";
        return queryGst14aa;
    }
    public String QueryFor_gst9a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH cte AS (\n" +
                "    SELECT \n" +
                "        zc.ZONE_NAME, \n" +
                "        zc.ZONE_CODE, \n" +
                "        cc.COMM_NAME, \n" +
                "        cc.COMM_CODE, \n" +
                "        1 AS col8,\n" +
                "        _11a.PROSECUTION_SANCTIONED AS col5 \n" +
                "    FROM \n" +
                "        mis_dla_gst_lgl_4 AS _11a \n" +
                "    LEFT JOIN \n" +
                "        mis_gst_commcode AS cc ON _11a.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN \n" +
                "        mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "    WHERE \n" +
                "        _11a.MM_YYYY = '"+month_date+"' \n" +
                "),\n" +
                "cte1 AS (\n" +
                "    SELECT \n" +
                "        zc.ZONE_NAME, \n" +
                "        zc.ZONE_CODE, \n" +
                "        cc.COMM_NAME, \n" +
                "        cc.COMM_CODE, \n" +
                "        _11a.PROSECUTION_SANCTIONED AS col5_1\n" +
                "    FROM \n" +
                "        mis_dla_gst_lgl_4 AS _11a \n" +
                "    LEFT JOIN \n" +
                "        mis_gst_commcode AS cc ON _11a.COMM_CODE = cc.COMM_CODE \n" +
                "    LEFT JOIN \n" +
                "        mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "    WHERE \n" +
                "        _11a.MM_YYYY = '"+prev_month_new+"'\n" +
                ")\n" +
                "SELECT \n" +
                "    cte.ZONE_NAME,\n" +
                "    cte.ZONE_CODE,\n" +
                "    cte.COMM_NAME,\n" +
                "    cte.COMM_CODE,\n" +
                "    cte.col8,\n" +
                "    cte.col5,\n" +
                "    cte1.col5_1,\n" +
                "    CASE \n" +
                "        WHEN (cte.col5 + cte1.col5_1) <> 0 THEN cte.col8 / (cte.col5 + cte1.col5_1)\n" +
                "        ELSE NULL -- or any default value or handling you prefer\n" +
                "    END AS total_score\n" +
                "FROM \n" +
                "    cte \n" +
                "INNER JOIN \n" +
                "    cte1 ON cte.COMM_CODE = cte1.COMM_CODE;";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst9b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa=  "WITH score_data AS (\n"
                + "    SELECT \n"
                + "        zc.ZONE_NAME,\n"
                + "        cc.ZONE_CODE,\n"
                + "        SUM(14c.PROSECUTION_LAUNCHED) AS col4_4,\n"
                + "        SUM(14c.ARRESTS_MADE) AS col1_4,\n"
                + "        (SUM(14c.PROSECUTION_LAUNCHED) * 100 / SUM(14c.ARRESTS_MADE)) AS score_of_subparameter9b\n"
                + "    FROM \n"
                + "        mis_gst_commcode AS cc\n"
                + "    RIGHT JOIN \n"
                + "        mis_gi_gst_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n"
                + "    LEFT JOIN \n"
                + "        mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "    WHERE \n"
                + "        14c.MM_YYYY <= '" + month_date + "'\n"
                + "    GROUP BY \n"
                + "        cc.ZONE_CODE, zc.ZONE_NAME\n"
                + "),\n"
                + "ranked_data AS (\n"
                + "    SELECT \n"
                + "        col4_4,\n"
                + "        @row_number := @row_number + 1 AS row_num,\n"
                + "        @total_rows := @total_rows + 1\n"
                + "    FROM \n"
                + "        score_data, (SELECT @row_number := 0, @total_rows := 0) AS vars\n"
                + "    ORDER BY \n"
                + "        col4_4\n"
                + ")\n"
                + "SELECT \n"
                + "    ZONE_NAME,\n"
                + "    ZONE_CODE,\n"
                + "    col4_4,\n"
                + "    col1_4,\n"
                + "    score_of_subparameter9b,\n"
                + "    (SELECT \n"
                + "        AVG(col4_4) \n"
                + "     FROM \n"
                + "        ranked_data \n"
                + "     WHERE \n"
                + "        row_num IN (FLOOR((@total_rows + 1) / 2), CEIL((@total_rows + 1) / 2))) AS median9_b\n"
                + "FROM \n"
                + "    score_data\n"
                + "ORDER BY \n"
                + "    score_of_subparameter9b DESC;\n"
                + "";
        return queryGst14aa;
    }
    public String QueryFor_gst9b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "SELECT \n"
                + "    zc.ZONE_NAME,\n"
                + "    cc.ZONE_CODE,\n"
                + "    cc.COMM_NAME,\n"
                + "    SUM(gst.PROSECUTION_LAUNCHED) AS col4_4,\n"
                + "    SUM(gst.ARRESTS_MADE) AS col1_4,\n"
                + "    (SUM(gst.PROSECUTION_LAUNCHED) * 100 / SUM(gst.ARRESTS_MADE)) AS score_of_subparameter9b,\n"
                + "    (SELECT AVG(sub.prosecution_launched) AS median_9b\n"
                + "     FROM (\n"
                + "         SELECT SUM(gst.PROSECUTION_LAUNCHED) AS prosecution_launched\n"
                + "         FROM mis_gst_commcode AS cc\n"
                + "         RIGHT JOIN mis_gi_gst_1a AS gst ON cc.COMM_CODE = gst.COMM_CODE\n"
                + "         LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "         WHERE gst.MM_YYYY <= '" + month_date + "'\n"
                + "         GROUP BY cc.COMM_CODE\n"
                + "         ORDER BY prosecution_launched\n"
                + "         LIMIT 2) AS sub) AS median_9b\n"
                + "FROM \n"
                + "    mis_gst_commcode AS cc\n"
                + "RIGHT JOIN \n"
                + "    mis_gi_gst_1a AS gst ON cc.COMM_CODE = gst.COMM_CODE\n"
                + "LEFT JOIN \n"
                + "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "WHERE \n"
                + "    gst.MM_YYYY <= '" + month_date + "' \n"
                + "    AND cc.ZONE_CODE = '" + zone_code + "'\n"
                + "GROUP BY \n"
                + "    zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME \n"
                + "ORDER BY \n"
                + "    score_of_subparameter9b DESC;\n"
                + "";
        return queryGst14aa;
    }
    public String QueryFor_gst9b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT \n"
                + "    zc.ZONE_NAME,\n"
                + "    cc.ZONE_CODE,\n"
                + "    cc.COMM_NAME,\n"
                + "    SUM(gst.PROSECUTION_LAUNCHED) AS col4_4,\n"
                + "    SUM(gst.ARRESTS_MADE) AS col1_4,\n"
                + "    (SUM(gst.PROSECUTION_LAUNCHED) * 100 / SUM(gst.ARRESTS_MADE)) AS score_of_subparameter9b,\n"
                + "    (SELECT AVG(sub.prosecution_launched) AS median_9b\n"
                + "     FROM (\n"
                + "         SELECT SUM(gst.PROSECUTION_LAUNCHED) AS prosecution_launched\n"
                + "         FROM mis_gst_commcode AS cc\n"
                + "         RIGHT JOIN mis_gi_gst_1a AS gst ON cc.COMM_CODE = gst.COMM_CODE\n"
                + "         LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "         WHERE gst.MM_YYYY <= '" + month_date + "'\n"
                + "         GROUP BY cc.COMM_CODE\n"
                + "         ORDER BY prosecution_launched\n"
                + "         LIMIT 2) AS sub) AS median_9b\n"
                + "FROM \n"
                + "    mis_gst_commcode AS cc\n"
                + "RIGHT JOIN \n"
                + "    mis_gi_gst_1a AS gst ON cc.COMM_CODE = gst.COMM_CODE\n"
                + "LEFT JOIN \n"
                + "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n"
                + "WHERE \n"
                + "    gst.MM_YYYY <= '" + month_date + "' \n"
                + "GROUP BY \n"
                + "    zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME \n"
                + "ORDER BY \n"
                + "    score_of_subparameter9b DESC;\n"
                + "";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst10a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT \n" +
                "    zc.ZONE_NAME, \n" +
                "    cc.ZONE_CODE, \n" +
                "    FORMAT(SUM(TAXPAYER_AUDITED_NO_LARGE + TAXPAYER_AUDITED_NO_MEDIUM + TAXPAYER_AUDITED_NO_SMALL), 2) AS col3,\n" +
                "    FORMAT(SUM(TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL), 2) AS total,\n" +
                "    CASE \n" +
                "        WHEN SUM(TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL) != 0 \n" +
                "        THEN (2 * SUM(TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL)) / 12.0 \n" +
                "        ELSE 0.00 \n" +
                "    END AS col2,\n" +
                "    (SUM(TAXPAYER_AUDITED_NO_LARGE + TAXPAYER_AUDITED_NO_MEDIUM + TAXPAYER_AUDITED_NO_SMALL) / \n" +
                "    CASE \n" +
                "        WHEN SUM(TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL) != 0 \n" +
                "        THEN (2 * SUM(TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL)) / 12.0 \n" +
                "        ELSE 1 \n" +
                "    END) * 100 AS total_score\n" +
                "FROM \n" +
                "    mis_gst_commcode AS cc \n" +
                "RIGHT JOIN \n" +
                "    mis_dga_gst_adt_2 AS c2 \n" +
                "    ON cc.COMM_CODE = c2.COMM_CODE \n" +
                "LEFT JOIN \n" +
                "    mis_gst_zonecode AS zc \n" +
                "    ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE \n" +
                "    c2.MM_YYYY = '" + month_date + "'  -- Replace with the appropriate month_date\n" +
                "GROUP BY \n" +
                "    cc.ZONE_CODE\n" +
                "ORDER BY \n" +
                "    total_score DESC;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst10a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT\n" +
                "    zc.ZONE_NAME,\n" +
                "    cc.ZONE_CODE,\n" +
                "    cc.COMM_NAME,\n" +
                "    (TAXPAYER_AUDITED_NO_LARGE + TAXPAYER_AUDITED_NO_MEDIUM + TAXPAYER_AUDITED_NO_SMALL) AS col3,\n" +
                "    (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL) AS total,\n" +
                "    CASE\n" +
                "        WHEN (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL) != 0\n" +
                "        THEN (2 * (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL)) / 12.0\n" +
                "        ELSE 0.00\n" +
                "    END AS col2,\n" +
                "    ((TAXPAYER_AUDITED_NO_LARGE + TAXPAYER_AUDITED_NO_MEDIUM + TAXPAYER_AUDITED_NO_SMALL) /\n" +
                "        CASE\n" +
                "            WHEN (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL) != 0\n" +
                "            THEN (2 * (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL)) / 12.0\n" +
                "            ELSE 1\n" +
                "        END\n" +
                "    ) * 100 AS total_score\n" +
                "FROM\n" +
                "    mis_gst_commcode AS cc\n" +
                "RIGHT JOIN\n" +
                "    mis_dga_gst_adt_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN\n" +
                "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE\n" +
                "    14c.MM_YYYY =  '" + month_date + "'\n" +
                "    AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                "ORDER BY\n" +
                "    total_score DESC\n" +
                "LIMIT 0, 1000;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst10a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT\n" +
                "    zc.ZONE_NAME,\n" +
                "    cc.ZONE_CODE,\n" +
                "    cc.COMM_NAME,\n" +
                "    (TAXPAYER_AUDITED_NO_LARGE + TAXPAYER_AUDITED_NO_MEDIUM + TAXPAYER_AUDITED_NO_SMALL) AS col3,\n" +
                "    (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL) AS total,\n" +
                "    CASE\n" +
                "        WHEN (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL) != 0\n" +
                "        THEN (2 * (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL)) / 12.0\n" +
                "        ELSE 0.00\n" +
                "    END AS col2,\n" +
                "    ((TAXPAYER_AUDITED_NO_LARGE + TAXPAYER_AUDITED_NO_MEDIUM + TAXPAYER_AUDITED_NO_SMALL) /\n" +
                "        CASE\n" +
                "            WHEN (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL) != 0\n" +
                "            THEN (2 * (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL)) / 12.0\n" +
                "            ELSE 1\n" +
                "        END\n" +
                "    ) * 100 AS total_score\n" +
                "FROM\n" +
                "    mis_gst_commcode AS cc\n" +
                "RIGHT JOIN\n" +
                "    mis_dga_gst_adt_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN\n" +
                "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE\n" +
                "    14c.MM_YYYY = '" + month_date + "'\n" +
                "ORDER BY\n" +
                "    total_score DESC\n" +
                "LIMIT 0, 1000;\n";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst10b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT cc.ZONE_CODE, zc.ZONE_NAME,\n" +
                "SUM(14c.AUDIT_PARAS_BREAKUP_6_12_NO_LARGE + 14c.AUDIT_PARAS_BREAKUP_6_12_NO_MEDIUM + 14c.AUDIT_PARAS_BREAKUP_6_12_NO_SMALL) AS col36, \n" +
                "SUM(14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_LARGE + 14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_MEDIUM + 14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_SMALL) AS col38, \n" +
                "SUM(14c.AUDIT_PARAS_CLOSING_NO_LARGE + 14c.AUDIT_PARAS_CLOSING_NO_MEDIUM + 14c.AUDIT_PARAS_CLOSING_NO_SMALL) AS col30 \n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_dga_gst_adt_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '"+ month_date+"' \n" +
                "GROUP BY cc.ZONE_CODE;";
        return queryGst14aa;
    }
    public String QueryFor_gst10b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME, \n" +
                "(14c.AUDIT_PARAS_BREAKUP_6_12_NO_LARGE + 14c.AUDIT_PARAS_BREAKUP_6_12_NO_MEDIUM + 14c.AUDIT_PARAS_BREAKUP_6_12_NO_SMALL) AS col36, \n" +
                "(14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_LARGE + 14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_MEDIUM + 14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_SMALL) AS col38, \n" +
                "(14c.AUDIT_PARAS_CLOSING_NO_LARGE + 14c.AUDIT_PARAS_CLOSING_NO_MEDIUM + 14c.AUDIT_PARAS_CLOSING_NO_SMALL) AS col30 \n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_dga_gst_adt_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '"+ month_date+"' AND zc.ZONE_CODE = '" + zone_code + "';";
        return queryGst14aa;
    }
    public String QueryFor_gst10b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="SELECT cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME, \n" +
                "(14c.AUDIT_PARAS_BREAKUP_6_12_NO_LARGE + 14c.AUDIT_PARAS_BREAKUP_6_12_NO_MEDIUM + 14c.AUDIT_PARAS_BREAKUP_6_12_NO_SMALL) AS col36, \n" +
                "(14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_LARGE + 14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_MEDIUM + 14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_SMALL) AS col38, \n" +
                "(14c.AUDIT_PARAS_CLOSING_NO_LARGE + 14c.AUDIT_PARAS_CLOSING_NO_MEDIUM + 14c.AUDIT_PARAS_CLOSING_NO_SMALL) AS col30 \n" +
                "FROM mis_gst_commcode AS cc \n" +
                "RIGHT JOIN mis_dga_gst_adt_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '"+ month_date+"';";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst10c_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="";
        return queryGst14aa;
    }
    public String QueryFor_gst10c_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="";
        return queryGst14aa;
    }
    public String QueryFor_gst10c_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst1od_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="";
        return queryGst14aa;
    }
    public String QueryFor_gst10d_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="";
        return queryGst14aa;
    }
    public String QueryFor_gst10d_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gs11a_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH cte AS (\n" +
                "                        SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS col10 \n" +
                "                        FROM mis_dla_gst_lgl_1 11a \n" +
                "                        LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                        LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                        WHERE 11a.MM_YYYY = '"+ month_date+"' AND FORUM_CODE = 6 \n" +
                "                        GROUP BY zc.ZONE_CODE), \n" +
                "                        cte1 AS (\n" +
                "                        SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4 \n" +
                "                        FROM mis_dla_gst_lgl_1 11a \n" +
                "                        LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                        LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                        WHERE 11a.MM_YYYY = '"+ prev_month_new+"' AND FORUM_CODE = 6 \n" +
                "                        GROUP BY zc.ZONE_CODE) \n" +
                "                        SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.col10, cte1.col4, ((cte.col10 / cte1.col4)) AS total_score \n" +
                "                        FROM cte \n" +
                "                        INNER JOIN cte1 ON cte.ZONE_CODE = cte1.ZONE_CODE\n" +
                "                        order by total_score desc;";
        return queryGst14aa;
    }
    public String QueryFor_gs11a_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "WITH cte AS (SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n" +
                "                        (DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS col10 \n" +
                "                        FROM mis_dla_gst_lgl_1 AS 11a \n" +
                "                        LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                        LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                        WHERE 11a.MM_YYYY = '"+ month_date+"' AND FORUM_CODE = 6 and zc.ZONE_CODE = '" + zone_code + "' ),cte1 AS ( \n" +
                "                        SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n" +
                "                        (DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4 \n" +
                "                        FROM mis_dla_gst_lgl_1 AS 11a \n" +
                "                        LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                        LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE WHERE \n" +
                "                        11a.MM_YYYY = '"+ prev_month_new+"' AND FORUM_CODE = 6 and zc.ZONE_CODE = '" + zone_code + "') \n" +
                "                        SELECT cte.ZONE_NAME,cte.ZONE_CODE,cte.COMM_NAME,cte.COMM_CODE,cte.col10,cte1.col4, \n" +
                "                        ((cte.col10 / cte1.col4) ) AS total_score FROM cte \n" +
                "                        INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE\n" +
                "                        order by total_score desc;";
        return queryGst14aa;
    }
    public String QueryFor_gs11a_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH cte AS (SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n" +
                "                        (DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS col10 \n" +
                "                        FROM mis_dla_gst_lgl_1 AS 11a \n" +
                "                        LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                        LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "                        WHERE 11a.MM_YYYY = '"+ month_date+"' AND FORUM_CODE = 6 ),cte1 AS ( \n" +
                "                        SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n" +
                "                        (DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4 \n" +
                "                        FROM mis_dla_gst_lgl_1 AS 11a \n" +
                "                        LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "                        LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE WHERE \n" +
                "                        11a.MM_YYYY = '"+ prev_month_new+"' AND FORUM_CODE = 6 ) \n" +
                "                        SELECT cte.ZONE_NAME,cte.ZONE_CODE,cte.COMM_NAME,cte.COMM_CODE,cte.col10,cte1.col4, \n" +
                "                        ((cte.col10 / cte1.col4) ) AS total_score FROM cte \n" +
                "                        INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE\n" +
                "                        order by total_score desc;";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst11b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH cte AS (\n" +
                "    SELECT zc.ZONE_NAME,\n" +
                "           zc.ZONE_CODE,\n" +
                "           SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A,\n" +
                "           SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A\n" +
                "    FROM mis_dla_gst_lgl_1a AS 11a\n" +
                "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                "    WHERE 11a.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6\n" +
                "    GROUP BY zc.ZONE_CODE\n" +
                "),\n" +
                "cte1 AS (\n" +
                "    SELECT zc.ZONE_NAME,\n" +
                "           zc.ZONE_CODE,\n" +
                "           SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B,\n" +
                "           SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B\n" +
                "    FROM mis_dla_gst_lgl_1b AS 11b\n" +
                "    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                "    WHERE 11b.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6\n" +
                "    GROUP BY zc.ZONE_CODE\n" +
                ")\n" +
                "SELECT cte.ZONE_NAME,\n" +
                "       cte.ZONE_CODE,\n" +
                "       cte.col10A,\n" +
                "       cte.col12A,\n" +
                "       cte1.col10B,\n" +
                "       cte1.col12B,\n" +
                "       (cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B) AS numerator,\n" +
                "       (cte.col10A + cte1.col10B) AS denominator,\n" +
                "       CASE \n" +
                "           WHEN (cte.col10A + cte1.col10B) = 0 THEN NULL \n" +
                "           ELSE (((cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B)) / (cte.col10A + cte1.col10B)) \n" +
                "       END AS total_score \n" +
                "FROM cte\n" +
                "INNER JOIN cte1 ON cte.ZONE_CODE = cte1.ZONE_CODE\n" +
                "ORDER BY total_score ASC;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst11b_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "WITH cte AS (\n" +
                "    SELECT \n" +
                "        zc.ZONE_NAME, \n" +
                "        zc.ZONE_CODE, \n" +
                "        cc.COMM_NAME, \n" +
                "        cc.COMM_CODE, \n" +
                "        (11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n" +
                "        (11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A \n" +
                "    FROM \n" +
                "        mis_dla_gst_lgl_1a AS 11a\n" +
                "    LEFT JOIN \n" +
                "        mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN \n" +
                "        mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                "    WHERE \n" +
                "        11a.MM_YYYY = '" + month_date + "'\n" +
                "        AND FORUM_CODE = 6 \n" +
                "        AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                "), \n" +
                "cte1 AS (\n" +
                "    SELECT \n" +
                "        zc.ZONE_NAME, \n" +
                "        zc.ZONE_CODE, \n" +
                "        cc.COMM_NAME, \n" +
                "        cc.COMM_CODE, \n" +
                "        (11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, \n" +
                "        (11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B \n" +
                "    FROM \n" +
                "        mis_dla_gst_lgl_1b AS 11b\n" +
                "    LEFT JOIN \n" +
                "        mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN \n" +
                "        mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                "    WHERE \n" +
                "        11b.MM_YYYY = '" + month_date + "'\n" +
                "        AND FORUM_CODE = 6 \n" +
                "        AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                ") \n" +
                "SELECT \n" +
                "    cte.ZONE_NAME, \n" +
                "    cte.ZONE_CODE, \n" +
                "    cte.COMM_NAME, \n" +
                "    cte.COMM_CODE, \n" +
                "    cte.col10A, \n" +
                "    cte.col12A, \n" +
                "    cte1.col10B, \n" +
                "    cte1.col12B, \n" +
                "    (cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B) AS numerator, \n" +
                "    (cte.col10A + cte1.col10B) AS denominator, \n" +
                "    (((cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B)) / (cte.col10A + cte1.col10B)) AS total_score \n" +
                "FROM \n" +
                "    cte \n" +
                "INNER JOIN \n" +
                "    cte1 ON cte.COMM_CODE = cte1.COMM_CODE\n" +
                "    order by total_score asc;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst11b_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "WITH cte AS (\n" +
                "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE,\n" +
                "           (11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A,\n" +
                "           (11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A\n" +
                "    FROM mis_dla_gst_lgl_1a AS 11a\n" +
                "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                "    WHERE 11a.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6\n" +
                "),\n" +
                "cte1 AS (\n" +
                "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE,\n" +
                "           (11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B,\n" +
                "           (11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B\n" +
                "    FROM mis_dla_gst_lgl_1b AS 11b\n" +
                "    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                "    WHERE 11b.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6\n" +
                ")\n" +
                "SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_NAME, cte.COMM_CODE, \n" +
                "       cte.col10A, cte.col12A, cte1.col10B, cte1.col12B,\n" +
                "       (cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B) AS numerator,\n" +
                "       (cte.col10A + cte1.col10B) AS denominator,\n" +
                "       (((cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B)) / (cte.col10A + cte1.col10B)) AS total_score\n" +
                "FROM cte\n" +
                "INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE\n" +
                "order by total_score asc;\n";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst11c_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "WITH cte AS (\n" +
                "    SELECT zc.ZONE_NAME, zc.ZONE_CODE,\n" +
                "           SUM(11a.DEPARTMENT_DISPOSAL_NO + 11a.TAXPAYER_DISPOSAL_NO) AS col10\n" +
                "    FROM mis_dla_gst_lgl_1 AS 11a\n" +
                "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                "    WHERE 11a.MM_YYYY = '" + month_date + "' AND 11a.FORUM_CODE = 7\n" +
                "    GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
                "),\n" +
                "cte1 AS (\n" +
                "    SELECT zc.ZONE_NAME, zc.ZONE_CODE,\n" +
                "           SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col4\n" +
                "    FROM mis_dla_gst_lgl_1 AS 11a\n" +
                "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                "    WHERE 11a.MM_YYYY = '" + prev_month_new + "' AND 11a.FORUM_CODE = 7\n" +
                "    GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
                ")\n" +
                "SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.col10, cte1.col4,\n" +
                "       (cte.col10 / NULLIF(cte1.col4, 0)) AS total_score\n" +
                "FROM cte\n" +
                "INNER JOIN cte1 ON cte.ZONE_CODE = cte1.ZONE_CODE\n" +
                "order by total_score desc;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst11c_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH cte AS (\n" +
                "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE,\n" +
                "           (DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS col10\n" +
                "    FROM mis_dla_gst_lgl_1 AS a\n" +
                "    LEFT JOIN mis_gst_commcode AS cc ON a.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                "    WHERE a.MM_YYYY = '" + month_date + "' AND a.FORUM_CODE = 7 AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                "), \n" +
                "cte1 AS (\n" +
                "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE,\n" +
                "           (DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4\n" +
                "    FROM mis_dla_gst_lgl_1 AS a\n" +
                "    LEFT JOIN mis_gst_commcode AS cc ON a.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                "    WHERE a.MM_YYYY = '" + prev_month_new + "' AND a.FORUM_CODE = 7 AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                ") \n" +
                "SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_NAME, cte.COMM_CODE, cte.col10, cte1.col4,\n" +
                "       (cte.col10 / cte1.col4) AS total_score\n" +
                "FROM cte\n" +
                "INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE\n" +
                "order by total_score desc;\n";
        return queryGst14aa;
    }
    public String QueryFor_gst11c_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH cte AS (\n" +
                "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE,\n" +
                "           (DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS col10\n" +
                "    FROM mis_dla_gst_lgl_1 AS 11a\n" +
                "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                "    WHERE 11a.MM_YYYY =  '" + month_date + "' AND 11a.FORUM_CODE = 7\n" +
                "),\n" +
                "cte1 AS (\n" +
                "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE,\n" +
                "           (DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4\n" +
                "    FROM mis_dla_gst_lgl_1 AS 11a\n" +
                "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                "    WHERE 11a.MM_YYYY = '" + prev_month_new + "'AND 11a.FORUM_CODE = 7\n" +
                ")\n" +
                "SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_NAME, cte.COMM_CODE, cte.col10, cte1.col4,\n" +
                "       (cte.col10 / cte1.col4) AS total_score\n" +
                "FROM cte\n" +
                "INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE\n" +
                "order by total_score desc;\n";
        return queryGst14aa;
    }
    // ********************************************************************************************************************************
    public String QueryFor_gst11d_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH cte AS (  SELECT zc.ZONE_NAME,zc.ZONE_CODE,\n" +
                "SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A,\n" +
                "SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A\n" +
                "FROM mis_dla_gst_lgl_1a AS 11a\n" +
                "LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                "WHERE 11a.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 7\n" +
                "GROUP BY zc.ZONE_CODE),\n" +
                "cte1 AS (\n" +
                "SELECT zc.ZONE_NAME,zc.ZONE_CODE,\n" +
                "SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B,\n" +
                "SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B\n" +
                "FROM mis_dla_gst_lgl_1b AS 11b\n" +
                "LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                "WHERE\n" +
                "11b.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 7\n" +
                "GROUP BY zc.ZONE_CODE)\n" +
                "SELECT cte.ZONE_NAME,cte.ZONE_CODE,cte.col10A,cte.col12A,cte1.col10B,cte1.col12B,\n" +
                "(cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B) AS numerator,\n" +
                "(cte.col10A + cte1.col10B) AS denominator,\n" +
                "(((cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B)) / (cte.col10A + cte1.col10B)) AS total_score\n" +
                "FROM cte\n" +
                "INNER JOIN cte1 ON cte.ZONE_CODE = cte1.ZONE_CODE\n" +
                "order by total_score asc ;";
        return queryGst14aa;
    }
    public String QueryFor_gst11d_CommissonaryWise(String month_date, String zone_code){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa="WITH cte AS (\n" +
                "\tSELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME,cc.COMM_CODE, \n" +
                "\t(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n" +
                "\t(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A \n" +
                "\tFROM mis_dla_gst_lgl_1a AS 11a \n" +
                "\tLEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "\tLEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "\tWHERE 11a.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 7 and zc.ZONE_CODE = '" + zone_code + "'\n" +
                "), \n" +
                "cte1 AS ( \n" +
                "         SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME,cc.COMM_CODE, \n" +
                "         (11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, \n" +
                "\t\t(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B \n" +
                "          FROM mis_dla_gst_lgl_1b AS 11b \n" +
                "          LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n" +
                "          LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "          WHERE 11b.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 7 and zc.ZONE_CODE = '" + zone_code + "'\n" +
                ") \n" +
                "     SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_NAME,cte.COMM_CODE, cte.col10A, cte.col12A, cte1.col10B, cte1.col12B, \n" +
                "     (cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B) AS numerator, \n" +
                "      (cte.col10A + cte1.col10B) AS denominator, \n" +
                "\t(((cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B)) / (cte.col10A + cte1.col10B)) AS total_score \n" +
                "FROM cte INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE\n" +
                "order by total_score ;";
        return queryGst14aa;
    }
    public String QueryFor_gst11d_AllCommissonaryWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String queryGst14aa= "WITH cte AS (\n" +
                "\tSELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME,cc.COMM_CODE, \n" +
                "\t(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n" +
                "\t(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A \n" +
                "\tFROM mis_dla_gst_lgl_1a AS 11a \n" +
                "\tLEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                "\tLEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "\tWHERE 11a.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 7 \n" +
                "), \n" +
                "cte1 AS ( \n" +
                "         SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME,cc.COMM_CODE, \n" +
                "         (11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, \n" +
                "\t\t(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B \n" +
                "          FROM mis_dla_gst_lgl_1b AS 11b \n" +
                "          LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n" +
                "          LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                "          WHERE 11b.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 7 \n" +
                ") \n" +
                "     SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_NAME,cte.COMM_CODE, cte.col10A, cte.col12A, cte1.col10B, cte1.col12B, \n" +
                "     (cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B) AS numerator, \n" +
                "      (cte.col10A + cte1.col10B) AS denominator, \n" +
                "\t(((cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B)) / (cte.col10A + cte1.col10B)) AS total_score \n" +
                "FROM cte INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE\n" +
                "order by total_score ;";

        return queryGst14aa;
    }
}
