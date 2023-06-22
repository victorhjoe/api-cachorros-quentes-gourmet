package com.lanchonete.cachorrosquentesgourmet.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lanchonete.cachorrosquentesgourmet.services.IngredienteService;
import com.lanchonete.cachorrosquentesgourmet.shared.IngredienteDTO;
import com.lanchonete.cachorrosquentesgourmet.view.model.ingrediente.IngredienteRequest;
import com.lanchonete.cachorrosquentesgourmet.view.model.ingrediente.IngredienteResponse;

@CrossOrigin
@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public ResponseEntity<List<IngredienteResponse>> obterTodos(){
        
        List<IngredienteDTO> ingredientes = ingredienteService.obterTodos();
        ModelMapper mapper = new ModelMapper();

        List<IngredienteResponse> resposta = ingredientes.stream()
            .map(ingrediente -> mapper.map(ingrediente, IngredienteResponse.class))
            .collect(Collectors.toList());

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<IngredienteResponse>> obterPorId(@PathVariable Integer id){
        Optional<IngredienteDTO> ingredienteDTO = ingredienteService.obterPorId(id);
        
        IngredienteResponse ingrediente = new ModelMapper().map(ingredienteDTO.get(), IngredienteResponse.class);

        return new ResponseEntity<>(Optional.of(ingrediente), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<IngredienteResponse> adicionar(@RequestBody IngredienteRequest ingredienteReq){
        ModelMapper mapper = new ModelMapper();

        IngredienteDTO dto = mapper.map(ingredienteReq, IngredienteDTO.class);

        dto = ingredienteService.adicionar(dto);

        return new ResponseEntity<>(mapper.map(dto, IngredienteResponse.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        ingredienteService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredienteResponse> atualizar(@RequestBody IngredienteRequest IngredienteReq, @PathVariable Integer id){
        ModelMapper mapper = new ModelMapper();

        IngredienteDTO dto = mapper.map(IngredienteReq, IngredienteDTO.class);



        dto = ingredienteService.atualizar(dto, id);

        return new ResponseEntity<>(mapper.map(dto, IngredienteResponse.class), HttpStatus.OK);
    }
    
}
