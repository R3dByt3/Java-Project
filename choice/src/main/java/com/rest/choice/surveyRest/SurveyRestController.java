package com.rest.choice.surveyRest;

import com.rest.choice.model.SurveyBase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SurveyRestController {

    final VotingDtoRepository _votingDtoRepository;

    public SurveyRestController(VotingDtoRepository votingDtoRepository) {
        _votingDtoRepository = votingDtoRepository;
    }

    @PostMapping(value = "/voting", consumes = "application/json")
    void addVoting(@RequestBody SurveyBase votingDto) {
        _votingDtoRepository.save(votingDto);
    }
}
