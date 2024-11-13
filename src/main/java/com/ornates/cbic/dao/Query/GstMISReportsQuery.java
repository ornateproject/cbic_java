package com.ornates.cbic.dao.Query;

import com.ornates.cbic.service.DateCalculate;

public class GstMISReportsQuery {
    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Registration__1__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_Registration_CurrentMonth_CgstMISReports(String month_date){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter1 ="";
        return queryGstParameter1;
    }
    public String QueryFor_Registration_1_MonthBack_CgstMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter1 ="";
        return queryGstParameter1;
    }
    public String QueryFor_Registration_2_MonthBack_CgstMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter1 ="";
        return queryGstParameter1;
    }
    public String QueryFor_Registration_3_MonthBack_CgstMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter1 ="";
        return queryGstParameter1;
    }
    public String QueryFor_Registration_4_MonthBack_CgstMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter1 ="";
        return queryGstParameter1;
    }
    public String QueryFor_Registration_5_MonthBack_CgstMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter1 ="";
        return queryGstParameter1;
    }
    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=ReturnFiling__2__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_ReturnFiling_CurrentMonth_CgstMISReports(String month_date){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter2 ="WITH score_calculation AS (\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, SUM(14c.GSTR_3BM_F) AS col3,\n"
                + "        (SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / NULLIF(SUM(14c.GSTR_3BM_F), 0)) * 100 AS total_score\n"
                + "    FROM mis_gst_commcode AS cc \n"
                + "    RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "    WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY zc.ZONE_NAME,cc.ZONE_CODE\n"
                + ")\n"
                + "SELECT \n"
                + "    DENSE_RANK() OVER (ORDER BY total_score ASC) AS z_rank,\n"
                + "    ZONE_NAME, ZONE_CODE, col21, col3,total_score\n"
                + "FROM score_calculation\n"
                + "ORDER BY total_score ASC;";
        return queryGstParameter2;
    }
    public String QueryFor_ReturnFiling_1_MonthBack_CgstMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter2 ="WITH score_calculation AS (\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, SUM(14c.GSTR_3BM_F) AS col3,\n"
                + "        (SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / NULLIF(SUM(14c.GSTR_3BM_F), 0)) * 100 AS total_score\n"
                + "    FROM mis_gst_commcode AS cc \n"
                + "    RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "    WHERE 14c.MM_YYYY = '" + MonthBack_1 + "' GROUP BY zc.ZONE_NAME,cc.ZONE_CODE\n"
                + ")\n"
                + "SELECT \n"
                + "    DENSE_RANK() OVER (ORDER BY total_score ASC) AS z_rank,\n"
                + "    ZONE_NAME, ZONE_CODE, col21, col3,total_score\n"
                + "FROM score_calculation\n"
                + "ORDER BY total_score ASC;";
        return queryGstParameter2;
    }
    public String QueryFor_ReturnFiling_2_MonthBack_CgstMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter2 ="WITH score_calculation AS (\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, SUM(14c.GSTR_3BM_F) AS col3,\n"
                + "        (SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / NULLIF(SUM(14c.GSTR_3BM_F), 0)) * 100 AS total_score\n"
                + "    FROM mis_gst_commcode AS cc \n"
                + "    RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "    WHERE 14c.MM_YYYY = '" + MonthBack_2 + "' GROUP BY zc.ZONE_NAME,cc.ZONE_CODE\n"
                + ")\n"
                + "SELECT \n"
                + "    DENSE_RANK() OVER (ORDER BY total_score ASC) AS z_rank,\n"
                + "    ZONE_NAME, ZONE_CODE, col21, col3,total_score\n"
                + "FROM score_calculation\n"
                + "ORDER BY total_score ASC;";
        return queryGstParameter2;
    }
    public String QueryFor_ReturnFiling_3_MonthBack_CgstMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter2 ="WITH score_calculation AS (\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, SUM(14c.GSTR_3BM_F) AS col3,\n"
                + "        (SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / NULLIF(SUM(14c.GSTR_3BM_F), 0)) * 100 AS total_score\n"
                + "    FROM mis_gst_commcode AS cc \n"
                + "    RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "    WHERE 14c.MM_YYYY = '" + MonthBack_3 + "' GROUP BY zc.ZONE_NAME,cc.ZONE_CODE\n"
                + ")\n"
                + "SELECT \n"
                + "    DENSE_RANK() OVER (ORDER BY total_score ASC) AS z_rank,\n"
                + "    ZONE_NAME, ZONE_CODE, col21, col3,total_score\n"
                + "FROM score_calculation\n"
                + "ORDER BY total_score ASC;";
        return queryGstParameter2;
    }
    public String QueryFor_ReturnFiling_4_MonthBack_CgstMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter2 ="WITH score_calculation AS (\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, SUM(14c.GSTR_3BM_F) AS col3,\n"
                + "        (SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / NULLIF(SUM(14c.GSTR_3BM_F), 0)) * 100 AS total_score\n"
                + "    FROM mis_gst_commcode AS cc \n"
                + "    RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "    WHERE 14c.MM_YYYY = '" + MonthBack_4 + "' GROUP BY zc.ZONE_NAME,cc.ZONE_CODE\n"
                + ")\n"
                + "SELECT \n"
                + "    DENSE_RANK() OVER (ORDER BY total_score ASC) AS z_rank,\n"
                + "    ZONE_NAME, ZONE_CODE, col21, col3,total_score\n"
                + "FROM score_calculation\n"
                + "ORDER BY total_score ASC;";
        return queryGstParameter2;
    }
    public String QueryFor_ReturnFiling_5_MonthBack_CgstMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter2 ="WITH score_calculation AS (\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) AS col21, SUM(14c.GSTR_3BM_F) AS col3,\n"
                + "        (SUM(14c.GSTR_3BM_F - 14c.GSTR_3BM_D) / NULLIF(SUM(14c.GSTR_3BM_F), 0)) * 100 AS total_score\n"
                + "    FROM mis_gst_commcode AS cc \n"
                + "    RIGHT JOIN mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n"
                + "    WHERE 14c.MM_YYYY = '" + MonthBack_5 + "' GROUP BY zc.ZONE_NAME,cc.ZONE_CODE\n"
                + ")\n"
                + "SELECT \n"
                + "    DENSE_RANK() OVER (ORDER BY total_score ASC) AS z_rank,\n"
                + "    ZONE_NAME, ZONE_CODE, col21, col3,total_score\n"
                + "FROM score_calculation\n"
                + "ORDER BY total_score ASC;";
        return queryGstParameter2;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Scrutiny__3__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    public String QueryFor_Scrutiny_CurrentMonth_CgstMISReports(String month_date){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter3 ="";
        return queryGstParameter3;
    }
    public String QueryFor_Scrutiny_1_MonthBack_CgstMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter3 ="";
        return queryGstParameter3;
    }
    public String QueryFor_Scrutiny_2_MonthBack_CgstMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter3 ="";
        return queryGstParameter3;
    }
    public String QueryFor_Scrutiny_3_MonthBack_CgstMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter3 ="";
        return queryGstParameter3;
    }
    public String QueryFor_Scrutiny_4_MonthBack_CgstMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter3 ="";
        return queryGstParameter3;
    }
    public String QueryFor_Scrutiny_5_MonthBack_CgstMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter3 ="";
        return queryGstParameter3;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Investigation__4__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_Investigation_CurrentMonth_CgstMISReports(String month_date){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter4 ="";
        return queryGstParameter4;
    }
    public String QueryFor_Investigation_1_MonthBack_CgstMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter4 ="";
        return queryGstParameter4;
    }
    public String QueryFor_Investigation_2_MonthBack_CgstMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter4 ="";
        return queryGstParameter4;
    }
    public String QueryFor_Investigation_3_MonthBack_CgstMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter4 ="";
        return queryGstParameter4;
    }
    public String QueryFor_Investigation_4_MonthBack_CgstMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter4 ="";
        return queryGstParameter4;
    }
    public String QueryFor_Investigation_5_MonthBack_CgstMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter4 ="";
        return queryGstParameter4;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Adjudication__5__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_Adjudication_CurrentMonth_CgstMISReports(String month_date){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        String next_month_new = DateCalculate.getNextMonth(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter5 ="WITH ranked_data AS (\n" +
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
        return queryGstParameter5;
    }
    public String QueryFor_Adjudication_1_MonthBack_CgstMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter5 ="WITH ranked_data AS (\n" +
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
                "    WHERE 14c.MM_YYYY = '" + MonthBack_1 + "'\n" +
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
                "    WHERE 14c.MM_YYYY = '" + MonthBack_1 + "'\n" +
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
                "    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
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
        return queryGstParameter5;
    }
    public String QueryFor_Adjudication_2_MonthBack_CgstMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is next month for this query
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter5 ="WITH ranked_data AS (\n" +
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
                "    WHERE 14c.MM_YYYY = '" + MonthBack_2 + "'\n" +
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
                "    WHERE 14c.MM_YYYY = '" + MonthBack_2 + "'\n" +
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
                "    WHERE 14c.MM_YYYY = '" + MonthBack_1 + "'\n" +
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
        return queryGstParameter5;
    }
    public String QueryFor_Adjudication_3_MonthBack_CgstMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is next month for this query
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter5 ="WITH ranked_data AS (\n" +
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
                "    WHERE 14c.MM_YYYY = '" + MonthBack_3 + "'\n" +
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
                "    WHERE 14c.MM_YYYY = '" + MonthBack_3 + "'\n" +
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
                "    WHERE 14c.MM_YYYY = '" + MonthBack_2 + "'\n" +
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
        return queryGstParameter5;
    }
    public String QueryFor_Adjudication_4_MonthBack_CgstMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is mext month for this query
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter5 ="WITH ranked_data AS (\n" +
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
                "    WHERE 14c.MM_YYYY = '" + MonthBack_4 + "'\n" +
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
                "    WHERE 14c.MM_YYYY = '" + MonthBack_4 + "'\n" +
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
                "    WHERE 14c.MM_YYYY = '" + MonthBack_3 + "'\n" +
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
        return queryGstParameter5;
    }
    public String QueryFor_Adjudication_5_MonthBack_CgstMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is mext month for this query
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter5 ="WITH ranked_data AS (\n" +
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
                "    WHERE 14c.MM_YYYY = '" + MonthBack_5 + "'\n" +
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
                "    WHERE 14c.MM_YYYY = '" + MonthBack_5 + "'\n" +
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
                "    WHERE 14c.MM_YYYY = '" + MonthBack_4 + "'\n" +
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
        return queryGstParameter5;
    }
    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Adjudication_Legacy__6__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_Adjudication_Legacy_CurrentMonth_CgstMISReports(String month_date){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter6 ="";
        return queryGstParameter6;
    }
    public String QueryFor_Adjudication_Legacy_1_MonthBack_CgstMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter6 ="";
        return queryGstParameter6;
    }
    public String QueryFor_Adjudication_Legacy_2_MonthBack_CgstMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter6 ="";
        return queryGstParameter6;
    }
    public String QueryFor_Adjudication_Legacy_3_MonthBack_CgstMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter6 ="";
        return queryGstParameter6;
    }
    public String QueryFor_Adjudication_Legacy_4_MonthBack_CgstMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter6 ="";
        return queryGstParameter6;
    }
    public String QueryFor_Adjudication_Legacy_5_MonthBack_CgstMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter6 ="";
        return queryGstParameter6;
    }

   // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Refunds__7__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
   public String QueryFor_Refunds_CurrentMonth_CgstMISReports(String month_date){
       // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
       String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
       //  in this query normal month date is current month and MonthBack_1 is a previous month
       String queryGstParameter7 ="";
       return queryGstParameter7;
   }
    public String QueryFor_Refunds_1_MonthBack_CgstMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter7 ="";
        return queryGstParameter7;
    }
    public String QueryFor_Refunds_2_MonthBack_CgstMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter7 ="";
        return queryGstParameter7;
    }
    public String QueryFor_Refunds_3_MonthBack_CgstMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter7 ="";
        return queryGstParameter7;
    }
    public String QueryFor_Refunds_4_MonthBack_CgstMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter7 ="";
        return queryGstParameter7;
    }
    public String QueryFor_Refunds_5_MonthBack_CgstMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter7 ="";
        return queryGstParameter7;
    }

