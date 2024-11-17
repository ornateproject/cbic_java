package com.ornates.cbic.controller;

import com.ornates.cbic.dao.Query.GstSubParameterWiseQuery;
import com.ornates.cbic.dao.pool.JDBCConnection;
import com.ornates.cbic.dao.result.GetExecutionSQL;
import com.ornates.cbic.model.response.GST4A;
import com.ornates.cbic.service.GstGradeScore;

import com.ornates.cbic.service.RelevantAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/cbic/testing2")
@Controller
public class TestingController2 {
    private Logger logger = LoggerFactory.getLogger(TestingController2.class);

    private final GstGradeScore score = new GstGradeScore();

    @ResponseBody
    @RequestMapping(value = "/")
    public String home() {
        return "its working api";
    }

    @ResponseBody
    @RequestMapping(value = "/db_check")
    public String bdTest() {
        Connection con = JDBCConnection.getTNConnection();
        System.out.println("Connection :"+con);
        return "its working api";
    }

    @ResponseBody
    @RequestMapping(value = "/test/gst5a")
    //  http://localhost:8080/cbicApi/cbic/testing2/test/gst5a?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/testing2/test/gst5a?month_date=2024-04-01&zone_code=70&type=commissary
    //  http://localhost:8080/cbicApi/cbic/testing2/test/gst5a?month_date=2024-04-01&type=all_commissary
    public List<GST4A> getGst5A(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        logger.info("Fetching GST5A data for month_date: {}, type: {}, zone_code: {}", month_date, type, zone_code);
        List<GST4A> allGstaList = new ArrayList<>();
        try {
            if ("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst5a_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String absval = rsGst14aa.getInt("col10") + "/" + rsGst14aa.getInt("col4");
                    double totalScore = Double.parseDouble(String.format("%.2f", rsGst14aa.getDouble("score_of_subparameter5a")));
                    int way_to_grade = score.marks5a(totalScore);
                    int insentavization = way_to_grade;
                    if (rsGst14aa.getDouble("col10") > rsGst14aa.getDouble("median5a") && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    double sub_parameter_weighted_average = insentavization * 0.5;
                    allGstaList.add(new GST4A(zoneName, "All", totalScore, absval, zoneCode, "GST5A",
                            0, "no", way_to_grade, insentavization, sub_parameter_weighted_average));
                }
            }else if ("commissary".equalsIgnoreCase(type)) {
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst5b_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra= RelevantAspect.Gst5B_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    double total = rsGst14aa.getDouble("score_of_parameter");
                    int col22=rsGst14aa.getInt("col22");
                    int col23=rsGst14aa.getInt("col23");
                    int col16=rsGst14aa.getInt("col16");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal) ;
                    int way_to_grade = score.marks5b(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.5;
                    String absval = String.valueOf(col22+col23) + "/" + String.valueOf(col16);
                    allGstaList.add(new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average));
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst5b_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.Gst5B_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    double total = rsGst14aa.getDouble("score_of_parameter");
                    int col22=rsGst14aa.getInt("col22");
                    int col23=rsGst14aa.getInt("col23");
                    int col16=rsGst14aa.getInt("col16");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks5b(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.5;
                    String absval = String.valueOf(col22+col23) + "/" + String.valueOf(col16);
                    allGstaList.add(new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream().sorted(Comparator.comparing(GST4A::getTotal_score).reversed()).collect(Collectors.toList());
    }

    @ResponseBody
    @RequestMapping(value = "/test/gst5b")
    // http://localhost:8080/cbicApi/cbic/testing2/test/gst5b?month_date=2024-04-01&type=zone
    public List<GST4A> getGst5B(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GST4A> allGstaList = new ArrayList<>();
        try {
            if ("zone".equalsIgnoreCase(type)) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst5b_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String absval = (rsGst14aa.getInt("col22") + rsGst14aa.getInt("col23")) + "/" + rsGst14aa.getInt("col16");
                    double totalScore = Double.parseDouble(String.format("%.2f", rsGst14aa.getDouble("total_score")));
                    int way_to_grade = score.marks5b(totalScore);
                    double sub_parameter_weighted_average = way_to_grade * 0.5;
                    allGstaList.add(new GST4A(zoneName, "ALL", totalScore, absval, zoneCode, "GST5B",
                            0, "no", way_to_grade, 0, sub_parameter_weighted_average));
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst5b_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.Gst5B_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    double total = rsGst14aa.getDouble("score_of_parameter");
                    int col22=rsGst14aa.getInt("col22");
                    int col23=rsGst14aa.getInt("col23");
                    int col16=rsGst14aa.getInt("col16");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal) ;
                    int way_to_grade = score.marks5b(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.5;
                    String absval = String.valueOf(col22+col23) + "/" + String.valueOf(col16);
                    allGstaList.add(new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average));
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst5b_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.Gst5B_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    double total = rsGst14aa.getDouble("score_of_parameter");
                    int col22=rsGst14aa.getInt("col22");
                    int col23=rsGst14aa.getInt("col23");
                    int col16=rsGst14aa.getInt("col16");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks5b(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.5;
                    String absval = String.valueOf(col22+col23) + "/" + String.valueOf(col16);
                    allGstaList.add(new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream().sorted(Comparator.comparing(GST4A::getTotal_score).reversed()).collect(Collectors.toList());
    }


    @ResponseBody
    @RequestMapping(value = "/test/gst5_combined/url4")
    // http://localhost:8080/cbicApi/cbic/testing2/test/gst5_combined/url4?month_date=2024-04-01&type=all_commissary    // 4 no url wrong data
    public List<GST4A> getGst5Combined_4_noURL(@RequestParam String month_date, @RequestParam String type) {
        List<GST4A> gst5aList = getGst5A(month_date, type, null);
        List<GST4A> gst5bList = getGst5B(month_date, type, null);

        Map<String, GST4A> combinedMap = new HashMap<>();

        for (GST4A gsta : gst5aList) {
            combinedMap.put(gsta.getZone_code(), gsta);
        }

        for (GST4A gstb : gst5bList) {
            GST4A existing = combinedMap.get(gstb.getZone_code());
            if (existing != null) {
                existing.setSub_parameter_weighted_average(
                        existing.getSub_parameter_weighted_average() + gstb.getSub_parameter_weighted_average()
                );
                existing.setTotal_score(existing.getTotal_score() + gstb.getTotal_score());
            } else {
                combinedMap.put(gstb.getZone_code(), gstb);
            }
        }

        List<GST4A> combinedList = new ArrayList<>(combinedMap.values());
        combinedList.sort(Comparator.comparing(GST4A::getSub_parameter_weighted_average).reversed());

        int rank = 1;
        for (GST4A gsta : combinedList) {
            gsta.setZonal_rank(rank++);
        }

        return combinedList;
    }



    @ResponseBody
    @RequestMapping(value = "/test/gst5_combined/url1")
    // http://localhost:8080/cbicApi/cbic/testing2/test/gst5_combined/url1?month_date=2024-04-01&type=zone    // 1 no url  correct data
    public List<GST4A> getGst5Combined_1_noURL(@RequestParam String month_date, @RequestParam String type) {
        List<GST4A> gst5aList = getGst5A(month_date, type, null);
        List<GST4A> gst5bList = getGst5B(month_date, type, null);

        Map<String, GST4A> combinedMap = new HashMap<>();

        for (GST4A gsta : gst5aList) {
            combinedMap.put(gsta.getZone_code(), gsta);
        }

        for (GST4A gstb : gst5bList) {
            GST4A existing = combinedMap.get(gstb.getZone_code());
            if (existing != null) {
                existing.setSub_parameter_weighted_average(
                        existing.getSub_parameter_weighted_average() + gstb.getSub_parameter_weighted_average()
                );
                existing.setTotal_score(existing.getTotal_score() + gstb.getTotal_score());
            } else {
                combinedMap.put(gstb.getZone_code(), gstb);
            }
        }

        List<GST4A> combinedList = new ArrayList<>(combinedMap.values());
        combinedList.sort(Comparator.comparing(GST4A::getSub_parameter_weighted_average).reversed());

        int rank = 1;
        for (GST4A gsta : combinedList) {
            gsta.setZonal_rank(rank++);
        }

        return combinedList;
    }
}
