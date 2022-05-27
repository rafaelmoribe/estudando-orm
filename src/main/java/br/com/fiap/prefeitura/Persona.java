package br.com.fiap.prefeitura;

import java.util.Collection;
import jakarta.persistence.*;

/**
 * Classe que representa uma Persona. O objeto Persona é usado para definir como
 * a página principal será apresentada.
 * 
 * @author Ana Sofia Nunez de Abreu, Bruno Vieira Campos Gouveia, Rafael
 *         Kimihiro Moribe, Tiago Vieira Cavalcante
 *
 */

@Entity
@Table(name = "tbl_persona")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "per_seq")
	@SequenceGenerator(name = "per_seq", sequenceName = "TBL_PERSONA_SEQ", allocationSize = 1)
	@Column(name = "id_persona")
	private Integer id_persona;

	@Column(name = "nome_persona")
	private String nome_persona;

	@OneToMany(mappedBy = "persona")
	private Collection<Cidadao> cidadaos;

	public Collection<Cidadao> getCidadaos() {
		return cidadaos;
	}

	public void setCidadaos(Collection<Cidadao> cidadaos) {
		this.cidadaos = cidadaos;
	}

	public void setId_persona(Integer id_persona) {
		this.id_persona = id_persona;
	}

	public Integer getId_persona() {
		return id_persona;
	}

	public void setId(Integer id_persona) {
		this.id_persona = id_persona;
	}

	public String getNome_persona() {
		return nome_persona;
	}

	public void setNome_persona(String nome_persona) {
		this.nome_persona = nome_persona;
	}

	@Override
	public String toString() {
		return ("ID-" + id_persona.toString() + " | " + nome_persona);
	}

}
