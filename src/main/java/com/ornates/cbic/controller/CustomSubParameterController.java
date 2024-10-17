package com.ornates.cbic.controller;

import com.ornates.cbic.dao.Query.CustomSubParameterWiseQuery;
import com.ornates.cbic.dao.pool.JDBCConnection;
import com.ornates.cbic.dao.result.GetExecutionSQL;
import com.ornates.cbic.model.response.GST4A;
import com.ornates.cbic.service.CustomGreadeScore;
import com.ornates.cbic.service.CustomRelaventAspect;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/*
 * @Author: @RKS & @Nishant
 */

//@CrossOrigin
@RequestMapping("/cbic/custom")
@Controller
public class CustomSubParameterController {
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
    // conpleted codes are - 1, 2a, 4d, 5a, 5b,5c, 6a(only zone ), 9a ---------no one is running properly

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus1*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus1")
    //  http://localhost:8080/cbicApi/cbic/custom/cus1?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus1?month_date=2024-04-01&zone_code=69&type=commissary
    //	http://localhost:8080/cbicApi/cbic/custom/cus1?month_date=2024-04-01&type=all_commissary
    public Object getCus1(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryCustom1=new CustomSubParameterWiseQuery().QueryFor_cus1a_ZoneWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryCustom1);
                while(rsGst14aa.next()) {
                    String ra=CustomRelaventAspect.cus1_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String commname="ALL";
                    Double t_score = rsGst14aa.getDouble("total_score");
                    double col_difference=rsGst14aa.getInt("col_difference");
                    double col10=rsGst14aa.getInt("col10");
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int Zonal_rank = 0;
                    String gst = "no";

                    int insentavization = 0;
                    int way_to_grade = score.c_marks1(totalScore);
                    String absval = String.valueOf(col_difference) + "/" + String.valueOf(col10);
                    double sub_parameter_weighted_average_bfore = way_to_grade * 1 ;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);

                    gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) { // cus 1
                String queryCustom1=new CustomSubParameterWiseQuery().QueryFor_cus1a_CommissonaryWise(month_date,zone_code);
                Connection con;
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryCustom1);
                while (rsGst14aa.next()) {
                    String zoneName = ((ResultSet) rsGst14aa).getString("ZONE_NAME");
                    String ra = CustomRelaventAspect.cus1_RA;
                    String zoneCode = ((ResultSet) rsGst14aa).getString("ZONE_CODE");
                    String commname = ((ResultSet) rsGst14aa).getString("COMM_NAME");
                    Double t_score = rsGst14aa.getDouble("total_score");
                    double col_difference=rsGst14aa.getInt("col_difference");
                    double col10=rsGst14aa.getInt("col10");
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int Zonal_rank = 0;
                    String gst = "no";

                    int insentavization = 0;
                    int way_to_grade = score.c_marks1(totalScore);
                    String absval = String.valueOf(col_difference) + "/" + String.valueOf(col10);
                    double sub_parameter_weighted_average_bfore = way_to_grade * 1 ;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) { // cus 1

                String queryCustom1=new CustomSubParameterWiseQuery().QueryFor_cus1a_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryCustom1);
                while (rsGst14aa.next()) {
                    String zoneName = ((ResultSet) rsGst14aa).getString("ZONE_NAME");
                    String ra = CustomRelaventAspect.cus1_RA;
                    String zoneCode = ((ResultSet) rsGst14aa).getString("ZONE_CODE");
                    String commname = ((ResultSet) rsGst14aa).getString("COMM_NAME");
                    Double t_score = rsGst14aa.getDouble("total_score");
                    double col_difference=rsGst14aa.getInt("col_difference");
                    double col10=rsGst14aa.getInt("col10");
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int Zonal_rank = 0;
                    String gst = "no";

                    int insentavization = 0;
                   int way_to_grade = score.c_marks1(totalScore);
                    String absval = String.valueOf(col_difference) + "/" + String.valueOf(col10);
                    double sub_parameter_weighted_average_bfore = way_to_grade * 1 ;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus2A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus2a")
    //  http://localhost:8080/cbicApi/cbic/custom/cus2a?month_date=2015-03-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus2a?month_date=2015-03-01&zone_code=69&type=commissary
    //	http://localhost:8080/cbicApi/cbic/custom/cus2a?month_date=2015-03-01&type=all_commissary
    public Object getCus2a(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'

                String queryGst14aa = "";

                //Result Set
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String ra = CustomRelaventAspect.cus2a_RA;
                    String commname = "ALL";
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col10 = rsGst14aa.getInt("col10");
                    int col12 = rsGst14aa.getInt("col12");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col10-col12)+"/"+String.valueOf(col10);

                    if((col10) != 0) {
                        total = ((double) (col10-col12) / (col10));
                    }else {
                        total = 0.00;
                    }

                    rank = score.c_marks2a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
//              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'

                String queryGst14aa = "";


                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String ra = CustomRelaventAspect.cus2a_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    int col10 = rsGst14aa.getInt("col10");
                    int col12 = rsGst14aa.getInt("col12");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col10-col12) + "/" + String.valueOf(col10);

                    if(col10 != 0) {
                        total = ((double) (col10-col12) / col10);
                    }else {
                        total = 0.00;
                    }
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    rank = score.c_marks2a(total);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
//              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'

                String queryGst14aa = "";


                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String ra = CustomRelaventAspect.cus2a_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    int col10 = rsGst14aa.getInt("col10");
                    int col12 = rsGst14aa.getInt("col12");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col10-col12) + "/" + String.valueOf(col10);

                    if(col10 != 0) {
                        total = ((double) (col10-col12) / col10);
                    }else {
                        total = 0.00;
                    }
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    rank = score.c_marks2a(total);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus4D*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus4d")
    //  http://localhost:8080/cbicApi/cbic/custom/cus4d?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus4d?month_date=2024-04-01&zone_code=69&type=commissary
    //	http://localhost:8080/cbicApi/cbic/custom/cus4d?month_date=2024-04-01&type=all_commissary
    public Object getCus4d(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryCustom4d=new CustomSubParameterWiseQuery().QueryFor_cus4d_ZoneWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryCustom4d);

                // Query string
