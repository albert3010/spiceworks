package practice.generic;

import java.util.*;

/**
 * Created by OMPRAKASH on 9/24/2016.
 */
public class BallCollision {


    public static void main(String[] args) {

        Stack<MyDataStruct.Node> st;


//        int siz[] = { 8,5, 3, 4, 6,9,2,1};

        List<Integer> siz = new LinkedList<>();
        List<Integer> dir = new LinkedList<>();

//        char dir[] = { 'R', 'R','R', 'L', 'L', 'L','L','L'};

        helperUtil();
    }

    private static void helperUtil() {

       // int n = siz.length;

        MyDataStruct.Node nd;
        MyDataStruct.MyStack st = new MyDataStruct.MyStack();
        int sz;
        String dr;

        Scanner read = new Scanner(System.in);

        int n = read.nextInt();


        while(n>0) {

            sz = read.nextInt();
            dr = read.next();

            MyDataStruct.Node newNode = new MyDataStruct.Node(sz, dr);
            if (st.peek() == null) {
                st.push(newNode);

            } else {
                nd = st.peek();

                if (Objects.equals(nd.dir, "L")) {
                    st.push(newNode);
                } else if (dr == "R") {
                    st.push(newNode);

                } else { // L R R L

                    while (nd != null) {

                        sz = nd.size;
                        dr = nd.dir;

                        if (dr == "R") {
                            if (sz < sz) {
                                st.pop();
                                nd = st.peek();
                            } else {
                                break;// no push left bcoz of size
                            }

                        } else {
                            st.push(newNode);
                            break;
                        }
                        if(nd==null){
                            st.push(newNode);
                            break;
                        }
                    }

                }
            }
            n--;
        }

        nd = st.peek();
        String result="";

        while (nd != null) {
            sz = nd.size;
            dr = nd.dir;
//            System.out.print(sz);
//            System.out.print(dr);
//            System.out.println();

            result = sz+dr+" "+result;

            st.pop();
            nd = st.peek();
        }


    }
}
