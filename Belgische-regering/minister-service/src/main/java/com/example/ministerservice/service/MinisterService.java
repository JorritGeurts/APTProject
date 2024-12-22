package com.example.ministerservice.service;

import com.example.ministerservice.dto.MinisterRequest;
import com.example.ministerservice.dto.MinisterResponse;
import com.example.ministerservice.dto.PartijlidResponse;
import com.example.ministerservice.dto.RegeringResponse;
import com.example.ministerservice.model.Minister;
import com.example.ministerservice.repository.MinisterRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MinisterService {

    private final MinisterRepository ministerRepository;
    private final WebClient webClient;

    @Value("${regeringservice.baseurl}")
    private String regeringServiceBaseUrl;

    @Value("${partijlidservice.baseurl}")
    private String partijlidServiceBaseUrl;

    @PostConstruct
    public void loadData() {
        if (ministerRepository.count() <= 0) {
            Minister minister1 = Minister.builder()
                    .naam("Premier")
                    .partijlidNaam("Alexander De Croo")
                    .regeringNaam("Federaal")
                    .build();

            ministerRepository.save(minister1);
        }
    }

    public List<MinisterResponse> getAllMinisters() {
        List<Minister> ministers = ministerRepository.findAll();
        return ministers.stream()
                .map(this::mapToMinisterResponse)
                .toList();
    }

    public MinisterResponse getMinisterByNaam(String naam) {
        Optional<Minister> minister = ministerRepository.findByNaam(naam);
        return minister.map(this::mapToMinisterResponse)
                .orElseThrow(() -> new IllegalArgumentException("Minister with naam " + naam + " not found."));
    }

    /**
     * Create a new minister by fetching details from PartijlidService and RegeringService.
     */
    public void createMinister(MinisterRequest ministerRequest) {
        // Fetch partijlid and regering details using names
        PartijlidResponse partijlidResponse = getPartijlidByName(ministerRequest.getPartijlidNaam());
        RegeringResponse regeringResponse = getRegeringByName(ministerRequest.getRegeringNaam());

        Minister minister = Minister.builder()
                .naam(ministerRequest.getNaam())
                .partijlidNaam(partijlidResponse.getNaam()) // Extract name from PartijlidResponse
                .regeringNaam(regeringResponse.getNaam()) // Extract name from RegeringResponse
                .build();

        ministerRepository.save(minister);
    }

    /**
     * Get a minister by their ID.
     */
    public MinisterResponse getMinisterById(long id) {
        Optional<Minister> minister = ministerRepository.findById(id);
        return minister.map(this::mapToMinisterResponse)
                .orElseThrow(() -> new IllegalArgumentException("Minister with ID " + id + " not found."));
    }

    /**
     * Edit an existing minister with updated details, fetching new details from PartijlidService and RegeringService if required.
     */
    public void editMinister(long id, MinisterRequest editMinisterRequest) {
        Minister existingMinister = ministerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Minister not found with id: " + id));

        // Update name if needed
        if (editMinisterRequest.getNaam() != null) {
            existingMinister.setNaam(editMinisterRequest.getNaam());
        }

        // Update PartijlidNaam if it has changed
        if (editMinisterRequest.getPartijlidNaam() != null &&
                !editMinisterRequest.getPartijlidNaam().equals(existingMinister.getPartijlidNaam())) {
            PartijlidResponse partijlidResponse = getPartijlidByName(editMinisterRequest.getPartijlidNaam());
            existingMinister.setPartijlidNaam(partijlidResponse.getNaam());
        }

        // Update RegeringNaam if it has changed
        if (editMinisterRequest.getRegeringNaam() != null &&
                !editMinisterRequest.getRegeringNaam().equals(existingMinister.getRegeringNaam())) {
            RegeringResponse regeringResponse = getRegeringByName(editMinisterRequest.getRegeringNaam());
            existingMinister.setRegeringNaam(regeringResponse.getNaam());
        }

        // Save the updated minister details
        ministerRepository.save(existingMinister);
    }


    /**
     * Delete a minister by their ID.
     */
    public void deleteMinister(long id) {
        if (!ministerRepository.existsById(id)) {
            throw new IllegalArgumentException("Minister with ID " + id + " not found.");
        }
        ministerRepository.deleteById(id);
    }

    /**
     * Call the PartijlidService to retrieve a Partijlid's details by name.
     */
    private PartijlidResponse getPartijlidByName(String partijlidNaam) {
        return webClient.get()
                .uri(partijlidServiceBaseUrl + "/api/partijlid/naam/{naam}", partijlidNaam)
                .retrieve()
                .bodyToMono(PartijlidResponse.class)
                .block();
    }

    /**
     * Call the RegeringService to retrieve a Regering's details by name.
     */
    private RegeringResponse getRegeringByName(String regeringNaam) {
        return webClient.get()
                .uri(regeringServiceBaseUrl + "/api/regering/naam/{naam}", regeringNaam)
                .retrieve()
                .bodyToMono(RegeringResponse.class)
                .block();
    }

    /**
     * Map a Minister entity to a MinisterResponse DTO.
     */
    private MinisterResponse mapToMinisterResponse(Minister minister) {
        return MinisterResponse.builder()
                .id(minister.getId())
                .naam(minister.getNaam())
                .partijlidNaam(minister.getPartijlidNaam())
                .regeringNaam(minister.getRegeringNaam())
                .build();
    }
}
