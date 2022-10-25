import model.Employee;
import model.Input;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class App {

    public static void main(String[] args) {
        List<Input> inputList = new ArrayList<>();

        inputList.add(new Input(2, "Ratan Tata", null, "Mumbai"));
        inputList.add(new Input(3, "Gautam Adani", 2, "Ahmedabad"));
        inputList.add(new Input(4, "Mukesh Ambani", 3, "Mumbai"));
        inputList.add(new Input(5, "L. N. Mittal", 2, "London"));
        inputList.add(new Input(6, "Cyrus Poonawalla", 4, "Pune"));

        EmpService empService = new EmpService();
        empService.addEmployees(inputList);
        Employee ceo = empService.updateCEO();
        System.out.println(ceo.getName());

        System.out.println(empService.maxDepth());

        List<Employee> managers  = empService.getAllManagers();
        print(managers);
        test();

    }
    static void print(List<Employee> employees){
        for( Employee emp :employees){
            System.out.println(emp.getName());
        }
    }


    static void test(){
         PriorityQueue<Integer> heap = new PriorityQueue<>((a, b)-> a-b);

        heap.add(3);
        heap.add(2);
        heap.add(1);
        heap.add(22);
        System.out.println(heap.peek());
    }

}
//hr@interviewvector.com
//Subj : Omprakash Yadav|Dunzo|SSE 1|Live Coding (90 Mins)
