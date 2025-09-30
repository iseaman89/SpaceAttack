package spawners;

import core.CollisionController;
import core.Updater;
import entities.Enemy;
import enums.EnemyType;
import params.EnemySpawnParam;
import pools.IPool;

public class EnemySpawner {
    private final IPool<Enemy, EnemyType> enemyPool;
    private final Updater updater;
    private final CollisionController collisionController;

    public EnemySpawner(IPool<Enemy, EnemyType> enemyPool, Updater updater, CollisionController collisionController) {
        this.enemyPool = enemyPool;
        this.updater = updater;
        this.collisionController = collisionController;
    }

    public void spawn(EnemySpawnParam param){
        var enemy = enemyPool.get(param.type());
        enemy.getModel().setY(param.y());
        enemy.getModel().setX(param.x());
        enemy.getModel().setSpeed(param.speed());
        enemy.getModel().setActive(true);
        enemy.getModel().setHp(param.hp());
        enemy.getController().setAttackTime(param.attackTime());
        enemy.getController().reset();
        updater.add(enemy.getController());
        collisionController.addCollidable(enemy);
    }
}
