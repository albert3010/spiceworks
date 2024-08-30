package practice_lld.top25.lld.splitwise.split;

public interface SplitVisitor<T, R> {

    R visitPercent(T t);
    R visitEqual(T t);

}
