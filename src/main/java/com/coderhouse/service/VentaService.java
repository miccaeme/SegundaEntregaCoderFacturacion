package com.coderhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderhouse.models.Cliente;
import com.coderhouse.models.Venta;
import com.coderhouse.repositories.ClienteRepository;
import com.coderhouse.repositories.VentaRepository;

@Service
public class VentaService {
	@Autowired
	
	private VentaRepository ventaRepository ;
	
	public List<Venta> getAllVenta(){
		return ventaRepository.findAll();
	}
	
	public Venta findById(Long id) {
		return ventaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
	
	}
	@Transactional
	
	public Venta saveVenta (Venta venta) {
		return ventaRepository.save(venta);
		
	}
	
	@Transactional
	public Venta updateVentaById(Long id, Venta ventaDetails) {
		Venta venta = ventaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
		venta.setMontoTotal(ventaDetails.getMontoTotal());
		venta.setEstado(ventaDetails.getEstado());
		venta.setMetodoDePago(ventaDetails.getMetodoDePago());
		venta.setNombreCliente(ventaDetails.getNombreCliente());
		
		return ventaRepository.save(venta);
	}
	
	@Transactional
	public void deleteVentaById(Long id) {
		if(!ventaRepository.existsById(id)) {
			throw new IllegalArgumentException("Venta no encontrada");
		}
		ventaRepository.deleteById(id);
	}
	
}
