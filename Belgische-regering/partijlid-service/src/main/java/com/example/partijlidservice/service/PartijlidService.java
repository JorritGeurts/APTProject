package com.example.partijlidservice.service;

import com.example.partijlidservice.dto.PartijlidResponse;
import com.example.partijlidservice.dto.PartijlidRequest;
import com.example.partijlidservice.model.Partijlid;
import com.example.partijlidservice.repository.PartijlidRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PartijlidService {

    private final PartijlidRepository partijlidRepository;

    @PostConstruct
    public void loadData() {
        if (partijlidRepository.count() <= 0) {
            Partijlid partijlid1 = Partijlid.builder()
                    .naam("Bart De Wever")
                    .email("bart.dewever@n-va.be")
                    .partijNaam("NVA")
                    .regeringNaam("Federaal")
                    .build();

            Partijlid partijlid2 = Partijlid.builder()
                    .naam("Tom Van Grieken")
                    .email("tom.vangrieken@vlaamsbelang.org")
                    .partijNaam("Vlaams Belang")
                    .regeringNaam("Federaal")
                    .build();

            Partijlid partijlid3 = Partijlid.builder()
                    .naam("Alexander De Croo")
                    .email("alexander.decroo@premier.fed.be")
                    .partijNaam("Open VLD")
                    .regeringNaam("Federaal")
                    .build();

            Partijlid partijlid4 = Partijlid.builder()
                    .naam("Vincent Van Pethegem")
                    .email("info@vincent.minfin.be")
                    .partijNaam("CD&V")
                    .regeringNaam("Federaal")
                    .build();

            Partijlid partijlid5 = Partijlid.builder()
                    .naam("Frank Vandenbroucke")
                    .email("info@vandenbroucke.fed.be")
                    .partijNaam("Vooruit")
                    .regeringNaam("Federaal")
                    .build();

            Partijlid partijlid6 = Partijlid.builder()
                    .naam("Petra De Sutter")
                    .email("info@desutter.fed.be.")
                    .partijNaam("Groen")
                    .regeringNaam("Federaal")
                    .build();

            Partijlid partijlid7 = Partijlid.builder()
                    .naam("Raoul Hedebouw")
                    .email("raoul.hedebouw@ptb.be")
                    .partijNaam("PVDA")
                    .regeringNaam("Federaal")
                    .build();

            Partijlid partijlid8 = Partijlid.builder()
                    .naam("Jan Jambon")
                    .email("jan.jambon@n-va.be")
                    .partijNaam("NVA")
                    .regeringNaam("Vlaams")
                    .build();

            Partijlid partijlid9 = Partijlid.builder()
                    .naam("Chris Janssens")
                    .email("chris.janssens@vlaamsbelang.org")
                    .partijNaam("Vlaams Belang")
                    .regeringNaam("Vlaams")
                    .build();

            Partijlid partijlid10 = Partijlid.builder()
                    .naam("Gwendolyn Rutten")
                    .email("gwendolyn.rutten@aarschot.be")
                    .partijNaam("Open VLD")
                    .regeringNaam("Vlaams")
                    .build();

            Partijlid partijlid11 = Partijlid.builder()
                    .naam("Wouter Beke")
                    .email("info@wouterbeke.be")
                    .partijNaam("CD&V")
                    .regeringNaam("Vlaams")
                    .build();

            Partijlid partijlid12 = Partijlid.builder()
                    .naam("Conner Rouseau")
                    .email("conner.rousseau@vooruit.be")
                    .partijNaam("Vooruit")
                    .regeringNaam("Vlaams")
                    .build();

            Partijlid partijlid13 = Partijlid.builder()
                    .naam("Jeremie Vaneeckhout")
                    .email("jeremie.vaneeckhout@vlaamsparlement.be")
                    .partijNaam("Groen")
                    .regeringNaam("Vlaams")
                    .build();

            Partijlid partijlid14 = Partijlid.builder()
                    .naam("Jos D'Haese")
                    .email("jos.dhaese@vlaamsparlement.be")
                    .partijNaam("PVDA")
                    .regeringNaam("Vlaams")
                    .build();

            partijlidRepository.save(partijlid1);
            partijlidRepository.save(partijlid2);
            partijlidRepository.save(partijlid3);
            partijlidRepository.save(partijlid4);
            partijlidRepository.save(partijlid5);
            partijlidRepository.save(partijlid6);
            partijlidRepository.save(partijlid7);
            partijlidRepository.save(partijlid8);
            partijlidRepository.save(partijlid9);
            partijlidRepository.save(partijlid10);
            partijlidRepository.save(partijlid11);
            partijlidRepository.save(partijlid12);
            partijlidRepository.save(partijlid13);
            partijlidRepository.save(partijlid14);
        }

    }

    public List<PartijlidResponse> getAllPartijleden() {
        List<Partijlid> partijliden = partijlidRepository.findAll();
        return partijliden.stream()
                .map(this::mapToPartijlidResponse)
                .toList();
    }
    public void createPartijlid(PartijlidRequest partijlidRequest) {
        Partijlid partijlid = Partijlid.builder()
                .naam(partijlidRequest.getNaam())
                .email(partijlidRequest.getEmail())
                .partijNaam(partijlidRequest.getPartijNaam())
                .regeringNaam(partijlidRequest.getRegeringNaam())
                .build();

        partijlidRepository.save(partijlid);
    }

    public PartijlidResponse getPartijlidByNaam(String naam) {
        Optional<Partijlid> partijlid = partijlidRepository.findByNaam(naam);
        if (partijlid.isPresent()) {
            return mapToPartijlidResponse(partijlid.get());
        } else {
            throw new IllegalArgumentException("Partijlid with naam " + naam + " not found.");
        }
    }

    public PartijlidResponse getPartijlidById(long id) {
        Optional<Partijlid> partijlid = partijlidRepository.findById(id);
        if (partijlid.isPresent()) {
            return mapToPartijlidResponse(partijlid.get());
        } else {
            throw new IllegalArgumentException("Partijlid with ID " + id + " not found.");
        }
    }

    public void editPartijlid(long id, PartijlidRequest editPartijlidRequest) {
        Partijlid existingPartijlid = partijlidRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Partijlid not found with id: " + id));

        existingPartijlid.setNaam(editPartijlidRequest.getNaam());
        existingPartijlid.setEmail(editPartijlidRequest.getEmail());
        existingPartijlid.setPartijNaam(editPartijlidRequest.getPartijNaam());
        existingPartijlid.setRegeringNaam(editPartijlidRequest.getRegeringNaam());

        partijlidRepository.save(existingPartijlid);
    }

    public void deletePartijlid(long id) {
        if (!partijlidRepository.existsById(id)) {
            throw new IllegalArgumentException("Partijlid with ID " + id + " not found.");
        }
        partijlidRepository.deleteById(id);
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
