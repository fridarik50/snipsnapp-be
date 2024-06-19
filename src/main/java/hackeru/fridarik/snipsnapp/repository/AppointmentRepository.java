package hackeru.fridarik.snipsnapp.repository;

import hackeru.fridarik.snipsnapp.entity.Appointment;
import hackeru.fridarik.snipsnapp.entity.Barber;
import hackeru.fridarik.snipsnapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByBarber(Barber barber);

    List<Appointment> findByCustomer(Customer customer);
}
