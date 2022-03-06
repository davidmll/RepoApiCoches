package com.projecto.java.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projecto.java.model.Modelo;

@Repository
public interface ModeloRepository extends CrudRepository<Modelo, Integer> {

}
