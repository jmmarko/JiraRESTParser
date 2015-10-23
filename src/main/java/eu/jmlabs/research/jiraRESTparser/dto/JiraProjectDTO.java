package eu.jmlabs.research.jiraRESTparser.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mark0
 * Date: 6/12/14
 * Time: 3:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class JiraProjectDTO implements Serializable {
    int id;
    String key;
    String name;
    String description;
    JiraUserDTO lead;
    List<JiraComponentDTO> components;
    List<JiraIssueTypeDTO> issueTypes;
    String assigneeType;
    List<JiraVersionDTO> versions;
    Map<String, String> roles;

    public JiraProjectDTO() {}

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JiraUserDTO getLead() {
        return lead;
    }

    public void setLead(JiraUserDTO lead) {
        this.lead = lead;
    }

    public List<JiraComponentDTO> getComponents() {
        return components;
    }

    public void setComponents(List<JiraComponentDTO> components) {
        this.components = components;
    }

    public List<JiraIssueTypeDTO> getIssueTypes() {
        return issueTypes;
    }

    public void setIssueTypes(List<JiraIssueTypeDTO> issueTypes) {
        this.issueTypes = issueTypes;
    }

    public String getAssigneeType() {
        return assigneeType;
    }

    public void setAssigneeType(String assigneeType) {
        this.assigneeType = assigneeType;
    }

    public List<JiraVersionDTO> getVersions() {
        return versions;
    }

    public void setVersions(List<JiraVersionDTO> versions) {
        this.versions = versions;
    }

    public Map<String, String> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, String> roles) {
        this.roles = roles;
    }
}
