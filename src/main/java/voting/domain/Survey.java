package voting.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Survey {
    private String id;
    private String title;
    private String description;
    private long createdTime;
    private Map<Vote, Integer> votes;

    public Survey(String title, String description) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.createdTime = new Date().getTime();

        this.votes = new HashMap<>();
        for(Vote vote: Vote.values()) {
            this.votes.put(vote, 0);
        }
    }

    // constructor
    // for deserialisation only
    public Survey(String id, String title, String description, long createdTime, Map<Vote, Integer> votes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdTime = createdTime;
        this.votes = votes;
    }

    public int totalVoteCount() {
        int totalCount = 0;
        for(Vote vote: Vote.values()) {
            totalCount += this.votes.get(vote);
        }
        return totalCount;
    }

    public void addVote(Vote vote) {
        // check vote note null
        int previousCount = this.votes.get(vote);
        this.votes.put(vote, previousCount + 1);
    }

    public int getVote(Vote vote) {
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

    public long createdTime() {
        return createdTime;
    }

    public boolean isExpired(int lifeSpan) {
        long currentTime = new Date().getTime();
        long countDown = currentTime - this.createdTime;

        return ((int) countDown) >= lifeSpan;
    }
}