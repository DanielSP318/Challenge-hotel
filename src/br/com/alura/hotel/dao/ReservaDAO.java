package br.com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.hotel.modelo.Reserva;

public class ReservaDAO {
	
	private Connection connection;

	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Reserva> listar() {
		try {
			List<Reserva> reservas = new ArrayList<>();
			String sql = "SELECT * FROM RESERVAS";
			try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
				pstm.execute();
				resultEmReserva(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> pesquisar(String pesquisa) {
		try {
			String sql = "SELECT * FROM RESERVAS WHERE ID = ?";
			List<Reserva> reservas = new ArrayList<>();
			try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
				pstm.setString(1, pesquisa);
				pstm.execute();
				resultEmReserva(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void reserva(Reserva reserva) {
		String sql = "INSERT INTO RESERVAS (DATA_ENTRADA, DATA_SAIDA, VALOR, FORMA_PAGAMENTO) VALUES (?, ?, ?, ?);";
		try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, reserva.getDataEntrada());
			preparedStatement.setString(2, reserva.getDataSaida());
			preparedStatement.setDouble(3, reserva.getValor());
			preparedStatement.setString(4, reserva.getFormaDePagamento());
			preparedStatement.execute();

			try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
				while (resultSet.next()) {
					reserva.setId(resultSet.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deletar(Integer id) {
		try {
			String sql = "DELETE FROM RESERVAS WHERE ID = ?";
			try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, id);
				preparedStatement.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterar(Reserva reserva) {
		try {
			String sql = "UPDATE RESERVAS SET DATA_ENTRADA = ?, DATA_SAIDA = ?, VALOR = ?, FORMA_PAGAMENTO = ? WHERE ID = ?";
			try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
				preparedStatement.setString(1, reserva.getDataEntrada());
				preparedStatement.setString(2, reserva.getDataSaida());
				preparedStatement.setDouble(3, reserva.getValor());
				preparedStatement.setString(4, reserva.getFormaDePagamento());
				preparedStatement.setInt(5, reserva.getId());
				preparedStatement.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void resultEmReserva(List<Reserva> reservas, PreparedStatement preparedStatement) {
		try (ResultSet result = preparedStatement.getResultSet()) {
			while (result.next()) {
				Reserva reserva = new Reserva(result.getInt(1), result.getString(2), result.getString(3),
						result.getDouble(4), result.getString(5));
				reservas.add(reserva);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
