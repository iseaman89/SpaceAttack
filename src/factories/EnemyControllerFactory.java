package factories;

import controllers.*;
import core.Updater;
import events.EventBus;
import models.SpaceShipModel;
import params.EnemyControllerParam;
import spawners.BulletSpawner;

public class EnemyControllerFactory implements IFactory<EnemyController, EnemyControllerParam>{
    private final EventBus eventBus;
    private final Updater updater;
    private final SpaceShipModel shipModel;
    private final BulletSpawner bulletSpawner;

    public EnemyControllerFactory(EventBus eventBus, Updater updater, SpaceShipModel shipModel, BulletSpawner bulletSpawner) {
        this.eventBus = eventBus;
        this.updater = updater;
        this.shipModel = shipModel;
        this.bulletSpawner = bulletSpawner;
    }

    @Override
    public EnemyController create(EnemyControllerParam param) {
        switch (param.enemyType()){
            case BASIC -> {
                return new EnemyBasicController(param.enemy(), eventBus, updater, shipModel);
            }
            case SHOOTER -> {
                return new EnemyShooterController(param.enemy(), eventBus, updater, shipModel, bulletSpawner);
            }
            case MOVER -> {
                return new EnemyMoverController(param.enemy(), eventBus, updater, shipModel, bulletSpawner);
            }
            case BOSS -> {
                return new EnemyBossController(param.enemy(), eventBus, updater, shipModel, bulletSpawner);
            }
        }
        return null;
    }
}
