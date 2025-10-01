package controllers;

import core.Updater;
import entities.Enemy;
import events.EventBus;
import events.RepaintEvent;
import models.SpaceShipModel;

public class EnemyBasicController extends EnemyController{
    private double dirX;
    private double dirY;
    private double timer = 0;
    private boolean inAttack = false;

    public EnemyBasicController(Enemy enemy, EventBus eventBus, Updater updater, SpaceShipModel shipModel) {
        super(enemy, eventBus, updater, shipModel);
    }

    private void move(double deltaTime){
        if (enemy.getModel().getY() >= 680) deactivate();
        fixDirection();
        enemy.getModel().setY(enemy.getModel().getY() + (dirY * enemy.getModel().getSpeed() * deltaTime));
        enemy.getModel().setX(enemy.getModel().getX() + (dirX * enemy.getModel().getSpeed() * deltaTime));
        eventBus.publish(new RepaintEvent());
    }

    private void fixDirection() {
        if (inAttack) return;
        var dx = shipModel.getX() - enemy.getModel().getX();
        var dy = shipModel.getY() - enemy.getModel().getY();
        var length = Math.sqrt(dx*dx + dy*dy);
        dirX = dx / length;
        dirY = dy / length;
        inAttack = true;
    }

    @Override
    public void update(double deltaTime){
        timer += deltaTime;
        if (timer >= attackTime) move(deltaTime);
    }

    @Override
    public void reset() {
        inAttack = false;
        timer = 0;
    }
}
