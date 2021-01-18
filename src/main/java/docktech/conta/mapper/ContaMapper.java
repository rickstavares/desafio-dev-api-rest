package docktech.conta.mapper;

import docktech.conta.model.Conta;
import docktech.conta.model.ContaDTO;
import docktech.pessoa.model.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class ContaMapper {

    public Conta fromDTO(ContaDTO contaDTO) {
        Conta conta = new Conta();
        conta.setId(contaDTO.getId());
        conta.setPessoa(new Pessoa(contaDTO.getIdPessoa()));
        conta.setLimiteSaqueDiario(contaDTO.getLimiteSaqueDiario());
        conta.setSaldo(contaDTO.getSaldo());
        conta.setFlagAtivo(contaDTO.isFlagAtivo());
        conta.setTipoConta(contaDTO.getTipoConta());
        conta.setDataCriacao(contaDTO.getDataCriacao());

        return conta;
    }

    public ContaDTO toDTO(Conta conta) {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setId(conta.getId());
        contaDTO.setIdPessoa(conta.getPessoa().getId());
        contaDTO.setLimiteSaqueDiario(conta.getLimiteSaqueDiario());
        contaDTO.setSaldo(conta.getSaldo());
        contaDTO.setFlagAtivo(conta.isFlagAtivo());
        contaDTO.setTipoConta(conta.getTipoConta());
        contaDTO.setDataCriacao(conta.getDataCriacao());

        return contaDTO;
    }

}
