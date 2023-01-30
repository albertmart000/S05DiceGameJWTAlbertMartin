package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.services;

import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.dto.GameDTO;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.entity.Dice;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.entity.Game;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.entity.Player;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.exceptions.ResourceNotFoundException;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.repository.IGameRepository;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.repository.IPlayerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GameServiceImpl implements IGameService {

    private final ModelMapper modelMapper;
    private final IGameRepository gameRepository;
    private final IPlayerRepository playerRepository;

    @Override
    public GameDTO addGame(GameDTO gameDTO, Long playerId) {
        GameDTO newGameDTO = createGame(playerId);
        Game newGame = mapDTOToEntity(newGameDTO);
        gameRepository.save(newGame);
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador", "id", playerId));
        List<Game> listGamesPlayer = player.getGameList();
        player.setGameList(listGamesPlayer);
        double rateGamesWon;
        double gamesWon = 0;
        for (Game gamePlayed : listGamesPlayer) {
            if (gamePlayed.getDice1() + gamePlayed.getDice2() == 7)
                gamesWon++;
        }
        rateGamesWon = (gamesWon / listGamesPlayer.size()) * 100;
        player.setRateGamesWon(rateGamesWon);
        playerRepository.save(player);
        return mapEntityToDTO(newGame);
    }

    @Override
    public GameDTO createGame(Long playerId) {
        int dice1 = Dice.throwDice();
        int dice2 = Dice.throwDice();
        return new GameDTO(playerId, dice1, dice2);
    }

    @Override
    public List<GameDTO> getGameListByPlayer(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador", "id", playerId));
        List<Game> gamesList = player.getGameList();
        return gamesList.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteGameList(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador", "id", playerId));
        gameRepository.deleteAllByPlayerId(playerId);
        player.setGameList(new ArrayList<>());
        player.setRateGamesWon(0.0);
        playerRepository.save(player);
    }

    private GameDTO mapEntityToDTO(Game game) {
        return modelMapper.map(game, GameDTO.class);
    }

    private Game mapDTOToEntity(GameDTO gameDTO) {
        return modelMapper.map(gameDTO, Game.class);
    }
}
