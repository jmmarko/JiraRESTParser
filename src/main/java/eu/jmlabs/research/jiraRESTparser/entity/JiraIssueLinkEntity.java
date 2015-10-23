package eu.jmlabs.research.jiraRESTparser.entity;

import javax.persistence.*;

/**
 * Created by Mark0 on 21/10/15.
 */
@Entity
@Table(name = "jira_issue_link", schema = "", catalog = "database")
public class JiraIssueLinkEntity {
    private int id;
    private int issueLinkId;
    private Integer linkTypeId;
    private Integer sourceIssueId;
    private Integer destinationIssueId;
    private Integer sequence;
    private JiraIssueEntity destinationIssue;
    private JiraIssueLinkTypeEntity issueLinkType;
    private JiraIssueEntity sourceIssue;

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
    @Column(name = "issue_link_id", nullable = false, insertable = true, updatable = true)
    public int getIssueLinkId() {
        return issueLinkId;
    }

    public void setIssueLinkId(int issueLinkId) {
        this.issueLinkId = issueLinkId;
    }

    @Basic
    @Column(name = "link_type_id", nullable = true, insertable = true, updatable = true)
    public Integer getLinkTypeId() {
        return linkTypeId;
    }

    public void setLinkTypeId(Integer linkTypeId) {
        this.linkTypeId = linkTypeId;
    }

    @Basic
    @Column(name = "source_issue_id", nullable = true, insertable = true, updatable = true)
    public Integer getSourceIssueId() {
        return sourceIssueId;
    }

    public void setSourceIssueId(Integer sourceIssueId) {
        this.sourceIssueId = sourceIssueId;
    }

    @Basic
    @Column(name = "destination_issue_id", nullable = true, insertable = true, updatable = true)
    public Integer getDestinationIssueId() {
        return destinationIssueId;
    }

    public void setDestinationIssueId(Integer destinationIssueId) {
        this.destinationIssueId = destinationIssueId;
    }

    @Basic
    @Column(name = "sequence", nullable = true, insertable = true, updatable = true)
    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JiraIssueLinkEntity that = (JiraIssueLinkEntity) o;

        if (id != that.id) return false;
        if (issueLinkId != that.issueLinkId) return false;
        if (linkTypeId != null ? !linkTypeId.equals(that.linkTypeId) : that.linkTypeId != null) return false;
        if (sourceIssueId != null ? !sourceIssueId.equals(that.sourceIssueId) : that.sourceIssueId != null)
            return false;
        if (destinationIssueId != null ? !destinationIssueId.equals(that.destinationIssueId) : that.destinationIssueId != null)
            return false;
        if (sequence != null ? !sequence.equals(that.sequence) : that.sequence != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + issueLinkId;
        result = 31 * result + (linkTypeId != null ? linkTypeId.hashCode() : 0);
        result = 31 * result + (sourceIssueId != null ? sourceIssueId.hashCode() : 0);
        result = 31 * result + (destinationIssueId != null ? destinationIssueId.hashCode() : 0);
        result = 31 * result + (sequence != null ? sequence.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "destination_issue_id", referencedColumnName = "issue_id", insertable = false, updatable = false)
    public JiraIssueEntity getDestinationIssue() {
        return destinationIssue;
    }

    public void setDestinationIssue(JiraIssueEntity destinationIssue) {
        this.destinationIssue = destinationIssue;
    }

    @ManyToOne
    @JoinColumn(name = "link_type_id", referencedColumnName = "issue_link_type_id", insertable = false, updatable = false)
    public JiraIssueLinkTypeEntity getIssueLinkType() {
        return issueLinkType;
    }

    public void setIssueLinkType(JiraIssueLinkTypeEntity issueLinkType) {
        this.issueLinkType = issueLinkType;
    }

    @ManyToOne
    @JoinColumn(name = "source_issue_id", referencedColumnName = "issue_id", insertable = false, updatable = false)
    public JiraIssueEntity getSourceIssue() {
        return sourceIssue;
    }

    public void setSourceIssue(JiraIssueEntity sourceIssue) {
        this.sourceIssue = sourceIssue;
    }
}
