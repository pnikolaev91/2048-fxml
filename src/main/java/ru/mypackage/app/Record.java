package ru.mypackage.app;

import java.io.*;

public class Record implements Serializable {
    private static final long serialVersionUID = -4774892012473509211L;
    private Long aLong = 0L;

    public Long get() {
        return aLong;
    }

    public void set(Long aLong) {
        this.aLong = aLong;
    }

    public void save() {
        // Serialization
        try (FileOutputStream file = new FileOutputStream("record")) {
            try (ObjectOutputStream out = new ObjectOutputStream(file)) {
                out.writeObject(this);
            }
        } catch (IOException ignore) {
        }
    }

    public static Record load() {
        try (FileInputStream file = new FileInputStream("record")) {
            try (ObjectInputStream in = new ObjectInputStream(file)) {
                try {
                    return (Record) in.readObject();
                } catch (ClassNotFoundException e) {
                    return new Record();
                }
            }
        } catch (Exception e) {
            return new Record();
        }
    }

    @Override
    public String toString() {
        return String.valueOf(aLong);
    }
}
