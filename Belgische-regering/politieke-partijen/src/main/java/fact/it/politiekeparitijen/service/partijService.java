package fact.it.politiekeparitijen.service;


import fact.it.politiekeparitijen.dto.partijResponse;
import fact.it.politiekeparitijen.model.partij;
import fact.it.politiekeparitijen.repository.partijRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class partijService {

    private final partijRepository partijrepository;

    @PostConstruct
    public void loadData() {
        System.out.println(partijrepository.count());
        if(partijrepository.count() <= 0){
            partij partij1 = new partij();
            partij1.setPartijNaam("Vlaams Belang");
            partij1.setAdres("Madouplein 8");
            partij1.setPostCode("1210");
            partij1.setStad("Brussel");
            partij1.setCoalitie(false);

            partij partij2 = new partij();
            partij2.setPartijNaam("NVA");
            partij2.setAdres("Koningsstraat 47");
            partij2.setPostCode("1000");
            partij2.setStad("Brussel");
            partij2.setCoalitie(false);

            partij partij3 = new partij();
            partij3.setPartijNaam("Open VLD");
            partij3.setAdres("Melsensstraat 34");
            partij3.setPostCode("1000 ");
            partij3.setStad("Brussel");
            partij3.setCoalitie(true);

            partij partij4 = new partij();
            partij4.setPartijNaam("CD&V");
            partij4.setAdres("Gare Maritime");
            partij4.setPostCode("1000");
            partij4.setStad("Brussel");
            partij4.setCoalitie(true);

            partij partij5 = new partij();
            partij5.setPartijNaam("Vooruit");
            partij5.setAdres("Keizerslaan 13");
            partij5.setPostCode("1000");
            partij5.setStad("Brussel");
            partij5.setCoalitie(true);

            partij partij6 = new partij();
            partij6.setPartijNaam("Groen");
            partij6.setAdres("Van Orleystraat 5-11");
            partij6.setPostCode("1000");
            partij6.setStad("Brussel");
            partij6.setCoalitie(true);

            partij partij7 = new partij();
            partij7.setPartijNaam("PVDA");
            partij7.setAdres("Maurice Lemonnierlaan 171");
            partij7.setPostCode("1000");
            partij7.setStad("Brussel");
            partij7.setCoalitie(false);

            partijrepository.save(partij1);
            partijrepository.save(partij2);
            partijrepository.save(partij3);
            partijrepository.save(partij4);
            partijrepository.save(partij5);
            partijrepository.save(partij6);
            partijrepository.save(partij7);

        }
    }
}
