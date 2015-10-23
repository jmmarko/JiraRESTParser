package eu.jmlabs.research.jiraRESTparser.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Mark0 on 21/10/15.
 */
@Entity
@Table(name = "jira_resolution", schema = "", catalog = "database")
public class JiraResolutionEntity {
    private int id;
    private int resolutionId;
    private String name;
    private String description;
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
    @Column(name = "resolution_id", nullable = false, insertable = true, updatable = true)
    public int getResolutionId() {
        return resolutionId;
    }

    public void setResolutionId(int resolutionId) {
        this.resolutionId = resolutionId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JiraResolutionEntity that = (JiraResolutionEntity) o;

        if (id != that.id) return false;
        if (resolutionId != that.resolutionId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + resolutionId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "resolution")
    public Collection<JiraIssueEntity> getIssues() {
        return issues;
    }

    public void setIssues(Collection<JiraIssueEntity> issues) {
        this.issues = issues;
    }
}
