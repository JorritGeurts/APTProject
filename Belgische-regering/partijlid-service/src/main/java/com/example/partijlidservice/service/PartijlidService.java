package com.example.partijlidservice.service;

import com.example.partijlidservice.dto.PartijlidRequest;
import com.example.partijlidservice.dto.PartijlidResponse;
import com.example.partijlidservice.dto.PartijResponse;
import com.example.partijlidservice.dto.RegeringResponse;
import com.example.partijlidservice.model.Partijlid;
import com.example.partijlidservice.repository.PartijlidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PartijlidService {

    private final PartijlidRepository partijlidRepository;
    private final WebClient webClient;

    @Value("${regeringservice.baseurl}")
    private String regeringServiceBaseUrl;

    @Value("${partijservice.baseurl}")
    private String partijServiceBaseUrl;

    public List<PartijlidResponse> getAllPartijleden() {
        List<Partijlid> partijliden = partijlidRepository.findAll();
        return partijliden.stream()
                .map(this::mapToPartijlidResponse)
                .toList();
    }

    public void createPartijlid(PartijlidRequest partijlidRequest) {
        // Fetch partij and regering details using names instead of IDs
        //PartijResponse partijResponse = getPartijByName(partijlidRequest.getPartijNaam());
        //RegeringResponse regeringResponse = getRegeringByName(partijlidRequest.getRegeringNaam());

        Partijlid partijlid = Partijlid.builder()
                .naam(partijlidRequest.getNaam())
                .email(partijlidRequest.getEmail())
                .partijNaam(partijlidRequest.getPartijNaam()) // Extract name from PartijResponse
                .regeringNaam(partijlidRequest.getRegeringNaam()) // Extract name from RegeringResponse
                .build();

        partijlidRepository.save(partijlid);
    }

    public PartijlidResponse getPartijlidByNaam(String naam) {
        Optional<Partijlid> partijlid = partijlidRepository.findByNaam(naam);
        return partijlid.map(this::mapToPartijlidResponse)
                .orElseThrow(() -> new IllegalArgumentException("Partijlid with naam " + naam + " not found."));
    }

    public PartijlidResponse getPartijlidById(long id) {
        Optional<Partijlid> partijlid = partijlidRepository.findById(id);
        return partijlid.map(this::mapToPartijlidResponse)
                .orElseThrow(() -> new IllegalArgumentException("Partijlid with ID " + id + " not found."));
    }

    public void editPartijlid(long id, PartijlidRequest editPartijlidRequest) {
        Partijlid existingPartijlid = partijlidRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Partijlid not found with id: " + id));

        if (editPartijlidRequest.getNaam() != null) {
            existingPartijlid.setNaam(editPartijlidRequest.getNaam());
        }
        if (editPartijlidRequest.getEmail() != null) {
            existingPartijlid.setEmail(editPartijlidRequest.getEmail());
        }
        if (editPartijlidRequest.getPartijNaam() != null) {
            PartijResponse partijResponse = getPartijByName(editPartijlidRequest.getPartijNaam());
            existingPartijlid.setPartijNaam(partijResponse.getNaam());
        }
        if (editPartijlidRequest.getRegeringNaam() != null) {
            RegeringResponse regeringResponse = getRegeringByName(editPartijlidRequest.getRegeringNaam());
            existingPartijlid.setRegeringNaam(regeringResponse.getNaam());
        }

        partijlidRepository.save(existingPartijlid);
    }

    public void deletePartijlid(long id) {
        if (!partijlidRepository.existsById(id)) {
            throw new IllegalArgumentException("Partijlid with ID " + id + " not found.");
        }
        partijlidRepository.deleteById(id);
    }

    // Fetch Partij details by name
    private PartijResponse getPartijByName(String partijNaam) {
        return webClient.get()
                .uri(partijServiceBaseUrl + "/api/partij/naam/{naam}", partijNaam)
                .retrieve()
                .bodyToMono(PartijResponse.class)
                .block();
    }

    // Fetch Regering details by name
    private RegeringResponse getRegeringByName(String regeringNaam) {
        return webClient.get()
                .uri(regeringServiceBaseUrl + "/api/regering/naam/{naam}", regeringNaam)
                .retrieve()
                .bodyToMono(RegeringResponse.class)
                .block();
    }

    private PartijlidResponse mapToPartijlidResponse(Partijlid partijlid) {
        return PartijlidResponse.builder()
                .id(partijlid.getId())
                .naam(partijlid.getNaam())
                .email(partijlid.getEmail())
                .partijNaam(partijlid.getPartijNaam())
                .regeringNaam(partijlid.getRegeringNaam())
                .build();
    }
}
