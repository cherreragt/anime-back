package com.anime.app.service;

import com.anime.app.domain.News;
import com.anime.app.dto.NewsDTO;
import com.anime.app.exceptions.BadRequest;
import com.anime.app.exceptions.Conflict;
import com.anime.app.exceptions.NoContent;
import com.anime.app.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NewsService {
    public final NewsRepository newsRepository;

    public void createNews(NewsDTO newsDTO) {
        // TODO falta integracion al bucket
        newsDTO.setCreatedAt(new Date());
        newsRepository.save(new News(newsDTO));
    }

    public void updateNews(NewsDTO newsDTO) {
        // TODO falta integracion al bucket
        newsRepository.save(new News(newsDTO));
    }

    public void deleteNews(Long newsId) {
        if (newsId == null || newsId == 0) {
            throw new BadRequest("DEBES ESPECIFICAR EL ID");
        }

        newsRepository.deleteById(newsId);
    }

    public List<News> getAllNews() {
        List<News> news = newsRepository.findAll();

        if (news.isEmpty()) {
            throw new NoContent("SIN CONTENIDO");
        }

        return news;
    }

    public Optional<News> getNewsById(Long newsId) {
        if (newsId == null || newsId == 0) {
            throw new BadRequest("DEBES ESPECIFICAR EL ID");
        }

        Optional<News> news = newsRepository.findById(newsId);

        if (news.isEmpty()) {
            throw new NoContent("SIN CONTENIDO");
        }

        return news;
    }

    public List<News> getUserNews(Long userId) {
        if (userId == null || userId == 0) {
            throw new BadRequest("DEBES ESPECIFICAR EL ID");
        }

        List<News> news = newsRepository.findByAuthorId(userId);

        if (news.isEmpty()) {
            throw new NoContent("SIN CONTENIDO");
        }

        return news;
    }
}
