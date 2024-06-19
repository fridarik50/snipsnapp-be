package hackeru.fridarik.snipsnapp.repository;

import hackeru.fridarik.snipsnapp.entity.Barber;
import hackeru.fridarik.snipsnapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmail(String email);
    Customer findByEmailAndPassword(String email, String password);
    Optional<Customer> findByEmail(String email);
}
