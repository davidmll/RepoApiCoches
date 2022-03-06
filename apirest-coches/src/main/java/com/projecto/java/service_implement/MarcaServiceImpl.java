package com.projecto.java.service_implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projecto.java.Dao.MarcaRepository;
import com.projecto.java.model.Marca;
import com.projecto.java.service.MarcaService;

@Service
public class MarcaServiceImpl implements MarcaService {

	@Autowired
	private MarcaRepository repo;
	
	@Override
	@Transactional (readOnly = true)
	public List<Marca> findAllMarcas(){
		return (List<Marca>) repo.findAll();
	}
	
	@Override
	@Transactional (readOnly = true)
	public Marca findByMarca(int id) {
		return repo.findById(id).orElse(null);
	}
	
	@Override
	public Marca saveMarca(Marca marca) {
		return repo.save(marca);
	}
	
	@Override
	public void deleteMarca(int id) {
		repo.deleteById(id);
	}
}
