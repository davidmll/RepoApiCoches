package com.projecto.java.service_implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projecto.java.Dao.ModeloRepository;
import com.projecto.java.model.Modelo;

@Service
public class ModeloServiceImpl {

	@Autowired
	private ModeloRepository repo;

	@Transactional (readOnly = true)
	public List<Modelo> findAllModelos(){
		return  (List<Modelo>) repo.findAll();
	}
	
	@Transactional (readOnly = true)
	public Modelo findByModelo(int id) {
		return repo.findById(id).get();
	}
	
	@Transactional (readOnly = true)
	public Modelo saveModelo(Modelo modelo) {
		return repo.save(modelo);
	}
	
	@Transactional (readOnly = true)
	public void deleteModelo(int id) {
		repo.deleteById(id);
	}
}
