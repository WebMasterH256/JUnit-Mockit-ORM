package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
public class Pedido {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data;
	
	@ManyToMany
	@JoinTable(
			name = "pedido_produto",
			joinColumns = @JoinColumn(name = "pedido_id"),
			inverseJoinColumns = @JoinColumn(name = "produto_id")
	)
	private List<Produto> produtos;
	
	public Pedido() {}
	public Pedido(LocalDate data, List<Produto> produtos) {
		this.data = data;
		this.produtos = produtos;
	}
	
}