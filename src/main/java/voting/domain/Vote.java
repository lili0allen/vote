package voting.domain;

import java.util.Date;

public class Vote {

    private long id;
    public enum Answer {GOOD, FAIR, POOR}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }




}
