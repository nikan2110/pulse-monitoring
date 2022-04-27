package telran.pulse.monitoring.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.pulse.monitoring.service.SensorValuesService;

@RestController
@RequestMapping("/sensors")
public class SensorValuesController {

	SensorValuesService sensorValuesService;

	@Autowired
	public SensorValuesController(SensorValuesService sensorValuesService) {
		this.sensorValuesService = sensorValuesService;
	}

	@GetMapping("/average/{sensorId}")
	int getAverageSensorDates(@PathVariable int sensorId, @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime from,
			@DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime to) {
		return sensorValuesService.getAverageDates(sensorId, from, to);

	}

	@GetMapping("/jumps/{sensorId}")
	long getCountJumpsSensorDates(@PathVariable int sensorId, @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime from,
			@DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime to) {
		return sensorValuesService.getJumpsCountDates(sensorId, from, to);
	}

}
