package com.dynatrace.appmon.expertservices.allianzappmonexporter.config.controller;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.dynatrace.appmon.expertservices.allianzappmonexporter.config.Config;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.config.MeasureTranslations;


public class ConfigController {
	
	private Config config;
	private MeasureTranslations measureTranslations;
	
	private static ConfigController instance = null;
	
	protected ConfigController () {
		setConfig();
		setMeasureTranslations();
	}
	
	
	public static ConfigController getInstance() {
		if(instance == null) {
			instance = new ConfigController();
		}
		return instance;
	}
	
	public Config getConfig() {
		return this.config;
	}
	
	public MeasureTranslations getTranslations() {
		return this.measureTranslations;
	}
	
	private Config setConfig()
	{
		JAXBContext jaxbContext = null;
	     try {
	         jaxbContext = JAXBContext.newInstance(Config.class);
	         Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	         
	         InputStream stream = getClass().getResourceAsStream("/settings.xml");
	         this.config = (Config) jaxbUnmarshaller.unmarshal(stream);

	     } 
	     catch (JAXBException e) {
	         e.printStackTrace();
	     }
	     finally {
	    	 
	     }
	     
	     return config;
	     
	}
	
	private void setMeasureTranslations() {
		JAXBContext jaxbContext = null;
	     try {
	         jaxbContext = JAXBContext.newInstance(MeasureTranslations.class);
	         Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	         
	         InputStream stream = getClass().getResourceAsStream("/measuretranslations.xml");
	         this.measureTranslations = (MeasureTranslations) jaxbUnmarshaller.unmarshal(stream);

	     } 
	     catch (JAXBException e) {
	    	 
	         e.printStackTrace();
	     }
	     finally {
	    	 
	     }
	}
	
	public String getAppMonQueryStringForHost(String hostName)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("?");
		builder.append("source=live:");
		builder.append(getEncodedText(config.getProfile()));
		builder.append("&");
		builder.append("filter=hf:Host?");
		builder.append(hostName);
		builder.append("&");
		builder.append("filter=tf:OffSetTimeFrame?");
		builder.append(config.getTimeFrameValue());
		builder.append(":");
		builder.append(config.getTimeFrameUnit());
		builder.append("&type=xml");
		return builder.toString();
	}
	
	private String getEncodedText(String input) {
		String output = "";
		try {
			output = URLEncoder.encode(input, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	
	public String getCompleteURI()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(config.getBaseUri());
		builder.append("/rest/management/reports/create/");
		builder.append(getEncodedText(config.getDashboard()));
		
		return builder.toString();
	}
	
	public String getCompleteUriFromPath(String path) {
		StringBuilder builder = new StringBuilder();
		builder.append(config.getBaseUri());
		builder.append(path);
		return builder.toString();
	}
}
