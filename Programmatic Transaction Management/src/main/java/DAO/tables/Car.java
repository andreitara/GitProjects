package DAO.tables;

/**
 * Created by Andrei on 11/23/2014.
 */
public class Car {

    private int id;
    private String mark;
    private int year;
    private int engine;

    public Car() {
    }

    public Car(String mark, int year, int engine) {
        this.mark = mark;
        this.year = year;
        this.engine = engine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getEngine() {
        return engine;
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }

    public void print(){
        System.out.println(id + " " + mark + " " + year + " " + engine);
    }

}
