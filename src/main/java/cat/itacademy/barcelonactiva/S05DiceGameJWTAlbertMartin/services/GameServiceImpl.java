package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.services;

import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.dto.GameDTO;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.entity.Game;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.entity.Player;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.exceptions.ResourceNotFoundException;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.repository.IGameRepository;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.repository.IPlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements IGameService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IGameRepository gameRepository;

    @Autowired
    private IPlayerRepository playerRepository;

//    @Autowired
//    private PlayerServiceImpl playerService;

    @Override
    public GameDTO addGame(GameDTO gameDTO, Long id) {
        Game game = mapDTOToEntity(gameDTO);
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador", "id", id));
        game.setPlayer(player);
        Game newGame = gameRepository.save(game);
        for (Game gamePlayed : player.getGameList()) {
            int gamesWon = 0;
            if (gamePlayed.getDice1() + gamePlayed.getDice2() == 7) {
                player.setRateGamesWon((double) gamesWon / player.getGameList().size() * 100);
            }
        }
//        PlayerDTO playerDTO = playerService.mapEntityToDTO(player);

        return mapEntityToDTO(newGame);
    }

    //        if (gameList.size() == 0) {
//            this.rateGamesWon = 0.0;
//        } else {
//            for (Game game : gameList) {
//                int gamesWon = 0;
//                if (game.isPlayerHasWon()) {
//                    gamesWon++;
//                    this.rateGamesWon = (double) gamesWon / gameList.size() * 100;
//                }
//            }
//        }
//        return rateGamesWon;

//    public GameDTO addGame(GameDTO gameDTO, Long id) {
//        Game game = mapDTOToEntity(gameDTO);
//        Player player = playerRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Jugador", "id", id));
//        game.setPlayer(player);
//        Game newGame = gameRepository.save(game);
//        return mapEntityToDTO(newGame);
//   }


    @Override
    public List<GameDTO> getGameListByPlayer(Long id) {
        List<Game> gamesList = gameRepository.findAllByPlayerPlayerId(id);
        return gamesList.stream()
                .map(player -> mapEntityToDTO(player))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean deleteGameList(Long id) {
        gameRepository.deleteGamesByPlayerPlayerIdIs(id);
        return true;
    }

    // Convierte entidad a DTO
    private GameDTO mapEntityToDTO(Game game) {
        return modelMapper.map(game, GameDTO.class);
    }

    // Convierte de DTO a Entidad
    private Game mapDTOToEntity(GameDTO gameDTO) {
        return modelMapper.map(gameDTO, Game.class);
    }
}
