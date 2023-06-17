package com.lanchonete.cachorrosquentesgourmet.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanchonete.cachorrosquentesgourmet.model.Promocao;
import com.lanchonete.cachorrosquentesgourmet.model.exception.ResourceNotFoundException;
import com.lanchonete.cachorrosquentesgourmet.repository.PromocaoRepository;
import com.lanchonete.cachorrosquentesgourmet.shared.PromocaoDTO;

@Service
public class PromocaoService {
    
    @Autowired
    private PromocaoRepository promocaoRepository;

    /**
     * Método para retornar uma lista de promoções.
     * @return Retorna uma lista de promoções.
     * 
     */
    public List<PromocaoDTO> obterTodos(){     
        List<Promocao> promocoes = promocaoRepository.obterTodos();

        List<PromocaoDTO> dto = promocoes.stream()
                .map(promocao -> new ModelMapper().map(promocao, PromocaoDTO.class))
                .collect(Collectors.toList());
        return dto;
    }

    /**
     * Método que retorna a promoção encontrada pelo seu id.
     * @param id da promoção que será localizada.
     * @return Retorna uma promoção caso seja localizada.
     */
    public Optional<PromocaoDTO> obterPorId(Integer id){
        
        Optional<Promocao> promocao = promocaoRepository.obterPorId(id);

        if(promocao.isEmpty()){
            throw new ResourceNotFoundException("A promoção com o id " + id + " não foi encontrado. Promoção não existe.");
        }

        PromocaoDTO promocaoDTO = new ModelMapper().map(promocao.get(), PromocaoDTO.class);

        return Optional.of(promocaoDTO);
    }

    /**
     * Método para adicionar uma promoção na lista.
     * @param promocao que foi adicionada na lista.
     * @return Retorna a promoção que foi adicionado na lista.
     */
    public PromocaoDTO adicionar(PromocaoDTO promocaoDTO){

        ModelMapper mapper = new ModelMapper();

        Promocao promocao = mapper.map(promocaoDTO, Promocao.class);

        promocaoRepository.adicionar(promocao);
        promocaoDTO.setId(promocao.getId());

        return promocaoDTO;
    }

    /**
     * Método para deletar a promoção por id.
     * @param id da promoção a ser deletada.
     */
    public void deletar(Integer id){
        Optional<Promocao> promocao = promocaoRepository.obterPorId(id);

        if(promocao.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível deletar a promoção com o id" + id + ". Promoção não encontrada.");
        }

        promocaoRepository.deletar(id);
    }

    /**
     * Método para atualizar a promocao na lista.
     * @param promocao que será atualizada.
     * @return Retorna a promocao após atualizar a lista.
     */
    public PromocaoDTO atualizar(PromocaoDTO promocaoDTO, Integer id){

       Optional<Promocao> promocaoEncontrada = promocaoRepository.obterPorId(id);

        if(promocaoEncontrada.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível atualizar a promoção com o id" + id + ". Id não encontrado.");
        }

        promocaoDTO.setId(id);

        ModelMapper mapper = new ModelMapper();

        Promocao promocao = mapper.map(promocaoDTO, Promocao.class);

        promocaoRepository.atualizar(promocao);

        return promocaoDTO;
    }
}
