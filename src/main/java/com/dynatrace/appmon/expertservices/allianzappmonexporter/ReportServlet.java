package com.dynatrace.appmon.expertservices.allianzappmonexporter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.dynatrace.appmon.expertservices.allianzappmonexporter.appmon.connector.AppMonConnector;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.appmon.controller.ReportController;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.config.Config;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.config.controller.ConfigController;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.model.PrometheusMeasure;


public class ReportServlet extends HttpServlet {
	final static Logger logger = Logger.getLogger(AppMonConnector.class);
   private static final long serialVersionUID = 1L;
   
 
   private Config config;
   private ReportController reportController;
    
   public ReportServlet() {
	   ConfigController configController = ConfigController.getInstance();
	   reportController = new ReportController();
	   config = configController.getConfig();
   }
 
   @Override
   protected void doGet(HttpServletRequest request,
           HttpServletResponse response) throws ServletException, IOException {
	   
       ServletOutputStream out = response.getOutputStream();
       
       String hostnameFromQuery = request.getParameter("hostname");
       logger.info("Request received for Host: " +hostnameFromQuery);
       if(hostnameFromQuery != null && !hostnameFromQuery.isEmpty()) {
    	   ArrayList<PrometheusMeasure> prometheusMeasures = getPrometheusMeasures(hostnameFromQuery);
           if(prometheusMeasures.isEmpty()) {
        	   out.println("No metrics found for host.");
        	   out.println("----------APPMON STATUS----------");
        	   out.println(reportController.getAppMonStatus());
           }
           else {
        	   for(PrometheusMeasure measure : prometheusMeasures) {
        		   out.println(measure.toString());
        	   }
           }
       }
       else
    	   out.println("No hostname querystring specified.");
       
       
   }
 
   @Override
   protected void doPost(HttpServletRequest request,
           HttpServletResponse response) throws ServletException, IOException {
       this.doGet(request, response);
   }
   
   private ArrayList<PrometheusMeasure> getPrometheusMeasures(String hostname) {
	   
	   ArrayList<PrometheusMeasure> prometheusMeasures =  reportController.getPrometheusMeasuresForHost(hostname);
	   /*StringBuilder builder = new StringBuilder();
	   for(PrometheusMeasure measure : prometheusMeasures) {
		   builder.append(measure.toString());
		   builder.append("<br\\>");
	   }*/
	   
	   return prometheusMeasures;
   }

}
