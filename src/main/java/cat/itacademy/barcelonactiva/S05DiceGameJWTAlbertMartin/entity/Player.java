package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "player")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    //@Column(name = "name")
    private String name;

    @Temporal(TemporalType.DATE)
   // @Column(name = "registration_date")
    private LocalDate registrationDate = LocalDate.now();


//    @Transient
   private double rateGamesWon;

    @JsonManagedReference
    @OneToMany(mappedBy = "player")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Game> gameList = new ArrayList<>();

    public Player() {
        super();
    }

    public Player(Long playerId, String name) {
        super();
        this.playerId = playerId;
        this.name = name;
    }

    public Player(Long playerId, String name, LocalDate registrationDate) {
        this.playerId = playerId;
        this.name = name;
        this.registrationDate = registrationDate;
    }

//    public Player(Long playerId, String name, LocalDate registrationDate, double rateGamesWon, List<Game> gameList) {
//        this.playerId = playerId;
//        this.name = name;
//        this.registrationDate = registrationDate;
//        this.rateGamesWon = rateGamesWon;
//        this.gameList = gameList;
//    }

    public Player(Long playerId, String name, LocalDate registrationDate, List<Game> gameList) {
        super();
        this.playerId = playerId;
        this.name = name;
        this.registrationDate = registrationDate;
        this.gameList = gameList;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public double getRateGamesWon() {
        return rateGamesWon;
    }

    public void setRateGamesWon(double rateGamesWon) {
        this.rateGamesWon = rateGamesWon;
    }
//    public double getRateGamesWon() {
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
//    }
//
//    public void setRateGamesWon(double rateGamesWon) {
//        this.rateGamesWon = rateGamesWon;
//    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

}
