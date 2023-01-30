package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {

    private Long gameId;
    private Long playerId;
    private int dice1;
    private int dice2;

    public GameDTO(Long playerId, Integer dice1, Integer dice2) {
        super();
        this.playerId = playerId;
        this.dice1 = dice1;
        this.dice2 = dice2;
    }
}
