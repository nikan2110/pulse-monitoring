package telran.pulse.monitoring.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.pulse.monitoring.dto.DoctorData;
import telran.pulse.monitoring.dto.PatientData;
import telran.pulse.monitoring.dto.VisitData;
import telran.pulse.monitoring.service.VisitService;

@RestController
@RequestMapping("/visits")
public class VisitsController {

	VisitService visitService;

	@Autowired
	public VisitsController(VisitService visitService) {
		this.visitService = visitService;
	}

	@PostMapping("/patients")
	PatientData addPatient(@RequestBody PatientData patientData) {
		visitService.addPatient(patientData.patientId, patientData.patientName);
		return patientData;
	}

	@PostMapping("/doctors")
	DoctorData addDoctor(@RequestBody @Valid DoctorData doctorData) {
		visitService.addDoctor(doctorData.email, doctorData.name);
		return doctorData;
	}

	@PostMapping
	Map<String, Object> addVisit(@RequestBody Map<String, Object> visit) {
		visitService.addVisit((Integer) visit.get("patientId"), (String) visit.get("email"),
				LocalDateTime.parse((String)visit.get("date")));
		return visit;
	}

	@GetMapping("/{patientId}")
	List<VisitData> getVisitsPatientDates(@PathVariable int patientId,
			@DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime from,
			@DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime to) {
		return visitService.getVisits(patientId, from, to);
	}

}
