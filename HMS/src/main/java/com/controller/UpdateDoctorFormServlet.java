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

@WebServlet("/UpdateDoctorFormServlet")
public class UpdateDoctorFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HospitalDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new HospitalDAOImpl();
	}

	public UpdateDoctorFormServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<head>");
		out.print("<link rel='stylesheet' href='./css/form.css'>");
		out.print("</head>");

		int id = Integer.parseInt(request.getParameter("id"));

		Doctor doctor = dao.getDoctorById(id);

		RequestDispatcher rd = request.getRequestDispatcher("/doctors");

		if (doctor != null) {

			out.print("<form action ='updateDoctor' method='get'>");
			out.print("Id<input type = 'text' name='id' value='" + doctor.getId() + "'readonly > <br>");
			out.print("Enter new NAME<input type ='text' name='doctorName' value='" + doctor.getName() + "'><br>");
			out.print("Enter new Specialization <input type ='text' name='specialization' value='"
					+ doctor.getSpecialization() + "'><br>");
			out.print("Enter new Contact <input type ='text' name='contact' value='" + doctor.getContact() + "'><br>");
			out.print("<input type='submit' value='UPDATE'> <br>");
			out.print("</form>");

			rd.include(request, response);

		} else {
			out.print("<h1>" + "Record Not Found" + "</h1>");
		}
	}

}
