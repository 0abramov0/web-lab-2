package models;

public class HitChecker {
    public boolean checkHit(Point point) {
        return checkHitToCircle(point) || checkHitToRectangle(point) || checkHitToTriangle(point);
    }

    public boolean checkHitToCircle(Point point) {
        double x = point.getXAsDouble();
        double y = point.getYAsDouble();
        int r = point.getRAsInteger();
        return (x <= 0 && y >= 0) && (4 * (x * x + y * y) <= r * r);
    }

    public boolean checkHitToRectangle(Point point) {
        double x = point.getXAsDouble();
        double y = point.getYAsDouble();
        int r = point.getRAsInteger();
        return 0 <= x && x <= r && y >= -((float) r / 2) && y <= 0;
    }

    public boolean checkHitToTriangle(Point point) {
        double x = point.getXAsDouble();
        double y = point.getYAsDouble();
        int r = point.getRAsInteger();
        return x <= 0 && y <= 0 && (-2 * x - y - r <= 0);
    }
}