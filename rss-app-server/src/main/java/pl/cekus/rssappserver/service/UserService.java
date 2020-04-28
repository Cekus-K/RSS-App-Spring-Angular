package pl.cekus.rssappserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.cekus.rssappserver.model.User;
import pl.cekus.rssappserver.repository.UserRepository;

@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createUser(User user) {
        if (userRepository.findUserByUsername(user.getUsername()) == null) {
            if (user.getPassword().equals(user.getPasswordConfirm())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                logger.info("Register new user: " + user.getUsername());
                return userRepository.save(user).getUsername();
            }
        }
        logger.info(user.getUsername() + " already exists!");
        return userRepository.findUserByUsername(user.getUsername()).getUsername();
    }

    public User getCurrentLoggedInUser() {
        UserDetails userDetails;
        try {
            userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Get current logged in user.");
        } catch (ClassCastException e) {
            userDetails = userRepository.findUserByUsername("admin");
            logger.info("Get default admin user.");
        }
        return userRepository.findUserByUsername(userDetails.getUsername());
    }

    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
