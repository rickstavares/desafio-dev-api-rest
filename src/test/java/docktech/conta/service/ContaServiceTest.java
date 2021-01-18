package docktech.conta.service;

import docktech.conta.mapper.ContaMapper;
import docktech.conta.model.Conta;
import docktech.conta.model.ContaDTO;
import docktech.conta.repository.ContaRepository;
import docktech.transacao.model.TransacaoDTO;
import docktech.transacao.repository.TransacaoRepository;
import docktech.transacao.service.TransacaoService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContaServiceTest {

    @Inject
    ContaRepository contaRepository;

    @Inject
    ContaMapper contaMapper;

    @Inject
    TransacaoService transacaoService;

    @Inject
    TransacaoRepository transacaoRepository;

    private ContaService underTest;

    @BeforeEach
    void init() {
        transacaoRepository.deleteAll();
        contaRepository.deleteAll();
        MockitoAnnotations.initMocks(this);
        underTest = new ContaService(contaRepository, contaMapper, transacaoService);
    }

    @Test
    void testCriarConta() {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setIdPessoa(1L);
        contaDTO.setDataCriacao(LocalDateTime.now());
        contaDTO.setFlagAtivo(true);
        contaDTO.setSaldo(BigDecimal.TEN);
        contaDTO.setLimiteSaqueDiario(BigDecimal.ONE);
        contaDTO.setTipoConta(1);

        contaDTO = underTest.createConta(contaDTO);

        Assertions.assertEquals(1L, contaDTO.getIdPessoa());
        Assertions.assertTrue(contaDTO.isFlagAtivo());
        Assertions.assertEquals(BigDecimal.TEN, contaDTO.getSaldo());
        Assertions.assertEquals(BigDecimal.ONE, contaDTO.getLimiteSaqueDiario());
    }

    @Test
    void testDeposito() {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setIdPessoa(1L);
        contaDTO.setDataCriacao(LocalDateTime.now());
        contaDTO.setFlagAtivo(true);
        contaDTO.setSaldo(BigDecimal.valueOf(10.01));
        contaDTO.setLimiteSaqueDiario(BigDecimal.ONE);
        contaDTO.setTipoConta(1);

        ContaDTO localContaDTO = underTest.createConta(contaDTO);
        BigDecimal saldo = underTest.getSaldo(localContaDTO.getId());
        Assertions.assertEquals(BigDecimal.valueOf(10.01), saldo);

        localContaDTO = underTest.depositar(localContaDTO.getId(), BigDecimal.valueOf(20.01), LocalDate.now());

        Assertions.assertEquals(BigDecimal.valueOf(30.02), localContaDTO.getSaldo());

        List<TransacaoDTO> transacaoDTOList = transacaoService.getListaTransacoesPorConta(localContaDTO.getId());
        Assertions.assertEquals(1, transacaoDTOList.size());
        Assertions.assertEquals(BigDecimal.valueOf(20.01), transacaoDTOList.get(0).getValor());
    }

    @Test
    void testSaque() {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setIdPessoa(1L);
        contaDTO.setDataCriacao(LocalDateTime.now());
        contaDTO.setFlagAtivo(true);
        contaDTO.setSaldo(BigDecimal.valueOf(10.01));
        contaDTO.setLimiteSaqueDiario(BigDecimal.ONE);
        contaDTO.setTipoConta(1);

        ContaDTO localContaDTO = underTest.createConta(contaDTO);
        BigDecimal saldo = underTest.getSaldo(localContaDTO.getId());
        Assertions.assertEquals(BigDecimal.valueOf(10.01), saldo);

        localContaDTO = underTest.sacar(localContaDTO.getId(), BigDecimal.valueOf(5.03), LocalDate.now());

        Assertions.assertEquals(BigDecimal.valueOf(4.98), localContaDTO.getSaldo());

        List<TransacaoDTO> transacaoDTOList = transacaoService.getListaTransacoesPorConta(localContaDTO.getId());
        Assertions.assertEquals(1, transacaoDTOList.size());
        Assertions.assertEquals(BigDecimal.valueOf(5.03).negate(), transacaoDTOList.get(0).getValor());
    }

    @Test
    void testBloquearEHabilitarConta() {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setIdPessoa(1L);
        contaDTO.setDataCriacao(LocalDateTime.now());
        contaDTO.setFlagAtivo(true);
        contaDTO.setSaldo(BigDecimal.TEN);
        contaDTO.setLimiteSaqueDiario(BigDecimal.ONE);
        contaDTO.setTipoConta(1);

        ContaDTO localContaDTO = underTest.createConta(contaDTO);
        Assertions.assertTrue(localContaDTO.isFlagAtivo());

        Conta conta = underTest.bloquearConta(localContaDTO.getId());
        Assertions.assertFalse(conta.isFlagAtivo());

        conta = underTest.ativarConta(localContaDTO.getId());
        Assertions.assertTrue(conta.isFlagAtivo());
    }

}
