package eu.jmlabs.research.jiraRESTparser.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Mark0
 * Date: 6/12/14
 * Time: 3:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class JiraResolutionDTO implements Serializable {
    int id;
    String description;
    String name;

    public JiraResolutionDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
