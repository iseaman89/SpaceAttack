package pools;

import entities.Bullet;
import factories.IFactory;
import params.BulletsParam;

import java.util.ArrayList;
import java.util.List;

public class BulletPool implements IPool<Bullet, BulletsParam> {
    private final IFactory<Bullet, BulletsParam> bulletFactory;
    private final List<Bullet> bullets = new ArrayList<>();

    public BulletPool(IFactory<Bullet, BulletsParam> bulletFactory) {
        this.bulletFactory = bulletFactory;
    }

    private Bullet instantiateBullet(BulletsParam param){
        var newBullet = bulletFactory.create(param);
        bullets.add(newBullet);
        newBullet.getBulletModel().setActive(true);
        return newBullet;
    }

    public Bullet get(BulletsParam param){
        for (var b : bullets){
            if (b.getBulletModel().isActive() || b.getBulletType() != param.bulletType()) continue;
            b.getBulletModel().setActive(true);
            return b;
        }

        return instantiateBullet(param);
    }

    public void deactivate(Bullet bullet) {
        bullet.getBulletModel().setActive(false);
    }

    public void deactivateAll(){
        bullets.forEach(Bullet::deactivate);
    }

    public List<Bullet> getPooledObj(){
        return bullets;
    }
}
