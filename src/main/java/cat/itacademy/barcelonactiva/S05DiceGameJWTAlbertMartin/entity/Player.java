package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "player")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    private String name;

    @Temporal(TemporalType.DATE)
    private LocalDate registrationDate = LocalDate.now();

    private double rateGamesWon;

    @JsonManagedReference
    @OneToMany(mappedBy = "player")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Game> gameList;
}
