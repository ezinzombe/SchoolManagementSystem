package zw.co.soskode.SchoolManagementSystem.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by tdhla on 12/7/2016.
 */
@MappedSuperclass
public abstract class BaseEntityId implements Serializable {

    private Long id;
    @DateTimeFormat(pattern = "dd/M/yyyy hh:mm:ss")
    private Date dateCreated;
    @DateTimeFormat(pattern = "dd/M/yyyy hh:mm:ss")
    private Date dateModified;
    private String createdBy;
    private String lastModifiedBy;
    private Boolean active = Boolean.TRUE;

    public BaseEntityId(Long id) {
        this.id = id;
    }

    public BaseEntityId() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    @PrePersist
    public void addPrePersistAuditInfo() {
        setCreatedDateIfNotSet();
    }

    private void setCreatedDateIfNotSet() {

        if (getDateCreated() == null) {

            setDateCreated(new Date());
        }
    }

    @Column(nullable = true)
    @CreatedBy
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(nullable = true)
    @LastModifiedBy
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Transient
    public boolean isNew() {
        return (getId() == null);
    }


}
