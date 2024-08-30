package practice_lld.top25.lld.splitwise.split;

public enum SplitType {
    EQUAL {
        <T, R> R apply(SplitVisitor<T, R> visitor, T t) {
            return visitor.visitEqual(t);
        }
    },
    PERCENT {
        <T, R> R apply(SplitVisitor<T, R> visitor, T t) {
            return visitor.visitPercent(t);
        }
    };

    abstract <T, R>  R apply(SplitVisitor<T, R> visitor, T t);
}