package org.ideacollaborate.repository;

import org.ideacollaborate.model.entity.Collaboration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollaborationRepository extends JpaRepository<Collaboration, Long> {

    List<Collaboration> findByIdea_IdeaId(Long ideaId);

    boolean existsByIdea_IdeaIdAndEmployee_EmployeeId(Long ideaId, String employeeId);
}
