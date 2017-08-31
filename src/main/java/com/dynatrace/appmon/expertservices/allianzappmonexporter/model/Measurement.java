package com.dynatrace.appmon.expertservices.allianzappmonexporter.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Measurement {
	private String timestamp;
	private Double avg;
	private Double min;
	private Double max;
	private Double sum;
	private Double count;
	
	public String getTimestamp() {
		return timestamp;
	}
	@XmlAttribute
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
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
	
	
}
