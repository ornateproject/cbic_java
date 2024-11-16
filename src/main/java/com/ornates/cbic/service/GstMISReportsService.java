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
            double weighted_average = way_to_grade;

            GstMISReports cgstMISReports = new GstMISReports(zoneName, zone_code, weighted_average, 0.00, formattedDate, gstname);
            reportsList.add(cgstMISReports);
        }
        return reportsList;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Scrutiny__3__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public List<GstMISReports> processResultSet_Scrutiny(ResultSet rs, String formattedDate, String gstname) throws SQLException {
        List<GstMISReports> reportsList = new ArrayList<>();
        while (rs.next()) {
            String zone_code = rs.getString("ZONE_CODE");
            String zoneName = rs.getString("ZONE_NAME");
            double total3a = rs.getDouble("score_of_subpara3a");
            double total3b = rs.getDouble("score_of_parameter3b");

            double median3a = rs.getDouble("median_numerator_3a");
            double median3b = rs.getDouble("median_numerator_3b");

            Double numerator_3a = rs.getDouble("numerator_3a");
            Double numerator_3b = rs.getDouble("numerator_3b");

            int way_to_grade3a = score.marks3a(total3a);
            int way_to_grade3b = score.marks3b(total3b);

            int insentavization3a = way_to_grade3a;
            int insentavization3b = way_to_grade3b;

            // Logic to adjust insentavization3a and insentavization3b based on the median and numerator values
            if (numerator_3a > median3a && way_to_grade3a < 10) {
                insentavization3a += 1;
            }
            if (numerator_3b > median3b && way_to_grade3b < 10) {
                insentavization3b += 1;
            }

            Integer way_to_grade = way_to_grade3a + way_to_grade3b;
            Integer insentavization = insentavization3a + insentavization3b;

            double sub_parameter_weighted_average3a = insentavization3a * 0.5;
            double sub_parameter_weighted_average3b = insentavization3b * 0.5;

            double total_score = sub_parameter_weighted_average3a + sub_parameter_weighted_average3b;
            double weighted_average = sub_parameter_weighted_average3a + sub_parameter_weighted_average3b;


            GstMISReports cgstMISReports = new GstMISReports(zoneName, zone_code, weighted_average, 0.00, formattedDate, gstname);
            reportsList.add(cgstMISReports);
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

// =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Adjudication_Legacy__6__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
public List<GstMISReports> processResultSet_AdjudicationLegacy(ResultSet rs, String formattedDate, String gstname) throws SQLException {
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
// =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Refunds__7__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
public List<GstMISReports> processResultSet_Refunds(ResultSet rs, String formattedDate, String gstname) throws SQLException {
    List<GstMISReports> reportsList = new ArrayList<>();
    while (rs.next()) {
        String zone_code = rs.getString("ZONE_CODE");
        Integer insentavization = 0;
        String zoneName = rs.getString("ZONE_NAME");
        // Int Zonal_rank = rs.getInt("z_rank");
        double tScore = rs.getDouble("total_score"); // 100 is multiply in query

        String formattedTotal = String.format("%.2f", tScore);
        double total_score = Double.parseDouble(formattedTotal);
        int way_to_grade = score.marks7(total_score);
        double weighted_average = way_to_grade * 0.5;

        GstMISReports cgstMISReports = new GstMISReports(zoneName, zone_code, weighted_average, 0.00, formattedDate, gstname);
            reportsList.add(cgstMISReports);
    }
    return reportsList;
}

// =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=RecoveryOfArrears__8__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
public List<GstMISReports> processResultSet_RecoveryOfArrears(ResultSet rs, String formattedDate, String gstname) throws SQLException {
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
//            GstMISReports cgstMISReports = new GstMISReports(zoneName, zone_code, weighted_average, 0.00, formattedDate, gstname);
//            reportsList.add(cgstMISReports);
    }
    return reportsList;
}

// =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Arrests & prosecution__9__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
public List<GstMISReports> processResultSet_ArrestsAndProsecution(ResultSet rs, String formattedDate, String gstname) throws SQLException {
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
//            GstMISReports cgstMISReports = new GstMISReports(zoneName, zone_code, weighted_average, 0.00, formattedDate, gstname);
//            reportsList.add(cgstMISReports);
    }
    return reportsList;
}

// =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Audit__10__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
public List<GstMISReports> processResultSet_Audit(ResultSet rs, String formattedDate, String gstname) throws SQLException {
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
//            GstMISReports cgstMISReports = new GstMISReports(zoneName, zone_code, weighted_average, 0.00, formattedDate, gstname);
//            reportsList.add(cgstMISReports);
    }
    return reportsList;
}

// =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Appeals__11__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
public List<GstMISReports> processResultSet_Appeals(ResultSet rs, String formattedDate, String gstname) throws SQLException {
    List<GstMISReports> reportsList = new ArrayList<>();
    while (rs.next()) {
        String zone_code = rs.getString("ZONE_CODE");
        String zoneName = rs.getString("ZONE_NAME");
        double total_score_11a = rs.getDouble("total_score_11a") * 100;
        double total_score_11b = rs.getDouble("total_score_11b") * 100;
        double total_score_11c = rs.getDouble("total_score_11c") * 100;
        double total_score_11d = rs.getDouble("total_score_11d") * 100;

        double numerator_11a = rs.getDouble("numerator_11a");
        double median_11a = rs.getDouble("median_11a");
        double numerator_11c = rs.getDouble("numerator_11c");
        double median_11c = rs.getDouble("median_11c");

        Integer way_to_grade_11a = score.marks11a(total_score_11a, numerator_11a);
        Integer way_to_grade_11b = score.marks11b(total_score_11b);
        Integer way_to_grade_11c = score.marks11c(total_score_11c,numerator_11c);
        Integer way_to_grade_11d = score.marks11d(total_score_11d);

        int insentavization_11a = way_to_grade_11a;
        int insentavization_11b = way_to_grade_11b;
        int insentavization_11c = way_to_grade_11c;
        int insentavization_11d = way_to_grade_11d;

        if (numerator_11a > median_11a && way_to_grade_11a < 10) {
            insentavization_11a += 1;
        }
        if (numerator_11c > median_11c && way_to_grade_11c < 10) {
            insentavization_11c += 1;
        }

        double sub_parameter_weighted_average_11a = insentavization_11a * 0.25;
        double sub_parameter_weighted_average_11b = insentavization_11b * 0.25;
        double sub_parameter_weighted_average_11c = insentavization_11c * 0.25;
        double sub_parameter_weighted_average_11d = insentavization_11d * 0.25;

        double weighted_average = (sub_parameter_weighted_average_11a + sub_parameter_weighted_average_11b + sub_parameter_weighted_average_11c + sub_parameter_weighted_average_11d) ;


        GstMISReports cgstMISReports = new GstMISReports(zoneName, zone_code, weighted_average, 0.00, formattedDate, gstname);
            reportsList.add(cgstMISReports);
    }
    return reportsList;
}

}

