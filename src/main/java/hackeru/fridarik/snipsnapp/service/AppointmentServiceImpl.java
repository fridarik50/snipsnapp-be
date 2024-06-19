package hackeru.fridarik.snipsnapp.service;

import hackeru.fridarik.snipsnapp.dto.*;
import hackeru.fridarik.snipsnapp.entity.Appointment;
import hackeru.fridarik.snipsnapp.entity.Barber;
import hackeru.fridarik.snipsnapp.entity.Customer;
import hackeru.fridarik.snipsnapp.error.PaginationException;
import hackeru.fridarik.snipsnapp.error.ResourceNotFoundException;
import hackeru.fridarik.snipsnapp.repository.AppointmentRepository;
import hackeru.fridarik.snipsnapp.repository.BarberRepository;
import hackeru.fridarik.snipsnapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    //props:
    private final BarberRepository barberRepository;
    private final CustomerRepository customerRepository;
    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;


    @Override
    public AppointmentResponseDTO createAppointment(AppointmentCreateDTO dto) {
        Barber barber = barberRepository.findById(dto.getBarber().getId()).orElseThrow(() -> new ResourceNotFoundException("barber", "id", dto.getBarber().getId()));
        Customer customer = customerRepository.findById(dto.getCustomer().getId()).orElseThrow(() -> new ResourceNotFoundException("customer", "id", dto.getCustomer().getId()));
        Appointment appointment = modelMapper.map(dto, Appointment.class);
        appointment.setBarber(barber);
        appointment.setCustomer(customer);
        appointment = appointmentRepository.save(appointment);
        return modelMapper.map(appointment, AppointmentResponseDTO.class);
    }

    @Override
    public AppointmentResponseDTO getAppointmentById(long id) {
        return modelMapper.map(getAppointmentEntityOrThrow(id), AppointmentResponseDTO.class);
    }

    @Override
    public AppointmentResponseDTO updateAppointmentInfo(long id, AppointmentCreateDTO dto) {
        Appointment appointment = getAppointmentEntityOrThrow(id);
        appointment.setDate(dto.getDate());
        appointment.setStartHour(dto.getStartHour());
        appointment.setEndingHour(dto.getEndingHour());
        appointment.setComments(dto.getComments());

        return modelMapper.map(appointmentRepository.save(appointment), AppointmentResponseDTO.class);
    }

    @Override
    public AppointmentResponseDTO deleteAppointment(long id) {
        AppointmentResponseDTO appointmentDto = modelMapper.map(getAppointmentEntityOrThrow(id), AppointmentResponseDTO.class);;
        appointmentRepository.deleteById(id);
        return appointmentDto;
    }

    @Override
    public Appointment getAppointmentEntityOrThrow(long id) {
        return appointmentRepository.findById(id).orElseThrow(ResourceNotFoundException.newInstance("Appointment", "id", id));
    }

    @Override
    public List<AppointmentResponseDTO> getAppointmentByBarber(long barberId) {
        Barber barber = barberRepository.findById(barberId).orElseThrow(() -> new ResourceNotFoundException("barber", "id", barberId));
        List<Appointment> appointments = appointmentRepository.findByBarber(barber);
        return appointments.stream().map( a -> modelMapper.map(a, AppointmentResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponseDTO> getAppointmentByCustomer(long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customer", "id", customerId));
        List<Appointment> appointments = appointmentRepository.findByCustomer(customer);
        return appointments.stream().map( a -> modelMapper.map(a, AppointmentResponseDTO.class))
                .collect(Collectors.toList());
    }
}
