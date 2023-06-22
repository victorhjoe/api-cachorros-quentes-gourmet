package com.lanchonete.cachorrosquentesgourmet.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lanchonete.cachorrosquentesgourmet.services.LancheService;
import com.lanchonete.cachorrosquentesgourmet.shared.LancheDTO;
import com.lanchonete.cachorrosquentesgourmet.view.model.Lanche.LancheRequest;
import com.lanchonete.cachorrosquentesgourmet.view.model.Lanche.LancheResponse;

@CrossOrigin
@RestController
@RequestMapping("/api/lanches")
public class LancheController {

    @Autowired
    private LancheService lancheService;

    @GetMapping
    public ResponseEntity<List<LancheResponse>> obterTodos(){
        
        List<LancheDTO> lanches = lancheService.obterTodos();
        ModelMapper mapper = new ModelMapper();

        List<LancheResponse> resposta = lanches.stream()
            .map(lanche -> mapper.map(lanche, LancheResponse.class))
            .collect(Collectors.toList());

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<LancheResponse>> obterPorId(@PathVariable Integer id){
        Optional<LancheDTO> lancheDTO = lancheService.obterPorId(id);
        
        LancheResponse lanche = new ModelMapper().map(lancheDTO.get(), LancheResponse.class);

        return new ResponseEntity<>(Optional.of(lanche), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LancheResponse> adicionar(@RequestBody LancheResponse lancheReq){
        ModelMapper mapper = new ModelMapper();

        LancheDTO dto = mapper.map(lancheReq, LancheDTO.class);

        dto = lancheService.adicionar(dto);

        return new ResponseEntity<>(mapper.map(dto, LancheResponse.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        lancheService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LancheResponse> atualizar(@RequestBody LancheRequest lancheReq, @PathVariable Integer id){
        ModelMapper mapper = new ModelMapper();

        LancheDTO dto = mapper.map(lancheReq, LancheDTO.class);

        dto = lancheService.atualizar(dto, id);

        return new ResponseEntity<>(mapper.map(dto, LancheResponse.class), HttpStatus.OK);
    }
    
}
