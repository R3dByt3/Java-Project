package com.rest.choice.surveyRest;

import com.rest.choice.model.SurveyBase;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VotingDtoRepository extends MongoRepository<SurveyBase, String> {
}
