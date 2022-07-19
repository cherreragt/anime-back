package com.anime.app.domain;

import com.anime.app.dto.SocialNetworkDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "social_network")
public class SocialNetwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String link;
    private String icon;
    private boolean active;

    public SocialNetwork(SocialNetworkDTO socialNetworkDTO) { BeanUtils.copyProperties(socialNetworkDTO, this); }
}
