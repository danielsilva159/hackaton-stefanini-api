package com.stefanini.dao;

import com.stefanini.dao.abstracao.GenericDao;
import com.stefanini.model.Perfil;
import com.stefanini.model.Pessoa;
import com.stefanini.model.PessoaPerfil;

import javax.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

/**
 * O Unico objetivo da Dao 
 * @author joaopedromilhome
 *
 */
public class PerfilDao extends GenericDao<Perfil, Long> {

	public PerfilDao() {
		super(Perfil.class);
	}

	/**
	 * Efetuando busca de Pessoa por email
	 * @param nome
	 * @return
	 */
	public Optional<Perfil> buscarPessoaPorNome(String nome){
		TypedQuery<Perfil> q2 =
				entityManager.createQuery(" select p from Perfil p where p.nome=:nome", Perfil.class);
		q2.setParameter("nome", nome);
		return q2.getResultStream().findFirst();
	}
	
	//Eu que criei
	/*public Optional<List<PessoaPerfil>> buscarPessoaEPerfil(){
		TypedQuery<Perfil> q2 = entityManager.createQuery("select p from PessoaPerfil p",Pessoa.class);
		
		return q2.getResultStream();
	}*/

}
