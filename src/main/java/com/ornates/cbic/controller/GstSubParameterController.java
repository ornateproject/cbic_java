package com.ornates.cbic.controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.ornates.cbic.dao.Query.GstSubParameterWiseQuery;
import com.ornates.cbic.service.GstSubParameterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ornates.cbic.dao.pool.JDBCConnection;
import com.ornates.cbic.dao.result.GetExecutionSQL;
import com.ornates.cbic.model.response.GST4A;
import com.ornates.cbic.service.DateCalculate;
import com.ornates.cbic.service.GstGradeScore;
import com.ornates.cbic.service.RelevantAspect;
/*
 * @Author: @Kinshuk_Maity
 */
//@CrossOrigin
@RequestMapping("/cbic") //....
@Controller
public class GstSubParameterController {
    private Logger logger = LoggerFactory.getLogger(CustomMISReportsController.class);
    GstGradeScore score=new GstGradeScore();
    GstSubParameterService gstSubParameterService = new GstSubParameterService();
    @ResponseBody
    @RequestMapping(value = "/")
    public String home() {
        return "its working api";
    }

    /*
     * Date: April 29, 2024
     * Purpose: This methods for performing check the database  .
     */
    @ResponseBody
    @RequestMapping(value = "/db_check")
    public String bdTest() {

        //Connection done
        Connection con = JDBCConnection.getTNConnection();
        System.out.println("Connection :"+con);
        return "its working api";
    }
    /*
     * Date: May 04, 2024
     * created:
     * updated: RKS, may 17, 2024
     * Purpose: This methods have core function in Return Filing .
     */
    @ResponseBody
    @RequestMapping(value = "/gst1a")
    //  http://localhost:8080/cbicApi/cbic/gst1a?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst1a?month_date=2023-04-01&zone_code=70&type=commissary
    public Object getGst1A(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;


        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1a_ZoneWise(month_date);
                //Result Set
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.GST1A_RA;
                    String commname = "ALL";
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col6 = rsGst14aa.getInt("col6");
                    int col2 = rsGst14aa.getInt("col2");
                    int col4 = rsGst14aa.getInt("col4");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col6)+"/"+String.valueOf(col2 + col4);

                    if((col2 + col4) != 0) {
                        total = (((double) col6 * 100) / (col2 + col4));
                    }else {
                        total = 0.00;
                    }

                    rank = score.marks1a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),"ALL",totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1a_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.GST1A_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col6 = rsGst14aa.getInt("col6");
                    int col2 = rsGst14aa.getInt("col2");
                    int col4 = rsGst14aa.getInt("col4");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col6)+"/"+String.valueOf(col2 + col4);

                    if((col2 + col4) != 0) {
                        total = (((double) col6 * 100) / (col2 + col4));
                    }else {
                        total = 0.00;
                    }
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    rank = score.marks2(total);
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }


    /*
     * Date: May 04, 2024
     * created:
     * updated: RKS, may 18, 2024
     * Purpose: This methods have core function in Return Filing .
     */

    @ResponseBody
    @RequestMapping(value = "/gst1b")
    //  http://localhost:8080/cbicApi/cbic/gst1b?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst1b?month_date=2023-04-01&zone_code=70&type=commissary
    public Object getGst1B(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1b_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.GST1B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = "ALL";
                    int col10=rsGst14aa.getInt("col10");
                    int col7=rsGst14aa.getInt("col7");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    if(col7 != 0) {
                        total = (((double) col10 * 100) / col7);
                    }else{
                        total = 0.00;
                    }
                    String absval = String.valueOf(col10) + "/" + String.valueOf(col7);

                    rank = score.marks1b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1b_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.GST1B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    int col10=rsGst14aa.getInt("col10");
                    int col7=rsGst14aa.getInt("col7");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col10) + "/" + String.valueOf(col7);

                    if(col7 != 0) {
                        total = (((double) col10 * 100) / col7);
                    }else{
                        total = 0.00;
                    }

                    rank = score.marks1b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }


    /*
     * Date: May 04, 2024
     * created:
     * updated: RKS, may 18, 2024
     * Purpose: This methods have core function in Return Filing .
     */

    @ResponseBody
    @RequestMapping(value = "/gst1c")
    //  http://localhost:8080/cbicApi/cbic/gst1c?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst1c?month_date=2023-04-01&zone_code=70&type=commissary
//	  http://localhost:8080/cbicApi/cbic/gst1c?month_date=2023-04-01&type=all_commissary
    public Object getGst1C(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1c_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while (rsGst14aa.next()) {
                    String ra= RelevantAspect.GST1C_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname="ALL";
                    int col10 = rsGst14aa.getInt("col10");
                    int col2 = rsGst14aa.getInt("col2");
                    int col3 = rsGst14aa.getInt("col3");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval=String.valueOf(col10)+"/"+String.valueOf(col2 + col3);

                    if((col2 + col3) != 0){
                        total = ((double) (col10) * 100 / (col2 + col3));
                    }else {
                        total=0.00;
                    }
                    //rank=score.marks1c(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);

                    int way_to_grade = score.marks1c(totalScore);
                    double sub_parameter_weighted_average_bfore = way_to_grade * 0.1;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);

                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst1c_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.GST1C_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col10 = rsGst14aa.getInt("col10");
                    int col2 = rsGst14aa.getInt("col2");
                    int col3 = rsGst14aa.getInt("col3");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval=String.valueOf(col10)+"/"+String.valueOf(col2 + col3);
                    if((col2 + col3) != 0){
                        total = ((double) (col10) * 100 / (col2 + col3));
                    }else {
                        total=0.00;
                    }
                    //rank=score.marks1c(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);

                    int way_to_grade = score.marks1c(totalScore);
                    double sub_parameter_weighted_average_bfore = way_to_grade * 0.1;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);

                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst1c_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.GST1C_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col10 = rsGst14aa.getInt("col10");
                    int col2 = rsGst14aa.getInt("col2");
                    int col3 = rsGst14aa.getInt("col3");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval=String.valueOf(col10)+"/"+String.valueOf(col2 + col3);

                    if((col2 + col3) != 0){
                        total = ((double) (col10) * 100 / (col2 + col3));
                    }else {
                        total=0.00;
                    }
                    rank=score.marks1c(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade =score.marks1c(totalScore);
                    double sub_parameter_weighted_average_bfore = way_to_grade * 0.1;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);

                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
//        return allGstaList.stream()
//                .sorted(Comparator.comparing(GST4A::getSub_parameter_weighted_average))
//                .collect(Collectors.toList());

    }
    /*
     * Date: May 04, 2024
     * created:
     * updated: RKS, may 22, 2024
     * Purpose: This methods have core function in Return Filing .
     */
//
//	@ResponseBody
//	@RequestMapping(value = "/gst1d")
//	//  http://localhost:8080/CbicAPI/cbic/gst1d?month_date=2023-04-01&type=zone
//	//  http://localhost:8080/CbicAPI/cbic/gst1d?month_date=2023-04-01&zone_code=70&type=commissary
//	public Object getGst1D(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code) {
//
//		List<GST4A> allGstaList = new ArrayList<>();
//		GST4A gsta = null;
//		int rank = 0;
//		double total = 0.00;
//
//		try {
//			if (type.equalsIgnoreCase("zone")) {
//				// Query string
//				String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE, "
//						+ "sum(14c.OPENING_BALANCE+14c.no_of_arn_received_gstn+14c.no_of_arn_received_gstn-14c.DISPOSAL_OF_ARN_DEEMED_REG) as col17, "
//						+ "sum(14c.OPENING_BALANCE) as col1, "
//						+ "sum(14c.no_of_arn_received_gstn) as col2, "
//						+ "sum(14c.no_of_arn_received_gstn) as col3, "
//						+ "sum(14c.no_of_arn_received_cpc) as col4, "
//						+ "sum(14c.no_of_arn_received_cpc) as col5 "
//						+ "FROM  mis_gst_commcode  cc right join mis_dpm_gst_14a  14c on cc.COMM_CODE=14c.COMM_CODE "
//						+ " left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE  "
//						+ " where MM_YYYY='" + month_date + "' group by ZONE_CODE";
//				//Result Set
//				ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
//				while (rsGst14aa.next()) {
//					String ra= RelevantAspect.GST1D_RA;
//					String zoneCode = rsGst14aa.getString("ZONE_CODE");
//					int col17 = rsGst14aa.getInt("col17");
//					int col1 = rsGst14aa.getInt("col1");
//					int col2 = rsGst14aa.getInt("col2");
//					int col3 = rsGst14aa.getInt("col3");
//					int col4 = rsGst14aa.getInt("col4");
//					int col5 = rsGst14aa.getInt("col5");
//					String absval=String.valueOf(col17)+"/"+String.valueOf((col1 + col2 + col3 + col4 + col5));
//
//
//					if((col1 + col2 + col3 + col4 + col5) != 0) {
//						total = ((double) col17 / (col1 + col2 + col3 + col4 + col5));
//					}else{
//						total = 0.00;
//					}
//					rank = score.marks1d(total);
//					String formattedTotal = String.format("%.2f", total);
//					double totalScore = Double.parseDouble(formattedTotal);
//					gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),"ALL",totalScore,rank,absval,zoneCode,ra);
//					allGstaList.add(gsta);
//				}
//			}else if (type.equalsIgnoreCase("commissary")) {
//				String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE,cc.COMM_NAME,\n" +
//						"(14c.OPENING_BALANCE+14c.no_of_arn_received_gstn+14c.no_of_arn_received_gstn-14c.DISPOSAL_OF_ARN_DEEMED_REG) as col17, \n" +
//						"(14c.OPENING_BALANCE) as col1,\n" +
//						"(14c.no_of_arn_received_gstn) as col2,\n" +
//						"(14c.no_of_arn_received_gstn) as col3, \n" +
//						"(14c.no_of_arn_received_cpc) as col4,\n" +
//						"(14c.no_of_arn_received_cpc) as col5 \n" +
//						"FROM  mis_gst_commcode  cc right join mis_dpm_gst_14a  14c on cc.COMM_CODE=14c.COMM_CODE \n" +
//						"left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE  where MM_YYYY='"+ month_date+"' and zc.ZONE_CODE = '"+zone_code+"';";
//				//Result Set
//				ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
//				while (rsGst14aa.next()) {
//					String ra= RelevantAspect.GST1D_RA;
//					String zoneCode = rsGst14aa.getString("ZONE_CODE");
//					String commname=rsGst14aa.getString("COMM_NAME");
//					int col17 = rsGst14aa.getInt("col17");
//					int col1 = rsGst14aa.getInt("col1");
//					int col2 = rsGst14aa.getInt("col2");
//					int col3 = rsGst14aa.getInt("col3");
//					int col4 = rsGst14aa.getInt("col4");
//					int col5 = rsGst14aa.getInt("col5");
//					String absval=String.valueOf(col17)+"/"+String.valueOf((col1 + col2 + col3 + col4 + col5));
//
//
//					if((col1 + col2 + col3 + col4 + col5) != 0) {
//						total = ((double) col17 / (col1 + col2 + col3 + col4 + col5));
//					}else{
//						total = 0.00;
//					}
//					rank = score.marks1d(total);
//					String formattedTotal = String.format("%.2f", total);
//					double totalScore = Double.parseDouble(formattedTotal);
//					gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname, totalScore,rank,absval,zoneCode,ra);
//					allGstaList.add(gsta);
//				}
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return allGstaList;
//	}

    @ResponseBody
    @RequestMapping(value = "/gst1d")
    //  http://localhost:8080/cbicApi/cbic/gst1d?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst1d?month_date=2023-04-01&zone_code=70&type=commissary
