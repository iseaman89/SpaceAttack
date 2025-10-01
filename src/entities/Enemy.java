package entities;

import controllers.*;
import core.CollisionController;
import core.ICollidable;
import enums.CollisionsType;
import enums.EnemyType;
import events.EventBus;
import events.ScoreEvent;
import factories.IFactory;
import models.EnemyModel;
import params.EnemyControllerParam;
import spawners.ExplosionSpawner;
import views.BaseView;

import java.awt.*;

public class Enemy implements ICollidable {
    private final EnemyModel model;
    private final EnemyController controller;
    private final BaseView view;
    private final EventBus eventBus;
    private final CollisionController collisionController;
    private final CollisionsType collisionsType;
    private final ExplosionSpawner explosionSpawner;

    public Enemy(IFactory<EnemyController, EnemyControllerParam>  controllerFactory,
                 EnemyType type, IFactory<BaseView, EnemyType> viewFactory, CollisionController collisionController,
                 EventBus eventBus, ExplosionSpawner explosionSpawner) {
        model = new EnemyModel(type);
        this.collisionController = collisionController;
        this.eventBus = eventBus;
        this.explosionSpawner = explosionSpawner;
        controller = controllerFactory.create(new EnemyControllerParam(type, this));
        this.view = viewFactory.create(type);
        collisionsType = CollisionsType.ENEMY_SHIP;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)model.getX(), (int)model.getY(), view.getWidth(), view.getHeight());
    }

    @Override
    public CollisionsType getType() { return collisionsType; }

    @Override
    public void onCollisionEnter(ICollidable other) {
        if (other.getType() == CollisionsType.ENEMY_BULLET) return;
        if (other.getType() == CollisionsType.SHIP) deactivate();
        if (other.getType() == CollisionsType.BULLET){
            model.setHp(model.getHp() - 1);
        }
        if (model.getHp() <= 0 && model.isActive()) {
            explosionSpawner.spawn(model.getX() + 8, model.getY() + 8, (e) -> {});
            eventBus.publish(new ScoreEvent());
            deactivate();
        }
    }

    public void deactivate(){
        controller.deactivate();
        collisionController.removeCollidable(this);
    }

    public EnemyModel getModel() { return model; }

    public EnemyController getController() { return controller; }

    public BaseView getView() { return view; }
}
