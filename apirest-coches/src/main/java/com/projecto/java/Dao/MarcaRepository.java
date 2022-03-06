package com.projecto.java.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projecto.java.model.Marca;

@Repository
public interface MarcaRepository extends CrudRepository<Marca, Integer> {

}
