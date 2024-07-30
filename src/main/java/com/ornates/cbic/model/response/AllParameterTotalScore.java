package com.ornates.cbic.model.response;

public class AllParameterTotalScore {
    private String zoneName;
    private String commName;
    private String zone_code;
    private double totalScore;
    private String padrameter_name;
    @Override
    public String toString() {
        return "AllParameterTotalScore [zoneName=" + zoneName + ", commName=" + commName + ", zone_code=" + zone_code
                + ", totalScore=" + totalScore + ", padrameter_name=" + padrameter_name + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }
    public AllParameterTotalScore(String zoneName, String commName, String zone_code, double totalScore,
                                  String padrameter_name) {
        super();
        this.zoneName = zoneName;
        this.commName = commName;
        this.zone_code = zone_code;
        this.totalScore = totalScore;
        this.padrameter_name = padrameter_name;
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
    public String getPadrameter_name() {
        return padrameter_name;
    }
    public void setPadrameter_name(String padrameter_name) {
        this.padrameter_name = padrameter_name;
    }


}

// ---------------------------------


/* public class AllParameterTotalScore {
    private String zoneName;
    private String commName;
    private String zone_code;
    private double totalScore;
    private String p1;
    private String p2;
    private String p3;
    private String p4;
    private String p5;
    private String p6;
    private String p7;
    private String p8;
    private String p9;
    private String p10;
    private String p11;


	public AllParameterTotalScore() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AllParameterTotalScore(String zoneName, String commName, String zone_code, double totalScore, String p1,
			String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10,
			String p11) {
		super();
		this.zoneName = zoneName;
		this.commName = commName;
		this.zone_code = zone_code;
		this.totalScore = totalScore;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
		this.p5 = p5;
		this.p6 = p6;
		this.p7 = p7;
		this.p8 = p8;
		this.p9 = p9;
		this.p10 = p10;
		this.p11 = p11;
	}
	@Override
	public String toString() {
		return "AllParameterTotalScore [zoneName=" + zoneName + ", commName=" + commName + ", zone_code=" + zone_code
				+ ", totalScore=" + totalScore + ", p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + ", p4=" + p4 + ", p5="
				+ p5 + ", p6=" + p6 + ", p7=" + p7 + ", p8=" + p8 + ", p9=" + p9 + ", p10=" + p10 + ", p11=" + p11
				+ "]";
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
	public String getP1() {
		return p1;
	}
	public void setP1(String p1) {
		this.p1 = p1;
	}
	public String getP2() {
		return p2;
	}
	public void setP2(String p2) {
		this.p2 = p2;
	}
	public String getP3() {
		return p3;
	}
	public void setP3(String p3) {
		this.p3 = p3;
	}
	public String getP4() {
		return p4;
	}
	public void setP4(String p4) {
		this.p4 = p4;
	}
	public String getP5() {
		return p5;
	}
	public void setP5(String p5) {
		this.p5 = p5;
	}
	public String getP6() {
		return p6;
	}
	public void setP6(String p6) {
		this.p6 = p6;
	}
	public String getP7() {
		return p7;
	}
	public void setP7(String p7) {
		this.p7 = p7;
	}
	public String getP8() {
		return p8;
	}
	public void setP8(String p8) {
		this.p8 = p8;
	}
	public String getP9() {
		return p9;
	}
	public void setP9(String p9) {
		this.p9 = p9;
	}
	public String getP10() {
		return p10;
	}
	public void setP10(String p10) {
		this.p10 = p10;
	}
	public String getP11() {
		return p11;
	}
	public void setP11(String p11) {
		this.p11 = p11;
	}
    */