//                String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE, " +
//                        "SUM(14c.PENDING_YEAR_1TO2_NO) AS col6, " +
//                        "SUM(14c.PENDING_MORE_2YEAR_NO) AS col8, " +
//                        "sum(14c.PENDING_MONTHS_0TO6_NO) AS col2, " +
//                        "sum(14c.PENDING_MONTHS_6TO12_NO) AS col4 " +
//                        "FROM MIS_DGI_CUS_5B AS 14c RIGHT JOIN mis_gst_commcode AS cc " +
//                        "ON 14c.COMM_CODE = cc.COMM_CODE " +
//                        "LEFT JOIN mis_gst_zonecode AS zc " +
//                        "ON zc.ZONE_CODE = cc.ZONE_CODE " +
//                        "WHERE 14c.MM_YYYY = '" + month_date + "' " +
//                        "GROUP BY cc.ZONE_CODE;";
                //Result Set

                while (rsGst14aa.next()) {
                    String ra =  CustomRelaventAspect.cus4d_RA;
                    String commname = "ALL";
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col6 = rsGst14aa.getInt("col6");
                    int col8 = rsGst14aa.getInt("col8");
                    int col2 = rsGst14aa.getInt("col2");
                    int col4 = rsGst14aa.getInt("col4");
                    int Zonal_rank = 0;
                    String gst = "no";
                    // int way_to_grade = 0;
                    int insentavization = 0;
                    //int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col6+col8)+"/"+String.valueOf(col2+col4+col6+col8);

                    if((col2+col4+col6+col8) != 0) {
                        total = ((double) (col6+col8)*100 / (col2+col4+col6+col8));
                    }else {
                        total = 0.00;
                    }
                    rank = score.c_marks4d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);


                    int way_to_grade = score.c_marks4d(total);
                    double sub_parameter_weighted_average_bfore = way_to_grade * 0.1;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }

            }else if (type.equalsIgnoreCase("commissary")) {
                String queryCustom4d=new CustomSubParameterWiseQuery().QueryFor_cus4d_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryCustom4d);

//                String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, "
//                        + "(14c.PENDING_YEAR_1TO2_NO) AS col6, "
//                        + "(14c.PENDING_MORE_2YEAR_NO) AS col8, "
//                        + "(14c.PENDING_MONTHS_0TO6_NO) AS col2, "
//                        + "(14c.PENDING_MONTHS_6TO12_NO) AS col4 "
//                        + "FROM MIS_DGI_CUS_5B AS 14c RIGHT JOIN mis_gst_commcode AS cc "
//                        + "ON 14c.COMM_CODE = cc.COMM_CODE "
//                        + "LEFT JOIN mis_gst_zonecode AS zc "
//                        + "ON zc.ZONE_CODE = cc.ZONE_CODE "
//                        + "WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" +
//                        zone_code + "';";


                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus4d_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    int col6 = rsGst14aa.getInt("col6");
                    int col8 = rsGst14aa.getInt("col8");
                    int col2 = rsGst14aa.getInt("col2");
                    int col4 = rsGst14aa.getInt("col4");
                    int Zonal_rank = 0;
                    String gst = "no";
                    //int way_to_grade = 0;
                    int insentavization = 0;
                    String absval=String.valueOf(col6+col8)+"/"+String.valueOf(col2+col4+col6+col8);

                    if((col2+col4+col6+col8) != 0) {
                        total = ((double) (col6+col8)*100 / (col2+col4+col6+col8));
                    }else {
                        total = 0.00;
                    }
                    rank = score.c_marks4d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);


                    int way_to_grade = score.c_marks4d(total);
                    double sub_parameter_weighted_average_bfore = way_to_grade * 0.1;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);

                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
