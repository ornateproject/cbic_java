package com.ornates.cbic.model.response;

public class TotalScore {
    private String zoneName;
    private  String commName;
    private String zone_code;
    private  double totalScore;
    private String absval;
    private  Integer Zonal_rank;
    private  String gst;

    public TotalScore(String zoneName, String commName, String zone_code, double totalScore, String absval, Integer zonal_rank, String gst) {
        this.zoneName = zoneName;
        this.commName = commName;
        this.zone_code = zone_code;
        this.totalScore = totalScore;
        this.absval = absval;
        Zonal_rank = zonal_rank;
        this.gst = gst;
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
}
