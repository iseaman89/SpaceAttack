package controllers;

import core.Updater;
import entities.Enemy;
import enums.BulletType;
import enums.CollisionsType;
import events.EventBus;
import models.SpaceShipModel;
import params.BulletsParam;
import spawners.BulletSpawner;

public class EnemyBossController extends EnemyController{
    private final BulletSpawner bulletSpawner;
    private double timer = 0;

    public EnemyBossController(Enemy enemy, EventBus eventBus, Updater updater, SpaceShipModel shipModel, BulletSpawner bulletSpawner) {
        super(enemy, eventBus, updater, shipModel);
        this.bulletSpawner = bulletSpawner;
    }

    @Override
    public void reset() {
        timer = 0;
    }

    @Override
    public void update(double deltaTime) {

        timer += deltaTime;

        double amplitude = 350;
        double frequency = 2;
        double centerX = 512;

        var x = centerX + Math.sin(timer * frequency) * amplitude;
        var y = enemy.getModel().getY() + enemy.getModel().getSpeed() * deltaTime;

        bulletSpawner.spawn(new BulletsParam(100, CollisionsType.ENEMY_BULLET, BulletType.BOSS, x, y, 1));
    }
}
