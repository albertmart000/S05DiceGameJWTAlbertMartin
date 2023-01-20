package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.repository;

import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    //comprobar Optional en Player
    Optional<User> findOneByEmail(String email);
}
