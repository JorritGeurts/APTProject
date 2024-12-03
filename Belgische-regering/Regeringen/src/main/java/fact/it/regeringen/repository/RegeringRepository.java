package fact.it.regeringen.repository;

import fact.it.regeringen.model.Regering;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface RegeringRepository extends JpaRepository<Regering, Long> {
}
