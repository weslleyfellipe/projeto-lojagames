package com.generation.lojagames.model;

import java.math.BigDecimal;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity // classe vai se tornar uma entidade do banco de dados
@Table(name="tb_produtos")//nomeando a tabela no banco de dados de tb_produtos

public class Produto {
	
	@Id // torna o campo uma chave primaria no banco de dados 
	@GeneratedValue(strategy=GenerationType.IDENTITY)// tornando a chave primaria auto increment 
	private Long id;
	
	@NotBlank(message = "Esse campo deve ser preenchido apenas com caracteres ! ")// validation - validar nosso atributo NN e tambem não vazio
	@Size(min= 3, max = 50 , message = "O atributo  nome  deve ter no minimo 3 caractere e no maximo 50 .")
    private String nome ;
	
	@NotBlank(message = "Esse campo deve ser preenchido apenas com caracteres ! ")// validation - validar nosso atributo NN e tambem não vazio
	@Size(min= 1, max = 20 , message = "O atributo  nome  deve ter no minimo 1 caractere e no maximo 20 .")
	private String console ;
	
	@Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal preco;
	
	@ManyToOne
    @JsonIgnoreProperties("produto")
    private Categoria categoria ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	


}
