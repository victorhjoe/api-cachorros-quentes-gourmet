package com.lanchonete.cachorrosquentesgourmet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.lanchonete.cachorrosquentesgourmet.model.Lanche;
import com.lanchonete.cachorrosquentesgourmet.model.Promocao;
import com.lanchonete.cachorrosquentesgourmet.repository.LancheRepository;
import com.lanchonete.cachorrosquentesgourmet.repository.PromocaoRepository;


@SpringBootTest
class CachorrosQuentesGourmetApplicationTests {

@Test
void deve_retornar_valor_do_completo_com_linguica(){
	LancheRepository lancheRepository = new LancheRepository();

	Optional<Lanche> result = lancheRepository.obterPorId(1);

	//Verificando se o valor do lanche retornado é igual a soma do valor dos ingredientes
	assertEquals(result.get().getValor(), 6.9);
}

@Test
void deve_retornar_valor_do_completo_com_salsicha(){
	LancheRepository lancheRepository = new LancheRepository();

	Optional<Lanche> result = lancheRepository.obterPorId(2);

	//Verificando se o valor do lanche retornado é igual a soma do valor dos ingredientes
	assertEquals(result.get().getValor(), 5.9);
}

@Test
void deve_retornar_valor_do_low_carb_completo(){
	LancheRepository lancheRepository = new LancheRepository();

	Optional<Lanche> result = lancheRepository.obterPorId(3);

	//Verificando se o valor do lanche retornado é igual a soma do valor dos ingredientes
	assertEquals(result.get().getValor(), 6.9);
}

@Test
void deve_retornar_valor_da_promocao_completo_linguica_com_refrigerante(){
	PromocaoRepository promocaoRepository = new PromocaoRepository();

	Optional<Promocao> result = promocaoRepository.obterPorId(1);

	//Verificando se o valor do lanche retornado é igual a soma do valor dos ingredientes
	assertEquals(result.get().getValor(), 12.9);
}

@Test
void deve_retornar_valor_da_promocao_completo_salsicha_com_refrigerante(){
	PromocaoRepository promocaoRepository = new PromocaoRepository();

	Optional<Promocao> result = promocaoRepository.obterPorId(2);

	//Verificando se o valor do lanche retornado é igual a soma do valor dos ingredientes
	assertEquals(result.get().getValor(), 11.9);
}

@Test
void deve_retornar_valor_da_promocao_completo_salsicha_com_low_carb_completo(){
	PromocaoRepository promocaoRepository = new PromocaoRepository();

	Optional<Promocao> result = promocaoRepository.obterPorId(3);

	//Verificando se o valor do lanche retornado é igual a soma do valor dos ingredientes
	assertEquals(result.get().getValor(), 12.42);
}

}
