package com.anime.app.domain;

import com.anime.app.dto.NewsDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_id")
    private Long authorId;

    private String title;
    private String url;
    private String description;
    private String image;

    @Column(name = "created_at")
    private Date createdAt;

    public News(NewsDTO dto){ BeanUtils.copyProperties(dto, this); }
}
