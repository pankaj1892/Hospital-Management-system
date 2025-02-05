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

@WebServlet("/DeleteDoctorServlet")
public class DeleteDoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HospitalDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new HospitalDAOImpl();
	}

	public DeleteDoctorServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			int id = Integer.parseInt(request.getParameter("id"));

			// HospitalDAO dao = new HospitalDAOImpl();

			int res = dao.deleteDoctor(id);

			RequestDispatcher rd = request.getRequestDispatcher("/doctors");

			if (res > 0) {
				out.print("Delete Success ....");
				rd.include(request, response);

			} else {
				out.print("delete Fialed....");
			}

		} catch (Exception e) {
			System.out.println("DeleteDoctorServlet..." + e);
		}

	}

}
