package com.lanchonete.cachorrosquentesgourmet.model;

public class Ingrediente {
    
    //#region Atributos
    private Integer id;

    private String nome;

    private Double valor;
    //#endregion

    public Ingrediente(){
        
    }

    public Ingrediente(Integer id, String nome, Double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    //#endregion
    
}
