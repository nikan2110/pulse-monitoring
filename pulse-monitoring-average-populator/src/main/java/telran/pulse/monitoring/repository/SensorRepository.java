package telran.pulse.monitoring.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import telran.pulse.monitoring.entities.SensorDoc;

public interface SensorRepository extends MongoRepository<SensorDoc, ObjectId> {

}
