package com.anime.app.controller;

import com.anime.app.dto.UserSocialNetworkDTO;
import com.anime.app.service.UserSocialNetworkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user-social-network")
@AllArgsConstructor
public class UserSocialNetworkController {
    private final UserSocialNetworkService userSocialNetworkService;

    @PostMapping
    public ResponseEntity<UserSocialNetworkDTO> createNews(@RequestBody UserSocialNetworkDTO userSocialNetworkDTO) {
        userSocialNetworkService.addSocialNetwork(userSocialNetworkDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<UserSocialNetworkDTO> updateNews(@RequestBody UserSocialNetworkDTO userSocialNetworkDTO) {
        userSocialNetworkService.updateSocialNetwork(userSocialNetworkDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserSocialNetworkDTO> deleteNews(@PathVariable Long id) {
        userSocialNetworkService.deleteSocialNetwork(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
