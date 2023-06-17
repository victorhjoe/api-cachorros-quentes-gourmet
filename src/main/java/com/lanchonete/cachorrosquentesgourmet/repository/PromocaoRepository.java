package com.lanchonete.cachorrosquentesgourmet.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.lanchonete.cachorrosquentesgourmet.model.Lanche;
import com.lanchonete.cachorrosquentesgourmet.model.Promocao;
import com.lanchonete.cachorrosquentesgourmet.model.exception.ResourceNotFoundException;

@Repository
public class PromocaoRepository {

    private LancheRepository lancheRepository;
    private List<Promocao> promocoes = new ArrayList<Promocao>();
    private Integer ultimoId = 0;

    public PromocaoRepository(){
        lancheRepository = new LancheRepository();
        List<Lanche> lanches = lancheRepository.obterTodos();
        Promocao completoComLinguicaERefrigerante = new Promocao(1, "Completo com linguiça e Refrigerante", lanches.get(0) , 6.00, 0.00);
        Promocao completoComSalsichaERefrigerante = new Promocao(2, "Completo com salsicha e Refrigerante", lanches.get(1) , 6.00, 0.00);
        List<Lanche> completosLinguicaELowCarb = lanches.stream().filter(lanche -> lanche.getId() != 2).collect(Collectors.toList());
        Promocao completoComLinguicaELowCarb = new Promocao(3, "Completo com salsicha e Low Carb", completosLinguicaELowCarb, 0.00, 10.00);
        List<Promocao> promocoesIniciais = Arrays.asList(completoComLinguicaERefrigerante, completoComSalsichaERefrigerante, completoComLinguicaELowCarb);
        promocoes.addAll(promocoesIniciais);
    }

    /**
     * Método para retornar uma lista de promoções.
     * @return Retorna uma lista de promoções.
     */
    public List<Promocao> obterTodos(){
        return promocoes;
    }

    /**
     * Método que retorna a promoção encontrada pelo seu id.
     * @param id da promoção que será localizada.
     * @return Retorna uma promoção caso seja localizada.
     */
    public Optional<Promocao> obterPorId(Integer id){
        return promocoes
                .stream()
                .filter(promocao -> promocao.getId() == id)
                .findFirst();
    }

    /**
     * Método para adicionar uma promoção na lista.
     * @param promocao que foi adicionado na lista.
     * @return Retorna a promoção que foi adicionada na lista.
     */
    public Promocao adicionar(Promocao promocao){
        ultimoId++;
        promocao.setId(ultimoId);
        promocoes.add(promocao);

        return promocao;
    }

    /**
     * Método para deletar a promocao por id.
     * @param id da promoção a ser deletada.
     */
    public void deletar(Integer id){
        promocoes.removeIf(promocao -> promocao.getId() == id);
    }

    /**
     * Método para atualizar a promocao na lista.
     * @param promocao que será atualizada.
     * @return Retorna a promocao após atualizar a lista.
     */
    public Promocao atualizar(Promocao promocao){
        Optional<Promocao> promocaoEncontrada = obterPorId(promocao.getId());

        if(promocaoEncontrada.isEmpty()){
            throw new ResourceNotFoundException("promoção não encontrada");
        }
        deletar(promocao.getId());
        promocoes.add(promocao);

        return promocao;
    }
}
