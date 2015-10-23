package eu.jmlabs.research.jiraRESTparser.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Mark0 on 21/10/15.
 */
@Entity
@Table(name = "jira_component", schema = "", catalog = "database")
public class JiraComponentEntity {
    private int id;
    private int componentId;
    private Integer projectId;
    private String name;
    private String lead;
    private Collection<JiraIssueComponentEntity> jiraIssuesWithComponent;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "component_id", nullable = false, insertable = true, updatable = true)
    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    @Basic
    @Column(name = "project_id", nullable = true, insertable = true, updatable = true)
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "lead", nullable = true, insertable = true, updatable = true, length = 255)
    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JiraComponentEntity that = (JiraComponentEntity) o;

        if (id != that.id) return false;
        if (componentId != that.componentId) return false;
        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (lead != null ? !lead.equals(that.lead) : that.lead != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + componentId;
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lead != null ? lead.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "component")
    public Collection<JiraIssueComponentEntity> getJiraIssuesWithComponent() {
        return jiraIssuesWithComponent;
    }

    public void setJiraIssuesWithComponent(Collection<JiraIssueComponentEntity> jiraIssuesWithComponent) {
        this.jiraIssuesWithComponent = jiraIssuesWithComponent;
    }
}
