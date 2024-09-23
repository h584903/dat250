package no.hvl.stud.springbootDemo;

import java.io.*;

import no.hvl.stud.springbootDemo.components.PollManager;
import no.hvl.stud.springbootDemo.domains.PollApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class  PollManagerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PollManager pollManager;

    @Test
    public void testCreateNewUser() throws Exception {
        String userInfoJson = "{ \"username\": \"tester\", \"email\": \"tester@example.com\"}";

        MvcResult result = mockMvc.perform(post("/newUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userInfoJson))
                .andExpect(status().isOk())
                .andReturn();

        PollApp.User user = pollManager.getUserByUsername("tester");
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("tester");
        assertThat(user.getEmail()).isEqualTo("tester@example.com");
    }

    @Test
    public void testCreateNewPoll() throws Exception {
        testCreateNewUser();

        String inputJson = "{"
                + "\"question\": \"What's your favorite programming language?\","
                + "\"validUntil\": \"2024-12-31T23:59:59Z\","
                + "\"voteOptions\": ["
                + "  { \"caption\": \"Rust\", \"presentationOrder\": 1 },"
                + "  { \"caption\": \"Python\", \"presentationOrder\": 2 }"
                + "]"
                + "}";

        MvcResult result = mockMvc.perform(post("/createPoll?username=tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk())
                .andReturn();

        PollApp.Poll poll = pollManager.getAllPolls().getFirst();
        assertThat(poll).isNotNull();
        assertThat(poll.getQuestion()).isEqualTo("What's your favorite programming language?");
        assertThat(poll.getVoteOptions().size()).isEqualTo(2);
        assertThat(poll.getVoteOptions().get(0).getCaption()).isEqualTo("Rust");
        assertThat(poll.getVoteOptions().get(1).getCaption()).isEqualTo("Python");
    }

    @Test
    public void testAddVoteToPoll() throws Exception {
        testCreateNewPoll();

        PollApp.Poll poll = pollManager.getAllPolls().getFirst();

        mockMvc.perform(post("/votePoll/{pollId}/vote?username=tester&voteOptionId=1", poll.getId()))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(poll.getVotes().size()).isEqualTo(1);
        PollApp.Vote vote = poll.getVotes().get(0);
        PollApp.User user = pollManager.getUserByUsername("tester");

        assertThat(vote.getVoter()).isEqualTo(user);
        assertThat(vote.getOptionId()).isEqualTo("1");
    }
    @Test
    public void testDeletePoll() throws Exception {
        testCreateNewPoll();

        PollApp.Poll poll = pollManager.getAllPolls().getFirst();

        assertThat(pollManager.getAllPolls().size()).isEqualTo(2);

        mockMvc.perform(delete("/deletePoll/{pollId}", poll.getId()))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(pollManager.getAllPolls().size()).isEqualTo(1);
    }


}
