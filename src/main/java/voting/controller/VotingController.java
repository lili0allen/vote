package voting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import voting.domain.*;

@Controller
public class VotingController {
    @GetMapping("/vote")
    public String surveyForm(Model model){
        model.addAttribute("survey", new Survey());
        return "survey-create";
    }

    @PostMapping("/vote")
    public String surveySubmit(@ModelAttribute Survey survey){
        return "survey-details";
    }

    @PostMapping("/voting")
    public String votingSubmit(@ModelAttribute Voting voting){
        return "result";
    }

    @PostMapping("/vote/initialise")
    public String votingSubmit(@ModelAttribute Voting voting){
        return "result";
    }

}
