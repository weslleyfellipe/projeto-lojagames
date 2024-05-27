package com.generation.lojagames.ProdutoRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.lojagames.model.Categoria;




public interface CategoriaRepository  extends JpaRepository<Categoria, Long>{
	
	//SELECT * FROM tb_temas WHERE descricao LIKE "%categorias%";
	public List<Categoria> findAllByCategoriaContainingIgnoreCase(@Param("Categorias") String categoria);


}
