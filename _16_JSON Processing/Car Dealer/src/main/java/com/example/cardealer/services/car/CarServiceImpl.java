package com.example.cardealer.services.car;

import com.example.cardealer.entities.car.Car;
import com.example.cardealer.entities.car.CarsExportDto;
import com.example.cardealer.repositories.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<CarsExportDto> selectAllToyotaCars() {
        List<Car> toyotaCars = this.carRepository.findAllByMakeLikeOrderByModelAscTravelledDistanceDesc("Toyota");
        ModelMapper mapper = new ModelMapper();

        List<CarsExportDto> collect = Arrays.stream(mapper.map(toyotaCars, CarsExportDto[].class)).collect(Collectors.toList());

        return collect;
    }
}
