package practice.generic;


/**
 * Created by OMPRAKASH on 9/24/2016.
 */
public class MyDataStruct {

    static class Node {
        int size;
        String dir;
        Node  next;

        Node(int x, String c) {
            this.size = x;
            this.dir =c;
            this.next = null;
        }
    }

    static class MyStack{
        Node top;

        public Node peek(){
            if(top != null){
                return top;
            }

            return null;
        }

        public Node pop(){
            if(top == null){
                return null;
            }else{
                Node temp = new Node(top.size, top.dir);
                top = top.next;
                return temp;
            }
        }

        public void push(Node n){
            if(n != null){
                n.next = top;
                top = n;
            }
        }
    }

}
