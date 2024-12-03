package fact.it.ministers.service;

import fact.it.ministers.dto.MinisterResponse;
import fact.it.ministers.model.Minister;
import fact.it.ministers.repository.MinisterRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MinisterService {

    private final MinisterRepository ministerRepository;

    @PostConstruct
    public void LoadData() {
        System.out.println(ministerRepository.count());
        if (ministerRepository.count() <= 0) {

            Minister minister1 = new Minister();
            minister1.setNaam("Premier");

            Minister minister2 = new Minister();
            minister2.setNaam("Minister van Defensie");

            Minister minister3 = new Minister();
            minister3.setNaam("Minister van Buitenlandse zaken");

            Minister minister4 = new Minister();
            minister4.setNaam("Minister van Justisie");

            Minister minister5 = new Minister();
            minister5.setNaam("Vlaams minister president");

            Minister minister6 = new Minister();
            minister6.setNaam("Minister van Onderwijs");

            Minister minister7 = new Minister();
            minister7.setNaam("Minister van Mobiliteit");

            Minister minister8 = new Minister();
            minister8.setNaam("Minister van Welzijn");

            ministerRepository.save(minister1);
            ministerRepository.save(minister2);
            ministerRepository.save(minister3);
            ministerRepository.save(minister4);
            ministerRepository.save(minister5);
            ministerRepository.save(minister6);
            ministerRepository.save(minister7);
            ministerRepository.save(minister8);

        }
    }

    public List<MinisterResponse> getAllMinisters() {
        return ministerRepository.findAll().stream()
                .map(minister -> MinisterResponse.builder()
                        .naam(minister.getNaam())
                        .build())
                .toList();
    }
}