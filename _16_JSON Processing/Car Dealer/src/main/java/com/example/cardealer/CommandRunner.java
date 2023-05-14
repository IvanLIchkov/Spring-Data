package com.example.cardealer;

import com.example.cardealer.entities.car.CarsExportDto;
import com.example.cardealer.services.seed.SeedService;
import com.example.cardealer.services.car.CarService;
import com.example.cardealer.services.customer.CustomerService;
import com.example.cardealer.services.supplier.SupplierService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final CustomerService customerService;
    private final CarService carService;
    private final SupplierService supplierService;

    private final Gson gson;

    public CommandRunner(SeedService seedService, CustomerService customerService, Gson gson, CarService carService, SupplierService supplierService) {
        this.seedService = seedService;
        this.customerService = customerService;
        this.carService = carService;
        this.supplierService = supplierService;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void run(String... args) throws Exception {
//        seedService.seedAll();
//        printAllToyotaCars();
//        System.out.println(this.gson.toJson(this.supplierService.selectAllSupplierWithNoImport())); print all local suppliers
        this.carService.selectAllCarsWithParts();


    }

    private void printAllToyotaCars() {
        List<CarsExportDto> carsExportDtos = this.carService.selectAllToyotaCars();

        String json = this.gson.toJson(carsExportDtos);

        System.out.println(json);
    }

}
