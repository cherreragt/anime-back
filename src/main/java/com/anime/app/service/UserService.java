package com.anime.app.service;

import com.anime.app.domain.News;
import com.anime.app.dto.B2FileResponse;
import com.anime.app.dto.UserDTO;
import com.anime.app.exceptions.BadRequest;
import com.anime.app.exceptions.NoContent;
import com.anime.app.repository.NewsRepository;
import com.anime.app.repository.RoleRepository;
import com.anime.app.repository.UserRepository;
import com.anime.app.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  private final NewsRepository newsRepository;

  private final Utils files;

  public InputStream b2FileResponse(String file) {
    if (file.isEmpty()) {
      throw new BadRequest("file es necesario");
    }
    var response = files.preSignFile(file);
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(HttpHeaders.AUTHORIZATION, response.getAuthorization());
    HttpEntity httpEntity = new HttpEntity<>("", httpHeaders);
    byte[] byteContent = restTemplate.exchange(response.getResource(), HttpMethod.GET, httpEntity, String.class).getBody().getBytes(StandardCharsets.ISO_8859_1);
    return new ByteArrayInputStream(byteContent);
  }

  public void createUser(UserDTO userDTO, MultipartFile []multipartFiles) {
    // TODO: falta la integracion al bucket para guardar la img
    /*var exists = userRepository.findUserByEmail(userDTO.getEmail());

    if (exists != null) {
      throw new Conflict("EL E-MAIL YA EXISTE");
    }

    exists = userRepository.findUserByUserName(userDTO.getUserName());

    if (exists != null) {
      throw new Conflict("EL NOMBRE DE USUARIO YA EXISTE");
    }

    var role = roleRepository.findRoleByName("ROLE_USER");

    if (role == null) {
      throw new BadRequest("NO HAY ROLES REGISTRADOS EN LA DB");
    }

    userDTO.setRegisterDate(new Date());

    var user = new User(userDTO);

    user.setRoles(List.of(role));
    userRepository.save(user);*/

    Arrays.stream(multipartFiles).forEach(multipartFile -> {
      // check que solo venga 1 video y X extension
      var nameFile = files.uploadFile(multipartFile);

      if (!nameFile.isEmpty()) {
        // TODO: Insert algo
        System.out.println(nameFile);
      }
    });

  }

  public List<News> getUserNews(Long userId) {
    if (userId == null || userId == 0) {
      throw new BadRequest("DEBES ESPECIFICAR EL ID");
    }

    var exists = userRepository.findById(userId);

    if (exists.isEmpty()) {
      throw new NoContent("NO EXISTE NINGUN USUARIO CON ESE ID");
    }

    List<News> news = exists.get().getNews();

    if (news.isEmpty()) {
      throw new NoContent("SIN CONTENIDO");
    }

    return news;
  }
}
