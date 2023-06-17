package com.lanchonete.cachorrosquentesgourmet.view.model.Lanche;

import java.util.ArrayList;
import java.util.List;

import com.lanchonete.cachorrosquentesgourmet.model.Ingrediente;

public class LancheRequest {
       //#region Atributos

       private String nome;
   
       private List<Ingrediente>  ingredientes;
       //#endregion
   
       //#region Constructor
       public LancheRequest() {
           this.ingredientes = new ArrayList<Ingrediente>();
       }
       //#endregion
   
       //#region Getters and Setters
   
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
       //#endregion
}
