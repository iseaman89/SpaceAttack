package factories;

import enums.BulletType;
import views.BaseView;

public class BulletViewFactory implements IFactory<BaseView, BulletType>{
    @Override
    public BaseView create(BulletType type) {
        switch (type) {
            case SHIP -> {
                return new BaseView("/bullet.png", 16, 24);
            }
            case ENEMY -> {
                return new BaseView("/bullet_enemy.png", 8, 16);
            }
            case BOSS -> {
                return new BaseView("/bullet_boss.png", 32, 32);
            }
        }
        return null;
    }
}
