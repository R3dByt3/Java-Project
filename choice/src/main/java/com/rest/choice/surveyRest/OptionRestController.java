package com.rest.choice.surveyRest;

import com.rest.choice.model.CheckBoxOption;
import com.rest.choice.model.OptionBase;
import com.rest.choice.model.RadioOption;
import com.rest.choice.model.TextOption;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/option")
public class OptionRestController {
    final OptionRepository _optionRepository;

    public OptionRestController(OptionRepository optionRepository) {
        _optionRepository = optionRepository;
    }

    @PostMapping()
    void addVoteFor(@RequestParam String optionId, @RequestParam String value)
    {
        Optional<OptionBase> option = _optionRepository.findById(optionId);

        option.ifPresent(o -> {
            if (o instanceof RadioOption){
                RadioOption ro = (RadioOption) o;
                ro.getRadioOptions().put(value, ro.getRadioOptions().get(value) + 1);
            } else if (o instanceof CheckBoxOption) {
                CheckBoxOption co = (CheckBoxOption) o;
                co.getCheckBoxOptions().put(value, co.getCheckBoxOptions().get(value) + 1);
            }
            else if (o instanceof TextOption){
                TextOption to = (TextOption) o;
                to.getAnswers().add(value);
            }
            else
                throw new IllegalStateException("Unexpected type");

            _optionRepository.save(o);
        });
    }
}
