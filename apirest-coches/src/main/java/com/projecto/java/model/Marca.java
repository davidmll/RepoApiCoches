package com.projecto.java.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "marcas")
public class Marca implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	Attributes
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idmarca;
	
	private String nombre;
	
	@OneToMany(mappedBy = "marca")
	private List<Coche> coches;
	
//	Getters and Setters

	public int getIdmarca() {
		return idmarca;
	}

	public void setIdmarca(int idmarca) {
		this.idmarca = idmarca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Coche> getCoches() {
		return coches;
	}

	public void setCoches(List<Coche> coches) {
		this.coches = coches;
	}
}
