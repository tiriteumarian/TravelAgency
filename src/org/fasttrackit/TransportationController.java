package org.fasttrackit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.fasttrackit.DAO.DestinationDAO;
import org.fasttrackit.DAO.TransportationDAO;
import org.fasttrackit.pojo.Destination;
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
public class TransportationController {

	@RequestMapping(value = "transportations.htm")
	public ModelAndView getTransportations() throws SQLException {
		ModelMap model = new ModelMap();
		TransportationDAO trdao = new TransportationDAO();
		ArrayList<Transportation> transportations = trdao.getTransportations();
		Map<Integer, String> fromDestinationMap = new LinkedHashMap<>();
		Map<Integer, String> toDestinationMap = new LinkedHashMap<>();
		DestinationDAO dedao = new DestinationDAO();
		
		for (Transportation transportation: transportations) {
			Destination fromDestination = dedao.getDestinationById(Integer.toString(transportation.getFromDestinationId()));
			Destination toDestination = dedao.getDestinationById(Integer.toString(transportation.getToDestinationId()));
			fromDestinationMap.put(Integer.valueOf(transportation.getId()), fromDestination.getAllDestination());
			toDestinationMap.put(Integer.valueOf(transportation.getId()), toDestination.getAllDestination());
		}
		
		model.put("transportations", transportations);
		model.put("fromDestinationMap", fromDestinationMap);
		model.put("toDestinationMap", toDestinationMap);

		return new ModelAndView("travel/transportations", "model", model);
	}

	@RequestMapping(value = "transportationDetails/{transportationId}")
	public ModelAndView getTransportationDetails(@PathVariable String transportationId) throws SQLException {
		TransportationDAO trdao = new TransportationDAO();
		Transportation transportation = trdao.getTransportationById(transportationId);
		ModelMap model = new ModelMap();
		
		DestinationDAO dedao = new DestinationDAO();
		
		Destination fromDestination = dedao.getDestinationById(Integer.toString(transportation.getFromDestinationId()));
		Destination toDestination = dedao.getDestinationById(Integer.toString(transportation.getToDestinationId()));
		
		model.put("fromDestination", fromDestination.getAllDestination());
		model.put("toDestination", toDestination.getAllDestination());
		model.put("transportation", transportation);
		
		return new ModelAndView("travel/transportationDetails", "model", model);
	}

	@RequestMapping(value = "transportationadd.htm")
	public ModelAndView displayAddForm(Model model) {

		Transportation tr = new Transportation();
		model.addAttribute("transportationForm", tr);
		model.addAttribute("formAction", "create");
		
		DestinationDAO dedao = new DestinationDAO();
		
		try	{
			ArrayList<Destination> destinations = dedao.getDestinations();
			model.addAttribute("destinations", destinations);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("travel/transportationadd", "model", model);
	}

	@RequestMapping(value = "transportationadd.htm", method = RequestMethod.POST)
	public ModelAndView transportationAdd(@ModelAttribute("transportationForm") Transportation transportation,
			ModelMap model, BindingResult result) {
		try {

			TransportationDAO trdao = new TransportationDAO();
			trdao.createTransportation(transportation);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("redirect:/transportations.htm");

	}

	@RequestMapping(value = "editTransportation/{transportationId}")
	public ModelAndView displayEditForm(@PathVariable String transportationId, Model model) throws SQLException {

		TransportationDAO trdao = new TransportationDAO();
		Transportation tr = trdao.getTransportationById(transportationId);
		model.addAttribute("transportationForm", tr);
		
		DestinationDAO dedao = new DestinationDAO();
		
		try	{
			ArrayList<Destination> destinations = dedao.getDestinations();
			model.addAttribute("destinations", destinations);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("travel/transportationEdit", "model", model);
	}

	@RequestMapping(value = "transportationEdit.htm", method = RequestMethod.POST)
	public ModelAndView transportationEdit(@ModelAttribute("transportationForm") Transportation transportation,
			ModelMap model, BindingResult result) {
		try {
			TransportationDAO trdao = new TransportationDAO();
			trdao.updateTransportation(transportation);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("redirect:/transportations.htm");
	}

	@RequestMapping(value = "/deleteTransportation/{transportationId}")
	public ModelAndView deleteTransportation(@PathVariable String transportationId, Model model)
			throws NumberFormatException, SQLException {
		TransportationDAO trdao = new TransportationDAO();
		trdao.delete(Integer.parseInt(transportationId));

		return new ModelAndView("redirect:/transportations.htm");
	}
}
