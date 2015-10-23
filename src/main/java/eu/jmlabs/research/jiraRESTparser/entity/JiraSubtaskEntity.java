package eu.jmlabs.research.jiraRESTparser.entity;

import javax.persistence.*;

/**
 * Created by Mark0 on 21/10/15.
 */
@Entity
@Table(name = "jira_subtask", schema = "", catalog = "database")
public class JiraSubtaskEntity {
    private int id;
    private int parentIssueId;
    private int childIssueId;

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
    @Column(name = "parent_issue_id", nullable = false, insertable = true, updatable = true)
    public int getParentIssueId() {
        return parentIssueId;
    }

    public void setParentIssueId(int parentIssueId) {
        this.parentIssueId = parentIssueId;
    }

    @Basic
    @Column(name = "child_issue_id", nullable = false, insertable = true, updatable = true)
    public int getChildIssueId() {
        return childIssueId;
    }

    public void setChildIssueId(int childIssueId) {
        this.childIssueId = childIssueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JiraSubtaskEntity that = (JiraSubtaskEntity) o;

        if (id != that.id) return false;
        if (parentIssueId != that.parentIssueId) return false;
        if (childIssueId != that.childIssueId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + parentIssueId;
        result = 31 * result + childIssueId;
        return result;
    }
}
