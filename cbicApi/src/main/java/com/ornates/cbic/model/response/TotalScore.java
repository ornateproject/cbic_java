package com.ornates.cbic.model.response;

public class TotalScore {
    private String zoneName;
    private  String commName;
    private  double totalScore;
    private  Integer Zonal_rank;

    public TotalScore(String zoneName, String commName, double totalScore, Integer zonal_rank) {
        this.zoneName = zoneName;
        this.commName = commName;
        this.totalScore = totalScore;
        Zonal_rank = zonal_rank;
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

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getZonal_rank() {
        return Zonal_rank;
    }

    public void setZonal_rank(Integer zonal_rank) {
        Zonal_rank = zonal_rank;
    }
}
