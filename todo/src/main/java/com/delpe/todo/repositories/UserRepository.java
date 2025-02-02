package com.delpe.todo.repositories;

import com.delpe.todo.domain.user.AccessLevel;
import com.delpe.todo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByAccessLevel(AccessLevel accessLevel);

    Optional<User> findByEmail(String email);

}
