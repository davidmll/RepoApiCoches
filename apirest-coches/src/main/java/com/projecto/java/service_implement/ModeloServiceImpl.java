package com.projecto.java.service_implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projecto.java.Dao.ModeloRepository;
import com.projecto.java.model.Modelo;
import com.projecto.java.service.ModeloService;

@Service
public class ModeloServiceImpl implements ModeloService {

	@Autowired
	private ModeloRepository repo;

	@Override
	@Transactional (readOnly = true)
	public List<Modelo> findAllModelos(){
		return  (List<Modelo>) repo.findAll();
	}
	
	@Override
	@Transactional (readOnly = true)
	public Modelo findByModelo(int id) {
		return repo.findById(id).orElse(null);
	}
	
	@Override
	public Modelo saveModelo(Modelo modelo) {
		return repo.save(modelo);
	}
	
	@Override
	public void deleteModelo(int id) {
		repo.deleteById(id);
	}
}
