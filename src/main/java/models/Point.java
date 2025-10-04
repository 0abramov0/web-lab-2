package models;

public record Point (String xStr, String yStr, String rStr){
    public double getXAsDouble() {
        return Math.round(Float.parseFloat(xStr) * 1000) / 1000.0;
    }

    public double getYAsDouble() {
        return Math.round(Double.parseDouble(yStr) * 1000) / 1000.0;
    }

    public int getRAsInteger() {
        return Integer.parseInt(rStr);
    }

    @Override
    public String toString() {
        return "Point{" +
                "xStr='" + xStr + '\'' +
                ", yStr='" + yStr + '\'' +
                ", rStr='" + rStr + '\'' +
                '}';
    }
}