package fact.it.regeringen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "regering")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Regering {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String Naam;
}
