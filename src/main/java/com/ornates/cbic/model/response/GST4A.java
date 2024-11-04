package com.ornates.cbic.model.response;

import java.io.Serializable;

public class GST4A implements Serializable {

	private String zone_name;
	private String commissionerate_name;
	private Double total_score;
	private String absolutevale;
	private String zone_code;
	private String relevant_aspect;
	private Integer Zonal_rank;
	private String gst;
	private Integer way_to_grade;
	private Integer insentavization;
	private double sub_parameter_weighted_average;

	public GST4A(String zone_name, String commissionerate_name, Double total_score, String absolutevale,
				 String zone_code, String relevant_aspect, Integer zonal_rank, String gst, Integer way_to_grade,
				 Integer insentavization, double sub_parameter_weighted_average) {
		this.zone_name = zone_name;
		this.commissionerate_name = commissionerate_name;
		this.total_score = total_score;
		this.absolutevale = absolutevale;
		this.zone_code = zone_code;
		this.relevant_aspect = relevant_aspect;
		Zonal_rank = zonal_rank;
		this.gst = gst;
		this.way_to_grade = way_to_grade;
		this.insentavization = insentavization;
		this.sub_parameter_weighted_average = sub_parameter_weighted_average;
	}

	@Override
	public String toString() {
		return "GST4A{" +
				"zone_name='" + zone_name + '\'' +
				", commissionerate_name='" + commissionerate_name + '\'' +
				", total_score=" + total_score +
				", absolutevale='" + absolutevale + '\'' +
				", zone_code='" + zone_code + '\'' +
				", relevant_aspect='" + relevant_aspect + '\'' +
				", Zonal_rank=" + Zonal_rank +
				", gst='" + gst + '\'' +
				", way_to_grade=" + way_to_grade +
				", insentavization=" + insentavization +
				", sub_parameter_weighted_average=" + sub_parameter_weighted_average +
				'}';
	}

	public String getZone_name() {
		return zone_name;
	}

	public void setZone_name(String zone_name) {
		this.zone_name = zone_name;
	}

	public String getCommissionerate_name() {
		return commissionerate_name;
	}

	public void setCommissionerate_name(String commissionerate_name) {
		this.commissionerate_name = commissionerate_name;
	}

	public Double getTotal_score() {
		return total_score;
	}

	public void setTotal_score(Double total_score) {
		this.total_score = total_score;
	}

	public String getAbsolutevale() {
		return absolutevale;
	}

	public void setAbsolutevale(String absolutevale) {
		this.absolutevale = absolutevale;
	}

	public String getZone_code() {
		return zone_code;
	}

	public void setZone_code(String zone_code) {
		this.zone_code = zone_code;
	}

	public String getRelevant_aspect() {
		return relevant_aspect;
	}

	public void setRelevant_aspect(String relevant_aspect) {
		this.relevant_aspect = relevant_aspect;
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
