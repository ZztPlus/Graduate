package info;

import java.sql.Connection;

public class User {
    private  String id;
    private  String number;

    public String getNumber() { return number; }

    public String getId() {
        return id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setId(String id) { this.id = id; }

    public String toString() {
        return " "+ id + " " + number +" \n";
    }
}
