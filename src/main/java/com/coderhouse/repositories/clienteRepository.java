package com.coderhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Cliente;

public interface clienteRepository extends JpaRepository<Cliente, Long> {

}
