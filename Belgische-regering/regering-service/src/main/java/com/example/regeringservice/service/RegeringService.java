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
                    .beschrijving("De Belgische federale regering is de uitvoerende macht van België. Ze voert niet alleen de Belgische wetten uit, maar speelt ook een rol bij de wetgevende macht via wetsontwerpen en amendementen. De federale regering bestaat uit ten hoogste 15 ministers. Hiernaast kunnen er ook nog staatssecretarissen worden toegevoegd aan de regering. Deze regering is samengesteld uit evenveel Nederlandstaligen als Franstaligen, eventueel de eerste minister uitgezonderd.")
                    .website("https://www.belgium.be")
                    .build();

            Regering regering2 = Regering.builder()
                    .naam("Vlaams")
                    .beschrijving("De Vlaamse Regering is de uitvoerende macht van Vlaanderen. De Vlaamse Regering beheert de bevoegdheden van zowel het Vlaams Gewest als de Vlaamse Gemeenschap. Tot voor de Staatshervorming van 1993 heette de regering de Vlaamse Executieve. Het voorbereiden, formuleren en uitvoeren van beleid gebeurt door een reeks ministeries en agentschappen, ingedeeld volgens beleidsdomein. De Vlaamse Regering zetelt aan het Martelaarsplein (Place des Martyrs) te Brussel.")
                    .website("https://www.vlaamsparlement.be")
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
                .beschrijving(regering.getBeschrijving())
                .website(regering.getWebsite())
                .build();
    }
}
