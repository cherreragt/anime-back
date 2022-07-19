package com.anime.app.repository;

import com.anime.app.domain.News;
import com.anime.app.dto.NewsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByAuthorId(Long id);
}
