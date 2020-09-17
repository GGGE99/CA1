/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import entities.Car;

/**
 *
 * @author marcg
 */
public class CarDTO {
    
     private Integer id;
    private int year;
    private String make;
    private String model;
    private double price;

    public CarDTO(Car car) {
        this.id = car.getId();
        this.year = car.getYear();
        this.make = car.getMake();
        this.model = car.getModel();
        this.price = car.getPrice();
    }
    
    
    
}
