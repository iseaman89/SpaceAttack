package controllers;

import core.IUpdateListener;
import core.Updater;
import entities.Bullet;
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

    private void move(double deltaTime){
        bullet.getModel().setY(bullet.getModel().getY() + bullet.getModel().getSpeed() * deltaTime * direction);
        eventBus.publish(new RepaintEvent());
    }

    @Override
    public void update(double deltaTime) {
        if (!bullet.getModel().isActive()) return;
        if (bullet.getBulletType().isWithinBounds(bullet.getModel().getY())) move(deltaTime);
        else deactivate();
    }

    public void deactivate() {
        bullet.getModel().setActive(false);
        updater.remove(this);
    }

    public void setDirection(double direction) { this.direction = direction; }
}
