package voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import voting.controller.form.SurveyForm;
import voting.controller.model.SurveyTemplateUiModel;
import voting.domain.Survey;
import voting.domain.SurveyService;

@Controller
public class SurveyTemplateController {
    @GetMapping("/survey")
    public String surveyForm(Model model){
        model.addAttribute("survey", new SurveyForm());
        return "survey-create";
    }

    @PostMapping("/survey")
    public String surveySubmit(@ModelAttribute(value = "survey") SurveyForm survey, Model model){
        String surveyId = surveyService.createSurveyTemplate(survey.getTitle(), survey.getDescription());

        Survey surveyCreated = surveyService.getSurveyTemplate(surveyId);
        SurveyTemplateUiModel uiModel = toSurveyTemplateUiModel(surveyCreated);
        model.addAttribute("surveyUiModel", uiModel);

        return "survey-details";
    }

    private SurveyTemplateUiModel toSurveyTemplateUiModel(Survey survey) {
        SurveyTemplateUiModel surveyTemplateUiModel = new SurveyTemplateUiModel();
        surveyTemplateUiModel.setId(survey.id());
        surveyTemplateUiModel.setTitle(survey.title());
        surveyTemplateUiModel.setDescription(survey.description());
        surveyTemplateUiModel.setCreatedTime(survey.createdTime());

        return surveyTemplateUiModel;
    }

    @Autowired
    private SurveyService surveyService;
}
