package com.example.partijlidservice.service;

import com.example.partijlidservice.dto.PartijlidRequest;
import com.example.partijlidservice.dto.PartijlidResponse;
import com.example.partijlidservice.model.Partijlid;
import com.example.partijlidservice.repository.PartijlidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartijlidService {

    private final PartijlidRepository partijlidRepository;
    private final PartijRepository partijRepository; // Assuming you have this repository
    private final RegeringRepository regeringRepository; // Assuming you have this repository

    public boolean addPartijlid(PartijlidRequest requestDTO) {
        Partijlid partijlid = new Partijlid();
        partijlid.setNaam(requestDTO.getNaam());
        partijlid.setTelefoonnummer(requestDTO.getTelefoonnummer());
        partijlid.setPartij(partijRepository.findById(requestDTO.getPartijId()).orElse(null));
        partijlid.setRegering(regeringRepository.findById(requestDTO.getRegeringId()).orElse(null));
        partijlidRepository.save(partijlid);
        return true;
    }

    public PartijlidResponse getPartijlidById(Long id) {
        Partijlid partijlid = partijlidRepository.findById(id).orElse(null);
        if (partijlid == null) return null;

        return new PartijlidResponse(
                partijlid.getId(),
                partijlid.getNaam(),
                partijlid.getTelefoonnummer(),
                partijlid.getPartij() != null ? partijlid.getPartij().getNaam() : null,
                partijlid.getRegering() != null ? partijlid.getRegering().getNaam() : null

        );
    }
}
