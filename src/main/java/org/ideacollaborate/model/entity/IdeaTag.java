package org.ideacollaborate.model.entity;


import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
public class IdeaTag {

    @EmbeddedId
    private IdeaTagId id;

    public IdeaTag(IdeaTagId id) {
        this.id = id;
    }

    public IdeaTag() {

    }

    @Embeddable
    public static class IdeaTagId implements Serializable {
        private Long ideaId;
        private Long tagId;

        public Long getTagId() {
            return tagId;
        }

        public void setTagId(Long tagId) {
            this.tagId = tagId;
        }

        public Long getIdeaId() {
            return ideaId;
        }

        public void setIdeaId(Long ideaId) {
            this.ideaId = ideaId;
        }
    }

    public IdeaTagId getId() {
        return id;
    }

    public void setId(IdeaTagId id) {
        this.id = id;
    }
}
