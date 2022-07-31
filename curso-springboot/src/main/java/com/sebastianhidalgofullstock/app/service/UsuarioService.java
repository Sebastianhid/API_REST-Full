package com.sebastianhidalgofullstock.app.service;

import java.awt.print.Pageable;
import java.util.Optional;
import org.springframework.data.domain.Page;
import com.sebastianhidalgofullstock.app.entity.Usuario;


public interface UsuarioService {
	
	public Iterable<Usuario> findAll();
	


	public Optional<Usuario> findById(Long id);

	public Usuario save(Usuario usuario);

	public void deleteById(Long id);
}
