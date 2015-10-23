package eu.jmlabs.research.jiraRESTparser.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Mark0 on 21/10/15.
 */
@Entity
@Table(name = "jira_change_group", schema = "", catalog = "database")
public class JiraChangeGroupEntity {
    private int id;
    private int changeGroupId;
    private int issueId;
    private String author;
    private Timestamp createdDate;

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
    @Column(name = "change_group_id", nullable = false, insertable = true, updatable = true)
    public int getChangeGroupId() {
        return changeGroupId;
    }

    public void setChangeGroupId(int changeGroupId) {
        this.changeGroupId = changeGroupId;
    }

    @Basic
    @Column(name = "issue_id", nullable = false, insertable = true, updatable = true)
    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    @Basic
    @Column(name = "author", nullable = true, insertable = true, updatable = true, length = 255)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "createdDate", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JiraChangeGroupEntity that = (JiraChangeGroupEntity) o;

        if (id != that.id) return false;
        if (changeGroupId != that.changeGroupId) return false;
        if (issueId != that.issueId) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + changeGroupId;
        result = 31 * result + issueId;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
