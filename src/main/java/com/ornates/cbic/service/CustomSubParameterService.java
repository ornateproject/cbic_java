package com.ornates.cbic.service;

import com.ornates.cbic.model.response.GST4A;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomSubParameterService {
    CustomGreadeScore score = new CustomGreadeScore();
    Double total;
    Double median;
    GST4A gsta = null;
    public List<GST4A> cus6aZone(ResultSet rs) throws SQLException {
        List<GST4A> allGstaList = new ArrayList<>();
        while (rs.next()) {
            String ra = CustomRelaventAspect.cus6a_RA;
            String commname = "ALL";
            String zoneCode = rs.getString("ZONE_CODE");
            int col9_3a = rs.getInt("col9_3a");
            int col9_3b = rs.getInt("col9_3b");
            int col3_3a = rs.getInt("col3_3a");
            int col3_3b = rs.getInt("col3_3b");
            median = rs.getDouble("median_6a");
            Double numerator_6c = rs.getDouble("numerator_9");
            int Zonal_rank = 0;
            String gst = "no";
            //String absval=String.valueOf(col9_3a+col9_3b)+"/"+String.valueOf(col3_3a+col3_3b);
            String absval = "";
            if (!(col9_3a+col9_3b == 0 && col3_3a+col3_3b== 0)) {
                absval = String.valueOf(col9_3a+col9_3b ) + "/" + String.valueOf(col3_3a+col3_3b);
            }
            if((col3_3a+col3_3b) != 0) {
                total = ((double) (col9_3a+col9_3b) * 100 / (col3_3a+col3_3b));
            }else {
                total = 0.00;
            }
            String formattedTotal = String.format("%.2f", total);
            double totalScore = Double.parseDouble(formattedTotal);
            int way_to_grade = score.c_marks6a(totalScore);
            int insentavization = score.c_marks6a(totalScore);
            if (numerator_6c > median && way_to_grade < 10) {
                insentavization += 1;
            }
            double sub_parameter_weighted_average = insentavization * 0.2;
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
            if (!(col9_3a+col9_3b == 0 && col3_3a+col3_3b== 0)) {
                gsta=new GST4A(rs.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                        Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                allGstaList.add(gsta);}
            allGstaList.sort((a, b) -> Double.compare(b.getTotal_score(), a.getTotal_score()));
        }System.out.println("median cus6a zone wise :-" + median);
        return allGstaList;
    }
}
