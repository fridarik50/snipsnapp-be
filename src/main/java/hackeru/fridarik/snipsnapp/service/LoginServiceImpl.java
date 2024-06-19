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

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService , UserDetailsService {

    private final BarberRepository barberRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    @Override
    public BarberResponseDTO loginBarber(LoginDTO dto) {
        Optional<Barber> barber = barberRepository.findByEmail(dto.getEmail());
        if (barber.isEmpty()) {
            return null;
        }
        return modelMapper.map(barber.get(), BarberResponseDTO.class);
    }

    @Override
    public CustomerResponseDTO loginCustomer(LoginDTO dto) {
        Optional<Customer> customer = customerRepository.findByEmail(dto.getEmail());
        if (customer.isEmpty()) {
            return null;
        }
        return modelMapper.map(customer.get(), CustomerResponseDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> optCustomer =  customerRepository.findByEmail(email);
        if(optCustomer.isPresent()){
            Customer customer = optCustomer.get();
            return new UserAuthDetails(customer);
        }
        Optional<Barber> optBarber = barberRepository.findByEmail(email);
        if(optBarber.isPresent()){
            Barber barber = optBarber.get();
            return new UserAuthDetails(barber);
        }

        throw new UsernameNotFoundException("Email " + email + " was not found");

    }
}
