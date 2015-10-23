package eu.jmlabs.research.jiraRESTparser.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Mark0 on 21/10/15.
 */
@Entity
@Table(name = "jira_issue_link_type", schema = "", catalog = "database")
public class JiraIssueLinkTypeEntity {
    private int id;
    private int issueLinkTypeId;
    private String linkname;
    private String inward;
    private String outward;
    private Collection<JiraIssueLinkEntity> issuesWithLinkType;

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
    @Column(name = "issue_link_type_id", nullable = false, insertable = true, updatable = true)
    public int getIssueLinkTypeId() {
        return issueLinkTypeId;
    }

    public void setIssueLinkTypeId(int issueLinkTypeId) {
        this.issueLinkTypeId = issueLinkTypeId;
    }

    @Basic
    @Column(name = "linkname", nullable = true, insertable = true, updatable = true, length = 255)
    public String getLinkname() {
        return linkname;
    }

    public void setLinkname(String linkname) {
        this.linkname = linkname;
    }

    @Basic
    @Column(name = "inward", nullable = true, insertable = true, updatable = true, length = 255)
    public String getInward() {
        return inward;
    }

    public void setInward(String inward) {
        this.inward = inward;
    }

    @Basic
    @Column(name = "outward", nullable = true, insertable = true, updatable = true, length = 255)
    public String getOutward() {
        return outward;
    }

    public void setOutward(String outward) {
        this.outward = outward;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JiraIssueLinkTypeEntity that = (JiraIssueLinkTypeEntity) o;

        if (id != that.id) return false;
        if (issueLinkTypeId != that.issueLinkTypeId) return false;
        if (linkname != null ? !linkname.equals(that.linkname) : that.linkname != null) return false;
        if (inward != null ? !inward.equals(that.inward) : that.inward != null) return false;
        if (outward != null ? !outward.equals(that.outward) : that.outward != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + issueLinkTypeId;
        result = 31 * result + (linkname != null ? linkname.hashCode() : 0);
        result = 31 * result + (inward != null ? inward.hashCode() : 0);
        result = 31 * result + (outward != null ? outward.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "issueLinkType")
    public Collection<JiraIssueLinkEntity> getIssuesWithLinkType() {
        return issuesWithLinkType;
    }

    public void setIssuesWithLinkType(Collection<JiraIssueLinkEntity> issuesWithLinkType) {
        this.issuesWithLinkType = issuesWithLinkType;
    }
}
