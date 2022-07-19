package com.anime.app.repository;

import com.anime.app.domain.SocialNetwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Long> {
    SocialNetwork findByNameAndActive(String name, boolean active);
    List<SocialNetwork> findAllByActive(boolean active);
}
