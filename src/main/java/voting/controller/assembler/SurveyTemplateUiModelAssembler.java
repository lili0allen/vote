package voting.controller.assembler;

import voting.controller.model.SurveyTemplateUiModel;
import voting.domain.Survey;
import voting.domain.SurveyType;

public class SurveyTemplateUiModelAssembler {

    public SurveyTemplateUiModel toSurveyTemplateUiModel(Survey survey) {
        SurveyTemplateUiModel surveyTemplateUiModel = new SurveyTemplateUiModel();
        surveyTemplateUiModel.setId(survey.id());
        surveyTemplateUiModel.setTitle(survey.title());
        surveyTemplateUiModel.setDescription(survey.description());
        surveyTemplateUiModel.setSurveyType(toSurveyTypeUIValue(survey.surveyType()));
        surveyTemplateUiModel.setCreatedTime(survey.createdTime());
        return surveyTemplateUiModel;
    }

    private String toSurveyTypeUIValue(SurveyType surveyType) {
        if(surveyType == SurveyType.STANDALONE) {
            return "Standalone";
        }

        if(surveyType == SurveyType.INVITATION) {
            return "Invitation";
        }
        throw new IllegalArgumentException("Not supported yet: " + surveyType);
    }
}