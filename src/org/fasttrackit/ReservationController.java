package org.fasttrackit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.fasttrackit.DAO.AccommodationDAO;
import org.fasttrackit.DAO.ClientDAO;
import org.fasttrackit.DAO.DestinationDAO;
import org.fasttrackit.DAO.ReservationDAO;
import org.fasttrackit.DAO.TransportationDAO;
import org.fasttrackit.pojo.Accommodation;
import org.fasttrackit.pojo.Client;
import org.fasttrackit.pojo.Destination;
import org.fasttrackit.pojo.Reservation;
import org.fasttrackit.pojo.Transportation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReservationController {

	@RequestMapping(value = "reservations.htm")
	public ModelAndView getReservations() throws SQLException {
		ModelMap model = new ModelMap();
		ReservationDAO redao = new ReservationDAO();
		ArrayList<Reservation> reservations = redao.getReservations();
		Map<Integer, String> clientIdMap = new LinkedHashMap<>();
		Map<Integer, String> destinationIdMap = new LinkedHashMap<>();
		Map<Integer, String> transportationIdMap = new LinkedHashMap<>();
		Map<Integer, String> accommodationIdMap = new LinkedHashMap<>();
		DestinationDAO dedao = new DestinationDAO();
		AccommodationDAO acdao = new AccommodationDAO();
		ClientDAO cldao = new ClientDAO ();
		TransportationDAO trdao = new TransportationDAO();
		
		for (Reservation reservation: reservations) {
			Client client = cldao.getClientById(Integer.toString(reservation.getClientId()));
			Accommodation accommodation = acdao.getAccommodationById(reservation.getAccommodationId());
			Destination destination = dedao.getDestinationById(Integer.toString(reservation.getDestinationId()));
			Transportation transportation = trdao.getTransportationById(Integer.toString(reservation.getTransportationId()));
			clientIdMap.put(Integer.valueOf(reservation.getId()), client.getFullName());
			accommodationIdMap.put(Integer.valueOf(reservation.getId()), accommodation.getType());
			destinationIdMap.put(Integer.valueOf(reservation.getId()), destination.getAllDestination());
			transportationIdMap.put(Integer.valueOf(reservation.getId()), transportation.getType());
		}
		
		model.put("reservations", reservations);
		model.put("clientIdMap", clientIdMap);
		model.put("accommodationIdMap", accommodationIdMap);
		model.put("destinationIdMap", destinationIdMap);
		model.put("transportationIdMap", transportationIdMap);
		
		return new ModelAndView("travel/reservations", "model", model);
	}


	@RequestMapping(value = "reservationDetails/{reservationId}")
	public ModelAndView getReservationDetails(@PathVariable String reservationId) throws SQLException {
		ReservationDAO redao = new ReservationDAO();
		Reservation reservation = redao.getReservationById(reservationId);
		ModelMap model = new ModelMap();

		DestinationDAO dedao = new DestinationDAO();
		AccommodationDAO acdao = new AccommodationDAO();
		ClientDAO cldao = new ClientDAO ();
		TransportationDAO trdao = new TransportationDAO();
		
		Client client = cldao.getClientById(Integer.toString(reservation.getClientId()));
		Accommodation accommodation = acdao.getAccommodationById(reservation.getAccommodationId());
		Destination destination = dedao.getDestinationById(Integer.toString(reservation.getDestinationId()));
		Transportation transportation = trdao.getTransportationById(Integer.toString(reservation.getTransportationId()));
		
		model.put("client", client.getFullName());
		model.put("accommodation", accommodation.getType());
		model.put("destination", destination.getAllDestination());
		model.put("transportation", transportation.getType());
		
		return new ModelAndView("travel/reservationDetails", "model", model);
	}

	@RequestMapping(value = "reservationadd.htm")
	public ModelAndView displayAddForm(Model model) {

		Reservation re = new Reservation();
		model.addAttribute("reservationForm", re);
		
		ClientDAO cldao = new ClientDAO();
		AccommodationDAO acdao = new AccommodationDAO();
		DestinationDAO dedao = new DestinationDAO();
		TransportationDAO trdao = new TransportationDAO();
		
		try {
			ArrayList<Accommodation> accommodations = acdao.getAccommodations();
			model.addAttribute("accommodations", accommodations);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try	{
			ArrayList<Destination> destinations = dedao.getDestinations();
			model.addAttribute("destinations", destinations);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try	{
			ArrayList<Transportation> transportations = trdao.getTransportations();
			model.addAttribute("transportations", transportations);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ArrayList<Client> clients = cldao.getClients();
			model.addAttribute("clients", clients);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("travel/reservationadd", "model", model);
	}

	@RequestMapping(value = "reservationadd.htm", method = RequestMethod.POST)
	public ModelAndView reservatinoAdd(@ModelAttribute("reservationForm") Reservation reservation, ModelMap model,
			BindingResult result) {
		try {

			ReservationDAO redao = new ReservationDAO();
			System.out.println("pun clientidul acum");
			System.out.println(reservation.getClientId());
			redao.createReservation(reservation);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("redirect:/reservations.htm");
	}

	@RequestMapping(value = "editReservation/{reservationId}")
	public ModelAndView displayEditForm(@PathVariable String reservationId, Model model) throws SQLException {

		ReservationDAO redao = new ReservationDAO();
		Reservation re = redao.getReservationById(reservationId);
		model.addAttribute("reservationForm", re);
		
		ClientDAO cldao = new ClientDAO();
		AccommodationDAO acdao = new AccommodationDAO();
		DestinationDAO dedao = new DestinationDAO();
		TransportationDAO trdao = new TransportationDAO();
		
		try {
			ArrayList<Accommodation> accommodations = acdao.getAccommodations();
			model.addAttribute("accommodations", accommodations);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try	{
			ArrayList<Destination> destinations = dedao.getDestinations();
			model.addAttribute("destinations", destinations);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try	{
			ArrayList<Transportation> transportations = trdao.getTransportations();
			model.addAttribute("transportations", transportations);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ArrayList<Client> clients = cldao.getClients();
			model.addAttribute("clients", clients);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("travel/reservationEdit", "model", model);
	}

	@RequestMapping(value = "/deleteReservation/{reservationId}")
	public ModelAndView deleteReservation(@PathVariable String reservationId, Model model)
			throws NumberFormatException, SQLException {
		ReservationDAO redao = new ReservationDAO();
		redao.delete(Integer.parseInt(reservationId));

		return new ModelAndView("redirect:/reservations.htm");
	}
	@RequestMapping(value = "reservationEdit.htm", method = RequestMethod.POST)
	public ModelAndView reservationEdit(@ModelAttribute("reservationForm") Reservation reservation,
			ModelMap model, BindingResult result) {
		try {
			ReservationDAO redao = new ReservationDAO();
			redao.updateReservation(reservation);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("redirect:/reservations.htm");
	}
}
