package com.anime.app.service;

import com.anime.app.domain.News;
import com.anime.app.dto.NewsDTO;
import com.anime.app.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NewsService {
    public final NewsRepository newsRepository;

    public void createNews(NewsDTO newsDTO) {
        newsRepository.save(new News(newsDTO));
    }
}
