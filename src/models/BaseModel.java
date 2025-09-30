package models;

public class BaseModel {
    private double x;
    private double y;
    private int speed;

    public BaseModel(double x, double y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
