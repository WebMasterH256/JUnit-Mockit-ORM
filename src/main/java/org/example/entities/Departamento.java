package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter @Setter
public class Departamento 
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
	private List<Funcionario> funcionarios;
	
	public Departamento() {}
	public Departamento(String nome) { this.nome = nome; }
	
}