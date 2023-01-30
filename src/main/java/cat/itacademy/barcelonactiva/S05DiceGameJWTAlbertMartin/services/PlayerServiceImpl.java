package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.services;

import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.entity.Player;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.exceptions.ResourceNotFoundException;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.repository.IPlayerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PlayerServiceImpl implements IPlayerService {

    private final IPlayerRepository playerRepository;
    private final ModelMapper modelMapper;

    @Override
    public PlayerDTO addPlayer(PlayerDTO playerDTO) {
        Player player = mapDTOToEntity(playerDTO);
        if (player.getName() == null || player.getName().isEmpty()) {
            Player newPlayer = playerRepository.save(player);
            newPlayer.setName("Anonymous");
        }
        if (playerRepository.existsByName(player.getName())) {
            Player newPlayer = playerRepository.save(player);
            newPlayer.setName(player.getName() + newPlayer.getPlayerId());
        }
        Player newPlayer = playerRepository.save(player);
        return mapEntityToDTO(newPlayer);
    }

    @Override
    public PlayerDTO getPlayerById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador", "id", id));
        return mapEntityToDTO(player);
    }

    @Override
    public PlayerDTO updatePlayer(PlayerDTO playerDTOToUpdate, Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador", "id", id));
        player.setName(playerDTOToUpdate.getName());
        player.setRegistrationDate(playerDTOToUpdate.getRegistrationDate());
        Player playedUpdated = playerRepository.save(player);
        return mapEntityToDTO(playedUpdated);
    }

    @Override
    public void deletePlayer(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador", "id", id));
        playerRepository.delete(player);
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        List<Player> allPlayersList = playerRepository.findAll();
        return allPlayersList.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDTO getWinningPlayer() {
        List<Player> allPlayersList = playerRepository.findAll();
        Comparator<Player> comparator = Comparator.comparing(Player::getRateGamesWon);
        Player winningPlayer = allPlayersList.stream()
                .sorted(comparator).toList()
                .get(allPlayersList.size() - 1);
        return mapEntityToDTO(winningPlayer);
    }

    @Override
    public PlayerDTO getLosingPlayer() {
        List<Player> allPlayersList = playerRepository.findAll();
        Comparator<Player> comparator = Comparator.comparing(Player::getRateGamesWon);
        Player losingPlayer = allPlayersList.stream()
                .sorted(comparator).toList()
                .get(0);
        return mapEntityToDTO(losingPlayer);
    }

    @Override
    public List<PlayerDTO> getRankingPlayers() {
        List<Player> allPlayersList = playerRepository.findAll();
        Comparator<PlayerDTO> comparator = Comparator.comparing(PlayerDTO::getRateGamesWon);
        return allPlayersList.stream()
                .map(this::mapEntityToDTO)
                .sorted(comparator.reversed()).toList();

    }

    private PlayerDTO mapEntityToDTO(Player player) {
        return modelMapper.map(player, PlayerDTO.class);
    }

    private Player mapDTOToEntity(PlayerDTO playerDTO) {
        return modelMapper.map(playerDTO, Player.class);
    }
}
