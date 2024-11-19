package com.ornates.cbic.controller;

import com.ornates.cbic.dao.Query.CustomSubParameterWiseQuery;
import com.ornates.cbic.dao.result.GetExecutionSQL;
import com.ornates.cbic.model.response.GST4A;
import com.ornates.cbic.service.CustomGreadeScore;
import com.ornates.cbic.service.CustomRelaventAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// In this controller we will try to fatch data from cus6a,6b,6c,6d,6e,6f and try to make parameter
@Controller
@RequestMapping("/controller/Custom")
public class TestingController3 {
    private Logger logger = LoggerFactory.getLogger(CustomMISReportsController.class);
    CustomGreadeScore score = new CustomGreadeScore();

    @ResponseBody
    @RequestMapping(value ="/cus6a")
    //  http://localhost:8080/cbicApi/controller/Custom/cus6a?month_date=2024-04-01&type=zone
    public Object getCus6a(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        double median = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryCustom5b = new CustomSubParameterWiseQuery().QueryFor_cus6a_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom5b);
                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus6a_RA;
                    String commname = "ALL";
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col9_3a = rsGst14aa.getInt("col9_3a");
                    int col9_3b = rsGst14aa.getInt("col9_3b");
                    int col3_3a = rsGst14aa.getInt("col3_3a");
                    int col3_3b = rsGst14aa.getInt("col3_3b");
                    median = rsGst14aa.getDouble("median_6a");
                    Double numerator_6c = rsGst14aa.getDouble("numerator_9");
                    int Zonal_rank = 0;
                    String gst = "no";
                    //String absval=String.valueOf(col9_3a+col9_3b)+"/"+String.valueOf(col3_3a+col3_3b);
                    String absval = "";
                    if (!(col9_3a + col9_3b == 0 && col3_3a + col3_3b == 0)) {
                        absval = String.valueOf(col9_3a + col9_3b) + "/" + String.valueOf(col3_3a + col3_3b);
                    }
                    if ((col3_3a + col3_3b) != 0) {
                        total = ((double) (col9_3a + col9_3b) * 100 / (col3_3a + col3_3b));
                    } else {
                        total = 0.00;
                    }
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks6a(totalScore);
                    int insentavization = score.c_marks6a(totalScore);
                    if (numerator_6c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    double sub_parameter_weighted_average = insentavization * 0.2;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    if (!(col9_3a + col9_3b == 0 && col3_3a + col3_3b == 0)) {
                        gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore, absval, zoneCode, ra,
                                Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
                        allGstaList.add(gsta);
                    }
                    allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));
                }
                System.out.println("median cus6a zone wise :-" + median);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus6B*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus6b")
    //  http://localhost:8080/cbicApi/controller/Custom/cus6b?month_date=2024-04-01&type=zone
    public Object getCus6b(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string
                String queryCustom6b = new CustomSubParameterWiseQuery().QueryFor_cus6b_ZoneWise(month_date);
                //Result Set
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom6b);
                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus6b_RA;
                    String commname = "ALL";
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col18_3a = rsGst14aa.getInt("col18_3a");
                    int col18_3b = rsGst14aa.getInt("col18_3b");
                    int col12_3a = rsGst14aa.getInt("col12_3a");
                    int col12_3b = rsGst14aa.getInt("col12_3b");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    // String absval=String.valueOf(col18_3a+col18_3b)+"/"+String.valueOf(col12_3a+col12_3b);
                    String absval = "";
                    if (!(col18_3a+col18_3b == 0 && col12_3a+col12_3b== 0)) {
                        absval = String.valueOf(col18_3a+col18_3b ) + "/" + String.valueOf(col12_3a+col12_3b);
                    }
                    if((col12_3a+col12_3b) != 0) {
                        total = ((double) (col18_3a+col18_3b) * 100 / (col12_3a+col12_3b));
                    }else {
                        total = 0.00;
                    }
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks6b(totalScore);
                    double sub_parameter_weighted_average = way_to_grade * 0.2;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    if (!(col18_3a+col18_3b == 0 && col12_3a+col12_3b== 0)){
                        gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                                Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                        allGstaList.add(gsta);}
                    allGstaList.sort((a, b) -> Double.compare(a.getTotal_score(), b.getTotal_score()));
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus6c*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus6c")
    //  http://localhost:8080/cbicApi/controller/Custom/cus6c?month_date=2024-04-01&type=zone
    public Object getCus6c(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string
                String queryCustom6c = new CustomSubParameterWiseQuery().QueryFor_cus6c_ZoneWise(month_date);
                //Result Set
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom6c);
                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus6c_RA;
                    String commname = "ALL";
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col18_1 = rsGst14aa.getInt("col18_1");
                    int col8_ddm = rsGst14aa.getInt("col8_ddm");
                    int col9_ddm = rsGst14aa.getInt("col9_ddm");
                    double median = rsGst14aa.getDouble("median_6c");
                    Double numerator_6c = rsGst14aa.getDouble("col18_1");
                    String absval = rsGst14aa.getString("abs_value_pq");
                    int Zonal_rank = 0;
                    String gst = "no";
                    if((col8_ddm+col9_ddm) != 0) {
                        total = ((double) (col18_1) * 100 / (col8_ddm+col9_ddm));
                    }else {
                        total = 0.00;
                    }
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks6c(totalScore);
                    int insentavization = score.c_marks6c(totalScore);
                    if (numerator_6c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    double sub_parameter_weighted_average = insentavization * 0.2;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                    allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=cus6c*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    @ResponseBody
    @RequestMapping(value = "/cus6d")
    //  http://localhost:8080/cbicApi/controller/Custom/cus6d?month_date=2024-04-01&type=zone
    public Object getCus6d(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryCustom6d = new CustomSubParameterWiseQuery().QueryFor_cus6d_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryCustom6d);
                while (rsGst14aa.next()) {
                    String ra = CustomRelaventAspect.cus6d_RA;
                    String commname = "ALL";
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col4_7 = rsGst14aa.getInt("col4_7");
                    int col5_cus3a = rsGst14aa.getInt("col5_cus3a");
                    int col8_cus3a = rsGst14aa.getInt("col8_cus3a");
                    int col5_cus3b = rsGst14aa.getInt("col5_cus3b");
                    int col8_cus3b = rsGst14aa.getInt("col8_cus3b");
                    double median = rsGst14aa.getDouble("median_6c");
                    Double numerator_6c = rsGst14aa.getDouble("col4_7");
                    String absval = rsGst14aa.getString("absolute_value");
                    total = rsGst14aa.getDouble("total_score");

                    int Zonal_rank = 0;
                    String gst = "no";

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.c_marks6d(totalScore);
                    int insentavization = score.c_marks6d(totalScore);
                    if (numerator_6c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // 2 floating point
                    double sub_parameter_weighted_average = insentavization * 0.1;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                    allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }
}
