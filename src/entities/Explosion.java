package entities;

import models.ExplosionModel;
import ui.animation.Animation;

import java.awt.*;
import java.util.function.Consumer;

public class Explosion {
    private final ExplosionModel model;
    private final Animation animation;
    private Consumer<Explosion> onFinished;

    public Explosion(Animation animation) {
        this.model = new ExplosionModel();
        this.animation = animation;
    }

    public void update(double deltaTime) {
        if (!model.isActive()) return;

        animation.update(deltaTime);

        if (animation.isFinished()) {
            model.setActive(false);
            if (onFinished != null) {
                onFinished.accept(this);
            }
        }
    }

    public void draw(Graphics g) {
        if (!model.isActive()) return;

        g.drawImage(animation.getCurrentFrame().getSprite(),
                (int) model.getX(),
                (int) model.getY(),
                null);
    }

    public void activate(double x, double y, Consumer<Explosion> onFinished) {
        model.setX(x);
        model.setY(y);
        model.setActive(true);
        animation.reset();
        this.onFinished = onFinished;
    }

    public ExplosionModel getModel() { return model; }
}