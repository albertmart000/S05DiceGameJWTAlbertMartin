package cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.dto;

import cat.itacademy.barcelonactiva.S05DiceGameJWTAlbertMartin.entity.Dice;

public class GameDTO {

    private Long gameId;
    private int dice1; // = Dice.throwDice();
    private int dice2; // = Dice.throwDice();
    //private boolean playerHasWon; // = playerHasWon(dice1, dice2);

    public GameDTO() {
        super();
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public int getDice1() {
        int dice1 = (int) Math.floor(Math.random()*6+1);
        return dice1;
    }

    public void setDice1(int dice1) {
        this.dice1 = dice1;
    }

    public int getDice2() {
        int dice2 = (int) Math.floor(Math.random()*6+1);
        return dice2;
    }

    public void setDice2(int dice2) {
        this.dice2 = dice2;
    }

 //   public boolean isPlayerHasWon() {
//        return (this.dice1 + this.dice2== 7);
//    }
//
//    public void setPlayerHasWon(boolean playerHasWon) {
//        this.playerHasWon = playerHasWon;
//    }//

//    public boolean playerHasWon(int dice1, int dice2) {
//        return this.dice1 + this.dice2 == 7;
//    }
//
//    public boolean isPlayerHasWon() {
//        return playerHasWon;
//    }
}
