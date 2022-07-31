package com.sebastianhidalgofullstock.app.controller;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

import com.sebastianhidalgofullstock.app.entity.Usuario;
import com.sebastianhidalgofullstock.app.service.UsuarioService;




@RestController
@RequestMapping("/api/usuarios")

public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
	}
	//Leer
	@GetMapping ("/{id}")
	public ResponseEntity<?> read (@PathVariable(value = "id") Long usuarioId){
		Optional<Usuario> oUsuario = usuarioService.findById(usuarioId);
		if (!oUsuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oUsuario);
	}
	//actualizar
	@PutMapping("/{id}")
	public ResponseEntity<?> update (@RequestBody Usuario usuarioDetails, @PathVariable(value="id") Long usuarioId){
		Optional<Usuario> usuario = usuarioService.findById(usuarioId);
		
		if (!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		usuario.get().setNombre(usuarioDetails.getNombre());
		usuario.get().setClave(usuarioDetails.getClave());
		usuario.get().setEmail(usuarioDetails.getEmail());
		usuario.get().setEstado(usuarioDetails.getEstado());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario.get()));
	}
	
	//Borar
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable(value = "id") Long usuarioId){
		if(!usuarioService.findById(usuarioId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		usuarioService.deleteById(usuarioId);
		return ResponseEntity.ok().build();
	}
	
	//listar
	@GetMapping
	public List<Usuario> readAll(){
		List<Usuario> usuario=StreamSupport
				.stream(usuarioService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return usuario;
	}
	
	
	
}
