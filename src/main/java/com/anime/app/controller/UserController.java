package com.anime.app.controller;

import com.anime.app.domain.News;
import com.anime.app.dto.UserDTO;
import com.anime.app.service.NewsService;
import com.anime.app.service.UserService;
import com.backblaze.b2.client.exceptions.B2Exception;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

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
  // Ejemplo de como reproducir un video mp4, para otros agregar mas headers
  @SneakyThrows
  @GetMapping(path = "/test/{file}")
  public ResponseEntity<InputStreamResource> test(@PathVariable("file") String file) {

    return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType("video/mp4;"))
            .body(new InputStreamResource(userService.b2FileResponse(file)));
  }

  @GetMapping(path = "/{id}/news")
  public ResponseEntity<List<News>> getUserNews(@PathVariable Long id) {
    return new ResponseEntity<>(userService.getUserNews(id), HttpStatus.OK);
  }
}
