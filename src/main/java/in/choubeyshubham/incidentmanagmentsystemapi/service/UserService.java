package in.choubeyshubham.incidentmanagmentsystemapi.service;


import in.choubeyshubham.incidentmanagmentsystemapi.model.User;
import in.choubeyshubham.incidentmanagmentsystemapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationService locationService;

    public User registerUser(User user) {
        // Auto-fill City and Country based on pin code
        if (user.getPinCode() != null) {
            String[] location = locationService.getLocationFromPinCode(user.getPinCode());
            user.setCity(location[0]);
            user.setCountry(location[1]);
        }
        // Add password encryption here in a real application
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }
}
