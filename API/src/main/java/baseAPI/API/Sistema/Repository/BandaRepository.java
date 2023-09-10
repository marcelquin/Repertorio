package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.Banda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandaRepository extends JpaRepository<Banda, Long> {
}
