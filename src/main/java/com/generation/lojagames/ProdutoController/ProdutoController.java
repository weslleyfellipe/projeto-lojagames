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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.generation.lojagames.ProdutoRepository.CategoriaRepository;
import com.generation.lojagames.ProdutoRepository.ProdutoRepository;
import com.generation.lojagames.model.Produto;

import jakarta.validation.Valid;

@RestController// anotação que diz para o spring que essa é uuma controladora de rotas e acesso ao metodos 
@RequestMapping("/produtos")//rota para chegar nessa classe "insomnia"
@CrossOrigin(origins = "*",allowedHeaders = "*")//liberar acesso a outras maquinas /allowedHeaders = liberar passagem para paramentos para outras maquinas 
public class ProdutoController {

	@Autowired // chamada de  dependencias -- instaciar a Classe ProdutoRepository com o codigo abaixo
	private ProdutoRepository produtoRepository;	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping//defini o verbo http que atende esse metodo
	public ResponseEntity<List<Produto>> getAll(){
		//ResponseEntity - uma resposta que vai formata em http
		return ResponseEntity.ok(produtoRepository.findAll());
	//sELECT * FROM tb_postagens

	}
    
	@GetMapping("/{id}")//localhost:8080/postagens/1
	public ResponseEntity<Produto>getByid(@PathVariable Long id ){
		//findByid= SELECT * FROM tb_postagens WHERE id = 1
 	return produtoRepository.findById(id)      //Procura uma postagem pelo seu identificador (id).
			//	Se encontrar a postagem, retorna essa postagem junto com uma mensagem dizendo que tudo está ok.
 	.map(resposta -> ResponseEntity.ok(resposta))    
	.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // vai fazer a resposta se for falsa e vai apenas buildar e a resposta é Not_Found error 404
		
		}
		
	
	@GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> getByTitulo(@PathVariable String nome){
        return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
    }
  @PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto){
		if(categoriaRepository.existsById(produto.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED)
				.body(produtoRepository.save(produto));
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Essa categoria não existe!", null);
	}


    @PutMapping
    
	  public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto){
		// Verifica se a postagem existe no banco de dados pelo seu ID
		   if(produtoRepository.existsById(produto.getId())){
		   		// Se a postagem existe, verifica se o tema associado também existe
		   if(categoriaRepository.existsById(produto.getCategoria().getId()))
			 // Se o tema existe, salva a produto atualizada e retorna um status OK (200)
	   		return ResponseEntity.status(HttpStatus.OK)
          .body(produtoRepository.save(produto)); // se todas condincoes for atendida vai salvar.
		    // Se o tema não existe, lança uma exceção informando que o tema não existe
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Produto não existe !", null);
		 
	 }
     	// Se a produto não existe, retorna um status de não encontrado (404)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
    }
    @DeleteMapping ("/{id}")
	 public void  delete(@PathVariable Long id) {
		 Optional<Produto> postagem = produtoRepository.findById(id);// USANDO O OPTIONAL PARA O CODIGO NAO QUEBRAR MESMO ELE ESTANDO VAZIO .optional retornando o objeto inteiro ou vazio.
		 
		 if(postagem.isEmpty())// VALIDANDO SE ESTA VAZIO IS.EMPTY  se caso sim ele vai executar o throw new executando o not found.
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND);// meu if vai executar até o primeiro ponto e virgula ;
		 
		 produtoRepository.deleteById(id);
			 
    
    }
    
    @GetMapping("/ordenados-por-maior-preco")
    public List<Produto> listarProdutosOrdenadosPorPrecomaior() {
        return produtoRepository.findAllByOrderByPrecoDesc();
    }

    @GetMapping("/ordenados-por-menor-preco")
    public List<Produto> listarProdutosOrdenadosPorPrecomenor() {
        return produtoRepository.findAllByOrderByPrecoAsc();
    }

}
	
	

