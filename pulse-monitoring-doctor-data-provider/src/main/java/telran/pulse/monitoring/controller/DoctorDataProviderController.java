package telran.pulse.monitoring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import telran.pulse.monitoring.dto.DoctorPatientData;
import telran.pulse.monitoring.repo.*;

import static telran.pulse.monitoring.api.ApiConstants.*;

@RestController
public class DoctorDataProviderController {

	DoctorRepository doctorRepository;
	PatientRepository patientRepository;
	VisitReposiory visitReposiory;

	@Autowired
	public DoctorDataProviderController(DoctorRepository doctorRepository, PatientRepository patientRepository,
			VisitReposiory visitReposiory) {
		this.doctorRepository = doctorRepository;
		this.patientRepository = patientRepository;
		this.visitReposiory = visitReposiory;
	}

	@GetMapping(value = DOCTOR_PATIENT_DATA_URL + "/{id}")
	DoctorPatientData getDoctorPatientData(@PathVariable int id) {
		String email = visitReposiory.getDoctorEmail(id);
		String doctorName = doctorRepository.getById(email).getName();
		String patientName = patientRepository.getById(id).getName();
		return new DoctorPatientData(email, doctorName, patientName);

	}

}
