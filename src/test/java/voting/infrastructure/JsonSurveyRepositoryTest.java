package voting.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class JsonSurveyRepositoryTest {

    private String jsonUrl;
    private String surveyId;

    protected void setUp(){
        jsonUrl = "test/resources/data.json";
        surveyId = "67425420-dc3e-435c-bf0f-a785847f5359";
    }

    @Test
    public void getSurvey(){

    }

    @Test
    public void toSurveyDomain(){

    }

    @Test
    public void toSurveyJsonObject(){

    }

}