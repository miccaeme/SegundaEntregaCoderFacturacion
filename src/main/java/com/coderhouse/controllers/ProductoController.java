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

import com.coderhouse.models.Cliente;
import com.coderhouse.models.Producto;
import com.coderhouse.repositories.ProductoRepository;
import com.coderhouse.service.ClienteService;
import com.coderhouse.service.ProductoService;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {
	
	@Autowired
	//Si no se inyecta no se puede acceder a sus metodos
	private ProductoService productoService ;
	
	@GetMapping
	public ResponseEntity<List<Producto>> getAllProducto(){
		try {
			List<Producto> producto = productoService.getAllProducto();
			return ResponseEntity.ok(producto);
		
		}catch (Exception e) {
				
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//error 500
		}
	} 
	
	@GetMapping("/{id}")
	// Busca al cliente, si lo encuentra confirma y guarda y si no muestra error 404.
	//Busca un solo alumno por ID.
	public ResponseEntity <Producto> getProductoById(@PathVariable Long id){
		try {
				Producto producto = productoService.findById(id);
				return ResponseEntity.ok(producto);// 200
		}
		catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build(); //400
		}
				
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping
	public ResponseEntity <Producto> createProducto(@RequestBody Producto producto) {
		try {
			Producto productoCreado = productoService.saveProducto(producto);
			return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);//200
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}}
		
		@PutMapping("/{id}")
		public ResponseEntity<Producto> updateProgramaById(@PathVariable Long id, @RequestBody Producto productoModificado) {
			try {
				Producto updateProducto = productoService.updateProductoById(id, productoModificado);
				return ResponseEntity.ok(updateProducto);
			} catch (IllegalArgumentException e) {
				return ResponseEntity.notFound().build(); // 404
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
			}}
		

		@DeleteMapping("/{id}")
		public ResponseEntity<Void> deleteProductoById(@PathVariable Long id) {
			try {
				productoService.deleteProductoById(id);
				return ResponseEntity.noContent().build(); // 400

			} catch (IllegalArgumentException e) {
				return ResponseEntity.notFound().build(); // 404

			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
			}
		
		}}
