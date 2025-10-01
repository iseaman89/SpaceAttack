package spawners;

import core.CollisionController;
import core.Updater;
import entities.Bullet;
import params.BulletsParam;
import pools.IPool;

public class BulletSpawner {
    private final IPool<Bullet, BulletsParam> bulletPool;
    private final Updater updater;
    private final CollisionController collisionController;

    public BulletSpawner(IPool<Bullet, BulletsParam>  bulletPool, Updater updater, CollisionController collisionController) {
        this.bulletPool = bulletPool;
        this.updater = updater;
        this.collisionController = collisionController;
    }

    public void spawn(BulletsParam param){
        var bullet = bulletPool.get(param);
        bullet.getModel().setX(param.x());
        bullet.getModel().setY(param.y());
        bullet.getController().setDirection(param.direction());
        updater.add(bullet.getController());
        collisionController.addCollidable(bullet);
    }
}
