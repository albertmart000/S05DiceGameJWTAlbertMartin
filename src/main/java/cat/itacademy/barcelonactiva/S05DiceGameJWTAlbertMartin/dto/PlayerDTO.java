package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.dto;

import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.entity.Game;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO implements Serializable {

    private Long playerId;
    private String name;
    private LocalDate registrationDate = LocalDate.now();
    private double rateGamesWon;
    private List<Game> gameList = new ArrayList<>();

}
