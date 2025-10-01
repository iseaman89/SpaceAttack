package params;

import entities.Enemy;
import enums.EnemyType;

public record EnemyControllerParam(EnemyType enemyType, Enemy enemy) {}
