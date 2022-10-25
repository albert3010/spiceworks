package practice.Hackethon;

public enum RatingPoints {

    FIRST(3),
    SECOND(2),
    THIRD(1);

    private int point;

    RatingPoints(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }
}
