package com.example.ministerservice.service;

import com.example.ministerservice.dto.MinisterResponse;
import com.example.ministerservice.dto.MinisterRequest;
import com.example.ministerservice.model.Minister;
import com.example.ministerservice.repository.MinisterRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MinisterService {

    private final MinisterRepository ministerRepository;

    @PostConstruct
    public void loadData() {
        if (ministerRepository.count() <= 0) {
            Minister minister1 = Minister.builder()
                    .naam("Premier")
                    .partijlidNaam("Alexander De Croo")
                    .regeringNaam("Federaal")
                    .build();

            Minister minister2 = Minister.builder()
                    .naam("Minister van Defensie")
                    .partijlidNaam("Ludivine Dedonder")
                    .regeringNaam("Federaal")
                    .build();

            Minister minister3 = Minister.builder()
                    .naam("Minister van Buitenlandse zaken")
                    .partijlidNaam("Bernard Quintin")
                    .regeringNaam("Federaal")
                    .build();

            Minister minister4 = Minister.builder()
                    .naam("Minister van Justitie")
                    .partijlidNaam("Paul Van Tigchelt")
                    .regeringNaam("Federaal")
                    .build();

            Minister minister5 = Minister.builder()
                    .naam("Minister van Onderwijs")
                    .partijlidNaam("Zuhal Demir")
                    .regeringNaam("Vlaams")
                    .build();

            Minister minister6 = Minister.builder()
                    .naam("Vlaams minister-president")
                    .partijlidNaam("Matthias Diependaele")
                    .regeringNaam("Vlaams")
                    .build();

            ministerRepository.save(minister1);
            ministerRepository.save(minister2);
            ministerRepository.save(minister3);
            ministerRepository.save(minister4);
            ministerRepository.save(minister5);
            ministerRepository.save(minister6);
        }
    }

    public List<MinisterResponse> getAllMinisters() {
        List<Minister> ministers = ministerRepository.findAll();
        return ministers.stream()
                .map(this::mapToMinisterResponse)
                .toList();
    }
    public void createMinister(MinisterRequest ministerRequest) {
        Minister minister = Minister.builder()
                .naam(ministerRequest.getNaam())
                .partijlidNaam(ministerRequest.getPartijlidNaam())
                .regeringNaam(ministerRequest.getRegeringNaam())
                .build();

        ministerRepository.save(minister);
    }

    public MinisterResponse getMinisterById(long id) {
        Optional<Minister> minister = ministerRepository.findById(id);
        if (minister.isPresent()) {
            return mapToMinisterResponse(minister.get());
        } else {
            throw new IllegalArgumentException("Minister with ID " + id + " not found.");
        }
    }

    public void editMinister(long id, MinisterRequest editMinisterRequest) {
        Minister existingMinister = ministerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Minister not found with id: " + id));

        if (editMinisterRequest.getNaam() != null) {
            existingMinister.setNaam(editMinisterRequest.getNaam());
        }
        if (editMinisterRequest.getPartijlidNaam() != null) {
            existingMinister.setPartijlidNaam(editMinisterRequest.getPartijlidNaam());
        }
        if (editMinisterRequest.getRegeringNaam() != null) {
            existingMinister.setRegeringNaam(editMinisterRequest.getRegeringNaam());
        }

        ministerRepository.save(existingMinister);
    }

    public void deleteMinister(long id) {
        if (!ministerRepository.existsById(id)) {
            throw new IllegalArgumentException("Minister with ID " + id + " not found.");
        }
        ministerRepository.deleteById(id);
    }

    private MinisterResponse mapToMinisterResponse(Minister minister) {
        return MinisterResponse.builder()
                .id(minister.getId())
                .naam(minister.getNaam())
                .partijlidNaam(minister.getPartijlidNaam())
                .regeringNaam(minister.getRegeringNaam())
                .build();
    }
}
