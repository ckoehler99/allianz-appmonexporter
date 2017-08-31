package com.dynatrace.appmon.expertservices.allianzappmonexporter.appmon.controller;

import java.util.ArrayList;
import java.util.List;

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
	
	public ReportController() {
		appMonController = new AppMonController();
		configController = ConfigController.getInstance();
		measureTranslations = configController.getTranslations();
	}

	public ArrayList<PrometheusMeasure> getPrometheusMeasuresForHost(String hostname) {
		ArrayList<PrometheusMeasure> measures = new ArrayList<PrometheusMeasure>();
		
		DashboardReport appMonMeasures = appMonController.getDashboardReportForHost(hostname);
		
		for(ChartDashlet dashlet : appMonMeasures.getCharDashlets()) {
			PrometheusMeasure prometheus = translateAppMonMeasureToPrometheus(dashlet);
			if(prometheus!=null) 
				measures.add(prometheus);
		}
		
		return measures;
		
	}
	
	private PrometheusMeasure translateAppMonMeasureToPrometheus(ChartDashlet dashlet) {
		PrometheusMeasure measure = null;
		Measure appMonMeasure = dashlet.getMeasures().get(0);
		String appMonMeasureName = dashlet.getName();
		
		for (MeasureTranslation translation : measureTranslations.getMeasureTranslations()) {
			if (translation.getDtname().equals(appMonMeasureName)) {
				measure = new PrometheusMeasure();
				measure.setMetricName(translation.getPtsname());
				measure.setMetricValue(appMonMeasure.getAvg().toString());
			}
		}
		
		return measure;
	}
}
