package docktech.conta.controller;

import docktech.conta.model.ContaDTO;
import docktech.conta.service.ContaService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
@RequestMapping("/api/conta")
public class ContaController {

    private final ContaService contaService;

    @Inject
    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping("/extrato/{idConta}/{dataInicio}/{dataFim}")
    public ResponseEntity<ContaDTO> getExtrato(@PathVariable Long idConta, @PathVariable LocalDate dataInicio,
        @PathVariable LocalDate dataFim) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/saldo/{idConta}")
    public ResponseEntity<BigDecimal> getSaldo(@PathVariable Long idConta) {
        try {
            return new ResponseEntity<>(contaService.getSaldo(idConta), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(String.format("Erro ao tentar verificar saldo: %s)", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ContaDTO> createConta(@RequestBody ContaDTO contaDTO) {
        try {
            return new ResponseEntity<>(contaService.createConta(contaDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(String.format("Erro ao tentar criar conta: %s)", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/deposito/{idConta}/{valor}/{dataTransacao}")
    public ResponseEntity<ContaDTO> depositar(@PathVariable Long idConta, @PathVariable BigDecimal valor,
        @PathVariable String dataTransacao) {

        try {
            return new ResponseEntity<>(contaService.depositar(idConta, valor, LocalDate.parse(dataTransacao, DateTimeFormatter.ISO_DATE)),
                HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(String.format("Erro ao tentar depositar: %s)", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/saque/{idConta}/{valor}/{dataTransacao}")
    public ResponseEntity<ContaDTO> sacar(@PathVariable Long idConta, @PathVariable BigDecimal valor,
        @PathVariable String dataTransacao) {

        try {
            return new ResponseEntity<>(contaService.sacar(idConta, valor, LocalDate.parse(dataTransacao, DateTimeFormatter.ISO_DATE)),
                HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(String.format("Erro ao tentar sacar: %s)", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/bloqueio/{idConta}")
    public ResponseEntity<Void> bloquearConta(@PathVariable Long idConta) {
        try {
            contaService.bloquearConta(idConta);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(String.format("Erro ao tentar bloquear conta: %s)", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ativacao/{idConta}")
    public ResponseEntity<Void> ativarConta(@PathVariable Long idConta) {
        try {
            contaService.ativarConta(idConta);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(String.format("Erro ao tentar ativar conta: %s)", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
