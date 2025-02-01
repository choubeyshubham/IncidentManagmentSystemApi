package in.choubeyshubham.incidentmanagmentsystemapi.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String username;
    private String phoneNumber;
    private String address;
    private String pinCode;
    private String city;
    private String country;

    // In a production system, store the encrypted password
    private String password;
}
