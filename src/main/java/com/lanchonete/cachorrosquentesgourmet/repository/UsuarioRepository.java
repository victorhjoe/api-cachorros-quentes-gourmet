//package com.lanchonete.cachorrosquentesgourmet.repository;
//
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Repository;
//
//import com.lanchonete.cachorrosquentesgourmet.model.Usuario;
//
//@Repository
//public class UsuarioRepository {
//
//    private final List<Usuario> usuarios = new ArrayList<>();
//
//    public UsuarioRepository(){
//        Usuario admin = new Usuario(1, "Admin", "admin@admin.com", "$2a$12$VwWN5d/et9TTkdWhlWI1euF.muyPEwEIZj5XsZptZh7lJG/stbXAS");
//        usuarios.add(admin);
//    }
//
//    public UserDetails findByEmail(String email){
//        return usuarios.stream().filter(usuario -> usuario.getEmail().equals(email)).findFirst().get();
//    }
//
//    public Optional<Usuario> findById(Integer id){
//        return usuarios.stream().filter(usuario -> usuario.getId().equals(id)).findFirst();
//    }
//}
