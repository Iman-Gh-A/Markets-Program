package ir.ac.kntu.model.classes.persons;

import java.lang.IllegalArgumentException;

public class Person {
    private String name;
    private String id;

    public Person(String name, String id) {
        if (!name.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("The name should be a-z and A-Z");
        }
        if (!id.matches("\\d{10}")) {
            throw new IllegalArgumentException("The ID should be 10 number");
        }
        this.name = name;
        this.id = id;
    }

    public void setId(String id) {
        if (!id.matches("\\d{10}")) {
            throw new IllegalArgumentException("The ID should be 10 number");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("The name should be a-z and A-Z");
        }
        this.name = name;
    }

    public String getId() {
        return id;
    }
}
