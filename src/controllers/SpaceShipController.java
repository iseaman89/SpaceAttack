package controllers;

import core.IUpdateListener;
import enums.BulletType;
import enums.CollisionsType;
import enums.SpaceShipAnimationState;
import events.EventBus;
import events.RepaintEvent;
import events.ScoreEvent;
import models.SpaceShipModel;
import params.BulletsParam;
import spawners.BulletSpawner;
import ui.animation.Animation;
import ui.animation.ShipAnimation;
import views.MainWindow;

import java.util.function.Consumer;

public class SpaceShipController implements IUpdateListener {
    private final SpaceShipModel shipModel;
    private final ShipAnimation shipAnimation;
    private final EventBus eventBus;
    private final InputController inputController;
    private final BulletSpawner bulletSpawner;
    private final MainWindow mainWindow;
    private final Animation engineAnimation;
    private final Consumer<ScoreEvent> scoreHandler = e -> updateScore();

    private double lastShootTime = 0;
    private double startTime = 0;

    public SpaceShipController(SpaceShipModel shipModel, ShipAnimation shipAnimation, EventBus eventBus, InputController inputController, BulletSpawner bulletSpawner, MainWindow mainWindow, Animation engineAnimation) {
        this.shipModel = shipModel;
        this.shipAnimation = shipAnimation;
        this.eventBus = eventBus;
        this.inputController = inputController;
        this.bulletSpawner = bulletSpawner;
        this.mainWindow = mainWindow;
        this.engineAnimation = engineAnimation;
    }

    private void moveLeft(double deltaTime){
        var newX = shipModel.getX() - shipModel.getSpeed() * deltaTime;
        shipAnimation.setCurrentState(SpaceShipAnimationState.LEFT);
        if (newX <= 0) newX = 0;
        shipModel.setX(newX);
        eventBus.publish(new RepaintEvent());
    }

    private void moveRight(double deltaTime){
        var newX = shipModel.getX() + shipModel.getSpeed() * deltaTime;
        shipAnimation.setCurrentState(SpaceShipAnimationState.RIGHT);
        if (newX + shipAnimation.getWidth() > mainWindow.getWidth()) newX = mainWindow.getWidth() - shipAnimation.getWidth();
        shipModel.setX(newX);
        eventBus.publish(new RepaintEvent());
    }

    private void moveUp(double deltaTime){
        var newY = shipModel.getY() - shipModel.getSpeed() * deltaTime;
        if (newY <= 0) newY = 0;
        shipModel.setY(newY);
        eventBus.publish(new RepaintEvent());
    }

    private void moveDown(double deltaTime){
        var newY = shipModel.getY() + shipModel.getSpeed() * deltaTime;
        if (newY + shipAnimation.getHeight() > mainWindow.getHeight()) newY = mainWindow.getHeight() - shipAnimation.getHeight();
        shipModel.setY(newY);
        eventBus.publish(new RepaintEvent());
    }

    private void shoot(){
        if (startTime - lastShootTime >= .7){
            bulletSpawner.spawn(new BulletsParam(250, CollisionsType.BULLET, BulletType.SHIP, shipModel.getX(), shipModel.getY(), -1));
            bulletSpawner.spawn(new BulletsParam(250, CollisionsType.BULLET, BulletType.SHIP, shipModel.getX() + 32, shipModel.getY(), -1));
            lastShootTime = startTime;
        }
    }

    private void updateScore() { shipModel.setScore(shipModel.getScore() + 100); }

    @Override
    public void update(double deltaTime) {
        startTime += deltaTime;

        engineAnimation.update(deltaTime);

        if (inputController.isLeftPressed()) moveLeft(deltaTime);
        else if (inputController.isRightPressed()) moveRight(deltaTime);
        else shipAnimation.setCurrentState(SpaceShipAnimationState.IDLE);
        if (inputController.isUpPressed()) moveUp(deltaTime);
        if (inputController.isDownPressed()) moveDown(deltaTime);
        shoot();
    }

    public void subscribe() {
        eventBus.subscribe(ScoreEvent.class, scoreHandler);
    }

    public void unsubscribe() {
        eventBus.unsubscribe(ScoreEvent.class, scoreHandler);
    }
}
