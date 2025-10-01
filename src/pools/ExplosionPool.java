package pools;

import entities.Explosion;
import ui.animation.Animation;

import java.util.ArrayList;
import java.util.List;

public class ExplosionPool {
    private final List<Explosion> explosions = new ArrayList<>();
    private final Animation baseAnimation;

    public ExplosionPool(Animation baseAnimation) {
        this.baseAnimation = baseAnimation;
    }

    private Explosion createExplosion() {
        return new Explosion(new Animation(baseAnimation.getFrames(), 0.1, false));
    }

    public Explosion get() {
        for (Explosion e : explosions) {
            if (!e.getModel().isActive()) {
                return e;
            }
        }
        Explosion newExp = createExplosion();
        explosions.add(newExp);
        return newExp;
    }

    public List<Explosion> getAll() { return explosions; }
}