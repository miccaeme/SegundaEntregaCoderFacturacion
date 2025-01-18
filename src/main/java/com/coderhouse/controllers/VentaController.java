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

import com.coderhouse.models.Categoria;
import com.coderhouse.models.Venta;
import com.coderhouse.repositories.CategoriaRepository;
import com.coderhouse.repositories.VentaRepository;

@RestController
@RequestMapping
public class VentaController {
	@Autowired
	//Acceder a sus metodos
	private VentaRepository ventaRepository ;
	
	@GetMapping
	public List<Venta> getAllVenta(){
		
		return ventaRepository.findAll();
	} //Busca a todos las ventas
	
	@GetMapping("/{id}")
	// Busca a la venta, si lo encuentra confirma y guarda y si no muestra error 404.
	//Busca un solo alumno por ID.
	public ResponseEntity <Venta> getVentaById(@PathVariable Long id){
		if (ventaRepository.existsById(id)) {
			Venta venta = ventaRepository.findById(id).get();
		return ResponseEntity.ok(venta);
		
		} else {
			return ResponseEntity.notFound().build();
			}
	}
	
	@PostMapping
	public Venta createVenta(@RequestBody Venta venta) {
		return ventaRepository.save(venta);
		
	}
}
