package com.generation.lojagames.ProdutoController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.generation.lojagames.ProdutoRepository.CategoriaRepository;
import com.generation.lojagames.model.Categoria;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*" ,allowedHeaders = "*") //@CrossOrigin(origins = "", allowedHeaders = ""): Essa linha está permitindo que qualquer site (origem) possa fazer solicitações para o servidor 
//onde este código está rodando. Isso é feito para facilitar o acesso aos recursos do servidor de diferentes lugares na web.
//origins = "*": O asterisco (*) aqui significa "todos os sites". Isso permite que qualquer site, não importa de onde, possa acessar os recursos do servidor.
//allowedHeaders = "*": De novo, o asterisco (*) significa "todos os cabeçalhos". Isso permite que qualquer tipo de informação nos cabeçalhos das solicitações possa ser enviada para o servidor.

public class CategoriaController {
	
	 @Autowired // injeção de dependencias - estou trazendo o tema repository injentando todo o codigo aqui na controller.
	    private CategoriaRepository categoriaRepository;
	    
	 // Método para buscar todos os temas
	    @GetMapping
	    public ResponseEntity<List<Categoria>> getAll(){
	        return ResponseEntity.ok(categoriaRepository.findAll()); // 
		    }

	 // Método para buscar um tema por ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Categoria> getById(@PathVariable Long id){
	        return categoriaRepository.findById(id)
	            .map(resposta -> ResponseEntity.ok(resposta))
	            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());//NOT_FOUND . 404
	    }

	    // Método para buscar temas pela descrição
	    @GetMapping("/categoria/{descricao}")
	    public ResponseEntity<List<Categoria>> getByDescricao(@PathVariable 
	    String descricao){
	        return ResponseEntity.ok(categoriaRepository
	            .findAllByCategoriaContainingIgnoreCase(descricao));
	    }
	    
	    // Método para criar um novo tema
	    @PostMapping
	    public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria){
	        return ResponseEntity.status(HttpStatus.CREATED)
	                .body(categoriaRepository.save(categoria));
	    }

	    // Método para atualizar um tema existente
	    @PutMapping
	    public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria){
	        return categoriaRepository.findById(categoria.getId())
	            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
	            .body(categoriaRepository.save(categoria)))
	            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	    }

	    // Método para deletar um tema por ID
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        Optional<Categoria> tema = categoriaRepository.findById(id);
	        
	        if(tema.isEmpty())// verificar se tem o id para deletar
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	        
	        categoriaRepository.deleteById(id);              
	    }


}    
