package telran.pulse.monitoring.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
public class SensorRedis {

	@Id
	int id;
	List<Integer> values = new ArrayList<>();

	public SensorRedis(int id) {
		this.id = id;
	}
	
	public int getLastValue() {
		return values.get(values.size()-1);
	}
	
	public int addCurrentValue(int currentValue) {
		values.add(currentValue);
		return values.size()-1;
	}

}
