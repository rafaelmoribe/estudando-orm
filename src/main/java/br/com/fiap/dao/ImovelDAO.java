package br.com.fiap.dao;

import br.com.fiap.prefeitura.Imovel;
import jakarta.persistence.EntityManager;

/**
 * Classe que herda da Classe GenericDAO, usada para persistir objetos da Classe
 * Imóvel. Implementa algumas funções básicas como salvar(create),
 * recuperar(read), listar (read), excluir (delete)
 * 
 * @author Ana Sofia Nunez de Abreu, Bruno Vieira Campos Gouveia, Rafael
 *         Kimihiro Moribe, Tiago Vieira Cavalcante
 *
 */

public class ImovelDAO extends GenericDAO<Imovel, Integer> {

	public ImovelDAO(EntityManager em) {
		// TODO Auto-generated constructor stub

		super(em);
	}

}
