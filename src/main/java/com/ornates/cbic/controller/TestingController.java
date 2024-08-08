//package com.ornates.cbic.controller;
//
//import com.ornates.cbic.dao.result.GetExecutionSQL;
//import com.ornates.cbic.model.response.GST4A;
//import com.ornates.cbic.service.DateCalculate;
//import com.ornates.cbic.service.RelevantAspect;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestingController {
//    @ResponseBody
//    @RequestMapping(value = "/gst11a")
//    //  http://localhost:8080/cbicApi/cbic/gst11a?month_date=2023-04-01&type=zone
//    //  http/localhost:8080/cbicApi/cbic/gst11a?month_date=2023-04-01&zone_code=70&type=commissary
//    //	  http://localhost:8080/cbicApi/cbic/gst11a?month_date=2023-04-01&type=all_commissary
//    public Object getGst11A(@RequestParam String month_date, @RequestParam String type, @RequestParam(required = false) String zone_code) {
//        List<GST4A> allGstaList = new ArrayList<>();
//        GST4A gsta = null;
//        int rank = 0;
//        double total = 0.00;
//
//        try {
//
//            if (type.equalsIgnoreCase("zone")) {
//                String prev_month_new = DateCalculate.getPreviousMonth(month_date);
//
//                String queryTotalPending = "WITH cte AS (" +
//                        "SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS col10 " +
//                        "FROM mis_dla_gst_lgl_1 11a " +
//                        "LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE " +
//                        "LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE " +
//                        "WHERE 11a.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6 " +
//                        "GROUP BY zc.ZONE_CODE), " +
//                        "cte1 AS (" +
//                        "SELECT zc.ZONE_NAME, zc.ZONE_CODE, SUM(DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4 " +
//                        "FROM mis_dla_gst_lgl_1 11a " +
//                        "LEFT JOIN mis_gst_commcode cc ON 11a.COMM_CODE = cc.COMM_CODE " +
//                        "LEFT JOIN mis_gst_zonecode zc ON cc.ZONE_CODE = zc.ZONE_CODE " +
//                        "WHERE 11a.MM_YYYY = '" + prev_month_new + "' AND FORUM_CODE = 6 " +
//                        "GROUP BY zc.ZONE_CODE) " +
//                        "SELECT cte.ZONE_NAME, cte.ZONE_CODE, cte.col10, cte1.col4, ((cte.col10 / cte1.col4)) AS total_score " +
//                        "FROM cte " +
//                        "INNER JOIN cte1 ON cte.ZONE_CODE = cte1.ZONE_CODE";
//                ResultSet rsTotalPending = GetExecutionSQL.getResult(queryTotalPending);
//
//                while (rsTotalPending.next()) {
//                    String ra= RelevantAspect.Gst11A_RA;
//                    String zoneName = rsTotalPending.getString("ZONE_NAME");
//                    String zoneCode = rsTotalPending.getString("ZONE_CODE");
//                    String commname = "ALL";
//                    double col10 = rsTotalPending.getDouble("col10");
//                    double col4 = rsTotalPending.getDouble("col4");
//                    int Zonal_rank = 0;
//                    String gst = "no";
//                    int way_to_grade = 0;
//                    int insentavization = 0;
//                    int sub_parameter_weighted_average = 0;
//                    String absval=String.valueOf(col10)+"/"+String.valueOf(col4);
//                    total = (col4 != 0) ? (((col10 * 100) / col4) ) : 0;
//
//                    rank = score.marks11a(total,col10);
//                    String formattedTotal = String.format("%.2f", total);
//                    double totalScore = Double.parseDouble(formattedTotal);
//                    gsta = new GST4A(zoneName, commname, totalScore,absval,zoneCode,ra,
//                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
//                    allGstaList.add(gsta);
//                }
//            }else if (type.equalsIgnoreCase("commissary")) { //gst 11a
//                String prev_month_new = DateCalculate.getPreviousMonth(month_date);
//
//                String queryTotalPending = "WITH cte AS ( " +
//                        "SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, " +
//                        "(DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS col10 " +
//                        "FROM mis_dla_gst_lgl_1 AS 11a " +
//                        "LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE " +
//                        "LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE " +
//                        "WHERE 11a.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6 AND cc.ZONE_CODE = '" + zone_code + "'),cte1 AS ( " +
//                        "SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, " +
//                        "(DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4 " +
//                        "FROM mis_dla_gst_lgl_1 AS 11a " +
//                        "LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE " +
//                        "LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE WHERE " +
//                        "11a.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6 AND cc.ZONE_CODE = '" + zone_code + "') " +
//                        "SELECT cte.ZONE_NAME,cte.ZONE_CODE,cte.COMM_NAME,cte.COMM_CODE,cte.col10,cte1.col4, " +
//                        "((cte.col10 / cte1.col4) ) AS total_score FROM cte " +
//                        "INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE";
//
//
//                ResultSet rsTotalPending = GetExecutionSQL.getResult(queryTotalPending);
//
//                while (rsTotalPending.next()) {
//                    String ra = RelevantAspect.Gst11A_RA;
//                    String zoneName = rsTotalPending.getString("ZONE_NAME");
//                    String zoneCode = rsTotalPending.getString("ZONE_CODE");
//                    String commname = rsTotalPending.getString("COMM_NAME");
//                    double col10 = rsTotalPending.getDouble("col10");
//                    double col4 = rsTotalPending.getDouble("col4");
//                    int Zonal_rank = 0;
//                    String gst = "no";
//                    int way_to_grade = 0;
//                    int insentavization = 0;
//                    int sub_parameter_weighted_average = 0;
//                    total = (col4 != 0) ? ((col10 / col4)) : 0;
//                    rank = score.marks11a(total, col10);
//                    String absval = String.valueOf(col10) + "/" + String.valueOf(col4);
//
//                    total = (col4 != 0) ? (col10 * 100 / col4) : 0;
//
//                    rank = score.marks11a(total, col10);
//                    String formattedTotal = String.format("%.2f", total);
//                    double totalScore = Double.parseDouble(formattedTotal);
//                    gsta = new GST4A(rsTotalPending.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
//                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
//                    allGstaList.add(gsta);
//                }
//            }else if (type.equalsIgnoreCase("all_commissary")) { //gst 11a
//                String prev_month_new = DateCalculate.getPreviousMonth(month_date);
//
//                String queryTotalPending = "WITH cte AS ( " +
//                        "SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, " +
//                        "(DEPARTMENT_DISPOSAL_NO + TAXPAYER_DISPOSAL_NO) AS col10 " +
//                        "FROM mis_dla_gst_lgl_1 AS 11a " +
//                        "LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE " +
//                        "LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE " +
//                        "WHERE 11a.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6 ),cte1 AS ( " +
//                        "SELECT zc.ZONE_NAME, zc.ZONE_CODE, cc.COMM_NAME, cc.COMM_CODE, " +
//                        "(DEPARTMENT_CLOSING_NO + TAXPAYER_CLOSING_NO) AS col4 " +
//                        "FROM mis_dla_gst_lgl_1 AS 11a " +
//                        "LEFT JOIN mis_gst_commcode AS cc ON 11a.COMM_CODE = cc.COMM_CODE " +
//                        "LEFT JOIN mis_gst_zonecode AS zc ON cc.ZONE_CODE = zc.ZONE_CODE WHERE " +
//                        "11a.MM_YYYY = '" + month_date + "' AND FORUM_CODE = 6 ) " +
//                        "SELECT cte.ZONE_NAME,cte.ZONE_CODE,cte.COMM_NAME,cte.COMM_CODE,cte.col10,cte1.col4, " +
//                        "((cte.col10 / cte1.col4) ) AS total_score FROM cte " +
//                        "INNER JOIN cte1 ON cte.COMM_CODE = cte1.COMM_CODE";
//
//
//                ResultSet rsTotalPending = GetExecutionSQL.getResult(queryTotalPending);
//
//                while (rsTotalPending.next()) {
//                    String ra = RelevantAspect.Gst11A_RA;
//                    String zoneName = rsTotalPending.getString("ZONE_NAME");
//                    String zoneCode = rsTotalPending.getString("ZONE_CODE");
//                    String commname = rsTotalPending.getString("COMM_NAME");
//                    double col10 = rsTotalPending.getDouble("col10");
//                    double col4 = rsTotalPending.getDouble("col4");
//                    int Zonal_rank = 0;
//                    String gst = "no";
//                    int way_to_grade = 0;
//                    int insentavization = 0;
//                    int sub_parameter_weighted_average = 0;
//                    total = (col4 != 0) ? ((col10 * 100 / col4)) : 0;
//                    rank = score.marks11a(total, col10);
//                    String absval = String.valueOf(col10) + "/" + String.valueOf(col4);
//
//                    total = (col4 != 0) ? (col10 / col4) : 0;
//
//                    rank = score.marks11a(total, col10);
//                    String formattedTotal = String.format("%.2f", total);
//                    double totalScore = Double.parseDouble(formattedTotal);
//                    gsta = new GST4A(rsTotalPending.getString("ZONE_NAME"), commname, totalScore,absval,zoneCode,ra,
//                            Zonal_rank,gst,way_to_grade,insentavization,sub_parameter_weighted_average);
//                    allGstaList.add(gsta);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return allGstaList;
//    }
//}
