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
import com.model.Patient;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private HospitalDAO hospitalDAO;

    @Override
    public void init() throws ServletException {
        hospitalDAO = new HospitalDAOImpl();     }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String keyword = request.getParameter("keyword");
        String searchType = request.getParameter("type"); // "doctor" or "patient"

        if (keyword == null || keyword.trim().isEmpty() || searchType == null) {
            out.println("<h3>Invalid search request. Please provide a keyword and type.</h3>");
            return;
        }

        try {
            if (searchType.equalsIgnoreCase("doctor")) {
                List<Doctor> doctors = hospitalDAO.searchDoctors(keyword);
                if (doctors.isEmpty()) {
                    out.println("<h3>No doctors found matching: " + keyword + "</h3>");
                } else {
                	Iterator<Doctor> itr = doctors.iterator();
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
                    out.println("</table>");
                }
            } else if (searchType.equalsIgnoreCase("patient")) {
                List<Patient> patients = hospitalDAO.searchPatients(keyword);
                if (patients.isEmpty()) {
                    out.println("<h3>No patients found matching: " + keyword + "</h3>");
                } else {
                	Iterator<Patient> itr = patients.iterator();

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
            } else {
                out.println("<h3>Invalid search type. Please use 'doctor' or 'patient'.</h3>");
            }
        } catch (Exception e) {
            out.println("<h3>Error occurred while processing the search: " + e.getMessage() + "</h3>");
        }
    }

    @Override
    public void destroy() {
        hospitalDAO = null;
    }
}
