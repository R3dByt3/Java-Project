package com.rest.choice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VotingController {

    final VotingDtoRepository _votingDtoRepository;

    public VotingController(VotingDtoRepository votingDtoRepository) {
        _votingDtoRepository = votingDtoRepository;
    }

    @PostMapping("/voting")
    void addVoting(@RequestBody VotingDto votingDto) {
        _votingDtoRepository.save(votingDto);
    }
}
