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

@WebServlet("/AddDoctorServlet")
public class AddDoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HospitalDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new HospitalDAOImpl();
	}
	public AddDoctorServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("addDoctor");
		String speci = request.getParameter("specialization");
		String contact = request.getParameter("contact");

		Doctor doctor = new Doctor(name, speci, contact);

		//HospitalDAO dao = new HospitalDAOImpl();
		int res = dao.addDoctor(doctor);
		RequestDispatcher rd = request.getRequestDispatcher("/doctors");
		if (res > 0) {
			out.print("<h2>Success! Doctor added.</h2>");
			rd.include(request, response);
		} else {
			out.print("<h2>Failed to add doctor!</h2>");
		}
	}
}
