package voting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import voting.domain.*;
import voting.infrastructure.WorkWithJson;

@Controller
public class VotingController {
    @GetMapping("/vote")
    public String surveyForm(Model model){
        model.addAttribute("survey", new Survey());
        return "survey-create";
    }

    @PostMapping("/vote/initialise")
    public String surveySubmit(@ModelAttribute Survey survey){
        WorkWithJson workWithJson = new WorkWithJson();
        workWithJson.addSurvey(survey.getId(), survey.getTitle(), survey.getDescription());
        return "survey-details";
    }

    @GetMapping("/vote/{id}")
    public String surveyEntry(@PathVariable String id, Model model){
        WorkWithJson workWithJson = new WorkWithJson();
        model.addAttribute("survey", workWithJson.getSurvey(id));
        model.addAttribute("options", new Vote());
        return "survey";
    }
}
