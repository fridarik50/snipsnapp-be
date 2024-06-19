package hackeru.fridarik.snipsnapp.service;

import hackeru.fridarik.snipsnapp.dto.*;
import hackeru.fridarik.snipsnapp.entity.Customer;

import java.util.List;

public interface CustomerService {

    CustomerResponseDTO createCustomer(CustomerCreateDTO dto);

    EntityListDTO<CustomerResponseDTO> getAllCustomers(int pageNo, int pageSize, String sortDir, String... sortBy);

    CustomerResponseDTO getCustomerById(long id);

    CustomerResponseDTO updateCustomerInfo(long id, CustomerCreateDTO dto);

    CustomerResponseDTO deleteCustomer(long id);

    Customer getCustomerEntityOrThrow(long id);

    List<CustomerResponseDTO> searchByName(String name);
}
