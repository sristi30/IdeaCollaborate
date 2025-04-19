package org.ideacollaborate.repository;

import org.ideacollaborate.model.entity.Idea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long> {

    List<Idea> findAllByOrderByCreationDateDesc();

    @Query("SELECT i FROM Idea i LEFT JOIN Vote v ON v.idea.ideaId = i.ideaId GROUP BY i ORDER BY COUNT(v) DESC")
    List<Idea> findAllByOrderByVoteCountDesc();  // Assuming you store a voteCount field or compute dynamically.
}
