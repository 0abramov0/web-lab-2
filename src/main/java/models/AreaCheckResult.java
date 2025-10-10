package models;

public class AreaCheckResult {
    private final Point point;
    private final boolean hit;


    public AreaCheckResult(Point point, boolean isHit) {
        this.point = point;
        this.hit = isHit;
    }

    public boolean isHit() {
        return hit;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return "AreaCheckResult{" +
                "point=" + point +
                ", isHit=" + hit +
                '}';
    }
}
