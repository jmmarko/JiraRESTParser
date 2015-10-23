package eu.jmlabs.research.jiraRESTparser.entity;

import javax.persistence.*;

/**
 * Created by Mark0 on 21/10/15.
 */
@Entity
@Table(name = "jira_issue_component", schema = "", catalog = "database")
public class JiraIssueComponentEntity {
    private int id;
    private int componentId;
    private int issueId;
    private JiraComponentEntity component;
    private JiraIssueEntity issue;

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
    @Column(name = "issue_id", nullable = false, insertable = true, updatable = true)
    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JiraIssueComponentEntity that = (JiraIssueComponentEntity) o;

        if (id != that.id) return false;
        if (componentId != that.componentId) return false;
        if (issueId != that.issueId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + componentId;
        result = 31 * result + issueId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "component_id", referencedColumnName = "component_id", nullable = false, insertable = false, updatable = false)
    public JiraComponentEntity getComponent() {
        return component;
    }

    public void setComponent(JiraComponentEntity component) {
        this.component = component;
    }

    @ManyToOne
    @JoinColumn(name = "issue_id", referencedColumnName = "issue_id", nullable = false, insertable = false, updatable = false)
    public JiraIssueEntity getIssue() {
        return issue;
    }

    public void setIssue(JiraIssueEntity issue) {
        this.issue = issue;
    }
}
