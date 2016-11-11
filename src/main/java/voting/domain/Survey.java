package voting.domain;

import java.util.UUID;

public class Survey {
    private String uniqueID = UUID.randomUUID().toString();
    private String title;
    private String description;

    public String getUniqueID() {
        return uniqueID;
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
