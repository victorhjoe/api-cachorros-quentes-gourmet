package com.lanchonete.cachorrosquentesgourmet.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.lanchonete.cachorrosquentesgourmet.model.Ingrediente;
import com.lanchonete.cachorrosquentesgourmet.model.Lanche;
import com.lanchonete.cachorrosquentesgourmet.model.exception.ResourceNotFoundException;

@Repository
public class LancheRepository {

    private IngredienteRepository ingredienteRepository;
    private List<Lanche> lanches = new ArrayList<Lanche>();
    private Integer ultimoId = 0;

    public LancheRepository(){
        //Setup para dados iniciais no banco
       ingredienteRepository = new IngredienteRepository();
       List<Ingrediente> ingredientesIniciais = ingredienteRepository.obterTodos();

       Lanche completoComLinguica = new Lanche(1, "Completo com linguiça", ingredientesIniciais.stream()
       .filter(ingrediente -> ingrediente.getNome() != "Salsicha").collect(Collectors.toList()));

       Lanche completoComSalsicha = new Lanche(2, "Completo com salsicha", ingredientesIniciais.stream()
       .filter(ingrediente -> ingrediente.getNome() != "Linguiça").collect(Collectors.toList()));

       Lanche lowCarbCompleto = new Lanche(3, "Low Carb Completo", ingredientesIniciais.stream()
       .filter(ingrediente -> ingrediente.getNome() != "pão").collect(Collectors.toList()));

       List<Lanche> lanchesIniciais = Arrays.asList(completoComLinguica, completoComSalsicha, lowCarbCompleto);
       lanches.addAll(lanchesIniciais);
    }

    /**
     * Método para retornar uma lista de lanches.
     * @return Retorna uma lista de lanches.
     */
    public List<Lanche> obterTodos(){
        return lanches;
    }

    /**
     * Método que retorna o lanche encontrado pelo seu id.
     * @param id do lanche que será localizado.
     * @return Retorna um lanche caso seja localizado.
     */
    public Optional<Lanche> obterPorId(Integer id){
        return lanches
                .stream()
                .filter(lanche -> lanche.getId() == id)
                .findFirst();
    }

    /**
     * Método para adicionar um lanche na lista.
     * @param lanche que foi adicionado na lista.
     * @return Retorna o lanche que foi adicionado na lista.
     */
    public Lanche adicionar(Lanche lanche){
        ultimoId++;
        lanche.setId(ultimoId);
        lanches.add(lanche);

        return lanche;
    }

    /**
     * Método para deletar o lanche por id.
     * @param id do lanche a ser deletado.
     */
    public void deletar(Integer id){
        lanches.removeIf(lanche -> lanche.getId() == id);
    }

    /**
     * Método para atualizar o lanche na lista.
     * @param lanche que será atualizado.
     * @return Retorna o lanche após atualizar a lista.
     */
    public Lanche atualizar(Lanche lanche){
        Optional<Lanche> lancheEncontrado = obterPorId(lanche.getId());

        if(lancheEncontrado.isEmpty()){
            throw new ResourceNotFoundException("lanche não encontrado");
        }
        deletar(lanche.getId());
        lanches.add(lanche);

        return lanche;
    }
}
