package com.example.cardealer.services.car;

import com.example.cardealer.entities.car.CarsExportDto;

import java.util.List;

public interface CarService {

    List<CarsExportDto> selectAllToyotaCars();
}
