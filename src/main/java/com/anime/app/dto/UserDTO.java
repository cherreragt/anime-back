package com.anime.app.dto;

import com.anime.app.domain.Role;
import com.anime.app.domain.User;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
  private Long id;
  private String email;
  private String password;
  private String token;
  private String sex;
  private Date birthday;
  private String aboutMe;
  private String avatar;
  private Boolean active;
  private String wallpaper;
  private String userName;
  private Date registerDate;
  // private List<Role> roles;

  public UserDTO (User user) {
    BeanUtils.copyProperties(user, this);
  }
}
