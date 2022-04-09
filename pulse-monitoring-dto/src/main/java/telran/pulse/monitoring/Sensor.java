package telran.pulse.monitoring;

public class Sensor {

	public int id;
	public int value;
	public int seqNum;
	public long timestamp;

	public Sensor() {
	}

	public Sensor(int id, int value, int seqNum) {
		this.id = id;
		this.value = value;
		this.seqNum = seqNum;
		timestamp = System.currentTimeMillis();
	}

	public Sensor(int id, int value) {
		this.id = id;
		this.value = value;
	}
	
	

}
