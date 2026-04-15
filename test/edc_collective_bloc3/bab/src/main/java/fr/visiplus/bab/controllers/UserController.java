package fr.visiplus.bab.controllers;

import fr.visiplus.bab.dtos.LoginRequest;
import fr.visiplus.bab.dtos.UserDTO;
import fr.visiplus.bab.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private UserService userService;

  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public ResponseEntity<UserDTO> login(@RequestBody LoginRequest login) {
    return ResponseEntity.of(userService.login(login));
  }

  @PostMapping("/register")
  public UserDTO register(@RequestBody User user) {
    return userService.register(user);
  }
}
