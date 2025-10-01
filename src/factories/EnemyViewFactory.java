package factories;

import enums.EnemyType;
import views.BaseView;

public class EnemyViewFactory implements IFactory<BaseView, EnemyType>{
    @Override
    public BaseView create(EnemyType type) {
        switch (type){
            case BASIC -> {
                return new BaseView("/enemy1.png", 40, 40);
            }
            case SHOOTER -> {
                return new BaseView("/enemy2.png", 40, 40);
            }
            case MOVER -> {
                return new BaseView("/enemy3.png", 40, 40);
            }
            case BOSS -> {
                return new BaseView("/boss.png", 120, 120);
            }
        }
        return null;
    }
}
