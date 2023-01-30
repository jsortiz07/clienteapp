package com.appjohan.clienteapp.models.service;

import java.util.List;

import com.appjohan.clienteapp.models.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> ListarAll();
	public void guardar(Cliente cliente);
	public Cliente SearchById(Long id);
	public void eliminar(Long id);

}
