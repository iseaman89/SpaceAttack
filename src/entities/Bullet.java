package entities;

import controllers.BulletController;
import core.CollisionController;
import core.ICollidable;
import core.Updater;
import enums.BulletType;
import enums.CollisionsType;
import events.EventBus;
import models.BulletModel;
import views.BaseView;

import java.awt.*;

public class Bullet implements ICollidable {
    private final BulletModel bulletModel;
    private final BulletController bulletController;
    private final BaseView bulletView;
    private final CollisionController collisionController;
    private final CollisionsType collisionsType;
    private final BulletType bulletType;

    public Bullet(EventBus eventBus, Updater updater, int speed, CollisionController collisionController, CollisionsType collisionsType, BulletType bulletType){
        bulletModel = new BulletModel(speed);
        this.collisionController = collisionController;
        this.bulletType = bulletType;
        bulletController = new BulletController(this, eventBus, updater);
        bulletView = createView();
        this.collisionsType = collisionsType;
    }

    private BaseView createView() {
        switch (bulletType) {
            case SHIP -> {
                return new BaseView("/bullet.png", 16, 24);
            }
            case ENEMY -> {
                return new BaseView("/bullet_enemy.png", 8, 16);
            }
            case BOSS -> {
                return new BaseView("/bullet_boss.png", 32, 32);
            }
        }
        return null;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)bulletModel.getX(), (int)bulletModel.getY(), bulletView.getWidth(), bulletView.getHeight());
    }

    @Override
    public CollisionsType getType() {
        return collisionsType;
    }

    @Override
    public void onCollisionEnter(ICollidable other) {
        if (other.getType() == CollisionsType.SHIP && bulletType == BulletType.SHIP) return;
        if (other.getType() == CollisionsType.ENEMY_SHIP && bulletType == BulletType.ENEMY) return;
        deactivate();
    }

    public void deactivate() {
        bulletController.deactivate();
        collisionController.removeCollidable(this);
    }

    public BulletModel getBulletModel() {
        return bulletModel;
    }

    public BulletController getBulletController() {
        return bulletController;
    }

    public BaseView getView() {
        return bulletView;
    }

    public BulletType getBulletType() {
        return bulletType;
    }
}
