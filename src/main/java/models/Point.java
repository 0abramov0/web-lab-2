package models;

public class Point {
    private final double x;
    private final double y;
    private final long r;

    public Point(double x, double y, long r) {
        this.x = Math.round(x * 1000) / 1000.0;;
        this.y = Math.round(y * 1000) / 1000.0;;
        this.r = r;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public long getR() {
        return r;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", r='" + r + '\'' +
                '}';
    }
}