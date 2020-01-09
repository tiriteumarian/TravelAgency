package org.fasttrackit;

import java.sql.SQLException;
import java.util.ArrayList;

import org.fasttrackit.DAO.DestinationDAO;
import org.fasttrackit.pojo.Destination;
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
public class DestinationController {

	@RequestMapping(value = "destinations.htm")
	public ModelAndView getDestinations() throws SQLException {
		ModelMap model = new ModelMap();
		DestinationDAO dedao = new DestinationDAO();
		ArrayList<Destination> destinations = dedao.getDestinations();
		model.put("destinations", destinations);

		return new ModelAndView("travel/destinations", "model", model);
	}

	@RequestMapping(value = "destinationDetails/{destinationId}")
	public ModelAndView getDestinationDetails(@PathVariable String destinationId) throws SQLException {
		DestinationDAO dedao = new DestinationDAO();
		Destination destination = dedao.getDestinationById(destinationId);
		ModelMap model = new ModelMap();
		model.put("destination", destination);
		return new ModelAndView("travel/destinationDetails", "model", model);
	}

	@RequestMapping(value = "destinationadd.htm")
	public ModelAndView displayAddForm(Model model) {

		Destination de = new Destination();
		model.addAttribute("destinationForm", de);

		return new ModelAndView("travel/destinationadd", "model", model);
	}
	
	@RequestMapping(value="destinationadd.htm", method=RequestMethod.POST)
	public ModelAndView destinationAdd (@ModelAttribute ("destinationForm") Destination destination, ModelMap model, BindingResult result) {
		try {
			
			DestinationDAO dedao = new DestinationDAO();
			dedao.createDestination(destination);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("redirect:/destinations.htm");
	}
	@RequestMapping(value = "editDestination/{destinationId}")
	public ModelAndView displayEditForm(@PathVariable String destinationId, Model model) throws SQLException {

		DestinationDAO dedao = new DestinationDAO();
		Destination de = dedao.getDestinationById(destinationId);
		model.addAttribute("destinationForm", de);

		return new ModelAndView("travel/destinationEdit", "model", model);
	}
		
		@RequestMapping(value = "destinationEdit.htm", method = RequestMethod.POST)
		public ModelAndView destinationEdit(@ModelAttribute("destinationForm") Destination destination,
				ModelMap model, BindingResult result) {
			try {
				DestinationDAO dedao = new DestinationDAO();
				dedao.updateDestination(destination);
				System.out.println(destination.getId());
				System.out.println(destination.getCountry());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return new ModelAndView("redirect:/destinations.htm");
	}
	@RequestMapping(value = "/deleteDestination/{destinationId}")
	public ModelAndView deleteDestination
	(@PathVariable String destinationId, Model model)
			throws NumberFormatException, SQLException {
		DestinationDAO dedao = new DestinationDAO();
		dedao.delete(Integer.parseInt(destinationId));
		
		return new ModelAndView("redirect:/destinations.htm");
	}
}
