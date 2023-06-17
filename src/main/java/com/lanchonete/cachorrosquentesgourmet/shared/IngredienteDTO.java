package com.lanchonete.cachorrosquentesgourmet.shared;

public class IngredienteDTO {
        
    //#region Atributos
    private Integer id;

    private String nome;

    private Double valor;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    //#endregion
}
