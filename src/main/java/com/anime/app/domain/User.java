package com.anime.app.domain;

import com.anime.app.dto.RoleDTO;
import com.anime.app.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;
  private String password;
  private String token;
  private String sex;
  private String birthday;
  @Column(name = "about_me")
  private String aboutMe;
  private String avatar;
  private Boolean active;
  private String wallpaper;
  @Column(name = "username")
  private String userName;
  @Column(name = "register_date")
  private String registerDate;


  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
          name = "user_has_role",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private List<Role> roles;

  public User(UserDTO dto) {
    BeanUtils.copyProperties(dto, this);
  }
}
