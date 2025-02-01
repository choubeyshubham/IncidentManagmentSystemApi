package in.choubeyshubham.incidentmanagmentsystemapi.controller;

import in.choubeyshubham.incidentmanagmentsystemapi.model.Incident;
import in.choubeyshubham.incidentmanagmentsystemapi.model.User;
import in.choubeyshubham.incidentmanagmentsystemapi.service.IncidentService;
import in.choubeyshubham.incidentmanagmentsystemapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {
    @Autowired
    private IncidentService incidentService;

    @Autowired
    private UserService userService;

    // Create an incident (associate with the logged in user via userId)
    @PostMapping
    public Incident createIncident(@RequestBody Incident incident, @RequestParam Long userId) throws Exception {
        User user = userService.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));
        incident.setUser(user);
        return incidentService.createIncident(incident);
    }

    // Edit/update an incident
    @PutMapping("/{id}")
    public Incident updateIncident(@PathVariable Long id,
                                   @RequestBody Incident incident,
                                   @RequestParam Long userId) throws Exception {
        return incidentService.updateIncident(id, incident, userId);
    }

    // Search an incident by its auto-generated incident ID
    @GetMapping("/search")
    public Incident searchIncident(@RequestParam String incidentId,
                                   @RequestParam Long userId) throws Exception {
        Incident incident = incidentService.findByIncidentId(incidentId)
                .orElseThrow(() -> new Exception("Incident not found"));
        if (!incident.getUser().getId().equals(userId)) {
            throw new Exception("Unauthorized access");
        }
        return incident;
    }

    // List all incidents for a user
    @GetMapping
    public List<Incident> getUserIncidents(@RequestParam Long userId) {
        return incidentService.getIncidentsByUser(userId);
    }
}
