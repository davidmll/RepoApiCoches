package com.projecto.java.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projecto.java.model.Coche;
import com.projecto.java.service.CocheService;

@RestController
@RequestMapping("/api")
public class CocheController {

	@Autowired
	private CocheService service;

//	Methods Get 

	@GetMapping("/coches")
	public List<Coche> index() {
		return service.findAllCoches();
	}

	@GetMapping("/coche/{id}")
	public ResponseEntity<?> findByCoche(@PathVariable int id) {

		Coche coche = null;
		Map<String, Object> response = new HashMap<>();

		try {
			coche = service.findByCoche(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (coche == null) {
			response.put("mensaje", "El coche ID: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Coche>(coche, HttpStatus.OK);
	}

//	Method Post

	@PostMapping("/saveCoche")
	public ResponseEntity<?> saveCoche(@RequestBody Coche coche) {
		Coche cocheNew = null;

		Map<String, Object> response = new HashMap<>();

		try {

			cocheNew = service.saveCoche(coche);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", cocheNew);
		response.put("cliente", "El coche ha sido creado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

//	Method Put

	@PutMapping("/updateCoche/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateCoche(@RequestBody Coche coche, @PathVariable int id) {

		Coche cocheUpdate = service.findByCoche(id);

		Map<String, Object> response = new HashMap<>();

		if (cocheUpdate == null) {
			response.put("mensaje", "Error: no se puedo editar el coche con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			cocheUpdate.setIdcoche(id);
			cocheUpdate.setCilindrada(coche.getCilindrada());
			cocheUpdate.setColor(coche.getColor());
			cocheUpdate.setMarca(coche.getMarca());
			cocheUpdate.setModelo(coche.getModelo());
			cocheUpdate.setVelocidad(coche.getVelocidad());

			service.saveCoche(cocheUpdate);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("coche", cocheUpdate);
		response.put("mensaje", "El coche ha sido actualizado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

//	Method Delete

	@DeleteMapping("/deleteCoche/{id}")
	public ResponseEntity<?> deleteCoche(@PathVariable int id) {

		Coche cocheDelete = service.findByCoche(id);
		Map<String, Object> response = new HashMap<>();

		if (cocheDelete == null) {
			response.put("mensaje", "Error: no se pudo eliminar el cliente con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			service.deleteCoche(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar la información en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("coche", cocheDelete);
		response.put("mensaje", "El coche ha sido eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
