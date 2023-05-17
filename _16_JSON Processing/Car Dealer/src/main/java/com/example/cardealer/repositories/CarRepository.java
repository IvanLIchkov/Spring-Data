package com.example.cardealer.repositories;

import com.example.cardealer.entities.car.Car;
import com.example.cardealer.entities.car.CarImportDto;
import com.example.cardealer.entities.car.CarWithPartsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByMakeLikeOrderByModelAscTravelledDistanceDesc(String make);

    @Query("select new com.example.cardealer.entities.car.CarWithPartsDto(c.make,c.model,c.travelledDistance, c.parts) from Car c ")
    List<CarWithPartsDto> getAllCarsWithParts();
}
