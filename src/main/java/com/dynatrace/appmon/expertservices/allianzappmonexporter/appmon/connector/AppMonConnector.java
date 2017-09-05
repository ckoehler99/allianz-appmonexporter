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

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.log4j.Logger;

import com.dynatrace.appmon.expertservices.allianzappmonexporter.config.*;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.config.controller.ConfigController;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.model.HttpMethod;


public class AppMonConnector {
	
	final static Logger logger = Logger.getLogger(AppMonConnector.class);
	private Config config;
	private ConfigController configController;
		
	public AppMonConnector() {
		configController = ConfigController.getInstance();
		config = configController.getConfig();
	}
	
	public String getAppMonStatus()
	{
		String retString = "";
		String url = "/rest/management/version";
		retString = getAppMonData("GET", config.getCompleteUriFromPath(url));
		
		return retString;
	}
	
	private String getAppMonData(String httpMethod, String url)
	{
		StringBuffer response = new StringBuffer();
		BufferedReader reader = null;
		HttpURLConnection connection;
		if(httpMethod.equals("GET"))
		{
			try {
				if(config.getIsSSL()) {
					
					// Create all-trusting host name verifier
					HostnameVerifier allHostsValid = new HostnameVerifier() {
					    public boolean verify(String hostname, SSLSession session) {
					        return true;
					    }
					};
					// Install the all-trusting host verifier
					HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
					connection = (HttpsURLConnection) new URL(url).openConnection();
				}
				else
					connection = (HttpURLConnection) new URL(url).openConnection();
				String encoded = Base64.getEncoder().encodeToString((config.getUser()+":"+config.getPass()).getBytes(StandardCharsets.UTF_8));
				connection.setRequestProperty("Authorization", "Basic " + encoded);
				logger.info("AppMon Called: " + url);
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				logger.info("AppMon Response Code: " + connection.getResponseCode() + " - " + connection.getResponseMessage()  + " (detailed response in Debug logging)");
				int responseCode = connection.getResponseCode();
				if(responseCode != 200)
				{
					switch (responseCode){
					case 404: logger.error("404 - Make sure the dashboard has been properly imported."); break;
					case 401: logger.error("401 - Make sure the user [" + config.getUser() + "] has been created and has appropriate rights."); break;
					case 500: logger.error("500 - Internal Server Error - Something really went wrong"); break;
					case 503: logger.error("503 - AppMon Service Unavailable");
					}
					return response.toString();
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.append(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("Error while communicating with AppMon server (http/https properly configured?): " + e.getMessage());
				response.append(e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				logger.fatal("Unable to connect to AppMon server: " + e.getMessage());
				response.append(e.getMessage());
			}
		}
		
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
		logger.debug("AppMon Response: " + response);
		return response.toString();
	}
	
	public String getAppMonDataForHost(String hostName)
	{
		String response = "";
		String url = config.getCompleteURI() + config.getQueryString(hostName);
		response = getAppMonData("GET", url);
		return response;
	}
	
	
}
