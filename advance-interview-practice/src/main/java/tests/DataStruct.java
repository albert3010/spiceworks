package tests;

/**
 * Created by  on 9/23/2016.
 */
public  class DataStruct {

   static class Node {
        int val;
        Node pre , next;

        Node(int x) {
            this.val = x;
            this.pre = null;
            this.next = null;
        }
    }

   static class Stack{
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
                Node temp = new Node(top.val);
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

    class Queue{
        Node first, last;

        public void enqueue(Node n){
            if(first == null){
                first = n;
                last = first;
            }else{
                last.next = n;
                last = n;
            }
        }

        public Node dequeue(){
            if(first == null){
                return null;
            }else{
                Node temp = new Node(first.val);
                first = first.next;
                return temp;
            }
        }
    }

    public static class LinkedList {
        Node first;

        LinkedList() {
            first = null;
        }

        public void insertNode(int val) {
            Node n = new Node(val);

            if (first != null) {

                first.next = n;
                first = n;

            } else {
                first = n;
            }

        }

        public Node deleteNode() throws Exception {
            if (first == null) {  //means LinkedList in empty, throw exception.
                throw new Exception("LinkedList doesn't contain any Nodes.");
            }
            Node tmp = first;
            first = first.next;
            return tmp;
        }

        /**
         * Display generic Single LinkedList
         */
        public void displayLinkedList() {
            System.out.print("Displaying LinkedList [first--->last]: ");
            Node tempDisplay = first; // start at the beginning of linkedList
            while (tempDisplay != null){ // Executes until we don't find end of list.
                System.out.println(tempDisplay.val);
                tempDisplay = tempDisplay.next; // move to next Node
            }
            System.out.println();

        }

  }

   static void inOrder(Node n) {
        if(n == null)
            return;

        if(n.pre != null)
            inOrder(n.pre);

        System.out.print(n.val + " ");

        if(n.next != null)
            inOrder(n.next);
    }

}
