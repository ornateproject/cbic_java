package com.ornates.cbic.controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ornates.cbic.dao.Query.CGSTSubParameterWiseQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ornates.cbic.dao.pool.JDBCConnection;
import com.ornates.cbic.dao.result.GetExecutionSQL;
import com.ornates.cbic.model.response.GST4A;
import com.ornates.cbic.service.DateCalculate;
import com.ornates.cbic.service.GradeScore;
import com.ornates.cbic.service.RelevantAspect;
/*
 * @Author: @Kinshuk_Maity
 */
//@CrossOrigin
@RequestMapping("/cbic") //....
@Controller
public class RetriveCbicDetailsController {
    GradeScore score=new GradeScore();
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
                String queryGst14aa = new CGSTSubParameterWiseQuery().QueryFor_gst1a_ZoneWise(month_date);
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
                String queryGst14aa = new CGSTSubParameterWiseQuery().QueryFor_gst1a_CommissonaryWise(month_date,zone_code);

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
                String queryGst14aa = new CGSTSubParameterWiseQuery().QueryFor_gst1b_ZoneWise(month_date);
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
                String queryGst14aa = new CGSTSubParameterWiseQuery().QueryFor_gst1b_CommissonaryWise(month_date,zone_code);
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
                String queryGst14aa = new CGSTSubParameterWiseQuery().QueryFor_gst1c_ZoneWise(month_date);

                //Result Set
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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
//					int col4 = rsGst14aa.getInt("col4");
//					int col5 = rsGst14aa.getInt("col5");
                    String absval=String.valueOf(col10)+"/"+String.valueOf(col2 + col3);

                    if((col2 + col3) != 0){
                        total = ((double) (col10) * 100 / (col2 + col3));
                    }else {
                        total=0.00;
                    }

                    rank=score.marks1c(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa= new CGSTSubParameterWiseQuery().QueryFor_gst1c_CommissonaryWise(month_date,zone_code);

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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
//					int col4 = rsGst14aa.getInt("col4");
//					int col5 = rsGst14aa.getInt("col5");
                    String absval=String.valueOf(col10)+"/"+String.valueOf(col2 + col3);

                    if((col2 + col3) != 0){
                        total = ((double) (col10) * 100 / (col2 + col3));
                    }else {
                        total=0.00;
                    }

                    rank=score.marks1c(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa= new CGSTSubParameterWiseQuery().QueryFor_gst1c_AllCommissonaryWise(month_date);

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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
//					int col4 = rsGst14aa.getInt("col4");
//					int col5 = rsGst14aa.getInt("col5");
                    String absval=String.valueOf(col10)+"/"+String.valueOf(col2 + col3);

                    if((col2 + col3) != 0){
                        total = ((double) (col10) * 100 / (col2 + col3));
                    }else {
                        total=0.00;
                    }

                    rank=score.marks1c(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);

                }

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return allGstaList;
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
                String queryGst14aa = new CGSTSubParameterWiseQuery().QueryFor_gst1d_ZoneWise(month_date);

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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;

                    String absval=String.valueOf(col14)+"/"+String.valueOf((col1 + col2 + col3));


                    if((col1 + col2 + col3) != 0) {
                        total = ((double) col14 * 100 / (col1 + col2 + col3));
                    }else{
                        total = 0.00;
                    }
                    rank = score.marks1d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),"ALL",totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa = new CGSTSubParameterWiseQuery().QueryFor_gst1d_CommissonaryWise(month_date,zone_code);

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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;

                    String absval=String.valueOf(col14)+"/"+String.valueOf((col1 + col2 + col3));


                    if((col1 + col2 + col3) != 0) {
                        total = ((double) col14 * 100 / (col1 + col2 + col3));
                    }else{
                        total = 0.00;
                    }
                    rank = score.marks1d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa = new CGSTSubParameterWiseQuery().QueryFor_gst1d_AllCommissonaryWise(month_date);

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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;

                    String absval=String.valueOf(col14)+"/"+String.valueOf((col1 + col2 + col3));


                    if((col1 + col2 + col3) != 0) {
                        total = ((double) col14 * 100/ (col1 + col2 + col3));
                    }else{
                        total = 0.00;
                    }
                    rank = score.marks1d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
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

                // Query string
                String queryGst14aa =new CGSTSubParameterWiseQuery().QueryFor_gst1e_ZoneWise(month_date);


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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col9)+"/"+String.valueOf(col1 + col2 + col5 + col6);

                    if((col1 + col2 + col5 + col6) != 0) {
                        total = (((double) col9 * 100)/ (col1 + col2 + col5 + col6));
                    }else {
                        total=0.00;
                    }
                    rank = score.marks1e(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                // Query string
                String queryGst14aa =  new CGSTSubParameterWiseQuery().QueryFor_gst1e_CommissonaryWise(month_date,zone_code);

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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col9)+"/"+String.valueOf(col1 + col2 + col5 + col6);

                    if((col1 + col2 + col5 + col6) != 0) {
                        total = (((double) col9 * 100)/ (col1 + col2 + col5 + col6));
                    }else {
                        total=0.00;
                    }
                    rank = score.marks1e(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa = new CGSTSubParameterWiseQuery().QueryFor_gst1e_AllCommissonaryWise(month_date);

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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col9)+"/"+String.valueOf(col1 + col2 + col5 + col6);

                    if((col1 + col2 + col5 + col6) != 0) {
                        total = (((double) col9 * 100)/ (col1 + col2 + col5 + col6));
                    }else {
                        total=0.00;
                    }
                    rank = score.marks1e(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
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

                // Query string
                String queryGst14aa = new CGSTSubParameterWiseQuery().QueryFor_gst1f_ZoneWise(month_date);


                //Result Set
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);

                while (rsGst14aa.next()) {
                    String ra= RelevantAspect.GST1F_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col14 = rsGst14aa.getInt("col14");
                    int col10 = rsGst14aa.getInt("col10");
                    int col11 = rsGst14aa.getInt("col11");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col14)+"/"+String.valueOf(col10 + col11);

                    if ((col10 + col11) != 0) {
                        total = (((double) col14 * 100)/ (col10 + col11));
                    }else{
                        total=0.00;
                    }

                    rank = score.marks1f(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),"ALL",totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {//gst1f-commissary
                String queryGst14aa = new CGSTSubParameterWiseQuery().QueryFor_gst1f_CommissonaryWise(month_date,zone_code);
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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col14) + "/" + String.valueOf(col10 + col11);

                    if ((col10 + col11) != 0) {
                        total = (((double) col14 * 100)/ (col10 + col11));
                    } else {
                        total = 0.00;
                    }

                    rank = score.marks1f(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta = new GST4A(zonename, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa = new CGSTSubParameterWiseQuery().QueryFor_gst1f_AllCommissonaryWise(month_date);
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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col14) + "/" + String.valueOf(col10 + col11);

                    if ((col10 + col11) != 0) {
                        total = (((double) col14 * 100)/ (col10 + col11));
                    } else {
                        total = 0.00;
                    }

                    rank = score.marks1f(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
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
                String queryGst14aa =new CGSTSubParameterWiseQuery().QueryFor_gst2_ZoneWise(month_date);

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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col21)+"/"+String.valueOf(col3);
                    total = rsGst14aa.getDouble("total_score");

                    //total=((double) col21 / col3);
                    //}
                    rank=score.marks2(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }

            } else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa=new CGSTSubParameterWiseQuery().QueryFor_gst2_CommissonaryWise(month_date,zone_code);

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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col21)+"/"+String.valueOf(col3);

                    //total=((double) col21 / col3);
                    total=(((double) col21) * 100 / col3);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    rank=score.marks2(total);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa=new CGSTSubParameterWiseQuery().QueryFor_gst2_AllCommissonaryWise(month_date);

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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col21)+"/"+String.valueOf(col3);

                    total=(((double) col21) *100 / col3);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    rank=score.marks2(total);
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

                String queryGst14aa= new CGSTSubParameterWiseQuery().QueryFor_gst3a_ZoneWise(month_date);


                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String commname = "All";
                    String ra = RelevantAspect.Gst3A_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String absval = rsGst14aa.getString("absval");
                    Double t_score = rsGst14aa.getDouble("total_score");
                    median = rsGst14aa.getDouble("median_numerator_3a");
                    Double numerator_3b = rsGst14aa.getDouble("numerator_3a");

                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks3a(totalScore);
                    int insentavization = score.marks3a(totalScore);

                    if (numerator_3b > median && way_to_grade < 10) {
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
                String queryGst14aa= new CGSTSubParameterWiseQuery().QueryFor_gst3a_CommissonaryWise(month_date,zone_code);

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
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col4 + col9 + col10) + "/" + String.valueOf(col2 + col1);
                    if (col2 + col1 != 0) {
                        total = (((double) (col4 + col9 + col10)) * 100 / (col2 + col1));
                    }
                   //  rank = score.marks3a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks3a(totalScore);
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);
                // Query string
                String queryGst14aa=new CGSTSubParameterWiseQuery().QueryFor_gst3a_AllCommissonaryWise(month_date);
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
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;

                    String absval = String.valueOf(col4 + col9 + col10) + "/" + String.valueOf(col2 + col1);
                    if (col2 + col1 != 0) {
                        total = (((double) (col4 + col9 + col10)) * 100 / (col2 + col1));
                    }
                   // rank = score.marks3a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks3a(totalScore);
                    // System.out.println(totalScore);
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
                String queryGst14aa =new CGSTSubParameterWiseQuery().QueryFor_gst3b_ZoneWise(month_date);


                PreparedStatement psGst14aa = con.prepareStatement(queryGst14aa);
                ResultSet rsGst14aa = psGst14aa.executeQuery();
                while (rsGst14aa.next()) {
                    String commname = "All";
                    String ra = RelevantAspect.Gst3B_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String absval = rsGst14aa.getString("absval");
                    Double t_score = rsGst14aa.getDouble("score_of_parameter");
                    median = rsGst14aa.getDouble("median_numerator_3b");
                    Double numerator_3b = rsGst14aa.getDouble("numerator_3b");

                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks3b(totalScore);
                    int insentavization = score.marks3b(totalScore);
                    System.out.println("insentavization3b :-" + insentavization);

                    if (numerator_3b > median && way_to_grade < 10) {
                        insentavization += 1;
                    }

                    System.out.println("insentavization3b after :-" + insentavization);

                    int Zonal_rank = 0;
                    String gst = "no";

                    double sub_parameter_weighted_average = insentavization * 0.5 ;
                    //double sub_parameter_weighted_average = 0.00 ;

                    gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
                System.out.println("gst3b median zone wise:- " + median); //**************************** for testing ******************************************
            }else if (type.equalsIgnoreCase("commissary")) {
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);

                // Query string
                String queryGst14aa= new CGSTSubParameterWiseQuery().QueryFor_gst3b_CommissonaryWise(month_date,zone_code);


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
                    double total = rsGst14aa.getDouble("score_of_parameter") * 100;
                    int Zonal_rank = 0;
                    String gst = "no";

                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;


                   // rank=score.marks3b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal) ;
                    int way_to_grade = score.marks3b(totalScore);
                    gsta=new GST4A(zoneName, commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);

                // Query string
                String queryGst14aa= new CGSTSubParameterWiseQuery().QueryFor_gst3b_AllCommissonaryWise(month_date);


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
                    double total = rsGst14aa.getDouble("score_of_parameter") * 100;
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;


                    // rank=score.marks3b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks3b(totalScore);
                    gsta=new GST4A(zoneName, commname,totalScore,absval,zoneCode,ra,
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
    @RequestMapping(value = "/gst4a")
    //  http://localhost:8080/cbicApi/cbic/gst4a?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst4a?month_date=2023-04-01&zone_code=70&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/gst4a?month_date=2023-04-01&type=all_commissary
    public Object getGst4A(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code ) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;


        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string

                // Query string
                String queryGst14aa=new CGSTSubParameterWiseQuery().QueryFor_gst4a_ZoneWise(month_date);


                //Result Set
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String commname="ALL";
                    String ra=RelevantAspect.GST4A_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");

                    int col13=rsGst14aa.getInt("col13");
                    int col1=rsGst14aa.getInt("col1");
                    Double t_score = rsGst14aa.getDouble("total_score");
                    int Zonal_rank = 0;
                    String gst = "no";

                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col13)+"/"+String.valueOf(col1);

                    // rank=score.marks4a(total);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks4a(totalScore);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }

            } else if (type.equalsIgnoreCase("commissary")) {
                // Query string
                String queryGst14aa= new CGSTSubParameterWiseQuery().QueryFor_gst4a_CommissonaryWise(month_date,zone_code);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra=RelevantAspect.GST4A_RA;
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    Double t_score = rsGst14aa.getDouble("score_of_parameter4a");
                    int col13=rsGst14aa.getInt("col13");
                    int col1=rsGst14aa.getInt("col1");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col13)+"/"+String.valueOf(col1);

