package com.ornates.cbic.service;

import com.ornates.cbic.model.response.GstMISReports;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GstMISReportsService {
    GstGradeScore score = new GstGradeScore();


    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=ReturnFiling__2__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    // Method to process the ResultSet and create CgstMISReports objects
    public List<GstMISReports> processResultSet_ReturnFiling(ResultSet rs, String formattedDate, String gstname) throws SQLException {
        List<GstMISReports> reportsList = new ArrayList<>();
        while (rs.next()) {
            String zone_code = rs.getString("ZONE_CODE");
            String zoneName = rs.getString("ZONE_NAME");
            double total = rs.getDouble("total_score");
            String formattedTotal = String.format("%.2f", total);
            double total_score = Double.parseDouble(formattedTotal);
            Integer way_to_grade = score.marks2(total_score);
            double weighted_average = way_to_grade * 0.5;

            GstMISReports cgstMISReports = new GstMISReports(zoneName, zone_code, weighted_average, 0.00, formattedDate, gstname);
            reportsList.add(cgstMISReports);
        }
        return reportsList;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Scrutiny__3__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public List<GstMISReports> processResultSet_Scrutiny(ResultSet rs, String formattedDate, String gstname) throws SQLException {
        List<GstMISReports> reportsList = new ArrayList<>();
        while (rs.next()) {
//            String zone_code = rs.getString("ZONE_CODE");
//            String zoneName = rs.getString("ZONE_NAME");
//            double total = rs.getDouble("total_score");
//            String formattedTotal = String.format("%.2f", total);
//            double total_score = Double.parseDouble(formattedTotal);
//            Integer way_to_grade = score.marks2(total_score);
//            double weighted_average = way_to_grade * 0.5;
//
//            CgstMISReports cgstMISReports = new CgstMISReports(zoneName, zone_code, weighted_average, 0.00, formattedDate, gstname);
//            reportsList.add(cgstMISReports);
        }
        return reportsList;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Investigation__4__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public List<GstMISReports> processResultSet_Investigation(ResultSet rs, String formattedDate, String gstname) throws SQLException {
        List<GstMISReports> reportsList = new ArrayList<>();
        while (rs.next()) {
//            String zone_code = rs.getString("ZONE_CODE");
//            String zoneName = rs.getString("ZONE_NAME");
//            double total = rs.getDouble("total_score");
//            String formattedTotal = String.format("%.2f", total);
//            double total_score = Double.parseDouble(formattedTotal);
//            Integer way_to_grade = score.marks2(total_score);
//            double weighted_average = way_to_grade * 0.5;
//
//            CgstMISReports cgstMISReports = new CgstMISReports(zoneName, zone_code, weighted_average, 0.00, formattedDate, gstname);
//            reportsList.add(cgstMISReports);
        }
        return reportsList;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Adjudication__5__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public List<GstMISReports> processResultSet_Adjudication(ResultSet rs, String formattedDate, String gstname) throws SQLException {
        List<GstMISReports> reportsList = new ArrayList<>();
        while (rs.next()) {
            String zoneName = rs.getString("ZONE_NAME");
            String zone_code = rs.getString("ZONE_CODE");

            double total5a = rs.getDouble("score_of_subparameter5a");
            double total5b = rs.getDouble("score_of_subparameter5b");

            double median5a = rs.getDouble("median5a");
            //double median5b = rsGst14aa.getDouble("median_numerator_3b");

            Double numerator_5a = rs.getDouble("col10");
            //Double numerator_5b = rsGst14aa.getDouble("numerator_3b");

            int way_to_grade5a = score.marks5a(total5a);
            int way_to_grade5b = score.marks5b(total5b);

            int insentavization5a = way_to_grade5a;
            int insentavization5b = way_to_grade5b;

            if (numerator_5a > median5a && way_to_grade5a < 10) {
                insentavization5a += 1;
            }

            double sub_parameter_weighted_average5a = insentavization5a * 0.5;
            double sub_parameter_weighted_average5b = insentavization5b * 0.5;

            double weighted_average = sub_parameter_weighted_average5a + sub_parameter_weighted_average5b;

            GstMISReports cgstMISReports = new GstMISReports(zoneName, zone_code, weighted_average, 0.00, formattedDate, gstname);
            reportsList.add(cgstMISReports);
        }
        return reportsList;
    }
}
