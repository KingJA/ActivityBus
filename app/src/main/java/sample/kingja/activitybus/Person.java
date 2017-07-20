package sample.kingja.activitybus;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2017/7/19 14:18
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Person implements Serializable {
    private String name;

    public Person(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
