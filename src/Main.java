import controllers.*;
import core.CollisionController;
import core.Updater;
import entities.SpaceShip;
import events.EventBus;
import factories.*;
import gameplay.WaveController;
import models.SpaceModel;
import models.SpaceShipModel;
import pools.BulletPool;
import pools.EnemyPool;
import pools.ExplosionPool;
import spawners.BulletSpawner;
import spawners.EnemySpawner;
import spawners.ExplosionSpawner;
import stateMachine.StateMachine;
import stateMachine.states.GameOverState;
import stateMachine.states.GameState;
import stateMachine.states.MainMenuState;
import ui.animation.Animation;
import ui.animation.ShipAnimation;
import views.*;

import javax.swing.*;

public class Main {
    private static long lastTime = System.nanoTime();

    public static void main(String[] args) {
        var updater = new Updater();
        var eventBus = new EventBus();
        var collisionController = new CollisionController();
        var stateMachine = new StateMachine();

        var engineAnimation = getEngineAnimation();
        var explosionAnimation = getExplosionAnimation();
        var explosionPool = new ExplosionPool(explosionAnimation);
        var explosionSpawner = new ExplosionSpawner(explosionPool);

        var shipModel = new SpaceShipModel(500, 450, 200, 5, 0);
        var spaceView = new BaseView("/space.png", 1024, 640);
        var spaceModel = new SpaceModel(0, 0, 30);
        var spaceModel2 = new SpaceModel(0, -640, 30);
        var bulletViewFactory = new BulletViewFactory();
        var bulletFactory = new BulletFactory(eventBus, updater, collisionController, bulletViewFactory);
        var bulletPool = new BulletPool(bulletFactory);
        var bulletSpawner = new BulletSpawner(bulletPool, updater, collisionController);
        var enemyControllerFactory = new EnemyControllerFactory(eventBus, updater, shipModel, bulletSpawner);
        var enemyViewFactory = new EnemyViewFactory();
        var enemyFactory = new EnemyFactory(eventBus, collisionController, explosionSpawner, enemyControllerFactory, enemyViewFactory);
        var enemyPool = new EnemyPool(enemyFactory);
        var enemySpawner = new EnemySpawner(enemyPool, updater, collisionController);
        var shipAnimation = new ShipAnimation();
        var gameOverPanel = new GameOverPanel(new MainFont(18).getFont());
        var spacePanel = new SpacePanel(shipAnimation, shipModel, spaceView, spaceModel, spaceModel2, bulletPool, enemyPool, eventBus, gameOverPanel, engineAnimation, explosionPool);
        var mainMenuPanel = new MainMenuPanel(spaceView, spaceModel, spaceModel2);
        var mainWindow = new MainWindow(spacePanel, mainMenuPanel);
        var inputController = new InputController(spacePanel);
        var shipController = new SpaceShipController(shipModel, shipAnimation, eventBus, inputController, bulletSpawner, mainWindow, engineAnimation);
        var spaceController = new SpaceController(spaceModel, spaceModel2, spacePanel);
        var ship = new SpaceShip(shipModel, shipController, shipAnimation, collisionController, stateMachine, explosionSpawner);

        var waveController = new WaveController(enemySpawner, enemyPool, stateMachine);

        var mainMenuState = new MainMenuState(mainWindow, mainMenuPanel, stateMachine);
        var gameState = new GameState(mainWindow, updater, ship, waveController, collisionController, spacePanel, bulletPool, enemyPool, explosionSpawner);
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

    private static Animation getEngineAnimation() {
        BaseView[] engineFrames = new BaseView[]{
                new BaseView("/fire1.png", 8, 16),
                new BaseView("/fire2.png", 8, 16)
        };

        return new Animation(engineFrames, 0.2, true);
    }

    private static Animation getExplosionAnimation() {
        BaseView[] explosionFrames = new BaseView[]{
                new BaseView("/boom1.png", 8, 8),
                new BaseView("/boom2.png", 32, 32),
                new BaseView("/boom3.png", 24, 24),
                new BaseView("/boom4.png", 24, 24)
        };

        return new Animation(explosionFrames, 0.2, true);
    }
}
