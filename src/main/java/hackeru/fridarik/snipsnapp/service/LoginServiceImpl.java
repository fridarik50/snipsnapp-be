package hackeru.fridarik.snipsnapp.service;

import hackeru.fridarik.snipsnapp.dto.BarberResponseDTO;
import hackeru.fridarik.snipsnapp.dto.CustomerResponseDTO;
import hackeru.fridarik.snipsnapp.dto.LoginDTO;
import hackeru.fridarik.snipsnapp.entity.Barber;
import hackeru.fridarik.snipsnapp.entity.Customer;
import hackeru.fridarik.snipsnapp.repository.BarberRepository;
import hackeru.fridarik.snipsnapp.repository.CustomerRepository;
import hackeru.fridarik.snipsnapp.security.UserAuthDetails;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService , UserDetailsService {

    private final BarberRepository barberRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    @Override
    public BarberResponseDTO loginBarber(LoginDTO dto) {
        Barber barber = barberRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (barber == null) {
            return null;
        }
        return modelMapper.map(barber, BarberResponseDTO.class);
    }

    @Override
    public CustomerResponseDTO loginCustomer(LoginDTO dto) {
        Customer customer = customerRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (customer == null) {
            return null;
        }
        return modelMapper.map(customer, CustomerResponseDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Barber> barberOpt = barberRepository.findByEmail(email);
        return barberOpt.map(UserAuthDetails::new)
                .orElse(customerRepository.findByEmail(email).map(UserAuthDetails::new)
                        .orElseThrow(() -> new UsernameNotFoundException("Email " + email + "was not found")));

    }
}
