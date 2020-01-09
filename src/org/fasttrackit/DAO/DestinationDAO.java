package org.fasttrackit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.fasttrackit.helper.DBHelper;
import org.fasttrackit.pojo.Destination;

public class DestinationDAO {

	public void createDestination(Destination de) throws SQLException {
		Connection con = DBHelper.getConnection();

		String insertString = "INSERT INTO destination (country, region, city, description, image_path) VALUES (?, ?, ?, ?, ?);";
		PreparedStatement stmt = con.prepareStatement(insertString);

		stmt.setString(1, de.getCountry());
		stmt.setString(2, de.getRegion());
		stmt.setString(3, de.getCity());
		stmt.setString(4, de.getDescription());
		stmt.setString(5, de.getImagePath());
		stmt.executeUpdate();

		DBHelper.closeConnection(con);

	}

	public ArrayList<Destination> getDestinations() throws SQLException {
		Connection con = DBHelper.getConnection();
		String selectString = "select * from destination";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(selectString);

		ArrayList<Destination> result = new ArrayList<Destination>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String country = rs.getString("country");
			String region = rs.getString("region");
			String city = rs.getString("city");
			String description = rs.getString("description");
			String imagePath = rs.getString("image_Path");
			Destination de = new Destination(id, country, region, city, description, imagePath);
			result.add(de);
		}
		DBHelper.closeConnection(con);
		return result;
	}

	public Destination getDestinationById(String destinationId) throws SQLException {
		Connection con = DBHelper.getConnection();
		String selectString = "select * from destination where id=?";
		PreparedStatement stmt = con.prepareStatement(selectString);
		stmt.setString(1, destinationId);
		ResultSet rs = stmt.executeQuery();
		Destination result = null;
		if (rs.next()) {
			int id = rs.getInt("id");
			String country = rs.getString("country");
			String region = rs.getString("region");
			String city = rs.getString("city");
			String description = rs.getString("description");
			String imagePath = rs.getString("image_path");
			result = new Destination(id, country, region, city, description, imagePath);
		}

		DBHelper.closeConnection(con);
		return result;
	}

	public void updateDestination(Destination destination) throws SQLException {
		Connection con = DBHelper.getConnection();
		String updateString = "UPDATE destination SET country=?, region=?, city=?, description=?, image_path=? WHERE id=?";
		PreparedStatement stmt = con.prepareStatement(updateString);
		stmt.setString(1, destination.getCountry());
		stmt.setString(2, destination.getRegion());
		stmt.setString(3, destination.getCity());
		stmt.setString(4, destination.getDescription());
		stmt.setString(5, destination.getImagePath());
		stmt.setInt(6, destination.getId());

		stmt.executeUpdate();

		DBHelper.closeConnection(con);
	}

	public void delete(int destinationId) throws SQLException {
		Connection con = DBHelper.getConnection();
		String deleteString = "DELETE FROM destination WHERE id=?";
		PreparedStatement stmt = con.prepareStatement(deleteString);
		stmt.setInt(1, destinationId);
		stmt.executeUpdate();

		DBHelper.closeConnection(con);
	}
}