//              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'
                String queryCustom4d=new CustomSubParameterWiseQuery().QueryFor_cus4d_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom4d);
                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus4d_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    int col6 = rsGst14aa.getInt("col6");
                    int col8 = rsGst14aa.getInt("col8");
                    int col2 = rsGst14aa.getInt("col2");
                    int col4 = rsGst14aa.getInt("col4");
                    int Zonal_rank = 0;
                    String gst = "no";
                    //int way_to_grade = 0;
                    int insentavization = 0;
                    String absval=String.valueOf(col6+col8)+"/"+String.valueOf(col2+col4+col6+col8);

                    if((col2+col4+col6+col8) != 0) {
                        total = ((double) (col6+col8)*100 / (col2+col4+col6+col8));
                    }else {
                        total = 0.00;
                    }
                    rank = score.c_marks4d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);


                    int way_to_grade = score.c_marks4d(total);
                    double sub_parameter_weighted_average_bfore = way_to_grade * 0.1;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;

    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus5A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus5a")
    //  http://localhost:8080/cbicApi/cbic/custom/cus5a?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus5a?month_date=2024-04-01&zone_code=69&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus5a?month_date=2024-04-01&type=all_commissary
    public Object getCus5a(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus5a_ZoneWise(month_date);
                //Result Set
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus5a_RA;
                    String commname = "ALL";
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col5a = rsGst14aa.getInt("col5a");
                    int col3a = rsGst14aa.getInt("col3a");
                    double median = rsGst14aa.getDouble("cus5a_median");
                    Double numerator_6c = rsGst14aa.getDouble("col5a");
                    int Zonal_rank = 0;
                    String gst = "no";
                    // int insentavization = 0;
                    String absval=String.valueOf(col5a)+"/"+String.valueOf(col3a);
                    if((col3a) != 0) {
                        total = ((double) (col5a) * 100 / (col3a));
                    }else {
                        total = 0.00;
                    }
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks5a(totalScore);
                    int insentavization = score.c_marks5a(totalScore);
                    if (numerator_6c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // 2 floating point
                    double sub_parameter_weighted_average = way_to_grade * 0.3;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus5a_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus5a_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col5a = rsGst14aa.getInt("col5a");
                    int col3a = rsGst14aa.getInt("col3a");
                    double median = rsGst14aa.getDouble("cus5a_median");
                    Double numerator_6c = rsGst14aa.getDouble("col5a");
                    int Zonal_rank = 0;
                    String gst = "no";
                    //int insentavization = 0;
                    String absval=String.valueOf(col5a)+"/"+String.valueOf(col3a);

                    if((col3a) != 0) {
                        total = ((double) (col5a) * 100 / (col3a));
                    }else {
                        total = 0.00;
                    }
                    rank = score.c_marks5a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks5a(totalScore);
                    int insentavization = score.c_marks5a(totalScore);
                    if (numerator_6c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }

                    // 2 floating point
                    double sub_parameter_weighted_average = way_to_grade * 0.3;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus5a_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus5a_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col5a = rsGst14aa.getInt("col5a");
                    int col3a = rsGst14aa.getInt("col3a");
                    double median = rsGst14aa.getDouble("cus5a_median");
                    Double numerator_6c = rsGst14aa.getDouble("col5a");
                    //col3a is giving null for any date column, that reason total_score is o
                    int Zonal_rank = 0;
                    String gst = "no";

                    // int insentavization = 0;
                    String absval=String.valueOf(col5a)+"/"+String.valueOf(col3a);

                    if((col3a) != 0) {
                        total = ((double) (col5a) * 100 / (col3a));
                    }else {
                        total = 0.00;
                    }

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks5a(totalScore);
                    int insentavization = score.c_marks5a(totalScore);
                    if (numerator_6c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // 2 floating point
                    double sub_parameter_weighted_average = way_to_grade * 0.3;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GST4A::getTotal_score).reversed()).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus5B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus5b")
    //  http://localhost:8080/cbicApi/cbic/custom/cus5b?month_date=2022-02-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus5b?month_date=2022-02-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus5b?month_date=2022-02-01&type=all_commissary
    public Object CustomGst5b(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code){
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;


        try {
            // Query string
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus5b_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus5B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname= "ALL";
                    int col7d=rsGst14aa.getInt("col7d");
                    int col6a=rsGst14aa.getInt("col6a");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval=String.valueOf(col7d)+"/"+String.valueOf(col6a);

                    if(col6a !=0){
                        total = ((double) col7d * 100 / col6a);
                    }else {
                        total=0.00;
                    }

                    rank=score.c_marks5b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks5b(totalScore);
                    // 2 floating point
                    double sub_parameter_weighted_average = way_to_grade * 0.4;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }

            } else if (type.equalsIgnoreCase("commissary")) {  // cus5b
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus5b_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus5B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col7d=rsGst14aa.getInt("col7d");
                    int col6a=rsGst14aa.getInt("col6a");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval=String.valueOf(col7d)+"/"+String.valueOf(col6a);

                    if(col6a !=0){
                        total = ((double) col7d * 100 / col6a);
                    }else {
                        total=0.00;
                    }

                    rank=score.c_marks5b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks5b(totalScore);
                    // 2 floating point
                    double sub_parameter_weighted_average = way_to_grade * 0.4;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {  // cus5b
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus5b_AllCommissonaryWise(month_date);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus5B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col7d=rsGst14aa.getInt("col7d");
                    int col6a=rsGst14aa.getInt("col6a");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval=String.valueOf(col7d)+"/"+String.valueOf(col6a);

                    if(col6a !=0){
                        total = ((double) col7d * 100 / col6a);
                    }else {
                        total=0.00;
                    }

                    rank=score.c_marks5b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks5b(totalScore);
                    // 2 floating point
                    double sub_parameter_weighted_average = way_to_grade * 0.4;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GST4A::getTotal_score)).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus5c*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus5c")
    //  http://localhost:8080/cbicApi/cbic/custom/cus5c?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus5c?month_date=2024-04-01&zone_code=58&type=commissary
    //	http://localhost:8080/cbicApi/cbic/custom/cus5c?month_date=2024-04-01&type=all_commissary
    public Object CustomGst5c(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code){
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        try {
            // Query string
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa =new CustomSubParameterWiseQuery().QueryFor_cus5c_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String zonename = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String ra= CustomRelaventAspect.cus5c_RA;
                    String commname= "ALL";
                    String absval= rsGst14aa.getString("absval");
                    total = rsGst14aa.getDouble("total_score") * 100;
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;

                    rank=score.c_marks5c(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks5c(totalScore);
                    // 2 floating point
                    double sub_parameter_weighted_average = way_to_grade * 0.3;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GST4A(zonename,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }

            } else if (type.equalsIgnoreCase("commissary")) {  // cus5c
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus5c_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String zonename = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String ra= CustomRelaventAspect.cus5c_RA;
                    String commname= rsGst14aa.getString("COMM_NAME");
                    String absval= rsGst14aa.getString("absval");
                    total = rsGst14aa.getDouble("total_score")  * 100;
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;

                    rank=score.c_marks5c(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks5c(totalScore);
                    // 2 floating point
                    double sub_parameter_weighted_average = way_to_grade * 0.3;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GST4A(zonename,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {  // cus5c
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus5c_AllCommissonaryWise(month_date);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String zonename = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String ra= CustomRelaventAspect.cus5c_RA;
                    String commname= rsGst14aa.getString("COMM_NAME");
                    String absval= rsGst14aa.getString("absval");
                    total = rsGst14aa.getDouble("total_score") * 100;
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;

                    rank=score.c_marks5c(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks5c(totalScore);
                    // 2 floating point
                    double sub_parameter_weighted_average = way_to_grade * 0.3;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GST4A(zonename,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GST4A::getTotal_score)).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus6A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus6a")
    //  http://localhost:8080/cbicApi/cbic/custom/cus6a?month_date=2015-03-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus6a?month_date=2015-03-01&zone_code=69&type=commissary
    public Object getCus6a(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;


        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string
                String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE,\n" +
                        "    SUM(14c.IMPORT_GOLD_DISPOSAL_NOC + 14c.IMPORT_NARCO_DISPOSAL_NOC + 14c.IMPORT_FICN_DISPOSAL_NOC + 14c.IMPORT_WILD_DISPOSAL_NOC + 14c.IMPORT_ODS_DISPOSAL_NOC + 14c.IMPORT_IPR_DISPOSAL_NOC + 14c.IMPORT_OTHERS_DISPOSAL_NOC + 14c.EXPORT_GOLD_DISPOSAL_NOC + 14c.EXPORT_NARCO_DISPOSAL_NOC + 14c.EXPORT_FICN_DISPOSAL_NOC + 14c.EXPORT_WILD_DISPOSAL_NOC + 14c.EXPORT_ODS_DISPOSAL_NOC + 14c.EXPORT_IPR_DISPOSAL_NOC + 14c.EXPORT_OTHERS_DISPOSAL_NOC) AS col9_3a,\n" +
                        "    SUM(15c.IMPORT_VALUE_DISPOSAL_NOC + 15c.IMPORT_MIS_DISPOSAL_NOC + 15c.IMPORT_DEEC_DISPOSAL_NOC + 15c.IMPORT_DEPB_DISPOSAL_NOC + 15c.IMPORT_EPCG_DISPOSAL_NOC + 15c.IMPORT_EOU_DISPOSAL_NOC + 15c.IMPORT_END_DISPOSAL_NOC + 15c.IMPORT_OTHERS_DISPOSAL_NOC + 15c.EXPORT_DEEC_DISPOSAL_NOC + 15c.EXPORT_DEPB_DISPOSAL_NOC + 15c.EXPORT_EPCG_DISPOSAL_NOC + 15c.EXPORT_EOU_DISPOSAL_NOC + 15c.EXPORT_DBK_DISPOSAL_NOC + 15c.EXPORT_OTHERS_DISPOSAL_NOC) AS col9_3b,\n" +
                        "    SUM(14c.IMPORT_NARCO_CLOSING_NOC + 14c.IMPORT_FICN_CLOSING_NOC + 14c.IMPORT_WILD_CLOSING_NOC + 14c.IMPORT_ODS_CLOSING_NOC + 14c.IMPORT_IPR_CLOSING_NOC + 14c.IMPORT_OTHERS_CLOSING_NOC + 14c.EXPORT_GOLD_CLOSING_NOC + 14c.EXPORT_NARCO_CLOSING_NOC + 14c.EXPORT_FICN_CLOSING_NOC + 14c.EXPORT_WILD_CLOSING_NOC + 14c.EXPORT_ODS_CLOSING_NOC + 14c.EXPORT_IPR_CLOSING_NOC + 14c.EXPORT_OTHERS_CLOSING_NOC) AS col3_3a,\n" +
                        "    SUM(15c.IMPORT_VALUE_CLOSING_NOC + 15c.IMPORT_MIS_CLOSING_NOC + 15c.IMPORT_DEEC_CLOSING_NOC + 15c.IMPORT_DEPB_CLOSING_NOC + 15c.IMPORT_EPCG_CLOSING_NOC + 15c.IMPORT_EOU_CLOSING_NOC + 15c.IMPORT_END_CLOSING_NOC + 15c.IMPORT_OTHERS_CLOSING_NOC + 15c.EXPORT_DEEC_CLOSING_NOC + 15c.EXPORT_DEPB_CLOSING_NOC + 15c.EXPORT_EPCG_CLOSING_NOC + 15c.EXPORT_EOU_CLOSING_NOC + 15c.EXPORT_DBK_CLOSING_NOC + 15c.EXPORT_OTHERS_CLOSING_NOC) AS col3_3b\n" +
                        "FROM mis_dri_cus_3a AS 14c\n" +
                        "RIGHT JOIN mis_gst_commcode AS cc ON 14c.COMM_CODE = cc.COMM_CODE\n" +
                        "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "LEFT JOIN mis_dri_cus_3b AS 15c ON 14c.COMM_CODE = 15c.COMM_CODE\n" +
                        "WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                        "GROUP BY cc.ZONE_CODE;";
                //Result Set
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus6a_RA;
                    String commname = "ALL";
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col9_3a = rsGst14aa.getInt("col9_3a");
                    int col9_3b = rsGst14aa.getInt("col9_3b");
                    int col3_3a = rsGst14aa.getInt("col3_3a");
                    int col3_3b = rsGst14aa.getInt("col3_3b");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col9_3a + col9_3b)+"/"+String.valueOf(col3_3a + col3_3b);

                    if((col3_3a + col3_3b) != 0) {
                        total = ((double) (col9_3a + col9_3b) / (col3_3a + col3_3b));
                    }else {
                        total = 0.00;
                    }

                    rank = score.c_marks6a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) { // cus 1

                String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME, (14c.CLOSING_NO) as col10,\r\n"
                        + "(14c.MONTHS_3_NO) as col12 \r\n"
                        + "FROM  mis_dgi_cus_4 as 14c right join  mis_gst_commcode as cc on 14c.COMM_CODE=cc.COMM_CODE\r\n"
                        + "left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE \r\n"
                        + "where  14c.MM_YYYY='" + month_date + "'  and cc.ZONE_CODE = '" + zone_code + "';";


                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus1_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    int col10 = rsGst14aa.getInt("col10");
                    int col12 = rsGst14aa.getInt("col12");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col10-col12) + "/" + String.valueOf(col10);

                    if(col10 != 0) {
                        total = ((double) (col10-col12) / col10);
                    }else {
                        total = 0.00;
                    }
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    rank = score.c_marks1(total);
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus9A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus9a")
    //  http://localhost:8080/cbicApi/cbic/custom/cus9a?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus9a?month_date=2024-04-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus9a?month_date=2024-04-01&type=all_commissary
    public Object CustomGst9a(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code){
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        Double median = 0.00;

        try {
            // Query string
            if (type.equalsIgnoreCase("zone")) {
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus9a_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus9a_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String commname= "ALL";
                    double s3col9=rsGst14aa.getDouble("s3col9");
                    double s3col12=rsGst14aa.getDouble("s3col12");
                    double s3col3 =rsGst14aa.getDouble("s3col3");
                    double s6col9=rsGst14aa.getDouble("s6col9");
                    double s6col12=rsGst14aa.getDouble("s6col12");
                    double s6col3 =rsGst14aa.getDouble("s6col3");
                    total=rsGst14aa.getDouble("total_score") * 100;
                    median = rsGst14aa.getDouble("median_9a");
                    Double numerator_9a = rsGst14aa.getDouble("numerator_9a");
                    //String absval=String.valueOf(s3col9 + s3col12 + s6col9 + s6col12)+"/"+String.valueOf(s3col3 + s6col3);
                    String absval = String.format("%.2f", s3col9 + s3col12 + s6col9 + s6col12) + "/" + String.format("%.2f", s3col3 + s6col3);


                    rank=score.c_marks9a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks9a(totalScore);
                    int insentavization = score.c_marks9a(totalScore);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_9a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }

                    //System.out.println("insentavization3b after :-" + insentavization);

                    int Zonal_rank = 0;
                    String gst = "no";

                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.5 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println("cus 9a median zone wise :- "+median);

            } else if (type.equalsIgnoreCase("commissary")) {  // cus9a
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus9a_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus9a_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String commname= rsGst14aa.getString("COMM_NAME");
                    double s3col9=rsGst14aa.getDouble("s3col9");
                    double s3col12=rsGst14aa.getDouble("s3col12");
                    double s3col3 =rsGst14aa.getDouble("s3col3");
                    double s6col9=rsGst14aa.getDouble("s6col9");
                    double s6col12=rsGst14aa.getDouble("s6col12");
                    double s6col3 =rsGst14aa.getDouble("s6col3");
                    median = rsGst14aa.getDouble("median_9a");
                    Double numerator_9a = rsGst14aa.getDouble("numerator_9a");
                    total=rsGst14aa.getDouble("total_score") * 100;
                    int Zonal_rank = 0;
                    String gst = "no";

                    //String absval=String.valueOf(s3col9 + s3col12 + s6col9 + s6col12)+"/"+String.valueOf(s3col3 + s6col3);
                    String absval = String.format("%.2f", s3col9 + s3col12 + s6col9 + s6col12) + "/" + String.format("%.2f", s3col3 + s6col3);

                    rank=score.c_marks9a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks9a(totalScore);
                    int insentavization = score.c_marks9a(totalScore);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_9a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.5 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);

                }System.out.println("cus 9a median commissionary rate wise :- "+median);
            }else if (type.equalsIgnoreCase("all_commissary")) {  // cus9a
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus9a_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus9a_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String commname= rsGst14aa.getString("COMM_NAME");
                    double s3col9=rsGst14aa.getDouble("s3col9");
                    double s3col12=rsGst14aa.getDouble("s3col12");
                    double s3col3=rsGst14aa.getDouble("s3col3");
                    double s6col9=rsGst14aa.getDouble("s6col9");
                    double s6col12=rsGst14aa.getDouble("s6col12");
                    double s6col3=rsGst14aa.getDouble("s6col3");
                    total=rsGst14aa.getDouble("total_score") * 100;
                    median = rsGst14aa.getDouble("median_9a");
                    Double numerator_9a = rsGst14aa.getDouble("numerator_9a");
                    int Zonal_rank = 0;
                    String gst = "no";
                    //String absval=String.valueOf(s3col9 + s3col12 + s6col9 + s6col12)+"/"+String.valueOf(s3col3 + s6col3);
                    String absval = String.format("%.2f", s3col9 + s3col12 + s6col9 + s6col12) + "/" + String.format("%.2f", s3col3 + s6col3);


                    rank=score.c_marks9a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks9a(totalScore);
                    int insentavization = score.c_marks9a(totalScore);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_9a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.5 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }System.out.println("cus 9a median commissionary rate wise :- " +median);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus9B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus9b")
    //  http://localhost:8080/cbicApi/cbic/custom/cus9b?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus9b?month_date=2024-04-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus9b?month_date=2024-04-01&type=all_commissary
    public Object CustomGst9b(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code){
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        Double median = 0.00;

        try {
            // Query string
            if (type.equalsIgnoreCase("zone")) {
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus9b_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
//                    String ra= CustomRelaventAspect.cus9b_RA;
//                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
//                    String zoneName =rsGst14aa.getString("ZONE_NAME");
//                    String commname= "ALL";
//                    double s5col13 =rsGst14aa.getDouble("s5col13");
//                    double s5col11 =rsGst14aa.getDouble("s5col11");
//                    median =rsGst14aa.getDouble("Median");
//                    total=rsGst14aa.getDouble("total_score") * 100;
//                    Double numerator_9b = rsGst14aa.getDouble("s5col13");
//                    String absval = String.format("%.2f", s5col13) + "/" + String.format("%.2f", s5col11);
//
//
//                    // rank=score.c_marks9b(total);
//                    String formattedTotal = String.format("%.2f", total);
//                    double totalScore = Double.parseDouble(formattedTotal);
//                    int way_to_grade = score.c_marks9b(totalScore);
//                    int insentavization = score.c_marks9b(totalScore);
//                    // System.out.println("insentavization3b :-" + insentavization);
//
//                    if (numerator_9b > median && way_to_grade < 10) {
//                        insentavization += 1;
//                    }
//                    //System.out.println("insentavization9b after :-" + insentavization);
//                    int Zonal_rank = 0;
//                    String gst = "CUS9B";
//
//                    // 2 floating point
//                    double sub_parameter_weighted_average = insentavization * 0.5 ;
//                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
//                    gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
//                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
//                    allGstaList.add(gsta);
                }
                System.out.println("cus 9b median zone wise :- "+median);

            } else if (type.equalsIgnoreCase("commissary")) {  // cus9b
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus9b_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
//                    String ra= CustomRelaventAspect.cus9b_RA;
//                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
//                    String zoneName =rsGst14aa.getString("ZONE_NAME");
//                    String commname= rsGst14aa.getString("COMM_NAME");
//                    double s5col13 =rsGst14aa.getDouble("s5col13");
//                    double s5col11 =rsGst14aa.getDouble("s5col11");
//                    median =rsGst14aa.getDouble("median_value");
//                    Double numerator_9b = rsGst14aa.getDouble("s5col13");
//                    total=rsGst14aa.getDouble("total_score") * 100;
//                    int Zonal_rank = 0;
//                    String gst = "no";
//                    //String absval=String.valueOf(s3col9 + s3col12 + s6col9 + s6col12)+"/"+String.valueOf(s3col3 + s6col3);
//                    String absval = String.format("%.2f", s5col13) + "/" + String.format("%.2f", s5col11);
//
//                    // rank=score.c_marks9b(total);
//                    String formattedTotal = String.format("%.2f", total);
//                    double totalScore = Double.parseDouble(formattedTotal);
//                    int way_to_grade = score.c_marks9b(totalScore);
//                    int insentavization = score.c_marks9b(totalScore);
//                    // System.out.println("insentavization3b :-" + insentavization);
//
//                    if (numerator_9b > median && way_to_grade < 10) {
//                        insentavization += 1;
//                    }
//                    // 2 floating point
//                    double sub_parameter_weighted_average = insentavization * 0.5 ;
//                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
//                    gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
//                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
//                    allGstaList.add(gsta);
                }System.out.println("cus 9b median commissionary rate wise :- "+ median);
            }else if (type.equalsIgnoreCase("all_commissary")) {  // cus9b
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus9b_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
//                    String ra= CustomRelaventAspect.cus9b_RA;
//                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
//                    String zoneName =rsGst14aa.getString("ZONE_NAME");
//                    String commname= rsGst14aa.getString("COMM_NAME");
//                    double s5col13 =rsGst14aa.getDouble("s5col13");
//                    double s5col11 =rsGst14aa.getDouble("s5col11");
//                    median =rsGst14aa.getDouble("median_value");
//                    Double numerator_9b = rsGst14aa.getDouble("s5col13");
//                    total=rsGst14aa.getDouble("total_score") * 100;
//                    int Zonal_rank = 0;
//                    String gst = "no";
//                    //String absval=String.valueOf(s3col9 + s3col12 + s6col9 + s6col12)+"/"+String.valueOf(s3col3 + s6col3);
//                    String absval = String.format("%.2f", s5col13) + "/" + String.format("%.2f", s5col11);
//
//                    // rank=score.c_marks9b(total);
//                    String formattedTotal = String.format("%.2f", total);
//                    double totalScore = Double.parseDouble(formattedTotal);
//                    int way_to_grade = score.c_marks9b(totalScore);
//                    int insentavization = score.c_marks9b(totalScore);
//                    // System.out.println("insentavization3b :-" + insentavization);
//
//                    if (numerator_9b > median && way_to_grade < 10) {
//                        insentavization += 1;
//                    }
//                    // 2 floating point
//                    double sub_parameter_weighted_average = insentavization * 0.5 ;
//                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
//                    gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
//                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
//                    allGstaList.add(gsta);
                }System.out.println("cus 9b median commissionary rate wise :- " + median);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GST4A::getTotal_score).reversed()).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus10A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus10B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus11A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus11B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus12A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus12a")
    //  http://localhost:8080/cbicApi/cbic/custom/cus12a?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus12a?month_date=2024-04-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus12a?month_date=2024-04-01&type=all_commissary
    public Object CustomGst12a(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code){
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        Double median = 0.00;
        try {
            // Query string
            if (type.equalsIgnoreCase("zone")) {
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus12a_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
//                    String ra= CustomRelaventAspect.cus12a_RA;
//                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
//                    String zoneName =rsGst14aa.getString("ZONE_NAME");
//                    String commname= "ALL";
//                    total=rsGst14aa.getDouble("total_score") * 100;
//                    Double numerator_12a = rsGst14aa.getDouble("numerator12A");
//                    String absval = rsGst14aa.getString("absvl");
//                    median = rsGst14aa.getDouble("median12A");
//
//                    String formattedTotal = String.format("%.2f", total);
//                    double totalScore = Double.parseDouble(formattedTotal);
//                    int way_to_grade = score.c_marks12a(totalScore,numerator_12a);
//                    int insentavization = score.c_marks12a(totalScore,numerator_12a);
//
//                    if (numerator_12a > median && way_to_grade < 10) {
//                        insentavization += 1;
//                    }
//                    int Zonal_rank = 0;
//                    String gst = "CUS12A";
//
//                    // 2 floating point
//                    double sub_parameter_weighted_average = insentavization * 0.5 ;
//                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
//                    gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
//                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
//                    allGstaList.add(gsta);
                }
                System.out.println("cus 12A median zone wise :- "+median);

            } else if (type.equalsIgnoreCase("commissary")) {  // cus11a
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus12a_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus12a_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String commname= rsGst14aa.getString("COMM_NAME");
                    double s5col13 =rsGst14aa.getDouble("s5col13");
                    double s5col11 =rsGst14aa.getDouble("s5col11");
                    median =rsGst14aa.getDouble("median_value");
                    Double numerator_12a = rsGst14aa.getDouble("s5col13");
                    total=rsGst14aa.getDouble("total_score") * 100;
                    int Zonal_rank = 0;
                    String gst = "no";
                    //String absval=String.valueOf(s3col9 + s3col12 + s6col9 + s6col12)+"/"+String.valueOf(s3col3 + s6col3);
                    String absval = String.format("%.2f", s5col13) + "/" + String.format("%.2f", s5col11);

                    // rank=score.c_marks9b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks12a(totalScore,numerator_12a);
                    int insentavization = score.c_marks12a(totalScore,numerator_12a);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_12a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.5 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }System.out.println("cus 11a median commissionary rate wise :- "+ median);
            }else if (type.equalsIgnoreCase("all_commissary")) {  // cus11a
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus12a_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus12a_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String commname= rsGst14aa.getString("COMM_NAME");
                    double s5col13 =rsGst14aa.getDouble("s5col13");
                    double s5col11 =rsGst14aa.getDouble("s5col11");
                    median =rsGst14aa.getDouble("median_value");
                    Double numerator_12a = rsGst14aa.getDouble("s5col13");
                    total=rsGst14aa.getDouble("total_score") * 100;
                    int Zonal_rank = 0;
                    String gst = "no";
                    //String absval=String.valueOf(s3col9 + s3col12 + s6col9 + s6col12)+"/"+String.valueOf(s3col3 + s6col3);
                    String absval = String.format("%.2f", s5col13) + "/" + String.format("%.2f", s5col11);

                    // rank=score.c_marks9b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks12a(totalScore,numerator_12a);
                    int insentavization = score.c_marks12a(totalScore,numerator_12a);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_12a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.5 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }System.out.println("cus 11a median commissionary rate wise :- " + median);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GST4A::getTotal_score).reversed()).collect(Collectors.toList());
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus12B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus12b")
    //  http://localhost:8080/cbicApi/cbic/custom/cus12b?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/custom/cus12b?month_date=2024-04-01&zone_code=58&type=commissary
    //  http://localhost:8080/cbicApi/cbic/custom/cus12b?month_date=2024-04-01&type=all_commissary
    public Object CustomGst12b(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code){
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        Double median = 0.00;
        try {
            // Query string
            if (type.equalsIgnoreCase("zone")) {
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa = new CustomSubParameterWiseQuery().QueryFor_cus12b_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
//                    String ra= CustomRelaventAspect.cus12b_RA;
//                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
//                    String zoneName =rsGst14aa.getString("ZONE_NAME");
//                    String commname= "ALL";
//                    total=rsGst14aa.getDouble("total_score") * 100;
//                    String absval = rsGst14aa.getString("absvl");
//
//                    String formattedTotal = String.format("%.2f", total);
//                    double totalScore = Double.parseDouble(formattedTotal);
//                    int way_to_grade = score.c_marks12b(totalScore);
//                    int insentavization = 0;
//
//                    int Zonal_rank = 0;
//                    String gst = "CUS12B";
//
//                    // 2 floating point
//                    double sub_parameter_weighted_average = way_to_grade * 0.5 ;
//                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
//                    gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
//                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
//                    allGstaList.add(gsta);
                }
            } else if (type.equalsIgnoreCase("commissary")) {  // cus11a
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus12a_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus12a_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String commname= rsGst14aa.getString("COMM_NAME");
                    double s5col13 =rsGst14aa.getDouble("s5col13");
                    double s5col11 =rsGst14aa.getDouble("s5col11");
                    median =rsGst14aa.getDouble("median_value");
                    Double numerator_12a = rsGst14aa.getDouble("s5col13");
                    total=rsGst14aa.getDouble("total_score") * 100;
                    int Zonal_rank = 0;
                    String gst = "no";
                    //String absval=String.valueOf(s3col9 + s3col12 + s6col9 + s6col12)+"/"+String.valueOf(s3col3 + s6col3);
                    String absval = String.format("%.2f", s5col13) + "/" + String.format("%.2f", s5col11);

                    // rank=score.c_marks9b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks12a(totalScore,numerator_12a);
                    int insentavization = score.c_marks12a(totalScore,numerator_12a);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_12a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.5 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }System.out.println("cus 11a median commissionary rate wise :- "+ median);
            }else if (type.equalsIgnoreCase("all_commissary")) {  // cus11a
                //              '" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String queryGst14aa=new CustomSubParameterWiseQuery().QueryFor_cus12a_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= CustomRelaventAspect.cus12a_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String commname= rsGst14aa.getString("COMM_NAME");
                    double s5col13 =rsGst14aa.getDouble("s5col13");
                    double s5col11 =rsGst14aa.getDouble("s5col11");
                    median =rsGst14aa.getDouble("median_value");
                    Double numerator_12a = rsGst14aa.getDouble("s5col13");
                    total=rsGst14aa.getDouble("total_score") * 100;
                    int Zonal_rank = 0;
                    String gst = "no";
                    //String absval=String.valueOf(s3col9 + s3col12 + s6col9 + s6col12)+"/"+String.valueOf(s3col3 + s6col3);
                    String absval = String.format("%.2f", s5col13) + "/" + String.format("%.2f", s5col11);

                    // rank=score.c_marks9b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks12a(totalScore,numerator_12a);
                    int insentavization = score.c_marks12a(totalScore,numerator_12a);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_12a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.5 ;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GST4A(zoneName,commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }System.out.println("cus 11a median commissionary rate wise :- " + median);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GST4A::getTotal_score)).collect(Collectors.toList());
    }
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus13A*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus13B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus13C*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus13D*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus13E*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
}
