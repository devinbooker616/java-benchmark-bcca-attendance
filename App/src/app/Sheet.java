package app;

import java.io.*;

public class Sheet implements java.io.Serializable {
    String name;
    String day;
    String timeOfArrival;

    public Sheet(String name, String timeOfArrival, String day) {
        this.name = name;
        this.day = day;
        this.timeOfArrival = timeOfArrival;
    }

}