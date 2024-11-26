package fact.it.politiekeparitijen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mongo-partij")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class partij {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long partijId;

    private String partijNaam;
    private String adres;
    private String postCode;
    private String stad;
    private boolean coalitie;
}
