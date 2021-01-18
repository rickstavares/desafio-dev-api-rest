package docktech.conta.repository;

import docktech.conta.model.Conta;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query(value = "SELECT saldo FROM Conta where id = :id")
    BigDecimal getSaldoById(long id);

}
