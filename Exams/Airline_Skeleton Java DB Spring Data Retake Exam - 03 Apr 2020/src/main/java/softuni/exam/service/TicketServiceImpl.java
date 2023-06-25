package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Passenger;
import softuni.exam.models.Plane;
import softuni.exam.models.Ticket;
import softuni.exam.models.Town;
import softuni.exam.models.dto.Ticket.ImportTicketDto;
import softuni.exam.models.dto.Ticket.ImportTicketsDto;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.repository.TicketRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private static final String TICKET_XML_URL = "Exams/Airline_Skeleton Java DB Spring Data Retake Exam - 03 Apr 2020/src/main/resources/files/xml/tickets.xml";

    private final TicketRepository ticketRepository;
    private final TownRepository townRepository;
    private final PassengerRepository passengerRepository;
    private final PlaneRepository planeRepository;

    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;

    public TicketServiceImpl(TicketRepository ticketRepository,
                             TownRepository townRepository,
                             PassengerRepository passengerRepository,
                             PlaneRepository planeRepository,
                             XmlParser xmlParser,
                             ValidationUtil validationUtil,
                             ModelMapper mapper) {
        this.ticketRepository = ticketRepository;
        this.townRepository = townRepository;
        this.passengerRepository = passengerRepository;
        this.planeRepository = planeRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.ticketRepository.count()>0;
    }

    @Override
    public String readTicketsFileContent() {
        try {
            return Files.readString(Path.of(TICKET_XML_URL));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String importTickets() {
        ImportTicketsDto importTicketsDto = this.xmlParser.parseXml(ImportTicketsDto.class, readTicketsFileContent());
        StringBuilder output = new StringBuilder();
        for (ImportTicketDto ticket : importTicketsDto.getTickets()) {
            if(!validationUtil.isValid(ticket)){
                output.append("Invalid Ticket!").append("\n");
                continue;
            }
            Optional<Town> fromTown = this.townRepository.findTownByName(ticket.getFromTown().getName());
            Optional<Town> toTown = this.townRepository.findTownByName(ticket.getToTown().getName());
            Optional<Passenger> passenger = this.passengerRepository.getPassengerByEmail(ticket.getPassenger().getEmail());
            Optional<Plane> plane = this.planeRepository.getPlaneByRegisterNumber(ticket.getPlane().getRegisterNumber());

            Ticket ticketToPersist = this.mapper.map(ticket, Ticket.class);

            if(fromTown.isEmpty() || toTown.isEmpty() || passenger.isEmpty() || plane.isEmpty()){
                output.append("Invalid Ticket!").append("\n");
                continue;
            }
            ticketToPersist.setFromTown(fromTown.get());
            ticketToPersist.setToTown(toTown.get());
            ticketToPersist.setPassengers(passenger.get());
            ticketToPersist.setPlane(plane.get());

            this.ticketRepository.save(ticketToPersist);
            output.append(String.format("Successfully imported Ticket %s - %s",ticket.getFromTown().getName(),ticket.getToTown().getName())).append("\n");
        }

        return String.valueOf(output);
    }
}
