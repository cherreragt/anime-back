package com.anime.app.service;

import com.anime.app.domain.SocialNetwork;
import com.anime.app.dto.SocialNetworkDTO;
import com.anime.app.exceptions.BadRequest;
import com.anime.app.exceptions.Conflict;
import com.anime.app.exceptions.NoContent;
import com.anime.app.repository.SocialNetworkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SocialNetworkService {
    private final SocialNetworkRepository socialNetworkRepository;

    public void addSocialNetwork(SocialNetworkDTO socialNetworkDTO) {
        if (socialNetworkDTO == null) {
            throw new BadRequest("DEBES MANDAR UNA RED SOCIAL PARA CREARLA");
        }

        if (socialNetworkDTO.getLink().isBlank()) {
            throw new BadRequest("DEBES ESPECIFICAR UN LINK");
        }

        // TODO FALTA INTEGRACION BUCKET Y VERIFICACION QUE SE PASE UN ICON

        socialNetworkRepository.save(new SocialNetwork(socialNetworkDTO));
    }

    public void updateSocialNetwork(SocialNetworkDTO socialNetworkDTO) {
        if (socialNetworkDTO == null) {
            throw new BadRequest("DEBES MANDAR UNA RED SOCIAL PARA ACTUALIZARLA");
        }

        if (socialNetworkDTO.getId() == null || socialNetworkDTO.getId() == 0) {
            throw new BadRequest("DEBES ESPECIFICAR EL ID");
        }

        var exists = socialNetworkRepository.findById(socialNetworkDTO.getId());

        if (exists.isEmpty()) {
            throw new Conflict("NO SE ENCONTRO NINGUNA RED SOCIAL CON ESE ID");
        }

        if (exists.get().getLink().isBlank()) {
            throw new BadRequest("DEBES ESPECIFICAR UN LINK");
        }

        // TODO FALTA INTEGRACION BUCKET Y VERIFICACION QUE SE PASE UN ICON

        socialNetworkRepository.save(new SocialNetwork(socialNetworkDTO));
    }

    public void deleteSocialNetwork(Long id) {
        if (id == null || id == 0) {
            throw new BadRequest("DEBES ESPECIFICAR EL ID");
        }

        var exists = socialNetworkRepository.findById(id);

        if (exists.isEmpty()) {
            throw new NoContent("NO SE ENCONTRO NINGUNA RED SOCIAL CON ESE ID");
        }

        socialNetworkRepository.deleteById(id);
    }

    public List<SocialNetwork> getActiveSocialNetworks() {
        List<SocialNetwork> socialNetworks = socialNetworkRepository.findAllByActive(true);

        if (socialNetworks.isEmpty()) {
            throw new NoContent("SIN CONTENIDO");
        }

        return socialNetworks;
    }
}
