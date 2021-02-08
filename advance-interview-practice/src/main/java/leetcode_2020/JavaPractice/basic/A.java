package leetcode_2020.JavaPractice.basic;

public abstract class A implements AI, BI {

    final static int a =1;

    public int addB(){
        return AI.a;
    }

//    public int add1(){
//        return 1;
//    }

    public int add2(){
        int b = a;
        return 1;
    }
    public static class getNext {
        static int x = AI.a;
        public static int value() {
            return ++x;
        }
    }

    public int add1(){
       return 1;
    }
}
