package com.lanchonete.cachorrosquentesgourmet.view.model.Lanche;

import java.util.ArrayList;
import java.util.List;

import com.lanchonete.cachorrosquentesgourmet.model.Ingrediente;

public class LancheResponse {
       //#region Atributos
       private Integer id;

       private String nome;
   
       private List<Ingrediente>  ingredientes;

       private Double valor;
       //#endregion
   
       //#region Constructor
       public LancheResponse() {
           this.ingredientes = new ArrayList<Ingrediente>();
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

       public void setValor(Double valor) {
            this.valor = valor;
       }
       //#endregion
}
