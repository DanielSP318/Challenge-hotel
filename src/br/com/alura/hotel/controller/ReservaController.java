package br.com.alura.hotel.controller;

import java.sql.Connection;
import java.util.List;

import br.com.alura.hotel.dao.ReservaDAO;
import br.com.alura.hotel.factory.ConnectionFactory;
import br.com.alura.hotel.modelo.Reserva;

public class ReservaController {

	private ReservaDAO reservaDAO;

	public ReservaController() {
		Connection connection = new ConnectionFactory().connection();
		this.reservaDAO = new ReservaDAO(connection);
	}

	public void reserva(Reserva reserva) {
		this.reservaDAO.reserva(reserva);
	}

	public List<Reserva> listar() {
		return this.reservaDAO.listar();
	}

	public void deletar(Integer id) {
		this.reservaDAO.deletar(id);
	}

	public void alterar(Reserva reserva) {
		this.reservaDAO.alterar(reserva);
	}

	public List<Reserva> pesquisar(String pesquisa) {
		return this.reservaDAO.pesquisar(pesquisa);
	}
}
