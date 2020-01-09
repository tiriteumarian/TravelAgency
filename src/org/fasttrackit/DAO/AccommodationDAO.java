package org.fasttrackit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.fasttrackit.helper.DBHelper;
import org.fasttrackit.pojo.Accommodation;

public class AccommodationDAO {

	public void createAccommodation(Accommodation ac) throws SQLException {
		Connection con = DBHelper.getConnection();

		String insertString = "INSERT INTO accommodation (name, type, price, contact, details) VALUES (?, ?, ?, ?, ?);";
		PreparedStatement stmt = con.prepareStatement(insertString);

		stmt.setString(1, ac.getName());
		stmt.setString(2, ac.getType());
		stmt.setDouble(3, ac.getPrice());
		stmt.setString(4, ac.getContact());
		stmt.setString(5, ac.getDetails());
		stmt.executeUpdate();

		DBHelper.closeConnection(con);

	}

	public ArrayList<Accommodation> getAccommodations() throws SQLException {
		Connection con = DBHelper.getConnection();
		String selectString = "select * from accommodation";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(selectString);

		ArrayList<Accommodation> result = new ArrayList<Accommodation>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String type = rs.getString("type");
			Double price = rs.getDouble("price");
			String contact = rs.getString("contact");
			String details = rs.getString("details");
			Accommodation ac = new Accommodation(id, name, type, price, contact, details);
			result.add(ac);
		}
		DBHelper.closeConnection(con);
		return result;

	}

	public Accommodation getAccommodationById(int accommodationId) throws SQLException {
		Connection con = DBHelper.getConnection();
		String selectString = "select * from accommodation where id=?";
		PreparedStatement stmt = con.prepareStatement(selectString);
		stmt.setInt(1, accommodationId);
		ResultSet rs = stmt.executeQuery();
		Accommodation result = null;
		if (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String type = rs.getString("type");
			Double price = rs.getDouble("price");
			String contact = rs.getString("contact");
			String details = rs.getString("details");
			result = new Accommodation(id, name, type, price, contact, details);
		}
		DBHelper.closeConnection(con);
		return result;
	}

	public void updateAccommodation(Accommodation accommodation) throws SQLException {
		Connection con = DBHelper.getConnection();
		String updateString = "UPDATE accommodation SET name=?, type=?, price=?, contact=?, details=? WHERE id=?";
		PreparedStatement stmt = con.prepareStatement(updateString);
		stmt.setString(1, accommodation.getName());
		stmt.setString(2, accommodation.getType());
		stmt.setDouble(3, accommodation.getPrice());
		stmt.setString(4, accommodation.getContact());
		stmt.setString(5, accommodation.getDetails());
		stmt.setInt(6, accommodation.getId());
		System.out.println(stmt.toString());

		stmt.executeUpdate();

		DBHelper.closeConnection(con);
	}

	public void delete(int accommodationId) throws SQLException {
		Connection con = DBHelper.getConnection();
		String deleteString = "DELETE FROM accommodation WHERE id=?";
		PreparedStatement stmt = con.prepareStatement(deleteString);
		stmt.setInt(1, accommodationId);
		stmt.executeUpdate();

		DBHelper.closeConnection(con);
	}


}
