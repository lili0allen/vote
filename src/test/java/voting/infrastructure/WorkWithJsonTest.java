package voting.infrastructure;

import org.junit.Before;
import voting.domain.Vote;

import static org.junit.Assert.assertEquals;

public class WorkWithJsonTest {
    private WorkWithJson testInstance;

    private String surveyId = "5d5e33ce-308f-42cd-ae7e-f9841c290009";

    @Before
    public void setUp() throws Exception {
        this.testInstance = new WorkWithJson();
    }

    @org.junit.Test
    public void getVoteCount() throws Exception {
        assertEquals(2, this.testInstance.getVoteCount(surveyId, Vote.GOOD));
        assertEquals(0, this.testInstance.getVoteCount(surveyId, Vote.POOR));
    }

}