package docktech.transacao.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class TransacaoDTO {

    private Long id;
    private Long idConta;
    private BigDecimal valor;
    private LocalDate dataTransacao;

}
