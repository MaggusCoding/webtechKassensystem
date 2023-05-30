package htwberlin.backend_kasse.repos;

import htwberlin.backend_kasse.entities.MitarbeiterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MitarbeiterRepository extends JpaRepository<MitarbeiterEntity, Integer> {
}
