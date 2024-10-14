package no.hvl.stud.springbootDemo.controllers;

import no.hvl.stud.springbootDemo.components.PollManager;
import no.hvl.stud.springbootDemo.domains.PollApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class PollsController {
    @Autowired
    private PollManager pollManager;

    @PostMapping("/createPoll")
    public void createPoll(@RequestParam String username, @RequestBody PollApp.Poll poll) {
        pollManager.createPoll(poll, username);
    }

    @GetMapping("/listPolls")
    public List<PollApp.Poll> listAllPolls() {
        return pollManager.getAllPolls();
    }

    @PostMapping("/votePoll/{pollId}/vote")
    public ResponseEntity<String> addVoteToPoll(@RequestParam String username, @PathVariable String pollId, @RequestParam String voteOptionId) {

        try {
            pollManager.addVoteToPoll(username, pollId, voteOptionId);
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Vote added to the poll");

    }
    @DeleteMapping("deletePoll/{pollId}")
    public ResponseEntity<String> deletePoll(@PathVariable String pollId) {
        try {
            pollManager.deletePoll(pollId);
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Poll was deleted");
    }

}
