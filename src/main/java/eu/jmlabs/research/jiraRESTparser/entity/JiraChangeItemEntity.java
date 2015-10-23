package eu.jmlabs.research.jiraRESTparser.entity;

import javax.persistence.*;

/**
 * Created by Mark0 on 21/10/15.
 */
@Entity
@Table(name = "jira_change_item", schema = "", catalog = "database")
public class JiraChangeItemEntity {
    private int id;
    private int changeItemId;
    private Integer changeGroupId;
    private String fieldType;
    private String field;
    private String oldValue;
    private String oldString;
    private String newValue;
    private String newString;

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
    @Column(name = "change_item_id", nullable = false, insertable = true, updatable = true)
    public int getChangeItemId() {
        return changeItemId;
    }

    public void setChangeItemId(int changeItemId) {
        this.changeItemId = changeItemId;
    }

    @Basic
    @Column(name = "change_group_id", nullable = true, insertable = true, updatable = true)
    public Integer getChangeGroupId() {
        return changeGroupId;
    }

    public void setChangeGroupId(Integer changeGroupId) {
        this.changeGroupId = changeGroupId;
    }

    @Basic
    @Column(name = "fieldType", nullable = true, insertable = true, updatable = true, length = 255)
    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    @Basic
    @Column(name = "field", nullable = true, insertable = true, updatable = true, length = 255)
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Basic
    @Column(name = "oldValue", nullable = true, insertable = true, updatable = true, length = 16777215)
    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    @Basic
    @Column(name = "oldString", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getOldString() {
        return oldString;
    }

    public void setOldString(String oldString) {
        this.oldString = oldString;
    }

    @Basic
    @Column(name = "newValue", nullable = true, insertable = true, updatable = true, length = 16777215)
    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    @Basic
    @Column(name = "newString", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getNewString() {
        return newString;
    }

    public void setNewString(String newString) {
        this.newString = newString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JiraChangeItemEntity that = (JiraChangeItemEntity) o;

        if (id != that.id) return false;
        if (changeItemId != that.changeItemId) return false;
        if (changeGroupId != null ? !changeGroupId.equals(that.changeGroupId) : that.changeGroupId != null)
            return false;
        if (fieldType != null ? !fieldType.equals(that.fieldType) : that.fieldType != null) return false;
        if (field != null ? !field.equals(that.field) : that.field != null) return false;
        if (oldValue != null ? !oldValue.equals(that.oldValue) : that.oldValue != null) return false;
        if (oldString != null ? !oldString.equals(that.oldString) : that.oldString != null) return false;
        if (newValue != null ? !newValue.equals(that.newValue) : that.newValue != null) return false;
        if (newString != null ? !newString.equals(that.newString) : that.newString != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + changeItemId;
        result = 31 * result + (changeGroupId != null ? changeGroupId.hashCode() : 0);
        result = 31 * result + (fieldType != null ? fieldType.hashCode() : 0);
        result = 31 * result + (field != null ? field.hashCode() : 0);
        result = 31 * result + (oldValue != null ? oldValue.hashCode() : 0);
        result = 31 * result + (oldString != null ? oldString.hashCode() : 0);
        result = 31 * result + (newValue != null ? newValue.hashCode() : 0);
        result = 31 * result + (newString != null ? newString.hashCode() : 0);
        return result;
    }
}
