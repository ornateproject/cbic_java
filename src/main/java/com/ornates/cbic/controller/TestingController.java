package com.ornates.cbic.controller;

import com.ornates.cbic.dao.pool.JDBCConnection;
import com.ornates.cbic.dao.result.GetExecutionSQL;
import com.ornates.cbic.model.response.GST4A;
import com.ornates.cbic.service.GstGradeScore;
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
import java.util.List;

@RequestMapping("/cbic/t_score/testing")
@Controller
public class TestingController {
    private Logger logger = LoggerFactory.getLogger(CustomMISReportsController.class);

    GstGradeScore score = new GstGradeScore();

    @ResponseBody
    @RequestMapping(value = "/")
    public String home() {
        return "its working api";
    }

    @ResponseBody
    @RequestMapping(value = "/db_check")
    public String bdTest() {
        Connection con = JDBCConnection.getTNConnection();
        System.out.println("Connection :" + con);
        return "its working api";
    }

    @ResponseBody
    @RequestMapping(value = "/refundsTesting")
    //  http://localhost:8080/cbicApi/cbic/t_score/testing/refundsTesting?month_date=2023-04-01&type=parameter
    public Object refunds(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code, @RequestParam(required = false) String come_name) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;

        try {
            if (type.equalsIgnoreCase("parameter")) {
                String quaryGst7 = "SELECT " +
                        "SUM(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS col16, " +
                        "SUM(14c.age_breakup_above60_no) AS col22, " +
                        "cc.ZONE_CODE,zc.ZONE_NAME " +
                        "FROM mis_gst_commcode AS cc " +
                        "RIGHT JOIN mis_dpm_gst_4 AS 14c " +
                        "ON cc.COMM_CODE = 14c.COMM_CODE " +
                        "LEFT JOIN mis_gst_zonecode AS zc " +
                        "ON zc.ZONE_CODE = cc.ZONE_CODE " +
                        "WHERE 14c.MM_YYYY = '" + month_date + "' " +
                        "GROUP BY cc.ZONE_CODE;";
                ResultSet rsGst7 = GetExecutionSQL.getResult(quaryGst7);
                while (rsGst7.next()) {
                    String ra = "GST7";
                    String zoneCode = rsGst7.getString("ZONE_CODE");
                    String commname="ALL";
                    Double col22 = rsGst7.getDouble("col22");
                    Double col16 = rsGst7.getDouble("col16");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    // for only this sub parameter
                    int col22ab = rsGst7.getInt("col22");
                    int col16ab = rsGst7.getInt("col16");
                    String absval = String.valueOf(col22ab) + "/" + String.valueOf(col16ab);
                    if (col16 != 0) {
                        total = ((col22 *100)/col16);
                    }else {
                        total = 0;
                    }
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks7(totalScore);
                    int sub_parameter_weighted_average = way_to_grade;
                    gsta = new GST4A(rsGst7.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

}
