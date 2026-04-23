package org.example;

import jakarta.persistence.Persistence;

public class Main {
	public static void main(String[] args) {
		var emf = Persistence.createEntityManagerFactory("hibernate-testes");
		var em = emf.createEntityManager();
		
		System.out.println("Conexão estabelecida com sucesso!");
		
		em.close();
		emf.close();
	}
}