package com.dynatrace.appmon.expertservices.allianzappmonexporter.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class ChartDashlet {
	private ArrayList<Measure> measures;
	private String name;

	public ArrayList<Measure> getMeasures() {
		return measures;
	}

	@XmlElementWrapper(name = "measures")
	@XmlElement(name = "measure")
	public void setMeasures(ArrayList<Measure> measures) {
		this.measures = measures;
	}

	public String getName() {
		return name;
	}
@XmlAttribute(name="name")
	public void setName(String name) {
		this.name = name;
	}
	
	
}
