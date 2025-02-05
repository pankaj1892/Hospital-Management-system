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
import com.model.Doctor;

@WebServlet("/ViewAllDoctor")
public class ViewAllDoctorSrevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HospitalDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new HospitalDAOImpl();
	}

	public ViewAllDoctorSrevlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// HospitalDAO dao = new HospitalDAOImpl();
		List<Doctor> list = dao.getAllDoctor();

		Iterator<Doctor> itr = list.iterator();

		out.print("<head>");
		out.print("<link rel='stylesheet' href='./css/table.css'>");
		out.print("</head>");

		out.print("<table id='hms' >");

		out.print("<tr>");
		out.print("<th> ID </th>");
		out.print("<th> NAME </th>");
		out.print("<th> Specialization </th>");
		out.print("<th> CONTACT </th>");
		out.print("<th> REMOVE </th>");
		out.print("<th> Modify </th>");

		out.print("</tr>");

		while (itr.hasNext()) {
			Doctor doc = (Doctor) itr.next();
			out.print("<tr>");
			out.print("<td>" + doc.getId() + "</td>");
			out.print("<td>" + doc.getName() + "</td>");
			out.print("<td>" + doc.getSpecialization() + "</td>");
			out.print("<td>" + doc.getContact() + "</td>");
			out.print("<td>");

			out.print("<a href='deletedoctor?id=" + doc.getId() + "'>" + "DELETE" + "</a>");
			out.print("<td>");
			out.print("<a href='UpdateDoctorFormServlet?id=" + doc.getId() + "'>" + "UPDATE" + "</a>");

			out.print("</td>");
			out.print("</tr>");
		}
		out.print("</table>");
	}

}
