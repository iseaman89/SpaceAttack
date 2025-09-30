package pools;

import entities.Enemy;
import enums.EnemyType;
import factories.IFactory;

import java.util.ArrayList;
import java.util.List;

public class EnemyPool implements IPool<Enemy, EnemyType>{
    private final IFactory<Enemy, EnemyType> enemyFactory;
    private final List<Enemy> enemies = new ArrayList<>();

    public EnemyPool(IFactory<Enemy, EnemyType> enemyFactory) {
        this.enemyFactory = enemyFactory;
    }

    private Enemy instantiateEnemy(EnemyType type){
        var newEnemy = enemyFactory.create(type);
        enemies.add(newEnemy);
        newEnemy.getModel().setActive(false);
        return newEnemy;
    }

    @Override
    public Enemy get(EnemyType type) {
        return enemies.stream().filter(e -> !e.getModel().isActive() && e.getModel().getType() == type)
                .findFirst().orElse(instantiateEnemy(type));
    }

    @Override
    public void deactivate(Enemy enemy) {
        enemy.getModel().setActive(false);
    }

    @Override
    public void deactivateAll() {
        enemies.forEach(Enemy::deactivate);
    }

    @Override
    public List<Enemy> getPooledObj() {
        return enemies;
    }
}
