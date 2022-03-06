package com.projecto.java.service;

import java.util.List;

import com.projecto.java.model.Coche;

public interface CocheService {

	List<Coche> findAllCoches();

	Coche findByCoche(int id);

	Coche saveCoche(Coche coche);

	void deleteCoche(int id);

}