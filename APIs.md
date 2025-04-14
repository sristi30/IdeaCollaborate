## Login
POST /login: Employee login + generate OAuth token.

## Add Idea
POST /ideas → Add new idea

## List all ideas
GET /ideas

## Vote on an Idea
POST /ideas/{id}/vote

## Express interest to collaborate on an idea
POST /ideas/{id}/collaborate

## List of other employees who have expressed interest in collaborating on an idea
GET /ideas/{id}/collaborators