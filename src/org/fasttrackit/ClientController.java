package org.fasttrackit;

import java.sql.SQLException;
import java.util.ArrayList;

import org.fasttrackit.DAO.ClientDAO;
import org.fasttrackit.pojo.Client;
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
public class ClientController {

	@RequestMapping(value = "clients.htm")
	public ModelAndView getClients() throws SQLException {
		ModelMap model = new ModelMap();
		ClientDAO cldao = new ClientDAO();
		ArrayList<Client> clients = cldao.getClients();
		model.put("clients", clients);

		return new ModelAndView("travel/clients", "model", model);
	}

	@RequestMapping(value = "clientDetails/{clientId}")
	public ModelAndView getClientDetails(@PathVariable String clientId) throws SQLException {
		ClientDAO cldao = new ClientDAO();
		Client client = cldao.getClientById(clientId);
		ModelMap model = new ModelMap();
		model.put("client", client);
		return new ModelAndView("travel/clientDetails", "model", model);
	}

	@RequestMapping(value = "clientadd.htm")
	public ModelAndView displayAddForm(Model model) {

		Client cl = new Client();
		model.addAttribute("clientForm", cl);
		return new ModelAndView("travel/clientadd", "model", model);
	}

	@RequestMapping(value = "clientadd.htm", method = RequestMethod.POST)
	public ModelAndView clientAdd(@ModelAttribute("clientForm") Client client, ModelMap model, BindingResult result) {
		try {

			ClientDAO cldao = new ClientDAO();
			cldao.createClient(client);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("redirect:/clients.htm");
	}

	@RequestMapping(value = "editClient/{clientId}")
	public ModelAndView displayEditForm(@PathVariable String clientId, Model model) throws SQLException {

		ClientDAO cldao = new ClientDAO();
		Client cl = cldao.getClientById(clientId);
		model.addAttribute("clientForm", cl);

		return new ModelAndView("travel/clientEdit", "model", model);
	}

	@RequestMapping(value = "clientEdit.htm", method = RequestMethod.POST)
	public ModelAndView clientEdit(@ModelAttribute("clientForm") Client client,
			ModelMap model, BindingResult result) {
		try {
			ClientDAO cldao = new ClientDAO();
			cldao.updateClient(client);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("redirect:/clients.htm");
	}

	@RequestMapping(value = "/deleteClient/{clientId}")
	public ModelAndView deleteClient(@PathVariable String clientId, Model model)
			throws NumberFormatException, SQLException {
		ClientDAO cldao = new ClientDAO();
		cldao.delete(Integer.parseInt(clientId));

		return new ModelAndView("redirect:/clients.htm");
	}
}
