package com.rest.choice.surveyRest;

import com.rest.choice.model.ComplexSurvey;
import com.rest.choice.model.SimpleSurvey;
import com.rest.choice.model.SurveyBase;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/survey")
public class SurveyRestController {

    final SurveyRepository _votingRepository;
    final OptionRepository _optionRepository;

    public SurveyRestController(SurveyRepository votingDtoRepository, OptionRepository optionRepository) {
        _votingRepository = votingDtoRepository;
        _optionRepository = optionRepository;
    }

    @PostMapping(consumes = "application/json")
    String addVoting(@RequestBody SurveyBase surveyBase)
    {
        if (surveyBase instanceof SimpleSurvey){
            SimpleSurvey simpleSurvey = (SimpleSurvey) surveyBase;
            _optionRepository.insert(simpleSurvey.getOption());
        }
        else if (surveyBase instanceof ComplexSurvey){
            ComplexSurvey complexSurvey = (ComplexSurvey) surveyBase;
            _optionRepository.insert(complexSurvey.getOptions());
        }
        else
            throw new IllegalStateException("Unknown type");

        _votingRepository.insert(surveyBase);
        return surveyBase.get_id();
    }

    @GetMapping(produces = "application/json")
    Optional<SurveyBase> getVoting(@RequestParam String id){
        return _votingRepository.findById(id);
    }
}
