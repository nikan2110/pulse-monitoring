package telran.pulse.monitoring.dto;

public class Sensor {

	public int id;
	public int value;
	public int seqNum;
	public long timestamp;

	public Sensor() {
	}

	public Sensor(int id, int value) {
		this.id = id;
		this.value = value;
		timestamp = System.currentTimeMillis();
	}

}
