package com.ornates.cbic.controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import com.ornates.cbic.dao.Query.GstMISReportsQuery;
import com.ornates.cbic.dao.result.GetExecutionSQL;
import com.ornates.cbic.model.response.GstMISReports;
import com.ornates.cbic.service.GstGradeScore;
import com.ornates.cbic.service.GstMISReportsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ornates.cbic.dao.pool.JDBCConnection;

//@CrossOrigin
@RequestMapping("/cbic/CgstMISReports")
@Controller
public class GstMISReportsController {
    private Logger logger = LoggerFactory.getLogger(CustomMISReportsController.class);

    GstGradeScore score=new GstGradeScore();
    GstMISReportsService gstMISReportsService =new GstMISReportsService();

    @ResponseBody
    @RequestMapping(value = "/")
    public String home() {
        return "its working api";
    }

    /*
     * Date: nov 12, 2024
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

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Registration__1__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    @ResponseBody
    @RequestMapping(value = "/registration")
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/registration?month_date=2024-04-01
    public Object registration(@RequestParam String month_date){
        System.out.println("Hello, I am RKS");
        return null;
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------
//    @ResponseBody
//    @RequestMapping(value = "/returnFiling")
////  http://localhost:8080/cbicApi/cbic/CgstMISReports/returnFiling?month_date=2024-07-01
//    public Object returnFiling0(@RequestParam String month_date) {
//        List<CgstMISReports> allGstaList = new ArrayList<>();
//        CgstMISReports cgstMISReports = null;
//        Connection con = null;
//        ResultSet rsGst14aa = null;
//
//        // Define the expected zone codes from 50 to 70
//        List<String> expectedZoneCodes = IntStream.rangeClosed(50, 70)
//                .mapToObj(String::valueOf)
//                .collect(Collectors.toList());
//
//        // Parse the month_date to get the formatted date
//        LocalDate parsedDate = LocalDate.parse(month_date);
//        String formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH)); // e.g., "August 2024"
//
//        try {
//            String query_assessment = new CgstMISReportsQuery().QueryFor_ReturnFiling_CgstMISReports(month_date);
//            rsGst14aa = GetExecutionSQL.getResult(query_assessment);
//
//            while (rsGst14aa.next()) {
//                int col21 = rsGst14aa.getInt("col21");
//                int col3 = rsGst14aa.getInt("col3");
//                String zone_code = rsGst14aa.getString("ZONE_CODE");
//                String zoneName = rsGst14aa.getString("ZONE_NAME");
//                double total = rsGst14aa.getDouble("total_score");
//                double national_average = 0.00;
//                String gstname = "ReturnFiling";
//                String formattedTotal = String.format("%.2f", total);
//                double total_score = Double.parseDouble(formattedTotal);
//                Integer way_to_grade = score.marks2(total_score);
//                double weighted_average = way_to_grade * 0.5;
//
//                cgstMISReports = new CgstMISReports(zoneName, zone_code, weighted_average, national_average, formattedDate, gstname);
//                allGstaList.add(cgstMISReports);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rsGst14aa != null) rsGst14aa.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // Ensure all expected zone codes are included in the result
//        Map<String, CgstMISReports> resultMap = allGstaList.stream()
//                .collect(Collectors.toMap(CgstMISReports::getZone_code, report -> report));
//
//        expectedZoneCodes.forEach(zoneCode -> {
//            if (!resultMap.containsKey(zoneCode)) {
//                // Add entry with null values if zone code data is missing
//                resultMap.put(zoneCode, new CgstMISReports(null, zoneCode, null, 0.00, formattedDate, "ReturnFiling"));
//            }
//        });
//
//        // Return the list sorted by zone_code
//        return resultMap.values().stream()
//                .sorted(Comparator.comparing(CgstMISReports::getZone_code))
//                .collect(Collectors.toList());
//    }



    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=ReturnFiling__2__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    @ResponseBody
    @RequestMapping(value = "/returnFiling") //2
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/returnFiling?month_date=2024-07-01&type=1_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/returnFiling?month_date=2024-07-01&type=2_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/returnFiling?month_date=2024-07-01&type=3_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/returnFiling?month_date=2024-07-01&type=4_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/returnFiling?month_date=2024-07-01&type=5_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/returnFiling?month_date=2024-07-01&type=6_Month
    public Object returnFiling(@RequestParam String month_date, @RequestParam String type) {
        List<GstMISReports> allGstaList = new ArrayList<>();
        Connection con = null;
        ResultSet rsGst14aa = null;
        String formattedDate = "";

        // Define the expected zone codes from 50 to 70
        List<String> expectedZoneCodes = IntStream.rangeClosed(50, 70)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());

        try {
            LocalDate parsedDate = LocalDate.parse(month_date);

            if (type.equalsIgnoreCase("1_Month")) {
                formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_ReturnFiling_CurrentMonth_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_ReturnFiling(rsGst14aa, formattedDate, "Return Filing"));
            } else if (type.equalsIgnoreCase("2_Month")) {
                formattedDate = parsedDate.minusMonths(1).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_ReturnFiling_1_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_ReturnFiling(rsGst14aa, formattedDate, "Return Filing"));
            } else if (type.equalsIgnoreCase("3_Month")) {
                formattedDate = parsedDate.minusMonths(2).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_ReturnFiling_2_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_ReturnFiling(rsGst14aa, formattedDate, "Return Filing"));
            } else if (type.equalsIgnoreCase("4_Month")) {
                formattedDate = parsedDate.minusMonths(3).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_ReturnFiling_3_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_ReturnFiling(rsGst14aa, formattedDate, "Return Filing"));
            } else if (type.equalsIgnoreCase("5_Month")) {
                formattedDate = parsedDate.minusMonths(4).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_ReturnFiling_4_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_ReturnFiling(rsGst14aa, formattedDate, "Return Filing"));
            } else if (type.equalsIgnoreCase("6_Month")) {
                formattedDate = parsedDate.minusMonths(5).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_ReturnFiling_5_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_ReturnFiling(rsGst14aa, formattedDate, "Return Filing"));
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

        // Ensure all expected zone codes are included in the result
        Map<String, GstMISReports> resultMap = allGstaList.stream()
                .collect(Collectors.toMap(GstMISReports::getZone_code, report -> report));

        String finalFormattedDate = formattedDate;
        expectedZoneCodes.forEach(zoneCode -> {
            if (!resultMap.containsKey(zoneCode)) {
                resultMap.put(zoneCode, new GstMISReports(null, zoneCode, null, 0.00, finalFormattedDate, "ReturnFiling"));
            }
        });
        // Return the list sorted by zone_code
        return resultMap.values().stream()
                .sorted(Comparator.comparing(GstMISReports::getZone_code))
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Scrutiny__3__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    @ResponseBody
    @RequestMapping(value = "/scrutiny")
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/scrutiny?month_date=2024-07-01&type=1_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/scrutiny?month_date=2024-07-01&type=2_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/scrutiny?month_date=2024-07-01&type=3_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/scrutiny?month_date=2024-07-01&type=4_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/scrutiny?month_date=2024-07-01&type=5_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/scrutiny?month_date=2024-07-01&type=6_Month
    public Object scrutiny(@RequestParam String month_date, @RequestParam String type) {
        List<GstMISReports> allGstaList = new ArrayList<>();
        Connection con = null;
        ResultSet rsGst14aa = null;
        String formattedDate = "";

        // Define the expected zone codes from 50 to 70
        List<String> expectedZoneCodes = IntStream.rangeClosed(50, 70)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());

        try {
            LocalDate parsedDate = LocalDate.parse(month_date);

            if (type.equalsIgnoreCase("1_Month")) {
                formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Scrutiny_CurrentMonth_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Scrutiny(rsGst14aa, formattedDate, "Scrutiny"));
            } else if (type.equalsIgnoreCase("2_Month")) {
                formattedDate = parsedDate.minusMonths(1).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Scrutiny_1_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Scrutiny(rsGst14aa, formattedDate, "Scrutiny"));
            } else if (type.equalsIgnoreCase("3_Month")) {
                formattedDate = parsedDate.minusMonths(2).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Scrutiny_2_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Scrutiny(rsGst14aa, formattedDate, "Scrutiny"));
            } else if (type.equalsIgnoreCase("4_Month")) {
                formattedDate = parsedDate.minusMonths(3).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Scrutiny_3_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Scrutiny(rsGst14aa, formattedDate, "Scrutiny"));
            } else if (type.equalsIgnoreCase("5_Month")) {
                formattedDate = parsedDate.minusMonths(4).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Scrutiny_4_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Scrutiny(rsGst14aa, formattedDate, "Scrutiny"));
            } else if (type.equalsIgnoreCase("6_Month")) {
                formattedDate = parsedDate.minusMonths(5).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Scrutiny_5_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Scrutiny(rsGst14aa, formattedDate, "Scrutiny"));
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

        // Ensure all expected zone codes are included in the result
        Map<String, GstMISReports> resultMap = allGstaList.stream()
                .collect(Collectors.toMap(GstMISReports::getZone_code, report -> report));

        String finalFormattedDate = formattedDate;
        expectedZoneCodes.forEach(zoneCode -> {
            if (!resultMap.containsKey(zoneCode)) {
                resultMap.put(zoneCode, new GstMISReports(null, zoneCode, null, 0.00, finalFormattedDate, "Scrutiny"));
            }
        });
        // Return the list sorted by zone_code
        return resultMap.values().stream()
                .sorted(Comparator.comparing(GstMISReports::getZone_code))
                .collect(Collectors.toList());
    }


    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Investigation__4__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    @ResponseBody
    @RequestMapping(value = "/investigation")
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/registration?month_date=2024-04-01
    public Object investigation(@RequestParam String month_date){
        System.out.println("Hello, I am RKS");
        return null;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Adjudication__5__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    @ResponseBody
    @RequestMapping(value = "/adjudication") //5
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/adjudication?month_date=2024-07-01&type=1_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/adjudication?month_date=2024-07-01&type=2_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/adjudication?month_date=2024-07-01&type=3_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/adjudication?month_date=2024-07-01&type=4_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/adjudication?month_date=2024-07-01&type=5_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/adjudication?month_date=2024-07-01&type=6_Month
    public Object adjudication(@RequestParam String month_date, @RequestParam String type) {
        List<GstMISReports> allGstaList = new ArrayList<>();
        Connection con = null;
        ResultSet rsGst14aa = null;
        String formattedDate = "";

        // Define the expected zone codes from 50 to 70
        List<String> expectedZoneCodes = IntStream.rangeClosed(50, 70)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());

        try {
            LocalDate parsedDate = LocalDate.parse(month_date);

            if (type.equalsIgnoreCase("1_Month")) {
                formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Adjudication_CurrentMonth_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Adjudication(rsGst14aa, formattedDate, "Adjudication"));
            } else if (type.equalsIgnoreCase("2_Month")) {
                formattedDate = parsedDate.minusMonths(1).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Adjudication_1_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Adjudication(rsGst14aa, formattedDate, "Adjudication"));
            } else if (type.equalsIgnoreCase("3_Month")) {
                formattedDate = parsedDate.minusMonths(2).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Adjudication_2_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Adjudication(rsGst14aa, formattedDate, "Adjudication"));
            } else if (type.equalsIgnoreCase("4_Month")) {
                formattedDate = parsedDate.minusMonths(3).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Adjudication_3_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Adjudication(rsGst14aa, formattedDate, "Adjudication"));
            } else if (type.equalsIgnoreCase("5_Month")) {
                formattedDate = parsedDate.minusMonths(4).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Adjudication_4_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Adjudication(rsGst14aa, formattedDate, "Adjudication"));
            } else if (type.equalsIgnoreCase("6_Month")) {
                formattedDate = parsedDate.minusMonths(5).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Adjudication_5_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Adjudication(rsGst14aa, formattedDate, "Adjudication"));
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

        // Ensure all expected zone codes are included in the result
        Map<String, GstMISReports> resultMap = allGstaList.stream()
                .collect(Collectors.toMap(GstMISReports::getZone_code, report -> report));

        String finalFormattedDate = formattedDate;
        expectedZoneCodes.forEach(zoneCode -> {
            if (!resultMap.containsKey(zoneCode)) {
                resultMap.put(zoneCode, new GstMISReports(null, zoneCode, null, 0.00, finalFormattedDate, "Adjudication"));
            }
        });
        // Return the list sorted by zone_code
        return resultMap.values().stream()
                .sorted(Comparator.comparing(GstMISReports::getZone_code))
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Adjudication_Legacy__6__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    @ResponseBody
    @RequestMapping(value = "/adjudicationLegacy")
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/registration?month_date=2024-04-01
    public Object adjudicationLegacy(@RequestParam String month_date){
        System.out.println("Hello, I am RKS");
        return null;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Refunds__7__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    @ResponseBody
    @RequestMapping(value = "/refunds")
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/refunds?month_date=2024-07-01&type=1_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/refunds?month_date=2024-07-01&type=2_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/refunds?month_date=2024-07-01&type=3_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/refunds?month_date=2024-07-01&type=4_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/refunds?month_date=2024-07-01&type=5_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/refunds?month_date=2024-07-01&type=6_Month
    public Object refunds(@RequestParam String month_date, @RequestParam String type) {
        List<GstMISReports> allGstaList = new ArrayList<>();
        Connection con = null;
        ResultSet rsGst14aa = null;
        String formattedDate = "";

        // Define the expected zone codes from 50 to 70
        List<String> expectedZoneCodes = IntStream.rangeClosed(50, 70)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());

        try {
            LocalDate parsedDate = LocalDate.parse(month_date);

            if (type.equalsIgnoreCase("1_Month")) {
                formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Refunds_CurrentMonth_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Refunds(rsGst14aa, formattedDate, "Refunds"));
            } else if (type.equalsIgnoreCase("2_Month")) {
                formattedDate = parsedDate.minusMonths(1).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Refunds_1_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Refunds(rsGst14aa, formattedDate, "Refunds"));
            } else if (type.equalsIgnoreCase("3_Month")) {
                formattedDate = parsedDate.minusMonths(2).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Refunds_2_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Refunds(rsGst14aa, formattedDate, "Refunds"));
            } else if (type.equalsIgnoreCase("4_Month")) {
                formattedDate = parsedDate.minusMonths(3).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Refunds_3_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Refunds(rsGst14aa, formattedDate, "Refunds"));
            } else if (type.equalsIgnoreCase("5_Month")) {
                formattedDate = parsedDate.minusMonths(4).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Refunds_4_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Refunds(rsGst14aa, formattedDate, "Refunds"));
            } else if (type.equalsIgnoreCase("6_Month")) {
                formattedDate = parsedDate.minusMonths(5).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Refunds_5_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Refunds(rsGst14aa, formattedDate, "Refunds"));
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

        // Ensure all expected zone codes are included in the result
        Map<String, GstMISReports> resultMap = allGstaList.stream()
                .collect(Collectors.toMap(GstMISReports::getZone_code, report -> report));

        String finalFormattedDate = formattedDate;
        expectedZoneCodes.forEach(zoneCode -> {
            if (!resultMap.containsKey(zoneCode)) {
                resultMap.put(zoneCode, new GstMISReports(null, zoneCode, null, 0.00, finalFormattedDate, "Adjudication"));
            }
        });
        // Return the list sorted by zone_code
        return resultMap.values().stream()
                .sorted(Comparator.comparing(GstMISReports::getZone_code))
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=RecoveryOfArrears__8__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    @ResponseBody
    @RequestMapping(value = "/recoveryOfArrears")
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/registration?month_date=2024-04-01
    public Object recoveryOfArrears(@RequestParam String month_date){
        System.out.println("Hello, I am RKS");
        return null;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Arrests & prosecution__9__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    @ResponseBody
    @RequestMapping(value = "/arrestAndProsecution")
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/registration?month_date=2024-04-01
    public Object arrestAndProsecution(@RequestParam String month_date){
        System.out.println("Hello, I am RKS");
        return null;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Audit__10__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    @ResponseBody
    @RequestMapping(value = "/audit")
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/registration?month_date=2024-04-01
    public Object audit(@RequestParam String month_date){
        System.out.println("Hello, I am RKS");
        return null;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Appeals__11__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    @ResponseBody
    @RequestMapping(value = "/appeals")
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/appeals?month_date=2024-07-01&type=1_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/appeals?month_date=2024-07-01&type=2_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/appeals?month_date=2024-07-01&type=3_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/refunds?month_date=2024-07-01&type=4_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/refunds?month_date=2024-07-01&type=5_Month
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/refunds?month_date=2024-07-01&type=6_Month
    public Object appeals(@RequestParam String month_date, @RequestParam String type) {
        List<GstMISReports> allGstaList = new ArrayList<>();
        Connection con = null;
        ResultSet rsGst14aa = null;
        String formattedDate = "";

        // Define the expected zone codes from 50 to 70
        List<String> expectedZoneCodes = IntStream.rangeClosed(50, 70)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());

        try {
            LocalDate parsedDate = LocalDate.parse(month_date);

            if (type.equalsIgnoreCase("1_Month")) {
                formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Appeals_CurrentMonth_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Appeals(rsGst14aa, formattedDate, "Appeals"));
            } else if (type.equalsIgnoreCase("2_Month")) {
                formattedDate = parsedDate.minusMonths(1).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Appeals_1_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Appeals(rsGst14aa, formattedDate, "Appeals"));
            } else if (type.equalsIgnoreCase("3_Month")) {
                formattedDate = parsedDate.minusMonths(2).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Appeals_2_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Appeals(rsGst14aa, formattedDate, "Appeals"));
            } else if (type.equalsIgnoreCase("4_Month")) {
                formattedDate = parsedDate.minusMonths(3).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Appeals_3_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Appeals(rsGst14aa, formattedDate, "Appeals"));
            } else if (type.equalsIgnoreCase("5_Month")) {
                formattedDate = parsedDate.minusMonths(4).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Appeals_4_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Appeals(rsGst14aa, formattedDate, "Appeals"));
            } else if (type.equalsIgnoreCase("6_Month")) {
                formattedDate = parsedDate.minusMonths(5).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new GstMISReportsQuery().QueryFor_Appeals_5_MonthBack_CgstMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(gstMISReportsService.processResultSet_Appeals(rsGst14aa, formattedDate, "Appeals"));
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

        // Ensure all expected zone codes are included in the result
        Map<String, GstMISReports> resultMap = allGstaList.stream()
                .collect(Collectors.toMap(GstMISReports::getZone_code, report -> report));

        String finalFormattedDate = formattedDate;
        expectedZoneCodes.forEach(zoneCode -> {
            if (!resultMap.containsKey(zoneCode)) {
                resultMap.put(zoneCode, new GstMISReports(null, zoneCode, null, 0.00, finalFormattedDate, "Appeals"));
            }
        });
        // Return the list sorted by zone_code
        return resultMap.values().stream()
                .sorted(Comparator.comparing(GstMISReports::getZone_code))
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=END=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

}
