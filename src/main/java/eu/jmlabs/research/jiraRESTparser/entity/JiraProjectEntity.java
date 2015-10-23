package eu.jmlabs.research.jiraRESTparser.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Mark0 on 21/10/15.
 */
@Entity
@Table(name = "jira_project", schema = "", catalog = "database")
public class JiraProjectEntity {
    private int id;
    private String name;
    private String description;
    private int projectId;
    private String leader;
    private String projectKey;
    private Collection<JiraIssueEntity> issues;
    private Collection<JiraVersionEntity> versions;

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
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "project_id", nullable = false, insertable = true, updatable = true)
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "leader", nullable = true, insertable = true, updatable = true, length = 255)
    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    @Basic
    @Column(name = "project_key", nullable = true, insertable = true, updatable = true, length = 45)
    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JiraProjectEntity that = (JiraProjectEntity) o;

        if (id != that.id) return false;
        if (projectId != that.projectId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (leader != null ? !leader.equals(that.leader) : that.leader != null) return false;
        if (projectKey != null ? !projectKey.equals(that.projectKey) : that.projectKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + projectId;
        result = 31 * result + (leader != null ? leader.hashCode() : 0);
        result = 31 * result + (projectKey != null ? projectKey.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "project")
    public Collection<JiraIssueEntity> getIssues() {
        return issues;
    }

    public void setIssues(Collection<JiraIssueEntity> issues) {
        this.issues = issues;
    }

    @OneToMany(mappedBy = "project")
    public Collection<JiraVersionEntity> getVersions() {
        return versions;
    }

    public void setVersions(Collection<JiraVersionEntity> versions) {
        this.versions = versions;
    }
}
