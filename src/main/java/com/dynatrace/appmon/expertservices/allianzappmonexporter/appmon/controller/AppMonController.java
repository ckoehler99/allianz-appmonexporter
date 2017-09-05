package com.dynatrace.appmon.expertservices.allianzappmonexporter.appmon.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.dynatrace.appmon.expertservices.allianzappmonexporter.appmon.connector.AppMonConnector;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.model.DashboardReport;

public class AppMonController {
	
	private AppMonConnector appMonConnector;
	final static Logger logger = Logger.getLogger(AppMonConnector.class);
	
	public AppMonController()
	{
		appMonConnector = new AppMonConnector();
	}
	
	public DashboardReport getDashboardReportForHost(String hostName) {
		logger.info("Getting dashboard report for host: " + hostName);
		DashboardReport report = null;
		
		String result = appMonConnector.getAppMonDataForHost(hostName);
		
	       if (result!=null)
	       {
	    	   JAXBContext jaxbContext = null;
	  	     try {
	  	         jaxbContext = JAXBContext.newInstance(DashboardReport.class);
	  	         Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	  	           	         
	  	       InputStream stream = new ByteArrayInputStream(result.getBytes(StandardCharsets.UTF_8));
	  	     report = (DashboardReport) jaxbUnmarshaller.unmarshal(stream);

	  	     } 
	  	     catch (JAXBException e) {
	  	         e.printStackTrace();
	  	     }
	  	     finally {
	  	    	 
	  	     }
	       }
		
		return report;
	}
	
	public String getAppMonStatus() {
		return appMonConnector.getAppMonStatus();
	}

}
