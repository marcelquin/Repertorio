package baseAPI.API.Repository;

import baseAPI.API.Model.Banda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandaRepository extends JpaRepository<Banda, Long> {
    default boolean findByName(String nome) {
        return false;
    }
}
