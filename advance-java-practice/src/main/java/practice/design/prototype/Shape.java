package practice.design.prototype;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by omprakash.yadav on 13/03/16.
 */
public abstract class Shape implements Cloneable {

    @Getter @Setter
    private String id;
    @Getter
    protected String type;

    public abstract void draw();
//    ObjectInputStream in=new ObjectInputStream(new FileInputStream("f.txt"));
    @Override
    public Object clone() {
        Object clone = null;

        try {
            clone = super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }





}
