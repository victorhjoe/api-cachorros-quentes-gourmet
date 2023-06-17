package com.lanchonete.cachorrosquentesgourmet.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanchonete.cachorrosquentesgourmet.model.Ingrediente;
import com.lanchonete.cachorrosquentesgourmet.model.Lanche;
import com.lanchonete.cachorrosquentesgourmet.model.exception.BadRequestException;
import com.lanchonete.cachorrosquentesgourmet.model.exception.ResourceNotFoundException;
import com.lanchonete.cachorrosquentesgourmet.repository.IngredienteRepository;
import com.lanchonete.cachorrosquentesgourmet.repository.LancheRepository;
import com.lanchonete.cachorrosquentesgourmet.shared.LancheDTO;

@Service
public class LancheService {
    
    @Autowired
    private LancheRepository lancheRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    /**
     * Método para retornar uma lista de lanches.
     * @return Retorna uma lista de lanches.
     * 
     */
    public List<LancheDTO> obterTodos(){     
        List<Lanche> lanches = lancheRepository.obterTodos();

        return lanches.stream()
                .map(lanche -> new ModelMapper().map(lanche, LancheDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Método que retorna o lanche encontrado pelo seu id.
     * @param id do lanche que será localizado.
     * @return Retorna um lanche caso seja localizado.
     */
    public Optional<LancheDTO> obterPorId(Integer id){
        
        Optional<Lanche> lanche = lancheRepository.obterPorId(id);

        if(lanche.isEmpty()){
            throw new ResourceNotFoundException("O lanche com o id " + id + " não foi encontrado. Lanche não existe.");
        }

        LancheDTO lancheDTO = new ModelMapper().map(lanche.get(), LancheDTO.class);

        return Optional.of(lancheDTO);
    }

    /**
     * Método para adicionar um lanche na lista.
     * @param lanche que foi adicionado na lista.
     * @return Retorna o lanche que foi adicionado na lista.
     */
    public LancheDTO adicionar(LancheDTO lancheDTO){

        for (Ingrediente ingrediente : lancheDTO.getIngredientes()) {
            Optional<Ingrediente> ingredienteEncontrado = ingredienteRepository.obterPorId(ingrediente.getId());

            if(ingredienteEncontrado.isEmpty()){
                throw new BadRequestException("O ingrediente com o id " + ingrediente.getId() + " não foi encontrado.");
            }
        }

        lancheDTO.setValor(lancheDTO.getIngredientes().stream().mapToDouble(ingrediente -> ingrediente.getValor()).sum()); 

        ModelMapper mapper = new ModelMapper();

        Lanche lanche = mapper.map(lancheDTO, Lanche.class);

        lancheRepository.adicionar(lanche);
        lancheDTO.setId(lanche.getId());

        return lancheDTO;
    }

    /**
     * Método para deletar o lanche por id.
     * @param id do lanche a ser deletado.
     */
    public void deletar(Integer id){
        Optional<Lanche> lanche = lancheRepository.obterPorId(id);

        if(lanche.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível deletar o lanche com o id" + id + ". Lanche não encontrado.");
        }

        lancheRepository.deletar(id);
    }

    /**
     * Método para atualizar o lanche na lista.
     * @param lanche que será atualizado.
     * @return Retorna o lanche após atualizar a lista.
     */
    public LancheDTO atualizar(LancheDTO lancheDTO, Integer id){

       Optional<Lanche> lancheEncontrado = lancheRepository.obterPorId(id);

        if(lancheEncontrado.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível atualizar o lanche com o id " + id + ". Id não encontrado.");
        }

        lancheDTO.setId(id);

        lancheDTO.setValor(lancheDTO.getIngredientes().stream().mapToDouble(ingrediente -> ingrediente.getValor()).sum()); 

        ModelMapper mapper = new ModelMapper();

        Lanche lanche = mapper.map(lancheDTO, Lanche.class);

        lancheRepository.atualizar(lanche);

        return lancheDTO;
    }
}
