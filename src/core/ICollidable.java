package core;

import enums.CollisionsType;

import java.awt.*;

public interface ICollidable {
    Rectangle getBounds();
    CollisionsType getType();
    void onCollisionEnter(ICollidable other);
}
