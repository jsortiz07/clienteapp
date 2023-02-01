package com.appjohan.clienteapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.appjohan.clienteapp.models.entity.Ciudad;
import com.appjohan.clienteapp.models.entity.Cliente;
import com.appjohan.clienteapp.models.service.ICiudadService;
import com.appjohan.clienteapp.models.service.IClienteService;

@Controller
@RequestMapping("/views/clientes")
public class ClienteController {

	private final Logger LOG = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private ICiudadService ciudadService;

	@GetMapping("/")
	public String listarClientes(Model model) {
		List<Cliente> listadoClientes = clienteService.ListarAll();

		model.addAttribute("titulo", "Lista de clientes");
		model.addAttribute("clientes", listadoClientes);

		return "/views/clientes/listar";
	}

	@GetMapping("/create")
	public String crearCliente(Model model) {
		Cliente cliente = new Cliente();
		List<Ciudad> listCiudades = ciudadService.ListaCiudades(); // se guarda una lista de ciudades

		model.addAttribute("Titulo", "Nuevo cliente"); // con esta linea se envia un titulo a la vista
		model.addAttribute("clientes", cliente);
		model.addAttribute("ciudades", listCiudades);
		return "/views/clientes/formcliente";
	}

	@PostMapping("/save")
	public String guardarCliente(@Valid @ModelAttribute Cliente cliente, BindingResult result, Model model) {

		List<Ciudad> listCiudades = ciudadService.ListaCiudades(); // se guarda una lista de ciudades

		if (result.hasErrors()) { // si encuentra errores

			model.addAttribute("Titulo", "Nuevo cliente"); // con esta linea se envia un titulo a la vista
			model.addAttribute("clientes", cliente);
			model.addAttribute("ciudades", listCiudades);
			LOG.info("Existen errores");
			return "/views/clientes/formcliente";

		}
		clienteService.guardar(cliente);
		LOG.info("Usuario recibido {}", cliente);
		return "redirect:/views/clientes/";
	}

	@GetMapping("/edit/{id}")
	public String editCliente(@PathVariable("id") Long idCliente, Model model) { // @PathVariable sirve para enviar el
																					// id a la vista
		
		Cliente cliente = null;

		// validacion en caso de que el usuario no existe
		if (idCliente > 0) {
			cliente = clienteService.SearchById(idCliente);
			if (cliente == null) {
				LOG.info("Error:El ID del cliente no existe ");
				return "redirect:/views/clientes/";
			}
		} else {
			LOG.info("Error con el id cliente");
			return "redirect:/views/clientes/";
		}

		List<Ciudad> listCiudades = ciudadService.ListaCiudades(); // se guarda una lista de ciudades

		model.addAttribute("titulo", " Editar cliente");
		model.addAttribute("clientes", cliente);
		model.addAttribute("ciudades", listCiudades);
		LOG.info("Existen errores en el formulario");

		return "/views/clientes/formcliente";
	}

	@GetMapping("/delete/{id}")
	public String deleteCliente(@PathVariable("id") Long idCliente, Model model) {

		Cliente cliente = null;

		// validacion en caso de que el usuario no existe
		
		if (idCliente > 0) {
			cliente = clienteService.SearchById(idCliente);
			if (cliente == null) {
				LOG.info("Error:El ID del cliente no existe ");
				return "redirect:/views/clientes/";
			}
		} else {
			LOG.info("Error con el id cliente");
			return "redirect:/views/clientes/";
		}

		clienteService.eliminar(idCliente);

		return "redirect:/views/clientes/";
	}

}
