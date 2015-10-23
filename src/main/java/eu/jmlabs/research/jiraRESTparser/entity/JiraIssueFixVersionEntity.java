package eu.jmlabs.research.jiraRESTparser.entity;

import javax.persistence.*;

/**
 * Created by Mark0 on 21/10/15.
 */
@Entity
@Table(name = "jira_issue_fix_version", schema = "", catalog = "database")
public class JiraIssueFixVersionEntity {
    private int id;
    private int issueId;
    private int versionId;
    private JiraIssueEntity issue;
    private JiraVersionEntity version;

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
    @Column(name = "issue_id", nullable = false, insertable = true, updatable = true)
    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    @Basic
    @Column(name = "version_id", nullable = false, insertable = true, updatable = true)
    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JiraIssueFixVersionEntity that = (JiraIssueFixVersionEntity) o;

        if (id != that.id) return false;
        if (issueId != that.issueId) return false;
        if (versionId != that.versionId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + issueId;
        result = 31 * result + versionId;
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

    @ManyToOne
    @JoinColumn(name = "version_id", referencedColumnName = "version_id", nullable = false, insertable = false, updatable = false)
    public JiraVersionEntity getVersion() {
        return version;
    }

    public void setVersion(JiraVersionEntity version) {
        this.version = version;
    }
}
