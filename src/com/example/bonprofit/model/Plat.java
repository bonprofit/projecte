package com.example.bonprofit.model;

public class Plat {

	private String idplat;
	private String nom;
	private String descripcio;
	private String tipus;
	private String img;

	public Plat() {
		super();
	}

	public Plat(String idPlat, String nom, String descripcio, String tipus,
			String img) {
		super();
		this.idplat = idPlat;
		this.nom = nom;
		this.descripcio = descripcio;
		this.tipus = tipus;
		this.img = img;
	}

	public String getIdPlat() {
		return idplat;
	}

	public void setIdPlat(String idPlat) {
		this.idplat = idPlat;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public String getTipus() {
		return tipus;
	}

	public void setTipus(String tipus) {
		this.tipus = tipus;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
