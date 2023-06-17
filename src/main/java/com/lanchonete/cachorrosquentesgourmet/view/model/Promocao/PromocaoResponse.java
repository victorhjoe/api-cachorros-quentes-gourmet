package com.lanchonete.cachorrosquentesgourmet.view.model.Promocao;

import java.util.ArrayList;
import java.util.List;

import com.lanchonete.cachorrosquentesgourmet.model.Lanche;

public class PromocaoResponse {
       
    //#region Atributos
    private Integer id;

    private String nome;

    private List<Lanche> lanches;

    private Double acrescimo;

    private Double desconto;

    private Double valor;
    //#endregion

    //#region Constructor
    public PromocaoResponse() {
        this.lanches = new ArrayList<Lanche>();
    }
    //#endregion

    //#region Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Lanche> getLanches() {
        return lanches;
    }

    public void setLanches(List<Lanche> lanches) {
        this.lanches = lanches;
    }

    public Double getAcrescimo() {
        return acrescimo;
    }

    public void setAcrescimo(Double acrescimo) {
        this.acrescimo = acrescimo;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
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
