package com.ornates.cbic.service;

import com.ornates.cbic.model.response.GST4A;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestingServicegst3b {
    double median = 0.0;
    TestingGradeScoreGst3b score = new TestingGradeScoreGst3b();

    public List<GST4A> gst3bZone(ResultSet rsGst14aa) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
        List<Double> col22Values = new ArrayList<>();

        while (rsGst14aa.next()) {
            double col22 = rsGst14aa.getDouble("col22");
            col22Values.add(col22);
            median = MedianCalculator.calculateMedian(col22Values);

        }System.out.println(median);

        // Collect col22 values and process ResultSet for GST4A objects
        while (rsGst14aa.next()) {
            double col22 = rsGst14aa.getDouble("col22");
            col22Values.add(col22);

            String commname = "All";
            String ra = RelevantAspect.Gst3B_RA;
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            double col14 = rsGst14aa.getDouble("col14");

            double totalScore = (col14 != 0) ? (col22 / col14) * 100 : 0.00;
            String formattedTotal = String.format("%.2f", totalScore);
            totalScore = Double.parseDouble(formattedTotal);
//            double median = MedianCalculator.calculateMedian(col22Values);
//            System.out.println(median);


            String absval = col22 + "/" + col14;
            int wayToGrade = score.marks3b(totalScore);

            Integer insentavization = 0;
            GST4A gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, 0, "GST 3B", wayToGrade, insentavization, 0.00);
            allGstaList.add(gsta);
        }
//
//        // Calculate median
//        double median = MedianCalculator.calculateMedian(col22Values);
//      //  System.out.println(median);
//
//        // Update insentavization based on median
//        for (GST4A gsta : allGstaList) {
//            double col22 = Double.parseDouble(gsta.getAbsolutevale().split("/")[0]); // Extract col22 from absval
//            int wayToGrade = gsta.getWay_to_grade();
//            int insentavization = (median > col22) ? Math.min(wayToGrade + 1, 10) : 0;
//            gsta.setInsentavization(insentavization);
//        }

        return allGstaList;
    }
}