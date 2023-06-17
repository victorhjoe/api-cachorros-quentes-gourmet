package com.lanchonete.cachorrosquentesgourmet.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lanchonete.cachorrosquentesgourmet.model.Ingrediente;
import com.lanchonete.cachorrosquentesgourmet.model.exception.ResourceNotFoundException;

@Repository
public class IngredienteRepository {
    
    private List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();


    private Integer ultimoId = 7;

    //#region Constructor
    public IngredienteRepository(){
        Ingrediente pao = new Ingrediente(1, "pão", 2.00);
        Ingrediente batataPalha = new Ingrediente(2, "Batata Palha", 0.40);
        Ingrediente linguica = new Ingrediente(3, "Linguiça", 3.00);
        Ingrediente salsicha = new Ingrediente(4, "Salsicha", 2.00);
        Ingrediente ovoDeCodorna = new Ingrediente(5, "Ovo de codorna", 0.30);
        Ingrediente pureDeBatatas = new Ingrediente(6, "Purê de batatas", 1.00);
        Ingrediente milho = new Ingrediente(7, "Milho", 0.20);
        
        List<Ingrediente> ingredientesIniciais = Arrays.asList(pao, batataPalha, linguica, salsicha, ovoDeCodorna, pureDeBatatas, milho);

        ingredientes.addAll(ingredientesIniciais);
    }
    //#endregion

    /**
     * Método para retornar uma lista de ingredientes.
     * @return Retorna uma lista de ingredientes.
     */
    public List<Ingrediente> obterTodos(){
        return ingredientes;
    }

    /**
     * Método que retorna o ingrediente encontrado pelo seu id.
     * @param id do ingrediente que será localizado.
     * @return Retorna um ingrediente caso seja localizado.
     */
    public Optional<Ingrediente> obterPorId(Integer id){
        return ingredientes
                .stream()
                .filter(ingrediente -> ingrediente.getId() == id)
                .findFirst();
    }

    /**
     * Método para adicionar um ingrediente na lista.
     * @param ingrediente que foi adicionado ns lista.
     * @return Retorna o ingrediente que foi adicionado na lista.
     */
    public Ingrediente adicionar(Ingrediente ingrediente){
        ultimoId++;
        ingrediente.setId(ultimoId);
        ingredientes.add(ingrediente);

        return ingrediente;
    }

    /**
     * Método para deletar o ingrediente por id.
     * @param id do ingrediente a ser deletado.
     */
    public void deletar(Integer id){
        ingredientes.removeIf(ingrediente -> ingrediente.getId() == id);
    }

    /**
     * Método para atualizar o ingrediente na lista.
     * @param ingrediente que será atualizado.
     * @return Retorna o ingrediente após atualizar a lista.
     */
    public Ingrediente atualizar(Ingrediente ingrediente){
        Optional<Ingrediente> ingredienteEncontrado = obterPorId(ingrediente.getId());

        if(ingredienteEncontrado.isEmpty()){
            throw new ResourceNotFoundException("Ingrediente não encontrado");
        }
        deletar(ingrediente.getId());
        ingredientes.add(ingrediente);

        return ingrediente;
    }

}
