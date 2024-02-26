package com.example.cardealsapp.service;





import com.example.cardealsapp.entities.Car;

import java.io.IOException;


public interface CarService {

    boolean areImported();

    String readCarsFileContent() throws IOException;
	
	String importCars() throws IOException;

    String getCarsOrderByPicturesCountThenByMake();

    Car findById(Long car);
}
