package com.example.partijlidservice.service;

import com.example.partijlidservice.dto.PartijlidResponse;
import com.example.partijlidservice.model.Partijlid;
import com.example.partijlidservice.repository.PartijlidRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PartijlidService {
    private final PartijlidRepository partijlidRepository;
    private final WebClient webClient;

    public List<PartijlidResponse> getAllPartijleden() {
        List<Partijlid> partijleden = partijlidRepository.findAll();

        return partijleden.stream().map(lid -> new PartijlidResponse(lid.getNaam(), lid.getTelefoonnummer())
        ).collect(Collectors.toList());
    }

}
