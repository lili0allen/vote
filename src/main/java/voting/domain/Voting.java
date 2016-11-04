package voting.domain;

import java.util.Date;

/**
 * Created by lili on 4/11/2016.
 */
public class Voting {

    private long id;
    private String content;
    private Date createdTime = new Date();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedTime(){
        return createdTime;
    }




}
