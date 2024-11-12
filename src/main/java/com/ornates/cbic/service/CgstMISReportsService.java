package com.ornates.cbic.service;

import com.ornates.cbic.model.response.CgstMISReports;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CgstMISReportsService {
    CgstGradeScore score = new CgstGradeScore();


    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=ReturnFiling__2__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    // Method to process the ResultSet and create CgstMISReports objects
    public List<CgstMISReports> processResultSet_ReturnFiling(ResultSet rs, String formattedDate, String gstname) throws SQLException {
        List<CgstMISReports> reportsList = new ArrayList<>();
        while (rs.next()) {
            String zone_code = rs.getString("ZONE_CODE");
            String zoneName = rs.getString("ZONE_NAME");
            double total = rs.getDouble("total_score");
            String formattedTotal = String.format("%.2f", total);
            double total_score = Double.parseDouble(formattedTotal);
            Integer way_to_grade = score.marks2(total_score);
            double weighted_average = way_to_grade * 0.5;

            CgstMISReports cgstMISReports = new CgstMISReports(zoneName, zone_code, weighted_average, 0.00, formattedDate, gstname);
            reportsList.add(cgstMISReports);
        }
        return reportsList;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Scrutiny__3__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public List<CgstMISReports> processResultSet_Scrutiny(ResultSet rs, String formattedDate, String gstname) throws SQLException {
        List<CgstMISReports> reportsList = new ArrayList<>();
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
    public List<CgstMISReports> processResultSet_Investigation(ResultSet rs, String formattedDate, String gstname) throws SQLException {
        List<CgstMISReports> reportsList = new ArrayList<>();
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
    public List<CgstMISReports> processResultSet_Adjudication(ResultSet rs, String formattedDate, String gstname) throws SQLException {
        List<CgstMISReports> reportsList = new ArrayList<>();
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
}
