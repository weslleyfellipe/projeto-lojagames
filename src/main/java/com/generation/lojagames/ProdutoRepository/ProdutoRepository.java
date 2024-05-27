package com.generation.lojagames.ProdutoRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.lojagames.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	//SELECT * FROM tb_temas WHERE descricao LIKE "%descricao%";
	public List<Produto> findAllByNomeContainingIgnoreCase(@Param("Nome") String nome);


}
