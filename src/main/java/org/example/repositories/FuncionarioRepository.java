package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.entities.Funcionario;
import java.util.List;

public class FuncionarioRepository {
	
	private final EntityManager em;
	
	public FuncionarioRepository(EntityManager em) {
		this.em = em;
	}
	
	public Funcionario salvar(Funcionario f) {
		em.getTransaction().begin();
		em.persist(f);
		em.getTransaction().commit();
		return f;
	}
	
	public Funcionario buscarPorId(Long id) {
		return em.find(Funcionario.class, id);
	}
	
	public List<Funcionario> listarTodos() {
		return em.createQuery("SELECT f FROM Funcionario f", Funcionario.class).getResultList();
	}
	
	public void deletar(Long id) {
		em.getTransaction().begin();
		Funcionario f = em.find(Funcionario.class, id);
		if (f != null) em.remove(f);
		em.getTransaction().commit();
	}
}