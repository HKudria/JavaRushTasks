package com.javarush.task.task24.task2413;

public abstract class BaseObject {
    private double x, y, radius;

    public BaseObject(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    abstract void draw(Canvas canvas);
    abstract void move();

    boolean intersects(BaseObject o){
        double distance = Math.sqrt(Math.pow(o.x - x,2) + Math.pow(o.y - y,2));
        if (distance <= max(radius,o.radius)) return true;
        else return false;
    }

    double max (double first, double second){
        if (first<second) return second;
        else return first;
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

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
