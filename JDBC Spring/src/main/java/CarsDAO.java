import java.util.List;

/**
 * Created by Andrei on 11/20/2014.
 */
public interface CarsDAO {

    public Car getCarByID(int id);

    public void addCar(Car car);

    public Car getCarByNameAndAge(String name, int age);

    public List<Car> getAllCars();

}
