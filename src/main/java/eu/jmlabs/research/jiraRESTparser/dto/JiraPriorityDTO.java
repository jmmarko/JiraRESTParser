package eu.jmlabs.research.jiraRESTparser.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Mark0
 * Date: 6/12/14
 * Time: 3:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class JiraPriorityDTO implements Serializable {
    int id;
    String description;
    String name;
    String statusColor;
    String iconUrl;

    public JiraPriorityDTO() {}

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

    public String getStatusColor() {
        return statusColor;
    }

    public void setStatusColor(String statusColor) {
        this.statusColor = statusColor;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
