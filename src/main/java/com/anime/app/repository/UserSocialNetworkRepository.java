package com.anime.app.repository;

import com.anime.app.domain.UserSocialNetwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSocialNetworkRepository extends JpaRepository<UserSocialNetwork, Long> {
    UserSocialNetwork findBySocialId(Long id);
    List<UserSocialNetwork> findAllByUserId(Long id);
}
