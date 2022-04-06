package telran.pulse.monitoring;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JumpsPopulatorApplication {

	static Logger LOG = LoggerFactory.getLogger(JumpsPopulatorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JumpsPopulatorApplication.class, args);

	}

	@Bean
	Consumer<SensorJump> jumpsConsumer() {
		return this::jumpProcessing;
	}

	void jumpProcessing(SensorJump sensorJump) {
		LOG.trace("receiver sensor id {} previous value {} current value {}", sensorJump.sensorId,
				sensorJump.previousValue, sensorJump.currentValue);
	}

}
