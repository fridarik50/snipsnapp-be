package hackeru.fridarik.snipsnapp.repository;

import hackeru.fridarik.snipsnapp.entity.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BarberRepository extends JpaRepository<Barber, Long> {
    Barber findByEmailAndPassword(String email, String password);
    boolean existsBarberByEmail(String email);
    Optional<Barber> findByEmail(String email);
}
