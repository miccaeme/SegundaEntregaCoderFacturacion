package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Cliente;
import com.coderhouse.repositories.clienteRepository;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	
	@Autowired
	//Si no se inyecta no se puede acceder a sus metodos
	private clienteRepository clienteRepository ;
	
	@GetMapping
	public List<Cliente> getAllClient(){
		
		return clienteRepository.findAll();
	} //busca a todos los clientes
	
	@GetMapping("/{id}")
	// Busca al cliente, si lo encuentra confirma y guarda y si no muestra error 404.
	//Busca un solo alumno por ID.
	public ResponseEntity <Cliente> getClienteById(@PathVariable Long id){
		if (clienteRepository.existsById(id)) {
			Cliente cliente = clienteRepository.findById(id).get();
		return ResponseEntity.ok(cliente);
		
		} else {
			return ResponseEntity.notFound().build();
			}
	}
	
	@PostMapping
	public Cliente createCliente(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
		
	}
	}
