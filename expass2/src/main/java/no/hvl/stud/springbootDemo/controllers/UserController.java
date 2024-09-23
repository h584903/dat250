package no.hvl.stud.springbootDemo.controllers;

import no.hvl.stud.springbootDemo.components.PollManager;
import no.hvl.stud.springbootDemo.domains.PollApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
@RestController
public class UserController {
    @Autowired
    private PollManager pollManager;

    @GetMapping("/listUsers")
    public Collection<PollApp.User> listUsers() {
        return pollManager.getAllUsers();
    }
    @PostMapping("/newUser")
    public void createUser(@RequestBody PollApp.User user) {
        pollManager.addUser(user);
    }

}
