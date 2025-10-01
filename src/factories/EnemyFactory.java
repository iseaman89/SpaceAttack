package factories;

import controllers.EnemyController;
import core.CollisionController;
import entities.Enemy;
import enums.EnemyType;
import events.EventBus;
import params.EnemyControllerParam;
import spawners.ExplosionSpawner;
import views.BaseView;

public class EnemyFactory implements IFactory<Enemy, EnemyType>{
    private final EventBus eventBus;
    private final CollisionController collisionController;
    private final ExplosionSpawner explosionSpawner;
    private final IFactory<EnemyController, EnemyControllerParam> controllerFactory;
    private final IFactory<BaseView, EnemyType> viewFactory;

    public EnemyFactory(EventBus eventBus, CollisionController collisionController, ExplosionSpawner explosionSpawner,
                        IFactory<EnemyController, EnemyControllerParam> controllerFactory,
                        IFactory<BaseView, EnemyType> viewFactory) {
        this.eventBus = eventBus;
        this.collisionController = collisionController;
        this.explosionSpawner = explosionSpawner;
        this.controllerFactory = controllerFactory;
        this.viewFactory = viewFactory;
    }

    @Override
    public Enemy create(EnemyType type) {
        return new Enemy(controllerFactory, type, viewFactory, collisionController, eventBus, explosionSpawner);
    }
}
