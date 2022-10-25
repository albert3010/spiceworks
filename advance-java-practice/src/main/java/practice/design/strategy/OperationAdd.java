package practice.design.strategy;

/**
 * Created by omprakash.yadav on 15/03/16.
 */
public class OperationAdd implements Strategy {

    @Override
    public int doOperation(int num1, int mun2){
        return num1+mun2;
    }
}
