package com.lanchonete.cachorrosquentesgourmet.model;

import java.util.ArrayList;
import java.util.List;

public class Promocao {
    
    //#region Atributos
    private Integer id;

    private String nome;

    private List<Lanche> lanches;

    private Double acrescimo;

    private Double desconto;

    private Double valor;
    //#endregion

    //#region Constructor
    public Promocao() {
        this.lanches = new ArrayList<Lanche>();
    }

    public Promocao(Integer id, String nome, List<Lanche> lanches, Double acrescimo, Double desconto) {
        this.lanches = new ArrayList<Lanche>();
        this.id = id;
        this.nome = nome;
        this.acrescimo = acrescimo;
        this.desconto = desconto;
        this.lanches = lanches;
        this.valor = this.lanches.stream().mapToDouble(lanche -> lanche.getValor()).sum();
        
        Double descontoEmPorcentagem = valor * (desconto / 100);
        if (acrescimo != null) valor -= descontoEmPorcentagem;

        if (acrescimo != null) valor += acrescimo;
    }

    public Promocao(Integer id, String nome, Lanche lanche, Double acrescimo, Double desconto) {
        this.id = id;
        this.nome = nome;
        this.acrescimo = acrescimo;
        this.desconto = desconto;
        this.lanches = new ArrayList<Lanche>();
        this.lanches.add(lanche);
        this.valor = lanche.getValor();
        this.valor = lanche.getValor();
        
        Double descontoEmPorcentagem = this.valor * (desconto / 100);
        if (acrescimo != null) this.valor -= descontoEmPorcentagem;

        if (acrescimo != null) this.valor += acrescimo;
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
