package com.ornates.cbic.service;

import com.ornates.cbic.model.response.GST4A;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

            if(col14 != 0){
                totalScore = (col22 / col14) * 100;
            }else {
                totalScore = 0.00;
            }
            String absval = String.valueOf(col22) + "/" + String.valueOf(col14);

//                    Double t_score = rsGst14aa.getDouble("score_of_parameter");
//                    median = rsGst14aa.getDouble("median_numerator_3b");
//                    Double numerator_3b = rsGst14aa.getDouble("numerator_3b");
//
//                    String formattedTotal = String.format("%.2f", t_score);
//                    double totalScore = Double.parseDouble(formattedTotal);
//                    int way_to_grade = score.marks3b(totalScore);
//                    int insentavization = score.marks3b(totalScore);
//                    // System.out.println("insentavization3b :-" + insentavization);
//
//                    if (numerator_3b > median && way_to_grade < 10) {
//                        insentavization += 1;
//                    }
//
//                    //System.out.println("insentavization3b after :-" + insentavization);
//
            int Zonal_rank = 0;
            String gst = "no";


            Integer way_to_grade = 0;
            Integer insentavization = 0;
            double sub_parameter_weighted_average = 0.00;
//
//                    double sub_parameter_weighted_average = insentavization * 0.5 ;
//                    //double sub_parameter_weighted_average = 0.00 ;

            gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
            allGstaList.add(gsta);
        }
        return allGstaList;
    }
        // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=GST 3B zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*


        // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=GST 3B zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*


        // *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=GST 3B zone wise *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*


}
