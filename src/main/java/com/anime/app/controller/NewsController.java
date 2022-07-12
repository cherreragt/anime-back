package com.anime.app.controller;

import com.anime.app.dto.NewsDTO;
import com.anime.app.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/news")
@AllArgsConstructor
public class NewsController {
    public final NewsService newsService;

    @PostMapping(path = "/save")
    public ResponseEntity save(@RequestBody NewsDTO newsDTO) {
        newsService.createNews(newsDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
