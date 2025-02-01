package in.choubeyshubham.incidentmanagmentsystemapi.controller;


import in.choubeyshubham.incidentmanagmentsystemapi.model.User;
import in.choubeyshubham.incidentmanagmentsystemapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        Optional<User> userOpt = userService.findByEmail(loginRequest.getEmail());
        if (userOpt.isPresent() &&
                userOpt.get().getPassword().equals(loginRequest.getPassword())) {
            // In production, return a JWT token rather than plain text.
            return "Logged in successfully";
        }
        return "Invalid credentials";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody User userRequest) {
        Optional<User> userOpt = userService.findByEmail(userRequest.getEmail());
        if (userOpt.isPresent()) {
            // In production, send an email with reset instructions.
            return "Password reset instructions sent to " + userRequest.getEmail();
        }
        return "User not found";
    }
}
