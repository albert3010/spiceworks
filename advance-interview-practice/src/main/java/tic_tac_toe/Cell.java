package tic_tac_toe;

public class Cell {
    int id;
    String turnId;

    public Cell(int id) {
        this.id = id;
        this.turnId = "_";
    }

    public void setTurnId(String turnId) {
        this.turnId = turnId;
    }

    public boolean canMakeTurn() {
        return this.turnId == "_";
    }

    public String getTurnId(){
        return this.turnId;
    }
}
