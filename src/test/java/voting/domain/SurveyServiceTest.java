package voting.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class SurveyServiceTest {

    private String title = "title";
    private String desc = "desc";
    private String type = "INVITATION";

    @Test(expected = IllegalArgumentException.class)
    public void createSurveyTemplateShouldNotAllowEmptyTitle() throws Exception {
        new SurveyService().createSurveyTemplate(null, desc, type);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldNotAllowDescriptionLongerThan200Characters() throws Exception {
        String descWith201Characters = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890X";
        new SurveyService().createSurveyTemplate(title, descWith201Characters, type);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createSurveyTemplateShouldNotAllowEmptyType() throws Exception {
        new SurveyService().createSurveyTemplate(title, desc, null);
    }

    @Test
    public void createSurveyTemplate() throws Exception {

    }

    @Test
    public void getSurveyTemplate() throws Exception {

    }

    @Test
    public void addVote() throws Exception {

    }

}