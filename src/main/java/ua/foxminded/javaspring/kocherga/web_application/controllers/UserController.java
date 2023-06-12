package ua.foxminded.javaspring.kocherga.web_application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.foxminded.javaspring.kocherga.web_application.models.User;
import ua.foxminded.javaspring.kocherga.web_application.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/message")
    public String getMessage() {
        return "Hi, I/m from Controller";
    }

//    @PostMapping
//    public User saveUser(@RequestBody User user) {
//        return userService.saveUser(user);
//    }
}
