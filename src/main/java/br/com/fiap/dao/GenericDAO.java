package br.com.fiap.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaQuery;

/**
 * Classe abstrata que será estendida pelos objetos que serão persistidos.
 * Implementa algumas funções básicas como salvar(create), recuperar(read),
 * listar (read), excluir (delete)
 * 
 * 
 * @author Ana Sofia Nunez de Abreu, Bruno Vieira Campos Gouveia, Rafael
 *         Kimihiro Moribe, Tiago Vieira Cavalcante
 * 
 */
public abstract class GenericDAO<E, C> {
	protected Class<E> classEntidade;
	protected EntityManager em;

	public GenericDAO(EntityManager em) {
		this.em = em;
		this.classEntidade = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	/**
	 * Salva o objeto.
	 * 
	 * @param chave
	 */
	public void salvar(E entidade) {
		this.em.persist(entidade);

	}

	/**
	 * Recupera o objeto indicado pela chave persistido no banco.
	 * 
	 * @param chave
	 */
	public E recuperar(C chave) {
		return em.find(classEntidade, chave);
	}

	/**
	 * Exclui o objeto indicado pela chave persistido no banco.
	 * 
	 * @param chave
	 */
	public void excluir(C chave) {
		E entidadeExcluir = this.recuperar(chave);
		if (entidadeExcluir == null) {
			throw new IllegalArgumentException(
					"Nenhum Registro de " + this.classEntidade.getSimpleName() + "para chave digitada");

		}
		this.em.remove(entidadeExcluir);
	}

	/**
	 * Lista os objetos persistidos no Banco de dados.
	 * @return retona uma Lista (List<Classe>) dos objetos persistidos no banco
	 */
	public List<E> listar() {
		CriteriaQuery<E> query = this.em.getCriteriaBuilder().createQuery(this.classEntidade);
		return this.em.createQuery(query.select(query.from(this.classEntidade))).getResultList();

	}

}
