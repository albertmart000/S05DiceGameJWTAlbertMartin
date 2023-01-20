package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.controllers;

import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.services.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private IPlayerService playerService;

    @PostMapping
    public ResponseEntity<PlayerDTO> addPlayer(@RequestBody PlayerDTO playerDTO) {
        try {
            PlayerDTO newPlayerDTO = playerService.addPlayer(playerDTO);
            return new ResponseEntity<>(newPlayerDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerDTO> getPlayer(@PathVariable Long playerId) {
        return ResponseEntity.ok(playerService.getPlayerById(playerId));
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<PlayerDTO> updatePlayer(@RequestBody PlayerDTO playerDTO,
                                                  @PathVariable Long playerId) {
        PlayerDTO playerToUpdate = playerService.updatePlayer(playerDTO, playerId);
        return new ResponseEntity<>(playerToUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long playerId) {
        playerService.deletePlayer(playerId);
        return new ResponseEntity<>("S'ha esborrat el jugador amb el id: " + playerId,
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers(){
        try {
            List<PlayerDTO> playerDTOList = playerService.getAllPlayers();
            if (playerDTOList.isEmpty()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Error", "No n'hi ha cap jugador a la llista");
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(playerDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}


