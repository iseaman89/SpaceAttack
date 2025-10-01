package entities;

import controllers.SpaceShipController;
import core.CollisionController;
import core.ICollidable;
import enums.CollisionsType;
import models.SpaceShipModel;
import spawners.ExplosionSpawner;
import stateMachine.StateMachine;
import stateMachine.states.GameOverState;
import ui.animation.ShipAnimation;

import java.awt.*;

public class SpaceShip implements ICollidable {
    private final SpaceShipModel model;
    private final SpaceShipController controller;
    private final ShipAnimation shipAnimation;
    private final CollisionController collisionController;
    private final StateMachine stateMachine;
    private final ExplosionSpawner explosionSpawner;
    private final CollisionsType collisionsType;
    private long lastHitTime = 0;

    public SpaceShip(SpaceShipModel model, SpaceShipController controller, ShipAnimation shipAnimation, CollisionController collisionController, StateMachine stateMachine, ExplosionSpawner explosionSpawner) {
        this.model = model;
        this.controller = controller;
        this.shipAnimation = shipAnimation;
        this.collisionController = collisionController;
        this.stateMachine = stateMachine;
        this.explosionSpawner = explosionSpawner;
        collisionsType = CollisionsType.SHIP;
    }

    public void reset(){
        model.setX(490);
        model.setY(450);
        model.setScore(0);
        model.setHp(5);
        model.setActive(true);
        collisionController.addCollidable(this);
    }

    private void deactivate(){
        model.setActive(false);
        explosionSpawner.spawn(model.getX() + 16, model.getY() + 16, explosion -> stateMachine.setState(GameOverState.class));
        collisionController.removeCollidable(this);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)model.getX(), (int)model.getY(), shipAnimation.getWidth(), shipAnimation.getHeight());
    }

    @Override
    public CollisionsType getType() { return collisionsType; }

    @Override
    public void onCollisionEnter(ICollidable other) {
        long now = System.currentTimeMillis();
        long invalidDuration = 200;
        if (now - lastHitTime < invalidDuration) return;

        if (other.getType() == CollisionsType.BULLET) return;
        if (other.getType() == CollisionsType.ENEMY_BULLET || other.getType() == CollisionsType.ENEMY_SHIP) {
            model.setHp(model.getHp() - 1);
            lastHitTime = now;
        }
        if (model.getHp() <= 0) deactivate();
    }

    public SpaceShipModel getModel() { return model; }

    public SpaceShipController getController() { return controller; }
}
