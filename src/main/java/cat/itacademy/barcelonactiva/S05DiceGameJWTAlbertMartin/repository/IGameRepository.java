package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.repository;

import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IGameRepository extends JpaRepository<Game, Long> {

    @Transactional
    void deleteAllByPlayerId(Long playerId);

}
