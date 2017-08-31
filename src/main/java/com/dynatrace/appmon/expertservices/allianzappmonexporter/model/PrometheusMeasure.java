package com.dynatrace.appmon.expertservices.allianzappmonexporter.model;

public class PrometheusMeasure {
	// https://prometheus.io/docs/instrumenting/exposition_formats/
	
	private String metricName;
	private String metricValue;
	private String timestamp;
	public String getMetricName() {
		return metricName;
	}
	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}
	public String getMetricValue() {
		return metricValue;
	}
	public void setMetricValue(String metricValue) {
		this.metricValue = metricValue;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(getMetricName());
		builder.append(" ");
		builder.append(getMetricValue());
		
		return builder.toString();	
	}

}
