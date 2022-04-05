package telran.pulse.monitoring;

public class SensorJump {

	public int sensorId;
	public int previousValue;
	public int currentValue;
	public long timeStamp;

	public SensorJump() {
	}

	public SensorJump(int sensorId, int previousValue, int currentValue) {
		this.sensorId = sensorId;
		this.previousValue = previousValue;
		this.currentValue = currentValue;
		timeStamp = System.currentTimeMillis();
	}

	
	
}
