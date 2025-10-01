package core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollisionController implements IUpdateListener {
    private final List<ICollidable> collidables = new CopyOnWriteArrayList<>();

    private void checkCollisions() {
        var snapshot = new ArrayList<>(collidables);

        for (int i = 0; i < snapshot.size(); i++) {
            for (int j = i + 1; j < snapshot.size(); j++) {
                var a = snapshot.get(i);
                var b = snapshot.get(j);

                if (a.getBounds().intersects(b.getBounds())) {
                    if (a.getType() == b.getType()) continue;
                    a.onCollisionEnter(b);
                    b.onCollisionEnter(a);
                }
            }
        }
    }

    @Override
    public void update(double deltaTime) { checkCollisions(); }

    public void addCollidable(ICollidable collidable) { collidables.add(collidable); }

    public void removeCollidable(ICollidable collidable) { collidables.remove(collidable); }
}
