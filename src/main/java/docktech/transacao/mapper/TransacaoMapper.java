package docktech.transacao.mapper;

import docktech.conta.model.Conta;
import docktech.transacao.model.Transacao;
import docktech.transacao.model.TransacaoDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TransacaoMapper {

    public Transacao fromDTO(TransacaoDTO transacaoDTO) {
        Transacao transacao = new Transacao();
        transacao.setId(transacaoDTO.getId());
        transacao.setConta(new Conta(transacaoDTO.getIdConta()));
        transacao.setValor(transacaoDTO.getValor());
        transacao.setDataTransacao(transacaoDTO.getDataTransacao());

        return transacao;
    }

    public TransacaoDTO toDTO(Transacao transacao) {
        TransacaoDTO transacaoDTO = new TransacaoDTO();
        transacaoDTO.setId(transacao.getId());
        transacaoDTO.setIdConta(transacao.getConta().getId());
        transacaoDTO.setValor(transacao.getValor());
        transacaoDTO.setDataTransacao(transacao.getDataTransacao());

        return transacaoDTO;
    }

    public List<TransacaoDTO> toDTOS(List<Transacao> transacaoList) {
        return transacaoList.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

}
