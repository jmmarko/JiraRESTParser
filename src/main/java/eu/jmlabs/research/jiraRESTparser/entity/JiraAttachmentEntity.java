package eu.jmlabs.research.jiraRESTparser.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Mark0 on 21/10/15.
 */
@Entity
@Table(name = "jira_attachment", schema = "", catalog = "database")
public class JiraAttachmentEntity {
    private int id;
    private int attachmentId;
    private String mimeType;
    private String filename;
    private Timestamp createdDate;
    private Long fileSize;
    private String filenameKeywords;
    private String content;
    private String contentKeywords;
    private String author;
    private Byte zip;
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
    @Column(name = "attachment_id", nullable = false, insertable = true, updatable = true)
    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    @Basic
    @Column(name = "mimeType", nullable = true, insertable = true, updatable = true, length = 255)
    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Basic
    @Column(name = "filename", nullable = true, insertable = true, updatable = true, length = 255)
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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
    @Column(name = "file_size", nullable = true, insertable = true, updatable = true)
    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @Basic
    @Column(name = "filename_keywords", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getFilenameKeywords() {
        return filenameKeywords;
    }

    public void setFilenameKeywords(String filenameKeywords) {
        this.filenameKeywords = filenameKeywords;
    }

    @Basic
    @Column(name = "content", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "content_keywords", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getContentKeywords() {
        return contentKeywords;
    }

    public void setContentKeywords(String contentKeywords) {
        this.contentKeywords = contentKeywords;
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
    @Column(name = "zip", nullable = true, insertable = true, updatable = true)
    public Byte getZip() {
        return zip;
    }

    public void setZip(Byte zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JiraAttachmentEntity that = (JiraAttachmentEntity) o;

        if (id != that.id) return false;
        if (attachmentId != that.attachmentId) return false;
        if (mimeType != null ? !mimeType.equals(that.mimeType) : that.mimeType != null) return false;
        if (filename != null ? !filename.equals(that.filename) : that.filename != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (fileSize != null ? !fileSize.equals(that.fileSize) : that.fileSize != null) return false;
        if (filenameKeywords != null ? !filenameKeywords.equals(that.filenameKeywords) : that.filenameKeywords != null)
            return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (contentKeywords != null ? !contentKeywords.equals(that.contentKeywords) : that.contentKeywords != null)
            return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (zip != null ? !zip.equals(that.zip) : that.zip != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + attachmentId;
        result = 31 * result + (mimeType != null ? mimeType.hashCode() : 0);
        result = 31 * result + (filename != null ? filename.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (fileSize != null ? fileSize.hashCode() : 0);
        result = 31 * result + (filenameKeywords != null ? filenameKeywords.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (contentKeywords != null ? contentKeywords.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
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
