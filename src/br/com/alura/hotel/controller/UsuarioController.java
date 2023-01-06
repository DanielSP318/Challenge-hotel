package br.com.alura.hotel.controller;

import java.sql.Connection;

import br.com.alura.hotel.dao.UsuarioDAO;
import br.com.alura.hotel.factory.ConnectionFactory;

public class UsuarioController {

	private UsuarioDAO usuarioDAO;

	public UsuarioController(String usuario, String password) {
		Connection connection = new ConnectionFactory().connection();
		this.usuarioDAO = new UsuarioDAO(connection, usuario, password);
	}

	public boolean login() {
		return this.usuarioDAO.login();
	}
}
