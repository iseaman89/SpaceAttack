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
        newBullet.getModel().setActive(true);
        return newBullet;
    }

    @Override
    public Bullet get(BulletsParam param){
        for (var b : bullets){
            if (b.getModel().isActive() || b.getBulletType() != param.bulletType()) continue;
            b.getModel().setActive(true);
            return b;
        }

        return instantiateBullet(param);
    }

    @Override
    public void deactivate(Bullet bullet) { bullet.getModel().setActive(false); }

    @Override
    public void deactivateAll(){ bullets.forEach(Bullet::deactivate); }

    @Override
    public List<Bullet> getPooledObj(){ return bullets; }
}
