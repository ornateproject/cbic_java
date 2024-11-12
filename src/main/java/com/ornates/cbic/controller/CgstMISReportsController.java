package com.ornates.cbic.controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import com.ornates.cbic.dao.Query.CgstMISQuery;
import com.ornates.cbic.dao.Query.CgstMISReportsQuery;
import com.ornates.cbic.dao.result.GetExecutionSQL;
import com.ornates.cbic.model.response.CgstMISReports;
import com.ornates.cbic.model.response.TotalScore;
import com.ornates.cbic.service.CgstGradeScore;
import com.ornates.cbic.service.RelevantAspect;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ornates.cbic.dao.pool.JDBCConnection;

//@CrossOrigin
@RequestMapping("/cbic/CgstMISReports")
@Controller
public class CgstMISReportsController {

    CgstGradeScore score=new CgstGradeScore();

    @ResponseBody
    @RequestMapping(value = "/")
    public String home() {
        return "its working api";
    }

    /*
     * Date: july 2, 2024
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

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=ReturnFiling__2__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    @ResponseBody
    @RequestMapping(value = "/returnFiling")
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/returnFiling?month_date=2024-04-01
    public Object returnFiling(@RequestParam String month_date){
        List<CgstMISReports> allGstaList = new ArrayList<>();
        CgstMISReports cgstMISReports = null;
        Connection con = null;
        ResultSet rsGst14aa = null;
        //double median =0.00;

        try {
            String query_assessment = new CgstMISReportsQuery().QueryFor_ReturnFiling_CgstMISReports(month_date);
            rsGst14aa = GetExecutionSQL.getResult(query_assessment);

            while (rsGst14aa.next()) {
                int col21=rsGst14aa.getInt("col21");
                int col3=rsGst14aa.getInt("col3");
                String zone_code = rsGst14aa.getString("ZONE_CODE");
                Integer insentavization = 0;
                String zoneName = rsGst14aa.getString("ZONE_NAME");
                double  total = rsGst14aa.getDouble("total_score");
                double national_average = 0.00;
                String date = "null";
                String gstname = "ReturnFiling";
                String absval = String.valueOf(col21)+"/"+String.valueOf(col3);

                String ra= RelevantAspect.Gst2_RA;
                String formattedTotal = String.format("%.2f", total);
                double total_score = Double.parseDouble(formattedTotal);
                Integer way_to_grade =score.marks2(total_score);
                double weighted_average = way_to_grade * 0.5;
                cgstMISReports = new CgstMISReports(zoneName, zone_code, weighted_average, national_average, date, gstname);
                allGstaList.add(cgstMISReports);
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
                .sorted(Comparator.comparing(CgstMISReports::getWeighted_average).reversed())
                .collect(Collectors.toList());
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=ReturnFiling__2__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=


    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Scrutiny__3__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    @ResponseBody
    @RequestMapping(value = "/scrutiny")
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/registration?month_date=2024-04-01
    public Object scrutiny(@RequestParam String month_date){
        System.out.println("Hello, I am RKS");
        return null;
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
    @RequestMapping(value = "/adjudication")
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/registration?month_date=2024-04-01
    public Object adjudication(@RequestParam String month_date){
        System.out.println("Hello, I am RKS");
        return null;
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
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/registration?month_date=2024-04-01
    public Object refunds(@RequestParam String month_date){
        System.out.println("Hello, I am RKS");
        return null;
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
    //  http://localhost:8080/cbicApi/cbic/CgstMISReports/registration?month_date=2024-04-01
    public Object appeals(@RequestParam String month_date){
        System.out.println("Hello, I am RKS");
        return null;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=END=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

}
