package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Appointment;
import com.model.Doctor;
import com.model.Patient;
import com.utility.DBUtility;

public class HospitalDAOImpl implements HospitalDAO {

	@Override
	public int addDoctor(Doctor doctor) {
		String sql = "INSERT INTO doctors (name, specialization, contact) VALUES (?, ?, ?)";
		try (Connection con = DBUtility.getDBConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, doctor.getName());
			ps.setString(2, doctor.getSpecialization());
			ps.setString(3, doctor.getContact());
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("addDoctor " + e);
		}
		return 0;
	}

	@Override
	public int addPatient(Patient patient) {
		String sql = "INSERT INTO patients (name, age, contact, ailment) VALUES (?, ?, ?, ?)";
		try (Connection con = DBUtility.getDBConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, patient.getName());
			ps.setInt(2, patient.getAge());
			ps.setString(3, patient.getContact());
			ps.setString(4, patient.getAilment());
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("addpatient " + e);
		}
		return 0;
	}

	@Override
	public int createAppointment(int id, int patientId, int doctorId, String appointmentDate) {
		int i = 0;
		String sql = "INSERT INTO appointments (id,patient_id, doctor_id, appointment_date) VALUES (?,?, ?, ?)";
		try (Connection con = DBUtility.getDBConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.setInt(2, patientId);
			ps.setInt(3, doctorId);
			ps.setString(4, appointmentDate);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createAppointment " + e);
		}
		return i;
	}

	@Override
	public int createBill(int patientId, double amount) {

		String sql = "INSERT INTO bills (patient_id, amount) VALUES (?, ?)";
		try (Connection con = DBUtility.getDBConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, patientId);
			ps.setDouble(2, amount);
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("createBill " + e);
		}
		return 0;
	}

	@Override
	public Doctor getDoctorById(int id) {
		String sql = "SELECT * FROM doctors WHERE id = ?";
		try (Connection con = DBUtility.getDBConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Doctor(rs.getInt("id"), rs.getString("name"), rs.getString("specialization"),
						rs.getString("contact"));
			}
		} catch (SQLException e) {
			System.out.println("getDoctorById " + e);
		}
		return null;
	}

	@Override
	public Patient getPatientById(int id) {
		String sql = "SELECT * FROM patients WHERE id = ?";
		try (Connection con = DBUtility.getDBConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Patient(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("contact"),
						rs.getString("ailment"));
			}
		} catch (SQLException e) {
			System.out.println("getPatientById " + e);
		}
		return null;
	}

	@Override
	public List<Doctor> getAllDoctor() {
		List<Doctor> docList = new ArrayList<Doctor>();
		String sql = "SELECT * FROM doctors";
		try (Connection con = DBUtility.getDBConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String spe = rs.getString(3);
				String con1 = rs.getString(4);
				Doctor doctor = new Doctor(id, name, spe, con1);
				docList.add(doctor);
			}

		} catch (Exception e) {
			System.out.println("HospitalDAOImpl getAllDoctor.." + e);
		}

		return docList;
	}

	@Override
	public List<Patient> getAllPatient() {
		List<Patient> patiList = new ArrayList<Patient>();
		String sql = "SELECT * FROM patients";
		try (Connection con = DBUtility.getDBConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				String contact = rs.getString(4);
				String ailment = rs.getString(5);
				Patient patient = new Patient(id, name, age, contact, ailment);
				patiList.add(patient);
			}

		} catch (Exception e) {
			System.out.println("HospitalDAOImpl getAllDoctor.." + e);
		}

		return patiList;
	}

	@Override
	public int deletePatient(int id) {
		int i = 0;
		String sql = "DELETE FROM patients where id=?";
		try (Connection con = DBUtility.getDBConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			i = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("HospitalDAOImpl deletePatient " + e);
		}
		return i;
	}

	@Override
	public int updateDoctor(Doctor doctor) {
		int i = 0;
		String sql = "UPDATE Doctors SET name=?, specialization=?, contact=? WHERE id=?";
		try (Connection con = DBUtility.getDBConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, doctor.getName());
			ps.setString(2, doctor.getSpecialization());
			ps.setString(3, doctor.getContact());
			ps.setInt(4, doctor.getId());
			i = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("HospitalDAOImpl updateDocter " + e);
		}
		return i;
	}

	@Override
	public int deleteDoctor(int id) {
		int i = 0;
		String sql = "DELETE FROM doctors where id=?";
		try (Connection con = DBUtility.getDBConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			i = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("HospitalDAOImpl deleteDoctor.. " + e);
		}
		return i;
	}

	@Override
	public List<Doctor> getDoctorsSortedByName() {
		List<Doctor> sortedDoctors = new ArrayList<>();
		String sql = "SELECT * FROM doctors ORDER BY name ASC";
		try (Connection con = DBUtility.getDBConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String specialization = rs.getString("specialization");
				String contact = rs.getString("contact");
				Doctor doctor = new Doctor(id, name, specialization, contact);
				sortedDoctors.add(doctor);
			}
		} catch (SQLException e) {
			System.out.println("getDoctorsSortedByName " + e);
		}
		return sortedDoctors;
	}

	@Override
	public List<Patient> getPatientsSortedByName() {
		List<Patient> sortedPatients = new ArrayList<>();
		String sql = "SELECT * FROM patients ORDER BY name ASC";
		try (Connection con = DBUtility.getDBConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String contact = rs.getString("contact");
				String ailment = rs.getString("ailment");
				Patient patient = new Patient(id, name, age, contact, ailment);
				sortedPatients.add(patient);
			}
		} catch (SQLException e) {
			System.out.println("getPatientsSortedByName " + e);
		}
		return sortedPatients;
	}

	@Override
	public List<Appointment> getAllAppointments() {
		List<Appointment> appointments = new ArrayList<>();
		String sql = "SELECT * FROM appointments";
		try (Connection con = DBUtility.getDBConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int patientId = rs.getInt("patient_id");
				int doctorId = rs.getInt("doctor_id");
				String appointmentDate = rs.getString("appointment_date");
				Appointment appointment = new Appointment(id, patientId, doctorId, appointmentDate);
				appointments.add(appointment);
			}
		} catch (SQLException e) {
			System.out.println("getAllAppointments " + e);
		}
		return appointments;
	}
	
	@Override
	public List<Doctor> searchDoctors(String keyword) {
	    List<Doctor> searchResults = new ArrayList<>();
	    String sql = "SELECT * FROM doctors WHERE name LIKE ? OR specialization LIKE ? OR contact LIKE ?";
	    try (Connection con = DBUtility.getDBConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
	        String searchKeyword = "%" + keyword + "%";
	        ps.setString(1, searchKeyword);
	        ps.setString(2, searchKeyword);
	        ps.setString(3, searchKeyword);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("name");
	            String specialization = rs.getString("specialization");
	            String contact = rs.getString("contact");
	            Doctor doctor = new Doctor(id, name, specialization, contact);
	            searchResults.add(doctor);
	        }
	    } catch (SQLException e) {
	        System.out.println("searchDoctors " + e);
	    }
	    return searchResults;
	}

	@Override
	public List<Patient> searchPatients(String keyword) {
	    List<Patient> searchResults = new ArrayList<>();
	    String sql = "SELECT * FROM patients WHERE name LIKE ? OR contact LIKE ? OR ailment LIKE ?";
	    try (Connection con = DBUtility.getDBConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
	        String searchKeyword = "%" + keyword + "%";
	        ps.setString(1, searchKeyword);
	        ps.setString(2, searchKeyword);
	        ps.setString(3, searchKeyword);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("name");
	            int age = rs.getInt("age");
	            String contact = rs.getString("contact");
	            String ailment = rs.getString("ailment");
	            Patient patient = new Patient(id, name, age, contact, ailment);
	            searchResults.add(patient);
	        }
	    } catch (SQLException e) {
	        System.out.println("searchPatients " + e);
	    }
	    return searchResults;
	}


}
