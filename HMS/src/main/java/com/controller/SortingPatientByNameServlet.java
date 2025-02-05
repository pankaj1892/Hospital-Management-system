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
import com.model.Patient;

@WebServlet("/SortingPatientByNameServlet")
public class SortingPatientByNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HospitalDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new HospitalDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		List<Patient> list = dao.getPatientsSortedByName();

		Iterator<Patient> itr = list.iterator();

		out.print("<head>");
		out.print("<link rel='stylesheet' href='./css/table.css'>");
		out.print("</head>");

		out.print("<table id='hms' >");

		out.print("<tr>");
		out.print("<th> ID </th>");
		out.print("<th> NAME </th>");
		out.print("<th> AGE </th>");
		out.print("<th> CONTACT </th>");
		out.print("<th> AILMENT </th>");
		out.print("<th> REMOVE </th>");

		out.print("</tr>");

		while (itr.hasNext()) {
			Patient pat = (Patient) itr.next();
			out.print("<tr>");
			out.print("<td>" + pat.getId() + "</td>");
			out.print("<td>" + pat.getName() + "</td>");
			out.print("<td>" + pat.getAge() + "</td>");
			out.print("<td>" + pat.getContact() + "</td>");
			out.print("<td>" + pat.getAilment() + "</td>");
			out.print("<td>");
			out.print("<a href='deletepatient?id=" + pat.getId() + "'>" + "DELETE" + "</a>");

			out.print("</td>");
			out.print("</tr>");
		}
		out.print("</table>");
	}
}
