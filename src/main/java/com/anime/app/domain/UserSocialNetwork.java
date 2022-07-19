package com.anime.app.domain;

import com.anime.app.dto.UserSocialNetworkDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "user_social_network")
public class UserSocialNetwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "social_id")
    private Long socialId;

    private String link;

    private boolean visible;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    private User user;

    public UserSocialNetwork(UserSocialNetworkDTO userSocialNetworkDTO) {
        BeanUtils.copyProperties(userSocialNetworkDTO, this);
    }
}
