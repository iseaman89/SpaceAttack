package factories;

import core.CollisionController;
import core.Updater;
import entities.Bullet;
import events.EventBus;
import params.BulletsParam;

public class BulletFactory implements IFactory<Bullet, BulletsParam> {
    private final EventBus eventBus;
    private final Updater updater;
    private final CollisionController collisionController;

    public BulletFactory(EventBus eventBus, Updater updater, CollisionController collisionController) {
        this.eventBus = eventBus;
        this.updater = updater;
        this.collisionController = collisionController;
    }

    @Override
     public Bullet create(BulletsParam param){
        return new Bullet(eventBus, updater, param.speed(), collisionController, param.collisionsType(), param.bulletType());
    }
}

