package com.anime.app.service;

import com.anime.app.domain.Category;
import com.anime.app.dto.CategoryDTO;
import com.anime.app.exceptions.Conflict;
import com.anime.app.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {
    public final CategoryRepository categoryRepository;

    public void createCategory(CategoryDTO categoryDTO) {
        var exists = categoryRepository.findCategoryByName(categoryDTO.getName());

        if (exists != null) {
            throw new Conflict("LA CATEGORIA YA EXISTE");
        }

        var category = new Category(categoryDTO);

        categoryRepository.save(category);
    }
}
