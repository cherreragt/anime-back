package com.anime.app.domain;

import com.anime.app.dto.NewsDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String url;
    private String description;
    private String image;

    @Column(name = "created_at")
    private Date createdAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

    public News(NewsDTO dto){ BeanUtils.copyProperties(dto, this); }
}
