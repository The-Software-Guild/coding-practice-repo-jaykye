package CarLot.dao;

import CarLot.dto.Car;

import java.util.List;

public interface CarLotDAO {
    public Car addCar(String VIN, Car car);

    public Car getCar(String VIN);
    public List<Car> getCars();

    public void editCar(String VIN, Car car);

    public Car removeCar(String VIN);
}