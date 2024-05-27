package com.generation.lojagames.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tb_categoria")//nomeando a tabela no banco de dados de tb_categoria
public class Categoria {
	
	@Id // torna o campo uma chave primaria no banco de dados 
	@GeneratedValue(strategy=GenerationType.IDENTITY)// tornando a chave primaria auto increment 
	private Long id;
	
	@NotBlank(message = "Esse campo deve ser preenchido apenas com caracteres ! ")// validation - validar nosso atributo NN e tambem não vazio
	@Size(min= 1, max = 20 , message = "O atributo  nome  deve ter no minimo 3 caractere e no maximo 50 .")
	private String categoria ;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties
    private List<Produto> produto;

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	
}
