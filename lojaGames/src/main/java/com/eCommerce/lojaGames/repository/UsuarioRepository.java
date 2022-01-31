package com.eCommerce.lojaGames.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eCommerce.lojaGames.model.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

}
