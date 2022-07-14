package com.anime.app.domain;


import com.anime.app.dto.RoleDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment
  private Long id;

  private String name;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//cascade para que se pueda crear roles dentro del user
  //eager para que cargue todos los items de la relacion d1
  @JoinTable(
          name = "user_has_role",//pivote
          joinColumns = @JoinColumn(name = "role_id"),//id de la tabla actual es decir role = role_id
          inverseJoinColumns = @JoinColumn(name = "user_id")//tabla secundaria
  )
  @JsonIgnore
  private List<User> users;

  public Role(RoleDTO roleDTO) {
    BeanUtils.copyProperties(roleDTO, this);
  }
}
