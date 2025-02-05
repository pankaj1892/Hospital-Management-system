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

@WebServlet("/UpdateDoctorServlet")
public class UpdateDoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HospitalDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new HospitalDAOImpl();
	}

	public UpdateDoctorServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			int id = Integer.parseInt(request.getParameter("id"));

			String name = request.getParameter("doctorName");
			String spe = request.getParameter("specialization");
			String contact = request.getParameter("contact");

			Doctor doctor = new Doctor(id, name, spe, contact);
			// HospitalDAO dao = new HospitalDAOImpl();
			int res = dao.updateDoctor(doctor);

			RequestDispatcher rd = request.getRequestDispatcher("/doctors");

			if (res > 0) {
				out.print("Update SUCCESS.....");
				rd.include(request, response);
			} else {
				out.print("Update FAILED...");
			}

		} catch (Exception e) {
			System.out.println("UpdateDoctorServlet...." + e);
		}

	}
}
