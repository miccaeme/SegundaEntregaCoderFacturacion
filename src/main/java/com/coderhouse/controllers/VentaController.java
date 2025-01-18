package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Categoria;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.Venta;
import com.coderhouse.repositories.CategoriaRepository;
import com.coderhouse.repositories.VentaRepository;
import com.coderhouse.service.ClienteService;
import com.coderhouse.service.VentaService;

@RestController
@RequestMapping ("/api/venta")
public class VentaController {
	@Autowired
	//Si no se inyecta no se puede acceder a sus metodos
	private VentaService ventaService ;
	
	@GetMapping
	public ResponseEntity<List<Venta>> getAllVenta(){
		try {
			List<Venta> venta = ventaService.getAllVenta();
			return ResponseEntity.ok(venta);
		
		}catch (Exception e) {
				
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//error 500
		}
	} 
	
	@GetMapping("/{id}")
	// Busca venta, si lo encuentra confirma y guarda y si no muestra error 404.
	//Busca por ID.
	public ResponseEntity <Venta> getVentaById(@PathVariable Long id){
		try {
			Venta venta =ventaService.findById(id);
				return ResponseEntity.ok(venta);// 200
		}
		catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build(); //400
		}
				
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping
	public ResponseEntity <Venta> createVenta(@RequestBody Venta venta) {
		try {
			Venta ventaCreado = ventaService.saveVenta(venta);
			return ResponseEntity.status(HttpStatus.CREATED).body(ventaCreado);//200
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}}
		
		@PutMapping("/{id}")
		public ResponseEntity<Venta> updateVentaById(@PathVariable Long id, @RequestBody Venta ventaModificada) {
			try {
				Venta updateVenta = ventaService.updateVentaById(id, ventaModificada);
				return ResponseEntity.ok(updateVenta);
			} catch (IllegalArgumentException e) {
				return ResponseEntity.notFound().build(); // 404
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
			}}
		

		@DeleteMapping("/{id}")
		public ResponseEntity<Void> deleteVentaById(@PathVariable Long id) {
			try {
				ventaService.deleteVentaById(id);
				return ResponseEntity.noContent().build(); // 400

			} catch (IllegalArgumentException e) {
				return ResponseEntity.notFound().build(); // 404

			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
			}
		
}}