//	  http://localhost:8080/cbicApi/cbic/gst1d?month_date=2023-04-01&type=all_commissary
    public Object getGst1D(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1d_ZoneWise(month_date);

                //Result Set
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String ra= RelevantAspect.GST1D_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col14 = rsGst14aa.getInt("col14");
                    int col1 = rsGst14aa.getInt("col1");
                    int col2 = rsGst14aa.getInt("col2");
                    int col3 = rsGst14aa.getInt("col3");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval=String.valueOf(col14)+"/"+String.valueOf((col1 + col2 + col3));
                    if((col1 + col2 + col3) != 0) {
                        total = ((double) col14 * 100 / (col1 + col2 + col3));
                    }else{
                        total = 0.00;
                    }
                    rank = score.marks1d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks1d(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.1;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),"ALL",totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1d_CommissonaryWise(month_date,zone_code);

                //Result Set
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String ra= RelevantAspect.GST1D_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col14 = rsGst14aa.getInt("col14");
                    int col1 = rsGst14aa.getInt("col1");
                    int col2 = rsGst14aa.getInt("col2");
                    int col3 = rsGst14aa.getInt("col3");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval=String.valueOf(col14)+"/"+String.valueOf((col1 + col2 + col3));
                    if((col1 + col2 + col3) != 0) {
                        total = ((double) col14 * 100 / (col1 + col2 + col3));
                    }else{
                        total = 0.00;
                    }
                    //rank = score.marks1d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade =score.marks1d(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.1;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1d_AllCommissonaryWise(month_date);

                //Result Set
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String ra= RelevantAspect.GST1D_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col14 = rsGst14aa.getInt("col14");
                    int col1 = rsGst14aa.getInt("col1");
                    int col2 = rsGst14aa.getInt("col2");
                    int col3 = rsGst14aa.getInt("col3");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval=String.valueOf(col14)+"/"+String.valueOf((col1 + col2 + col3));


                    if((col1 + col2 + col3) != 0) {
                        total = ((double) col14 * 100/ (col1 + col2 + col3));
                    }else{
                        total = 0.00;
                    }
                    rank = score.marks1d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks1d(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.1;
                    sub_parameter_weighted_average = Math.round(sub_parameter_weighted_average * 100.0) / 100.0;
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    /*
     * Date: May 04, 2024
     * created:
     * updated: RKS & Nishant, may 18,31, 2024
     * Purpose: This methods have core function in Return Filing .
     */
    @ResponseBody
    @RequestMapping(value = "/gst1e")
    //  http://localhost:8080/cbicApi/cbic/gst1e?month_date=2023-04-01&type=zone
//  http://localhost:8080/cbicApi/cbic/gst1e?month_date=2023-04-01&zone_code=51&type=commissary
//	  http://localhost:8080/cbicApi/cbic/gst1e?month_date=2023-04-01&type=all_commissary
    public Object getGst1E(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa =new GstSubParameterWiseQuery().QueryFor_gst1e_ZoneWise(month_date);
                //Result Set
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String ra= RelevantAspect.GST1E_RA;
                    String commname="ALL";
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col9 = rsGst14aa.getInt("col9");
                    int col1 = rsGst14aa.getInt("col1");
                    int col2 = rsGst14aa.getInt("col2");
                    int col5 = rsGst14aa.getInt("col5");
                    int col6 = rsGst14aa.getInt("col6");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval=String.valueOf(col9)+"/"+String.valueOf(col1 + col2 + col5 + col6);

                    if((col1 + col2 + col5 + col6) != 0) {
                        total = (((double) col9 * 100)/ (col1 + col2 + col5 + col6));
                    }else {
                        total=0.00;
                    }
                    rank = score.marks1e(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks1e(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.3;
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                // Query string
                String queryGst14aa =  new GstSubParameterWiseQuery().QueryFor_gst1e_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);

                while (rsGst14aa.next()) {
                    String ra= RelevantAspect.GST1E_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col9 = rsGst14aa.getInt("col9");
                    int col1 = rsGst14aa.getInt("col1");
                    int col2 = rsGst14aa.getInt("col2");
                    int col5 = rsGst14aa.getInt("col5");
                    int col6 = rsGst14aa.getInt("col6");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval=String.valueOf(col9)+"/"+String.valueOf(col1 + col2 + col5 + col6);

                    if((col1 + col2 + col5 + col6) != 0) {
                        total = (((double) col9 * 100)/ (col1 + col2 + col5 + col6));
                    }else {
                        total=0.00;
                    }
                    //rank = score.marks1e(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks1e(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.3;
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1e_AllCommissonaryWise(month_date);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);

                while (rsGst14aa.next()) {
                    String ra= RelevantAspect.GST1E_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col9 = rsGst14aa.getInt("col9");
                    int col1 = rsGst14aa.getInt("col1");
                    int col2 = rsGst14aa.getInt("col2");
                    int col5 = rsGst14aa.getInt("col5");
                    int col6 = rsGst14aa.getInt("col6");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval=String.valueOf(col9)+"/"+String.valueOf(col1 + col2 + col5 + col6);

                    if((col1 + col2 + col5 + col6) != 0) {
                        total = (((double) col9 * 100)/ (col1 + col2 + col5 + col6));
                    }else {
                        total=0.00;
                    }
                    //rank = score.marks1e(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks1e(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.3;
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace() ;
        }
        return allGstaList;
    }


    /*
     * Date: May 04, 2024
     * created:
     * updated: RKS & Nishant, may 24,31, 2024
     * Purpose: This methods have core function in Return Filing .
     */
    @ResponseBody
    @RequestMapping(value = "/gst1f")
    //  http://localhost:8080/cbicApi/cbic/gst1f?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst1f?month_date=2023-04-01&zone_code=51&type=commissary
    //  http://localhost:8080/cbicApi/cbic/gst1f?month_date=2023-04-01&type=all_commissary
    public Object getGst1F(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code ) {
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1f_ZoneWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);

                while (rsGst14aa.next()) {
                    String ra= RelevantAspect.GST1F_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col14 = rsGst14aa.getInt("col14");
                    int col10 = rsGst14aa.getInt("col10");
                    int col11 = rsGst14aa.getInt("col11");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval=String.valueOf(col14)+"/"+String.valueOf(col10 + col11);

                    if ((col10 + col11) != 0) {
                        total = (((double) col14 * 100)/ (col10 + col11));
                    }else{
                        total=0.00;
                    }

                    //rank = score.marks1f(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks1f(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.3;
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),"ALL",totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {//gst1f-commissary
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1f_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.GST1F_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String zonename = rsGst14aa.getString("ZONE_NAME");
                    int col14 = rsGst14aa.getInt("col14");
                    int col10 = rsGst14aa.getInt("col10");
                    int col11 = rsGst14aa.getInt("col11");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval = String.valueOf(col14) + "/" + String.valueOf(col10 + col11);

                    if ((col10 + col11) != 0) {
                        total = (((double) col14 * 100)/ (col10 + col11));
                    } else {
                        total = 0.00;
                    }

                    rank = score.marks1f(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks1f(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.3;
                    gsta = new GST4A(zonename, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst1f_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.GST1F_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String zonename = rsGst14aa.getString("ZONE_NAME");
                    int col14 = rsGst14aa.getInt("col14");
                    int col10 = rsGst14aa.getInt("col10");
                    int col11 = rsGst14aa.getInt("col11");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval = String.valueOf(col14) + "/" + String.valueOf(col10 + col11);

                    if ((col10 + col11) != 0) {
                        total = (((double) col14 * 100)/ (col10 + col11));
                    } else {
                        total = 0.00;
                    }

                    //rank = score.marks1f(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks1f(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.3;
                    gsta = new GST4A(zonename, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    /*
     * Date: May 04, 2024
     * created:
     * updated: Nishant, may 28, 2024
     * Purpose: This methods have core function in Return Filing .
     */
    @ResponseBody
    @RequestMapping(value = "/gst2")
//	  http://localhost:8080/cbicApi/cbic/gst2?month_date=2023-04-01&type=zone
//	  http://localhost:8080/cbicApi/cbic/gst2?month_date=2023-04-01&zone_code=70&type=commissary
//	  http://localhost:8080/cbicApi/cbic/gst2?month_date=2023-04-01&type=all_commissary
    public Object getGst2(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;


        try {
            // Query string
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa =new GstSubParameterWiseQuery().QueryFor_gst2_ZoneWise(month_date);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.Gst2_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname= "ALL";
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval=String.valueOf(col21)+"/"+String.valueOf(col3);
                    total = rsGst14aa.getDouble("total_score");

                    //total=((double) col21 / col3);
                    //}
                    //rank=score.marks2(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks2(totalScore);
                    int sub_parameter_weighted_average = way_to_grade;
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }

            } else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst2_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra=RelevantAspect.Gst2_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval=String.valueOf(col21)+"/"+String.valueOf(col3);

                    //total=((double) col21 / col3);
                    total=(((double) col21) * 100 / col3);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks2(totalScore);
                    int sub_parameter_weighted_average = way_to_grade;

                    // rank=score.marks2(total);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst2_AllCommissonaryWise(month_date);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra=RelevantAspect.Gst2_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col21=rsGst14aa.getInt("col21");
                    int col3=rsGst14aa.getInt("col3");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval=String.valueOf(col21)+"/"+String.valueOf(col3);

                    total=(((double) col21) *100 / col3);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks2(totalScore);
                    int sub_parameter_weighted_average = way_to_grade;
                    //rank=score.marks2(total);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }
    /*
     * Date: May 04, 2024
     * created:
     * updated: Nishant, may 18, 2024
     * Purpose: This methods have core function in Return Filing .
     */
//    @ResponseBody
//    @RequestMapping(value = "/gst3a")
//    // http://localhost:8080/cbicApi/cbic/gst3a?month_date=2023-04-01&type=zone
//    //  http://localhost:8080/cbicApi/cbic/gst3a?month_date=2023-04-01&zone_code=70&type=commissary
//    public Object getGst3A(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
//
//        List<GST4A> allGstaList = new ArrayList<>();
//        GST4A gsta = null;
//        int rank = 0;
//        double total = 0.00;
//
//        try {
//            if (type.equalsIgnoreCase("zone")) {
//                String prev_month_new =DateCalculate.getPreviousMonth(month_date);
//                // Query string
//                String queryGst14aa= "WITH current_month_data AS (\n" +
//                        "    SELECT\n" +
//                        "        zc.ZONE_NAME,\n" +
//                        "        cc.ZONE_CODE,\n" +
//                        "        SUM(14c.SCRUTINIZED_ASMT_10) AS col5,\n" +
//                        "        SUM(14c.RETURN_SCRUTINY) AS col2\n" +
//                        "    FROM\n" +
//                        "        mis_gst_commcode AS cc\n" +
//                        "        RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
//                        "        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
//                        "    WHERE\n" +
//                        "        14c.MM_YYYY = '"+ month_date+"'\n" +
//                        "    GROUP BY\n" +
//                        "        cc.ZONE_CODE\n" +
//                        "),\n" +
//                        "previous_month_data AS (\n" +
//                        "    SELECT\n" +
//                        "        zc.ZONE_NAME,\n" +
//                        "        cc.ZONE_CODE,\n" +
//                        "        SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col6,\n" +
//                        "        SUM(14c.RETURN_SCRUTINY) AS col3\n" +
//                        "    FROM\n" +
//                        "        mis_gst_commcode AS cc\n" +
//                        "        RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
//                        "        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
//                        "    WHERE\n" +
//                        "        14c.MM_YYYY =  '" + prev_month_new + "'\n" +
//                        "    GROUP BY\n" +
//                        "        cc.ZONE_CODE\n" +
//                        ")\n" +
//                        "SELECT\n" +
//                        "    curr.ZONE_NAME,\n" +
//                        "    curr.ZONE_CODE,\n" +
//                        "    curr.col5 AS col5_current_month,\n" +
//                        "    curr.col2 AS col2_current_month,\n" +
//                        "    prev.col6 AS col6_previous_month,\n" +
//                        "    prev.col3 AS col3_previous_month\n" +
//                        "FROM\n" +
//                        "    current_month_data AS curr\n" +
//                        "    LEFT JOIN previous_month_data AS prev ON curr.ZONE_CODE = prev.ZONE_CODE;";
//
//                //Result Set
//                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
//                while(rsGst14aa.next()) {
//                    String ra = RelevantAspect.Gst3A_RA;
//                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
//                    int col6 = rsGst14aa.getInt("col6_previous_month");
//                    int col5 = rsGst14aa.getInt("col5_current_month");
//                    int col3 = rsGst14aa.getInt("col3_previous_month");
//                    int col2 = rsGst14aa.getInt("col2_current_month");
//                    String absval = String.valueOf(col6 + col5) + "/" + String.valueOf(col3 + col2);
//                    if (col3 + col2 != 0) {
//                        total = ((double) (col6 + col5) / (col3 + col2));
//                    }
//                    rank = score.marks3a(total);
//                    String formattedTotal = String.format("%.2f", total);
//                    double totalScore = Double.parseDouble(formattedTotal);
//                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", totalScore, rank, absval, zoneCode, ra);
//                    allGstaList.add(gsta);
//                }
//            }else if (type.equalsIgnoreCase("commissary")) {
//                String prev_month_new =DateCalculate.getPreviousMonth(month_date);
//                // Query string
//                String queryGst14aa="WITH SecondQuery AS (\n" +
//                        "    SELECT cc.COMM_CODE, 14c.CLOSING_NO AS col2\n" +
//                        "    FROM mis_gst_commcode AS cc \n" +
//                        "    RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
//                        "    WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + prev_month_new + "'\n" +
//                        ")\n" +
//                        "SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, \n" +
//                        "       14c.SCRUTINIZED_ASMT_10 AS col6, \n" +
//                        "       14c.SCRUTINIZED_DISCRIPANCY_FOUND AS col5, \n" +
//                        "       14c.RETURN_SCRUTINY AS col3,\n" +
//                        "       sq.col2\n" +
//                        "FROM mis_gst_commcode AS cc \n" +
//                        "RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
//                        "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
//                        "LEFT JOIN SecondQuery AS sq ON sq.COMM_CODE = cc.COMM_CODE\n" +
//                        "WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "';\n";
//
//
//
//                //Result Set
//                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
//                while(rsGst14aa.next()) {
//                    String commname=rsGst14aa.getString("COMM_NAME");
//                    String ra=RelevantAspect.Gst3A_RA;
//                    String zoneName = rsGst14aa.getString("ZONE_NAME");
//                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
//                    int col6=rsGst14aa.getInt("col6");
//                    int col5=rsGst14aa.getInt("col5");
//                    int col3=rsGst14aa.getInt("col3");
//                    int col2=rsGst14aa.getInt("col2");
//                    String absval = String.valueOf(col6 + col5) + "/" + String.valueOf(col3 + col2);
//
//                    if ((col3+col2) != 0){
//                        total =((double) (col6+col5)/(col3+col2));
//                    }
//
//                    rank=score.marks3a(total);
//                    String formattedTotal = String.format("%.2f", total);
//                    double totalScore = Double.parseDouble(formattedTotal);
//
//                    gsta = new GST4A(zoneName, commname, (Double)totalScore, rank, absval, zoneCode,ra);
//                    allGstaList.add(gsta);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return allGstaList;
//    }

    //                              that code for temporary

    @ResponseBody
    @RequestMapping(value = "/gst3a")
    // http://localhost:8080/cbicApi/cbic/gst3a?month_date=2023-05-01&type=zone
    // http://localhost:8080/cbicApi/cbic/gst3a?month_date=2023-05-01&zone_code=69&type=commissary
    // http://localhost:8080/cbicApi/cbic/gst3a?month_date=2023-05-01&type=all_commissary
    public Object getGst3A(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        Double median = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);
                // Query string
//                String queryGst14aa= "\n" +
//                        "WITH ranked_data AS (SELECT current_data.ZONE_NAME, current_data.ZONE_CODE, current_data.col4,  current_data.col9,  current_data.col10,  current_data.col2,  previous_data.col1,\n" +
//                        "        (current_data.col4 + current_data.col9 + current_data.col10) / (current_data.col2 + previous_data.col1)*100 AS total_score,\n" +
//                        "        concat((current_data.col4 + current_data.col9 + current_data.col10), \"/\" , (current_data.col2 + previous_data.col1)) as absval\n" +
//                        "    FROM  \n" +
//                        "        (SELECT zc.ZONE_NAME,  cc.ZONE_CODE,SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col4,SUM(14c.OUTCOME_ASMT_12_ISSUED) AS col9,  SUM(14c.OUTCOME_SECTION_61) AS col10,SUM(14c.RETURN_SCRUTINY) AS col2  \n" +
//                        "            FROM mis_gst_commcode AS cc  \n" +
//                        "                RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
//                        "                LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
//                        "            WHERE 14c.MM_YYYY = '"+ month_date+"' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
//                        "        ) AS current_data\n" +
//                        "    JOIN \n" +
//                        "        (SELECT zc.ZONE_NAME,  cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col1 \n" +
//                        "            FROM mis_gst_commcode AS cc \n" +
//                        "                RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
//                        "                LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
//                        "            WHERE 14c.MM_YYYY = '" + prev_month_new + "' GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
//                        "        ) AS previous_data\n" +
//                        "    ON current_data.ZONE_CODE = previous_data.ZONE_CODE\n" +
//                        ")\n" +
//                        "SELECT ZONE_NAME,ZONE_CODE,col4,col9,col10,col2,col1,total_score,absval,\n" +
//                        "    RANK() OVER (ORDER BY total_score DESC) AS z_rank FROM ranked_data;";

                //Result Set
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst3a_ZoneWise(month_date);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String commname = "All";
                    String ra = RelevantAspect.Gst3A_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String absval = rsGst14aa.getString("absval");
                    Double t_score = rsGst14aa.getDouble("total_score");
                    median = rsGst14aa.getDouble("median_numerator_3a");
                    Double numerator_3a = rsGst14aa.getDouble("numerator_3a");

                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks3a(totalScore);
                    int insentavization = score.marks3a(totalScore);

                    if (numerator_3a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "no";

                    Double sub_parameter_weighted_average = insentavization * 0.5 ;

                    gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println("gst3a median zone wise :- " + median); //********************************** for testing ************************************

            }else if (type.equalsIgnoreCase("commissary")) {
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);
                // Query string
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst3a_CommissonaryWise(month_date,zone_code);

                //Result Set
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra = RelevantAspect.Gst3A_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col1 = rsGst14aa.getInt("prev_col1");
                    int col2 = rsGst14aa.getInt("col2");
                    int col4 = rsGst14aa.getInt("col4");
                    int col9 = rsGst14aa.getInt("col9");
                    int col10 = rsGst14aa.getInt("col10");
                    Double t_score = rsGst14aa.getDouble("total_score");
                    median = rsGst14aa.getDouble("median");
                    Double numerator_3a = rsGst14aa.getDouble("numerator_3a");

                    String absval = String.valueOf(col4 + col9 + col10) + "/" + String.valueOf(col2 + col1);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks3a(totalScore);
                    int insentavization = score.marks3a(totalScore);

                    if (numerator_3a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "no";

                    Double sub_parameter_weighted_average = insentavization * 0.5 ;
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }System.out.println("median gst3a all commi : "+ median);
            }else if (type.equalsIgnoreCase("all_commissary")) {
                // Query string
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst3a_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra = RelevantAspect.Gst3A_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col1 = rsGst14aa.getInt("prev_col1");
                    int col2 = rsGst14aa.getInt("col2");
                    int col4 = rsGst14aa.getInt("col4");
                    int col9 = rsGst14aa.getInt("col9");
                    int col10 = rsGst14aa.getInt("col10");
                    Double t_score = rsGst14aa.getDouble("total_score");
                    median = rsGst14aa.getDouble("median");
                    Double numerator_3a = rsGst14aa.getDouble("numerator_3a");

                    String absval = String.valueOf(col4 + col9 + col10) + "/" + String.valueOf(col2 + col1);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks3a(totalScore);
                    int insentavization = score.marks3a(totalScore);

                    if (numerator_3a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "no";

                    Double sub_parameter_weighted_average = insentavization * 0.5 ;
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println("median gst3a all commi : "+ median);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    /*
     * Date: May 04, 2024
     * created:
     * updated: Nishant, may 18, 2024
     * Purpose: This methods have core function in Return Filing .
     */

//    @ResponseBody
//    @RequestMapping(value = "/gst3b")
//    //  http://localhost:8080/cbicApi/cbic/gst3b?month_date=2023-04-01&type=zone
//    //  http://localhost:8080/cbicApi/cbic/gst3b?month_date=2023-04-01&zone_code=70&type=commissary
//    public Object getGst3B(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
//
//        List<GST4A> allGstaList = new ArrayList<>();
//        GST4A gsta = null;
//        int rank = 0;
//        double total = 0.00;
//        //Connection done
//        Connection con= JDBCConnection.getTNConnection();
//
//        try {
//            if (type.equalsIgnoreCase("zone")) {
//                String prev_month_new =DateCalculate.getPreviousMonth(month_date);
//                // Query string
//                String queryGst14aa = "WITH current_month_data AS (\n" +
//                        "    SELECT\n" +
//                        "        zc.ZONE_NAME,\n" +
//                        "        cc.ZONE_CODE,\n" +
//                        "      \n" +
//                        "        SUM(14c.TAX_LIABILITY_DETECTECT) AS col14,\n" +
//                        "        SUM(14c.AMOUNT_RECOVERED_TAX+14c.AMOUNT_RECOVERED_INTEREST+14c.AMOUNT_RECOVERED_PENALTY) AS col22\n" +
//                        "    FROM\n" +
//                        "        mis_gst_commcode AS cc\n" +
//                        "        RIGHT JOIN mis_dggst_gst_scr_2a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
//                        "        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
//                        "    WHERE\n" +
//                        "        14c.MM_YYYY = '" + month_date + "'\n" +
//                        "    GROUP BY\n" +
//                        "        cc.ZONE_CODE\n" +
//                        "),\n" +
//                        "previous_month_data AS (\n" +
//                        "    SELECT\n" +
//                        "        zc.ZONE_NAME,\n" +
//                        "        cc.ZONE_CODE,\n" +
//                        "        SUM(14c.AMOUNT_RECOVERED_TAX+14c.AMOUNT_RECOVERED_INTEREST+14c.AMOUNT_RECOVERED_PENALTY) AS col22_1,\n" +
//                        "        SUM(14c.TAX_LIABILITY_DETECTECT) AS col14_1\n" +
//                        "    FROM\n" +
//                        "        mis_gst_commcode AS cc\n" +
//                        "        RIGHT JOIN mis_dggst_gst_scr_2a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
//                        "        LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
//                        "    WHERE\n" +
//                        "        14c.MM_YYYY = '" + prev_month_new + "'\n" +
//                        "    GROUP BY\n" +
//                        "        cc.ZONE_CODE\n" +
//                        ")\n" +
//                        "SELECT\n" +
//                        "    curr.ZONE_NAME,\n" +
//                        "    curr.ZONE_CODE,\n" +
//                        "    curr.col22 AS col22_current_month,\n" +
//                        "    curr.col14 AS col14_current_month,\n" +
//                        "    prev.col22_1 AS col22_1_previous_month,\n" +
//                        "    prev.col14_1 AS col14_1_previous_month\n" +
//                        "FROM\n" +
//                        "    current_month_data AS curr\n" +
//                        "    LEFT JOIN previous_month_data AS prev ON curr.ZONE_CODE = prev.ZONE_CODE;";
//
//                PreparedStatement psGst14aa = con.prepareStatement(queryGst14aa);
//
//                //Result Set
//                ResultSet rsGst14aa = psGst14aa.executeQuery();
//
//
//                while (rsGst14aa.next()) {
//                    String ra = RelevantAspect.Gst3B_RA;
//                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
//                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
//                    int col22 = rsGst14aa.getInt("col22_current_month");
//                    int col14 = rsGst14aa.getInt("col14");
//                    if (col14 != 0) {
//                        total = ((double) col22 / col14);
//                    }
//                    rank = score.marks3b(total);
//                    String absval = String.valueOf(col22) + "/" + String.valueOf(col14);
//                    String formattedTotal = String.format("%.2f", total);
//                    double totalScore = Double.parseDouble(formattedTotal);
//                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", (Double) totalScore, rank, absval, zoneCode, ra);
//                    allGstaList.add(gsta);
//                }
//            }else if (type.equalsIgnoreCase("commissary")) {
//
//                // Query string
//                String queryGst14aa="SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 14c.amount_recovered_penalty AS col22, "
//                        +"14c.tax_liability_detectect AS col14 "
//                        +"FROM mis_gst_commcode AS cc "
//                        +"RIGHT JOIN mis_dggst_gst_scr_2a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE "
//                        + "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE "
//                        + "WHERE 14c.MM_YYYY ='" +month_date+"' AND zc.ZONE_CODE = '"+ zone_code +"';";
//
//                //Prepared Statement
//                PreparedStatement psGst14aa=con.prepareStatement(queryGst14aa);
//                //Result Set
//                ResultSet rsGst14aa= psGst14aa.executeQuery();
//
//                while(rsGst14aa.next() ) {
//                    String commname=rsGst14aa.getString("COMM_NAME");
//                    String ra=RelevantAspect.Gst3B_RA;
//                    String zoneName = rsGst14aa.getString("ZONE_NAME");
//                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
//                    int col22=rsGst14aa.getInt("col22");
//                    int col14=rsGst14aa.getInt("col14");
//                    String absval=String.valueOf(col22)+"/"+String.valueOf(col14);
//
//                    if (col14 != 0){
//                        total =((double) col22/col14);
//                    }
//
//                    rank=score.marks3b(total);
//                    String formattedTotal = String.format("%.2f", total);
//                    double totalScore = Double.parseDouble(formattedTotal);
//                    gsta=new GST4A(zoneName, commname,totalScore, rank, absval, zoneCode,ra);
//                    allGstaList.add(gsta);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return allGstaList;
//    }

    //                              that code for temporary
    @ResponseBody
    @RequestMapping(value = "/gst3b") // msg:- 3b zone testing done, commmisonary not testing
    //  http://localhost:8080/cbicApi/cbic/gst3b?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst3b?month_date=2023-04-01&zone_code=70&type=commissary
    // http://localhost:8080/cbicApi/cbic/gst3b?month_date=2023-05-01&type=all_commissary
    public Object getGst3B(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        Double median = 0.00;
        Connection con = JDBCConnection.getTNConnection();

        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa =new GstSubParameterWiseQuery().QueryFor_gst3b_ZoneWise(month_date);
                PreparedStatement psGst14aa=con.prepareStatement(queryGst14aa);
                //Result Set
                ResultSet rsGst14aa= psGst14aa.executeQuery();
                while(rsGst14aa.next() ) {
                    String commname = "ALL";
                    String ra = RelevantAspect.Gst3B_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String absval = rsGst14aa.getString("absval");
                    double t_score = rsGst14aa.getDouble("score_of_parameter");
                    median = rsGst14aa.getDouble("median_numerator_3b");
                    Double numerator_3b = rsGst14aa.getDouble("numerator_3b");


                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks3b(totalScore);
                    int insentavization = score.marks3b(totalScore);

                    if (numerator_3b > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "no";

                    Double sub_parameter_weighted_average = insentavization * 0.5;
                    gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra,
                            Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);
                // Query string
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst3b_CommissonaryWise(month_date,zone_code);
                PreparedStatement psGst14aa=con.prepareStatement(queryGst14aa);
                ResultSet rsGst14aa= psGst14aa.executeQuery();
                //allGstaList.addAll(gstSubParameterService.gst3bZone(rsGst14aa));
                while(rsGst14aa.next() ) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.Gst3B_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String absval=rsGst14aa.getString("absval");
                    double t_score = rsGst14aa.getDouble("score_of_parameter") * 100;
                    median = rsGst14aa.getDouble("median_3b");
                    Double numerator_3b = rsGst14aa.getDouble("numerator_3b");


                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks3b(totalScore);
                    int insentavization = score.marks3b(totalScore);

                    if (numerator_3b > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "no";

                    Double sub_parameter_weighted_average = insentavization * 0.5 ;
                    gsta=new GST4A(zoneName, commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);

                // Query string
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst3b_AllCommissonaryWise(month_date);
                //Prepared Statement
                PreparedStatement psGst14aa=con.prepareStatement(queryGst14aa);
                //Result Set
                ResultSet rsGst14aa= psGst14aa.executeQuery();

                while(rsGst14aa.next() ) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.Gst3B_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String absval=rsGst14aa.getString("absval");
                    double t_score = rsGst14aa.getDouble("score_of_parameter") * 100;
                    median = rsGst14aa.getDouble("median_3b");
                    Double numerator_3b = rsGst14aa.getDouble("numerator_3b");


                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks3b(totalScore);
                    int insentavization = score.marks3b(totalScore);

                    if (numerator_3b > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "no";

                    Double sub_parameter_weighted_average = insentavization * 0.5 ;
                    gsta=new GST4A(zoneName, commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println("median 3b commi wise :-" + median);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GST4A::getTotal_score).reversed()).collect(Collectors.toList());
    }


    /*
     * Date: May 04, 2024
     * created:
     * updated: Nishant, may 18, 2024
     * Purpose: This methods have core function in Return Filing .
     */
    @ResponseBody
    @RequestMapping(value = "/gst4a")
    //  http://localhost:8080/cbicApi/cbic/gst4a?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst4a?month_date=2024-04-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst4a?month_date=2024-04-01&type=all_commissary
    public Object getGst4A(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code ) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        Double median = 0.00;
        int rank = 0;
        double total = 0.00;
        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string

                // Query string
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst4a_ZoneWise(month_date);


                //Result Set
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String commname="ALL";
                    String ra=RelevantAspect.GST4A_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");

                    int col13=rsGst14aa.getInt("col13");
                    int col1=rsGst14aa.getInt("col1");
                    Double t_score = rsGst14aa.getDouble("total_score");
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int Zonal_rank = 0;
                    String gst = "no";

                    // int insentavization = 0;
                    // int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col13)+"/"+String.valueOf(col1);

                    //Double t_score = rsGst14aa.getDouble("score_of_parameter");
                    median = rsGst14aa.getDouble("median_4a");
                    Double numerator_3b = rsGst14aa.getDouble("col13");


                    int way_to_grade = score.marks4a(totalScore);
                    int insentavization = score.marks4a(totalScore);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_3b > median && way_to_grade < 10) {
                        insentavization += 1;
                    }

                    //System.out.println("insentavization3b after :-" + insentavization);

                    // int Zonal_rank = 0;
                    // String gst = "no";

                    double sub_parameter_weighted_average_bfore = insentavization * 0.3 ;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    //double sub_parameter_weighted_average = 0.00 ;

                    gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }

            } else if (type.equalsIgnoreCase("commissary")) {
                // Query string
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst4a_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra=RelevantAspect.GST4A_RA;
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    Double t_score = rsGst14aa.getDouble("score_of_parameter4a");
                    int col13=rsGst14aa.getInt("col13");
                    int col1=rsGst14aa.getInt("col1");
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int Zonal_rank = 0;
                    String gst = "no";

                    // int insentavization = 0;
                    // int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col13)+"/"+String.valueOf(col1);

                    //Double t_score = rsGst14aa.getDouble("score_of_parameter");
                    median = rsGst14aa.getDouble("median_4a");
                    Double numerator_3b = rsGst14aa.getDouble("col13");


                    int way_to_grade = score.marks4a(totalScore);
                    int insentavization = score.marks4a(totalScore);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_3b > median && way_to_grade < 10) {
                        insentavization += 1;
                    }

                    //System.out.println("insentavization3b after :-" + insentavization);

                    // int Zonal_rank = 0;
                    // String gst = "no";

                    double sub_parameter_weighted_average_bfore = insentavization * 0.3 ;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    //double sub_parameter_weighted_average = 0.00 ;

                    gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst4a_AllCommissonaryWise(month_date);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra=RelevantAspect.GST4A_RA;
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    Double t_score = rsGst14aa.getDouble("score_of_parameter4a");
                    int col13=rsGst14aa.getInt("col13");
                    int col1=rsGst14aa.getInt("col1");
                    // Double t_score = rsGst14aa.getDouble("total_score");
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int Zonal_rank = 0;
                    String gst = "no";

                    // int insentavization = 0;
                    // int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col13)+"/"+String.valueOf(col1);

                    //Double t_score = rsGst14aa.getDouble("score_of_parameter");
                    median = rsGst14aa.getDouble("median_4a");
                    Double numerator_3b = rsGst14aa.getDouble("col13");


                    int way_to_grade = score.marks4a(totalScore);
                    int insentavization = score.marks4a(totalScore);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_3b > median && way_to_grade < 10) {
                        insentavization += 1;
                    }

                    //System.out.println("insentavization3b after :-" + insentavization);

                    // int Zonal_rank = 0;
                    // String gst = "no";

                    double sub_parameter_weighted_average_bfore = insentavization * 0.3 ;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    //double sub_parameter_weighted_average = 0.00 ;

                    gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    /*
     * Date: May 04, 2024
     * created:
     * updated: Nishant, may 18, 2024
     * Purpose: This methods have core function in Return Filing .
     */

    @ResponseBody
    @RequestMapping(value = "/gst4b")
    //  http://localhost:8080/cbicApi/cbic/gst4b?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst4b?month_date=2023-04-01&zone_code=70&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/gst4b?month_date=2023-04-01&type=all_commissary
    public Object getGst4B(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code ) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        Connection con= JDBCConnection.getTNConnection();
        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst4b_ZoneWise(month_date);
                PreparedStatement psGst14aa=con.prepareStatement(queryGst14aa);
                //Result Set
                ResultSet rsGst14aa= psGst14aa.executeQuery();
                while(rsGst14aa.next()) {
                    String ra = RelevantAspect.Gst4B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col29 = rsGst14aa.getInt("col29");
                    int col31 = rsGst14aa.getInt("col31");
                    int col25 = rsGst14aa.getInt("col25");
                    Double t_score = rsGst14aa.getDouble("total_score4b");
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int Zonal_rank = 0;
                    String gst = "no";
                    // int way_to_grade = 0;
                    int insentavization = 0;
//                    int sub_parameter_weighted_average = 0;
                    //  int way_to_grade = score.marks4b(totalScore);
//                    int sub_parameter_weighted_average = way_to_grade*0.3;
//                    if (col25 != 0) {
//                        total = (((double) (col29 + col31) * 100) / col25);
//                    }
                    //}
                    rank = score.marks4b(total);
                    //double totalScore = 0;
                    int way_to_grade = score.marks4b(totalScore);
                    double sub_parameter_weighted_average_bfore = way_to_grade * 0.3;
                    // String formattedTotal = String.format("%.2f", total);
                    totalScore = Double.parseDouble(formattedTotal);
                    String absval = String.valueOf(col29 + col31) + "/" + String.valueOf(col25);
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", totalScore, absval, zoneCode, ra,
                            Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average_bfore);
                    allGstaList.add(gsta);
                }
            } else if (type.equalsIgnoreCase("commissary")) {
                // Query string
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst4b_CommissonaryWise(month_date,zone_code);


                //Prepared Statement
                PreparedStatement psGst14aa=con.prepareStatement(queryGst14aa);

                //Result Set
                ResultSet rsGst14aa= psGst14aa.executeQuery();
                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.Gst4B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col29=rsGst14aa.getInt("col29");
                    int col31=rsGst14aa.getInt("col31");
                    int col25=rsGst14aa.getInt("col25");
                    Double t_score = rsGst14aa.getDouble("total_score4b");
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int Zonal_rank = 0;
                    String gst = "no";
                    // int way_to_grade = 0;
                    int insentavization = 0;
                    //  int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col29+col31) + "/" + String.valueOf(col25);
                    // if(col25!=0) {
                    //total=(((double) (col29+col31) * 100) / col25);
                    //}
                    //}
                    rank=score.marks4b(total);
                    int way_to_grade = score.marks4b(totalScore);
                    double sub_parameter_weighted_average = way_to_grade * 0.3;
                    // String formattedTotal = String.format("%.2f", total);
                    //double totalScore = Double.parseDouble(formattedTotal);

                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);

                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                // Query string
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst4b_AllCommissonaryWise(month_date);
                //Prepared Statement
                PreparedStatement psGst14aa=con.prepareStatement(queryGst14aa);

                //Result Set
                ResultSet rsGst14aa= psGst14aa.executeQuery();
                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.Gst4B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col29=rsGst14aa.getInt("col29");
                    int col31=rsGst14aa.getInt("col31");
                    int col25=rsGst14aa.getInt("col25");
                    Double t_score = rsGst14aa.getDouble("total_score4b");
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int Zonal_rank = 0;
                    String gst = "no";
                    //int way_to_grade = 0;
                    int insentavization = 0;
                    // int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col29+col31) + "/" + String.valueOf(col25);
                    // if(col25!=0) {
                    // total=(((double) (col29+col31) * 100) / col25);
                    //}
                    //}
                    rank=score.marks4b(total);
                    int way_to_grade = score.marks4b(totalScore);
                    double sub_parameter_weighted_average = way_to_grade * 0.3;
                    //  String formattedTotal = String.format("%.2f", total);
                    // double totalScore = Double.parseDouble(formattedTotal);

                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);

                    allGstaList.add(gsta);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;

    }

    /*
     * Date: May 04, 2024
     * created:
     * updated: RKS , july 9. 2024
     * Purpose: This methods have core function in Return Filing .
     */
    @ResponseBody
    @RequestMapping(value = "/gst4c")
    //  http://localhost:8080/cbicApi/cbic/gst4c?month_date=2024-04-01&type=zone                        // updated
    //  http://localhost:8080/cbicApi/cbic/gst4c?month_date=2024-04-01&zone_code=70&type=commissary     //  updated
    //	http://localhost:8080/cbicApi/cbic/gst4c?month_date=2024-04-01&type=all_commissary              //  updated
    public Object getGst4AC(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList=new ArrayList<>();
        GST4A gsta=null;
        int rank = 0;
        double total = 0.00;
        double median = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {

                // Query string
                String queryGst3aa= new GstSubParameterWiseQuery().QueryFor_gst4c_ZoneWise(month_date);
                //Result Set
                ResultSet rsGst14aa=GetExecutionSQL.getResult(queryGst3aa);
                while( rsGst14aa.next()) {
                    String ra=RelevantAspect.Gst4C_RA;
                    String commname="ALL";
                    String zoneName =rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    Double t_score = rsGst14aa.getDouble("score_of_parameter4c");
                    String absval = rsGst14aa.getString("avsvl");
                    double col1_7=rsGst14aa.getInt("col1_7");
                    double col1_8=rsGst14aa.getInt("col1_8");
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int Zonal_rank = 0;
                    String gst = "no";

                    // int insentavization = 0;
                    // int sub_parameter_weighted_average = 0;
                    //String absval=String.valueOf(col13)+"/"+String.valueOf(col1);

                    //Double t_score = rsGst14aa.getDouble("score_of_parameter");
                    median = rsGst14aa.getDouble("median_4c");
                    Double numerator_3b = rsGst14aa.getDouble("col1_7");


                    int way_to_grade = score.marks4c(totalScore);
                    int insentavization = score.marks4c(totalScore);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_3b > median && way_to_grade < 10) {
                        insentavization += 1;
                    }

                    //System.out.println("insentavization3b after :-" + insentavization);

                    // int Zonal_rank = 0;
                    // String gst = "no";

                    double sub_parameter_weighted_average_bfore = insentavization * 0.2 ;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    //double sub_parameter_weighted_average = 0.00 ;

                    gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }System.out.println("gst4c zone wise median :" + median);

                //String start_date = null;
                //System.out.println("Start Date: " + start_date);
                System.out.println("Month Date: " + month_date);

            } else if (type.equalsIgnoreCase("commissary")) { //gst4c
                //String prev_month_new =DateCalculate.getPreviousMonth(month_date);
                // Query string
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst4c_CommissonaryWise(month_date,zone_code);

                //Result Set
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next() ) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.Gst4C_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    Double t_score = rsGst14aa.getDouble("score_of_parameter4c");
                    String absval = rsGst14aa.getString("avsvl");
                    median = rsGst14aa.getDouble("median_4c");
                    int col1_7=rsGst14aa.getInt("col1_7");
                    int col1_8=rsGst14aa.getInt("col1_8");

                    //rank=score.marks4c(total);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks4c(totalScore);
                    int insentavization = score.marks4c(totalScore);

                    if (col1_7 > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "no";
                    double sub_parameter_weighted_average = insentavization * 0.2;
                    sub_parameter_weighted_average = Double.parseDouble(String.format("%.2f", sub_parameter_weighted_average));
                    // rank = score.marks6a(total);
                    rank = 0;
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println("gst4c commissonary median :" + median);
            }else if (type.equalsIgnoreCase("all_commissary")) {
                // Query string
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst4c_AllCommissonaryWise(month_date);
                //Result Set
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next() ) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.Gst4C_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    Double t_score = rsGst14aa.getDouble("score_of_parameter4c");
                    String absval = rsGst14aa.getString("avsvl");
                    median = rsGst14aa.getDouble("median_4c");
                    int col1_7=rsGst14aa.getInt("col1_7");
                    int col1_8=rsGst14aa.getInt("col1_8");

                    //rank=score.marks4c(total);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks4c(totalScore);
                    int insentavization = score.marks4c(totalScore);

                    if (col1_7 > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "no";
                    double sub_parameter_weighted_average = insentavization * 0.2;
                    sub_parameter_weighted_average = Double.parseDouble(String.format("%.2f", sub_parameter_weighted_average));
                    // rank = score.marks6a(total);
                    rank = 0;
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println( "gst4c all commissonary median : " + median);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    /*
     * Date: May 04, 2024
     * created:
     * updated: Nishant, may 18, 2024
     * Purpose: This methods have core function in Return Filing .
     */
    @ResponseBody
    @RequestMapping(value = "/gst4d")
    //  http://localhost:8080/cbicApi/cbic/gst4d?month_date=2023-05-01&type=zone                            // updated
    //  http://localhost:8080/cbicApi/cbic/gst4d?month_date=2023-05-01&zone_code=70&type=commissary         // not updated
    //	http://localhost:8080/cbicApi/cbic/gst4d?month_date=2023-05-01&type=all_commissary					// updated
    public Object getGst4d(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GST4A> allGstaList=new ArrayList<>();
        GST4A gsta=null;
        int rank = 0;
        double total = 0.00;
        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst4d_ZoneWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra=RelevantAspect.GST4D_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String commname="ALL";
                    Double t_score = rsGst14aa.getDouble("total_score");
                    String absval = rsGst14aa.getString("absval");
                    double col6_1=rsGst14aa.getInt("col6_1");
                    double col6_3=rsGst14aa.getInt("col6_3");
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int Zonal_rank = 0;
                    String gst = "no";

                    // int insentavization = 0;
                    // int sub_parameter_weighted_average = 0;
//                    String absval=String.valueOf(col6_1)+"/"+String.valueOf(col6_3);

                    //Double t_score = rsGst14aa.getDouble("score_of_parameter");
                    double median = rsGst14aa.getDouble("median_4d");
                    Double numerator_3b = rsGst14aa.getDouble("col6_1");


                    int way_to_grade = score.marks4d(totalScore);
                    int insentavization = score.marks4d(totalScore);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_3b > median && way_to_grade < 10) {
                        insentavization += 1;
                    }

                    //System.out.println("insentavization3b after :-" + insentavization);

                    // int Zonal_rank = 0;
                    // String gst = "no";

                    double sub_parameter_weighted_average_bfore = insentavization * 0.2 ;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    //double sub_parameter_weighted_average = 0.00 ;

                    gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            } else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date,zone_code);
//                // Query string
//                String queryGst46a="SELECT " +
//                        "cc.ZONE_CODE, " +
//                        " cc.COMM_NAME,"+
//                        "zc.ZONE_NAME, " +
//                        "(14c.REALISATION_CGST_AMT + 14c.REALISATION_IGST_AMT + 14c.REALISATION_SGST_AMT + 14c.REALISATION_CESS_AMT) AS col6_1 " +
//                        "FROM mis_gst_commcode AS cc " +
//                        "RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
//                        "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
//                        "WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "';";
//
//
//                String queryGst46b= "SELECT " +
//                        "cc.ZONE_CODE, " +
//                        " cc.COMM_NAME,"+
//                        "zc.ZONE_NAME, " +
//                        "(" +
//                        "    14c.REALISATION_CGST_AMT + " +
//                        "    14c.REALISATION_IGST_AMT + " +
//                        "    14c.REALISATION_SGST_AMT + " +
//                        "    14c.REALISATION_CESS_AMT " +
//                        ") AS col6_2 " +
//                        "FROM " +
//                        "    mis_gst_commcode AS cc " +
//                        "RIGHT JOIN " +
//                        "    mis_gi_gst_1 AS 14c " +
//                        "    ON cc.COMM_CODE = 14c.COMM_CODE " +
//                        "LEFT JOIN " +
//                        "    mis_gst_zonecode AS zc " +
//                        "    ON zc.ZONE_CODE = cc.ZONE_CODE " +
//                        "WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + prev_month_new + "';";
//
//                String queryGst6c= "SELECT "
//                        + "    cc.ZONE_CODE, "
//                        +" cc.COMM_NAME,"
//                        + "    zc.ZONE_NAME, "
//                        + "    (14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_3 "
//                        + "FROM mis_gst_commcode AS cc "
//                        + "RIGHT JOIN "
//                        + "    mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE "
//                        + "LEFT JOIN "
//                        + "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE "
//                        + "WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "';";
//
//
//                String queryGst6d="SELECT " +
//                        "cc.ZONE_CODE, " +
//                        " cc.COMM_NAME,"+
//                        "zc.ZONE_NAME, " +
//                        "(14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_4 " +
//                        "FROM " +
//                        "mis_gst_commcode AS cc " +
//                        "RIGHT JOIN " +
//                        "mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
//                        "LEFT JOIN " +
//                        "mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
//                        "WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "';";
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
//                ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst46b);
//                ResultSet rsGst6c =GetExecutionSQL.getResult(queryGst6c);
//                ResultSet rsGst6d =GetExecutionSQL.getResult(queryGst6d);

                while(rsGst14aa.next()  ) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.GST4D_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    Double t_score = rsGst14aa.getDouble("total_score");
                    String absval = rsGst14aa.getString("absval");
                    double col6_1=rsGst14aa.getInt("col6_1");
                    double col6_3=rsGst14aa.getInt("col6_3");
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int Zonal_rank = 0;
                    String gst = "no";

                    // int insentavization = 0;
                    // int sub_parameter_weighted_average = 0;
                    //String absval=String.valueOf(col13)+"/"+String.valueOf(col1);

                    //Double t_score = rsGst14aa.getDouble("score_of_parameter");
                    double median = rsGst14aa.getDouble("median_4d");
                    Double numerator_3b = rsGst14aa.getDouble("col6_1");
//                    String absval=String.valueOf(col6_1)+"/"+String.valueOf(col6_3);


                    int way_to_grade = score.marks4d(totalScore);
                    int insentavization = score.marks4d(totalScore);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_3b > median && way_to_grade < 10) {
                        insentavization += 1;
                    }

                    //System.out.println("insentavization3b after :-" + insentavization);

                    // int Zonal_rank = 0;
                    // String gst = "no";

                    double sub_parameter_weighted_average_bfore = insentavization * 0.2 ;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    //double sub_parameter_weighted_average = 0.00 ;

                    gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                // Query string
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst4d_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.GST4D_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    Double t_score = rsGst14aa.getDouble("total_score");
                    String absval = rsGst14aa.getString("absval");
                    double col6_1=rsGst14aa.getInt("col6_1");
                    double col6_3=rsGst14aa.getInt("col6_3");
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int Zonal_rank = 0;
                    String gst = "no";

                    // int insentavization = 0;
                    // int sub_parameter_weighted_average = 0;
                    //String absval=String.valueOf(col13)+"/"+String.valueOf(col1);

                    //Double t_score = rsGst14aa.getDouble("score_of_parameter");
                    double median = rsGst14aa.getDouble("median_4d");
                    Double numerator_3b = rsGst14aa.getDouble("col6_1");
//                    String absval=String.valueOf(col6_1)+"/"+String.valueOf(col6_3);


                    int way_to_grade = score.marks4d(totalScore);
                    int insentavization = score.marks4d(totalScore);
                    // System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_3b > median && way_to_grade < 10) {
                        insentavization += 1;
                    }

                    //System.out.println("insentavization3b after :-" + insentavization);

                    // int Zonal_rank = 0;
                    // String gst = "no";

                    double sub_parameter_weighted_average_bfore = insentavization * 0.2 ;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    //double sub_parameter_weighted_average = 0.00 ;

                    gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }
    /*
     * Date: May 04, 2024
     * created:
     * updated: RKS & Nishant, june 11, 2024
     * Purpose: This methods have core function in Return Filing .
     */

    @ResponseBody
    @RequestMapping(value = "/gst5a")
    //  http://localhost:8080/cbicApi/cbic/gst5a?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst5a?month_date=2023-04-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst5a?month_date=2024-04-01&type=all_commissary
    public Object getGst5A(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        Double median = 0.00;
        //Connection done
        Connection con= JDBCConnection.getTNConnection();

        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst5a_ZoneWise(month_date);
                PreparedStatement psGst14aa = con.prepareStatement(queryGst14aa);
                ResultSet rsGst14aa = psGst14aa.executeQuery();
                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.GST5A_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = "All";
                    int col10 = rsGst14aa.getInt("col10");
                    int col4 = rsGst14aa.getInt("col4");
                    Double numerator_5a = rsGst14aa.getDouble("col10");
                    median = rsGst14aa.getDouble("median5a");
                    int Zonal_rank = 0;
                    String gst = "no";
                    String absval = String.valueOf(col10) + "/" + String.valueOf(col4);
                    total = rsGst14aa.getDouble("score_of_subparameter5a");

                    // rank = score.marks5a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);

                    int way_to_grade = score.marks5a(totalScore);
                    int insentavization = score.marks5a(totalScore);
                    if (numerator_5a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }

                    double sub_parameter_weighted_average = insentavization * 0.5 ;

                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println("gst5a median zone wise:- " + median);
            }else if (type.equalsIgnoreCase("commissary")) {
                // Query string
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst5a_CommissonaryWise(month_date,zone_code);


                //Prepared Statement
                PreparedStatement psGst14aa=con.prepareStatement(queryGst14aa);

                //Result Set
                ResultSet rsGst14aa= psGst14aa.executeQuery();
                while(rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    total = rsGst14aa.getDouble("score_of_subparameter_5a");
                    median = rsGst14aa.getDouble("median_5a");
                    Double numerator_5a = rsGst14aa.getDouble("numerator_5a");
                    String ra = RelevantAspect.GST5A_RA;
                    int col10 = rsGst14aa.getInt("numerator_5a");
                    int col4 = rsGst14aa.getInt("col4");
                    int Zonal_rank = 0;
                    String gst = "no";
                    String absval = rsGst14aa.getString("absvl_5a");
                    //String absval = String.valueOf(col10) + "/" + String.valueOf(col4);

                    // rank = score.marks5a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);

                    int way_to_grade = score.marks5a(totalScore);
                    int insentavization = score.marks5a(totalScore);
                    if (numerator_5a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }

                    double sub_parameter_weighted_average = insentavization * 0.5 ;

                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                // Query string
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst5a_AllCommissonaryWise(month_date);


                //Prepared Statement
                PreparedStatement psGst14aa=con.prepareStatement(queryGst14aa);

                //Result Set
                ResultSet rsGst14aa= psGst14aa.executeQuery();
                while(rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    total = rsGst14aa.getDouble("score_of_subparameter");
                    median = rsGst14aa.getDouble("median");
                    Double numerator_5a = rsGst14aa.getDouble("col10");
                    String ra = RelevantAspect.GST5A_RA;
                    int col10 = rsGst14aa.getInt("col10");
                    int col4 = rsGst14aa.getInt("col4");
                    int Zonal_rank = 0;
                    String gst = "no";
                    String absval = String.valueOf(col10) + "/" + String.valueOf(col4);

                    // rank = score.marks5a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);

                    int way_to_grade = score.marks5a(totalScore);
                    int insentavization = score.marks5a(totalScore);
                    if (numerator_5a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }

                    double sub_parameter_weighted_average = insentavization * 0.5 ;

                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }System.out.println("GST5A median commissonary wise:- "+ median);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GST4A::getTotal_score).reversed()).collect(Collectors.toList());
    }
    /*
     * Date: May 04, 2024
     * created:
     * updated: Nishant, may 18, 2024
     * Purpose: This methods have core function in Return Filing .
     */
    @ResponseBody
    @RequestMapping(value = "/gst5b")
    //  http://localhost:8080/cbicApi/cbic/gst5b?month_date=2023-05-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst5b?month_date=2023-05-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst5b?month_date=2023-05-01&type=all_commissary
    public Object getGst5B(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa =  new GstSubParameterWiseQuery().QueryFor_gst5b_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.Gst5B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    total = rsGst14aa.getDouble("total_score");
                    String commname="ALL";
                    int col22 = rsGst14aa.getInt("col22");
                    int col23 = rsGst14aa.getInt("col23");
                    int col16 = rsGst14aa.getInt("col16");
                    int Zonal_rank = 0;
                    String gst = "no";

                    int insentavization = 0;
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks5b(totalScore);
                    double sub_parameter_weighted_average = way_to_grade * 0.5 ;
                    String absval = String.valueOf(col22 + col23) + "/" + String.valueOf(col16);
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                // Query string
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst5b_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.Gst5B_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    total = rsGst14aa.getDouble("score_of_parameter");
                    int col22=rsGst14aa.getInt("col22");
                    int col23=rsGst14aa.getInt("col23");
                    int col16=rsGst14aa.getInt("col16");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;

//                    if (col16 != 0) {
//                        total=(((double) (col22+col23) * 100)/ (col16));
//                    }
                    //rank=score.marks5b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal) ;
                    int way_to_grade = score.marks5b(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.5;
                    String absval = String.valueOf(col22+col23) + "/" + String.valueOf(col16);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                // Query string
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst5b_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.Gst5B_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    total = rsGst14aa.getDouble("score_of_parameter");
                    int col22=rsGst14aa.getInt("col22");
                    int col23=rsGst14aa.getInt("col23");
                    int col16=rsGst14aa.getInt("col16");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;

//                    if (col16 != 0) {
//                        total=(((double) (col22+col23) * 100)/ (col16));}
                    //rank=score.marks5b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks5b(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.5;
                    String absval = String.valueOf(col22+col23) + "/" + String.valueOf(col16);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GST4A::getSub_parameter_weighted_average).reversed()).collect(Collectors.toList());
    }
    /*
     * Date: May 04, 2024
     * created:
     * updated: Nishant, may 18, 2024
     * Purpose: This methods have core function in Return Filing .
     */
    @ResponseBody
    @RequestMapping(value = "/gst6a")
    //  http://localhost:8080/cbicApi/cbic/gst6a?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst6a?month_date=2024-04-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst6a?month_date=2024-04-01&type=all_commissary
    public Object getGst6A(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        double median = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst6a_ZoneWise(month_date);
                // Query string
//                String queryGst14aa = "SELECT zc.ZONE_NAME,cc.ZONE_CODE,sum(14c.COMM_DISPOSAL_NO+14c.JC_DISPOSAL_NO+14c.AC_DISPOSAL_NO+14c.SUP_DISPOSAL_NO) as col9 " +
//
//                        " FROM mis_gst_commcode AS cc " +
//                        "RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
//                        "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
//                        "WHERE  14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE;";
//
//                String prev_month_new = DateCalculate.getPreviousMonth(month_date);
//
//                String queryGst3aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE, sum(14c.COMM_CLOSING_NO+14c.JC_CLOSING_NO+14c.AC_CLOSING_NO+14c.SUP_CLOSING_NO) AS col3 FROM mis_gst_commcode AS cc " +
//                        "RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
//                        "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
//                        "WHERE  14c.MM_YYYY = '" + prev_month_new + "' GROUP BY CC.ZONE_CODE;";

                //Result Set
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                // ResultSet rsGst3aa = GetExecutionSQL.getResult(queryGst3aa);
                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.Gst6A_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname ="ALL";
                    int col9 = rsGst14aa.getInt("col9");
                    int col3 = rsGst14aa.getInt("col3");
                    total = rsGst14aa.getDouble("total_score");
                    median = rsGst14aa.getDouble("median");
                    Double numerator_6a = rsGst14aa.getDouble("col9");
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks6a(totalScore);
                    int insentavization = score.marks6a(totalScore);

                    if (numerator_6a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "GST6A";
                    double sub_parameter_weighted_average = insentavization * 0.25;
                    // rank = score.marks6a(total);
                    rank = rsGst14aa.getInt("z_rank");
                    String absval = String.valueOf(col9) + "/" + String.valueOf(col3);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println("gst6a zone wise median :- " + median); //********************************** for testing ************************************
            }else if (type.equalsIgnoreCase("commissary")) { //6a
                // Query string
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst6a_CommissonaryWise(month_date,zone_code);

                //Result Set
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra = RelevantAspect.Gst6A_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col9 = rsGst14aa.getInt("col9");
                    int col3 = rsGst14aa.getInt("col3");
                    total = rsGst14aa.getDouble("total_score");
                    median = rsGst14aa.getDouble("median_numerator_6a");
                    Double numerator_6a = rsGst14aa.getDouble("numerator_6a");
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks6a(totalScore);
                    int insentavization = score.marks6a(totalScore);

                    if (numerator_6a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "GST6A";
                    double sub_parameter_weighted_average = insentavization * 0.25;
                    // rank = score.marks6a(total);
                    rank = rsGst14aa.getInt("z_rank");
                    String absval = String.valueOf(col9) + "/" + String.valueOf(col3);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }System.out.println("gst6a commmi median :- " + median);
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst6a_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra = RelevantAspect.Gst6A_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col9 = rsGst14aa.getInt("col9");
                    int col3 = rsGst14aa.getInt("col3");
                    total = rsGst14aa.getDouble("total_score");
                    median = rsGst14aa.getDouble("median_numerator_6a");
                    Double numerator_6a = rsGst14aa.getDouble("numerator_6a");
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks6a(totalScore);
                    int insentavization = score.marks6a(totalScore);

                    if (numerator_6a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "GST6A";
                    double sub_parameter_weighted_average = insentavization * 0.25;
                    // rank = score.marks6a(total);
                    rank = rsGst14aa.getInt("z_rank");
                    String absval = String.valueOf(col9) + "/" + String.valueOf(col3);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }System.out.println("gst6a all commi wise median :- " + median);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GST4A::getTotal_score).reversed()).collect(Collectors.toList());
    }

    /*
     * Date: May 04, 2024
     * created:
     * updated: Nishant, may 18, 2024
     * Purpose: This methods have core function in Return Filing .
     */
    @ResponseBody
    @RequestMapping(value = "/gst6b")
    //  http://localhost:8080/cbicApi/cbic/gst6b?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst6b?month_date=2023-04-01&zone_code=70&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/gst6b?month_date=2023-04-01&type=all_commissary
    public Object getGst6BC(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        //Connection done
        Connection con= JDBCConnection.getTNConnection();
        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string
//                String queryGst14aa = "SELECT zc.ZONE_NAME,cc.ZONE_CODE, sum(14c.COMM_MORE_YEAR_AMT+14c.JC_MORE_YEAR_AMT+14c.AC_MORE_YEAR_AMT+14c.SUP_MORE_YEAR_AMT) AS col18, " +
//                        "sum(14c.COMM_CLOSING_NO+14c.JC_CLOSING_NO+14c.AC_CLOSING_NO+14c.SUP_CLOSING_NO) AS col13 " +
//                        "FROM mis_gst_commcode AS cc " +
//                        "RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
//                        "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
//                        "WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE;";

                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst6b_ZoneWise(month_date);
                PreparedStatement psGst14aa = con.prepareStatement(queryGst14aa);
                ResultSet rsGst14aa = psGst14aa.executeQuery();
                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.Gst6B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = "ALL";
                    int col18 = rsGst14aa.getInt("col18");
                    int col13 = rsGst14aa.getInt("col13");
                    int Zonal_rank = 0;
                    String gst = "GST6B";
                    int insentavization = 0;


                    total = rsGst14aa.getDouble("total_score");

//                    if ((col13) != 0) {
//                        total = (((double) (col18)) / (col13));
//                    }
                    //rank = score.marks6b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    String absval = String.valueOf(col18) + "/" + String.valueOf(col13);
                    int way_to_grade = score.marks6b(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.25;

                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                // Query string
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst6b_CommissonaryWise(month_date,zone_code);


                //Prepared Statement
                PreparedStatement psGst14aa = con.prepareStatement(queryGst14aa);

                //Result Set
                ResultSet rsGst14aa = psGst14aa.executeQuery();

                while (rsGst14aa.next()) {
                    String commname = rsGst14aa.getString("COMM_NAME");
                    String ra = RelevantAspect.Gst6B_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");

                    int col18 = rsGst14aa.getInt("col18");
                    int col13 = rsGst14aa.getInt("col13");
                    int Zonal_rank = 0;
                    String gst = "GST6B";
                    //int way_to_grade = 0;
                    int insentavization = 0;
                    //  int sub_parameter_weighted_average = 0;

                    if ((col13) != 0) {
                        total = (((double) (col18) * 100) / (col13));
                    }
                    rank = score.marks6b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks6b(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.25;
                    String absval = String.valueOf(col18) + "/" + String.valueOf(col13);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) { //6b
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst6b_AllCommissonaryWise(month_date);



                //Prepared Statement
                PreparedStatement psGst14aa = con.prepareStatement(queryGst14aa);

                //Result Set
                ResultSet rsGst14aa = psGst14aa.executeQuery();

                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.Gst6B_RA;
                    String commname = rsGst14aa.getString("COMM_NAME");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");

                    int col18 = rsGst14aa.getInt("col18");
                    int col13 = rsGst14aa.getInt("col13");
                    int Zonal_rank = 0;
                    String gst = "GST6B";
                    // int way_to_grade = 0;
                    int insentavization = 0;
                    // int sub_parameter_weighted_average = 0;

                    if ((col13) != 0) {
                        total = (((double) (col18) * 100) / (col13));
                    }
                    rank = score.marks6b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks6b(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.25;
                    String absval = String.valueOf(col18) + "/" + String.valueOf(col13);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }
    /*
     * Date: May 04, 2024
     * created:
     * updated: Nishant, may 18, 2024
     * Purpose: This methods have core function in Return Filing .
     */
    @ResponseBody
    @RequestMapping(value = "/gst6c")
    //  http://localhost:8080/cbicApi/cbic/gst6c?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst6c?month_date=2024-04-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst6c?month_date=2024-04-01&type=all_commissary
    public Object getGst6C(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        double median = 0.00;
        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string
//                String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE,sum(14c.COMM_DISPOSAL_NO+14c.JC_DISPOSAL_NO+14c.AC_DISPOSAL_NO+14c.SUP_DISPOSAL_NO) AS col9 " +
//
//                        " FROM mis_gst_commcode AS cc " +
//                        "RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
//                        "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
//                        "WHERE  14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE;";
//
//                String prev_month_new = DateCalculate.getPreviousMonth(month_date);
//
//                String queryGst3aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE, sum(14c.COMM_CLOSING_NO+14c.JC_CLOSING_NO+14c.AC_CLOSING_NO+14c.SUP_CLOSING_NO) AS col3 FROM mis_gst_commcode AS cc " +
//                        "RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
//                        "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
//                        "WHERE 14c.MM_YYYY = '" + prev_month_new + "' GROUP BY cc.ZONE_CODE;";
                String queryGst14aa =  new GstSubParameterWiseQuery().QueryFor_gst6c_ZoneWise(month_date);
                //Result Set
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.Gst6A_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname ="ALL";
                    //int col9 = rsGst14aa.getInt("col9");
                    //int col3 = rsGst14aa.getInt("col3");
                    total = rsGst14aa.getDouble("score_of_parameter6c");
                    median = rsGst14aa.getDouble("median_numerator_6c");
                    Double numerator_6c = rsGst14aa.getDouble("numerator_6c");
                    String absval = rsGst14aa.getString("absval");

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks6c(totalScore);
                    int insentavization = score.marks6c(totalScore);

                    if (numerator_6c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "GST6C";
                    double sub_parameter_weighted_average = insentavization * 0.25;
                    // rank = score.marks6a(total);
                    rank = 0;
                    //String absval = String.valueOf(col9) + "/" + String.valueOf(col3);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println("GST6C zone wise median :" + median);
            }else if (type.equalsIgnoreCase("commissary")) { // gst 6c
                // Query string
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst6c_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String commname = rsGst14aa.getString("COMM_NAME");
                    String ra = RelevantAspect.Gst6C_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col9 = rsGst14aa.getInt("col9");
                    int col3 = rsGst14aa.getInt("col3");
                    total = rsGst14aa.getDouble("total_score");
                    median = rsGst14aa.getDouble("median_6c");
                    Double numerator_6c = rsGst14aa.getDouble("col9");
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks6c(totalScore);
                    int insentavization = score.marks6c(totalScore);

                    if (numerator_6c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "GST6C";
                    double sub_parameter_weighted_average = insentavization * 0.25;
                    // rank = score.marks6a(total);
                    rank = 0;
                    String absval = String.valueOf(col9) + "/" + String.valueOf(col3);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }System.out.println("gst6c commmi(2no url) wise median " + median);
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst6c_AllCommissonaryWise(month_date);
                //Result Set
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String commname = rsGst14aa.getString("COMM_NAME");
                    String ra = RelevantAspect.Gst6C_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col9 = rsGst14aa.getInt("col9");
                    int col3 = rsGst14aa.getInt("col3");
                    total = rsGst14aa.getDouble("total_score");
                    median = rsGst14aa.getDouble("median_6c");
                    Double numerator_6c = rsGst14aa.getDouble("col9");
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks6c(totalScore);
                    int insentavization = score.marks6c(totalScore);

                    if (numerator_6c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "GST6C";
                    double sub_parameter_weighted_average = insentavization * 0.25;
                    // rank = score.marks6a(total);
                    rank = 0;
                    String absval = String.valueOf(col9) + "/" + String.valueOf(col3);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println("gst6c all commmi wise median " + median);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList.stream()
                .sorted(Comparator.comparing(GST4A::getTotal_score).reversed()).collect(Collectors.toList());
    }
    /*
     * Date: May 04, 2024
     * created:
     * updated:Nishant, may 18, 2024
     * Purpose: This methods have core function in Return Filing .
     */
    @ResponseBody
    @RequestMapping(value = "/gst6d")
    //  http://localhost:8080/cbicApi/cbic/gst6d?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst6d?month_date=2023-04-01&zone_code=70&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/gst6d?month_date=2023-04-01&type=all_commissary
    public Object getGst6D(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        Connection con= JDBCConnection.getTNConnection();
        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst6d_ZoneWise(month_date);
                PreparedStatement psGst14aa = con.prepareStatement(queryGst14aa);
                ResultSet rsGst14aa = psGst14aa.executeQuery();
                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.Gst6D_RA;
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname="ALL";
                    int col18 = rsGst14aa.getInt("col18");
                    int col13 = rsGst14aa.getInt("col13");
                    int Zonal_rank = 0;
                    String gst = "GST6D";
                    int insentavization = 0;

                    if ((col13) != 0) {
                        total = (((double) (col18) * 100) / (col13));
                    }

                    //}
                    //rank = score.marks6d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    String absval = String.valueOf(col18) + "/" + String.valueOf(col13);
                    int way_to_grade = score.marks6d(totalScore);
                    double sub_parameter_weighted_average = way_to_grade * 0.25;
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            } else if (type.equalsIgnoreCase("commissary")) { // gst 6d
                // Query string
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst6d_CommissonaryWise(month_date,zone_code);
                PreparedStatement psGst14aa=con.prepareStatement(queryGst14aa);
                ResultSet rsGst14aa= psGst14aa.executeQuery();
                while(rsGst14aa.next()) {
                    String ra=RelevantAspect.Gst6D_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col18=rsGst14aa.getInt("col18");
                    int col13=rsGst14aa.getInt("col13");
                    int Zonal_rank = 0;
                    String gst = "GST6D";
                    int insentavization = 0;

                    if((col13)!=0) {
                        total= (((double) (col18) * 100) / (col13));
                    }

                    //rank=score.marks6d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks6d(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.25;

                    String absval=String.valueOf(col18)+"/"+String.valueOf(col13);
                    gsta=new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst6d_AllCommissonaryWise(month_date);

                //Prepared Statement
                PreparedStatement psGst14aa=con.prepareStatement(queryGst14aa);
                //Result Set
                ResultSet rsGst14aa= psGst14aa.executeQuery();
                while(rsGst14aa.next()) {
                    String ra=RelevantAspect.Gst6D_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col18=rsGst14aa.getInt("col18");
                    int col13=rsGst14aa.getInt("col13");
                    int Zonal_rank = 0;
                    String gst = "GST6D";
                    int insentavization = 0;

                    if((col13)!=0) {
                        total= (((double) (col18) * 100) / (col13));
                    }

                    rank=score.marks6d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks6d(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.25;

                    String absval=String.valueOf(col18)+"/"+String.valueOf(col13);
                    gsta=new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }
    /*
     * Date: May 04, 2024
     * created:
     * updated: RKS & Nishant may 24, 2024
     * Purpose: This methods have core function in Return Filing .
     */
    @ResponseBody
    @RequestMapping(value = "/gst7")
    //  http://localhost:8080/cbicApi/cbic/gst7?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst7?month_date=2023-04-01&zone_code=70&type=commissary
    //  http://localhost:8080/cbicApi/cbic/gst7?month_date=2023-04-01&type=all_commissary
    public Object getGst7(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;

        try {
            if(type.equalsIgnoreCase("zone")) {
                String quaryGst7 = new GstSubParameterWiseQuery().QueryFor_gst7_ZoneWise(month_date);
                ResultSet rsGst7 = GetExecutionSQL.getResult(quaryGst7);
                while (rsGst7.next()) {
                    String ra = RelevantAspect.Gst7_RA;
                    String zoneCode = rsGst7.getString("ZONE_CODE");
                    String commname="ALL";
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
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
                    //rank = score.marks7(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks7(totalScore);
                    int sub_parameter_weighted_average = way_to_grade;
                    gsta = new GST4A(rsGst7.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) { // gst 7
                String quaryGst7 =  new GstSubParameterWiseQuery().QueryFor_gst7_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(quaryGst7);
                while(rsGst14aa.next()) {
                    String ra=RelevantAspect.Gst7_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col22=rsGst14aa.getInt("col22");
                    int col16=rsGst14aa.getInt("col16");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval=String.valueOf(col22)+"/"+String.valueOf(col16);
                    if (col16 != 0){
                        total =(((double) col22 * 100 )/col16);
                    }
                    else{
                        total=0;
                    }
                    //rank=score.marks7(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks7(totalScore);
                    int sub_parameter_weighted_average = way_to_grade;
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);

                }
            }else if (type.equalsIgnoreCase("all_commissary")) { // gst 7
                String quaryGst7 = new GstSubParameterWiseQuery().QueryFor_gst7_AllCommissonaryWise(month_date);


                ResultSet rsGst14aa =GetExecutionSQL.getResult(quaryGst7);

                while(rsGst14aa.next()) {
                    String ra=RelevantAspect.Gst7_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col22=rsGst14aa.getInt("col22");
                    int col16=rsGst14aa.getInt("col16");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    String absval=String.valueOf(col22)+"/"+String.valueOf(col16);

                    if (col16 != 0){
                        total =(((double) col22 * 100 )/col16);
                    }
                    else{
                        total=0;
                    }
                    //rank=score.marks7(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks7(totalScore);
                    int sub_parameter_weighted_average = way_to_grade;
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    /*
     * Date: May 04, 2024
     * created:
     * updated: Nishant, may 23, 2024
     * Purpose: This methods have core function in Return Filing .
     */
    @ResponseBody
    @RequestMapping(value = "/gst8a")  // only zone wise working
    //  http://localhost:8080/cbicApi/cbic/gst8a?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst8a?month_date=2023-04-01&zone_code=70&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/gst8a?month_date=2023-04-01&type=all_commissary
    public Object getGst8AC(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList=new ArrayList<>();
        GST4A gsta=null;
        int rank = 0;
        double total = 0.00;
        double median = 0.00;
        try {
            if(type.equalsIgnoreCase("zone")) {
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);
                // Query string
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst8a_ZoneWise(month_date);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra=RelevantAspect.GST8A_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col15=rsGst14aa.getInt("col15");
                    int col3=rsGst14aa.getInt("col3");
                    Double t_score = rsGst14aa.getDouble("total_score8A");
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int Zonal_rank = 0;
                    String gst = "no";
                    median = rsGst14aa.getDouble("median_col15");
                    Double numerator_3b = rsGst14aa.getDouble("col15");
                    int way_to_grade = score.marks8a(totalScore);
                    int insentavization = score.marks8a(totalScore);
                    if (numerator_3b > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // int insentavization = 0;
                    // int sub_parameter_weighted_average = 0;
                    double sub_parameter_weighted_average_bfore = insentavization * 0.6;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    String absval = String.valueOf(col15) + "/" + String.valueOf(col3);
//                    if ((col3) != 0){
//                        total =(((double) (col13) * 100)/(col3));
//                    }
//                    else{
//                        total=0;
//                    }

                    //  rank=score.marks8a(total);


                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                    //allGstaList.add(gsta);
                }
                System.out.println("gst8a zone wise median :" + median);

            }else if (type.equalsIgnoreCase("commissary")) {
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);
                // Query string
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst8a_CommissonaryWise(month_date,zone_code);
                //Result Set
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.GST8A_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col15=rsGst14aa.getInt("col15");
                    int col3=rsGst14aa.getInt("col3");
                    median = rsGst14aa.getDouble("median_col15");
                    Double t_score = rsGst14aa.getDouble("total_score8A");
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int Zonal_rank = 0;
                    String gst = "no";
                    // int sub_parameter_weighted_average = 0;
                    Double numerator_3b = rsGst14aa.getDouble("col15");
                    int way_to_grade = score.marks8a(totalScore);
                    int insentavization = score.marks8a(totalScore);
                    if (numerator_3b > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // int insentavization = 0;
                    // int sub_parameter_weighted_average = 0;
                    double sub_parameter_weighted_average_bfore = insentavization * 0.6;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    String absval = String.valueOf(col15) + "/" + String.valueOf(col3);
//                    if ((col3) != 0){
//                        total =(((double) (col13) * 100)/(col3));
//                    }
//                    else{
//                        total=0;
//                    }
                    //rank=score.marks8a(total);



                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println("gst8a cmsnry wise median :" + median);

            }else if (type.equalsIgnoreCase("all_commissary")) {
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);
                // Query string
                String queryGst14aa=  new GstSubParameterWiseQuery().QueryFor_gst8a_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.GST8A_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col15=rsGst14aa.getInt("col15");
                    int col3=rsGst14aa.getInt("col3");
                    median = rsGst14aa.getDouble("median_col15");
                    Double numerator_3b = rsGst14aa.getDouble("col15");
                    Double t_score = rsGst14aa.getDouble("total_score8A");
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int Zonal_rank = 0;
                    String gst = "no";


                    String absval = String.valueOf(col15) + "/" + String.valueOf(col3);

                    int way_to_grade = score.marks8a(totalScore);
                    int insentavization = score.marks8a(totalScore);
                    if (numerator_3b > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    double sub_parameter_weighted_average_bfore = insentavization * 0.6;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_bfore);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
//                    if ((col3) != 0){
//                        total =(((double) (col13) * 100)/(col3));
//                    }
//                    else{
//                        total=0;
//                    }
                    // rank=score.marks8a(total);

                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println("gst8a all_cmsnry_median :" + median);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    /*
     * Date: May 04, 2024
     * created:
     * updated: Nishant, may 23, 2024
     * Purpose: This methods have core function in Return Filing .
     */
    @ResponseBody
    @RequestMapping(value = "/gst8b")
    //  http://localhost:8080/cbicApi/cbic/gst8b?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst8b?month_date=2023-04-01&zone_code=70&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/gst8b?month_date=2023-04-01&type=all_commissary
    public Object getGst8B(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code, Object sub_parameter_weighted_average_bfore) {

        List<GST4A> allGstaList=new ArrayList<>();
        GST4A gsta=null;
        int rank = 0;
        double total = 0.00;
        try {
            if(type.equalsIgnoreCase("zone")) {
                // Query string
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst8b_ZoneWise(month_date);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String ra=RelevantAspect.GST8B_RA;
                    int col20=rsGst14aa.getInt("col20");
                    int col22=rsGst14aa.getInt("col22");
                    Double t_score = rsGst14aa.getDouble("total_score");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    // int sub_parameter_weighted_average = 0;
                    rank=score.marks8b(total);
                    String absval = (col20-col22) + "/" +(col20);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    //int way_to_grade = score.marks8b(totalScore);
                    int way_to_grade = score.marks8b(totalScore);
                    Double sub_parameter_weighted_average_before = way_to_grade * 0.4;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_before);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    //double sub_parameter_weighted_average = 0.00;
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                // Query string
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst8b_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    Double t_score = rsGst14aa.getDouble("total_score");
                    String ra=RelevantAspect.GST8B_RA;
                    int col20=rsGst14aa.getInt("col20");
                    int col22=rsGst14aa.getInt("col22");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    //int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col20-col22) + "/" + String.valueOf(col20);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks8b(totalScore);
                    Double sub_parameter_weighted_average_before = way_to_grade * 0.4;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_before);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                // Query string
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst8b_AllCommissonaryWise(month_date);

                //Result Set
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                //ResultSet rsGst3aa=GetExecutionSQL.getResult(queryGst3aa);
                while(rsGst14aa.next()) {
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    Double t_score = rsGst14aa.getDouble("total_score");
                    String ra=RelevantAspect.GST8B_RA;
                    int col20=rsGst14aa.getInt("col20");
                    int col22=rsGst14aa.getInt("col22");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    // int sub_parameter_weighted_average = 0;
//                    total = col20 != 0 ? (((double) (col20 - col22) * 100) / col20) : 0.0;
//
//                    total=((double) (col20-col22)/ col20);
//                    //}
                    rank=score.marks8b(total);
                    String absval = String.valueOf(col20-col22) + "/" + String.valueOf(col20);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks8b(totalScore);
                    Double sub_parameter_weighted_average_before = way_to_grade * 0.4;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_before);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);

                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }


    /*
     * Date: May 17, 2024
     * created : RKS
     * queries : Nishant
     * updated: RKS, may 28, 2024
     * Purpose: This methods have core function in Return Filing .
     */

    @ResponseBody
    @RequestMapping(value = "/gst9a")
    //  http://localhost:8080/cbicApi/cbic/gst9a?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst9a?month_date=2023-04-01&zone_code=70&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/gst9a?month_date=2023-04-01&type=all_commissary
    public Object getGst9a(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        String prev_month_new =DateCalculate.getPreviousMonth(month_date);

        try {
            // Query string
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa =new GstSubParameterWiseQuery().QueryFor_gst9a_ZoneWise(month_date);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.GST9A_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname= "ALL";
                    int col8=rsGst14aa.getInt("col8");
                    int col5=rsGst14aa.getInt("col5");
                    total = rsGst14aa.getDouble("total_score9A") ;
                    int Zonal_rank = 0;
                    String gst = "no";

                    int insentavization = 0;

                    String absval=String.valueOf(col8)+"/"+String.valueOf(col5);

                    rank=score.marks9a(total);

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks9a(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.5;
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }

            } else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst9a_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.GST9A_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col8=rsGst14aa.getInt("col8");
                    int col5=rsGst14aa.getInt("col5");

                    total = rsGst14aa.getDouble("total_score9A") ;
                    int Zonal_rank = 0;
                    String gst = "no";

                    int insentavization = 0;

                    String absval=String.valueOf(col8)+"/"+String.valueOf(col5 );

                    rank=score.marks9a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks9a(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.5;
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst9a_AllCommissonaryWise(month_date);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.GST9A_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col8=rsGst14aa.getInt("col8");
                    int col5=rsGst14aa.getInt("col5");

                    total = rsGst14aa.getDouble("total_score9A") ;
                    int Zonal_rank = 0;
                    String gst = "no";
                    //int way_to_grade = 0;
                    int insentavization = 0;
                    // int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col8)+"/"+String.valueOf(col5 );

                    rank=score.marks9a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks9a(totalScore);
                    Double sub_parameter_weighted_average = way_to_grade * 0.5;
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    /*
     * Date: May 04, 2024
     * created: RKS
     * updated: Nishant & RKS, may 27, 2024
     * Purpose: This methods have core function in Return Filing .
     */
    @ResponseBody
    @RequestMapping(value = "/gst9b")
    //  http://localhost:8080/cbicApi/cbic/gst9b?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst9b?month_date=2024-04-01&zone_code=51&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/gst9b?month_date=2024-04-01&type=all_commissary
    public Object getGst9b(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst9b_ZoneWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.GST9B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zone_name = rsGst14aa.getString("ZONE_NAME");
                    String commname= "ALL";
                    int col4=rsGst14aa.getInt("col4_4");
                    int col4_1=rsGst14aa.getInt("col1_4");
                    Double t_score = rsGst14aa.getDouble("score_of_subparameter9b");
                    // String commname= "ALL";
                    int Zonal_rank = 0;
                    // String gst = "no";
                    //int insentavization = 0;
                    // total = rsGst14aa.getDouble("score_of_parameter6c");

                    double median = rsGst14aa.getDouble("median9_b");
                    // total = rsGst14aa.getDouble("total_score");
                    Double numerator_6a = rsGst14aa.getDouble("col4_4");
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks9b(totalScore);
                    int insentavization = score.marks9b(totalScore);

                    if (numerator_6a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // int Zonal_rank = 0;
                    String gst = "no";
                    double sub_parameter_weighted_average = insentavization * 0.5;
                    // rank = score.marks6a(total);
                    // rank = rsGst14aa.getInt("z_rank");
                    String absval = String.valueOf(col4) + "/" + String.valueOf(col4_1);
                    gsta = new GST4A(zone_name, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            } else if (type.equalsIgnoreCase("commissary")) { // gst 9b
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);

                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst9b_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.GST9B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String zone_name = rsGst14aa.getString("ZONE_NAME");

                    Double t_score = rsGst14aa.getDouble("score_of_subparameter9b");
                    int col4_4=rsGst14aa.getInt("col4_4");
                    int col1_4=rsGst14aa.getInt("col1_4");
                    // String commname= "ALL";
                    int Zonal_rank = 0;
                    // String gst = "no";
                    //int insentavization = 0;
                    // total = rsGst14aa.getDouble("score_of_parameter6c");

                    double median = rsGst14aa.getDouble("median_9b");
                    // total = rsGst14aa.getDouble("total_score");
                    Double numerator_6a = rsGst14aa.getDouble("col4_4");
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks9b(totalScore);
                    int insentavization = score.marks9b(totalScore);

                    if (numerator_6a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // int Zonal_rank = 0;
                    String gst = "no";
                    double sub_parameter_weighted_average = insentavization * 0.5;
                    // rank = score.marks6a(total);
                    // rank = rsGst14aa.getInt("z_rank");
                    String absval = String.valueOf(col4_4) + "/" + String.valueOf(col1_4);
                    gsta = new GST4A(zone_name, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) { // gst 9b
                String queryGst14aa=new GstSubParameterWiseQuery().QueryFor_gst9b_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.GST9B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zone_name = rsGst14aa.getString("ZONE_NAME");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col4_4=rsGst14aa.getInt("col4_4");
                    int col1_4=rsGst14aa.getInt("col1_4");
                    Double t_score = rsGst14aa.getDouble("score_of_subparameter9b");
                    // String commname= "ALL";
                    int Zonal_rank = 0;
                    // String gst = "no";
                    //int insentavization = 0;
                    // total = rsGst14aa.getDouble("score_of_parameter6c");

                    double median = rsGst14aa.getDouble("median_9b");
                    // total = rsGst14aa.getDouble("total_score");
                    Double numerator_6a = rsGst14aa.getDouble("col4_4");
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks9b(totalScore);
                    int insentavization = score.marks9b(totalScore);

                    if (numerator_6a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    // int Zonal_rank = 0;
                    String gst = "no";
                    double sub_parameter_weighted_average = insentavization * 0.5;
                    // rank = score.marks6a(total);
                    // rank = rsGst14aa.getInt("z_rank");
                    String absval = String.valueOf(col4_4) + "/" + String.valueOf(col1_4);
                    gsta = new GST4A(zone_name, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }


    /*
     * Date: May 04, 2024
     * created:
     * updated: RKS, may 23, 2024
     * Purpose: This methods have core function in Return Filing .
     */

    @ResponseBody
    @RequestMapping(value = "/gst10a")
    //  http://localhost:8080/cbicApi/cbic/gst10a?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst10a?month_date=2023-04-01&zone_code=70&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/gst10a?month_date=2023-04-01&type=all_commissary
    public Object getGst10A(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta;
        int rank;
        double total;
        double X;

        int N = convertMonthToFinancialMonth(month_date);
        System.out.println(month_date + "-" + N);

        try {
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst10a_ZoneWise(month_date);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.Gst10A_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
//                    double  col3 = rsGst14aa.getInt("col3");
//                    double col2 = rsGst14aa.getInt("col2");
                    double col3 = rsGst14aa.getDouble("col3");
                    double col2 = rsGst14aa.getDouble("col2");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;

//                    if (col2 != 0) {
//                        X = (N * col2) / 12.0;
//                    } else {
//                        X = 0.00;
//                    }
                    // String formatted = String.format("%.2f");
//                   String absval = col3 + "/" + col2;
                    String absval = String.format("%.2f", col3) + "/" + String.format("%.2f", col2);
//                    String absval = String.format("%.2f", col3) + "/" + String.format("%.2f", col2);

//                    if (X != 0) {
//                        total = (((double) col3  * 100)/ X);
//                    } else {
//                        total = 0.00;
//                    }

                    //rank = score.marks10a(total);
                    total=rsGst14aa.getDouble("total_score");
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            } else if (type.equalsIgnoreCase("commissary")) { // gst 10a
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst10a_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.Gst10A_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
//                    double col3 = rsGst14aa.getInt("col3");
//                    double col2 = rsGst14aa.getInt("col2");
                    double col3 = rsGst14aa.getDouble("col3");
                    double col2 = rsGst14aa.getDouble("col2");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;

//                    if (col2 != 0) {
//                        X = (N * col2) / 12.0;
//                    } else {
//                        X = 0.00;
//                    }
//                    String formattedX = String.format("%.2f", X);
                    // String absval = col3 + "/" + formattedX;
//                    String absval = col3 + "/" + col2;
                    String absval = String.format("%.2f", col3) + "/" + String.format("%.2f", col2);
//                    if (X != 0) {
//                        total = (((double) col3 * 100) / X);
//                    } else {
//                        total = 0.00;
//                    }
//
//                    rank = score.marks10a(total);
                    total=rsGst14aa.getDouble("total_score");
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa =  new GstSubParameterWiseQuery().QueryFor_gst10a_AllCommissonaryWise(month_date);

                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.Gst10A_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
//                    double col3 = rsGst14aa.getInt("col3");
//                    double col2 = rsGst14aa.getInt("col2");
                    double col3 = rsGst14aa.getDouble("col3");
                    double col2 = rsGst14aa.getDouble("col2");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;

//                    if (col2 != 0) {
//                        X = (N * col2) / 12.0;
//                    } else {
//                        X = 0.00;
//                    }
//                    String formattedX = String.format("%.2f", X);
                    //String absval = col3 + "/" + formattedX;
//                        int i = 2;
//                    String absval = (col3, i;) + "/" + (col2, i;);
//                    String absval = col3 + "/" + col2;
                    // String absval=String.valueOf(col3)+"/"+String.valueOf(col2);
                    // Format col3 and col2 with two decimal places
                    String formattedCol3 = String.format("%.2f", (double) col3);
                    String formattedCol2 = String.format("%.2f", (double) col2);
//
//                    // Construct absval with formatted values
//                    String absval = formattedCol3 + "/" + formattedCol2;
//                   // String formattedX = String.format("%.2f", absval);
                    String absval = String.format("%.2f", col3) + "/" + String.format("%.2f", col2);

//                    SELECT FORMAT(col3, 2) + '/' + FORMAT(col2, 2) AS absval
//                    FROM your_table_name;
//                     String absval = SELECT FORMAT(col3, 2) + '/' + FORMAT(col2, 2) AS absval
////                    FROM your_table_name;

//                    if (X != 0) {
//                        total = (((double) col3 * 100) / X);
//                    } else {
//                        total = 0.00;
//                    }

                    //rank = score.marks10a(total);


                    total=rsGst14aa.getDouble("total_score");
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    private int convertMonthToFinancialMonth(String month_date) {
        int month = Integer.parseInt(month_date.substring(5, 7)); //month_date.substring(5, 7) extracts the month part from the date string. For example, if month_date is "2023-04-01", month_date.substring(5, 7) would yield "04". Integer.parseInt converts this string representation of the month to an integer. Thus, for "2023-04-01", month would be 4.
        return (month >= 4) ? (month - 3) : (month + 9); //The ternary operator (condition) ? value_if_true : value_if_false is used here to determine the financial month.
    }


    /*
     * Date: May 04, 2024
     * created: RKS
     * updated: Nishant & RKS, may 27, 2024
     * Purpose: This methods have core function in Return Filing .
     */
    @ResponseBody
    @RequestMapping(value = "/gst10b")
    //  http://localhost:8080/cbicApi/cbic/gst10b?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst10b?month_date=2023-04-01&zone_code=70&type=commissary
    public Object getGst10b(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;


        try {
            // Query string
            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gst10b_ZoneWise(month_date);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.Gst10B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname= "ALL";
                    int col36=rsGst14aa.getInt("col36");
                    int col38=rsGst14aa.getInt("col38");
                    int col30=rsGst14aa.getInt("col30");
                    int Zonal_rank = 0;
                    String gst = "no";
                    //int way_to_grade = 0;
                    int insentavization = 0;
                    // int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col36 + col38)+"/"+String.valueOf(col30);
                    if(col30 != 0){
                        total=(((double) (col36 + col38) * 100) / (col30));
                    }else {
                        total = 0.00;
                    }

                    rank=score.marks10b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks10b(totalScore);
                    Double sub_parameter_weighted_average_before = way_to_grade * 0.3;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_before);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }

            } else if (type.equalsIgnoreCase("commissary")) { // Gst 10b
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst10b_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.Gst10B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    int col36=rsGst14aa.getInt("col36");
                    int col38=rsGst14aa.getInt("col38");
                    int col30=rsGst14aa.getInt("col30");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    //int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col36 + col38)+"/"+String.valueOf(col30);
                    if(col30 != 0){
                        total=(((double) (col36 + col38) * 100) / (col30));
                    }else {
                        total = 0.00;
                    }

                    rank=score.marks10b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks10b(totalScore);
                    Double sub_parameter_weighted_average_before = way_to_grade * 0.3;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_before);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) { // Gst 10b
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst10b_AllCommissonaryWise(month_date);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.Gst10B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    int col36=rsGst14aa.getInt("col36");
                    int col38=rsGst14aa.getInt("col38");
                    int col30=rsGst14aa.getInt("col30");
                    int Zonal_rank = 0;
                    String gst = "no";
                    // int way_to_grade = 0;
                    int insentavization = 0;
                    // int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col36 + col38)+"/"+String.valueOf(col30);
                    if(col30 != 0){
                        total=(((double) (col36 + col38) * 100) / (col30));
                    }else {
                        total = 0.00;
                    }

                    rank=score.marks10b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks10b(totalScore);
                    Double sub_parameter_weighted_average_before = way_to_grade * 0.3;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_before);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    /*
     * Date: May 04, 2024
     * created: RKS
     * updated: RKS, may 18, 2024
     * Purpose: This methods have core function in Return Filing .
     */
    @ResponseBody
    @RequestMapping(value = "/gst11a")
    //  http://localhost:8080/cbicApi/cbic/gst11a?month_date=2024-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst11a?month_date=2024-04-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst11a?month_date=2024-04-01&type=all_commissary
    public Object getGst11A(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;

        try {

            if (type.equalsIgnoreCase("zone")) {
                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gs11a_ZoneWise(month_date);

                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);

                while (rsGst14aa.next()) {
                    String ra=RelevantAspect.Gst11A_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    // double t_score = rsGst14aa.getDouble("total_score") * 100;
                    String commname = "ALL";
                    total = rsGst14aa.getDouble("total_score")*100;
                    double median = rsGst14aa.getDouble("median_11a");
                    int col_10=rsGst14aa.getInt("col10");
                    int col_4=rsGst14aa.getInt("col4");
                    Double numerator_11a = rsGst14aa.getDouble("col10");
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks11a(totalScore, numerator_11a);
                    int insentavization = score.marks11a(totalScore, numerator_11a);

                    if (numerator_11a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "no";
                    double sub_parameter_weighted_average = insentavization * 0.25;
                    // rank = score.marks6a(total);
                    //rank = rsGst14aa.getInt("z_rank");
                    String absval = String.valueOf(col_10) + "/" + String.valueOf(col_4);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) { //gst 11a
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String queryGst14aa = new GstSubParameterWiseQuery().QueryFor_gs11a_CommissonaryWise(month_date,zone_code);



                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.Gst11A_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    total = rsGst14aa.getDouble("total_score")*100;
                    double median = rsGst14aa.getDouble("median_11a");
                    int col_10=rsGst14aa.getInt("col10");
                    int col_4=rsGst14aa.getInt("col4");
                    Double numerator_11a = rsGst14aa.getDouble("col10");
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks11a(totalScore, numerator_11a);
                    int insentavization = score.marks11a(totalScore, numerator_11a);

                    if (numerator_11a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "no";
                    double sub_parameter_weighted_average = insentavization * 0.25;
                    // rank = score.marks6a(total);
                    //rank = rsGst14aa.getInt("z_rank");
                    String absval = String.valueOf(col_10) + "/" + String.valueOf(col_4);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);

                }
            }else if (type.equalsIgnoreCase("all_commissary")) { //gst 11a
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String queryGst14aa =  new GstSubParameterWiseQuery().QueryFor_gs11a_AllCommissonaryWise(month_date);


                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.Gst11A_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = rsGst14aa.getString("COMM_NAME");
                    total = rsGst14aa.getDouble("total_score")*100;
                    double median = rsGst14aa.getDouble("median_11a");
                    int col_10=rsGst14aa.getInt("col10");
                    int col_4=rsGst14aa.getInt("col4");
                    Double numerator_11a = rsGst14aa.getDouble("col10");
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks11a(totalScore, numerator_11a);
                    int insentavization = score.marks11a(totalScore, numerator_11a);

                    if (numerator_11a > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "no";
                    double sub_parameter_weighted_average = insentavization * 0.25;
                    // rank = score.marks6a(total);
                    //rank = rsGst14aa.getInt("z_rank");
                    String absval = String.valueOf(col_10) + "/" + String.valueOf(col_4);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    /*
     * Date: May 23, 2024
     * @Kinshuk_Maity
     * Purpose: This methods have core function in Registration.
     */

    @ResponseBody
    @RequestMapping(value = "/gst11b")
    //  http://localhost:8080/cbicApi/cbic/gst11b?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst11b?month_date=2023-04-01&zone_code=70&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/gst11b?month_date=2023-04-01&type=all_commissary
    public Object getGst11B(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                // Query string
                String queryGst49 = new GstSubParameterWiseQuery().QueryFor_gst11b_ZoneWise(month_date);
                ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst49);
                while(rsGst46b.next()) {
                    String ra= RelevantAspect.Gst11B_RA;
                    String zoneCode = rsGst46b.getString("ZONE_CODE");
                    String commname="ALL";
                    int col10A = rsGst46b.getInt("col10A");
                    int col12A = rsGst46b.getInt("col12A");
                    int col10B = rsGst46b.getInt("col10B");
                    int col12B = rsGst46b.getInt("col12B");
                    int Zonal_rank = 0;
                    String gst = "no";
                    //int way_to_grade = 0;
                    int insentavization = 0;
                    //int sub_parameter_weighted_average = 0;
                    int numerator = rsGst46b.getInt("numerator");
                    int denominator = rsGst46b.getInt("denominator");


                    if (denominator != 0){
                        total = rsGst46b.getDouble("total_score") * 100;
                    }else{
                        total = 0.00;
                    }

                    String absval = String.valueOf(numerator) + "/" + String.valueOf(denominator);


                    //total = (col4 != 0) ? ((col10 / col4) * 100) : 0;

                    rank = score.marks11b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks11b(totalScore);
                    Double sub_parameter_weighted_average_before = way_to_grade * 0.25;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_before);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);

                    gsta=new GST4A(rsGst46b.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) { //gst 11b
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                String queryGst49 = new GstSubParameterWiseQuery().QueryFor_gst11b_CommissonaryWise(month_date,zone_code);
                ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst49);

                while(rsGst46b.next()) {
                    String ra= RelevantAspect.Gst11B_RA;
                    String zoneCode = rsGst46b.getString("ZONE_CODE");
                    String commname=rsGst46b.getString("COMM_NAME");
                    int col10A = rsGst46b.getInt("col10A");
                    int col12A = rsGst46b.getInt("col12A");
                    int col10B = rsGst46b.getInt("col10B");
                    int col12B = rsGst46b.getInt("col12B");
                    int Zonal_rank = 0;
                    String gst = "no";
                    // int way_to_grade = 0;
                    int insentavization = 0;
                    //int sub_parameter_weighted_average = 0;
                    int numerator = rsGst46b.getInt("numerator");
                    int denominator = rsGst46b.getInt("denominator");


                    if (denominator != 0){
                        total = rsGst46b.getDouble("total_score") * 100;
                    }else{
                        total = 0.00;
                    }

                    String absval = String.valueOf(numerator) + "/" + String.valueOf(denominator);


                    //total = (col4 != 0) ? ((col10 / col4) * 100) : 0;

                    rank = score.marks11b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks11b(totalScore);
                    Double sub_parameter_weighted_average_before = way_to_grade * 0.25;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_before);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    gsta=new GST4A(rsGst46b.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) { //gst 11b
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String queryGst49 = new GstSubParameterWiseQuery().QueryFor_gst11b_AllCommissonaryWise(month_date);
                ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst49);

                while(rsGst46b.next()) {
                    String ra= RelevantAspect.Gst11B_RA;
                    String zoneCode = rsGst46b.getString("ZONE_CODE");
                    String commname=rsGst46b.getString("COMM_NAME");
                    int col10A = rsGst46b.getInt("col10A");
                    int col12A = rsGst46b.getInt("col12A");
                    int col10B = rsGst46b.getInt("col10B");
                    int col12B = rsGst46b.getInt("col12B");
                    int Zonal_rank = 0;
                    String gst = "no";
                    //int way_to_grade = 0;
                    int insentavization = 0;
                    //int sub_parameter_weighted_average = 0;
                    int numerator = rsGst46b.getInt("numerator");
                    int denominator = rsGst46b.getInt("denominator");


                    if (denominator != 0){
                        total = rsGst46b.getDouble("total_score") * 100;
                    }else{
                        total = 0.00;
                    }

                    String absval = String.valueOf(numerator) + "/" + String.valueOf(denominator);


                    //total = (col4 != 0) ? ((col10 / col4) * 100) : 0;

                    rank = score.marks11b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks11b(totalScore);
                    Double sub_parameter_weighted_average_before = way_to_grade * 0.25;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_before);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    gsta=new GST4A(rsGst46b.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }
    /*
     * Date: May 04, 2024
     * created:
     * updated: RKS, may 24, 2024
     * Purpose: This methods have core function in Return Filing .
     */
    @ResponseBody
    @RequestMapping(value = "/gst11c")
    //  http://localhost:8080/cbicApi/cbic/gst11c?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst11c?month_date=2023-04-01&zone_code=70&type=commissary
    // http://localhost:8080/cbicApi/cbic/gst11c?month_date=2024-04-01&type=all_commissary
    public Object getGst11C(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst11c_ZoneWise(month_date);
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.Gst11C_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String commname="ALL";
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col10 = rsGst14aa.getInt("col10");
                    int col4 = rsGst14aa.getInt("col4");
                    // double t_score = rsGst14aa.getDouble("total_score") * 100;
                    total = rsGst14aa.getDouble("total_score")*100;
                    double median = rsGst14aa.getDouble("median_11c");
                    int col_10=rsGst14aa.getInt("col10");
                    int col_4=rsGst14aa.getInt("col4");
                    Double numerator_11c = rsGst14aa.getDouble("col10");
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks11c(totalScore, numerator_11c);
                    int insentavization = score.marks11c(totalScore, numerator_11c);

                    if (numerator_11c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "no";
                    double sub_parameter_weighted_average = insentavization * 0.25;
                    // rank = score.marks6a(total);
                    //rank = rsGst14aa.getInt("z_rank");
                    String absval = String.valueOf(col_10) + "/" + String.valueOf(col_4);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) { // gst 11c
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                // Query string
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst11c_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.Gst11C_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col10 = rsGst14aa.getInt("col10");
                    int col4 = rsGst14aa.getInt("col4");
                    // double t_score = rsGst14aa.getDouble("total_score") * 100;
                    total = rsGst14aa.getDouble("total_score")*100;
                    double median = rsGst14aa.getDouble("median_11c");
                    int col_10=rsGst14aa.getInt("col10");
                    int col_4=rsGst14aa.getInt("col4");
                    Double numerator_11c = rsGst14aa.getDouble("col10");
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks11c(totalScore, numerator_11c);
                    int insentavization = score.marks11c(totalScore, numerator_11c);

                    if (numerator_11c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "no";
                    double sub_parameter_weighted_average = insentavization * 0.25;
                    // rank = score.marks6a(total);
                    //rank = rsGst14aa.getInt("z_rank");
                    String absval = String.valueOf(col_10) + "/" + String.valueOf(col_4);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) { // gst 11c
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                // Query string
                String queryGst14aa= new GstSubParameterWiseQuery().QueryFor_gst11c_AllCommissonaryWise(month_date);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.Gst11C_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col10 = rsGst14aa.getInt("col10");
                    int col4 = rsGst14aa.getInt("col4");
                    // double t_score = rsGst14aa.getDouble("total_score") * 100;
                    total = rsGst14aa.getDouble("total_score")*100;
                    double median = rsGst14aa.getDouble("median_11c");
                    int col_10=rsGst14aa.getInt("col10");
                    int col_4=rsGst14aa.getInt("col4");
                    Double numerator_11c = rsGst14aa.getDouble("col10");
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks11c(totalScore, numerator_11c);
                    int insentavization = score.marks11c(totalScore, numerator_11c);

                    if (numerator_11c > median && way_to_grade < 10) {
                        insentavization += 1;
                    }
                    int Zonal_rank = 0;
                    String gst = "no";
                    double sub_parameter_weighted_average = insentavization * 0.25;
                    // rank = score.marks6a(total);
                    //rank = rsGst14aa.getInt("z_rank");
                    String absval = String.valueOf(col_10) + "/" + String.valueOf(col_4);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGstaList;
    }

    /*
     * Date: May 23, 2024
     * @Kinshuk_Maity
     * Purpose: This methods have core function in Registration.
     */

    @ResponseBody // incomplete code lack of resources
    @RequestMapping(value = "/gst11d")
    //  http://localhost:8080/cbicApi/cbic/gst11d?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst11d?month_date=2023-04-01&zone_code=70&type=commissary
    public Object getGst11D(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                // Query string
                String queryGst49 = new GstSubParameterWiseQuery().QueryFor_gst11d_ZoneWise(month_date);

                ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst49);

                while(rsGst46b.next()) {
                    String ra= RelevantAspect.Gst11D_RA;
                    String zoneCode = rsGst46b.getString("ZONE_CODE");
                    //String commname=rsGst46b.getString("COMM_NAME");
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col10A = rsGst46b.getInt("col10A");
                    int col12A = rsGst46b.getInt("col12A");
                    int col10B = rsGst46b.getInt("col10B");
                    int col12B = rsGst46b.getInt("col12B");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    // int sub_parameter_weighted_average = 0;
                    int numerator = rsGst46b.getInt("numerator");
                    int denominator = rsGst46b.getInt("denominator");


                    if (denominator != 0){
                        total = rsGst46b.getDouble("total_score") * 100;
                    }else{
                        total = 0.00;
                    }

                    String absval = String.valueOf(numerator) + "/" + String.valueOf(denominator);


                    //total = (col4 != 0) ? ((col10 / col4) * 100) : 0;

                    rank = score.marks11d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    // int way_to_grade = score.marks11b(totalScore);
                    int way_to_grade = score.marks11d(totalScore);
                    Double sub_parameter_weighted_average_before = way_to_grade * 0.25;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_before);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    gsta=new GST4A(rsGst46b.getString("ZONE_NAME"),"All",totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String queryGst49 = new GstSubParameterWiseQuery().QueryFor_gst11d_CommissonaryWise(month_date,zone_code);



                ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst49);

                while(rsGst46b.next()) {
                    String ra= RelevantAspect.Gst11D_RA;
                    String zoneCode = rsGst46b.getString("ZONE_CODE");
                    String commname=rsGst46b.getString("COMM_NAME");
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col10A = rsGst46b.getInt("col10A");
                    int col12A = rsGst46b.getInt("col12A");
                    int col10B = rsGst46b.getInt("col10B");
                    int col12B = rsGst46b.getInt("col12B");
                    int Zonal_rank = 0;
                    String gst = "no";
                    //int way_to_grade = 0;
                    int insentavization = 0;
                    //int sub_parameter_weighted_average = 0;
                    int numerator = rsGst46b.getInt("numerator");
                    int denominator = rsGst46b.getInt("denominator");


                    if (denominator != 0){
                        total = rsGst46b.getDouble("total_score") * 100;
                    }else{
                        total = 0.00;
                    }

                    String absval = String.valueOf(numerator) + "/" + String.valueOf(denominator);


                    //total = (col4 != 0) ? ((col10 / col4) * 100) : 0;

                    rank = score.marks11d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks11d(totalScore);
                    Double sub_parameter_weighted_average_before = way_to_grade * 0.25;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_before);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    gsta=new GST4A(rsGst46b.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String queryGst49 = new GstSubParameterWiseQuery().QueryFor_gst11d_AllCommissonaryWise(month_date);
                ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst49);

                while(rsGst46b.next()) {
                    String ra= RelevantAspect.Gst11D_RA;
                    String zoneCode = rsGst46b.getString("ZONE_CODE");
                    String commname=rsGst46b.getString("COMM_NAME");
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col10A = rsGst46b.getInt("col10A");
                    int col12A = rsGst46b.getInt("col12A");
                    int col10B = rsGst46b.getInt("col10B");
                    int col12B = rsGst46b.getInt("col12B");
                    int Zonal_rank = 0;
                    String gst = "no";
                    // int way_to_grade = 0;
                    int insentavization = 0;
                    //int sub_parameter_weighted_average = 0;
                    int numerator = rsGst46b.getInt("numerator");
                    int denominator = rsGst46b.getInt("denominator");


                    if (denominator != 0){
                        total = rsGst46b.getDouble("total_score") * 100;
                    }else{
                        total = 0.00;
                    }

                    String absval = String.valueOf(numerator) + "/" + String.valueOf(denominator);


                    //total = (col4 != 0) ? ((col10 / col4) * 100) : 0;

                    rank = score.marks11d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks11d(totalScore);
                    Double sub_parameter_weighted_average_before = way_to_grade * 0.25;
                    String formattedSubParameterWeightedAverage = String.format("%.2f", sub_parameter_weighted_average_before);
                    double sub_parameter_weighted_average = Double.parseDouble(formattedSubParameterWeightedAverage);
                    gsta=new GST4A(rsGst46b.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
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