package controllers;

import core.Updater;
import entities.Enemy;
import enums.BulletType;
import enums.CollisionsType;
import events.EventBus;
import events.RepaintEvent;
import models.SpaceShipModel;
import params.BulletsParam;
import spawners.BulletSpawner;

public class EnemyBossController extends EnemyController{
    private final BulletSpawner bulletSpawner;
    private double timer = 0;
    private double lastShootTime = 0;
    private double startTime = 0;
    private double direction = 1;

    public EnemyBossController(Enemy enemy, EventBus eventBus, Updater updater, SpaceShipModel shipModel, BulletSpawner bulletSpawner) {
        super(enemy, eventBus, updater, shipModel);
        this.bulletSpawner = bulletSpawner;
    }

    private void shoot(){
        var cooldown = .7;
        if (startTime - lastShootTime >= cooldown){
            bulletSpawner.spawn(new BulletsParam(200, CollisionsType.ENEMY_BULLET, BulletType.BOSS, enemy.getModel().getX() + 32, enemy.getModel().getY() + 74, 1));
            lastShootTime = startTime;
        }
    }

    private void move(double deltaTime){
        if (enemy.getModel().getX() <= 0) direction = 1;
        if (enemy.getModel().getX() >= 900) direction = -1;
        enemy.getModel().setX(enemy.getModel().getX() + enemy.getModel().getSpeed() * deltaTime * direction);
        eventBus.publish(new RepaintEvent());
    }

    @Override
    public void reset() {
        timer = 0;
        lastShootTime = 0;
        direction = 1;
    }

    @Override
    public void update(double deltaTime) {
        timer += deltaTime;
        startTime += deltaTime;

        if (timer >= attackTime) {
            shoot();
            move(deltaTime);
        }
    }
}
