package org.ideacollaborate.model.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;



@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "idea_id", referencedColumnName = "idea_id")
    Idea idea;

    private LocalDateTime voteDate;

    public Vote(Employee employee, Idea idea, LocalDateTime voteDate) {
        this.employee = employee;
        this.idea = idea;
        this.voteDate = voteDate;
    }

    public Vote() {

    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
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

    public LocalDateTime getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(LocalDateTime voteDate) {
        this.voteDate = voteDate;
    }
}
