package practice.tests;

/**
 * Created by OMPRAKASH on 9/24/2016.
 */
public class LCAnArray {

    int n;
    public static void main(String[] args) {

        NTree root = new NTree(9,3);

        for(NTree c : root.childrens){
            c = new NTree(15,3);
        }

    }

}
