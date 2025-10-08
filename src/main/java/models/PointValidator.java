package models;

import java.util.Arrays;
import java.util.List;

public class PointValidator {
    public void validatePoint(Point point) throws IllegalArgumentException {
        validateX(point.xStr());
        validateY(point.yStr());
        validateR(point.rStr());
    }

    public void validateX(String xStr) throws IllegalArgumentException {
        if (xStr == null || xStr.isEmpty()) {
            throw new IllegalArgumentException("X can't be null");
        }
        double x = Double.parseDouble(xStr);
        if (x < -5 || x > 3) {
            throw new IllegalArgumentException("X must be less than 3 and more than -3");
        }
    }

    public void validateY(String yString) {
        if (yString == null || yString.isEmpty()) {
            throw new IllegalArgumentException("Y can't be null");
        }
        double y = Double.parseDouble(yString);
        if (y >= 3 || y <= -3) {
            throw new IllegalArgumentException("Y must be less than 3 and more than -3");
        }
    }

    public void validateR(String rString) {
        if (rString == null || rString.isEmpty()) {
            throw new IllegalArgumentException("R can't be null");
        }
        int r = Integer.parseInt(rString);
        List<Integer> rValues = Arrays.asList(1, 2, 3, 4, 5);
        if (!rValues.contains(r)) {
            throw new IllegalArgumentException("R must be in " + rValues);
        }
    }
}