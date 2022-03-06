package com.projecto.java.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.projecto.java.model.Marca;

public interface MarcaService {

	List<Marca> findAllMarcas();

	Marca findByMarca(int id);

	Marca saveMarca(Marca marca);

	void deleteMarca(int id);

}