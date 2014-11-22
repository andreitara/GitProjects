package DAO.tables;

/**
 * Created by Andrei on 11/22/2014.
 */
public class CarDriver {

    private String nameDriver;
    private String nameCar;
    private int yearCar;

    public CarDriver() {
    }

    public String getNameDriver() {
        return nameDriver;
    }

    public void setNameDriver(String nameDriver) {
        this.nameDriver = nameDriver;
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public int getYearCar() {
        return yearCar;
    }

    public void setYearCar(int yearCar) {
        this.yearCar = yearCar;
    }

    public void print(){
        System.out.println("View: " + nameDriver + " drive " + nameCar + "-" + yearCar );
    }
}
