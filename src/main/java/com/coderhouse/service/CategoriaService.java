package com.coderhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderhouse.models.Categoria;
import com.coderhouse.models.Cliente;
import com.coderhouse.repositories.CategoriaRepository;
import com.coderhouse.repositories.ClienteRepository;

@Service
public class CategoriaService {
	@Autowired
	//Si no se inyecta no se puede acceder a sus metodos
	private CategoriaRepository categoriaRepository ;
	
	public List<Categoria> getAllCategoria(){
		return categoriaRepository.findAll();
	}
	
	public Categoria findById(Long id) {
		return categoriaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Categoria no encontrada"));
	
	}
	@Transactional
	
	public Categoria saveCategoria (Categoria categoria) {
		return categoriaRepository.save(categoria);
		
	}
	
	@Transactional
	public Categoria updateCategoriaById(Long id, Categoria categoriaDetails) {
		Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
		categoria.setNombre(categoriaDetails.getNombre());
		categoria.setDescripcion(categoriaDetails.getDescripcion());
		
		
		return categoriaRepository.save(categoria);
	}
	
	@Transactional
	public void deleteCategoriaById(Long id) {
		if(!categoriaRepository.existsById(id)) {
			throw new IllegalArgumentException("Categoria no encontrada");
		}
		categoriaRepository.deleteById(id);
	}
	

}
