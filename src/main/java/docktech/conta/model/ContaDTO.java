package docktech.conta.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ContaDTO {

    private Long id;
    private Long idPessoa;
    private BigDecimal saldo;
    private BigDecimal limiteSaqueDiario;
    private boolean flagAtivo;
    private int tipoConta;
    private LocalDateTime dataCriacao;
}
