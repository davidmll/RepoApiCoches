package com.projecto.java.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.projecto.java.model.Coche;


@Repository
public interface CocheRepository extends CrudRepository<Coche, Integer> {
	
}
