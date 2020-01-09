package org.fasttrackit;

import java.sql.SQLException;
import java.util.ArrayList;
import org.fasttrackit.DAO.AccommodationDAO;
import org.fasttrackit.pojo.Accommodation;
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
public class AccommodationController {

	@RequestMapping(value = "accommodations.htm")
	public ModelAndView getAccommodations() throws SQLException {
		ModelMap model = new ModelMap();
		AccommodationDAO acdao = new AccommodationDAO();
		ArrayList<Accommodation> accommodations = acdao.getAccommodations();
		System.out.println(accommodations.size());
		model.put("accommodations", accommodations);

		return new ModelAndView("travel/accommodations", "model", model);
	}

	@RequestMapping(value = "accommodationDetails/{accommodationId}")
	public ModelAndView getAccommodationDetails(@PathVariable String accommodationId) throws SQLException {
		AccommodationDAO acdao = new AccommodationDAO();
		Accommodation accommodation = acdao.getAccommodationById(Integer.parseInt(accommodationId));
		ModelMap model = new ModelMap();
		model.put("accommodation", accommodation);
		return new ModelAndView("travel/accommodationDetails", "model", model);
	}

	@RequestMapping(value = "accommodationadd.htm")
	public ModelAndView displayAddForm(Model model) {

		Accommodation ac = new Accommodation();
		model.addAttribute("accommodationForm", ac);
		model.addAttribute("formAction", "create");

		return new ModelAndView("travel/accommodationadd", "model", model);
	}

	@RequestMapping(value = "accommodationadd.htm", method = RequestMethod.POST)
	public ModelAndView accommodationAdd(@ModelAttribute("accommodationForm") Accommodation accommodation,
			ModelMap model, BindingResult result) {
		try {

			AccommodationDAO acdao = new AccommodationDAO();
			acdao.createAccommodation(accommodation);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("redirect:/accommodations.htm");
	}

	@RequestMapping(value = "editAccommodation/{accommodationId}")
	public ModelAndView displayEditForm(@PathVariable String accommodationId, Model model) throws SQLException {

		AccommodationDAO acdao = new AccommodationDAO();
		Accommodation ac = acdao.getAccommodationById(Integer.parseInt(accommodationId));
		model.addAttribute("accommodationForm", ac);
		return new ModelAndView("travel/accommodationEdit", "model", model);
	}

	@RequestMapping(value = "accommodationEdit.htm", method = RequestMethod.POST)
	public ModelAndView accommodationEdit(@ModelAttribute("accommodationForm") Accommodation accommodation,
			ModelMap model, BindingResult result) {
		try {
			System.out.println(accommodation);
			System.out.println(accommodation.getId());
			System.out.println(accommodation.getType());
			System.out.println(accommodation.getDetails());
			AccommodationDAO acdao = new AccommodationDAO();
			acdao.updateAccommodation(accommodation);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("redirect:/accommodations.htm");
	}

	@RequestMapping(value = "/deleteAccommodation/{accommodationId}")
	public ModelAndView deleteAccommodation
	(@PathVariable String accommodationId, Model model)
			throws NumberFormatException, SQLException {
		AccommodationDAO acdao = new AccommodationDAO();
		acdao.delete(Integer.parseInt(accommodationId));
		
		return new ModelAndView("redirect:/accommodations.htm");
	}
}

