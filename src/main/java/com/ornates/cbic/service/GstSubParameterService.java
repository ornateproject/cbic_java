package com.ornates.cbic.service;

import com.ornates.cbic.model.response.GST4A;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GstSubParameterService {
    GstGradeScore score = new GstGradeScore();
    Double totalScore;
    Double median;
    GST4A gsta = null;

    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=GST 3B zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*
    public List<GST4A> gst3bZone(ResultSet rsGst14aa) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();

        while (rsGst14aa.next()) {
            String commname = "All";
            String ra = RelevantAspect.Gst3B_RA;
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            Double col22 = rsGst14aa.getDouble("col22");
            Double col14 = rsGst14aa.getDouble("col14");

            double totalScore = (col14 != 0) ? (col22 / col14) * 100 : 0.00;
            String formattedTotal = String.format("%.2f", totalScore);
            totalScore = Double.parseDouble(formattedTotal);

            String absval = String.valueOf(col22) + "/" + String.valueOf(col14);
            int way_to_grade = score.marks3b(totalScore);

            int Zonal_rank = 0;
            String gst = "GST 3B";

            Integer insentavization = 0;
            double sub_parameter_weighted_average = 0.00;
            gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }
        // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 4D zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*

    public List<GST4A> gst4dZone(ResultSet rsGst14aa) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
        List<Double> s2col7Values = new ArrayList<>();  // List to store all s2col7 values

        // First, collect all the s2col7 values
        while(rsGst14aa.next()) {
            double s2col7 = rsGst14aa.getInt("s2col7");
            s2col7Values.add(s2col7);
        }

        // Sort the list of s2col7 values to calculate median
        Collections.sort(s2col7Values);

        // Calculate the median
        double median = 0.00;
        int size = s2col7Values.size();
        if (size > 0) {
            if (size % 2 == 0) {
                // Even number of elements, average the two middle elements
                median = (s2col7Values.get(size / 2 - 1) + s2col7Values.get(size / 2)) / 2.0;
            } else {
                // Odd number of elements, pick the middle element
                median = s2col7Values.get(size / 2);
            }
        }

        // Now iterate through the ResultSet again to create GST4A objects
        rsGst14aa.beforeFirst();  // Reset the cursor to the beginning
        while(rsGst14aa.next()) {
            String ra = RelevantAspect.GST4D_RA;
            String zoneCode = rsGst14aa.getString("ZONE_CODE");
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String commname = "ALL";
            double s2col7 = rsGst14aa.getInt("s2col7");
            double s1col7 = rsGst14aa.getInt("s1col7");
            double totalScore = (s1col7 != 0) ? (s2col7 / s1col7) * 100 : 0.00;
            String formattedTotal = String.format("%.2f", totalScore);
            totalScore = Double.parseDouble(formattedTotal);
            String absval = String.valueOf(s2col7) + "/" + String.valueOf(s1col7);
            int way_to_grade = score.marks4d(totalScore);
            int Zonal_rank = 0;
            String gst = "GST 4D";

            int insentavization = score.marks4d(totalScore);

            if (s2col7 > median && way_to_grade < 10) {
                insentavization += 1;
            }

            double sub_parameter_weighted_average_bfore = insentavization * 0.2 ;
            String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
            double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);

            GST4A gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }


    // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 3B zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*


        // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*= GST 3B zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*


}
