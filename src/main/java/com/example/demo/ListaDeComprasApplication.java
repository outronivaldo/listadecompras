package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import domain.ListaDeCompras;
import repositories.ListaDeComprasRepository;



@SpringBootApplication
public class ListaDeComprasApplication implements CommandLineRunner {
	
	@Autowired
	private ListaDeComprasRepository listaRep;
	

	public static void main(String[] args) {
		SpringApplication.run(ListaDeComprasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ListaDeCompras lista1 = new ListaDeCompras();
		lista1.setNome("Lista teste");
		
		listaRep.save(lista1);
	}

}
