package spawners;

import core.IUpdateListener;
import entities.Explosion;
import pools.ExplosionPool;

import java.util.function.Consumer;

public class ExplosionSpawner implements IUpdateListener {
    private final ExplosionPool explosionPool;

    public ExplosionSpawner(ExplosionPool explosionPool) { this.explosionPool = explosionPool; }

    public void spawn(double x, double y, Consumer<Explosion> onFinished) {
        var explosion = explosionPool.get();
        explosion.activate(x, y, onFinished);
    }

    @Override
    public void update(double deltaTime) {
        for (var e : explosionPool.getAll()) {
            if (!e.getModel().isActive()) continue;
            e.update(deltaTime);
        }
    }
}
