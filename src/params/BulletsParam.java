package params;

import enums.BulletType;
import enums.CollisionsType;

public record BulletsParam(int speed, CollisionsType collisionsType, BulletType bulletType, double x, double y, double direction){}
