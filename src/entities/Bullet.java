package entities;

import controllers.BulletController;
import core.CollisionController;
import core.ICollidable;
import core.Updater;
import enums.BulletType;
import enums.CollisionsType;
import events.EventBus;
import factories.IFactory;
import models.BulletModel;
import views.BaseView;

import java.awt.*;

public class Bullet implements ICollidable {
    private final BulletModel model;
    private final BulletController controller;
    private final BaseView view;
    private final CollisionController collisionController;
    private final CollisionsType collisionsType;
    private final BulletType bulletType;

    public Bullet(EventBus eventBus, Updater updater, int speed, CollisionController collisionController, IFactory<BaseView, BulletType> viewFactory, CollisionsType collisionsType, BulletType bulletType){
        model = new BulletModel(speed);
        this.collisionController = collisionController;
        this.bulletType = bulletType;
        controller = new BulletController(this, eventBus, updater);
        view = viewFactory.create(bulletType);
        this.collisionsType = collisionsType;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) model.getX(), (int) model.getY(), view.getWidth(), view.getHeight());
    }

    @Override
    public CollisionsType getType() {
        return collisionsType;
    }

    @Override
    public void onCollisionEnter(ICollidable other) {
        if (other.getType() == CollisionsType.SHIP && bulletType == BulletType.SHIP) return;
        if (other.getType() == CollisionsType.ENEMY_SHIP && bulletType == BulletType.ENEMY) return;
        if (other.getType() == CollisionsType.ENEMY_SHIP && bulletType == BulletType.BOSS) return;
        if (other.getType() == CollisionsType.BULLET || other.getType() == CollisionsType.ENEMY_BULLET) return;

        deactivate();
    }

    public void deactivate() {
        controller.deactivate();
        collisionController.removeCollidable(this);
    }

    public BulletModel getModel() {
        return model;
    }

    public BulletController getController() {
        return controller;
    }

    public BaseView getView() {
        return view;
    }

    public BulletType getBulletType() {
        return bulletType;
    }
}
