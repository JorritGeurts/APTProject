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
            Partijlid partijlid1 = new Partijlid();
            partijlid1.setNaam("Bart De Wever");
            partijlid1.setEmail("bart.dewever@n-va.be");
            partijlid1.setPartijNaam("NVA");
            partijlid1.setRegeringNaam("Federaal");

            Partijlid partijlid2 = new Partijlid();
            partijlid2.setNaam("Tom Van Grieken");
            partijlid2.setEmail("tom.vangrieken@vlaamsbelang.org");
            partijlid2.setPartijNaam("Vlaams Belang");
            partijlid2.setRegeringNaam("Federaal");

            Partijlid partijlid3 = new Partijlid();
            partijlid3.setNaam("Alexander De Croo");
            partijlid3.setEmail("alexander.decroo@premier.fed.be");
            partijlid3.setPartijNaam("Open VLD");
            partijlid3.setRegeringNaam("Federaal");

            Partijlid partijlid4 = new Partijlid();
            partijlid4.setNaam("Vincent Van Pethegem");
            partijlid4.setEmail("info@vincent.minfin.be");
            partijlid4.setPartijNaam("CD&V");
            partijlid4.setRegeringNaam("Federaal");

            Partijlid partijlid5 = new Partijlid();
            partijlid5.setNaam("Frank Vandenbroucke");
            partijlid5.setEmail("info@vandenbroucke.fed.be");
            partijlid5.setPartijNaam("Vooruit");
            partijlid5.setRegeringNaam("Federaal");

            Partijlid partijlid6 = new Partijlid();
            partijlid6.setNaam("Petra De Sutter");
            partijlid6.setEmail("info@desutter.fed.be.");
            partijlid6.setPartijNaam("Groen");
            partijlid6.setRegeringNaam("Federaal");

            Partijlid partijlid7 = new Partijlid();
            partijlid7.setNaam("Raoul Hedebouw");
            partijlid7.setEmail("raoul.hedebouw@ptb.be");
            partijlid7.setPartijNaam("PVDA");
            partijlid7.setRegeringNaam("Federaal");

            Partijlid partijlid8 = new Partijlid();
            partijlid8.setNaam("Jan Jambon");
            partijlid8.setEmail("jan.jambon@n-va.be");
            partijlid8.setPartijNaam("NVA");
            partijlid8.setRegeringNaam("Vlaams");

            Partijlid partijlid9 = new Partijlid();
            partijlid9.setNaam("Chris Janssens");
            partijlid9.setEmail("chris.janssens@vlaamsbelang.org");
            partijlid9.setPartijNaam("Vlaams Belang");
            partijlid9.setRegeringNaam("Vlaams");

            Partijlid partijlid10 = new Partijlid();
            partijlid10.setNaam("Gwendolyn Rutten");
            partijlid10.setEmail("gwendolyn.rutten@aarschot.be");
            partijlid10.setPartijNaam("Open VLD");
            partijlid10.setRegeringNaam("Vlaams");

            Partijlid partijlid11 = new Partijlid();
            partijlid11.setNaam("Wouter Beke");
            partijlid11.setEmail("info@wouterbeke.be");
            partijlid11.setPartijNaam("CD&V");
            partijlid11.setRegeringNaam("Vlaams");

            Partijlid partijlid12 = new Partijlid();
            partijlid12.setNaam("Conner Rouseau");
            partijlid12.setEmail("conner.rousseau@vooruit.be");
            partijlid12.setPartijNaam("Vooruit");
            partijlid12.setRegeringNaam("Vlaams");

            Partijlid partijlid13 = new Partijlid();
            partijlid13.setNaam("Jeremie Vaneeckhout");
            partijlid13.setEmail("jeremie.vaneeckhout@vlaamsparlement.be");
            partijlid13.setPartijNaam("Groen");
            partijlid13.setRegeringNaam("Vlaams");

            Partijlid partijlid14 = new Partijlid();
            partijlid14.setNaam("Jos D'Haese");
            partijlid14.setEmail("jos.dhaese@vlaamsparlement.be");
            partijlid14.setPartijNaam("PVDA");
            partijlid14.setRegeringNaam("Vlaams");

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
        Partijlid partijlid = new Partijlid();
        partijlid.setNaam(partijlidRequest.getNaam());
        partijlid.setEmail(partijlidRequest.getEmail());
        partijlid.setPartijNaam(partijlidRequest.getPartijNaam());
        partijlid.setRegeringNaam(partijlidRequest.getRegeringNaam());

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
