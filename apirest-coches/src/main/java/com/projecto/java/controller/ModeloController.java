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

import com.projecto.java.model.Modelo;
import com.projecto.java.service.ModeloService;

@RestController
@RequestMapping("/api")
public class ModeloController {

	@Autowired
	private ModeloService service;
	
//	Methods Get 

	@GetMapping("/modelos")
	public List<Modelo> index() {
		return service.findAllModelos();
	}

	@GetMapping("/modelo/{id}")
	public ResponseEntity<?> findByModelo(@PathVariable int id) {

		Modelo modelo = null;
		Map<String, Object> response = new HashMap<>();

		try {
			modelo = service.findByModelo(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (modelo == null) {
			response.put("mensaje", "El modelo con ID: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Modelo>(modelo, HttpStatus.OK);
	}

//	Method Post

	@PostMapping("/saveModelo")
	public ResponseEntity<?> saveModelo(@RequestBody Modelo modelo) {
		Modelo modeloNew = null;

		Map<String, Object> response = new HashMap<>();

		try {

			modeloNew = service.saveModelo(modelo);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", modeloNew);
		response.put("marca", "El modelo ha sido creado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

//	Method Put

	@PutMapping("/updateModelo/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateModelo(@RequestBody Modelo modelo, @PathVariable int id) {

		Modelo modeloUpdate = service.findByModelo(id);

		Map<String, Object> response = new HashMap<>();

		if (modeloUpdate == null) {
			response.put("mensaje", "Error: no se puedo editar el modelo con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			
			modeloUpdate.setIdmodelo(id);
			modeloUpdate.setNombre(modelo.getNombre());
			modeloUpdate.setFecha_modelo(modelo.getFecha_modelo());
			modeloUpdate.setCoches(modelo.getCoches());

			service.saveModelo(modeloUpdate);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("modelo", modeloUpdate);
		response.put("mensaje", "El modelo ha sido actualizado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

//	Method Delete

	@DeleteMapping("/deleteModelo/{id}")
	public ResponseEntity<?> deleteModelo(@PathVariable int id) {

		Modelo modeloDelete= service.findByModelo(id);
		Map<String, Object> response = new HashMap<>();

		if (modeloDelete == null) {
			response.put("mensaje", "Error: no se pudo eliminar el modelo con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			service.deleteModelo(id);
//			Funciona pero en postman da error en la coleccion por inicializacion
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar la información en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("modelo",modeloDelete);
		response.put("mensaje", "El modelo ha sido eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
