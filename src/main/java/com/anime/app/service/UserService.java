package com.anime.app.service;

import com.anime.app.domain.User;
import com.anime.app.dto.UserDTO;
import com.anime.app.exceptions.BadRequest;
import com.anime.app.exceptions.Conflict;
import com.anime.app.repository.RoleRepository;
import com.anime.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

  public final UserRepository userRepository;
  public final RoleRepository roleRepository;

  public void createUser(UserDTO userDTO) {
    // TODO: lanzar una busqueda por email para lanzar una excepcion de que ya existe
    var exists = userRepository.findUserByEmail(userDTO.getEmail());

    if (exists != null) {
      throw new Conflict("EL USUARIO YA EXISTE");
    }

    var user = new User(userDTO);
    var role = roleRepository.findRoleByName("ROLE_USER");

    if (role == null) {
      throw new BadRequest("NO HAY ROLES REGISTRADOS EN LA DB");
    }

    user.setRoles(List.of(role));
    userRepository.save(user);
  }
}
