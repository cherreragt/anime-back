package com.anime.app.repository;

import com.anime.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findUserByEmail(String email);
  User findUserByUserName(String userName);
}
