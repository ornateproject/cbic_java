package com.ornates.cbic.model.response;

public class GST4A {
	
	private String zone_name;
	private String commissionerate_name;
	private Double total_score;
	private Integer rank;
	private String absolutevale;
	private String zone_code;
	private String relevant_aspect;
	
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
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
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
	public GST4A(String zone_name, String commissionerate_name, Double total_score, Integer rank, String absolutevale,
			String zone_code, String relevant_aspect) {
		super();
		this.zone_name = zone_name;
		this.commissionerate_name = commissionerate_name;
		this.total_score = total_score;
		this.rank = rank;
		this.absolutevale = absolutevale;
		this.zone_code = zone_code;
		this.relevant_aspect = relevant_aspect;
	}
	public GST4A() {
		super();
	}
	
	
	

}
