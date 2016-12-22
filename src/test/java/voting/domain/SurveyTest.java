package voting.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;

public class SurveyTest {
    private Survey instance;

    private String title = "title";
    private String desc = "desc";
    private SurveyType type = SurveyType.INVITATION;

    @Before
    public void setUp() throws Exception {
        this.instance = new Survey(title, desc, type);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldNotAllowEmptyOrNullTitle() throws Exception {
        new Survey(null, desc, type);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldNotAllowDescriptionLongerThan200Characters() throws Exception {
        String descWith201Characters = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890X";
        new Survey(title, descWith201Characters, type);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldNotAllowNullSurveyType() throws Exception {
        new Survey(title, desc, null);
    }

    @Test
    public void totalVoteCount() throws Exception {
        assertEquals(0, instance.totalVoteCount());

        instance.addVote(VoteOption.GOOD);
        instance.addVote(VoteOption.GOOD);
        instance.addVote(VoteOption.POOR);
        instance.addVote(VoteOption.FAIR);

        assertEquals(4, instance.totalVoteCount());
    }

    @Test
    public void addVoteShouldIncreaseVoteOption() throws Exception {
        assertEquals(0, instance.getVote(VoteOption.GOOD));

        instance.addVote(VoteOption.GOOD);
        instance.addVote(VoteOption.GOOD);

        assertEquals(2, instance.getVote(VoteOption.GOOD));
    }

    @Test
    public void getVote() throws Exception {
        assertEquals(0, instance.getVote(VoteOption.GOOD));
        assertEquals(0, instance.getVote(VoteOption.POOR));

        instance.addVote(VoteOption.GOOD);

        assertEquals(1, instance.getVote(VoteOption.GOOD));
        assertEquals(0, instance.getVote(VoteOption.POOR));
    }

    @Test
    public void surveyType() throws Exception {
        assertEquals(SurveyType.INVITATION, instance.surveyType());
    }

    @Test
    public void isExpired() throws Exception {
        Date dateOneMinuteAgo = new Date(System.currentTimeMillis() - 1 * 60 * 1000L);
        Map<VoteOption, Integer> votes = Collections.emptyMap();

        Survey surveyCreatedOneMinuteAgo =
                new Survey("id-001", title, desc, type, dateOneMinuteAgo.getTime(), votes);

        assertTrue(surveyCreatedOneMinuteAgo.isExpired(59 * 1000));
        assertFalse(surveyCreatedOneMinuteAgo.isExpired(61 * 1000));
    }

}