package com.anime.app.dto;

import com.anime.app.domain.Role;
import com.anime.app.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class RoleDTO {
  public Long Id;
  public String name;
  private List<User> users;

  public RoleDTO(Role role) {
    BeanUtils.copyProperties(role, this);
  }
}
