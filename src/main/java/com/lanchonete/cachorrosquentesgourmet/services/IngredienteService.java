package com.lanchonete.cachorrosquentesgourmet.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lanchonete.cachorrosquentesgourmet.model.Ingrediente;
import com.lanchonete.cachorrosquentesgourmet.model.exception.ResourceNotFoundException;
import com.lanchonete.cachorrosquentesgourmet.repository.IngredienteRepository;
import com.lanchonete.cachorrosquentesgourmet.shared.IngredienteDTO;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    /**
     * Método para retornar uma lista de ingredientes.
     * @return Retorna uma lista de ingredientes.
     * 
     */
    public List<IngredienteDTO> obterTodos(){     
        List<Ingrediente> ingredientes = ingredienteRepository.obterTodos();

        return ingredientes.stream()
                .map(ingrediente -> new ModelMapper().map(ingrediente, IngredienteDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Método que retorna o ingrediente encontrado pelo seu id.
     * @param id do ingrediente que será localizado.
     * @return Retorna um ingrediente caso seja localizado.
     */
    public Optional<IngredienteDTO> obterPorId(Integer id){
        
        Optional<Ingrediente> ingrediente = ingredienteRepository.obterPorId(id);

        if(ingrediente.isEmpty()){
            throw new ResourceNotFoundException("O ingrediente com o id " + id + " não foi encontrado. Ingrediente não existe.");
        }

        IngredienteDTO ingredienteDTO = new ModelMapper().map(ingrediente.get(), IngredienteDTO.class);

        return Optional.of(ingredienteDTO);
    }

    /**
     * Método para adicionar um ingrediente na lista.
     * @param ingrediente que foi adicionado ns lista.
     * @return Retorna o ingrediente que foi adicionado na lista.
     */
    public IngredienteDTO adicionar(IngredienteDTO ingredienteDTO){

        ModelMapper mapper = new ModelMapper();

        Ingrediente ingrediente = mapper.map(ingredienteDTO, Ingrediente.class);

        ingredienteRepository.adicionar(ingrediente);
        ingredienteDTO.setId(ingrediente.getId());

        return ingredienteDTO;
    }

    /**
     * Método para deletar o ingrediente por id.
     * @param id do ingrediente a ser deletado.
     */
    public void deletar(Integer id){
        Optional<Ingrediente> ingrediente = ingredienteRepository.obterPorId(id);

        if(ingrediente.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível deletar o ingrediente com o id" + id + ". Ingrediente não encontrado.");
        }

        ingredienteRepository.deletar(id);
    }

    /**
     * Método para atualizar o ingrediente na lista.
     * @param ingrediente que será atualizado.
     * @return Retorna o ingrediente após atualizar a lista.
     */
    public IngredienteDTO atualizar(IngredienteDTO ingredienteDTO, Integer id){

       Optional<Ingrediente> ingredienteEncontrado = ingredienteRepository.obterPorId(id);

        if(ingredienteEncontrado.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível atualizar o ingrediente com o id" + id + ". Id não encontrado.");
        }

        ingredienteDTO.setId(id);

        ModelMapper mapper = new ModelMapper();

        Ingrediente ingrediente = mapper.map(ingredienteDTO, Ingrediente.class);

        ingredienteRepository.atualizar(ingrediente);

        return ingredienteDTO;
    }
    
}
