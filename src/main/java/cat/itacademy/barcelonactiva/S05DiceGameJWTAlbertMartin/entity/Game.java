package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Table(name = "game")
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

   // @Column(name = "dice1")
    private int dice1 = Dice.throwDice();

   // @Column(name = "dice2")
    private int dice2 = Dice.throwDice();

    //private boolean playerHasWon = playerHasWon(dice1, dice2);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Player player;

    public Game() {
        super();
    }

    public Long getGameId() {
        return gameId;
    }

    public int getDice1() {
        return dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public void setDice1(int dice1) {
        this.dice1 = dice1;
    }

    public void setDice2(int dice2) {
        this.dice2 = dice2;
    }

//    public boolean playerHasWon(int dice1, int dice2) {
//        return dice1 + dice2 == 7;
//    }
//
//    public boolean isPlayerHasWon() {
//        return playerHasWon;
//    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
