package voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import voting.controller.form.SurveyForm;
import voting.controller.model.SurveyTemplateUiModel;
import voting.domain.Survey;
import voting.domain.SurveyService;
import voting.controller.assembler.SurveyTemplateUiModelAssembler;
import voting.controller.transformer.SurveyTypeTransformer;


@Controller
public class SurveyTemplateController {
    @GetMapping("/")
    public String surveyForm(Model model){
        model.addAttribute("survey", new SurveyForm());
        model.addAttribute("types", new SurveyTypeTransformer().surveyTypeString());
        return "survey-create";
    }

    @PostMapping("/survey")
    public String surveySubmit(@ModelAttribute(value = "survey") SurveyForm survey, Model model){
        String surveyId = surveyService.createSurveyTemplate(survey.getTitle(), survey.getDescription(), survey.getSurveyType());
        Survey surveyCreated = surveyService.getSurveyTemplate(surveyId);
        SurveyTemplateUiModel uiModel = new SurveyTemplateUiModelAssembler().toSurveyTemplateUiModel(surveyCreated);
        model.addAttribute("surveyUiModel", uiModel);
        return "survey-details";
    }

    @Autowired
    private SurveyService surveyService;
}
