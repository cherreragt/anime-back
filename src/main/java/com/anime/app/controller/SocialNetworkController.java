package com.anime.app.controller;

import com.anime.app.domain.SocialNetwork;
import com.anime.app.dto.SocialNetworkDTO;
import com.anime.app.service.SocialNetworkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/social-network")
@AllArgsConstructor
public class SocialNetworkController {
    private final SocialNetworkService socialNetworkService;

    @PostMapping
    public ResponseEntity<SocialNetworkDTO> createNews(@RequestBody SocialNetworkDTO socialNetworkDTO) {
        socialNetworkService.addSocialNetwork(socialNetworkDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<SocialNetworkDTO> updateNews(@RequestBody SocialNetworkDTO socialNetworkDTO) {
        socialNetworkService.updateSocialNetwork(socialNetworkDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<SocialNetworkDTO> deleteNews(@PathVariable Long id) {
        socialNetworkService.deleteSocialNetwork(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<SocialNetwork>> loadAllActiveSocialNetworks() {
        return new ResponseEntity<>(socialNetworkService.getActiveSocialNetworks(), HttpStatus.OK);
    }
}
