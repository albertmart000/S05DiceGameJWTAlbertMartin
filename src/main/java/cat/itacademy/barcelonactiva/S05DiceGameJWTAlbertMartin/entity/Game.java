package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game")
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;
    private Long playerId;
    private Integer dice1;
    private Integer dice2;

    @ManyToOne
    @JoinColumn(name = "playerId", insertable=false, updatable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Player player;

}
