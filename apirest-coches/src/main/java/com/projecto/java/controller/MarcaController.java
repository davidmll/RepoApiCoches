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

import com.projecto.java.model.Marca;
import com.projecto.java.service.MarcaService;

@RestController
@RequestMapping("/api")
public class MarcaController {

	@Autowired
	private MarcaService service;
	
//	Methods Get 

	@GetMapping("/marcas")
	public List<Marca> index() {
		return service.findAllMarcas();
	}

	@GetMapping("/marca/{id}")
	public ResponseEntity<?> findByMarca(@PathVariable int id) {

		Marca marca = null;
		Map<String, Object> response = new HashMap<>();

		try {
			marca = service.findByMarca(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (marca == null) {
			response.put("mensaje", "La marca con ID: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Marca>(marca, HttpStatus.OK);
	}

//	Method Post

	@PostMapping("/saveMarca")
	public ResponseEntity<?> saveMarca(@RequestBody Marca marca) {
		Marca marcaNew = null;

		Map<String, Object> response = new HashMap<>();

		try {

			marcaNew = service.saveMarca(marca);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", marcaNew);
		response.put("marca", "La marca ha sido creado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

//	Method Put

	@PutMapping("/updateMarca/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateMarca(@RequestBody Marca marca, @PathVariable int id) {

		Marca marcaUpdate = service.findByMarca(id);

		Map<String, Object> response = new HashMap<>();

		if (marcaUpdate == null) {
			response.put("mensaje", "Error: no se puedo editar la marca con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			
			marcaUpdate.setIdmarca(id);
			marcaUpdate.setNombre(marca.getNombre());
			marcaUpdate.setCoches(marca.getCoches());

			service.saveMarca(marcaUpdate);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("marca", marcaUpdate);
		response.put("mensaje", "La marca ha sido actualizada con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

//	Method Delete

	@DeleteMapping("/deleteMarca/{id}")
	public ResponseEntity<?> deleteMarca(@PathVariable int id) {

		Marca marcaDelete= service.findByMarca(id);
		Map<String, Object> response = new HashMap<>();

		if (marcaDelete == null) {
			response.put("mensaje", "Error: no se pudo eliminar la marca con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			service.deleteMarca(id);
//			Funciona pero en postman da error en la coleccion por inicializacion
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar la información en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("marca", marcaDelete);
		response.put("mensaje", "La marca ha sido eliminada con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
