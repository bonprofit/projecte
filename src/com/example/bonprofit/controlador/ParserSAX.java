package com.example.bonprofit.controlador;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.bonprofit.model.Plat;

public class ParserSAX extends DefaultHandler {
	/* atributs per saber quin tag estem tractant */
	private String cadena = "";

	private Plat p = null;

	/* atributs d'instància */
	private ArrayList<Plat> llista = null;

	public ParserSAX() {
		llista = new ArrayList<Plat>();
	}

	/**
	 * Inici d'un tag
	 */
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("plat")) {
			p = new Plat();
		}
	}

	/**
	 * Fi d'un tag
	 */
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (qName.equalsIgnoreCase("nom")) {
			p.setNom(cadena);
		}
		if (qName.equalsIgnoreCase("descripcio")) {
			p.setDescripcio(cadena);
		}
		if (qName.equalsIgnoreCase("idplat")) {
			p.setIdPlat(cadena);
		}
		if (qName.equalsIgnoreCase("img")) {
			p.setImg(cadena);
		}
		if (qName.equalsIgnoreCase("plat")) {
			llista.add(p);
		}
		cadena = "";
	}

	/**
	 * Contingut d'un tag
	 */
	public void characters(char ch[], int start, int length)
			throws SAXException {

		super.characters(ch, start, length);
		cadena = new String(ch, start, length);
	}

	/**
	 * Obtenir la llista d'entrades que s'ha llegit
	 * 
	 * @return
	 */
	public ArrayList<Plat> getLlista() {
		return llista;
	}
}