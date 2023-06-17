package com.lanchonete.cachorrosquentesgourmet.model;

import java.util.ArrayList;
import java.util.List;

public class Lanche {
    //#region Atributos
    private Integer id;

    private String nome;

    private List<Ingrediente>  ingredientes;

    private Double valor;
    
    //#endregion

    //#region Constructor
    public Lanche() {
        this.ingredientes = new ArrayList<Ingrediente>();
    }
    
    public Lanche(Integer id, String nome, List<Ingrediente> ingredientes) {
        this.id = id;
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.valor = this.getIngredientes().stream().mapToDouble(ingrediente -> ingrediente.getValor()).sum();
    }

    //#endregion

    //#region Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Double getValor() {
        return valor;
    }
     //#endregion
}
