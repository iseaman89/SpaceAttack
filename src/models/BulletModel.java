package models;

public class BulletModel extends BaseModel{
    private boolean active;
    private int damage;

    public BulletModel(int speed) {
        super(0, 0, speed);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
