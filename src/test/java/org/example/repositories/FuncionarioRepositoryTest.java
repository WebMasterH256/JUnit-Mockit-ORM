package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class FuncionarioRepositoryTest {
	
	//todo O @Mock cria um objeto falso que finge ser da classe declarada.
	//todo Nesse casso, não há nenhum banco por trás desse teste
	@Mock
	private EntityManager em;
	
	@Mock
	private EntityTransaction tx;
	
	@Mock
	private TypedQuery<Funcionario> query; // novo Mock no topo da classe
	
	@InjectMocks
	private FuncionarioRepository repository;
	
	//todo Um teste tem que utilizar alguns dos métodos presentes no @BeforeEach.
	// No contrário ocorrerá uma "UnnecessaryStubbingException"
	@BeforeEach
	void setUp() {
		// quando o "getTransaction()" (prsente no métod0 save) for chamado,
		// ele retorna outro objeto @Mock
		// lenient().when(em.getTransaction()).thenReturn(tx);
		//todo Usar o "lenient()" permite um método não ser obrigatoriamente utilizado
	}
	
	@Test
	void deveSalvarFuncionario() {
		// ARRANGE
		var depto = new Departamento("TI");
		var funcionario = new Funcionario("Carlos", depto);
		
		when(em.getTransaction()).thenReturn(tx);
		
		// ACT
		repository.salvar(funcionario);
		
		//todo ASSERT: Verifica se o "em.persisit()" foi chamado com o funcionário
		// O "times()" indica a quantidade de vezes que o métod0 foi chamado
		verify(em, times(1)).persist(funcionario);
	}
	
	@Test
	void deveBuscarPorId() {
		// ARRANGE
		var id = 1L;
		
		// ACT
		repository.buscarPorId(id);
		
		// ASSERT
		verify(em, times(1)).find(Funcionario.class, id);
	}
	
	@Test
	void deveListarTodos() {
		
		// ARRANGE
		String consulta = "SELECT f FROM Funcionario f";
		
		// Ensina: quando createQuery() for chamado, retorna o dublê query
		when(em.createQuery(consulta, Funcionario.class))
				.thenReturn(query);
		
		// Ensina: quando getResultList() for chamado no query, retorna lista vazia
		when(query.getResultList()).thenReturn(List.of());
		
		// ACT
		repository.listarTodos();
		
		// ASSERT
		verify(em, times(1)).createQuery(consulta, Funcionario.class);
	}
	
	@Test
	void deveDeletar() {
		// ARRANGE
		var id = 1L;
		// Usa-se um funcionário de exemplo para verificar o métod0
		var funcionario = new Funcionario("Carlos", new Departamento("TI"));
		when(em.find(Funcionario.class, id)).thenReturn(funcionario);
		
		//todo Ele diz o que deve retornar em todo método adicionado
		when(em.getTransaction()).thenReturn(tx);
		
		// ACT
		repository.deletar(id);
		
		// ASSERT
		verify(em).remove(funcionario);
	}
}