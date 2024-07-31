package com.ornates.cbic.controller;

import com.ornates.cbic.dao.pool.JDBCConnection;
import com.ornates.cbic.dao.result.GetExecutionSQL;
import com.ornates.cbic.model.response.GST4A;
import com.ornates.cbic.model.response.TotalScore;
import com.ornates.cbic.service.CustomGreadeScore;
import com.ornates.cbic.service.CustomRelaventAspect;
import com.ornates.cbic.service.DateCalculate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*
 * @Author: @RKS & @Nishant
 */

//@CrossOrigin
@RequestMapping("/cbic/custom/parameter")
@Controller
public class CustomParameterController {
    CustomGreadeScore score = new CustomGreadeScore();
    @ResponseBody
    @RequestMapping(value = "/")
    public String home() {
        return "its working api";
    }

    /*
     * Date: may 30, 2024
     * Purpose: This methods for performing check the database  .
     */
    @ResponseBody
    @RequestMapping(value = "/db_check")
    public String bdTest() {

        //Connection done
        Connection con = JDBCConnection.getTNConnection();
        System.out.println("Connection :"+con);
        return "its working api";
    }

    @ResponseBody
    @RequestMapping(value = "/adjudication") // custom
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/adjudication?month_date=2023-05-01&type=parameter                   // for return filing button
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/adjudication?month_date=2023-05-01&type=zone&zone_code=59           // for all button
    //	http://localhost:8080/cbicApi/cbic/custom/parameter/adjudication?month_date=2023-05-01&type=commissary&zone_code=59     // for show button, zone wise
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/adjudication?month_date=2023-05-01&type=all_commissary              // for all commissary
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/adjudication?month_date=2023-05-01&type=come_name&zone_code=64&come_name=Rajkot     // for particular commissary wise, show button
    public Object adjudication(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<TotalScore> allGstaList = new ArrayList<>();
        TotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        Integer Zonal_rank = 0;
        try {

            if (type.equalsIgnoreCase("parameter")) { // adjudication all zone name 1
                //String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String query_assessment = "WITH CTE_5a AS (\n" +
                        "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
                        "           (SUM(14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO) * 100) / \n" +
                        "           SUM(14c.COMM_OPENING_NO + 14c.JC_OPENING_NO + 14c.AC_OPENING_NO) AS total_score5a\n" +
                        "    FROM Mis_DGI_CUS_1A AS 14c \n" +
                        "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                        "    WHERE 14c.MM_YYYY = '2022-03-01' \n" +
                        "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        "),\n" +
                        "CTE_5b AS (\n" +
                        "    SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                        "           (SUM(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT) * 100) / \n" +
                        "           SUM(14c.AC_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.COMM_CLOSING_NO) AS total_score5b\n" +
                        "    FROM mis_dgi_cus_1A AS 14c  \n" +
                        "    RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE \n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                        "    WHERE 14c.MM_YYYY = '2022-03-01' \n" +
                        "    GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        "),\n" +
                        "CTE_5c AS (\n" +
                        "    SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
                        "           (((SUM(14c.CLOSING_NO) - SUM(14c.YEAR_1)) * 100) / SUM(14c.CLOSING_NO)) AS total_score5c\n" +
                        "    FROM mis_gst_commcode AS cc \n" +
                        "    RIGHT JOIN mis_dgi_cus_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                        "    WHERE 14c.MM_YYYY = '2022-03-01'\n" +
                        "    GROUP BY zc.ZONE_NAME, cc.ZONE_CODE\n" +
                        "),\n" +
                        "ALL_ZONES AS (\n" +
                        "    SELECT ZONE_NAME, ZONE_CODE FROM CTE_5a\n" +
                        "    UNION\n" +
                        "    SELECT ZONE_NAME, ZONE_CODE FROM CTE_5b\n" +
                        "    UNION\n" +
                        "    SELECT ZONE_NAME, ZONE_CODE FROM CTE_5c\n" +
                        ")\n" +
                        "SELECT \n" +
                        "    az.ZONE_NAME, az.ZONE_CODE,\n" +
                        "    COALESCE(a.total_score5a, 0) AS total_score5a,\n" +
                        "    COALESCE(b.total_score5b, 0) AS total_score5b,\n" +
                        "    COALESCE(c.total_score5c, 0) AS total_score5c,\n" +
                        "    ((COALESCE(a.total_score5a, 0) + COALESCE(b.total_score5b, 0) + COALESCE(c.total_score5c, 0)) * 0.3333) as parameter,\n" +
                        "    ROW_NUMBER() OVER (ORDER BY ((COALESCE(a.total_score5a, 0) + COALESCE(b.total_score5b, 0) + COALESCE(c.total_score5c, 0)) * 0.3333) ) AS z_rank\n" +
                        "FROM ALL_ZONES az\n" +
                        "LEFT JOIN CTE_5a a ON az.ZONE_CODE = a.ZONE_CODE\n" +
                        "LEFT JOIN CTE_5b b ON az.ZONE_CODE = b.ZONE_CODE\n" +
                        "LEFT JOIN CTE_5c c ON az.ZONE_CODE = c.ZONE_CODE\n" +
                        "ORDER BY parameter ;\n";

                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    Integer way_to_grade = 0;
                    Integer insentavization = 0;
                    double sub_parameter_weighted_average = 0.00;
                    Zonal_rank = rsGst14aa.getInt("z_rank");
                    double  total = rsGst14aa.getDouble("parameter");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = null;
                    String ra ="null";

                    // Object total = ((double) col21*100 / col3);
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            } else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
                //String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String query_assessment = "";

                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    double tScore = rsGst14aa.getDouble("total_score") * 100;
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer way_to_grade = 0;
                    Integer insentavization = 0;
                    double sub_parameter_weighted_average = 0.00;
                    Zonal_rank = rsGst14aa.getInt("z_rank");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String gst = "null";
                    String absval = "null";
                    String ra ="null";
                    String commName = rsGst14aa.getString("COMM_NAME");


