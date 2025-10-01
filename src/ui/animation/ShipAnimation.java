package ui.animation;

import enums.SpaceShipAnimationState;
import views.BaseView;

import java.awt.*;

public class ShipAnimation {
    private final BaseView shipLeft;
    private final BaseView shipRight;
    private final BaseView shipIdle;
    private SpaceShipAnimationState currentState;


    public ShipAnimation() {
        shipLeft = new BaseView("/ship_left.png", 43, 48);
        shipRight = new BaseView("/ship_right.png", 43, 48);
        shipIdle = new BaseView("/ship_idle.png", 48, 48);
    }

    public Image getSprite(){
        return currentState == SpaceShipAnimationState.IDLE ? shipIdle.getSprite() :
                currentState == SpaceShipAnimationState.LEFT ? shipLeft.getSprite() : shipRight.getSprite();
    }

    public int getWidth(){
        return currentState == SpaceShipAnimationState.IDLE ? shipIdle.getWidth()
                : currentState == SpaceShipAnimationState.LEFT ? shipLeft.getWidth() : shipRight.getWidth();
    }

    public int getHeight(){
        return currentState == SpaceShipAnimationState.IDLE ? shipIdle.getHeight()
                : currentState == SpaceShipAnimationState.LEFT ? shipLeft.getHeight() : shipRight.getHeight();
    }

    public void setCurrentState(SpaceShipAnimationState currentState) { this.currentState = currentState; }
}
