package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Passenger;
import softuni.exam.models.Town;
import softuni.exam.models.dto.passenger.ExportPassengerDto;
import softuni.exam.models.dto.passenger.ImportPassengerDto;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final static String PASSENGERS_JSON_URL = "Exams/Airline_Skeleton Java DB Spring Data Retake Exam - 03 Apr 2020/src/main/resources/files/json/passengers.json";

    private final PassengerRepository passengerRepository;
    private final TownRepository townRepository;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final ModelMapper mapper;


    public PassengerServiceImpl(PassengerRepository passengerRepository, TownRepository townRepository, ValidationUtil validationUtil, Gson gson, ModelMapper mapper) {
        this.passengerRepository = passengerRepository;
        this.townRepository = townRepository;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.mapper = mapper;
    }


    @Override
    public boolean areImported() {
        return this.passengerRepository.count()>0;
    }

    @Override
    public String readPassengersFileContent()  {
        try {
            return Files.readString(Path.of(PASSENGERS_JSON_URL));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String importPassengers() throws IOException {
        ImportPassengerDto[] importPassengerDtos = this.gson.fromJson(readPassengersFileContent(), ImportPassengerDto[].class);
        StringBuilder output = new StringBuilder();
        for (ImportPassengerDto importPassengerDto : importPassengerDtos) {
            if(!validationUtil.isValid(importPassengerDto)){
                output.append("Invalid Passenger!").append("\n");
                continue;
            }
            Optional<Town> townByName = townRepository.findTownByName(importPassengerDto.getTown());
            Optional<Passenger> passengerByFirstNameAndLastName = this.passengerRepository.getPassengerByFirstNameAndLastName(importPassengerDto.getFirstName(), importPassengerDto.getLastName());
            if(townByName.isEmpty() || passengerByFirstNameAndLastName.isPresent()){
                output.append("Invalid Passenger!").append("\n");
                continue;
            }
            Passenger passengerToPersist = this.mapper.map(importPassengerDto, Passenger.class);
            passengerToPersist.setTown(townByName.get());
            output.append(String.format("Successfully imported Passenger %s - %s",importPassengerDto.getLastName(),importPassengerDto.getEmail()))
                    .append("\n");
            this.passengerRepository.save(passengerToPersist);
        }
        return String.valueOf(output);
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {

        List<String> output = this.passengerRepository.passengers().stream().map(ExportPassengerDto::toString).toList();
        return String.join("",output);
    }
}
