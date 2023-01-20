package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.dto;



import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.entity.Game;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlayerDTO implements Serializable {

    private Long playerId;
    private String name;
    private LocalDate registrationDate = LocalDate.now();
    private double rateGamesWon;
    private List<Game> gameList = new ArrayList<>();

    public PlayerDTO() {
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
//            for (Game game: gameList) {
//                int gamesWon = 0;
//                if (game.getDice1() + game.getDice2() == 7) {
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
