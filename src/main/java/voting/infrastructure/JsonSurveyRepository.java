package voting.infrastructure;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import voting.domain.Survey;
import voting.domain.SurveyRepository;
import voting.domain.SurveyType;
import voting.domain.Vote;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JsonSurveyRepository implements SurveyRepository {

    private File file;
    private JSONParser parser = new JSONParser();

    @Value("${spring.jsonUrl}")
    private String jsonUrl;

    public JsonSurveyRepository() {

    }

    private void checkFileExist(){
        this.file = new File(jsonUrl);
        if(!this.file.exists()){
            try {
                file.createNewFile();
                FileReader fileReader = new FileReader(file);
                BufferedReader br = new BufferedReader(fileReader);
                if(br.readLine() == null){
                    JSONObject surveys = new JSONObject();
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(surveys.toJSONString());
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void saveSurvey(Survey survey) {
        this.checkFileExist();
        try {
            FileReader fileReader = new FileReader(file);

            JSONObject surveyJson = toSurveyJsonObject(survey);

            JSONObject allSurveys = (JSONObject) parser.parse(fileReader);
            allSurveys.put(survey.id(), surveyJson);

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(allSurveys.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Survey getSurvey(String surveyId) {
        this.checkFileExist();
        try {
            FileReader fileReader = new FileReader(file);
            JSONObject surveys = (JSONObject) parser.parse(fileReader);
            JSONObject survey = (JSONObject) surveys.get(surveyId);

            if(survey == null) {
                return null;
            }

            return toSurveyDomain(survey);
        } catch (Exception e) {
            throw new RuntimeException("Fail to find survey, surveyID:" + surveyId, e);
        }
    }

    private Survey toSurveyDomain(JSONObject jsonObject) {
        JSONObject votesJson = (JSONObject)jsonObject.get("votes");

        Map<Vote, Integer> votesMap = new HashMap<>();
        for(Vote vote: Vote.values()) {
            votesMap.put(vote, ((Number) votesJson.get(vote.toString())).intValue());
        }

        return new Survey(
                (String) jsonObject.get("id"),
                (String) jsonObject.get("title"),
                (String) jsonObject.get("description"),
                (SurveyType) jsonObject.get("surveyType"),
                (Long) jsonObject.get("createdTime"),
                votesMap);
    }

    private JSONObject toSurveyJsonObject(Survey surveyDomain) {
        JSONObject survey = new JSONObject();
        JSONObject votes = new JSONObject();

        for (Vote vote : Vote.values()) {
            votes.put(vote, surveyDomain.getVote(vote));
        }

        survey.put("id", surveyDomain.id());
        survey.put("title", surveyDomain.title());
        survey.put("description", surveyDomain.description());
        survey.put("surveyType", surveyDomain.surveyType());
        survey.put("createdTime", surveyDomain.createdTime());
        survey.put("votes", votes);
        return survey;
    }
}
