package telran.pulse.monitoring.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import telran.pulse.monitoring.dto.VisitData;
import telran.pulse.monitoring.entities.Doctor;
import telran.pulse.monitoring.entities.Patient;
import telran.pulse.monitoring.entities.Visit;
import telran.pulse.monitoring.repo.DoctorRepository;
import telran.pulse.monitoring.repo.PatientRepository;
import telran.pulse.monitoring.repo.VisitReposiory;

public class VisitServiceImpl implements VisitService {

	DoctorRepository doctorRepository;
	PatientRepository patientRepository;
	VisitReposiory visitReposiory;

	@Autowired
	public VisitServiceImpl(DoctorRepository doctorRepository, PatientRepository patientRepository,
			VisitReposiory visitReposiory) {
		this.doctorRepository = doctorRepository;
		this.patientRepository = patientRepository;
		this.visitReposiory = visitReposiory;
	}

	@Override
	@Transactional
	public void addPatient(int patientId, String name) {
		if (patientRepository.existsById(patientId)) {
			throw new EntityExistsException("Patient with id " + patientId + " already exist");
		}
		Patient patient = new Patient(patientId, name);
		patientRepository.save(patient);

	}

	@Override
	@Transactional
	public void addDoctor(String email, String name) {
		if (doctorRepository.existsById(email)) {
			throw new EntityExistsException("Doctor with email  " + email + " already exist");
		}
		Doctor doctor = new Doctor(email, name);
		doctorRepository.save(doctor);

	}

	@Override
	@Transactional
	public void addVisit(int patientId, String email, LocalDateTime dateTime) {
		Patient patient = patientRepository.findById(patientId).orElse(null);
		if (patient == null) {
			throw new EntityNotFoundException("Patient witn " + patientId + " not found");
		}
		Doctor doctor = doctorRepository.findById(email).orElse(null);
		if (doctor == null) {
			throw new EntityNotFoundException("Doctor witn " + email + " not found");
		}
		Visit visit = new Visit(dateTime, doctor, patient);
		visitReposiory.save(visit);

	}

	@Override
	public List<VisitData> getVisits(int patientId, LocalDateTime from, LocalDateTime to) {
		return visitReposiory.getVisitsPatientDates(patientId, from, to);
	}

}
