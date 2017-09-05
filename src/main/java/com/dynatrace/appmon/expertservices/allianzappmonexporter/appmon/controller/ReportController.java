package com.dynatrace.appmon.expertservices.allianzappmonexporter.appmon.controller;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.dynatrace.appmon.expertservices.allianzappmonexporter.appmon.connector.AppMonConnector;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.config.MeasureTranslation;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.config.MeasureTranslations;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.config.controller.ConfigController;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.model.ChartDashlet;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.model.DashboardReport;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.model.Measure;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.model.PrometheusMeasure;

public class ReportController {
	
	private AppMonController appMonController;
	private ConfigController configController;
	private MeasureTranslations measureTranslations;
	final static Logger logger = Logger.getLogger(AppMonConnector.class);
	
	public ReportController() {
		appMonController = new AppMonController();
		configController = ConfigController.getInstance();
		measureTranslations = configController.getTranslations();
	}

	public ArrayList<PrometheusMeasure> getPrometheusMeasuresForHost(String hostname) {
		ArrayList<PrometheusMeasure> measures = new ArrayList<PrometheusMeasure>();
		
		DashboardReport appMonMeasures = appMonController.getDashboardReportForHost(hostname);
		if(appMonMeasures != null) {
			for(ChartDashlet dashlet : appMonMeasures.getCharDashlets()) {
				PrometheusMeasure prometheus = translateAppMonMeasureToPrometheus(dashlet);
				if(prometheus!=null) {
					measures.add(prometheus);
					logger.info("Measure found " + prometheus.getMetricName());
				}
				else
					logger.warn("No measure found for " + hostname + ", measure " + dashlet.getName());
			}
		}
		else 
			logger.warn("No measures found for host: " + hostname);
		
		
		
		return measures;
		
	}
	
	public String getAppMonStatus() {
		String status = "";
		status = appMonController.getAppMonStatus();
		return status;
	}
	
	private PrometheusMeasure translateAppMonMeasureToPrometheus(ChartDashlet dashlet) {
		logger.info("Translating measure to prometheus for " + dashlet.getName());
		PrometheusMeasure measure = null;
		if(dashlet.getMeasures() != null && !dashlet.getMeasures().isEmpty())
		{
			Measure appMonMeasure = dashlet.getMeasures().get(0);
			String appMonMeasureName = dashlet.getName();
			
			for (MeasureTranslation translation : measureTranslations.getMeasureTranslations()) {
				if (translation.getDtname().equals(appMonMeasureName)) {
					measure = new PrometheusMeasure();
					measure.setMetricName(translation.getPtsname());
					measure.setMetricValue(appMonMeasure.getAvg().toString());
					logger.info("Measure translation found for " + dashlet.getName());
					return measure;
				}
			}
		}
		else
			logger.warn("No measures in dashlet found for: " + dashlet.getName());
		
		return measure;
	}
}
