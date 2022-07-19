package com.anime.app.dto;

import com.anime.app.domain.SocialNetwork;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Setter
@Getter
@NoArgsConstructor
public class SocialNetworkDTO {
    private Long id;
    private String name;
    private String link;
    private String icon;
    private boolean active;

    public SocialNetworkDTO(SocialNetwork socialNetwork) { BeanUtils.copyProperties(socialNetwork, this); }
}
