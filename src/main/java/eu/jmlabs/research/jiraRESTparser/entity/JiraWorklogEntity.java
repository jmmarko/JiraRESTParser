package eu.jmlabs.research.jiraRESTparser.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Mark0 on 21/10/15.
 */
@Entity
@Table(name = "jira_worklog", schema = "", catalog = "database")
public class JiraWorklogEntity {
    private int id;
    private int worklogId;
    private int issueId;
    private String author;
    private Timestamp createdDate;
    private String updateAuthor;
    private Timestamp updateDate;
    private Timestamp startDate;
    private Long timeWorked;
    private String content;
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
    @Column(name = "worklog_id", nullable = false, insertable = true, updatable = true)
    public int getWorklogId() {
        return worklogId;
    }

    public void setWorklogId(int worklogId) {
        this.worklogId = worklogId;
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

    @Basic
    @Column(name = "updateAuthor", nullable = true, insertable = true, updatable = true, length = 255)
    public String getUpdateAuthor() {
        return updateAuthor;
    }

    public void setUpdateAuthor(String updateAuthor) {
        this.updateAuthor = updateAuthor;
    }

    @Basic
    @Column(name = "updateDate", nullable = true, insertable = true, updatable = true)
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @Basic
    @Column(name = "startDate", nullable = true, insertable = true, updatable = true)
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "time_worked", nullable = true, insertable = true, updatable = true)
    public Long getTimeWorked() {
        return timeWorked;
    }

    public void setTimeWorked(Long timeWorked) {
        this.timeWorked = timeWorked;
    }

    @Basic
    @Column(name = "content", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JiraWorklogEntity that = (JiraWorklogEntity) o;

        if (id != that.id) return false;
        if (worklogId != that.worklogId) return false;
        if (issueId != that.issueId) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (updateAuthor != null ? !updateAuthor.equals(that.updateAuthor) : that.updateAuthor != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (timeWorked != null ? !timeWorked.equals(that.timeWorked) : that.timeWorked != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + worklogId;
        result = 31 * result + issueId;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (updateAuthor != null ? updateAuthor.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (timeWorked != null ? timeWorked.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
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
