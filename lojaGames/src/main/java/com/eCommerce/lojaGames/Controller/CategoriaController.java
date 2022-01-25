package com.eCommerce.lojaGames.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.lojaGames.model.Categoria;
import com.eCommerce.lojaGames.repository.CategoriaRepository;


@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping("/all") // findAll --> Capacidade de puxar todas as categorias.
	public ResponseEntity<List<Categoria>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}") //findById --> Capacidade de trazer uma única categoria por Id.
	//ResponseEntity --> Necessário para rodar.
	public ResponseEntity<Categoria> GetById(@PathVariable long id){ //PathVariable --> Pega o valor que vem pela URL
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp)) // Resposta
				.orElse(ResponseEntity.notFound().build()); // 404 Not Found
	}

	@GetMapping("/{categoria}") // findAllByCategoriaContainingIgnoreCase --> Capacidade de trazer uma categoria pelo nome da categoria, sem diferenciar maiúscula e minúscula
	public ResponseEntity<List<Categoria>> GetByCategoria(@PathVariable String categoria) {
		return ResponseEntity.ok(repository.findAllByCategoriaContainingIgnoreCase(categoria));
	}
	
	@PostMapping("/new") // Salva no banco de dados
	public ResponseEntity<Categoria> newCategoria(@RequestBody Categoria newCategoria) {
		return ResponseEntity.status(201).body(repository.save(newCategoria));
	}

	@PutMapping("/edit") // Edita valores no banco de dados
	public ResponseEntity<Categoria> editCategoria(@RequestBody Categoria editCategoria) {
		return ResponseEntity.status(200).body(repository.save(editCategoria));
	}

	@DeleteMapping("/delete/{id}") // Deleta linha no banco de dados por id
	public void deleteCategoria(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	
	/*
	FIND ALL - OK 
	FIND ID - OK
	FIND CATEGORIA - OK
	POST - OK
	PUT - OK
	DELETE - OK	
	*/
}
