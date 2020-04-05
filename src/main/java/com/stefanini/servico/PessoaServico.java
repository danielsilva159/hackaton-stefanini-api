package com.stefanini.servico;

import com.stefanini.dao.PessoaDao;
import com.stefanini.exception.NegocioException;
import com.stefanini.model.Endereco;
import com.stefanini.model.Pessoa;

import javax.ejb.*;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * 
 * Classe de servico, as regras de negocio devem estar nessa classe
 * 
 * @author joaopedromilhome
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PessoaServico implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaDao dao;

	@Inject
	private PessoaPerfilServico pessoaPerfilServico;
	
	@Inject
	private EnderecoServico enderecoServico;

	/**
	 * Salvar os dados de uma Pessoa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Pessoa salvar(@Valid Pessoa pessoa) {
		
		Pessoa pessoaSalva = dao.salvar(pessoa);
		 
		List<Endereco> enderecos = new ArrayList<>();
		
		 for (Endereco enderecoSalvo : enderecos) {

				enderecoSalvo.setPessoa(pessoaSalva.getId()); 
				
				enderecoServico.salvar(enderecoSalvo);
				
				}
		 return pessoaSalva;
	}
	/**
	 * Validando se existe pessoa com email
	 */
	public Boolean validarPessoa(@Valid Pessoa pessoa){
		if(pessoa.getId() != null){
			Optional<Pessoa> encontrar = dao.encontrar(pessoa.getId());
			if(encontrar.get().getEmail().equals(pessoa.getEmail())){
				return Boolean.TRUE;
			}
		}
		Optional<Pessoa> pessoa1 = dao.buscarPessoaPorEmail(pessoa.getEmail());
		return pessoa1.isEmpty();
	}

	/**
	 * Atualizar o dados de uma pessoa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Pessoa atualizar(@Valid Pessoa entity) {
		return dao.atualizar(entity);
	}

	/**
	 * Remover uma pessoa pelo id
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(@Valid Long id) throws NegocioException {
		if(pessoaPerfilServico.buscarPessoaPerfil(id,null).count() == 0){
			dao.remover(id);
			return;
		}
		throw new NegocioException("Não foi possivel remover a pessoa");
	}

	/**
	 * Buscar uma lista de Pessoa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Optional<List<Pessoa>> getList() {
		return dao.getList();
	}

	/**
	 * Buscar uma Pessoa pelo ID
	 */
//	@Override
	public Optional<TypedQuery<Pessoa>> encontrarPessoas(Long id) {
		
		return dao.encontrarPessoa(id);
	}
	
	public Optional<List<Pessoa>> ListaPessoas(){
		return dao.pessoasCompletos();
	}

}
