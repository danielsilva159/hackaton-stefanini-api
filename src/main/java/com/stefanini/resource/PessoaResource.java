package com.stefanini.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.stefanini.dto.ErroDto;
import com.stefanini.exception.NegocioException;
import com.stefanini.model.Pessoa;
import com.stefanini.model.PessoaPerfil;
import com.stefanini.servico.PessoaPerfilServico;
import com.stefanini.servico.PessoaServico;

@Path("pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaResource {

	private static Logger log = Logger.getLogger(PessoaResource.class.getName());

	/**
	 * Classe de servico da Pessoa
	 */
	@Inject
	private PessoaServico pessoaServico;
	/**
	 *
	 */
	@Inject
	private PessoaPerfilServico pessoaPerfilServico;
	@Context
	private UriInfo uriInfo;


	/**
	 *
	 * @return
	 */
	
	
	@GET
	public Response obterPessoas() {
		log.info("Obtendo lista de pessoas");
	
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
		Optional<List<Pessoa>> listPessoa = pessoaServico.ListaPessoas();
		return listPessoa.map(pessoas -> Response.ok(pessoas).build()).orElseGet(() -> Response.status(Status.NOT_FOUND).build());

	}

	
	/**
	 *
	 * @param pessoa
	 * @return
	 */
	@POST
	public Response adicionarPessoa(@Valid Pessoa pessoa) {
		if(pessoaServico.validarPessoa(pessoa)){
			
			return Response.ok(pessoaServico.salvar(pessoa)).build();
		}
		return Response.status(Status.METHOD_NOT_ALLOWED).entity(new ErroDto("email","email já existe", pessoa.getEmail())).build();
	}


	/**
	 *
	 * @param pessoa
	 * @return
	 */
	@PUT
	public Response atualizarPessoa(@Valid Pessoa pessoa) {
		if(pessoaServico.validarPessoa(pessoa)){
			return Response.ok(pessoaServico.atualizar(pessoa)).build();
		}
		return Response.status(Status.METHOD_NOT_ALLOWED).entity(new ErroDto("email","email já existe", pessoa.getEmail())).build();
	}


	/**
	 *
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("{id}")
	public Response deletarPessoa(@PathParam("id") Long id) {
		try{
			if(pessoaServico.encontrarPessoas(id).isPresent()){
				pessoaServico.remover(id);
				return Response.status(Response.Status.OK).build();
			}else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (NegocioException e) {
			return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(new ErroDto(null,e.getMensagem(),id)).build();
		}
	}


	/**
	 *
	 * @param id
	 * @return
	 */
	@GET
	@Path("{id}")
	public Response obterPessoa(@PathParam("id") Long id) {
		return pessoaServico.encontrarPessoas(id).map(pessoas -> Response.ok(pessoas).build()).orElseGet(() -> Response.status(Status.NOT_FOUND).build());
	}
	
	@GET
	@Path("/perfilPessoa/{idPessoa,idPerfil}")
	public Response obterPessoasPerfis(@PathParam("idPessoa") Long idPessoa, @PathParam("idPerfil") Long idPerfil) {
		log.info("Obtendo lista de pessoas");
		//MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
		//Optional<List<PessoaPerfil>> listPessoa = pessoaPerfilServico.getList();
		Stream<PessoaPerfil> pessoa = pessoaPerfilServico.buscarPessoaPerfil(idPessoa, idPerfil);
		return Response.ok(pessoa.getClass()).build();
	}
}
