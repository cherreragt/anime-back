package com.anime.app.repository;

import com.anime.app.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
    News findByAuthorId(Long id);
}
