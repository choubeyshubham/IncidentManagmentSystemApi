package in.choubeyshubham.incidentmanagmentsystemapi.repository;


import in.choubeyshubham.incidentmanagmentsystemapi.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    Optional<Incident> findByIncidentId(String incidentId);
    boolean existsByIncidentId(String incidentId);
    List<Incident> findByUserId(Long userId);
}
