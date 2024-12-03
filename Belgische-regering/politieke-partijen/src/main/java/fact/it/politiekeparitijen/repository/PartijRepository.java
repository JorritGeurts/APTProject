package fact.it.politiekeparitijen.repository;

import fact.it.politiekeparitijen.model.Partij;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartijRepository extends JpaRepository<Partij, Long> {
    List<Partij> findByNaamIn(List<String> naam);
}
