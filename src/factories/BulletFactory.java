package factories;

import core.CollisionController;
import core.Updater;
import entities.Bullet;
import enums.BulletType;
import events.EventBus;
import params.BulletsParam;
import views.BaseView;

public class BulletFactory implements IFactory<Bullet, BulletsParam> {
    private final EventBus eventBus;
    private final Updater updater;
    private final CollisionController collisionController;
    private final IFactory<BaseView, BulletType> viewFactory;

    public BulletFactory(EventBus eventBus, Updater updater, CollisionController collisionController, IFactory<BaseView,
            BulletType> viewFactory) {
        this.eventBus = eventBus;
        this.updater = updater;
        this.collisionController = collisionController;
        this.viewFactory = viewFactory;
    }

    @Override
     public Bullet create(BulletsParam param){
        return new Bullet(eventBus, updater, param.speed(), collisionController, viewFactory, param.collisionsType(),
                param.bulletType());
    }
}

