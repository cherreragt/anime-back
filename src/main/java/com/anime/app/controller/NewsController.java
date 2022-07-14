package com.anime.app.controller;

import com.anime.app.domain.News;
import com.anime.app.dto.NewsDTO;
import com.anime.app.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/news")
@AllArgsConstructor
public class NewsController {
    public final NewsService newsService;

    @PostMapping
    public ResponseEntity<NewsDTO> createNews(@RequestBody NewsDTO newsDTO) {
        newsService.createNews(newsDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<NewsDTO> updateNews(@RequestBody NewsDTO newsDTO) {
        newsService.updateNews(newsDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<NewsDTO> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<News>> loadAllNews() {
        return new ResponseEntity<>(newsService.getAllNews(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Optional<News>> loadNewsById(@PathVariable Long id) {
        return new ResponseEntity<>(newsService.getNewsById(id), HttpStatus.OK);
    }
}
