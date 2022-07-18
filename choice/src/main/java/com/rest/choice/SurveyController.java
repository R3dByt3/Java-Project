package com.rest.choice;

import com.rest.choice.model.SurveyBase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SurveyController {

    final VotingDtoRepository _votingDtoRepository;

    public SurveyController(VotingDtoRepository votingDtoRepository) {
        _votingDtoRepository = votingDtoRepository;
    }

    @PostMapping("/voting")
    void addVoting(@RequestBody SurveyBase votingDto) {
        _votingDtoRepository.save(votingDto);
    }
}
