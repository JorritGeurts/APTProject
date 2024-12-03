package fact.it.ministers.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "minister")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Minister {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ministerId;

    private String naam;

}
