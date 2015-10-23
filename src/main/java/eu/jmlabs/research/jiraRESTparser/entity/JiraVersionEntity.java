package eu.jmlabs.research.jiraRESTparser.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Mark0 on 21/10/15.
 */
@Entity
@Table(name = "jira_version", schema = "", catalog = "database")
public class JiraVersionEntity {
    private int id;
    private int versionId;
    private String name;
    private String description;
    private Timestamp releaseDate;
    private JiraProjectEntity project;
    private Collection<JiraIssueFixVersionEntity> jiraIssuesWithFixVersion;

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
    @Column(name = "version_id", nullable = false, insertable = true, updatable = true)
    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
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
    @Column(name = "releaseDate", nullable = true, insertable = true, updatable = true)
    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JiraVersionEntity that = (JiraVersionEntity) o;

        if (id != that.id) return false;
        if (versionId != that.versionId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (releaseDate != null ? !releaseDate.equals(that.releaseDate) : that.releaseDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + versionId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id", nullable = false)
    public JiraProjectEntity getProject() {
        return project;
    }

    public void setProject(JiraProjectEntity project) {
        this.project = project;
    }

    @OneToMany(mappedBy = "version")
    public Collection<JiraIssueFixVersionEntity> getJiraIssuesWithFixVersion() {
        return jiraIssuesWithFixVersion;
    }

    public void setJiraIssuesWithFixVersion(Collection<JiraIssueFixVersionEntity> jiraIssuesWithFixVersion) {
        this.jiraIssuesWithFixVersion = jiraIssuesWithFixVersion;
    }
}
