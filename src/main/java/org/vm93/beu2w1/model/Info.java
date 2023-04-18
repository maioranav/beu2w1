package org.vm93.beu2w1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "langresp")
@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
public class Info {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String lang;
	private String description;
	
	public Info(String lang, String desc) {
		super();
		this.lang = lang;
		this.description = desc;
	}
	

}
