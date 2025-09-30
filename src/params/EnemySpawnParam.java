package params;

import enums.EnemyType;

public record EnemySpawnParam(EnemyType type, double attackTime, int hp, int speed, int x, int y) {
}
