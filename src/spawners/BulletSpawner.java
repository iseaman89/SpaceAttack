package spawners;

import core.CollisionController;
import core.Updater;
import entities.Bullet;
import models.SpaceShipModel;
import params.BulletsParam;
import pools.IPool;

public class BulletSpawner {
    private final IPool<Bullet, BulletsParam> bulletPool;
    private final SpaceShipModel shipModel;
    private final Updater updater;
    private final CollisionController collisionController;

    public BulletSpawner(IPool<Bullet, BulletsParam>  bulletPool, SpaceShipModel shipModel, Updater updater, CollisionController collisionController) {
        this.bulletPool = bulletPool;
        this.shipModel = shipModel;
        this.updater = updater;
        this.collisionController = collisionController;
    }

    public void spawn(BulletsParam param){
        var bullet = bulletPool.get(param);
        bullet.getBulletModel().setX(param.x());
        bullet.getBulletModel().setY(param.y());
        bullet.getBulletController().setDirection(param.direction());
        updater.add(bullet.getBulletController());
        collisionController.addCollidable(bullet);
    }
}
