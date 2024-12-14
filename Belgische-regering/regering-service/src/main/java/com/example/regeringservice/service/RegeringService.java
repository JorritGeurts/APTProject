package com.example.regeringservice.service;

import com.example.regeringservice.dto.RegeringRequest;
import com.example.regeringservice.dto.RegeringResponse;
import com.example.regeringservice.model.Regering;
import com.example.regeringservice.repository.RegeringRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegeringService {
    private final RegeringRepository regeringRepository;

    @PostConstruct
    public void loadData() {
        if (regeringRepository.count() <= 0) {
            Regering regering1 = Regering.builder()
                    .naam("Federaal")
                    .build();

            Regering regering2 = Regering.builder()
                    .naam("Vlaams")
                    .build();

            regeringRepository.save(regering1);
            regeringRepository.save(regering2);
        }
    }

    public List<RegeringResponse> getAllRegeringen() {
        List<Regering> regeringen = regeringRepository.findAll();

        return regeringen.stream().map(this::mapToProductResponse).toList();
    }

    public RegeringResponse getRegeringByNaam(String naam) {
        Regering regering = regeringRepository.findByNaam(naam)
                .orElseThrow(() -> new IllegalArgumentException("Partij not found with name: " + naam));

        return mapToProductResponse(regering);
    }

    private RegeringResponse mapToProductResponse(Regering regering) {
        return RegeringResponse.builder()
                .id(regering.getId())
                .naam(regering.getNaam())
                .build();
    }
}
