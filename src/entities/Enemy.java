package entities;

import controllers.*;
import core.CollisionController;
import core.ICollidable;
import core.Updater;
import enums.CollisionsType;
import enums.EnemyType;
import events.EventBus;
import events.ScoreEvent;
import models.EnemyModel;
import models.SpaceShipModel;
import spawners.BulletSpawner;
import views.BaseView;

import java.awt.*;

public class Enemy implements ICollidable {
    private final EnemyModel model;
    private final EnemyController controller;
    private final BaseView view;
    private final EventBus eventBus;
    private final CollisionController collisionController;
    private final CollisionsType collisionsType;
    private final BulletSpawner bulletSpawner;

    public Enemy(EventBus eventBus, Updater updater, EnemyType type, SpaceShipModel shipModel, CollisionController collisionController, BulletSpawner bulletSpawner) {
        model = new EnemyModel(type);
        this.eventBus = eventBus;
        this.collisionController = collisionController;
        this.bulletSpawner = bulletSpawner;
        controller = createController(type, eventBus, updater, shipModel);
        this.view = createView(type);
        collisionsType = CollisionsType.ENEMY_SHIP;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)model.getX(), (int)model.getY(), view.getWidth(), view.getHeight());
    }

    @Override
    public CollisionsType getType() {
        return collisionsType;
    }

    @Override
    public void onCollisionEnter(ICollidable other) {
        if (other.getType() == CollisionsType.ENEMY_BULLET) return;
        if (other.getType() == CollisionsType.SHIP) deactivate();
        if (other.getType() == CollisionsType.BULLET){
            model.setHp(model.getHp() - 1);
        }
        if (model.getHp() <= 0 && model.isActive()) {
            eventBus.publish(new ScoreEvent());
            deactivate();
        }
    }

    public EnemyModel getModel() {
        return model;
    }

    public EnemyController getController() {
        return controller;
    }

    public BaseView getView() {
        return view;
    }

    public void deactivate(){
        controller.deactivate();
        collisionController.removeCollidable(this);
    }

    private EnemyController createController(EnemyType type, EventBus eventBus, Updater updater, SpaceShipModel shipModel) {
        switch (type){
            case BASIC -> {
                return new EnemyBasicController(this, eventBus, updater, shipModel);
            }
            case SHOOTER -> {
                return new EnemyShooterController(this, eventBus, updater, shipModel, bulletSpawner);
            }
            case MOVER -> {
                return new EnemyMoverController(this, eventBus, updater, shipModel, bulletSpawner);
            }
            case BOSS -> {
                return new EnemyBossController(this, eventBus, updater, shipModel, bulletSpawner);
            }
        }
        return null;
    }

    private BaseView createView(EnemyType type) {
        switch (type){
            case BASIC -> {
                return new BaseView("/enemy1.png", 40, 40);
            }
            case SHOOTER -> {
                return new BaseView("/enemy2.png", 40, 40);
            }
            case MOVER -> {
                return new BaseView("/enemy3.png", 40, 40);
            }
            case BOSS -> {
                return new BaseView("/boss.png", 120, 120);
            }
        }
        return null;
    }
}
