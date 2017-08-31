package com.dynatrace.appmon.expertservices.allianzappmonexporter.config;

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
	
	public String getCompleteURI()
	{
		StringBuilder builder = new StringBuilder();
		String prot;
		prot = this.getIsSSL() ? "https" : "http";
		builder.append(prot);
		builder.append("://");
		builder.append(getHost());
		builder.append(":");
		builder.append(getPort());
		builder.append("/rest/management/reports/create/");
		builder.append(getDashboard());
		
		return builder.toString();
	}
	
	public String getQueryString(String hostName)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("?filter=hf:Host?");
		builder.append(hostName);
		builder.append("&filter=tf:OffSetTimeFrame?1:MINUTES&type=xml");
		return builder.toString();
	}
    
}