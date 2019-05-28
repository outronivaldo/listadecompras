package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ListaDeCompras;

@Repository
public interface ListaDeComprasRepository extends JpaRepository<ListaDeCompras, Integer> {

}
