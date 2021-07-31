package io;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class MyPerson implements Serializable {

    private void readObjectNoData() throws ObjectStreamException {
        System.out.println("readObjectNoData");
    }
}
