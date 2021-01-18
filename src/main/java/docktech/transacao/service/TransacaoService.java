package docktech.transacao.service;

import docktech.transacao.mapper.TransacaoMapper;
import docktech.transacao.model.Transacao;
import docktech.transacao.model.TransacaoDTO;
import docktech.transacao.repository.TransacaoRepository;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final TransacaoMapper transacaoMapper;

    @Inject
    public TransacaoService(TransacaoRepository transacaoRepository, TransacaoMapper transacaoMapper) {
        this.transacaoRepository = transacaoRepository;
        this.transacaoMapper = transacaoMapper;
    }

    public Transacao criarTransacao(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }

    public List<TransacaoDTO> getListaTransacoesPorConta(long idConta) {
        return transacaoMapper.toDTOS(transacaoRepository.findAllByContaId(idConta));
    }

    public List<TransacaoDTO> getListaTransacoesPorConta(long idConta, LocalDate dataInicio, LocalDate dataFim) {
        return transacaoMapper.toDTOS(transacaoRepository.findAllByContaIdAndDataTransacaoBetween(idConta, dataInicio, dataFim));
    }

}
