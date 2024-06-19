package hackeru.fridarik.snipsnapp.service;

import hackeru.fridarik.snipsnapp.dto.*;
import hackeru.fridarik.snipsnapp.entity.Appointment;
import hackeru.fridarik.snipsnapp.entity.Barber;
import hackeru.fridarik.snipsnapp.entity.Customer;

import java.util.List;

public interface AppointmentService {

    AppointmentResponseDTO createAppointment(AppointmentCreateDTO dto);

//    BarberListDTO getAllBarbers(int pageNo, int pageSize, String sortDir, String... sortBy);

    AppointmentResponseDTO getAppointmentById(long id);

    AppointmentResponseDTO updateAppointmentInfo(long id, AppointmentCreateDTO dto);

    AppointmentResponseDTO deleteAppointment(long id);

    Appointment getAppointmentEntityOrThrow(long id);

    List<AppointmentResponseDTO> getAppointmentByBarber(long barberId);
    List<AppointmentResponseDTO> getAppointmentByCustomer(long customerId);
}
