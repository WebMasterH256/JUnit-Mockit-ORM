package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Funcionario {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamento;
	
	@OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
	private List<Produto> produtos;
	
	public Funcionario() {}
	public Funcionario(String nome, Departamento departamento) {
		this.nome = nome;
		this.departamento = departamento;
	}
	
}