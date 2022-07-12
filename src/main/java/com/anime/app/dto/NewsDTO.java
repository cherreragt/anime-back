package com.anime.app.dto;

import com.anime.app.domain.News;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class NewsDTO {
    private Long id;
    private Long authorId;
    private String title;
    private String url;
    private String description;
    private String image;
    private Date createdAt;

    public NewsDTO(News news){ BeanUtils.copyProperties(news, this); }
}
