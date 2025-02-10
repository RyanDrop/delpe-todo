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

            User.UserBuilder builderUser = User.builder();
            builderUser.firstName("Default");
            builderUser.lastName("Manager");
            builderUser.email("manager@mail.com");
            builderUser.password(passwordEncoder.encode("dmaloc"));
            builderUser.accessLevel(AccessLevel.GERENTE);
            User defaultUser = builderUser.build();
            userRepository.save(defaultUser);
        }
    }
}
