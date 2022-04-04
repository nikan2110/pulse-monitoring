package telran.pulse.monitoring.service;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import telran.pulse.monitoring.Sensor;

@Service
public class AnalyserService {
	@Autowired
	StreamBridge streamBridge;

	@Bean
	Consumer<Sensor> pulseConsumer() {
		return this::pulseProcessing;
	}

	void pulseProcessing(Sensor sensor) {
		System.out.printf("sequence number %d, sensor id %d, wating time %d\n", sensor.seqNum, sensor.id,
				System.currentTimeMillis() - sensor.timestamp);
	}

}
