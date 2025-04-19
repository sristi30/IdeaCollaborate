package org.ideacollaborate.repository;

import org.ideacollaborate.model.entity.IdeaTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeaTagRepository extends JpaRepository<IdeaTag, IdeaTag.IdeaTagId> {

}
