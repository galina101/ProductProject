package org.example;

import io.javalin.Javalin;
import org.example.Controller.CarController;

/**
 * We run the main method,
 * which instantiates the CarController,
 *      which instantiates the CarService,
 * We start the api.
 */
public class Main {


    public static void main(String[] args) {

        CarController carController = new CarController();

        Javalin api = carController.getAPI();
        api.start(9002);
    }
}