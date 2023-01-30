package com.appjohan.clienteapp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appjohan.clienteapp.models.entity.Cliente;
import com.appjohan.clienteapp.models.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public List<Cliente> ListarAll() {
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	public void guardar(Cliente cliente) {
		
		clienteRepository.save(cliente);
		
	}

	@Override
	public Cliente SearchById(Long id) {
		// TODO Auto-generated method stub
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Long id) {

		clienteRepository.deleteById(id);
	}

}
