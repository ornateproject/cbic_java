package com.ornates.cbic.controller;

import com.ornates.cbic.dao.Query.TestingQueryGst3b;
import com.ornates.cbic.dao.pool.JDBCConnection;
import com.ornates.cbic.dao.result.GetExecutionSQL;
import com.ornates.cbic.model.response.GST4A;
import com.ornates.cbic.service.GstGradeScore;
import com.ornates.cbic.service.TestingServicegst3b;
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
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/TestingControllergst3b")
@Controller
public class TestingControllergst3b {
    private Logger logger = LoggerFactory.getLogger(CustomMISReportsController.class);
    GstGradeScore score=new GstGradeScore();
    TestingServicegst3b gstSubParameterService = new TestingServicegst3b();


    @ResponseBody
    @RequestMapping(value = "/gst3b")
    //  http://localhost:8080/cbicApi/TestingControllergst3b/gst3b?month_date=2024-06-01&type=zone
    public Object getGst3B(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        Double median = 0.00;
        Connection con = JDBCConnection.getTNConnection();

        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa = new TestingQueryGst3b().QueryFor_gst3b_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                allGstaList.addAll(gstSubParameterService.gst3bZone(rsGst14aa));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GST4A::getTotal_score).reversed()).collect(Collectors.toList());
    }
}

