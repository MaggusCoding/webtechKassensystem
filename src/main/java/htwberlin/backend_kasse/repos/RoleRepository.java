package htwberlin.backend_kasse.repos;

import java.util.Optional;
import htwberlin.backend_kasse.entities.ERole;
import htwberlin.backend_kasse.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
