package com.anime.app.service;

import com.anime.app.domain.News;
import com.anime.app.domain.User;
import com.anime.app.dto.UserDTO;
import com.anime.app.exceptions.BadRequest;
import com.anime.app.exceptions.Conflict;
import com.anime.app.exceptions.NoContent;
import com.anime.app.repository.NewsRepository;
import com.anime.app.repository.RoleRepository;
import com.anime.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

  public final UserRepository userRepository;
  public final RoleRepository roleRepository;

  public final NewsRepository newsRepository;

  public void createUser(UserDTO userDTO) {
    // TODO: falta la integracion al bucket para guardar la img
    var exists = userRepository.findUserByEmail(userDTO.getEmail());

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
    userRepository.save(user);
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
