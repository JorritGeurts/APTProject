package fact.it.politiekeparitijen.repository;

import fact.it.politiekeparitijen.model.partij;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface partijRepository extends JpaRepository<partij, Long> {
    List<partij> findByNaamIn(List<String> naam);
}
