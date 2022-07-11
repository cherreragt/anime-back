package com.anime.app.domain;

import com.anime.app.dto.CategoryDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean nsfw;

    public Category(CategoryDTO dto)  { BeanUtils.copyProperties(dto, this); }
}
