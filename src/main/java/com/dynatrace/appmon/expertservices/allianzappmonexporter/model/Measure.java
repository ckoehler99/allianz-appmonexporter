package com.dynatrace.appmon.expertservices.allianzappmonexporter.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Measure {
	private String measure;
	private String aggregation;
	private Double avg;
	private Double min;
	private Double max;
	private Double sum;
	private Double count;
	private String unit;
	private ArrayList<Measurement> measurements;
	
	public String getMeasure() {
		return measure;
	}
	@XmlAttribute
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public String getAggregation() {
		return aggregation;
	}
	@XmlAttribute
	public void setAggregation(String aggregation) {
		this.aggregation = aggregation;
	}
	public Double getAvg() {
		return avg;
	}
	@XmlAttribute
	public void setAvg(Double avg) {
		this.avg = avg;
	}
	public Double getMin() {
		return min;
	}
	@XmlAttribute
	public void setMin(Double min) {
		this.min = min;
	}
	public Double getMax() {
		return max;
	}
	@XmlAttribute
	public void setMax(Double max) {
		this.max = max;
	}
	public Double getSum() {
		return sum;
	}
	@XmlAttribute
	public void setSum(Double sum) {
		this.sum = sum;
	}
	public Double getCount() {
		return count;
	}
	@XmlAttribute
	public void setCount(Double count) {
		this.count = count;
	}
	
	
	public String getUnit() {
		return unit;
	}
	@XmlAttribute
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public ArrayList<Measurement> getMeasurements() {
		return measurements;
	}
	@XmlElement(name = "measurement")
	public void setMeasurements(ArrayList<Measurement> measurements) {
		this.measurements = measurements;
	}
	
	

}
