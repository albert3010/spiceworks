package leetcode.JavaPractice.basic;

public class B {
     static int b = 1;

    public int getNum(int a){
        b = b+1;
        return a;
    }

    public static int getNum2(int a){
        return 3;
    }

    private int getNum3(int a){
        return 3;
    }

    private int getNum4(int a){
        return 3;
    }

    protected int getNum5(int a){
        return 3;
    }

    int getNum6(int a){
        return 3;
    }

    final int getNum7(int a){
        return 3;
    }

    private int getNum1(){
        return 3;
    }
}
