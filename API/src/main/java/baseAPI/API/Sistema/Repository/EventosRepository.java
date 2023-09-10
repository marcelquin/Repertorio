package baseAPI.API.Sistema.Repository;

import baseAPI.API.Sistema.Model.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosRepository extends JpaRepository<Eventos, Long> {

    default void findByName(String nome) {}
}
