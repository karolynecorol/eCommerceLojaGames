package com.eCommerce.lojaGames.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.lojaGames.model.Categoria;
import com.eCommerce.lojaGames.model.Produto;
import com.eCommerce.lojaGames.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping("/all") // findAll --> Capacidade de puxar todas as categorias.
	public ResponseEntity<List<Produto>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}") //findById --> Capacidade de trazer uma única categoria por Id.
	//ResponseEntity --> Necessário para rodar.
	public ResponseEntity<Produto> GetById(@PathVariable long id){ //PathVariable --> Pega o valor que vem pela URL
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp)) // Resposta
				.orElse(ResponseEntity.notFound().build()); // 404 Not Found
	}

	@GetMapping("/{produto}") 
		public ResponseEntity<List<Produto>> GetByProduto (@PathVariable String produto)  {
			return ResponseEntity.ok(repository.findAllByProdutoContainingIgnoreCase(produto));
	}     

	@PostMapping("/new")
		public ResponseEntity <Produto> newProduto (@RequestBody Produto newProduto) {
			return ResponseEntity.status(201).body(repository.save(newProduto));
		}

	@PutMapping("/edit")
		public ResponseEntity <Produto> editProduto (@RequestBody Produto editProduto) {
			return ResponseEntity.status (200).body(repository.save(editProduto));
	}

	@DeleteMapping ("/delete/{id}")
		public void deleteProduto (@PathVariable long id) {
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
