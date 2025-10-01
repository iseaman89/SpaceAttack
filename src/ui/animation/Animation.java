package ui.animation;

import views.BaseView;

public class Animation {
    private final BaseView[] frames;
    private final double frameDuration; // скільки часу триває 1 кадр
    private final boolean loop;

    private int currentFrame = 0;
    private double timer = 0;
    private boolean finished = false;

    public Animation(BaseView[] frames, double frameDuration, boolean loop) {
        this.frames = frames;
        this.frameDuration = frameDuration;
        this.loop = loop;
    }

    public void update(double deltaTime) {
        if (finished) return;

        timer += deltaTime;

        if (timer >= frameDuration) {
            timer = 0;
            currentFrame++;

            if (currentFrame >= frames.length) {
                if (loop) {
                    currentFrame = 0;
                } else {
                    currentFrame = frames.length - 1; // зупиняємось на останньому кадрі
                    finished = true;
                }
            }
        }
    }


    public void reset() {
        currentFrame = 0;
        timer = 0;
        finished = false;
    }

    public BaseView[] getFrames() { return frames; }

    public BaseView getCurrentFrame() { return frames[currentFrame]; }

    public boolean isFinished() { return finished; }
}
