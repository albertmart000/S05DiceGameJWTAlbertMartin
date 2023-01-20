package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.services;

import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.dto.PlayerDTO;

import java.util.List;

public interface IPlayerService {

    PlayerDTO addPlayer(PlayerDTO playerDTO);

    PlayerDTO updatePlayer(PlayerDTO playerDTOToUpdate, Long id);

    Boolean deletePlayer(Long id);

    PlayerDTO getPlayerById(Long id);

    List<PlayerDTO> getAllPlayers();

    PlayerDTO getWinningPlayer();

    PlayerDTO getLosingPlayer();

    List<PlayerDTO> getRankingPlayers();

}
