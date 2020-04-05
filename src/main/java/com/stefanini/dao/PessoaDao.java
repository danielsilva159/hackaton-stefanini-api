package com.stefanini.dao;

import com.stefanini.dao.abstracao.GenericDao;
import com.stefanini.model.Endereco;
import com.stefanini.model.Pessoa;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
/**
 * O Unico objetivo da Dao 
 * @author joaopedromilhome
 *
 */
public class PessoaDao extends GenericDao<Pessoa, Long> {

	public PessoaDao() {
		super(Pessoa.class);
	}
	
	@SuppressWarnings("unchecked")
	public 	Optional<List<Pessoa>> pessoasCompletos(){
		
		
		return Optional.of(getEntityManager().createQuery(
				"select distinct p from Pessoa p left join fetch p.enderecos left join fetch p.perfils " +
				"ORDER BY p.nome", Pessoa.class).getResultList());

		
//		// Pessoa pessoa = new Pessoa();
//		// pessoa.setEnderecos(enderecos);;
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
//		Root<Pessoa> entityPessoa = cq.from(Pessoa.class);
//		Root<Endereco> entityEndereco = cq.from(Endereco.class);
//		cq.multiselect(entityPessoa, entityEndereco);
//		cq.where(cb.equal(entityEndereco.get("pessoa"), entityPessoa.get("id")));
//		Query query = entityManager.createQuery(cq);
//		return query.getResultList();
//		cq.select(entityPessoa);
//		Root<Endereco> entityRoot = cq.from(Endereco.class);
//		cq.where(cb.equal(entityPessoa.get("id"), entityRoot.get("idPessoa")));
//		return ;
//		//TypedQuery<Pessoa> q1 = cb.createQuery(""); 
		//List<Pessoa>q1 = entityManager.createQuery("select from Pessoa ").getResultList();
		
	//return q1;
		
//		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
//		
//
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Endereco> query = builder.createQuery(Endereco.class);
//		query.from(Endereco.class);
//		Root<Endereco> employee = query.from(Endereco.class);
//		 employee.fetch(Endereco_.complemento, JoinType.INNER);
//		query.select(employee);
//		TypedQuery<Endereco> typedQuery = entityManager.createQuery(query);
//        List<Endereco> resultList = typedQuery.getResultList();
//        
//        for (Endereco e : resultList) {
//            System.out.println(e.getComplemento());
//        }
		
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Pessoa> cq = cb.createQuery(Pessoa.class);
//		Root<Pessoa> root = cq.from(Pessoa.class);
//		Join<Object, Object> book = root.join(Pessoa.);
//		 
//		ParameterExpression<String> pTitle = cb.parameter(String.class);
//		cq.where(cb.equal(root.get(Author_.LAST_NAME), pTitle));
//		 
//		TypedQuery<Author> q = em.createQuery(cq);
//		q.setParameter(pTitle, "%Hibernate%");
//		List<Author> authors = q.getResultList();
		
		
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Pessoa> cq = cb.createQuery(Pessoa.class);
//		Root<Pessoa> player = cq.from(Pessoa.class);
//        Join<Pessoa, Endereco> team = player.join(Pessoa_.);
        

        // Get MetaModel from Root
        //EntityType<Player> Player_ = player.getModel();

        // set the where clause
//        cq.where(cb.equal(league.get(League_.sport), sport));
//        cq.select(player).distinct(true);
//        TypedQuery<Player> q = em.createQuery(cq);
//        players = q.getResultList();
        
	//	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Pessoa> query = builder.createQuery(Pessoa.class);
//        Root<Pessoa> usuarioRoot = query.from(Pessoa.class);
//        
//        CriteriaQuery<Endereco> queryEnd = builder.createQuery(Endereco.class);
//        Root<Endereco> enderecoRoot = query.from(Endereco.class);
//
//        Predicate predicate = builder.and();
//
////        if (filter.getId() != null)
//            predicate = builder.and(predicate, builder.equal(usuarioRoot.<Long>get("id"), 1));
//
//        if (!filter.getNome().isEmpty())
//            predicate = builder.and(predicate, builder.like(usuarioRoot.<String>get("nome"), "%" + filter.getNome() + "%"));

    //Adicionado join
    //usuarioRoot.join("enderecos");

        //TypedQuery<Pessoa> typedQuery = entityManager.createQuery(
        //Adicionado .distinct(true)
      //          query.select(usuarioRoot).where(predicate.in(enderecoRoot.<Long>get("idPessoa"), 1))
                //queryEnd.select(usuarioRoot).where(predicate)
            //);

       // return typedQuery.getResultList();
		//return getEntityManager().createQuery(query).getResultList();
		
		
//				CriteriaBuilder cb =  getEntityManager().getCriteriaBuilder();// pega cb do Entity Manager
//				CriteriaQuery<Pessoa> cQuery = cb.createQuery(Pessoa.class);
//				Root<Endereco> entidadeRoot = cQuery.from(Endereco.class);
//				Path<String> pathAtributo1 = entidadeRoot.<String>get("logradouro");
//				TypedQuery<Pessoa> query1 = entityManager.createQuery(cQuery.select(cb.construct(Pessoa.class, pathAtributo1)));
//				
//				return query1.getResultList();
	 
	//	TypedQuery<Pessoa> q2 =
		//		entityManager.createQuery(" select from Pessoa", Pessoa.class);

		//return q2.getResultList();
		
		
	}

	/**
	 * Efetuando busca de Pessoa por email
	 * @param email
	 * @return
	 */
	public Optional<Pessoa> buscarPessoaPorEmail(String email){
		TypedQuery<Pessoa> q2 =
				entityManager.createQuery(" select p from Pessoa p where p.email=:email", Pessoa.class);
		q2.setParameter("email", email);
		return q2.getResultStream().findFirst();
	}
	
	public Optional<TypedQuery<Pessoa>> encontrarPessoa(Long id) {
		return Optional.of(getEntityManager().createQuery(
				"select distinct p from Pessoa p left join fetch p.enderecos  where p.id = p.enderecos.id" 
				, Pessoa.class));
	}
	

}
