package org.ideacollaborate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "org.ideacollaborate.repository")
public class IdeaCollaborateApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdeaCollaborateApplication.class, args);
    }
}
