package in.choubeyshubham.incidentmanagmentsystemapi.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "incidents")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Auto-generated and unique (e.g. RMG345712022)
    @Column(unique = true, nullable = false)
    private String incidentId;

    // Link to the reporting user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String reporterName;

    @Column(columnDefinition = "TEXT")
    private String incidentDetails;

    private LocalDateTime reportedDateTime;

    @Enumerated(EnumType.STRING)
    private Priority priority;  // HIGH, MEDIUM, LOW

    @Enumerated(EnumType.STRING)
    private IncidentStatus status;  // OPEN, IN_PROGRESS, CLOSED

    public enum Priority {
        HIGH, MEDIUM, LOW
    }

    public enum IncidentStatus {
        OPEN, IN_PROGRESS, CLOSED
    }
}
