package eu.jmlabs.research.jiraRESTparser.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Mark0 on 21/10/15.
 */
@Entity
@Table(name = "jira_issue_type", schema = "", catalog = "database")
public class JiraIssueTypeEntity {
    private int id;
    private int issueTypeId;
    private String name;
    private String description;
    private byte[] icon;
    private Boolean subtask;
    private Collection<JiraIssueEntity> issues;

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
    @Column(name = "issue_type_id", nullable = false, insertable = true, updatable = true)
    public int getIssueTypeId() {
        return issueTypeId;
    }

    public void setIssueTypeId(int issueTypeId) {
        this.issueTypeId = issueTypeId;
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
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "icon", nullable = true, insertable = true, updatable = true)
    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    @Column(name = "subtask", nullable = true, insertable = true, updatable = true, length = 0, precision = 0)
    @Basic
    public Boolean getSubtask() {
        return subtask;
    }

    public void setSubtask(Boolean subtask) {
        subtask = subtask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JiraIssueTypeEntity that = (JiraIssueTypeEntity) o;

        if (id != that.id) return false;
        if (issueTypeId != that.issueTypeId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (!Arrays.equals(icon, that.icon)) return false;
        if (subtask != null ? !subtask.equals(that.subtask) : that.subtask != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + issueTypeId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (icon != null ? Arrays.hashCode(icon) : 0);
        result = 31 * result + (subtask != null ? subtask.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "issueType")
    public Collection<JiraIssueEntity> getIssues() {
        return issues;
    }

    public void setIssues(Collection<JiraIssueEntity> issues) {
        this.issues = issues;
    }
}
