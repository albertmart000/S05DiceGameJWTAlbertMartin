package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.services;

import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.entity.Player;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.exceptions.ResourceNotFoundException;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.repository.IPlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements IPlayerService {

    @Autowired
    private IPlayerRepository playerRepository;

    @Autowired
    private ModelMapper modelMapper;

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
    public Boolean deletePlayer(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador", "id", id));
        playerRepository.delete(player);
        return true;
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        List<Player> playersList = playerRepository.findAll();
        return playersList.stream()
                .map(player -> mapEntityToDTO(player))
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDTO getWinningPlayer() {
        return null;
    }

    @Override
    public PlayerDTO getLosingPlayer() {
        return null;
    }

    @Override
    public List<PlayerDTO> getRankingPlayers() {
        return null;
    }

    // Convierte entidad a DTO
    private PlayerDTO mapEntityToDTO(Player player) {
        return modelMapper.map(player, PlayerDTO.class);
    }

    // Convierte de DTO a Entidad
    private Player mapDTOToEntity(PlayerDTO playerDTO) {
        return modelMapper.map(playerDTO, Player.class);
    }
}
