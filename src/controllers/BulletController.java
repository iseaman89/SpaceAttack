package controllers;

import core.IUpdateListener;
import core.Updater;
import entities.Bullet;
import enums.BulletType;
import events.EventBus;
import events.RepaintEvent;

public class BulletController implements IUpdateListener {
    private final Bullet bullet;
    private final EventBus eventBus;
    private final Updater updater;
    private double direction;


    public BulletController(Bullet bullet, EventBus eventBus, Updater updater) {
        this.updater = updater;
        this.bullet = bullet;
        this.eventBus = eventBus;
    }

    @Override
    public void update(double deltaTime) {
        if (!bullet.getBulletModel().isActive()) return;
        if ((bullet.getBulletModel().getY() >= -20 && bullet.getBulletType() == BulletType.SHIP) || (bullet.getBulletModel().getY() <= 660 && bullet.getBulletType() == BulletType.ENEMY)) move(deltaTime);
        else deactivate();
    }

    public void deactivate() {
        bullet.getBulletModel().setActive(false);
        updater.remove(this);
    }

    private void move(double deltaTime){
        bullet.getBulletModel().setY(bullet.getBulletModel().getY() + bullet.getBulletModel().getSpeed() * deltaTime * direction);
        eventBus.publish(new RepaintEvent());
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }
}
