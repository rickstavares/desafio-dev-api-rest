package docktech.conta.model;

import docktech.pessoa.model.Pessoa;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "CONTA")
@NoArgsConstructor
public class Conta {

    public Conta(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idConta")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoa;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "limiteSaqueDiario")
    private BigDecimal limiteSaqueDiario;

    @Column(name = "flagAtivo")
    private boolean flagAtivo;

    //Eu iria criar um enum, mas como nos requisitos a tabela tem que ser um int, então vou manter.
    // tipo 1 - Pessoa fisica
    // tipo 2 - Pessoa juridica
    @Column(name = "tipoConta")
    private int tipoConta;

    @Column(name = "dataCriacao")
    private LocalDateTime dataCriacao;

}
