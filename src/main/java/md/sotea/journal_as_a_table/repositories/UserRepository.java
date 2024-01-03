package md.sotea.journal_as_a_table.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import md.sotea.journal_as_a_table.security_mysql.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

// import org.springframework.data.repository.CrudRepository;

// public interface UserRepository extends CrudRepository<User, Long> {
// }