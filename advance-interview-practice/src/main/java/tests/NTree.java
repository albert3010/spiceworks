package tests;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OMPRAKASH on 9/24/2016.
 */
class NTree{
     int n;
     int val;

    List<NTree> childrens;

    NTree(int val, int n){
        this.val = val;
        this.childrens = new ArrayList<NTree>(n);
    }
}