package baseAPI.API.Repository;

import baseAPI.API.Model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {

    default boolean findByName(String nome) {
        return false;
    }
}
