package org.ideacollaborate.service;

import org.ideacollaborate.exception.ResourceNotFoundException;
import org.ideacollaborate.model.dto.IdeaRequest;
import org.ideacollaborate.model.entity.*;
import org.ideacollaborate.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IdeaService {

    private final IdeaRepository ideaRepository;
    private final TagRepository tagRepository;
    private final IdeaTagRepository ideaTagRepository;
    private final CollaborationRepository collaborationRepository;
    private final VoteRepository voteRepository;

    @Autowired
    public IdeaService(IdeaRepository ideaRepository,
                       TagRepository tagRepository,
                       IdeaTagRepository ideaTagRepository,
                       CollaborationRepository collaborationRepository,
                       VoteRepository voteRepository) {
        this.ideaRepository = ideaRepository;
        this.tagRepository = tagRepository;
        this.ideaTagRepository = ideaTagRepository;
        this.collaborationRepository = collaborationRepository;
        this.voteRepository = voteRepository;
    }

    @Transactional
    public Idea addIdea(IdeaRequest ideaRequest) {
        Employee employee = (Employee) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Idea idea = new Idea(ideaRequest.getTitle(), ideaRequest.getDescription(), employee, LocalDateTime.now());
        ideaRepository.save(idea);

        for (String tagName : ideaRequest.getTags()) {

            // Check if tag is valid (must exist in DB)
            Tag tag = tagRepository.findByName(tagName)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Invalid tag: " + tagName
                    ));

            // Create the relation entry
            IdeaTag.IdeaTagId ideaTagId = new IdeaTag.IdeaTagId();
            ideaTagId.setIdeaId(idea.getIdeaId());
            ideaTagId.setTagId(tag.getTagId());

            IdeaTag ideaTag = new IdeaTag(ideaTagId);
            ideaTagRepository.save(ideaTag);
        }

        return idea;
    }

    public List<Idea> getAllIdeas(String sortBy) {
        if (sortBy.equals("voteCount")) {
            return ideaRepository.findAllByOrderByVoteCountDesc();
        }
        return ideaRepository.findAllByOrderByCreationDateDesc();
    }

    @Transactional
    public void voteOnIdea(Long ideaId) {
        Employee employee = (Employee) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        // Fetch the idea by its ID
        Idea idea = ideaRepository.findById(ideaId)
                .orElseThrow(() -> new ResourceNotFoundException("Idea not found with id: " + ideaId));

        // Check if the employee is not the owner of the idea
        if (idea.getCreatedBy().getEmployeeId().equals(employee.getEmployeeId())) {
            throw new IllegalStateException("You cannot vote on your own idea.");
        }

        // Create a new vote object
        Vote vote = new Vote(employee, idea, LocalDateTime.now());

        // Save the vote in the database
        if (!voteRepository.existsByEmployee_EmployeeIdAndIdea_IdeaId(employee.getEmployeeId(), ideaId)) {
            voteRepository.save(vote);
        }
    }

    @Transactional
    public void collaborateOnIdea(Long ideaId) {
        Employee employee = (Employee) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Idea idea = ideaRepository.findById(ideaId)
                .orElseThrow(() -> new ResourceNotFoundException("Idea not found with id: " + ideaId));
        Collaboration collaboration = new Collaboration(employee, idea, LocalDateTime.now());
        if (!collaborationRepository.existsByIdea_IdeaIdAndEmployee_EmployeeId(ideaId, employee.getEmployeeId())) {
            collaborationRepository.save(collaboration);
        }
    }

    @Transactional(readOnly = true)
    public List<Employee> getCollaborators(Long ideaId) {


        List<Collaboration> collaborations = collaborationRepository.findByIdea_IdeaId(ideaId);

        return collaborations.stream()
                .map(Collaboration::getEmployee)
                .collect(Collectors.toList());

    }


}
