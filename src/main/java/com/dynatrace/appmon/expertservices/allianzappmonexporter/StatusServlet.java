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
import com.dynatrace.appmon.expertservices.allianzappmonexporter.config.Config;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.config.controller.ConfigController;
import com.dynatrace.appmon.expertservices.allianzappmonexporter.model.DashboardReport;


public class StatusServlet extends HttpServlet {
    
   private static final long serialVersionUID = 1L;
 
   private Config config;
   private AppMonConnector connector;
    
   public StatusServlet() {
	   ConfigController configController = ConfigController.getInstance(); 
	   connector = new AppMonConnector();
	   config = configController.getConfig();
   }
 
   @Override
   protected void doGet(HttpServletRequest request,
           HttpServletResponse response) throws ServletException, IOException {
        
       ServletOutputStream out = response.getOutputStream();
        
       out.println("<html>");
       out.println("<head><title>Hello Servlet</title></head>");
        
       out.println("<body>");
       out.println("<h3>Hello World</h3>");
       //String result = connector.getAppMonData();
       //out.println(connector.getAppMonData());
       out.println("</body>");
       out.println("<html>");
       
   }
 
   @Override
   protected void doPost(HttpServletRequest request,
           HttpServletResponse response) throws ServletException, IOException {
       this.doGet(request, response);
   }

}
