package practice.generic;

/**
 * Created by omprakash.yadav on 07/03/16.
 */
 class Box<Txx> {

    private Txx t;

    public void add(Txx t) {
        this.t = t;
    }

    public Txx get() {
        return t;
    }
}

public class SampleGeneric {

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<Integer>();
        Box<String> stringBox = new Box<String>();

        integerBox.add(55);
        stringBox.add("enigma");

        System.out.println(integerBox.get());
        System.out.println(stringBox.get());

    }

}
