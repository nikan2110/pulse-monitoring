package telran.pulse.monitoring.service;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import telran.pulse.monitoring.Sensor;
import telran.pulse.monitoring.SensorJump;
import telran.pulse.monitoring.entities.SensorRedis;
import telran.pulse.monitoring.repo.SensorRepository;

@Service
public class AnalyserService {

	static Logger LOG = LoggerFactory.getLogger(AnalyserService.class);

	StreamBridge streamBridge;
	SensorRepository sensorRepository;
	@Value("${app.jump.threshold:50}")
	int jumpPercentThreshold;
	@Value("${app.critical.threshold:100}")
	int criticalPercentThreshold;

	@Autowired
	public AnalyserService(StreamBridge streamBridge, SensorRepository sensorRepository) {
		this.streamBridge = streamBridge;
		this.sensorRepository = sensorRepository;
	}

	@Bean
	Consumer<Sensor> pulseConsumer() {
		return this::pulseProcessing;
	}

	void pulseProcessing(Sensor sensor) {
		LOG.trace("Received sensor id {}; value{}", sensor.id, sensor.value);
		SensorRedis sensorRedis = sensorRepository.findById(sensor.id).orElse(null);
		if (sensorRedis == null) {
			LOG.debug("for sensor id {} not found record in redis", sensor.id);
			sensorRedis = new SensorRedis(sensor.id);
		} else {
			int lastValue = sensorRedis.getLastValue();
			int delta = Math.abs(lastValue - sensor.value);
			double percent = delta / lastValue * 100;
			if (percent > jumpPercentThreshold) {
				LOG.debug("sensor id {} has values jump {}", sensor.id, delta);
				SensorJump sensorJump = new SensorJump(sensor.id, lastValue, sensor.value);
				streamBridge.send("jumps-out-0", sensorJump);
				if (percent > criticalPercentThreshold) {
					LOG.debug("sensor id {} has critical values jump {}", sensor.id, delta);
					streamBridge.send("critical-jumps-out-0", sensorJump);
				}
			}

		}
	}

}
