package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.HospitalDAO;
import com.dao.HospitalDAOImpl;

//@WebServlet("/CreateBillServlet")
public class CreateBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HospitalDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new HospitalDAOImpl();
	}

	public CreateBillServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			int patientId = Integer.parseInt(request.getParameter("patientId"));
			double amount = Double.parseDouble(request.getParameter("amount"));

			// HospitalDAO dao = new HospitalDAOImpl();
			int result = dao.createBill(patientId, amount);

			if (result > 0) {
				out.print("<h2>Bill created successfully!</h2>");
			} else {
				out.print("<h2>Failed to create bill!</h2>");
			}
		} catch (Exception e) {
			out.print("<h2>Error: " + e.getMessage() + "</h2>");
		}
	}
}
