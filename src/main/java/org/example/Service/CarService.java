package org.example.Service;

import org.example.Model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarService {

    List<Car> carList;
    public CarService(){
        carList = new ArrayList<>();
    }

    public List<Car> getAllCars(){
        return carList;
    }

    public void insertCar(Car car){
        carList.add(car);
    }
}
