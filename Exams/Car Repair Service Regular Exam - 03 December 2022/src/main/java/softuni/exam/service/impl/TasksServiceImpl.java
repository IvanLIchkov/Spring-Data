package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.importTasks.ImportTaskXml;
import softuni.exam.models.dto.importTasks.ImportTasksXml;
import softuni.exam.models.entity.*;
import softuni.exam.repository.CarsRepository;
import softuni.exam.repository.MechanicsRepository;
import softuni.exam.repository.PartsRepository;
import softuni.exam.repository.TasksRepository;
import softuni.exam.service.TasksService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO: Implement all methods
@Service
public class TasksServiceImpl implements TasksService {
    private static String TASKS_FILE_PATH = "/Users/scopi/Desktop/Spring-Data/Exams/Car Repair Service Regular Exam - 03 December 2022/src/main/resources/files/xml/tasks.xml";

    private TasksRepository tasksRepository;
    private CarsRepository carsRepository;
    private MechanicsRepository mechanicsRepository;
    private PartsRepository partsRepository;

    private FileUtil fileUtil;
    private ValidationUtil validationUtil;
    private XmlParser xmlParser;
    private ModelMapper mapper;

    public TasksServiceImpl(TasksRepository tasksRepository,
                            CarsRepository carsRepository,
                            MechanicsRepository mechanicsRepository,
                            PartsRepository partsRepository,
                            FileUtil fileUtil, ValidationUtil validationUtil,
                            XmlParser xmlParser,
                            ModelMapper mapper) {
        this.tasksRepository = tasksRepository;
        this.carsRepository = carsRepository;
        this.mechanicsRepository = mechanicsRepository;
        this.partsRepository = partsRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.tasksRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return Files.readString(Path.of(TASKS_FILE_PATH));
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        ImportTasksXml importTasksXml = this.xmlParser.parseXml(ImportTasksXml.class, readTasksFileContent());
        StringBuilder output = new StringBuilder();
        for (ImportTaskXml taskXml : importTasksXml.getTaskXmls()) {
            output.append(System.lineSeparator());
            Optional<Car> carById = this.carsRepository.findCarById(taskXml.getCar().getId());
            Optional<Part> partById = this.partsRepository.findPartById(taskXml.getPart().getId());
            Optional<Mechanic> mechanicByFirstName = this.mechanicsRepository.findMechanicByFirstName(taskXml.getMechanic().getFirstName());
            if(carById.isEmpty() || partById.isEmpty() || mechanicByFirstName.isEmpty() || !this.validationUtil.isValid(taskXml)){
                output.append("Invalid task");
                continue;
            }
            Task taskToPersist = this.mapper.map(taskXml, Task.class);
            taskToPersist.setCar(carById.get());
            taskToPersist.setPart(partById.get());
            taskToPersist.setMechanic(mechanicByFirstName.get());
            this.tasksRepository.save(taskToPersist);
            output.append(String.format("Successfully imported task %.2f",taskXml.getPrice()));

        }

        return String.valueOf(output).trim();
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {
        List<Task> tasks = this.tasksRepository.getTaskByCar_CarTypeOrderByPriceDesc(CarType.coupe);
        List<String> output = tasks.stream().map(this::outputToString).collect(Collectors.toList());

        return String.join("\n", output);
    }

    private String outputToString(Task task){
        String carMake = task.getCar().getCarMake();
        String carModel = task.getCar().getCarModel();
        int kilometers = task.getCar().getKilometers();
        String firstName = task.getMechanic().getFirstName();
        String lastName = task.getMechanic().getLastName();
        Long id = task.getId();
        double engine = task.getCar().getEngine();
        BigDecimal price = task.getPrice();
        String output = String.format("Car %s %s with %dkm\n" +
                "-Mechanic: %s %s - task №%d:\n" +
                " --Engine: %.1f\n" +
                "---Price: %.2f$", carMake, carModel, kilometers, firstName, lastName, id, engine, price);
        return output.trim();
    }
}
