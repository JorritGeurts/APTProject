package fact.it.ministers.repository;

import fact.it.ministers.model.Minister;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface MinisterRepository extends JpaRepository<Minister, Long> {

}
