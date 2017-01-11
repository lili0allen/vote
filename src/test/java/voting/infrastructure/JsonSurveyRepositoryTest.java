package voting.infrastructure;

import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import voting.domain.Survey;
import voting.domain.SurveyType;
import voting.domain.VoteOption;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class JsonSurveyRepositoryTest {

    private String jsonUrl;
    private String surveyId;
    private File file;
    private JSONParser parser = new JSONParser();
    private JsonSurveyRepository testInstance;

    @Autowired
    ApplicationContext applicationContext;


    protected void setUp(){
        jsonUrl = "test/resources/data.json";
        surveyId = "67425420-dc3e-435c-bf0f-a785847f5359";
    }

    @Before
    public void initTest() {
        testInstance = new JsonSurveyRepository("test/resources/data.json");
    }

    @Test
    public void testGetSurvey(){
        Map<VoteOption, Integer> votes = new HashMap<>();
        votes.put(VoteOption.FAIR, 28);
        votes.put(VoteOption.POOR, 56);
        votes.put(VoteOption.GOOD, 27);

        Survey survey = new Survey(
                "67425420-dc3e-435c-bf0f-a785847f5359",
                "test title",
                "test description",
                SurveyType.STANDALONE,
                1482186332516L,
                votes
                );

        assertEquals(survey, testInstance.getSurvey(surveyId));
    }

//    @Test
//    public void testToSurveyDomain(){
//
//    }
//
//    @Test
//    public void testToSurveyJsonObject(){
//
//    }

}