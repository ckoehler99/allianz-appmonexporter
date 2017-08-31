package com.dynatrace.appmon.expertservices.allianzappmonexporter.config;

import javax.xml.bind.annotation.XmlAttribute;

public class MeasureTranslation {
	
	private String dtname;
	private String ptsname;
	public String getDtname() {
		return dtname;
	}
	@XmlAttribute(name="dtname")
	public void setDtname(String dtname) {
		this.dtname = dtname;
	}
	public String getPtsname() {
		return ptsname;
	}
	@XmlAttribute(name="ptsname")
	public void setPtsname(String ptsname) {
		this.ptsname = ptsname;
	}
	
	

}
