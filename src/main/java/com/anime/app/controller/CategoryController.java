package com.anime.app.controller;

import com.anime.app.dto.CategoryDTO;
import com.anime.app.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/category")
@AllArgsConstructor
public class CategoryController {
    public final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO categoryDTO)
    {
        categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
