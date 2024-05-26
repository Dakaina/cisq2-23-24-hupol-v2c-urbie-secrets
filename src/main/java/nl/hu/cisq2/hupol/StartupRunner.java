package nl.hu.cisq2.hupol;

import nl.hu.cisq2.hupol.security.application.AdminService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class StartupRunner implements CommandLineRunner {
    private final AdminService adminService;

    @Value("${secret.admin.username}")
    private String username;

    @Value("${secret.admin.password}")
    private String password;

    public StartupRunner(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void run(String... args) {
        this.adminService.registerNewUserAsAdmin(username, password);
    }
}
