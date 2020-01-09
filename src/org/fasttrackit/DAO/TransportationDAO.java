package org.fasttrackit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.fasttrackit.helper.DBHelper;
import org.fasttrackit.pojo.Transportation;

public class TransportationDAO {
	public void createTransportation(Transportation tr) throws SQLException {
		Connection con = DBHelper.getConnection();

		String insertString = "INSERT INTO transportation (type, company, contact, details, from_destination_id, to_destination_id) VALUES (?, ?, ?, ?, ?, ?);";
		PreparedStatement stmt = con.prepareStatement(insertString);

		stmt.setString(1, tr.getType());
		stmt.setString(2, tr.getCompany());
		stmt.setString(3, tr.getContact());
		stmt.setString(4, tr.getDetails());
		stmt.setInt(5, tr.getFromDestinationId());
		stmt.setInt(6, tr.getToDestinationId());
		stmt.executeUpdate();

		DBHelper.closeConnection(con);

	}

	public ArrayList<Transportation> getTransportations() throws SQLException {
		Connection con = DBHelper.getConnection();
		String selectString = "select * from transportation";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(selectString);

		ArrayList<Transportation> result = new ArrayList<Transportation>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String type = rs.getString("type");
			String company = rs.getString("company");
			String contact = rs.getString("contact");
			String details = rs.getString("details");
			int fromDestinationId = rs.getInt("from_destination_id");
			int toDestinationId = rs.getInt("to_destination_id");
			double price = rs.getDouble("price");
			Transportation tr = new Transportation(id, type, company, contact, details, fromDestinationId,
					toDestinationId, price);
			result.add(tr);
		}
		DBHelper.closeConnection(con);
		return result;
	}

	public Transportation getTransportationById(String transportationId) throws SQLException {
		Connection con = DBHelper.getConnection();
		String selectString = "select * from transportation where id=?";
		PreparedStatement stmt = con.prepareStatement(selectString);
		stmt.setString(1, transportationId);
		ResultSet rs = stmt.executeQuery();
		Transportation result = null;
		if (rs.next()) {
			int id = rs.getInt("id");
			String type = rs.getString("type");
			String company = rs.getString("company");
			String contact = rs.getString("contact");
			String details = rs.getString("details");
			int fromDestinationId = rs.getInt("from_destination_id");
			int toDestinationId = rs.getInt("to_destination_id");
			double price = rs.getDouble("price");
			result = new Transportation(id, type, company, contact, details, fromDestinationId, toDestinationId, price);
		}
		DBHelper.closeConnection(con);
		return result;
	}

	public void updateTransportation(Transportation transportation) throws SQLException {
		Connection con = DBHelper.getConnection();
		String updateString = "UPDATE transportation SET type=?, company=?, contact=?, details=?, from_destination_id=?, to_destination_id=?, price=? WHERE id=?";
		PreparedStatement stmt = con.prepareStatement(updateString);
		stmt.setString(1, transportation.getType());
		stmt.setString(2, transportation.getCompany());
		stmt.setString(3, transportation.getContact());
		stmt.setString(4, transportation.getDetails());
		stmt.setInt(5, transportation.getFromDestinationId());
		stmt.setInt(6, transportation.getToDestinationId());
		stmt.setDouble(7, transportation.getPrice());
		stmt.setInt(8, transportation.getId());

		stmt.executeUpdate();

		DBHelper.closeConnection(con);
	}

	public void delete(int transportationId) throws SQLException {
		Connection con = DBHelper.getConnection();
		String deleteString = "DELETE FROM transportation WHERE id=?";
		PreparedStatement stmt = con.prepareStatement(deleteString);
		stmt.setInt(1, transportationId);
		stmt.executeUpdate();

		DBHelper.closeConnection(con);
	}

}
