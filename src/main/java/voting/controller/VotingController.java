package voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.context.request.WebRequest;
import voting.controller.assembler.SurveyTemplateUiModelAssembler;
import voting.controller.model.SurveyTemplateUiModel;
import voting.controller.transformer.VoteOptionTransformer;
import voting.domain.Survey;
import voting.domain.SurveyService;
import voting.domain.SurveyType;
import voting.domain.VoteOption;

import java.text.NumberFormat;
import java.util.List;

@Controller
public class VotingController {

    @Value("${spring.expireTime}")
    private int expireTime;

    @GetMapping("/survey/{id}")
    public String getSurveyEntryForm(@PathVariable String id, Model model){
        Survey survey = surveyService.getSurveyTemplate(id);
        List<String> voteOptions = new VoteOptionTransformer().VoteOptionString();

        SurveyTemplateUiModel surveyTemplateUiModel = new SurveyTemplateUiModelAssembler().toSurveyTemplateUiModel(survey);
        int TEN_MINUTES_IN_MILLISECONDS = expireTime * 60 * 1000;
        model.addAttribute("survey", surveyTemplateUiModel);
        model.addAttribute("options", voteOptions);
        model.addAttribute("expire", TEN_MINUTES_IN_MILLISECONDS);
        model.addAttribute("votesCount", survey.totalVoteCount());

        boolean expired = survey.isExpired(TEN_MINUTES_IN_MILLISECONDS);
        SurveyType surveyType = survey.surveyType();

        if (surveyType == SurveyType.STANDALONE){
            return "survey-standalone";
        }else if(expired){
            return "survey-close";
        }else{
            return "survey";
        }
    }

    @GetMapping("/survey/result/{id}")
    public String surveyResult(@PathVariable String id, Model model){
        Survey survey = surveyService.getSurveyTemplate(id);

        int totalVoteCount = survey.totalVoteCount();
        double poor = survey.getVote(VoteOption.POOR);
        double fair = survey.getVote(VoteOption.FAIR);
        double good = survey.getVote(VoteOption.GOOD);
        double poorPercentage = 0;
        double fairPercentage = 0;
        double goodPercentage = 0;

        if(survey.totalVoteCount() != 0){
            poorPercentage = (poor / totalVoteCount);
            fairPercentage = (fair / totalVoteCount);
            goodPercentage = (good / totalVoteCount);
        }

        NumberFormat defaultFormat = NumberFormat.getPercentInstance();
        defaultFormat.setMinimumFractionDigits(2);

        SurveyTemplateUiModel surveyTemplateUiModel = new SurveyTemplateUiModelAssembler().toSurveyTemplateUiModel(survey);
        int TEN_MINUTES_IN_MILLISECONDS = expireTime * 60 * 1000;
        model.addAttribute("survey", surveyTemplateUiModel);
        model.addAttribute("total", totalVoteCount);
        model.addAttribute("expire", TEN_MINUTES_IN_MILLISECONDS);

        model.addAttribute("poorResult", defaultFormat.format(poorPercentage));
        model.addAttribute("fairResult", defaultFormat.format(fairPercentage));
        model.addAttribute("goodResult", defaultFormat.format(goodPercentage));

        return "survey-result";
    }

    @PostMapping("/survey/voteSubmit")
    public String voteSubmit(WebRequest request, Model model){
        String surveyID = request.getParameter("surveyID");
        VoteOption vote = new VoteOptionTransformer().voteOptionFromString(request.getParameter("vote"));
        model.addAttribute("surveyUiModelID", surveyID);
        surveyService.addVote(surveyID, vote);
        return "vote-finish";
    }

    @PostMapping("/survey/standaloneVoteSubmit")
    @ResponseBody
    public String standaloneVoteSubmit(WebRequest request, Model model){
        String surveyID = request.getParameter("surveyID");
        VoteOption vote = new VoteOptionTransformer().voteOptionFromString(request.getParameter("vote"));
        model.addAttribute("surveyUiModelID", surveyID);
        surveyService.addVote(surveyID, vote);
        Survey survey = surveyService.getSurveyTemplate(surveyID);
        String totalVoteCount = Integer.toString(survey.totalVoteCount());
        return totalVoteCount;
    }

    @Autowired
    private SurveyService surveyService;
}
