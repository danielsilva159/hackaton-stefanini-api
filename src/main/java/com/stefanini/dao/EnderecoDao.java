package com.stefanini.dao;

import java.util.List;

import javax.management.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.stefanini.dao.abstracao.GenericDao;
import com.stefanini.model.Endereco;

/**
 * O Unico objetivo da Dao 
 * @author joaopedromilhome
 *
 */
public class EnderecoDao extends GenericDao<Endereco, Long> {

	public EnderecoDao() {
		super(Endereco.class);
	}
	
	public List<Endereco> ListEnderecosPessoa(Long idPessoa){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Endereco> criteriaQuery = builder.createQuery(Endereco.class);
		Root<Endereco> root = criteriaQuery.from(Endereco.class);
		//root.join("idPessoa");
		
		criteriaQuery.where(builder.equal(root.get("idPessoa"), idPessoa));
		
		TypedQuery<Endereco> resultList = entityManager.createQuery(criteriaQuery);
		
		return resultList.getResultList();
		 
	}

}
