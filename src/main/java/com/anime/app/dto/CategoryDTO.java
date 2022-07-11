package com.anime.app.dto;

import com.anime.app.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private boolean nsfw;

    public CategoryDTO(Category category) { BeanUtils.copyProperties(category, this); }
}
