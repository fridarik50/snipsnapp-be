package hackeru.fridarik.snipsnapp;

import hackeru.fridarik.snipsnapp.dto.*;
import hackeru.fridarik.snipsnapp.service.AppointmentService;
import hackeru.fridarik.snipsnapp.service.BarberService;
import hackeru.fridarik.snipsnapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class SnipSnappApplication implements CommandLineRunner {

    @Autowired
    BarberService barberService;

    @Autowired
    CustomerService customerService;
    @Autowired
    AppointmentService appointmentService;
    public static void main(String[] args) {
        SpringApplication.run(SnipSnappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*BarberCreateDTO barberCreateDTO = new BarberCreateDTO(
                "Shaul Kaldeta",
                "Men's Haircuts, Boy's Haircuts",
                "14 years, been a barber since military service.",
                "Sunday-Thursday: 16:00-21:00, Friday: 08:30-14:00",
                "Helmonit 22 street, Rishon Le Zion",
                "050-1234567",
                "Shaull@gmail.com",
                "Zvika1234"
        );
        BarberResponseDTO r1 = barberService.createBarber(barberCreateDTO);
        System.out.println(r1);

        BarberCreateDTO barberCreateDTO2 = new BarberCreateDTO(
                "David Cohen",
                "Men's Haircuts, Women's Haircuts, Children's Haircuts",
                "18 years, I've been cutting hair since High School.",
                "Sunday-Thursday: 09:00-15:00 & 17:00-21:00, Friday: 08:00-13:00",
                "HaGefen 7 street, Rishon Le Zion",
                "050-2345678",
                "davidtheking@gmail.com",
                "Itzchak2468"
        );
        BarberResponseDTO r2 = barberService.createBarber(barberCreateDTO2);
        System.out.println(r2);

        CustomerCreateDTO customerCreateDTO = new CustomerCreateDTO(
                "Shlomo Fridlender",
                "050-1234567",
                "shlomothewise@gmail.com",
                "Arik1234"
        );

        CustomerResponseDTO r3 = customerService.createCustomer(customerCreateDTO);
        System.out.println(r3);

        CustomerCreateDTO customerCreateDTO2 = new CustomerCreateDTO(
                "Hertzel Shapira",
                "050-9876543",
                "thesear@gmail.com",
                "Ori4567"
        );

        CustomerResponseDTO r4 = customerService.createCustomer(customerCreateDTO2);
        System.out.println(r4);

        AppointmentCreateDTO app1 = new AppointmentCreateDTO(LocalDate.parse("2024-06-14"), LocalTime.parse("11:00"),  LocalTime.parse("11:30"), "", r1, r3);
        AppointmentResponseDTO appRes1 =  appointmentService.createAppointment(app1);
        System.out.println(appRes1);

        AppointmentCreateDTO app2 = new AppointmentCreateDTO(LocalDate.parse("2024-06-16"), LocalTime.parse("12:00"),  LocalTime.parse("12:30"), "", r1, r3);

        AppointmentResponseDTO appRes2 =  appointmentService.createAppointment(app2);
        AppointmentCreateDTO app3 = new AppointmentCreateDTO(LocalDate.parse("2024-06-17"), LocalTime.parse("13:30"),  LocalTime.parse("14:00"), "", r1, r3);
        AppointmentResponseDTO appRes3 =  appointmentService.createAppointment(app3);*/
    }
}
