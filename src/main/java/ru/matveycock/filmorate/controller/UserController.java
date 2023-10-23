package ru.matveycock.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.matveycock.filmorate.exception.ValidationException;
import ru.matveycock.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.matveycock.filmorate.service.ValidationService.validateUser;

@Slf4j
@RestController
@Validated
public class UserController {
    private final Map<Integer, User> users = new HashMap();

    @PutMapping("/users")
    public void updateUser(@Valid @RequestBody User user){
        try {
            validateUser(user);
            users.remove(user.getId());
            users.put(user.getId(), user);
        }catch (ValidationException exception){
            log.error("User is not validated");
        }
    }

    @PostMapping("/users")
    public void addUser(@Valid @RequestBody User user){
        try {
            validateUser(user);
            users.put(user.getId(), user);
        }catch (ValidationException exception){
            log.error("User is not validated");
        }
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        log.info("Был выполнен запрос всех пользователей");
        return  new ArrayList<User>(users.values());
    }
}
