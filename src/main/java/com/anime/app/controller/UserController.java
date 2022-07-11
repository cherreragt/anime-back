package com.anime.app.controller;

import com.anime.app.dto.UserDTO;
import com.anime.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user")
@AllArgsConstructor
public class UserController {

  public final UserService userService;

  @PostMapping
  public ResponseEntity<UserDTO> test(
          @RequestBody UserDTO userDTO
  ) {
    userService.createUser(userDTO);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
