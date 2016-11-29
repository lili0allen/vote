package voting.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    public String createSurveyTemplate(String title, String description){
        if(title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Survey 'title' can not be null or empty");
        }

        Survey survey = new Survey(title.trim(), description);
        surveyRepository.saveSurvey(survey);

        return survey.id();
    }

    public Survey getSurveyTemplate(String id){
        return this.surveyRepository.getSurvey(id);
    }

    public void addVote(String surveyID, Vote vote){
        Survey survey = this.surveyRepository.getSurvey(surveyID);
        if(survey == null) {
            throw new IllegalArgumentException("Survey not found, surveyID: '" + surveyID + "'");
        }

        survey.addVote(vote);
        surveyRepository.saveSurvey(survey);
    }
}
