package com.delpe.todo.migrations;


import com.delpe.todo.domain.user.AccessLevel;
import com.delpe.todo.domain.user.User;
import com.delpe.todo.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultSupportUserMigration implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultSupportUserMigration(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        String email = "manager@mail.com";
        System.out.print("Passou");
        if (userRepository.findByEmail(email).isEmpty()) {
            System.out.print("Criando Email");

            User defaultUser = new User();
            defaultUser.setFirstName("Default");
            defaultUser.setLastName("Manager");
            defaultUser.setEmail("manager@mail.com");
            defaultUser.setPassword(passwordEncoder.encode("dmaloc"));
            defaultUser.setAccessLevel(AccessLevel.GERENTE);
            userRepository.save(defaultUser);
        }
    }
}
