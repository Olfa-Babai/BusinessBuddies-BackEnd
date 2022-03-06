package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dictionnary implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iddictionnary;
	private String word;
	public int getIddictionnary() {
		return iddictionnary;
	}
	public void setIddictionnary(int iddictionnary) {
		this.iddictionnary = iddictionnary;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	
}
