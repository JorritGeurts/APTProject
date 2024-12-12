package com.example.partijservice.service;

import com.example.partijservice.dto.PartijResponse;
import com.example.partijservice.model.Partij;
import com.example.partijservice.repository.PartijRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartijService {
    private final PartijRepository partijRepository;

    @PostConstruct
    public void loadData() {
        if (partijRepository.count() <= 0) {
            Partij partij1 = Partij.builder()
                    .naam("Vlaams Belang")
                    .adres("Madouplein 8")
                    .postcode("1210")
                    .stad("Brussel")
                    .coalitieVlaams(false)
                    .coalitieFederaal(false)
                    .build();

            Partij partij2 = Partij.builder()
                    .naam("NVA")
                    .adres("Koningsstraat 47")
                    .postcode("1000")
                    .stad("Brussel")
                    .coalitieVlaams(true)
                    .coalitieFederaal(false)
                    .build();

            Partij partij3 = Partij.builder()
                    .naam("Open VLD")
                    .adres("Melsensstraat 34")
                    .postcode("1000")
                    .stad("Brussel")
                    .coalitieVlaams(true)
                    .coalitieFederaal(true)
                    .build();

            Partij partij4 = Partij.builder()
                    .naam("CD&V")
                    .adres("Gare Maritime")
                    .postcode("1000")
                    .stad("Brussel")
                    .coalitieVlaams(true)
                    .coalitieFederaal(true)
                    .build();

            Partij partij5 = Partij.builder()
                    .naam("Vooruit")
                    .adres("Keizerslaan 13")
                    .postcode("1000")
                    .stad("Brussel")
                    .coalitieVlaams(false)
                    .coalitieFederaal(true)
                    .build();

            Partij partij6 = Partij.builder()
                    .naam("Groen")
                    .adres("Van Orleystraat 5-11")
                    .postcode("1000")
                    .stad("Brussel")
                    .coalitieVlaams(false)
                    .coalitieFederaal(true)
                    .build();

            Partij partij7 = Partij.builder()
                    .naam("PVDA")
                    .adres("Maurice Lemonnierlaan 171")
                    .postcode("1000")
                    .stad("Brussel")
                    .coalitieVlaams(false)
                    .coalitieFederaal(false)
                    .build();

            partijRepository.save(partij1);
            partijRepository.save(partij2);
            partijRepository.save(partij3);
            partijRepository.save(partij4);
            partijRepository.save(partij5);
            partijRepository.save(partij6);
            partijRepository.save(partij7);
        }
    }

    public List<PartijResponse> getAllPartijen() {
        List<Partij> partijen = partijRepository.findAll();

        return partijen.stream().map(this::mapToPartijResponse).toList();
    }

    public PartijResponse getPartijByNaam(String naam) {
        Partij partij = partijRepository.findByNaam(naam)
                .orElseThrow(() -> new IllegalArgumentException("Partij not found with name: " + naam));

        return mapToPartijResponse(partij);
    }

    private PartijResponse mapToPartijResponse(Partij partij) {
        return PartijResponse.builder()
                .id(partij.getId())
                .naam(partij.getNaam())
                .adres(partij.getAdres())
                .postcode(partij.getPostcode())
                .stad(partij.getStad())
                .coalitieVlaams(partij.isCoalitieVlaams())
                .coalitieFederaal(partij.isCoalitieFederaal())
                .build();
    }
}
