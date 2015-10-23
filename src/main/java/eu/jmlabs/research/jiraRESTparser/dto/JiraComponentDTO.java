package eu.jmlabs.research.jiraRESTparser.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Mark0
 * Date: 6/12/14
 * Time: 3:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class JiraComponentDTO implements Serializable {
    int id;
    String name;
    String assigneeType;
    String realAssigneeType;
    boolean isAssigneeTypeValid;

    public JiraComponentDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssigneeType() {
        return assigneeType;
    }

    public void setAssigneeType(String assigneeType) {
        this.assigneeType = assigneeType;
    }

    public String getRealAssigneeType() {
        return realAssigneeType;
    }

    public void setRealAssigneeType(String realAssigneeType) {
        this.realAssigneeType = realAssigneeType;
    }

    public boolean isAssigneeTypeValid() {
        return isAssigneeTypeValid;
    }

    public void setAssigneeTypeValid(boolean assigneeTypeValid) {
        isAssigneeTypeValid = assigneeTypeValid;
    }
}
