package com.ornates.cbic.model.response;

import java.io.Serializable;

public class TotalScore implements Serializable {

    private String zoneName;
    private String commName;
    private String zone_code;
    private double totalScore;
    private String absval;
    private Integer Zonal_rank;
    private String gst;
    private String ra;
    private Integer way_to_grade;
    private Integer insentavization;
    private double sub_parameter_weighted_average;
    public TotalScore(String zoneName, String commName, String zone_code, double totalScore, String absval,
                      Integer zonal_rank, String gst, String ra, Integer way_to_grade, Integer insentavization,
                      double sub_parameter_weighted_average) {
        super();
        this.zoneName = zoneName;
        this.commName = commName;
        this.zone_code = zone_code;
        this.totalScore = totalScore;
        this.absval = absval;
        Zonal_rank = zonal_rank;
        this.gst = gst;
        this.ra = ra;
        this.way_to_grade = way_to_grade;
        this.insentavization = insentavization;
        this.sub_parameter_weighted_average = sub_parameter_weighted_average;
    }
    @Override
    public String toString() {
        return "TotalScore [zoneName=" + zoneName + ", commName=" + commName + ", zone_code=" + zone_code
                + ", totalScore=" + totalScore + ", absval=" + absval + ", Zonal_rank=" + Zonal_rank + ", gst=" + gst
                + ", ra=" + ra + ", way_to_grade=" + way_to_grade + ", insentavization=" + insentavization
                + ", sub_parameter_weighted_average=" + sub_parameter_weighted_average + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }
    public String getZoneName() {
        return zoneName;
    }
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }
    public String getCommName() {
        return commName;
    }
    public void setCommName(String commName) {
        this.commName = commName;
    }
    public String getZone_code() {
        return zone_code;
    }
    public void setZone_code(String zone_code) {
        this.zone_code = zone_code;
    }
    public double getTotalScore() {
        return totalScore;
    }
    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }
    public String getAbsval() {
        return absval;
    }
    public void setAbsval(String absval) {
        this.absval = absval;
    }
    public Integer getZonal_rank() {
        return Zonal_rank;
    }
    public void setZonal_rank(Integer zonal_rank) {
        Zonal_rank = zonal_rank;
    }
    public String getGst() {
        return gst;
    }
    public void setGst(String gst) {
        this.gst = gst;
    }
    public String getRa() {
        return ra;
    }
    public void setRa(String ra) {
        this.ra = ra;
    }
    public Integer getWay_to_grade() {
        return way_to_grade;
    }
    public void setWay_to_grade(Integer way_to_grade) {
        this.way_to_grade = way_to_grade;
    }
    public Integer getInsentavization() {
        return insentavization;
    }
    public void setInsentavization(Integer insentavization) {
        this.insentavization = insentavization;
    }
    public double getSub_parameter_weighted_average() {
        return sub_parameter_weighted_average;
    }
    public void setSub_parameter_weighted_average(double sub_parameter_weighted_average) {
        this.sub_parameter_weighted_average = sub_parameter_weighted_average;
    }


}
