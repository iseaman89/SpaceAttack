package models;

import enums.EnemyType;

public class EnemyModel extends BaseModel {
    private final EnemyType type;
    private int hp;
    private int damage;
    private boolean active;

    public EnemyModel(EnemyType type) {
        super(0,0, 0);
        this.type = type;
    }

    public EnemyType getType() {
        return type;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
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
