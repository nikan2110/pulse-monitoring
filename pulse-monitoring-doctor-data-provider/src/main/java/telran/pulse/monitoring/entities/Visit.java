package telran.pulse.monitoring.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "visits")
public class Visit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	LocalDateTime date;
	@ManyToOne
	@JoinColumn(name = "doctor_email")
	Doctor doctor;
	@ManyToOne
	@JoinColumn(name = "patient_id")
	Patient patient;

	public Visit() {
	}

	public Visit(LocalDateTime date, Doctor doctor, Patient patient) {
		this.date = date;
		this.doctor = doctor;
		this.patient = patient;
	}

}
