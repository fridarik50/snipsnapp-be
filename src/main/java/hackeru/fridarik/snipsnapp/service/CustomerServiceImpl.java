package hackeru.fridarik.snipsnapp.service;

import hackeru.fridarik.snipsnapp.dto.*;
import hackeru.fridarik.snipsnapp.entity.Barber;
import hackeru.fridarik.snipsnapp.entity.Customer;
import hackeru.fridarik.snipsnapp.error.PaginationException;
import hackeru.fridarik.snipsnapp.error.ResourceNotFoundException;
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
public class CustomerServiceImpl implements CustomerService {

    //props:
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public CustomerResponseDTO createCustomer(CustomerCreateDTO dto) {
        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        return modelMapper.map(customerRepository.save(modelMapper.map(dto, Customer.class)), CustomerResponseDTO.class);
    }

    @Override
    public EntityListDTO<CustomerResponseDTO> getAllCustomers(int pageNo, int pageSize, String sortDir, String... sortBy) {
        try {
            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.fromString(sortDir), sortBy);
            Page<Customer> cp = customerRepository.findAll(pageable);

            if (pageNo > cp.getTotalPages()) {
                throw new PaginationException("Page Number " + pageNo + " Exceeds totalPages " + cp.getTotalPages());
            }



            return new EntityListDTO<CustomerResponseDTO>(
                    cp.getTotalElements(),
                    cp.getNumber(),
                    cp.getSize(),
                    cp.getTotalPages(),
                    cp.isFirst(),
                    cp.isLast(),
                    cp.getContent().stream().map(c -> modelMapper.map(c, CustomerResponseDTO.class)).toList()
            );
        } catch (IllegalArgumentException e){
            throw new PaginationException(e.getMessage());
        }
    }

    @Override
    public CustomerResponseDTO getCustomerById(long id) {
        return modelMapper.map(getCustomerEntityOrThrow(id), CustomerResponseDTO.class);
    }

    @Override
    public CustomerResponseDTO updateCustomerInfo(long id, CustomerCreateDTO dto) {
        Customer customer = getCustomerEntityOrThrow(id);

        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setPassword(dto.getPassword());

        return modelMapper.map(customerRepository.save(customer), CustomerResponseDTO.class);
    }

    @Override
    public CustomerResponseDTO deleteCustomer(long id) {
        CustomerResponseDTO customerDto = modelMapper.map(getCustomerEntityOrThrow(id), CustomerResponseDTO.class);
        customerRepository.deleteById(id);
        return customerDto;
    }

    @Override
    public Customer getCustomerEntityOrThrow(long id) {
        return customerRepository.findById(id).orElseThrow(ResourceNotFoundException.newInstance("Customer", "id", id));
    }

    @Override
    public List<CustomerResponseDTO> searchByName(String name){
        String lowerCaseName = name.toLowerCase();
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream()
                .filter( b -> b.getName().toLowerCase().contains(lowerCaseName))
                .map(b -> modelMapper.map(b, CustomerResponseDTO.class))
                .collect(Collectors.toList());
    }
}
