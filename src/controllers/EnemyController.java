package controllers;

import core.IUpdateListener;
import core.Updater;
import entities.Enemy;
import events.EventBus;
import models.SpaceShipModel;

public abstract class EnemyController implements IUpdateListener {
    protected final Enemy enemy;
    protected final EventBus eventBus;
    protected final Updater updater;
    protected final SpaceShipModel shipModel;
    protected double attackTime;

    public EnemyController(Enemy enemy, EventBus eventBus, Updater updater, SpaceShipModel shipModel) {
        this.enemy = enemy;
        this.eventBus = eventBus;
        this.updater = updater;
        this.shipModel = shipModel;
    }

    public void deactivate() {
        enemy.getModel().setActive(false);
        updater.remove(this);
    }

    public abstract void reset();

    public double getAttackTime() {
        return attackTime;
    }

    public void setAttackTime(double attackTime) {
        this.attackTime = attackTime;
    }
}
