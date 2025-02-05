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
import com.model.Doctor;

@WebServlet("/ViewDoctorDetailsServlet")
public class ViewDoctorDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HospitalDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new HospitalDAOImpl();
	}

	public ViewDoctorDetailsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			int id = Integer.parseInt(request.getParameter("viewDoctorId"));
			// HospitalDAO dao = new HospitalDAOImpl();
			Doctor doctor = dao.getDoctorById(id);
			
			RequestDispatcher rd = request.getRequestDispatcher("/doctors");
					

			if (doctor != null) {
				out.print("<h2>Doctor Details:</h2>");
				out.print("<p>ID: " + doctor.getId() + "</p>");
				out.print("<p>Name: " + doctor.getName() + "</p>");
				out.print("<p>Specialization: " + doctor.getSpecialization() + "</p>");
				out.print("<p>Contact: " + doctor.getContact() + "</p>");
				
				rd.include(request, response);
			} else {
				out.print("<h2>Doctor not found!</h2>");

			}
		} catch (Exception e) {
			out.print("<h2>Error retrieving doctor details.</h2>");
		}
	}
}
