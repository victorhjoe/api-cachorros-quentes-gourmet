package com.lanchonete.cachorrosquentesgourmet.view.model.ingrediente;

public class IngredienteRequest {
        
    //#region Atributos

    private String nome;

    private Double valor;
    //#endregion

    //#region Getters and Setters
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
