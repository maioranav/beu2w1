package org.vm93.beu2w1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="postazioni")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Postazione {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private PostazioneType type;
	
	private Integer maxp;
	
	@ManyToOne
	@JoinColumn
	private Edificio edificio;

	public Postazione(String description, PostazioneType type, Integer maxp, Edificio edificio) {
		super();
		this.description = description;
		this.type = type;
		this.maxp = maxp;
		this.edificio = edificio;
	}
	
	
	
}
