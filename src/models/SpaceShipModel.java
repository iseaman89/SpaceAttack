package models;

public class SpaceShipModel extends BaseModel {
    private int hp;
    private int score;


    public SpaceShipModel(double x, double y, int speed, int hp, int score) {
        super(x, y, speed);
        this.hp = hp;
        this.score = score;
    }

    public int getHp() { return hp; }
    public int getScore() { return score; }

    public void setHp(int hp) { this.hp = hp; }
    public void setScore(int score) { this.score = score; }
}
