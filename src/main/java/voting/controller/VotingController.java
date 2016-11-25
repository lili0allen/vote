package voting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import voting.domain.*;
import voting.infrastructure.WorkWithJson;

import javax.annotation.PostConstruct;
import java.util.Date;

@Controller
public class VotingController {
    @GetMapping("/survey")
    public String surveyForm(Model model){
        model.addAttribute("survey", new Survey());
        return "survey-create";
    }

    @PostMapping("/survey/initialise")
    public String surveySubmit(@ModelAttribute Survey survey){
        WorkWithJson workWithJson = new WorkWithJson();
        workWithJson.addSurvey(survey.getId(), survey.getTitle(), survey.getDescription());
        return "survey-details";
    }

    @GetMapping("/survey/{id}")
    public String surveyEntry(@PathVariable String id, Model model){
        WorkWithJson workWithJson = new WorkWithJson();
        model.addAttribute("survey", workWithJson.getSurvey(id));
        model.addAttribute("options", Vote.values());
        long currentTime = new Date().getTime();
        long createdTime = (long) workWithJson.getSurvey(id).get("createdTime");
        long countDown = currentTime - createdTime;
        if(((int) countDown) < 10*60*1000){
            model.addAttribute("countDown",countDown);
            return "survey";
        }else{
            return "survey-close";
        }

    }

    @GetMapping("/survey/result/{id}")
    public String surveyResult(@PathVariable String id, Model model){
        WorkWithJson workWithJson = new WorkWithJson();
        model.addAttribute("survey", workWithJson.getSurvey(id));
        model.addAttribute("votes", workWithJson.getVotes(id));
        return "survey-result";
    }

    @PostMapping("/survey/voteSubmit")
    public String voteSubmit(WebRequest request){
        WorkWithJson workWithJson = new WorkWithJson();
        workWithJson.addVote(request.getParameter("surveyID"), Vote.valueOf(request.getParameter("vote")) );
        return "vote-finish";
    }
}