                    String formattedTotal = String.format("%.2f", tScore);
                    double total_score = Double.parseDouble(formattedTotal);
                    totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            } else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String query_assessment = "";

                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer way_to_grade = 0;
                    Integer insentavization = 0;
                    double sub_parameter_weighted_average = 0.00;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String gst = rsGst14aa.getString("gst");
                    String absval = rsGst14aa.getString("absolute_value");
                    double tScore = rsGst14aa.getDouble("score_of_subParameter") * 100;
                    String ra =rsGst14aa.getString("ra");
                    Zonal_rank = null;
                    String commName = "null";

                    String formattedTotal = String.format("%.2f", tScore);
                    double total_score = Double.parseDouble(formattedTotal);
                    totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            } else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
                //String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String query_assessment = "";

                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer way_to_grade = 0;
                    Integer insentavization = 0;
                    double sub_parameter_weighted_average = 0.00;
                    String commName = rsGst14aa.getString("COMM_NAME");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double tScore = rsGst14aa.getDouble("total_score") * 100;
                    Zonal_rank = rsGst14aa.getInt("z_rank");
                    String gst = "null";
                    String absval = "null";
                    String ra ="null";


                    String formattedTotal = String.format("%.2f", tScore);
                    double total_score = Double.parseDouble(formattedTotal);
                    totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            } else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
                //String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String query_assessment = "";

                rsGst14aa = GetExecutionSQL.getResult(query_assessment);


                while (rsGst14aa.next()) {
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer way_to_grade = 0;
                    Integer insentavization = 0;
                    double sub_parameter_weighted_average = 0.00;
                    String commName = rsGst14aa.getString("COMM_NAME");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double tScore = rsGst14aa.getDouble("score_of_subParameter") * 100;
                    Zonal_rank = null;
                    String gst = rsGst14aa.getString("gst");
                    String absval = rsGst14aa.getString("absolute_value");
                    String ra =rsGst14aa.getString("ra");


                    String formattedTotal = String.format("%.2f", tScore);
                    double total_score = Double.parseDouble(formattedTotal);
                    totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rsGst14aa != null) rsGst14aa.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allGstaList;
    }
}
