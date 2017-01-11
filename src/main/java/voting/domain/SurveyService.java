package voting.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voting.controller.transformer.SurveyTypeTransformer;

@Service
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    public String createSurveyTemplate(String title, String description, String surveyType){
        if(title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Survey 'title' can not be null or empty");
        }

        if(description != null && description.length() > 200) {
            throw new IllegalArgumentException("description must not contain more than 200 characters");
        }

        if(surveyType == null || surveyType.trim().isEmpty()) {
            throw new IllegalArgumentException("Survey 'type' can not be null or empty");
        }

        Survey survey = new Survey(title.trim(), description, new SurveyTypeTransformer().surveyTypeFromString(surveyType));
        surveyRepository.saveSurvey(survey);

        return survey.id();
    }

    public Survey getSurveyTemplate(String id){
        return this.surveyRepository.getSurvey(id);
    }

    public void addVote(String surveyID, VoteOption vote){
        if(surveyID == null || surveyID.trim().isEmpty()){
            throw new IllegalArgumentException("Survey 'id' can not be null or empty");
        }
        if(vote == null || vote.toString().isEmpty()){
            throw new IllegalArgumentException("Survey 'voteOption' can not be null or empty");
        }
        Survey survey = this.surveyRepository.getSurvey(surveyID);
        if(survey == null) {
            throw new IllegalArgumentException("Survey not found, surveyID: '" + surveyID + "'");
        }

        survey.addVote(vote);
        surveyRepository.saveSurvey(survey);
    }

    // for test only
    protected void setSurveyRepository(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }
}
