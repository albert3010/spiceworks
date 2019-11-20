package design.strategy;

/**
 * Created by omprakash.yadav on 15/03/16.
 */
public class StrategyPattern {

    public static void main(String[] args) {

        Context add = new Context(new OperationAdd());
        System.out.println(add.executeStrategy(1,3));

        Context sub = new Context(new OperationSub());
        System.out.println(sub.executeStrategy(5,3));

    }
}
