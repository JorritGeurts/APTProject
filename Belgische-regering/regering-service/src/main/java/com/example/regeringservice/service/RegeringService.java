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

    private RegeringResponse mapToProductResponse(Regering regering) {
        return RegeringResponse.builder()
                .id(regering.getId())
                .naam(regering.getNaam())
                .build();
    }

    public void editRegering(String id, RegeringRequest request) {
        // Find the existing Regering by ID
        Regering existingRegering = regeringRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Regering not found with id: " + id));

        // Check and update the fields if they are not null
        if (request.getNaam() != null) {
            existingRegering.setNaam(request.getNaam());
        }
        // Add more fields if necessary, e.g., if there are other fields in the RegeringRequest

        // Save the updated entity
        regeringRepository.save(existingRegering);
    }
}
