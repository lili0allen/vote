package voting.infrastructure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SaveToJson {
    private String fileDir = "data/data.json";

    public SaveToJson(){

    }

    private void addRecord(JSONObject obj){
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File dataFile = new File(classLoader.getResource(this.fileDir).getFile());

            FileWriter file = new FileWriter(dataFile);
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
