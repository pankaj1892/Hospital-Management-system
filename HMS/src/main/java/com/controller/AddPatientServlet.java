package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.HospitalDAO;
import com.dao.HospitalDAOImpl;
import com.model.Patient;

@WebServlet("/AddPatientServlet")
public class AddPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HospitalDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new HospitalDAOImpl();
	}

	public AddPatientServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("patientName");
		int age = Integer.parseInt(request.getParameter("age"));
		String contact = request.getParameter("patientContact");
		String ailment = request.getParameter("ailment");

		Patient patient = new Patient(name, age, contact, ailment);

		// HospitalDAO dao = new HospitalDAOImpl();
		int result = dao.addPatient(patient);

		RequestDispatcher rd = request.getRequestDispatcher("/patients");
		if (result > 0) {
			out.print("<h2>Patient added successfully!</h2>");
			rd.include(request, response);
		} else {
			out.print("<h2>Failed to add patient!</h2>");
		}
	}
}
