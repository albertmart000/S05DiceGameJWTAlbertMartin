package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.controllers;

import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.dto.GameDTO;
import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.services.GameServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class GameController {

    @Autowired
    private GameServiceImpl gameService;

//    @PostMapping("/{playerId}/games")
//    public ResponseEntity<GameDTO> addGame(@PathVariable Long playerId, @RequestBody GameDTO gameDTO) {
//        GameDTO gameDTO = gameService.addGame(gameDTO, playerId);
//        return new ResponseEntity<>(gameDTO, HttpStatus.CREATED);
//    }

    @PostMapping("/{playerId}/games")
    public ResponseEntity<GameDTO> addGame(@PathVariable Long playerId, @RequestBody GameDTO gameDTO) {
        try {
            GameDTO newGameDTO = gameService.addGame(gameDTO, playerId);
            return new ResponseEntity<>(newGameDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{playerId}/games")
    public ResponseEntity<List<GameDTO>> getGameListByPlayer(@PathVariable Long playerId){
        try {
            List<GameDTO> gameDTOList = gameService.getGameListByPlayer(playerId);
            if (gameDTOList.isEmpty()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Error", "No n'hi ha cap jugada a la llista");
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(gameDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{playerId}/games")
    public ResponseEntity<String> deleteGameList(@PathVariable Long playerId) {
        gameService.deleteGameList(playerId);
        return new ResponseEntity<>("S'ha esborrat la llista de jugades el jugador amb el id: " + playerId,
                HttpStatus.OK);
    }
}
