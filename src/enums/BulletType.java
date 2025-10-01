package enums;

public enum BulletType {
    SHIP {
        @Override
        public boolean isWithinBounds(double y) {
            return y >= -20;
        }
    },
    ENEMY {
        @Override
        public boolean isWithinBounds(double y) {
            return y <= 660;
        }
    },
    BOSS {
        @Override
        public boolean isWithinBounds(double y) {
            return y <= 660;
        }
    };

    public abstract boolean isWithinBounds(double y);
}