package docktech.conta.service;

import docktech.conta.mapper.ContaMapper;
import docktech.conta.model.Conta;
import docktech.conta.model.ContaDTO;
import docktech.conta.repository.ContaRepository;
import docktech.transacao.model.Transacao;
import docktech.transacao.service.TransacaoService;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ContaMapper contaMapper;
    private final TransacaoService transacaoService;

    @Inject
    ContaService(ContaRepository contaRepository, ContaMapper contaMapper, TransacaoService transacaoService) {
        this.contaRepository = contaRepository;
        this.contaMapper = contaMapper;
        this.transacaoService = transacaoService;
    }

    public ContaDTO getContaPorId(long idConta) {
        return contaMapper.toDTO(contaRepository.findById(idConta).get());
    }

    public BigDecimal getExtrato(long idConta, LocalDate dataInicio, LocalDate dataFim) {
        return null;
    }

    public ContaDTO createConta(ContaDTO contaDTO) {
        Conta conta = contaRepository.save(contaMapper.fromDTO(contaDTO));
        return contaMapper.toDTO(conta);
    }

    public BigDecimal getSaldo(long idConta) {
        return contaRepository.getSaldoById(idConta);
    }

    @Transactional
    public ContaDTO depositar(Long idConta, BigDecimal deposito, LocalDate dataTransacao) {
        transacaoService.criarTransacao(Transacao.builder()
            .conta(new Conta(idConta))
            .dataTransacao(dataTransacao)
            .valor(deposito)
            .build());

        Conta conta = contaRepository.findById(idConta).get();
        conta.setSaldo(conta.getSaldo().add(deposito));

        return contaMapper.toDTO(conta);
    }

    @Transactional
    public Conta bloquearConta(long idConta) {
        Conta conta = contaRepository.findById(idConta).get();
        conta.setFlagAtivo(false);

        return conta;
    }

    @Transactional
    public Conta ativarConta(long idConta) {
        Conta conta = contaRepository.findById(idConta).get();
        conta.setFlagAtivo(true);

        return conta;
    }

    @Transactional
    public ContaDTO sacar(Long idConta, BigDecimal deposito, LocalDate dataTransacao) {
        transacaoService.criarTransacao(Transacao.builder()
            .conta(new Conta(idConta))
            .dataTransacao(dataTransacao)
            .valor(deposito.negate())
            .build());

        Conta conta = contaRepository.findById(idConta).get();
        conta.setSaldo(conta.getSaldo().subtract(deposito));

        return contaMapper.toDTO(conta);
    }
}
