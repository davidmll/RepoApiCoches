package com.projecto.java.model;

import java.io.Serializable;


import javax.persistence.*;

@Entity
@Table (name = "coches")
public class Coche implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	Attributes
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idcoche;

	private String color;
	private String matricula;
	
	private int cilindrada;
	private double velocidad;
	
	@ManyToOne
	@JoinColumn(name = "idmarca")
	private Marca marca;
	
	@ManyToOne
	@JoinColumn(name = "idmodelo")
	private Modelo modelo;
	
//	Getters and setters

	public int getIdcoche() {
		return idcoche;
	}

	public void setIdcoche(int idcoche) {
		this.idcoche = idcoche;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}	
}
