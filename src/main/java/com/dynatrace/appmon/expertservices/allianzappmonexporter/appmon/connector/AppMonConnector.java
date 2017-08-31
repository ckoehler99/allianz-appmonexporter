package com.dynatrace.appmon.expertservices.allianzappmonexporter.appmon.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

import com.dynatrace.appmon.expertservices.allianzappmonexporter.config.*;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.config.controller.ConfigController;


public class AppMonConnector {
	
	final static Logger logger = Logger.getLogger(AppMonConnector.class);
	private Config config;
	private ConfigController configController;
	
	public AppMonConnector() {
		configController = ConfigController.getInstance();
		config = configController.getConfig();
	}
	
	public Boolean isAppMonAvailable()
	{
		return true;
	}
	
	public String getAppMonDataForHost(String hostName)
	{
		HttpURLConnection connection;
		BufferedReader reader = null;
		try {
			if(config.getIsSSL())
				connection = (HttpsURLConnection) new URL(config.getCompleteURI()+config.getQueryString(hostName)).openConnection();
			else
				connection = (HttpURLConnection) new URL(config.getCompleteURI()+config.getQueryString(hostName)).openConnection();
			String encoded = Base64.getEncoder().encodeToString((config.getUser()+":"+config.getPass()).getBytes(StandardCharsets.UTF_8));
			connection.setRequestProperty("Authorization", "Basic " + encoded);
			
			reader =
					new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error while communicating with AppMon server (http/https properly configured?): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.fatal("Unable to connect to AppMon server: " + e.getMessage());
		}
		
		logger.debug(reader);
		
		StringBuffer response = new StringBuffer();
		String input;
		
		if (reader != null)
		{
			try {
				while ((input = reader.readLine()) != null){
				      response.append(input);
				   }
				reader.close(); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
		}
		
		return response.toString();
		
	}
	
	
}
