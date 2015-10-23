package eu.jmlabs.research.jiraRESTparser.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Mark0 on 21/10/15.
 */
@Entity
@Table(name = "jira_issue_comment", schema = "", catalog = "database")
public class JiraIssueCommentEntity {
    private int id;
    private String author;
    private String body;
    private String updateAuthor;
    private Timestamp created;
    private Timestamp updated;
    private int commentId;
    private JiraIssueEntity issue;
    private int issueId;

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
    @Column(name = "author", nullable = true, insertable = true, updatable = true, length = 255)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "body", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "update_author", nullable = true, insertable = true, updatable = true, length = 255)
    public String getUpdateAuthor() {
        return updateAuthor;
    }

    public void setUpdateAuthor(String updateAuthor) {
        this.updateAuthor = updateAuthor;
    }

    @Basic
    @Column(name = "created", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "updated", nullable = true, insertable = true, updatable = true)
    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    @Basic
    @Column(name = "comment_id", nullable = false, insertable = true, updatable = true)
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JiraIssueCommentEntity that = (JiraIssueCommentEntity) o;

        if (id != that.id) return false;
        if (commentId != that.commentId) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        if (updateAuthor != null ? !updateAuthor.equals(that.updateAuthor) : that.updateAuthor != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (updated != null ? !updated.equals(that.updated) : that.updated != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (updateAuthor != null ? updateAuthor.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        result = 31 * result + commentId;
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

    @Basic
    @Column(name = "issue_id", nullable = false, insertable = true, updatable = true)
    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }
}
