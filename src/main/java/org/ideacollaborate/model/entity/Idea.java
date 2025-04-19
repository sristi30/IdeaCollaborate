package org.ideacollaborate.model.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Idea {

    @Id
    @Column(name = "idea_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ideaId;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "employee_id")
    private Employee createdBy;

    @CreatedDate
    private LocalDateTime creationDate;

    public Idea(String title, String description, Employee createdBy, LocalDateTime creationDate) {
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
    }

    public Idea() {

    }

    public Long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(Long ideaId) {
        this.ideaId = ideaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
