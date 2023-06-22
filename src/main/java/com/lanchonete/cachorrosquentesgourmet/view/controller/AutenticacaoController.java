//package com.lanchonete.cachorrosquentesgourmet.view.controller;
//
//
//import com.lanchonete.cachorrosquentesgourmet.model.Usuario;
//import com.lanchonete.cachorrosquentesgourmet.security.TokenService;
//import com.lanchonete.cachorrosquentesgourmet.view.model.Login.LoginRequest;
//import com.lanchonete.cachorrosquentesgourmet.view.model.Login.LoginResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.*;
//
//@CrossOrigin
//@RestController
//@RequestMapping("/api/login")
//public class AutenticacaoController {
//
//    @Autowired
//    private AuthenticationManager manager;
//
//    @Autowired
//    TokenService tokenService;
//
//
//
//    @PostMapping
//    public ResponseEntity efetuarLogin(@RequestBody LoginRequest login){
//
//        try {
//            var authenticationToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());
//
//            var authentication = manager.authenticate(authenticationToken);
//
//            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
//
//
//
//            return ResponseEntity.ok(new LoginResponse(tokenJWT));
//        } catch (Exception e){
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//}