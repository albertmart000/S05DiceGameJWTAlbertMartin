package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.services;

import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.dto.GameDTO;

import java.util.List;

public interface IGameService {

    GameDTO createGame(Long playerId);

    GameDTO addGame (GameDTO gameDTO, Long playerId);

    List<GameDTO> getGameListByPlayer(Long playerId);

    void deleteGameList(Long playerId);

}

