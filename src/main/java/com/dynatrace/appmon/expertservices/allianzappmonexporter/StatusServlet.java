package com.dynatrace.appmon.expertservices.allianzappmonexporter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.dynatrace.appmon.expertservices.allianzappmonexporter.appmon.connector.AppMonConnector;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.appmon.controller.AppMonController;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.appmon.controller.ReportController;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.config.Config;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.config.controller.ConfigController;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.model.DashboardReport;


public class StatusServlet extends HttpServlet {
    
   private static final long serialVersionUID = 1L;
 
   private Config config;

   private ReportController reportController;
    
   public StatusServlet() {
	   ConfigController configController = ConfigController.getInstance(); 

	   reportController = new ReportController();
	   config = configController.getConfig();
   }
 
   @Override
   protected void doGet(HttpServletRequest request,
           HttpServletResponse response) throws ServletException, IOException {
        
       ServletOutputStream out = response.getOutputStream();
        
       out.println(reportController.getAppMonStatus());

       
   }
 
   @Override
   protected void doPost(HttpServletRequest request,
           HttpServletResponse response) throws ServletException, IOException {
       this.doGet(request, response);
   }

}
