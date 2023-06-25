package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.Passenger;
import softuni.exam.models.dto.passenger.ExportPassengerDto;

import java.util.List;
import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    Optional<Passenger> getPassengerByFirstNameAndLastName(String firstName, String lastName);

    Optional<Passenger> getPassengerByEmail(String email);


    @Query("select new softuni.exam.models.dto.passenger.ExportPassengerDto(p.firstName, p.lastName, p.email, p.phoneNumber, count(t)) from Passenger p " +
            "join Ticket t on t.passengers.id = p.id " +
            "group by p.id " +
            "order by count(t) desc, p.email")
    List<ExportPassengerDto> passengers();
}
