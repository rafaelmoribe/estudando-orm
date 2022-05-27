package br.com.fiap.dao;

import br.com.fiap.prefeitura.Cidadao;
import jakarta.persistence.EntityManager;

/**
 * Classe que herda da Classe GenericDAO, usada para persistir objetos da Classe
 * Cidad�o. Implementa algumas fun��es b�sicas como salvar(create),
 * recuperar(read), listar (read), excluir (delete)
 * 
 * @author Ana Sofia Nunez de Abreu, Bruno Vieira Campos Gouveia, Rafael
 *         Kimihiro Moribe, Tiago Vieira Cavalcante
 *
 */

public class CidadaoDAO extends GenericDAO<Cidadao, Integer> {
	public CidadaoDAO(EntityManager em) {
		super(em);

	}

}
