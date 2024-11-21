package com.ornates.cbic.service;

import com.ornates.cbic.model.response.CustomMISReports;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

@Service
public class CustomMISReportsService {

    CustomGreadeScore score = new CustomGreadeScore();
    private static final Map<String, String> zoneCodeToNameMap = Map.ofEntries(
            entry("54", "MEERUT CE & GST"),
            entry("55", "VISHAKAPATNAM CE & GST"),
            entry("56", "HYDERABAD CE & GST"),
            entry("57", "BENGALURU CE & GST"),
            entry("58", "THIRUVANANTHAPURAM CE & GST"),
            entry("60", "RANCHI CE & GST"),
            entry("62", "BHUBANESHWAR CE & GST"),
            entry("63", "JAIPUR CE & GST"),
            entry("66", "NAGPUR CE & GST"),
            entry("67", "MUMBAI CE & GST"),
            entry("68", "PUNE CE & GST"),
            entry("69", "BHOPAL CE & GST"),
            entry("70", "GUWAHATI CE & GST"),
            entry("71", "Ahmedabad CUS"),
            entry("72", "Bangalore CUS"),
            entry("73", "Chennai CUS"),
            entry("74", "DELHI CUS"),
            entry("75", "DELHI PREV"),
            entry("76", "KOLKATA CUS"),
            entry("77", "MUMBAI - I CUS"),
            entry("78", "MUMBAI - II CUS"),
            entry("79", "MUMBAI - III CUS"),
            entry("80", "PATNA PREV"),
            entry("81", "TIRUCHIRAPALLI PREV"),
            entry("DD", "DRI DG")
    );
    // Method to get the zone name based on zone code
    public String getZoneName(String zoneCode) {
        return zoneCodeToNameMap.getOrDefault(zoneCode, null);
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Timely payment of  Refunds__1__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public List<CustomMISReports> processResultSet_TimelyPaymentOfRefunds(ResultSet rs,String formattedDate, String gstname ) throws SQLException {
        List<CustomMISReports> reportsList = new ArrayList<>();
        while (rs.next()) {
            String zone_code = rs.getString("ZONE_CODE");
            String zoneName = rs.getString("ZONE_NAME");
            double  total = rs.getDouble("total_score");

            String formattedTotal = String.format("%.2f", total);
            double total_score = Double.parseDouble(formattedTotal);
            Integer way_to_grade =score.c_marks1(total_score);
            double weighted_average = way_to_grade;

            CustomMISReports customMISReports = new CustomMISReports(zoneName, zone_code, weighted_average, 0.00, formattedDate, gstname);
            reportsList.add(customMISReports);
        }
        return reportsList;
    }
    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Management of Export Obligation(EPCG)__2__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Management of Export Obligation(AA)__3__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Disposal / Pendency of Provisional Assessments__4__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Adjudication__5__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public List<CustomMISReports> processResultSet_Adjudication(ResultSet rs,String formattedDate, String gstname ) throws SQLException {
        List<CustomMISReports> reportsList = new ArrayList<>();
        while (rs.next()) {
            String zoneName = rs.getString("ZONE_NAME");
            String zone_code = rs.getString("ZONE_CODE");

            double total5a = rs.getDouble("total_score5a");
            double total5b = rs.getDouble("total_score5b");
            double total5c = rs.getDouble("total_score5c");

            double median5a = rs.getDouble("cus5a_median");
            //double median5b = rsGst14aa.getDouble("median_numerator_3b");

            Double numerator_5a = rs.getDouble("col5a");
            //Double numerator_5b = rsGst14aa.getDouble("numerator_3b");

            int way_to_grade5a = score.c_marks5a(total5a);
            int way_to_grade5b = score.c_marks5b(total5b);
            int way_to_grade5c = score.c_marks5c(total5c);

            int insentavization5a = way_to_grade5a;
            int insentavization5b = way_to_grade5b;
            int insentavization5c = way_to_grade5c;

            if (numerator_5a > median5a && way_to_grade5a < 10) {
                insentavization5a += 1;
            }
            Integer way_to_grade = way_to_grade5a + way_to_grade5b+way_to_grade5c;
            Integer insentavization = insentavization5a + insentavization5b +insentavization5c;

            double sub_parameter_weighted_average5a = insentavization5a * 0.3;
            double sub_parameter_weighted_average5b = insentavization5b * 0.4;
            double sub_parameter_weighted_average5c = insentavization5c * 0.3;

            // Calculate sub_parameter_weighted_average
            double sub_parameter_weighted_average = sub_parameter_weighted_average5a + sub_parameter_weighted_average5b + sub_parameter_weighted_average5c;

            // Round to two decimal places
            sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;

            double weighted_average = sub_parameter_weighted_average;

            //Double weighted_average = String.format("%.2f", sub_parameter_weighted_average);

            CustomMISReports customMISReports = new CustomMISReports(zoneName, zone_code, weighted_average, 0.00, formattedDate, gstname);
            reportsList.add(customMISReports);
        }
        return reportsList;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Investigation__6__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Arrests and Prosecution__7__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Monitoring of un-cleared/unclaimed cargo__8__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Disposal of confiscated Gold and NDPS__9__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public List<CustomMISReports> processResultSet_DisposalOfConfiscatedGoldAndNDPS(ResultSet rsGst14aa,String formattedDate, String gstname ) throws SQLException {
        List<CustomMISReports> reportsList = new ArrayList<>();
        while (rsGst14aa.next()) {
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zone_code = rsGst14aa.getString("ZONE_CODE");

            double total9a = rsGst14aa.getDouble("main_total_score");
            double total9b = rsGst14aa.getDouble("total_score");

            double median9a = rsGst14aa.getDouble("median_9a");
            double median9b = rsGst14aa.getDouble("Median");

            Double numerator_9a = rsGst14aa.getDouble("numerator_9a");
            Double numerator_9b = rsGst14aa.getDouble("s5col13");

            int way_to_grade9a = score.c_marks9a(total9a);
            int way_to_grade9b = score.c_marks9b(total9b);

            int insentavization9a = way_to_grade9a;
            int insentavization9b = way_to_grade9b;

            // Logic to adjust insentavization9a and insentavization9b based on the median and numerator values
            if (numerator_9a > median9a && way_to_grade9a < 10) {
                insentavization9a += 1;
            }
            if (numerator_9b > median9b && way_to_grade9b < 10) {
                insentavization9b += 1;
            }

            Integer way_to_grade = way_to_grade9a + way_to_grade9b;
            Integer insentavization = insentavization9a + insentavization9b;

            double sub_parameter_weighted_average9a = insentavization9a * 0.5;
            double sub_parameter_weighted_average9b = insentavization9b * 0.5;

            double total_score = sub_parameter_weighted_average9a + sub_parameter_weighted_average9b;
            double weighted_average = sub_parameter_weighted_average9a + sub_parameter_weighted_average9b;

            CustomMISReports customMISReports = new CustomMISReports(zoneName, zone_code, weighted_average, 0.00, formattedDate, gstname);
            reportsList.add(customMISReports);
        }
        return reportsList;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Recovery of Arrears__10__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Management of Warehousing bonds__11__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Commissioner (Appeals)__12__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
    public List<CustomMISReports> processResultSet_CommissionerAppeals(ResultSet rsGst14aa,String formattedDate, String gstname ) throws SQLException {
        List<CustomMISReports> reportsList = new ArrayList<>();
        while (rsGst14aa.next()) {
            String zoneName = rsGst14aa.getString("ZONE_NAME");
            String zone_code = rsGst14aa.getString("ZONE_CODE");
            double numerator = rsGst14aa.getDouble("numerator12A");
            double median12A = rsGst14aa.getDouble("median12A");
            double total_score12A = rsGst14aa.getDouble("Total_score12A");
            double total_score12B = rsGst14aa.getDouble("total_score12B");

            int way_to_grade12a = score.c_marks12a(total_score12A, numerator);
            int way_to_grade12b = score.c_marks12b(total_score12B);

            int insentavization12a = way_to_grade12a;
            int insentavization12b = way_to_grade12b;

            if (numerator > median12A && way_to_grade12a < 10) {
                insentavization12a += 1;
            }

            Integer way_to_grade = way_to_grade12a + way_to_grade12b;
            Integer insentavization = insentavization12a + insentavization12b;

            double sub_parameter_weighted_average12a = insentavization12a * 0.5;
            double sub_parameter_weighted_average12b = insentavization12b * 0.5;

            double total_score = sub_parameter_weighted_average12a + sub_parameter_weighted_average12b;

            double total_weighted_average = sub_parameter_weighted_average12a + sub_parameter_weighted_average12b;
            // Round to two decimal places
            Double weighted_average = Math.round(total_weighted_average * 100.0) / 100.0;


            CustomMISReports customMISReports = new CustomMISReports(zoneName, zone_code, weighted_average, 0.00, formattedDate, gstname);
            reportsList.add(customMISReports);
        }
        return reportsList;
    }

    // =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=Audit__13__=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

}
