package voting.domain;

import java.util.Date;

/**
 * Created by lili on 4/11/2016.
 */
public class Survey {
    private long id;
    private String title;
    private String description;
    private Date createdTime = new Date();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
