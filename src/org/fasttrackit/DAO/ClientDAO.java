package org.fasttrackit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.fasttrackit.helper.DBHelper;
import org.fasttrackit.pojo.Client;

public class ClientDAO {

	public void createClient(Client cl) throws SQLException {
		Connection con = DBHelper.getConnection();

		String insertString = "INSERT INTO client (first_name, last_name, phone, email) VALUES (?, ? , ? , ?);";
		PreparedStatement stmt = con.prepareStatement(insertString);
		stmt.setString(1, cl.getFirstName());
		stmt.setString(2, cl.getLastName());
		stmt.setString(3, cl.getPhone());
		stmt.setString(4, cl.getEmail());
		stmt.executeUpdate();

		DBHelper.closeConnection(con);

	}

	public ArrayList<Client> getClients() throws SQLException {
		Connection con = DBHelper.getConnection();
		String selectString = "select * from client";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(selectString);

		ArrayList<Client> result = new ArrayList<Client>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String phone = rs.getString("phone");
			String email = rs.getString("email");
			Client cl = new Client(id, firstName, lastName, phone, email);
			result.add(cl);
		}
		DBHelper.closeConnection(con);
		return result;
	}

	public Client getClientById(String clientId) throws SQLException {
		Connection con = DBHelper.getConnection();
		String selectString = "select * from client where id=?";
		PreparedStatement stmt = con.prepareStatement(selectString);
		stmt.setString(1, clientId);
		ResultSet rs = stmt.executeQuery();
		Client result = null;
		if (rs.next()) {
			int id = rs.getInt("id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String phone = rs.getString("phone");
			String email = rs.getString("email");
			result = new Client(id, firstName, lastName, phone, email);
		}
		DBHelper.closeConnection(con);
		return result;
	}

	public void updateClient(Client client) throws SQLException {
		Connection con = DBHelper.getConnection();
		String updateString = "UPDATE client SET first_name=?, last_name=?, phone=?, email=? WHERE id=?";
		PreparedStatement stmt = con.prepareStatement(updateString);
		stmt.setString(1, client.getFirstName());
		stmt.setString(2, client.getLastName());
		stmt.setString(3, client.getPhone());
		stmt.setString(4, client.getEmail());
		stmt.setInt(5, client.getId());

		stmt.executeUpdate();

		DBHelper.closeConnection(con);

	}

	public void delete(int clientId) throws SQLException {
		Connection con = DBHelper.getConnection();
		String deleteString = "DELETE FROM client WHERE id=?";
		PreparedStatement stmt = con.prepareStatement(deleteString);
		stmt.setInt(1, clientId);
		stmt.executeUpdate();

		DBHelper.closeConnection(con);
	}
}
