package com.anime.app.controller;

import com.anime.app.domain.News;
import com.anime.app.dto.UserDTO;
import com.anime.app.service.NewsService;
import com.anime.app.service.UserService;
import com.backblaze.b2.client.exceptions.B2Exception;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping(consumes = {
          MediaType.MULTIPART_FORM_DATA_VALUE,
          MediaType.APPLICATION_FORM_URLENCODED_VALUE,
          MediaType.APPLICATION_JSON_VALUE
  })
  public ResponseEntity<UserDTO> test(
          @RequestParam("files") MultipartFile[] files
          //@RequestBody UserDTO userDTO
  ) {
    UserDTO userDTO = new UserDTO();
    userService.createUser(userDTO, files);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping(path = "/{id}/news")
  public ResponseEntity<List<News>> getUserNews(@PathVariable Long id) {
    return new ResponseEntity<>(userService.getUserNews(id), HttpStatus.OK);
  }
}
