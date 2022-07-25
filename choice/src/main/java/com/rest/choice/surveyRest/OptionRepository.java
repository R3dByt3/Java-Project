package com.rest.choice.surveyRest;

import com.rest.choice.model.OptionBase;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OptionRepository extends MongoRepository<OptionBase, String> {
}
