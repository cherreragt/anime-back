package com.anime.app.dto;

import com.anime.app.domain.UserSocialNetwork;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Setter
@Getter
@NoArgsConstructor
public class UserSocialNetworkDTO {
    private Long id;
    private Long userId;
    private Long socialId;
    private String link;
    private boolean visible;

    public UserSocialNetworkDTO(UserSocialNetwork userSocialNetwork) {
        BeanUtils.copyProperties(userSocialNetwork, this);
    }
}
