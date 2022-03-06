package com.projecto.java.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.projecto.java.model.Modelo;

public interface ModeloService {

	List<Modelo> findAllModelos();

	Modelo findByModelo(int id);

	Modelo saveModelo(Modelo modelo);

	void deleteModelo(int id);

}