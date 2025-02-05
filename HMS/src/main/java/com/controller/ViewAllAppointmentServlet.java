package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.HospitalDAO;
import com.dao.HospitalDAOImpl;
import com.model.Appointment;


@WebServlet("/ViewAllAppointmentServlet")
public class ViewAllAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HospitalDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new HospitalDAOImpl();
	}

	public ViewAllAppointmentServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		List<Appointment> list = dao.getAllAppointments();

		Iterator<Appointment> itr = list.iterator();

		out.print("<head>");
		out.print("<link rel='stylesheet' href='./css/table.css'>");
		out.print("</head>");
		out.print("<table id='hms' >");
		out.print("<tr>");
		out.print("<th> ID </th>");
		out.print("<th> Patient ID </th>");
		out.print("<th> Doctor ID </th>");
		out.print("<th> Date </th>");
		out.print("<th> REMOVE </th>");
		out.print("</tr>");

		while (itr.next() != null) {
			Appointment app = (Appointment) itr.next();
			out.print("<tr>");
			out.print("<td>" + app.getId() + "</td>");
			out.print("<td>" + app.getPatientId() + "</td>");
			out.print("<td>" + app.getDoctorId() + "</td>");
			out.print("<td>" + app.getAppointmentDate() + "</td>");
			out.print("<td>");

			out.print("<a href='deletedoctor?id=" + app.getId() + "'>" + "DELETE" + "</a>");
			out.print("</td>");
			out.print("</tr>");
		}
		out.print("</table>");
	}

}
