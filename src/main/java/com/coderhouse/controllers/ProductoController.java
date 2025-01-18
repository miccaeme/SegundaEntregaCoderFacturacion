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
import com.coderhouse.models.Producto;
import com.coderhouse.repositories.ProductoRepository;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {
	@Autowired
	//Si no se inyecta no se puede acceder a sus metodos
	private ProductoRepository productoRepository ;
	
	@GetMapping
	public List<Producto> getAllProducto(){
		
		return productoRepository.findAll();
	} //busca a todos los alumnos
	
	@GetMapping("/{id}")
	// Busca al producto, si lo encuentra confirma y guarda y si no muestra error 404.
	//Busca un solo alumno por ID.
	public ResponseEntity <Producto> getProductoById(@PathVariable Long id){
		if (productoRepository.existsById(id)) {
			Producto producto = productoRepository.findById(id).get();
		return ResponseEntity.ok(producto);
		
		} else {
			return ResponseEntity.notFound().build();
			}
	}
	
	@PostMapping
	public Producto createProducto(@RequestBody Producto producto) {
		return productoRepository.save(producto);
		
	}
	
}
