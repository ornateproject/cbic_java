package com.ornates.cbic.controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

@CrossOrigin
@RequestMapping("/cbic")
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
	//  http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst1A(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {

		List<GST4A> allGstaList = new ArrayList<>();
		GST4A gsta = null;
		int rank = 0;
		double total = 0.00;


		try {
			if (type.equalsIgnoreCase("zone")) {
				// Query string
				String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE,sum(14c.DISPOSAL_OF_ARN_WITHIN_7) as col6, "
						+ "sum(14c.NO_OF_ARN_RECEIVED_GSTN) as col2, "
						+ "sum(14c.NO_OF_ARN_RECEIVED_CPC) as col4 "
						+ "FROM mis_gst_commcode as cc right join mis_dpm_gst_14a as 14c on cc.COMM_CODE=14c.COMM_CODE "
						+ "left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE "
						+ "where  14c.MM_YYYY='" + month_date + "'  group by ZONE_CODE;";
				//Result Set
				ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
				while (rsGst14aa.next()) {
					String ra = RelevantAspect.GST1A_RA;
					String commname = "ALL";
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					int col6 = rsGst14aa.getInt("col6");
					int col2 = rsGst14aa.getInt("col2");
					int col4 = rsGst14aa.getInt("col4");
					String absval=String.valueOf(col6)+"/"+String.valueOf(col2 + col4);

					if((col2 + col4) != 0) {
						total = ((double) col6 / (col2 + col4));
					}else {
						total = 0.00;
                    }

					rank = score.marks1a(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),"ALL",totalScore,rank,absval,zoneCode,ra);
					allGstaList.add(gsta);
				}
			}else if (type.equalsIgnoreCase("commissary")) {
				String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE,cc.COMM_NAME," +
						"(14c.DISPOSAL_OF_ARN_WITHIN_7) as col6, " +
						"(14c.NO_OF_ARN_RECEIVED_GSTN) as col2, " +
						"(14c.NO_OF_ARN_RECEIVED_CPC) as col4 FROM mis_gst_commcode as cc " +
						"right join mis_dpm_gst_14a as 14c on cc.COMM_CODE=14c.COMM_CODE " +
						"left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE " +
						"where  14c.MM_YYYY='" + month_date + "' and cc.ZONE_CODE = '" + zone_code + "';";

				ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
				while (rsGst14aa.next()) {
					String ra = RelevantAspect.GST1A_RA;
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					String commname = rsGst14aa.getString("COMM_NAME");
					//String zoneName = rsGst14aa.getString("ZONE_NAME");
					int col21 = rsGst14aa.getInt("col21");
					int col3 = rsGst14aa.getInt("col3");
					String absval = String.valueOf(col21) + "/" + String.valueOf(col3);

					total = ((double) col21 / col3);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					rank = score.marks2(total);
					gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore, rank, absval, zoneCode, ra);
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
	//  http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&type=zone
    //  http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst1B(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {

		List<GST4A> allGstaList = new ArrayList<>();
		GST4A gsta = null;
		int rank = 0;
		double total = 0.00;

		try {
			if (type.equalsIgnoreCase("zone")) {
				// Query string
				String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE,sum(14c.DISPOSAL_OF_ARN_PV_NOT_RECEIVED) as col10,"
						+ " sum(14c.DISPOSAL_OF_ARN_PV_RECOMMENDED) as col7 "
						+ "FROM mis_gst_commcode as cc right join mis_dpm_gst_14a as 14c on cc.COMM_CODE=14c.COMM_CODE"
						+ " left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE"
						+ " where  14c.MM_YYYY='" + month_date + "'  group by ZONE_CODE;";
				ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
				while (rsGst14aa.next()) {
					String ra = RelevantAspect.GST1B_RA;
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					String commname = "ALL";
					int col10=rsGst14aa.getInt("col10");
					int col7=rsGst14aa.getInt("col7");
					if(col7 != 0) {
						total = ((double) col10 / col7) * 100;
					}else{
						total = 0.00;
					}
					String absval = String.valueOf(col10) + "/" + String.valueOf(col7);

					rank = score.marks1b(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore, rank, absval, zoneCode, ra);
					allGstaList.add(gsta);
				}
			}else if (type.equalsIgnoreCase("commissary")) {
				String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE,cc.COMM_NAME, " +
						"(14c.DISPOSAL_OF_ARN_PV_NOT_RECEIVED) as col10, " +
						"(14c.DISPOSAL_OF_ARN_PV_RECOMMENDED) as col7 FROM mis_gst_commcode as cc " +
						"right join mis_dpm_gst_14a as 14c on cc.COMM_CODE=14c.COMM_CODE " +
						"left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE " +
						"where  14c.MM_YYYY='" + month_date + "'  and cc.ZONE_CODE = '" + zone_code + "';";
				ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
				while (rsGst14aa.next()) {
					String ra = RelevantAspect.GST1B_RA;
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					String commname = rsGst14aa.getString("COMM_NAME");
					int col10=rsGst14aa.getInt("col10");
					int col7=rsGst14aa.getInt("col7");
					String absval = String.valueOf(col10) + "/" + String.valueOf(col7);

					if(col7 != 0) {
						total = ((double) col10 / col7);
					}else{
						total = 0.00;
					}

					rank = score.marks1b(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore, rank, absval, zoneCode, ra);
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
	//  http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&type=zone
    //  http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst1C(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {

		List<GST4A> allGstaList = new ArrayList<>();
		GST4A gsta = null;
		int rank = 0;
		double total = 0.00;


		try {
			if (type.equalsIgnoreCase("zone")) {
				// Query string
				String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE,sum(14c.DISPOSAL_OF_ARN_DEEMED_REG) as col13," +
						"sum(14c.no_of_arn_received_gstn) as col2,sum(1) as col3,sum(14c.no_of_arn_received_cpc) as col4," +
						"sum(1) as col5 FROM  mis_gst_commcode as cc right join mis_dpm_gst_14a as 14c on cc.COMM_CODE=14c.COMM_CODE " +
						"left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE where MM_YYYY='" + month_date + "' group by ZONE_CODE;";

				//Result Set
				ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
				while (rsGst14aa.next()) {
					String ra= RelevantAspect.GST1C_RA;
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					String commname="ALL";
					int col13 = rsGst14aa.getInt("col13");
					int col2 = rsGst14aa.getInt("col2");
					int col3 = rsGst14aa.getInt("col3");
					int col4 = rsGst14aa.getInt("col4");
					int col5 = rsGst14aa.getInt("col5");
					String absval=String.valueOf(col13)+"/"+String.valueOf(col2 + col3 + col4 + col5);

					if((col2 + col3 + col4 + col5) != 0){
						total = ((double) (col13) / (col2 + col3 + col4 + col5));
					}else {
						total=0.00;
					}

					rank=score.marks1c(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,rank,absval,zoneCode,ra);
					allGstaList.add(gsta);
				}
			}else if (type.equalsIgnoreCase("commissary")) {
				String queryGst14aa= "SELECT zc.ZONE_NAME, cc.ZONE_CODE,cc.COMM_NAME, (14c.DISPOSAL_OF_ARN_DEEMED_REG) as col13, " +
						"(14c.no_of_arn_received_gstn) as col2, (1) as col3, (14c.no_of_arn_received_cpc) as col4, (1) as col5 " +
						"FROM  mis_gst_commcode as cc right join mis_dpm_gst_14a as 14c on cc.COMM_CODE=14c.COMM_CODE " +
						"left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE where MM_YYYY='2023-04-01' and zc.ZONE_CODE ='69';";

				ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
				while(rsGst14aa.next()) {
					String ra= RelevantAspect.GST1C_RA;
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					String commname=rsGst14aa.getString("COMM_NAME");
					int col13 = rsGst14aa.getInt("col13");
					int col2 = rsGst14aa.getInt("col2");
					int col3 = rsGst14aa.getInt("col3");
					int col4 = rsGst14aa.getInt("col4");
					int col5 = rsGst14aa.getInt("col5");
					String absval=String.valueOf(col13)+"/"+String.valueOf(col2 + col3 + col4 + col5);

					if((col2 + col3 + col4 + col5) != 0){
						total = ((double) (col13) / (col2 + col3 + col4 + col5));
					}else {
						total=0.00;
					}

					rank=score.marks1c(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,rank,absval,zoneCode,ra);
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
	 * updated: RKS, may 22, 2024
	 * Purpose: This methods have core function in Return Filing .
	 */

	@ResponseBody
	@RequestMapping(value = "/gst1d")
	//  http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst1D(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code) {

		List<GST4A> allGstaList = new ArrayList<>();
		GST4A gsta = null;
		int rank = 0;
		double total = 0.00;

		try {
			if (type.equalsIgnoreCase("zone")) {
				// Query string
				String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE, "
						+ "sum(14c.OPENING_BALANCE+14c.no_of_arn_received_gstn+14c.no_of_arn_received_gstn-14c.DISPOSAL_OF_ARN_DEEMED_REG) as col17, "
						+ "sum(14c.OPENING_BALANCE) as col1, "
						+ "sum(14c.no_of_arn_received_gstn) as col2, "
						+ "sum(14c.no_of_arn_received_gstn) as col3, "
						+ "sum(14c.no_of_arn_received_cpc) as col4, "
						+ "sum(14c.no_of_arn_received_cpc) as col5 "
						+ "FROM  mis_gst_commcode  cc right join mis_dpm_gst_14a  14c on cc.COMM_CODE=14c.COMM_CODE "
						+ " left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE  "
						+ " where MM_YYYY='" + month_date + "' group by ZONE_CODE";
				//Result Set
				ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
				while (rsGst14aa.next()) {
					String ra= RelevantAspect.GST1D_RA;
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					int col17 = rsGst14aa.getInt("col17");
					int col1 = rsGst14aa.getInt("col1");
					int col2 = rsGst14aa.getInt("col2");
					int col3 = rsGst14aa.getInt("col3");
					int col4 = rsGst14aa.getInt("col4");
					int col5 = rsGst14aa.getInt("col5");
					String absval=String.valueOf(col17)+"/"+String.valueOf((col1 + col2 + col3 + col4 + col5));


					if((col1 + col2 + col3 + col4 + col5) != 0) {
						total = ((double) col17 / (col1 + col2 + col3 + col4 + col5));
					}else{
						total = 0.00;
					}
					rank = score.marks1d(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),"ALL",totalScore,rank,absval,zoneCode,ra);
					allGstaList.add(gsta);
				}
			}else if (type.equalsIgnoreCase("commissary")) {
				String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE,cc.COMM_NAME,\n" +
						"(14c.OPENING_BALANCE+14c.no_of_arn_received_gstn+14c.no_of_arn_received_gstn-14c.DISPOSAL_OF_ARN_DEEMED_REG) as col17, \n" +
						"(14c.OPENING_BALANCE) as col1,\n" +
						"(14c.no_of_arn_received_gstn) as col2,\n" +
						"(14c.no_of_arn_received_gstn) as col3, \n" +
						"(14c.no_of_arn_received_cpc) as col4,\n" +
						"(14c.no_of_arn_received_cpc) as col5 \n" +
						"FROM  mis_gst_commcode  cc right join mis_dpm_gst_14a  14c on cc.COMM_CODE=14c.COMM_CODE \n" +
						"left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE  where MM_YYYY='"+ month_date+"' and zc.ZONE_CODE = '"+zone_code+"';";
				//Result Set
				ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
				while (rsGst14aa.next()) {
					String ra= RelevantAspect.GST1D_RA;
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					String commname=rsGst14aa.getString("COMM_NAME");
					int col17 = rsGst14aa.getInt("col17");
					int col1 = rsGst14aa.getInt("col1");
					int col2 = rsGst14aa.getInt("col2");
					int col3 = rsGst14aa.getInt("col3");
					int col4 = rsGst14aa.getInt("col4");
					int col5 = rsGst14aa.getInt("col5");
					String absval=String.valueOf(col17)+"/"+String.valueOf((col1 + col2 + col3 + col4 + col5));


					if((col1 + col2 + col3 + col4 + col5) != 0) {
						total = ((double) col17 / (col1 + col2 + col3 + col4 + col5));
					}else{
						total = 0.00;
					}
					rank = score.marks1d(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname, totalScore,rank,absval,zoneCode,ra);
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
	@RequestMapping(value = "/gst1e")
	//  http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&type=zone
//  http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst1E(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

		List<GST4A> allGstaList = new ArrayList<>();
		GST4A gsta = null;
		int rank = 0;
		double total = 0.00;

		try {
			if (type.equalsIgnoreCase("zone")) {

				// Query string
				String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE,sum(1+1) as col9,sum(15a.SUO_MOTO_OPENING_BALANCE) as col1," +
						"sum(15a.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION) as col2,sum(15a.CANCELLATION_OPENING_BALANCE) as col5," +
						"sum(15a.CANCELLATION_NO_APPLICATION_RECEIVED) as col6 FROM  mis_gst_commcode as cc " +
						"right join mis_dpm_gst_15a as 15a on cc.COMM_CODE=15a.COMM_CODE " +
						"left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE " +
						"where MM_YYYY='" + month_date + "' group by ZONE_CODE";

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
					String absval=String.valueOf(col9)+"/"+String.valueOf(col1 + col2 + col5 + col6);

					if((col1 + col2 + col5 + col6) != 0) {
						total = ((double) col9 / (col1 + col2 + col5 + col6));
					}else {
						total=0.00;
					}
					rank = score.marks1e(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,rank,absval,zoneCode,ra);
					allGstaList.add(gsta);
				}
			}else if (type.equalsIgnoreCase("commissary")) {
				// Query string
				String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE,cc.COMM_NAME,(1+1) as col9,(15a.SUO_MOTO_OPENING_BALANCE) as col1, " +
						"(15a.SUO_MOTO_GSTIN_LIABLE_FOR_CANCELLATION) as col2,(15a.CANCELLATION_OPENING_BALANCE) as col5, " +
						"(15a.CANCELLATION_NO_APPLICATION_RECEIVED) as col6 FROM  mis_gst_commcode as cc  " +
						"right join mis_dpm_gst_15a as 15a on cc.COMM_CODE=15a.COMM_CODE " +
						"left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE " +
						"where MM_YYYY='"+ month_date+"' and cc.ZONE_CODE='"+zone_code+"';";
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
					String absval=String.valueOf(col9)+"/"+String.valueOf(col1 + col2 + col5 + col6);

					if((col1 + col2 + col5 + col6) != 0) {
						total = ((double) col9 / (col1 + col2 + col5 + col6));
					}else {
						total=0.00;
					}
					rank = score.marks1e(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),"ALL",totalScore,rank,absval,zoneCode,ra);
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
	@RequestMapping(value = "/gst1f")
	//  http://localhost:8080/CbicAPI/cbic/gst1f?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/gst1f?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst1F(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code ) {

		List<GST4A> allGstaList = new ArrayList<>();
		GST4A gsta = null;
		int rank = 0;
		double total = 0.00;

		try {
			if (type.equalsIgnoreCase("zone")) {

				// Query string
				String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE,sum(1) as col14,sum(15a.REVOCATION_OPENING_BALANCE) as col10," +
						"sum(15a.REVOCATION_ARN_RECEIVED) as col11 FROM  mis_gst_commcode as cc " +
						"right join mis_dpm_gst_15a as 15a on cc.COMM_CODE=15a.COMM_CODE " +
						"left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE " +
						"where MM_YYYY='" + month_date + "' group by ZONE_CODE";

				//Result Set
				ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);

				while (rsGst14aa.next()) {
					String ra= RelevantAspect.GST1F_RA;
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					int col14 = rsGst14aa.getInt("col14");
					int col10 = rsGst14aa.getInt("col10");
					int col11 = rsGst14aa.getInt("col11");
					String absval=String.valueOf(col14)+"/"+String.valueOf(col10 + col11);

					if ((col10 + col11) != 0) {
						total = ((double) col14 / (col10 + col11));
					}else{
						total=0.00;
					}

					rank = score.marks1f(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),"ALL",totalScore,rank,absval,zoneCode,ra);
					allGstaList.add(gsta);
				}
			}else if (type.equalsIgnoreCase("commissary")) {//gst1f-commissary
				String queryGst14aa ="SELECT zc.ZONE_NAME, cc.ZONE_CODE,cc.COMM_NAME,(1) as col14,(15a.REVOCATION_OPENING_BALANCE) as col10, (15a.REVOCATION_ARN_RECEIVED) as col11 FROM  mis_gst_commcode as cc  right join mis_dpm_gst_15a as 15a on cc.COMM_CODE=15a.COMM_CODE  left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE where MM_YYYY='" + month_date + "' and cc.ZONE_CODE='"+zone_code+"';";

				//Result Set
				ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

				while (rsGst14aa.next()) {
					String ra = RelevantAspect.GST1F_RA;
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					String commname=rsGst14aa.getString("COMM_NAME");
					String zonename = rsGst14aa.getString("ZONE_NAME");
					int col14 = rsGst14aa.getInt("col14");
					int col10 = rsGst14aa.getInt("col10");
					int col11 = rsGst14aa.getInt("col11");
					String absval = String.valueOf(col14) + "/" + String.valueOf(col10 + col11);

					if ((col10 + col11) != 0) {
						total = ((double) col14 / (col10 + col11));
					} else {
						total = 0.00;
					}

					rank = score.marks1f(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta = new GST4A(zonename, commname, totalScore, rank, absval, zoneCode, ra);
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
	 * Purpose: This methods have core function in Return Filing .
	 */	

	@ResponseBody
	@RequestMapping(value = "/gst2")
	//  http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst2(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

		List<GST4A> allGstaList = new ArrayList<>();
		GST4A gsta = null;
		int rank = 0;
		double total = 0.00;


			try {
				// Query string
				if (type.equalsIgnoreCase("zone")) {
					String queryGst14aa =
							"SELECT " +
									"    zc.ZONE_NAME, " +
									"    cc.COMM_NAME, " +
									"    cc.ZONE_CODE, " +
									"    SUM(14c.GSTR_3BM_D) AS col21, " +
									"    SUM(14c.GSTR_3BM_F) AS col3 " +
									"FROM " +
									"    mis_gst_commcode AS cc " +
									"RIGHT JOIN " +
									"    mis_gst_gst_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
									"LEFT JOIN " +
									"    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
									"WHERE " +
									"    14c.MM_YYYY = '" + month_date + "' " +
									"GROUP BY " +
									"   cc.ZONE_CODE ;";
					ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);

					while(rsGst14aa.next()) {
						String ra= RelevantAspect.Gst2_RA;
						String zoneCode = rsGst14aa.getString("ZONE_CODE");
						String commname= "ALL";
						//String zoneName = rsGst14aa.getString("ZONE_NAME");
						int col21=rsGst14aa.getInt("col21");
						int col3=rsGst14aa.getInt("col3");
						String absval=String.valueOf(col21)+"/"+String.valueOf(col3);


						total=((double) col21 / col3);

						//}
						rank=score.marks2(total);
						String formattedTotal = String.format("%.2f", total);
						double totalScore = Double.parseDouble(formattedTotal);
						gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,rank,absval,zoneCode,ra);
						allGstaList.add(gsta);
					}

				} else if (type.equalsIgnoreCase("commissary")) {
					String queryGst14aa="SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,14c.GSTR_3BM_D as col21,14c.GSTR_3BM_F as col3 "
							+ "FROM  mis_gst_commcode as cc right join mis_gst_gst_2 as 14c on cc.COMM_CODE=14c.COMM_CODE "
							+ "left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE "
							+ "where  14c.MM_YYYY='"+ month_date+"' and zc.ZONE_CODE='"+zone_code+"';";
					ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
					while(rsGst14aa.next()) {
						String ra=RelevantAspect.Gst2_RA;
						String zoneCode = rsGst14aa.getString("ZONE_CODE");
						String commname=rsGst14aa.getString("COMM_NAME");
						//String zoneName = rsGst14aa.getString("ZONE_NAME");
						int col21=rsGst14aa.getInt("col21");
						int col3=rsGst14aa.getInt("col3");
						String absval=String.valueOf(col21)+"/"+String.valueOf(col3);
						if (col3 != 0) {
						total=((double) col21 / col3);}
						String formattedTotal = String.format("%.2f", total);
						double totalScore = Double.parseDouble(formattedTotal);
						//}
						rank=score.marks2(total);
						gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,rank,absval,zoneCode,ra);
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
	@RequestMapping(value = "/gst3a")
	//http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst3A(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

		List<GST4A> allGstaList = new ArrayList<>();
		GST4A gsta = null;
		int rank = 0;
		double total = 0.00;

		try {
			if (type.equalsIgnoreCase("zone")) {
			// Query string
			String queryGst14aa="SELECT zc.ZONE_NAME, cc.ZONE_CODE, SUM(14c.SCRUTINIZED_ASMT_10) AS col6, " +
					"SUM(14c.SCRUTINIZED_DISCRIPANCY_FOUND) AS col5, " +
					"SUM(14c.RETURN_SCRUTINY) AS col3 FROM mis_gst_commcode as cc " +
					"RIGHT JOIN mis_dggst_gst_scr_1 as 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
					"LEFT JOIN mis_gst_zonecode as zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
					"WHERE  14c.MM_YYYY = '" + month_date + "' " +
					"GROUP BY  cc.ZONE_CODE";

			String prev_month_new =DateCalculate.getPreviousMonth(month_date);

			String queryGst3aa="SELECT zc.ZONE_NAME,cc.ZONE_CODE, SUM(14c.CLOSING_NO) AS col2 FROM mis_gst_commcode as cc " +
					"RIGHT JOIN mis_dggst_gst_scr_1 as 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
					"LEFT JOIN mis_gst_zonecode as zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
					"WHERE 14c.MM_YYYY = '" + prev_month_new + "' " +
					"GROUP BY cc.ZONE_CODE";
			//Result Set
			ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
			ResultSet rsGst3aa=GetExecutionSQL.getResult(queryGst3aa);
			while(rsGst14aa.next() && rsGst3aa.next()) {
				String ra = RelevantAspect.Gst3A_RA;
				String zoneCode = rsGst14aa.getString("ZONE_CODE");
				int col6 = rsGst14aa.getInt("col6");
				int col5 = rsGst14aa.getInt("col5");
				int col3 = rsGst14aa.getInt("col3");
				int col2 = rsGst3aa.getInt("col2");
				String absval = String.valueOf(col6 + col5) + "/" + String.valueOf(col3 + col2);
				if (col3 + col2 != 0) {
					total = ((double) (col6 + col5) / (col3 + col2));
				}
				rank = score.marks3a(total);
				String formattedTotal = String.format("%.2f", total);
				double totalScore = Double.parseDouble(formattedTotal);
				gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", totalScore, rank, absval, zoneCode, ra);
				allGstaList.add(gsta);
			}
			}else if (type.equalsIgnoreCase("commissary")) {
				// Query string
				String queryGst14aa="SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 14c.SCRUTINIZED_ASMT_10 AS col6, " +
						"14c.SCRUTINIZED_DISCRIPANCY_FOUND AS col5, " +
						"14c.RETURN_SCRUTINY AS col3 FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "'";

				String prev_month_new =DateCalculate.getPreviousMonth(month_date);

				String queryGst3aa= "SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 14c.CLOSING_NO AS col2 FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "'";

				//Result Set
				ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
				ResultSet rsGst3aa=GetExecutionSQL.getResult(queryGst3aa);
				while(rsGst14aa.next() && rsGst3aa.next()) {
					String commname=rsGst14aa.getString("COMM_NAME");
					String ra=RelevantAspect.Gst3A_RA;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					int col6=rsGst14aa.getInt("col6");
					int col5=rsGst14aa.getInt("col5");
					int col3=rsGst14aa.getInt("col3");
					int col2=rsGst3aa.getInt("col2");
					String absval = String.valueOf(col6 + col5) + "/" + String.valueOf(col3 + col2);
					if ((col3+col2) != 0){
						total =((double) (col6+col5)/(col3+col2));
					}

					rank=score.marks3a(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);

					gsta = new GST4A(zoneName, commname, (Double)totalScore, rank, absval, zoneCode,ra);
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
	@RequestMapping(value = "/gst3b")
	//  http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&type=zone
    //  http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst3B(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

		List<GST4A> allGstaList = new ArrayList<>();
		GST4A gsta = null;
		int rank = 0;
		double total = 0.00;
		//Connection done
		Connection con= JDBCConnection.getTNConnection();

		try {
			if (type.equalsIgnoreCase("zone")) {
				// Query string
				String queryGst14aa = "SELECT zc.ZONE_NAME,cc.ZONE_CODE, sum(14c.amount_recovered_penalty) AS col22, "
						+ "sum(14c.tax_liability_detectect) AS col14 "
						+ "FROM mis_gst_commcode AS cc "
						+ "RIGHT JOIN mis_dggst_gst_scr_2a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE "
						+ "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE "
						+ "WHERE 14c.MM_YYYY ='" + month_date + "' GROUP BY cc.ZONE_CODE;";

				PreparedStatement psGst14aa = con.prepareStatement(queryGst14aa);
//				PreparedStatement psGst3aa=con.prepareStatement(queryGst3aa);

				//Result Set
				ResultSet rsGst14aa = psGst14aa.executeQuery();


				while (rsGst14aa.next()) {
					String ra = RelevantAspect.Gst3B_RA;
					//String zoneName = rsGst14aa.getString("ZONE_NAME");
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					int col22 = rsGst14aa.getInt("col22");
					int col14 = rsGst14aa.getInt("col14");
					if (col14 != 0) {
						total = ((double) col22 / col14);
					}
					rank = score.marks3b(total);
					String absval = String.valueOf(col22) + "/" + String.valueOf(col14);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", (Double) totalScore, rank, absval, zoneCode, ra);
					allGstaList.add(gsta);
				}
			}else if (type.equalsIgnoreCase("commissary")) {

					// Query string
					String queryGst14aa="SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 14c.amount_recovered_penalty AS col22, "
							+"14c.tax_liability_detectect AS col14 "
							+"FROM mis_gst_commcode AS cc "
							+"RIGHT JOIN mis_dggst_gst_scr_2a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE "
							+ "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE "
							+ "WHERE 14c.MM_YYYY ='" +month_date+"' AND zc.ZONE_CODE = '"+ zone_code +"';";

					//Prepared Statement
					PreparedStatement psGst14aa=con.prepareStatement(queryGst14aa);
					//Result Set
					ResultSet rsGst14aa= psGst14aa.executeQuery();

					while(rsGst14aa.next() ) {
						String commname=rsGst14aa.getString("COMM_NAME");
						String ra=RelevantAspect.Gst3B_RA;
						String zoneName = rsGst14aa.getString("ZONE_NAME");
						String zoneCode = rsGst14aa.getString("ZONE_CODE");
						int col22=rsGst14aa.getInt("col22");
						int col14=rsGst14aa.getInt("col14");
						String absval=String.valueOf(col22)+"/"+String.valueOf(col14);
						if (col14 != 0){
							total =((double) col22/col14);
						}
						rank=score.marks3b(total);
						String formattedTotal = String.format("%.2f", total);
						double totalScore = Double.parseDouble(formattedTotal);
						gsta=new GST4A(zoneName, commname,totalScore, rank, absval, zoneCode,ra);
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
	//  http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst4A(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code ) {

		List<GST4A> allGstaList = new ArrayList<>();
		GST4A gsta = null;
		int rank = 0;
		double total = 0.00;


		try {
			if (type.equalsIgnoreCase("zone")) {
				// Query string

				// Query string
				String queryGst14aa="SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, " +
						"(14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO) as col13, " +
						"14c.OPENING_BALANCE_NO as col1 " +
						"FROM mis_gst_commcode as cc " +
						"RIGHT JOIN mis_gi_gst_2 as 14c on cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode as zc on zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE 14c.MM_YYYY = '"+ month_date+"' ;";

				//Result Set
				ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

				while(rsGst14aa.next()) {
					String commname=rsGst14aa.getString("COMM_NAME");
					String ra=RelevantAspect.GST4A_RA;
					String zoneCode = rsGst14aa.getString("ZONE_CODE");

					int col13=rsGst14aa.getInt("col13");
					int col1=rsGst14aa.getInt("col1");
					String absval=String.valueOf(col13)+"/"+String.valueOf(col1);

					if(col1!=0) {
						total=((double) (col13) / col1);
					}
					//}
					rank=score.marks4a(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,rank,absval,zoneCode,ra);
					allGstaList.add(gsta);
				}

			} else if (type.equalsIgnoreCase("commissary")) {
				// Query string
				String queryGst14aa="SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, " +
						"(14c.SCN_NO + 14c.VOLUNTARY_NO + 14c.MERIT_NO + 14c.TRANSFER_NO) as col13, " +
						"14c.OPENING_BALANCE_NO as col1 " +
						"FROM mis_gst_commcode as cc " +
						"RIGHT JOIN mis_gi_gst_2 as 14c on cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode as zc on zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE 14c.MM_YYYY = '"+ month_date+"' AND zc.ZONE_CODE = '"+zone_code+"';";
				ResultSet rsGst14aa =GetExecutionSQL.getResult(queryGst14aa);
				while(rsGst14aa.next()) {
					String commname=rsGst14aa.getString("COMM_NAME");
					String ra=RelevantAspect.GST4A_RA;
					String zoneCode = rsGst14aa.getString("ZONE_CODE");

					int col13=rsGst14aa.getInt("col13");
					int col1=rsGst14aa.getInt("col1");
					String absval=String.valueOf(col13)+"/"+String.valueOf(col1);

					if(col1!=0) {
						total=((double) (col13) / col1);
					}
					//}
					rank=score.marks4a(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,rank,absval,zoneCode,ra);
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
	public Object getGst4B(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code ) {

		List<GST4A> allGstaList = new ArrayList<>();
		GST4A gsta = null;
		int rank = 0;
		double total = 0.00;
		//Connection done
		Connection con= JDBCConnection.getTNConnection();


		// Query string
//		String queryGst14a="SELECT *,cc.ZONE_CODE,cc.COMM_NAME,zc.ZONE_NAME FROM  mis_gst_commcode as cc right join mis_gi_gst_2 as 2c on cc.COMM_CODE=2c.COMM_CODE left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE where 2c.MM_YYYY='"+month_date+"'";
//
//		//Prepared Statement
//		PreparedStatement psGst14a;
//
//		//Result Set
//		ResultSet rsGst14a=null;

		try {
			if (type.equalsIgnoreCase("zone")) {

//			psGst14a=con.prepareStatement(queryGst14a);
//
//			//executing the query
//			rsGst14a = psGst14a.executeQuery();
//
//			while(rsGst14a.next()) {
//
//				int zone_code=rsGst14a.getInt("ZONE_CODE");
				//double total=0;
				//int rank=0;

				// Query string
				String queryGst14aa="SELECT zc.ZONE_NAME, cc.ZONE_CODE,sum(1) as col29,sum(2c.MORE_THAN_2_NO) as col31,sum(2c.MORE_THAN_2_NO) as col25 FROM  mis_gst_commcode as cc right join mis_gi_gst_2 as 2c on cc.COMM_CODE=2c.COMM_CODE left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE where MM_YYYY='"+month_date+"' group by ZONE_CODE";

				//Prepared Statement
				PreparedStatement psGst14aa=con.prepareStatement(queryGst14aa);

				//Result Set
				ResultSet rsGst14aa= psGst14aa.executeQuery();
				while(rsGst14aa.next()) {
					String ra=RelevantAspect.Gst4B_RA;
					//String zoneName = rsGst14aa.getString("ZONE_NAME");
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					int col29=rsGst14aa.getInt("col29");
					int col31=rsGst14aa.getInt("col31");
					int col25=rsGst14aa.getInt("col25");
					//String absval=String.valueOf(col29+32)+"/"+String.valueOf(col25);
					if(col25!=0) {
						total=((double) (col29+col31) / col25);
					}
					//}
					rank=score.marks4b(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					String absval = String.valueOf(col29+col31) + "/" + String.valueOf(col25);
					gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", (Double) totalScore, rank, absval, zoneCode,ra);
					//gsta=new Gst(rsGst14aa.getString("ZONE_NAME"),"ALL",(int)total,rank);
					//gsta = new GST4A(zoneName, "ALL", (int) total, rank, absval, zoneCode,ra);
					allGstaList.add(gsta);
				}
			} else if (type.equalsIgnoreCase("commissary")) {
				// Query string
				String queryGst14aa="SELECT zc.ZONE_NAME,cc.COMM_NAME, cc.ZONE_CODE,(1) as col29,(2c.MORE_THAN_2_NO) as col31,(2c.MORE_THAN_2_NO) as col25 FROM  mis_gst_commcode as cc right join mis_gi_gst_2 as 2c on cc.COMM_CODE=2c.COMM_CODE left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE where 2c.MM_YYYY='"+month_date+"'  AND zc.ZONE_CODE = '"+zone_code+"';";

				//Prepared Statement
				PreparedStatement psGst14aa=con.prepareStatement(queryGst14aa);

				//Result Set
				ResultSet rsGst14aa= psGst14aa.executeQuery();
				while(rsGst14aa.next()) {
					String commname=rsGst14aa.getString("COMM_NAME");
					String ra=RelevantAspect.Gst4B_RA;
					//String zoneName = rsGst14aa.getString("ZONE_NAME");
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					int col29=rsGst14aa.getInt("col29");
					int col31=rsGst14aa.getInt("col31");
					int col25=rsGst14aa.getInt("col25");
					String absval = String.valueOf(col29+col31) + "/" + String.valueOf(col25);
					// if(col25!=0) {
					total=((double) (col29+col31) / col25);
					//}
					//}
					rank=score.marks4b(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);

					gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,(Double)totalScore,rank,absval,zoneCode,ra);

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
	@RequestMapping(value = "/gst4c")
	public Object getGst4AC(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code) {

		List<GST4A> allGstaList=new ArrayList<>();
		GST4A gsta=null;
		int rank = 0;
		double total = 0.00;
		// Query string
//		//String queryGst14a="SELECT cc.ZONE_NAME, cc.COMM_NAME, zc.ZONE_CODE FROM mis_gst_commcode as cc " +
//	            "RIGHT JOIN mis_dggst_gst_scr_1 as 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
//	            "LEFT JOIN mis_gst_zonecode as zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
//	            "WHERE 14c.MM_YYYY = '" + month_date + "'";

		try {
			if (type.equalsIgnoreCase("zone")) {
				//ResultSet rsGst14a =GetExecutionSQL.getResult(queryGst14a);
				//while(rsGst14a.next()) {
				//String zonecode=rsGst14a.getString("ZONE_CODE");


				// Query string
				String queryGst14aa="SELECT zc.ZONE_NAME, cc.ZONE_CODE, 14c.MOVING_TARGET_CESS+sum(14c.MOVING_TARGET_CGST+14c.MOVING_TARGET_IGST+14c.MOVING_TARGET_SGST) AS col8 " +

						"FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_ddm_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE  14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE;";

				String prev_month_new =DateCalculate.getPreviousMonth(month_date);

				String queryGst3aa= "SELECT zc.ZONE_NAME, cc.ZONE_CODE, sum(14c.DETECTION_CGST_AMT+14c.DETECTION_SGST_AMT+14c.DETECTION_IGST_AMT+14c.DETECTION_CESS_AMT) AS col6_1, sum(14c.DETECTION_CGST_AMT+14c.DETECTION_SGST_AMT+14c.DETECTION_IGST_AMT+14c.DETECTION_CESS_AMT) AS col6_2 FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_gi_gst_1  AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE 14c.MM_YYYY = '" + prev_month_new + "' GROUP BY cc.ZONE_CODE;";

				//Result Set
				ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
				ResultSet rsGst3aa=GetExecutionSQL.getResult(queryGst3aa);
				while(rsGst14aa.next() && rsGst3aa.next()) {
					String ra=RelevantAspect.Gst4C_RA;
					//String zoneName = rsGst14aa.getString("ZONE_NAME");
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					int col8=rsGst14aa.getInt("col8");
					//int col5=rsGst14aa.getInt("col5");
					//int col3=rsGst14aa.getInt("col3");
					int col6_1=rsGst3aa.getInt("col6_1");
					int col6_2=rsGst3aa.getInt("col6_2");
					String absval = String.valueOf(col6_1+ col6_2) + "/" + String.valueOf(col8);
					if ((col8) != 0){
						total =((double) (col6_1+col6_1)/(col8));
					}
					else{
						total=0;
					}
					//String absval=String.valueOf(col6+col5)+"/"+String.valueOf(col3+col2);
					//int col2=0;
					//if(rsGst3aa.next()) {
					//col2=rsGst3aa.getInt("col2");
//						String absval=String.valueOf(col6+col5)+"/"+String.valueOf(col3+col2);
//						 String zoneCode = rsGst14aa.getString("ZONE_CODE");
					//}

					// total=((double) (col6+col5)/ (col3+col2));
					//}
					rank=score.marks4c(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					//gsta=new Gst(rsGst14aa.getString("ZONE_NAME"),"ALL",(int)total,rank,absval,zoneCode);

					gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", (Double)totalScore, rank, absval, zoneCode,ra);
					allGstaList.add(gsta);
				}
			} else if (type.equalsIgnoreCase("commissary")) {
				// Query string
				String queryGst14aa="SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 14c.MOVING_TARGET_CESS+14c.MOVING_TARGET_CGST+14c.MOVING_TARGET_IGST+14c.MOVING_TARGET_SGST AS col8 " +

						"FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_ddm_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "';";

				String prev_month_new =DateCalculate.getPreviousMonth(month_date);

				String queryGst3aa= "SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 14c.DETECTION_CGST_AMT+14c.DETECTION_SGST_AMT+14c.DETECTION_IGST_AMT+14c.DETECTION_CESS_AMT AS col6_1, 14c.DETECTION_CGST_AMT+14c.DETECTION_SGST_AMT+14c.DETECTION_IGST_AMT+14c.DETECTION_CESS_AMT AS col6_2 FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_gi_gst_1  AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + prev_month_new + "';";

				//Result Set
				ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
				ResultSet rsGst3aa=GetExecutionSQL.getResult(queryGst3aa);
				while(rsGst14aa.next() && rsGst3aa.next()) {
					String commname=rsGst14aa.getString("COMM_NAME");
					String ra=RelevantAspect.Gst4C_RA;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					int col8=rsGst14aa.getInt("col8");
					//int col5=rsGst14aa.getInt("col5");
					//int col3=rsGst14aa.getInt("col3");
					int col6_1=rsGst3aa.getInt("col6_1");
					int col6_2=rsGst3aa.getInt("col6_2");
					String absval = String.valueOf(col6_1+ col6_2) + "/" + String.valueOf(col8);
					if ((col8) != 0){
						total =((double) (col6_1+col6_1)/(col8));
					}
					else{
						total=0;
					}
					//String absval=String.valueOf(col6+col5)+"/"+String.valueOf(col3+col2);
					//int col2=0;
					//if(rsGst3aa.next()) {
					//col2=rsGst3aa.getInt("col2");
//						String absval=String.valueOf(col6+col5)+"/"+String.valueOf(col3+col2);
//						 String zoneCode = rsGst14aa.getString("ZONE_CODE");
					//}

					// total=((double) (col6+col5)/ (col3+col2));
					//}
					rank=score.marks4c(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					//gsta=new Gst(rsGst14aa.getString("ZONE_NAME"),"ALL",(int)total,rank,absval,zoneCode);

					gsta = new GST4A(zoneName, commname, (Double)totalScore, rank, absval, zoneCode,ra);
					allGstaList.add(gsta);
					//allGstaList.add(gsta);
				}
			}
			//}

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
	public Object getGst4d(@RequestParam String month_date ,@RequestParam String type, @RequestParam(required = false) String zone_code) {

		List<GST4A> allGstaList=new ArrayList<>();
		GST4A gsta=null;
		int rank = 0;
		double total = 0.00;
		// Query string
		//String queryGst14a="SELECT cc.ZONE_CODE,cc.COMM_NAME,zc.ZONE_NAME FROM  mis_gst_commcode as cc right join mis_gi_gst_1 as 14c on cc.COMM_CODE=14c.COMM_CODE left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE where 14c.MM_YYYY='"+month_date+"'";
		try {
			if (type.equalsIgnoreCase("zone")) {
				//ResultSet rsGst14a =GetExecutionSQL.getResult(queryGst14a);
				//while(rsGst14a.next()) {
				String prev_month_new =DateCalculate.getPreviousMonth(month_date);
				//int zone_code=rsGst14a.getInt("ZONE_CODE");


				// Query string
				String queryGst46a="SELECT " +
						"cc.ZONE_CODE, " +
						"zc.ZONE_NAME, " +
						"sum(14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_1 " +
						"FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE;";


				String queryGst46b= "SELECT " +
						"cc.ZONE_CODE, " +
						"zc.ZONE_NAME, " +
						"SUM(" +
						"    14c.REALISATION_CGST_AMT_PRE + " +
						"    14c.REALISATION_IGST_AMT_PRE + " +
						"    14c.REALISATION_SGST_AMT_PRE + " +
						"    14c.REALISATION_CESS_AMT_PRE " +
						") AS col6_2 " +
						"FROM " +
						"    mis_gst_commcode AS cc " +
						"RIGHT JOIN " +
						"    mis_gi_gst_1 AS 14c " +
						"    ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN " +
						"    mis_gst_zonecode AS zc " +
						"    ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE 14c.MM_YYYY = '"+prev_month_new+"' " +
						"GROUP BY cc.ZONE_CODE;";

				String queryGst6c= "SELECT "
						+ "    cc.ZONE_CODE, "
						+ "    zc.ZONE_NAME, "
						+ "    SUM(14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_3 "
						+ "FROM mis_gst_commcode AS cc "
						+ "RIGHT JOIN "
						+ "    mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE "
						+ "LEFT JOIN "
						+ "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE "
						+ "WHERE "
						+ "    14c.MM_YYYY = '" + month_date + "' " // Use the monthDate variable here
						+ "GROUP BY "
						+ "    cc.ZONE_CODE; ";


				String queryGst6d="SELECT " +
						"cc.ZONE_CODE, " +
						"zc.ZONE_NAME, " +
						"SUM(14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_4 " +
						"FROM " +
						"mis_gst_commcode AS cc " +
						"RIGHT JOIN " +
						"mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN " +
						"mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE " +
						"14c.MM_YYYY = '"+prev_month_new+"' " +
						"GROUP BY " +
						"cc.ZONE_CODE;";

				ResultSet rsGst46a =GetExecutionSQL.getResult(queryGst46a);
				ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst46b);
				ResultSet rsGst6c =GetExecutionSQL.getResult(queryGst6c);
				ResultSet rsGst6d =GetExecutionSQL.getResult(queryGst6d);

				//System.out.println("rsGst46a:="+rsGst46a+"\nrsGst46b:="+rsGst46b+"\nrsGst48:="+rsGst48);
				while(rsGst46a.next() && rsGst46b.next() && rsGst6c.next() && rsGst6d.next() ) {
					String ra=RelevantAspect.Gst4D_RA;
					String zoneCode = rsGst46a.getString("ZONE_CODE");
					int col6_1=rsGst46a.getInt("col6_1");
					int col6_2=rsGst46b.getInt("col6_2");
					int col6_3=rsGst6c.getInt("col6_3");
					int col6_4=rsGst6d.getInt("col6_4");
					//System.out.println("col6_1:="+col6_1+"\ncol6_2:="+col6_2+"\ncol8:="+col8);
					String absval = String.valueOf(col6_1+ col6_2) + "/" + String.valueOf(col6_3+col6_4);
					if(col6_3+col6_4!=0) {
						total=((double) (col6_1+col6_2) / col6_3+col6_4);
					}
					rank=score.marks4d(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					//gsta=new Gst(rsGst14aa.getString("ZONE_NAME"),"ALL",(int)total,rank,absval,zoneCode);

					gsta = new GST4A(rsGst46a.getString("ZONE_NAME"), "ALL", (Double)totalScore, rank, absval, zoneCode,ra);
					allGstaList.add(gsta);
				}
			} else if (type.equalsIgnoreCase("commissary")) {
				String prev_month_new =DateCalculate.getPreviousMonth(month_date);
				//int zone_code=rsGst14a.getInt("ZONE_CODE");


				// Query string
				String queryGst46a="SELECT " +
						"cc.ZONE_CODE, " +
						" cc.COMM_NAME,"+
						"zc.ZONE_NAME, " +
						"(14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_1 " +
						"FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "';";


				String queryGst46b= "SELECT " +
						"cc.ZONE_CODE, " +
						" cc.COMM_NAME,"+
						"zc.ZONE_NAME, " +
						"(" +
						"    14c.REALISATION_CGST_AMT_PRE + " +
						"    14c.REALISATION_IGST_AMT_PRE + " +
						"    14c.REALISATION_SGST_AMT_PRE + " +
						"    14c.REALISATION_CESS_AMT_PRE " +
						") AS col6_2 " +
						"FROM " +
						"    mis_gst_commcode AS cc " +
						"RIGHT JOIN " +
						"    mis_gi_gst_1 AS 14c " +
						"    ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN " +
						"    mis_gst_zonecode AS zc " +
						"    ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + prev_month_new + "';";
				String queryGst6c= "SELECT "
						+ "    cc.ZONE_CODE, "
						+" cc.COMM_NAME,"
						+ "    zc.ZONE_NAME "
						+ "    (14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_3 "
						+ "FROM mis_gst_commcode AS cc "
						+ "RIGHT JOIN "
						+ "    mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE "
						+ "LEFT JOIN "
						+ "    mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE "
						+ "WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "';";


				String queryGst6d="SELECT " +
						"cc.ZONE_CODE, " +
						" cc.COMM_NAME,"+
						"zc.ZONE_NAME " +
						"(14c.DETECTION_CGST_AMT + 14c.DETECTION_SGST_AMT + 14c.DETECTION_IGST_AMT + 14c.DETECTION_CESS_AMT) AS col6_4 " +
						"FROM " +
						"mis_gst_commcode AS cc " +
						"RIGHT JOIN " +
						"mis_gi_gst_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN " +
						"mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "';";
				ResultSet rsGst46a =GetExecutionSQL.getResult(queryGst46a);
				ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst46b);
				ResultSet rsGst6c =GetExecutionSQL.getResult(queryGst6c);
				ResultSet rsGst6d =GetExecutionSQL.getResult(queryGst6d);

				//System.out.println("rsGst46a:="+rsGst46a+"\nrsGst46b:="+rsGst46b+"\nrsGst48:="+rsGst48);
				while(rsGst46a.next() && rsGst46b.next() && rsGst6c.next() && rsGst6d.next() ) {
					String commname=rsGst46a.getString("COMM_NAME");
					String ra=RelevantAspect.Gst4D_RA;
					String zoneCode = rsGst46a.getString("ZONE_CODE");
					int col6_1=rsGst46a.getInt("col6_1");
					int col6_2=rsGst46b.getInt("col6_2");
					int col6_3=rsGst6c.getInt("col6_3");
					int col6_4=rsGst6d.getInt("col6_4");
					//System.out.println("col6_1:="+col6_1+"\ncol6_2:="+col6_2+"\ncol8:="+col8);
					String absval = String.valueOf(col6_1+ col6_2) + "/" + String.valueOf(col6_3+col6_4);
					if(col6_3+col6_4!=0) {
						total=((double) (col6_1+col6_2) / col6_3+col6_4);
					}
					rank=score.marks4d(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					//gsta=new Gst(rsGst14aa.getString("ZONE_NAME"),"ALL",(int)total,rank,absval,zoneCode);

					gsta = new GST4A(rsGst46a.getString("ZONE_NAME"), commname, (Double)totalScore, rank, absval, zoneCode,ra);
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
	@RequestMapping(value = "/gst5a")
	//  http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&type=zone
   //  http://localhost:8080/CbicAPI/cbic/gst2?month_date=2023-04-01&zone_code=70&type=commissary
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
				String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE, "
						+ "sum(14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + 14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + 14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no + 14c.adc_commissionerate_opening_no + 14c.adc_audit_opening_no + 14c.adc_investigation_opening_no + 14c.adc_callbook_opening_no + 14c.dc_commissionerate_opening_no + 14c.dc_audit_opening_no + 14c.dc_investigation_opening_no) as col9, "
						+ "sum(14c.dc_callbook_opening_no + 14c.superintendent_commissionerate_opening_no + 14c.superintendent_audit_opening_no + 14c.superintendent_investigation_opening_no + 14c.superintendent_callbook_opening_no) as col3 "
						+ "FROM mis_gst_commcode as cc "
						+ "right join mis_dpm_gst_adj_1 as 14c on cc.COMM_CODE = 14c.COMM_CODE "
						+ "left join mis_gst_zonecode as zc on zc.ZONE_CODE = cc.ZONE_CODE "
						+ "where 14c.MM_YYYY = '" + month_date + "'" +
						"GROUP BY cc.ZONE_CODE;";

				//Prepared Statement
				PreparedStatement psGst14aa = con.prepareStatement(queryGst14aa);

				//Result Set
				ResultSet rsGst14aa = psGst14aa.executeQuery();

				while (rsGst14aa.next()) {
					String ra = RelevantAspect.GST5A_RA;
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					int col9 = rsGst14aa.getInt("col9");
					int col3 = rsGst14aa.getInt("col3");
					String absval = String.valueOf(col9) + "/" + String.valueOf(col3);

					if ((col3) != 0) {
						total = ((double) (col9) / (col3));
					}

					//}
					rank = score.marks5a(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", (Double) totalScore, rank, absval, zoneCode, ra);
					//gsta=new GSTOneA(rsGst14aa.getString("ZONE_NAME"),"ALL",(int)total,rank);
					allGstaList.add(gsta);
				}
			}else if (type.equalsIgnoreCase("commissary")) {
					// Query string
					String queryGst14aa= "SELECT zc.ZONE_NAME,cc.COMM_NAME, cc.ZONE_CODE, "
							+ "(14c.adc_commissionerate_disposal_no + 14c.adc_audit_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_investigation_disposal_no + 14c.adc_callbook_disposal_no + 14c.dc_commissionerate_disposal_no + 14c.dc_audit_disposal_no + 14c.dc_investigation_disposal_no + 14c.dc_callbook_disposal_no + 14c.superintendent_commissionerate_disposal_no + 14c.superintendent_audit_disposal_no + 14c.superintendent_investigation_disposal_no + 14c.superintendent_callbook_disposal_no + 14c.adc_commissionerate_opening_no + 14c.adc_audit_opening_no + 14c.adc_investigation_opening_no + 14c.adc_callbook_opening_no + 14c.dc_commissionerate_opening_no + 14c.dc_audit_opening_no + 14c.dc_investigation_opening_no) as col9, "
							+ "(14c.dc_callbook_opening_no + 14c.superintendent_commissionerate_opening_no + 14c.superintendent_audit_opening_no + 14c.superintendent_investigation_opening_no + 14c.superintendent_callbook_opening_no) as col3 "
							+ "FROM mis_gst_commcode as cc "
							+ "right join mis_dpm_gst_adj_1 as 14c on cc.COMM_CODE = 14c.COMM_CODE "
							+ "left join mis_gst_zonecode as zc on zc.ZONE_CODE = cc.ZONE_CODE "
							+ "where 14c.MM_YYYY = '"+month_date+"' AND zc.ZONE_CODE='"+zone_code+"';";

					//Prepared Statement
					PreparedStatement psGst14aa=con.prepareStatement(queryGst14aa);

					//Result Set
					ResultSet rsGst14aa= psGst14aa.executeQuery();
					while(rsGst14aa.next()) {
						String commname=rsGst14aa.getString("COMM_NAME");
						String ra=RelevantAspect.GST5A_RA;
						String zoneName = rsGst14aa.getString("ZONE_NAME");
						String zoneCode = rsGst14aa.getString("ZONE_CODE");
						int col9=rsGst14aa.getInt("col9");
						int col3=rsGst14aa.getInt("col3");
						String absval=String.valueOf(col9)+"/"+String.valueOf(col3);

						if((col3)!=0) {
							total=((double) (col9) / (col3));
						}

						//}
						rank=score.marks5a(total);
						String formattedTotal = String.format("%.2f", total);
						double totalScore = Double.parseDouble(formattedTotal);
						gsta=new GST4A(zoneName, commname, (Double)totalScore, rank, absval, zoneCode,ra);
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
	//  http://localhost:8080/CbicAPI/cbic/gst5b?month_date=2023-04-01&type=zone
    //  http://localhost:8080/CbicAPI/cbic/gst5b?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst5B(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
		List<GST4A> allGstaList = new ArrayList<>();
		GST4A gsta = null;
		int rank = 0;
		double total = 0.00;
		try {
			if (type.equalsIgnoreCase("zone")) {
				// Query string
				String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE,sum(14c.ADC_COMMISSIONERATE_TIME_LESS_3_NO +14c.ADC_AUDIT_TIME_LESS_3_NO+14c.ADC_INVESTIGATION_TIME_LESS_3_NO+14c.ADC_CALLBOOK_TIME_LESS_3_NO+14c.DC_COMMISSIONERATE_TIME_LESS_3_NO +14c.DC_AUDIT_TIME_LESS_3_NO+14c.DC_INVESTIGATION_TIME_LESS_3_NO+14c.DC_CALLBOOK_TIME_LESS_3_NO+14c.SUPERINTENDENT_COMMISSIONERATE_TIME_LESS_3_NO +14c.SUPERINTENDENT_AUDIT_TIME_LESS_3_NO+14c.SUPERINTENDENT_INVESTIGATION_TIME_LESS_3_NO+14c.SUPERINTENDENT_CALLBOOK_TIME_LESS_3_NO) as col21, " +
						"sum(14c.ADC_COMMISSIONERATE_TIME_3_TO_6_NO+14c.ADC_AUDIT_TIME_3_TO_6_NO+14c.ADC_INVESTIGATION_TIME_3_TO_6_NO+14c.ADC_CALLBOOK_TIME_3_TO_6_NO+14c.DC_COMMISSIONERATE_TIME_3_TO_6_NO +14c.DC_AUDIT_TIME_3_TO_6_NO+14c.DC_INVESTIGATION_TIME_3_TO_6_NO+14c.DC_CALLBOOK_TIME_3_TO_6_NO+14c.SUPERINTENDENT_COMMISSIONERATE_TIME_3_TO_6_NO +14c.SUPERINTENDENT_AUDIT_TIME_3_TO_6_NO+14c.SUPERINTENDENT_INVESTIGATION_TIME_3_TO_6_NO+14c.SUPERINTENDENT_CALLBOOK_TIME_3_TO_6_NO) AS col22 " +
						" FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE;";

				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String queryGst3aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE, sum(14c.ADC_COMMISSIONERATE_OPENING_NO+14c.ADC_AUDIT_OPENING_NO+14c.ADC_INVESTIGATION_OPENING_NO+14c.ADC_CALLBOOK_OPENING_NO+14c.DC_COMMISSIONERATE_OPENING_NO+14c.DC_AUDIT_OPENING_NO+14c.DC_INVESTIGATION_OPENING_NO+14c.DC_CALLBOOK_OPENING_NO+14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO+14c.SUPERINTENDENT_AUDIT_OPENING_NO+14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO+14c.SUPERINTENDENT_CALLBOOK_OPENING_NO) AS col15 FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE 14c.MM_YYYY = '" + month_date + "'GROUP BY cc.ZONE_CODE;";

				//Result Set
				ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
				ResultSet rsGst3aa = GetExecutionSQL.getResult(queryGst3aa);
				while (rsGst14aa.next() && rsGst3aa.next()) {
					String ra = RelevantAspect.Gst5B_RA;
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					String commname="ALL";
					int col21 = rsGst14aa.getInt("col21");
					int col22 = rsGst14aa.getInt("col22");
					int col15 = rsGst3aa.getInt("col15");

					total = ((double) (col21 + col22) / (col15));
					//}
					rank = score.marks5b(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					String absval = String.valueOf(col21 + col22) + "/" + String.valueOf(col15);
					gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, (Double) totalScore, rank, absval, zoneCode, ra);
					allGstaList.add(gsta);
				}
			}else if (type.equalsIgnoreCase("commissary")) {
				// Query string
				String queryGst14aa="SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,(14c.ADC_COMMISSIONERATE_TIME_LESS_3_NO +14c.ADC_AUDIT_TIME_LESS_3_NO+14c.ADC_INVESTIGATION_TIME_LESS_3_NO+14c.ADC_CALLBOOK_TIME_LESS_3_NO+14c.DC_COMMISSIONERATE_TIME_LESS_3_NO +14c.DC_AUDIT_TIME_LESS_3_NO+14c.DC_INVESTIGATION_TIME_LESS_3_NO+14c.DC_CALLBOOK_TIME_LESS_3_NO+14c.SUPERINTENDENT_COMMISSIONERATE_TIME_LESS_3_NO +14c.SUPERINTENDENT_AUDIT_TIME_LESS_3_NO+14c.SUPERINTENDENT_INVESTIGATION_TIME_LESS_3_NO+14c.SUPERINTENDENT_CALLBOOK_TIME_LESS_3_NO) as col21, " +
						"(14c.ADC_COMMISSIONERATE_TIME_3_TO_6_NO+14c.ADC_AUDIT_TIME_3_TO_6_NO+14c.ADC_INVESTIGATION_TIME_3_TO_6_NO+14c.ADC_CALLBOOK_TIME_3_TO_6_NO+14c.DC_COMMISSIONERATE_TIME_3_TO_6_NO +14c.DC_AUDIT_TIME_3_TO_6_NO+14c.DC_INVESTIGATION_TIME_3_TO_6_NO+14c.DC_CALLBOOK_TIME_3_TO_6_NO+14c.SUPERINTENDENT_COMMISSIONERATE_TIME_3_TO_6_NO +14c.SUPERINTENDENT_AUDIT_TIME_3_TO_6_NO+14c.SUPERINTENDENT_INVESTIGATION_TIME_3_TO_6_NO+14c.SUPERINTENDENT_CALLBOOK_TIME_3_TO_6_NO) AS col22 " +
						" FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "'";

				String prev_month_new =DateCalculate.getPreviousMonth(month_date);

				String queryGst3aa= "SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, (14c.ADC_COMMISSIONERATE_OPENING_NO+14c.ADC_AUDIT_OPENING_NO+14c.ADC_INVESTIGATION_OPENING_NO+14c.ADC_CALLBOOK_OPENING_NO+14c.DC_COMMISSIONERATE_OPENING_NO+14c.DC_AUDIT_OPENING_NO+14c.DC_INVESTIGATION_OPENING_NO+14c.DC_CALLBOOK_OPENING_NO+14c.SUPERINTENDENT_COMMISSIONERATE_OPENING_NO+14c.SUPERINTENDENT_AUDIT_OPENING_NO+14c.SUPERINTENDENT_INVESTIGATION_OPENING_NO+14c.SUPERINTENDENT_CALLBOOK_OPENING_NO) AS col15 FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_dpm_gst_adj_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "'";

				//Result Set
				ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
				ResultSet rsGst3aa=GetExecutionSQL.getResult(queryGst3aa);
				while(rsGst14aa.next() && rsGst3aa.next()) {
					String commname=rsGst14aa.getString("COMM_NAME");
					String ra=RelevantAspect.Gst5B_RA;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					int col21=rsGst14aa.getInt("col21");
					int col22=rsGst14aa.getInt("col22");
					int col15=rsGst3aa.getInt("col15");
					if (col15 != 0) {
					total=((double) (col21+col22)/ (col15));}
					rank=score.marks5b(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					String absval = String.valueOf(col21+col22) + "/" + String.valueOf(col15);
					gsta = new GST4A(zoneName, commname, (Double)totalScore, rank, absval, zoneCode,ra);
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
	//  http://localhost:8080/CbicAPI/cbic/gst6a?month_date=2023-04-01&type=zone
    //  http://localhost:8080/CbicAPI/cbic/gst6a?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst6A(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

		List<GST4A> allGstaList = new ArrayList<>();
		GST4A gsta = null;
		int rank = 0;
		double total = 0.00;

		try {
			if (type.equalsIgnoreCase("zone")) {

				// Query string
				String queryGst14aa = "SELECT zc.ZONE_NAME,cc.ZONE_CODE,sum(14c.COMM_DISPOSAL_NO+14c.JC_DISPOSAL_NO+14c.AC_DISPOSAL_NO+14c.SUP_DISPOSAL_NO) as col9 " +

						" FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE  14c.MM_YYYY = '" + month_date + "'GROUP BY cc.ZONE_CODE;";

				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String queryGst3aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE, sum(14c.COMM_CLOSING_NO+14c.JC_CLOSING_NO+14c.AC_CLOSING_NO+14c.SUP_CLOSING_NO) AS col3 FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE  14c.MM_YYYY = '" + month_date + "'GROUP BY CC.ZONE_CODE;";

				//Result Set
				ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
				ResultSet rsGst3aa = GetExecutionSQL.getResult(queryGst3aa);
				while (rsGst14aa.next() && rsGst3aa.next()) {
					String ra = RelevantAspect.Gst6A_RA;
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					String commname ="ALL";
					int col9 = rsGst14aa.getInt("col9");
					int col3 = rsGst3aa.getInt("col3");
					if (col3 != 0) {
						total = ((double) (col9) / col3);
					}

					rank = score.marks6a(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					String absval = String.valueOf(col9) + "/" + String.valueOf(col3);
					gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, (Double) totalScore, rank, absval, zoneCode, ra);
					allGstaList.add(gsta);
				}
			}else if (type.equalsIgnoreCase("commissary")) {
				// Query string
				String queryGst14aa="SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,(14c.COMM_DISPOSAL_NO+14c.JC_DISPOSAL_NO+14c.AC_DISPOSAL_NO+14c.SUP_DISPOSAL_NO) as col9 " +

						" FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "'";

				String prev_month_new =DateCalculate.getPreviousMonth(month_date);

				String queryGst3aa= "SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, (14c.COMM_CLOSING_NO+14c.JC_CLOSING_NO+14c.AC_CLOSING_NO+14c.SUP_CLOSING_NO) AS col3 FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "'";

				//Result Set
				ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
				ResultSet rsGst3aa=GetExecutionSQL.getResult(queryGst3aa);
				while(rsGst14aa.next() && rsGst3aa.next()) {
					String commname=rsGst14aa.getString("COMM_NAME");
					String ra=RelevantAspect.Gst6A_RA;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					int col9=rsGst14aa.getInt("col9");
					int col3=rsGst3aa.getInt("col3");
					if(col3!=0) {
						total=((double) (col9) / col3);
					}

					rank=score.marks6a(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					String absval = String.valueOf(col9) + "/" + String.valueOf(col3);
					gsta = new GST4A(zoneName, commname, (Double)totalScore, rank, absval, zoneCode,ra);
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
	//  http://localhost:8080/CbicAPI/cbic/gst6b?month_date=2023-04-01&type=zone
    //  http://localhost:8080/CbicAPI/cbic/gst6b?month_date=2023-04-01&zone_code=70&type=commissary
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
				String queryGst14aa = "SELECT zc.ZONE_NAME,cc.ZONE_CODE, sum(14c.COMM_DISPOSAL_NO) AS col1, " +
						"sum(14c.JC_DISPOSAL_NO ) AS col2, sum(14c.AC_DISPOSAL_NO ) AS col3, sum(14c.SUP_DISPOSAL_NO) AS col4, " +
						"sum(14c.COMM_CLOSING_NO) AS col5, sum(14c.JC_CLOSING_NO) AS col6, sum(14c.AC_CLOSING_NO) AS col7, " +
						"sum(14c.SUP_CLOSING_NO) AS col8 " +
						"FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE;";

				//Prepared Statement
				PreparedStatement psGst14aa = con.prepareStatement(queryGst14aa);

				//Result Set
				ResultSet rsGst14aa = psGst14aa.executeQuery();

				while (rsGst14aa.next()) {
					String ra = RelevantAspect.Gst6B_RA;
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					String commname = "ALL";
					int col1 = rsGst14aa.getInt("col1");
					int col2 = rsGst14aa.getInt("col2");
					int col3 = rsGst14aa.getInt("col3");
					int col4 = rsGst14aa.getInt("col4");
					int col5 = rsGst14aa.getInt("col5");
					int col6 = rsGst14aa.getInt("col6");
					int col7 = rsGst14aa.getInt("col7");
					int col8 = rsGst14aa.getInt("col8");

					if ((col5 + col6 + col7 + col8) != 0) {
						total = ((double) (col1 + col2 + col3 + col4) / (col5 + col6 + col7 + col8));
					}
					rank = score.marks6b(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					String absval = String.valueOf(col1 + col2 + col3 + col4) + "/" + String.valueOf(col5 + col6 + col7 + col8);
					gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, (Double) totalScore, rank, absval, zoneCode, ra);
					allGstaList.add(gsta);
				}
			}else if (type.equalsIgnoreCase("commissary")) {
				// Query string
				String queryGst14aa = "SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, 14c.COMM_DISPOSAL_NO AS col1, " +
						"14c.JC_DISPOSAL_NO AS col2, 14c.AC_DISPOSAL_NO AS col3, 14c.SUP_DISPOSAL_NO AS col4, " +
						"14c.COMM_CLOSING_NO AS col5, 14c.JC_CLOSING_NO AS col6, 14c.AC_CLOSING_NO AS col7, " +
						"14c.SUP_CLOSING_NO AS col8 " +
						"FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_dgi_st_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE 14c.MM_YYYY = '" + month_date + "' AND zc.ZONE_CODE = '" + zone_code + "';";


				//Prepared Statement
				PreparedStatement psGst14aa = con.prepareStatement(queryGst14aa);

				//Result Set
				ResultSet rsGst14aa = psGst14aa.executeQuery();

				while (rsGst14aa.next()) {
					String commname = rsGst14aa.getString("COMM_NAME");
					String ra = RelevantAspect.Gst6B_RA;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String zoneCode = rsGst14aa.getString("ZONE_CODE");

					int col1 = rsGst14aa.getInt("col1");
					int col2 = rsGst14aa.getInt("col2");
					int col3 = rsGst14aa.getInt("col3");
					int col4 = rsGst14aa.getInt("col4");
					int col5 = rsGst14aa.getInt("col5");
					int col6 = rsGst14aa.getInt("col6");
					int col7 = rsGst14aa.getInt("col7");
					int col8 = rsGst14aa.getInt("col8");

					if ((col5 + col6 + col7 + col8) != 0) {
						total = ((double) (col1 + col2 + col3 + col4) / (col5 + col6 + col7 + col8));
					}

					//}
					rank = score.marks6b(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					String absval = String.valueOf(col1 + col2 + col3 + col4) + "/" + String.valueOf(col5 + col6 + col7 + col8);
					gsta = new GST4A(zoneName, commname, (Double) totalScore, rank, absval, zoneCode, ra);
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
	//  http://localhost:8080/CbicAPI/cbic/gst6c?month_date=2023-04-01&type=zone
    //  http://localhost:8080/CbicAPI/cbic/gst6c?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst6C(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

		List<GST4A> allGstaList = new ArrayList<>();
		GST4A gsta = null;
		int rank = 0;
		double total = 0.00;
		try {
			if (type.equalsIgnoreCase("zone")) {
				// Query string
				String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE,sum(14c.COMM_DISPOSAL_NO+14c.JC_DISPOSAL_NO+14c.AC_DISPOSAL_NO+14c.SUP_DISPOSAL_NO) AS col9 " +

						" FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE  14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE;";

				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String queryGst3aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE, sum(14c.COMM_CLOSING_NO+14c.JC_CLOSING_NO+14c.AC_CLOSING_NO+14c.SUP_CLOSING_NO) AS col3 FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE;";

				//Result Set
				ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
				ResultSet rsGst3aa = GetExecutionSQL.getResult(queryGst3aa);
				while (rsGst14aa.next() && rsGst3aa.next()) {
					String ra = RelevantAspect.Gst6C_RA;
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					String commname= "ALL";
					int col9 = rsGst14aa.getInt("col9");
					int col3 = rsGst3aa.getInt("col3");
					if (col3 != 0) {
						total = ((double) (col9) / col3);
					}

					rank = score.marks6c(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					//gsta=new Gst(rsGst14aa.getString("ZONE_NAME"),"ALL",(int)total,rank,absval,zoneCode);
					String absval = String.valueOf(col9) + "/" + String.valueOf(col3);
					gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, (Double) totalScore, rank, absval, zoneCode, ra);
					allGstaList.add(gsta);
				}
			}else if (type.equalsIgnoreCase("commissary")) { // gst 6c
				// Query string
				String queryGst14aa="SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE,(14c.COMM_DISPOSAL_NO+14c.JC_DISPOSAL_NO+14c.AC_DISPOSAL_NO+14c.SUP_DISPOSAL_NO) as col9 " +

						" FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "'";

				String prev_month_new =DateCalculate.getPreviousMonth(month_date);

				String queryGst3aa= "SELECT zc.ZONE_NAME, cc.COMM_NAME, cc.ZONE_CODE, (14c.COMM_CLOSING_NO+14c.JC_CLOSING_NO+14c.AC_CLOSING_NO+14c.SUP_CLOSING_NO) AS col3 FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE cc.ZONE_CODE = '" + zone_code + "' AND 14c.MM_YYYY = '" + month_date + "'";

				//Result Set
				ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
				ResultSet rsGst3aa=GetExecutionSQL.getResult(queryGst3aa);
				while(rsGst14aa.next() && rsGst3aa.next()) {
					String commname=rsGst14aa.getString("COMM_NAME");
					String ra=RelevantAspect.Gst6C_RA;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					int col9=rsGst14aa.getInt("col9");
					int col3=rsGst3aa.getInt("col3");

					if(col3!=0) {
						total=((double) (col9) / col3);
					}

					rank=score.marks6c(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					//gsta=new Gst(rsGst14aa.getString("ZONE_NAME"),"ALL",(int)total,rank,absval,zoneCode);
					String absval = String.valueOf(col9) + "/" + String.valueOf(col3);
					gsta = new GST4A(zoneName, commname, (Double)totalScore, rank, absval, zoneCode,ra);
					allGstaList.add(gsta);
					//allGstaList.add(gsta);
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
	//  http://localhost:8080/CbicAPI/cbic/gst6d?month_date=2023-04-01&type=zone
    //  http://localhost:8080/CbicAPI/cbic/gst6d?month_date=2023-04-01&zone_code=70&type=commissary
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
				String queryGst14aa =
						"SELECT zc.ZONE_NAME,cc.ZONE_CODE, " +
								"    sum(14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) AS col18, " +
								"    sum(14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col13 " +
								"FROM mis_gst_commcode AS cc " +
								"RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
								"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
								"WHERE 14c.MM_YYYY = '" + month_date + "'GROUP BY cc.ZONE_CODE;";

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

					if ((col13) != 0) {
						total = ((double) (col18) / (col13));
					}

					//}
					rank = score.marks6d(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					String absval = String.valueOf(col18) + "/" + String.valueOf(col13);
					gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, (Double) totalScore, rank, absval, zoneCode, ra);
					allGstaList.add(gsta);
				}
			} else if (type.equalsIgnoreCase("commissary")) { // gst 6d
				// Query string
				String queryGst14aa=
						"SELECT zc.ZONE_NAME,cc.COMM_NAME,cc.ZONE_CODE, " +
								"    (14c.COMM_MORE_YEAR_AMT + 14c.JC_MORE_YEAR_AMT + 14c.AC_MORE_YEAR_AMT + 14c.SUP_MORE_YEAR_AMT) AS col18, " +
								"    (14c.COMM_CLOSING_NO + 14c.JC_CLOSING_NO + 14c.AC_CLOSING_NO + 14c.SUP_CLOSING_NO) AS col13 " +
								"FROM mis_gst_commcode AS cc " +
								"RIGHT JOIN mis_dgi_ce_1a AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
								"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
								"WHERE 14c.MM_YYYY = '"+month_date+"'AND zc.ZONE_CODE = '"+zone_code+"';";


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

					if((col13)!=0) {
						total=((double) (col18) / (col13));
					}

					rank=score.marks6d(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					String absval=String.valueOf(col18)+"/"+String.valueOf(col13);
					gsta=new GST4A(zoneName, commname, (Double)totalScore, rank, absval, zoneCode,ra);
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
	@RequestMapping(value = "/gst7")
	//  http://localhost:8080/CbicAPI/cbic/gst7?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/gst7?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst7(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
		List<GST4A> allGstaList = new ArrayList<>();
		GST4A gsta = null;
		int rank = 0;
		double total = 0.00;

		try {
			if(type.equalsIgnoreCase("zone")) {
				String quaryGst7 = "SELECT " +
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
					int col22 = rsGst7.getInt("col22");
					int col16 = rsGst7.getInt("col16");
					String absval = String.valueOf(col22) + "/" + String.valueOf(col16);

					if (col16 != 0) {
						total = ((double) col22 / col16) * 100;
					} else {
						total = 0;
					}
					//total=((double) col22 / col16)*100;
					rank = score.marks7(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta = new GST4A(rsGst7.getString("ZONE_NAME"), commname, (Double) totalScore, rank, absval, zoneCode, ra);
					allGstaList.add(gsta);
				}
			}else if (type.equalsIgnoreCase("commissary")) { // gst 7
				String quaryGst7 = "SELECT DISTINCT" +
						"(14c.opening_balance_no + 14c.RFD_01_NO - 14c.RFD_03_NO - 14c.RFD_06_SANCTIONED_NO - 14c.RFD_06_REJECTED_NO) AS col16, " +
						"(14c.age_breakup_above60_no) AS col22, " +
						"cc.ZONE_CODE, " +
						"zc.ZONE_NAME, " +
						"cc.COMM_NAME " +
						"FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_dpm_gst_4 AS 14c " +
						"ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc " +
						"ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE 14c.MM_YYYY = '" + month_date + "' AND cc.ZONE_CODE = '" + zone_code + "';";

				ResultSet rsGst14aa =GetExecutionSQL.getResult(quaryGst7);

				while(rsGst14aa.next()) {
					String ra=RelevantAspect.Gst7_RA;
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					String commname=rsGst14aa.getString("COMM_NAME");
					//String zoneName = rsGst14aa.getString("ZONE_NAME");
					int col22=rsGst14aa.getInt("col22");
					int col16=rsGst14aa.getInt("col16");
					String absval=String.valueOf(col22)+"/"+String.valueOf(col16);

					//total=((double) col22 / col16);
					if (col16 != 0){
						total =((double) col22/col16);
					}
					else{
						total=0;
					}
					rank=score.marks7(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),commname,totalScore,rank,absval,zoneCode,ra);
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
	@RequestMapping(value = "/gst8a")
	//  http://localhost:8080/CbicAPI/cbic/gst8a?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/gst8a?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst8AC(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

		List<GST4A> allGstaList=new ArrayList<>();
		GST4A gsta=null;
		int rank = 0;
		double total = 0.00;


		try {
			if(type.equalsIgnoreCase("zone")) {
				//ResultSet rsGst14a =GetExecutionSQL.getResult(queryGst14a);
				//while(rsGst14a.next()) {
				//String zonecode=rsGst14a.getString("ZONE_CODE");
				String prev_month_new =DateCalculate.getPreviousMonth(month_date);

				// Query string
				String queryGst14aa="WITH cte AS ( "
						+ "SELECT DISTINCT "
						+ "zc.ZONE_NAME, "
						+ "zc.ZONE_CODE, "

						+ "cc.COMM_CODE, "
						+ "sum(a11.ARREAR_REALISED_AMT) AS col13 "
						+ "FROM mis_tar_gst_3_new AS a11 "
						+ "LEFT JOIN mis_gst_commcode AS cc ON a11.COMM_CODE = cc.COMM_CODE "
						+ "LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE "
						+ "WHERE a11.MM_YYYY = "+month_date+"' GROUP BY cc.ZONE_CODE"
						+ "), "
						+ "cte1 AS ( "
						+ "SELECT DISTINCT "
						+ "zc.ZONE_NAME, "
						+ "zc.ZONE_CODE, "

						+ "cc.COMM_CODE, "
						+ "sum(1) AS col3 "
						+ "FROM mis_tar_gst_3_new AS a11 "
						+ "LEFT JOIN mis_gst_commcode AS cc ON a11.COMM_CODE = cc.COMM_CODE "
						+ "LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE "
						+ "WHERE a11.MM_YYYY = '"+ prev_month_new +"' "
						+ "AND cc.ZONE_CODE = '"+zone_code+"'"
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
				//ResultSet rsGst3aa=GetExecutionSQL.getResult(queryGst3aa);
				while(rsGst14aa.next()) {
					//String commname=rsGst14aa.getString("COMM_NAME");
					String ra=RelevantAspect.Gst8A_RA;
					//String zoneName = rsGst14aa.getString("ZONE_NAME");
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					int col13=rsGst14aa.getInt("col13");
					//int col5=rsGst14aa.getInt("col5");
					//int col3=rsGst14aa.getInt("col3");
					//int col6_1=rsGst3aa.getInt("col6_1");
					int col3=rsGst14aa.getInt("col3");
					String absval = String.valueOf(col13) + "/" + String.valueOf(col3);
					if ((col3) != 0){
						total =((double) (col13)/(col3));
					}
					else{
						total=0;
					}
					//String absval=String.valueOf(col6+col5)+"/"+String.valueOf(col3+col2);
					//int col2=0;
					//if(rsGst3aa.next()) {
					//col2=rsGst3aa.getInt("col2");
//						String absval=String.valueOf(col6+col5)+"/"+String.valueOf(col3+col2);
//						 String zoneCode = rsGst14aa.getString("ZONE_CODE");
					//}

					// total=((double) (col6+col5)/ (col3+col2));
					//}
					rank=score.marks8a(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					//gsta=new Gst(rsGst14aa.getString("ZONE_NAME"),"ALL",(int)total,rank,absval,zoneCode);

					gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", (Double)totalScore, rank, absval, zoneCode,ra);
					allGstaList.add(gsta);
					//allGstaList.add(gsta);
				}
			}else if (type.equalsIgnoreCase("commissary")) {
				String prev_month_new =DateCalculate.getPreviousMonth(month_date);

				// Query string
				String queryGst14aa="WITH cte AS ( "
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
				//ResultSet rsGst3aa=GetExecutionSQL.getResult(queryGst3aa);
				while(rsGst14aa.next()) {
					String commname=rsGst14aa.getString("COMM_NAME");
					String ra=RelevantAspect.Gst8A_RA;
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					int col13=rsGst14aa.getInt("col13");
					//int col5=rsGst14aa.getInt("col5");
					//int col3=rsGst14aa.getInt("col3");
					//int col6_1=rsGst3aa.getInt("col6_1");
					int col3=rsGst14aa.getInt("col3");
					String absval = String.valueOf(col13) + "/" + String.valueOf(col3);
					if ((col3) != 0){
						total =((double) (col13)/(col3));
					}
					else{
						total=0;
					}
					//String absval=String.valueOf(col6+col5)+"/"+String.valueOf(col3+col2);
					//int col2=0;
					//if(rsGst3aa.next()) {
					//col2=rsGst3aa.getInt("col2");
//						String absval=String.valueOf(col6+col5)+"/"+String.valueOf(col3+col2);
//						 String zoneCode = rsGst14aa.getString("ZONE_CODE");
					//}

					// total=((double) (col6+col5)/ (col3+col2));
					//}
					rank=score.marks8a(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					//gsta=new Gst(rsGst14aa.getString("ZONE_NAME"),"ALL",(int)total,rank,absval,zoneCode);

					gsta = new GST4A(zoneName, commname, (Double)totalScore, rank, absval, zoneCode,ra);
					allGstaList.add(gsta);
					//allGstaList.add(gsta);
				}
			}
			//}

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
	//  http://localhost:8080/CbicAPI/cbic/gst8b?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/gst8b?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst8B(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

		List<GST4A> allGstaList=new ArrayList<>();
		GST4A gsta=null;
		int rank = 0;
		double total = 0.00;
		// Query string
		///String queryGst14a="SELECT *,cc.ZONE_CODE,cc.COMM_NAME,zc.ZONE_NAME FROM  mis_gst_commcode as cc right join mis_tar_gst_3_new as 14c on cc.COMM_CODE=14c.COMM_CODE left join mis_gst_zonecode as zc on zc.ZONE_CODE=cc.ZONE_CODE where 14c.MM_YYYY='"+month_date+"'";
		try {
			if(type.equalsIgnoreCase("zone")) {
				//ResultSet rsGst14a =GetExecutionSQL.getResult(queryGst14a);
				//while(rsGst14a.next()) {
				//int zone_code=rsGst14a.getInt("ZONE_CODE");


				// Query string
				String queryGst14aa="SELECT cc.ZONE_CODE, zc.ZONE_NAME, sum(14c.CLOSING_AMT + 14c.RECEIPTS_AMT - 14c.ARREAR_REALISED_AMT - 14c.ARREAR_OTHER_AMT) AS col1, " +
						"sum(14c.BELOW_YEAR_AMT) AS col2 " +
						"FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_tar_gst_3_new AS 14c ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE 14c.MM_YYYY = '"+month_date+"'" +
						"GROUP BY cc.ZONE_CODE;";

				String prev_month_new =DateCalculate.getPreviousMonth(month_date);

				String queryGst3aa="SELECT cc.ZONE_CODE, zc.ZONE_NAME, " +
						"SUM(14c.CLOSING_AMT + 14c.RECEIPTS_AMT - 14c.ARREAR_REALISED_AMT - 14c.ARREAR_OTHER_AMT) AS col23 " +
						"FROM mis_gst_commcode AS cc " +
						"RIGHT JOIN mis_tar_gst_3_new AS 14c " +
						"ON cc.COMM_CODE = 14c.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE " +
						"WHERE 14c.MM_YYYY = '" +prev_month_new + "' " +
						"GROUP BY cc.ZONE_CODE;";

				//Result Set
				ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
				ResultSet rsGst3aa=GetExecutionSQL.getResult(queryGst3aa);
				while(rsGst14aa.next() && rsGst3aa.next()) {
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					String ra=RelevantAspect.Gst8B_RA;
					int col1=rsGst14aa.getInt("col1");
					int col2=rsGst14aa.getInt("col2");

					//int col3=rsGst14aa.getInt("col3");
					int col23=0;

					if(rsGst3aa.next()) {
						col23=rsGst3aa.getInt("col23");
					}

					total=((double) (col1-col2)/ col23);
					//}
					rank=score.marks8b(total);
					String absval = String.valueOf(col1-col2) + "/" + String.valueOf(col23);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", (Double)totalScore, rank, absval, zoneCode,ra);
					//gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),"ALL",(int)total,rank);
					allGstaList.add(gsta);
				}
			}else if (type.equalsIgnoreCase("commissary")) {
				// Query string
				String queryGst14aa= "SELECT cc.ZONE_CODE, zc.ZONE_NAME, cc.COMM_NAME, "
						+ "(14c.CLOSING_AMT + 14c.RECEIPTS_AMT - 14c.ARREAR_REALISED_AMT - 14c.ARREAR_OTHER_AMT) AS col1, "
						+ "(14c.BELOW_YEAR_AMT) AS col2 "
						+ "FROM mis_gst_commcode AS cc "
						+ "RIGHT JOIN mis_tar_gst_3_new AS 14c ON cc.COMM_CODE = 14c.COMM_CODE "
						+ "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE "
						+ "WHERE 14c.MM_YYYY = '"+month_date+"' AND zc.ZONE_CODE = '"+zone_code+"';";


				String prev_month_new =DateCalculate.getPreviousMonth(month_date);

				String queryGst3aa= "SELECT cc.ZONE_CODE,zc.ZONE_NAME,cc.COMM_NAME,"
						+ "(14c.CLOSING_AMT + 14c.RECEIPTS_AMT - 14c.ARREAR_REALISED_AMT - 14c.ARREAR_OTHER_AMT) AS col23 "
						+ "FROM mis_gst_commcode AS cc "
						+ "RIGHT JOIN mis_tar_gst_3_new AS 14c ON cc.COMM_CODE = 14c.COMM_CODE "
						+ "LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE "
						+ "WHERE 14c.MM_YYYY = '"+prev_month_new+"' and zc.ZONE_CODE='"+zone_code+"';";


				//Result Set
				ResultSet rsGst14aa= GetExecutionSQL.getResult(queryGst14aa);
				ResultSet rsGst3aa=GetExecutionSQL.getResult(queryGst3aa);
				while(rsGst14aa.next() && rsGst3aa.next()) {
					String zoneName = rsGst14aa.getString("ZONE_NAME");
					String commname=rsGst14aa.getString("COMM_NAME");
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					String ra=RelevantAspect.Gst8B_RA;
					int col1=rsGst14aa.getInt("col1");
					int col2=rsGst14aa.getInt("col2");

					//int col3=rsGst14aa.getInt("col3");
					int col23=0;

					if(rsGst3aa.next()) {
						col23=rsGst3aa.getInt("col23");
					}

					total=((double) (col1-col2)/ col23);
					//}
					rank=score.marks8b(total);
					String absval = String.valueOf(col1-col2) + "/" + String.valueOf(col23);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta = new GST4A(zoneName, commname, (Double)totalScore, rank, absval, zoneCode,ra);
					//gsta=new GST4A(rsGst14aa.getString("ZONE_NAME"),"ALL",(int)total,rank);
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
	//  http://localhost:8080/CbicAPI/cbic/gst10a?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/gst10a?month_date=2023-04-01&zone_code=70&type=commissary
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
				String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE, sum(TAXPAYER_AUDITED_NO_LARGE + TAXPAYER_AUDITED_NO_MEDIUM + TAXPAYER_AUDITED_NO_SMALL) AS col3,\n" +
						" sum(TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE+TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM+TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL) AS col2 FROM  mis_gst_commcode AS cc \n" +
						" RIGHT JOIN mis_dga_gst_adt_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						" WHERE 14c.MM_YYYY = '" + month_date + "' GROUP BY cc.ZONE_CODE ;";
				ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);
				while (rsGst14aa.next()) {
					String ra = RelevantAspect.Gst10A_RA;
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					int col3 = rsGst14aa.getInt("col3");
					int col2 = rsGst14aa.getInt("col2");

					if (col2 != 0) {
						X = (N * col2) / 12.0;
					} else {
						X = 0.00;
					}
					String absval = col3 + "/" + X;
					if (X != 0) {
						total = ((double) col3 / X);
					} else {
						total = 0.00;
					}

					rank = score.marks10a(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), "ALL", totalScore, rank, absval, zoneCode, ra);
					allGstaList.add(gsta);
				}
			} else if (type.equalsIgnoreCase("commissary")) { // gst 10a
				String queryGst14aa = "SELECT zc.ZONE_NAME, cc.ZONE_CODE,cc.COMM_NAME, (TAXPAYER_AUDITED_NO_LARGE + TAXPAYER_AUDITED_NO_MEDIUM + TAXPAYER_AUDITED_NO_SMALL) AS col3,\n" +
						" (TAXPAYER_ALLOTTED_AUDIT_FY_NO_LARGE+TAXPAYER_ALLOTTED_AUDIT_FY_NO_MEDIUM+TAXPAYER_ALLOTTED_AUDIT_FY_NO_SMALL) AS col2 FROM  mis_gst_commcode AS cc \n" +
						" RIGHT JOIN mis_dga_gst_adt_2 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE \n" +
						" WHERE 14c.MM_YYYY = '" + month_date + "' and cc.ZONE_CODE= '" + zone_code + "';";
				ResultSet rsGst14aa = GetExecutionSQL.getResult(queryGst14aa);

				while (rsGst14aa.next()) {
					String ra = RelevantAspect.Gst10A_RA;
					String zoneCode = rsGst14aa.getString("ZONE_CODE");
					String commname = rsGst14aa.getString("COMM_NAME");
					int col3 = rsGst14aa.getInt("col3");
					int col2 = rsGst14aa.getInt("col2");

					if (col2 != 0) {
						X = (N * col2) / 12.0;
					} else {
						X = 0.00;
					}
					String absval = col3 + "/" + X;
					if (X != 0) {
						total = ((double) col3 / X);
					} else {
						total = 0.00;
					}

					rank = score.marks10a(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta = new GST4A(rsGst14aa.getString("ZONE_NAME"), commname, totalScore, rank, absval, zoneCode, ra);
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
	 * updated: RKS, may 18, 2024
	 * Purpose: This methods have core function in Return Filing .
	 */
	@ResponseBody
	@RequestMapping(value = "/gst11a")
	//  http://localhost:8080/CbicAPI/cbic/gst11a?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/gst11a?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst11A(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
		List<GST4A> allGstaList = new ArrayList<>();
		GST4A gsta = null;
		int rank = 0;
		double total = 0.00;

		try {

			if (type.equalsIgnoreCase("zone")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String queryTotalPending = "WITH cte AS (" +
						"SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS col10 " +
						"FROM mis_dla_gst_lgl_1 11a " +
						"LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE " +
						"WHERE 11a.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6 " +
						"GROUP BY zc.ZONE_CODE), " +
						"cte1 AS (" +
						"SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4 " +
						"FROM mis_dla_gst_lgl_1 11a " +
						"LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE " +
						"WHERE 11a.MM_YYYY = '" + prev_month_new + "' AND FORUM_CODE = 6 " +
						"GROUP BY zc.ZONE_CODE) " +
						"SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.col10, cte1.col4, ((cte.col10 / cte1.col4) * 100) AS total_score " +
						"FROM cte " +
						"INNER JOIN cte1 ON cte.ZONE_CODE = cte1.ZONE_CODE";
				ResultSet rsTotalPending = GetExecutionSQL.getResult(queryTotalPending);

				while (rsTotalPending.next()) {
					String ra=RelevantAspect.Gst11A_RA;
					String zoneName = rsTotalPending.getString("ZONE_NAME");
					String zoneCode = rsTotalPending.getString("ZONE_CODE");
					String commname = "ALL";
					double col10 = rsTotalPending.getDouble("col10");
					double col4 = rsTotalPending.getDouble("col4");
					String absval=String.valueOf(col10)+"/"+String.valueOf(col4);
					total = (col4 != 0) ? ((col10 / col4) * 100) : 0;

					rank = score.marks11a(total,col10);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta = new GST4A(zoneName, commname, totalScore,rank,absval,zoneCode,ra);
					allGstaList.add(gsta);
			}
			}else if (type.equalsIgnoreCase("commissary")) { //gst 11a
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String queryTotalPending = "WITH cte AS ( " +
						"SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, " +
						"(DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS col10 " +
						"FROM mis_dla_gst_lgl_1 AS 11a " +
						"LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE " +
						"WHERE 11a.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6 AND cc.ZONE_CODE = '" + zone_code + "'),cte1 AS ( " +
						"SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, " +
						"(DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4 " +
						"FROM mis_dla_gst_lgl_1 AS 11a " +
						"LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE WHERE " +
						"11a.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6 AND cc.ZONE_CODE = '" + zone_code + "') " +
						"SELECT cte.ZONE_NAME,cte.ZONE_CODE,cte.COMM_NAME,cte.COMM_CODE,cte.col10,cte1.col4, " +
						"((cte.col10 / cte1.col4) * 100) AS total_score FROM cte " +
						"INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE";


				ResultSet rsTotalPending = GetExecutionSQL.getResult(queryTotalPending);

				while (rsTotalPending.next()) {
					String ra = RelevantAspect.Gst11A_RA;
					String zoneName = rsTotalPending.getString("ZONE_NAME");
					String zoneCode = rsTotalPending.getString("ZONE_CODE");
					String commname = rsTotalPending.getString("COMM_NAME");
					double col10 = rsTotalPending.getDouble("col10");
					double col4 = rsTotalPending.getDouble("col4");
					total = (col4 != 0) ? ((col10 / col4)) : 0;
					rank = score.marks11a(total, col10);
					String absval = String.valueOf(col10) + "/" + String.valueOf(col4);

					total = (col4 != 0) ? (col10 / col4) : 0;

					rank = score.marks11a(total, col10);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta = new GST4A(rsTotalPending.getString("ZONE_NAME"), commname, totalScore, rank, absval, zoneCode, ra);
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
	@RequestMapping(value = "/gst11b")
	//  http://localhost:8080/CbicAPI/cbic/gst11b?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/gst11b?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst11B(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
		List<GST4A> allGstaList = new ArrayList<>();
		GST4A gsta = null;
		int rank = 0;
		double total = 0.00;

		try {
			if (type.equalsIgnoreCase("zone")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);
				// Query string
				String queryGst49 = "WITH cte AS ( "
					    + "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, "
					    + "           SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, "
					    + "           SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A "
					    + "    FROM mis_dla_gst_lgl_1a AS 11a "
					    + "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE "
					    + "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE "
					    + "    WHERE 11a.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 6 "
					    + "    GROUP BY zc.ZONE_CODE "
					    + "), "
					    + "cte1 AS ( "
					    + "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, "
					    + "           SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, "
					    + "           SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B "
					    + "    FROM mis_dla_gst_lgl_1b AS 11b "
					    + "    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE "
					    + "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE "
					    + "    WHERE 11b.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 6 "
					    + "    GROUP BY zc.ZONE_CODE"
					    + ") "
					    + "SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_NAME, cte.col10A, cte.col12A, cte1.col10B, cte1.col12B, "
					    + "       (cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B) AS numerator, "
					    + "       (cte.col10A + cte1.col10B) AS denominator, "
					    + "       (((cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B)) / (cte.col10A + cte1.col10B)) AS total_score "
					    + "FROM cte "
					    + "INNER JOIN cte1 ON cte.ZONE_CODE = cte1.ZONE_CODE;";

				
				ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst49);

				while(rsGst46b.next()) {
					String ra= RelevantAspect.Gst11B_RA;
					String zoneCode = rsGst46b.getString("ZONE_CODE");
					String commname="ALL";
					//String zoneName = rsGst14aa.getString("ZONE_NAME");
					int col10A = rsGst46b.getInt("col10A");
					int col12A = rsGst46b.getInt("col12A");
					int col10B = rsGst46b.getInt("col10B");
					int col12B = rsGst46b.getInt("col12B");
					int numerator = rsGst46b.getInt("numerator");
					int denominator = rsGst46b.getInt("denominator");
					

					if (denominator != 0){
						total = rsGst46b.getDouble("total_score");
					}else{
						total = 0.00;
					}

					String absval = String.valueOf(numerator) + "/" + String.valueOf(denominator);


					//total = (col4 != 0) ? ((col10 / col4) * 100) : 0;

					rank = score.marks11b(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta=new GST4A(rsGst46b.getString("ZONE_NAME"),commname,totalScore,rank,absval,zoneCode,ra);
					allGstaList.add(gsta);
				}
			}else if (type.equalsIgnoreCase("commissary")) { //gst 11b
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String queryGst49 = "WITH cte AS ( "
					    + "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME,cc.COMM_CODE, "
					    + "           (11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, "
					    + "           (11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A "
					    + "    FROM mis_dla_gst_lgl_1a AS 11a "
					    + "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE "
					    + "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE "
					    + "    WHERE 11a.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 6 AND cc.ZONE_CODE = '" + zone_code + "'"
					    + "    "
					    + "), "
					    + "cte1 AS ( "
					    + "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME,cc.COMM_CODE, "
					    + "           (11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, "
					    + "           (11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B "
					    + "    FROM mis_dla_gst_lgl_1b AS 11b "
					    + "    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE "
					    + "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE "
					    + "    WHERE 11b.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 6 AND cc.ZONE_CODE = '" + zone_code + "'"
					    + "     "
					    + ") "
					    + "SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_NAME,cte.COMM_CODE, cte.col10A, cte.col12A, cte1.col10B, cte1.col12B, "
					    + "       (cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B) AS numerator, "
					    + "       (cte.col10A + cte1.col10B) AS denominator, "
					    + "       (((cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B)) / (cte.col10A + cte1.col10B)) AS total_score "
					    + "FROM cte "
					    + "INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE;";


				ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst49);

				while(rsGst46b.next()) {
					String ra= RelevantAspect.Gst11B_RA;
					String zoneCode = rsGst46b.getString("ZONE_CODE");
					String commname=rsGst46b.getString("COMM_NAME");
					//String zoneName = rsGst14aa.getString("ZONE_NAME");
					int col10A = rsGst46b.getInt("col10A");
					int col12A = rsGst46b.getInt("col12A");
					int col10B = rsGst46b.getInt("col10B");
					int col12B = rsGst46b.getInt("col12B");
					int numerator = rsGst46b.getInt("numerator");
					int denominator = rsGst46b.getInt("denominator");
					

					if (denominator != 0){
						total = rsGst46b.getDouble("total_score");
					}else{
						total = 0.00;
					}

					String absval = String.valueOf(numerator) + "/" + String.valueOf(denominator);


					//total = (col4 != 0) ? ((col10 / col4) * 100) : 0;

					rank = score.marks11b(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta=new GST4A(rsGst46b.getString("ZONE_NAME"),commname,totalScore,rank,absval,zoneCode,ra);
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
	//  http://localhost:8080/CbicAPI/cbic/gst11c?month_date=2023-04-01&type=zone
    //  http://localhost:8080/CbicAPI/cbic/gst11c?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst11C(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {

		List<GST4A> allGstaList = new ArrayList<>();
		GST4A gsta = null;
		int rank = 0;
		double total = 0.00;

		try {
			if (type.equalsIgnoreCase("zone")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);
				// Query string
				String queryGst49= "WITH cte AS ( " +
						"SELECT zc.ZONE_NAME, zc.ZONE_CODE, " +
						"SUM(11a.DEPARTMENT_DISPOSAL_NO + 11a.TAXPAYER_DISPOSAL_NO) AS col10 " +
						"FROM mis_dla_gst_lgl_1 11a " +
						"LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE " +
						"WHERE 11a.MM_YYYY = '" + month_date + "' AND 11a.FORUM_CODE = 7 " +
						"GROUP BY zc.ZONE_NAME, zc.ZONE_CODE), " +
						"cte1 AS ( " +
						"SELECT zc.ZONE_NAME, zc.ZONE_CODE, " +
						"SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col4 " +
						"FROM mis_dla_gst_lgl_1 11a " +
						"LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE " +
						"WHERE 11a.MM_YYYY = '" + prev_month_new + "' AND 11a.FORUM_CODE = 7 " +
						"GROUP BY zc.ZONE_NAME, zc.ZONE_CODE) " +
						"SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.col10, cte1.col4, " +
						"((cte.col10 / cte1.col4) * 100) AS total_score " +
						"FROM cte " +
						"INNER JOIN cte1 ON cte.ZONE_CODE = cte1.ZONE_CODE;";



				ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst49);

				while(rsGst46b.next()) {
					String ra= RelevantAspect.Gst11C_RA;
					String zoneCode = rsGst46b.getString("ZONE_CODE");
					String commname="ALL";
					//String zoneName = rsGst14aa.getString("ZONE_NAME");
					int col10 = rsGst46b.getInt("col10");
					int col4 = rsGst46b.getInt("col4");

					if (col4 != 0){
						total = rsGst46b.getDouble("total_score");
					}else{
						total = 0.00;
					}

					String absval = String.valueOf(col10) + "/" + String.valueOf(col4);


					//total = (col4 != 0) ? ((col10 / col4) * 100) : 0;

					rank = score.marks11c(total,col10);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta=new GST4A(rsGst46b.getString("ZONE_NAME"),commname,totalScore,rank,absval,zoneCode,ra);
					allGstaList.add(gsta);
				}
			}else if (type.equalsIgnoreCase("commissary")) { // gst 11c
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				// Query string
				String queryGst49= "WITH cte AS ( " +
						"SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, " +
						"(DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS col10 " +
						"FROM mis_dla_gst_lgl_1 AS 11a " +
						"LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE " +
						"WHERE 11a.MM_YYYY = '" + month_date + "' AND 11a.FORUM_CODE = 7 AND cc.ZONE_CODE = '" + zone_code + "' " +
						"), " +
						"cte1 AS ( " +
						"SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, " +
						"(DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4 " +
						"FROM mis_dla_gst_lgl_1 AS 11a " +
						"LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE " +
						"LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE " +
						"WHERE 11a.MM_YYYY = '" + prev_month_new + "' AND 11a.FORUM_CODE = 7 AND cc.ZONE_CODE = '" + zone_code + "' " +
						") " +
						"SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_NAME, cte.COMM_CODE, cte.col10, cte1.col4, " +
						"((cte.col10 / cte1.col4)) AS total_score " +
						"FROM cte " +
						"INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE;";




				ResultSet rsGst46b =GetExecutionSQL.getResult(queryGst49);

				while(rsGst46b.next()) {
					String ra= RelevantAspect.Gst11C_RA;
					String zoneCode = rsGst46b.getString("ZONE_CODE");
					String commname=rsGst46b.getString("COMM_NAME");
					//String zoneName = rsGst14aa.getString("ZONE_NAME");
					int col10 = rsGst46b.getInt("col10");
					int col4 = rsGst46b.getInt("col4");
					String absval = String.valueOf(col10) + "/" + String.valueOf(col4);

					if (col4 != 0){
						total = rsGst46b.getDouble("total_score");
					}else{
						total = 0.00;
					}


					rank = score.marks11c(total,col10);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta=new GST4A(rsGst46b.getString("ZONE_NAME"),commname,totalScore,rank,absval,zoneCode,ra);
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
	//  http://localhost:8080/CbicAPI/cbic/gst11d?month_date=2023-04-01&type=zone
	//  http://localhost:8080/CbicAPI/cbic/gst11d?month_date=2023-04-01&zone_code=70&type=commissary
	public Object getGst11D(@RequestParam String month_date,@RequestParam String type, @RequestParam(required = false) String zone_code) {
		List<GST4A> allGstaList = new ArrayList<>();
		GST4A gsta = null;
		int rank = 0;
		double total = 0.00;

		try {
			if (type.equalsIgnoreCase("zone")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);
				// Query string
				String queryGst49 = "WITH cte AS ( "
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, "
						+ "           SUM(11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, "
						+ "           SUM(11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A "
						+ "    FROM mis_dla_gst_lgl_1a AS 11a "
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE "
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE "
						+ "    WHERE 11a.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 7 "
						+ "    GROUP BY zc.ZONE_CODE "
						+ "), "
						+ "cte1 AS ( "
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, "
						+ "           SUM(11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, "
						+ "           SUM(11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B "
						+ "    FROM mis_dla_gst_lgl_1b AS 11b "
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE "
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE "
						+ "    WHERE 11b.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 7 "
						+ "    GROUP BY zc.ZONE_CODE"
						+ ") "
						+ "SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_NAME, cte.col10A, cte.col12A, cte1.col10B, cte1.col12B, "
						+ "       (cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B) AS numerator, "
						+ "       (cte.col10A + cte1.col10B) AS denominator, "
						+ "       (((cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B)) / (cte.col10A + cte1.col10B)) AS total_score "
						+ "FROM cte "
						+ "INNER JOIN cte1 ON cte.ZONE_CODE = cte1.ZONE_CODE;";


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
					int numerator = rsGst46b.getInt("numerator");
					int denominator = rsGst46b.getInt("denominator");


					if (denominator != 0){
						total = rsGst46b.getDouble("total_score");
					}else{
						total = 0.00;
					}

					String absval = String.valueOf(numerator) + "/" + String.valueOf(denominator);


					//total = (col4 != 0) ? ((col10 / col4) * 100) : 0;

					rank = score.marks11d(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta=new GST4A(rsGst46b.getString("ZONE_NAME"),"All",totalScore,rank,absval,zoneCode,ra);
					allGstaList.add(gsta);
				}
			}else if (type.equalsIgnoreCase("commissary")) {
				String prev_month_new = DateCalculate.getPreviousMonth(month_date);

				String queryGst49 = "WITH cte AS ( "
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME,cc.COMM_CODE, "
						+ "           (11a.DEPARTMENT_CLOSING_NO + 11a.TAXPAYER_CLOSING_NO) AS col10A, "
						+ "           (11a.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11a.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12A "
						+ "    FROM mis_dla_gst_lgl_1a AS 11a "
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE "
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE "
						+ "    WHERE 11a.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 7 AND cc.ZONE_CODE = '" + zone_code + "'"
						+ "    "
						+ "), "
						+ "cte1 AS ( "
						+ "    SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME,cc.COMM_CODE, "
						+ "           (11b.DEPARTMENT_CLOSING_NO + 11b.TAXPAYER_CLOSING_NO) AS col10B, "
						+ "           (11b.DEPARTMENT_BREAKUP_LESS_1YEAR_NO + 11b.TAXPAYER_BREAKUP_LESS_1YEAR_NO) AS col12B "
						+ "    FROM mis_dla_gst_lgl_1b AS 11b "
						+ "    LEFT JOIN mis_gst_commcode AS cc ON 11b.COMM_CODE = cc.COMM_CODE "
						+ "    LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE "
						+ "    WHERE 11b.MM_YYYY = '"+month_date+"' AND FORUM_CODE = 7 AND cc.ZONE_CODE = '" + zone_code + "'"
						+ "     "
						+ ") "
						+ "SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.COMM_NAME,cte.COMM_CODE, cte.col10A, cte.col12A, cte1.col10B, cte1.col12B, "
						+ "       (cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B) AS numerator, "
						+ "       (cte.col10A + cte1.col10B) AS denominator, "
						+ "       (((cte.col10A - cte.col12A) + (cte1.col10B - cte1.col12B)) / (cte.col10A + cte1.col10B)) AS total_score "
						+ "FROM cte "
						+ "INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE;";


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
					int numerator = rsGst46b.getInt("numerator");
					int denominator = rsGst46b.getInt("denominator");


					if (denominator != 0){
						total = rsGst46b.getDouble("total_score");
					}else{
						total = 0.00;
					}

					String absval = String.valueOf(numerator) + "/" + String.valueOf(denominator);


					//total = (col4 != 0) ? ((col10 / col4) * 100) : 0;

					rank = score.marks11d(total);
					String formattedTotal = String.format("%.2f", total);
					double totalScore = Double.parseDouble(formattedTotal);
					gsta=new GST4A(rsGst46b.getString("ZONE_NAME"),commname,totalScore,rank,absval,zoneCode,ra);
					allGstaList.add(gsta);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allGstaList;
	}
}