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

@WebServlet("/ViewPatientDetailsServlet")
public class ViewPatientDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HospitalDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new HospitalDAOImpl();
	}

	public ViewPatientDetailsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.print("<head>");
		out.print("<link rel='stylesheet' href='./css/form.css'>");
		out.print("</head>");


		try {
			int id = Integer.parseInt(request.getParameter("viewPatientId"));
			// HospitalDAO dao = new HospitalDAOImpl();
			Patient res = dao.getPatientById(id);

			RequestDispatcher rd = request.getRequestDispatcher("/patients");

			if (res != null) {
				out.print("<h2>Doctor Details:</h2>");
				out.print("<p>ID: " + res.getId() + "</p>");
				out.print("<p>NAME: " + res.getName() + "</p>");
				out.print("<p>AGE: " + res.getAge() + "</p>");
				out.print("<p>CONTACT: " + res.getContact() + "</p>");
				out.print("<p>AILMENT: " + res.getAilment() + "</p>");

				rd.include(request, response);
			}
		} catch (Exception e) {
			out.print("<h2>Error retrieving patient details.</h2>");
		}
	}
}
