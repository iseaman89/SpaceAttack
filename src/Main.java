import controllers.*;
import core.CollisionController;
import core.Updater;
import entities.SpaceShip;
import events.EventBus;
import factories.BulletFactory;
import factories.EnemyFactory;
import gameplay.WaveController;
import models.SpaceModel;
import models.SpaceShipModel;
import pools.BulletPool;
import pools.EnemyPool;
import spawners.BulletSpawner;
import spawners.EnemySpawner;
import stateMachine.StateMachine;
import stateMachine.states.GameOverState;
import stateMachine.states.GameState;
import stateMachine.states.MainMenuState;
import ui.Animation;
import ui.ShipAnimation;
import views.*;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Main {
    private static long lastTime = System.nanoTime();

    public static void main(String[] args) {
        var updater = new Updater();
        var eventBus = new EventBus();
        var collisionController = new CollisionController();
        var stateMachine = new StateMachine();

        BaseView[] engineFrames = new BaseView[]{
                new BaseView("/fire1.png", 8, 16),
                new BaseView("/fire2.png", 8, 16)
        };

        Animation engineAnimation = new Animation(engineFrames, 0.2, true);

        var shipModel = new SpaceShipModel(500, 450, 200, 5, 0);
        var spaceView = new BaseView("/space.png", 1024, 640);
        var spaceModel = new SpaceModel(0, 0, 30);
        var spaceModel2 = new SpaceModel(0, -640, 30);
        var bulletFactory = new BulletFactory(eventBus, updater, collisionController);
        var bulletPool = new BulletPool(bulletFactory);
        var bulletSpawner = new BulletSpawner(bulletPool, shipModel, updater, collisionController);
        var enemyFactory = new EnemyFactory(eventBus, updater, shipModel, collisionController, bulletSpawner);
        var enemyPool = new EnemyPool(enemyFactory);
        var enemySpawner = new EnemySpawner(enemyPool, updater, collisionController);
        var shipAnimation = new ShipAnimation();
        var gameOverPanel = new GameOverPanel(new MainFont(18).getFont());
        var spacePanel = new SpacePanel(shipAnimation, shipModel, spaceView, spaceModel, spaceModel2, bulletPool, enemyPool, eventBus, gameOverPanel, engineAnimation);
        var mainMenuPanel = new MainMenuPanel(spaceView, spaceModel, spaceModel2);
        var mainWindow = new MainWindow(spacePanel, mainMenuPanel);
        var inputController = new InputController(spacePanel);
        var shipController = new SpaceShipController(shipModel, shipAnimation, eventBus, inputController, bulletSpawner, mainWindow, engineAnimation);
        var spaceController = new SpaceController(spaceModel, spaceModel2, spacePanel);
        var ship = new SpaceShip(shipModel, shipController, shipAnimation, collisionController, stateMachine);

        var waveController = new WaveController(enemySpawner, enemyPool, stateMachine);

        var mainMenuState = new MainMenuState(mainWindow, mainMenuPanel, stateMachine);
        var gameState = new GameState(mainWindow, updater, ship, waveController, collisionController, spacePanel, bulletPool, enemyPool);
        var gameOverState = new GameOverState(spacePanel, stateMachine);


        stateMachine.register(MainMenuState.class, mainMenuState);
        stateMachine.register(GameState.class, gameState);
        stateMachine.register(GameOverState.class, gameOverState);

        stateMachine.setState(MainMenuState.class);

        updater.add(spaceController);

        var timer = new Timer(16, e -> {
           var now = System.nanoTime();
           var deltaTime = (now -lastTime) / 1_000_000_000.0;
           lastTime = now;

           updater.update(deltaTime);
        });

        timer.start();
    }
}
