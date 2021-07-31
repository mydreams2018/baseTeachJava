package io;

import java.io.IOException;
import java.io.Serializable;

public class MyTs extends MyPerson implements Serializable {

    private static final long serialVersionUID = 42L;

    private String name = "kungreat";
    transient private Integer age = 18;
    public static String group = "GP";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyTs{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.defaultWriteObject();

    }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException{
        stream.defaultReadObject();
//        this.age=18;
    }

}
