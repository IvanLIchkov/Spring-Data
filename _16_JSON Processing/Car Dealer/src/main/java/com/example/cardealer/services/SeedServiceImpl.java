package com.example.cardealer.services;

import com.example.cardealer.entities.car.Car;
import com.example.cardealer.entities.car.CarImportDto;
import com.example.cardealer.entities.customer.Customer;
import com.example.cardealer.entities.customer.CustomerImportDto;
import com.example.cardealer.entities.part.Part;
import com.example.cardealer.entities.part.PartImportDto;
import com.example.cardealer.entities.sale.DISCOUNT;
import com.example.cardealer.entities.sale.Sale;
import com.example.cardealer.entities.supplier.Supplier;
import com.example.cardealer.entities.supplier.SupplierImportDto;
import com.example.cardealer.repositories.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

@Service
public class SeedServiceImpl implements SeedService {
    private static final String RESOURCE_PATH = "/Users/scopi/Desktop/Spring-Data/_16_JSON Processing/Car Dealer/src/main/resources/resourcesForCarDealer/";
    private static final String CARS_PATH = RESOURCE_PATH + "cars.json";
    private static final String CUSTOMERS_PATH = RESOURCE_PATH + "customers.json";
    private static final String PARTS_PATH = RESOURCE_PATH + "parts.json";
    private static final String SUPPLIERS_PATH = RESOURCE_PATH + "suppliers.json";

    private final static Random random = new Random();
    private static FileReader fileReader;
    private final ModelMapper mapper;
    private final Gson gson;

    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;

    public SeedServiceImpl(SupplierRepository supplierRepository, PartRepository partRepository, CarRepository carRepository, CustomerRepository customerRepository, SaleRepository saleRepository) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.mapper = new ModelMapper();
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void seedCars() throws FileNotFoundException {
        fileReader = new FileReader(CARS_PATH);
        CarImportDto[] carImportDtos = this.gson.fromJson(fileReader, CarImportDto[].class);

        List<Car> cars = Arrays.stream(carImportDtos)
                .map(carImportDto -> this.mapper.map(carImportDto, Car.class))
                .map(this::setRandomParts)
                .toList();

        this.carRepository.saveAll(cars);
    }

    private Car setRandomParts(Car car){
        long partCount = this.partRepository.count();

        int randomNumberOfParts = random.nextInt(5 - 3)+3;

        Set<Part> parts = new HashSet<>();
        for (int i = 1 ; i <=randomNumberOfParts ; i++) {
            long randomPartId = random.nextLong(partCount)+1;

            Optional<Part> part = this.partRepository.findById(randomPartId);

            parts.add(part.get());
        }
        car.setParts(parts);
        return car;
    }

    @Override
    public void seedCustomers() throws FileNotFoundException {
        fileReader = new FileReader(CUSTOMERS_PATH);

        CustomerImportDto[] customerImportDtos =this.gson.fromJson(fileReader, CustomerImportDto[].class);

        List<Customer> customers = Arrays.stream(customerImportDtos)
                .map(customerImportDto -> this.mapper.map(customerImportDto, Customer.class))
                .toList();
        this.customerRepository.saveAll(customers);
    }

    @Override
    public void seedParts() throws FileNotFoundException {
        fileReader = new FileReader(PARTS_PATH);

        PartImportDto[] partImportDtos = this.gson.fromJson(fileReader, PartImportDto[].class);

        List<Part> parts = Arrays.stream(partImportDtos)
                .map(partImportDto -> this.mapper.map(partImportDto, Part.class))
                .map(this::setRandomSupplier)
                .toList();
        this.partRepository.saveAll(parts);


    }
    private Part setRandomSupplier(Part part){
        long suppliersDbCount = this.supplierRepository.count();

        int randomSupplierId = random.nextInt((int) suppliersDbCount)+1;

        part.setSupplier(this.supplierRepository.findById((long) randomSupplierId).get());
        return part;
    }

    @Override
    public void seedSuppliers() throws FileNotFoundException {
        fileReader = new FileReader(SUPPLIERS_PATH);

        SupplierImportDto[] supplierImportDtos = this.gson.fromJson(fileReader, SupplierImportDto[].class);

        List<Supplier> suppliers = Arrays.stream(supplierImportDtos)
                .map(supplierImportDto -> this.mapper.map(supplierImportDto, Supplier.class))
                .toList();
        this.supplierRepository.saveAll(suppliers);
    }

    @Override
    public void seedSales() {
        long carsCount = this.carRepository.count();

        Set<Sale> sales = new HashSet<>();

        for (int i = 0; i < carsCount; i++) {
            long randomCardId = random.nextLong(carsCount)+1;
            Car car = this.carRepository.findById(randomCardId).get();

            long customersCount = this.customerRepository.count();
            long randomCustomerId = random.nextLong(customersCount)+1;
            Customer customer = this.customerRepository.findById(randomCustomerId).get();

            DISCOUNT[] values = DISCOUNT.values();
            int lenght= values.length;
            int randIndex = random.nextInt(lenght);

            Sale sale;
            if(customer.isYoungDriver()){
                sale = new Sale(values[randIndex].getNumVal(),car,customer);
            }else {
                sale = new Sale(car, customer);
            }

            sales.add(sale);
        }
        this.saleRepository.saveAll(sales);
    }
}
