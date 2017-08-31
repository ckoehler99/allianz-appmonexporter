package com.dynatrace.appmon.expertservices.allianzappmonexporter.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="dashboardreport")
public class DashboardReport {
	private String name;
	private String version;
	private ArrayList<ChartDashlet> charDashlets;
	
	public String getName() {
		return name;
	}
	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	@XmlAttribute
	public void setVersion(String version) {
		this.version = version;
	}
	public ArrayList<ChartDashlet> getCharDashlets() {
		return charDashlets;
	}
	@XmlElementWrapper(name = "data")
	@XmlElement(name = "chartdashlet")
	public void setCharDashlets(ArrayList<ChartDashlet> charDashlets) {
		this.charDashlets = charDashlets;
	}
	
	

}
