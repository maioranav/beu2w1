package org.vm93.beu2w1.model;

import org.vm93.beu2w1.config.accessCodeConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="edifici")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Edificio {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, unique = true)
	private String indirizzo;
	
	private String citta;
	
	@Convert(converter = accessCodeConverter.class)
	private String accesscode;


	public Edificio(String nome, String indirizzo, String citta) {
		super();
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.citta = citta;
	}
	
	public Edificio(String nome, String indirizzo, String citta, String accesscode) {
		this(nome, indirizzo, citta);
		this.accesscode = accesscode;
	}
	
}
