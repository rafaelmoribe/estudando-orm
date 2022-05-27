package br.com.fiap.prefeitura;

import java.util.Collection;
import jakarta.persistence.*;

/**
 * Classe que representa um cidadão básico. O objeto cidadão possui um objeto da
 * classe Persona que define como a página principal será apresentada.
 * 
 * @author Ana Sofia Nunez de Abreu, Bruno Vieira Campos Gouveia, Rafael
 *         Kimihiro Moribe, Tiago Vieira Cavalcante
 * 
 */

@Entity
@Table(name = "tbl_cidadao")
public class Cidadao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cidadao_seq")
	@SequenceGenerator(name = "cidadao_seq", sequenceName = "TBL_CIDADAO_SEQ", allocationSize = 1)
	@Column(name = "id_cidadao")
	private Integer id_cidadao;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "CPF")
	private String cpf;

	@Column(name = "TELEFONE")
	private String telefone;

	@JoinColumn(name = "id_persona")
	@ManyToOne
	private Persona persona;

	@OneToMany(mappedBy = "id_imovel")
	private Collection<Imovel> imoveis;

	public Collection<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(Collection<Imovel> imoveis) {
		this.imoveis = imoveis;
	}

	public Integer getId_cidadao() {
		return id_cidadao;
	}

	public void setId_cidadao(Integer id_cidadao) {
		this.id_cidadao = id_cidadao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return ("ID-" + id_cidadao.toString() + " | " + nome + ", " + cpf + ", " + telefone + ", "
				+ persona.getNome_persona());
	}

}
