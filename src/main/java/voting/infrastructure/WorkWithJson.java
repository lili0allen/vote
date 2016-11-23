package voting.infrastructure;

import java.io.*;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import voting.domain.Vote;


public class WorkWithJson{
    private static final String FILE_DIR = "data/data.json";
    private ClassLoader classLoader;
    private File file;
    private JSONParser parser = new JSONParser();

    public WorkWithJson(){
        this.classLoader = getClass().getClassLoader();
        this.file = new File(classLoader.getResource(FILE_DIR).getFile());
        try {
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

    private void addRecord(JSONObject obj){
        try {
            FileReader fileReader = new FileReader(file);
            JSONObject surveys = (JSONObject) parser.parse(fileReader);
            surveys.put(obj.get("id"), obj);

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(surveys.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSurvey(String id, String title, String description){
        JSONObject survey = new JSONObject();
        JSONObject votes = new JSONObject();

        for (Vote vote : Vote.values()) {
            votes.put(vote, 0);
        }

        survey.put("id", id);
        survey.put("title", title);
        survey.put("description", description);
        survey.put("createdTime", new Date().getTime());
        survey.put("votes", votes);

        this.addRecord(survey);
    }

    public JSONObject getSurvey(String id){
        JSONObject survey = new JSONObject();
        try {
            FileReader fileReader = new FileReader(file);
            JSONObject surveys = (JSONObject) parser.parse(fileReader);
            survey = (JSONObject) surveys.get(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return survey;
    }

    public void addVote(String surveyID, Vote vote){
        int voteCount = this.getVoteCount(surveyID, vote);

        JSONObject votes = this.getVotes(surveyID);
        votes.put(vote.toString(), voteCount+1);

        JSONObject survey = this.getSurvey(surveyID);
        survey.put("votes", votes);

        try {
            FileReader fileReader = new FileReader(file);
            JSONObject surveys = (JSONObject) parser.parse(fileReader);
            surveys.put(surveyID, survey);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(surveys.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getVoteCount(String surveyID, Vote vote){
        JSONObject votes = this.getVotes(surveyID);
        int voteCount = ((Long)votes.get(vote.toString())).intValue();
        return voteCount;
    }

    public JSONObject getVotes(String surveyID){
        JSONObject survey = this.getSurvey(surveyID);
        JSONObject votes = (JSONObject) survey.get("votes");
        return votes;
    }
}
