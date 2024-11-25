package com.ornates.cbic.dao.Query;

import com.ornates.cbic.service.DateCalculate;

public class TestingQueryGst3b {

    public String QueryFor_gst3b_ZoneWise(String month_date){
        //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
        String prev_month_new = DateCalculate.getPreviousMonth(month_date);
        String getFinancialYear = DateCalculate.getFinancialYearStart(month_date);
        String queryGst14aa="WITH CumulativeData AS (\n" +
                "    SELECT cc.ZONE_CODE,zc.ZONE_NAME,scr.MM_YYYY,SUM(TAX_LIABILITY_DETECTECT) \n" +
                "            OVER ( PARTITION BY cc.ZONE_CODE, zc.ZONE_NAME ORDER BY scr.MM_YYYY) AS col14,\n" +
                "        SUM(scr.AMOUNT_RECOVERED_TAX + scr.AMOUNT_RECOVERED_INTEREST + scr.AMOUNT_RECOVERED_PENALTY\n" +
                "        ) OVER (PARTITION BY cc.ZONE_CODE, zc.ZONE_NAME ORDER BY scr.MM_YYYY) AS col22\n" +
                "    FROM mis_gst_commcode AS cc\n" +
                "    RIGHT JOIN mis_dggst_gst_scr_1 AS scr ON cc.COMM_CODE = scr.COMM_CODE\n" +
                "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                "    WHERE scr.MM_YYYY BETWEEN '" + getFinancialYear + "' AND '" + month_date + "'\n" +
                ")\n" +
                "SELECT ZONE_CODE,ZONE_NAME,MM_YYYY,\n" +
                "    MAX(col14) AS col14, MAX(col22) AS col22  \n" +
                "FROM CumulativeData\n" +
                "WHERE MM_YYYY = '" + month_date + "' GROUP BY ZONE_CODE, ZONE_NAME, MM_YYYY;";
        return queryGst14aa;
    }
}
