 package practice.tests;


import java.util.LinkedList;

/**
 * Created by OMPRAKASH on 9/19/2016.
 */
public class problem {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(3);
        list.add(2);
        list.add(4);
        list.addFirst(9);


//        for(Integer i : list){
//            System.out.println(i);
//        }

        DataStruct.Node node = new DataStruct.Node(1);

        DataStruct.Stack stack = new DataStruct.Stack();

        DataStruct.LinkedList linkedList = new DataStruct.LinkedList();

        linkedList.insertNode(5);

        DataStruct.Node node1 = linkedList.first;

        node1.pre = new DataStruct.Node(2);
        node1.pre.pre = new DataStruct.Node(1);
        node1.pre.next = new DataStruct.Node(3);
        node1.next = new DataStruct.Node(7);
        node1.next.pre = new DataStruct.Node(6);
        node1.next.next = new DataStruct.Node(8);

        DataStruct.inOrder(node1);




    }
}
