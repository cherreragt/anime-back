package com.anime.app.repository;

import com.anime.app.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  //@Query(nativeQuery = true, value = "SELECT * FROM roles WHERE name = \"ROLE_USER\"")
  Role findRoleByName(String name);
}
