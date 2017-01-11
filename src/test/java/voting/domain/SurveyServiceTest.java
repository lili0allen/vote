package voting.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SurveyServiceTest {

    private String title = "title";
    private String desc = "desc";
    private String type = "Invitation";
    private String surveyID = "67425420-dc3e-435c-bf0f-a785847f5359";
    private VoteOption voteOption = VoteOption.FAIR;

    private SurveyService instance;
    private SurveyRepositoryStub surveyRepositoryStub;

    @Before
    public void setUp() throws Exception {
        this.instance = new SurveyService();
        this.surveyRepositoryStub = new SurveyRepositoryStub();
        this.instance.setSurveyRepository(surveyRepositoryStub);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createSurveyTemplateShouldNotAllowEmptyTitle() throws Exception {
        instance.createSurveyTemplate(null, desc, type);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldNotAllowDescriptionLongerThan200Characters() throws Exception {
        String descWith201Characters = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890X";
        instance.createSurveyTemplate(title, descWith201Characters, type);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createSurveyTemplateShouldNotAllowEmptyType() throws Exception {
        instance.createSurveyTemplate(title, desc, null);
    }

    @Test
    public void testCreateSurveyTemplate() throws Exception {
        instance.createSurveyTemplate(title, desc, type);

        Survey created = surveyRepositoryStub.saved;
        assertNotNull(created);
        assertEquals(title, created.title());
        assertEquals(desc, created.description());
        assertEquals(type.toLowerCase(), created.surveyType().name().toLowerCase());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addVoteShouldNotAllowEmptySurveyID() throws Exception {
        instance.addVote(null, voteOption);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addVoteShouldNotAllowEmptyVoteOption() throws Exception {
        instance.addVote(surveyID, null);
    }

    @Test
    public void testAddVote() throws Exception {
        instance.addVote(surveyID,voteOption);
        Survey selected = surveyRepositoryStub.saved;
        assertNotNull(selected);
        assertTrue(selected.getVote(VoteOption.FAIR)==0);
    }

    private class SurveyRepositoryStub implements SurveyRepository {
        private Survey saved;

        @Override
        public void saveSurvey(Survey survey) {
            this.saved = survey;
        }

        @Override
        public Survey getSurvey(String surveyId) {
            return saved;
        }
    }

}