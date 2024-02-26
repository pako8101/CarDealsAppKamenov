package com.example.cardealsapp.repository;


import com.example.cardealsapp.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

   // List<Car> findAllOrderByPicturesCountThenByMake();
}
