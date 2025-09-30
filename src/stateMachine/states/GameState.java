package stateMachine.states;

import controllers.SpaceShipController;
import core.CollisionController;
import core.Updater;
import entities.Bullet;
import entities.Enemy;
import entities.SpaceShip;
import enums.EnemyType;
import gameplay.WaveController;
import params.BulletsParam;
import pools.IPool;
import stateMachine.IState;
import stateMachine.StateMachine;
import views.MainWindow;
import views.SpacePanel;

public class GameState implements IState {
    private final MainWindow mainWindow;
    private final Updater updater;
    private final SpaceShip ship;
    private final WaveController waveController;
    private final CollisionController collisionController;
    private final SpacePanel spacePanel;
    private final IPool<Bullet, BulletsParam> bulletPool;
    private final IPool<Enemy, EnemyType> enemyPool;

    public GameState(MainWindow mainWindow, Updater updater, SpaceShip ship, WaveController waveController, CollisionController collisionController, SpacePanel spacePanel, IPool<Bullet, BulletsParam> bulletPool, IPool<Enemy, EnemyType> enemyPool) {
        this.mainWindow = mainWindow;
        this.updater = updater;
        this.ship = ship;
        this.waveController = waveController;
        this.collisionController = collisionController;
        this.spacePanel = spacePanel;
        this.bulletPool = bulletPool;
        this.enemyPool = enemyPool;
    }

    private void startGame() {
        ship.reset();
        waveController.reset();
        bulletPool.deactivateAll();
        enemyPool.deactivateAll();
    }

    @Override
    public void enter() {
        startGame();
        mainWindow.showPanel("game");
        updater.add(ship.getController());
        updater.add(waveController);
        updater.add(collisionController);
        ship.getController().subscribe();
        spacePanel.subscribe();
    }

    @Override
    public void exit() {
        updater.remove(ship.getController());
        updater.remove(waveController);
        updater.remove(collisionController);
        ship.getController().unsubscribe();
        spacePanel.unsubscribe();
    }
}
