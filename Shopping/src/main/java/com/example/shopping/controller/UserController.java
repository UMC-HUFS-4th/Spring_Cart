package com.example.shopping.controller;

import com.example.shopping.entity.User;
import com.example.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/shopping/user") //http://localhost::8080/shopping/item
@RestController

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(
            @RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok("회원가입되었습니다: " + user.getUsername());
    }

    @GetMapping("/get/{user_id}")
    public ResponseEntity<User> getUser(@PathVariable Long user_id) {
        User user = userService.getUser(user_id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/update/{user_id}")
    public ResponseEntity<String> updateUser(
            @PathVariable Long user_id,
            @RequestBody User user
    ) {
        userService.updateUser(user_id, user);
        return ResponseEntity.ok(" 회원정보가 수정되었습니다.");
    }

    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long user_id
    ) {
        userService.deleteUser(user_id);
        return ResponseEntity.ok("회원탈퇴가 완료되었습니다.");
    }

}