//    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=RecoveryOfArrears__8__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
//    public String QueryFor_Scrutiny_CurrentMonth_CgstMISReports(String month_date){
//        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
//        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
//        //  in this query normal month date is current month and MonthBack_1 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_1_MonthBack_CgstMISReports(String month_date){
//        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
//        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
//        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_2_MonthBack_CgstMISReports(String month_date){
//        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
//        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
//        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_3_MonthBack_CgstMISReports(String month_date){
//        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
//        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
//        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_4_MonthBack_CgstMISReports(String month_date){
//        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
//        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
//        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_5_MonthBack_CgstMISReports(String month_date){
//        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
//        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
//        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//
//    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Arrests & prosecution__9__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
//
//    public String QueryFor_Scrutiny_CurrentMonth_CgstMISReports(String month_date){
//        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
//        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
//        //  in this query normal month date is current month and MonthBack_1 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_1_MonthBack_CgstMISReports(String month_date){
//        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
//        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
//        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_2_MonthBack_CgstMISReports(String month_date){
//        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
//        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
//        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_3_MonthBack_CgstMISReports(String month_date){
//        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
//        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
//        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_4_MonthBack_CgstMISReports(String month_date){
//        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
//        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
//        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_5_MonthBack_CgstMISReports(String month_date){
//        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
//        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
//        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Audit__10__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
//    public String QueryFor_Scrutiny_CurrentMonth_CgstMISReports(String month_date){
//        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
//        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
//        //  in this query normal month date is current month and MonthBack_1 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_1_MonthBack_CgstMISReports(String month_date){
//        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
//        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
//        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_2_MonthBack_CgstMISReports(String month_date){
//        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
//        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
//        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_3_MonthBack_CgstMISReports(String month_date){
//        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
//        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
//        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_4_MonthBack_CgstMISReports(String month_date){
//        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
//        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
//        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_5_MonthBack_CgstMISReports(String month_date){
//        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
//        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
//        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//
//    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Appeals=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
//
//    public String QueryFor_Scrutiny_CurrentMonth_CgstMISReports(String month_date){
//        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
//        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
//        //  in this query normal month date is current month and MonthBack_1 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_1_MonthBack_CgstMISReports(String month_date){
//        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
//        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
//        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_2_MonthBack_CgstMISReports(String month_date){
//        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
//        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
//        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_3_MonthBack_CgstMISReports(String month_date){
//        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
//        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
//        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_4_MonthBack_CgstMISReports(String month_date){
//        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
//        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
//        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }
//    public String QueryFor_Scrutiny_5_MonthBack_CgstMISReports(String month_date){
//        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
//        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
//        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
//        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
//        String queryGstParameter3 ="";
//        return queryGstParameter3;
//    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=END=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

}
