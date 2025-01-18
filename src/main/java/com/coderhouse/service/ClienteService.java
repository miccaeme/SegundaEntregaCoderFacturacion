package com.coderhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderhouse.models.Cliente;
import com.coderhouse.repositories.ClienteRepository;

@Service

public class ClienteService {
	@Autowired
	//Si no se inyecta no se puede acceder a sus metodos
	private ClienteRepository clienteRepository ;
	
	public List<Cliente> getAllCliente(){
		return clienteRepository.findAll();
	}
	
	public Cliente findById(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
	
	}
	@Transactional
	
	public Cliente saveCliente (Cliente cliente) {
		return clienteRepository.save(cliente);
		
	}
	
	@Transactional
	public Cliente updateClienteById(Long id, Cliente clienteDetails) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
		cliente.setNombre(clienteDetails.getNombre());
		cliente.setApellido(clienteDetails.getApellido());
		
		if(clienteDetails.getDNI()!=0){
			
			cliente.setDNI(clienteDetails.getDNI());
			}
		if (clienteDetails.getnDeCliente()!=null && !clienteDetails.getnDeCliente().isEmpty()){
			
			cliente.setnDeCliente(clienteDetails.getnDeCliente());
			
			
		}
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void deleteClienteById(Long id) {
		if(!clienteRepository.existsById(id)) {
			throw new IllegalArgumentException("Cliente no encontrado");
		}
		clienteRepository.deleteById(id);
	}
	
}
