package in.choubeyshubham.incidentmanagmentsystemapi.service;

;
import in.choubeyshubham.incidentmanagmentsystemapi.model.Incident;
import in.choubeyshubham.incidentmanagmentsystemapi.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Random;
import java.util.Optional;
import java.util.List;

@Service
public class IncidentService {
    @Autowired
    private IncidentRepository incidentRepository;

    private static final String PREFIX = "RMG";
    private Random random = new Random();

    public Incident createIncident(Incident incident) {
        incident.setIncidentId(generateUniqueIncidentId());
        incident.setReportedDateTime(LocalDateTime.now());
        return incidentRepository.save(incident);
    }

    public Incident updateIncident(Long id, Incident updatedIncident, Long userId) throws Exception {
        Incident existing = incidentRepository.findById(id)
                .orElseThrow(() -> new Exception("Incident not found"));
        if (!existing.getUser().getId().equals(userId)) {
            throw new Exception("Unauthorized to update this incident");
        }
        if (existing.getStatus() == Incident.IncidentStatus.CLOSED) {
            throw new Exception("Cannot edit a closed incident");
        }
        // Update allowed fields only
        existing.setIncidentDetails(updatedIncident.getIncidentDetails());
        existing.setPriority(updatedIncident.getPriority());
        existing.setStatus(updatedIncident.getStatus());
        return incidentRepository.save(existing);
    }

    public Optional<Incident> findByIncidentId(String incidentId) {
        return incidentRepository.findByIncidentId(incidentId);
    }

    public List<Incident> getIncidentsByUser(Long userId) {
        return incidentRepository.findByUserId(userId);
    }

    private String generateUniqueIncidentId() {
        String incidentId;
        do {
            int randomNumber = random.nextInt(90000) + 10000;  // generate a 5-digit number
            String year = String.valueOf(Year.now().getValue());
            incidentId = PREFIX + randomNumber + year;
        } while (incidentRepository.existsByIncidentId(incidentId));
        return incidentId;
    }
}
