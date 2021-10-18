package com.devsec.research.deserialization;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Native
 */
@WebServlet("/native")
public class Native extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Native() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String serialPayload = null;
		
		try {
			serialPayload = request.getParameter("payload"); 
			InputStream is = new ByteArrayInputStream(Base64.getDecoder().decode(serialPayload));
			ObjectInputStream ois = new ObjectInputStream(is);
			
			Integer obj = (Integer)ois.readObject();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.getWriter().append("Payload: ").append(serialPayload);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
