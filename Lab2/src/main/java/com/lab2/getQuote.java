package com.lab2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class getQuote
 */
public class getQuote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getQuote() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		String socketType = request.getParameter("socketType");
		String quntity = request.getParameter("quentity");
		String customerName = request.getParameter("customerName");
		String customerEmail = request.getParameter("customerEmail");
		
		int quantityInt = 0;
		boolean isValid = true;
		String errorMessage = "";
		
		try {
			quantityInt = Integer.parseInt(quntity);
            if (quantityInt <= 0) {
                isValid = false;
                errorMessage = "Quantity must be a positive number.";
            }
        } catch (NumberFormatException e) {
        	isValid = false;
            errorMessage = "Invalid quantity. Please enter a numeric value.";
        }
		
		if (customerName == null || customerName.trim().isEmpty()) {
            isValid = false;
            errorMessage += "Name cannot be empty.";
        }
        
        if (customerEmail == null || customerEmail.trim().isEmpty()) {
            isValid = false;
            errorMessage += "Email cannot be empty.";
        }

        if (isValid) {
            double pricePerUnit = 10;
            if(socketType.equals("typeA")) {
            	pricePerUnit = 100;
            }else if(socketType.equals("typeB")) {
            	pricePerUnit = 50;            
            }else if(socketType.equals("typeC")) {
            	pricePerUnit = 25;
            }
            else {
            	pricePerUnit = 0;
            }
            double totalCost = quantityInt * pricePerUnit;
            
            response.getWriter().println("<h2>Quote Details</h2>");
            response.getWriter().println("<p>Customer Name: " + customerName + "</p>");
            response.getWriter().println("<p>Customer Email: " + customerEmail + "</p>");
            response.getWriter().println("<p>Socket Type: " + socketType + "</p>");
            response.getWriter().println("<p>Quantity: " + quantityInt + "</p>");
            response.getWriter().println("<p>Total Cost: $" + totalCost + "</p>");
        } else {
            
            response.getWriter().println("<h2>Error</h2>");
            response.getWriter().println("<p>" + errorMessage + "</p>");
            response.getWriter().println("<a href='index.html'>Go Back</a>");
        }
	}

}
