package org.ideacollaborate.repository;

import org.ideacollaborate.model.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    boolean existsByEmployee_EmployeeIdAndIdea_IdeaId(String employeeId, Long ideaId);

}
