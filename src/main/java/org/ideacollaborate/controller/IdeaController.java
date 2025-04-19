package org.ideacollaborate.controller;

import org.ideacollaborate.model.dto.IdeaRequest;
import org.ideacollaborate.model.entity.Employee;
import org.ideacollaborate.model.entity.Idea;
import org.ideacollaborate.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ideas")
public class IdeaController {

    private final IdeaService ideaService;

    @Autowired
    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @PostMapping
    public ResponseEntity<Idea> addIdea(@RequestBody IdeaRequest ideaRequest) {
        Idea idea = ideaService.addIdea(ideaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(idea);
    }

    @GetMapping
    public ResponseEntity<List<Idea>> getAllIdeas(@RequestParam(value = "sortBy", defaultValue = "voteCount") String sortBy) {
        List<Idea> ideas = ideaService.getAllIdeas(sortBy);
        return ResponseEntity.ok(ideas);
    }

    @PostMapping("/{ideaId}/vote")
    public ResponseEntity<Void> voteOnIdea(@PathVariable Long ideaId) {
        ideaService.voteOnIdea(ideaId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/collaborate")
    public ResponseEntity<Void> collaborateOnIdea(@PathVariable Long id) {
        ideaService.collaborateOnIdea(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{ideaId}/collaborators")
    public ResponseEntity<List<Employee>> getCollaborators(@PathVariable Long ideaId) {
        List<Employee> collaborators = ideaService.getCollaborators(ideaId);
        return ResponseEntity.ok(collaborators);
    }
}
