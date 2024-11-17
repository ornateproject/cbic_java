package com.ornates.cbic.controller;

import com.ornates.cbic.dao.Query.CustomMISReportsQuery;
import com.ornates.cbic.dao.pool.JDBCConnection;
import com.ornates.cbic.dao.result.GetExecutionSQL;
import com.ornates.cbic.model.response.CustomMISReports;
import com.ornates.cbic.service.CustomMISReportsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@CrossOrigin
@RequestMapping("/cbic/CustomMISReports")
@Controller
public class CustomMISReportsController {
    private Logger logger = LoggerFactory.getLogger(CustomMISReportsController.class);
    CustomMISReportsService customMISReportsService = new CustomMISReportsService();

    @ResponseBody
    @RequestMapping(value = "/")
    public String home() {
        return "its working api";
    }

    /*
     * Date: nov 16, 2024
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

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Timely payment of  Refunds__1__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    @ResponseBody
    @RequestMapping(value = "/TimelyPaymentOfRefunds")
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/TimelyPaymentOfRefunds?month_date=2024-07-01&type=1_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/TimelyPaymentOfRefunds?month_date=2024-07-01&type=2_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/TimelyPaymentOfRefunds?month_date=2024-07-01&type=3_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/TimelyPaymentOfRefunds?month_date=2024-07-01&type=4_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/TimelyPaymentOfRefunds?month_date=2024-07-01&type=5_Month
    //  http://localhost:8080/cbicApi/cbic/CustomMISReports/TimelyPaymentOfRefunds?month_date=2024-07-01&type=6_Month
    public Object TimelyPaymentOfRefunds(@RequestParam String month_date, @RequestParam String type){
        List<CustomMISReports> allGstaList = new ArrayList<>();
        Connection con = null;
        ResultSet rsGst14aa = null;
        String formattedDate = "";


        // Define the expected zone codes from 50 to 81
        List<String> expectedZoneCodes = IntStream.rangeClosed(50, 81)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());

        try {
            LocalDate parsedDate = LocalDate.parse(month_date);

            if (type.equalsIgnoreCase("1_Month")) {
                formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_TimelyPaymentOfRefunds_CurrentMonth_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_TimelyPaymentOfRefunds(rsGst14aa, formattedDate, "Timely payment of  Refunds"));
            } else if (type.equalsIgnoreCase("2_Month")) {
                formattedDate = parsedDate.minusMonths(1).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_TimelyPaymentOfRefunds_1_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_TimelyPaymentOfRefunds(rsGst14aa, formattedDate, "Timely payment of  Refunds"));
            } else if (type.equalsIgnoreCase("3_Month")) {
                formattedDate = parsedDate.minusMonths(2).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_TimelyPaymentOfRefunds_2_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_TimelyPaymentOfRefunds(rsGst14aa, formattedDate, "Timely payment of  Refunds"));
            } else if (type.equalsIgnoreCase("4_Month")) {
                formattedDate = parsedDate.minusMonths(3).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_TimelyPaymentOfRefunds_3_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_TimelyPaymentOfRefunds(rsGst14aa, formattedDate, "Timely payment of  Refunds"));
            } else if (type.equalsIgnoreCase("5_Month")) {
                formattedDate = parsedDate.minusMonths(4).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_TimelyPaymentOfRefunds_4_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_TimelyPaymentOfRefunds(rsGst14aa, formattedDate, "Timely payment of  Refunds"));
            } else if (type.equalsIgnoreCase("6_Month")) {
                formattedDate = parsedDate.minusMonths(5).format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
                String query = new CustomMISReportsQuery().QueryFor_TimelyPaymentOfRefunds_5_MonthBack_CustomMISReports(month_date);
                rsGst14aa = GetExecutionSQL.getResult(query);
                allGstaList.addAll(customMISReportsService.processResultSet_TimelyPaymentOfRefunds(rsGst14aa, formattedDate, "Timely payment of  Refunds"));
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
        Map<String, CustomMISReports> resultMap = allGstaList.stream()
                .collect(Collectors.toMap(CustomMISReports::getZone_code, report -> report));

        String finalFormattedDate = formattedDate;
        expectedZoneCodes.forEach(zoneCode -> {
            if (!resultMap.containsKey(zoneCode)) {
                resultMap.put(zoneCode, new CustomMISReports(null, zoneCode, null, 0.00, finalFormattedDate, "Timely payment of  Refunds"));
            }
        });
        // Return the list sorted by zone_code
        return resultMap.values().stream()
                .sorted(Comparator.comparing(CustomMISReports::getZone_code))
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Management of Export Obligation(EPCG)__2__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Management of Export Obligation(AA)__3__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Disposal / Pendency of Provisional Assessments__4__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Adjudication__5__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Investigation__6__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Arrests and Prosecution__7__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Monitoring of un-cleared/unclaimed cargo__8__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Disposal of confiscated Gold and NDPS__9__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Recovery of Arrears__10__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Management of Warehousing bonds__11__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Commissioner (Appeals)__12__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Audit__13__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

}