//                    if(col1!=0) {
//                        total=(((double) (col13) * 100) / col1);
//                    }
//                    //}
                    rank=score.marks4a(total);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks4a(totalScore);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa=new CGSTSubParameterWiseQuery().QueryFor_gst4a_AllCommissonaryWise(month_date);

                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra=RelevantAspect.GST4A_RA;
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    Double t_score = rsGst14aa.getDouble("score_of_parameter4a");
                    int col13=rsGst14aa.getInt("col13");
                    int col1=rsGst14aa.getInt("col1");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col13)+"/"+String.valueOf(col1);

//                    if(col1!=0) {
//                        total=(((double) (col13) * 100) / col1);
//                    }
//                    //}
                    //rank=score.marks4a(total);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks4a(totalScore);
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
                String queryGst14aa=new CGSTSubParameterWiseQuery().QueryFor_gst4b_ZoneWise(month_date);
                PreparedStatement psGst14aa=con.prepareStatement(queryGst14aa);

                //Result Set
                ResultSet rsGst14aa= psGst14aa.executeQuery();
                while(rsGst14aa.next()) {
                    String ra=RelevantAspect.Gst4B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col29=rsGst14aa.getInt("col29");
                    int col31=rsGst14aa.getInt("col31");
                    int col25=rsGst14aa.getInt("col25");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    if(col25!=0) {
                        total=(((double) (col29+col31) * 100)/ col25);
                    }
                    //}
                    rank=score.marks4b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    String absval = String.valueOf(col29+col31) + "/" + String.valueOf(col25);
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            } else if (type.equalsIgnoreCase("commissary")) {
                // Query string
                String queryGst14aa=new CGSTSubParameterWiseQuery().QueryFor_gst4b_CommissonaryWise(month_date,zone_code);


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
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col29+col31) + "/" + String.valueOf(col25);
                    // if(col25!=0) {
                    total=(((double) (col29+col31) * 100) / col25);
                    //}
                    //}
                    rank=score.marks4b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);

                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);

                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                // Query string
                String queryGst14aa=new CGSTSubParameterWiseQuery().QueryFor_gst4b_AllCommissonaryWise(month_date);
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
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col29+col31) + "/" + String.valueOf(col25);
                    // if(col25!=0) {
                    total=(((double) (col29+col31) * 100) / col25);
                    //}
                    //}
                    rank=score.marks4b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);

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
    //  http://localhost:8080/cbicApi/cbic/gst4c?month_date=2023-05-01&type=zone                        // updated
    //  http://localhost:8080/cbicApi/cbic/gst4c?month_date=2023-05-01&zone_code=70&type=commissary     //  updated
    //	http://localhost:8080/cbicApi/cbic/gst4c?month_date=2023-05-01&type=all_commissary              //  updated
    public Object getGst4AC(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList=new ArrayList<>();
        GST4A gsta=null;
        int rank = 0;
        double total = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {

                // Query string
                String queryGst3aa= new CGSTSubParameterWiseQuery().QueryFor_gst4c_ZoneWise(month_date);
                //Result Set
                ResultSet rsGst3aa=GetExecutionSQL.getResult(queryGst3aa);
                while( rsGst3aa.next()) {
                    String ra=RelevantAspect.Gst4C_RA;
                    String zoneName = rsGst3aa.getString("ZONE_NAME");
                    String zoneCode = rsGst3aa.getString("ZONE_CODE");
                    Double t_score = rsGst3aa.getDouble("score_of_parameter4c");
                    int col1_7=rsGst3aa.getInt("col1_7");
                    int col1_8=rsGst3aa.getInt("col1_8");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col1_7) + "/" + String.valueOf(col1_8);
//                    if ((col1_8) != 0){
//                        total =(((double) (col1_7) * 100)/(col1_8));
//                    }
//                    else{
//                        total=0;
//                    }

                    rank=score.marks4c(total);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks4c(totalScore);
                    gsta = new GST4A(zoneName, "ALL", totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            } else if (type.equalsIgnoreCase("commissary")) { //gst4c
                //String prev_month_new =DateCalculate.getPreviousMonth(month_date);
                // Query string
                String queryGst14aa= new CGSTSubParameterWiseQuery().QueryFor_gst4c_CommissonaryWise(month_date,zone_code);

                //Result Set
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next() ) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.Gst4C_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    Double t_score = rsGst14aa.getDouble("score_of_subparameter4c");
                    int col1_7=rsGst14aa.getInt("col1_7");
                    int col1_8=rsGst14aa.getInt("col1_8");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col1_7) + "/" + String.valueOf(col1_8);
//                    if ((col1_7) != 0){
//                        total =(((double) (col1_6) * 100)/(col1_7));
//                    }
//                    else{total=0;}

                    //rank=score.marks4c(total);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks4c(totalScore);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                // Query string
                String queryGst14aa=new CGSTSubParameterWiseQuery().QueryFor_gst4c_AllCommissonaryWise(month_date);
                //Result Set
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next() ) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.Gst4C_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    Double t_score = rsGst14aa.getDouble("score_of_subparameter4c");
                    int col1_7=rsGst14aa.getInt("col1_7");
                    int col1_8=rsGst14aa.getInt("col1_8");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col1_7) + "/" + String.valueOf(col1_8);
//                    if ((col1_7) != 0){
//                        total =(((double) (col1_6) * 100)/(col1_7));
//                    }
//                    else{total=0;}

                    //rank=score.marks4c(total);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks4c(totalScore);
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
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);

                // Query string
                String queryGst46a=new CGSTSubParameterWiseQuery().QueryFor_gst4d_ZoneWise(month_date);


                ResultSet rsGst46a =GetExecutionSQL.getResult(queryGst46a);
                while(rsGst46a.next()) {
                    String ra=RelevantAspect.GST4D_RA;
                    String zoneCode = rsGst46a.getString("ZONE_CODE");
                    int col6_1=rsGst46a.getInt("col6_1");
                    int col6_2=rsGst46a.getInt("col6_2");
                    int col6_3=rsGst46a.getInt("col6_3");
                    int col6_4=rsGst46a.getInt("col6_4");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col6_1+ col6_2) + "/" + String.valueOf(col6_3+col6_4);
                    if(col6_3+col6_4!=0) {
                        total=(((double) (col6_1+col6_2) * 100) / (col6_3+col6_4));
                    }
                    rank=score.marks4d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);

                    gsta = new GST4A(rsGst46a.getString("ZONE_NAME"), "ALL", totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            } else if (type.equalsIgnoreCase("commissary")) {
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);

                String queryGst46a = new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date,zone_code);



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
                ResultSet rsGst46a =GetExecutionSQL.getResult(queryGst46a);
