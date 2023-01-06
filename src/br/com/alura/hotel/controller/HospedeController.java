package br.com.alura.hotel.controller;

import java.sql.Connection;
import java.util.List;

import br.com.alura.hotel.dao.HospedeDAO;
import br.com.alura.hotel.factory.ConnectionFactory;
import br.com.alura.hotel.modelo.Hospede;

public class HospedeController {
	
	private HospedeDAO hospedeDAO;

	public HospedeController() {
		Connection connection = new ConnectionFactory().connection();
		this.hospedeDAO = new HospedeDAO(connection);
	}

	public void salvar(Hospede hospede) {
		this.hospedeDAO.salvar(hospede);
	}

	public List<Hospede> listar() {
		return this.hospedeDAO.listar();
	}

	public void deletar(Integer id) {
		this.hospedeDAO.deletar(id);
	}

	public void alterar(Hospede hospede) {
		this.hospedeDAO.alterar(hospede);
	}

	public List<Hospede> pesquisar(String pesquisa) {
		return this.hospedeDAO.pesquisar(pesquisa);
	}
}
