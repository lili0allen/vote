package voting.domain;

public interface SurveyRepository {
    void saveSurvey(Survey survey);
    Survey getSurvey(String surveyId);
}
