package com.projecto.java.service_implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projecto.java.Dao.CocheRepository;
import com.projecto.java.model.Coche;
import com.projecto.java.service.CocheService;

@Service
public class CocheServiceImpl implements CocheService {

	@Autowired
	private CocheRepository repo;
	
	@Override
	@Transactional (readOnly = true)
	public List<Coche> findAllCoches(){
		return (List<Coche>) repo.findAll();
	}
	
	@Override
	@Transactional (readOnly = true)
	public Coche findByCoche(int id) {
		return repo.findById(id).get();
	}
	
	@Override
	@Transactional (readOnly = true)
	public Coche saveCoche(Coche coche) {
		return repo.save(coche);
	}
	
	@Override
	@Transactional (readOnly = true)
	public void deleteCoche(int id) {
		repo.deleteById(id);
	}

}
