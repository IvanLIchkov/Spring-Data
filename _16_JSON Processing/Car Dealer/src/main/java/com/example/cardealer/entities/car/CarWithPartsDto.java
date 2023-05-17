package com.example.cardealer.entities.car;

import com.example.cardealer.entities.part.Part;
import com.example.cardealer.entities.part.PartNameAndPriceDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CarWithPartsDto implements Serializable {
    private CarImportDto car;
    private Set<Part> parts;

    public CarWithPartsDto() {
//        this.parts = new HashSet<>();
    }

    public CarWithPartsDto(String make, String model, Long travelledDistance, Set<Part> parts) {
        this();
        this.car = new CarImportDto(make,model,travelledDistance);
        this.parts = parts;
    }

    public CarImportDto getCar() {
        return car;
    }

    public void setCar(CarImportDto car) {
        this.car = car;
    }

    //    public List<PartNameAndPriceDto> getParts() {
//        return parts;
//    }

//    public void setParts(Set<Part> parts) {
//        ModelMapper mapper = new ModelMapper();
//        List<PartNameAndPriceDto> collect = Arrays.stream(mapper.map(parts, PartNameAndPriceDto[].class)).collect(Collectors.toList());
//        this.parts = collect;
//    }
}
