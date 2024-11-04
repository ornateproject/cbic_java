package com.ornates.cbic.controller;

import com.ornates.cbic.dao.Query.CustomParameterWiseQuery;
import com.ornates.cbic.dao.pool.JDBCConnection;
import com.ornates.cbic.dao.result.GetExecutionSQL;
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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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


    // ***********************************CUS 1 parameter wise(Timely Payment Of Refunds) *****************************************************************
    @ResponseBody
    @RequestMapping(value = "/timelyrefunds") // custom 1
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/timelyrefunds?month_date=2024-04-01&type=parameter                   // for return filing button
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/timelyrefunds?month_date=2024-04-01&type=zone&zone_code=76           // for all button
    //	http://localhost:8080/cbicApi/cbic/custom/parameter/timelyrefunds?month_date=2024-04-01&type=commissary&zone_code=76     // for show button, zone wise
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/timelyrefunds?month_date=2024-04-01&type=all_commissary              // for all commissary
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/timelyrefunds?month_date=2024-04-01&type=come_name&zone_code=76&come_name=Kolkata(Port)     // for particular commissary wise, show button
    public Object timelypaymentofrefunds(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<TotalScore> allGstaList = new ArrayList<>();
        TotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        Integer Zonal_rank = 0;
        try {

            if (type.equalsIgnoreCase("parameter")) { // adjudication all zone name 1
                //		'" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                String query_assessment_cus1 = new CustomParameterWiseQuery().QueryFor_TimelyPaymentOfRefunds_1_ZoneWise(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment_cus1);

                while (rsGst14aa.next()) {
                    double col_difference=rsGst14aa.getInt("col_difference");
                    double col10=rsGst14aa.getInt("col10");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col_difference) + "/" + String.valueOf(col10);

                    String ra= CustomRelaventAspect.cus1_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.c_marks1(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            } else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
                //String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                String query_assessment_cus1 = new CustomParameterWiseQuery().QueryFor_TimelyPaymentOfRefunds_1_ParticularZoneWise(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment_cus1);

                while (rsGst14aa.next()) {
                    double col_difference=rsGst14aa.getInt("col_difference");
                    double col10=rsGst14aa.getInt("col10");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String commName = rsGst14aa.getString("COMM_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String gst = "ALL";
                    String absval = String.valueOf(col_difference) + "/" + String.valueOf(col10);

                    String ra= CustomRelaventAspect.cus1_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.c_marks1(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            } else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                String query_assessment_cus1 = new CustomParameterWiseQuery().QueryFor_TimelyPaymentOfRefunds_1_ParticularSubparameterWise(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment_cus1);

                while (rsGst14aa.next()) {
                    double col_difference=rsGst14aa.getInt("col_difference");
                    double col10=rsGst14aa.getInt("col10");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String gst = rsGst14aa.getString("Cus1");
                    String commName = "null";
                    String absval = String.valueOf(col_difference) + "/" + String.valueOf(col10);

                    String ra= CustomRelaventAspect.cus1_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.c_marks1(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            } else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
                //String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                String query_assessment_cus1 = new CustomParameterWiseQuery().QueryFor_TimelyPaymentOfRefunds_1_AllCommissary(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment_cus1);

                while (rsGst14aa.next()) {
                    double col_difference=rsGst14aa.getInt("col_difference");
                    double col10=rsGst14aa.getInt("col10");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String commName = rsGst14aa.getString("COMM_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String gst = "null";
                    String absval = String.valueOf(col_difference) + "/" + String.valueOf(col10);

                    String ra= CustomRelaventAspect.cus1_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.c_marks1(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            } else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
                //String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                String query_assessment_cus1 = new CustomParameterWiseQuery().QueryFor_TimelyPaymentOfRefunds_1_ParticularCommissonaryInSubparameter(month_date,zone_code,come_name);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment_cus1);

                while (rsGst14aa.next()) {
                    double col_difference=rsGst14aa.getInt("col_difference");
                    double col10=rsGst14aa.getInt("col10");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String commName = rsGst14aa.getString("COMM_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String gst = rsGst14aa.getString("description");
                    String absval = String.valueOf(col_difference) + "/" + String.valueOf(col10);

                    String ra= CustomRelaventAspect.cus1_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.c_marks1(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
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

    // ***********************************CUS 5 parameter wise(Adjudication) *****************************************************************
    @ResponseBody
    @RequestMapping(value = "/adjudication") // custom 5
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/adjudication?month_date=2024-04-01&type=parameter                   // for return filing button
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/adjudication?month_date=2024-04-01&type=zone&zone_code=76           // for all button
    //	http://localhost:8080/cbicApi/cbic/custom/parameter/adjudication?month_date=2024-04-01&type=commissary&zone_code=76     // for show button, zone wise
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/adjudication?month_date=2024-04-01&type=all_commissary              // for all commissary
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/adjudication?month_date=2024-04-01&type=come_name&zone_code=76&come_name=Kolkata(Port)     // for particular commissary wise, show button
    public Object adjudication(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<TotalScore> allGstaList = new ArrayList<>();
        TotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        Integer Zonal_rank = 0;
        try {

            if (type.equalsIgnoreCase("parameter")) { // adjudication all zone name 1
                //		'" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                //String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String query_assessment_cus5 = new CustomParameterWiseQuery().QueryFor_Adjudication_5_ZoneWise(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment_cus5);
                // rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
//                    String zoneName = rsGst14aa.getString("ZONE_NAME");
//                    zone_code = rsGst14aa.getString("ZONE_CODE");
//
//                    double total5a = rsGst14aa.getDouble("total_score5a");
//                    double total5b = rsGst14aa.getDouble("total_score5b");
//                    double total5c = rsGst14aa.getDouble("total_score5c");
//
//                    double median5a = rsGst14aa.getDouble("cus5a_median");
//                    //double median5b = rsGst14aa.getDouble("median_numerator_3b");
//
//                    Double numerator_5a = rsGst14aa.getDouble("col5a");
//                    //Double numerator_5b = rsGst14aa.getDouble("numerator_3b");
//
//                    int way_to_grade5a = score.c_marks5a(total5a);
//                    int way_to_grade5b = score.c_marks5b(total5b);
//                    int way_to_grade5c = score.c_marks5c(total5c);
//
//                    int insentavization5a = way_to_grade5a;
//                    int insentavization5b = way_to_grade5b;
//                    int insentavization5c = way_to_grade5c;
//
//                    if (numerator_5a > median5a && way_to_grade5a < 10) {
//                        insentavization5a += 1;
//                    }
//                    Integer way_to_grade = way_to_grade5a + way_to_grade5b+way_to_grade5c;
//                    Integer insentavization = insentavization5a + insentavization5b +insentavization5c;
//
//                    double sub_parameter_weighted_average5a = insentavization5a * 0.3;
//                    double sub_parameter_weighted_average5b = insentavization5b * 0.4;
//                    double sub_parameter_weighted_average5c = insentavization5c * 0.3;
//
//                    // Calculate sub_parameter_weighted_average
//                    double sub_parameter_weighted_average = sub_parameter_weighted_average5a + sub_parameter_weighted_average5b + sub_parameter_weighted_average5c;
//
//                    // Round to two decimal places
//                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
//
//                    double total_weighted_average = sub_parameter_weighted_average;
//
//                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average);
//
//
//
//                    String commName = "ALL";
//                    String gst = "ALL";
//                    String absval = "null";
//                    String ra ="Adjudication";
//
//                    totalScore = new TotalScore(zoneName, commName, zone_code, total_weighted_average, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
//                    allGstaList.add(totalScore);


                       //   for back data testing only

//					System.out.println(zoneName);
//					System.out.println("total5a: " + total5a);
//					System.out.println("numerator_5a : " + numerator_5a);
//					System.out.println("median5a : " + median5a);
//					System.out.println("way_to_grade5a: " + way_to_grade5a);
//					System.out.println("insentavization5a : " + insentavization5a);
//					System.out.println("sub_parameter_weighted_average5a : " + sub_parameter_weighted_average5a);
//
//					System.out.println("total5b: " + total5b);
//					//System.out.println("numerator_5b : " + numerator_5b);
//					//System.out.println("median5b : " + median5b);
//					System.out.println("way_to_grade5b: " + way_to_grade5b);
//					System.out.println("insentavization5b : " + insentavization5b);
//					System.out.println("sub_parameter_weighted_average5b : " + sub_parameter_weighted_average5b);
//					System.out.println("total_score : " + total_weighted_average);
//					System.out.println("********************************************************************************************");
                }
            } else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
                String query_assessment = new CustomParameterWiseQuery().QueryFor_Adjudication_5_ParticularZoneWise(month_date,zone_code);

                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
//                    zone_code = rsGst14aa.getString("ZONE_CODE");
//                    String commName = rsGst14aa.getString("COMM_NAME");
//                    String zoneName = rsGst14aa.getString("ZONE_NAME");
//
//                    double numerator5a = rsGst14aa.getDouble("numerator5a");
//                    double median5a = rsGst14aa.getDouble("median5a");
//                    double toal_score5a = rsGst14aa.getDouble("total_score5a") * 100;
//                    double toal_score5b = rsGst14aa.getDouble("total_score5b") * 100;
//                    double toal_score5c = rsGst14aa.getDouble("total_score5c") * 100;
//
//                    double sub_parameter_weighted_average = 0.00;
//                    String gst = "null";
//                    String absval = "null";
//                    String ra ="null";
//                    double total_score = 0.00;
//
//                    int way_to_grade5a = score.c_marks5a(toal_score5a);
//                    int way_to_grade5b = score.c_marks5b(toal_score5b);
//                    int way_to_grade5c = score.c_marks5b(toal_score5c);
//
//                    int insentavization5a = way_to_grade5a;
//                    int insentavization5b = way_to_grade5b;
//                    int insentavization5c = way_to_grade5c;
//
//                    if (numerator5a > median5a && way_to_grade5a < 10) {
//                        insentavization5a += 1;
//                    }
//
//                    Integer way_to_grade = way_to_grade5a + way_to_grade5b + way_to_grade5c;
//                    Integer insentavization = insentavization5a + insentavization5b + insentavization5c;
//
//                    double sub_parameter_weighted_average5a = insentavization5a * 0.3;
//                    double sub_parameter_weighted_average5b = insentavization5b * 0.4;
//                    double sub_parameter_weighted_average5c = insentavization5c * 0.3;
//
//                    double total_weighted_average = sub_parameter_weighted_average5a + sub_parameter_weighted_average5b + sub_parameter_weighted_average5c;
//                    total_weighted_average = Double.parseDouble(String.format("%.2f", total_weighted_average));
//
//                    totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,total_weighted_average);
//                    allGstaList.add(totalScore);
                }
            } else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                String query_assessment_cus5 = new CustomParameterWiseQuery().QueryFor_Adjudication_5_ParticularSubparameterWise(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment_cus5);


                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    String gst = rsGst14aa.getString("custom");
                    String absval = rsGst14aa.getString("absolutevalue");
                    Double t_score = rsGst14aa.getDouble("total_score");
                    Double median = rsGst14aa.getDouble("cus5a_median");
                    Double numerator = rsGst14aa.getDouble("col5a");

                    String formattedTotal = String.format("%.2f", t_score);
                    double total_score = Double.parseDouble(formattedTotal);

                    int way_to_grade5a = 0;
                    int way_to_grade5b = 0;
                    int way_to_grade5c = 0;
                    int way_to_grade=way_to_grade5a+way_to_grade5b+way_to_grade5c;
                    int insentavization_5a = 0;
                    int insentavization_5b=0;
                    int insentavization_5c=0;
                    int insentavization=insentavization_5a+insentavization_5b+insentavization_5c;

                    // Logic based on parameter type
                    if ("CUS5A".equalsIgnoreCase(gst)) {
                        way_to_grade5a = score.c_marks5a(total_score);
                        insentavization_5a = score.c_marks5a(total_score);

                        if (numerator > median && way_to_grade5a < 10) {
                            insentavization_5a += 1;
                        }
                    } else if ("CUS5B".equalsIgnoreCase(gst)) {
                        way_to_grade5b = score.c_marks5b(total_score);
                        insentavization_5b = way_to_grade5b;

                    }  else if ("CUS5C".equalsIgnoreCase(gst)) {
                        way_to_grade5c = score.c_marks5c(total_score);
                        insentavization_5c = way_to_grade5c;
                    }
                    else {
                        // Default handling if parameter type is neither 5a nor 5b
                        way_to_grade5a = 0;
                        way_to_grade5b = 0;
                        way_to_grade5c = 0;
                        insentavization_5a = 0;
                        insentavization_5b = 0;
                        insentavization_5c = 0;
                    }


                    String commName = "null";
                    String ra = "Adjudication";

                    Double sub_parameter_weighted_average = insentavization_5a * 0.3+insentavization_5b*0.4+insentavization_5c*0.3;
                    // Round to two decimal places
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    double total_weighted_average = sub_parameter_weighted_average;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average);


                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            } else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
                //String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                String query_assessment = new CustomParameterWiseQuery().QueryFor_Adjudication_5_AllCommissary(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
//                    zone_code = rsGst14aa.getString("ZONE_CODE");
//                    if (zone_code == null) {
//                        continue;
//                    }
//                    String commName = rsGst14aa.getString("COMM_NAME");
//                    String zoneName = rsGst14aa.getString("ZONE_NAME");
//
//                    double numerator5a = rsGst14aa.getDouble("numerator5a");
//                    double median5a = rsGst14aa.getDouble("median5a");
//                    double toal_score5a = rsGst14aa.getDouble("total_score5a") * 100;
//                    double toal_score5b = rsGst14aa.getDouble("total_score5b") * 100;
//                    double toal_score5c = rsGst14aa.getDouble("total_score5c") * 100;
//
//                    double sub_parameter_weighted_average = 0.00;
//                    String gst = "null";
//                    String absval = "null";
//                    String ra ="null";
//                    double total_score = 0.00;
//
//                    int way_to_grade5a = score.c_marks5a(toal_score5a);
//                    int way_to_grade5b = score.c_marks5b(toal_score5b);
//                    int way_to_grade5c = score.c_marks5c(toal_score5c);
//
//                    int insentavization5a = way_to_grade5a;
//                    int insentavization5b = way_to_grade5b;
//                    int insentavization5c = way_to_grade5c;
//
//                    if (numerator5a > median5a && way_to_grade5a < 10) {
//                        insentavization5a += 1;
//                    }
//
//                    Integer way_to_grade = way_to_grade5a + way_to_grade5b + way_to_grade5c;
//                    Integer insentavization = insentavization5a + insentavization5b + insentavization5c;
//
//                    double sub_parameter_weighted_average5a = insentavization5a * 0.3;
//                    double sub_parameter_weighted_average5b = insentavization5b * 0.4;
//                    double sub_parameter_weighted_average5c = insentavization5c * 0.3;
//
//                    double total_weighted_average = sub_parameter_weighted_average5a + sub_parameter_weighted_average5b + sub_parameter_weighted_average5c;
//                    total_weighted_average = Double.parseDouble(String.format("%.2f", total_weighted_average));
//
//                    totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,total_weighted_average);
//                    allGstaList.add(totalScore);


                    // for back data chack only

//                    System.out.println("********************************************************************");
//                    System.out.println("commName :-" + commName);
//                    System.out.println("insentavization5a :-" + insentavization5a);
//                    System.out.println("insentavization5b :-" + insentavization5b);
//                    System.out.println("insentavization5c :-" + insentavization5c);
//                    System.out.println("sub_parameter_weighted_average5a :-" + sub_parameter_weighted_average5a);
//                    System.out.println("sub_parameter_weighted_average5b :-" + sub_parameter_weighted_average5b);
//                    System.out.println("sub_parameter_weighted_average5c :-" + sub_parameter_weighted_average5c);


                }
            } else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
                String query_assessment = new CustomParameterWiseQuery().QueryFor_Adjudication_5_ParticularCommissonaryInSubparameter(month_date,zone_code,come_name);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
//                    zone_code = rsGst14aa.getString("ZONE_CODE");
//                    String commName = rsGst14aa.getString("COMM_NAME");
//                    String zoneName = rsGst14aa.getString("ZONE_NAME");
//                    double numerator = rsGst14aa.getDouble("numerator");
//                    double median = rsGst14aa.getDouble("median");
//                    String absval = rsGst14aa.getString("absvl");
//                    double tScore = rsGst14aa.getDouble("total_score") * 100;
//                    String gst = rsGst14aa.getString("gst");
//
//                    Integer way_to_grade = 0;
//                    Integer insentavization = 0;
//                    Integer insentavization5a = 0;
//                    Integer insentavization5b = 0;
//                    Integer insentavization5c = 0;
//                    double insentive = 0.00;
//                    double sub_parameter_weighted_average = 0.00;
//
//                    if ("GST5A".equalsIgnoreCase(gst)) {
//                        way_to_grade = score.c_marks5a(tScore);
//                        insentavization5a = score.c_marks5a(tScore);
//
//                        if (numerator > median && way_to_grade < 10) {
//                            insentavization5a += 1;
//                        }
//                    } else if ("GST5B".equalsIgnoreCase(gst)) {
//                        way_to_grade = score.c_marks5b(tScore);
//                        insentavization5b = way_to_grade;
//                    } else if ("GST5C".equalsIgnoreCase(gst)) {
//                        way_to_grade = score.c_marks5c(tScore);
//                        insentavization5c = way_to_grade;
//                    } else {
//                        // Default handling if parameter type is neither 5a nor 5b
//                        way_to_grade = 0;
//                        insentavization = 0;
//                    }
//
//                    Double total_sub_parameter_weighted_average = insentavization5a * 0.3 +
//                                                                      insentavization5b * 0.4 +
//                                                                            insentavization5c * 0.3;
//                    total_sub_parameter_weighted_average = Double.parseDouble(String.format("%.2f", total_sub_parameter_weighted_average));
//                    total_sub_parameter_weighted_average = Math.round(total_sub_parameter_weighted_average * 100.0) / 100.0;
//
//                    Zonal_rank = null;
//                    String ra ="Adjudication";
//
//                    String formattedTotal = String.format("%.2f", tScore);
//                    double total_score = Double.parseDouble(formattedTotal);
//                    totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,total_sub_parameter_weighted_average);
//                    allGstaList.add(totalScore);
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
        return allGstaList.stream()
                .sorted(Comparator.comparing(TotalScore::getSub_parameter_weighted_average).reversed()).collect(Collectors.toList());
    }


    // ***********************************CUS 9 parameter wise(Disposal Of Confiscated Gold and NDPS) *****************************************************************
    @ResponseBody
    @RequestMapping(value = "/DisposalOfConfiscatedGoldAndNDPS") // custom 9
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/DisposalOfConfiscatedGoldAndNDPS?month_date=2024-04-01&type=parameter                   // for return filing button
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/DisposalOfConfiscatedGoldAndNDPS?month_date=2024-04-01&type=zone&zone_code=76           // for all button
    //	http://localhost:8080/cbicApi/cbic/custom/parameter/DisposalOfConfiscatedGoldAndNDPS?month_date=2024-04-01&type=commissary&zone_code=58     // for show button, zone wise
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/DisposalOfConfiscatedGoldAndNDPS?month_date=2024-04-01&type=all_commissary              // for all commissary
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/DisposalOfConfiscatedGoldAndNDPS?month_date=2024-04-01&type=come_name&zone_code=76&come_name=Kolkata(Port)     // for particular commissary wise, show button
    public Object DisposalOfConfiscatedGoldAndNDPS(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<TotalScore> allGstaList = new ArrayList<>();
        TotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        Integer Zonal_rank = 0;
        try {

            if (type.equalsIgnoreCase("parameter")) { // adjudication all zone name 1
                //		'" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                //String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                String query_assessment_cus9 = new CustomParameterWiseQuery().QueryFor_DisposalOfConfiscatedGoldAndNDPS_9_ZoneWise(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment_cus9);

                while (rsGst14aa.next()) {
//                    String zoneName = rsGst14aa.getString("ZONE_NAME");
//                    zone_code = rsGst14aa.getString("ZONE_CODE");
//
//                    double total9a = rsGst14aa.getDouble("main_total_score");
//                    double total9b = rsGst14aa.getDouble("total_score");
//
//                    double median9a = rsGst14aa.getDouble("median_9a");
//                    double median9b = rsGst14aa.getDouble("Median");
//
//                    Double numerator_9a = rsGst14aa.getDouble("numerator_9a");
//                    Double numerator_9b = rsGst14aa.getDouble("s5col13");
//
//                    int way_to_grade9a = score.c_marks9a(total9a);
//                    int way_to_grade9b = score.c_marks9b(total9b);
//
//                    int insentavization9a = way_to_grade9a;
//                    int insentavization9b = way_to_grade9b;
//
//                    // Logic to adjust insentavization9a and insentavization9b based on the median and numerator values
//                    if (numerator_9a > median9a && way_to_grade9a < 10) {
//                        insentavization9a += 1;
//                    }
//                    if (numerator_9b > median9b && way_to_grade9b < 10) {
//                        insentavization9b += 1;
//                    }
//
//                    Integer way_to_grade = way_to_grade9a + way_to_grade9b;
//                    Integer insentavization = insentavization9a + insentavization9b;
//
//                    double sub_parameter_weighted_average9a = insentavization9a * 0.5;
//                    double sub_parameter_weighted_average9b = insentavization9b * 0.5;
//
//                    double total_score = sub_parameter_weighted_average9a + sub_parameter_weighted_average9b;
//                    double total_weighted_average = sub_parameter_weighted_average9a + sub_parameter_weighted_average9b;
//                   // Integer Zonal_rank = null;
//                    String commName = "ALL";
//                    String gst = "ALL";
//                    String absval = "null";
//                    String ra = "Disposal of confiscated Gold and NDPS";
//
//                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
//                    allGstaList.add(totalScore);



                    // FOR BACK DATA CHECK ONLY

//					System.out.println(zoneName);
//					System.out.println("total3a: " + total3a);
//					System.out.println("numerator_3a : " + numerator_3a);
//					System.out.println("median3a : " + median3a);
//					System.out.println("way_to_grade3a: " + way_to_grade3a);
//					System.out.println("insentavization3a : " + insentavization3a);
//					System.out.println("sub_parameter_weighted_average3a : " + sub_parameter_weighted_average3a);
//
//					System.out.println("total3b: " + total3b);
//					System.out.println("numerator_3b : " + numerator_3b);
//					System.out.println("median3b : " + median3b);
//					System.out.println("way_to_grade3b: " + way_to_grade3b);
//					System.out.println("insentavization3b : " + insentavization3b);
//					System.out.println("sub_parameter_weighted_average3b : " + sub_parameter_weighted_average3b);
//					System.out.println("total_score : " + total_weighted_average);
//					System.out.println("********************************************************************************************");

                }
            } else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
                //String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                String query_assessment = new CustomParameterWiseQuery().QueryFor_DisposalOfConfiscatedGoldAndNDPS_9_ParticularZoneWise(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
//                    zone_code = rsGst14aa.getString("ZONE_CODE");
//                    String commName = rsGst14aa.getString("COMM_NAME");
//                    String zoneName = rsGst14aa.getString("ZONE_NAME");
//
//                    double tScore_9a = rsGst14aa.getDouble("total_score9a") * 100;
//                    double tScore_9b = rsGst14aa.getDouble("total_score9b") * 100;
//
//                    Double median_9a = rsGst14aa.getDouble("numerator_9a");
//                    Double numerator_9a = rsGst14aa.getDouble("numerator_9a");
//
//                    Double median_9b = rsGst14aa.getDouble("median9b");
//                    Double numerator_9b = rsGst14aa.getDouble("numerator9b");
//                    String gst ="null";
//                    String absval = "null";
//                    String ra ="Disposal Of Confiscated Gold and NDPS";
//
//                    int way_to_grade9a = score.c_marks9a(tScore_9a);
//                    int way_to_grade9b = score.c_marks9b(tScore_9b);
//
//                    int insentavization9a = way_to_grade9a;
//                    int insentavization9b = way_to_grade9b;
//
//                    // Logic to adjust insentavization3a and insentavization3b based on the median and numerator values
//                    if (numerator_9a > median_9a && way_to_grade9a < 10) {
//                        insentavization9a += 1;
//                    }
//                    if (numerator_9b > median_9b && way_to_grade9b < 10) {
//                        insentavization9b += 1;
//                    }
//
//                    Integer way_to_grade = way_to_grade9a + way_to_grade9b;
//                    Integer insentavization = insentavization9a + insentavization9b;
//
//                    double sub_parameter_weighted_average9a = insentavization9a * 0.5;
//                    double sub_parameter_weighted_average9b = insentavization9b * 0.5;
//
//                    double total_weighted_average = sub_parameter_weighted_average9a + sub_parameter_weighted_average9b;
//                    double sub_parameter_weighted_average = 0.00;
//
//                    double total_score = 0;
//                    totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,total_weighted_average);
//                    allGstaList.add(totalScore);

                }
            } else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
                String query_assessment_cus9 = new CustomParameterWiseQuery().QueryFor_DisposalOfConfiscatedGoldAndNDPS_9_ParticularSubparameterWise(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment_cus9);

                while (rsGst14aa.next()) {
//                    String zoneName = rsGst14aa.getString("ZONE_NAME");
//                    zone_code = rsGst14aa.getString("ZONE_CODE");
//                    String gst = rsGst14aa.getString("custom");
//                    String absval = rsGst14aa.getString("absolute_value");
//                    Double t_score = rsGst14aa.getDouble("total_score");
//                    Double median = rsGst14aa.getDouble("Median");
//                    Double numerator = rsGst14aa.getDouble("numerator");
//
//                    String formattedTotal = String.format("%.2f", t_score);
//                    double total_score = Double.parseDouble(formattedTotal);
//
//                    int way_to_grade;
//                    int insentavization;
//
//                    // Logic based on parameter type
//                    if ("CUS9A".equalsIgnoreCase(gst)) {
//                        way_to_grade = score.c_marks9a(total_score);
//                        insentavization = score.c_marks9a(total_score);
//
//                        if (numerator > median && way_to_grade < 10) {
//                            insentavization += 1;
//                        }
//                    } else if ("CUS9B".equalsIgnoreCase(gst)) {
//                        way_to_grade = score.c_marks9b(total_score);
//                        insentavization = score.c_marks9b(total_score);
//
//                        if (numerator > median && way_to_grade < 10) {
//                            insentavization += 1;
//                        }
//                    } else {
//                        // Default handling if parameter type is neither 9a nor 9b
//                        way_to_grade = 0;
//                        insentavization = 0;
//                    }
//
//                   // Integer Zonal_rank1 = null;
//                    String commName = "null";
//                    String ra = "Disposal of confiscated Gold and NDPS";
//
//                    Double sub_parameter_weighted_average = insentavization * 0.5;
//                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
//                    allGstaList.add(totalScore);

                }
            } else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
                String query_assessment = new CustomParameterWiseQuery().QueryFor_DisposalOfConfiscatedGoldAndNDPS_9_AllCommissary(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
//                    zone_code = rsGst14aa.getString("ZONE_CODE");
//                    String commName = rsGst14aa.getString("COMM_NAME");
//                    String zoneName = rsGst14aa.getString("ZONE_NAME");
//
//                    double tScore_9a = rsGst14aa.getDouble("total_score9a") * 100;
//                    double tScore_9b = rsGst14aa.getDouble("total_score9b") * 100;
//
//                    Double median_9a = rsGst14aa.getDouble("numerator_9a");
//                    Double numerator_9a = rsGst14aa.getDouble("numerator_9a");
//
//                    Double median_9b = rsGst14aa.getDouble("median9b");
//                    Double numerator_9b = rsGst14aa.getDouble("numerator9b");
//                    String gst ="null";
//                    String absval = "null";
//                    String ra ="Disposal Of Confiscated Gold and NDPS";
//
//                    int way_to_grade9a = score.c_marks9a(tScore_9a);
//                    int way_to_grade9b = score.c_marks9b(tScore_9b);
//
//                    int insentavization9a = way_to_grade9a;
//                    int insentavization9b = way_to_grade9b;
//
//                    // Logic to adjust insentavization3a and insentavization3b based on the median and numerator values
//                    if (numerator_9a > median_9a && way_to_grade9a < 10) {
//                        insentavization9a += 1;
//                    }
//                    if (numerator_9b > median_9b && way_to_grade9b < 10) {
//                        insentavization9b += 1;
//                    }
//
//                    Integer way_to_grade = way_to_grade9a + way_to_grade9b;
//                    Integer insentavization = insentavization9a + insentavization9b;
//
//                    double sub_parameter_weighted_average9a = insentavization9a * 0.5;
//                    double sub_parameter_weighted_average9b = insentavization9b * 0.5;
//
//                    double total_weighted_average = sub_parameter_weighted_average9a + sub_parameter_weighted_average9b;
//                    double sub_parameter_weighted_average = 0.00;
//
//                    double total_score = 0;
//                    totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,total_weighted_average);
//                    allGstaList.add(totalScore);



                    // FOR BACK DATA CHECK ONLY
//                    System.out.println("zoneName :-" + zoneName);
//                    System.out.println("commName :-" + commName);
//                    System.out.println("tScore_9a :-" + tScore_9a);
//                    System.out.println("tScore_9b :-" + tScore_9b);
//                    System.out.println("**********************************************************");
                }
            } else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
                //String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                String query_assessment = new CustomParameterWiseQuery().QueryFor_DisposalOfConfiscatedGoldAndNDPS_9_ParticularCommissonaryInSubparameter(month_date,zone_code,come_name);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
//                    String zoneName = rsGst14aa.getString("ZONE_NAME");
//                    zone_code = rsGst14aa.getString("ZONE_CODE");
//                    String commName = rsGst14aa.getString("COMM_NAME");
//                    Integer way_to_grade = 0;
//                    Integer insentavization = 0;
//                    double tScore = rsGst14aa.getDouble("total_score") * 100;
//                    String gst =rsGst14aa.getString("gst");
//                    String absval = rsGst14aa.getString("absvl");
//                    Double median = rsGst14aa.getDouble("median");
//                    Double numerator = rsGst14aa.getDouble("numerator");
//                    // Double numerator_3b = rsGst14aa.getDouble("numerator_3b");
//                    String ra ="SCRUTINY & ASSESSMENT";
//                    Zonal_rank = null;
//
//
//                    String formattedTotal = String.format("%.2f", tScore);
//                    double total_score = Double.parseDouble(formattedTotal);
//
//                    // Logic based on parameter type
//                    if ("GST9A".equalsIgnoreCase(gst)) {
//                        way_to_grade = score.c_marks9a(total_score);
//                        insentavization = score.c_marks9a(total_score);
//
//                        if (numerator > median && way_to_grade < 10) {
//                            insentavization += 1;
//                        }
//                    } else if ("GST9B".equalsIgnoreCase(gst)) {
//                        way_to_grade = score.c_marks9b(total_score);
//                        insentavization = score.c_marks9b(total_score);
//
//                        if (numerator > median && way_to_grade < 10) {
//                            insentavization += 1;
//                        }
//                    } else {
//                        // Default handling if parameter type is neither 3a nor 3b
//                        way_to_grade = 0;
//                        insentavization = 0;
//                    }
//                    Double sub_parameter_weighted_average = insentavization * 0.5;
//                    totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
//                    allGstaList.add(totalScore);


                    // FOR BACK DATA CHECK ONLY

//                    System.out.println("zoneName :-" + zoneName);
//                    System.out.println("commName :-" + commName);
//                    System.out.println("tScore :-" + tScore);
//                   // System.out.println("tScore_9b :-" + tScore_9b);
//                    System.out.println("**********************************************************");
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
        return allGstaList.stream()
                .sorted(Comparator.comparing(TotalScore::getSub_parameter_weighted_average).reversed()).collect(Collectors.toList());
    }

    // ***********************************CUS 12 parameter wise(Commissioner (Appeals)) *****************************************************************
    @ResponseBody
    @RequestMapping(value = "/CommissionerAppeals") // custom 12
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/CommissionerAppeals?month_date=2024-04-01&type=parameter                   // for return filing button
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/CommissionerAppeals?month_date=2024-04-01&type=zone&zone_code=76           // for all button
    //	http://localhost:8080/cbicApi/cbic/custom/parameter/CommissionerAppeals?month_date=2024-04-01&type=commissary&zone_code=58     // for show button, zone wise
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/CommissionerAppeals?month_date=2024-04-01&type=all_commissary              // for all commissary
    //  http://localhost:8080/cbicApi/cbic/custom/parameter/CommissionerAppeals?month_date=2024-04-01&type=come_name&zone_code=76&come_name=Kolkata(Port)     // for particular commissary wise, show button
    public Object CommissionerAppeals(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<TotalScore> allGstaList = new ArrayList<>();
        TotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        Integer Zonal_rank = 0;
        try {

            if (type.equalsIgnoreCase("parameter")) { // adjudication all zone name 1
                //		'" + month_date + "'	 '" + prev_month_new + "'	'" + zone_code + "'		'" + come_name + "' 	'" + next_month_new + "'
                //String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                String query_assessment = new CustomParameterWiseQuery().QueryFor_CommissionerAppeals_12_ZoneWise(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
//                    String zoneName = rsGst14aa.getString("ZONE_NAME");
//                    zone_code = rsGst14aa.getString("ZONE_CODE");
//                    double numerator12A = rsGst14aa.getDouble("numerator12A");
//                    double median12A = rsGst14aa.getDouble("median12A");
//                    double total_score12A = rsGst14aa.getDouble("Total_score12A");
//                    double total_score12B = rsGst14aa.getDouble("total_score12B");
//
//                    int way_to_grade12a = score.c_marks12a(total_score12A,numerator12A);
//                    int way_to_grade12b = score.c_marks12b(total_score12B);
//
//                    int insentavization12a = way_to_grade12a;
//                    int insentavization12b = way_to_grade12b;
//
//                    if (numerator12A > median12A && way_to_grade12a < 10) {
//                        insentavization12a += 1;
//                    }
//
//                    Integer way_to_grade = way_to_grade12a + way_to_grade12b;
//                    Integer insentavization = insentavization12a + insentavization12b;
//
//                    double sub_parameter_weighted_average12a = insentavization12a * 0.5;
//                    double sub_parameter_weighted_average12b = insentavization12b * 0.5;
//
//                    double total_weighted_average = sub_parameter_weighted_average12a + sub_parameter_weighted_average12b;
//                    // Format the total_weighted_average to two decimal places
//                    String formatted_total_weighted_average = String.format("%.2f", total_weighted_average);
//                    // You can then parse it back to double if needed
//                    total_weighted_average = Double.parseDouble(formatted_total_weighted_average);
//
//                    double sub_parameter_weighted_average = 0.00;
//                    Zonal_rank = 0;
//                    String commName = "ALL";
//                    String gst = "ALL";
//                    String absval = "null";
//                    String ra ="Commissioner (Appeals)";
//                    double total_score = 0;
//
//                    totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,total_weighted_average);
//                    allGstaList.add(totalScore);
                }
            } else if (type.equalsIgnoreCase("zone")) { // for parameter zone all button 2
                //String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                String query_assessment = new CustomParameterWiseQuery().QueryFor_CommissionerAppeals_12_ParticularZoneWise(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
//                    String zoneName = rsGst14aa.getString("ZONE_NAME");
//                    zone_code = rsGst14aa.getString("ZONE_CODE");
//                    String commName = rsGst14aa.getString("COMM_NAME");
//                    int numerator_12a = rsGst14aa.getInt("numerator12A");
//                    double median12A = rsGst14aa.getDouble("median12A");
//                    double total_score12A = rsGst14aa.getDouble("total_score12A") * 100;
//                    double total_score12B = rsGst14aa.getDouble("total_score12B") * 100;
//                    String gst ="null";
//                    String absval = "null";
//                    String ra ="Commissioner (Appeals)";
//                    Zonal_rank = 0;
//
//                    int way_to_grade12a = score.c_marks12a(total_score12A,numerator_12a);
//                    int way_to_grade12b = score.c_marks12b(total_score12B);
//
//                    int insentavization12a = way_to_grade12a;
//                    int insentavization12b = way_to_grade12b;
//
//                    if (numerator_12a > median12A && way_to_grade12a < 10) {
//                        insentavization12a += 1;
//                    }
//
//                    Integer way_to_grade = way_to_grade12a + way_to_grade12b;
//                    Integer insentavization = insentavization12a + insentavization12b;
//
//                    double sub_parameter_weighted_average12a = insentavization12a * 0.5;
//                    double sub_parameter_weighted_average12b = insentavization12b * 0.5;
//
//                    double total_weighted_average = sub_parameter_weighted_average12a + sub_parameter_weighted_average12b;
//                    // Format the total_weighted_average to two decimal places
//                    String formatted_total_weighted_average = String.format("%.2f", total_weighted_average);
//                    // You can then parse it back to double if needed
//                    total_weighted_average = Double.parseDouble(formatted_total_weighted_average);
//
//                    double sub_parameter_weighted_average = 0.00;
//
//                    double total_score =0.00;
//                    totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,total_weighted_average);
//                    allGstaList.add(totalScore);

//                    System.out.println("total_score12B :-" + total_score12B);
//                    System.out.println("total_score12A :-" + total_score12A);
//                    System.out.println("way_to_grade12a :-" + way_to_grade12a);
//                    System.out.println("sub_parameter_weighted_average12a :-" + sub_parameter_weighted_average12a);
//                    System.out.println("sub_parameter_weighted_average12b :-" + sub_parameter_weighted_average12b);

                }
            } else if (type.equalsIgnoreCase("commissary")) {   // for show button, zone wise 3
                String query_assessment = new CustomParameterWiseQuery().QueryFor_CommissionerAppeals_12_ParticularSubparameterWise(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
//                    String zoneName = rsGst14aa.getString("ZONE_NAME");
//                    zone_code = rsGst14aa.getString("ZONE_CODE");
//                    String gst = rsGst14aa.getString("GST");
//                    String absval = rsGst14aa.getString("absvl");
//                    Double t_score = rsGst14aa.getDouble("total_score") * 100;
//                    Double median = rsGst14aa.getDouble("median");
//                    Double numerator = rsGst14aa.getDouble("numerator");
//
//                    String formattedTotal = String.format("%.2f", t_score);
//                    double total_score = Double.parseDouble(formattedTotal);
//
//                    int way_to_grade;
//                    int insentavization;
//
//                    // Logic based on parameter type
//                    if ("GST12A".equalsIgnoreCase(gst)) {
//                        way_to_grade = score.c_marks12a(total_score,numerator);
//                        insentavization = score.c_marks12a(total_score,numerator);
//
//                        if (numerator > median && way_to_grade < 10) {
//                            insentavization += 1;
//                        }
//                    } else if ("GST12B".equalsIgnoreCase(gst)) {
//                        way_to_grade = score.c_marks12b(total_score);
//                        insentavization = way_to_grade;
//                    } else {
//                        // Default handling if parameter type is neither 5a nor 5b
//                        way_to_grade = 0;
//                        insentavization = 0;
//                    }
//
//                    Zonal_rank = null;
//                    String commName = "null";
//                    String ra = "Commissioner (Appeals)";
//
//                    Double sub_parameter_weighted_average = insentavization * 0.5;
//                    // Format the total_weighted_average to two decimal places
//                    String formatted_total_weighted_average = String.format("%.2f", sub_parameter_weighted_average);
//                    // You can then parse it back to double if needed
//                    sub_parameter_weighted_average = Double.parseDouble(formatted_total_weighted_average);
//                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
//                    allGstaList.add(totalScore);
                }
            } else if (type.equalsIgnoreCase("all_commissary")) { // for all commissary 4
                //String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String query_assessment = new CustomParameterWiseQuery().QueryFor_CommissionerAppeals_12_AllCommissary(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
//                    String zoneName = rsGst14aa.getString("ZONE_NAME");
//                    zone_code = rsGst14aa.getString("ZONE_CODE");
//                    String commName = rsGst14aa.getString("COMM_NAME");
//                    int numerator_12a = rsGst14aa.getInt("numerator12A");
//                    double median12A = rsGst14aa.getDouble("median12A");
//                    double total_score12A = rsGst14aa.getDouble("total_score12A");
//                    double total_score12B = rsGst14aa.getDouble("total_score12B");
//                    //double tScore = rsGst14aa.getDouble("total_score") * 100;
//                     Zonal_rank = 0;
//                    int way_to_grade12a = score.c_marks12a(total_score12A,numerator_12a);
//                    int way_to_grade12b = score.c_marks12b(total_score12B);
//
//                    int insentavization12a = way_to_grade12a;
//                    int insentavization12b = way_to_grade12b;
//
//                    if (numerator_12a > median12A && way_to_grade12a < 10) {
//                        insentavization12a += 1;
//                    }
//                    Integer way_to_grade = way_to_grade12a + way_to_grade12b;
//                    Integer insentavization = insentavization12a + insentavization12b;
//
//                    double sub_parameter_weighted_average12a = insentavization12a * 0.5;
//                    double sub_parameter_weighted_average12b = insentavization12b * 0.5;
//
//                    double total_weighted_average = sub_parameter_weighted_average12a + sub_parameter_weighted_average12b;
//                    // Format the total_weighted_average to two decimal places
//                    String formatted_total_weighted_average = String.format("%.2f", total_weighted_average);
//                    // You can then parse it back to double if needed
//                    total_weighted_average = Double.parseDouble(formatted_total_weighted_average);
//
//
//                    double total_score = 0.00;
//                    String gst = "ALL";
//                    String absval = "null";
//                    String ra ="Commissioner (Appeals)";
//
////					String formattedTotal = String.format("%.2f", tScore);
////					double total_score = Double.parseDouble(formattedTotal);
//                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
//                    allGstaList.add(totalScore);
                }
            } else if (type.equalsIgnoreCase("come_name")) { // for particular commissary wise, show button 5
                //String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                String query_assessment = new CustomParameterWiseQuery().QueryFor_CommissionerAppeals_12_ParticularCommissonaryInSubparameter(month_date,zone_code,come_name);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
//                    zone_code = rsGst14aa.getString("ZONE_CODE");
//                    String zoneName = rsGst14aa.getString("ZONE_NAME");
//                    String commName = rsGst14aa.getString("COMM_NAME");
//                    double tScore = rsGst14aa.getDouble("total_score") ;
//                    String gst =rsGst14aa.getString("gst");
//                    String absval = rsGst14aa.getString("absvl");
//                    Double median = rsGst14aa.getDouble("median");
//                    Double numerator = rsGst14aa.getDouble("numerator");
//                    String ra = "Commissioner (Appeals)";
//                     Zonal_rank = null;
//
//
//                    String formattedTotal = String.format("%.2f", tScore);
//                    double total_score = Double.parseDouble(formattedTotal);
//
//                    int way_to_grade;
//                    Integer insentavization = 0;
//
//                    // Logic based on parameter type
//                    if ("GST12A".equalsIgnoreCase(gst)) {
//                        way_to_grade = score.c_marks12a(total_score,numerator);
//                        insentavization = score.c_marks12a(total_score,numerator);
//
//                        if (numerator > median && way_to_grade < 10) {
//                            insentavization += 1;
//                        }
//                    } else if ("GST12B".equalsIgnoreCase(gst)) {
//                        way_to_grade = score.c_marks12b(total_score);
//                        insentavization = way_to_grade;
//                    } else {
//                        // Default handling if parameter type is neither 5a nor 5b
//                        way_to_grade = 0;
//                        insentavization = 0;
//                    }
//
//                    Double sub_parameter_weighted_average = insentavization * 0.5;
//                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
//                    allGstaList.add(totalScore);
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
        return allGstaList.stream()
                .sorted(Comparator.comparing(TotalScore::getSub_parameter_weighted_average).reversed()).collect(Collectors.toList());
    }
}
