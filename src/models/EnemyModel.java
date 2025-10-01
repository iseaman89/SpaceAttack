package models;

import enums.EnemyType;

public class EnemyModel extends BaseModel {
    private final EnemyType type;
    private int hp;

    public EnemyModel(EnemyType type) {
        super(0,0, 0);
        this.type = type;
    }

    public EnemyType getType() { return type; }
    public int getHp() { return hp; }

    public void setHp(int hp) { this.hp = hp; }
}
