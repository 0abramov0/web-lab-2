package models;

import java.util.Arrays;
import java.util.List;

public class PointValidator {
    public void validateX(String xStr) throws IllegalArgumentException {
        if (xStr == null || xStr.isEmpty()) {
            throw new IllegalArgumentException("X can't be null");
        }
        try {
            double x = Double.parseDouble(xStr);
            if (x < -5 || x > 3) {
                throw new IllegalArgumentException("X must be less than 3 and more than -5");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("X must be a number");
        }
    }

    public void validateY(String yStr) {
        if (yStr == null || yStr.isEmpty()) {
            throw new IllegalArgumentException("Y can't be null");
        }
        try {
            double y = Double.parseDouble(yStr);
            if (y >= 3 || y <= -3) {
                throw new IllegalArgumentException("Y must be less than 3 and more than -3");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Y must be a number");
        }
    }

    public void validateR(String rString) {
        if (rString == null || rString.isEmpty()) {
            throw new IllegalArgumentException("R can't be null");
        }
        try {
            long r = Long.parseLong(rString);
            List<Long> rValues = Arrays.asList(1L, 2L, 3L, 4L, 5L);
            if (!rValues.contains(r)) {
                throw new IllegalArgumentException("R must be in " + rValues);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("R must be an integer number");
        }
    }
}