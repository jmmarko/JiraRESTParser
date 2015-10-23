package eu.jmlabs.research.jiraRESTparser.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Mark0
 * Date: 6/12/14
 * Time: 3:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class JiraVersionDTO implements Serializable {
    int id;
    String name;
    String description;
    boolean archived;
    boolean released;
    Date releaseDate;
    String userReleaseDate;

    public JiraVersionDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public boolean isReleased() {
        return released;
    }

    public void setReleased(boolean released) {
        this.released = released;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getUserReleaseDate() {
        return userReleaseDate;
    }

    public void setUserReleaseDate(String userReleaseDate) {
        this.userReleaseDate = userReleaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
