package fact.it.regeringen.service;

import fact.it.regeringen.dtos.RegeringResponse;
import fact.it.regeringen.model.Regering;
import fact.it.regeringen.repository.RegeringRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegeringService {

    private final RegeringRepository regeringRepository;

    @PostConstruct
    public void loadData() {
        System.out.println(regeringRepository.count());
        if(regeringRepository.count() <= 0){
            Regering regering1 = new Regering();
            regering1.setNaam("Vlaamse regering");

            Regering regering2 = new Regering();
            regering2.setNaam("Federale regering");
        }
    }

    public List<RegeringResponse> getAllRegeringen() {
        return regeringRepository.findAll().stream()
                .map(regering -> RegeringResponse.builder()
                        .Naam(regering.getNaam())
                        .build())
                .toList();
    }
}
