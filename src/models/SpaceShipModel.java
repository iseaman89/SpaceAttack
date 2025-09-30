package models;

public class SpaceShipModel extends BaseModel {
    private int damage;
    private int hp;
    private int score;

    public SpaceShipModel(double x, double y, int speed, int hp, int score) {
        super(x, y, speed);
        this.hp = hp;
        this.score = score;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
