package nl.hu.cisq2.hupol.security.application;

import jakarta.transaction.Transactional;
import nl.hu.cisq2.hupol.security.data.UserRepository;
import nl.hu.cisq2.hupol.security.domain.Role;
import nl.hu.cisq2.hupol.security.domain.User;
import nl.hu.cisq2.hupol.security.domain.UserProfile;
import nl.hu.cisq2.hupol.security.domain.exception.UserAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
@Transactional
public class AuthenticationService implements UserDetailsService {
    @Value("${secret.pepper}")
    private String pepper;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserProfile login(String username, String password) {
        User user = this.userRepository.findById(username)
                .orElseThrow(() -> new BadCredentialsException("User not found"));

        if (passwordEncoder.matches(password + pepper, user.getPassword())) {
            return new UserProfile(user.getUsername(), user.getAuthorities());
        }
        throw new BadCredentialsException("Wrong password");
    }

    public UserProfile register(String username, String password) {
        if (this.userRepository.existsById(username)) {
            throw new UserAlreadyExists();
        }

        List<Role> roles = new ArrayList<>();
        roles.add(Role.ROLE_USER);

        String encodedPassword = passwordEncoder.encode(password + pepper);
        User user = new User(username, encodedPassword, roles);

        this.userRepository.save(user);

        return new UserProfile(user.getUsername(), user.getAuthorities());
    }
    @Override
    public User loadUserByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
