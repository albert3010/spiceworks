//import java.util.*;
//
//public class App {
//
//    class Node{
//        int totalRating;
//        int count;
//        Node(int totalRating, int count){
//            this.totalRating = totalRating;
//            this.count = count;
//        }
//    }
//
//    static class model.Employee{
//        String id;
//        int rating;
//        List<String> subOrd;
//        model.Employee(String id, int rating, List<String> subOrd){
//            this.id = id;
//            this.rating = rating;
//            this.subOrd = subOrd;
//        }
//    }
//
//    public static void main (String[] args) {
//        System.out.println("Hello Java");
//
//
//        model.Employee A = new model.Employee("A", 5, new ArrayList<String>());
//        model.Employee B = new model.Employee("B", 3, new ArrayList<String>());
//        model.Employee D = new model.Employee("D", 4, new ArrayList<String>());
//        model.Employee E = new model.Employee("E", 10, new ArrayList<String>());
//        model.Employee C = new model.Employee("C", 2, new ArrayList<String>());
//        A.subOrd.add(B);
//        A.reports.add(C);
//        C.reports.add(D);
//        C.reports.add(E);
//
//        List<model.Employee> input = new ArrayList<model.Employee>();
//        input.add(A);
//        input.add(B);
//        input.add(C);
//        input.add(D);
//        input.add(E);
//
//        // example 2
//        List<model.Employee> employees = new ArrayList<>();
//        model.Employee A = new model.Employee("A", 5 , new ArrayList<>(Arrays.asList(new String[]{"B", "C"})));
//        model.Employee B = new model.Employee("B", 3, new ArrayList<>());
//        model.Employee C = new model.Employee("C", 2 , new ArrayList<>(Arrays.asList(new String[]{"D", "E"})));
//        model.Employee D = new model.Employee("D", 4, new ArrayList<>());
//        model.Employee E = new model.Employee("E", 10, new ArrayList<>());
//        employees.add(A);
//        employees.add(B);
//        employees.add(C);
//        employees.add(D);
//        employees.add(E);
//
//
//        System.out.println(getHighestRating(employees));
//    }
//
//    static String getHighestRating(List<model.Employee> employees){
//        Map<String, List<String>> map = new HashMap<>();
//        Map<String, String> parent = new HashMap<>();
//        Map<String, String> rating = new HashMap<>();
//        String parentTmp = "";
//        for(model.Employee emp : employees){
//            map.put(emp.id, emp.subOrd);
//            parentTmp = emp.id;
//            rating.put(emp.id, emp.rating);
//            for( String sub :emp.subOrd){
//                parent.put(sub, emp.id);
//                rating.put(sub, sub.rating);
//            }
//        }
//        String root ="";
//
//        while(parentTmp!=null){
//            String parentTmp = parent.get(parentTmp);
//            if(parentTmp==null){
//                root = parentTmp;
//            }
//        }
//
//        traverseTree(root, map, rating);
//        return empAns;
//    }
//
//
//    static   Node traverseTree(String node, Map<String, List<String>> map,  Map<String, Integer> rating){
//        if(node== null) return null;
//
//        int totalRating = rating.get(node);
//        int count =1;
//        for(String sub : map.get(node)){
//            Node subNode = traverseTree(sub, map);
//            totalRating+= subNode.totalRating;
//            count+= subNode.count;
//        }
//        double avgRating = totalRating/(count+0.0);
//        if(avgRating > avgRatingAns){
//            empAns = node;
//            avgRatingAns = avgRating;
//        }
//        return new Node(totalRating, count);
//    }
//}
//}
