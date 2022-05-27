package br.com.fiap.dao;

import br.com.fiap.prefeitura.Persona;
import jakarta.persistence.EntityManager;

/**
 * Classe que herda da Classe GenericDAO, usada para persistir objetos da Classe
 * Persona. Implementa algumas funções básicas como salvar(create),
 * recuperar(read), listar (read), excluir (delete)
 * 
 * @author Ana Sofia Nunez de Abreu, Bruno Vieira Campos Gouveia, Rafael
 *         Kimihiro Moribe, Tiago Vieira Cavalcante
 *
 */

public class PersonaDAO extends GenericDAO<Persona, Integer> {
	public PersonaDAO(EntityManager em) {
		super(em);

	}

}
