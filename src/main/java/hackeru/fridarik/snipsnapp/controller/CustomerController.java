package hackeru.fridarik.snipsnapp.controller;

import hackeru.fridarik.snipsnapp.dto.*;
import hackeru.fridarik.snipsnapp.service.CustomerService;
import hackeru.fridarik.snipsnapp.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<EntityListDTO<CustomerResponseDTO>> getAllCustomers(
            @RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "4") int pageSize,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String... sortBy
    ){
        return ResponseEntity.ok(customerService.getAllCustomers(pageNo, pageSize, sortDir, sortBy));
    }

    @PostMapping
    public ResponseEntity<AuthDTO<CustomerResponseDTO>> createCustomer(
            @RequestBody @Valid CustomerCreateDTO dto,
            UriComponentsBuilder uriBuilder
    ){
        CustomerResponseDTO response = customerService.createCustomer(dto);
        String token = jwtService.createToken(response.getEmail());
        AuthDTO<CustomerResponseDTO> authDTO = new AuthDTO<CustomerResponseDTO>(response, token);
        URI uri = uriBuilder.path("/api/v1/customers/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(authDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable long id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomerById(
            @PathVariable long id,
            @RequestBody @Valid CustomerCreateDTO dto
    ){
        return ResponseEntity.ok(customerService.updateCustomerInfo(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> deleteCustomerById(@PathVariable long id){
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<CustomerResponseDTO>> searchCustomer(@PathVariable String name){
        return ResponseEntity.ok(customerService.searchByName(name));
    }
}
