package br.com.alura.hotel.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	private DataSource dataSource;

	public ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/banco_hotel?useTimezone=true&serverTimezone=UTC"); //Coloque o nome do seu banco de dados no localhost ;D
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("sua senha aqui");

		this.dataSource = comboPooledDataSource;
	}

	public Connection connection() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
