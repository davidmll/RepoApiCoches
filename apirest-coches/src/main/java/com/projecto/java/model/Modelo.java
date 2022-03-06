package com.projecto.java.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "modelos")
public class Modelo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	Attribute
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idmodelo;
	
	private String nombre;
	@Temporal(TemporalType.DATE)
	private Date fecha_modelo;
	
	@OneToMany(mappedBy = "modelo")
	private List<Coche> coches;
	
//	Method
	@PrePersist
	public void prePersist() {
		fecha_modelo = new Date();
	}

//	Getters and Setters
	
	public int getIdmodelo() {
		return idmodelo;
	}

	public void setIdmodelo(int idmodelo) {
		this.idmodelo = idmodelo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha_modelo() {
		return fecha_modelo;
	}

	public void setFecha_modelo(Date fecha_modelo) {
		this.fecha_modelo = fecha_modelo;
	}

	public List<Coche> getCoches() {
		return coches;
	}

	public void setCoches(List<Coche> coches) {
		this.coches = coches;
	}	
}
