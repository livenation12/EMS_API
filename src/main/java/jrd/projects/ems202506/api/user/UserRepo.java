package jrd.projects.ems202506.api.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	Boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);

}
