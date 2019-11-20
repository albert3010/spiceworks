package company.google;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by oyadav on 12/12/16.
 */

//Given a list of manager and employee information represented in hashMap entries {AAA->BBB,CCC,EEE},{CCC->DDD}.
//Print company structure tree with proper indentations.
// userid - albert3010
// pass - newton3010
public class CompanyStructure {

    @Test
    public void companyStructureTest() {

        HashMap<String, List<String>> data = new HashMap<>();

        List<String> l1 = new LinkedList<>();
        l1.add("e1");
        l1.add("e2");
        l1.add("e3");

        data.put("a1", l1);
        List<String> l2 = new LinkedList<>();
        l2.add("c1");
        l2.add("c2");
        l2.add("c3");

        data.put("e2", l2);

        List<String> l3 = new LinkedList<>();
        l3.add("d1");
        l3.add("d2");
        l3.add("d3");

        data.put("c2", l3);
        makeCompanyStruct(data);

    }

    private void makeCompanyStruct(HashMap<String, List<String>> data) {


        HashMap<String, String> tree = new HashMap<>();

        data.keySet().stream().forEach(e -> {

            List<String> list = data.get(e);
            list.stream().forEach(e1 -> {
                tree.put(e1, e);
            });

        });

        Optional<String> s = tree.keySet().stream().findFirst();
//        System.out.println(tree.get(s.get()));
//        System.out.println(s);
        while (true){

            String parent  = tree.get(s.get());
            if(parent==null){
                break;
            }
            Optional<String>  s2 = Optional.of( parent );


            s = s2;
        }
       // System.out.println(s);
        printTree(s.get(), data, 0);

    }

    private void printTree(String s, HashMap<String, List<String>> data, int space) {


        int left = space;

        while (left > 0) {

            System.out.print("-");
            left--;
        }
        System.out.println(s);

        List<String> list = data.get(s);

        if(list==null)
             return;
        for(String e : list){
      //new StringBuilder(e).reverse();
        printTree(e, data, space+1);

        }

    }
}
