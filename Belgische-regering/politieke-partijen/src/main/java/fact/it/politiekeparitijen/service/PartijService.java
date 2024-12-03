package fact.it.politiekeparitijen.service;


import fact.it.politiekeparitijen.dto.PartijResponse;
import fact.it.politiekeparitijen.model.Partij;
import fact.it.politiekeparitijen.repository.PartijRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartijService {

    private final PartijRepository partijrepository;

    @PostConstruct
    public void loadData() {
        System.out.println(partijrepository.count());
        if(partijrepository.count() <= 0){
            Partij partij1 = new Partij();
            partij1.setPartijNaam("Vlaams Belang");
            partij1.setAdres("Madouplein 8");
            partij1.setPostCode("1210");
            partij1.setStad("Brussel");
            partij1.setCoalitieVlaams(false);
            partij1.setCoalitieFederaal(false);

            Partij partij2 = new Partij();
            partij2.setPartijNaam("NVA");
            partij2.setAdres("Koningsstraat 47");
            partij2.setPostCode("1000");
            partij2.setStad("Brussel");
            partij2.setCoalitieVlaams(true);
            partij2.setCoalitieFederaal(false);

            Partij partij3 = new Partij();
            partij3.setPartijNaam("Open VLD");
            partij3.setAdres("Melsensstraat 34");
            partij3.setPostCode("1000 ");
            partij3.setStad("Brussel");
            partij3.setCoalitieVlaams(true);
            partij3.setCoalitieFederaal(true);

            Partij partij4 = new Partij();
            partij4.setPartijNaam("CD&V");
            partij4.setAdres("Gare Maritime");
            partij4.setPostCode("1000");
            partij4.setStad("Brussel");
            partij4.setCoalitieVlaams(true);
            partij4.setCoalitieFederaal(true);

            Partij partij5 = new Partij();
            partij5.setPartijNaam("Vooruit");
            partij5.setAdres("Keizerslaan 13");
            partij5.setPostCode("1000");
            partij5.setStad("Brussel");
            partij5.setCoalitieVlaams(false);
            partij5.setCoalitieFederaal(true);

            Partij partij6 = new Partij();
            partij6.setPartijNaam("Groen");
            partij6.setAdres("Van Orleystraat 5-11");
            partij6.setPostCode("1000");
            partij6.setStad("Brussel");
            partij6.setCoalitieVlaams(false);
            partij6.setCoalitieFederaal(true);

            Partij partij7 = new Partij();
            partij7.setPartijNaam("PVDA");
            partij7.setAdres("Maurice Lemonnierlaan 171");
            partij7.setPostCode("1000");
            partij7.setStad("Brussel");
            partij7.setCoalitieVlaams(false);
            partij7.setCoalitieFederaal(false);

            partijrepository.save(partij1);
            partijrepository.save(partij2);
            partijrepository.save(partij3);
            partijrepository.save(partij4);
            partijrepository.save(partij5);
            partijrepository.save(partij6);
            partijrepository.save(partij7);

        }
    }

    public List<PartijResponse> getAllPartijen() {
        return partijrepository.findAll().stream()
                .map(regering -> PartijResponse.builder()
                        .partijNaam(regering.getPartijNaam())
                        .adres(regering.getAdres()) // Example: Map additional fields as needed
                        .build())
                .toList();
    }

}
