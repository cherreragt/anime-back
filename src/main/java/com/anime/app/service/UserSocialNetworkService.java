package com.anime.app.service;

import com.anime.app.domain.UserSocialNetwork;
import com.anime.app.dto.SocialNetworkDTO;
import com.anime.app.dto.UserSocialNetworkDTO;
import com.anime.app.exceptions.BadRequest;
import com.anime.app.exceptions.Conflict;
import com.anime.app.exceptions.NoContent;
import com.anime.app.repository.SocialNetworkRepository;
import com.anime.app.repository.UserSocialNetworkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserSocialNetworkService {
    private final UserSocialNetworkRepository userSocialNetworkRepository;
    private final SocialNetworkRepository socialNetworkRepository;

    public void addSocialNetwork(UserSocialNetworkDTO userSocialNetworkDTO) {
        if (userSocialNetworkDTO == null) {
            throw new BadRequest("DEBES MANDAR UNA RED SOCIAL PARA CREARLA");
        }

        if (userSocialNetworkDTO.getSocialId() == null || userSocialNetworkDTO.getSocialId() == 0) {
            throw new BadRequest("DEBES ESPECIFICAR EL ID DE LA RED SOCIAL");
        }

        if (userSocialNetworkDTO.getLink().isBlank()) {
            throw new BadRequest("DEBES ESPECIFICAR UN LINK");
        }

        var userSocialNetwork = userSocialNetworkRepository.findBySocialId(userSocialNetworkDTO.getSocialId());

        if (userSocialNetwork != null) {
            throw new Conflict("LA RED SOCIAL YA EXISTE");
        }

        var exists = socialNetworkRepository.findById(userSocialNetworkDTO.getSocialId());

        if (exists.isEmpty()) {
            throw new BadRequest("NO SE ENCONTRO NINGUNA RED SOCIAL CON ESE ID");
        }

        if (!exists.get().isActive()) {
            throw new BadRequest("LA RED SOCIAL NO SE ENCUENTRA DISPONIBLE");
        }

        userSocialNetworkRepository.save(new UserSocialNetwork(userSocialNetworkDTO));
    }

    public void updateSocialNetwork(UserSocialNetworkDTO userSocialNetworkDTO) {
        if (userSocialNetworkDTO == null) {
            throw new BadRequest("DEBES MANDAR UNA RED SOCIAL PARA ACTUALIZARLA");
        }

        if (userSocialNetworkDTO.getId() == null || userSocialNetworkDTO.getId() == 0) {
            throw new BadRequest("DEBES ESPECIFICAR EL ID");
        }

        var userSocialNetwork = userSocialNetworkRepository.findById(userSocialNetworkDTO.getId());

        if (userSocialNetwork.isEmpty()) {
            throw new Conflict("NO SE ENCONTRO NINGUNA RED SOCIAL CON ESE ID");
        }

        if (userSocialNetworkDTO.getLink().isBlank()) {
            throw new BadRequest("DEBES ESPECIFICAR UN LINK");
        }

        var exists = socialNetworkRepository.findById(userSocialNetworkDTO.getSocialId());

        if (exists.isEmpty()) {
            throw new BadRequest("NO SE ENCONTRO NINGUNA RED SOCIAL CON ESE ID");
        }

        if (!exists.get().isActive()) {
            throw new BadRequest("LA RED SOCIAL NO SE ENCUENTRA DISPONIBLE");
        }

        userSocialNetworkRepository.save(new UserSocialNetwork(userSocialNetworkDTO));
    }

    public void deleteSocialNetwork(Long id) {
        if (id == null || id == 0) {
            throw new BadRequest("DEBES ESPECIFICAR EL ID");
        }

        var exists = userSocialNetworkRepository.findById(id);

        if (exists.isEmpty()) {
            throw new NoContent("NO SE ENCONTRO NINGUNA RED SOCIAL CON ESE ID");
        }

        userSocialNetworkRepository.deleteById(id);
    }
}
