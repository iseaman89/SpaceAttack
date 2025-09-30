package factories;

import core.CollisionController;
import core.Updater;
import entities.Enemy;
import enums.EnemyType;
import events.EventBus;
import models.SpaceShipModel;
import spawners.BulletSpawner;

public class EnemyFactory implements IFactory<Enemy, EnemyType>{
    private final EventBus eventBus;
    private final Updater updater;
    private final SpaceShipModel shipModel;
    private final CollisionController collisionController;
    private final BulletSpawner bulletSpawner;

    public EnemyFactory(EventBus eventBus, Updater updater, SpaceShipModel shipModel, CollisionController collisionController, BulletSpawner bulletSpawner) {
        this.eventBus = eventBus;
        this.updater = updater;
        this.shipModel = shipModel;
        this.collisionController = collisionController;
        this.bulletSpawner = bulletSpawner;
    }

    @Override
    public Enemy create(EnemyType type) {
        return new Enemy(eventBus, updater, type, shipModel, collisionController, bulletSpawner);
    }
}
