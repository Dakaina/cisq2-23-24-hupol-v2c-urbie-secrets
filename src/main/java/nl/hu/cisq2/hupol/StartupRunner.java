package nl.hu.cisq2.hupol;

import nl.hu.cisq2.hupol.security.application.AdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class StartupRunner implements CommandLineRunner {
    private final AdminService adminService;

    public StartupRunner(final AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void run(final String... args) {
        final String username = "admin";
        final String password = "topsecretpasswordforadmin";

        this.adminService.registerNewUserAsAdmin(username, password);
    }
}
