package docktech.transacao.controller;

import docktech.transacao.model.TransacaoDTO;
import docktech.transacao.service.TransacaoService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
@RequestMapping("/api/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @Inject
    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping("/by-conta/{idConta}")
    public ResponseEntity<List<TransacaoDTO>> getListaTransacoes(@PathVariable Long idConta) {
        try {
            return new ResponseEntity<>(transacaoService.getListaTransacoesPorConta(idConta), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(String.format("Erro ao tentar recuperar lista de transacoes conta: %s)", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/by-conta/periodo/{idConta}/{dataInicio}/{dataFim}")
    public ResponseEntity<List<TransacaoDTO>> getListaTransacoesPorPeriodo(@PathVariable Long idConta,
        @PathVariable String dataInicio, @PathVariable String dataFim) {
        try {
            return new ResponseEntity<>(transacaoService.getListaTransacoesPorConta(idConta,
                LocalDate.parse(dataInicio, DateTimeFormatter.ISO_DATE),
                LocalDate.parse(dataFim, DateTimeFormatter.ISO_DATE)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(String.format("Erro ao tentar recuperar lista de transacoes conta: %s)", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
