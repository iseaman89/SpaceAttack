package gameplay;

import core.IUpdateListener;
import entities.Enemy;
import enums.EnemyType;
import params.EnemySpawnParam;
import pools.IPool;
import spawners.EnemySpawner;
import stateMachine.StateMachine;
import stateMachine.states.GameOverState;

import java.util.ArrayList;
import java.util.List;

public class WaveController implements IUpdateListener {
    private final EnemySpawner enemySpawner;
    private final IPool<Enemy, EnemyType> enemyPool;
    private final StateMachine stateMachine;
    private final List<Wave> waves;
    private final List<EnemySpawnParam> spawned;
    private int currentWaveIndex = 0;
    private double timer = 0;

    public WaveController(EnemySpawner enemySpawner, IPool<Enemy, EnemyType> enemyPool, StateMachine stateMachine) {
        this.enemySpawner = enemySpawner;
        this.enemyPool = enemyPool;
        this.stateMachine = stateMachine;
        spawned = new ArrayList<>();
        waves = List.of(
                new Wave(List.of(
                        new EnemySpawnParam(EnemyType.SHOOTER, 22, 2, 0, 100, 100),
                        new EnemySpawnParam(EnemyType.SHOOTER, 24, 2, 0, 200, 100),
                        new EnemySpawnParam(EnemyType.SHOOTER, 26, 2, 0, 300, 100),
                        new EnemySpawnParam(EnemyType.SHOOTER, 30, 2, 0, 400, 100),
                        new EnemySpawnParam(EnemyType.SHOOTER, 28, 2, 0, 500, 100),
                        new EnemySpawnParam(EnemyType.SHOOTER, 31, 2, 0, 600, 100),
                        new EnemySpawnParam(EnemyType.SHOOTER, 32, 2, 0, 700, 100),
                        new EnemySpawnParam(EnemyType.SHOOTER, 24, 2, 0, 800, 100),
                        new EnemySpawnParam(EnemyType.SHOOTER, 22, 2, 0, 900, 100),
                        new EnemySpawnParam(EnemyType.SHOOTER, 12, 2, 0, 100, 150),
                        new EnemySpawnParam(EnemyType.SHOOTER, 14, 2, 0, 200, 150),
                        new EnemySpawnParam(EnemyType.SHOOTER, 16, 2, 0, 300, 150),
                        new EnemySpawnParam(EnemyType.SHOOTER, 20, 2, 0, 400, 150),
                        new EnemySpawnParam(EnemyType.SHOOTER, 18, 2, 0, 500, 150),
                        new EnemySpawnParam(EnemyType.SHOOTER, 21, 2, 0, 600, 150),
                        new EnemySpawnParam(EnemyType.SHOOTER, 22, 2, 0, 700, 150),
                        new EnemySpawnParam(EnemyType.SHOOTER, 14, 2, 0, 800, 150),
                        new EnemySpawnParam(EnemyType.SHOOTER, 12, 2, 0, 900, 150),
                        new EnemySpawnParam(EnemyType.SHOOTER, 2, 2, 0, 100, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 4, 2, 0, 200, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 6, 2, 0, 300, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 10, 2, 0, 400, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 8, 2, 0, 500, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 11, 2, 0, 600, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 12, 2, 0, 700, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 4, 2, 0, 800, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 2, 2, 0, 900, 200)
                )),
                new Wave(List.of(
                        new EnemySpawnParam(EnemyType.BASIC, 12, 4, 250, 100, 100),
                        new EnemySpawnParam(EnemyType.BASIC, 14, 4, 250, 200, 100),
                        new EnemySpawnParam(EnemyType.BASIC, 16, 4, 250, 300, 100),
                        new EnemySpawnParam(EnemyType.BASIC, 10, 4, 250, 400, 100),
                        new EnemySpawnParam(EnemyType.BASIC, 8, 4, 250, 500, 100),
                        new EnemySpawnParam(EnemyType.BASIC, 11, 4, 250, 600, 100),
                        new EnemySpawnParam(EnemyType.BASIC, 12, 4, 250, 700, 100),
                        new EnemySpawnParam(EnemyType.BASIC, 14, 4, 250, 800, 100),
                        new EnemySpawnParam(EnemyType.BASIC, 6, 4, 250, 900, 100),
                        new EnemySpawnParam(EnemyType.SHOOTER, 12, 2, 0, 100, 150),
                        new EnemySpawnParam(EnemyType.SHOOTER, 14, 2, 0, 200, 150),
                        new EnemySpawnParam(EnemyType.SHOOTER, 16, 2, 0, 300, 150),
                        new EnemySpawnParam(EnemyType.SHOOTER, 20, 2, 0, 400, 150),
                        new EnemySpawnParam(EnemyType.SHOOTER, 18, 2, 0, 500, 150),
                        new EnemySpawnParam(EnemyType.SHOOTER, 21, 2, 0, 600, 150),
                        new EnemySpawnParam(EnemyType.SHOOTER, 22, 2, 0, 700, 150),
                        new EnemySpawnParam(EnemyType.SHOOTER, 14, 2, 0, 800, 150),
                        new EnemySpawnParam(EnemyType.SHOOTER, 12, 2, 0, 900, 150),
                        new EnemySpawnParam(EnemyType.SHOOTER, 2, 2, 0, 100, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 4, 2, 0, 200, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 6, 2, 0, 300, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 10, 2, 0, 400, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 8, 2, 0, 500, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 11, 2, 0, 600, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 12, 2, 0, 700, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 4, 2, 0, 800, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 2, 2, 0, 900, 200)
                )),
                new Wave(List.of(
                        new EnemySpawnParam(EnemyType.BASIC, 12, 4, 250, 100, 100),
                        new EnemySpawnParam(EnemyType.BASIC, 14, 4, 250, 200, 100),
                        new EnemySpawnParam(EnemyType.BASIC, 16, 4, 250, 300, 100),
                        new EnemySpawnParam(EnemyType.BASIC, 10, 4, 250, 400, 100),
                        new EnemySpawnParam(EnemyType.BASIC, 8, 4, 250, 500, 100),
                        new EnemySpawnParam(EnemyType.BASIC, 11, 4, 250, 600, 100),
                        new EnemySpawnParam(EnemyType.BASIC, 12, 4, 250, 700, 100),
                        new EnemySpawnParam(EnemyType.BASIC, 14, 4, 250, 800, 100),
                        new EnemySpawnParam(EnemyType.BASIC, 6, 4, 250, 900, 100),
                        new EnemySpawnParam(EnemyType.MOVER, 12, 6, 100, 100, 150),
                        new EnemySpawnParam(EnemyType.MOVER, 14, 6, 100, 200, 150),
                        new EnemySpawnParam(EnemyType.MOVER, 10, 6, 100, 300, 150),
                        new EnemySpawnParam(EnemyType.MOVER, 5, 6, 100, 400, 150),
                        new EnemySpawnParam(EnemyType.MOVER, 7, 6, 100, 500, 150),
                        new EnemySpawnParam(EnemyType.MOVER, 11, 6, 100, 600, 150),
                        new EnemySpawnParam(EnemyType.MOVER, 9, 6, 100, 700, 150),
                        new EnemySpawnParam(EnemyType.MOVER, 11, 6, 100, 800, 150),
                        new EnemySpawnParam(EnemyType.MOVER, 12, 6, 100, 900, 150),
                        new EnemySpawnParam(EnemyType.SHOOTER, 2, 2, 0, 100, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 4, 2, 0, 200, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 6, 2, 0, 300, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 10, 2, 0, 400, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 8, 2, 0, 500, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 11, 2, 0, 600, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 12, 2, 0, 700, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 4, 2, 0, 800, 200),
                        new EnemySpawnParam(EnemyType.SHOOTER, 2, 2, 0, 900, 200)
                )),
                new Wave(List.of(
                        new EnemySpawnParam(EnemyType.BOSS, 0, 80,100, 450, 120)
                ))
        );
    }

    public void reset() {
        currentWaveIndex = 3;
        timer = 0;
        spawned.clear();
    }

    @Override
    public void update(double deltaTime) {
        if (currentWaveIndex >= waves.size()) {
            stateMachine.setState(GameOverState.class);
            return;
        }

        timer += deltaTime;
        var wave = waves.get(currentWaveIndex);

        for (var spawn : wave.getSpawns()) {
            if (!spawned.contains(spawn) && timer >= 2) {
                enemySpawner.spawn(spawn);
                spawned.add(spawn);
            }
        }

        if (spawned.size() == wave.getSpawns().size() && enemyPool.getPooledObj().stream().noneMatch(e -> e.getModel().isActive())) {
            currentWaveIndex++;
            timer = 0;
            spawned.clear();
        }
    }
}