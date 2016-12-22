package voting.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Survey {
    private String id;
    private String title;
    private String description;
    private SurveyType surveyType;
    private long createdTime;
    private Map<VoteOption, Integer> votes;

    public Survey(String title, String description, SurveyType surveyType) {
        if(title == null || title.isEmpty()) {
            throw new IllegalArgumentException("title is required");
        }

        if(description != null && description.length() > 200) {
            throw new IllegalArgumentException("description must not contain more than 200 characters");
        }

        if(surveyType == null) {
            throw new IllegalArgumentException("surveyType must not be null");
        }

        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.surveyType = surveyType;
        this.createdTime = new Date().getTime();

        this.votes = new HashMap<>();
        for(VoteOption vote: VoteOption.values()) {
            this.votes.put(vote, 0);
        }
    }

    // constructor
    // for deserialisation only
    public Survey(String id, String title, String description, SurveyType surveyType, long createdTime, Map<VoteOption, Integer> votes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.surveyType = surveyType;
        this.createdTime = createdTime;
        this.votes = votes;
    }

    public int totalVoteCount() {
        int totalCount = 0;
        for(VoteOption vote: VoteOption.values()) {
            totalCount += this.votes.get(vote);
        }
        return totalCount;
    }

    public void addVote(VoteOption vote) {
        // check vote note null
        int previousCount = this.votes.get(vote);
        this.votes.put(vote, previousCount + 1);
    }

    public int getVote(VoteOption vote) {
        return this.votes.get(vote);
    }

    public String id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public SurveyType surveyType() {
        return surveyType;
    }

    public long createdTime() {
        return createdTime;
    }

    public boolean isExpired(int lifeSpanInMilliSeconds) {
        long currentTime = new Date().getTime();
        long countDown = currentTime - this.createdTime;

        return ((int) countDown) >= lifeSpanInMilliSeconds;
    }
}
