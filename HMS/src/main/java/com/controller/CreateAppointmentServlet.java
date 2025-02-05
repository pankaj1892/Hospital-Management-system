package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.HospitalDAO;
import com.dao.HospitalDAOImpl;

//@WebServlet("/CreateAppointmentServlet")
public class CreateAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HospitalDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new HospitalDAOImpl();
	}

	public CreateAppointmentServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			int patientId = Integer.parseInt(request.getParameter("patientId"));
			int doctorId = Integer.parseInt(request.getParameter("doctorId"));
			String appointmentDate = request.getParameter("appointmentDate");

			int result = dao.createAppointment(id, patientId, doctorId, appointmentDate);
			
			RequestDispatcher rd = request.getRequestDispatcher("/allAppointment");

			if (result > 0) {
				out.print("<h2>Appointment created successfully!</h2>");
				rd.include(request, response);
			} else {
				out.print("<h2>Failed to create appointment!</h2>");
			}
		} catch (Exception e) {
			out.print("<h2>Error: " + e.getMessage() + "</h2>");
		}
	}
}
