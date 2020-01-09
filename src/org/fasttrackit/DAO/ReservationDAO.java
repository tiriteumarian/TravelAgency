package org.fasttrackit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.fasttrackit.helper.DBHelper;
import org.fasttrackit.pojo.Reservation;


public class ReservationDAO {

	public void createReservation(Reservation re) throws SQLException {
		Connection con = DBHelper.getConnection();

		String insertString = "INSERT INTO reservation (client_id, destination_id, transportation_id, accommodation_id) VALUES (?, ?, ?, ?);";
		PreparedStatement stmt = con.prepareStatement(insertString);

		stmt.setInt(1, re.getClientId());
		stmt.setInt(2, re.getDestinationId());
		stmt.setInt(3, re.getTransportationId());
		stmt.setInt(4, re.getAccommodationId());
		stmt.executeUpdate();
		System.out.println("am salvat cu succes");

		DBHelper.closeConnection(con);

	}

	public ArrayList<Reservation> getReservations() throws SQLException {
		Connection con = DBHelper.getConnection();
		String selectString = "select * from reservation";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(selectString);

		ArrayList<Reservation> result = new ArrayList<Reservation>();
		while (rs.next()) {
			int id = rs.getInt("id");
			int clientId = rs.getInt("client_id");
			int destinationId = rs.getInt("destination_id");
			int transportationId = rs.getInt("transportation_id");
			int accommodationId = rs.getInt("accommodation_id");
			Reservation ac = new Reservation(id, clientId, destinationId, transportationId, accommodationId);
			result.add(ac);
		}
		DBHelper.closeConnection(con);
		return result;
	}

	public Reservation getReservationById(String reservationId) throws SQLException {
		Connection con = DBHelper.getConnection();
		String selectString = "select * from reservation where id=?";
		PreparedStatement stmt = con.prepareStatement(selectString);
		stmt.setString(1, reservationId);
		ResultSet rs = stmt.executeQuery();
		Reservation result = null;
		if (rs.next()) {
			int id = rs.getInt("id");
			int clientId = rs.getInt("client_id");
			int destinationId = rs.getInt("destination_id");
			int transportationId = rs.getInt("transportation_id");
			int accommodationId = rs.getInt("accommodation_id");
			result = new Reservation(id, clientId, destinationId, transportationId, accommodationId);

		}
		DBHelper.closeConnection(con);
		return result;
	}

	public void updateReservation(Reservation reservation) throws SQLException {
		Connection con = DBHelper.getConnection();
		String updateString = "UPDATE reservation SET client_id=?, destination_id=?, transportation_id=?, accommodation_id=? WHERE id=?";
		PreparedStatement stmt = con.prepareStatement(updateString);
		
		stmt.setInt(1, reservation.getClientId());
		stmt.setInt(2, reservation.getDestinationId());
		stmt.setInt(3, reservation.getTransportationId());
		stmt.setInt(4, reservation.getAccommodationId());
		stmt.setInt(5, reservation.getId());
		
		System.out.println(stmt.toString());

		stmt.executeUpdate();

		DBHelper.closeConnection(con);
	}

	public void delete(int destinationId) throws SQLException {
		Connection con = DBHelper.getConnection();
		String deleteString = "DELETE FROM reservation WHERE id=?";
		PreparedStatement stmt = con.prepareStatement(deleteString);
		stmt.setInt(1, destinationId);
		stmt.executeUpdate();
		System.out.println("am sters cu succes");

		DBHelper.closeConnection(con);
	}
	
	
}
