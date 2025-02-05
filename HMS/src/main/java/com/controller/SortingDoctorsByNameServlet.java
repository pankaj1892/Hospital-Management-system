package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.HospitalDAO;
import com.dao.HospitalDAOImpl;
import com.model.Doctor;

@WebServlet("/SortingDoctorsByNameServlet")
public class SortingDoctorsByNameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private HospitalDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new HospitalDAOImpl();
    }

    public SortingDoctorsByNameServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        List<Doctor> sortedDoctors = dao.getDoctorsSortedByName();

        out.print("<head>");
        out.print("<link rel='stylesheet' href='./css/table.css'>");
        out.print("</head>");

        out.print("<table id='hms'>");
        out.print("<tr>");
        out.print("<th>ID</th>");
        out.print("<th>Name</th>");
        out.print("<th>Specialization</th>");
        out.print("<th>Contact</th>");
        out.print("<th>Remove</th>");
        out.print("<th>Modify</th>");
        out.print("</tr>");

        for (Doctor doctor : sortedDoctors) {
            out.print("<tr>");
            out.print("<td>" + doctor.getId() + "</td>");
            out.print("<td>" + doctor.getName() + "</td>");
            out.print("<td>" + doctor.getSpecialization() + "</td>");
            out.print("<td>" + doctor.getContact() + "</td>");
            out.print("<td><a href='deletedoctor?id=" + doctor.getId() + "'>DELETE</a></td>");
            out.print("<td><a href='UpdateDoctorFormServlet?id=" + doctor.getId() + "'>UPDATE</a></td>");
            out.print("</tr>");
        }
        out.print("</table>");
    }
}
