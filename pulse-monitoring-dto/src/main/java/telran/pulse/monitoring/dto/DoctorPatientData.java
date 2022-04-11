package telran.pulse.monitoring.dto;

public class DoctorPatientData {

	public String email;
	public String doctorName;
	public String patientName;

	public DoctorPatientData() {

	}

	public DoctorPatientData(String email, String doctorName, String patientName) {
		this.email = email;
		this.doctorName = doctorName;
		this.patientName = patientName;
	}

}
