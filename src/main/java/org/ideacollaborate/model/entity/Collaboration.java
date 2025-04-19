package org.ideacollaborate.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Collaboration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long collaborationId;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "idea_id", referencedColumnName = "idea_id")
    private Idea idea;

    private LocalDateTime expressedAt;

    public Collaboration(Employee employee, Idea idea, LocalDateTime expressedAt) {
        this.employee = employee;
        this.idea = idea;
        this.expressedAt = expressedAt;
    }

    public Collaboration() {

    }

    public Long getCollaborationId() {
        return collaborationId;
    }

    public void setCollaborationId(Long collaborationId) {
        this.collaborationId = collaborationId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Idea getIdea() {
        return idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    }

    public LocalDateTime getExpressedAt() {
        return expressedAt;
    }

    public void setExpressedAt(LocalDateTime expressedAt) {
        this.expressedAt = expressedAt;
    }
}
