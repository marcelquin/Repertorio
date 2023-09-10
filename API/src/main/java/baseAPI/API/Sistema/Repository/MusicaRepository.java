package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.Banda;
import baseAPI.API.Sistema.Model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {
}
