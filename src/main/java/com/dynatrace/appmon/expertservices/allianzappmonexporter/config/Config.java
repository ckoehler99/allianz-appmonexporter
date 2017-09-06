package com.dynatrace.appmon.expertservices.allianzappmonexporter.config;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="dynatrace")
public class Config {
    public Config() {
    }
    private String host;
    private String port;
    private String user;
    private String pass;
    private String dashboard;
    private Boolean isSSL;
    private String profile;
    private String timeFrameUnit;
    private String timeFrameValue;

   public String getHost() {
		return host;
	}
   @XmlElement(name = "host")
	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}
	@XmlElement(name = "port")
	public void setPort(String port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}
	@XmlElement(name = "user")
	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}
	@XmlElement(name = "pass")
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getDashboard() {
		return dashboard;
	}
	@XmlElement(name = "dashboard")
	public void setDashboard(String dashboard) {
		this.dashboard = dashboard;
	}
	public Boolean getIsSSL() {
		return isSSL;
	}
	@XmlElement(name = "ssl")
	public void setIsSSL(Boolean isSSL) {
		this.isSSL = isSSL;
	}
	
	public String getProfile() {
		return profile;
	}
	@XmlElement(name = "profile")
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	public String getTimeFrameUnit() {
		return timeFrameUnit;
	}
	@XmlElement(name = "timeFrameUnit")
	public void setTimeFrameUnit(String timeFrameUnit) {
		this.timeFrameUnit = timeFrameUnit;
	}
	public String getTimeFrameValue() {
		return timeFrameValue;
	}
	@XmlElement(name = "timeFrameValue")
	public void setTimeFrameValue(String timeFrameValue) {
		this.timeFrameValue = timeFrameValue;
	}
	public String getBaseUri()
	{
		StringBuilder builder = new StringBuilder();
		String prot;
		prot = this.getIsSSL() ? "https" : "http";
		builder.append(prot);
		builder.append("://");
		builder.append(getHost());
		builder.append(":");
		builder.append(getPort());
		
		return builder.toString();
	}
	
	
	
	
    
}