package com.ornates.cbic.model.response;

import java.io.Serializable;

public class MonthlyYearlyScore implements Serializable {
	private String zoneName;
	private String commName;
	private String zone_code;
	private String padrameter_name;
	private double total_score_of_month;
	private double total_score_previous_month;
	private double total_score_previous_month_2;
	private double total_score_of_year;
	private double total_score_previous_year;
	private double total_score_previous_year_2;
	@Override
	public String toString() {
		return "MonthlyYearlyScore [zoneName=" + zoneName + ", commName=" + commName + ", zone_code=" + zone_code
				+ ", padrameter_name=" + padrameter_name + ", total_score_of_month=" + total_score_of_month
				+ ", total_score_previous_month=" + total_score_previous_month + ", total_score_previous_month_2="
				+ total_score_previous_month_2 + ", total_score_of_year=" + total_score_of_year
				+ ", total_score_previous_year=" + total_score_previous_year + ", total_score_previous_year_2="
				+ total_score_previous_year_2 + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public MonthlyYearlyScore(String zoneName, String commName, String zone_code, String padrameter_name,
							  double total_score_of_month, double total_score_previous_month, double total_score_previous_month_2,
							  double total_score_of_year, double total_score_previous_year, double total_score_previous_year_2) {
		super();
		this.zoneName = zoneName;
		this.commName = commName;
		this.zone_code = zone_code;
		this.padrameter_name = padrameter_name;
		this.total_score_of_month = total_score_of_month;
		this.total_score_previous_month = total_score_previous_month;
		this.total_score_previous_month_2 = total_score_previous_month_2;
		this.total_score_of_year = total_score_of_year;
		this.total_score_previous_year = total_score_previous_year;
		this.total_score_previous_year_2 = total_score_previous_year_2;
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
	public String getPadrameter_name() {
		return padrameter_name;
	}
	public void setPadrameter_name(String padrameter_name) {
		this.padrameter_name = padrameter_name;
	}
	public double getTotal_score_of_month() {
		return total_score_of_month;
	}
	public void setTotal_score_of_month(double total_score_of_month) {
		this.total_score_of_month = total_score_of_month;
	}
	public double getTotal_score_previous_month() {
		return total_score_previous_month;
	}
	public void setTotal_score_previous_month(double total_score_previous_month) {
		this.total_score_previous_month = total_score_previous_month;
	}
	public double getTotal_score_previous_month_2() {
		return total_score_previous_month_2;
	}
	public void setTotal_score_previous_month_2(double total_score_previous_month_2) {
		this.total_score_previous_month_2 = total_score_previous_month_2;
	}
	public double getTotal_score_of_year() {
		return total_score_of_year;
	}
	public void setTotal_score_of_year(double total_score_of_year) {
		this.total_score_of_year = total_score_of_year;
	}
	public double getTotal_score_previous_year() {
		return total_score_previous_year;
	}
	public void setTotal_score_previous_year(double total_score_previous_year) {
		this.total_score_previous_year = total_score_previous_year;
	}
	public double getTotal_score_previous_year_2() {
		return total_score_previous_year_2;
	}
	public void setTotal_score_previous_year_2(double total_score_previous_year_2) {
		this.total_score_previous_year_2 = total_score_previous_year_2;
	}



}
