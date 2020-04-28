package pl.cekus.rssappserver.controller;

import org.springframework.web.bind.annotation.*;
import pl.cekus.rssappserver.model.User;
import pl.cekus.rssappserver.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.createUser(user);
    }
}
