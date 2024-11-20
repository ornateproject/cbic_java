package com.ornates.cbic.dao.Query;

import com.ornates.cbic.service.DateCalculate;

public class CustomMISReportsQuery {

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Timely payment of  Refunds__1__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_TimelyPaymentOfRefunds_CurrentMonth_CustomMISReports(String month_date){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter1 = "SELECT \n"
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
        return queryGstParameter1;
    }
    public String QueryFor_TimelyPaymentOfRefunds_1_MonthBack_CustomMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter1 ="SELECT \n"
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
                + "    c14.MM_YYYY = '" + MonthBack_1 + "'  \n"
                + "GROUP BY \n"
                + "    zc.ZONE_CODE, zc.ZONE_NAME, cc.ZONE_CODE \n"
                + "ORDER BY \n"
                + "    total_score ASC \n"
                + "LIMIT 0, 1000;\n"
                + "";
        return queryGstParameter1;
    }
    public String QueryFor_TimelyPaymentOfRefunds_2_MonthBack_CustomMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter1 ="SELECT \n"
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
                + "    c14.MM_YYYY = '" + MonthBack_2 + "'  \n"
                + "GROUP BY \n"
                + "    zc.ZONE_CODE, zc.ZONE_NAME, cc.ZONE_CODE \n"
                + "ORDER BY \n"
                + "    total_score ASC \n"
                + "LIMIT 0, 1000;\n"
                + "";
        return queryGstParameter1;
    }
    public String QueryFor_TimelyPaymentOfRefunds_3_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter1 ="SELECT \n"
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
                + "    c14.MM_YYYY = '" + MonthBack_3 + "'  \n"
                + "GROUP BY \n"
                + "    zc.ZONE_CODE, zc.ZONE_NAME, cc.ZONE_CODE \n"
                + "ORDER BY \n"
                + "    total_score ASC \n"
                + "LIMIT 0, 1000;\n"
                + "";
        return queryGstParameter1;
    }
    public String QueryFor_TimelyPaymentOfRefunds_4_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter1 ="SELECT \n"
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
                + "    c14.MM_YYYY = '" + MonthBack_4 + "'  \n"
                + "GROUP BY \n"
                + "    zc.ZONE_CODE, zc.ZONE_NAME, cc.ZONE_CODE \n"
                + "ORDER BY \n"
                + "    total_score ASC \n"
                + "LIMIT 0, 1000;\n"
                + "";
        return queryGstParameter1;
    }
    public String QueryFor_TimelyPaymentOfRefunds_5_MonthBack_CustomMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter1 ="SELECT \n"
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
                + "    c14.MM_YYYY = '" + MonthBack_5 + "'  \n"
                + "GROUP BY \n"
                + "    zc.ZONE_CODE, zc.ZONE_NAME, cc.ZONE_CODE \n"
                + "ORDER BY \n"
                + "    total_score ASC \n"
                + "LIMIT 0, 1000;\n"
                + "";
        return queryGstParameter1;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Management of Export Obligation(EPCG)__2__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_ManagementOfExportObligation_EPCG_CurrentMonth_CustomMISReports(String month_date){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter2 ="";
        return queryGstParameter2;
    }
    public String QueryFor_ManagementOfExportObligation_EPCG_1_MonthBack_CustomMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter2 ="";
        return queryGstParameter2;
    }
    public String QueryFor_ManagementOfExportObligation_EPCG_2_MonthBack_CustomMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter2 ="";
        return queryGstParameter2;
    }
    public String QueryFor_ManagementOfExportObligation_EPCG_3_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter2 ="";
        return queryGstParameter2;
    }
    public String QueryFor_ManagementOfExportObligation_EPCG_4_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter2 ="";
        return queryGstParameter2;
    }
    public String QueryFor_ManagementOfExportObligation_EPCG_5_MonthBack_CustomMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter2 ="";
        return queryGstParameter2;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Management of Export Obligation(AA)__3__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_ManagementOfExportObligation_AA_CurrentMonth_CustomMISReports(String month_date){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter3 ="";
        return queryGstParameter3;
    }
    public String QueryFor_ManagementOfExportObligation_AA_1_MonthBack_CustomMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter3 ="";
        return queryGstParameter3;
    }
    public String QueryFor_ManagementOfExportObligation_AA_2_MonthBack_CustomMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter3 ="";
        return queryGstParameter3;
    }
    public String QueryFor_ManagementOfExportObligation_AA_3_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter3 ="";
        return queryGstParameter3;
    }
    public String QueryFor_ManagementOfExportObligation_AA_4_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter3 ="";
        return queryGstParameter3;
    }
    public String QueryFor_ManagementOfExportObligation_AA_5_MonthBack_CustomMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter3 ="";
        return queryGstParameter3;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Disposal / Pendency of Provisional Assessments__4__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_Disposal_PendencyOfProvisionalAssessments_CurrentMonth_CustomMISReports(String month_date){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter4 ="";
        return queryGstParameter4;
    }
    public String QueryFor_Disposal_PendencyOfProvisionalAssessments_1_MonthBack_CustomMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter4 ="";
        return queryGstParameter4;
    }
    public String QueryFor_Disposal_PendencyOfProvisionalAssessments_2_MonthBack_CustomMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter4 ="";
        return queryGstParameter4;
    }
    public String QueryFor_Disposal_PendencyOfProvisionalAssessments_3_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter4 ="";
        return queryGstParameter4;
    }
    public String QueryFor_Disposal_PendencyOfProvisionalAssessments_4_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter4 ="";
        return queryGstParameter4;
    }
    public String QueryFor_Disposal_PendencyOfProvisionalAssessments_5_MonthBack_CustomMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter4 ="";
        return queryGstParameter4;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Adjudication__5__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_Adjudication_CurrentMonth_CustomMISReports(String month_date){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter5 ="WITH cte_5a AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO) AS col5a\n" +
                "FROM Mis_DGI_CUS_1A AS 14c\n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),cte_3a AS (SELECT zc.ZONE_NAME,cc.ZONE_CODE,SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO) AS col3a\n" +
                "FROM Mis_DGI_CUS_1A AS 14c\n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = DATE_SUB('" + month_date + "', INTERVAL 1 MONTH)\n" +
                "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),median_cte AS (\n" +
                "SELECT AVG(col5a) AS cus5a_median\n" +
                "FROM (\n" +
                "\t\tSELECT col5a, ROW_NUMBER() OVER (ORDER BY col5a) AS rn, COUNT(*) OVER () AS cnt\n" +
                "\t\t\tFROM cte_5a\n" +
                "\t\t\t) AS temp\n" +
                "\t\t\tWHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "),\n" +
                "cte_5b AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
                "SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) AS col7d,\n" +
                "SUM(14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO) AS col6a,\n" +
                "(SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) / \n" +
                "SUM(14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO)) * 100 AS total_score5b\n" +
                "FROM mis_dgi_cus_1A AS 14c\n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "cte_5c AS (SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col7,SUM(14c.YEAR_1) AS col9,\n" +
                "((SUM(14c.CLOSING_NO) - SUM(14c.YEAR_1)) / SUM(14c.CLOSING_NO)) * 100 AS total_score5c\n" +
                "FROM mis_gst_commcode AS cc\n" +
                "RIGHT JOIN mis_dgi_cus_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                ")\n" +
                "SELECT cte_5a.ZONE_NAME, cte_5a.ZONE_CODE, cte_5a.col5a, cte_3a.col3a,median_cte.cus5a_median,\n" +
                "(cte_5a.col5a / cte_3a.col3a) * 100 AS total_score5a,\n" +
                "cte_5b.col7d, cte_5b.col6a, cte_5b.total_score5b,cte_5c.col7, cte_5c.col9, cte_5c.total_score5c\n" +
                "FROM cte_5a\n" +
                "JOIN cte_3a ON cte_5a.ZONE_CODE = cte_3a.ZONE_CODE\n" +
                "JOIN cte_5b ON cte_5a.ZONE_CODE = cte_5b.ZONE_CODE\n" +
                "JOIN cte_5c ON cte_5a.ZONE_CODE = cte_5c.ZONE_CODE\n" +
                "CROSS JOIN median_cte\n" +
                "ORDER BY total_score5a DESC, \n" +
                "total_score5b ASC, total_score5c ASC;";
        return queryGstParameter5;
    }
    public String QueryFor_Adjudication_1_MonthBack_CustomMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter5 ="WITH cte_5a AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO) AS col5a\n" +
                "FROM Mis_DGI_CUS_1A AS 14c\n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_1 + "'\n" +
                "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),cte_3a AS (SELECT zc.ZONE_NAME,cc.ZONE_CODE,SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO) AS col3a\n" +
                "FROM Mis_DGI_CUS_1A AS 14c\n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = DATE_SUB('" + MonthBack_1 + "', INTERVAL 1 MONTH)\n" +
                "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),median_cte AS (\n" +
                "SELECT AVG(col5a) AS cus5a_median\n" +
                "FROM (\n" +
                "\t\tSELECT col5a, ROW_NUMBER() OVER (ORDER BY col5a) AS rn, COUNT(*) OVER () AS cnt\n" +
                "\t\t\tFROM cte_5a\n" +
                "\t\t\t) AS temp\n" +
                "\t\t\tWHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "),\n" +
                "cte_5b AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
                "SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) AS col7d,\n" +
                "SUM(14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO) AS col6a,\n" +
                "(SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) / \n" +
                "SUM(14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO)) * 100 AS total_score5b\n" +
                "FROM mis_dgi_cus_1A AS 14c\n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_1 + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "cte_5c AS (SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col7,SUM(14c.YEAR_1) AS col9,\n" +
                "((SUM(14c.CLOSING_NO) - SUM(14c.YEAR_1)) / SUM(14c.CLOSING_NO)) * 100 AS total_score5c\n" +
                "FROM mis_gst_commcode AS cc\n" +
                "RIGHT JOIN mis_dgi_cus_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_1 + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                ")\n" +
                "SELECT cte_5a.ZONE_NAME, cte_5a.ZONE_CODE, cte_5a.col5a, cte_3a.col3a,median_cte.cus5a_median,\n" +
                "(cte_5a.col5a / cte_3a.col3a) * 100 AS total_score5a,\n" +
                "cte_5b.col7d, cte_5b.col6a, cte_5b.total_score5b,cte_5c.col7, cte_5c.col9, cte_5c.total_score5c\n" +
                "FROM cte_5a\n" +
                "JOIN cte_3a ON cte_5a.ZONE_CODE = cte_3a.ZONE_CODE\n" +
                "JOIN cte_5b ON cte_5a.ZONE_CODE = cte_5b.ZONE_CODE\n" +
                "JOIN cte_5c ON cte_5a.ZONE_CODE = cte_5c.ZONE_CODE\n" +
                "CROSS JOIN median_cte\n" +
                "ORDER BY total_score5a DESC, \n" +
                "total_score5b ASC, total_score5c ASC;";
        return queryGstParameter5;
    }
    public String QueryFor_Adjudication_2_MonthBack_CustomMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter5 ="WITH cte_5a AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO) AS col5a\n" +
                "FROM Mis_DGI_CUS_1A AS 14c\n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_2 + "'\n" +
                "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),cte_3a AS (SELECT zc.ZONE_NAME,cc.ZONE_CODE,SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO) AS col3a\n" +
                "FROM Mis_DGI_CUS_1A AS 14c\n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = DATE_SUB('" + MonthBack_2 + "', INTERVAL 1 MONTH)\n" +
                "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),median_cte AS (\n" +
                "SELECT AVG(col5a) AS cus5a_median\n" +
                "FROM (\n" +
                "\t\tSELECT col5a, ROW_NUMBER() OVER (ORDER BY col5a) AS rn, COUNT(*) OVER () AS cnt\n" +
                "\t\t\tFROM cte_5a\n" +
                "\t\t\t) AS temp\n" +
                "\t\t\tWHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "),\n" +
                "cte_5b AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
                "SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) AS col7d,\n" +
                "SUM(14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO) AS col6a,\n" +
                "(SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) / \n" +
                "SUM(14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO)) * 100 AS total_score5b\n" +
                "FROM mis_dgi_cus_1A AS 14c\n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_2 + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "cte_5c AS (SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col7,SUM(14c.YEAR_1) AS col9,\n" +
                "((SUM(14c.CLOSING_NO) - SUM(14c.YEAR_1)) / SUM(14c.CLOSING_NO)) * 100 AS total_score5c\n" +
                "FROM mis_gst_commcode AS cc\n" +
                "RIGHT JOIN mis_dgi_cus_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_2 + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                ")\n" +
                "SELECT cte_5a.ZONE_NAME, cte_5a.ZONE_CODE, cte_5a.col5a, cte_3a.col3a,median_cte.cus5a_median,\n" +
                "(cte_5a.col5a / cte_3a.col3a) * 100 AS total_score5a,\n" +
                "cte_5b.col7d, cte_5b.col6a, cte_5b.total_score5b,cte_5c.col7, cte_5c.col9, cte_5c.total_score5c\n" +
                "FROM cte_5a\n" +
                "JOIN cte_3a ON cte_5a.ZONE_CODE = cte_3a.ZONE_CODE\n" +
                "JOIN cte_5b ON cte_5a.ZONE_CODE = cte_5b.ZONE_CODE\n" +
                "JOIN cte_5c ON cte_5a.ZONE_CODE = cte_5c.ZONE_CODE\n" +
                "CROSS JOIN median_cte\n" +
                "ORDER BY total_score5a DESC, \n" +
                "total_score5b ASC, total_score5c ASC;";
        return queryGstParameter5;
    }
    public String QueryFor_Adjudication_3_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter5 ="WITH cte_5a AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO) AS col5a\n" +
                "FROM Mis_DGI_CUS_1A AS 14c\n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_3 + "'\n" +
                "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),cte_3a AS (SELECT zc.ZONE_NAME,cc.ZONE_CODE,SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO) AS col3a\n" +
                "FROM Mis_DGI_CUS_1A AS 14c\n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = DATE_SUB('" + MonthBack_3 + "', INTERVAL 1 MONTH)\n" +
                "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),median_cte AS (\n" +
                "SELECT AVG(col5a) AS cus5a_median\n" +
                "FROM (\n" +
                "\t\tSELECT col5a, ROW_NUMBER() OVER (ORDER BY col5a) AS rn, COUNT(*) OVER () AS cnt\n" +
                "\t\t\tFROM cte_5a\n" +
                "\t\t\t) AS temp\n" +
                "\t\t\tWHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "),\n" +
                "cte_5b AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
                "SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) AS col7d,\n" +
                "SUM(14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO) AS col6a,\n" +
                "(SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) / \n" +
                "SUM(14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO)) * 100 AS total_score5b\n" +
                "FROM mis_dgi_cus_1A AS 14c\n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_3 + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "cte_5c AS (SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col7,SUM(14c.YEAR_1) AS col9,\n" +
                "((SUM(14c.CLOSING_NO) - SUM(14c.YEAR_1)) / SUM(14c.CLOSING_NO)) * 100 AS total_score5c\n" +
                "FROM mis_gst_commcode AS cc\n" +
                "RIGHT JOIN mis_dgi_cus_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_3 + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                ")\n" +
                "SELECT cte_5a.ZONE_NAME, cte_5a.ZONE_CODE, cte_5a.col5a, cte_3a.col3a,median_cte.cus5a_median,\n" +
                "(cte_5a.col5a / cte_3a.col3a) * 100 AS total_score5a,\n" +
                "cte_5b.col7d, cte_5b.col6a, cte_5b.total_score5b,cte_5c.col7, cte_5c.col9, cte_5c.total_score5c\n" +
                "FROM cte_5a\n" +
                "JOIN cte_3a ON cte_5a.ZONE_CODE = cte_3a.ZONE_CODE\n" +
                "JOIN cte_5b ON cte_5a.ZONE_CODE = cte_5b.ZONE_CODE\n" +
                "JOIN cte_5c ON cte_5a.ZONE_CODE = cte_5c.ZONE_CODE\n" +
                "CROSS JOIN median_cte\n" +
                "ORDER BY total_score5a DESC, \n" +
                "total_score5b ASC, total_score5c ASC;";
        return queryGstParameter5;
    }
    public String QueryFor_Adjudication_4_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter5 ="WITH cte_5a AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO) AS col5a\n" +
                "FROM Mis_DGI_CUS_1A AS 14c\n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_4 + "'\n" +
                "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),cte_3a AS (SELECT zc.ZONE_NAME,cc.ZONE_CODE,SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO) AS col3a\n" +
                "FROM Mis_DGI_CUS_1A AS 14c\n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = DATE_SUB('" + MonthBack_4 + "', INTERVAL 1 MONTH)\n" +
                "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),median_cte AS (\n" +
                "SELECT AVG(col5a) AS cus5a_median\n" +
                "FROM (\n" +
                "\t\tSELECT col5a, ROW_NUMBER() OVER (ORDER BY col5a) AS rn, COUNT(*) OVER () AS cnt\n" +
                "\t\t\tFROM cte_5a\n" +
                "\t\t\t) AS temp\n" +
                "\t\t\tWHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "),\n" +
                "cte_5b AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
                "SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) AS col7d,\n" +
                "SUM(14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO) AS col6a,\n" +
                "(SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) / \n" +
                "SUM(14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO)) * 100 AS total_score5b\n" +
                "FROM mis_dgi_cus_1A AS 14c\n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_4 + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "cte_5c AS (SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col7,SUM(14c.YEAR_1) AS col9,\n" +
                "((SUM(14c.CLOSING_NO) - SUM(14c.YEAR_1)) / SUM(14c.CLOSING_NO)) * 100 AS total_score5c\n" +
                "FROM mis_gst_commcode AS cc\n" +
                "RIGHT JOIN mis_dgi_cus_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_4 + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                ")\n" +
                "SELECT cte_5a.ZONE_NAME, cte_5a.ZONE_CODE, cte_5a.col5a, cte_3a.col3a,median_cte.cus5a_median,\n" +
                "(cte_5a.col5a / cte_3a.col3a) * 100 AS total_score5a,\n" +
                "cte_5b.col7d, cte_5b.col6a, cte_5b.total_score5b,cte_5c.col7, cte_5c.col9, cte_5c.total_score5c\n" +
                "FROM cte_5a\n" +
                "JOIN cte_3a ON cte_5a.ZONE_CODE = cte_3a.ZONE_CODE\n" +
                "JOIN cte_5b ON cte_5a.ZONE_CODE = cte_5b.ZONE_CODE\n" +
                "JOIN cte_5c ON cte_5a.ZONE_CODE = cte_5c.ZONE_CODE\n" +
                "CROSS JOIN median_cte\n" +
                "ORDER BY total_score5a DESC, \n" +
                "total_score5b ASC, total_score5c ASC;";
        return queryGstParameter5;
    }
    public String QueryFor_Adjudication_5_MonthBack_CustomMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter5 ="WITH cte_5a AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO) AS col5a\n" +
                "FROM Mis_DGI_CUS_1A AS 14c\n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_5 + "'\n" +
                "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),cte_3a AS (SELECT zc.ZONE_NAME,cc.ZONE_CODE,SUM(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO) AS col3a\n" +
                "FROM Mis_DGI_CUS_1A AS 14c\n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                "WHERE 14c.MM_YYYY = DATE_SUB('" + MonthBack_5 + "', INTERVAL 1 MONTH)\n" +
                "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),median_cte AS (\n" +
                "SELECT AVG(col5a) AS cus5a_median\n" +
                "FROM (\n" +
                "\t\tSELECT col5a, ROW_NUMBER() OVER (ORDER BY col5a) AS rn, COUNT(*) OVER () AS cnt\n" +
                "\t\t\tFROM cte_5a\n" +
                "\t\t\t) AS temp\n" +
                "\t\t\tWHERE rn IN (FLOOR((cnt + 1) / 2), CEIL((cnt + 1) / 2))\n" +
                "),\n" +
                "cte_5b AS (\n" +
                "SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
                "SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) AS col7d,\n" +
                "SUM(14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO) AS col6a,\n" +
                "(SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) / \n" +
                "SUM(14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO)) * 100 AS total_score5b\n" +
                "FROM mis_dgi_cus_1A AS 14c\n" +
                "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_5 + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                "),\n" +
                "cte_5c AS (SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col7,SUM(14c.YEAR_1) AS col9,\n" +
                "((SUM(14c.CLOSING_NO) - SUM(14c.YEAR_1)) / SUM(14c.CLOSING_NO)) * 100 AS total_score5c\n" +
                "FROM mis_gst_commcode AS cc\n" +
                "RIGHT JOIN mis_dgi_cus_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "WHERE 14c.MM_YYYY = '" + MonthBack_5 + "' GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                ")\n" +
                "SELECT cte_5a.ZONE_NAME, cte_5a.ZONE_CODE, cte_5a.col5a, cte_3a.col3a,median_cte.cus5a_median,\n" +
                "(cte_5a.col5a / cte_3a.col3a) * 100 AS total_score5a,\n" +
                "cte_5b.col7d, cte_5b.col6a, cte_5b.total_score5b,cte_5c.col7, cte_5c.col9, cte_5c.total_score5c\n" +
                "FROM cte_5a\n" +
                "JOIN cte_3a ON cte_5a.ZONE_CODE = cte_3a.ZONE_CODE\n" +
                "JOIN cte_5b ON cte_5a.ZONE_CODE = cte_5b.ZONE_CODE\n" +
                "JOIN cte_5c ON cte_5a.ZONE_CODE = cte_5c.ZONE_CODE\n" +
                "CROSS JOIN median_cte\n" +
                "ORDER BY total_score5a DESC, \n" +
                "total_score5b ASC, total_score5c ASC;";
        return queryGstParameter5;
    }
    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Investigation__6__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_Investigation_CurrentMonth_CustomMISReports(String month_date){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter6 ="";
        return queryGstParameter6;
    }
    public String QueryFor_Investigation_1_MonthBack_CustomMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter6 ="";
        return queryGstParameter6;
    }
    public String QueryFor_Investigation_2_MonthBack_CustomMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter6 ="";
        return queryGstParameter6;
    }
    public String QueryFor_Investigation_3_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter6 ="";
        return queryGstParameter6;
    }
    public String QueryFor_Investigation_4_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter6 ="";
        return queryGstParameter6;
    }
    public String QueryFor_Investigation_5_MonthBack_CustomMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter6 ="";
        return queryGstParameter6;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Arrests and Prosecution__7__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_ArrestsAndProsecution_CurrentMonth_CustomMISReports(String month_date){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter7 ="";
        return queryGstParameter7;
    }
    public String QueryFor_ArrestsAndProsecution_1_MonthBack_CustomMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter7 ="";
        return queryGstParameter7;
    }
    public String QueryFor_ArrestsAndProsecution_2_MonthBack_CustomMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter7 ="";
        return queryGstParameter7;
    }
    public String QueryFor_ArrestsAndProsecution_3_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter7 ="";
        return queryGstParameter7;
    }
    public String QueryFor_ArrestsAndProsecution_4_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter7 ="";
        return queryGstParameter7;
    }
    public String QueryFor_ArrestsAndProsecution_5_MonthBack_CustomMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter7 ="";
        return queryGstParameter7;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Monitoring of un-cleared/unclaimed cargo__8__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_MonitoringOfUnClearedUnclaimedCargo_CurrentMonth_CustomMISReports(String month_date){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_MonitoringOfUnClearedUnclaimedCargo_1_MonthBack_CustomMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_MonitoringOfUnClearedUnclaimedCargo_2_MonthBack_CustomMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_MonitoringOfUnClearedUnclaimedCargo_3_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_MonitoringOfUnClearedUnclaimedCargo_4_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }
    public String QueryFor_MonitoringOfUnClearedUnclaimedCargo_5_MonthBack_CustomMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter8 ="";
        return queryGstParameter8;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Disposal of confiscated Gold and NDPS__9__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_DisposalOfConfiscatedGoldAndNDPS_CurrentMonth_CustomMISReports(String month_date){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter9 = "WITH main_query AS (\r\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s3col9, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END) AS s3col12, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_1 + "' THEN CB_QUAN ELSE 0 END) AS s3col3,\r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s6col9, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + month_date + "' THEN PARTY_QUAN ELSE 0 END) AS s6col12, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_1 + "' THEN CB_QUAN ELSE 0 END) AS s6col3,\r\n"
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
                + "          (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_1 + "' THEN CB_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_1 + "' THEN CB_QUAN ELSE 0 END))\r\n"
                + "        ) AS total_score\r\n"
                + "    FROM mis_gst_commcode AS cc \r\n"
                + "    RIGHT JOIN mis_dol_cus_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \r\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \r\n"
                + "    WHERE 14c.MM_YYYY IN ('" + month_date + "', '" + MonthBack_1 + "')\r\n"
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
                + "    WHERE mis.MM_YYYY ='" + MonthBack_1 + "'\r\n"
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
        return queryGstParameter9;
    }
    public String QueryFor_DisposalOfConfiscatedGoldAndNDPS_1_MonthBack_CustomMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter9 ="WITH main_query AS (\r\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_1 + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s3col9, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_1 + "' THEN PARTY_QUAN ELSE 0 END) AS s3col12, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_2 + "' THEN CB_QUAN ELSE 0 END) AS s3col3,\r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_1 + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s6col9, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_1 + "' THEN PARTY_QUAN ELSE 0 END) AS s6col12, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_2 + "' THEN CB_QUAN ELSE 0 END) AS s6col3,\r\n"
                + "\r\n"
                + "        (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_1 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_1 + "' THEN PARTY_QUAN ELSE 0 END) + \r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_1 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY ='" + MonthBack_1 + "' THEN PARTY_QUAN ELSE 0 END)) AS numerator_9a,\r\n"
                + "\r\n"
                + "        ( \r\n"
                + "          (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_1 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_1 + "' THEN PARTY_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_1 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_1 + "' THEN PARTY_QUAN ELSE 0 END)) \r\n"
                + "          / \r\n"
                + "          (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_2 + "' THEN CB_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_2 + "' THEN CB_QUAN ELSE 0 END))\r\n"
                + "        ) AS total_score\r\n"
                + "    FROM mis_gst_commcode AS cc \r\n"
                + "    RIGHT JOIN mis_dol_cus_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \r\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \r\n"
                + "    WHERE 14c.MM_YYYY IN ('" + MonthBack_1 + "', '" + MonthBack_2 + "')\r\n"
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
                + "    WHERE mis.MM_YYYY = '" + MonthBack_1 + "'\r\n"
                + "    GROUP BY cc.ZONE_CODE\r\n"
                + "),\r\n"
                + "RipeClosingCTE AS (\r\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(mis.RIPE_CLOSING) AS s5col11\r\n"
                + "    FROM MIS_DOL_CUS_3 AS mis\r\n"
                + "    RIGHT JOIN mis_gst_commcode AS cc ON mis.COMM_CODE = cc.COMM_CODE\r\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                + "    WHERE mis.MM_YYYY ='" + MonthBack_2 + "'\r\n"
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
        return queryGstParameter9;
    }
    public String QueryFor_DisposalOfConfiscatedGoldAndNDPS_2_MonthBack_CustomMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter9 ="WITH main_query AS (\r\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_2 + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s3col9, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_2 + "' THEN PARTY_QUAN ELSE 0 END) AS s3col12, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_3 + "' THEN CB_QUAN ELSE 0 END) AS s3col3,\r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_2 + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s6col9, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_2 + "' THEN PARTY_QUAN ELSE 0 END) AS s6col12, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_3 + "' THEN CB_QUAN ELSE 0 END) AS s6col3,\r\n"
                + "\r\n"
                + "        (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_2 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_2 + "' THEN PARTY_QUAN ELSE 0 END) + \r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_2 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY ='" + MonthBack_2 + "' THEN PARTY_QUAN ELSE 0 END)) AS numerator_9a,\r\n"
                + "\r\n"
                + "        ( \r\n"
                + "          (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_2 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_2 + "' THEN PARTY_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_2 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_2 + "' THEN PARTY_QUAN ELSE 0 END)) \r\n"
                + "          / \r\n"
                + "          (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_3 + "' THEN CB_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_3 + "' THEN CB_QUAN ELSE 0 END))\r\n"
                + "        ) AS total_score\r\n"
                + "    FROM mis_gst_commcode AS cc \r\n"
                + "    RIGHT JOIN mis_dol_cus_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \r\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \r\n"
                + "    WHERE 14c.MM_YYYY IN ('" + MonthBack_2 + "', '" + MonthBack_3 + "')\r\n"
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
                + "    WHERE mis.MM_YYYY = '" + MonthBack_2 + "'\r\n"
                + "    GROUP BY cc.ZONE_CODE\r\n"
                + "),\r\n"
                + "RipeClosingCTE AS (\r\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(mis.RIPE_CLOSING) AS s5col11\r\n"
                + "    FROM MIS_DOL_CUS_3 AS mis\r\n"
                + "    RIGHT JOIN mis_gst_commcode AS cc ON mis.COMM_CODE = cc.COMM_CODE\r\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                + "    WHERE mis.MM_YYYY ='" + MonthBack_3 + "'\r\n"
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
        return queryGstParameter9;
    }
    public String QueryFor_DisposalOfConfiscatedGoldAndNDPS_3_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter9 ="WITH main_query AS (\r\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_3 + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s3col9, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_3 + "' THEN PARTY_QUAN ELSE 0 END) AS s3col12, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_4 + "' THEN CB_QUAN ELSE 0 END) AS s3col3,\r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_3 + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s6col9, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_3 + "' THEN PARTY_QUAN ELSE 0 END) AS s6col12, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_4 + "' THEN CB_QUAN ELSE 0 END) AS s6col3,\r\n"
                + "\r\n"
                + "        (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_3 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_3 + "' THEN PARTY_QUAN ELSE 0 END) + \r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_3 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY ='" + MonthBack_3 + "' THEN PARTY_QUAN ELSE 0 END)) AS numerator_9a,\r\n"
                + "\r\n"
                + "        ( \r\n"
                + "          (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_3 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_3 + "' THEN PARTY_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_3 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_3 + "' THEN PARTY_QUAN ELSE 0 END)) \r\n"
                + "          / \r\n"
                + "          (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_4 + "' THEN CB_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_4 + "' THEN CB_QUAN ELSE 0 END))\r\n"
                + "        ) AS total_score\r\n"
                + "    FROM mis_gst_commcode AS cc \r\n"
                + "    RIGHT JOIN mis_dol_cus_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \r\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \r\n"
                + "    WHERE 14c.MM_YYYY IN ('" + MonthBack_3 + "', '" + MonthBack_4 + "')\r\n"
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
                + "    WHERE mis.MM_YYYY = '" + MonthBack_3 + "'\r\n"
                + "    GROUP BY cc.ZONE_CODE\r\n"
                + "),\r\n"
                + "RipeClosingCTE AS (\r\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(mis.RIPE_CLOSING) AS s5col11\r\n"
                + "    FROM MIS_DOL_CUS_3 AS mis\r\n"
                + "    RIGHT JOIN mis_gst_commcode AS cc ON mis.COMM_CODE = cc.COMM_CODE\r\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                + "    WHERE mis.MM_YYYY ='" + MonthBack_4 + "'\r\n"
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
        return queryGstParameter9;
    }
    public String QueryFor_DisposalOfConfiscatedGoldAndNDPS_4_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter9 ="WITH main_query AS (\r\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_4 + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s3col9, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_4 + "' THEN PARTY_QUAN ELSE 0 END) AS s3col12, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_5 + "' THEN CB_QUAN ELSE 0 END) AS s3col3,\r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_4 + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s6col9, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_4 + "' THEN PARTY_QUAN ELSE 0 END) AS s6col12, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_5 + "' THEN CB_QUAN ELSE 0 END) AS s6col3,\r\n"
                + "\r\n"
                + "        (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_4 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_4 + "' THEN PARTY_QUAN ELSE 0 END) + \r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_4 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY ='" + MonthBack_4 + "' THEN PARTY_QUAN ELSE 0 END)) AS numerator_9a,\r\n"
                + "\r\n"
                + "        ( \r\n"
                + "          (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_4 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_4 + "' THEN PARTY_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_4 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_4 + "' THEN PARTY_QUAN ELSE 0 END)) \r\n"
                + "          / \r\n"
                + "          (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_5 + "' THEN CB_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_5 + "' THEN CB_QUAN ELSE 0 END))\r\n"
                + "        ) AS total_score\r\n"
                + "    FROM mis_gst_commcode AS cc \r\n"
                + "    RIGHT JOIN mis_dol_cus_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \r\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \r\n"
                + "    WHERE 14c.MM_YYYY IN ('" + MonthBack_4 + "', '" + MonthBack_5 + "')\r\n"
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
                + "    WHERE mis.MM_YYYY = '" + MonthBack_4 + "'\r\n"
                + "    GROUP BY cc.ZONE_CODE\r\n"
                + "),\r\n"
                + "RipeClosingCTE AS (\r\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(mis.RIPE_CLOSING) AS s5col11\r\n"
                + "    FROM MIS_DOL_CUS_3 AS mis\r\n"
                + "    RIGHT JOIN mis_gst_commcode AS cc ON mis.COMM_CODE = cc.COMM_CODE\r\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                + "    WHERE mis.MM_YYYY ='" + MonthBack_5 + "'\r\n"
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
        return queryGstParameter9;
    }
    public String QueryFor_DisposalOfConfiscatedGoldAndNDPS_5_MonthBack_CustomMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter9 ="WITH main_query AS (\r\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_5 + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s3col9, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_5 + "' THEN PARTY_QUAN ELSE 0 END) AS s3col12, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_6 + "' THEN CB_QUAN ELSE 0 END) AS s3col3,\r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_5 + "' THEN 14c.SALE_QUAN ELSE 0 END) AS s6col9, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_5 + "' THEN PARTY_QUAN ELSE 0 END) AS s6col12, \r\n"
                + "        SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_6 + "' THEN CB_QUAN ELSE 0 END) AS s6col3,\r\n"
                + "\r\n"
                + "        (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_5 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_5 + "' THEN PARTY_QUAN ELSE 0 END) + \r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_5 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "         SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY ='" + MonthBack_5 + "' THEN PARTY_QUAN ELSE 0 END)) AS numerator_9a,\r\n"
                + "\r\n"
                + "        ( \r\n"
                + "          (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_5 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_5 + "' THEN PARTY_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_5 + "' THEN 14c.SALE_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_5 + "' THEN PARTY_QUAN ELSE 0 END)) \r\n"
                + "          / \r\n"
                + "          (SUM(CASE WHEN 14c.COMMODITY_CODE = 3 AND 14c.MM_YYYY = '" + MonthBack_6 + "' THEN CB_QUAN ELSE 0 END) + \r\n"
                + "           SUM(CASE WHEN 14c.COMMODITY_CODE = 6 AND 14c.MM_YYYY = '" + MonthBack_6 + "' THEN CB_QUAN ELSE 0 END))\r\n"
                + "        ) AS total_score\r\n"
                + "    FROM mis_gst_commcode AS cc \r\n"
                + "    RIGHT JOIN mis_dol_cus_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \r\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \r\n"
                + "    WHERE 14c.MM_YYYY IN ('" + MonthBack_5 + "', '" + MonthBack_6 + "')\r\n"
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
                + "    WHERE mis.MM_YYYY = '" + MonthBack_5 + "'\r\n"
                + "    GROUP BY cc.ZONE_CODE\r\n"
                + "),\r\n"
                + "RipeClosingCTE AS (\r\n"
                + "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(mis.RIPE_CLOSING) AS s5col11\r\n"
                + "    FROM MIS_DOL_CUS_3 AS mis\r\n"
                + "    RIGHT JOIN mis_gst_commcode AS cc ON mis.COMM_CODE = cc.COMM_CODE\r\n"
                + "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                + "    WHERE mis.MM_YYYY ='" + MonthBack_6 + "'\r\n"
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
        return queryGstParameter9;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Recovery of Arrears__10__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_RecoveryOfArrears_CurrentMonth_CustomMISReports(String month_date){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter10 ="";
        return queryGstParameter10;
    }
    public String QueryFor_RecoveryOfArrears_1_MonthBack_CustomMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter10 ="";
        return queryGstParameter10;
    }
    public String QueryFor_RecoveryOfArrears_2_MonthBack_CustomMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter10 ="";
        return queryGstParameter10;
    }
    public String QueryFor_RecoveryOfArrears_3_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter10 ="";
        return queryGstParameter10;
    }
    public String QueryFor_RecoveryOfArrears_4_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter10 ="";
        return queryGstParameter10;
    }
    public String QueryFor_RecoveryOfArrears_5_MonthBack_CustomMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter10 ="";
        return queryGstParameter10;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Management of Warehousing bonds__11__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_ManagementOfWarehousingBonds_CurrentMonth_CustomMISReports(String month_date){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter11 ="";
        return queryGstParameter11;
    }
    public String QueryFor_ManagementOfWarehousingBonds_1_MonthBack_CustomMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter11 ="";
        return queryGstParameter11;
    }
    public String QueryFor_ManagementOfWarehousingBonds_2_MonthBack_CustomMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter11 ="";
        return queryGstParameter11;
    }
    public String QueryFor_ManagementOfWarehousingBonds_3_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter11 ="";
        return queryGstParameter11;
    }
    public String QueryFor_ManagementOfWarehousingBonds_4_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter11 ="";
        return queryGstParameter11;
    }
    public String QueryFor_ManagementOfWarehousingBonds_5_MonthBack_CustomMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter11 ="";
        return queryGstParameter11;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Commissioner (Appeals)__12__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_CommissionerAppeals_CurrentMonth_CustomMISReports(String month_date){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter12 ="";
        return queryGstParameter12;
    }
    public String QueryFor_CommissionerAppeals_1_MonthBack_CustomMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter12 ="";
        return queryGstParameter12;
    }
    public String QueryFor_CommissionerAppeals_2_MonthBack_CustomMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter12 ="";
        return queryGstParameter12;
    }
    public String QueryFor_CommissionerAppeals_3_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter12 ="";
        return queryGstParameter12;
    }
    public String QueryFor_CommissionerAppeals_4_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter12 ="";
        return queryGstParameter12;
    }
    public String QueryFor_CommissionerAppeals_5_MonthBack_CustomMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter12 ="";
        return queryGstParameter12;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Audit__13__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public String QueryFor_Audit_CurrentMonth_CustomMISReports(String month_date){
        // '" + month_date + "'	'" + zone_code + "' '" + MonthBack_1 + "' '" + next_month_new + "'  //  this query for current month
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date);
        //  in this query normal month date is current month and MonthBack_1 is a previous month
        String queryGstParameter13 ="";
        return queryGstParameter13;
    }
    public String QueryFor_Audit_1_MonthBack_CustomMISReports(String month_date){
        // '" + MonthBack_1 + "' '" + zone_code + "' '" + MonthBack_2 + "' 	'" + next_month_new + "'  //  This query for 1 month back
        String MonthBack_1 = DateCalculate.get_1_MonthBack(month_date); // that is current month_date
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_1 is a current month and MonthBack_2 is a previous month
        String queryGstParameter13 ="";
        return queryGstParameter13;
    }
    public String QueryFor_Audit_2_MonthBack_CustomMISReports(String month_date){
        //           '" + MonthBack_2 + "'	'" + zone_code + "'		'" + MonthBack_3 + "' 	'" + next_month_new + "'
        String MonthBack_2 = DateCalculate.get_2_MonthBack(month_date); // that is current month_date
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_2 is a current month and MonthBack_3 is a previous month
        String queryGstParameter13 ="";
        return queryGstParameter13;
    }
    public String QueryFor_Audit_3_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_3 + "'	'" + zone_code + "'		'" + MonthBack_4 + "' 	'" + next_month_new + "'
        String MonthBack_3 = DateCalculate.get_3_MonthBack(month_date); // that is current month_date
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_3 is a current month and MonthBack_4 is a previous month
        String queryGstParameter13 ="";
        return queryGstParameter13;
    }
    public String QueryFor_Audit_4_MonthBack_CustomMISReports(String month_date){
        //             	 '" + MonthBack_4 + "'	'" + zone_code + "'		'" + MonthBack_5 + "' 	'" + next_month_new + "'
        String MonthBack_4 = DateCalculate.get_4_MonthBack(month_date); // that is current month_date
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_4 is a current month and MonthBack_5 is a previous month
        String queryGstParameter13 ="";
        return queryGstParameter13;
    }
    public String QueryFor_Audit_5_MonthBack_CustomMISReports(String month_date){
        //               '" + MonthBack_5 + "'	'" + zone_code + "'		'" + MonthBack_6 + "' 	'" + next_month_new + "'
        String MonthBack_5 = DateCalculate.get_5_MonthBack(month_date); // that is current month_date
        String MonthBack_6 = DateCalculate.get_6_MonthBack(month_date); // thas is previous month
        // in this query MonthBack_5 is a current month and MonthBack_6 is a previous month
        String queryGstParameter13 ="";
        return queryGstParameter13;
    }
}
