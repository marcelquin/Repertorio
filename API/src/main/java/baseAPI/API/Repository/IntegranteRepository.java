package baseAPI.API.Repository;

import baseAPI.API.Model.Integrante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegranteRepository extends JpaRepository<Integrante, Long> {
    default void findByName(String nome) {}
}
