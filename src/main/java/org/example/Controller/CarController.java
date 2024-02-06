package org.example.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.example.Model.Car;
import org.example.Service.CarService;

import java.util.List;

public class CarController {

    static CarService carService;

    public CarController(){
        this.carService = new CarService();
    }

    /**
     * We will use this method to create & configure a Javalin api.
     * We will define endpoints pertaining to a specific HTTP verb
     * eg "get", "post" with app.get, app.post, etc.
     * Every endpoint will contain a URI (Uniform Resource Identifier)
     * , as well as a lambda expression informing Javalin how to
     * interact with the Context. The Context is a object that
     * manages both the HTTP request and response
     * @return
     */
    public Javalin getAPI(){
        Javalin api = Javalin.create();

        api.get("/health/",
                context ->
                {
                    context.result("the server is UP");
                }
        );
        api.get("/car/", CarController::getAllCarHandler);
        api.post("/car/", CarController::postCarHandler);
        return api;
    }

    public static void getAllCarHandler(Context context){
        List<Car> carList = carService.getAllCars();
        context.json(carList);
    }
    public static void postCarHandler(Context context){
        ObjectMapper om = new ObjectMapper();
        try{
            Car c = om.readValue(context.body(), Car.class);
            carService.insertCar(c);
//            201 - resource created
            context.status(201);
        } catch (JsonProcessingException e) {
//            Jackson was unable to parse the JSON, probably due to user error, so 400
            context.status(400);
        }
    }
}
