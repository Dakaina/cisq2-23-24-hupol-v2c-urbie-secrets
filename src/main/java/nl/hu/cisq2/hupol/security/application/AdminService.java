package nl.hu.cisq2.hupol.security.application;

import nl.hu.cisq2.hupol.security.data.UserRepository;
import nl.hu.cisq2.hupol.security.domain.Role;
import nl.hu.cisq2.hupol.security.domain.User;
import nl.hu.cisq2.hupol.security.domain.exception.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static nl.hu.cisq2.hupol.security.domain.Role.ROLE_ADMIN;
import static nl.hu.cisq2.hupol.security.domain.Role.ROLE_USER;

@Service
public class AdminService {
    @Value("${secret.pepper}")
    private String pepper;
    private final UserRepository userRepository;

    private final AuthenticationService authenticationService;

    @Autowired
    public AdminService(UserRepository userRepository, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    public void registerNewUserAsAdmin(String username, String password) {
        if (this.userRepository.existsById(username)) {
            return;
        }

        this.authenticationService.register(username, password);
        promote(username);
    }

    public void promote(String username) {
        User user = this.userRepository.findById(username)
                .orElseThrow(UserNotFound::new);

        user.addRole(ROLE_ADMIN);

        this.userRepository.save(user);
    }

    public void demote(String username) {
        User user = this.userRepository.findById(username)
                .orElseThrow(UserNotFound::new);

        user.removeRole(ROLE_ADMIN);

        this.userRepository.save(user);
    }
}
