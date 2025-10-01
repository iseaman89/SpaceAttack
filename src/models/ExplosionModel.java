package models;

public class ExplosionModel {
    private double x;
    private double y;
    private boolean active;

    public ExplosionModel() {
        this.active = false;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public boolean isActive() { return active; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setActive(boolean active) { this.active = active; }
}