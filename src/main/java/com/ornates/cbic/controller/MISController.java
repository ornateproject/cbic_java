package com.ornates.cbic.controller;

import com.ornates.cbic.dao.Query.MISQuery;
import com.ornates.cbic.dao.pool.JDBCConnection;
import com.ornates.cbic.dao.result.GetExecutionSQL;
import com.ornates.cbic.model.response.TotalScore;
import com.ornates.cbic.service.GradeScore;
import com.ornates.cbic.service.RelevantAspect;
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

//@CrossOrigin
@RequestMapping("/cbic/MIS")
@Controller
public class MISController {
    GradeScore score=new GradeScore();
    @ResponseBody
    @RequestMapping(value = "/")
    public String home() {
        return "its working api";
    }

    /*
     * Date: june 4, 2024
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

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=registration__1__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    @ResponseBody
    @RequestMapping(value = "/registration") //1
    //  http://localhost:8080/cbicApi/cbic/MIS/registration?month_date=2024-04-01&zone_code=59&type=CurrentMonth
    //  http://localhost:8080/cbicApi/cbic/MIS/registration?month_date=2024-04-01&zone_code=59&type=1_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/registration?month_date=2024-04-01&zone_code=59&type=2_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/registration?month_date=2024-04-01&zone_code=59&type=3_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/registration?month_date=2024-04-01&zone_code=59&type=4_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/registration?month_date=2024-04-01&zone_code=59&type=5_MonthBack
    public Object registration(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<TotalScore> allGstaList = new ArrayList<>();
        TotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        //double median =0.00;

        try {
            if (type.equalsIgnoreCase("CurrentMonth")) {
                String query_assessment = new MISQuery().QueryFor_Registration_CurrentMonth(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    double total_score = rsGst14aa.getDouble("total_score");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer Zonal_rank = rsGst14aa.getInt("z_rank");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String ra ="Registration";
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    Integer way_to_grade = 0;
                    Integer insentavization = 0;
                    double sub_parameter_weighted_average = 0.00;

                    totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(totalScore);

                }

            }else if (type.equalsIgnoreCase("1_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Registration_1_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    double total_score = rsGst14aa.getDouble("total_score");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer Zonal_rank = rsGst14aa.getInt("z_rank");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String ra ="Registration";
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    Integer way_to_grade = 0;
                    Integer insentavization = 0;
                    double sub_parameter_weighted_average = 0.00;

                    totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("2_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Registration_2_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    double total_score = rsGst14aa.getDouble("total_score");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer Zonal_rank = rsGst14aa.getInt("z_rank");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String ra ="Registration";
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    Integer way_to_grade = 0;
                    Integer insentavization = 0;
                    double sub_parameter_weighted_average = 0.00;

                    totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("3_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Registration_3_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    double total_score = rsGst14aa.getDouble("total_score");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer Zonal_rank = rsGst14aa.getInt("z_rank");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String ra ="Registration";
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    Integer way_to_grade = 0;
                    Integer insentavization = 0;
                    double sub_parameter_weighted_average = 0.00;

                    totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("4_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Registration_4_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    double total_score = rsGst14aa.getDouble("total_score");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer Zonal_rank = rsGst14aa.getInt("z_rank");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String ra ="Registration";
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    Integer way_to_grade = 0;
                    Integer insentavization = 0;
                    double sub_parameter_weighted_average = 0.00;

                    totalScore = new TotalScore(zoneName, commName,zone_code, total_score, absval, Zonal_rank, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("5_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Registration_5_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    double total_score = rsGst14aa.getDouble("total_score");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer Zonal_rank = rsGst14aa.getInt("z_rank");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String ra ="Registration";
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    Integer way_to_grade = 0;
                    Integer insentavization = 0;
                    double sub_parameter_weighted_average = 0.00;

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
        return allGstaList.stream()
                .sorted(Comparator.comparing(TotalScore::getSub_parameter_weighted_average).reversed())
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=returnFiling__2__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    @ResponseBody
    @RequestMapping(value = "/returnFiling") //2
    //  http://localhost:8080/cbicApi/cbic/MIS/returnFiling?month_date=2024-04-01&zone_code=59&type=CurrentMonth
    //  http://localhost:8080/cbicApi/cbic/MIS/returnFiling?month_date=2024-04-01&zone_code=59&type=1_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/returnFiling?month_date=2024-04-01&zone_code=59&type=2_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/returnFiling?month_date=2024-04-01&zone_code=59&type=3_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/returnFiling?month_date=2024-04-01&zone_code=59&type=4_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/returnFiling?month_date=2024-04-01&zone_code=59&type=5_MonthBack
    public Object returnFiling(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<TotalScore> allGstaList = new ArrayList<>();
        TotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        //double median =0.00;

        try {

            if (type.equalsIgnoreCase("CurrentMonth")) {
                String query_assessment = new MISQuery().QueryFor_ReturnFiling_CurrentMonth(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);

                }

            }else if (type.equalsIgnoreCase("1_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_ReturnFiling_1_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("2_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_ReturnFiling_2_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("3_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_ReturnFiling_3_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("4_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_ReturnFiling_4_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("5_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_ReturnFiling_5_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
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
        return allGstaList.stream()
                .sorted(Comparator.comparing(TotalScore::getSub_parameter_weighted_average).reversed())
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=scrutiny/assessment__3__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    @ResponseBody
    @RequestMapping(value = "/scrutiny/assessment") //3
    //  http://localhost:8080/cbicApi/cbic/MIS/scrutiny/assessment?month_date=2024-04-01&zone_code=59&type=CurrentMonth
    //  http://localhost:8080/cbicApi/cbic/MIS/scrutiny/assessment?month_date=2024-04-01&zone_code=59&type=1_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/scrutiny/assessment?month_date=2024-04-01&zone_code=59&type=2_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/scrutiny/assessment?month_date=2024-04-01&zone_code=59&type=3_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/scrutiny/assessment?month_date=2024-04-01&zone_code=59&type=4_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/scrutiny/assessment?month_date=2024-04-01&zone_code=59&type=5_MonthBack
    public Object scrutinyAssessment(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<TotalScore> allGstaList = new ArrayList<>();
        TotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        //double median =0.00;

        try {

            if (type.equalsIgnoreCase("CurrentMonth")) {
                String query_assessment = new MISQuery().QueryFor_Scrutiny_CurrentMonth(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double total3a = rsGst14aa.getDouble("score_of_subpara3a");
                    double total3b = rsGst14aa.getDouble("score_of_parameter3b");

                    double median3a = rsGst14aa.getDouble("median_numerator_3a");
                    double median3b = rsGst14aa.getDouble("median_numerator_3b");

                    Double numerator_3a = rsGst14aa.getDouble("numerator_3a");
                    Double numerator_3b = rsGst14aa.getDouble("numerator_3b");

                    int way_to_grade3a = score.marks3a(total3a);
                    int way_to_grade3b = score.marks3b(total3b);

                    int insentavization3a = way_to_grade3a;
                    int insentavization3b = way_to_grade3b;

                    if (numerator_3a > median3a && way_to_grade3a < 10) {
                        insentavization3a += 1;
                    }
                    if (numerator_3b > median3b && way_to_grade3b < 10) {
                        insentavization3b += 1;
                    }

                    Integer way_to_grade = way_to_grade3a + way_to_grade3b;
                    Integer insentavization = insentavization3a + insentavization3b;

                    double sub_parameter_weighted_average3a = insentavization3a * 0.5;
                    double sub_parameter_weighted_average3b = insentavization3b * 0.5;

                    double total_weighted_average = sub_parameter_weighted_average3a + sub_parameter_weighted_average3b;
                    double total_score = 0.00;
                    Integer Zonal_rank = 0;
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    String ra = "SCRUTINY & ASSESSMENT";
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
                    allGstaList.add(totalScore);

                }

            }else if (type.equalsIgnoreCase("1_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Scrutiny_1_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double total3a = rsGst14aa.getDouble("score_of_subpara3a");
                    double total3b = rsGst14aa.getDouble("score_of_parameter3b");

                    double median3a = rsGst14aa.getDouble("median_numerator_3a");
                    double median3b = rsGst14aa.getDouble("median_numerator_3b");

                    Double numerator_3a = rsGst14aa.getDouble("numerator_3a");
                    Double numerator_3b = rsGst14aa.getDouble("numerator_3b");

                    int way_to_grade3a = score.marks3a(total3a);
                    int way_to_grade3b = score.marks3b(total3b);

                    int insentavization3a = way_to_grade3a;
                    int insentavization3b = way_to_grade3b;

                    if (numerator_3a > median3a && way_to_grade3a < 10) {
                        insentavization3a += 1;
                    }
                    if (numerator_3b > median3b && way_to_grade3b < 10) {
                        insentavization3b += 1;
                    }

                    Integer way_to_grade = way_to_grade3a + way_to_grade3b;
                    Integer insentavization = insentavization3a + insentavization3b;

                    double sub_parameter_weighted_average3a = insentavization3a * 0.5;
                    double sub_parameter_weighted_average3b = insentavization3b * 0.5;

                    double total_weighted_average = sub_parameter_weighted_average3a + sub_parameter_weighted_average3b;
                    double total_score = 0.00;
                    Integer Zonal_rank = 0;
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    String ra = "SCRUTINY & ASSESSMENT";
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("2_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Scrutiny_2_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double total3a = rsGst14aa.getDouble("score_of_subpara3a");
                    double total3b = rsGst14aa.getDouble("score_of_parameter3b");

                    double median3a = rsGst14aa.getDouble("median_numerator_3a");
                    double median3b = rsGst14aa.getDouble("median_numerator_3b");

                    Double numerator_3a = rsGst14aa.getDouble("numerator_3a");
                    Double numerator_3b = rsGst14aa.getDouble("numerator_3b");

                    int way_to_grade3a = score.marks3a(total3a);
                    int way_to_grade3b = score.marks3b(total3b);

                    int insentavization3a = way_to_grade3a;
                    int insentavization3b = way_to_grade3b;

                    if (numerator_3a > median3a && way_to_grade3a < 10) {
                        insentavization3a += 1;
                    }
                    if (numerator_3b > median3b && way_to_grade3b < 10) {
                        insentavization3b += 1;
                    }

                    Integer way_to_grade = way_to_grade3a + way_to_grade3b;
                    Integer insentavization = insentavization3a + insentavization3b;

                    double sub_parameter_weighted_average3a = insentavization3a * 0.5;
                    double sub_parameter_weighted_average3b = insentavization3b * 0.5;

                    double total_weighted_average = sub_parameter_weighted_average3a + sub_parameter_weighted_average3b;
                    double total_score = 0.00;
                    Integer Zonal_rank = 0;
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    String ra = "SCRUTINY & ASSESSMENT";
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("3_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Scrutiny_3_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double total3a = rsGst14aa.getDouble("score_of_subpara3a");
                    double total3b = rsGst14aa.getDouble("score_of_parameter3b");

                    double median3a = rsGst14aa.getDouble("median_numerator_3a");
                    double median3b = rsGst14aa.getDouble("median_numerator_3b");

                    Double numerator_3a = rsGst14aa.getDouble("numerator_3a");
                    Double numerator_3b = rsGst14aa.getDouble("numerator_3b");

                    int way_to_grade3a = score.marks3a(total3a);
                    int way_to_grade3b = score.marks3b(total3b);

                    int insentavization3a = way_to_grade3a;
                    int insentavization3b = way_to_grade3b;

                    if (numerator_3a > median3a && way_to_grade3a < 10) {
                        insentavization3a += 1;
                    }
                    if (numerator_3b > median3b && way_to_grade3b < 10) {
                        insentavization3b += 1;
                    }

                    Integer way_to_grade = way_to_grade3a + way_to_grade3b;
                    Integer insentavization = insentavization3a + insentavization3b;

                    double sub_parameter_weighted_average3a = insentavization3a * 0.5;
                    double sub_parameter_weighted_average3b = insentavization3b * 0.5;

                    double total_weighted_average = sub_parameter_weighted_average3a + sub_parameter_weighted_average3b;
                    double total_score = 0.00;
                    Integer Zonal_rank = 0;
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    String ra = "SCRUTINY & ASSESSMENT";
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("4_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Scrutiny_4_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double total3a = rsGst14aa.getDouble("score_of_subpara3a");
                    double total3b = rsGst14aa.getDouble("score_of_parameter3b");

                    double median3a = rsGst14aa.getDouble("median_numerator_3a");
                    double median3b = rsGst14aa.getDouble("median_numerator_3b");

                    Double numerator_3a = rsGst14aa.getDouble("numerator_3a");
                    Double numerator_3b = rsGst14aa.getDouble("numerator_3b");

                    int way_to_grade3a = score.marks3a(total3a);
                    int way_to_grade3b = score.marks3b(total3b);

                    int insentavization3a = way_to_grade3a;
                    int insentavization3b = way_to_grade3b;

                    if (numerator_3a > median3a && way_to_grade3a < 10) {
                        insentavization3a += 1;
                    }
                    if (numerator_3b > median3b && way_to_grade3b < 10) {
                        insentavization3b += 1;
                    }

                    Integer way_to_grade = way_to_grade3a + way_to_grade3b;
                    Integer insentavization = insentavization3a + insentavization3b;

                    double sub_parameter_weighted_average3a = insentavization3a * 0.5;
                    double sub_parameter_weighted_average3b = insentavization3b * 0.5;

                    double total_weighted_average = sub_parameter_weighted_average3a + sub_parameter_weighted_average3b;
                    double total_score = 0.00;
                    Integer Zonal_rank = 0;
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    String ra = "SCRUTINY & ASSESSMENT";
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("5_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Scrutiny_5_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double total3a = rsGst14aa.getDouble("score_of_subpara3a");
                    double total3b = rsGst14aa.getDouble("score_of_parameter3b");

                    double median3a = rsGst14aa.getDouble("median_numerator_3a");
                    double median3b = rsGst14aa.getDouble("median_numerator_3b");

                    Double numerator_3a = rsGst14aa.getDouble("numerator_3a");
                    Double numerator_3b = rsGst14aa.getDouble("numerator_3b");

                    int way_to_grade3a = score.marks3a(total3a);
                    int way_to_grade3b = score.marks3b(total3b);

                    int insentavization3a = way_to_grade3a;
                    int insentavization3b = way_to_grade3b;

                    if (numerator_3a > median3a && way_to_grade3a < 10) {
                        insentavization3a += 1;
                    }
                    if (numerator_3b > median3b && way_to_grade3b < 10) {
                        insentavization3b += 1;
                    }

                    Integer way_to_grade = way_to_grade3a + way_to_grade3b;
                    Integer insentavization = insentavization3a + insentavization3b;

                    double sub_parameter_weighted_average3a = insentavization3a * 0.5;
                    double sub_parameter_weighted_average3b = insentavization3b * 0.5;

                    double total_weighted_average = sub_parameter_weighted_average3a + sub_parameter_weighted_average3b;
                    double total_score = 0.00;
                    Integer Zonal_rank = 0;
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    String ra = "SCRUTINY & ASSESSMENT";
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
                    allGstaList.add(totalScore);
                }            }
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
                .sorted(Comparator.comparing(TotalScore::getSub_parameter_weighted_average).reversed())
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Investigation__4__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    @ResponseBody
    @RequestMapping(value = "/Investigation") //4
    //  http://localhost:8080/cbicApi/cbic/MIS/Investigation?month_date=2024-04-01&zone_code=59&type=CurrentMonth
    //  http://localhost:8080/cbicApi/cbic/MIS/Investigation?month_date=2024-04-01&zone_code=59&type=1_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/Investigation?month_date=2024-04-01&zone_code=59&type=2_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/Investigation?month_date=2024-04-01&zone_code=59&type=3_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/Investigation?month_date=2024-04-01&zone_code=59&type=4_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/Investigation?month_date=2024-04-01&zone_code=59&type=5_MonthBack
    public Object investigation(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<TotalScore> allGstaList = new ArrayList<>();
        TotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        //double median =0.00;

        try {

            if (type.equalsIgnoreCase("CurrentMonth")) {
                String query_assessment = new MISQuery().QueryFor_Investigation_CurrentMonth(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);

                }

            }else if (type.equalsIgnoreCase("1_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Investigation_1_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("2_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Investigation_2_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("3_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Investigation_3_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("4_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Investigation_4_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("5_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Investigation_5_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
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
        return allGstaList.stream()
                .sorted(Comparator.comparing(TotalScore::getSub_parameter_weighted_average).reversed())
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=adjudication__5__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    @ResponseBody
    @RequestMapping(value = "/adjudication") //5
    //  http://localhost:8080/cbicApi/cbic/MIS/adjudication?month_date=2024-04-01&zone_code=59&type=CurrentMonth
    //  http://localhost:8080/cbicApi/cbic/MIS/adjudication?month_date=2024-04-01&zone_code=59&type=1_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/adjudication?month_date=2024-04-01&zone_code=59&type=2_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/adjudication?month_date=2024-04-01&zone_code=59&type=3_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/adjudication?month_date=2024-04-01&zone_code=59&type=4_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/adjudication?month_date=2024-04-01&zone_code=59&type=5_MonthBack
    public Object adjudication(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<TotalScore> allGstaList = new ArrayList<>();
        TotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;

        try {

            if (type.equalsIgnoreCase("CurrentMonth")) {
                String query_assessment = new MISQuery().QueryFor_Adjudication_CurrentMonth(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double total5a = rsGst14aa.getDouble("score_of_subparameter5a");
                    double total5b = rsGst14aa.getDouble("score_of_subparameter5b");

                    double median5a = rsGst14aa.getDouble("median5a");

                    Double numerator_5a = rsGst14aa.getDouble("col10");

                    int way_to_grade5a = score.marks5a(total5a);
                    int way_to_grade5b = score.marks5b(total5b);

                    int insentavization5a = way_to_grade5a;
                    int insentavization5b = way_to_grade5b;

                    if (numerator_5a > median5a && way_to_grade5a < 10) {
                        insentavization5a += 1;
                    }
                    Integer way_to_grade = way_to_grade5a + way_to_grade5b;
                    Integer insentavization = insentavization5a + insentavization5b;

                    double sub_parameter_weighted_average5a = insentavization5a * 0.5;
                    double sub_parameter_weighted_average5b = insentavization5b * 0.5;

                    double total_weighted_average = sub_parameter_weighted_average5a + sub_parameter_weighted_average5b;

                    double totol_score = 0.00;
                    Integer Zonal_rank = rsGst14aa.getInt("z_rank");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    String ra ="Adjudication";

                    totalScore = new TotalScore(zoneName, commName, zone_code, totol_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
                    allGstaList.add(totalScore);
                }

            }else if (type.equalsIgnoreCase("1_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Adjudication_1_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double total5a = rsGst14aa.getDouble("score_of_subparameter5a");
                    double total5b = rsGst14aa.getDouble("score_of_subparameter5b");

                    double median5a = rsGst14aa.getDouble("median5a");

                    Double numerator_5a = rsGst14aa.getDouble("col10");

                    int way_to_grade5a = score.marks5a(total5a);
                    int way_to_grade5b = score.marks5b(total5b);

                    int insentavization5a = way_to_grade5a;
                    int insentavization5b = way_to_grade5b;

                    if (numerator_5a > median5a && way_to_grade5a < 10) {
                        insentavization5a += 1;
                    }
                    Integer way_to_grade = way_to_grade5a + way_to_grade5b;
                    Integer insentavization = insentavization5a + insentavization5b;

                    double sub_parameter_weighted_average5a = insentavization5a * 0.5;
                    double sub_parameter_weighted_average5b = insentavization5b * 0.5;

                    double total_weighted_average = sub_parameter_weighted_average5a + sub_parameter_weighted_average5b;

                    double totol_score = 0.00;
                    Integer Zonal_rank = rsGst14aa.getInt("z_rank");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    String ra ="Adjudication";

                    totalScore = new TotalScore(zoneName, commName, zone_code, totol_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("2_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Adjudication_2_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double total5a = rsGst14aa.getDouble("score_of_subparameter5a");
                    double total5b = rsGst14aa.getDouble("score_of_subparameter5b");

                    double median5a = rsGst14aa.getDouble("median5a");

                    Double numerator_5a = rsGst14aa.getDouble("col10");

                    int way_to_grade5a = score.marks5a(total5a);
                    int way_to_grade5b = score.marks5b(total5b);

                    int insentavization5a = way_to_grade5a;
                    int insentavization5b = way_to_grade5b;

                    if (numerator_5a > median5a && way_to_grade5a < 10) {
                        insentavization5a += 1;
                    }
                    Integer way_to_grade = way_to_grade5a + way_to_grade5b;
                    Integer insentavization = insentavization5a + insentavization5b;

                    double sub_parameter_weighted_average5a = insentavization5a * 0.5;
                    double sub_parameter_weighted_average5b = insentavization5b * 0.5;

                    double total_weighted_average = sub_parameter_weighted_average5a + sub_parameter_weighted_average5b;

                    double totol_score = 0.00;
                    Integer Zonal_rank = rsGst14aa.getInt("z_rank");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    String ra ="Adjudication";

                    totalScore = new TotalScore(zoneName, commName, zone_code, totol_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("3_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Adjudication_3_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double total5a = rsGst14aa.getDouble("score_of_subparameter5a");
                    double total5b = rsGst14aa.getDouble("score_of_subparameter5b");

                    double median5a = rsGst14aa.getDouble("median5a");

                    Double numerator_5a = rsGst14aa.getDouble("col10");

                    int way_to_grade5a = score.marks5a(total5a);
                    int way_to_grade5b = score.marks5b(total5b);

                    int insentavization5a = way_to_grade5a;
                    int insentavization5b = way_to_grade5b;

                    if (numerator_5a > median5a && way_to_grade5a < 10) {
                        insentavization5a += 1;
                    }
                    Integer way_to_grade = way_to_grade5a + way_to_grade5b;
                    Integer insentavization = insentavization5a + insentavization5b;

                    double sub_parameter_weighted_average5a = insentavization5a * 0.5;
                    double sub_parameter_weighted_average5b = insentavization5b * 0.5;

                    double total_weighted_average = sub_parameter_weighted_average5a + sub_parameter_weighted_average5b;

                    double totol_score = 0.00;
                    Integer Zonal_rank = rsGst14aa.getInt("z_rank");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    String ra ="Adjudication";

                    totalScore = new TotalScore(zoneName, commName, zone_code, totol_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("4_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Adjudication_4_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double total5a = rsGst14aa.getDouble("score_of_subparameter5a");
                    double total5b = rsGst14aa.getDouble("score_of_subparameter5b");

                    double median5a = rsGst14aa.getDouble("median5a");

                    Double numerator_5a = rsGst14aa.getDouble("col10");

                    int way_to_grade5a = score.marks5a(total5a);
                    int way_to_grade5b = score.marks5b(total5b);

                    int insentavization5a = way_to_grade5a;
                    int insentavization5b = way_to_grade5b;

                    if (numerator_5a > median5a && way_to_grade5a < 10) {
                        insentavization5a += 1;
                    }
                    Integer way_to_grade = way_to_grade5a + way_to_grade5b;
                    Integer insentavization = insentavization5a + insentavization5b;

                    double sub_parameter_weighted_average5a = insentavization5a * 0.5;
                    double sub_parameter_weighted_average5b = insentavization5b * 0.5;

                    double total_weighted_average = sub_parameter_weighted_average5a + sub_parameter_weighted_average5b;

                    double totol_score = 0.00;
                    Integer Zonal_rank = rsGst14aa.getInt("z_rank");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    String ra ="Adjudication";

                    totalScore = new TotalScore(zoneName, commName, zone_code, totol_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("5_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Adjudication_5_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    zone_code = rsGst14aa.getString("ZONE_CODE");

                    double total5a = rsGst14aa.getDouble("score_of_subparameter5a");
                    double total5b = rsGst14aa.getDouble("score_of_subparameter5b");

                    double median5a = rsGst14aa.getDouble("median5a");

                    Double numerator_5a = rsGst14aa.getDouble("col10");

                    int way_to_grade5a = score.marks5a(total5a);
                    int way_to_grade5b = score.marks5b(total5b);

                    int insentavization5a = way_to_grade5a;
                    int insentavization5b = way_to_grade5b;

                    if (numerator_5a > median5a && way_to_grade5a < 10) {
                        insentavization5a += 1;
                    }
                    Integer way_to_grade = way_to_grade5a + way_to_grade5b;
                    Integer insentavization = insentavization5a + insentavization5b;

                    double sub_parameter_weighted_average5a = insentavization5a * 0.5;
                    double sub_parameter_weighted_average5b = insentavization5b * 0.5;

                    double total_weighted_average = sub_parameter_weighted_average5a + sub_parameter_weighted_average5b;

                    double totol_score = 0.00;
                    Integer Zonal_rank = rsGst14aa.getInt("z_rank");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = "null";
                    String ra ="Adjudication";

                    totalScore = new TotalScore(zoneName, commName, zone_code, totol_score, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, total_weighted_average);
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
        return allGstaList.stream()
                .sorted(Comparator.comparing(TotalScore::getSub_parameter_weighted_average).reversed())
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=adjudication(legacy cases__6__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    @ResponseBody
    @RequestMapping(value = "/adjudication(legacy cases)") //6
    //  http://localhost:8080/cbicApi/cbic/MIS/adjudication(legacy cases)?month_date=2024-04-01&zone_code=59&type=CurrentMonth
    //  http://localhost:8080/cbicApi/cbic/MIS/adjudication(legacy cases)?month_date=2024-04-01&zone_code=59&type=1_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/adjudication(legacy cases)?month_date=2024-04-01&zone_code=59&type=2_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/adjudication(legacy cases)?month_date=2024-04-01&zone_code=59&type=3_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/adjudication(legacy cases)?month_date=2024-04-01&zone_code=59&type=4_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/adjudication(legacy cases)?month_date=2024-04-01&zone_code=59&type=5_MonthBack
    public Object adjudicationLegacy(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<TotalScore> allGstaList = new ArrayList<>();
        TotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        //double median =0.00;

        try {

            if (type.equalsIgnoreCase("CurrentMonth")) {
                String query_assessment = new MISQuery().QueryFor_Adjudication_Legacy_CurrentMonth(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);

                }

            }else if (type.equalsIgnoreCase("1_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Adjudication_Legacy_1_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("2_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Adjudication_Legacy_2_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("3_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Adjudication_Legacy_3_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("4_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Adjudication_Legacy_4_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("5_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Adjudication_Legacy_5_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
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
        return allGstaList.stream()
                .sorted(Comparator.comparing(TotalScore::getSub_parameter_weighted_average).reversed())
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=refunds__7__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    @ResponseBody
    @RequestMapping(value = "/refunds") //7
    //  http://localhost:8080/cbicApi/cbic/MIS/refunds?month_date=2024-04-01&zone_code=59&type=CurrentMonth
    //  http://localhost:8080/cbicApi/cbic/MIS/refunds?month_date=2024-04-01&zone_code=59&type=1_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/refunds?month_date=2024-04-01&zone_code=59&type=2_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/refunds?month_date=2024-04-01&zone_code=59&type=3_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/refunds?month_date=2024-04-01&zone_code=59&type=4_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/refunds?month_date=2024-04-01&zone_code=59&type=5_MonthBack
    public Object refunds(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<TotalScore> allGstaList = new ArrayList<>();
        TotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        //double median =0.00;

        try {

            if (type.equalsIgnoreCase("CurrentMonth")) {
                String query_assessment = new MISQuery().QueryFor_Refunds_CurrentMonth(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double tScore = rsGst14aa.getDouble("total_score"); // 100 is multiply in query
                    String absval = rsGst14aa.getString("absolute_value");
                    String commName = "ALL";
                    String gst = "ALL";
                    //String ra ="null";
                    String ra = RelevantAspect.Gst7_RA;

                    String formattedTotal = String.format("%.2f", tScore);
                    double total_score = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks7(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    //System.out.println(way_to_grade);
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }

            }else if (type.equalsIgnoreCase("1_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Refunds_1_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double tScore = rsGst14aa.getDouble("total_score"); // 100 is multiply in query
                    String absval = rsGst14aa.getString("absolute_value");
                    String commName = "ALL";
                    String gst = "ALL";
                    //String ra ="null";
                    String ra = RelevantAspect.Gst7_RA;

                    String formattedTotal = String.format("%.2f", tScore);
                    double total_score = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks7(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    //System.out.println(way_to_grade);
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("2_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Refunds_2_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double tScore = rsGst14aa.getDouble("total_score"); // 100 is multiply in query
                    String absval = rsGst14aa.getString("absolute_value");
                    String commName = "ALL";
                    String gst = "ALL";
                    //String ra ="null";
                    String ra = RelevantAspect.Gst7_RA;

                    String formattedTotal = String.format("%.2f", tScore);
                    double total_score = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks7(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    //System.out.println(way_to_grade);
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("3_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Refunds_3_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double tScore = rsGst14aa.getDouble("total_score"); // 100 is multiply in query
                    String absval = rsGst14aa.getString("absolute_value");
                    String commName = "ALL";
                    String gst = "ALL";
                    //String ra ="null";
                    String ra = RelevantAspect.Gst7_RA;

                    String formattedTotal = String.format("%.2f", tScore);
                    double total_score = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks7(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    //System.out.println(way_to_grade);
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("4_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Refunds_4_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double tScore = rsGst14aa.getDouble("total_score"); // 100 is multiply in query
                    String absval = rsGst14aa.getString("absolute_value");
                    String commName = "ALL";
                    String gst = "ALL";
                    //String ra ="null";
                    String ra = RelevantAspect.Gst7_RA;

                    String formattedTotal = String.format("%.2f", tScore);
                    double total_score = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks7(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    //System.out.println(way_to_grade);
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("5_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_Refunds_5_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double tScore = rsGst14aa.getDouble("total_score"); // 100 is multiply in query
                    String absval = rsGst14aa.getString("absolute_value");
                    String commName = "ALL";
                    String gst = "ALL";
                    //String ra ="null";
                    String ra = RelevantAspect.Gst7_RA;

                    String formattedTotal = String.format("%.2f", tScore);
                    double total_score = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks7(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    //System.out.println(way_to_grade);
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst,ra,way_to_grade,insentavization,sub_parameter_weighted_average);
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
        return allGstaList.stream()
                .sorted(Comparator.comparing(TotalScore::getSub_parameter_weighted_average).reversed())
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=recovery of arrears__8__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    @ResponseBody
    @RequestMapping(value = "/recovery of arrears") //8
    //  http://localhost:8080/cbicApi/cbic/MIS/recovery of arrears?month_date=2024-04-01&zone_code=59&type=CurrentMonth
    //  http://localhost:8080/cbicApi/cbic/MIS/recovery of arrears?month_date=2024-04-01&zone_code=59&type=1_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/recovery of arrears?month_date=2024-04-01&zone_code=59&type=2_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/recovery of arrears?month_date=2024-04-01&zone_code=59&type=3_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/recovery of arrears?month_date=2024-04-01&zone_code=59&type=4_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/recovery of arrears?month_date=2024-04-01&zone_code=59&type=5_MonthBack
    public Object recovery(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<TotalScore> allGstaList = new ArrayList<>();
        TotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        //double median =0.00;

        try {

            if (type.equalsIgnoreCase("CurrentMonth")) {
                String query_assessment = new MISQuery().QueryFor_RecoveryOfArrears_CurrentMonth(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);

                }

            }else if (type.equalsIgnoreCase("1_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_RecoveryOfArrears_1_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("2_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_RecoveryOfArrears_2_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("3_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_RecoveryOfArrears_3_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("4_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_RecoveryOfArrears_4_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("5_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_RecoveryOfArrears_5_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
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
        return allGstaList.stream()
                .sorted(Comparator.comparing(TotalScore::getSub_parameter_weighted_average).reversed())
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=arrest and prosecution__9__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    @ResponseBody
    @RequestMapping(value = "/arrest and prosecution") //9
    //  http://localhost:8080/cbicApi/cbic/MIS/arrest and prosecution?month_date=2024-04-01&zone_code=59&type=CurrentMonth
    //  http://localhost:8080/cbicApi/cbic/MIS/arrest and prosecution?month_date=2024-04-01&zone_code=59&type=1_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/arrest and prosecution?month_date=2024-04-01&zone_code=59&type=2_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/arrest and prosecution?month_date=2024-04-01&zone_code=59&type=3_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/arrest and prosecution?month_date=2024-04-01&zone_code=59&type=4_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/arrest and prosecution?month_date=2024-04-01&zone_code=59&type=5_MonthBack
    public Object arrestProsecution(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<TotalScore> allGstaList = new ArrayList<>();
        TotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        //double median =0.00;

        try {

            if (type.equalsIgnoreCase("CurrentMonth")) {
                String query_assessment = new MISQuery().QueryFor_arrestsAndProsecution_CurrentMonth(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);

                }

            }else if (type.equalsIgnoreCase("1_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_arrestsAndProsecution_1_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("2_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_arrestsAndProsecution_2_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("3_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_arrestsAndProsecution_3_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("4_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_arrestsAndProsecution_4_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("5_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_arrestsAndProsecution_5_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
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
        return allGstaList.stream()
                .sorted(Comparator.comparing(TotalScore::getSub_parameter_weighted_average).reversed())
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=audit__10__*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    @ResponseBody
    @RequestMapping(value = "/audit") //10
    //  http://localhost:8080/cbicApi/cbic/MIS/audit?month_date=2024-04-01&zone_code=59&type=CurrentMonth
    //  http://localhost:8080/cbicApi/cbic/MIS/audit?month_date=2024-04-01&zone_code=59&type=1_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/audit?month_date=2024-04-01&zone_code=59&type=2_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/audit?month_date=2024-04-01&zone_code=59&type=3_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/audit?month_date=2024-04-01&zone_code=59&type=4_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/audit?month_date=2024-04-01&zone_code=59&type=5_MonthBack
    public Object audit(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<TotalScore> allGstaList = new ArrayList<>();
        TotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        //double median =0.00;

        try {

            if (type.equalsIgnoreCase("CurrentMonth")) {
                String query_assessment = new MISQuery().QueryFor_audit_CurrentMonth(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);

                }

            }else if (type.equalsIgnoreCase("1_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_audit_1_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("2_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_audit_2_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("3_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_audit_3_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("4_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_audit_4_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("5_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_audit_5_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
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
        return allGstaList.stream()
                .sorted(Comparator.comparing(TotalScore::getSub_parameter_weighted_average).reversed())
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=appeals__11__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    @ResponseBody
    @RequestMapping(value = "/appeals") //11
    //  http://localhost:8080/cbicApi/cbic/MIS/appeals?month_date=2024-04-01&zone_code=59&type=CurrentMonth
    //  http://localhost:8080/cbicApi/cbic/MIS/appeals?month_date=2024-04-01&zone_code=59&type=1_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/appeals?month_date=2024-04-01&zone_code=59&type=2_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/appeals?month_date=2024-04-01&zone_code=59&type=3_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/appeals?month_date=2024-04-01&zone_code=59&type=4_MonthBack
    //  http://localhost:8080/cbicApi/cbic/MIS/appeals?month_date=2024-04-01&zone_code=59&type=5_MonthBack
    public Object appeals(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {
        List<TotalScore> allGstaList = new ArrayList<>();
        TotalScore totalScore = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        //double median =0.00;

        try {

            if (type.equalsIgnoreCase("CurrentMonth")) {
                String query_assessment = new MISQuery().QueryFor_appeals_CurrentMonth(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);

                }

            }else if (type.equalsIgnoreCase("1_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_appeals_1_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("2_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_appeals_2_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("3_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_appeals_3_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("4_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_appeals_4_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    totalScore = new TotalScore(zoneName, commName, zone_code, total_score, absval, 0, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(totalScore);
                }
            }else if (type.equalsIgnoreCase("5_MonthBack")) {
                String query_assessment = new MISQuery().QueryFor_appeals_5_MonthBack(month_date,zone_code);
                rsGst14aa = GetExecutionSQL.getResult(query_assessment);

                while (rsGst14aa.next()) {
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    zone_code = rsGst14aa.getString("ZONE_CODE");
                    Integer insentavization = 0;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    double  total = rsGst14aa.getDouble("total_score");
                    String commName = "ALL";
                    String gst = "ALL";
                    String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                    String ra= RelevantAspect.Gst2_RA;
                    String formattedTotal = String.format("%.2f", total);
                    double total_score = Double.parseDouble(formattedTotal);
                    Integer way_to_grade =score.marks2(total_score);
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
        return allGstaList.stream()
                .sorted(Comparator.comparing(TotalScore::getSub_parameter_weighted_average).reversed())
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=END=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

}
