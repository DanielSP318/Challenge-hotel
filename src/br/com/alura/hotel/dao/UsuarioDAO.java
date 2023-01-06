package br.com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
	
	private String usuario;
	private String password;
	private Connection connection;

	public UsuarioDAO(Connection connection, String usuario, String password) {
		this.connection = connection;
		this.usuario = usuario;
		this.password = password;
	}

	public boolean login() {
		try {
			String sql = "SELECT * FROM USUARIO WHERE USUARIO = ? AND PASSWORD = ?";
			try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
				preparedStatement.setString(1, this.usuario);
				preparedStatement.setString(2, this.password);
				preparedStatement.execute();
				return logged(preparedStatement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean logged(PreparedStatement preparedStatement) {
		boolean logged = false;
		try (ResultSet resultSet = preparedStatement.getResultSet()) {
			while (resultSet.next()) {
				if (this.usuario.equals(resultSet.getString(2)) && this.password.equals(resultSet.getString(3)))
					logged = true;
			}
			return logged;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
