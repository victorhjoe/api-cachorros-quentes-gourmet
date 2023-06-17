package com.lanchonete.cachorrosquentesgourmet.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lanchonete.cachorrosquentesgourmet.services.PromocaoService;
import com.lanchonete.cachorrosquentesgourmet.shared.PromocaoDTO;
import com.lanchonete.cachorrosquentesgourmet.view.model.Promocao.PromocaoRequest;
import com.lanchonete.cachorrosquentesgourmet.view.model.Promocao.PromocaoResponse;

@RestController
@RequestMapping("/api/promocoes")
public class PromocaoController {
    
    @Autowired
    private PromocaoService promocaoService;

    @GetMapping
    public ResponseEntity<List<PromocaoResponse>> obterTodos(){
        
        List<PromocaoDTO> promocoes = promocaoService.obterTodos();
        ModelMapper mapper = new ModelMapper();

        List<PromocaoResponse> resposta = promocoes.stream()
            .map(promocao -> mapper.map(promocao, PromocaoResponse.class))
            .collect(Collectors.toList());

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PromocaoResponse>> obterPorId(@PathVariable Integer id){
        Optional<PromocaoDTO> promocaoDTO = promocaoService.obterPorId(id);
        
        PromocaoResponse promocao = new ModelMapper().map(promocaoDTO.get(), PromocaoResponse.class);

        return new ResponseEntity<>(Optional.of(promocao), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PromocaoResponse> adicionar(@RequestBody PromocaoRequest promocaoReq){
        ModelMapper mapper = new ModelMapper();

        PromocaoDTO dto = mapper.map(promocaoReq, PromocaoDTO.class);

        dto = promocaoService.adicionar(dto);

        return new ResponseEntity<>(mapper.map(dto, PromocaoResponse.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        promocaoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PromocaoResponse> atualizar(@RequestBody PromocaoRequest promocaoReq, @PathVariable Integer id){
        ModelMapper mapper = new ModelMapper();

        PromocaoDTO dto = mapper.map(promocaoReq, PromocaoDTO.class);

        dto = promocaoService.atualizar(dto, id);

        return new ResponseEntity<>(mapper.map(dto, PromocaoResponse.class), HttpStatus.OK);
    }
    
}
