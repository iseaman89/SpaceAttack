package controllers;

import core.Updater;
import entities.Enemy;
import enums.BulletType;
import enums.CollisionsType;
import events.EventBus;
import models.SpaceShipModel;
import params.BulletsParam;
import spawners.BulletSpawner;


public class EnemyShooterController extends EnemyController{
    private final BulletSpawner bulletSpawner;

    private double timer = 0;
    private double lastShootTime = 0;
    private double startTime = 0;

    public EnemyShooterController(Enemy enemy, EventBus eventBus, Updater updater, SpaceShipModel shipModel, BulletSpawner bulletSpawner) {
        super(enemy, eventBus, updater, shipModel);

        this.bulletSpawner = bulletSpawner;
    }

    private void shoot(){
        var cooldown = 1.5;
        if (startTime - lastShootTime >= cooldown){
            bulletSpawner.spawn(new BulletsParam(200, CollisionsType.ENEMY_BULLET, BulletType.ENEMY, enemy.getModel().getX() + 16, enemy.getModel().getY() + 32, 1));
            lastShootTime = startTime;
        }
    }

    @Override
    public void reset() {
        timer = 0;
        lastShootTime = 0;
    }

    @Override
    public void update(double deltaTime) {
        timer += deltaTime;
        startTime += deltaTime;

        if (timer >= attackTime) shoot();
    }
}
