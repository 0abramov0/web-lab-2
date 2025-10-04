package models;

public record AreaCheckResult(Point point, boolean isHit) {

    @Override
    public String toString() {
        return "AreaCheckResult{" +
                "point=" + point +
                ", isHit=" + isHit +
                '}';
    }
}
