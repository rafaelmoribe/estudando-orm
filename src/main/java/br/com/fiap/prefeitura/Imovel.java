package br.com.fiap.prefeitura;

import jakarta.persistence.*;

/**
 * Classe que representa um imóvel básico. O objeto imóvel possui associado um
 * cidadão que será associado como dono.
 * 
 * @author Ana Sofia Nunez de Abreu, Bruno Vieira Campos Gouveia, Rafael
 *         Kimihiro Moribe, Tiago Vieira Cavalcante
 *
 */

@Entity
@Table(name = "tbl_imovel")
public class Imovel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imo_seq")
	@SequenceGenerator(name = "imo_seq", sequenceName = "TBL_IMOVEL_SEQ", allocationSize = 1)
	@Column(name = "id_imovel")
	private Integer id_imovel;

	@Column(name = "inscricao")
	private Integer inscricao;

	@Column(name = "endereco")
	private String endereco;

	@Column(name = "cep")
	private String cep;

	@JoinColumn(name = "id_cidadao")
	@ManyToOne
	private Cidadao cidadao;

	@Column(name = "tamanho")
	private Integer tamanho;

	@Column(name = "iptu")
	private Integer iptu;

	public Integer getId_imovel() {
		return id_imovel;
	}

	public void setId_imovel(Integer id_imovel) {
		this.id_imovel = id_imovel;
	}

	public Integer getInscricao() {
		return inscricao;
	}

	public void setInscricao(Integer inscricao) {
		this.inscricao = inscricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cidadao getCidadao() {
		return cidadao;
	}

	public void setCidadao(Cidadao cidadao) {
		this.cidadao = cidadao;
	}

	public Integer getTamanho() {
		return tamanho;
	}

	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

	public Integer getIptu() {
		return iptu;
	}

	public void setIptu(Integer iptu) {
		this.iptu = iptu;
	}

	public void calculaIPTU() {
		this.iptu = this.tamanho * 27;// 27 BRL por m2, podia comparar com regioes anos anterior varias ideias
	}

	@Override
	public String toString() {
		return ("ID-" + id_imovel.toString() + " | " + inscricao + ", " + endereco + ", " + cep + ", "
				+ tamanho.toString());
	}

}