//                ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst46b);
//                ResultSet rsGst6c =GetExecutionSQL.getResult(queryGst6c);
//                ResultSet rsGst6d =GetExecutionSQL.getResult(queryGst6d);

                while(rsGst46a.next()  ) {
                    String commname=rsGst46a.getString("COMM_NAME");
                    String ra=RelevantAspect.GST4D_RA;
                    String zoneCode = rsGst46a.getString("ZONE_CODE");
                    int col6_1=rsGst46a.getInt("col6_1");
                    int col6_2=rsGst46a.getInt("col6_2");
                    int col6_3=rsGst46a.getInt("col6_3");
                    int col6_4=rsGst46a.getInt("col6_4");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col6_1+ col6_2) + "/" + String.valueOf(col6_3+col6_4);
                    if(col6_3+col6_4!=0) {
                        total=(((double) (col6_1+col6_2) * 100) / (col6_3+col6_4));
                    }
                    rank=score.marks4d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);

                    gsta = new GST4A(rsGst46a.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                // Query string
                String queryGst46a=new CGSTSubParameterWiseQuery().QueryFor_gst4d_AllCommissonaryWise(month_date);
                ResultSet rsGst46a =GetExecutionSQL.getResult(queryGst46a);

                while(rsGst46a.next()) {
                    String commname=rsGst46a.getString("COMM_NAME");
                    String ra=RelevantAspect.GST4D_RA;
                    String zoneCode = rsGst46a.getString("ZONE_CODE");
                    int col6_1=rsGst46a.getInt("col6_1");
                    int col6_2=rsGst46a.getInt("col6_2");
                    int col6_3=rsGst46a.getInt("col6_3");
                    int col6_4=rsGst46a.getInt("col6_4");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col6_1+ col6_2) + "/" + String.valueOf(col6_3+col6_4);
                    if(col6_3+col6_4!=0) {
                        total=(((double) (col6_1+col6_2) * 100) / (col6_3+col6_4));
                    }
                    rank=score.marks4d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);

                    gsta = new GST4A(rsGst46a.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
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
     * updated: RKS & Nishant, june 11, 2024
     * Purpose: This methods have core function in Return Filing .
     */

    @ResponseBody
    @RequestMapping(value = "/gst5a")
    //  http://localhost:8080/cbicApi/cbic/gst5a?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst5a?month_date=2023-04-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst5a?month_date=2023-05-01&type=all_commissary
    public Object getGst5A(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
        //Connection done
        Connection con= JDBCConnection.getTNConnection();

        try {
            if (type.equalsIgnoreCase("zone")) {
                // Query string
                String queryGst14aa = new CGSTSubParameterWiseQuery().QueryFor_gst5a_ZoneWise(month_date);


                //Prepared Statement
                PreparedStatement psGst14aa = con.prepareStatement(queryGst14aa);

                //Result Set
                ResultSet rsGst14aa = psGst14aa.executeQuery();

                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.GST5A_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col10 = rsGst14aa.getInt("col10");
                    int col4 = rsGst14aa.getInt("col4");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col10) + "/" + String.valueOf(col4);
                    total = rsGst14aa.getDouble("total_score");

                   // rank = score.marks5a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks5a(totalScore);
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                // Query string
                String queryGst14aa= new CGSTSubParameterWiseQuery().QueryFor_gst5a_CommissonaryWise(month_date,zone_code);


                //Prepared Statement
                PreparedStatement psGst14aa=con.prepareStatement(queryGst14aa);

                //Result Set
                ResultSet rsGst14aa= psGst14aa.executeQuery();
                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.GST5A_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col10=rsGst14aa.getInt("col10");
                    int col4=rsGst14aa.getInt("col4");
                    int Zonal_rank = 0;
                    String gst = "no";

                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col10)+"/"+String.valueOf(col4);

                    if((col4)!=0) {
                        total = (((double) (col10) * 100) / (col4));
                    }

                   // rank=score.marks5a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks5a(totalScore);
                    gsta=new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                // Query string
                String queryGst14aa= new CGSTSubParameterWiseQuery().QueryFor_gst5a_AllCommissonaryWise(month_date);


                //Prepared Statement
                PreparedStatement psGst14aa=con.prepareStatement(queryGst14aa);

                //Result Set
                ResultSet rsGst14aa= psGst14aa.executeQuery();
                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.GST5A_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    total = rsGst14aa.getDouble("score_of_subparameter");
                    int col10=rsGst14aa.getInt("col10");
                    int col4=rsGst14aa.getInt("col4");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col10)+"/"+String.valueOf(col4);

//                    if((col4)!=0) {
//                        total = (((double) (col10) * 100) / (col4));
//                    }

                   // rank=score.marks5a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks5a(totalScore);
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
                //String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String queryGst14aa =  new CGSTSubParameterWiseQuery().QueryFor_gst5b_ZoneWise(month_date);

                // Query string
//                String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE,sum(14c.ADC_COMMISSIONERATE_TIME_LESS_3_NO +14c.ADC_AUDIT_TIME_LESS_3_NO+14c.ADC_INVESTIGATION_TIME_LESS_3_NO+14c.ADC_CALLBOOK_TIME_LESS_3_NO+14c.DC_COMMISSIONERATE_TIME_LESS_3_NO +14c.DC_AUDIT_TIME_LESS_3_NO+14c.DC_INVESTIGATION_TIME_LESS_3_NO+14c.DC_CALLBOOK_TIME_LESS_3_NO+14c.SUPERINTENDENT_COMMISSIONERATE_TIME_LESS_3_NO +14c.SUPERINTENDENT_AUDIT_TIME_LESS_3_NO+14c.SUPERINTENDENT_INVESTIGATION_TIME_LESS_3_NO+14c.SUPERINTENDENT_CALLBOOK_TIME_LESS_3_NO) as col22, " +
//                        "sum(14c.ADC_COMMISSIONERATE_TIME_3_TO_6_NO+14c.ADC_AUDIT_TIME_3_TO_6_NO+14c.ADC_INVESTIGATION_TIME_3_TO_6_NO+14c.ADC_CALLBOOK_TIME_3_TO_6_NO+14c.DC_COMMISSIONERATE_TIME_3_TO_6_NO +14c.DC_AUDIT_TIME_3_TO_6_NO+14c.DC_INVESTIGATION_TIME_3_TO_6_NO+14c.DC_CALLBOOK_TIME_3_TO_6_NO+14c.SUPERINTENDENT_COMMISSIONERATE_TIME_3_TO_6_NO +14c.SUPERINTENDENT_AUDIT_TIME_3_TO_6_NO+14c.SUPERINTENDENT_INVESTIGATION_TIME_3_TO_6_NO+14c.SUPERINTENDENT_CALLBOOK_TIME_3_TO_6_NO) AS col23 " +
//                        " FROM mis_gst_commcode AS cc " +
//                        "RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
//                        "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
//                        "WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE;";
//
//                //String prev_month_new = DateCalculate.getPreviousMonth(month_date);
//                String next_month_new = DateCalculate.getNextMonth(month_date);
//
//                String queryGst3aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE, sum(14c.ADC_COMMISSIONERATE_OPENING_NO+14c.ADC_AUDIT_OPENING_NO+14c.ADC_INVESTIGATION_OPENING_NO+14c.ADC_CALLBOOK_OPENING_NO+14c.DC_COMMISSIONERATE_OPENING_NO+14c.DC_AUDIT_OPENING_NO+14c.DC_INVESTIGATION_OPENING_NO+14c.DC_CALLBOOK_OPENING_NO+14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO+14c.SUPERINTENDENT_AUDIT_OPENING_NO+14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO+14c.SUPERINTENDENT_CALLBOOK_OPENING_NO) AS col16 FROM mis_gst_commcode AS cc " +
//                        "RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
//                        "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
//                        "WHERE 14c.MM_YYYY = '" + next_month_new + "'GROUP BY cc.ZONE_CODE;";

                //Result Set
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                // ResultSet rsGst3aa = GetExecutionSQL.getResult(queryGst3aa);
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
                    int sub_parameter_weighted_average = 0;

//                    total = (((double) (col22 + col23) * 100) / (col16));
                    //}
                    //rank = score.marks5b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks5b(totalScore);
                    String absval = String.valueOf(col22 + col23) + "/" + String.valueOf(col16);
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                // Query string
                String queryGst14aa=new CGSTSubParameterWiseQuery().QueryFor_gst5b_CommissonaryWise(month_date,zone_code);
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
                    int sub_parameter_weighted_average = 0;
//                    if (col16 != 0) {
//                        total=(((double) (col22+col23) * 100)/ (col16));
//                    }
                    //rank=score.marks5b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal) ;
                    int way_to_grade = score.marks5b(totalScore);
                    String absval = String.valueOf(col22+col23) + "/" + String.valueOf(col16);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                // Query string
                String queryGst14aa= new CGSTSubParameterWiseQuery().QueryFor_gst5b_AllCommissonaryWise(month_date);
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
                    int sub_parameter_weighted_average = 0;
//                    if (col16 != 0) {
//                        total=(((double) (col22+col23) * 100)/ (col16));}
                    //rank=score.marks5b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks5b(totalScore);
                    String absval = String.valueOf(col22+col23) + "/" + String.valueOf(col16);
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
    @RequestMapping(value = "/gst6a")
    //  http://localhost:8080/cbicApi/cbic/gst6a?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst6a?month_date=2023-04-01&zone_code=70&type=commissary
    //	http://localhost:8080/cbicApi/cbic/gst6a?month_date=2023-04-01&type=all_commissary
    public Object getGst6A(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {

                String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                String queryGst14aa = new CGSTSubParameterWiseQuery().QueryFor_gst6a_ZoneWise(month_date);


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
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname ="ALL";
                    int col9 = rsGst14aa.getInt("col9");
                    int col3 = rsGst14aa.getInt("col3");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;

                    total = rsGst14aa.getDouble("total_score");
//                    if (col3 != 0) {
//                        total = (((double) (col9)* 100) / col3);
//                    }

                   // rank = score.marks6a(total);
                    rank = rsGst14aa.getInt("z_rank");
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks6a(totalScore);
                    String absval = String.valueOf(col9) + "/" + String.valueOf(col3);
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) { //6a
                // Query string
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);
                String queryGst14aa= new CGSTSubParameterWiseQuery().QueryFor_gst6a_CommissonaryWise(month_date,zone_code);

                //Result Set
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.Gst6A_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col9=rsGst14aa.getInt("col9");
                    int col3=rsGst14aa.getInt("col3");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    if(col3!=0) {
                        total=(((double) (col9)*100) / col3);
                    }

                    //rank=score.marks6a(total);
                    rank = rsGst14aa.getInt("z_rank");
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    String absval = String.valueOf(col9) + "/" + String.valueOf(col3);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa= new CGSTSubParameterWiseQuery().QueryFor_gst6a_AllCommissonaryWise(month_date);
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.Gst6A_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col9=rsGst14aa.getInt("col9");
                    int col3=rsGst14aa.getInt("col3");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    if(col3!=0) {
                        total=(((double) (col9)* 100) / col3);
                    }

                    //rank=score.marks6a(total);
                    rank = rsGst14aa.getInt("z_rank");
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    String absval = String.valueOf(col9) + "/" + String.valueOf(col3);
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

                String queryGst14aa = new CGSTSubParameterWiseQuery().QueryFor_gst6b_ZoneWise(month_date);
                PreparedStatement psGst14aa = con.prepareStatement(queryGst14aa);
                ResultSet rsGst14aa = psGst14aa.executeQuery();
                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.Gst6B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname = "ALL";
                    int col18 = rsGst14aa.getInt("col18");
                    int col13 = rsGst14aa.getInt("col13");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;

                    total = rsGst14aa.getDouble("total_score");

//                    if ((col13) != 0) {
//                        total = (((double) (col18)) / (col13));
//                    }
                    //rank = score.marks6b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    String absval = String.valueOf(col18) + "/" + String.valueOf(col13);
                    int way_to_grade = score.marks6b(totalScore);

                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                // Query string
                String queryGst14aa = new CGSTSubParameterWiseQuery().QueryFor_gst6b_CommissonaryWise(month_date,zone_code);


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
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;

                    if ((col13) != 0) {
                        total = (((double) (col18) * 100) / (col13));
                    }
                    rank = score.marks6b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    String absval = String.valueOf(col18) + "/" + String.valueOf(col13);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) { //6b
                String queryGst14aa = new CGSTSubParameterWiseQuery().QueryFor_gst6b_AllCommissonaryWise(month_date);



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
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;

                    if ((col13) != 0) {
                        total = (((double) (col18) * 100) / (col13));
                    }
                    rank = score.marks6b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
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
    //  http://localhost:8080/cbicApi/cbic/gst6c?month_date=2023-04-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst6c?month_date=2023-04-01&zone_code=70&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/gst6c?month_date=2023-04-01&type=all_commissary
    public Object getGst6C(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;
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
                String queryGst14aa =  new CGSTSubParameterWiseQuery().QueryFor_gst6c_ZoneWise(month_date);
                //Result Set
                ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.Gst6C_RA;
                    String zone_name = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String absval = rsGst14aa.getString("absval");
                    String commname= "ALL";
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    total = rsGst14aa.getDouble("score_of_parameter6c");

                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks6c(totalScore);
                    gsta = new GST4A(zone_name, commname, totalScore,absval,zoneCode,ra,
                                                         Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) { // gst 6c
                // Query string
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);

                String queryGst14aa=  // new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date,zone_code);
                        "WITH cte1 AS (\n" +
                        "    SELECT \n" +
                        "        zc.ZONE_NAME, \n" +
                        "        cc.COMM_NAME, \n" +
                        "        cc.ZONE_CODE, \n" +
                        "        (14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9\n" +
                        "    FROM \n" +
                        "        mis_gst_commcode AS cc\n" +
                        "    RIGHT JOIN \n" +
                        "        mis_dgi_ce_1a AS 14c \n" +
                        "    ON \n" +
                        "        cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_zonecode AS zc \n" +
                        "    ON \n" +
                        "        zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "    WHERE \n" +
                        "        cc.ZONE_CODE = '"+zone_code+"'\n" +
                        "        AND 14c.MM_YYYY =  '" + month_date + "'\n" +
                        "),\n" +
                        "cte2 AS (\n" +
                        "    SELECT \n" +
                        "        zc.ZONE_NAME, \n" +
                        "        cc.COMM_NAME, \n" +
                        "        cc.ZONE_CODE, \n" +
                        "        (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
                        "    FROM \n" +
                        "        mis_gst_commcode AS cc\n" +
                        "    RIGHT JOIN \n" +
                        "        mis_dgi_ce_1a AS 14c \n" +
                        "    ON \n" +
                        "        cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_zonecode AS zc \n" +
                        "    ON \n" +
                        "        zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "    WHERE \n" +
                        "        cc.ZONE_CODE = '"+zone_code+"' \n" +
                        "        AND 14c.MM_YYYY = '" + prev_month_new + "'\n" +
                        ")\n" +
                        "SELECT \n" +
                        "    cte1.ZONE_NAME,\n" +
                        "    cte1.COMM_NAME,\n" +
                        "    cte1.ZONE_CODE,\n" +
                        "    cte1.col9,\n" +
                        "    cte2.col3,\n" +
                        "    (cte1.col9 / cte2.col3) * 100 AS total_score\n" +
                        "FROM \n" +
                        "    cte1 \n" +
                        "INNER JOIN \n" +
                        "    cte2\n" +
                        "ON\n" +
                        "    cte1.ZONE_NAME = cte2.ZONE_NAME\n" +
                        "    AND cte1.COMM_NAME = cte2.COMM_NAME\n" +
                        "    AND cte1.ZONE_CODE = cte2.ZONE_CODE\n" +
                        "ORDER BY \n" +
                        "    total_score DESC;\n";

                //Result Set
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.Gst6C_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col9=rsGst14aa.getInt("col9");
                    int col3=rsGst14aa.getInt("col3");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;

                    if(col3!=0) {
                        total = (((double) (col9) * 100) / col3);
                    }

                    rank=score.marks6c(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal) ;
                    String absval = String.valueOf(col9) + "/" + String.valueOf(col3);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);
                String queryGst14aa= //new CGSTSubParameterWiseQuery().QueryFor_gst1b_CommissonaryWise(month_date);
                        "WITH CTE1 AS (\n" +
                        "    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,\n" +
                        "        (14c.COMM_DISPOSAL_NO + 14c.JC_DISPOSAL_NO + 14c.AC_DISPOSAL_NO + 14c.SUP_DISPOSAL_NO) AS col9\n" +
                        "    FROM mis_gst_commcode AS cc\n" +
                        "    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "    WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                        "),\n" +
                        "CTE2 AS (\n" +
                        "    SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,\n" +
                        "        (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col3\n" +
                        "    FROM mis_gst_commcode AS cc\n" +
                        "    RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "    WHERE 14c.MM_YYYY = '" + prev_month_new + "'\n" +
                        ")\n" +
                        "SELECT CTE1.ZONE_NAME, CTE1.COMM_NAME, CTE1.ZONE_CODE, CTE1.col9, CTE2.col3,\n" +
                        "    (CASE WHEN CTE2.col3 = 0 THEN 0 ELSE (CTE1.col9 / CTE2.col3) * 100 END) AS total_score\n" +
                        "FROM CTE1\n" +
                        "JOIN CTE2 \n" +
                        "    ON CTE1.ZONE_NAME = CTE2.ZONE_NAME \n" +
                        "    AND CTE1.COMM_NAME = CTE2.COMM_NAME \n" +
                        "    AND CTE1.ZONE_CODE = CTE2.ZONE_CODE\n" +
                        "ORDER BY total_score DESC;\n";
                //Result Set
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra=RelevantAspect.Gst6C_RA;
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col9=rsGst14aa.getInt("col9");
                    int col3=rsGst14aa.getInt("col3");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;

                    if(col3!=0) {
                        total = (((double) (col9) * 100) / col3);
                    }
                    rank=score.marks6c(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal) ;
                    String absval = String.valueOf(col9) + "/" + String.valueOf(col3);
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

        //Connection done
        Connection con= JDBCConnection.getTNConnection();

        try {
            if (type.equalsIgnoreCase("zone")) {

                // Query string
                String queryGst14aa = //new CGSTSubParameterWiseQuery().QueryFor_gst1b_CommissonaryWise(month_date);
                        "SELECT \n" +
                        "    zc.ZONE_NAME,\n" +
                        "    cc.ZONE_CODE,\n" +
                        "    SUM(\n" +
                        "        14c.COMM_MORE_YEAR_AMT +\n" +
                        "        14c.JC_MORE_YEAR_AMT +\n" +
                        "        14c.AC_MORE_YEAR_AMT +\n" +
                        "        14c.SUP_MORE_YEAR_AMT\n" +
                        "    ) AS col18,\n" +
                        "    SUM(\n" +
                        "        14c.COMM_CLOSING_NO +\n" +
                        "        14c.JC_CLOSING_NO +\n" +
                        "        14c.AC_CLOSING_NO +\n" +
                        "        14c.SUP_CLOSING_NO\n" +
                        "    ) AS col13,\n" +
                        "    COALESCE(\n" +
                        "        (SUM(\n" +
                        "            14c.COMM_MORE_YEAR_AMT +\n" +
                        "            14c.JC_MORE_YEAR_AMT +\n" +
                        "            14c.AC_MORE_YEAR_AMT +\n" +
                        "            14c.SUP_MORE_YEAR_AMT\n" +
                        "        ) / NULLIF(SUM(\n" +
                        "            14c.COMM_CLOSING_NO +\n" +
                        "            14c.JC_CLOSING_NO +\n" +
                        "            14c.AC_CLOSING_NO +\n" +
                        "            14c.SUP_CLOSING_NO\n" +
                        "        ), 0)) * 100, 0\n" +
                        "    ) AS total_score\n" +
                        "FROM mis_gst_commcode AS cc\n" +
                        "RIGHT JOIN mis_dgi_ce_1a AS 14c\n" +
                        "    ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "LEFT JOIN mis_gst_zonecode AS zc\n" +
                        "    ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "WHERE 14c.MM_YYYY = '" + month_date + "'\n" +
                        "GROUP BY cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        "ORDER BY total_score ASC;\n";

                //Prepared Statement
                PreparedStatement psGst14aa = con.prepareStatement(queryGst14aa);

                //Result Set
                ResultSet rsGst14aa = psGst14aa.executeQuery();

                while (rsGst14aa.next()) {
                    String ra = RelevantAspect.Gst6D_RA;
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname="ALL";
                    int col18 = rsGst14aa.getInt("col18");
                    int col13 = rsGst14aa.getInt("col13");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;

                    if ((col13) != 0) {
                        total = (((double) (col18) * 100) / (col13));
                    }

                    //}
                    rank = score.marks6d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    String absval = String.valueOf(col18) + "/" + String.valueOf(col13);
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            } else if (type.equalsIgnoreCase("commissary")) { // gst 6d
                // Query string
                String queryGst14aa= // new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date,zone_code);
                        "SELECT zc.ZONE_NAME,\n" +
                        "       cc.COMM_NAME,\n" +
                        "       cc.ZONE_CODE,\n" +
                        "       (14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) AS col18,\n" +
                        "       (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col13,\n" +
                        "       ((14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) /\n" +
                        "       (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) * 100) AS total_score\n" +
                        "FROM mis_gst_commcode AS cc\n" +
                        "RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "WHERE 14c.MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE = '"+zone_code+"'\n" +
                        "ORDER BY total_score ASC;\n";


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
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;

                    if((col13)!=0) {
                        total= (((double) (col18) * 100) / (col13));
                    }

                    rank=score.marks6d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    String absval=String.valueOf(col18)+"/"+String.valueOf(col13);
                    gsta=new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa=  //new CGSTSubParameterWiseQuery().QueryFor_gst1b_CommissonaryWise(month_date);
                        "SELECT \n" +
                        "    zc.ZONE_NAME, \n" +
                        "    cc.ZONE_CODE, \n" +
                        "    cc.COMM_NAME,\n" +
                        "    IFNULL((14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT), 0) AS col18, \n" +
                        "    IFNULL((14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO), 0) AS col13,\n" +
                        "    IFNULL((14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) / \n" +
                        "    (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO), 0) AS total_score_of_subpara4,\n" +
                        "    IFNULL(((14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) / \n" +
                        "    (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO)) * 100, 0) AS total_score\n" +
                        "FROM \n" +
                        "    mis_gst_commcode AS cc\n" +
                        "RIGHT JOIN \n" +
                        "    mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "LEFT JOIN \n" +
                        "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "WHERE \n" +
                        "    14c.MM_YYYY = '" + month_date + "'\n" +
                        "ORDER BY \n" +
                        "    total_score ASC;\n";
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
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;

                    if((col13)!=0) {
                        total= (((double) (col18) * 100) / (col13));
                    }

                    rank=score.marks6d(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal) * 100;
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
                String quaryGst7 = //new CGSTSubParameterWiseQuery().QueryFor_gst1b_CommissonaryWise(month_date);
                        "SELECT " +
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

                    String ra = RelevantAspect.Gst7_RA;
                    String zoneCode = rsGst7.getString("ZONE_CODE");
                    String commname="ALL";
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    Double col22 = rsGst7.getDouble("col22");
                    Double col16 = rsGst7.getDouble("col16");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;

                    // for only this sub parameter
                    int col22ab = rsGst7.getInt("col22");
                    int col16ab = rsGst7.getInt("col16");
                    String absval = String.valueOf(col22ab) + "/" + String.valueOf(col16ab);

//                    if (col16 != 0) {
//                        total = ((double) col22 / col16);
//                    } else {
//                        total = 0;
//                    }
                    //total=((double) col22 / col16)*100;

                    if (col16 != 0) {
                        total = ((col22 *100)/col16);
                    }else {
                        total = 0;
                    }

                    rank = score.marks7(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta = new GST4A(rsGst7.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) { // gst 7
                String quaryGst7 =  // new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date,zone_code);
                        "SELECT \r\n"
                        + "    cc.ZONE_CODE,\r\n"
                        + "    zc.ZONE_NAME,\r\n"
                        + "    cc.COMM_NAME,\r\n"
                        + "    SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO) AS col16,\r\n"
                        + "    SUM(dpm.age_breakup_above60_no) AS col22\r\n"
                        + "FROM mis_gst_commcode AS cc\r\n"
                        + "RIGHT JOIN mis_dpm_gst_4 AS dpm\r\n"
                        + "    ON cc.COMM_CODE = dpm.COMM_CODE\r\n"
                        + "LEFT JOIN mis_gst_zonecode AS zc\r\n"
                        + "    ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                        + "WHERE dpm.MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE = '"+zone_code+"'\r\n"
                        + "GROUP BY \r\n"
                        + "    cc.ZONE_CODE, \r\n"
                        + "    zc.ZONE_NAME,\r\n"
                        + "    cc.COMM_NAME;\r\n"
                        + "";

                ResultSet rsGst14aa =GetExecutionSQL.getResult(quaryGst7);

                while(rsGst14aa.next()) {
                    String ra=RelevantAspect.Gst7_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col22=rsGst14aa.getInt("col22");
                    int col16=rsGst14aa.getInt("col16");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col22)+"/"+String.valueOf(col16);

                    if (col16 != 0){
                        total =(((double) col22 * 100 )/col16);
                    }
                    else{
                        total=0;
                    }
                    rank=score.marks7(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);

                }
            }else if (type.equalsIgnoreCase("all_commissary")) { // gst 7
                String quaryGst7 =// new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date);
                        "SELECT \r\n"
                        + "    cc.ZONE_CODE,\r\n"
                        + "    zc.ZONE_NAME,\r\n"
                        + "    cc.COMM_NAME,\r\n"
                        + "    SUM(dpm.opening_balance_no + dpm.RFD_01_NO - dpm.RFD_03_NO - dpm.RFD_06_SANCTIONED_NO - dpm.RFD_06_REJECTED_NO) AS col16,\r\n"
                        + "    SUM(dpm.age_breakup_above60_no) AS col22\r\n"
                        + "FROM mis_gst_commcode AS cc\r\n"
                        + "RIGHT JOIN mis_dpm_gst_4 AS dpm\r\n"
                        + "    ON cc.COMM_CODE = dpm.COMM_CODE\r\n"
                        + "LEFT JOIN mis_gst_zonecode AS zc\r\n"
                        + "    ON zc.ZONE_CODE = cc.ZONE_CODE\r\n"
                        + "WHERE dpm.MM_YYYY = '" + month_date + "' \r\n"
                        + "GROUP BY \r\n"
                        + "    cc.ZONE_CODE, \r\n"
                        + "    zc.ZONE_NAME,\r\n"
                        + "    cc.COMM_NAME;\r\n"
                        + "";

                ResultSet rsGst14aa =GetExecutionSQL.getResult(quaryGst7);

                while(rsGst14aa.next()) {
                    String ra=RelevantAspect.Gst7_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col22=rsGst14aa.getInt("col22");
                    int col16=rsGst14aa.getInt("col16");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col22)+"/"+String.valueOf(col16);

                    if (col16 != 0){
                        total =(((double) col22 * 100 )/col16);
                    }
                    else{
                        total=0;
                    }
                    rank=score.marks7(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
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


        try {
            if(type.equalsIgnoreCase("zone")) {

                String prev_month_new =DateCalculate.getPreviousMonth(month_date);

                // Query string
                String queryGst14aa=// new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date);
                        "WITH cte AS (\n" +
                        "    SELECT DISTINCT \n" +
                        "        zc.ZONE_NAME,zc.ZONE_CODE, cc.COMM_CODE,\n" +
                        "        SUM(a11.ARREAR_REALISED_AMT) AS col13\n" +
                        "    FROM mis_tar_gst_3_new AS a11\n" +
                        "    LEFT JOIN mis_gst_commcode AS cc ON a11.COMM_CODE = cc.COMM_CODE\n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                        "    WHERE a11.MM_YYYY = '"+month_date+"'\n" +
                        "    GROUP BY zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_CODE\n" +
                        "), \n" +
                        "cte1 AS (\n" +
                        "    SELECT DISTINCT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_CODE,\n" +
                        "        SUM(1) AS col3\n" +
                        "    FROM mis_tar_gst_3_new AS a11\n" +
                        "    LEFT JOIN mis_gst_commcode AS cc ON a11.COMM_CODE = cc.COMM_CODE\n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                        "    WHERE a11.MM_YYYY = '"+ prev_month_new +"'\n" +
                        "    GROUP BY zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_CODE\n" +
                        ")\n" +
                        "SELECT\n" +
                        "    cte.ZONE_NAME, cte.ZONE_CODE,cte.COMM_CODE,\n" +
                        "    cte.col13, cte1.col3,\n" +
                        "    (cte.col13 / cte1.col3) AS total_score\n" +
                        "FROM cte\n" +
                        "INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE;\n";


                //Result Set
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra=RelevantAspect.GST8A_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col13=rsGst14aa.getInt("col13");
                    int col3=rsGst14aa.getInt("col3");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col13) + "/" + String.valueOf(col3);
                    if ((col3) != 0){
                        total =(((double) (col13) * 100)/(col3));
                    }
                    else{
                        total=0;
                    }

                    rank=score.marks8a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);

                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                    //allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);

                // Query string
                String queryGst14aa= // new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date,zone_code);
                        "WITH cte AS ( "
                        + "SELECT DISTINCT "
                        + "zc.ZONE_NAME, "
                        + "zc.ZONE_CODE, "
                        + "cc.COMM_NAME, "
                        + "cc.COMM_CODE, "
                        + "(a11.ARREAR_REALISED_AMT) AS col13 "
                        + "FROM mis_tar_gst_3_new AS a11 "
                        + "LEFT JOIN mis_gst_commcode AS cc ON a11.COMM_CODE = cc.COMM_CODE "
                        + "LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE "
                        + "WHERE a11.MM_YYYY = '"+month_date+"' AND cc.ZONE_CODE = '+zone_code+' "
                        + "), "
                        + "cte1 AS ( "
                        + "SELECT DISTINCT "
                        + "zc.ZONE_NAME, "
                        + "zc.ZONE_CODE, "
                        + "cc.COMM_NAME, "
                        + "cc.COMM_CODE, "
                        + "1 AS col3 "
                        + "FROM mis_tar_gst_3_new AS a11 "
                        + "LEFT JOIN mis_gst_commcode AS cc ON a11.COMM_CODE = cc.COMM_CODE "
                        + "LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE "
                        + "WHERE a11.MM_YYYY = '" + prev_month_new + "'"
                        + "AND cc.ZONE_CODE = '"+zone_code+"' "
                        + ") "
                        + "SELECT "
                        + "cte.ZONE_NAME, "
                        + "cte.ZONE_CODE, "
                        + "cte.COMM_NAME, "
                        + "cte.COMM_CODE, "
                        + "cte.col13, "
                        + "cte1.col3, "
                        + "((cte.col13 / cte1.col3)) AS total_score "
                        + "FROM cte "
                        + "INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE;";

                //Result Set
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.GST8A_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col13=rsGst14aa.getInt("col13");
                    int col3=rsGst14aa.getInt("col3");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col13) + "/" + String.valueOf(col3);
                    if ((col3) != 0){
                        total =(((double) (col13) * 100)/(col3));
                    }
                    else{
                        total=0;
                    }
                    rank=score.marks8a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);

                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);

                // Query string
                String queryGst14aa= // new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date);
                        "WITH cte AS (\n" +
                        "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, a.PROSECUTION_LAUNCHED AS col4_1, a.ARRESTS_MADE AS col4_2\n" +
                        "    FROM mis_gi_gst_1a AS a \n" +
                        "    LEFT JOIN mis_gst_commcode AS cc ON a.COMM_CODE = cc.COMM_CODE \n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                        "    WHERE a.MM_YYYY = '"+month_date+"' \n" +
                        "),\n" +
                        "cte1 AS (\n" +
                        "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, a.PROSECUTION_LAUNCHED AS col4_3, a.ARRESTS_MADE AS col4_4\n" +
                        "    FROM mis_gi_gst_1a AS a \n" +
                        "    LEFT JOIN mis_gst_commcode AS cc ON a.COMM_CODE = cc.COMM_CODE \n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                        "    WHERE a.MM_YYYY = '" + prev_month_new + "' \n" +
                        ")\n" +
                        "SELECT DISTINCT cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_NAME, cte.col4_1,cte.col4_2,cte1.col4_3,cte1.col4_4\n" +
                        "FROM cte INNER JOIN cte1 \n" +
                        "ON cte.ZONE_CODE = cte1.ZONE_CODE \n" +
                        "AND cte.COMM_NAME = cte1.COMM_NAME;";

                //Result Set
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String commname=rsGst14aa.getString("COMM_NAME");
                    String ra=RelevantAspect.GST8A_RA;
                    String zoneName = rsGst14aa.getString("ZONE_NAME");
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    int col13=rsGst14aa.getInt("col13");
                    int col3=rsGst14aa.getInt("col3");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col13) + "/" + String.valueOf(col3);
                    if ((col3) != 0){
                        total =(((double) (col13) * 100)/(col3));
                    }
                    else{
                        total=0;
                    }
                    rank=score.marks8a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);

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
     * updated: Nishant, may 23, 2024
     * Purpose: This methods have core function in Return Filing .
     */
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
    public Object getGst8B(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList=new ArrayList<>();
        GST4A gsta=null;
        int rank = 0;
        double total = 0.00;
        try {
            if(type.equalsIgnoreCase("zone")) {

                // Query string
                String queryGst14aa=// new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date);
                        "SELECT\n" +
                        "    cc.ZONE_CODE,\n" +
                        "    zc.ZONE_NAME,\n" +
                        "    SUM(tc.CLOSING_AMT) AS col20,\n" +
                        "    SUM(tc.BELOW_YEAR_AMT) AS col22,\n" +
                        "    -- Calculate total_score using the formula\n" +
                        "    (SUM(tc.CLOSING_AMT) - SUM(tc.BELOW_YEAR_AMT)) / NULLIF(SUM(tc.CLOSING_AMT), 0) * 100 AS total_score\n" +
                        "FROM\n" +
                        "    mis_gst_commcode AS cc\n" +
                        "RIGHT JOIN\n" +
                        "    mis_tar_gst_3_new AS tc ON cc.COMM_CODE = tc.COMM_CODE\n" +
                        "LEFT JOIN\n" +
                        "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "WHERE\n" +
                        "    tc.MM_YYYY = '" + month_date + "'\n" +
                        "GROUP BY\n" +
                        "    cc.ZONE_CODE, zc.ZONE_NAME\n" +
                        "ORDER BY\n" +
                        "    total_score ASC;\n";


//				String prev_month_new =DateCalculate.getPreviousMonth(month_date);
//
//				String queryGst3aa="SELECT cc.ZONE_CODE, zc.ZONE_NAME, " +
//						"SUM(14c.CLOSING_AMT) AS col21  " +
//						"FROM mis_gst_commcode AS cc " +
//						"RIGHT JOIN mis_tar_gst_3_new AS 14c " +
//						"ON cc.COMM_CODE = 14c.COMM_CODE " +
//						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
//						"WHERE 14c.MM_YYYY = '" +prev_month_new + "' " +
//						"GROUP BY cc.ZONE_CODE;";

                //Result Set
                ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
                //ResultSet rsGst3aa=GetExecutionSQL.getResult(queryGst3aa);
                while(rsGst14aa.next()) {
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String ra=RelevantAspect.GST8B_RA;
                    int col20=rsGst14aa.getInt("col20");
                    int col22=rsGst14aa.getInt("col22");
                    Double t_score = rsGst14aa.getDouble("total_score");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;

                    //int col3=rsGst14aa.getInt("col3");
                    //int col20=0;

//					if(rsGst3aa.next()) {
//						col21=rsGst3aa.getInt("col22");
//					}

                    // Calculate total
                    //total = col20 != 0 ? (((double) (col20 - col22) * 100) / col20) : 0.0;
                    //}
                    rank=score.marks8b(total);
                    String absval = (col20-col22) + "/" +(col20);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks8b(totalScore);
                    gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    //gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),"ALL",(int)total,rank);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                // Query string
                String queryGst14aa=// new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date,zone_code);
                        "SELECT \n" +
                        "    cc.ZONE_CODE, \n" +
                        "    zc.ZONE_NAME,\n" +
                        "    cc.COMM_NAME,  \n" +
                        "    MAX(14c.CLOSING_AMT) AS col20, \n" +
                        "    MAX(14c.BELOW_YEAR_AMT) AS col22,\n" +
                        "    ((MAX(14c.CLOSING_AMT) - MAX(14c.BELOW_YEAR_AMT)) / MAX(14c.CLOSING_AMT)) * 100 AS total_score\n" +
                        "FROM \n" +
                        "    mis_gst_commcode AS cc \n" +
                        "RIGHT JOIN \n" +
                        "    mis_tar_gst_3_new AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                        "LEFT JOIN \n" +
                        "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                        "WHERE \n" +
                        "    14c.MM_YYYY = '"+month_date+"' \n" +
                        "    AND zc.ZONE_CODE = '"+zone_code+"'\n" +
                        "GROUP BY \n" +
                        "    cc.ZONE_CODE, \n" +
                        "    zc.ZONE_NAME,\n" +
                        "    cc.COMM_NAME\n" +
                        "ORDER BY \n" +
                        "    total_score ASC;\n";


//				String prev_month_new =DateCalculate.getPreviousMonth(month_date);
//
//				String queryGst3aa= "SELECT cc.ZONE_CODE,zc.ZONE_NAME,cc.COMM_NAME,"
//						+ "(14c.CLOSING_AMT) AS col21 "
//						+ "FROM mis_gst_commcode AS cc "
//						+ "RIGHT JOIN mis_tar_gst_3_new AS 14c ON cc.COMM_CODE = 14c.COMM_CODE "
//						+ "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE "
//						+ "WHERE 14c.MM_YYYY = '"+prev_month_new+"' and zc.ZONE_CODE='"+zone_code+"';";


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
                    int sub_parameter_weighted_average = 0;

                    //int col3=rsGst14aa.getInt("col3");
//					int col21=0;
//
//					if(rsGst3aa.next()) {
//						col21=rsGst3aa.getInt("col21");
//					}
//                    total = col20 != 0 ? (((double) (col20 - col22) * 100) / col20) : 0.0;
//
//                    total=((double) (col20-col22)/ col20);
//                    //}
                    //rank=score.marks8b(total);
                    String absval = String.valueOf(col20-col22) + "/" + String.valueOf(col20);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks8b(totalScore);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                // Query string
                String queryGst14aa=// new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date);
                        "SELECT \n" +
                        "    cc.ZONE_CODE, \n" +
                        "    zc.ZONE_NAME,\n" +
                        "    cc.COMM_NAME,  \n" +
                        "    MAX(14c.CLOSING_AMT) AS col20, \n" +
                        "    MAX(14c.BELOW_YEAR_AMT) AS col22,\n" +
                        "    ((MAX(14c.CLOSING_AMT) - MAX(14c.BELOW_YEAR_AMT)) / MAX(14c.CLOSING_AMT)) * 100 AS total_score\n" +
                        "FROM \n" +
                        "    mis_gst_commcode AS cc \n" +
                        "RIGHT JOIN \n" +
                        "    mis_tar_gst_3_new AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                        "LEFT JOIN \n" +
                        "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                        "WHERE \n" +
                        "    14c.MM_YYYY = '"+month_date+"' \n" +
                        "GROUP BY \n" +
                        "    cc.ZONE_CODE, \n" +
                        "    zc.ZONE_NAME,\n" +
                        "    cc.COMM_NAME\n" +
                        "ORDER BY \n" +
                        "    total_score ASC;\n";
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
                    int sub_parameter_weighted_average = 0;
//                    total = col20 != 0 ? (((double) (col20 - col22) * 100) / col20) : 0.0;
//
//                    total=((double) (col20-col22)/ col20);
//                    //}
                    rank=score.marks8b(total);
                    String absval = String.valueOf(col20-col22) + "/" + String.valueOf(col20);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks8b(totalScore);
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
                String queryGst14aa =// new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date);
                        "WITH cte AS (\n" +
                        "    SELECT \n" +
                        "        zc.ZONE_NAME, \n" +
                        "        zc.ZONE_CODE, \n" +
                        "        COUNT(*) AS col8,\n" +
                        "        SUM(lgl.PROSECUTION_SANCTIONED) AS col5\n" +
                        "    FROM mis_dla_gst_lgl_4 AS lgl \n" +
                        "    LEFT JOIN mis_gst_commcode AS cc ON lgl.COMM_CODE = cc.COMM_CODE \n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                        "    WHERE lgl.MM_YYYY = '"+month_date+"'\n" +
                        "    GROUP BY zc.ZONE_CODE\n" +
                        "),\n" +
                        "cte1 AS (\n" +
                        "    SELECT \n" +
                        "        zc.ZONE_NAME, \n" +
                        "        zc.ZONE_CODE, \n" +
                        "        SUM(lgl.PROSECUTION_SANCTIONED) AS col5_1 \n" +
                        "    FROM mis_dla_gst_lgl_4 AS lgl \n" +
                        "    LEFT JOIN mis_gst_commcode AS cc ON lgl.COMM_CODE = cc.COMM_CODE \n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                        "    WHERE lgl.MM_YYYY = '"+prev_month_new+"'\n" +
                        "    GROUP BY zc.ZONE_CODE\n" +
                        ")\n" +
                        "SELECT \n" +
                        "    cte.ZONE_NAME, \n" +
                        "    cte.ZONE_CODE, \n" +
                        "    cte.col8,\n" +
                        "    cte.col5,\n" +
                        "    cte1.col5_1,\n" +
                        "    (cte.col8 / (cte.col5 + cte1.col5_1)) AS total_score\n" +
                        "FROM cte \n" +
                        "INNER JOIN cte1 ON cte.ZONE_CODE = cte1.ZONE_CODE;";
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.GST9A_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname= "ALL";
                    int col8=rsGst14aa.getInt("col8");
                    int col5=rsGst14aa.getInt("col5");
                    int col5_1=rsGst14aa.getInt("col5_1");
                    total = rsGst14aa.getDouble("total_score") * 100;
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col8)+"/"+String.valueOf(col5 + col5_1);

                    rank=score.marks9a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }

            } else if (type.equalsIgnoreCase("commissary")) {
                String queryGst14aa=// new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date,zone_code);
                        "WITH cte AS (\n" +
                        "    SELECT \n" +
                        "        zc.ZONE_NAME, \n" +
                        "        zc.ZONE_CODE, \n" +
                        "        cc.COMM_NAME, \n" +
                        "        cc.COMM_CODE, \n" +
                        "        1 AS col8,\n" +
                        "        _11a.PROSECUTION_SANCTIONED AS col5 \n" +
                        "    FROM \n" +
                        "        mis_dla_gst_lgl_4 AS _11a \n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_commcode AS cc ON _11a.COMM_CODE = cc.COMM_CODE \n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                        "    WHERE \n" +
                        "        _11a.MM_YYYY = '"+month_date+"' \n" +
                        "        AND cc.ZONE_CODE = '"+zone_code+"'\n" +
                        "),\n" +
                        "cte1 AS (\n" +
                        "    SELECT \n" +
                        "        zc.ZONE_NAME, \n" +
                        "        zc.ZONE_CODE, \n" +
                        "        cc.COMM_NAME, \n" +
                        "        cc.COMM_CODE, \n" +
                        "        _11a.PROSECUTION_SANCTIONED AS col5_1\n" +
                        "    FROM \n" +
                        "        mis_dla_gst_lgl_4 AS _11a \n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_commcode AS cc ON _11a.COMM_CODE = cc.COMM_CODE \n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                        "    WHERE \n" +
                        "        _11a.MM_YYYY = '"+prev_month_new+"'\n" +
                        "        AND cc.ZONE_CODE = '"+zone_code+"'\n" +
                        ")\n" +
                        "SELECT \n" +
                        "    cte.ZONE_NAME,\n" +
                        "    cte.ZONE_CODE,\n" +
                        "    cte.COMM_NAME,\n" +
                        "    cte.COMM_CODE,\n" +
                        "    cte.col8,\n" +
                        "    cte.col5,\n" +
                        "    cte1.col5_1,\n" +
                        "    CASE \n" +
                        "        WHEN (cte.col5 + cte1.col5_1) <> 0 THEN cte.col8 / (cte.col5 + cte1.col5_1)\n" +
                        "        ELSE NULL -- or any default value or handling you prefer\n" +
                        "    END AS total_score\n" +
                        "FROM \n" +
                        "    cte \n" +
                        "INNER JOIN \n" +
                        "    cte1 ON cte.COMM_CODE = cte1.COMM_CODE;";
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.GST9A_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col8=rsGst14aa.getInt("col8");
                    int col5=rsGst14aa.getInt("col5");
                    int col5_1=rsGst14aa.getInt("col5_1");
                    total = rsGst14aa.getDouble("total_score") * 100;
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col8)+"/"+String.valueOf(col5 + col5_1);

                    rank=score.marks9a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String queryGst14aa=// new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date);
                        "WITH cte AS (\n" +
                        "    SELECT \n" +
                        "        zc.ZONE_NAME, \n" +
                        "        zc.ZONE_CODE, \n" +
                        "        cc.COMM_NAME, \n" +
                        "        cc.COMM_CODE, \n" +
                        "        1 AS col8,\n" +
                        "        _11a.PROSECUTION_SANCTIONED AS col5 \n" +
                        "    FROM \n" +
                        "        mis_dla_gst_lgl_4 AS _11a \n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_commcode AS cc ON _11a.COMM_CODE = cc.COMM_CODE \n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                        "    WHERE \n" +
                        "        _11a.MM_YYYY = '"+month_date+"' \n" +
                        "),\n" +
                        "cte1 AS (\n" +
                        "    SELECT \n" +
                        "        zc.ZONE_NAME, \n" +
                        "        zc.ZONE_CODE, \n" +
                        "        cc.COMM_NAME, \n" +
                        "        cc.COMM_CODE, \n" +
                        "        _11a.PROSECUTION_SANCTIONED AS col5_1\n" +
                        "    FROM \n" +
                        "        mis_dla_gst_lgl_4 AS _11a \n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_commcode AS cc ON _11a.COMM_CODE = cc.COMM_CODE \n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                        "    WHERE \n" +
                        "        _11a.MM_YYYY = '"+prev_month_new+"'\n" +
                        ")\n" +
                        "SELECT \n" +
                        "    cte.ZONE_NAME,\n" +
                        "    cte.ZONE_CODE,\n" +
                        "    cte.COMM_NAME,\n" +
                        "    cte.COMM_CODE,\n" +
                        "    cte.col8,\n" +
                        "    cte.col5,\n" +
                        "    cte1.col5_1,\n" +
                        "    CASE \n" +
                        "        WHEN (cte.col5 + cte1.col5_1) <> 0 THEN cte.col8 / (cte.col5 + cte1.col5_1)\n" +
                        "        ELSE NULL -- or any default value or handling you prefer\n" +
                        "    END AS total_score\n" +
                        "FROM \n" +
                        "    cte \n" +
                        "INNER JOIN \n" +
                        "    cte1 ON cte.COMM_CODE = cte1.COMM_CODE;";
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.GST9A_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col8=rsGst14aa.getInt("col8");
                    int col5=rsGst14aa.getInt("col5");
                    int col5_1=rsGst14aa.getInt("col5_1");
                    total = rsGst14aa.getDouble("total_score") * 100;
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col8)+"/"+String.valueOf(col5 + col5_1);

                    rank=score.marks9a(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
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
    //  http://localhost:8080/cbicApi/cbic/gst9b?month_date=2023-05-01&type=zone
    //  http://localhost:8080/cbicApi/cbic/gst9b?month_date=2023-05-01&zone_code=51&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/gst9b?month_date=2023-04-01&type=all_commissary
    public Object getGst9b(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;


        try {
            // Query string
            if (type.equalsIgnoreCase("zone")) {
                //String prev_month_new =DateCalculate.getPreviousMonth(month_date);
                String queryGst14aa =// new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date);
                        "SELECT zc.ZONE_NAME, cc.ZONE_CODE, \n" +
                        "SUM(14c.PROSECUTION_LAUNCHED) AS col4_4, SUM(14c.ARRESTS_MADE) AS col1_4,\n" +
                        "(SUM(14c.PROSECUTION_LAUNCHED) * 100 / SUM(14c.ARRESTS_MADE)) as score_of_subparameter9b\n" +
                        "FROM mis_gst_commcode AS cc \n" +
                        "RIGHT JOIN mis_gi_gst_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                        "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                        "WHERE 14c.MM_YYYY <= '" + month_date + "' \n" +
                        "GROUP BY cc.ZONE_CODE, zc.ZONE_NAME \n" +
                        "ORDER BY score_of_subparameter9b desc;";
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);

                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.GST9B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname= "ALL";
                    int col4=rsGst14aa.getInt("col4_4");
                    int col4_1=rsGst14aa.getInt("col1_4");
                    Double t_score = rsGst14aa.getDouble("score_of_subparameter9b");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
//					int col4_2=rsGst14aa.getInt("col4_2");
//					int col4_4=rsGst14aa.getInt("col4_4");
                    String absval=String.valueOf(col4)+"/"+String.valueOf(col4_1);

                    if((col4_1) != 0){
                        total=(((double) (col4) * 100) /(col4_1));
                    }


                    //rank=score.marks9b(total);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks9b(totalScore);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            } else if (type.equalsIgnoreCase("commissary")) { // gst 9b
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);

                String queryGst14aa=// new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date,zone_code);
                        "SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
                        "SUM(gst.PROSECUTION_LAUNCHED) AS col4_4, SUM(gst.ARRESTS_MADE) AS col1_4,\n" +
                        "    (SUM(gst.PROSECUTION_LAUNCHED) * 100 / SUM(gst.ARRESTS_MADE)) AS score_of_subparameter9b\n" +
                        "FROM mis_gst_commcode AS cc \n" +
                        "    RIGHT JOIN mis_gi_gst_1a AS gst ON cc.COMM_CODE = gst.COMM_CODE \n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                        "WHERE gst.MM_YYYY <= '" + month_date + "' and cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME\n" +
                        "ORDER BY score_of_subparameter9b DESC;";
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.GST9B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    Double t_score = rsGst14aa.getDouble("score_of_subparameter9b");
                    int col4_4=rsGst14aa.getInt("col4_4");
                    int col1_4=rsGst14aa.getInt("col1_4");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
//					int col4_2=rsGst14aa.getInt("col4_2");
//					int col4_4=rsGst14aa.getInt("col4_4");
                    String absval=String.valueOf(col4_4)+"/"+String.valueOf(col1_4);

//                    if((col1_4) != 0){
//                        total=(((double) (col4_4) * 100) /(col1_4));
//                    }

                    //rank=score.marks9b(total);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks9b(totalScore);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) { // gst 9b
                String prev_month_new =DateCalculate.getPreviousMonth(month_date);

                String queryGst14aa=// new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date);
                        "SELECT zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME,\n" +
                        "SUM(gst.PROSECUTION_LAUNCHED) AS col4_4, SUM(gst.ARRESTS_MADE) AS col1_4,\n" +
                        "    (SUM(gst.PROSECUTION_LAUNCHED) * 100 / SUM(gst.ARRESTS_MADE)) AS score_of_subparameter9b\n" +
                        "FROM mis_gst_commcode AS cc \n" +
                        "    RIGHT JOIN mis_gi_gst_1a AS gst ON cc.COMM_CODE = gst.COMM_CODE \n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                        "WHERE gst.MM_YYYY <= '" + month_date + "'\n" +
                        "GROUP BY zc.ZONE_NAME, cc.ZONE_CODE, cc.COMM_NAME\n" +
                        "ORDER BY score_of_subparameter9b DESC;";
                ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
                while(rsGst14aa.next()) {
                    String ra= RelevantAspect.GST9B_RA;
                    String zoneCode = rsGst14aa.getString("ZONE_CODE");
                    String commname=rsGst14aa.getString("COMM_NAME");
                    int col4_4=rsGst14aa.getInt("col4_4");
                    int col1_4=rsGst14aa.getInt("col1_4");
                    Double t_score = rsGst14aa.getDouble("score_of_subparameter9b");
                    int Zonal_rank = 0;
                    String gst = "no";

                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
//					int col4_2=rsGst14aa.getInt("col4_2");
//					int col4_4=rsGst14aa.getInt("col4_4");
                    String absval=String.valueOf(col4_4)+"/"+String.valueOf(col1_4);

//                    if((col1_4) != 0){
//                        total=(((double) (col4_4) * 100) /(col1_4));
//                    }

                    //rank=score.marks9b(total);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade =score.marks9b(totalScore);
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
     * updated: RKS, may 23, 2024
     * Purpose: This methods have core function in Return Filing .
     */

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
                String queryGst14aa = "SELECT \n" +
                        "    zc.ZONE_NAME, \n" +
                        "    cc.ZONE_CODE, \n" +
                        "    FORMAT(SUM(TAXPAYER_AUDITED_NO_LARGE + TAXPAYER_AUDITED_NO_MEDIUM + TAXPAYER_AUDITED_NO_SMALL), 2) AS col3,\n" +
                        "    FORMAT(SUM(TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL), 2) AS total,\n" +
                        "    CASE \n" +
                        "        WHEN SUM(TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL) != 0 \n" +
                        "        THEN (2 * SUM(TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL)) / 12.0 \n" +
                        "        ELSE 0.00 \n" +
                        "    END AS col2,\n" +
                        "    (SUM(TAXPAYER_AUDITED_NO_LARGE + TAXPAYER_AUDITED_NO_MEDIUM + TAXPAYER_AUDITED_NO_SMALL) / \n" +
                        "    CASE \n" +
                        "        WHEN SUM(TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL) != 0 \n" +
                        "        THEN (2 * SUM(TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL)) / 12.0 \n" +
                        "        ELSE 1 \n" +
                        "    END) * 100 AS total_score\n" +
                        "FROM \n" +
                        "    mis_gst_commcode AS cc \n" +
                        "RIGHT JOIN \n" +
                        "    mis_dga_gst_adt_2 AS c2 \n" +
                        "    ON cc.COMM_CODE = c2.COMM_CODE \n" +
                        "LEFT JOIN \n" +
                        "    mis_gst_zonecode AS zc \n" +
                        "    ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                        "WHERE \n" +
                        "    c2.MM_YYYY = '" + month_date + "'  -- Replace with the appropriate month_date\n" +
                        "GROUP BY \n" +
                        "    cc.ZONE_CODE\n" +
                        "ORDER BY \n" +
                        "    total_score DESC;\n";
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
                String queryGst14aa = "SELECT\n" +
                        "    zc.ZONE_NAME,\n" +
                        "    cc.ZONE_CODE,\n" +
                        "    cc.COMM_NAME,\n" +
                        "    (TAXPAYER_AUDITED_NO_LARGE + TAXPAYER_AUDITED_NO_MEDIUM + TAXPAYER_AUDITED_NO_SMALL) AS col3,\n" +
                        "    (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL) AS total,\n" +
                        "    CASE\n" +
                        "        WHEN (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL) != 0\n" +
                        "        THEN (2 * (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL)) / 12.0\n" +
                        "        ELSE 0.00\n" +
                        "    END AS col2,\n" +
                        "    ((TAXPAYER_AUDITED_NO_LARGE + TAXPAYER_AUDITED_NO_MEDIUM + TAXPAYER_AUDITED_NO_SMALL) /\n" +
                        "        CASE\n" +
                        "            WHEN (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL) != 0\n" +
                        "            THEN (2 * (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL)) / 12.0\n" +
                        "            ELSE 1\n" +
                        "        END\n" +
                        "    ) * 100 AS total_score\n" +
                        "FROM\n" +
                        "    mis_gst_commcode AS cc\n" +
                        "RIGHT JOIN\n" +
                        "    mis_dga_gst_adt_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "LEFT JOIN\n" +
                        "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "WHERE\n" +
                        "    14c.MM_YYYY =  '" + month_date + "'\n" +
                        "    AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "ORDER BY\n" +
                        "    total_score DESC\n" +
                        "LIMIT 0, 1000;\n";
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
                String queryGst14aa = "SELECT\n" +
                        "    zc.ZONE_NAME,\n" +
                        "    cc.ZONE_CODE,\n" +
                        "    cc.COMM_NAME,\n" +
                        "    (TAXPAYER_AUDITED_NO_LARGE + TAXPAYER_AUDITED_NO_MEDIUM + TAXPAYER_AUDITED_NO_SMALL) AS col3,\n" +
                        "    (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL) AS total,\n" +
                        "    CASE\n" +
                        "        WHEN (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL) != 0\n" +
                        "        THEN (2 * (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL)) / 12.0\n" +
                        "        ELSE 0.00\n" +
                        "    END AS col2,\n" +
                        "    ((TAXPAYER_AUDITED_NO_LARGE + TAXPAYER_AUDITED_NO_MEDIUM + TAXPAYER_AUDITED_NO_SMALL) /\n" +
                        "        CASE\n" +
                        "            WHEN (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL) != 0\n" +
                        "            THEN (2 * (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE + TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM + TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL)) / 12.0\n" +
                        "            ELSE 1\n" +
                        "        END\n" +
                        "    ) * 100 AS total_score\n" +
                        "FROM\n" +
                        "    mis_gst_commcode AS cc\n" +
                        "RIGHT JOIN\n" +
                        "    mis_dga_gst_adt_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE\n" +
                        "LEFT JOIN\n" +
                        "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "WHERE\n" +
                        "    14c.MM_YYYY = '" + month_date + "'\n" +
                        "ORDER BY\n" +
                        "    total_score DESC\n" +
                        "LIMIT 0, 1000;\n";
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
                String queryGst14aa =// new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date);
                        "SELECT cc.ZONE_CODE, zc.ZONE_NAME,\n" +
                        "SUM(14c.AUDIT_PARAS_BREAKUP_6_12_NO_LARGE + 14c.AUDIT_PARAS_BREAKUP_6_12_NO_MEDIUM + 14c.AUDIT_PARAS_BREAKUP_6_12_NO_SMALL) AS col36, \n" +
                        "SUM(14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_LARGE + 14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_MEDIUM + 14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_SMALL) AS col38, \n" +
                        "SUM(14c.AUDIT_PARAS_CLOSING_NO_LARGE + 14c.AUDIT_PARAS_CLOSING_NO_MEDIUM + 14c.AUDIT_PARAS_CLOSING_NO_SMALL) AS col30 \n" +
                        "FROM mis_gst_commcode AS cc \n" +
                        "RIGHT JOIN mis_dga_gst_adt_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                        "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
                        "WHERE 14c.MM_YYYY = '"+ month_date+"' \n" +
                        "GROUP BY cc.ZONE_CODE;";
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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col36 + col38)+"/"+String.valueOf(col30);
                    if(col30 != 0){
                        total=(((double) (col36 + col38) * 100) / (col30));
                    }else {
                        total = 0.00;
                    }

                    rank=score.marks10b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }

            } else if (type.equalsIgnoreCase("commissary")) { // Gst 10b
                String queryGst14aa=// new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date,zone_code);
                        "SELECT cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME, \n" +
                        "(14c.AUDIT_PARAS_BREAKUP_6_12_NO_LARGE + 14c.AUDIT_PARAS_BREAKUP_6_12_NO_MEDIUM + 14c.AUDIT_PARAS_BREAKUP_6_12_NO_SMALL) AS col36, \n" +
                        "(14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_LARGE + 14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_MEDIUM + 14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_SMALL) AS col38, \n" +
                        "(14c.AUDIT_PARAS_CLOSING_NO_LARGE + 14c.AUDIT_PARAS_CLOSING_NO_MEDIUM + 14c.AUDIT_PARAS_CLOSING_NO_SMALL) AS col30 \n" +
                        "FROM mis_gst_commcode AS cc \n" +
                        "RIGHT JOIN mis_dga_gst_adt_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                        "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "WHERE 14c.MM_YYYY = '"+ month_date+"' AND zc.ZONE_CODE = '" + zone_code + "';";
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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col36 + col38)+"/"+String.valueOf(col30);
                    if(col30 != 0){
                        total=(((double) (col36 + col38) * 100) / (col30));
                    }else {
                        total = 0.00;
                    }

                    rank=score.marks10b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) { // Gst 10b
                String queryGst14aa=// new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date);
                        "SELECT cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME, \n" +
                        "(14c.AUDIT_PARAS_BREAKUP_6_12_NO_LARGE + 14c.AUDIT_PARAS_BREAKUP_6_12_NO_MEDIUM + 14c.AUDIT_PARAS_BREAKUP_6_12_NO_SMALL) AS col36, \n" +
                        "(14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_LARGE + 14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_MEDIUM + 14c.AUDIT_PARAS_BREAKUP_MORE_1_YEAR_NO_SMALL) AS col38, \n" +
                        "(14c.AUDIT_PARAS_CLOSING_NO_LARGE + 14c.AUDIT_PARAS_CLOSING_NO_MEDIUM + 14c.AUDIT_PARAS_CLOSING_NO_SMALL) AS col30 \n" +
                        "FROM mis_gst_commcode AS cc \n" +
                        "RIGHT JOIN mis_dga_gst_adt_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE \n" +
                        "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE\n" +
                        "WHERE 14c.MM_YYYY = '"+ month_date+"';";
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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col36 + col38)+"/"+String.valueOf(col30);
                    if(col30 != 0){
                        total=(((double) (col36 + col38) * 100) / (col30));
                    }else {
                        total = 0.00;
                    }

                    rank=score.marks10b(total);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
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
    //  http://localhost:8080/cbicApi/cbic/gst11a?month_date=2023-04-01&type=zone
    //  http/localhost:8080/cbicApi/cbic/gst11a?month_date=2023-04-01&zone_code=70&type=commissary
    //	  http://localhost:8080/cbicApi/cbic/gst11a?month_date=2023-04-01&type=all_commissary
    public Object getGst11A(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;

        try {

            if (type.equalsIgnoreCase("zone")) {
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String queryTotalPending =// new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date);
                        "WITH cte AS (\n" +
                        "                        SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS col10 \n" +
                        "                        FROM mis_dla_gst_lgl_1 11a \n" +
                        "                        LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                        "                        LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                        "                        WHERE 11a.MM_YYYY = '"+ month_date+"' AND FORUM_CODE = 6 \n" +
                        "                        GROUP BY zc.ZONE_CODE), \n" +
                        "                        cte1 AS (\n" +
                        "                        SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4 \n" +
                        "                        FROM mis_dla_gst_lgl_1 11a \n" +
                        "                        LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                        "                        LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                        "                        WHERE 11a.MM_YYYY = '"+ prev_month_new+"' AND FORUM_CODE = 6 \n" +
                        "                        GROUP BY zc.ZONE_CODE) \n" +
                        "                        SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.col10, cte1.col4, ((cte.col10 / cte1.col4)) AS total_score \n" +
                        "                        FROM cte \n" +
                        "                        INNER JOIN cte1 ON cte.ZONE_CODE = cte1.ZONE_CODE\n" +
                        "                        order by total_score desc;";
                ResultSet rsTotalPending = GetExecutionSQL.getResult(queryTotalPending);

                while (rsTotalPending.next()) {
                    String ra=RelevantAspect.Gst11A_RA;
                    String zoneName = rsTotalPending.getString("ZONE_NAME");
                    String zoneCode = rsTotalPending.getString("ZONE_CODE");
                    double t_score = rsTotalPending.getDouble("total_score") * 100;
                    String commname = "ALL";
                    double col10 = rsTotalPending.getDouble("col10");
                    double col4 = rsTotalPending.getDouble("col4");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval=String.valueOf(col10)+"/"+String.valueOf(col4);
                    //total = (col4 != 0) ? (((col10 * 100) / col4) ) : 0;

                    //rank = score.marks11a(total,col10);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade =score.marks11a(totalScore,col10);
                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) { //gst 11a
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String queryTotalPending = // new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date,zone_code);
                        "WITH cte AS (SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n" +
                        "                        (DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS col10 \n" +
                        "                        FROM mis_dla_gst_lgl_1 AS 11a \n" +
                        "                        LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                        "                        LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                        "                        WHERE 11a.MM_YYYY = '"+ month_date+"' AND FORUM_CODE = 6 and zc.ZONE_CODE = '" + zone_code + "' ),cte1 AS ( \n" +
                        "                        SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n" +
                        "                        (DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4 \n" +
                        "                        FROM mis_dla_gst_lgl_1 AS 11a \n" +
                        "                        LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                        "                        LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE WHERE \n" +
                        "                        11a.MM_YYYY = '"+ prev_month_new+"' AND FORUM_CODE = 6 and zc.ZONE_CODE = '" + zone_code + "') \n" +
                        "                        SELECT cte.ZONE_NAME,cte.ZONE_CODE,cte.COMM_NAME,cte.COMM_CODE,cte.col10,cte1.col4, \n" +
                        "                        ((cte.col10 / cte1.col4) ) AS total_score FROM cte \n" +
                        "                        INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE\n" +
                        "                        order by total_score desc;";


                ResultSet rsTotalPending = GetExecutionSQL.getResult(queryTotalPending);

                while (rsTotalPending.next()) {
                    String ra = RelevantAspect.Gst11A_RA;
                    String zoneName = rsTotalPending.getString("ZONE_NAME");
                    String zoneCode = rsTotalPending.getString("ZONE_CODE");
                    String commname = rsTotalPending.getString("COMM_NAME");
                    double t_score = rsTotalPending.getDouble("total_score") * 100;
                    double col10 = rsTotalPending.getDouble("col10");
                    double col4 = rsTotalPending.getDouble("col4");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    total = (col4 != 0) ? ((col10 / col4)) : 0;
                    rank = score.marks11a(total, col10);
                    String absval = String.valueOf(col10) + "/" + String.valueOf(col4);

                    total = (col4 != 0) ? (col10 * 100 / col4) : 0;

                    rank = score.marks11a(total, col10);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade =score.marks11a(totalScore, col10);
                    gsta = new GST4A(rsTotalPending.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) { //gst 11a
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String queryTotalPending = // new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date);
                        "WITH cte AS (SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n" +
                        "                        (DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS col10 \n" +
                        "                        FROM mis_dla_gst_lgl_1 AS 11a \n" +
                        "                        LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                        "                        LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                        "                        WHERE 11a.MM_YYYY = '"+ month_date+"' AND FORUM_CODE = 6 ),cte1 AS ( \n" +
                        "                        SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, \n" +
                        "                        (DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4 \n" +
                        "                        FROM mis_dla_gst_lgl_1 AS 11a \n" +
                        "                        LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                        "                        LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE WHERE \n" +
                        "                        11a.MM_YYYY = '"+ prev_month_new+"' AND FORUM_CODE = 6 ) \n" +
                        "                        SELECT cte.ZONE_NAME,cte.ZONE_CODE,cte.COMM_NAME,cte.COMM_CODE,cte.col10,cte1.col4, \n" +
                        "                        ((cte.col10 / cte1.col4) ) AS total_score FROM cte \n" +
                        "                        INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE\n" +
                        "                        order by total_score desc;";

                ResultSet rsTotalPending = GetExecutionSQL.getResult(queryTotalPending);

                while (rsTotalPending.next()) {
                    String ra = RelevantAspect.Gst11A_RA;
                    String zoneName = rsTotalPending.getString("ZONE_NAME");
                    String zoneCode = rsTotalPending.getString("ZONE_CODE");
                    String commname = rsTotalPending.getString("COMM_NAME");
                    double t_score = rsTotalPending.getDouble("total_score") * 100;
                    double col10 = rsTotalPending.getDouble("col10");
                    double col4 = rsTotalPending.getDouble("col4");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    total = (col4 != 0) ? ((col10 * 100 / col4)) : 0;
                    rank = score.marks11a(total, col10);
                    String absval = String.valueOf(col10) + "/" + String.valueOf(col4);

                    total = (col4 != 0) ? (col10 / col4) : 0;

                    rank = score.marks11a(total, col10);
                    String formattedTotal = String.format("%.2f", t_score);
                    double totalScore = Double.parseDouble(formattedTotal);
                    int way_to_grade = score.marks11a(totalScore, col10);
                    gsta = new GST4A(rsTotalPending.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
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
    public Object getGst11B(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                // Query string
                String queryGst49 = // new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date,zone_code);
                        "WITH cte AS (\n" +
                        "    SELECT zc.ZONE_NAME,\n" +
                        "           zc.ZONE_CODE,\n" +
                        "           SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A,\n" +
                        "           SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A\n" +
                        "    FROM mis_dla_gst_lgl_1a AS 11a\n" +
                        "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                        "    WHERE 11a.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6\n" +
                        "    GROUP BY zc.ZONE_CODE\n" +
                        "),\n" +
                        "cte1 AS (\n" +
                        "    SELECT zc.ZONE_NAME,\n" +
                        "           zc.ZONE_CODE,\n" +
                        "           SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B,\n" +
                        "           SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B\n" +
                        "    FROM mis_dla_gst_lgl_1b AS 11b\n" +
                        "    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE\n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                        "    WHERE 11b.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6\n" +
                        "    GROUP BY zc.ZONE_CODE\n" +
                        ")\n" +
                        "SELECT cte.ZONE_NAME,\n" +
                        "       cte.ZONE_CODE,\n" +
                        "       cte.col10A,\n" +
                        "       cte.col12A,\n" +
                        "       cte1.col10B,\n" +
                        "       cte1.col12B,\n" +
                        "       (cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B) AS numerator,\n" +
                        "       (cte.col10A + cte1.col10B) AS denominator,\n" +
                        "       CASE \n" +
                        "           WHEN (cte.col10A + cte1.col10B) = 0 THEN NULL \n" +
                        "           ELSE (((cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B)) / (cte.col10A + cte1.col10B)) \n" +
                        "       END AS total_score \n" +
                        "FROM cte\n" +
                        "INNER JOIN cte1 ON cte.ZONE_CODE = cte1.ZONE_CODE\n" +
                        "ORDER BY total_score ASC;\n";


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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
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
                    gsta=new GST4A(rsGst46b.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) { //gst 11b
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String queryGst49 =// new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date,zone_code);
                        "WITH cte AS (\n" +
                        "    SELECT \n" +
                        "        zc.ZONE_NAME, \n" +
                        "        zc.ZONE_CODE, \n" +
                        "        cc.COMM_NAME, \n" +
                        "        cc.COMM_CODE, \n" +
                        "        (11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n" +
                        "        (11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A \n" +
                        "    FROM \n" +
                        "        mis_dla_gst_lgl_1a AS 11a\n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                        "    WHERE \n" +
                        "        11a.MM_YYYY = '" + month_date + "'\n" +
                        "        AND FORUM_CODE = 6 \n" +
                        "        AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "), \n" +
                        "cte1 AS (\n" +
                        "    SELECT \n" +
                        "        zc.ZONE_NAME, \n" +
                        "        zc.ZONE_CODE, \n" +
                        "        cc.COMM_NAME, \n" +
                        "        cc.COMM_CODE, \n" +
                        "        (11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, \n" +
                        "        (11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B \n" +
                        "    FROM \n" +
                        "        mis_dla_gst_lgl_1b AS 11b\n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE\n" +
                        "    LEFT JOIN \n" +
                        "        mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                        "    WHERE \n" +
                        "        11b.MM_YYYY = '" + month_date + "'\n" +
                        "        AND FORUM_CODE = 6 \n" +
                        "        AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        ") \n" +
                        "SELECT \n" +
                        "    cte.ZONE_NAME, \n" +
                        "    cte.ZONE_CODE, \n" +
                        "    cte.COMM_NAME, \n" +
                        "    cte.COMM_CODE, \n" +
                        "    cte.col10A, \n" +
                        "    cte.col12A, \n" +
                        "    cte1.col10B, \n" +
                        "    cte1.col12B, \n" +
                        "    (cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B) AS numerator, \n" +
                        "    (cte.col10A + cte1.col10B) AS denominator, \n" +
                        "    (((cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B)) / (cte.col10A + cte1.col10B)) AS total_score \n" +
                        "FROM \n" +
                        "    cte \n" +
                        "INNER JOIN \n" +
                        "    cte1 ON cte.COMM_CODE = cte1.COMM_CODE\n" +
                        "    order by total_score asc;\n";


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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
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
                    gsta=new GST4A(rsGst46b.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) { //gst 11b
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String queryGst49 = // new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date);
                        "WITH cte AS (\n" +
                        "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE,\n" +
                        "           (11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A,\n" +
                        "           (11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A\n" +
                        "    FROM mis_dla_gst_lgl_1a AS 11a\n" +
                        "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                        "    WHERE 11a.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6\n" +
                        "),\n" +
                        "cte1 AS (\n" +
                        "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE,\n" +
                        "           (11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B,\n" +
                        "           (11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B\n" +
                        "    FROM mis_dla_gst_lgl_1b AS 11b\n" +
                        "    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE\n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                        "    WHERE 11b.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6\n" +
                        ")\n" +
                        "SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_NAME, cte.COMM_CODE, \n" +
                        "       cte.col10A, cte.col12A, cte1.col10B, cte1.col12B,\n" +
                        "       (cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B) AS numerator,\n" +
                        "       (cte.col10A + cte1.col10B) AS denominator,\n" +
                        "       (((cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B)) / (cte.col10A + cte1.col10B)) AS total_score\n" +
                        "FROM cte\n" +
                        "INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE\n" +
                        "order by total_score asc;\n";


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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
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
    public Object getGst11C(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

        List<GST4A> allGstaList = new ArrayList<>();
        GST4A gsta = null;
        int rank = 0;
        double total = 0.00;

        try {
            if (type.equalsIgnoreCase("zone")) {
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);
                // Query string
                String queryGst49= // new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date);
                        "WITH cte AS (\n" +
                        "    SELECT zc.ZONE_NAME, zc.ZONE_CODE,\n" +
                        "           SUM(11a.DEPARTMENT_DISPOSAL_NO + 11a.TAXPAYER_DISPOSAL_NO) AS col10\n" +
                        "    FROM mis_dla_gst_lgl_1 AS 11a\n" +
                        "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                        "    WHERE 11a.MM_YYYY = '" + month_date + "' AND 11a.FORUM_CODE = 7\n" +
                        "    GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
                        "),\n" +
                        "cte1 AS (\n" +
                        "    SELECT zc.ZONE_NAME, zc.ZONE_CODE,\n" +
                        "           SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col4\n" +
                        "    FROM mis_dla_gst_lgl_1 AS 11a\n" +
                        "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                        "    WHERE 11a.MM_YYYY = '" + prev_month_new + "' AND 11a.FORUM_CODE = 7\n" +
                        "    GROUP BY zc.ZONE_NAME, zc.ZONE_CODE\n" +
                        ")\n" +
                        "SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.col10, cte1.col4,\n" +
                        "       (cte.col10 / NULLIF(cte1.col4, 0)) AS total_score\n" +
                        "FROM cte\n" +
                        "INNER JOIN cte1 ON cte.ZONE_CODE = cte1.ZONE_CODE\n" +
                        "order by total_score desc;\n";

                ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst49);

                while(rsGst46b.next()) {
                    String ra= RelevantAspect.Gst11C_RA;
                    String zoneCode = rsGst46b.getString("ZONE_CODE");
                    String commname="ALL";
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col10 = rsGst46b.getInt("col10");
                    int col4 = rsGst46b.getInt("col4");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;

                    if (col4 != 0){
                        total = rsGst46b.getDouble("total_score") * 100;
                    }else{
                        total = 0.00;
                    }

                    String absval = String.valueOf(col10) + "/" + String.valueOf(col4);


                    //total = (col4 != 0) ? ((col10 / col4) * 100) : 0;

                    rank = score.marks11c(total,col10);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta=new GST4A(rsGst46b.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) { // gst 11c
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                // Query string
                String queryGst49= // new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date,zone_code);
                        "WITH cte AS (\n" +
                        "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE,\n" +
                        "           (DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS col10\n" +
                        "    FROM mis_dla_gst_lgl_1 AS a\n" +
                        "    LEFT JOIN mis_gst_commcode AS cc ON a.COMM_CODE = cc.COMM_CODE\n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                        "    WHERE a.MM_YYYY = '" + month_date + "' AND a.FORUM_CODE = 7 AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        "), \n" +
                        "cte1 AS (\n" +
                        "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE,\n" +
                        "           (DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4\n" +
                        "    FROM mis_dla_gst_lgl_1 AS a\n" +
                        "    LEFT JOIN mis_gst_commcode AS cc ON a.COMM_CODE = cc.COMM_CODE\n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                        "    WHERE a.MM_YYYY = '" + prev_month_new + "' AND a.FORUM_CODE = 7 AND cc.ZONE_CODE = '" + zone_code + "'\n" +
                        ") \n" +
                        "SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_NAME, cte.COMM_CODE, cte.col10, cte1.col4,\n" +
                        "       (cte.col10 / cte1.col4) AS total_score\n" +
                        "FROM cte\n" +
                        "INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE\n" +
                        "order by total_score desc;\n";
                ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst49);

                while(rsGst46b.next()) {
                    String ra= RelevantAspect.Gst11C_RA;
                    String zoneCode = rsGst46b.getString("ZONE_CODE");
                    String commname=rsGst46b.getString("COMM_NAME");
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col10 = rsGst46b.getInt("col10");
                    int col4 = rsGst46b.getInt("col4");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col10) + "/" + String.valueOf(col4);

                    if (col4 != 0){
                        total = rsGst46b.getDouble("total_score") * 100;
                    }else{
                        total = 0.00;
                    }


                    rank = score.marks11c(total,col10);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
                    gsta=new GST4A(rsGst46b.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) { // gst 11c
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                // Query string
                String queryGst49=// new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date);
                        "WITH cte AS (\n" +
                        "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE,\n" +
                        "           (DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS col10\n" +
                        "    FROM mis_dla_gst_lgl_1 AS 11a\n" +
                        "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                        "    WHERE 11a.MM_YYYY =  '" + month_date + "' AND 11a.FORUM_CODE = 7\n" +
                        "),\n" +
                        "cte1 AS (\n" +
                        "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE,\n" +
                        "           (DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4\n" +
                        "    FROM mis_dla_gst_lgl_1 AS 11a\n" +
                        "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                        "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                        "    WHERE 11a.MM_YYYY = '" + prev_month_new + "'AND 11a.FORUM_CODE = 7\n" +
                        ")\n" +
                        "SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_NAME, cte.COMM_CODE, cte.col10, cte1.col4,\n" +
                        "       (cte.col10 / cte1.col4) AS total_score\n" +
                        "FROM cte\n" +
                        "INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE\n" +
                        "order by total_score desc;\n";
                ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst49);

                while(rsGst46b.next()) {
                    String ra= RelevantAspect.Gst11C_RA;
                    String zoneCode = rsGst46b.getString("ZONE_CODE");
                    String commname=rsGst46b.getString("COMM_NAME");
                    //String zoneName = rsGst14aa.getString("ZONE_NAME");
                    int col10 = rsGst46b.getInt("col10");
                    int col4 = rsGst46b.getInt("col4");
                    int Zonal_rank = 0;
                    String gst = "no";
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
                    String absval = String.valueOf(col10) + "/" + String.valueOf(col4);

                    if (col4 != 0){
                        total = rsGst46b.getDouble("total_score") * 100;
                    }else{
                        total = 0.00;
                    }


                    rank = score.marks11c(total,col10);
                    String formattedTotal = String.format("%.2f", total);
                    double totalScore = Double.parseDouble(formattedTotal);
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
                String queryGst49 =// new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date);
                        "WITH cte AS (  SELECT zc.ZONE_NAME,zc.ZONE_CODE,\n" +
                        "SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A,\n" +
                        "SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A\n" +
                        "FROM mis_dla_gst_lgl_1a AS 11a\n" +
                        "LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE\n" +
                        "LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                        "WHERE 11a.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 7\n" +
                        "GROUP BY zc.ZONE_CODE),\n" +
                        "cte1 AS (\n" +
                        "SELECT zc.ZONE_NAME,zc.ZONE_CODE,\n" +
                        "SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B,\n" +
                        "SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B\n" +
                        "FROM mis_dla_gst_lgl_1b AS 11b\n" +
                        "LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE\n" +
                        "LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE\n" +
                        "WHERE\n" +
                        "11b.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 7\n" +
                        "GROUP BY zc.ZONE_CODE)\n" +
                        "SELECT cte.ZONE_NAME,cte.ZONE_CODE,cte.col10A,cte.col12A,cte1.col10B,cte1.col12B,\n" +
                        "(cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B) AS numerator,\n" +
                        "(cte.col10A + cte1.col10B) AS denominator,\n" +
                        "(((cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B)) / (cte.col10A + cte1.col10B)) AS total_score\n" +
                        "FROM cte\n" +
                        "INNER JOIN cte1 ON cte.ZONE_CODE = cte1.ZONE_CODE\n" +
                        "order by total_score ;";
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
                    int sub_parameter_weighted_average = 0;
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
                    gsta=new GST4A(rsGst46b.getString("ZONE_NAME"),"All",totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("commissary")) {
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String queryGst49 = // new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date,zone_code);
                        "WITH cte AS (\n" +
                        "\tSELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME,cc.COMM_CODE, \n" +
                        "\t(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n" +
                        "\t(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A \n" +
                        "\tFROM mis_dla_gst_lgl_1a AS 11a \n" +
                        "\tLEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                        "\tLEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                        "\tWHERE 11a.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 7 and zc.ZONE_CODE = '" + zone_code + "'\n" +
                        "), \n" +
                        "cte1 AS ( \n" +
                        "         SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME,cc.COMM_CODE, \n" +
                        "         (11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, \n" +
                        "\t\t(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B \n" +
                        "          FROM mis_dla_gst_lgl_1b AS 11b \n" +
                        "          LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n" +
                        "          LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                        "          WHERE 11b.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 7 and zc.ZONE_CODE = '" + zone_code + "'\n" +
                        ") \n" +
                        "     SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_NAME,cte.COMM_CODE, cte.col10A, cte.col12A, cte1.col10B, cte1.col12B, \n" +
                        "     (cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B) AS numerator, \n" +
                        "      (cte.col10A + cte1.col10B) AS denominator, \n" +
                        "\t(((cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B)) / (cte.col10A + cte1.col10B)) AS total_score \n" +
                        "FROM cte INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE\n" +
                        "order by total_score ;";


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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
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
                    gsta=new GST4A(rsGst46b.getString("ZONE_NAME"),commname,totalScore,absval,zoneCode,ra,
                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
                    allGstaList.add(gsta);
                }
            }else if (type.equalsIgnoreCase("all_commissary")) {
                String prev_month_new = DateCalculate.getPreviousMonth(month_date);

                String queryGst49 =// new CGSTSubParameterWiseQuery().QueryFor_gst4d_CommissonaryWise(month_date);
                        "WITH cte AS (\n" +
                        "\tSELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME,cc.COMM_CODE, \n" +
                        "\t(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, \n" +
                        "\t(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A \n" +
                        "\tFROM mis_dla_gst_lgl_1a AS 11a \n" +
                        "\tLEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE \n" +
                        "\tLEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                        "\tWHERE 11a.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 7 \n" +
                        "), \n" +
                        "cte1 AS ( \n" +
                        "         SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME,cc.COMM_CODE, \n" +
                        "         (11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, \n" +
                        "\t\t(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B \n" +
                        "          FROM mis_dla_gst_lgl_1b AS 11b \n" +
                        "          LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE \n" +
                        "          LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE \n" +
                        "          WHERE 11b.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 7 \n" +
                        ") \n" +
                        "     SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_NAME,cte.COMM_CODE, cte.col10A, cte.col12A, cte1.col10B, cte1.col12B, \n" +
                        "     (cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B) AS numerator, \n" +
                        "      (cte.col10A + cte1.col10B) AS denominator, \n" +
                        "\t(((cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B)) / (cte.col10A + cte1.col10B)) AS total_score \n" +
                        "FROM cte INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE\n" +
                        "order by total_score ;";


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
                    int way_to_grade = 0;
                    int insentavization = 0;
                    int sub_parameter_weighted_average = 0;
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