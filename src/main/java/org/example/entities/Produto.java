package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Produto 
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Double preco;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario responsavel;
	
	@ManyToMany(mappedBy = "produtos")
	private List<Pedido> pedidos;
	
	public Produto() {}
	public Produto(String nome, Double preco, Funcionario responsavel) {
		this.nome = nome;
		this.preco = preco;
		this.responsavel = responsavel;
	}
}