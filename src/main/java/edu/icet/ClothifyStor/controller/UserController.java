package edu.icet.ClothifyStor.controller;

import edu.icet.ClothifyStor.model.User;
import edu.icet.ClothifyStor.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class UserController {

    final UserService userService;

  @PostMapping("/user")
  ResponseEntity<?> seve(@RequestBody User user){
      try{
          return ResponseEntity.ok(userService.save(user));
      } catch (IllegalArgumentException e) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
      }
    }
    @GetMapping("/user")
    List<User> retrive(){
        return userService.read();
    }
    @PutMapping("/updateUser")
    public boolean updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
      String token = userService.auth(loginRequest);
        log.info("loginRequest {}", loginRequest);

        if (token != null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful!");
            response.put("token", token);
            log.info("token {}", token);

            return ResponseEntity.ok(response);
        } else {

            return ResponseEntity.status(401).body("Invalid email or password.");
        }
    }
}
