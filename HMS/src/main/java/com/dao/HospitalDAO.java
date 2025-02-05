package com.dao;

import java.util.List;

import com.model.Appointment;
import com.model.Doctor;
import com.model.Patient;

public interface HospitalDAO {
    int addDoctor(Doctor doctor);
    int addPatient(Patient patient);
    int createAppointment(int id, int patientId, int doctorId, String appointmentDate);
    int createBill(int patientId, double amount);
    Doctor getDoctorById(int id);
	Patient getPatientById(int id);
	List<Doctor>getAllDoctor();
	List<Patient>getAllPatient();
	int deletePatient(int id);
	int  updateDoctor(Doctor doctor);
	int deleteDoctor(int id);
	List<Doctor> getDoctorsSortedByName();
    List<Patient> getPatientsSortedByName();
    List<Appointment> getAllAppointments();
    List<Doctor> searchDoctors(String keyword);
    List<Patient> searchPatients(String keyword);
    
}
