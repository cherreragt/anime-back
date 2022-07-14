package com.anime.app.repository;

import com.anime.app.domain.News;
import com.anime.app.dto.NewsDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByAuthorId(Long id);
}
