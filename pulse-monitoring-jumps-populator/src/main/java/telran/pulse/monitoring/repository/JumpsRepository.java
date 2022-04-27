package telran.pulse.monitoring.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import telran.pulse.monitoring.entities.JumpDoc;

public interface JumpsRepository extends MongoRepository<JumpDoc, ObjectId> {

}
