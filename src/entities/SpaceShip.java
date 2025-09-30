package entities;

import controllers.SpaceShipController;
import core.CollisionController;
import core.ICollidable;
import enums.CollisionsType;
import models.SpaceShipModel;
import stateMachine.StateMachine;
import stateMachine.states.GameOverState;
import ui.ShipAnimation;

import java.awt.*;

public class SpaceShip implements ICollidable {
    private final SpaceShipModel model;
    private final SpaceShipController controller;
    private final ShipAnimation shipAnimation;
    private final CollisionController collisionController;
    private final StateMachine stateMachine;
    private final CollisionsType collisionsType;

    public SpaceShip(SpaceShipModel model, SpaceShipController controller, ShipAnimation shipAnimation, CollisionController collisionController, StateMachine stateMachine) {
        this.model = model;
        this.controller = controller;
        this.shipAnimation = shipAnimation;
        this.collisionController = collisionController;
        this.stateMachine = stateMachine;
        collisionsType = CollisionsType.SHIP;
    }

    public void reset(){
        model.setX(512);
        model.setY(450);
        model.setScore(0);
        model.setHp(5);
        collisionController.addCollidable(this);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)model.getX(), (int)model.getY(), shipAnimation.getWidth(), shipAnimation.getHeight());
    }

    @Override
    public CollisionsType getType() {
        return collisionsType;
    }

    @Override
    public void onCollisionEnter(ICollidable other) {
        if (other.getType() == CollisionsType.BULLET) return;
        if (other.getType() == CollisionsType.ENEMY_BULLET || other.getType() == CollisionsType.ENEMY_SHIP){
            model.setHp(model.getHp() - 1);
        }
        if (model.getHp() <= 0) deactivate();
    }

    public SpaceShipModel getModel() {
        return model;
    }

    public SpaceShipController getController() {
        return controller;
    }

    private void deactivate(){
        stateMachine.setState(GameOverState.class);
        collisionController.removeCollidable(this);
    }
}
