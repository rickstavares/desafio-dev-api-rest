package docktech.transacao.repository;

import docktech.transacao.model.Transacao;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findAllByContaId(long idConta);

    List<Transacao> findAllByContaIdAndDataTransacaoBetween(long idConta, LocalDate dataInicio, LocalDate dataFim);
}
