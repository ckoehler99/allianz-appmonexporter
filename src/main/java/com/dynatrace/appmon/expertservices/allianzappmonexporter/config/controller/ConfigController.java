package com.dynatrace.appmon.expertservices.allianzappmonexporter.config.controller;

import java.io.InputStream;

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
}
