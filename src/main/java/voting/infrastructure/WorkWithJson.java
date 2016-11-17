package voting.infrastructure;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WorkWithJson {
    private String fileDir = "data/data.json";

    public WorkWithJson(){

    }

    private void addRecord(JSONObject obj){
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File dataFile = new File(classLoader.getResource(this.fileDir).getFile());

            JSONArray surveys = new JSONArray();

            FileWriter file = new FileWriter(dataFile, true);
            file.write(obj.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addSurvey(String id, String title, String description){
        JSONObject survey = new JSONObject();

        survey.put("id", id);
        survey.put("title", title);
        survey.put("description", description);
        survey.put("createdTime", new Date().getTime());

        this.addRecord(survey);
    }






}
