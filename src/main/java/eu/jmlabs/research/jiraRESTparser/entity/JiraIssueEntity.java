package eu.jmlabs.research.jiraRESTparser.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Mark0 on 21/10/15.
 */
@Entity
@Table(name = "jira_issue", schema = "", catalog = "database")
public class JiraIssueEntity {
    private int id;
    private int issueId;
    private String reporter;
    private String pkey;
    private String summary;
    private String description;
    private String descriptionKeywords;
    private String summaryKeywords;
    private String environment;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private Timestamp dueDate;
    private Timestamp resolutionDate;
    private Integer votes;
    private Integer watches;
    private Integer timeOriginalEstimate;
    private Integer timeEstimate;
    private Integer timeSpent;
    private Integer workflowId;
    private Integer securityId;
    private Integer fixFor;
    private Boolean issueDetailsParsed;
    private Collection<JiraAttachmentEntity> attachments;
    private JiraPriorityEntity priority;
    private JiraProjectEntity project;
    private JiraResolutionEntity resolution;
    private JiraStatusEntity status;
    private JiraIssueTypeEntity issueType;
    private Collection<JiraIssueCommentEntity> comments;
    private int projectId;
    private int typeId;
    private int priorityId;
    private Integer resolutionId;
    private int statusId;
    private String assignee;

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
    @Column(name = "reporter", nullable = true, insertable = true, updatable = true, length = 255)
    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    @Basic
    @Column(name = "pkey", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPkey() {
        return pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
    }

    @Basic
    @Column(name = "summary", nullable = true, insertable = true, updatable = true, length = 255)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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
    @Column(name = "description_keywords", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getDescriptionKeywords() {
        return descriptionKeywords;
    }

    public void setDescriptionKeywords(String descriptionKeywords) {
        this.descriptionKeywords = descriptionKeywords;
    }

    @Basic
    @Column(name = "summary_keywords", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getSummaryKeywords() {
        return summaryKeywords;
    }

    public void setSummaryKeywords(String summaryKeywords) {
        this.summaryKeywords = summaryKeywords;
    }

    @Basic
    @Column(name = "environment", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
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
    @Column(name = "updatedDate", nullable = true, insertable = true, updatable = true)
    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Basic
    @Column(name = "dueDate", nullable = true, insertable = true, updatable = true)
    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    @Basic
    @Column(name = "resolutionDate", nullable = true, insertable = true, updatable = true)
    public Timestamp getResolutionDate() {
        return resolutionDate;
    }

    public void setResolutionDate(Timestamp resolutionDate) {
        this.resolutionDate = resolutionDate;
    }

    @Basic
    @Column(name = "votes", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    @Basic
    @Column(name = "watches", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getWatches() {
        return watches;
    }

    public void setWatches(Integer watches) {
        this.watches = watches;
    }

    @Basic
    @Column(name = "time_original_estimate", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTimeOriginalEstimate() {
        return timeOriginalEstimate;
    }

    public void setTimeOriginalEstimate(Integer timeOriginalEstimate) {
        this.timeOriginalEstimate = timeOriginalEstimate;
    }

    @Basic
    @Column(name = "time_estimate", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTimeEstimate() {
        return timeEstimate;
    }

    public void setTimeEstimate(Integer timeEstimate) {
        this.timeEstimate = timeEstimate;
    }

    @Basic
    @Column(name = "time_spent", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    @Basic
    @Column(name = "workflow_id", nullable = true, insertable = true, updatable = true)
    public Integer getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Integer workflowId) {
        this.workflowId = workflowId;
    }

    @Basic
    @Column(name = "security_id", nullable = true, insertable = true, updatable = true)
    public Integer getSecurityId() {
        return securityId;
    }

    public void setSecurityId(Integer securityId) {
        this.securityId = securityId;
    }

    @Basic
    @Column(name = "fix_for", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getFixFor() {
        return fixFor;
    }

    public void setFixFor(Integer fixFor) {
        this.fixFor = fixFor;
    }

    @Basic
    @Column(name = "issue_details_parsed", nullable = true, insertable = true, updatable = true)
    public Boolean getIssueDetailsParsed() {
        return issueDetailsParsed;
    }

    public void setIssueDetailsParsed(Boolean issueDetailsParsed) {
        this.issueDetailsParsed = issueDetailsParsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JiraIssueEntity that = (JiraIssueEntity) o;

        if (id != that.id) return false;
        if (issueId != that.issueId) return false;
        if (reporter != null ? !reporter.equals(that.reporter) : that.reporter != null) return false;
        if (pkey != null ? !pkey.equals(that.pkey) : that.pkey != null) return false;
        if (summary != null ? !summary.equals(that.summary) : that.summary != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (descriptionKeywords != null ? !descriptionKeywords.equals(that.descriptionKeywords) : that.descriptionKeywords != null)
            return false;
        if (summaryKeywords != null ? !summaryKeywords.equals(that.summaryKeywords) : that.summaryKeywords != null)
            return false;
        if (environment != null ? !environment.equals(that.environment) : that.environment != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (updatedDate != null ? !updatedDate.equals(that.updatedDate) : that.updatedDate != null) return false;
        if (dueDate != null ? !dueDate.equals(that.dueDate) : that.dueDate != null) return false;
        if (resolutionDate != null ? !resolutionDate.equals(that.resolutionDate) : that.resolutionDate != null)
            return false;
        if (votes != null ? !votes.equals(that.votes) : that.votes != null) return false;
        if (watches != null ? !watches.equals(that.watches) : that.watches != null) return false;
        if (timeOriginalEstimate != null ? !timeOriginalEstimate.equals(that.timeOriginalEstimate) : that.timeOriginalEstimate != null)
            return false;
        if (timeEstimate != null ? !timeEstimate.equals(that.timeEstimate) : that.timeEstimate != null) return false;
        if (timeSpent != null ? !timeSpent.equals(that.timeSpent) : that.timeSpent != null) return false;
        if (workflowId != null ? !workflowId.equals(that.workflowId) : that.workflowId != null) return false;
        if (securityId != null ? !securityId.equals(that.securityId) : that.securityId != null) return false;
        if (fixFor != null ? !fixFor.equals(that.fixFor) : that.fixFor != null) return false;
        if (issueDetailsParsed != null ? !issueDetailsParsed.equals(that.issueDetailsParsed) : that.issueDetailsParsed != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + issueId;
        result = 31 * result + (reporter != null ? reporter.hashCode() : 0);
        result = 31 * result + (pkey != null ? pkey.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (descriptionKeywords != null ? descriptionKeywords.hashCode() : 0);
        result = 31 * result + (summaryKeywords != null ? summaryKeywords.hashCode() : 0);
        result = 31 * result + (environment != null ? environment.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (updatedDate != null ? updatedDate.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (resolutionDate != null ? resolutionDate.hashCode() : 0);
        result = 31 * result + (votes != null ? votes.hashCode() : 0);
        result = 31 * result + (watches != null ? watches.hashCode() : 0);
        result = 31 * result + (timeOriginalEstimate != null ? timeOriginalEstimate.hashCode() : 0);
        result = 31 * result + (timeEstimate != null ? timeEstimate.hashCode() : 0);
        result = 31 * result + (timeSpent != null ? timeSpent.hashCode() : 0);
        result = 31 * result + (workflowId != null ? workflowId.hashCode() : 0);
        result = 31 * result + (securityId != null ? securityId.hashCode() : 0);
        result = 31 * result + (fixFor != null ? fixFor.hashCode() : 0);
        result = 31 * result + (issueDetailsParsed != null ? issueDetailsParsed.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "issue")
    public Collection<JiraAttachmentEntity> getAttachments() {
        return attachments;
    }

    public void setAttachments(Collection<JiraAttachmentEntity> attachments) {
        this.attachments = attachments;
    }

    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "priority_id", nullable = false, insertable = false, updatable = false)
    public JiraPriorityEntity getPriority() {
        return priority;
    }

    public void setPriority(JiraPriorityEntity priority) {
        this.priority = priority;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id", nullable = false, insertable = false, updatable = false)
    public JiraProjectEntity getProject() {
        return project;
    }

    public void setProject(JiraProjectEntity project) {
        this.project = project;
    }

    @ManyToOne
    @JoinColumn(name = "resolution_id", referencedColumnName = "resolution_id", insertable = false, updatable = false)
    public JiraResolutionEntity getResolution() {
        return resolution;
    }

    public void setResolution(JiraResolutionEntity resolution) {
        this.resolution = resolution;
    }

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "issue_type_id", nullable = false, insertable = false, updatable = false)
    public JiraIssueTypeEntity getIssueType() {
        return issueType;
    }

    public void setIssueType(JiraIssueTypeEntity issueType) {
        this.issueType = issueType;
    }

    @Basic
    @Column(name = "assignee", nullable = true, insertable = true, updatable = true, length = 255)
    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    @OneToMany(mappedBy = "issue")
    public Collection<JiraIssueCommentEntity> getComments() {
        return comments;
    }

    public void setComments(Collection<JiraIssueCommentEntity> comments) {
        this.comments = comments;
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
    @Column(name = "type_id", nullable = false, insertable = true, updatable = true)
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "priority_id", nullable = false, insertable = true, updatable = true)
    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    @Basic
    @Column(name = "resolution_id", nullable = true, insertable = true, updatable = true)
    public Integer getResolutionId() {
        return resolutionId;
    }

    public void setResolutionId(Integer resolutionId) {
        this.resolutionId = resolutionId;
    }

    @Basic
    @Column(name = "status_id", nullable = false, insertable = true, updatable = true)
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
